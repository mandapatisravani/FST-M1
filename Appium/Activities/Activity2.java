import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity2 {
	
	AndroidDriver driver;
	
	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");

        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();
	
		URL serverURL = new URI("http://127.0.0.1:4723").toURL();
		driver = new AndroidDriver(serverURL, options);
		driver.get("https://training-support.net");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@Test
	public void chromeTest() throws InterruptedException {
		
		Thread.sleep(10000);
		
		String pageHeading = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Training Support\"]")).getText();
		System.out.println(pageHeading);
		
		driver.findElement(AppiumBy.accessibilityId("About Us")).click();
		
		String aboutUsHeading = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"About Us\"]")).getText();
		System.out.println(aboutUsHeading);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
