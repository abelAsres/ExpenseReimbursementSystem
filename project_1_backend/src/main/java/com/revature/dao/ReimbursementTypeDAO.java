package com.revature.dao;

import com.revature.model.ReimbursementType;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementTypeDAO {
    public List<ReimbursementType> getAllReimbursementTypes() throws SQLException {
        List<ReimbursementType> reimbursementTypes = new ArrayList<>();
        try(Connection connection = ConnectionUtility.getConnection()){
            String query ="SELECT * FROM ers_reimbursement_type";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                reimbursementTypes.add(new ReimbursementType(resultSet.getInt("id"),
                        resultSet.getString("reimb_type")));
            }
            return  reimbursementTypes;
        }
    }

    public ReimbursementType getAllReimbursementTypeById(int id) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "SELECT * FROM ers_reimbursement_type WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return new ReimbursementType(resultSet.getInt("id"),resultSet.getString("reimb_type"));
        }
    }
}
