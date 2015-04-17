/*!
 * SAP UI development toolkit for HTML5 (SAPUI5/OpenUI5)
 * (c) Copyright 2009-2015 SAP SE or an SAP affiliate company.
 * Licensed under the Apache License, Version 2.0 - see LICENSE.txt.
 */
sap.ui.define(['jquery.sap.global','sap/ui/base/ManagedObject'],function(q,M){'use strict';var E=M.extend('sap.ui.core.util.ExportType',{metadata:{properties:{fileExtension:'string',mimeType:'string',charset:'string'}}});E.prototype.init=function(){this._oExport=null};E.prototype._generate=function(e){this._oExport=e;var c=this.generate();this._oExport=null;return c};E.prototype.generate=function(){return''};E.prototype.getColumnCount=function(){if(this._oExport){return this._oExport.getColumns().length}return 0};E.prototype.getRowCount=function(){if(this._oExport&&this._oExport.getBinding("rows")){return this._oExport.getBinding("rows").getLength()}return 0};E.prototype.columnGenerator=function(){var i=0,c=this._oExport.getColumns(),C=c.length;return{next:function(){if(i<C){var I=i;i++;return{value:{index:I,name:c[I].getName()},done:false}}else{return{value:undefined,done:true}}}}};E.prototype.cellGenerator=function(){var i=0,r=this._oExport.getAggregation('_template'),c=r.getCells(),C=c.length;return{next:function(){if(i<C){var I=i;i++;var m={};q.each(c[I].getCustomData(),function(){m[this.getKey()]=this.getValue()});return{value:{index:I,content:c[I].getContent(),customData:m},done:false}}else{return{value:undefined,done:true}}}}};E.prototype.rowGenerator=function(){var t=this,i=0,e=this._oExport,b=e.getBinding("rows"),B=e.getBindingInfo("rows"),c=b.getContexts(0,b.getLength()),C=c.length,r=e.getAggregation('_template');return{next:function(){if(i<C){var I=i;i++;r.setBindingContext(c[I],B.model);return{value:{index:I,cells:t.cellGenerator()},done:false}}else{return{value:undefined,done:true}}}}};return E},true);
