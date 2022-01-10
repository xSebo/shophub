from selenium.webdriver.chrome.options import Options
from suites.loginRegister import LoginRegisterSuite
from suites.categorySelection import CategorySelection
from suites.dashboard import Dashboard
from suites.stamps import Stamps
from suites.nav import Nav
from modules.main import *
import os
os.system('color')

options = Options()
#options.add_argument('--headless')
options.add_argument('--disable-gpu')
options.add_argument('log-level=3')
options.add_experimental_option('excludeSwitches', ['enable-logging'])

suites = {
    "loginRegister":LoginRegisterSuite(options),
    "categorySelection":CategorySelection(options),
    "nav":Nav(options),
    "dashboard":Dashboard(options),
    "stamps":Stamps(options)
}

def runAllSuites():
    for suite in suites.values():
        info("Starting Test Suite: " + str(suite))
        info("-"*30)
        for test in suite.tests:
            test()
        info("Finished: " + str(suite.passed) + "/" + str(len(suite.tests)) + " Passed")
        print("\n"*2)

def runSuite(name):
    if(name in suites.keys()):
        suite = suites[name]
        info("Starting Test Suite: " + str(suite))
        info("-"*30)
        for test in suite.tests:
            test()
        info("Finished: " + str(suite.passed) + "/" + str(len(suite.tests)) + " Passed")
        print("\n"*2)
    else:
        error("Suite does not exist")

#runAllSuites()
runSuite("stamps")
