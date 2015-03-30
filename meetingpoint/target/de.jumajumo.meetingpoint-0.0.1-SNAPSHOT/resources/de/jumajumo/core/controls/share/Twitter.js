jQuery.sap.declare("jumajumo.core.controls.share.Twitter");

var jumajumo = (function(module)
{
	"use strict";

	sap.ui.core.Control
			.extend(
					"jumajumo.core.controls.share.Twitter",
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
						renderer : function(oRm, oTwitter)
						{
							// do not render invisible cards
							if (!oTwitter.getVisible())
							{
								return;
							}

							oRm
									.write("<a href='http://twitter.com/share' class='twitter-share-button' ");

							oRm.write("data-url='" + oTwitter.getUrl() + "' ");
							oRm.write("data-text='" + oTwitter.getMessageText()
									+ "' ");
							// oRm.write("data-via='richerd'");
							oRm.write("data-count='horizontal' ");

							oRm.writeControlData(oTwitter);
							oRm.write(">");
							oRm.write("tweet");
							oRm.write("</a>");

							oRm
									.write("<script type='text/javascript' src='http://platform.twitter.com/widgets.js'></script>");

						}

					});

	return module;
}(jumajumo ||
{}));