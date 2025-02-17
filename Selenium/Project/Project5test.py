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

def test_navigate_to_anotherpage(browser):
        browser.get("https://alchemy.hguy.co/jobs")

        # Print the title of the page
        
        actual_job_link_text = browser.find_element(By.LINK_TEXT,"Jobs")
        actual_job_link_verify = actual_job_link_text.text
        actual_job_link_text.click()
        assert browser.current_url == 'https://alchemy.hguy.co/jobs/jobs/'
        print("The Actual browser title is:", actual_job_link_verify)
if __name__ == '__main__':
    pytest.main()
