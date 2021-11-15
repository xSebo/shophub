import re

f = open("companies.txt", "r")
test = f.read().split("\n")
f.close()

newArray = []
for each in test:
    newArray.append(re.sub('[^A-Za-z0-9]+', '', each) + ".co.uk")

print(newArray)