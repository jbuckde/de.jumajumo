sap.ui.define([
    "sap/ui/core/mvc/Controller",
    'sap/m/MessageToast',
    'sap/ui/Device'
], function (Controller, MessageToast, Device) {
    "use strict";

    return Controller.extend("de.jumajumo.homecontrol.ui.controller.App", {
        
        /**
         * Convenience method for accessing the router.
         * @public
         * @returns {sap.ui.core.routing.Router} the router for this component
         */
        getRouter : function () {
            return sap.ui.core.UIComponent.getRouterFor(this);
        },

        /**
         * Convenience method for getting the view model by name.
         * @public
         * @param {string} [sName] the model name
         * @returns {sap.ui.model.Model} the model instance
         */
        getModel : function (sName) {
            return this.getView().getModel(sName);
        },

        /**
         * Convenience method for setting the view model.
         * @public
         * @param {sap.ui.model.Model} oModel the model instance
         * @param {string} sName the model name
         * @returns {sap.ui.mvc.View} the view instance
         */
        setModel : function (oModel, sName) {
            return this.getView().setModel(oModel, sName);
        },

        /**
         * Convenience method for accessing the router.
         * @public
         * @param {sap.ui.base.Event} oEvent The item select event
         */
        onItemSelect: function(oEvent) {
            var oItem = oEvent.getParameter('item');
            var sKey = oItem.getKey();
            // if you click on home, settings or statistics button, call the navTo function
            if ((sKey === "home" || sKey === "garage" || sKey === "cameras" || sKey === "lightning" || sKey === "doorpictures")) {
                // if the device is phone, collaps the navigation side of the app to give more space
                if (Device.system.phone) {
                    this.onSideNavButtonPress();
                }
                this.getRouter().navTo(sKey);
            } else {
                MessageToast.show(sKey);
            }
        },
            
        onSideNavButtonPress: function() {
            var oToolPage = this.byId("app");
            var bSideExpanded = oToolPage.getSideExpanded();
            //this._setToggleButtonTooltip(bSideExpanded);
            oToolPage.setSideExpanded(!oToolPage.getSideExpanded());
        },

        switchDoor : function(oEvent)
        {
            var button = oEvent.getSource();

            button.setEnabled(false);
            jQuery
                    .ajax(
                            "http://192.168.1.83:8080/HomeControlServer/dispatcher/trigger/opengaragedoor/activate",
                            {
                                complete : function(oEvent)
                                {
                                    button.setEnabled(true);
                                }
                            });
        },

        switchDoorLight : function(oEvent)
        {
            var button = oEvent.getSource();

            button.setEnabled(false);
            jQuery
                    .ajax(
                            "http://192.168.1.83:8080/HomeControlServer/dispatcher/trigger/doorlightswitch/activate",
                            {
                                complete : function(oEvent)
                                {
                                    button.setEnabled(true);
                                }
                            });

        }
    });

});