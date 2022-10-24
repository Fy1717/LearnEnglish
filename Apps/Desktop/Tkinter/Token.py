class Token:
    def __init__(self, tokenStr, tokenExpTime):
        self.tokenStr=tokenStr
        self.tokenExpTime=tokenExpTime

    def getTokenStr(self):
        return self.tokenStr

    def setTokenStr(self, tokenStr):
        self.tokenStr = tokenStr

    def getTokenExpTime(self):
        return self.tokenExpTime

    def setTokenExpTime(self, tokenExpTime):
        self.TokenExpTime = tokenExpTime