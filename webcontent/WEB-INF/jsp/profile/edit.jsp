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
	                <i class="modernpics icons32">f</i>
	                <span class="panel-divider"></span>
	                <h2>Profile<span></span></h2>
	                <div class="aqua-panel-tabs-icons pull-right">
	                    <a href="#" class="minimize">--</a>
	                    <a href="#" class="modernpics maximize">v</a>
	                </div>
	            </div>
	            <div class="aqua-panel-content">
					
					<html:form action="/profile/save" styleClass="well form-horizontal" styleId="horizontalForm" method="post">
						<fieldset>
							<div class="control-group ">
								<label class="control-label" for="Form_subject" style="width: 245px;">Affiliation url</label>
								<div class="controls" style="line-height:30px;margin-left: 270px;">
									http://www.instantri.ch?affiliate=<c:out value="${affiliationUserSession.name}"/>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label" for="Form_subject" style="width: 245px;">Login</label>
								<div class="controls" style="line-height:30px;margin-left: 270px;">
									
									<c:out value="${profileForm.login }"/>
								</div>
							</div>
							
							<div class="control-group ">
								<label class="control-label" for="Form_subject" style="width: 245px;">Email</label>
								<div class="controls" style="line-height:30px;margin-left: 270px;">
									
									<c:out value="${profileForm.email }"/>
								</div>
							</div>
							
							<div class="control-group ">
								<label class="control-label" for="Form_subject" style="width: 245px;">Password</label>
								<div class="controls" style="line-height:30px;margin-left: 270px;">
									
									<a href="<html:rewrite page="/do/password/edit"/>" class="btn">Change password</a><span class="label label-info" style="margin-left: 20px;">For affiliation control panel only</span>
 								</div>
							</div>
							
							<div class="control-group ">
								<label class="control-label" for="Form_subject" style="width: 245px;">Percentage</label>
								<div class="controls" style="line-height:30px;margin-left: 270px;">
									
									<c:out value="${profileForm.percentage }"/> %
								</div>
							</div>
					
					
							<div class="control-group ">
								<label class="control-label" for="Form_sendEmailAlerts" style="width: 245px;">Send <span style=" font-style: italic;">'New user registers'</span> email alerts</label>
								<div class="controls" style="line-height:30px;margin-left: 270px;">
									<html:checkbox property="sendEmailAlerts" styleId="Form_sendEmailAlerts"   />
									<input type="hidden" name="sendEmailAlerts" value="false"/>
									<span class="label label-info" style="margin-left: 20px;"><span style=" font-style: italic;">'Credits purchased'</span> alerts are always sent</span>
								</div>
							</div>
					
							<div class="control-group ">
								<label class="control-label" for="Form_url" style="width: 245px;">User website url</label>
								<div class="controls" style="line-height:30px;margin-left: 270px;">
									<html:text property="url" styleId="Form_url"  style="width:400px;" maxlength="256" />
									<logic:messagesPresent property="url">
										<br/>
										<span class="label label-important">
											<html:errors property="url"/>
										</span>
									</logic:messagesPresent>
								</div>
							</div>
							
							<div class="control-group ">
								<label class="control-label" for="Form_bitcoinAddress" style="width: 245px;">Bitcoin wallet address</label>
								<div class="controls" style="line-height:30px;margin-left: 270px;">
									<html:text property="bitcoinAddress" styleId="Form_bitcoinAddress"  style="width:400px;" maxlength="256"/>
									<logic:messagesPresent property="bitcoinAddress">
										<br/>
										<span class="label label-important">
											<html:errors property="bitcoinAddress"/>
										</span>
									</logic:messagesPresent>
								</div>
							</div>
	
						</fieldset>
					
						<div class="form-actions">
							<button class="btn btn-primary" type="submit" name="yt0">Save</button>
						</div>
					</html:form>
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


