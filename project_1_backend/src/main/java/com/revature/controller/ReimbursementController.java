package com.revature.controller;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.revature.dto.ResponseReimbursementDTO;
import com.revature.service.JWTService;
import com.revature.service.ReimbursementService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.util.List;

public class ReimbursementController implements Controller {

    ReimbursementService reimbursementService;
    JWTService jwtService;

    public ReimbursementController(){
        reimbursementService = new ReimbursementService();
        jwtService=JWTService.getInstance();
    }

    private Handler getAllReimbursements = ctx -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = jwtService.parseJWT(jwt);

        if(!token.getBody().get("user_role").equals(1)){
            throw new UnauthorizedResponse("You must be logged in as a manager");
        }
        ctx.json(reimbursementService.getAllReimbursement());
    };


    private Handler editReimbursement = ctx -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = jwtService.parseJWT(jwt);

        if(!token.getBody().get("user_role").equals(1)){
            throw new UnauthorizedResponse("You must be logged in as a manager");
        }

        String id = ctx.pathParam("reimbursement_id");
        String status = ctx.queryParam("status");
        String userId = ctx.queryParam("user_id");

        if(status == null){
            throw new IllegalArgumentException("You need to provide a status query parameter when attempting to edit a reimbursement");
        }

        List<ResponseReimbursementDTO> responseReimbursementDTOS = reimbursementService.updateReimbursementById(id,status,userId);
        ctx.json(responseReimbursementDTOS);
    };

    @Override
    public void mapEndPoints(Javalin app) {
        app.get("/project-1/reimbursements",getAllReimbursements);
        app.patch("/project-1/reimbursements/{reimbursement_id}",editReimbursement);
    }
}
