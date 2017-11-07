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
                <i class="modernpics icons32">g</i>
                <span class="panel-divider"></span>
                <h2>Registered Users<span></span></h2>
                <div class="aqua-panel-tabs-icons pull-right">
                    <a href="#" class="minimize">--</a>
                    <a href="#" class="modernpics maximize">v</a>
                </div>
            </div>
            <div class="aqua-panel-content">
                <div id="user-grid" class="grid-view">
                  

                    
                    <c:if test="${pageCount > 1 }">
                        <div class="summary">Displaying 
                        	<c:out value="${((page - 1) * 40) + 1}"/>-<c:if test="${page == pageCount}"><c:out value="${registeredUserCount}"/></c:if><c:if test="${page != pageCount}"><c:out value="${((page - 1) * 40) + 41}"/></c:if>of <c:out value="${registeredUserCount}"/> results. (Last is first)
                        </div>
                        <div class="pagination">
                            <ul id="yw2" class="yiiPager">
                                <li class="previous <c:if test="${page == 1}">disabled</c:if>">
                                    
                                    <c:if test="${page != 1 }">
                                        <a href="<html:rewrite page="/do/registeredusers/list"/>?page=<c:out value="${page - 1}"/>">&larr;</a>
                                    </c:if>
                                    <c:if test="${page == 1 }">
                                        <a>&larr;</a>
                                    </c:if>
                                </li>
                                <%
                                	int pageCount = (Integer)request.getAttribute("pageCount");
                                	for( int i=1;i<=pageCount;i++){
                                		request.setAttribute("i", i);
                                %>
                                
                                <li <c:if test="${page == i}"> class="active"</c:if>>
                                    <c:if test="${page != i}">
                                       <a href="<html:rewrite page="/do/registeredusers/list"/>?page=<c:out value="${i}"/>"><c:out value="${i}"/></a>
                                    </c:if>
                                    <c:if test="${page == i}">
                                        <a><c:out value="${i}"/></a>
                                    </c:if>
                                </li>
                                <%} %>
                                <li class="next  <c:if test="${page == pageCount}">disabled</c:if>">
                                    
                                    <c:if test="${page != pageCount }">
                                        <a href="<html:rewrite page="/do/registeredusers/list"/>?page=<c:out value="${page + 1}"/>">&rarr;</a>
                                    </c:if>
                                    <c:if test="${page == pageCount }">
                                        <a>&rarr;</a>
                                    </c:if>
                                </li>
                            </ul>
                        </div>
                    </c:if>
                    <table class="items table table-striped table-bordered table-condensed">
                        <thead>
                            <tr>
                                <th id="user-grid_c0">
                                    ID
                                </th>
                                <th id="user-grid_c1">
                                   Login
                                </th>
                                <th id="user-grid_c2">
                                    Created
                                </th>
                                <th id="user-grid_c2">
                                    Validated
                                </th>
                                 <th id="user-grid_c2">
                                    Fraudulent
                                </th>
                                <th id="user-grid_c2">
                                    Payments
                                </th>
                                 <th id="user-grid_c2">
                                    BTC payed by user
                                </th>
                                 <th id="user-grid_c2">
                                    BTC assigned to me
                                </th>
                            </tr>
                           
                        </thead>
                        <tbody>
                         <c:if test="${empty registeredUserDataList}">
	                        <tr style="height: 100px;">
	                                <td colspan="8" style="text-align: center;vertical-align: middle;font-size: 16px;"> There is users yet</td>
	                                
	                        </tr>
                        </c:if>
                        <c:forEach items="${registeredUserDataList}" var="registeredUserData" varStatus="index">
                        	<c:choose>
                        		<c:when test="${registeredUserData.totalBitcoins == 0}">
                        			<tr>
                        		</c:when>
                        		<c:when test="${registeredUserData.totalBitcoins != 0}">
                        			<tr style="font-weight:bold;">
                        		</c:when>
                        	</c:choose>
                                <td <c:if test="${index.index % 2 == 0}">style="background-color:#e9e9e9;"</c:if>><c:out value="${registeredUserData.id }"/> </td>
                                <td <c:if test="${index.index % 2 == 0}">style="background-color:#e9e9e9;"</c:if>><c:out value="${registeredUserData.login }"/></td>
                                <td <c:if test="${index.index % 2 == 0}">style="background-color:#e9e9e9;"</c:if>><c:out value="${registeredUserData.created }"/></td>
                                <td <c:if test="${index.index % 2 == 0}">style="background-color:#e9e9e9;"</c:if>>
                                	<c:if test="${registeredUserData.validated }">
                                		<span class="label label-success">Yes</span>
                                	</c:if>
                                	<c:if test="${!registeredUserData.validated }">
                                		<span class="label label-important">No</span>
                                	</c:if>
                                </td>
                                <td <c:if test="${index.index % 2 == 0}">style="background-color:#e9e9e9;"</c:if>>
                                	<c:if test="${registeredUserData.fraudulent}">
                                		<span class="label label-important">Yes</span>
                                	</c:if>
                                	<c:if test="${!registeredUserData.fraudulent}">
                                		<span class="label label-success">No</span>
                                	</c:if>
                                </td>
                                
                                <td <c:if test="${index.index % 2 == 0}">style="background-color:#e9e9e9;"</c:if>><c:out value="${registeredUserData.payments }"/></td>
                                <td <c:if test="${index.index % 2 == 0}">style="background-color:#e9e9e9;"</c:if>><fmt:formatNumber value="${registeredUserData.totalBitcoins }" minFractionDigits="2" maxFractionDigits="8"/></td>
                                <td <c:if test="${index.index % 2 == 0}">style="background-color:#e9e9e9;"</c:if>><fmt:formatNumber value="${registeredUserData.assignedBitcoins }" minFractionDigits="2" maxFractionDigits="8"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${pageCount > 1 }">
                        <div class="summary">Displaying 
                        	<c:out value="${((page - 1) * 40) + 1}"/>-<c:if test="${page == pageCount}"><c:out value="${registeredUserCount}"/></c:if><c:if test="${page != pageCount}"><c:out value="${((page - 1) * 40) + 41}"/></c:if>of <c:out value="${registeredUserCount}"/> results.
                        </div>
                        <div class="pagination">
                            <ul id="yw2" class="yiiPager">
                                <li class="previous <c:if test="${page == 1}">disabled</c:if>">
                                    
                                    <c:if test="${page != 1 }">
                                        <a href="<html:rewrite page="/do/registeredusers/list"/>?page=<c:out value="${page - 1}"/>">&larr;</a>
                                    </c:if>
                                    <c:if test="${page == 1 }">
                                        <a>&larr;</a>
                                    </c:if>
                                </li>
                                <%
                                	int pageCount = (Integer)request.getAttribute("pageCount");
                                	for( int i=1;i<=pageCount;i++){
                                		request.setAttribute("i", i);
                                %>
                                
                                <li <c:if test="${page == i}"> class="active"</c:if>>
                                    <c:if test="${page != i}">
                                       <a href="<html:rewrite page="/do/registeredusers/list"/>?page=<c:out value="${i}"/>"><c:out value="${i}"/></a>
                                    </c:if>
                                    <c:if test="${page == i}">
                                        <a><c:out value="${i}"/></a>
                                    </c:if>
                                </li>
                                <%} %>
                                <li class="next  <c:if test="${page == pageCount}">disabled</c:if>">
                                    
                                    <c:if test="${page != pageCount }">
                                        <a href="<html:rewrite page="/do/registeredusers/list"/>?page=<c:out value="${page + 1}"/>">&rarr;</a>
                                    </c:if>
                                    <c:if test="${page == pageCount }">
                                        <a>&rarr;</a>
                                    </c:if>
                                </li>
                            </ul>
                        </div>
                    </c:if>



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


