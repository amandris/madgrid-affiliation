<%@page import="com.madgrid.model.AdminUser"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<tiles:insert template='../tile.jsp'>
	<tiles:put name='header' content='header.jsp'/>
	<tiles:put name='body' type='string'>

	<div class="aqua-container">
    <div class="span7">
        <div class="aqua-panel">
            <div class="aqua-panel-header">
                <i class="modernpics icons32">D</i>
                <span class="panel-divider"></span>
                <h2>Banners<span></span></h2>
                <div class="aqua-panel-tabs-icons pull-right">
                    <a href="#" class="minimize">--</a>
                    <a href="#" class="modernpics maximize">v</a>
                </div>
            </div>
            <div class="aqua-panel-content">
                <div id="user-grid" class="grid-view">
                  

                    
                   <table width="100%" border="0" cellpadding="20" cellspacing="0" align="center" valign="top" >
	
						<tr align="center">
							<td>
								<table style="width:80%;text-align: center;">
									<tr>
										<td>
											Give this url to anyone you want, put it in a banner, advertise it, etc..
										</td>
									<tr>
									<tr>
										<td style="font-size:18px;">
											http://www.instantri.ch?affiliate=<c:out value="${affiliationUserSession.name}"/>					
										</td>
									<tr>
									
								</table> 
							</td>
						</tr>
						
						<tr align="center">
							<td>
								<table style="width:80%;padding-top:5px;text-align: center;">
									<tr>
										<td>
											<img src="http://www.instantri.ch/img/banner1.jpg" >
										</td>
									</tr>
									<tr>
										<td style="font-size: 10px;">
											banner1.jpg (468x60) Thanks to DeslockDarkstar<br>
											<a href="http://www.instantri.ch/img/banner1.jpg" >http://www.instantri.ch/img/banner1.jpg</a>
										</td>
									</tr>
									<tr >
										<td>
											<img src="http://www.instantri.ch/img/banner2.png" style="border:solid grey 1px;margin-top:30px;">
										</td>
									</tr>
									<tr>
										<td  style="font-size: 10px;">
											banner2.png (468x60)<br/>
											<a href="http://www.instantri.ch/img/banner2.png" >http://www.instantri.ch/img/banner2.jpg</a>
										</td>
									</tr>
									<tr>
										<td>
											<img src="http://www.instantri.ch/img/banner3.png" style="border:solid grey 1px;margin-top:30px;">
										</td>
									</tr>
									<tr>
										<td  style="font-size: 10px;">
											banner3.png (200x200)<br/>
											<a href="http://www.instantri.ch/img/banner3.png" >http://www.instantri.ch/img/banner3.jpg</a>
										</td>
									<tr>
									<tr>
										<td>
											<img src="http://www.instantri.ch/img/banner4.png" style="border:solid grey 1px;margin-top:30px;">
										</td>
									<tr>
									<tr>
										<td  style="font-size: 10px;">
											banner4.png (728x90)<br/>
											<a href="http://www.instantri.ch/img/banner4.png" >http://www.instantri.ch/img/banner4.jpg</a>
										</td>
									<tr>
								</table> 
							</td>
						</tr>
					</table>


                </div>
            </div>
        </div>
    </div>

</div>
<div class="clear"></div>


	</tiles:put>
	
	<tiles:put name="scripts" type="string">
		<script type="text/javascript" src="<html:rewrite page="/js/jquery.yiigridview.js"/>"></script>
	<script type="text/javascript">
	 /*<![CDATA[*/

   


    jQuery(function($) {
        jQuery('a[rel="tooltip"]').tooltip();
        jQuery('a[rel="popover"]').popover();

        jQuery('#yw7 .alert').alert();

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
			
	</tiles:put>
</tiles:insert>


