from modules.navigation import *
from modules.main import *
from modules.navigation import *
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.common.exceptions import NoSuchElementException
import time

class Stamps:
	def __init__(self, options):
		self.options = options
		self.passed = 0
		self.tests = [
			self.addStamp,
			self.add2StampsThenClaimReward
		]

	def __str__(self):
		return "stamps"

	def addStamp(self):
		driver = startTest(self.options)
		loginAsBusinessOwner(driver)
		addBtn = driver.find_element(By.XPATH, '/html/body/div[2]/button[1]')
		addBtn.click()
		try:
			time.sleep(1)
			driver.find_element(By.XPATH, '/html/body/div[1]/div[1]/div/div[1]/div[1]/img')
			success("Successfully found and added stamp")
			self.passed+=1
		except NoSuchElementException:
			error("Did not add stamp")
			input()

	def add2StampsThenClaimReward(self):
				driver = startTest(self.options)
				loginAsBusinessOwner(driver)
				for x in range(2):
					addBtn = driver.find_element(By.XPATH, '/html/body/div[2]/button[1]')
					addBtn.click()
					time.sleep(1)
				try:
					claimBtn = driver.find_element(By.XPATH, '/html/body/div[1]/div[1]/div/div[3]/div/button')
					claimBtn.click()
					reward = driver.find_element(By.XPATH, '/html/body/div[3]')
					time.sleep(0.5)
					closeRewardBtn = driver.find_element(By.XPATH, '/html/body/div[3]/div[2]/header/button')
					closeRewardBtn.click()
					time.sleep(1)
					firstSlot = driver.find_element(By.XPATH, '/html/body/div[1]/div[1]/div/div[1]/div[1]/div[1]')
					success("Successfully added stamps and claimed reward")
					self.passed+=1
				except NoSuchElementException:
					error("failed to add stamps or claim reward")
					input()
