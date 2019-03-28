import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

public class AdminUnitTest {

    //Test that the method loginAdmin() works correctly when the password and the login are correct
    @Test
    public void checkLoginAdmin() throws SQLException {
        DaoAdmin dao = new DaoAdmin("jdbc:sqlite:mydatabase.db");
        String loginTested = "AdminCani";
        String pwdTested = "adminMDP";
        String expectedValue = "Vous êtes connecté";
        assertEquals(expectedValue, dao.loginAdmin(loginTested, pwdTested));
    }

}
