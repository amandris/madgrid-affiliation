<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>











<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<html:rewrite page="/css/style.css"/>" />
    <link rel="stylesheet" type="text/css" href="<html:rewrite page="/css/guiders-1.2.8.css"/>" />
    <script type="text/javascript" src="<html:rewrite page="/js/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<html:rewrite page="/js/jquery.ba-bbq.js"/>"></script>
    <script type="text/javascript" src="<html:rewrite page="/js/prettify.js"/>"></script>
    <script type="text/javascript" src="<html:rewrite page="/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<html:rewrite page="/js/guiders-1.2.8.js"/>"></script>
    <script type="text/javascript" src="<html:rewrite page="/js/loading-overlay.min.js"/>"></script>
    <title>Instantri.ch affiliation control panel</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
</head>
<body>

<div id="yw0"></div>
<div id="breadcrumbs"></div>

<div class="aqua-container">



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


    <div class="span3 offset2">
    
    <div class="btn-toolbar well" style="text-align: center;background-color: white;">
    	<div>
    		<img src="http://www.instantri.ch/img/box_instantrich.png" width="128"/>
    	</div>
    	<div>
	      	<span style="font-size:18px;color:#133f53;">Instantri.ch affiliation control panel</span>
	    </div>  
	</div>
    
        <div class="aqua-panel">
            <div class="aqua-panel-header">
                <i class="modernpics icons32">n</i><span class="panel-divider"></span><h2>Login</h2>
                <div class="aqua-panel-tabs-icons pull-right">
                    <a href="#" class="minimize"></a>
                </div>
            </div>
            <div class="aqua-panel-content">
            	<html:form  styleClass="form-inline" styleId="varticalForm" action="/affiliationuser/login" method="post">
            	                
            	                
            		<logic:messagesPresent property="login">
	                    <div class="alert alert-block alert-error" id="varticalForm_es_">
	                        <p>Please fix the following input errors:</p>
	                        <ul>
	                            <li>Incorrect username or password.</li>
	                        </ul>
	                    </div>
                    </logic:messagesPresent>
                   
                   
                   	
                   
                   
                    <div class="input-prepend">
                        <span class="add-on">
                            <div class="modernpics icons24">f</div>
                        </span>
                        <html:text property="login" style="width:310px;" styleId="login"/>
                        <span class="add-on">
                            <a class="modernpics icons24" rel="tooltip" data-placement="left" title="<strong>Note:</strong> Use the same username you picked for your instantri.ch account.">?</a>
                        </span>
                    </div>
                    <div class="input-prepend">
                        <span class="add-on">
                            <div class="modernpics icons24">n</div>
                        </span>
                        <html:password style="width:340px;" styleId="password" property="password"/>
                    </div>
                    <hr/>
                    <button class="pull-right btn btn-primary btn-large btn-block"  type="submit" id="_submit" name="_submit">Login</button>
                    <div class="clear"></div>
                </html:form>
            </div>
        </div>
   
	    <div class="clear"></div>
	    
	    <div class="span1" style="margin-left:35px;">
		    <a href="http://www.instantri.ch/affiliate" class="aqua-shortcut text-align-center" style="width:160px;">
		        <span class="modernpics newline">+</span>
		        <span class="label label-info">Join affiliation program</span>
		    </a>
		</div>
	    
	    <div class="span1" style="margin-left:60px;">
		    <a href="<html:rewrite page="/password.jsp"/>" class="aqua-shortcut text-align-center" style="width:160px;">
		        <span class="modernpics newline">R</span>
		        <span class="label label-warning">Reset Password</span>
		    </a>
		</div>
    </div>
    
</div>
<div class="clear"></div>

