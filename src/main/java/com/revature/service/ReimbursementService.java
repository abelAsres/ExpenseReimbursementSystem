package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.dto.ReimbursementDTO;
import com.revature.dto.ResponseReimbursementDTO;
import com.revature.model.Reimbursement;

import java.sql.SQLException;
import java.util.List;

public class ReimbursementService {
    private ReimbursementDAO reimbursementDAO;


    public ReimbursementService () {
        this.reimbursementDAO= new ReimbursementDAO();
    }

    public ReimbursementService(ReimbursementDAO mockReimbursementDAO) {
        this.reimbursementDAO= mockReimbursementDAO;
    }

    public List<Reimbursement> getAllReimbursement() throws SQLException {
        return reimbursementDAO.getAllReimbursement();
    }

    public Reimbursement getReimbursementById(String reimbursementId) throws SQLException {
        int id = Integer.parseInt(reimbursementId);
        try{
            return reimbursementDAO.getReimbursementById(id);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("You did not provide a valid id. "+e.getMessage());
        }
    }

    public List<ResponseReimbursementDTO> getReimbursementsByUserId(String userId) throws SQLException {
        int id = Integer.parseInt(userId);
        try{
            return reimbursementDAO.getReimbursementsByUserId(id);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("You did not provide a valid id. "+e.getMessage());
        }
    }

    public boolean removeReimbursement(String id) throws SQLException {
        int reimbursementId = Integer.parseInt(id);
        try{
            return reimbursementDAO.removeReimbursement(reimbursementId);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("You did not provide a valid id. "+e.getMessage());
        }
    }

    public ResponseReimbursementDTO addReimbursementForUser(String amountReimb, String authorIdReimb,String description, String imageLink, String typeIdReimb) throws SQLException {
        double amount = Double.parseDouble(amountReimb);
        int authorId = Integer.parseInt(authorIdReimb);
        int typeId = Integer.parseInt(typeIdReimb);
        return reimbursementDAO.addReimbursementForUser(amount,authorId,description,imageLink,typeId);
    }
}
