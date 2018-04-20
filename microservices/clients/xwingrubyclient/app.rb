require 'sinatra'
require 'httpclient'
require 'sequel'

get '/' do
  erb :home
end

get '/rest_request_example' do
  client = HTTPClient.new
  client.get("http://ip.jsontest.com").content
end

get '/read_db_raw_SQL_example' do
  ds = db["SELECT * FROM SampleTable"]
  ds.each {|r| puts "#{r} <br/>" }
end

get '/read_db_Sequel_ORM_example' do
  ds = db[:SampleTable]
  ds.each {|r| puts "#{r} <br/>" }
end

def db
  @db ||= Sequel.connect(
  'mysql2://' + ENV.fetch('MYSQLCS_CONNECT_STRING',
                          '127.0.0.1:3306/sample_db'),
    :user => ENV.fetch('MYSQLCS_USER_NAME','root'),
    :password => ENV.fetch('MYSQLCS_USER_PASSWORD','Welcome_1')
  )
end

# Only one view for this sample code, and in a single file for readability
# (*not* best practice)

__END__
@@home
<!doctype html>
<html>
  <body>
    <a href="/rest_request_example">REST request example</a><br/>
    <a href="/read_db_raw_SQL_example">Read DB using Raw SQL example</a><br/>
    <a href="/read_db_Sequel_ORM_example">Read DB using Sequel ORM example</a>
  </body>
</html>
