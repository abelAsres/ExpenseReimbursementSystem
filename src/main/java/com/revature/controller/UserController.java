package com.revature.controller;

import com.revature.dto.*;
import com.revature.model.User;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;
import com.revature.utility.GoogleStorageUtility;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UserController implements Controller{

    private UserService userService;
    private ReimbursementService reimbursementService;

    public UserController(){
        this.userService = new UserService();
        this.reimbursementService = new ReimbursementService();
    }
    private Handler getAllUsers = ctx -> {
        ctx.json(userService.getAllUsers());
    };

    private Handler getUserById = ctx -> {
        String id = ctx.pathParam("user_id");
        User user = userService.getUserById(id);
        ctx.json(user);
    };

    private Handler addUser = ctx -> {
        UserDTO userDTO = ctx.bodyAsClass(UserDTO.class);
        User user = userService.addUser(userDTO);
        ctx.status(201);
        ctx.json("User "+user.getUserName()+" has been registered");
    };

    private Handler removeUser = ctx -> {
        String id = ctx.pathParam("user_id");
        if(userService.removeUser(id)){
            ctx.json("User with ID "+id+" has been removed");
        }else{
            ctx.json("User with ID "+id+" does not exist");
        }
    };

    private Handler getReimbursementsByUserId = ctx -> {
        String id = ctx.pathParam("user_id");
        List<ResponseReimbursementDTO> reimbursements = new ArrayList<>();

        reimbursements = reimbursementService.getReimbursementsByUserId(id);
        ctx.json(reimbursements);
    };

    private Handler removeUserReimbursement = ctx ->{
        String id = ctx.pathParam("reimbursement_id");
        ctx.json(reimbursementService.removeReimbursement(id));
    };

    private Handler addReimbursementForUser = ctx -> {
        String userId = ctx.pathParam("user_id");
        //AddReimbursementDTO reimbursementDTO = ctx.bodyAsClass(AddReimbursementDTO.class);

        String description = ctx.formParam("description");
        String amount = ctx.formParam("amount");
        String author = ctx.formParam("author");
        String type = ctx.formParam("type");
        UploadedFile imageFile = ctx.uploadedFile("image");
        InputStream imageIs = imageFile.getContent();

        String imageLink = GoogleStorageUtility.uploadImage(imageFile.getFilename(),imageIs,imageFile.getContentType());

        ResponseReimbursementDTO reimbursementDTO= reimbursementService.addReimbursementForUser(amount,author,description,imageLink,type);


        ctx.status(201);
        ctx.json(reimbursementDTO);


        //User user = userService.getUserById(userId);
        //reimbursementDTO.setAuthor(user.getId());
        //reimbursementService.addReimbursementForUser(reimbursementDTO);
        //reimbursementDTO.toString();
    };

    @Override
    public void mapEndPoints(Javalin app) {
        app.get("/project-1/users",getAllUsers);
        app.get("/project-1/users/{user_id}",getUserById);
        app.post("/project-1/users",addUser);
        app.delete("/project-1/users/{user_id}",removeUser);
        app.get("/project-1/users/{user_id}/reimbursements",getReimbursementsByUserId);
        app.delete("/project-1/users/{user_id}/reimbursements/{reimbursement_id}",removeUserReimbursement);
        app.post("/project-1/users/{user_id}/reimbursements", addReimbursementForUser);
    }
}
