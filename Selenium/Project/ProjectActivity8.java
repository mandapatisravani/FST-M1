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

public class ProjectActivity8 {
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
	 public void applyleaveTest() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_dashboard_index")));
		driver.findElement(By.id("menu_dashboard_index")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/span[contains(text(),'Apply Leave')]")));
		driver.findElement(By.xpath("//a/span[contains(text(),'Apply Leave')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("applyleave_txtLeaveType")));
		WebElement dropdown = driver.findElement(By.id("applyleave_txtLeaveType"));
        // Create an instance of Select class and pass the dropdown WebElement to it
        Select select = new Select(dropdown);
        // Select an option by visible text
        select.selectByVisibleText("DayOff");	
        driver.findElement(By.id("applyleave_txtFromDate")).clear();
        driver.findElement(By.id("applyleave_txtFromDate")).sendKeys("2025-01-28");
        driver.findElement(By.id("applyleave_txtToDate")).clear();
        driver.findElement(By.id("applyleave_txtToDate")).sendKeys("2025-03-28");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='applyBtn']")));
        driver.findElement(By.id("applyleave_txtComment")).sendKeys("Testing Purpose");	
        driver.findElement(By.id("applyBtn")).click();
		
//        //check leave status
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='table']/tbody/tr[2]/td[4]")));
        WebElement cellValue = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[2]/td[4]"));
        System.out.println(cellValue.getText());
	 }
	       
	  @AfterClass
	  public void tearDown() {
	   // Close the browser
	     driver.quit();
	    }

}
