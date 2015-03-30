jQuery.sap.declare("jumajumo.core.controls.share.GooglePlus");

var jumajumo = (function(module)
{
	"use strict";

	sap.ui.core.Control
			.extend(
					"jumajumo.core.controls.share.GooglePlus",
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

							},

							aggregations :
							{},

							events :
							{}

						},
						renderer : function(oRm, oPlus)
						{
							// do not render invisible cards
							if (!oPlus.getVisible())
							{
								return;
							}

							oRm
									.write("<a href='https://plus.google.com/share?url="
											+ oPlus.getUrl() + "' ");
							oRm
									.write("onclick=\"javascript:window.open(this.href,'', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;\"");
							oRm.writeControlData(oPlus);
							oRm.write(">");

							oRm
									.write("<img src='https://www.gstatic.com/images/icons/gplus-32.png' alt='Share on Google+'/></a>");
						}

					});

	return module;
}(jumajumo ||
{}));