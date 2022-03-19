package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dto.UserDTO;
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

        List<User> expectedUsersList = userService.getallUsers();

        Assertions.assertEquals(expectedUsersList,mockUserList);
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
}
