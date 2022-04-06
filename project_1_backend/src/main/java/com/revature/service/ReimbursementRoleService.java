package com.revature.service;

import com.revature.dao.ReimbursementRoleDAO;
import com.revature.model.ReimbursementRole;
import com.revature.model.ReimbursementStatus;

import java.sql.SQLException;
import java.util.List;

public class ReimbursementRoleService {
    private ReimbursementRoleDAO reimbursementRoleDAO;

    public ReimbursementRoleService(){
        this.reimbursementRoleDAO=new ReimbursementRoleDAO();
    }

    public ReimbursementRoleService(ReimbursementRoleDAO mockReimbursementRoleDAO) {
        this.reimbursementRoleDAO = mockReimbursementRoleDAO;
    }

    public List<ReimbursementRole> getAllReimbursementRole() throws SQLException {
        return reimbursementRoleDAO.getAllReimbursementRole();
    }

    public ReimbursementRole getReimbursementRoleById(String id) throws SQLException{
        int statusId = Integer.parseInt(id);
        try{
            return reimbursementRoleDAO.getReimbursementRoleById(statusId);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("You did not provide a valid id. "+e.getMessage());
        }

    }
}
