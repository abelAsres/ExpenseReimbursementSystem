package com.revature.service;

import com.revature.dao.ReimbursementDAO;
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
}
