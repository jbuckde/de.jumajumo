<html>
<head>
<meta http-equiv='X-UA-Compatible' content='IE=edge' />
<link rel="icon" type="image/jpg"
	href="/resources/icon/meetingpoint.jpg" />
<title>Treffpunkt</title>

<style type="text/css">
.sapUiExploredFeedMargin {
  margin-top: 1rem;
  margin-bottom: 1rem;
  height: 5em;
</style>

<link rel="stylesheet" type="text/css" href="resources/styles/styles.css">

<script id='sap-ui-bootstrap' src='resources/sap-ui-core.js'
	data-sap-ui-theme='sap_bluecrystal' data-sap-ui-libs='sap.m'
	data-sap-ui-compatVersion="1.16" data-sap-ui-xx-bindingSyntax="complex"></script>


<script>
	jQuery.sap.registerModulePath('jumajumo', './resources/de/jumajumo');
	jQuery.sap.require("jumajumo.core.app.App");
	jQuery.sap.require("jumajumo.core.util.DateFormatter");
	jQuery.sap.require("jumajumo.core.util.ImageFormatter");
	jQuery.sap.require("jumajumo.mp.util.Formatter");

	new sap.m.Shell("Shell", {
		title : "jumajumo.mp",
		showLogout : false,
		app : new sap.ui.core.ComponentContainer({
			name : 'jumajumo.mp'
		})
	}).placeAt('content');
</script>

<script src='resources/de/jumajumo/core/jumajumo.js' type='text/javascript'></script>

<script type="text/javascript">
		// stable link handling: get the stable link uuid from the session
		<%
		// check if the parameter exists
		if (null != session.getAttribute("stableuuid")) 
		{%>
			jumajumo.getApp().setStableUUID('<%=session.getAttribute("stableuuid")%>');

			// remove the session attribute -- allow reload of the page w/o opening the stable link once again.
			<%session.removeAttribute("stableuuid");%>
	
		<%}%>
	
</script>

<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>

</head>
<body class='sapUiBody'>
	<div id='content'></div>
</body>
</html>