import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity5 {

	AndroidDriver driver;
	WebDriverWait wait;
	 @BeforeClass
	    public void setUp() throws MalformedURLException, URISyntaxException {
	        UiAutomator2Options options = new UiAutomator2Options();
	        options.setPlatformName("android");
	        options.setAutomationName("UiAutomator2");
	        options.setAppPackage("com.google.android.apps.messaging");
	        options.setAppActivity(".ui.ConversationListActivity");
	        options.noReset();
	        URL serverURL = new URI("http://localhost:4723").toURL();
	        driver = new AndroidDriver(serverURL, options);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    }
	 
	 @Test
	 public void messageApplication() {
		 
		WebElement startChat =  wait.until(ExpectedConditions.elementToBeClickable(driver
				.findElement(AppiumBy.accessibilityId("Start chat"))));
		
		startChat.click();
		WebElement numberField =  wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Type names, phone numbers, or emails']"))));
		
		driver.findElement(AppiumBy
				.xpath("//android.widget.TextView[@text=\"Anu Balan\"]")).click();
//		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
//		driver.findElement(AppiumBy.id("GlideMonogram")).click();
		
		WebElement textField =wait.until(ExpectedConditions.elementToBeClickable(driver
				.findElement(AppiumBy.id("com.google.android.apps.messaging:id/compose_message_text"))));
		textField.sendKeys("Hello From Appium");
		driver.findElement(AppiumBy.accessibilityId("Send SMS")).click();
		
		String sentMessage = "//android.widget.TextView[@resource-id='message_text']";
		WebElement messageElement = wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.xpath(sentMessage))));
		assert messageElement.getText().contains("Hello From Appium");
		
		
		
		
		 
	 }
}
