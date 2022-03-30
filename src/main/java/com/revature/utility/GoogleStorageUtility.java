package com.revature.utility;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.common.collect.Lists;
import com.revature.main.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GoogleStorageUtility {
    public static Logger logger = LoggerFactory.getLogger(Driver.class);
    private static final String GOOGLE_STORAGE_URL = "https://storage.googleapis.com/project-1-images";
    public static String uploadImage(String fileName ,InputStream file,String fileType) throws IOException {
        // The ID of your GCP project
        String projectId = "project-1-345401";

        // The ID of your GCS bucket
        String bucketName = "project-1-images";

        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(fileType).build();
        Blob blob = storage.create(blobInfo, file);
        return GOOGLE_STORAGE_URL+"/"+fileName;
    }

    private static Bucket getBucket(String bucketName) throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(System.getenv("GOOGLE_APPLICATION_CREDENTIALS")))
                .createScoped(Lists.newArrayList("https://storage.googleapis.com/project-1-images"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Bucket bucket = storage.get(bucketName);
        if (bucket == null) {
            throw new IOException("Bucket not found:"+bucketName);
        }
        return bucket;
    }

    public static boolean deleteImage(String fileLocation){
        // The ID of your GCP project
        String projectId = "project-1-345401";

        // The ID of your GCS bucket
        String bucketName = "project-1-images";

        String file = fileLocation.replace(GOOGLE_STORAGE_URL+"/","");

        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        return storage.delete(bucketName,file);
    }
}
