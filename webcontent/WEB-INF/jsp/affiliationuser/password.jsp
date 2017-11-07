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

	<span style="font-family:Arial;font-size:18px;">Affiliation User Change Password</span>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" valign="top">
		<tr>
			<td align="center">
				<html:form action="/affiliationuser/passwordsave">
					<table width="70%" class="tab">
						<tr>
							<td>
								Previous Password
								<span style="color:red;"><html:errors property="previousPassword"/></span>
							</td>
							<td>
								<html:password property="previousPassword" />
							</td>
						</tr>
						<tr>
							<td>
								New Password
								<span style="color:red;"><html:errors property="password"/></span>
							</td>
							<td>
								<html:password property="password" />
							</td>
						</tr>
						<tr>
							<td>
								Repeat New Password
								<span style="color:red;"><html:errors property="repeatPassword"/></span>
							</td>
							<td>
								<html:password property="repeatPassword" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="button" value="Cancel" onclick="document.location.href='<html:rewrite page="/do/affiliationuser/edit"/>'"/>
								<input type="submit" value="Ok" />
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>

</tiles:put>
</tiles:insert>


