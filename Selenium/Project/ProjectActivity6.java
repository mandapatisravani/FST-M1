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

public class Project6Test {
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
    
    //Test function
    @Test(priority = 2)
    public void JobSearch() {
    	
    	// Locate the search box
    	WebElement searchbox = driver.findElement(By.cssSelector("input#search_keywords"));
    	searchbox.click();
    	searchbox.sendKeys("banking");
    	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	//click on submit button
    	driver.findElement(By.xpath("//input[@type='submit']")).click();
    	//click on the job
    	WebElement joblist = driver.findElement(By.xpath("(//h3)[1]"));
    	joblist.click();
    	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	WebElement jobApply = driver.findElement(By.xpath("//input"));
    	jobApply.click();
    	wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    	WebElement email = driver.findElement(By.cssSelector("a.job_application_email"));
    	String actualEmail = email.getText();
    	System.out.println("Email Id is : " + actualEmail);
    
        // Assert page title
        Assert.assertNotNull(actualEmail, "The Email id should not null");
        
        
    }
    
    // Tear down function
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
