package com.revature.utility;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.common.collect.Lists;
import com.revature.main.Driver;
import io.javalin.http.UploadedFile;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GoogleStorageUtility {
    public static Logger logger = LoggerFactory.getLogger(Driver.class);
    private static final String GOOGLE_STORAGE_URL = System.getenv("gcp_bucket_project1");
    // The ID of your GCP project
    //String projectId = System.getenv("gcp_project1_id");

    // The ID of your GCS bucket
    //String bucketName = System.getenv("project1_bucket");

    public static boolean deleteImage(String fileLocation){
        // The ID of your GCP project
        String projectId = System.getenv("gcp_project1_id");

        // The ID of your GCS bucket
        String bucketName = System.getenv("project1_bucket");

        String file = fileLocation.replace(GOOGLE_STORAGE_URL+"/","");

        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        return storage.delete(bucketName,file);
    }


    public static String uploadImage(UploadedFile uploadedFile) throws IOException {
        Bucket bucket = getBucket(System.getenv("project1_bucket"));
        Blob blob = bucket.create(uploadedFile.getFilename(), uploadedFile.getContent(), uploadedFile.getContentType());
        logger.info("Blob Link:"+blob.getMediaLink());
        return GOOGLE_STORAGE_URL + "/" + uploadedFile.getFilename();
    }


    private static Bucket getBucket(String bucketName) throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(System.getenv("GOOGLE_APPLICATION_CREDENTIALS")))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Bucket bucket = storage.get(bucketName);
        if (bucket == null) {
            throw new IOException("Bucket not found:"+bucketName);
        }
        return bucket;
    }
}
