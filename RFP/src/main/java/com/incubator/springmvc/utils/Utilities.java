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
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ProgressEvent;
import com.amazonaws.services.s3.model.ProgressListener;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.incubator.springmvc.model.CustomUserDetails;

//Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//boolean authorized = authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));

public class Utilities {

	// private static final Logger logger =
	// LoggerFactory.getLogger(Utilities.class);
	public static boolean hasRole(String role) {
		// get security context from thread local
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null)
			return false;

		Authentication authentication = context.getAuthentication();
		if (authentication == null)
			return false;

		for (GrantedAuthority auth : authentication.getAuthorities()) {
			if (role.equals(auth.getAuthority()))
				return true;
		}

		return false;
	}

	public static CustomUserDetails getCurrentUserDetails() {
		// SecurityContext context = SecurityContextHolder.getContext();
		CustomUserDetails userDetails = null;
		// if (context != null){
		// Authentication authentication = context.getAuthentication();
		// if (authentication != null) {
		// userDetails = (CustomUserDetails) authentication.getPrincipal();
		// }
		// }

		// Authentication authentication = context.getAuthentication();
		// if (authentication == null)
		// return false;
		//
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// System.out.println("username "+authentication.getName());
		// System.out.println("username "+authentication.getAuthorities());
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			userDetails = (CustomUserDetails) authentication.getPrincipal();
		}
		// CustomUserDetails userDetails = (CustomUserDetails)
		// authentication.getPrincipal();
		/*
		 * userDetails.getId(); System.out.println("User has authorities: " +
		 * userDetails.getAuthorities());
		 */
		return userDetails;
	}

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

	public static File[] listFiles(String directoryName) {
		System.out.println("directory name " + directoryName);
		File directory = new File(directoryName);

		// get all the files from a directory
		if (directory.exists()) {
			File[] fList = directory.listFiles();
			for (File file : fList) {
				if (file.isFile()) {
					System.out.println(file.getName());
				}
			}
			return fList;
		}

		return new File[] {};
	}

	//This is used on file system.
	public static String getRootPath(){
		String rootPath = "";
		if(org.apache.commons.lang3.SystemUtils.IS_OS_LINUX){
			rootPath="/home/itadmin/Public/grace/logs";

		}else if(SystemUtils.IS_OS_WINDOWS){
			rootPath = System.getProperty("catalina.home");
		}
		//	 else if(SystemUtils.IS_OS_WINDOWS_2012)
		//	 logger.info("File root path is "+rootPath);
		return rootPath;
	}


	public static String accountTempDirectory() {
		String rootPath = getRootPath();
		System.out.println("rootpath: " + rootPath);
		String id = String.valueOf(Utilities.getCurrentUserDetails().getId());
		String subDirectory = File.separator + DeclareConstant.TEMP;
		String directory = rootPath + File.separator + id + subDirectory;
		return directory;
	}


	public static String changeAccountDirectory(UUID projectId) {
		String rootPath = getRootPath();
		String userId = String.valueOf(Utilities.getCurrentUserDetails().getId());
		String subDirectory = File.separator + String.valueOf(projectId);
		String directory = rootPath + File.separator + userId + subDirectory;
		return directory;
	}

	public static String accountTempImageDirectory() {
		return accountTempDirectory() + File.separator + DeclareConstant.IMAGE;
	}

	public static String accountTempAttachDirectory() {
		return accountTempDirectory() + File.separator + DeclareConstant.ATTACHMENTS;
	}

	public static String changeAccountImageDirectory(UUID projectId) {
		return changeAccountDirectory(projectId) + File.separator + DeclareConstant.IMAGE;
	}

	public static String changeAccountAttachDirectory(UUID projectId) {
		return changeAccountDirectory(projectId) + File.separator + DeclareConstant.ATTACHMENTS;
	}

	public static String getAccountDirectory(UUID creatorId, UUID projectId) {
		String rootPath = getRootPath();
		String userId = String.valueOf(creatorId);
		String subDirectory = File.separator + String.valueOf(projectId);
		String directory = rootPath + File.separator + userId + subDirectory;
		return directory;
	}

	public static String getAccountImageDirectory(UUID creatorId, UUID projectId) {

		return getAccountDirectory(creatorId, projectId) + File.separator + DeclareConstant.IMAGE;
	}

	public static String getAccountAttachDirectory(UUID creatorId, UUID projectId) {
		return getAccountDirectory(creatorId, projectId) + File.separator + DeclareConstant.ATTACHMENTS;
	}

	public static String getURLWithContextPath(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
	}

	/** * Display price in US Dollar currency * * @param price * @param rate */ 
	public static String showPriceInUSD(double price) { 
		double priceInUSD = price; 
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US); 
		return currencyFormat.format(priceInUSD);
	}
}
