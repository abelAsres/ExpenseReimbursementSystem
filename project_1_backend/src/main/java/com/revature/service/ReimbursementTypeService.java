package com.revature.service;

import com.revature.dao.ReimbursementTypeDAO;
import com.revature.model.ReimbursementType;

import java.sql.SQLException;
import java.util.List;

public class ReimbursementTypeService {
    private ReimbursementTypeDAO reimbursementTypeDAO;

    public ReimbursementTypeService(){
        this.reimbursementTypeDAO=new ReimbursementTypeDAO();
    }

    public ReimbursementTypeService(ReimbursementTypeDAO mockReimbursementTypeDAO) {
        this.reimbursementTypeDAO = mockReimbursementTypeDAO;
    }

    public List<ReimbursementType> getAllReimbursementTypes() throws SQLException {
        return reimbursementTypeDAO.getAllReimbursementTypes();
    }

    public ReimbursementType getAllReimbursementTypeById(String id) throws SQLException{
        int typeId = Integer.parseInt(id);
        try{
            return reimbursementTypeDAO.getAllReimbursementTypeById(typeId);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("You did not provide a valid id. "+e.getMessage());
        }

    }
}
