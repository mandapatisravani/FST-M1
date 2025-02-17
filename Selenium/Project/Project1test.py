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

def test_verify_title(browser):
        browser.get("https://alchemy.hguy.co/jobs")

        # Print the title of the page
        
        Actualtitle = browser.title 
        # Verify the title 
        assert Actualtitle == 'Alchemy Jobs – Job Board Application', f"Expected title to be 'Alchemy Jobs – Job Board Application', but got '{title}'"
        print("The Actual Title is:", Actualtitle)
if __name__ == '__main__':
    pytest.main()
