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

public class ProjectActivity5 {
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
		
		 WebElement login_Success = driver.findElement(By.id("welcome"));		 
		 
	    }
	 @Test(dependsOnMethods = {
			 "loginTest"			 
	 })	 
	 public void editUserTest() {
	
		driver.findElement(By.id("menu_pim_viewMyDetails")).click();
		
		
		WebElement editButton = driver.findElement(By.id("btnSave"));
		editButton.isEnabled();
		//Edit button
		driver.findElement(By.id("btnSave")).click();	
		driver.findElement(By.id("personal_txtEmpFirstName")).clear();	
		driver.findElement(By.id("personal_txtEmpFirstName")).sendKeys("Mani");
		driver.findElement(By.id("personal_txtEmpLastName")).clear();	
		driver.findElement(By.id("personal_txtEmpLastName")).sendKeys("bala");		
		driver.findElement(By.id("personal_optGender_2")).click();
		
		// Locate the dropdown element by its ID
        WebElement dropdown = driver.findElement(By.id("personal_cmbNation"));
        // Create an instance of Select class and pass the dropdown WebElement to it
        Select select = new Select(dropdown);
        // Select an option by visible text
        select.selectByVisibleText("Indian");	
        driver.findElement(By.id("personal_DOB")).clear();		
		driver.findElement(By.id("personal_DOB")).sendKeys("1999-09-11");
		driver.findElement(By.id("btnSave")).click();
		
	 }
	       
	  @AfterClass
	  public void tearDown() {
	   // Close the browser
	     driver.quit();
	    }


}
