package com.revature.dao;

import com.revature.dto.UserDTO;
import com.revature.model.User;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public UserDAO() {
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> allUsers = new ArrayList<>();
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "SELECT * FROM ers_users";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                allUsers.add(new User(resultSet.getInt("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("ers_password"),
                        resultSet.getString("user_first_name"),
                        resultSet.getString("user_last_name"),
                        resultSet.getString("user_email"),
                        resultSet.getInt("user_role_id"),
                        resultSet.getBytes("salt"),
                        resultSet.getString("profile_pic")));
            }
            return allUsers;
        }
    }

    public User getUserById(int id) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "SELECT * FROM ers_users WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return new User(resultSet.getInt("id"),resultSet.getString("user_name"),resultSet.getString("ers_password"),
                        resultSet.getString("user_first_name"), resultSet.getString("user_last_name"),resultSet.getString("user_email"),
                        resultSet.getInt("user_role_id"),resultSet.getBytes("salt"),resultSet.getString("profile_pic"));
            }
           return null;
        }
    }

    public User getUserByUserName(String userName) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "SELECT * FROM ers_users WHERE user_name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,userName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return new User(resultSet.getInt("id"),resultSet.getString("user_name"),resultSet.getString("ers_password"),
                        resultSet.getString("user_first_name"), resultSet.getString("user_last_name"),resultSet.getString("user_email"),
                        resultSet.getInt("user_role_id"),resultSet.getBytes("salt"),resultSet.getString("profile_pic"));
            }
            return null;
        }
    }
    public User addUser(UserDTO userDTO) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "INSERT INTO ers_users (user_name,ers_password,user_first_name,user_last_name,user_email,user_role_id,salt) " +
                    "VALUES (?,?,?,?,?,?,?)";

            PreparedStatement preparedStatment = connection.prepareStatement(query);

            preparedStatment.setString(1,userDTO.getUserName());
            preparedStatment.setString(2,userDTO.getPassword());
            preparedStatment.setString(3,userDTO.getFirstName());
            preparedStatment.setString(4,userDTO.getLastName());
            preparedStatment.setString(5,userDTO.getEmail());
            preparedStatment.setInt(6,userDTO.getRoleId());
            preparedStatment.setBytes(7,userDTO.getSalt());

            preparedStatment.executeUpdate();

            ResultSet results = preparedStatment.getGeneratedKeys();
            results.next();
            int id = results.getInt(1);

            return new User(id,userDTO.getUserName(),userDTO.getPassword(),userDTO.getFirstName(),userDTO.getLastName(),
                    userDTO.getEmail(), userDTO.getRoleId(), userDTO.getSalt(),userDTO.getProfilePic());
        }
    }

    public boolean removeUser(int id) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "DELETE FROM ers_users WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,id);

            int numOfRemovedUsers = preparedStatement.executeUpdate();

            if (numOfRemovedUsers == 1){
                return true;
            }
        }
        return false;
    }
/*
    public User updateUser(User user) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "UPDATE ers_users " +
                    "SET user_name = ? " +
                    ""
        }
    }
*/
}
