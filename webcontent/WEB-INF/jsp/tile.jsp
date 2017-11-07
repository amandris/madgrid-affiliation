<%@page import="java.util.Date"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%
response.setHeader( "Pragma", "no-cache" );
response.addHeader( "Cache-Control", "must-revalidate" );
response.addHeader( "Cache-Control", "no-cache" );
response.addHeader( "Cache-Control", "no-store" );
response.setDateHeader("Expires", 0);  



%>



<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">

    <link rel="stylesheet" type="text/css" href="<html:rewrite page="/css/style.css"/>" />
    <link rel="stylesheet" type="text/css" href="<html:rewrite page="/css/guiders-1.2.8.css"/>" />
    <link rel="stylesheet" type="text/css" href="<html:rewrite page="/css/themes/prettify.css"/>" />
    <script type="text/javascript" src="<html:rewrite page="/js/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<html:rewrite page="/js/jquery.ba-bbq.js"/>"></script>
    <script type="text/javascript" src="<html:rewrite page="/js/prettify.js"/>"></script>
    <script type="text/javascript" src="<html:rewrite page="/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<html:rewrite page="/js/guiders-1.2.8.js"/>"></script>
    <script type="text/javascript" src="<html:rewrite page="/js/loading-overlay.min.js"/>"></script>


    <title>Affiliation Program Instantri.ch</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
</head>
<body>

<tiles:get name='header'/>

<div id="yw7">

</div>

<c:if test="${ not empty systemAlert}">
	<div class="aqua-container">
		<div class="span7">
		<div class="alert in alert-block fade alert-success" >
			<a class="close" data-dismiss="alert">×</a>
			<span class="modernpics icons-green">!</span>
			<span class="panel-divider"></span>
			<c:out value="${systemAlert }"/> 
		</div>
		</div>
	</div>
</c:if>

<c:if test="${ not empty systemAlertWarning}">
	<div class="aqua-container">
		<div class="span7">
		<div class="alert in alert-block fade alert-danger" >
			<a class="close" data-dismiss="alert">×</a>
			<span class="modernpics icons-red">!</span>
			<span class="panel-divider"></span>
			<c:out value="${systemAlertWarning }"/> 
		</div>
		</div>
	</div>
</c:if>

<tiles:get name='body'/>

<tiles:get name='scripts'/>



</body>

</html>

