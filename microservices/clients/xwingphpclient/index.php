<?php

if(!defined('STDOUT')) define('STDOUT', fopen('php://stdout', 'w'));
fwrite(STDOUT, 'Fighter is running');

set_exception_handler(function ($e) {
    $code = $e->getCode() ? : 400;
    header("Content-Type: application/json", NULL, $code);
    echo json_encode(["error" => $e->getMessage()]);
    exit;
});

// assume JSON, handle requests by verb and path
$verb = $_SERVER['REQUEST_METHOD'];

// declare GET request response and database query response
$getRequestResponse;
$dbQueryResponse;

error_log(print_r($verb, TRUE));
switch ($verb) {
    case 'GET':
    		// sample GET URL
            $url = "http://ip.jsontest.com/";
			$getRequestResponse = file_get_contents($url);
			
			$link = mysqli_connect('myHost', 'myUser', 'myPassword', 'myDatabase');
			// Check connection
			if (mysqli_connect_errno()) {
			  echo "Failed to connect to MySQL: " . mysqli_connect_error();
			} else {
				echo 'Connected successfully';
				$query = 'SELECT * FROM MyTable';
				$result = mysqli_query($link, $query);
				$dbQueryResponse = $result->fetch_assoc();
			}
		
        break;
    default:
        throw new Exception('Method Not Supported', 405);
}

header("Content-Type: application/json");
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Methods:GET, POST, OPTIONS, PUT, PATCH, DELETE');
header('Access-Control-Allow-Headers:X-Requested-With,content-type');
header('Access-Control-Allow-Credentials:true');

echo $getRequestResponse;
echo 'X: ' . $dbQueryResponse['xCoordinate'];
echo 'Y: ' . $dbQueryResponse['yCoordinate'];
fwrite(STDOUT, $getRequestResponse);
fwrite(STDOUT, 'X: ' . $dbQueryResponse['xCoordinate']);
fwrite(STDOUT, 'Y: ' . $dbQueryResponse['yCoordinate']);
fclose(STDOUT);
