package com.revature.controller;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.revature.service.ReimbursementService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController implements Controller {

    ReimbursementService reimbursementService;

    public ReimbursementController(){
        reimbursementService = new ReimbursementService();
    }

    private Handler getBucket = ctx -> {
        Storage storage = (Storage) StorageOptions.getDefaultInstance().getService();

        String bucketName = "project-1-images";
        Bucket bucket = storage.create(BucketInfo.of(bucketName));

        ctx.json(bucket.getName());
    };

    private Handler getAllReimbursements = ctx -> {
        ctx.json(reimbursementService.getAllReimbursement());
    };


    private Handler editReimbursement = ctx -> {
        String id = ctx.formParam("id");


    };

    @Override
    public void mapEndPoints(Javalin app) {
        app.get("/project-1/bucket", getBucket);
        app.get("/project-1/reimbursements",getAllReimbursements);
    }
}
