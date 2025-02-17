package ProjectJob;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Random;

public class Project7Test {
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
    public void postAJob() {
    	
    	// Locate the link text
    	WebElement link = driver.findElement(By.linkText("Post a Job"));
    	link.click();
    	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	//Email id
    	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; 
    	StringBuilder email = new StringBuilder(); 
    	Random random = new Random();
    	for (int i = 0; i < 10; i++)
    	{ // Generate random string of 10 characters 
    		email.append(characters.charAt(random.nextInt(characters.length()))); 
    		}
    	email.append("@example.com");
    	String emailInput = email.toString();
    	
    	WebElement emailLocator = driver.findElement(By.id("create_account_email"));
    	emailLocator.sendKeys(emailInput);
    	//Job Title
    	WebElement jobTitle1 = driver.findElement(By.id("job_title"));
    	String jobCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    	StringBuilder jobTitle = new StringBuilder(); 
    	Random random1 = new Random(); 
    	for (int i = 0; i < 10; i++) 
    	{ // Generate random string of 10 characters 
    		jobTitle.append(characters.charAt(random1.nextInt(jobCharacters.length()))); 
    		} 
    	jobTitle.append("_QA");
    	String jobTitle2 = jobTitle.toString();
    	
    	jobTitle1.sendKeys(jobTitle2);
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,1000)");
    	//Job Type
    	WebElement dropdownElement = driver.findElement(By.id("job_type")); 
    	Select dropdown = new Select(dropdownElement); 
    	dropdown.selectByValue("2");
    	
    	//Job Description
    	WebElement insideIframe = driver.findElement(By.id("job_description_ifr"));
    	//WebElement jobDescription = driver.findElement(By.xpath("//body/p"));
    	insideIframe.click();
    	insideIframe.sendKeys("Test");
    	//Job URL
    	WebElement jobURL = driver.findElement(By.id("application"));
    	jobURL.sendKeys("https://test.com");
    	//Company name
    	WebElement companyName = driver.findElement(By.id("company_name"));
    	companyName.sendKeys("TestNGO");
    	//Preview
    	WebElement preview = driver.findElement(By.xpath("//input[@class='button']"));
    	preview.click();
    
    	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	//Submit Listing
    	WebElement submitListing = driver.findElement(By.id("job_preview_submit_button"));
    	submitListing.click();
    	
    	//Jobs 
    	// Locate the second heading
    	WebElement link1 = driver.findElement(By.linkText("Jobs"));
    	link1.click();
    	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement pageTitle = driver.findElement(By.xpath("//h1"));
    	String actualPageTitle = pageTitle.getText();
    	System.out.println("Jobs Page Title is: " + actualPageTitle);
        String expectedTitle = "Jobs";
        // Assert page title
        Assert.assertEquals(actualPageTitle, expectedTitle, "Page Title does not Match");
       
    	//JobSearch
    	// Locate the search box
    	WebElement searchbox = driver.findElement(By.cssSelector("input#search_keywords"));
    	searchbox.click();
    	searchbox.sendKeys(jobTitle2);
    	wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    	//click on submit button
    	driver.findElement(By.xpath("(//input[@type='submit'])[2]")).click();
    	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	WebElement actualJob = driver.findElement(By.xpath("(//div[@class='position']/h3)[1]"));
    	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	String actualJobTitle = actualJob.getText();
    	System.out.println("The Actual JobTitle: " + actualJobTitle);
    	
    	
    	
    
        // Assert page title
        Assert.assertEquals(actualJobTitle, jobTitle2);
        
        
    }
    
    // Tear down function
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
