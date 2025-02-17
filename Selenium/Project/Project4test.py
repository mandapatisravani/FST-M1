from selenium import webdriver 
from selenium.webdriver.common.by import By
import pytest 


@pytest.fixture 
def browser():
    # Initialize the Chrome WebDriver
    driver = webdriver.Chrome()
    yield driver
    # Quit the WebDriver
    driver.quit()

def test_verify_secondheading(browser):
        browser.get("https://alchemy.hguy.co/jobs")

        # Print the title of the page
        
        actual_second_heading = browser.find_element(By.XPATH,"//h2")
        actual_second_heading_verify = actual_second_heading.text

        
        # Verify the title 
        assert actual_second_heading_verify == 'Quia quis non', f"Expected title to be 'Quia quis non', but got '{actual_second_heading_verify}'"
        print("The Actual second heading is:", actual_second_heading_verify)
if __name__ == '__main__':
    pytest.main()
