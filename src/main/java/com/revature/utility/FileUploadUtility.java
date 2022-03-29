package com.revature.utility;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;
import com.revature.main.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUploadUtility {
    public static Logger logger = LoggerFactory.getLogger(Driver.class);

    public static String uploadImage(String fileName ,InputStream file,String fileType) throws IOException {
        Bucket bucket = getBucket("project-1-images");
        InputStream inputStream = file;
        Blob blob = bucket.create(fileName, inputStream, fileType);
        logger.info("Blob Link:"+blob.getMediaLink());
        return blob.getMediaLink();
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
}
