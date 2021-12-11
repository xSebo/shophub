from selenium import webdriver
from termcolor import colored

def startTest(options):
	driver = webdriver.Chrome(options=options)
	return driver

def error(text):
	print(colored("FAILED: "+text, "red"))

def success(text):
	print(colored("PASSED: "+text, "green"))

def info(text):
	print(colored(text, "blue"))
