/*!
 * SAP UI development toolkit for HTML5 (SAPUI5/OpenUI5)
 * (c) Copyright 2009-2015 SAP SE or an SAP affiliate company.
 * Licensed under the Apache License, Version 2.0 - see LICENSE.txt.
 */
sap.ui.define(['jquery.sap.global','./TextField','./library','sap/ui/core/Popup','jquery.sap.strings'],function(q,T,a,P){"use strict";var C=T.extend("sap.ui.commons.ComboBox",{metadata:{interfaces:["sap.ui.commons.ToolbarItem"],library:"sap.ui.commons",properties:{maxPopupItems:{type:"int",group:"Behavior",defaultValue:10},displaySecondaryValues:{type:"boolean",group:"Misc",defaultValue:false},selectedKey:{type:"string",group:"Data",defaultValue:null},selectedItemId:{type:"string",group:"Data",defaultValue:null}},defaultAggregation:"items",aggregations:{items:{type:"sap.ui.core.ListItem",multiple:true,singularName:"item",bindable:"bindable"},myListBox:{type:"sap.ui.commons.ListBox",multiple:false,visibility:"hidden"}},associations:{listBox:{type:"sap.ui.commons.ListBox",multiple:false}}}});C.prototype.init=function(){T.prototype.init.apply(this,arguments);this._iClosedUpDownIdx=-1;this._sCloseId=null;this.setAccessibleRole(sap.ui.core.AccessibleRole.Combobox);if(sap.ui.Device.browser.mobile){this.mobile=true}};C.prototype.exit=function(){if(this._oListBox){if(this._oListBoxDelegate){this._oListBox.removeDelegate(this._oListBoxDelegate)}if(this.getAggregation("myListBox")){this.destroyAggregation("myListBox",true)}else{this._oListBox.destroy()}this._oListBox=null}else if(this.getListBox()){var l=sap.ui.getCore().byId(this.getListBox());if(l){l.detachEvent("itemsChanged",this._handleItemsChanged,this);l.detachEvent("itemInvalidated",this._handleItemInvalidated,this)}}this._sWantedSelectedKey=undefined;this._sWantedSelectedItemId=undefined;if(this._sHandleItemsChanged){q.sap.clearDelayedCall(this._sHandleItemsChanged);this._sHandleItemsChanged=null}};C.prototype.onclick=function(e){if(this.getEnabled&&this.getEnabled()&&this.getEditable()&&e.target===this.getF4ButtonDomRef()){if(this.oPopup&&this.oPopup.isOpen()){this._close()}else if(!this._F4ForClose){this._open()}this.focus()}this._F4ForClose=false};C.prototype.onmousedown=function(e){var f=this.getF4ButtonDomRef();if(e.target!==f||!this.getEnabled()||!this.getEditable()){if(this.oPopup&&this.oPopup.isOpen()){e.stopPropagation()}return}else if(e.target==f&&q(this.getFocusDomRef()).data("sap.INItem")){e.stopPropagation();this.focus()}if(this.oPopup&&this.oPopup.isOpen()){this._F4ForClose=true}else{this._F4ForOpen=true}};C.prototype.onsapshow=function(e){if(this.mobile){return}if(this.oPopup&&this.oPopup.isOpen()){this._close()}else{this._open()}e.preventDefault();e.stopImmediatePropagation()};C.prototype.onsapnextmodifiers=function(e){T.prototype.onsapnextmodifiers.apply(this,arguments);if(e.keyCode==q.sap.KeyCodes.ARROW_DOWN&&e.altKey){this.onsapshow(e);e.stopPropagation()}};C.prototype.onsaphide=function(e){if(this.mobile){return}this._close();e.stopPropagation()};C.prototype.onsapescape=function(e){if(this.oPopup&&this.oPopup.isOpen()){this._close();e.stopPropagation()}T.prototype.onsapescape.apply(this,arguments);var i=this.getSelectedItemId();if(i){var I=sap.ui.getCore().byId(i);this._iClosedUpDownIdx=this.indexOfItem(I);var l=this._getListBox();l.setSelectedIndex(this._iClosedUpDownIdx);q(this.getInputDomRef()).attr("aria-posinset",this._iClosedUpDownIdx+1)}else{q(this.getInputDomRef()).removeAttr("aria-posinset");this._iClosedUpDownIdx=-1}};C.prototype.onsapenter=function(e){this._close();this._checkChange(e)};C.prototype.onsapfocusleave=function(e){var l=this._getListBox();if((e.relatedControlId&&q.sap.containsOrEquals(l.getFocusDomRef(),sap.ui.getCore().byId(e.relatedControlId).getFocusDomRef()))||this._bOpening){this.focus()}else{T.prototype.onsapfocusleave.apply(this,arguments)}};C.prototype._checkChange=function(e,I){var o=this.getInputDomRef();if(!o){return}var n=q(o).val(),O=this.getValue();if(!this._F4ForOpen&&(this.getEditable()&&this.getEnabled())){var b=this.getItems(),c=null,t,k,s,d;if(O!=n){this.setValue(n,true);for(var i=0,l=b.length;i<l;i++){t=b[i].getText();if(t===n){if(i==this._iClosedUpDownIdx){c=b[i];k=c.getKey();s=c.getId();d=i;break}else if(!d){c=b[i];k=c.getKey();s=c.getId();d=i}}}this.setProperty("selectedKey",k,true);this.setProperty("selectedItemId",s,true);if(s){this._iClosedUpDownIdx=d}else{this._iClosedUpDownIdx=-1}if(this.mobile){if(!s){this._addDummyOption(n)}else{this._removeDummyOption();this.getDomRef("select").selectedIndex=d}}}else{var f=this.getSelectedItemId();var N;d=this._iClosedUpDownIdx;if(d>=0){c=b[d];if(c.getText()==n){N=c.getId()}}if(N&&N!=f){this.setSelectedItemId(N,true)}else{return}}this.fireChange({newValue:n,selectedItem:c})}};C.prototype.onkeypress=function(e){if(e.target.id==this.getId()+"-select"){return}if(!this.getEnabled()||!this.getEditable()){return}if(this._sTypeAhead){q.sap.clearDelayedCall(this._sTypeAhead)}var k=q.sap.KeyCodes;if(C._isHotKey(e)||e.keyCode===k.F4&&e.which===0){return}var K=e.which||e.keyCode;if(K!==k.DELETE&&K!==k.BACKSPACE&&K!==k.ESCAPE){this._sTypeAhead=q.sap.delayedCall(200,this,"_doTypeAhead")}else{T.prototype.onkeypress.apply(this,arguments);if(K!==k.ESCAPE){q(this.getInputDomRef()).removeAttr("aria-posinset")}}};C.prototype.onsapup=function(e){if(e.target.id==this.getId()+"-select"){return}if(!this.getEnabled()||!this.getEditable()){return}if(q(this.getFocusDomRef()).data("sap.InNavArea")){return}var l=this._getListBox(),I=l.getItems(),d=this.getInputDomRef(),v=q(d).val();var i=this._prepareUpDown(I,v);i=this._updateIdx(I,d,i-1,i,e);e.preventDefault();e.stopPropagation()};C.prototype.onsapdown=function(e){if(e.target.id==this.getId()+"-select"){return}if(!this.getEnabled()||!this.getEditable()){return}if(q(this.getFocusDomRef()).data("sap.InNavArea")){return}var l=this._getListBox(),I=l.getItems(),d=this.getInputDomRef(),v=q(d).val();var i=this._prepareUpDown(I,v);i=this._updateIdx(I,d,i+1,i,e);e.preventDefault();e.stopPropagation()};C.prototype.onsaphome=function(e){T.prototype.onsaphome.apply(this,arguments);if(e.target.id==this.getId()+"-select"){return}if(!this.getEditable()||!this.getEnabled()||!this.oPopup||!this.oPopup.isOpen()){return}var l=this._getListBox(),i=l.getItems(),d=this.getInputDomRef();this._updateIdx(i,d,0,undefined,e);e.preventDefault();e.stopPropagation()};C.prototype.onsapend=function(e){T.prototype.onsapend.apply(this,arguments);if(e.target.id==this.getId()+"-select"){return}if(!this.getEditable()||!this.getEnabled()||!this.oPopup||!this.oPopup.isOpen()){return}var l=this._getListBox(),I=l.getItems(),d=this.getInputDomRef();var i=I.length-1;i=this._updateIdx(I,d,i,undefined,e);e.preventDefault();e.stopPropagation()};C.prototype._doTypeAhead=function(){this._sTypeAhead=null;this._sWantedSelectedKey=undefined;this._sWantedSelectedItemId=undefined;var L=this._getListBox(),I=L.getItems(),o,t,r=q(this.getInputDomRef()),v=r.val(),s=q.sap.startsWithIgnoreCase;this._sTypedChars=v;var f=false;var i=0;for(var l=I.length;i<l;i++){o=I[i];t=""+o.getText();if(s(t,v)&&o.getEnabled()){r.attr("aria-posinset",i+1);r.val(t);this._doSelect(v.length,t.length);L.setSelectedIndex(i);L.scrollToIndex(i,true);f=true;if(this.mobile){this._removeDummyOption();this.getDomRef("select").selectedIndex=i}return}}L.clearSelection();L.scrollToIndex(i,true);if(!f){r.removeAttr("aria-posinset");if(this.mobile){this._addDummyOption(v)}}};C.prototype._prepareUpDown=function(I,v){var t;if(this._iClosedUpDownIdx>=0&&I[this._iClosedUpDownIdx]&&I[this._iClosedUpDownIdx].getText()!==v){this._iClosedUpDownIdx=-1}if(this._iClosedUpDownIdx===-1){for(var i=0,l=I.length;i<l;i++){t=I[i].getText();if(t===v){this._iClosedUpDownIdx=i;break}}}return this._iClosedUpDownIdx};C.prototype._updateIdx=function(I,d,n,c,e){var l=I.length,f=n===0&&c===undefined,D=c!==undefined&&c<n||f,i,r=q(d);if(n<0){i=0}else if(n<l){i=n}else{i=l-1}var o,v=false;do{n=D?i++:i--;o=I[n];v=o&&o.getEnabled()&&!(o instanceof sap.ui.core.SeparatorItem)&&o.getId()!==this.getId()+"_shi"}while(!v&&i<l&&i>=0);if(v){var t=o.getText();var p=n+1;if(this._determinePosinset){p=this._determinePosinset(I,n)}r.attr("aria-posinset",p);r.val(t);this._doSelect();this._fireLiveChange(e);var L=this._getListBox();L.setSelectedIndex(n);L.scrollToIndex(n,true)}else{n=c}this._iClosedUpDownIdx=n;return n};C.prototype._doSelect=function(s,e){var d=this.getInputDomRef();if(d){var r=q(d);d.focus();r.selectText(s?s:0,e?e:r.val().length)}return this};C.prototype.getF4ButtonDomRef=function(){return this.getDomRef("icon")};C.prototype._getPrivateListBox=function(){if(this._oListBox){return this._oListBox}this._oListBox=new sap.ui.commons.ListBox(this.getId()+"-lb",{allowMultiSelect:false});this.setAggregation("myListBox",this._oListBox,true);this._oListBox.attachEvent("itemsChanged",this._handleItemsChanged,this);this._oListBox.attachEvent("itemInvalidated",this._handleItemInvalidated,this);if(this.getDomRef()){this.$().attr("aria-owns",this.getId()+"-input "+this._oListBox.getId())}return this._oListBox};C.prototype._getExistingListBox=function(){var l=this.getListBox(),L;if(l){L=sap.ui.getCore().getControl(l)}else if(this._oListBox){L=this._getPrivateListBox()}return L};C.prototype._getListBox=function(u){var l=this._getExistingListBox();if(!l){l=this._getPrivateListBox()}if(u){l.setAllowMultiSelect(false);l.setDisplaySecondaryValues(this.getDisplaySecondaryValues());var d=this.getDomRef();if(d){l.setMinWidth(q(d).rect().width+"px")}}return l};C.prototype._open=function(d){if(this.mobile){return}if(d===undefined){d=-1}if(!this.getEditable()||!this.getEnabled()){return}if(!this.oPopup){this.oPopup=new P()}this._F4ForOpen=false;var l=this._getListBox(!this.oPopup.isOpen());var p=this.oPopup;this._prepareOpen(l);if(!this._oListBoxDelegate){this._oListBoxDelegate={oCombo:this,onclick:function(E){var i=q(E.target).closest("li").attr("id");if(i){var n=new sap.ui.base.Event("_internalSelect",this.oCombo,{selectedId:i});this.oCombo._handleSelect(n)}}}}l.addDelegate(this._oListBoxDelegate);p.setContent(l);p.setAutoClose(true);p.setAutoCloseAreas([this.getDomRef()]);p.setDurations(0,0);p.setInitialFocusId(this.getId()+'-input');var s=this._rerenderListBox(l);if(s){return}p.attachOpened(this._handleOpened,this);var e=P.Dock;p.open(d,e.BeginTop,e.BeginBottom,this,null,null,P.CLOSE_ON_SCROLL);q(l.getFocusDomRef()).attr("tabIndex","-1");q(this.getDomRef()).attr("aria-expanded",true)};C.prototype._rerenderListBox=function(l){sap.ui.getCore().applyChanges();return false};C.prototype._prepareOpen=function(L){this._bOpening=true;var r=q(this.getInputDomRef()),v=r.val(),n,I=L.getItems(),t,s=q.sap.startsWithIgnoreCase,e=v==="",S=this.getSelectedItemId(),o;var i=0;var b=-1;for(var l=I.length;i<l;i++){o=I[i];if(!o.getEnabled()){continue}t=""+o.getText();if(e||s(t,v)){if(t==v&&i==this._iClosedUpDownIdx){b=i;n=t;break}else if(this._iClosedUpDownIdx<0&&t==v&&o.getId()==S){b=i;n=t;break}else if(b<0){b=i;n=t}}}if(b>=0){this._iClosedUpDownIdx=b;r.attr("aria-posinset",b+1);r.val(n);this._doSelect();var E=new q.Event("sapshow");E.which=q.sap.KeyCodes.F4;this._fireLiveChange(E)}var c=L.getItems().length;var m=this.getMaxPopupItems();L.setVisibleItems(m<c?m:-1);L.setSelectedIndex(b)};C.prototype._handleOpened=function(){this.oPopup.detachOpened(this._handleOpened,this);var l=this._getListBox();l.scrollToIndex(this._iClosedUpDownIdx,true);l.attachSelect(this._handleSelect,this);this.oPopup.attachClosed(this._handleClosed,this);if(!!sap.ui.Device.browser.internet_explorer){q.sap.delayedCall(0,this,function(){q(this.getInputDomRef()).focus()})}if(q(this.getFocusDomRef()).data("sap.InNavArea")){q(this.getFocusDomRef()).data("sap.InNavArea",false)}this._bOpening=false};C.prototype._close=function(e){if(this.oPopup){this.oPopup.close(0)}};C.prototype._handleClosed=function(){this.oPopup.detachClosed(this._handleClosed,this);var l=this._getListBox();l.removeDelegate(this._oListBoxDelegate);l.detachSelect(this._handleSelect,this);q(this.getDomRef()).attr("aria-expanded",false);if(this._cleanupClose){this._cleanupClose(l)}};C.prototype._handleSelect=function(c){var s=c.getParameter("selectedIndex"),S=c.getParameter("selectedId"),i=c.getParameter("selectedItem");if(!i&&S){i=sap.ui.getCore().byId(S);if(i.getParent()!==this._getListBox(false)){i=null}s=q.inArray(i,this._getListBox().getItems())}if(i&&i.getEnabled()){var n=i.getText();this._iClosedUpDownIdx=s;this._close();q(this.getInputDomRef()).attr("aria-posinset",this._getListBox().getSelectedIndex()+1);var o=this.getValue();var O=this.getSelectedKey();var N=i.getKey();var b=this.getSelectedItemId();var d=i.getId();this._sTypedChars=n;this._sWantedSelectedKey=undefined;this._sWantedSelectedItemId=undefined;if(o!=n||O!=N||b!=d){this.setValue(n,true);this.setProperty("selectedKey",N,true);this.setProperty("selectedItemId",d,true);this.fireChange({newValue:n,selectedItem:i})}else if(n!=q(this.getInputDomRef()).val()){q(this.getInputDomRef()).val(n)}}this._doSelect();return i};C.prototype.getItems=function(){var l=this._getExistingListBox();return l?l.getItems():[]};C.prototype.insertItem=function(i,I){i=this.validateAggregation("items",i,true);this._getListBox().insertItem(i,I);return this};C.prototype.addItem=function(i){i=this.validateAggregation("items",i,true);this._getListBox().addItem(i);return this};C.prototype.removeItem=function(e){return this._getListBox().removeItem(e)};C.prototype.removeAllItems=function(){var l=this._getExistingListBox();return l?l.removeAllItems():[]};C.prototype.indexOfItem=function(i){return this._getListBox().indexOfItem(i)};C.prototype.destroyItems=function(){var l=this._getExistingListBox();if(l){this._getListBox().destroyItems()}return this};C.prototype.updateItems=function(){this.bNoItemCheck=true;this.updateAggregation("items");this.bNoItemCheck=undefined;if(!this._sHandleItemsChanged){this._sHandleItemsChanged=q.sap.delayedCall(0,this,"_handleItemsChanged",[null,true])}};C.prototype.setListBox=function(l){var o=sap.ui.getCore().byId(this.getListBox());if(o){o.detachEvent("itemsChanged",this._handleItemsChanged,this);o.detachEvent("itemInvalidated",this._handleItemInvalidated,this);if(this._bListBoxDependentSet){this.removeDependent(o);this._bListBoxDependentSet=false}}if(this._oListBox&&l){this._oListBox.detachEvent("itemsChanged",this._handleItemsChanged,this);this._oListBox.detachEvent("itemInvalidated",this._handleItemInvalidated,this);if(this.getAggregation("myListBox")){this.destroyAggregation("myListBox",true)}else{this._oListBox.destroy()}this._oListBox=null}this.setAssociation("listBox",l);var L=typeof l==="string"?sap.ui.getCore().byId(l):l;if(L&&L.attachEvent){L.attachEvent("itemsChanged",this._handleItemsChanged,this);L.attachEvent("itemInvalidated",this._handleItemInvalidated,this)}if(L&&!L.getParent()){this.addDependent(L);this._bListBoxDependentSet=true}if(this.getDomRef()&&L){this.$().attr("aria-owns",this.getId()+"-input "+L.getId())}return this};C.prototype._handleItemsChanged=function(e,d){if(this.bNoItemCheck){return}if(d){this._sHandleItemsChanged=null}var I=[];if(this._getExistingListBox()){I=this._getListBox().getItems()}var s=this.getSelectedKey();var S=this.getSelectedItemId();var n,N,b;var v=this.getValue();var c=-1;var f=false;var F=false;var g=false;this._iClosedUpDownIdx=-1;var B=!!this.getBinding("value");var h=!!this.getBinding("selectedKey");if(B&&h){B=false}var i=0;var o;for(i=0;i<I.length;i++){o=I[i];if((this._sWantedSelectedKey||this._sWantedSelectedItemId)&&(o.getKey()==this._sWantedSelectedKey||o.getId()==this._sWantedSelectedItemId)&&o.getEnabled()){n=o.getKey();N=o.getId();b=o.getText();c=i;this._sWantedSelectedKey=undefined;this._sWantedSelectedItemId=undefined;break}else if(s&&o.getKey()==s&&o.getEnabled()&&!(g&&B)){f=true;n=s;N=o.getId();b=o.getText();c=i;if(b==v&&N==S&&!this._sWantedSelectedKey&&!this._sWantedSelectedItemId){break}if(h&&!this._sWantedSelectedKey&&!this._sWantedSelectedItemId){break}}else if(S&&o.getId()==S&&o.getEnabled()&&!f&&!(g&&B)){F=true;n=o.getKey();N=S;b=o.getText();c=i}else if(o.getText()==v&&o.getEnabled()&&!(f&&!B)&&!(F&&!B)&&!g){g=true;n=o.getKey();N=o.getId();b=v;c=i;if(B&&!this._sWantedSelectedKey&&!this._sWantedSelectedItemId){break}}}this._iClosedUpDownIdx=c;if(v!=b&&c>=0){this.setProperty("value",b,true);q(this.getInputDomRef()).val(b)}this.setProperty("selectedKey",n,true);this.setProperty("selectedItemId",N,true);var D=this.getDomRef();if(D){q(this.getInputDomRef()).attr("aria-setsize",I.length);if(N){q(this.getInputDomRef()).attr("aria-posinset",c+1)}else{q(this.getInputDomRef()).removeAttr("aria-posinset")}if(this.mobile){var j=this.getDomRef("select");while(j.length>0){j.remove(0)}for(i=0;i<I.length;i++){o=I[i];var O=document.createElement("option");O.text=o.getText();O.id=this.getId()+"-"+o.getId();if(!o.getEnabled()){O.disabled="disabled"}j.add(O,null)}j.selectedIndex=c}}};C.prototype._handleItemInvalidated=function(e){var i=e.getParameter("item");if(i.getId()==this.getSelectedItemId()){if(i.getKey()!=this.getSelectedKey()){this.setProperty("selectedKey",i.getKey(),true)}if(i.getText()!=this.getValue()){T.prototype.setValue.apply(this,[i.getText()])}}if(!this._sHandleItemsChanged){this._handleItemsChanged(e)}};C.prototype.getFocusInfo=function(){return{id:this.getId(),sTypedChars:this._sTypedChars}};C.prototype.applyFocusInfo=function(f){var i=q(this.getInputDomRef());i.val(f.sTypedChars);if(!this.getSelectedItemId()||sap.ui.getCore().byId(this.getSelectedItemId()).getText()!=f.sTypedChars){this._doTypeAhead()}this.focus();return this};C.prototype.onAfterRendering=function(e){T.prototype.onAfterRendering.apply(this,arguments);var l=this.getListBox();if(l){var L=sap.ui.getCore().getControl(l);if(L.getDomRef()){L.$().appendTo(sap.ui.getCore().getStaticAreaRef())}}if(this.mobile){var t=this;this.$("select").bind("change",function(){var n=t.$("select").val();var b=t.getItems();var E=true;var o=0;var O=t.getValue();for(var i=0;i<b.length;i++){if(b[i].getText()==n){E=b[i].getEnabled()}if(b[i].getText()==O){o=i}}if(E){t.setValue(n);t.fireChange({newValue:n,selectedItem:sap.ui.getCore().byId(t.getSelectedItemId())})}else{t.getDomRef("select").selectedIndex=o}});if(this.getSelectedItemId()){for(var i=0;i<this.getItems().length;i++){var I=this.getItems()[i];if(this.getSelectedItemId()==I.getId()){this.getDomRef("select").selectedIndex=i;break}}}else{this._addDummyOption(this.getValue())}}};C._isHotKey=function(e){if(e.altKey||e.ctrlKey||e.metaKey){return true}var k=e.keyCode||e.which,b=q.sap.KeyCodes;switch(k){case b.ENTER:case b.SHIFT:case b.TAB:case b.ALT:case b.CONTROL:return true;case b.END:case b.HOME:case b.ARROW_LEFT:case b.ARROW_UP:case b.ARROW_RIGHT:case b.ARROW_DOWN:case b.F1:case b.F2:case b.F3:case b.F4:case b.F5:case b.F6:case b.F7:case b.F8:case b.F9:case b.F10:case b.F11:case b.F12:if(e.type=="keypress"){return e.which===0}else{return true}default:return false}};C.prototype.setSelectedKey=function(s){if(this.getSelectedKey()==s){return this}if(!s&&this._isSetEmptySelectedKeyAllowed()){return this}var I=this.getItems();var n=true;var S;var b;for(var i=0;i<I.length;i++){if(I[i].getKey()==s&&I[i].getEnabled()){var o=I[i];S=o.getId();var v=o.getText();this.setValue(v,true);this._sTypedChars=v;b=i;n=false;break}}if(!n){this.setProperty("selectedKey",s,true);this.setProperty("selectedItemId",S,true);var d=this.getDomRef();if(d){q(this.getInputDomRef()).attr("aria-posinset",b+1);if(this.mobile){this._removeDummyOption();this.getDomRef("select").selectedIndex=b}}this._sWantedSelectedKey=undefined;this._iClosedUpDownIdx=b}else{this._sWantedSelectedKey=s;this._iClosedUpDownIdx=-1}this._sWantedSelectedItemId=undefined;return this};C.prototype._isSetEmptySelectedKeyAllowed=function(){this.setProperty("selectedKey","",true);this.setProperty("selectedItemId","",true);this.setValue("",true);return true};C.prototype.setSelectedItemId=function(s){if(this.getSelectedItemId()==s){return this}if(!s&&this._isSetEmptySelectedKeyAllowed()){return this}var I=this.getItems();var n=true;var k;var b;for(var i=0;i<I.length;i++){if(I[i].getId()==s&&I[i].getEnabled()){var S=I[i];k=S.getKey();var v=S.getText();this.setValue(v,true);this._sTypedChars=v;b=i;n=false;break}}if(!n){this.setProperty("selectedItemId",s,true);this.setProperty("selectedKey",k,true);var d=this.getDomRef();if(d){q(this.getInputDomRef()).attr("aria-posinset",b+1);if(this.mobile){this._removeDummyOption();this.getDomRef("select").selectedIndex=b}}this._sWantedSelectedItemId=undefined;this._iClosedUpDownIdx=b}else{this._sWantedSelectedItemId=s;this._iClosedUpDownIdx=-1}this._sWantedSelectedKey=undefined;return this};C.prototype.setValue=function(v,n){if(!n){var I=this.getItems();var k;var s;var b;this._iClosedUpDownIdx=-1;for(var i=0;i<I.length;i++){if(I[i].getText()==v&&I[i].getEnabled()){var S=I[i];s=S.getId();k=S.getKey();b=i;this._iClosedUpDownIdx=b;break}}this.setProperty("selectedKey",k,true);this.setProperty("selectedItemId",s,true);var d=this.getDomRef();if(d){if(s){q(this.getInputDomRef()).attr("aria-posinset",b+1)}else{q(this.getInputDomRef()).removeAttr("aria-posinset")}if(this.mobile){if(!s){this._addDummyOption(v)}else{this._removeDummyOption();this.getDomRef("select").selectedIndex=b}}}}T.prototype.setValue.apply(this,[v]);this._sTypedChars=this.getValue();this._sWantedSelectedKey=undefined;this._sWantedSelectedItemId=undefined;return this};C.prototype.invalidate=function(o){if(!o||!(o instanceof sap.ui.commons.ListBox)||o!=this._getListBox()){sap.ui.core.Control.prototype.invalidate.apply(this,arguments)}else{if(this.getUIArea()&&o.getDomRef()){this.getUIArea().addInvalidatedControl(o)}}};C.prototype.clone=function(i){var c=sap.ui.core.Control.prototype.clone.apply(this,arguments),l=this.getAggregation("myListBox"),L;if(l&&!c._oListBox){l.detachEvent("itemsChanged",this._handleItemsChanged,this);l.detachEvent("itemInvalidated",this._handleItemInvalidated,this);L=l.clone(i);L.attachEvent("itemsChanged",c._handleItemsChanged,c);L.attachEvent("itemInvalidated",c._handleItemInvalidated,c);c.setAggregation("myListBox",L,true);c._oListBox=L;l.attachEvent("itemsChanged",this._handleItemsChanged,this);l.attachEvent("itemInvalidated",this._handleItemInvalidated,this)}return c};C.prototype._addDummyOption=function(v){var o=this.getDomRef("dummyOption");if(!o){var i=this.getItems();o=document.createElement("option");o.text=v;o.id=this.getId()+"-dummyOption";if(i.length>0){this.getDomRef("select").add(o,q.sap.domById(this.getId()+"-"+i[0].getId()))}else{this.getDomRef("select").add(o,null)}}else{o.text=v}this.getDomRef("select").selectedIndex=0};C.prototype._removeDummyOption=function(){var o=this.getDomRef("dummyOption");if(o){this.getDomRef("select").remove(0)}};C.prototype.getFocusDomRef=function(){if(this.mobile){return this.getDomRef("select")||null}else{return this.getDomRef("input")||null}};return C},true);
