from selenium import webdriver 
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import pytest 


@pytest.fixture 
def browser():
    # Initialize the Chrome WebDriver
    driver = webdriver.Chrome()
    yield driver
    # Quit the WebDriver
    driver.quit()

def test_search_job(browser):
        browser.get("https://alchemy.hguy.co/jobs")

        # Print the title of the page
        
        actual_job_link_text = browser.find_element(By.LINK_TEXT,"Jobs")
        actual_job_link_verify = actual_job_link_text.text
        actual_job_link_text.click()
        assert browser.current_url == 'https://alchemy.hguy.co/jobs/jobs/'
        print("The Actual browser title is:", actual_job_link_verify)
        # Search for job
        search_box = browser.find_element(By.CSS_SELECTOR,"input#search_keywords")
        search_box.send_keys('Test QA')
        search_box.send_keys(Keys.RETURN)

        #Wait
        browser.implicitly_wait(5)

        # Click on the first job listing 
        first_job = browser.find_element(By.XPATH, "(//h3)[1]") 
        first_job.click()

        #Wait
        browser.implicitly_wait(5)

        #Apply for job
        apply_job = browser.find_element(By.CSS_SELECTOR,"input.application_button.button")
        apply_job.click()

        get_email_id = browser.find_element(By.CSS_SELECTOR,"a.job_application_email")
        get_email_id_verify = get_email_id.text
        print("The Email Job :" + get_email_id_verify)






if __name__ == '__main__':
    pytest.main()
