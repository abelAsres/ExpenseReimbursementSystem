package com.revature.dao;

import com.revature.model.ReimbursementRole;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementRoleDAO {

    public List<ReimbursementRole> getAllReimbursementRole() throws SQLException {
        List<ReimbursementRole> reimbursementRoles = new ArrayList<>();
        try(Connection connection = ConnectionUtility.getConnection()){
            String query ="SELECT * FROM ers_roles";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                reimbursementRoles.add(new ReimbursementRole(resultSet.getInt("id"),
                        resultSet.getString("user_role")));
            }
            return  reimbursementRoles;
        }
    }

    public ReimbursementRole getReimbursementRoleById(int id) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "SELECT * FROM ers_roles WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return new ReimbursementRole(resultSet.getInt("id"),resultSet.getString("user_role"));
        }
    }
}
