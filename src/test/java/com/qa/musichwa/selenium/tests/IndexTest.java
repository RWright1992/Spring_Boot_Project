package com.qa.musichwa.selenium.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.musichwa.selenium.pages.IndexPage;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:testschema.sql", "classpath:testdata.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class IndexTest {

	private final String DOMAIN = "http://localhost:";
	
	@LocalServerPort
	int port;
	
	private WebDriver driver;
	
	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1366, 768));
	}
	
	@Test
	public void testIndexLoad() {
		driver.get(DOMAIN + port);
		assertEquals("Home", driver.getTitle());
	}
	
	@Test
	public void missingValueTest() {
		driver.get(DOMAIN + port);
		
		IndexPage index = PageFactory.initElements(driver, IndexPage.class);
		
		try {
			index.getCreateBtn().click();
		} catch (UnhandledAlertException f) {
		    try {
		    	WebDriverWait wait = new WebDriverWait(driver, 2);
		        wait.until(ExpectedConditions.alertIsPresent());
		        Alert alert = driver.switchTo().alert();
		        String alertText = alert.getText();
		        assertTrue(alertText.contains("All fields"));
		        alert.accept();
		    } catch (NoAlertPresentException e) {
		        e.printStackTrace();
		    }
		}
	}
	
	@Test
	public void testFormInputAlert() {
		driver.get(DOMAIN + port);
		
		IndexPage index = PageFactory.initElements(driver, IndexPage.class);
		
		index.getNameInput().sendKeys("Test");
		index.getArtistInput().sendKeys("Test2");
		index.getYearInput().sendKeys("2021");
		index.getTypeInput().sendKeys("Single");
		index.getCreateBtn().click();
		
		WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(index.getSuccessAlert()));
		
		assertFalse(driver.findElements(By.className("alert-success")).isEmpty());
	}
	
	@Test
	public void testCreation() {
		driver.get(DOMAIN + port);
		
		IndexPage index = PageFactory.initElements(driver, IndexPage.class);
		
		index.getNameInput().sendKeys("Test");
		index.getArtistInput().sendKeys("Test2");
		index.getYearInput().sendKeys("2021");
		index.getTypeInput().sendKeys("Single");
		index.getCreateBtn().click();
		
		WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(index.getSuccessAlert()));
		
		assertTrue(index.getResultsDiv().getText().contains("Test2"));
	}
	
	@Test
	public void testDelete() {
		driver.get(DOMAIN + port);
		
		IndexPage index = PageFactory.initElements(driver, IndexPage.class);
		
		WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(index.getDelButton()));
		index.getDelButton().click();
		
		assertTrue(!driver.findElements(By.className("entry-values")).isEmpty());
	}
	
	@Test
	public void testReset() {
		driver.get(DOMAIN + port);
		
		IndexPage index = PageFactory.initElements(driver, IndexPage.class);
		
		index.getNameInput().sendKeys("Test");
		index.getResetButton().click();
		
		assertFalse(index.getNameInput().getText().contains("Test"));
	}
	
	@Test
	public void testModal() {
		driver.get(DOMAIN + port);
		
		IndexPage index = PageFactory.initElements(driver, IndexPage.class);
		
		index.getEditButton().click();
		
		WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(index.getEditModal()));
        
		driver.switchTo().activeElement();
		
		assertTrue(index.getEditModal().getText().contains("Artist"));
	}
	
	@After
	public void shutdown() {
		driver.close();
	}
}
