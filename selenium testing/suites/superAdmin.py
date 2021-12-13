from modules.navigation import *
from modules.main import *
from modules.navigation import *
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.common.exceptions import NoSuchElementException
import time

class SuperAdmin:
	def __init__(self, options):
		self.options = options
		self.passed = 0
		self.tests = [
			self.swapAccount
		]

	def __str__(self):
		return "Super Admin"

	def swapAccount(self):
		driver = startTest(self.options)
		login(driver)
		try:
			driver.get(BASE_URL + "/settings")
			superAdminTab = driver.find_element(By.XPATH, '/html/body/div/div[1]/div[2]')
			superAdminTab.click()
			time.sleep(1)
			swapAccountBtn = driver.find_element(By.XPATH, '/html/body/div/main/section[2]/article/div[2]/span[1]/button')
			swapAccountBtn.click()
			time.sleep(1)
			name = driver.find_element(By.XPATH, '/html/body/nav/div[2]/div[2]/div/a')
			time.sleep(1)
			assert name.text == 'Hi kerra!'
			success("Successfully swapped account")
			self.passed+=1
		except NoSuchElementException:
			error("Did not add Successfully swap account")
			input()
