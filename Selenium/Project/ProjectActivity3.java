package projectActivities;
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

public class ProjectActivity3 {
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
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 WebElement login_Success = driver.findElement(By.id("welcome"));
		 System.out.println(login_Success.getAccessibleName());	
		 Assert.assertTrue(driver.findElement(By.id("welcome")).isDisplayed());
		 //	Assert.assertEquals(login_Success.getAccessibleName(), "Welcome Avik");		 
		 
	    }
	       
	  @AfterClass
	  public void tearDown() {
	   // Close the browser
	     driver.quit();
	    }
}
