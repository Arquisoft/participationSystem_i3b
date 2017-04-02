package hello.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import scala.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterTest {
	private static WebDriver driver;
	private String baseUrl = "http://localhost:8080/";
	private static String username;
	private static String proposalName;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() {
		FirefoxBinary ffBinary = null;
		if (SystemUtils.IS_OS_WINDOWS) {
			ffBinary = new FirefoxBinary(new File(
					"FirefoxPortable\\FirefoxPortable.exe"));
		}
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);
	}

	// P1: Register new user into the database
	@Test
	public void test1() throws Exception {
		Random rand = new Random();
		username = "test" + rand.nextInt();
		driver.get(baseUrl + "/login");
		driver.findElement(By.id("registerUsername")).clear();
		driver.findElement(By.id("registerUsername")).sendKeys(username);
		driver.findElement(By.id("registerPassword")).clear();
		driver.findElement(By.id("registerPassword")).sendKeys("test");
		new Select(driver.findElement(By.id("ageSelect"))).selectByVisibleText(
				"22");
		driver.findElement(By.id("buttonRegister")).click();
	}

	// P2: Login with the user created previously
	@Test
	public void test2() throws Exception {
		login();
	}

	private void login() {
		driver.get(baseUrl);
		driver.findElement(By.id("userNameInput")).clear();
		driver.findElement(By.id("userNameInput")).sendKeys(username);
		driver.findElement(By.id("passwordInput")).clear();
		driver.findElement(By.id("passwordInput")).sendKeys("test");
		driver.findElement(By.id("loginButton")).click();
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Register", 10);
		SeleniumUtils.textoPresentePagina(driver, "User Home");
	}

	private void loginAdmin() {
		driver.get(baseUrl);
		driver.findElement(By.id("userNameInput")).clear();
		driver.findElement(By.id("userNameInput")).sendKeys("admin");
		driver.findElement(By.id("passwordInput")).clear();
		driver.findElement(By.id("passwordInput")).sendKeys("admin");
		driver.findElement(By.id("loginButton")).click();
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Register", 10);
		SeleniumUtils.textoPresentePagina(driver, "Admin Panel");
	}

	// P3: Create a new proposal
	@Test
	public void test3() throws Exception {
		Random rand = new Random();
		proposalName = "Proposal" + rand.nextInt();

		login();
		driver.findElement(By.id("titleInput")).clear();
		driver.findElement(By.id("titleInput")).sendKeys(proposalName);
		driver.findElement(By.id("contentInput")).clear();
		driver.findElement(By.id("contentInput")).sendKeys(
				"This is a proposal for testing");
		new Select(driver.findElement(By.id("category"))).selectByVisibleText(
				"Cat1");
		driver.findElement(By.id("SubmitProp")).click();

		SeleniumUtils.EsperaCargaPagina(driver, "text", proposalName, 15);

	}

	// P4: Vote up and down a proposal
	@Test
	public void test4() throws Exception {
		login();
		driver.findElement(By.id(proposalName)).click();
		SeleniumUtils.EsperaCargaPagina(driver, "id", "upvote", 10);
		driver.findElement(By.id("upvote")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "id", "upVotes", 10);
		assertEquals(driver.findElement(By.id("upVotes")).getText(), "1");
		assertEquals(driver.findElement(By.id("downVotes")).getText(), "0");
		driver.findElement(By.id("downvote")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "id", "downVotes", 10);
		assertEquals(driver.findElement(By.id("downVotes")).getText(), "1");
		assertEquals(driver.findElement(By.id("upVotes")).getText(), "0");
	}

	// P5: Comment a proposal and vote it
	@Test
	public void test5() throws Exception {
		login();
		driver.findElement(By.id(proposalName)).click();
		SeleniumUtils.EsperaCargaPagina(driver, "id", "contentInput", 10);
		driver.findElement(By.id("contentInput")).clear();
		driver.findElement(By.id("contentInput")).sendKeys(
				"This is a comment on a proposal");
		driver.findElement(By.id("SubmitComment")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text",
				"This is a comment on a proposal", 10);

	}

	// P6: Login as Admin
	@Test
	public void test6() throws Exception {
		loginAdmin();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
