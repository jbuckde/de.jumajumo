jQuery.sap.declare("jumajumo.ha.WebSocket");

jumajumo.ha.WebSocket =
{
	currentConnection : null,

	createNewConnection : function()
	{
		var that = this;

		var conn = new WebSocket('ws://' + window.location.host
				+ '/HomeControlServer/dispatcher/websocketapi');

		conn.onopen = function()
		{
			var json = '{"command" : "DEFINE_DEVICE","parameters" : {"deviceId" : "9a8bed96-230e-4b37-b030-4e3d1795996f"}}';
			conn.send(json);
		};

		conn.onmessage = function(e)
		{
		};

		conn.onclose = function()
		{
			that.currentConnection = null;
		};

		return conn;
	},

	send : function(json)
	{
		var that = this;
		if (null == this.currentConnection)
		{
			this.currentConnection = this.createNewConnection();

			jQuery.sap.delayedCall(2, this, function()
			{
				that.currentConnection.send(json);
			});
		} else
		{
			this.currentConnection.send(json);
		}
	}
};