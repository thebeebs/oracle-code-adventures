import requests
from flask import Flask
from flask import render_template
from flask_sqlalchemy import SQLAlchemy
from config import Configuration

# create Flask app
app = Flask(__name__)
app.config.from_object(Configuration)
db = SQLAlchemy(app)

# GET request to ip.jsontest.com
@app.route('/')
def home():
    return render_template('home.html')

@app.route('/rest_request_example')
def rest_request_example():
    return requests.get("http://ip.jsontest.com").text

@app.route('/read_db_SQL_example')
def read_db_SQL_example():
    conn = db.get_engine().connect()
    sql = "SELECT * FROM SampleTable"
    results = conn.execute(sql)
    rows = ""
    for row in results:
        rows = rows + ','.join(row) + "<br/>"
    return rows

if __name__ == '__main__':
    app.run(host=app.config['HOST'], port=app.config['PORT'])
