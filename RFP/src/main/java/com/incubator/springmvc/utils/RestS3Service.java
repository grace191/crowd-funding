//package com.incubator.springmvc.utils;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Map;
//
//import org.jets3t.service.MultipartUploadChunk;
//import org.jets3t.service.S3Service;
//import org.jets3t.service.S3ServiceException;
//import org.jets3t.service.ServiceException;
//import org.jets3t.service.VersionOrDeleteMarkersChunk;
//import org.jets3t.service.acl.AccessControlList;
//import org.jets3t.service.impl.rest.XmlResponsesSaxParser;
//import org.jets3t.service.model.BaseVersionOrDeleteMarker;
//import org.jets3t.service.model.LifecycleConfig;
//import org.jets3t.service.model.MultipartCompleted;
//import org.jets3t.service.model.MultipartPart;
//import org.jets3t.service.model.MultipartUpload;
//import org.jets3t.service.model.MultipleDeleteResult;
//import org.jets3t.service.model.NotificationConfig;
//import org.jets3t.service.model.S3BucketVersioningStatus;
//import org.jets3t.service.model.S3Object;
//import org.jets3t.service.model.StorageBucket;
//import org.jets3t.service.model.StorageObject;
//import org.jets3t.service.model.container.ObjectKeyAndVersion;
//import org.jets3t.service.security.AWSCredentials;
//import org.jets3t.service.security.ProviderCredentials;
//
//
//public class RestS3Service extends S3Service{
//	
//	protected RestS3Service(ProviderCredentials credentials) {
//		super(credentials);
//		// TODO Auto-generated constructor stub
//	}
//
////	String awsAccessKey = "YOUR_AWS_ACCESS_KEY";
////	String awsSecretKey = "YOUR_AWS_SECRET_KEY";
////	AWSCredentials awsCredentials = 
////	    new AWSCredentials(awsAccessKey, awsSecretKey);
////	
////	S3Service s3Service = new RestS3Service(awsCredentials);
//
//	@Override
//	protected void deleteBucketPolicyImpl(String arg0) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteLifecycleConfigImpl(String arg0) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public MultipleDeleteResult deleteMultipleObjectsWithMFAImpl(String arg0, ObjectKeyAndVersion[] arg1, String arg2,
//			String arg3, boolean arg4) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected String getBucketPolicyImpl(String arg0) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected S3BucketVersioningStatus getBucketVersioningStatusImpl(String arg0) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public LifecycleConfig getLifecycleConfigImpl(String arg0) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected NotificationConfig getNotificationConfigImpl(String arg0) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected boolean isRequesterPaysBucketImpl(String arg0) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	protected VersionOrDeleteMarkersChunk listVersionedObjectsChunkedImpl(String arg0, String arg1, String arg2,
//			long arg3, String arg4, String arg5, boolean arg6) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected BaseVersionOrDeleteMarker[] listVersionedObjectsImpl(String arg0, String arg1, String arg2, String arg3,
//			String arg4, long arg5) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected void multipartAbortUploadImpl(String arg0, String arg1, String arg2) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	protected MultipartCompleted multipartCompleteUploadImpl(String arg0, String arg1, String arg2,
//			List<MultipartPart> arg3) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected List<MultipartPart> multipartListPartsImpl(String arg0, String arg1, String arg2)
//			throws S3ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected MultipartUploadChunk multipartListUploadsChunkedImpl(String arg0, String arg1, String arg2, String arg3,
//			String arg4, Integer arg5, boolean arg6) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected MultipartUpload multipartStartUploadImpl(String arg0, String arg1, Map<String, Object> arg2,
//			AccessControlList arg3, String arg4, String arg5) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected MultipartPart multipartUploadPartCopyImpl(String arg0, String arg1, String arg2, Integer arg3,
//			String arg4, String arg5, Calendar arg6, Calendar arg7, String[] arg8, String[] arg9, Long arg10,
//			Long arg11, String arg12) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected MultipartPart multipartUploadPartImpl(String arg0, String arg1, Integer arg2, S3Object arg3)
//			throws S3ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected void setBucketPolicyImpl(String arg0, String arg1) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setLifecycleConfigImpl(String arg0, LifecycleConfig arg1) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	protected void setNotificationConfigImpl(String arg0, NotificationConfig arg1) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	protected void setRequesterPaysBucketImpl(String arg0, boolean arg1) throws S3ServiceException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	protected void updateBucketVersioningStatusImpl(String arg0, boolean arg1, boolean arg2, String arg3, String arg4)
//			throws S3ServiceException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	protected boolean isTargettingGoogleStorageService() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	protected boolean getDisableDnsBuckets() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	protected boolean getEnableServerSideEncryption() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	protected boolean getEnableStorageClasses() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public String getEndpoint() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected int getHttpPort() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	protected boolean getHttpsOnly() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	protected int getHttpsPort() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<String> getResourceParameterNames() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getRestHeaderPrefix() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getRestMetadataPrefix() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected String getSignatureIdentifier() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected String getVirtualPath() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected XmlResponsesSaxParser getXmlResponseSaxParser() throws ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected StorageBucket newBucket() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected StorageObject newObject() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
