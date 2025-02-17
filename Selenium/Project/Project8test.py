from selenium import webdriver 
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import pytest 
from selenium.webdriver.support.ui import WebDriverWait 
from selenium.webdriver.support import expected_conditions as EC


@pytest.fixture 
def browser():
    # Initialize the Chrome WebDriver
    driver = webdriver.Chrome()
    yield driver
    # Quit the WebDriver
    driver.quit()

def test_search_job(browser):
        browser.get("https://alchemy.hguy.co/jobs/wp-admin")

        # Print the title of the page
        username_field = browser.find_element(By.ID, "user_login")  
        password_field = browser.find_element(By.ID, "user_pass") 
        login_button = browser.find_element(By.ID, "wp-submit")  

        # Enter login credentials
        username_field.send_keys("​root") 
        password_field.send_keys("pa$$w0rd")  

        # Submit the login form
        login_button.click()
        try:
            dashboard = WebDriverWait(browser, 10).until(EC.presence_of_element_located((By.CLASS_NAME, 'wp-menu-name')))
            assert dashboard.is_displayed(), "Login failed: Dashboard not displayed"
        except NoSuchElementException:
            assert False, "Login failed: Dashboard element not found"



        
        






if __name__ == '__main__':
    pytest.main()
