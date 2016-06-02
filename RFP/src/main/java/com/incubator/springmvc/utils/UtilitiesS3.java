package com.incubator.springmvc.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.rds.model.DownloadDBLogFilePortionRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ProgressEvent;
import com.amazonaws.services.s3.model.ProgressListener;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.Copy;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.services.waf.model.GetByteMatchSetRequest;
import com.incubator.springmvc.model.CustomUserDetails;
import com.sun.mail.handlers.text_html;

//Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//boolean authorized = authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));

public class UtilitiesS3 {

	// private static final Logger logger =
	// LoggerFactory.getLogger(Utilities.class);

	public static String AddPrefixToFiles(String directory, String PREFIX) {
		// // VARIABLE SET UP.
		// String directory = "PATH-TO-DIRECTORY HERE!";
		// String PREFIX = "PREFIX HERE!";

		// No need to modify the code below.
		File[] files = new File(directory).listFiles();
		System.out.println("add prefix");
		String name = "";
		for (File file : files) {
			if (file.isFile()) {
				String ORIGINALNAME = file.getName();
				File NEWFILE = new File(directory + PREFIX + " " + ORIGINALNAME);
				boolean success = file.renameTo(NEWFILE);
				if (!success) {
					System.err.println("FAILED to rename " + file.getName());

				} else {
					name = file.getName();
					System.out.println(NEWFILE.getName());
				}
			}
		}
		return name;
	}

	public static File[] findFiles(String path, final String prefix) {

		File dir = new File(path);
		File[] foundFiles = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.startsWith(prefix);
			}
		});
		return foundFiles;
	}

	// public static String accountTempDirectory() {
	// String rootPath = System.getProperty("catalina.home");
	// String id = String.valueOf(Utilities.getCurrentUserDetails().getId());
	// String subDirectory = File.separator+DeclareConstant.TEMP;
	// String directory = rootPath + File.separator + id + subDirectory;
	// return directory;
	// }

	public static List<String> listFiles(String parentFolder) {
		List<String> fileNames = new ArrayList<String>();
		ObjectListing listing = UtilitiesS3.getS3Client().listObjects(DeclareConstant.BUCKET, parentFolder);
		List<S3ObjectSummary> summaries = listing.getObjectSummaries();

		while (listing.isTruncated()) {
			listing = UtilitiesS3.getS3Client().listNextBatchOfObjects(listing);
			summaries.addAll(listing.getObjectSummaries());
		}
		System.out.println(summaries);
		for (S3ObjectSummary summary : summaries) {

			String summaryKey = summary.getKey();
			// summaryKey = summaryKey.replace(parentFolder, "");
			fileNames.add(summaryKey);

			// System.out.println(summaryKey);

		}

		return fileNames;
	}

	public static List<String> listFileNames(String parentFolder) {
		List<String> fileNames = new ArrayList<String>();
		ObjectListing listing = UtilitiesS3.getS3Client().listObjects(DeclareConstant.BUCKET, parentFolder);
		List<S3ObjectSummary> summaries = listing.getObjectSummaries();

		while (listing.isTruncated()) {
			listing = UtilitiesS3.getS3Client().listNextBatchOfObjects(listing);
			summaries.addAll(listing.getObjectSummaries());
		}
		System.out.println(summaries);
		for (S3ObjectSummary summary : summaries) {

			String summaryKey = summary.getKey();
			summaryKey = summaryKey.replace(parentFolder, "");
			if (!summaryKey.equals("") && !summaryKey.equals("/")) {
				fileNames.add(summaryKey);
			}

			// System.out.println(summaryKey);

		}

		return fileNames;
	}

	// This is used on file system.
	// public static String getRootPath(){
	// String rootPath = "";
	// if(org.apache.commons.lang3.SystemUtils.IS_OS_LINUX){
	// rootPath="/home/itadmin/Public/grace/logs";
	//
	// }else if(SystemUtils.IS_OS_WINDOWS){
	// rootPath = System.getProperty("catalina.home");
	// }else if(SystemUtils.IS_OS_WINDOWS_2012)
	// logger.info("File root path is "+rootPath);
	// return rootPath;
	// }

	/* Amazon S3 methods */
	public static String getRootPath() {
		return "";

	}

	public static AmazonS3 getS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(DeclareConstant.ACCESSKEY,
				DeclareConstant.SECRETACCESSKEY);
		AmazonS3 s3client = new AmazonS3Client(credentials);
		return s3client;
	}

	public static TransferManager getTx() {
		TransferManager tx = new TransferManager(getS3Client());
		return tx;
	}

	public static void createFolder(String bucketName, String folderName, AmazonS3 client) {
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName + DeclareConstant.SUFFIX,
				emptyContent, metadata);
		// send request to S3 to create folder
		client.putObject(putObjectRequest);
	}

	public static void copyObjects(String srcFolder, String desFolder) {

		List<String> fileNames = listFileNames(srcFolder);
//		Copy copy = null;

		for (String fileName : fileNames) {
			AccessControlList srcAcl = getS3Client().getObjectAcl(DeclareConstant.BUCKET, srcFolder + fileName);
//			ObjectMetadata objectMetadata = getS3Client().getObjectMetadata(DeclareConstant.BUCKET,
//					srcFolder + fileName);
//			System.out.println("srcAcl" + srcAcl);
//			System.out.println("objectMetadata" + objectMetadata.getSSEAlgorithm());
			
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);

			CopyObjectRequest copyObjRequest = new CopyObjectRequest(
					DeclareConstant.BUCKET, srcFolder + fileName, DeclareConstant.BUCKET, desFolder + fileName);
//			objectMetadata.setServerSideEncryption(
//			          ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION); 
			             
			copyObjRequest.setNewObjectMetadata(objectMetadata);
			getTx().copy(copyObjRequest);
//			getS3Client().setObjectAcl(DeclareConstant.BUCKET, desFolder + fileName, srcAcl);
			System.out.println("files names" + desFolder + fileName);

//			S3Object s3Object = getS3Client()
//					.getObject(new GetObjectRequest(DeclareConstant.BUCKET, desFolder + fileName));
//			InputStream objectData = s3Object.getObjectContent();
//
//			objectMetadata.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
//			s3Object.setObjectMetadata(objectMetadata);
//			System.out.println(s3Object.getKey() +" "+ s3Object.getObjectMetadata().getServerSideEncryption());
		}

		// The copy method returns immediately as your data copies in the
		// background.
		// Use the returned transfer object to track the progress of the copy
		// operation.

		// Perform any work while the copy processes
