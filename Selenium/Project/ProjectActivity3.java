package ProjectJob;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Project3Test {
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
    	
    	// Locate the header image
    	WebElement headerImage = driver.findElement(By.xpath("//div/img")); 
    	String imageUrl = headerImage.getAttribute("src");
    	System.out.println("Header Image URL: " + imageUrl);
        
        // Assert page title
        Assert.assertEquals(imageUrl, "https://alchemy.hguy.co/jobs/wp-content/uploads/2019/09/career-corporate-job-776615-e1569782235456-1024x271.jpg");
        
        
    }
    
    
    // Tear down function
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}