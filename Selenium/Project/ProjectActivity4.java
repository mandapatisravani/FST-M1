package projectActivities;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ProjectActivity4 {
	
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
	 public void newEmployeeTest() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.findElement(By.id("menu_pim_viewPimModule")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnAdd")));
		driver.findElement(By.id("btnAdd")).click();
		driver.findElement(By.id("firstName")).sendKeys("GUNA");
		driver.findElement(By.id("lastName")).sendKeys("Sukesh");		
		driver.findElement(By.id("btnSave")).click();
		driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
		driver.findElement(By.id("empsearch_employee_name_empName")).sendKeys("SUKESH");	
		driver.findElement(By.id("searchBtn")).click();
		
		WebElement cellValue = driver.findElement(By.xpath("//table[contains(@class, 'table hover')]/tbody/tr[1]/td[3]"));
        System.out.println(cellValue.getText());
	 }
	       
	  @AfterClass
	  public void tearDown() {
	   // Close the browser
	     driver.quit();
	    }
}
