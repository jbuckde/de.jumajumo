jQuery.sap.declare("jumajumo.ha.WebSocket");

jumajumo.ha.WebSocket =
{

	connection: function()
	{
		return connection;
	}
};

var connection = new WebSocket('ws://192.168.1.83:8080/HomeControl/dispatcher/websocketapi');

connection.onopen = function () {

  var json = '{"command" : "DEFINE_DEVICE","parameters" : {"deviceId" : "9a8bed96-230e-4b37-b030-4e3d1795996f"}}';
  
  connection.send(json); 
};

connection.onmessage = function (e) 
{
};

connection.onclose = function()
{
};