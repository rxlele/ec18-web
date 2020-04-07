package com.ingsw.utils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;


public class AmazonS3Example {
	
	private static final String SUFFIX = "/";
	
	public void upload(String absolutePath, String name,String folder, InputStream is, ObjectMetadata md) throws AmazonServiceException, AmazonClientException, IOException {
		// credentials object identifying user for authentication
		// user must have AWSConnector and AmazonS3FullAccess for 
		// this example to work
		AWSCredentials credentials = new BasicAWSCredentials(
				"AKIA4D4FBHAYFVNET6PZ", 
				"V81BqC0WlIEdEYJLUYrXnwXodxp6HlZ7yoStTLDY");
		
		
		// create a client connection based on credentials
		AmazonS3 s3client = new AmazonS3Client(credentials);
		
		// create bucket - name must be unique for all S3 users
		String bucketName = "elasticbeanstalk-us-east-2-832965785648";
		//s3client.createBucket(bucketName);
		/*
		// list buckets
		for (Bucket bucket : s3client.listBuckets()) {
			System.out.println(" - " + bucket.getName());
		}
		*/
		// create folder into bucket
		String folderName = folder;
		//createFolder(bucketName, folderName, s3client);
		
		// upload file to folder and set it to public
		String fileName = folderName + SUFFIX + name;
		
		s3client.putObject(new PutObjectRequest(bucketName, fileName, is,md)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		//deleteFolder(bucketName, folderName, s3client);
		
		// deletes bucket
		//s3client.deleteBucket(bucketName);
	}
	public void upload(String absolutePath, String name,String folder, File file) throws AmazonServiceException, AmazonClientException, IOException {
		// credentials object identifying user for authentication
		// user must have AWSConnector and AmazonS3FullAccess for 
		// this example to work
		AWSCredentials credentials = new BasicAWSCredentials(
				"AKIA4D4FBHAYFVNET6PZ", 
				"V81BqC0WlIEdEYJLUYrXnwXodxp6HlZ7yoStTLDY");
		
		
		// create a client connection based on credentials
		AmazonS3 s3client = new AmazonS3Client(credentials);
		
		// create bucket - name must be unique for all S3 users
		String bucketName = "elasticbeanstalk-us-east-2-832965785648";
		//s3client.createBucket(bucketName);
		/*
		// list buckets
		for (Bucket bucket : s3client.listBuckets()) {
			System.out.println(" - " + bucket.getName());
		}
		*/
		// create folder into bucket
		String folderName = folder;
		//createFolder(bucketName, folderName, s3client);
		
		// upload file to folder and set it to public
		String fileName = folderName + SUFFIX + name;
		
		s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		//deleteFolder(bucketName, folderName, s3client);
		
		// deletes bucket
		//s3client.deleteBucket(bucketName);
	}
	public static void createFolder(String bucketName, String folderName, AmazonS3 client) {
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
				folderName + SUFFIX, emptyContent, metadata);
		// send request to S3 to create folder
		client.putObject(putObjectRequest);
	}
	/**
	 * This method first deletes all the files in given folder and than the
	 * folder itself
	 */
	
}
