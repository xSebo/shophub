from selenium.webdriver.common.by import By
import time
from selenium.common.exceptions import NoSuchElementException

BASE_URL = "http://localhost:5000"

def goToLoginPage(driver):
	driver.get(BASE_URL+"/login")

def goToRegisterPage(driver):
	driver.get(BASE_URL + "/signUp")

def loginDontSelectCategorys(driver):
	goToLoginPage(driver)
	inputElement = driver.find_element(By.ID,"loginEmail")
	inputElement.send_keys('randeepccovery@email.com')

	passElement = driver.find_element(By.ID,"loginPassword")
	passElement.send_keys("password123")

	btn = driver.find_element(By.XPATH,'//*[@id="loginForm"]/button')
	btn.click()

	time.sleep(1)

def login(driver):
	loginDontSelectCategorys(driver)
	try:
		driver.find_element(By.XPATH, "/html/body/div[3]")
		driver.find_element(By.XPATH, "/html/body/div[3]/div[2]/section/button[1]").click()
		driver.find_element(By.XPATH, "/html/body/div[3]/div[2]/section/button[2]").click()
		driver.find_element(By.XPATH, "/html/body/div[3]/div[2]/section/button[3]").click()
		driver.find_element(By.XPATH, "/html/body/div[3]/div[2]/footer/button").click()

		time.sleep(0.5)
	except NoSuchElementException:
		pass

