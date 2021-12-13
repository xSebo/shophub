from modules.navigation import *
from modules.main import *
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.common.exceptions import NoSuchElementException
import time

class Dashboard:
	def __init__(self, options):
		self.options = options
		self.passed = 0
		self.tests = [
			self.testDashboardTitle,
			self.testDashboardNoStarredText,
			self.testSearchByTermCorrectAmountOfResults,
			self.testSearchByTermIgnoresCase,
			self.testCanAddTagToSearch,
			self.testSearchByTagReturnsCorrectAmount,
			self.testWhenStarPressedBusinessIsMovedToTop
		]

	def __str__(self):
		return "DashboardSuite"

	def testDashboardTitle(self):
		driver = startTest(self.options)
		login(driver)
		try:
			assert "ShopHub" in driver.title
			success("Title valid on Dashboard Page")
			self.passed+=1
		except AssertionError as e:
			error("Title invalid on Dashboard Page")
			#input()

	def testDashboardNoStarredText(self):
		driver = startTest(self.options)
		login(driver)
		try:
			text = driver.find_element(By.XPATH, "/html/body/div[1]/div/h1[2]")
			assert text.text == "Looks like you don't have any stamps yet, favourite a shop or make a purchase to start collecting!"
			success("No Stamp text correctly appearing")
			self.passed+=1
		except NoSuchElementException:
			error("Text not appearing on page")
			input()
		except AssertionError:
			error("Text doesn't contain correct contents")
			input()

	def testSearchByTermCorrectAmountOfResults(self):
		driver = startTest(self.options)
		login(driver)

		searchBar = driver.find_element(By.ID, "main-search")
		searchBar.send_keys("hudso")

		time.sleep(0.5)

		results = driver.find_elements(By.CLASS_NAME, "business_container")

		try:
			assert len(results) == 1
			success("Number of results is correct")
			self.passed += 1
		except AssertionError:
			error("Number of results is not correct, should be 1")
			input()


	def testSearchByTermIgnoresCase(self):
		driver = startTest(self.options)
		login(driver)

		searchBar = driver.find_element(By.ID, "main-search")
		searchBar.send_keys("hudso")

		time.sleep(0.5)

		result = driver.find_element(By.XPATH, '//*[@id="business_card_container"]/div/div[3]/h1')

		try:
			assert result.text == "Hudson Group"
			success("Search gives correct value ignoring case")
			self.passed += 1
		except AssertionError:
			error("Search does not ignore case")
			input()

	def testCanAddTagToSearch(self):
		driver = startTest(self.options)
		login(driver)

		searchBar = driver.find_element(By.ID, "main-search")
		searchBar.send_keys("fashion")
		searchBar.send_keys(Keys.ENTER)

		time.sleep(0.5)

		results = driver.find_elements(By.CLASS_NAME, "tags")

		try:
			assert len(results) == 1
			success("Tags can be added")
			self.passed += 1
		except AssertionError:
			error("Tag was not added correctly")
			input()

	def testSearchByTagReturnsCorrectAmount(self):
		driver = startTest(self.options)
		login(driver)

		searchBar = driver.find_element(By.ID, "main-search")
		searchBar.send_keys("fashion")
		searchBar.send_keys(Keys.ENTER)

		time.sleep(0.5)

		results = driver.find_elements(By.CLASS_NAME, "business_container")

		try:
			assert len(results) == 5
			success("Tags are searchable")
			self.passed += 1
		except AssertionError:
			error("Tag searching does not return correct amount")
			input()

	#Favourite businesses
	def testWhenStarPressedBusinessIsMovedToTop(self):
		driver = startTest(self.options)
		login(driver)

		star = driver.find_element(By.XPATH, '//*[@id="business_card_container"]/div[1]/div[2]')
		driver.execute_script("arguments[0].click();", star)

		driver.refresh()

		time.sleep(1)

		results = driver.find_elements(By.CLASS_NAME, "reward_card")

		try:
			assert len(results) == 1
			success("Pressing the star moves the icon to favourited")
			self.passed += 1
		except AssertionError:
			error("Items do not move when favourited")
			input()		