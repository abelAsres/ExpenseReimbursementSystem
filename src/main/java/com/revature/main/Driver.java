package com.revature.main;

import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;
import com.revature.controller.UserContoller;
import io.javalin.Javalin;

public class Driver {
    public static void main(String[] args) {
        Javalin app = Javalin.create();

        mapControllers(app,new UserContoller(),new ExceptionController());

        app.start();
    }

    public static void mapControllers(Javalin app, Controller... controllers){
        for(Controller controller:controllers){
            controller.mapEndPoints(app);
        }
    }
}
