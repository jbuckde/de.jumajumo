// This is the top level view of the application. It creates the SplitApp control and the pages are initialized and appended to splitapp by routing configured in Component.js.
sap.ui.jsview("jumajumo.ha.view.App",
{

	getControllerName : function()
	{
		return "jumajumo.ha.view.App";
	},

	createContent : function(oController)
	{
		// to avoid scrollbars on desktop the root view must be set to block
		// display
		this.setDisplayBlock(true);

		// create app
		this.app = new sap.m.SplitApp("haApp",
		{
			defaultTransitionNameDetail : "fade",

			// The master area needs to be closed when navigation in detail area
			// is done.
			afterDetailNavigate : function()
			{
			},

		});

		return this.app;
	}
});