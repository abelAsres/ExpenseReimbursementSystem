package com.revature.service;

import com.revature.dao.ReimbursementRoleDAO;
import com.revature.model.ReimbursementRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ReimbursementRoleServiceTest {
    @Test
    public void test_getAllReimbursementRoles() throws SQLException {

        ReimbursementRoleDAO mockReimbursementRoleDAO = mock(ReimbursementRoleDAO.class);
        List<ReimbursementRole> mockReimbursementRoleList = new ArrayList<>();

        ReimbursementRoleService reimbursementRoleService = new ReimbursementRoleService(mockReimbursementRoleDAO);

        mockReimbursementRoleList.add(new ReimbursementRole(1,"Manager"));
        mockReimbursementRoleList.add(new ReimbursementRole(2,"Associate"));

        when(mockReimbursementRoleDAO.getAllReimbursementRole()).thenReturn(mockReimbursementRoleList);

        List<ReimbursementRole> reimbursementRoles = reimbursementRoleService.getAllReimbursementRole();

        Assertions.assertEquals(mockReimbursementRoleList,reimbursementRoles);
    }

    @Test
    public void test_getReimbursementRoleById() throws SQLException {
        ReimbursementRoleDAO mockReimbursementRoleDAO = mock(ReimbursementRoleDAO.class);
        ReimbursementRole mockReimbursementRole = new ReimbursementRole(1,"Lodging");

        ReimbursementRoleService reimbursementRoleService = new ReimbursementRoleService(mockReimbursementRoleDAO);


        when(mockReimbursementRoleDAO.getReimbursementRoleById(1)).thenReturn(mockReimbursementRole);

        ReimbursementRole actualReimbursementRole = reimbursementRoleService.getReimbursementRoleById("1");

        Assertions.assertEquals(mockReimbursementRole,actualReimbursementRole);
    }
}
