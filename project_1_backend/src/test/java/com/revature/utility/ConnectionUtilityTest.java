package com.revature.utility;

import com.revature.utility.ConnectionUtility;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ConnectionUtilityTest {
    @Test
    public void test_getConnection() throws SQLException {
        // If something is wrong with getting a connection,
        // a SQLException will be thrown
        // If any exception occurs inside a test case, but it is not being handled, then the test automatically
        // fails
        ConnectionUtility.getConnection();
    }
}
