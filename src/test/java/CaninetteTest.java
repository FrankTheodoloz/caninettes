import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.swing.core.matcher.JButtonMatcher.withText;


public class CaninetteTest
{
    private FrameFixture window;


    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @Before
    public void setUp() {
        window = new FrameFixture(GuiActionRunner.execute(CaniCrottes::creeFenetre));
    }

    @Test
    public void AfficherListeCani() throws InterruptedException
    {
        window.button(withText("Liste des caninettes")).click();
        Thread.sleep(2 * 1000);

    }

    @Test
    public void AfficherListeCaniHS() throws InterruptedException
    {
        window.button(withText("Caninettes hors service")).click();
        Thread.sleep(2 * 1000);

    }

    @Test
    public void bouttonQuitter()
    {
        window.button(withText("Quitter")).click();

    }


    @After
    public void tearDown() {
        window.cleanUp();
    }
}