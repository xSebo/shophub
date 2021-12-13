from modules.navigation import *
from modules.main import *
from modules.navigation import *
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.common.exceptions import NoSuchElementException
import time

class CategorySelection:
	def __init__(self, options):
		self.options = options
		self.passed = 0
		self.tests = [
			self.categoryCloses,
			self.categoryClosedOnReLogin
		]

	def __str__(self):
		return "CategorySelection"

	def categoryCloses(self):
		driver = startTest(self.options)
		loginDontSelectCategorys(driver)

		driver.find_element(By.XPATH, "/html/body/div[3]/div[2]/section/button[1]").click()
		driver.find_element(By.XPATH, "/html/body/div[3]/div[2]/section/button[2]").click()
		driver.find_element(By.XPATH, "/html/body/div[3]/div[2]/section/button[3]").click()
		driver.find_element(By.XPATH, "/html/body/div[3]/div[2]/footer/button").click()

		time.sleep(0.5)

		try:
			driver.find_element(By.XPATH, "/html/body/div[3]")
			error("Modal remains on submission")
			input()
		except NoSuchElementException:
			success("Modal disappears on submission")
			self.passed+=1

	def categoryClosedOnReLogin(self):
		driver = startTest(self.options)
		loginDontSelectCategorys(driver)

		try:
			driver.find_element(By.XPATH, "/html/body/div[3]")
			error("Modal remains on relogin")
			input()
		except NoSuchElementException:
			success("Modal disappears on relogin")
			self.passed+=1
		
			