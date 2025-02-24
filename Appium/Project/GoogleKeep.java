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

public class GoogleKeep {
	
	AndroidDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {
		
		
		URL serverURL = new URI("http://127.0.0.1:4723").toURL();
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.google.android.keep");
		options.setAppActivity(".activities.BrowseActivity");
		options.noReset();
		driver = new AndroidDriver(serverURL, options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
	}

	@Test
	public void addNotesGoogleKeep() {
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.accessibilityId("Create a note"))));
		driver.findElement(AppiumBy.accessibilityId("Create a note")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.accessibilityId("New text note")))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.id("com.google.android.keep:id/editable_title"))));
		driver.findElement(AppiumBy.id("com.google.android.keep:id/editable_title")).sendKeys("Appium Practice");
		driver.findElement(AppiumBy.id("com.google.android.keep:id/edit_note_text")).sendKeys("Keep Learning");
		driver.findElement(AppiumBy.accessibilityId("Navigate up")).click();
		
		 String element = "//android.widget.TextView[@resource-id='com.google.android.keep:id/index_note_title']";
		 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(AppiumBy.xpath(element)));
		 List<WebElement> noteList = driver.findElements(AppiumBy.xpath(element));
		
		 System.out.println("Total Notes Found: " + noteList.size());
		 
		 List<String> actualList = new ArrayList<String>();
		 
		 for(WebElement noteName:noteList) {
			 actualList.add(noteName.getText());
		 }
		 
		 assert actualList.contains("Appium Practice") : "Not found";
		 System.out.println("Validation completed");
	}
	
	@Test
	public void addNotesWithReminder() {
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.accessibilityId("Create a note"))));
		driver.findElement(AppiumBy.accessibilityId("Create a note")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.accessibilityId("New text note")))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.id("com.google.android.keep:id/editable_title"))));
		driver.findElement(AppiumBy.id("com.google.android.keep:id/editable_title")).sendKeys("Appium Practice By Anu");
		driver.findElement(AppiumBy.id("com.google.android.keep:id/edit_note_text")).sendKeys("Keep Learning");
		driver.findElement(AppiumBy.accessibilityId("Reminder")).click();
		String pickadate = "//android.widget.TextView[@resource-id=\"com.google.android.keep:id/menu_text\" and @text=\"Pick a date & time\"]";
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(AppiumBy.xpath(pickadate)))).click();
		String selectSession = "com.google.android.keep:id/time_spinner";
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id(selectSession))).click();
		String selectAfternoon = "//android.widget.TextView[@resource-id=\"com.google.android.keep:id/text\" and @text=\"Afternoon\"]";
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(selectAfternoon))).click();
		driver.findElement(AppiumBy.id("com.google.android.keep:id/save")).click();
		driver.findElement(AppiumBy.accessibilityId("Navigate up")).click();
		
		String timeOnNote = "com.google.android.keep:id/reminder_chip_text";
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.id(timeOnNote))));
		
		assert driver.findElement(AppiumBy.id(timeOnNote)).isDisplayed() : "Time not found";
		System.out.println("Note Added successfully with a reminder");
		
		

	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
