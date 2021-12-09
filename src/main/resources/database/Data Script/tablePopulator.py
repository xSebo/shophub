import re
import random
import hashlib

global current_user_id
global current_stamp_id
current_stamp_id = 1
current_user_id = 1

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

def createStampBoard():
    query = '10,"#ff0000","stamp.png"'
    return createInsert(query, "Stamp_Boards", "Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon")

def createRewards():
    global current_stamp_id
    rewardsArray = ['"10% off"','"5% off"','"2 for 1"','"£5 off"']
    query = rewardsArray[random.randint(0,len(rewardsArray)-1)] + ',' + str(random.randint(3,10)) + ',' + str(current_stamp_id+1)
    return createInsert(query, "Rewards", "Reward_Name, Reward_Stamp_Location, Stamp_Board_Id")

def linkTags(shopId):
    finalArray = []
    randomNo = random.randint(1,19)
    tagIdArray = []

    for i in range(1,20):
        tagIdArray.append(i)

    random.shuffle(tagIdArray)

    for i in range(1, randomNo):
        finalArray.append(createInsert((str(shopId) + ',' + str(tagIdArray[i])),"Shop_Tag_Links","Shop_Id, Tag_Id"))

    return finalArray

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

    stdPassword = "$2a$10$YnDtWkRyd3WfYb5CDHBNx.yfuWPW7dOg86NteaEAyaEmaRywfwueK"

    profilePic = "testImage.png"

    insertArray = []
    # Generate email from name, randomise 2fa status and append insert statement to array each iteration
    for i in range(0, amount):
        twoFAMethod = random.randint(1, 2)
        email = newForenames[i] + newSurnames[i] + "@email.com"
        stringInsert = '"' + newForenames[i].lower() + '","' + newSurnames[i].lower() + '","' + email.lower() + '","' + stdPassword + '","' + profilePic + '",' + str(twoFAMethod)
        insertArray.append(createInsert(stringInsert, "Users", "User_First_Name, User_Last_Name, User_Email, User_Password, User_Profile_Picture, Two_Factor_Method_Id"))

    tempAmount = current_user_id
    while current_user_id < (tempAmount+amount):
        # print(current_user_id)
        # print(current_user_id+amount)
        stringInsert = str(current_user_id) + ',' + '1' + ',' + str(userType)
        insertArray.append(createInsert(stringInsert, "User_Permissions", "User_ID, Shop_ID, Admin_Type_Id"))
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

        stringInsert = '"' + companyNames[i] + '","' + "" + '","' + websiteArray[i].lower() + '","' + str(earnings) + '","' + countries[countryi].lower() + '","' + "shopPic.png" + '","' +"shopBanner.png" + '",' + str(random.randint(0, 1)) + ',' + str(i+2) + ',' + str(random.randint(2,7))
        insertArray.append(createInsert(stringInsert, "Shops", "Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id"))

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
    global current_stamp_id
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

    amount = int(input("How many shops would you like to generate?: "))

    # Stamp Boards
    for i in range(0,amount):
        f.write(createStampBoard())
        f.write("\n")
        f.write(createRewards())
        f.write("\n")
        current_stamp_id+=1

    # Shops
    companies = companyPopulator(amount)
    for each in companies:
        f.write(each)
        f.write("\n")

    # Tags
    tagLinkArray = []
    for i in range(1, amount+1):
        tagLinkArray = linkTags(i)
        for i in range(0,len(tagLinkArray)):
            f.write(tagLinkArray[i])
            f.write("\n")

    f.close()


createSQLscript()
