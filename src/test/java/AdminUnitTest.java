import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

public class AdminUnitTest {

    //Test that the method loginAdmin() works correctly when the password and the login are correct
    @Test
    public void checkLoginAdmin() throws SQLException {
        DaoAdmin dao = new DaoAdmin(CaniCrottes.getSqliteConnection(true));
        String loginTested = "AdminCani";
        String pwdTested = "adminMDP";
        boolean expectedValue = true;
        assertEquals(expectedValue, dao.loginAdmin(loginTested, pwdTested));
    }

}
