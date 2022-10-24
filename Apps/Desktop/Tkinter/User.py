import http.client
import json
class User:
    def __init__(self, username, password):
        self.username=username
        self.password=password

    def getUsername(self):
        return self.username

    def setUsername(self, userName):
        self.username = userName

    def getPassword(self):
        return self.password

    def setPassword(self, passWord):
        self.password = passWord

    def login(self):
        conn = http.client.HTTPSConnection("api.learnenglish.helloworldeducation.com")
        payload = json.dumps({
            "Mail": self.username,
            "Password": self.password
        })

        headers = {
            'Content-Type': 'application/json'
        }

        conn.request("POST", "/api/user/login", payload, headers)
        response = conn.getresponse()

        responseCode = response.status

        #print("STATUS CODE : ", response.status)

        result = response.read().decode("utf-8")

        if responseCode == 200:
            return {"success": True, "data" : result}
        else:
            return {"success": False}  

    
