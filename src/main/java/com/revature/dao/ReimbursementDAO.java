package com.revature.dao;

import com.revature.dto.ReimbursementDTO;
import com.revature.dto.ResponseReimbursementDTO;
import com.revature.model.Reimbursement;
import com.revature.utility.ConnectionUtility;
import com.revature.utility.GoogleStorageUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO {
    public List<ResponseReimbursementDTO> getAllReimbursement() throws SQLException {
        List<ResponseReimbursementDTO> reimbursements = new ArrayList<>();
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "SELECT er.id,reimb_amount,reimb_submitted,reimb_resolved,reimb_description," +
                    "reimb_receipt,eu.user_name,(SELECT user_name FROM ers_users eu2 WHERE id= er.reimb_resolver) AS resolver," +
                    "ers.reimb_status,ert.reimb_type " +
                    "FROM ers_reimbursement er JOIN ers_users eu ON eu.id = er.reimb_author " +
                    "JOIN ers_reimbursement_status ers ON ers.id = er.reimb_status_id " +
                    "JOIN ers_reimbursement_type ert ON ert.id = er.reimb_type_id";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                reimbursements.add(new ResponseReimbursementDTO(resultSet.getInt("id"), resultSet.getDouble("reimb_amount"),
                        resultSet.getString("reimb_submitted"),resultSet.getString("reimb_resolved"),
                        resultSet.getString("reimb_description"), resultSet.getString("reimb_receipt"),
                        resultSet.getString("user_name"),resultSet.getString("resolver"), resultSet.getString("reimb_status"),
                        resultSet.getString("reimb_type")));
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

    public boolean removeReimbursement(int id) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){
            String query1 = "SELECT reimb_receipt FROM ers_reimbursement WHERE id =?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
            preparedStatement1.setInt(1,id);

            ResultSet resultSet = preparedStatement1.executeQuery();

            resultSet.next();
            GoogleStorageUtility.deleteImage(resultSet.getString("reimb_receipt"));

            String query2 = "DELETE FROM ers_reimbursement WHERE id = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
            preparedStatement2.setInt(1,id);

            int deleteRows = preparedStatement2.executeUpdate();

            if(deleteRows == 1){
                return true;
            }
        }
        return false;
    }

    public ResponseReimbursementDTO addReimbursementForUser(double amount, int authorId, String description, String imageLink, int typeId) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "INSERT INTO ers_reimbursement(reimb_amount,reimb_description,reimb_receipt,reimb_author,reimb_type_id) " +
                    "VALUES" +
                    "(?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setDouble(1,amount);
            preparedStatement.setString(2,description);
            preparedStatement.setString(3,imageLink);
            preparedStatement.setInt(4,authorId);
            preparedStatement.setInt(5,typeId);

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();

            int id = resultSet.getInt(1);

            String query1 =  "SELECT * FROM ers_reimbursement_type WHERE id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
            preparedStatement1.setString(1,resultSet.getString("reimb_type_id"));
            ResultSet resultSet1= preparedStatement1.executeQuery();
            resultSet1.next();


            String query2 =  "SELECT * FROM ers_reimbursement_status WHERE id = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
            preparedStatement2.setString(1,resultSet.getString("reimb_type_id"));
            ResultSet resultSet2= preparedStatement2.executeQuery();
            resultSet2.next();

            String query3 =  "SELECT * FROM ers_users WHERE id = ?";
            PreparedStatement preparedStatement3 = connection.prepareStatement(query3);
            preparedStatement3.setString(1,resultSet.getString("reimb_author"));
            ResultSet resultSet3= preparedStatement2.executeQuery();
            resultSet3.next();


            return new ResponseReimbursementDTO(id,resultSet.getDouble("reimb_amount"),resultSet.getString("reimb_submitted"),
                    resultSet.getString("reimb_resolved"),resultSet.getString("reimb_description"),
                    resultSet.getString("reimb_receipt"), resultSet3.getString("user_name"),
                    resultSet.getString("reimb_resolver"),resultSet2.getString("reimb_status_id"),
                    resultSet1.getString("reimb_type_id"));
        }
    }

}
