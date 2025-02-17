package ProjectJob;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Project2Test {
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
    	
    	// Locate the heading element
        WebElement headingElement = driver.findElement(By.cssSelector("h1.entry-title"));
        String heading = headingElement.getText();
        System.out.println("heading: " + heading);
        // Assert page title
        Assert.assertEquals(heading, "Welcome to Alchemy Jobs");
        
        
    }
    
    
    // Tear down function
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}