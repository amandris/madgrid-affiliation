<%@page import="com.madgrid.model.AdminUser"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>



<header>
    <div  id="aqua-menu" data-offset-top="80" data-spy="affix" class="subnav navbar affix">
        <div class="aqua-container">
            
            <ul id="yw3" class="nav">
                <li><a href="<html:rewrite page="/do/dashboard"/>"><i class="icon-home icon-white"></i> </a></li>
                <li><a href="<html:rewrite page="/do/registeredusers/list"/>"><i class="modernpics icons24 icons-white">g</i> Registered users</a></li>
                <li><a href="<html:rewrite page="/do/creditspurchased/list"/>"><i class="modernpics icons24 icons-white">#</i> Credits purchased</a></li>
                <li><a href="<html:rewrite page="/do/payment/list"/>"><i class="modernpics icons24 icons-white">$</i> Payments</a></li>
                <li><a href="<html:rewrite page="/do/banners"/>"><i class="modernpics icons24 icons-white">D</i> Banners</a></li>
                <li><a href="<html:rewrite page="/do/messagesreceived/list"/>"><i class="modernpics icons24 icons-white">m</i> Messages</a></li>
                <li><a href="<html:rewrite page="/do/profile/edit"/>"><i class="modernpics icons24 icons-white">f</i> Profile</a></li>
                <li><a href="<html:rewrite page="/do/affiliationuser/logoff"/>"><i class="modernpics icons24 icons-white"></i> Logout</a></li>
            </ul>
        </div>
    </div>
</header>

