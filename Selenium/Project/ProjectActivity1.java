package ProjectJob;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Project1Test {
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
    	
    	// Locate the title element
        WebElement titleElement = driver.findElement(By.cssSelector("h1.site-title"));
        String title = titleElement.getText();
        System.out.println("Title: " + title);

        // Locate the subtitle element
        WebElement subtitleElement = driver.findElement(By.cssSelector("p.site-description"));
        String subtitle = subtitleElement.getText();
        System.out.println("Subtitle: " + subtitle);
        String finalTitle = title + subtitle;
        System.out.println("The Final Title & SubTitle are: " + finalTitle);
    
        // Assert page title
        Assert.assertEquals(finalTitle, "Alchemy JobsJob Board Application");
        
        
    }
    
    
    // Tear down function
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}