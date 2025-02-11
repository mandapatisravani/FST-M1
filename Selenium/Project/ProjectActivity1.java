import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ProjectActivity1 {
	 public static void main(String[] args) {
	      
	        WebDriver driver = new FirefoxDriver();

	      
	        driver.get("http://alchemy.hguy.co/orangehrm");
	        
	       String actualTitle = driver.getTitle();

	        // Verify the title matches "OrangeHRM" exactly
	        if (actualTitle.equals("OrangeHRM")) {
	            System.out.println("Title matches: " + actualTitle);
	        } else {
	            System.out.println("Title does not match. Expected: OrangeHRM, Actual: " + actualTitle);
	        }
	        
	        
	        driver.quit();

}
}
