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

def test_verify_heading(browser):
        browser.get("https://alchemy.hguy.co/jobs")

        # Print the title of the page
        
        actual_heading = browser.find_element(By.CLASS_NAME,"entry-title");
        actual_heading_text = actual_heading.text

        
        # Verify the title 
        assert actual_heading_text == 'Welcome to Alchemy Jobs', f"Expected title to be 'Welcome to Alchemy Jobs', but got '{actual_heading_text}'"
        print("The Actual Heading is:", actual_heading_text)
if __name__ == '__main__':
    pytest.main()
