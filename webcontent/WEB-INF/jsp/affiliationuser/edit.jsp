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
	<tiles:put name='menu' content='menu.jsp'/>
	<tiles:put name='body' type='string'>

	<span style="font-family:Arial;font-size:18px;">Affiliation User Details</span>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr align="center">
			<td>
				<table style="width:80%;padding-top:5px;text-align: center;">
					<tr>
						<td>
							Give this url to anyone you want, put it in a banner, advertise it, etc..
						</td>
					<tr>
					<tr>
						<td style="font-size:20px;">
							http://www.instantri.ch?affiliate=<c:out value="${affiliationUser.name}"/>					
						</td>
					<tr>
					<tr>
						<td>
							If an user registered with your affilitaion code spends some bitcoins you'll get <c:out value="${affiliationUser.percentage}"/>% of them  
						</td>
					<tr>
				</table> 
			</td>
		</tr>
	
	
		<tr>
			<td align="center">
				
					<table width="70%" class="tab">
						
						<tr>
							<td>
								Created
							</td>
							<td>
								<fmt:setLocale value="en_US" /> 
								<fmt:formatDate value="${affiliationUser.created}" type="both" timeStyle="long" dateStyle="full" />
								
							</td>
						</tr>
					
						<tr>
							<td>
								Login
								
							</td>
							<td>
								<c:out value="${affiliationUser.login}"/>
							</td>
						</tr>
						<tr>
							<td>
								Password
							</td>
							<td>
								<a href="<html:rewrite page="/do/affiliationuser/password"/>">Change password</a>
							</td>
						</tr>
						<tr>
							<td>
								Name
							</td>
							<td>
								<c:out value="${affiliationUser.name}"/>
							</td>
						</tr>
						<tr>
							<td>
								Url
							</td>
							<td>
								<c:out value="${affiliationUser.url}"/>
							</td>
						</tr>
						<tr>
							<td>
								Email
							</td>
							<td>
								<c:out value="${affiliationUser.email}"/>
							</td>
						</tr>
						<tr>
							<td>
								Bitcoin Wallet Address
							</td>
							<td>
								<c:out value="${affiliationUser.bitcoinAddress}"/>
							</td>
						</tr>
						<tr>
							<td>
								Percentage
							</td>
							<td>
								<c:out value="${affiliationUser.percentage}"/> %
							</td>
						</tr>
						<tr>
							<td>
								Bitcoins transfer requested
							</td>
							<td>
								<c:if test="${affiliationUser.askedForTransfer}">
									Yes
								</c:if>
								<c:if test="${!affiliationUser.askedForTransfer}">
									No
								</c:if>
							</td>
						</tr>
						
					</table>
				
			</td>
		</tr>
		<tr align="center">
			<td>
				<table style="width:80%;padding-top:50px;text-align: center;">
					<tr>
						<td>
							For security reasons <strong>you cannot change any of this values</strong>. Only password can be changed.<br/>
							If you wish to modify your email adress, url, Bitcoin wallet address, etc... you need to send a message to the site admin so we can verify your identity.<br/>
							You can contact with admin clicking in <a href="<html:rewrite page="/do/affiliationcontact/edit"/>">Contact with admin</a>
							
						</td>
					<tr>
				</table> 
			</td>
		</tr>
		
		<tr>
			<td height="50px">
			</td>
		</tr>
		
		<tr>
			<td align="center"  style="font-family:Arial;font-size:18px;">
				Received Messages from admin
			</td>
		</tr>
		<tr>
			<td align="center">
				<table width="70%" class="tab">
					<tr>
						<td style="background-color: #303030;color:white;">
							<strong>Created</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Subject</strong>
						</td>
						<td style="background-color: #303030;color:white;">
							<strong>Message</strong>
						</td>
					</tr>
					<c:if test="${empty affiliationUserMessageList}">
						<tr>
							<td colspan="3">
								There are no messages
							</td>
						</tr>
					</c:if>
					<c:forEach items="${affiliationUserMessageList}" var="affiliationUserMessage">
						<tr>
							<td>
								<fmt:formatDate value="${affiliationUserMessage.created}" type="both" timeStyle="short" dateStyle="full" />
							</td>
							<td>
								<c:out value="${affiliationUserMessage.subject}"/>
							</td>
							<td>
								<c:out value="${affiliationUserMessage.message}"/>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


