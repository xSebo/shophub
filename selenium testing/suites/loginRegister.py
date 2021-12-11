from modules.navigation import *
from modules.main import *
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
import time

class LoginRegisterSuite:
	def __init__(self, options):
		self.options = options
		self.passed = 0
		self.tests = [
			self.testLoginPageTitle,
			self.testInvalidLogin,
			self.testValidRegister,
			self.testRegisterWithUsedEmail,
			self.testValidLogin
		]

	def __str__(self):
		return "LoginRegisterSuite"

	def testLoginPageTitle(self):
		driver = startTest(self.options)
		goToLoginPage(driver)
		try:
			assert "ShopHub" in driver.title
			success("Title valid on Login Page")
			self.passed+=1
		except AssertionError as e:
			error("Title invalid on Login Page")
			#input()

	def testInvalidLogin(self):
		driver = startTest(self.options)
		goToLoginPage(driver)

		inputElement = driver.find_element(By.ID,"loginEmail")
		inputElement.send_keys('john.watkins2002@gmail.com')

		passElement = driver.find_element(By.ID,"loginPassword")
		passElement.send_keys("asdf123")

		btn = driver.find_element(By.XPATH,'//*[@id="loginForm"]/button')
		btn.click()

		time.sleep(1)

		helpText = driver.find_element(By.ID, "loginHelpText")

		try:
			assert helpText.text.strip() == "Incorrect email or password"
			success("Incorrect login details erroring on Login Page")
			self.passed+=1
		except AssertionError as e:
			error("Incorrect login details not erroring on Login Page")
			#input()

	def testValidRegister(self):
		driver = startTest(self.options)
		goToRegisterPage(driver)

		nameInput = driver.find_element(By.ID,"newUserForename")
		nameInput.send_keys("Tom")

		surnameInput = driver.find_element(By.ID, "newUserLastname")
		surnameInput.send_keys("Fletcher")

		emailInput = driver.find_element(By.ID, "newUserEmail")
		emailInput.send_keys("tomfletcher@test.com")

		passInput = driver.find_element(By.ID, "newUserPassword")
		passInput.send_keys("password123")

		btn = driver.find_element(By.XPATH, '//*[@id="signUpForm"]/button')
		btn.click()

		time.sleep(1)

		try:
			assert driver.current_url == BASE_URL + "/dashboard"
			success("Register with valid details works")
			self.passed+=1
		except AssertionError as e:
			error("Register with valid details fails")
			#input()

	def testRegisterWithUsedEmail(self):
		driver = startTest(self.options)
		goToRegisterPage(driver)

		nameInput = driver.find_element(By.ID,"newUserForename")
		nameInput.send_keys("Tom")

		surnameInput = driver.find_element(By.ID, "newUserLastname")
		surnameInput.send_keys("Fletcher")

		emailInput = driver.find_element(By.ID, "newUserEmail")
		emailInput.send_keys("kobehamsi@email.com")

		passInput = driver.find_element(By.ID, "newUserPassword")
		passInput.send_keys("password123")

		btn = driver.find_element(By.XPATH, '//*[@id="signUpForm"]/button')
		btn.click()

		time.sleep(1)

		helpText = driver.find_element(By.ID, "emailHelpText")

		try:
			assert helpText.text == "Email in use"
			success("Register knows when emails are in use")
			self.passed+=1
		except AssertionError as e:
			error("Register fails to detect emails in use")
			#input()

	def testValidLogin(self):
		driver = startTest(self.options)
		goToLoginPage(driver)

		inputElement = driver.find_element(By.ID,"loginEmail")
		inputElement.send_keys('randeepccovery@email.com')

		passElement = driver.find_element(By.ID,"loginPassword")
		passElement.send_keys("password123")

		btn = driver.find_element(By.XPATH,'//*[@id="loginForm"]/button')
		btn.click()

		time.sleep(1)

		try:
			assert driver.current_url == BASE_URL + "/"
			success("Login in correctly redirects")
			self.passed+=1
		except AssertionError as e:
			error("Login in does not redirect")
			#input()
