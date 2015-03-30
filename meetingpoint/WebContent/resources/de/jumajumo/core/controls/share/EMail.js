jQuery.sap.declare("jumajumo.core.controls.share.EMail");

var jumajumo = (function(module)
{
	"use strict";

	sap.m.Button.extend("jumajumo.core.controls.share.EMail",
	{
		metadata :
		{
			properties :
			{
				"visible" :
				{
					type : "boolean",
					defaultValue : true
				},

				"url" :
				{
					type : "string",
					defaultValue : ""
				},

				"messageText" :
				{
					type : "string",
					defaultValue : ""
				}
			},

			aggregations :
			{},

			events :
			{}

		},
		renderer : "sap.m.ButtonRenderer",

		init : function()
		{
			this.setIcon("sap-icon://email");
			this.attachPress(this._press, this);
		},

		_press : function(oEvent)
		{
			window.open("mailto:" + "?subject=" + this.getMessageText()
					+ "&body=" + this.getUrl());
		}
	});

	return module;
}(jumajumo ||
{}));