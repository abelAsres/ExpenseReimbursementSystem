package com.revature.controller;

import com.revature.dto.LoginDTO;
import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AuthenticationContoller implements Controller{

    private UserService userService = new UserService();

    private Handler login = ctx -> {
        LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);

        User user = userService.login(loginDTO.getUserName(),loginDTO.getPassword());

        ctx.json(user.getUserName()+" has logged in");
    };

    private Handler logout = ctx -> {

    };

    @Override
    public void mapEndPoints(Javalin app) {
        app.post("/project-1/login",login);

        app.post("/project-1/logout",logout);
    }
}
