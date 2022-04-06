package com.revature.service;

import com.revature.dao.ReimbursementStatusDAO;
import com.revature.dao.ReimbursementTypeDAO;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;

import java.sql.SQLException;
import java.util.List;

public class ReimbursementStatusService {
    private ReimbursementStatusDAO reimbursementStatusDAO;

    public ReimbursementStatusService(){
        this.reimbursementStatusDAO=new ReimbursementStatusDAO();
    }

    public ReimbursementStatusService(ReimbursementStatusDAO mockReimbursementStatusDAO) {
        this.reimbursementStatusDAO = mockReimbursementStatusDAO;
    }

    public List<ReimbursementStatus> getAllReimbursementStatus() throws SQLException {
        return reimbursementStatusDAO.getAllReimbursementStatus();
    }

    public ReimbursementStatus getReimbursementStatusById(String id) throws SQLException{
        int statusId = Integer.parseInt(id);
        try{
            return reimbursementStatusDAO.getReimbursementStatusById(statusId);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("You did not provide a valid id. "+e.getMessage());
        }

    }
}
