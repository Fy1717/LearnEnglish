# dosyayı oku
# her satırdaki kellimenin sol tarafını key e, sağ tarafını da value ye ata
# içinden random sor
# eşleşiyosa doğru, eşleşmiyosa yanlış yazdır
import re

print("hilekar" == "hilekar")
myfile = open("Words.txt", "r", encoding="utf-8")
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

    print("YOUR RESULT : ", userAnswer, "\n\nREAL RESULT : ", answer, " --> ", match, "\n")

    count += 1
    
