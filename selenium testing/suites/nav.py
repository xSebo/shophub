from modules.navigation import *
from modules.main import *
from modules.navigation import *
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.common.exceptions import NoSuchElementException
import time

class Nav:
	def __init__(self, options):
		self.options = options
		self.passed = 0
		self.tests = [
			self.nameCorrect,
			self.logOut
		]

	def __str__(self):
		return "Nav"

	def nameCorrect(self):
		driver = startTest(self.options)
		login(driver)

		name = driver.find_element(By.XPATH, '//*[@id="ShopHubNav"]/div[2]/div/a')

		try:
			assert name.text == 'Hi randeep!'
			success("Name shows correctly")
			self.passed+=1
		except AssertionError as e:
			error("Name does not show correctly")
			#input()

	def logOut(self):
		driver = startTest(self.options)
		login(driver)

		btn = driver.find_element(By.XPATH, '/html/body/nav/div[2]/div[2]/div/div/a[3]')
		driver.execute_script("arguments[0].click();", btn)

		time.sleep(1)

		try:
			assert driver.current_url == BASE_URL + "/login"
			success("Redirect on logout works correctly")
			self.passed+=1
		except AssertionError as e:
			error("Redirect on logout does not work correctly")
			#input()



