var express = require('express');
var http = require('https');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {

	callback = function(response) {
		var str = ''
		response.on('data', function(chunk) {
			str += chunk;
		});

		response.on('end', function() {
			res.send(str);
		});
	}

	var req = http.request('https://ds-backend-gse00010206.apaas.em2.oraclecloud.com/reactors/SuperlaserTributaryBeamShaft/xwingnodeclient', callback);
	req.end();
	
});

module.exports = router;
