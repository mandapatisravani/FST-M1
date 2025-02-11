package projectActivities;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ProjectActivity6 {
	 WebDriver driver;
	 @BeforeClass
	    public void setUp() {
	        // Initialize driver
	        driver = new FirefoxDriver();	        
	        driver.get("http://alchemy.hguy.co/orangehrm");
	    }	
	 @Test
	 public void loginTest() {
		 WebElement login_UserName = driver.findElement(By.id("txtUsername"));
		 login_UserName.sendKeys("orange");
		 WebElement login_securitykey = driver.findElement(By.id("txtPassword"));
		 login_securitykey.sendKeys("orangepassword123");
		 driver.findElement(By.id("btnLogin")).click();
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 WebElement login_Success = driver.findElement(By.id("welcome"));		 
		 
	    }
	 @Test(dependsOnMethods = {
			 "loginTest"			 
	 })	 
	 public void searchDirectoryTest() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_directory_viewDirectory")));
		driver.findElement(By.id("menu_directory_viewDirectory")).click();	
		
		WebElement directory = driver.findElement(By.id("menu_directory_viewDirectory"));
		directory.isEnabled();
		//Edit button
		driver.findElement(By.id("menu_directory_viewDirectory")).click();	
	    wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    WebElement searchHeader = driver.findElement(By.xpath("//h1[contains(text(),'Search Directory')]"));	
	    Assert.assertEquals(searchHeader.getAccessibleName(), "Search Directory");
		
	 }
	       
	  @AfterClass
	  public void tearDown() {
	   // Close the browser
	     driver.quit();
	    }

}
