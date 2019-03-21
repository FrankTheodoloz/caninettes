import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.swing.core.matcher.JButtonMatcher.withText;

public class ConnexionFunctionnalTest {
    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @Before
    public void setUp() {
        window = new FrameFixture(GuiActionRunner.execute(CaniCrottes::createWindow));
    }

    @Test
    public void Connexion() throws InterruptedException {
        window.button("btnConnexion").click();
        window.button("btnConnexion").requireText("Connexion");

        window.textBox("txtLogin").enterText("AdminCani");
        window.textBox("txtPassword").enterText("adminMDP");
        window.button("btnSignIn").click();

    }


    @After
    public void tearDown() {
        window.cleanUp();
    }
}