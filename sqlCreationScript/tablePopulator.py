import random
import hashlib


def nameReader(pos, total, fileName, concat=False):
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
            newData.append(each)
        count = count + 1
        if count >= total:
            count = 0
    file.close()
    return newData


def namePopulator(amount, adminType): # Admin type,
    # READ NAMES FROM FILE
    forenameList = nameReader(2, 5, "forenames.csv")
    surnameList = nameReader(0, 10, "surnames.csv", True)

    # SELECT ONLY THE AMOUNT OF NAMES. REQUESTED
    newForenames = []
    newSurnames = []
    twoFAMethod = []
    for i in range(0, amount):
        newForenames.append(forenameList[random.randint(0, len(forenameList))])
        newSurnames.append(surnameList[random.randint(0, len(surnameList))])
        twoFAMethod.append(random.randint(0, 1))

    # GENERATE EXAMPLE PASSWORD WITH SHA256 + SALT
    salt = "EXAMPLESALT"
    stdPassword = hashlib.sha256(salt.encode() + b'password123').hexdigest()

    profilePic = "testImage.png"
