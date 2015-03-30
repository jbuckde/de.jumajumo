/*!
 * SAP UI development toolkit for HTML5 (SAPUI5/OpenUI5)
 * (c) Copyright 2009-2014 SAP AG or an SAP affiliate company. 
 * Licensed under the Apache License, Version 2.0 - see LICENSE.txt.
 */
sap.ui.define(['jquery.sap.global','sap/ui/base/ManagedObject'],function(q,M){"use strict";var T=M.extend("sap.ui.core.tmpl.Template",{constructor:function(i,s){M.apply(this,arguments)},metadata:{stereotype:"template","abstract":true,properties:{"content":{type:"string",group:"Data",defaultValue:null}},publicMethods:["declareControl","createControl","placeAt","createMetadata","createRenderer"],library:"sap.ui.core"}});T.prototype.getInterface=function(){return this};T._mSupportedTypes={};T.registerType=function(t,c){T._mSupportedTypes[t]=c};T.unregisterType=function(t){delete T._mSupportedTypes[t]};T.parsePath=function(p){var m=undefined,s=p.indexOf(">");if(s>0){m=p.substr(0,s);p=p.substr(s+1)}return{path:p,model:m}};T.prototype.init=function(s,S){if(this.getMetadata().getName()==="sap.ui.core.tmpl.Template"){throw new Error("The class 'sap.ui.core.tmpl.Template' is abstract and must not be instantiated!")}if(M.bindingParser===sap.ui.base.BindingParser.complexParser){T.prototype.extractBindingInfo=function(v,i,S){M.bindingParser=sap.ui.base.BindingParser.simpleParser;var r=sap.ui.core.Control.prototype.extractBindingInfo.apply(this,arguments);M.bindingParser=sap.ui.base.BindingParser.complexParser;return r}}};T.prototype.declareControl=function(c){if(c){var m=this.createMetadata(),r=this.createRenderer(),t=this;q.sap.require("sap.ui.core.tmpl.TemplateControl");sap.ui.core.tmpl.TemplateControl.extend(c,{metadata:m,init:function(){sap.ui.core.tmpl.TemplateControl.prototype.init.apply(this,arguments);this.setTemplate(t)},renderer:{renderTemplate:r}});return q.sap.getObject(c)}};T.prototype.createControl=function(i,c,v){q.sap.require("sap.ui.core.tmpl.TemplateControl");var C=new sap.ui.core.tmpl.TemplateControl({id:i,template:this,context:c});C.setTemplateRenderer(this.createRenderer(v));return C};T.prototype.placeAt=function(r,c,p,i){if(typeof c==="string"||typeof c==="number"){p=c;c=undefined}var I;if(!(r instanceof sap.ui.core.Control)&&i){var $=typeof r==="string"?q.sap.byId(r):q(r);if($.length>0){I=$.attr("id");r=$.get(0);var C=$.attr("data-context");c=c||C&&q.parseJSON(C);sap.ui.core.RenderManager.markInlineTemplate($)}}var o=this.createControl(I,c);o.placeAt(r,p);return o};T.prototype.createMetadata=function(){q.sap.log.error("The function createMetadata is an abstract function which needs to be implemented by subclasses.")};T.prototype.createRenderer=function(){q.sap.log.error("The function createRenderer is an abstract function which needs to be implemented by subclasses.")};sap.ui.template=function(t){if(!t){var a=[];q.each(T._mSupportedTypes,function(s,j){q("script[type='"+s+"'], [data-type='"+s+"']").each(function(m,E){a.push(sap.ui.template({id:E.id,domref:E,type:s,_class:j}))})});return a}else{if(typeof t==="string"){return sap.ui.template({id:t})}else if(t&&t.tagName&&t.nodeName&&t.ownerDocument&&t.nodeType===1){return sap.ui.template({id:t.id,domref:t})}t=q.extend({type:T.DEFAULT_TEMPLATE},t);var i,s,c,C,r,b=false,l=typeof t.src==="string";if(l){var R=q.sap.sjax({url:t.src,dataType:"text"});if(R.success){i=t.id,s=t.type,c=t.control,C=R.data;var d=/^<!--\sUI5:Template\stype=([a-z\/\-]*)\s(?:controller=([A-Za-z.]*)\s)?-->/,e=C.match(d);if(e){s=e[1];if(e.length==3){b=e[2]}C=C.substr(e[0].length)}}else{throw new Error("The template could not be loaded from "+t.src+"!")}}else{var E=t.domref||q.sap.domById(t.id),$=q(E),I=false;i=t.id||E&&E.id;s=$.attr("type")||t.type,c=$.attr("data-control")||t.control;if(i){var f=sap.ui.getCore().getTemplate(i);if(!f instanceof T){throw new Error("Object for id \""+i+"\" is no sap.ui.core.tmpl.Template!")}else{if(f){return f}}}if($.length===0){throw new Error("DOM element for the Template with the id \""+i+"\" not found!")}C=$.html();var g=/path="(\w|\/|&gt;)+"/i;if(g.test(C)){C=C.replace("&gt;",">")}var h=E.tagName.toLowerCase();if(h!=="script"){I=$.parents("body").length===1}}var j=t._class;if(!j){j=T._mSupportedTypes[s];if(!j){throw new Error("The type \""+s+"\" is not supported.")}}q.sap.require(j);var o=q.sap.getObject(j);var k=new o({id:i,content:C});if(c){k.declareControl(c)}if(b){k._sControllerName=b}if(I){k.placeAt(i,t.context,undefined,true)}return k}};T.DEFAULT_TEMPLATE="text/x-handlebars-template";T.registerType(T.DEFAULT_TEMPLATE,"sap.ui.core.tmpl.HandlebarsTemplate");return T},true);
