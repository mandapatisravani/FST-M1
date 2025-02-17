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

        # Navigate to the job listing creation page
        browser.execute_script("window.scrollBy(0, 1000);")
        browser.implicitly_wait(5)
        create_job_button = browser.find_element(By.XPATH, "(//div[@class='wp-menu-name'])[6]")  # Adjust as needed
        create_job_button.click()

        #Click on Post a job
        post_job = browser.find_element(By.LINK_TEXT,"Add New")
        post_job.click()


        # Fill out the job listing form
        job_title = browser.find_element(By.ID, "post-title-0") 
        

        job_title.send_keys('Software Engineer')  # Replace with the job title
        

        # Submit the job listing
        submit_button = browser.find_element(By.CSS_SELECTOR, "a.components-button.editor-post-preview.is-secondary")  # Adjust the ID as needed
        submit_button.click()
        browser.implicitly_wait(5)

        #click on icon
        wordpress_button = browser.find_element(By.XPATH,"//div[@class='edit-post-header']/a")
        wordpress_button.click()

        #search for job
        jobsearch_button = browser.find_element(By.ID,"post-search-input")
        jobsearch_button.click()
        jobsearch_button.send_keys("Software Engineer")

        #click on search
        submitsearch_button = browser.find_element(By.ID,"search-submit")
        submitsearch_button.click()
        browser.implicitly_wait(5)

        #Verify
        verify_button = browser.find_element(By.XPATH,"(//div[@class='job_position'])[1]")
        verify_buttontext = verify_button.text
        assert 'Software Engineer' in verify_buttontext

        

if __name__ == '__main__':
    pytest.main()
