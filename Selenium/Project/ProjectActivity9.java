package ProjectJob;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Project9Test {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        // Initialize the Firefox driver
        driver = new FirefoxDriver();
        // Initialize the wait object
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open browser
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");
    }

    @Test
    @Parameters({ "username1", "password1", "message" })
    public void loginTestCase(String username1, String password1, @Optional("Login Success!") String message) {
        // Find username and password fields
        WebElement usernameField = driver.findElement(By.id("user_login"));
        WebElement passwordField = driver.findElement(By.id("user_pass"));

        // Enter credentials
        usernameField.sendKeys(username1);
        passwordField.sendKeys(password1);

        // Click the submit button
        driver.findElement(By.xpath("//input[@class='button button-primary button-large']")).click();

       

        // Assert login message
        String loginMessage = driver.findElement(By.xpath("//h1")).getText();
        Assert.assertEquals("Dashboard", loginMessage);
        
    }
    
    
    @Test(priority = 1)
    public void postAJob() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,1000)");
    	
    	WebElement hamburgerMenu = driver.findElement(By.xpath("(//div[@class='wp-menu-name'])[6]")); 
    	
    	hamburgerMenu.click(); 
    	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
   
    	WebElement submenuItem = driver.findElement(By.linkText("Add New"));
    	submenuItem.click();
    
    	wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement jobTitle1 = driver.findElement(By.cssSelector("textarea.editor-post-title__input"));
    	String jobCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    	StringBuilder jobTitle = new StringBuilder(); 
    	Random random1 = new Random(); 
    	for (int i = 0; i < 10; i++) 
    	{ // Generate random string of 10 characters 
    		jobTitle.append(jobCharacters.charAt(random1.nextInt(jobCharacters.length()))); 
    		} 
    	String AppendText = "TestQA_";
    	String finalJobTitle = AppendText + jobTitle;
    	String jobTitle3 = finalJobTitle.toString();
    	
    	jobTitle1.sendKeys(jobTitle3);
		/*
		 * //Email id String characters =
		 * "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		 * StringBuilder email = new StringBuilder(); Random random = new Random(); for
		 * (int i = 0; i < 10; i++) { // Generate random string of 10 characters
		 * email.append(characters.charAt(random.nextInt(characters.length()))); }
		 * email.append("@example.com"); String emailInput = email.toString();
		 * 
		 * WebElement emailLocator = driver.findElement(By.id("create_account_email"));
		 * emailLocator.sendKeys(emailInput);
		 */
    	//company Website
    	WebElement companyWebsite = driver.findElement(By.name("_company_website"));
    	companyWebsite.click();
    	companyWebsite.sendKeys("ibm.com");
    	
    	//Company Name
    	WebElement companyName = driver.findElement(By.name("_company_name"));
    	companyName.click();
    	companyName.sendKeys("IBM");
    	
    	
    	//Publish
    	WebElement Publish = driver.findElement(By.cssSelector("a.components-button.editor-post-preview.is-secondary"));
    	Publish.click();
    	wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	
    	
		
		/*
		 * WebElement viewJob = driver.findElement(By.linkText("View Job"));
		 * viewJob.click(); wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		 * WebElement verifyJobTitle = driver.findElement(By.xpath("//h1"));
		 */
		
		
		  //Navigating back to home page 
    	WebElement homePage = driver.findElement(By.xpath("//a[@aria-label='View Posts']"));
		homePage.click(); 
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement verifyJobTitle = driver.findElement(By.xpath("(//a[@class='tips job_title'])[1]"));
		 
    	String actualJobTitle = verifyJobTitle.getText();
    	System.out.println("Jobs Page Title is: " + actualJobTitle);
        
    	
    	
    
        // Assert page title
        Assert.assertEquals(actualJobTitle, jobTitle3);
    }

    @AfterClass
    public void afterClass() {
        // Close browser
        driver.quit();
    }
}