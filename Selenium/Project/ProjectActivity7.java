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

public class ProjectActivity7 {
	
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
	 public void updateUserQualificationTest() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.findElement(By.id("menu_pim_viewMyDetails")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='sidenav']/li[9]")));
		driver.findElement(By.xpath("//ul[@id='sidenav']/li[9]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addWorkExperience")));
		driver.findElement(By.id("addWorkExperience")).click();
		driver.findElement(By.id("experience_employer")).clear();
		driver.findElement(By.id("experience_employer")).sendKeys("IBM");
		driver.findElement(By.id("experience_jobtitle")).clear();
		driver.findElement(By.id("experience_jobtitle")).sendKeys("Quality Analyst");
		driver.findElement(By.id("btnWorkExpSave")).click();
     
		
	 }
	       
	  @AfterClass
	  public void tearDown() {
	   // Close the browser
	     driver.quit();
	    }

}
