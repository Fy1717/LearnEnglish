import re
from tokenize import Token
from User import User
import http.client
import json


def login():
    loggedIn = False
    token = ""

    while loggedIn == False:
        username = str(input("Username : "))
        password = str(input("Password : "))

        user = User(username, password)

        loginResult = user.login()

        if loginResult["success"]:
            token = loginResult["data"]
            loggedIn = True
        else:
            print("PLEASE CHECK YOUR USERNAME AND PASSWORD")

    return token

userToken = login() 

def getWordListFromApi(token):
    conn = http.client.HTTPSConnection("api.learnenglish.helloworldeducation.com", port=443, timeout=60)
    headers = {"Authorization": "Bearer " + token}
    conn.request("GET", "/api/word", "", headers)
    response = conn.getresponse()
    responseCode = response.status

    if responseCode == 200:
        result = json.load(response)

        #print("BBBBB : ", response, " : ", response.status, " : " , response.reason, " : ", result)

        return {"success": True, "data" : result}
    else:
        return {"success": False}  

allSamples = getWordListFromApi(userToken)["data"]

print(allSamples)



''' myfile = open("Words.txt", "r", encoding="utf-8")
keyAndValues = []
while myfile:
    try:
        line = re.sub(r'^a-z', '', myfile.readline().replace("İ", "i").lower().rstrip().replace(" ", "").split("-")[1])
        keyOfWord = line.split(":")[0]
        valueOfWord = line.split(":")[1]
        
        keyAndValues.append({keyOfWord:valueOfWord})

        if line == "":
            break
        else:
            continue
            #print(keyOfWord + " " + valueOfWord)
    except:
        break

#print(keyAndValues)
myfile.close() 

count = 1
while count != len(keyAndValues):
    dictOfQuestion = keyAndValues[count]
    question = list(dictOfQuestion.keys())[0]
    answer = dictOfQuestion[question]
    
    print("QUESTION : ", question)
    userAnswer = str(input("YOUR ANSWER : ").replace(" ", ""))

    match = "It's False"
    if answer == userAnswer:
        match = "It's True"

    print("\n\nREAL RESULT : ", answer, " --> ", match, "\n")

    count += 1 '''
