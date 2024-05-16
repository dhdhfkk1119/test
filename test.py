from flask import Flask

app=Flask(__name__)

@app.route('/')
def hello():
 return 'Hello Jason World!'

if __name__=='__main__':
 app.run(host='123.124.193.175', port=5000, debug=True)
