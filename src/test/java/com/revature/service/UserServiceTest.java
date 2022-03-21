package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dto.UserDTO;
import com.revature.exception.UserNotFoundException;
import com.revature.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Test
    public void test_getAllUsers() throws SQLException {
        //Arrange
        UserDAO mockUserDAO = mock(UserDAO.class);

        List<User> mockUserList = new ArrayList<>();

        byte[] bytes = new byte[20];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);

        mockUserList.add(new User(1, "testUser","password","test","user","testuser@umail.com",1,bytes));
        mockUserList.add(new User(2, "testUser","password","test","user","testuser@umail.com",2,bytes));
        mockUserList.add(new User(3, "testUser","password","test","user","testuser@umail.com",3,bytes));
        mockUserList.add(new User(4, "testUser","password","test","user","testuser@umail.com",4,bytes));

        UserService userService = new UserService(mockUserDAO);

        // Whenever the code in the Service layer calls the getAllUsers() method
        // for the dao layer, then return the list of users
        // we have specified above

        //stubbing
        when(mockUserDAO.getAllUsers()).thenReturn(mockUserList);

        List<User> expectedUsersList = userService.getAllUsers();

        Assertions.assertEquals(expectedUsersList,mockUserList);
    }

    @Test
    public void test_getUserById() throws SQLException, UserNotFoundException {
        UserDAO mockUserDAO = mock(UserDAO.class);

        byte[] bytes = new byte[20];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);


        User expectedUser = new User(1,"testUser","password","test","user","testuser@gmail.com",5,bytes);

        UserService userService = new UserService(mockUserDAO);

        when(mockUserDAO.getUserById(1)).thenReturn(expectedUser);

        User actualUser = userService.getUserById("1");

        Assertions.assertEquals(expectedUser,actualUser);

    }

    @Test
    public void test_addUser() throws SQLException, NoSuchAlgorithmException {
        UserDAO mockUserDAO = mock(UserDAO.class);

        byte[] bytes = new byte[20];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);

        UserDTO mockUserDTO = new UserDTO("testUser","password","test","user","testuser@gmail.com",5);
        User expectedUser = new User(1,"testUser","password","test","user","testuser@gmail.com",5,bytes);

        UserService userService = new UserService(mockUserDAO);

        when(mockUserDAO.addUser(mockUserDTO)).thenReturn(expectedUser);


        User actualUser = userService.addUser(mockUserDTO);

        Assertions.assertEquals(expectedUser,actualUser);
    }

    @Test
    public void test_removeUser() throws SQLException {
        UserDAO mockUserDAO = mock(UserDAO.class);

        UserService userService = new UserService(mockUserDAO);

        Boolean expectedResult = true;

        when(mockUserDAO.removeUser(1)).thenReturn(expectedResult);

        Boolean userRemoved = userService.removeUser("1");

        Assertions.assertEquals(expectedResult,userRemoved);
    }

    /*

    @Test void test_updateUser() throws SQLException{
        UserDAO mockUserDAO = mock(UserDAO.class);

        UserService userService = new UserService(mockUserDAO);

        byte [] salt =  HashUtility.createSalt();
        User mockUser = new User(1,"testUser","password","test","user","testuser@revature.com",1,salt);
        User updatedUser = new User(1,"testUser","password","testy","user","testuser@revature.com",1,salt);

        when(mockUser.updateUser(mockUser)).thenReturn(updatedUser);

        User user = userService.updateUser(1,mockUser);

        Assertions.assertEquals(updatedUser,user);
    }
*/
    @Test
    public void test_validateUserDTOProperties(){
        UserDAO mockUserDAO = mock(UserDAO.class);

        UserDTO mockUserDTO = new UserDTO("testUser","password","Hugh","Jazz","hjazz@revature.com",1);

        UserService userService = new UserService(mockUserDAO);

        userService.validateUserDTO(mockUserDTO);

    }

    @Test
    public void test_validateUserFirstName_IllegalArgumentException(){

        UserDAO mockUserDAO = mock(UserDAO.class);

        UserDTO mockUserDTO = new UserDTO("testUser","password","Hugh123","Jazz","hjazz@revature.com",1);

        UserService userService = new UserService(mockUserDAO);

        // Act + Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.validateUserDTO(mockUserDTO);
        });
    }

    @Test
    public void test_validateUserLasttName_IllegalArgumentException(){
        UserDAO mockUserDAO = mock(UserDAO.class);

        UserDTO mockUserDTO = new UserDTO("testUser","password","Hugh","Jazz123","hjazz@revature.com",1);

        UserService userService = new UserService(mockUserDAO);

        // Act + Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.validateUserDTO(mockUserDTO);
        });
    }

    @Test
    public void test_validateUserPassword_IllegalArgumentException(){

        UserDAO mockUserDAO = mock(UserDAO.class);

        UserDTO mockUserDTO = new UserDTO("testUser",
                "PasswordPasswordPasswordPasswordPasswordPasswordPassword" +
                        "PasswordPasswordPasswordPasswordPasswordPasswordPassword" +
                        "PasswordPassword","Hugh","Jazz","hjazz@revature.com",1);

        UserService userService = new UserService(mockUserDAO);

        // Act + Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.validateUserDTO(mockUserDTO);
        });
    }

    @Test
    public void test_validateUserEmail_IllegalArgumentException(){
        UserDAO mockUserDAO = mock(UserDAO.class);

        UserDTO mockUserDTO = new UserDTO("testUser","password","Hugh","Jazz","hjazz@revature.m",1);

        UserService userService = new UserService(mockUserDAO);

        // Act + Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.validateUserDTO(mockUserDTO);
        });

    }

    @Test
    public void test_validateUserUserName_IllegalArgumentException(){
        UserDAO mockUserDAO = mock(UserDAO.class);

        UserDTO mockUserDTO = new UserDTO("testUser@@","password","Hugh","Jazz","hjazz@revature.com",1);

        UserService userService = new UserService(mockUserDAO);

        // Act + Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.validateUserDTO(mockUserDTO);
        });


    }
}
