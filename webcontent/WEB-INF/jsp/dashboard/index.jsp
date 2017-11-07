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
        <div id="content">

        
            <div class="span7" >
            	
            
                <div class="aqua-panel">
                    <div class="aqua-panel-header">
                        <i class="modernpics icons32">=</i>
                        <span class="panel-divider"></span><h2>Dashboard</h2>
                        <div class="aqua-panel-tabs-icons pull-right">
                            <a href="#" class="minimize">--</a>
                        </div>
                    </div>
                    <div class="aqua-panel-content">
                    	<ul class="breadcrumbs breadcrumb" style="margin-left: 0px;margin-right: 10px;">
							<li style="margin-left: 20px;">Bitcoins earned so far:</li>
							<li><span class="label label-success"><fmt:formatNumber value="${bitcoinsPayed}" minFractionDigits="2" maxFractionDigits="8"/> BTC</span></li>
							<li style="margin-left: 50px;">Bitcoins pending for payment:</li>
							<li>
								<c:if test="${bitcoinsPending == 0}">
									<span class="label label-success"><fmt:formatNumber value="${bitcoinsPending}" minFractionDigits="2" maxFractionDigits="8"/> BTC</span>
								</c:if>
								<c:if test="${bitcoinsPending > 0}">
									<span class="label label-important"><fmt:formatNumber value="${bitcoinsPending}" minFractionDigits="2" maxFractionDigits="8"/> BTC</span>
								</c:if>
							</li>
						</ul>
						
						<ul class="breadcrumbs breadcrumb" style="margin-left: 0px;margin-right: 10px;">
							<li style="margin-left: 20px;">Affiliation URL:</li>
							<li><strong>http://www.instantri.ch?affiliate=<c:out value="${affiliationUserSession.name}"/></strong></li>
							
							
						</ul>
                    
                    
                        <div class="inpanel tabs-above" id="yw0">
                            <div class="tab-content">
                                <div class="info-panel">
                                    <a href="<html:rewrite page="/do/registeredusers/list"/>">
                                        <div class="span1">
                                            <span class="aqua-shortcut text-align-center">
                                                <span class="modernpics newline">g</span>
                                                <span class="label label-info"><c:out value="${registeredUsersCount}"/> Registered users</span>
                                            </span>
                                        </div>
                                    </a>
                                    <a href="<html:rewrite page="/do/creditspurchased/list"/>">
                                        <div class="span1">
                                            <span class="aqua-shortcut text-align-center">
                                                <span class="modernpics newline">#</span>
                                                <span class="label label-info"><c:out value="${creditPurchasesCount}"/> Credit purchases</span>
                                            </span>
                                        </div>
                                    </a>
                                    <a href="<html:rewrite page="/do/payment/list"/>">
                                        <div class="span1">
                                            <span class="aqua-shortcut text-align-center">
                                                <span class="modernpics newline">$</span>
                                                <span class="label label-info"><c:out value="${affiliationPaymentCount}"/> Payments</span>
                                            </span>
                                        </div>
                                    </a>
                                    <a href="<html:rewrite page="/do/messagesreceived/list"/>">
                                        <div class="span1">
                                            <span class="aqua-shortcut text-align-center">
                                                <span class="modernpics newline">m</span>
                                                <span class="label label-info"><c:out value="${affiliationMessagesReceivedCount}"/> New messages</span>
                                            </span>
                                        </div>
                                    </a>
                                    
                                    <a href="<html:rewrite page="/do/banners"/>">
                                        <div class="span1">
                                            <span class="aqua-shortcut text-align-center">
                                                <span class="modernpics newline">D</span>
                                                <span class="label label-info">4 Banners</span>
                                            </span>
                                        </div>
                                    </a>
                                    
                                     <a href="<html:rewrite page="/do/profile/edit"/>">
                                        <div class="span1">
                                            <span class="aqua-shortcut text-align-center">
                                                <span class="modernpics newline">f</span>
                                                <span class="label label-info"> Profile</span>
                                            </span>
                                        </div>
                                    </a>


									<c:if test="${affiliationUserSession.creditAlreadyClaimed == false }">
	   									<a href="<html:rewrite page="/do/claimcredit"/>">
	                                        <div class="span1">
	                                            <span class="aqua-shortcut text-align-center">
	                                                <span class="modernpics newline">)</span>
	                                                <span class="label label-important"> Get your free credit</span>
	                                            </span>
	                                        </div>
	                                    </a>
	                                </c:if>
	                                
	                                <c:if test="${affiliationUserSession.creditAlreadyClaimed == true }">
                                        <div class="span1">
                                            <span class="aqua-shortcut text-align-center disabled">
                                                <span class="modernpics newline">)</span>
                                                <span class="label">Credit already claimed</span>
                                            </span>
                                        </div>
	                                </c:if>



									<c:choose>
	                                   <c:when test="${bitcoinsPending > 0 && affiliationUserSession.askedForTransfer == false}">
	                                   		<c:choose>
		                                   		<c:when test="${empty affiliationUserSession.bitcoinAddress or affiliationUserSession.bitcoinAddress == 'Not assigned'}">
		                                   			<a data-toggle="modal" data-target="#myModal" href="#" id="generateJsonButton">
				                                       <div class="span1" style="width:910px;">
			                                            <span class="aqua-shortcut text-align-center" style="width:900px;">
				                                                <span class="modernpics newline">$</span>
				                                                <span class="label label-important">Request <fmt:formatNumber value="${bitcoinsPending}" minFractionDigits="2" maxFractionDigits="8"/> BTC payment</span>
				                                            </span>
				                                        </div>
				                                    </a>
				                                    
													<div id="myModal" class="modal fade" aria-hidden="true" style="display: none;">
														<div class="modal-header">
															<a class="close" data-dismiss="modal">×</a>
															<h4>A Bitcoin wallet address is required</h4>
														</div>
															
														<div class="modal-body">
															<p>You still didn't set up a Bitcoin wallet address in your profile.</p>
															<p>Please go to your <a href="<html:rewrite page="/do/profile/edit"/>">profile</a> page and add a Bitcoin wallet address. You'll be able to request the payment after that.</p>
														</div>
															
														<div class="modal-footer">
															<a data-dismiss="modal" class="btn btn-primary" href="<html:rewrite page="/do/profile/edit"/>" onclick="document.location.href='<html:rewrite page="/do/profile/edit"/>'">Go to profile</a>                    
															<a data-dismiss="modal" class="btn" href="#">Close</a>                
														</div>
													</div>
		                                   		</c:when>
		                                   	
			                                   	<c:otherwise>
			                                   		<a href="<html:rewrite page="/do/paymentrequest"/>" id="generateJsonButton">
				                                       <div class="span1" style="width:910px;">
			                                            <span class="aqua-shortcut text-align-center" style="width:900px;">
				                                                <span class="modernpics newline">$</span>
				                                                <span class="label label-important">Request <fmt:formatNumber value="${bitcoinsPending}" minFractionDigits="2" maxFractionDigits="8"/> BTC payment</span>
				                                            </span>
				                                        </div>
				                                    </a>
			                                   	</c:otherwise>
			                                </c:choose>
		                                </c:when>
		                                <c:when test="${bitcoinsPending > 0 && affiliationUserSession.askedForTransfer == true}">
	                                        <div class="span1" style="width:910px;">
	                                            <span class="aqua-shortcut text-align-center disabled" style="width:900px;">
	                                                <span class="modernpics newline">$</span>
	                                                <span class="label">Bitcoin payment requested</span>
	                                            </span>
	                                        </div>
		                                </c:when>
		                                
		                               
		                                
		                                
		                                <c:otherwise>
	                                        <div class="span1" style="width:910px;">
	                                            <span class="aqua-shortcut text-align-center" style="width:900px;">
	                                                <span class="modernpics newline">$</span>
	                                                <span class="label label-success">No Bitcoins pending for payment</span>
	                                            </span>
	                                        </div>
		                                </c:otherwise>
		                            </c:choose>
                                  
                                </div>
                                <div class="clear"></div>




                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

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


