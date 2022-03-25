package com.revature.controller;

import com.revature.dto.LoginDTO;
import com.revature.dto.UserDTO;
import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.core.util.Header;
import io.javalin.http.Handler;

public class AuthenticationContoller implements Controller{

    private UserService userService = new UserService();

    private Handler login = ctx -> {
        LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);
        System.out.println(loginDTO);

        User user = userService.login(loginDTO.getUserName(),loginDTO.getPassword());

        ctx.header("Access-Control-Expose-Headers", "*");

        UserDTO loggedInUser = new UserDTO();
        loggedInUser.setUserName(user.getUserName());

        ctx.json(loggedInUser);
    };

    private Handler logout = ctx -> {

    };

    @Override
    public void mapEndPoints(Javalin app) {
        app.post("/project-1/login",login);

        app.post("/project-1/logout",logout);
    }
}
