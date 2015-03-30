jQuery.sap.declare("jumajumo.core.controls.share.UrlForCopy");

var jumajumo = (function(module)
{
	"use strict";

	sap.m.Button.extend("jumajumo.core.controls.share.UrlForCopy",
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
				}
			},

			aggregations :
			{},

			events :
			{}

		},
		renderer : function(oRm, oUFC)
		{
			// do not render invisible cards
			if (!oUFC.getVisible())
			{
				return;
			}

			oRm.renderControl(oUFC.oText);
		},

		init : function()
		{
			var that = this;
			jQuery.sap.require("sap.m.Input");
			this.oText = new sap.m.Input(
			{
				editable : false
			});
		},

		exit : function()
		{
			this.oText.destroy();
		},

		setUrl : function(sUrl)
		{
			this.oText.setValue(sUrl);
		}
	});

	return module;
}(jumajumo ||
{}));