/*!
 * SAP UI development toolkit for HTML5 (SAPUI5/OpenUI5)
 * (c) Copyright 2009-2015 SAP SE or an SAP affiliate company.
 * Licensed under the Apache License, Version 2.0 - see LICENSE.txt.
 */
sap.ui.define(['jquery.sap.global','./DatePicker','./TextFieldRenderer'],function(q,D,T){"use strict";var a=sap.ui.core.Renderer.extend(T);a.renderOuterAttributes=function(r,c){r.addClass("sapUiTfCombo");this.renderDatePickerARIAInfo(r,c);};a.renderOuterContentBefore=function(r,c){r.write("<div");r.writeAttribute('id',c.getId()+'-icon');r.writeAttribute('tabindex','-1');r.addClass("sapUiTfDateIcon");r.writeClasses();r.write("></div>");var b=sap.ui.getCore().getLibraryResourceBundle("sap.ui.commons");var t=b.getText("DATEPICKER_DATE_TYPE");var s=sap.ui.core.ValueStateSupport.enrichTooltip(c,c.getTooltip_AsString());if(s){t=t+". "+s;}r.write('<SPAN id="'+c.getId()+'-Descr" style="visibility: hidden; display: none;">');r.writeEscaped(t);r.write('</SPAN>');};a.renderInnerAttributes=function(r,d){if(d._bMobile){r.writeAttribute('type','date');r.addStyle('position','absolute');}};a.renderDatePickerARIAInfo=function(r,c){};a.renderARIAInfo=function(r,d){var p={role:d.getAccessibleRole().toLowerCase(),multiline:false,autocomplete:"none",haspopup:true,describedby:{value:d.getId()+"-Descr",append:true}};if(d.getValueState()==sap.ui.core.ValueState.Error){p["invalid"]=true;}r.writeAccessibilityState(d,p);};a.convertPlaceholder=function(d){var p=d.getPlaceholder();if(p.length==8&&!isNaN(p)){var o=d._oFormatYyyymmdd.parse(p);if(o){p=d._formatValue(o);}}return p;};return a;},true);
