import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity6 extends ActionsBase{
	
	AndroidDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setCapability("browserName", "Chrome");
	    options.setCapability("chromedriverExecutable", "C:\\Users\\AnuBalan\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	    options.noReset();
        options.noReset();
	
		URL serverURL = new URI("http://127.0.0.1:4723").toURL();
		driver = new AndroidDriver(serverURL, options);
		driver.get("https://training-support.net/webelements");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	
	@Test
	public void scrollChromeTest() throws InterruptedException {
		
		WebElement slider = driver.findElement(By.xpath("//span[text() = 'Sliders']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block:'center'})",slider );
		Actions action = new Actions(driver);
		action.moveToElement(slider).click().perform();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("h2"))));
		WebElement volume = driver.findElement(By.id("volume"));
		js.executeScript("arguments[0].scrollIntoView({block:'center'})",volume );
		
	}
	
	@Test
	public void voulme75Test() {
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("volume"))));
		Dimension dims = driver.manage().window().getSize();
		
		Point start = new Point((int)(dims.getWidth() * .50), (int)(dims.getHeight() * .81));
		Point end = new Point((int)(dims.getWidth() * .69), (int)(dims.getHeight() * .81));
		
		doSwipe(driver, start, end, 300);
		String volumeText = driver.findElement(By.xpath("(//h1)[2]")).getText();
		assertTrue(volumeText.contains("75%"));
		
	}
	
	@Test
	public void voulme25Test() throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("volume"))));
		Dimension dims = driver.manage().window().getSize();
		
		Point start = new Point((int)(dims.getWidth() * .69), (int)(dims.getHeight() * .81));
		Point end = new Point((int)(dims.getWidth() * .31), (int)(dims.getHeight() * .81));
		
		doSwipe(driver, start, end, 400);
		String volumeText = driver.findElement(By.xpath("(//h1)[2]")).getText();
		assertTrue(volumeText.contains("25%"));
		Thread.sleep(10000);
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
