package ProjectJob;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Project5Test {
    WebDriver driver;
    WebDriverWait wait;
    
    // Setup function
    @BeforeClass
    public void setUp() {
        // Initialize Fire fox driver
        driver = new FirefoxDriver();
        // Open the page
        driver.get("https://alchemy.hguy.co/jobs");
    }
    
    // Test function
    @Test(priority = 1)
    public void homePageTest() {
    	
    	// Locate the second heading
    	WebElement link = driver.findElement(By.linkText("Jobs"));
    	link.click();
    	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement pageTitle = driver.findElement(By.xpath("//h1"));
    	String actualPageTitle = pageTitle.getText();
    	System.out.println("Jobs Page Title is: " + actualPageTitle);
        String expectedTitle = "Jobs";
        // Assert page title
        Assert.assertEquals(actualPageTitle, expectedTitle, "Page Title does not Match");
        
        
    }
    
    
    // Tear down function
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