<script type="text/javascript">
    /*<![CDATA[*/
               
               
    $(document).ready(function(){
       	document.getElementById("login").placeholder = "Username";
       	document.getElementById("password").placeholder = "Password";
	});
    
   
               
    jQuery(function($) {
        jQuery('a[rel="tooltip"]').tooltip();
        jQuery('a[rel="popover"]').popover();
        
       
        jQuery('#yw0 .alert').alert();
        guiders.createGuider({'id':'start','buttons':[{'name':'Next'}],'description':'This is sample Guide tour. You can use it to teach new users, show some new functions etc.','overlay':true,'title':'Welcome','xButton':true,'next':'menu'});
        guiders.createGuider({'id':'menu','buttons':[{'name':'Previous','onclick':function(){guiders.prev();}},{'name':'Next','onclick':function(){guiders.next();}},{'name':'Exit','onclick':function(){guiders.hideAll();}}],'attachTo':'.aqua-well-mini-green','description':'\r\n<strong>\r\n    You can add notify Icons in header in 3 different colors: \r\n<\/strong>\r\n<ul>\r\n    <li>Red (aqua-well-mini-red),<\/li>\r\n    <li>Green (aqua-well-mini-green),<\/li>\r\n    <li>Blue (aqua-well-mini-blue),<\/li>\r\n<\/ul>\r\n\r\n<div class=\"clear\"><br/><\/div>\r\n<div class=\"aqua-header-shortcuts\">\r\n    <div style=\"background: #0b242f; padding: 10px; text-align: center;\">\r\n        <div class=\"btn-group\">\r\n            <a class=\"aqua-well-mini-blue text-align-center\" href=\"#\" data-toggle=\"dropdown\">\r\n                <span class=\"modernpics icons-white icons48 newline\">f<\/span>\r\n                <span class=\"label label-info\">Click me!<\/span>\r\n            <\/a>\r\n            <div class=\"dropdown-menu quick-info\">\r\n                <table class=\"info-dropdown\">\r\n                    <tbody><tr><td class=\"aqua-avatar-quick-list\" rowspan=\"4\"> <\/td><\/tr>\r\n                        <tr><td><a href=\"#\">John Doe <span class=\"modernpics icons16\">=<\/span><\/a><\/td><\/tr>\r\n                        <tr><td>2/9/2012 10:15 <span class=\"modernpics icons16\">}<\/span><\/td><\/tr>\r\n                        <tr><td>john.doe@mail.com <span class=\"modernpics icons16\">@<\/span><\/td><\/tr>\r\n                    <\/tbody><\/table>\r\n                <table class=\"info-dropdown\">\r\n                    <tbody><tr><td class=\"aqua-avatar-quick-list\" rowspan=\"4\">  <\/td><\/tr>\r\n                        <tr><td><a href=\"#\">John Doe <span class=\"modernpics icons16\">=<\/span><\/a><\/td><\/tr>\r\n                        <tr><td>2/9/2012 10:15 <span class=\"modernpics icons16\">}<\/span><\/td><\/tr>\r\n                        <tr><td>john.doe@mail.com <span class=\"modernpics icons16\">@<\/span><\/td><\/tr>\r\n                    <\/tbody><\/table>\r\n                <table class=\"info-dropdown\">\r\n                    <tbody><tr><td class=\"aqua-avatar-quick-list\" rowspan=\"4\">  <\/td><\/tr>\r\n                        <tr><td><a href=\"#\">John Doe <span class=\"modernpics icons16\">=<\/span><\/a><\/td><\/tr>\r\n                        <tr><td>2/9/2012 10:15 <span class=\"modernpics icons16\">}<\/span><\/td><\/tr>\r\n                        <tr><td>john.doe@mail.com <span class=\"modernpics icons16\">@<\/span><\/td><\/tr>\r\n                    <\/tbody><\/table>\r\n                <table class=\"info-dropdown\">\r\n                    <tbody><tr><td class=\"aqua-avatar-quick-list\" rowspan=\"4\">  <\/td><\/tr>\r\n                        <tr><td><a href=\"#\">John Doe <span class=\"modernpics icons16\">=<\/span><\/a><\/td><\/tr>\r\n                        <tr><td>2/9/2012 10:15 <span class=\"modernpics icons16\">}<\/span><\/td><\/tr>\r\n                        <tr><td>john.doe@mail.com <span class=\"modernpics icons16\">@<\/span><\/td><\/tr>\r\n                    <\/tbody><\/table>\r\n\r\n            <\/div>\r\n        <\/div>\r\n\r\n        <a class=\"aqua-well-mini-red text-align-center\" href=\"#\">\r\n            <span class=\"modernpics icons-white newline\">m<\/span>\r\n            <span class=\"label label-important\">3 messages<\/span>\r\n        <\/a>\r\n        <a class=\"aqua-well-mini-green text-align-center\" href=\"#\">\r\n            <span class=\"modernpics icons-white newline\">b<\/span>\r\n            <span class=\"label label-success\">10 comments<\/span>\r\n        <\/a>\r\n\r\n    <\/div>\r\n<\/div>\r\n<div class=\"clear\"><\/div>\r\n','overlay':true,'position':5,'title':'Notify Icons in header','width':'600px','xButton':true,'next':'menu1'});
        guiders.createGuider({'id':'menu1','buttons':[{'name':'Next'}],'attachTo':'#yw5','description':'Guides can easly stick to ID or Class of css elements.','overlay':true,'position':6,'title':'Menu','xButton':true,'next':'menu2'});
        guiders.createGuider({'id':'menu2','buttons':[{'name':'Next'}],'attachTo':'#yw5','description':'Guides can easly stick to ID or Class of css elements.','overlay':true,'position':5,'title':'Menu','xButton':true,'next':'menu3'});
        guiders.createGuider({'id':'menu3','buttons':[{'name':'Next'}],'attachTo':'#yw5','description':'Guides can easly stick to ID or Class of css elements.','overlay':true,'position':3,'title':'Menu','xButton':true});


        $(".aqua-panel-tabs-icons .minimize").click(function(){

            $(this).parents(".aqua-panel").children(".aqua-panel-content").slideToggle("fast");

            return false;
        });

        $(".aqua-panel-tabs-icons .maximize").click(function(){
            var panel = $(this).parents(".aqua-panel");



            if($(panel).hasClass("fullscreen")){
                $(panel).removeClass("fullscreen");
                $(panel).children(".aqua-panel-content").css("height", "auto");
                $(panel).children(".aqua-panel-content").css("width", "auto");
                $(panel).children(".aqua-panel-content").css("overflow", "inherit");
                $("body").css("overflow-y", "auto");
            }
            else{



                var w=window,d=document,e=d.documentElement,g=d.getElementsByTagName("body")[0],x=w.innerWidth||e.clientWidth||g.clientWidth,y=w.innerHeight||e.clientHeight||g.clientHeight;
                $(panel).addClass("fullscreen");
                $(panel).children(".aqua-panel-content").slideDown("fast");
                $(panel).children(".aqua-panel-content").css("height", y-60);
                $(panel).children(".aqua-panel-content").css("overflow-y", "scroll");
                $("body").css("overflow-y", "hidden");
                $(panel).children(".aqua-panel-content").css("width", x-20);

            }
            return false;
        });


    });
    /*]]>*/
</script>
</body>

</html>






