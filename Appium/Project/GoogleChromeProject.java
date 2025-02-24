
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class GoogleChromeProject {
	
	AndroidDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void setUpDriver() throws MalformedURLException, URISyntaxException {
	    URL serverURL = new URI("http://127.0.0.1:4723").toURL();
	    UiAutomator2Options options = new UiAutomator2Options();
	    options.setPlatformName("android");
	    options.setAutomationName("UiAutomator2");
	    options.setCapability("browserName", "Chrome");
	    options.setCapability("chromedriverExecutable", "C:\\Users\\AnuBalan\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	    options.noReset();
	    driver = new AndroidDriver(serverURL, options);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test (priority = 1)
	public void toDoList() throws InterruptedException {
		driver.get("https://v1.training-support.net/selenium");
		
		String ToDoList = "//div[@class='header' and contains(text(),'To-Do List')]";
        WebElement element = driver.findElement(By.xpath(ToDoList));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
       
        driver.findElement(By.xpath(ToDoList)).click();
        
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("taskInput"))));
        driver.findElement(By.cssSelector(".bottom")).click();
        driver.findElement(By.id("taskInput")).sendKeys("Appium Practice");
        driver.findElement(By.xpath("//button[text() = 'Add Task']")).click();
        driver.findElement(By.id("taskInput")).sendKeys("Keep Learning");
        driver.findElement(By.xpath("//button[text() = 'Add Task']")).click();
        driver.findElement(By.id("taskInput")).sendKeys("Motivate Yourself");
        driver.findElement(By.xpath("//button[text() = 'Add Task']")).click();
        
        List<WebElement> tasks = driver.findElements(By.className("item"));
        for (WebElement task : tasks) {
            task.click(); 
            Thread.sleep(5000);
        }
        Thread.sleep(5000);
        driver.findElement(By.cssSelector(".bottom")).click();
        List<WebElement> remainingTasks = driver.findElements(By.cssSelector(".task"));
        Assert.assertEquals(remainingTasks.size(), 0, "There are still tasks present!");
        
	}
	
	@DataProvider(name = "UserDetails")
	public Object[][] userCredentials() {
		return new Object[][] {
            {"admin", "password", "Welcome Back, admin"},
            {"Anu", "Welcome1!", "Invalid Credentials"}
        };
	}
	
	
	@Test(priority = 2, dataProvider = "UserDetails") 
	public void LoginForm(String enterUsername, String enterPassword, String expectedMessage) {
		 driver.get("https://v1.training-support.net/selenium");
		String login = "//div[contains(text(), 'Login')]";
		WebElement loginElement = driver.findElement(By.xpath(login));
		JavascriptExecutor js =  (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", loginElement);
		loginElement.click();
		
		WebElement username = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("username"))));
		username.clear();
		username.sendKeys(enterUsername);
		WebElement password = driver.findElement(By.id("password"));
		password.clear();
		password.sendKeys(enterPassword);
		driver.hideKeyboard();
		WebElement loginButton =driver.findElement(By.xpath("//*[text()= 'Log in']"));
		loginButton.click();
		
		String message = "action-confirmation";
		WebElement confirmMessage = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(message))));
		Assert.assertEquals(confirmMessage.getText(), expectedMessage, "Login message didnt match the expected");
		
	}	
	
	@Test(priority = 3, dataProvider = "UserDetails")
	public void PoupCardSignin(String enterUsername, String enterPassword, String expectedMessage) throws InterruptedException {
		 driver.get("https://v1.training-support.net/selenium");
		String login = "//div[contains(text(), 'Popups')]";
		WebElement popUpElement = driver.findElement(By.xpath(login));
		JavascriptExecutor js =  (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", popUpElement);
		popUpElement.click();
		
		String signIn = "//button[text() ='Sign In']";
		WebElement signInButton = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(signIn))));
		signInButton.click();
		
		WebElement username = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("username"))));
		username.clear();
		username.sendKeys(enterUsername);
		WebElement password = driver.findElement(By.id("password"));
		password.clear();
		password.sendKeys(enterPassword);
//		driver.hideKeyboard();
		
		WebElement loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()= 'Log in']")));
//		js.executeScript("arguments[0].scrollIntoView(true);", loginButton);
//		js.executeScript("arguments[0].click();", loginButton);
		Actions actions = new Actions(driver);
		actions.moveToElement(loginButton).click().perform();
		Thread.sleep(5000);

		String message = "action-confirmation";
		WebElement confirmMessage = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(message))));
		Assert.assertEquals(confirmMessage.getText(), expectedMessage, "Login message didnt match the expected");
		
	}	
	
//	@AfterTest
//	public void tearDownTest() {
//	    if (driver != null) {
//	        driver.quit(); // Quit the driver after each test
//	    }
//	}

	@AfterClass
	public void tearDownClass() {
	    if (driver != null) {
	        driver.quit(); // Ensure the driver is closed after the entire class runs
	        driver = null;
	    }
	}
}
