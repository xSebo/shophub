import re
import random
import hashlib

global current_user_id
current_user_id = 2

# Where pos = position on line, total = total elements in line, fileName is the file name and concat is to remove some
# random text from surnames.csv
#
# Returns a python list of the associated csv file element, formatted
def nameReader(pos, total, fileName, concat=False):
    # Reads and splits whole file
    count = 0
    file = open(fileName, "r")
    allData = file.read()
    allData = allData.split(",")

    newData = []
    count = 0
    for each in allData:
        if count == pos:
            if concat:
                each = each[5:]
                each = each.strip()
            newData.append(each)
        count = count + 1
        if count >= total:
            count = 0
    file.close()
    return newData

# Where data is the values (formatted) to be inserted, and table is the table to apply the changes
#
# Returns a complete insert statement
def createInsert(data, table, columns):
    tempString = 'INSERT INTO ' + table +  " (" + columns + ")" + ' VALUES ' + '(' + data + ');'
    return tempString

# Where amount is how many names to compile, and usertype is the type of user you'd like to generate (1,2,3)
#
# Returns a list of complete insert statements
def namePopulator(amount, userType):
    global current_user_id
    # READ NAMES FROM FILE
    forenameList = nameReader(2, 5, "forenames.csv")
    surnameList = nameReader(0, 10, "surnames.csv", True)

    # SELECT ONLY THE AMOUNT OF NAMES. REQUESTED
    newForenames = []
    newSurnames = []
    for i in range(0, amount):
        newForenames.append(re.sub('[^A-Za-z0-9]+', '', forenameList[random.randint(0, len(forenameList))]))
        newSurnames.append(surnameList[random.randint(0, len(surnameList))])

    # GENERATE EXAMPLE PASSWORD WITH SHA256 + SALT
    salt = "EXAMPLESALT"
    stdPassword = hashlib.sha256(salt.encode() + b'password123').hexdigest()

    profilePic = "testImage.png"

    insertArray = []
    # Generate email from name, randomise 2fa status and append insert statement to array each iteration
    for i in range(0, amount):
        twoFAMethod = random.randint(1, 2)
        email = newForenames[i] + newSurnames[i] + "@email.com"
        stringInsert = '"' + newForenames[i] + '","' + newSurnames[i] + '","' + email + '","' + stdPassword + '","' + profilePic + '",' + str(twoFAMethod)
        insertArray.append(createInsert(stringInsert, "Users", "User_First_Name, User_Last_Name, User_Email, User_Password, User_Profile_Picture, Two_Factor_Method_Id"))

    tempAmount = current_user_id
    while current_user_id < (tempAmount+amount-1):
        # print(current_user_id)
        # print(current_user_id+amount)
        stringInsert = str(current_user_id) + ',' + '1' + ',' + str(userType)
        insertArray.append(createInsert(stringInsert, "UserPermissions", "User_ID, Shop_ID, Admin_Type_Id"))
        current_user_id = current_user_id + 1

    return insertArray

# Where amount is how many companies you'd like to generate
#
# Returns a list of complete insert statements for companies
def companyPopulator(amount):
    # Get full list of companies
    f = open("companies.txt", "r")
    tempCompanyNames = f.read().split("\n")
    f.close()

    # Randomly select amount of companies
    companyNames = []
    for i in range(0, amount):
        companyNames.append(tempCompanyNames[random.randint(0, len(tempCompanyNames)-1)])

    # Get full list of countries, might need amending (lots of potentially unused countries)
    f = open("countries.txt", "r")
    tempCountries = f.read().split("\n")
    f.close()

    countries = []
    for each in tempCountries:
        each = each.replace("|"," ")
        countries.append(re.sub('[^A-Za-z0-9 ]+', '', each))

    # Generate fake website from company name
    websiteArray = []
    for each in companyNames:
        websiteArray.append(re.sub('[^A-Za-z0-9]+', '', each) + ".com")

    # Generate earnings amount, and append complete insert statement to array
    insertArray = []
    for i in range(0, amount):
        earnings = random.randint(0, 40000)

        countryi = random.randint(0, len(countries)-1)

        stringInsert = '"' + companyNames[i] + '","' + "" + '","' + websiteArray[i] + '","' + str(earnings) + '","' + countries[countryi] + '",' + str(random.randint(1, 2))
        insertArray.append(createInsert(stringInsert, "Shops", "Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Active"))

    return insertArray


# Where f is an open file, amount is how many users you'd like to generate and userType is the type of user to generate
# (1,2,3)
#
# Returns void (Writes changes to file)
def userWriter(f, amount, userType):
    users = namePopulator(amount, userType)
    for each in users:
        f.write(each)
        f.write("\n")

# Generates sql and writes to file
def createSQLscript():
    f = open("script.sql", "r+")
    f.truncate()
    f.close()

    f = open("script.sql", "a")

    # Users
    amount = int(input("How many users would you like to generate?: "))
    userWriter(f, amount, 1)
    f.write("\n")
    amount = int(input("How many businesses would you like to generate?: "))
    userWriter(f, amount, 2)
    f.write("\n")
    amount = int(input("How many super admins would you like to generate?: "))
    userWriter(f, amount, 3)
    f.write("\n")

    f.write("\n\n")

    # Shops
    amount = int(input("How many shops would you like to generate?: "))
    companies = companyPopulator(amount)
    for each in companies:
        f.write(each)
        f.write("\n")

    f.close()


createSQLscript()
