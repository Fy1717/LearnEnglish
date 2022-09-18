import re
from tokenize import Token
from User import User
from Token import Token
import http.client
import json

token = Token("", "")
def login():
    loggedIn = False

    while loggedIn == False:
        username = str(input("Username : "))
        password = str(input("Password : "))

        user = User(username, password)

        loginResult = user.login()

        if loginResult["success"]:
            token.setTokenStr(loginResult["data"])
            token.setTokenExpTime("X")
            loggedIn = True
        else:
            print("PLEASE CHECK YOUR USERNAME AND PASSWORD")          

def getWordListFromApi(tokenStr):
    conn = http.client.HTTPSConnection("api.learnenglish.helloworldeducation.com", port=443, timeout=60)
    headers = {"Authorization": "Bearer " + tokenStr}
    conn.request("GET", "/api/word", "", headers)
    response = conn.getresponse()
    responseCode = response.status

    if responseCode == 200:
        result = json.load(response)

        return {"success": True, "data" : result}
    else:
        return {"success": False}  


def askQuestions(samples):
    #print(samples)

    trueCount = 0
    falseCount = 0
    count = 0
    while count != len(samples):
        dictOfQuestion = samples[count]
        question = dictOfQuestion["english"]
        answer = dictOfQuestion["turkish"].lower().rstrip().replace(" ", "")
        
        print("\nQUESTION : ", question)
        #print("ANSWER : ", answer)

        userAnswer = str(input("YOUR ANSWER : ").lower().rstrip().replace(" ", ""))

        match = "It's False"
        if answer == userAnswer:
            match = "It's True"
            trueCount += 1
        else:
            falseCount += 1

        print("\n\nREAL RESULT : ", answer, " --> ", match, "\n")

        count += 1 

    print("\n\nCOUNT OF TRUE ANSWERS : ", trueCount)
    print("COUNT OF FALSE ANSWERS : ", falseCount, "\n")


while True:
    if token.getTokenStr() == "":
        login() 

    allSamples = getWordListFromApi(token.getTokenStr())["data"]
    askQuestions(allSamples)

    startAndStopControl = input("Yeniden denemek için direkt 'Enter'\nÇıkış yapmak için 'q' ya ardından 'Enter' tuşuna basınız : ")
    if "q" == startAndStopControl:
        exit()
    else:
        allSamples = getWordListFromApi(token.getTokenStr())["data"]
        askQuestions(allSamples)