//
//		if (copy.isDone()) {
//			System.out.println("Copy operation completed.");
//		}
	}
	
	public static byte[] getFileBytes(String fileName){
//		String fileName = "ceb546a2-e4ef-467a-a607-f81dcef39a01/f9f13873-df48-47c3-ac66-ca598abb41cc/attachments" +"/img_3.jpg";
		S3Object s3Object =UtilitiesS3.getS3Client()
				.getObject(new GetObjectRequest(DeclareConstant.BUCKET, fileName));
		InputStream inputStream = s3Object.getObjectContent();
		byte[] bytes = {};
		try {
			bytes = IOUtils.toByteArray(inputStream);
			System.out.println("bytes "+bytes.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * This method first deletes all the files in given folder and than the
	 * folder itself
	 */
	public static void deleteFolder(String folderName) {
		AmazonS3 client = UtilitiesS3.getS3Client();
		List<S3ObjectSummary> fileList = client.listObjects(DeclareConstant.BUCKET, folderName).getObjectSummaries();
		for (S3ObjectSummary file : fileList) {
			client.deleteObject(DeclareConstant.BUCKET, file.getKey());
		}
		client.deleteObject(DeclareConstant.BUCKET, folderName);
	}

	/**
	 * This method first deletes all the files in given folder
	 */
	public static void deleteFile(String fileName) {
		AmazonS3 client = UtilitiesS3.getS3Client();
		List<S3ObjectSummary> fileList = client.listObjects(DeclareConstant.BUCKET, fileName).getObjectSummaries();
		for (S3ObjectSummary file : fileList) {
			System.out.println(file.getKey());
			client.deleteObject(DeclareConstant.BUCKET, file.getKey());

		}
		// List<String> fileNames = listFiles(parentFolder);
		// for (String string : fileNames) {
		// System.out.println("filename"+string);
		// UtilitiesS3.getS3Client().deleteObject(DeclareConstant.BUCKET,string);
		// }

	}

	@SuppressWarnings("deprecation")
	public static void uploadFile(String fileName, File file) {
		// String fileName = folderName + DeclareConstant.SUFFIX +
		// "testvideo.mp4";
		// getS3Client().putObject(new PutObjectRequest(DeclareConstant.BUCKET,
		// fileName, file));
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
		InputStream inputStream = null;
		try {
			inputStream = FileUtils.openInputStream(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		;

		TransferManager tx = getTx();
		final Upload upload = tx.upload(DeclareConstant.BUCKET, fileName, inputStream, objectMetadata);
		// final Upload upload = tx.uplo
		if (upload.isDone() == false) {
			System.out.println("Transfer: " + upload.getDescription());
			System.out.println("  - State: " + upload.getState());
			System.out.println("  - Progress: " + upload.getProgress().getBytesTransferred());
		}

		// Transfers also allow you to set a <code>ProgressListener</code> to
		// receive
		// asynchronous notifications about your transfer's progress.
		// upload.addProgressListener(myProgressListener);

		// Or you can block the current thread and wait for your transfer to
		// to complete. If the transfer fails, this method will throw an
		// AmazonClientException or AmazonServiceException detailing the reason.
		// You can set a progress listener directly on a transfer, or you can
		// pass one into
		// the upload object to have it attached to the transfer as soon as it
		// starts
		upload.addProgressListener(new ProgressListener() {
			// This method is called periodically as your transfer progresses
			public void progressChanged(ProgressEvent progressEvent) {
				System.out.println(upload.getProgress().getPercentTransferred() + "%");

				if (progressEvent.getEventCode() == ProgressEvent.COMPLETED_EVENT_CODE) {
					System.out.println("Upload complete!!!");
				}
			}
		});

		// waitForCompletion blocks the current thread until the transfer
		// completes
		// and will throw an AmazonClientException or AmazonServiceException if
		// anything went wrong.
		try {
			upload.waitForCompletion();
			// getS3Client().setObjectAcl(DeclareConstant.BUCKET,fileName,CannedAccessControlList.PublicRead);
		} catch (AmazonServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AmazonClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// After the upload is complete, call shutdownNow to release the
		// resources.
		tx.shutdownNow();

	}

//	public static void setPublic(String fileName) {
//		getS3Client().setObjectAcl(DeclareConstant.BUCKET, fileName, CannedAccessControlList.PublicRead);
//	}

	public static S3ObjectInputStream downLoadFile(String fileName) {
		GetObjectRequest request = new GetObjectRequest(DeclareConstant.BUCKET, fileName);
		S3Object object = UtilitiesS3.getS3Client().getObject(request);
		S3ObjectInputStream objectContent = object.getObjectContent();
		return objectContent;
	}

	// public static void readS3File(String bucketName, String fileName){
	// S3Object s3object = getS3Client().getObject(new
	// GetObjectRequest(bucketName, fileName));
	// System.out.println(s3object.getObjectMetadata().getContentType());
	// System.out.println(s3object.getObjectMetadata().getContentLength());
	// BufferedReader reader = new BufferedReader(new
	// InputStreamReader(s3object.getObjectContent()));
	// }

	/* Amazon S3 methods */

	public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}

	public static String accountTempDirectory() {
		// String rootPath = getRootPath();
		// System.out.println("rootpath: " + rootPath);
		String id = String.valueOf(Utilities.getCurrentUserDetails().getId());
		String subDirectory = DeclareConstant.SUFFIX + DeclareConstant.TEMP;
		String directory = id + subDirectory;
		return directory;
	}

	public static String changeAccountDirectory(UUID projectId) {
		// String rootPath = getRootPath();
		String userId = String.valueOf(Utilities.getCurrentUserDetails().getId());
		String subDirectory = DeclareConstant.SUFFIX + String.valueOf(projectId);
		String directory = userId + subDirectory;
		return directory;
	}

	public static String accountTempImageDirectory() {
		return accountTempDirectory() + DeclareConstant.SUFFIX + DeclareConstant.IMAGE + DeclareConstant.SUFFIX;
	}

	public static String accountTempImageDirectoryWithoutSuffix() {
		return accountTempDirectory() + DeclareConstant.SUFFIX + DeclareConstant.IMAGE;
	}

	public static String accountTempAttachDirectory() {
		return accountTempDirectory() + DeclareConstant.SUFFIX + DeclareConstant.ATTACHMENTS + DeclareConstant.SUFFIX;
	}

	public static String accountTempAttachDirectoryWithoutSuffix() {
		return accountTempDirectory() + DeclareConstant.SUFFIX + DeclareConstant.ATTACHMENTS;
	}

	public static String changeAccountImageDirectory(UUID projectId) {
		return changeAccountDirectory(projectId) + DeclareConstant.SUFFIX + DeclareConstant.IMAGE+ DeclareConstant.SUFFIX;
	}

	public static String changeAccountAttachDirectory(UUID projectId) {
		return changeAccountDirectory(projectId) + DeclareConstant.SUFFIX + DeclareConstant.ATTACHMENTS+ DeclareConstant.SUFFIX;
	}

	public static String getAccountDirectory(UUID creatorId, UUID projectId) {
		// String rootPath = getRootPath();
		String userId = String.valueOf(creatorId);
		String subDirectory = DeclareConstant.SUFFIX + String.valueOf(projectId);
		String directory = userId + subDirectory;
		return directory;
	}

	public static String getAccountImageDirectory(UUID creatorId, UUID projectId) {

		return getAccountDirectory(creatorId, projectId) + DeclareConstant.SUFFIX + DeclareConstant.IMAGE + DeclareConstant.SUFFIX;
	}

	public static String getAccountImageDirectoryWithoutSuffix(UUID creatorId, UUID projectId) {

		return getAccountDirectory(creatorId, projectId) + DeclareConstant.SUFFIX + DeclareConstant.IMAGE;
	}
	public static String getAccountAttachDirectory(UUID creatorId, UUID projectId) {
		return getAccountDirectory(creatorId, projectId) + DeclareConstant.SUFFIX + DeclareConstant.ATTACHMENTS + DeclareConstant.SUFFIX;
	}
	
	public static String getAccountAttachDirectoryWithoutSuffix(UUID creatorId, UUID projectId) {
		return getAccountDirectory(creatorId, projectId) + DeclareConstant.SUFFIX + DeclareConstant.ATTACHMENTS;
	}

	public static String getURLWithContextPath(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
	}

}
