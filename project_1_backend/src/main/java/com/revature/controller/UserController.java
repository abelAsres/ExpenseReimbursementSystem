package com.revature.controller;

import com.revature.dto.*;
import com.revature.model.User;
import com.revature.service.JWTService;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;
import com.revature.utility.GoogleStorageUtility;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.http.UploadedFile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.tika.Tika;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UserController implements Controller{

    private UserService userService;
    private ReimbursementService reimbursementService;
    private JWTService jwtService;

    public UserController(){
        this.userService = new UserService();
        this.reimbursementService = new ReimbursementService();
        this.jwtService=JWTService.getInstance();
    }
    private Handler getAllUsers = ctx -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = jwtService.parseJWT(jwt);

        if(!token.getBody().get("user_role").equals(1)){
            throw new UnauthorizedResponse("You must be logged in as a manager");
        }
        ctx.json(userService.getAllUsers());
    };

    private Handler getUserById = ctx -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = jwtService.parseJWT(jwt);

        if(token == null){
            throw new UnauthorizedResponse("You must be logged in");
        }
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
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = jwtService.parseJWT(jwt);

        if(!token.getBody().get("user_role").equals(1)){
            throw new UnauthorizedResponse("You must be logged in as a manager");
        }
        String id = ctx.pathParam("user_id");
        if(userService.removeUser(id)){
            ctx.json("User with ID "+id+" has been removed");
        }else{
            ctx.json("User with ID "+id+" does not exist");
        }
    };

    private Handler getReimbursementsByUserId = ctx -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = jwtService.parseJWT(jwt);

        if(!token.getBody().get("user_role").equals(2)){
            throw new UnauthorizedResponse("You must be logged in as a associate");
        }
        String id = ctx.pathParam("user_id");
        List<ResponseReimbursementDTO> reimbursements = new ArrayList<>();

        reimbursements = reimbursementService.getReimbursementsByUserId(id);
        ctx.json(reimbursements);
    };

    private Handler removeUserReimbursement = ctx ->{
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = jwtService.parseJWT(jwt);

        if(!token.getBody().get("user_role").equals(2)){
            throw new UnauthorizedResponse("You must be logged in as a associate");
        }
        String id = ctx.pathParam("reimbursement_id");
        ctx.json(reimbursementService.removeReimbursement(id));
    };

    private Handler addReimbursementForUser = ctx -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = jwtService.parseJWT(jwt);

        if(token == null){
            throw new UnauthorizedResponse("You must be logged in");
        }

        String userId = ctx.pathParam("user_id");

        String description = ctx.formParam("description");
        String amount = ctx.formParam("amount");
        String author = ctx.formParam("author");
        String type = ctx.formParam("type");
        UploadedFile imageFile = ctx.uploadedFile("image");
        InputStream imageIs = imageFile.getContent();

        Tika tika = new Tika();
        String mimeType = tika.detect(imageIs);

        if (!mimeType.equals("image/jpeg") && !mimeType.equals("image/png") && !mimeType.equals("image/gif")) {
            throw new Exception("Image must be a JPEG, PNG, or GIF");
        }

        String imageLink = GoogleStorageUtility.uploadImage(imageFile);

        ResponseReimbursementDTO reimbursementDTO= reimbursementService.addReimbursementForUser(amount,author,description,imageLink,type);

        ctx.status(201);
        ctx.json(reimbursementDTO);
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
