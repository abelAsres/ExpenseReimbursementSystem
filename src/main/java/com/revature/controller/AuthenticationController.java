package com.revature.controller;

import com.revature.dto.LoginDTO;
import com.revature.dto.UserDTO;
import com.revature.model.User;
import com.revature.service.JWTService;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.core.util.Header;
import io.javalin.http.Handler;

public class AuthenticationController implements Controller{

    private UserService userService;
    private JWTService jwtService;

    public AuthenticationController() {
        this.userService = new UserService();
        this.jwtService = JWTService.getInstance();
    }

    private Handler login = ctx -> {
        LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);
        System.out.println(loginDTO);

        User user = userService.login(loginDTO.getUserName(),loginDTO.getPassword());
        String jwt = this.jwtService.createJWT(user);

        ctx.header("Access-Control-Expose-Headers", "*");

        UserDTO loggedInUser = new UserDTO(user.getUserName(),user.getFirstName(),
                user.getLastName(),user.getEmail(),user.getRoleId());

        // Send the JSON web token after logging in back to the client (frontend / postman)
        ctx.header("Token", jwt);
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
