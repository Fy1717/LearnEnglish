import ast

class Word:
    def __init__(self, turkish, english, turSentence, engSentence):
        self.turkish = turkish
        self.english = english
        self.turSentence = turSentence
        self.engSentence = engSentence
        self.sample = {
            'eng_word': english,
            'tur_word': turkish,
            'turSentence': turSentence,
            'engSentence': engSentence 
        }

    def getEng(self):
        return self.english

    def setEng(self, engWord):
        self.english = engWord

    def getTur(self):
        return self.turkish 

    def setTur(self, turWord):
        self.turkish = turWord


    def getTurSentence(self):
        return self.turSentence

    def getEngSentence(self):
        return self.engSentence

    def save(self):
        file = open('Words.txt', 'a', encoding="utf-8")
        file.writelines(str(self.sample) + '$')
        file.close()

        return True

def enterAndSaveInConsole():
    print('\n******************************************************\n')

    try:
        word_eng = input('-ENG- Kelimeyi Girin : ')
        word_tur = input('-TUR- Türkçe Karşılığını Girin : ')
        sentence_eng = input('-ENG- İngilizce Cümleyi Girin : ')
        sentence_tur = input('-TUR- Türkçe Cümleyi Girin : ')

        sampleFromUser = Word(word_tur, word_eng, sentence_tur, sentence_eng)

        sampleFromUser.save()

        return sampleFromUser
    except Exception as e:
        print("ERROR : " + str(e))

    print('\n******************************************************\n')

def listAll():
    print('\n******************************************************\n')

    try:
        dosya = open('Words.txt', 'r', encoding="utf-8")
        stringFile = dosya.read()
        stringToList = stringFile.split('$')

        for i in range(0, len(stringToList) - 1):
            word = stringToList[i] or []

            if len(word) > 0:
                dictOfWord = ast.literal_eval(word) 
                wordOfEnglish = dictOfWord['eng_word']
                wordOfTurkish = dictOfWord['tur_word']
                turSentence = dictOfWord['turSentence']
                engSentence = dictOfWord['engSentence']

                print('----------- WORD - ', i+1 , '-----------\n')

                print('English  = ', wordOfEnglish)
                print('Turkish  = ', wordOfTurkish)
                print('English Sentence = ', engSentence)
                print('Turkish Sentence = ', turSentence, '\n\n')
    except Exception as e:
        print("ERROR : " + str(e))

    print('\n******************************************************\n')
