package com.revature.main;

import com.revature.controller.*;
import io.javalin.Javalin;
import io.javalin.core.util.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {
    public static Logger logger = LoggerFactory.getLogger(Driver.class);

    public static void main(String[] args) {
        Javalin app = Javalin.create((config)->{
            config.enableCorsForAllOrigins();
        });


        // This will execute before every single request
        app.before((ctx) -> {
            logger.info(ctx.method() + " request received for " + ctx.path());

        });

        mapControllers(app,new UserController(),new AuthenticationController(),new ExceptionController(), new ReimbursementController());

        app.start();
    }

    public static void mapControllers(Javalin app, Controller... controllers){
        for(Controller controller:controllers){
            controller.mapEndPoints(app);
        }
    }
}
