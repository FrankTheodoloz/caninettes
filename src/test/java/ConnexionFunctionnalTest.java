import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.ComponentLookupScope;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.assertj.swing.core.matcher.JButtonMatcher.withText;
import static org.assertj.swing.finder.WindowFinder.findFrame;

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

        FrameFixture loginFrame = findFrame(LoginForm.class).using(window.robot());

        loginFrame.textBox("log").enterText("AdminCani");
        loginFrame.textBox("Password").enterText("adminMDP");
        loginFrame.button("btnSignIn").click();
        window.button("btnConnexion").requireText("Déconnexion");
        Thread.sleep(2000);
    }


    @Test
    public void FailConnexion() throws InterruptedException {
        window.button("btnConnexion").click();
        window.button("btnConnexion").requireText("Connexion");

        //Change frame
        FrameFixture loginFrame = findFrame(LoginForm.class).using(window.robot());

        loginFrame.textBox("log").enterText("Fail");
        loginFrame.textBox("Password").enterText("adminMDP");
        loginFrame.button("btnSignIn").click();
        loginFrame.button("btnSignIn").requireText("Connexion");
        Thread.sleep(2000);
    }


    @After
    public void tearDown() {
        window.cleanUp();
    }
}