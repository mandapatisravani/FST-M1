import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class GoogleTask {
	
	AndroidDriver driver;
	WebDriverWait wait;
	List<String> lists;
	
	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {
		
		
		URL serverURL = new URI("http://127.0.0.1:4723").toURL();
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.google.android.apps.tasks");
		options.setAppActivity(".ui.TaskListsActivity");
		options.noReset();
		driver = new AndroidDriver(serverURL, options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		lists = new ArrayList<String>();
		lists.add("Complete Activity with Google Tasks");
		lists.add("Complete Activity with Google Keep");
		lists.add("Complete the second Activity Google Keep");
	}
	
	@Test
	public void addTaskGoogleTask() throws InterruptedException {
		
		for(String list : lists) {
			
			driver.findElement(AppiumBy.accessibilityId("Create new task")).click();
			String editfield = "//android.widget.EditText[@resource-id=\"com.google.android.apps.tasks:id/add_task_title\"]";
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(AppiumBy.xpath(editfield))));
			driver.findElement(AppiumBy.xpath(editfield)).sendKeys(list);
			driver.findElement(AppiumBy.id("com.google.android.apps.tasks:id/add_task_done")).click();
//			Thread.sleep(5000);
		}		
		
	}
	
	@Test
	public void validateAddedTask() {
		
		String element = "//android.widget.TextView[@resource-id='com.google.android.apps.tasks:id/task_name']";

		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath(element)));
		List<WebElement> taskElements = driver.findElements(AppiumBy.xpath(element));
		List<String> actualTask = new ArrayList<String>();
		
		for(WebElement task: taskElements) {
			actualTask.add(task.getText());
		}
		
		System.out.println("Tasks found in Google Tasks App: " + actualTask);
		
		for(String expectedTask : lists) {
			assert actualTask.contains(expectedTask) : "Task Not Found " +expectedTask;
		}
		
		System.out.println("All tasks were added successfully");
	
	}

	
	
	@AfterClass
	public void tearDown() {	
		
		driver.quit();
	}
}
