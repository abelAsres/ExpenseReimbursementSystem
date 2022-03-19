package com.revature.controller;

//import com.revature.exception.AccountDoesNotBelongToClient;
//import com.revature.exception.ClientAlreadyExistsException;
//import com.revature.exception.ClientNotFoundException;
import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

// Instead of using try-catch (directly from the Java language), we can abstract that process away
// using Javalin's exception mapping functionality
// This ExceptionController provides us with the ability to "map" exceptions in one central location
// that get thrown from the endpoint handlers themselves
public class ExceptionController implements Controller{

    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    private ExceptionHandler clientNotFound = (e, ctx) -> {
        logger.warn("User attempted to retrieve a client that was not found. Exception message is " + e.getMessage());
        ctx.status(404);
        ctx.json(e.getMessage());
    };

    private ExceptionHandler badArgument = (e, ctx) -> {
        logger.warn("User input a bad argument. Exception message is " + e.getMessage());
        ctx.status(400);
        ctx.json(e.getMessage());
    };

    private ExceptionHandler sqlException = (e, ctx) -> {
        logger.warn("SQL error. Exception message is "+e.getMessage());
        ctx.status(400);
        ctx.json(e.getMessage());
    };

    private ExceptionHandler nullPointerException = (e, ctx) -> {
        logger.warn("Parameter specified as non-null is null. "+e.getMessage());
        ctx.status(400);
        ctx.json(e.getMessage());
    };

    private ExceptionHandler clientAlreadyExists = (e, ctx) ->{
        logger.warn("Client is already in the database: "+e.getMessage());
        ctx.status(400);
        ctx.json(e.getMessage());
    };

    private ExceptionHandler accountDoesNotBelongToClient = (e,ctx)->{
        logger.warn("Client is not authorized for this account "+e.getMessage());
        ctx.status(400);
        ctx.json(e.getMessage());
    };

    private ExceptionHandler noSuchAlgorithmException = (e,ctx) ->{
        logger.warn("Hashing the password did not work. "+ e.getMessage());
        ctx.status(400);
        ctx.json(e.getMessage());
    };
    @Override
    public void mapEndPoints(Javalin app) {
       // app.exception(ClientNotFoundException.class,clientNotFound);
        app.exception(IllegalArgumentException.class,badArgument);
        app.exception(SQLException.class,sqlException);
        app.exception(NullPointerException.class,nullPointerException);
        app.exception(NoSuchAlgorithmException.class,noSuchAlgorithmException);

        //app.exception(ClientAlreadyExistsException.class,clientAlreadyExists);
        //app.exception(AccountDoesNotBelongToClient.class,accountDoesNotBelongToClient);
    }
}
