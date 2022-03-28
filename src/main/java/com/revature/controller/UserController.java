package com.revature.controller;

import com.revature.dto.LoginDTO;
import com.revature.dto.ResponseReimbursementDTO;
import com.revature.dto.UserDTO;
import com.revature.model.User;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

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

    @Override
    public void mapEndPoints(Javalin app) {
        app.get("/project-1/users",getAllUsers);
        app.get("/project-1/users/{user_id}",getUserById);
        app.post("/project-1/users",addUser);
        app.delete("/project-1/users/{user_id}",removeUser);
        app.get("/project-1/users/{user_id}/reimbursements",getReimbursementsByUserId);
    }
}
