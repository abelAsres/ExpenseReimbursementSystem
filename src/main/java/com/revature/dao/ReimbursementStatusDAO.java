package com.revature.dao;

import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementStatusDAO {
    public List<ReimbursementStatus> getAllReimbursementStatus() throws SQLException {
        List<ReimbursementStatus> reimbursementStatusList = new ArrayList<>();
        try(Connection connection = ConnectionUtility.getConnection()){
            String query ="SELECT * FROM ers_reimbursement_status";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                reimbursementStatusList.add(new ReimbursementStatus(resultSet.getInt("id"),
                        resultSet.getString("reimb_status")));
            }
            return  reimbursementStatusList;
        }
    }

    public ReimbursementStatus getReimbursementStatusById(int id) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "SELECT * FROM ers_reimbursement_status WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return new ReimbursementStatus(resultSet.getInt("id"),resultSet.getString("reimb_status"));
        }
    }
}
