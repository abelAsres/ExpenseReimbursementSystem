package com.revature.service;

import com.revature.dao.ReimbursementStatusDAO;
import com.revature.dao.ReimbursementTypeDAO;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReimbursementStatusTest {
    @Test
    public void test_getAllReimbursementStatus() throws SQLException {
        ReimbursementStatusDAO mockReimbursementStatusDAO = mock(ReimbursementStatusDAO.class);
        List<ReimbursementStatus> mockReimbursementStatusList = new ArrayList<>();

        ReimbursementStatusService reimbursementStatusService = new ReimbursementStatusService(mockReimbursementStatusDAO);

        mockReimbursementStatusList.add(new ReimbursementStatus(1,"Lodging"));
        mockReimbursementStatusList.add(new ReimbursementStatus(2,"Travel"));
        mockReimbursementStatusList.add(new ReimbursementStatus(3, "Food"));
        mockReimbursementStatusList.add(new ReimbursementStatus(4,"Other"));

        when(mockReimbursementStatusDAO.getAllReimbursementStatus()).thenReturn(mockReimbursementStatusList);

        List<ReimbursementStatus> reimbursementTypes = reimbursementStatusService.getAllReimbursementStatus();

        Assertions.assertEquals(mockReimbursementStatusList,reimbursementTypes);
    }

    @Test
    public void test_getAllReimbursementStatusById() throws SQLException {
        ReimbursementStatusDAO mockReimbursementStatusDAO = mock(ReimbursementStatusDAO.class);
        ReimbursementStatus mockReimbursementStatus = new ReimbursementStatus(1,"Lodging");

        ReimbursementStatusService reimbursementStatusService = new ReimbursementStatusService(mockReimbursementStatusDAO);


        when(mockReimbursementStatusDAO.getReimbursementStatusById(1)).thenReturn(mockReimbursementStatus);

        ReimbursementStatus actualReimbursementStatus = reimbursementStatusService.getReimbursementStatusById("1");

        Assertions.assertEquals(mockReimbursementStatus,actualReimbursementStatus);
    }
}
