from selenium import webdriver 
from selenium.webdriver.common.by import By

import pytest 
from selenium.webdriver.common.keys import Keys 
import random 
import string
from selenium.webdriver.support.ui import Select
from selenium.webdriver.support.ui import WebDriverWait 
from selenium.webdriver.support import expected_conditions as EC

def generate_random_email(domain="example.com", length=10): 
    letters = string.ascii_lowercase 
    random_string = ''.join(random.choice(letters) for i in range(length)) 
    email = f"{random_string}@{domain}" 
    return email

def generate_random_job_title():
    job_titles = [
        'Software Engineer', 'Data Scientist', 'Product Manager',
        'Marketing Specialist', 'Sales Executive', 'UX Designer',
        'Project Manager', 'Business Analyst', 'DevOps Engineer',
        'HR Manager'
    ]
    return random.choice(job_titles)

@pytest.fixture 
def browser():
    # Initialize the Chrome WebDriver
    driver = webdriver.Firefox()
    yield driver
    # Quit the WebDriver
    driver.quit()

def test_search_job(browser):
        browser.get("https://alchemy.hguy.co/jobs")

        # Print the title of the page
        
        post_job_link_text = browser.find_element(By.LINK_TEXT,"Post a Job")
        post_job_link_verify = post_job_link_text.text
        post_job_link_text.click()
        assert browser.current_url == 'https://alchemy.hguy.co/jobs/post-a-job/'
        print("The Actual browser title is:", post_job_link_verify)
        
        #job listing
        random_email = generate_random_email()
        email_field = browser.find_element(By.ID,"create_account_email")
        email_field.send_keys(random_email)

        randomjob_title = generate_random_job_title()
        job_title = browser.find_element(By.ID,"job_title")

        job_title.send_keys(randomjob_title)
        browser.execute_script("window.scrollBy(0, 1000);")
        #job type
        job_type = browser.find_element(By.CSS_SELECTOR,"select#job_type")
        select = Select(job_type)
        select.select_by_value("2")
        #job description

        browser.implicitly_wait(2)
       

        iframe = browser.find_element(By.CSS_SELECTOR, "iframe#job_description_ifr")
        iframe.click()
       
        
        iframe.send_keys("Testing")
        browser.implicitly_wait(5)

        
        browser.execute_script("window.scrollBy(0, 1000);")

        #URL
        company_url = browser.find_element(By.CSS_SELECTOR,"input#application")
        company_url.click()
        
        company_url.send_keys("https//test.com")

        

        #company name
        company_name = browser.find_element(By.ID,"company_name")
        company_name.click()
        company_name.send_keys("IBM")

        #preview
        preview = browser.find_element(By.NAME,"submit_job")
        preview.click()

        browser.implicitly_wait(5)
        #submit
        submit_job_listing = browser.find_element(By.ID,"job_preview_submit_button")
        submit_job_listing.click()
        
        #jobcreated message
        job_created = browser.find_element(By.CLASS_NAME,"entry-content clear")
        job_created_text = job_created.text()
        print(job_created_text)

        #Jobs
        jobs = browser.find_element(By.LINK_TEXT,"Jobs")
        jobs.click()

        #search
        search = browser.find_element(By.ID,"search_keywords")
        search.click()
        search.send_keys(randomjob_title)
        search.send_Keys(Keys.RETURN)
        browser.implicitly_wait(5)

        job_listings = browser.find_elements(By.CSS_SELECTOR, '.job-listing')
        assert len(job_listings) > 0, "No job listings found" 
        for job in job_listings: 
             title = job.find_element(By.CSS_SELECTOR, '.position')  
             print(title.text)
        first_job_title = job_listings[0].find_element(By.CSS_SELECTOR, '.position').text 
        assert randomjob_title in first_job_title, "First job listing title does not match"
                              


        





if __name__ == '__main__':
    pytest.main()
