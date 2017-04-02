package cucumber;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hello.Application;
import org.apache.commons.lang3.SystemUtils;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Created by Oriol on 01/04/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberActions {
    private static FirefoxDriver driver;


    public static void setUp() {
        FirefoxBinary ffBinary = null;
        if (SystemUtils.IS_OS_WINDOWS) {
            ffBinary = new FirefoxBinary(new File("FirefoxPortable\\FirefoxPortable.exe"));
        }

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        driver = new FirefoxDriver(ffBinary, firefoxProfile);
    }

    public static void tearDown() {
        driver.quit();
    }

    @After
    public void cleanCookies() {
        driver.manage().deleteAllCookies();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Given("^user navigates to \"([^\"]*)\"$")
    public void userNavigatesTo(String url) throws Throwable {
        driver.get(url);
    }

    @And("^user logs in with name \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void userIntroducesUsernameAndPassword(String username, String password) throws Throwable {
        driver.findElement(By.id("userNameInput")).clear();
        driver.findElement(By.id("userNameInput")).sendKeys(username);
        driver.findElement(By.id("passwordInput")).clear();
        driver.findElement(By.id("passwordInput")).sendKeys(password);
        driver.findElement(By.id("loginButton")).click();
    }

    @And("^user creates proposal \"([^\"]*)\" with content \"([^\"]*)\" and category \"([^\"]*)\"$")
    public void userCreatesProposal(String title, String content, String category) throws Throwable {
        driver.findElement(By.id("titleInput")).clear();
        driver.findElement(By.id("titleInput")).sendKeys(title);
        driver.findElement(By.id("contentInput")).clear();
        driver.findElement(By.id("contentInput")).sendKeys(content);
        driver.findElement(By.id("category")).click();
        driver.findElement(By.id(category)).click();
        driver.findElement(By.id("SubmitProp")).click();
    }

    @And("^user visits \"([^\"]*)\"$")
    public void userVisitsProposal(String proposal) throws Throwable {
        driver.findElement(By.id(proposal)).click();
    }

    @And("^user upvotes the proposal$")
    public void userUpvotesProposal() throws Throwable {
        driver.findElement(By.id("upvote")).click();
    }

    @When("^user creates a comment with content \"([^\"]*)\"$")
    public void userComments(String content) throws Throwable {
        driver.findElement(By.id("contentInput")).clear();
        driver.findElement(By.id("contentInput")).sendKeys(content);
        driver.findElement(By.id("SubmitComment")).click();
    }

    @Then("^the user successfully logs in$")
    public void commentAppears(String content) throws Throwable {
        assertTrue(driver.findElementsByXPath("//*[contains(text(), '" + content + "')]").size() > 0);
    }

    @Then("^a comment appears with content \"([^\"]*)\"$")
    public void aCommentAppearsWithContent(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
