package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementRoleDAO;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReimbursementServiceTest {
    @Test
    public void test_getAllReimbursements() throws SQLException {

        ReimbursementDAO mockReimbursementDAO = mock(ReimbursementDAO.class);
        List<Reimbursement> mockReimbursementList = new ArrayList<>();

        ReimbursementService reimbursementService = new ReimbursementService(mockReimbursementDAO);

        mockReimbursementList.add(new Reimbursement(1,100.00,"22-03-2022 16:10:10","22-03-2022 16:10:10",
                "testDesc","testLink","testAuthor","testResolver",1,1));
        mockReimbursementList.add(new Reimbursement(2,100.00,"22-03-2022 16:10:10","22-03-2022 16:10:10",
                "testDesc","testLink","testAuthor","testResolver",1,1));
        mockReimbursementList.add(new Reimbursement(3,100.00,"22-03-2022 16:10:10","22-03-2022 16:10:10",
                "testDesc","testLink","testAuthor","testResolver",1,1));
        mockReimbursementList.add(new Reimbursement(4,100.00,"22-03-2022 16:10:10","22-03-2022 16:10:10",
                "testDesc","testLink","testAuthor","testResolver",1,1));
        mockReimbursementList.add(new Reimbursement(5,100.00,"22-03-2022 16:10:10","22-03-2022 16:10:10",
                "testDesc","testLink","testAuthor","testResolver",1,1));
        mockReimbursementList.add(new Reimbursement(6,100.00,"22-03-2022 16:10:10","22-03-2022 16:10:10",
                "testDesc","testLink","testAuthor","testResolver",1,1));

        when(mockReimbursementDAO.getAllReimbursement()).thenReturn(mockReimbursementList);

        List<Reimbursement> reimbursements = reimbursementService.getAllReimbursement();

        Assertions.assertEquals(mockReimbursementList,reimbursements);
    }

    @Test
    public void test_getReimbursementById() throws SQLException {
        ReimbursementDAO mockReimbursementDAO = mock(ReimbursementDAO.class);
        Reimbursement mockReimbursement = new Reimbursement(6,100.00,"22-03-2022 16:10:10","22-03-2022 16:10:10",
                "testDesc","testLink","testAuthor","testResolver",1,1);

        ReimbursementService reimbursementService = new ReimbursementService(mockReimbursementDAO);


        when(mockReimbursementDAO.getReimbursementById(1)).thenReturn(mockReimbursement);

        Reimbursement actualReimbursement = reimbursementService.getReimbursementById("1");

        Assertions.assertEquals(mockReimbursement,actualReimbursement);
    }
}
