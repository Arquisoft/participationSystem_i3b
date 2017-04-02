package cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * Created by Oriol on 01/04/2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources"})
public class CucumberTest {

    @BeforeClass
    public static void setUp() {
        CucumberActions.setUp();
    }

    @AfterClass
    public static void tearDown() {
        CucumberActions.tearDown();
    }
}
