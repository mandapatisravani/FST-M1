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

def test_verify_ImageSrc(browser):
        browser.get("https://alchemy.hguy.co/jobs")

        # Print the title of the page
        
        actual_image_src = browser.find_element(By.CSS_SELECTOR,"img.attachment-large.size-large.wp-post-image");
        actual_image_src_verify = actual_image_src.get_attribute('src')

        
        # Verify the title 
        assert actual_image_src_verify == 'https://alchemy.hguy.co/jobs/wp-content/uploads/2019/09/career-corporate-job-776615-e1569782235456-1024x271.jpg', f"Expected title to be 'https://alchemy.hguy.co/jobs/wp-content/uploads/2019/09/career-corporate-job-776615-e1569782235456-1024x271.jpg', but got '{actual_image_src_verify}'"
        print("The Actual image src is:", actual_image_src_verify)
if __name__ == '__main__':
    pytest.main()
