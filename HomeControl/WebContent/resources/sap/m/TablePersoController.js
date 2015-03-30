/*
 * SAP UI development toolkit for HTML5 (SAPUI5/OpenUI5)
 * (c) Copyright 2009-2014 SAP AG or an SAP affiliate company. 
 * Licensed under the Apache License, Version 2.0 - see LICENSE.txt.
 */
jQuery.sap.declare("sap.m.TablePersoController");jQuery.sap.require("sap.ui.base.ManagedObject");jQuery.sap.require("sap.m.TablePersoDialog");sap.ui.base.ManagedObject.extend("sap.m.TablePersoController",{constructor:function(i,s){sap.ui.base.ManagedObject.apply(this,arguments)},metadata:{properties:{"contentWidth":{type:"sap.ui.core.CSSSize"},"contentHeight":{type:"sap.ui.core.CSSSize",defaultValue:"20rem",since:"1.22"},"componentName":{type:"string",since:"1.20.2"},"hasGrouping":{type:"boolean",defaultValue:false,since:"1.22"},"showSelectAll":{type:"boolean",defaultValue:true,since:"1.22"},"showResetAll":{type:"boolean",defaultValue:true,since:"1.22"},},aggregations:{"_tablePersoDialog":{type:"sap.m.TablePersoDialog",multiple:false,visibility:"hidden"},"persoService":{type:"Object",multiple:false}},associations:{"table":{type:"sap.m.Table",multiple:false},"tables":{type:"sap.m.Table",multiple:true}},events:{personalizationsDone:{}},library:"sap.m"}});
sap.m.TablePersoController.prototype.init=function(){this._schemaProperty="_persoSchemaVersion";this._schemaVersion="1.0";this._oPersonalizations=null;this._mDelegateMap={};this._mTablePersMap={};this._mInitialTableStateMap={};this._triggersPersDoneEvent=true};
sap.m.TablePersoController.prototype.exit=function(){this._callFunctionForAllTables(jQuery.proxy(function(t){t.removeDelegate(this._mDelegateMap[t])},this));delete this._mDelegateMap;delete this._mTablePersMap;delete this._mInitialTableStateMap};
sap.m.TablePersoController.prototype.activate=function(){this._callFunctionForAllTables(this._rememberInitialTableStates);this._callFunctionForAllTables(this._createAndAddDelegateForTable);return this};
sap.m.TablePersoController.prototype.getTablePersoDialog=function(){return this.getAggregation("_tablePersoDialog")};
sap.m.TablePersoController.prototype.applyPersonalizations=function(t){var r=this.getPersoService().getPersData();var a=this;r.done(function(p){if(!!p){a._adjustTable(p,t)}});r.fail(function(){jQuery.sap.log.error("Problem reading persisted personalization data.")})};
sap.m.TablePersoController.prototype._createAndAddDelegateForTable=function(t){if(!this._mDelegateMap[t]){var T={onBeforeRendering:jQuery.proxy(function(){this.applyPersonalizations(t);if(!this.getAggregation("_tablePersoDialog")){this._createTablePersoDialog(t)}},this)};t.addDelegate(T);this._mDelegateMap[t]=T}};
sap.m.TablePersoController.prototype._createTablePersoDialog=function(t){var T=new sap.m.TablePersoDialog({persoDialogFor:t,persoMap:this._getPersoColumnMap(t),columnInfoCallback:this._tableColumnInfo,initialColumnState:this._mInitialTableStateMap[t],persoService:this.getPersoService(),contentWidth:this.getContentWidth(),contentHeight:this.getContentHeight(),hasGrouping:this.getHasGrouping(),showSelectAll:this.getShowSelectAll(),showResetAll:this.getShowResetAll(),});this.setAggregation("_tablePersoDialog",T);T.attachConfirm(jQuery.proxy(function(){this._oPersonalizations=T.retrievePersonalizations();this._callFunctionForAllTables(this._personalizeTable);this.savePersonalizations();this.firePersonalizationsDone()},this))};
sap.m.TablePersoController.prototype._adjustTable=function(d,t){if(d&&d.hasOwnProperty(this._schemaProperty)&&d[this._schemaProperty]===this._schemaVersion){this._oPersonalizations=d;if(!!t){this._personalizeTable(t)}else{this._callFunctionForAllTables(this._personalizeTable)}}};
sap.m.TablePersoController.prototype._personalizeTable=function(t){var p=this._getPersoColumnMap(t);if(!!p&&!!this._oPersonalizations){var d=false;for(var c=0,a=this._oPersonalizations.aColumns.length;c<a;c++){var n=this._oPersonalizations.aColumns[c];var T=p[n.id];if(!T){T=sap.ui.getCore().byId(n.id);if(!!T){jQuery.sap.log.info("Migrating personalization persistence id of column "+n.id);n.id=p[T];d=true}}if(T){T.setVisible(n.visible);T.setOrder(n.order)}else{jQuery.sap.log.warning("Personalization could not be applied to column "+n.id+" - not found!")}}if(d){this.savePersonalizations()}t.invalidate()}};
sap.m.TablePersoController.prototype.savePersonalizations=function(){var b=this._oPersonalizations;b[this._schemaProperty]=this._schemaVersion;var w=this.getPersoService().setPersData(b);w.done(function(){});w.fail(function(){jQuery.sap.log.error("Problem persisting personalization data.")})};
sap.m.TablePersoController.prototype.refresh=function(){var r=function(T){this._mTablePersMap={};T.invalidate()};this._callFunctionForAllTables(r);var t=this.getAggregation("_tablePersoDialog");if(!!t){t.setPersoMap(this._getPersoColumnMap(sap.ui.getCore().byId(t.getPersoDialogFor())))}};
sap.m.TablePersoController.prototype.openDialog=function(){var t=this.getAggregation("_tablePersoDialog");if(!!t){jQuery.sap.syncStyleClass("sapUiSizeCompact",t.getPersoDialogFor(),t._oDialog);t.open()}else{jQuery.sap.log.warning("sap.m.TablePersoController: trying to open TablePersoDialog before TablePersoService has been activated.")}};
sap.m.TablePersoController.prototype.setContentWidth=function(w){this.setProperty("contentWidth",w,true);var t=this.getAggregation("_tablePersoDialog");if(!!t){t.setContentWidth(w)}return this};
sap.m.TablePersoController.prototype.setContentHeight=function(h){this.setProperty("contentHeight",h,true);var t=this.getAggregation("_tablePersoDialog");if(!!t){t.setContentHeight(h)}return this};
sap.m.TablePersoController.prototype.setHasGrouping=function(h){this.setProperty("hasGrouping",h,true);var t=this.getAggregation("_tablePersoDialog");if(!!t){t.setHasGrouping(h)}return this};
sap.m.TablePersoController.prototype.setShowSelectAll=function(s){this.setProperty("showSelectAll",s,true);var t=this.getAggregation("_tablePersoDialog");if(!!t){t.setShowSelectAll(s)}return this};
sap.m.TablePersoController.prototype.setShowResetAll=function(s){this.setProperty("showResetAll",s,true);var t=this.getAggregation("_tablePersoDialog");if(!!t){t.setShowResetAll(s)}return this};
sap.m.TablePersoController.prototype.setComponentName=function(c){this.setProperty("componentName",c,true);return this};
sap.m.TablePersoController.prototype._getMyComponentName=function(c){if(this.getComponentName()){return this.getComponentName()}if(null===c)return"empty_component";var m=c.getMetadata();if("component"===c.getMetadata().getStereotype())return m._sComponentName;return this._getMyComponentName(c.getParent())};
sap.m.TablePersoController.prototype._getFirstTable=function(){var t=sap.ui.getCore().byId(this.getAssociation("table"));var T=this.getAssociation("tables");if(!t&&T&&T.length>0){t=sap.ui.getCore().byId(T[0])}return t};
sap.m.TablePersoController.prototype._callFunctionForAllTables=function(t){var T=sap.ui.getCore().byId(this.getAssociation("table"));if(!!T){t.call(this,T)}var a=this.getAssociation("tables");if(a){for(var i=0,l=this.getAssociation("tables").length;i<l;i++){T=sap.ui.getCore().byId(this.getAssociation("tables")[i]);t.call(this,T)}}};
sap.m.TablePersoController.prototype._isStatic=function(i){var u=sap.ui.getCore().getConfiguration().getUIDPrefix();var r=new RegExp("^"+u);return!r.test(i)};
sap.m.TablePersoController.prototype._getPersoColumnMap=function(t){var r=this._mTablePersMap[t];if(!r){r={};var e=function(i){var l=i.lastIndexOf("-");return i.substring(l+1)};var T=e.call(this,t.getId());if(!this._isStatic(T)){jQuery.sap.log.error("Table "+t.getId()+" must have a static id suffix. Otherwise personalization can not be persisted.");r=null;return null}var n;var c=this._getMyComponentName(t);var a=this;t.getColumns().forEach(function(N){if(!!r){var s=N.getId();var b=e.call(a,s);if(!a._isStatic(b)){jQuery.sap.log.error("Suffix "+b+" of table column "+s+" must be static. Otherwise personalization can not be persisted for its table.");r=null;return null}n=c+"-"+T+"-"+b;r[N]=n;r[n]=N}});this._mTablePersMap[t]=r}return r};
sap.m.TablePersoController.prototype._rememberInitialTableStates=function(t){this._mInitialTableStateMap[t]=this._tableColumnInfo(t,this._getPersoColumnMap(t),this.getPersoService())};
sap.m.TablePersoController.prototype._tableColumnInfo=function(t,p,P){if(!!p){var c=t.getColumns(),C=[];c.forEach(function(o){var s=null;if(P.getCaption){s=P.getCaption(o)}var g=null;if(P.getGroup){g=P.getGroup(o)}if(!s){var a=o.getHeader();if(a.getText&&a.getText()){s=a.getText()}else if(a.getTitle&&a.getTitle()){s=a.getTitle()}if(!s){s=o.getId();jQuery.sap.log.warning("Please 'getCaption' callback implentation in your TablePersoProvider for column "+o+". Table personalization uses column id as fallback value.")}}C.push({text:s,order:o.getOrder(),visible:o.getVisible(),id:p[o],group:g})});C.sort(function(a,b){return a.order-b.order});return C}return null};
