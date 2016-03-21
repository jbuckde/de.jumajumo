jQuery.sap.declare("jumajumo.ha.WebSocket");

jumajumo.ha.WebSocket =
{
	currentConnection : null,
	connection : function()
	{
		var that = this;

		if (null == this.currentConnection)
		{
			this.currentConnection = this.createNewConnection();

			this.currentConnection.onopen = function()
			{
				var json = '{"command" : "DEFINE_DEVICE","parameters" : {"deviceId" : "9a8bed96-230e-4b37-b030-4e3d1795996f"}}';
				that.currentConnection.send(json);
			};

			this.currentConnection.onmessage = function(e)
			{
			};

			this.currentConnection.onclose = function()
			{
				that.currentConnection = null;
			};
		}

		return this.currentConnection;
	},

	createNewConnection : function()
	{
		var conn = new WebSocket('ws://' + window.location.host
				+ '/HomeControlServer/dispatcher/websocketapi');

		return conn;
	}
};