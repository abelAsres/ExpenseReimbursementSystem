package com.revature.dao;

import com.revature.dto.ResponseReimbursementDTO;
import com.revature.model.Reimbursement;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO {
    public List<Reimbursement> getAllReimbursement() throws SQLException {
        List<Reimbursement> reimbursements = new ArrayList<>();
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "SELECT * FROM ers_reimbursement";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                reimbursements.add(new Reimbursement(resultSet.getInt("id"),
                        resultSet.getDouble("reimb_amount"),
                        resultSet.getString("reimb_submitted"),
                        resultSet.getString("reimb_approved"),
                        resultSet.getString("reimb_description"),
                        resultSet.getString("reimb_receipt"),
                        resultSet.getString("reimb_author"),
                        resultSet.getString("reimb_resolver"),
                        resultSet.getInt("reimb_status_id"),
                        resultSet.getInt("reimb_type_id")));
            }
        }
        return reimbursements;
    }

    public Reimbursement getReimbursementById(int id) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "SELECT * FROM ers_reimbursement WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return new Reimbursement(resultSet.getInt("id"),
                    resultSet.getDouble("reimb_amount"),
                    resultSet.getString("reimb_submitted"),
                    resultSet.getString("reimb_approved"),
                    resultSet.getString("reimb_description"),
                    resultSet.getString("reimb_receipt"),
                    resultSet.getString("reimb_author"),
                    resultSet.getString("reimb_resolver"),
                    resultSet.getInt("reimb_status_id"),
                    resultSet.getInt("reimb_type_id"));

        }
    }

    public List<ResponseReimbursementDTO> getReimbursementsByUserId(int id) throws SQLException {
        List<ResponseReimbursementDTO> reimbursements = new ArrayList<>();
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "SELECT er.id,reimb_amount,reimb_submitted,reimb_resolved,reimb_description," +
                    "reimb_receipt,eu.user_name,(SELECT user_name FROM ers_users eu2 WHERE id= er.reimb_resolver) AS resolver," +
                    "ers.reimb_status,ert.reimb_type " +
                    "FROM ers_reimbursement er JOIN ers_users eu ON eu.id = er.reimb_author " +
                    "JOIN ers_reimbursement_status ers ON ers.id = er.reimb_status_id " +
                    "JOIN ers_reimbursement_type ert ON ert.id = er.reimb_type_id " +
                    "WHERE er.reimb_author = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                reimbursements.add(new ResponseReimbursementDTO(resultSet.getInt("id"), resultSet.getDouble("reimb_amount"),
                        resultSet.getString("reimb_submitted"),resultSet.getString("reimb_resolved"),
                        resultSet.getString("reimb_description"), resultSet.getString("reimb_receipt"),
                        resultSet.getString("user_name"),resultSet.getString("resolver"), resultSet.getString("reimb_status"),
                        resultSet.getString("reimb_type")));
            }
            return reimbursements;
        }
    }

}
