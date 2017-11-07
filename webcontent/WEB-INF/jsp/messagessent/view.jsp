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
                <i class="modernpics icons32">m</i>
                <span class="panel-divider"></span>
                <h2>Message sent to admin<span></span></h2>
                <div class="aqua-panel-tabs-icons pull-right">
                    <a href="#" class="minimize">--</a>
                    <a href="#" class="modernpics maximize">v</a>
                </div>
            </div>
            <div class="aqua-panel-content">
                <div id="user-grid" class="grid-view">
                	<strong>Date:</strong>
					<div class="well">
						<fmt:formatDate value="${messageSent.created}" pattern="EEEE, dd MMMM yyyy HH:mm:ss"/>
					</div>
					<strong>Subject:</strong>
					<div class="well">
						<c:out value="${messageSent.subject}"/>
					</div>
					<strong>Message:</strong>
					<div class="well">
						<c:out value="${messageSent.message}"/>
					</div>


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

	function deleteMessage(id){
			if( confirm('¿Do you wish to delete the message?')){
				$.post("<html:rewrite page="/do/messagessent/delete"/>", { id: id}, function(data){
					if( data == 'ok'){
						$("#tr_"+id).hide();
					} 
				});
				
			}
		}
   


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


