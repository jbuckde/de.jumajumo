/*!
 * SAP UI development toolkit for HTML5 (SAPUI5/OpenUI5)
 * (c) Copyright 2009-2015 SAP SE or an SAP affiliate company.
 * Licensed under the Apache License, Version 2.0 - see LICENSE.txt.
 */
sap.ui.define(['jquery.sap.global'],function(q){"use strict";var R=function(){};R.render=function(r,o){var a=r;var n=o.getMaxValue();a.write("<div");a.writeControlData(o);a.addClass("sapUiRating");if(o.getEditable()){a.addClass("sapUiRatingEdit");}a.writeClasses();a.writeAttribute("tabindex","0");if(o.getTooltip()&&o.getTooltip_AsString()){a.writeAttributeEscaped("title",o.getTooltip_AsString());}else if(!o.getEditable()){a.writeAttribute("title",o._getDisplayValue());}a.writeAccessibilityState(o,{"role":"slider","orientation":"horizontal","valuemin":1,"valuemax":n,"disabled":!o.getEditable(),"live":"assertive"});a.write(">");for(var i=0;i<n;i++){R.renderItem(a,o,i,o._getDisplayValue());}a.write("</div>");};R.renderItem=function(r,o,i,v){var a=i+1;r.write("<div");r.writeAttribute("id",o.getId()+"-itm-"+a);r.writeAttribute("itemvalue",a);r.writeAttribute("class","sapUiRatingItm");r.writeAttribute("style","line-height:0px;");if(!o.getTooltip()&&o.getEditable()){r.writeAttributeEscaped("title",o._getText("RATING_TOOLTIP",[a,o.getMaxValue()]));}r.write(">");r.write("<img");r.writeAttribute("class","sapUiRatingItmImg");var I=R.getThemeSymbol("selected",o);r.writeAttributeEscaped("src",I);r.write("/>");r.write("<div");r.writeAttribute("class","sapUiRatingItmOvrflw");var b=o.getVisualMode();if(b=="Full"){v=Math.round(v);}var s;if(v>=a){s="width:0%;";}else if(v<i){s="width:100%;";}else{var d=v-i;if(b=="Half"){var w=50;if(d<0.25){w=100;}if(d>=0.75){w=0;}s="width:"+w+"%;";}else{s="width:"+(100-Math.round(d*100))+"%;";}}r.writeAttribute("style",s);r.write(">");r.write("<img");r.writeAttribute("class","sapUiRatingItmOvrflwImg");I=R.getThemeSymbol("unselected",o);r.writeAttributeEscaped("src",I);r.write("/>");r.write("</div>");r.write("</div>");};R.hoverRatingSymbol=function(c,r,a){var s=q.sap.byId(r.getId()+"-itm-"+c);s.addClass("sapUiRatingItmHov");var S=s.children("img");var i=R.getThemeSymbol(a?"unselected":"hover",r);S.attr("src",i);};R.unhoverRatingSymbol=function(c,r){var s=q.sap.byId(r.getId()+"-itm-"+c);s.removeClass("sapUiRatingItmHov");var S=s.children("img");var i=R.getThemeSymbol("selected",r);S.attr("src",i);};R.getThemeSymbol=function(t,r){var i,p;if(t=="selected"){i=r.getIconSelected();p="sap.ui.commons.RatingIndicator:sapUiRatingSymbolSelected";}else if(t=="unselected"){i=r.getIconUnselected();p="sap.ui.commons.RatingIndicator:sapUiRatingSymbolUnselected";}else{i=r.getIconHovered();p="sap.ui.commons.RatingIndicator:sapUiRatingSymbolHovered";}if(!i){var T="themes/"+sap.ui.getCore().getConfiguration().getTheme()+"/"+sap.ui.core.theming.Parameters.get(p);i=sap.ui.resource("sap.ui.commons",T);}return i;};return R;},true);
