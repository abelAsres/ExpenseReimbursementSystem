
package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dto.UserDTO;
import com.revature.exception.UserNotFoundException;
import com.revature.model.User;
import com.revature.utility.HashUtility;

import javax.security.auth.login.FailedLoginException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDAO userDao;

    public UserService() {
        this.userDao = new UserDAO();
    }

    public UserService(UserDAO mockDao) {
        this.userDao = mockDao;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = userDao.getAllUsers();
        ReimbursementService reimbursementService = new ReimbursementService();
        for (User user : users){
            String userId = ""+user.getId();
            user.setUserReimbursements(reimbursementService.getReimbursementsByUserId(userId));
        }
        return users;
    }


    public User login(String username, String password) throws SQLException, FailedLoginException, NoSuchAlgorithmException {
        User user = userDao.getUserByUserName(username);
        if (user == null || !HashUtility.generateHashSaltPassword("SHA-512",password,user.getSalt()).equals(user.getPassword())) {
            throw new FailedLoginException("Invalid username and/or password was provided");
        }
        return user;
    }

    public User getUserById(String id) throws SQLException,UserNotFoundException{
        try{
            int userId = Integer.parseInt(id);
            User user = userDao.getUserById(userId);

            if (user == null){
                throw new UserNotFoundException("User with id "+ id + " does not exist");
            }
            return user ;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("You did not provide a valid ID.  Try again!!!");
        }
    }
    public User addUser (UserDTO userDTO) throws SQLException, NoSuchAlgorithmException {
        validateUserDTO(userDTO);
        userDTO.setSalt(HashUtility.createSalt());
        //hash and salt password
        String hashSaltPassword = HashUtility.generateHashSaltPassword("SHA-512",userDTO.getPassword(),userDTO.getSalt());
        userDTO.setPassword(hashSaltPassword);
        return userDao.addUser(userDTO);
    }

    public boolean removeUser(String id) throws SQLException{
        try{
            int userId = Integer.parseInt(id);
            return userDao.removeUser(userId);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("You did not provide a valid ID.  Try again!!!");
        }
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

           if(!userDto.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z-]+[.]+[a-z]{2,3}$")){
               throw new IllegalArgumentException("Please ensure that your email is correct,Internationalized domain names are not allowed.");
           }

       }catch(NullPointerException e){
           new NullPointerException("Parameter specified as non-null is null");
       }
    }
}
