package ProjectJob;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Project8Test {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        // Initialize the Firefox driver
        driver = new FirefoxDriver();
        // Initialize the wait object
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open browser
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");
    }

    @Test
    @Parameters({ "username1", "password1", "message" })
    public void loginTestCase(String username1, String password1, @Optional("Login Success!") String message) {
        // Find username and password fields
        WebElement usernameField = driver.findElement(By.id("user_login"));
        WebElement passwordField = driver.findElement(By.id("user_pass"));

        // Enter credentials
        usernameField.sendKeys(username1);
        passwordField.sendKeys(password1);

        // Click the submit button
        driver.findElement(By.xpath("//input[@class='button button-primary button-large']")).click();

       

        // Assert login message
        String loginMessage = driver.findElement(By.xpath("//h1")).getText();
        Assert.assertEquals("Dashboard", loginMessage);
        
    }

    @AfterClass
    public void afterClass() {
        // Close browser
        driver.close();
    }
}