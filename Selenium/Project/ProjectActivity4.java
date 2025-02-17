package ProjectJob;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Project4Test {
    WebDriver driver;
    
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
    	WebElement heading = driver.findElement(By.xpath("//h2")); 
    	String secondHeading = heading.getText();
    	System.out.println("Second Heading: " + secondHeading);
        
        // Assert page title
        Assert.assertEquals(secondHeading, "Quia quis non");
        
        
    }
    
    
    // Tear down function
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}