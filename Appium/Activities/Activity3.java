import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Activity3 {
    AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException, URISyntaxException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.google.android.calculator");
        options.setAppActivity("com.android.calculator2.Calculator");
        
        options.noReset();

        URL serverURL = new URI("http://localhost:4723").toURL();

        driver = new AndroidDriver(serverURL, options);
    }
    
    @Test
    public void addCalculator() {
    
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_5")).click();
        driver.findElement(AppiumBy.accessibilityId("plus")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_9")).click();
        driver.findElement(AppiumBy.accessibilityId("equals")).click();

        String result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final")).getText();

        Assert.assertEquals(result, "14");
    }
    
    @Test
    public void subCalculator() {
    
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_1")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_0")).click();
        driver.findElement(AppiumBy.accessibilityId("minus")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_5")).click();
        driver.findElement(AppiumBy.accessibilityId("equals")).click();

        String result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final")).getText();

        Assert.assertEquals(result, "5");
    }


  
    @Test
    public void multiplyCalculator() {
    
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_5")).click();
        driver.findElement(AppiumBy.accessibilityId("multiply")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_1")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_0")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_0")).click();
        driver.findElement(AppiumBy.accessibilityId("equals")).click();

        String result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final")).getText();

        Assert.assertEquals(result, "500");
    }
    
    @Test
    public void divCalculator() {
    
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_5")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_0")).click();
        driver.findElement(AppiumBy.accessibilityId("divide")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_2")).click();

        driver.findElement(AppiumBy.accessibilityId("equals")).click();

        String result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final")).getText();

        Assert.assertEquals(result, "25");
    }
    
   
    
   

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}