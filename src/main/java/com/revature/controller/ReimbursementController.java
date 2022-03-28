package com.revature.controller;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController implements Controller {


    private Handler getBucket = ctx -> {
        Storage storage = (Storage) StorageOptions.getDefaultInstance().getService();

        String bucketName = "project-1-images";
        Bucket bucket = storage.create(BucketInfo.of(bucketName));

        ctx.json(bucket.getName());
    };



    @Override
    public void mapEndPoints(Javalin app) {
        app.get("/project-1/bucket", getBucket);
    }
}
