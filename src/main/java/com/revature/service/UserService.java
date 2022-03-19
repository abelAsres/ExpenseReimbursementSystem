
package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dto.UserDTO;
import com.revature.model.User;
import com.revature.utility.HashUtility;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private HashUtility hashUtility;
    private UserDAO userDao;

    public UserService() {
        this.userDao = new UserDAO();
        this.hashUtility = new HashUtility();
    }

    public UserService(UserDAO mockDao) {
        this.userDao = mockDao;
    }

    public List<User> getallUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    /*
    public User login(String username, String password) throws SQLException, FailedLoginException, NoSuchAlgorithmException, InvalidKeyException {
        User user = this.userDao.getUserByUsernameAndPassword(username, password);

        if (user == null) {
            throw new FailedLoginException("Invalid username and/or password was provided");
        }

        if(hashUtility.generateHashSaltPassword("SHA-512",password,user.getSalt()).equals(user.getPassword())){
            System.out.println("hash checks out");
        }else{
            throw new InvalidKeyException("Try again!!!");
        }

        return user;
    }*/

    public User addUser (UserDTO userDTO) throws SQLException, NoSuchAlgorithmException {
        validateUserDTO(userDTO);
        //hash and salt password
        String hashSaltPassword = hashUtility.generateHashSaltPassword("SHA-512",userDTO.getPassword(),userDTO.getSalt());
        userDTO.setPassword(hashSaltPassword);
        return userDao.addUser(userDTO);
    }

    public void validateUserDTO (UserDTO userDto){
       try{
           userDto.setUserName(userDto.getUserName().trim());
           userDto.setFirstName(userDto.getFirstName().trim());
           userDto.setLastName(userDto.getLastName().trim());

           if (userDto.getPassword().length() > 50){
               throw new IllegalArgumentException("Your password is too long. Please make it shorter");
           }

           if (!userDto.getUserName().matches("[a-zA-Z?['!_-]]+")) {
               throw new IllegalArgumentException("Username must only have alphabetical characters and/or '!_-. Username input was " + userDto.getUserName());
           }

           if (!userDto.getFirstName().matches("[a-zA-Z?']+")) {
               throw new IllegalArgumentException("First name must only have alphabetical characters. First name input was " + userDto.getFirstName());
           }

           if (!userDto.getLastName().matches("[a-zA-Z?']+")) {
               throw new IllegalArgumentException("Last name must only have alphabetical characters. Last name input was " + userDto.getLastName());
           }

           if(!userDto.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
               throw new IllegalArgumentException("Please ensure that your email is correct,Internationalized domain names are not allowed.");
           }

       }catch(NullPointerException e){
           new NullPointerException("Parameter specified as non-null is null");
       }
    }
}
