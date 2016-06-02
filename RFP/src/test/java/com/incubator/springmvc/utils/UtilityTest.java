package com.incubator.springmvc.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.transfer.Copy;

public class UtilityTest {

//	@Test
//	public void createS3Folder(){
//		String path = "test"+DeclareConstant.SUFFIX + "des" + DeclareConstant.SUFFIX ;
////		String path1 = "test1"+DeclareConstant.SUFFIX + DeclareConstant.TEMP;
//		UtilitiesS3.createFolder(DeclareConstant.BUCKET, path, UtilitiesS3.getS3Client());
////		UtilitiesS3.createFolder(DeclareConstant.BUCKET, path1, UtilitiesS3.getS3Client());
//		//	System.out.println(SystemUtils.IS_OS_WINDOWS_2012);
//	}
//
//	@Test
//	public void uploadFile(){
//		String fileName = "test"+DeclareConstant.SUFFIX + DeclareConstant.TEMP +DeclareConstant.SUFFIX  +"img_1.jpg";
//		
//		System.out.println(fileName);
//		UtilitiesS3.uploadFile(fileName, new File("C:\\Users\\tians\\Desktop\\images\\img_1.jpg"));
//		
//		
//
////		GetObjectMetadataRequest request2 = 
////				new GetObjectMetadataRequest(Utilities.getS3Client(), fileName);
////
////		ObjectMetadata metadata = s3client.getObjectMetadata(request2);
////
////		System.out.println("Encryption algorithm used: " + 
////				metadata.getSSEAlgorithm());
//	}
//	@Test
//	public void Copy(){
//		String sourceKey = "test"+DeclareConstant.SUFFIX + DeclareConstant.TEMP +DeclareConstant.SUFFIX;
//		String destinationKey ="test"+DeclareConstant.SUFFIX + "des" +DeclareConstant.SUFFIX;
//		UtilitiesS3.copyObjects(sourceKey, destinationKey);
//	}
	
//	@Test
//	public void getBytes(){
//		String fileName = "ceb546a2-e4ef-467a-a607-f81dcef39a01/f9f13873-df48-47c3-ac66-ca598abb41cc/attachments" +"/img_3.jpg";
//		S3Object s3Object =UtilitiesS3.getS3Client()
//				.getObject(new GetObjectRequest(DeclareConstant.BUCKET, fileName));
//		InputStream inputStream = s3Object.getObjectContent();
//		byte[] bytes = {};
//		try {
//			bytes = IOUtils.toByteArray(inputStream);
//			System.out.println("bytes "+bytes.length);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	
//	@Test
//	public void listFile(){
//		System.out.println(UtilitiesS3.listFileNames("ceb546a2-e4ef-467a-a607-f81dcef39a01/temp/images/"));
//	}
	
//	@Test
//	public void deleteFile(){
//		UtilitiesS3.deleteFile("ceb546a2-e4ef-467a-a607-f81dcef39a01/temp/images/");
//	}
}
