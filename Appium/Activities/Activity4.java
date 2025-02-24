import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

public class Activity4 {
    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException, URISyntaxException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.google.android.dialer");
        options.setAppActivity(".extensions.GoogleDialtactsActivity");
        options.noReset();

        URL serverURL = new URI("http://localhost:4723").toURL();

        driver = new AndroidDriver(serverURL, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    @Test
    public void addContact() {
    	
//    	driver.findElement(AppiumBy.accessibilityId("Create new contact")).click();
    	
    	driver.executeScript("mobile: clickGesture", Map.of("x", 380, "y", 826));
    	driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"First name\"]")).sendKeys("Anu");
    	driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"Last name\"]")).sendKeys("Balan");
    	driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"Phone\"]")).sendKeys("9042419334");
    	driver.findElement(AppiumBy.id("com.google.android.contacts:id/toolbar_button")).click();
    	wait.until(ExpectedConditions
    			.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.google.android.dialer:id/contact_name\" and @text=\"Anu Balan\"]")));
    	
    	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.google.android.dialer:id/contact_name\" and @text=\"Anu Balan\"]")).click();
    	String ContactName = driver.findElement(AppiumBy.id("com.google.android.contacts:id/large_title")).getText();
    	Assert.assertEquals(ContactName, "Anu Balan");
    	
    	driver.findElement(AppiumBy.accessibilityId("More options")).click();
    	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.google.android.contacts:id/title\" and @text=\"Delete\"]")).click();
    	driver.findElement(AppiumBy.id("android:id/button1")).click();
    
       
    }



    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}