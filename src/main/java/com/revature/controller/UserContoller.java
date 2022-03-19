package com.revature.controller;

import com.revature.dto.UserDTO;
import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserContoller implements Controller{

    private UserService userService = new UserService();

    private Handler getAllUsers = ctx -> {
        ctx.json(userService.getallUsers());
    };

    private Handler getUserById = ctx -> {
        String id = ctx.pathParam("client_id");
        //User user = userService.getUserById(id);

        //ctx.json(user);
    };

    private Handler addUser = ctx -> {
        UserDTO userDTO = ctx.bodyAsClass(UserDTO.class);
        User user = userService.addUser(userDTO);
        ctx.status(201);
        ctx.json("User "+user.getUserName()+" has been added");
    };

    @Override
    public void mapEndPoints(Javalin app) {
        app.get("/project-1/users",getAllUsers);
        app.get("/project-1/users/{user_id}",getUserById);
        app.post("/project-1/users",addUser);
    }
}
