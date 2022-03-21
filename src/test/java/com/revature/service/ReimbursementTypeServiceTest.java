package com.revature.service;

import com.revature.dao.ReimbursementTypeDAO;
import com.revature.model.ReimbursementType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.*;

public class ReimbursementTypeServiceTest {
    @Test
    public void test_getAllReimbursementTypes() throws SQLException {
        ReimbursementTypeDAO mockRimbursementTypeDAO = mock(ReimbursementTypeDAO.class);
        List<ReimbursementType> mockReimbursementTypes = new ArrayList<>();

        ReimbursementTypeService reimbursementTypeService = new ReimbursementTypeService(mockRimbursementTypeDAO);

        mockReimbursementTypes.add(new ReimbursementType(1,"Lodging"));
        mockReimbursementTypes.add(new ReimbursementType(2,"Travel"));
        mockReimbursementTypes.add(new ReimbursementType(3, "Food"));
        mockReimbursementTypes.add(new ReimbursementType(4,"Other"));

        when(mockRimbursementTypeDAO.getAllReimbursementTypes()).thenReturn(mockReimbursementTypes);

        List<ReimbursementType> reimbursementTypes = reimbursementTypeService.getAllReimbursementTypes();

        Assertions.assertEquals(mockReimbursementTypes,reimbursementTypes);
    }

    @Test
    public void test_getAllReimbursementTypeById() throws SQLException {
        ReimbursementTypeDAO mockReimbursementTypeDAO = mock(ReimbursementTypeDAO.class);
        ReimbursementType mockReimbursementType = new ReimbursementType(1,"Lodging");

        ReimbursementTypeService reimbursementTypeService = new ReimbursementTypeService(mockReimbursementTypeDAO);


        when(mockReimbursementTypeDAO.getAllReimbursementTypeById(1)).thenReturn(mockReimbursementType);

        ReimbursementType actualReimbursementType = reimbursementTypeService.getAllReimbursementTypeById("1");

        Assertions.assertEquals(mockReimbursementType,actualReimbursementType);
    }
}
