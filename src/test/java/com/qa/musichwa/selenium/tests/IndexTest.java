package com.qa.musichwa.selenium.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.musichwa.selenium.pages.IndexPage;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IndexTest {

	private final String DOMAIN = "http://localhost:";
	
	@LocalServerPort
	int port;
	
	private WebDriver driver;
	
	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);
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
	
//	@Test
//	public void testFormInput() {
//		driver.get(DOMAIN + port);
//		IndexPage index = PageFactory.initElements(driver, IndexPage.class);
//		
//	}
	
	@After
	public void shutdown() {
		driver.close();
	}
}
