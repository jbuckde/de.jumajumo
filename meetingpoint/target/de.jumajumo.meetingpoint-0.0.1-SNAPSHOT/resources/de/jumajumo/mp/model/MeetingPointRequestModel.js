jQuery.sap.declare("jumajumo.mp.model.MeetingPointRequestModel");

sap.ui.model.json.JSONModel
		.extend(
				"jumajumo.mp.model.MeetingPointRequestModel",
				{

					loadData : function(sUUID)
					{
						var that = this;

						// Data is fetched here
						jQuery.ajax(
						{ // load the data from a relative URL (the Data.json
							// file
							// in the same directory)
							url : "dispatcher/meetings/" + sUUID,
							async : true,
							dataType : "json",
							// contentType: "application/json",
							type : "GET",
							success : function(data)
							{
								that.setData(data);

								// load the distances
								that.loadDistances();
							}
						});

						// member the loaded uuid
						this.uuid = sUUID;
					},

					loadDistances : function()
					{
						// get the locations-address array
						var data = this.getData();

						// push the address to a destAddresses array
						var destAddresses =
						[];
						destAddresses.push(data.address)

						jQuery.sap.require("jumajumo.core.util.Geocode");
						var that = this;
						jumajumo.core.util.Geocode
								.loadDistances(
										destAddresses,
										function(response, status)
										{
											var destElements = response.rows[0].elements;
											var el = destElements[0];
											var data = that.getData();

											data.location =
											{};
											data.location.distance =
											[];
											that.setProperty(
													"/location/distance",
													el.distance);
											data.location.duration =
											[];
											that.setProperty(
													"/location/duration",
													el.duration);
										});
					},

					participate : function()
					{
						var that = this;

						// send participation request
						jQuery.ajax(
						{
							url : "dispatcher/meetings/" + that.uuid
									+ "/participate",
							async : true,
							dataType : "json",
							type : "POST"
						});
					}

				});