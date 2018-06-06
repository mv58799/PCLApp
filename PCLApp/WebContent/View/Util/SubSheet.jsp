<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>

	<table class="ODS_mainTable" border="0">
		<tr align="left">
			<td>
				<logic:equal  parameter="currentSheet" value="CustomerSheet">				
					<table width="60%" >
						<tr>
							<td align="left">
								<jsp:include page="CustomerSubSheet.jsp"/></td> 
						</tr>
					</table>
				</logic:equal>

				<logic:equal parameter="currentSheet" value="ProductSheet">				
					<table width="60%" >
						<tr>
							<td align="left">
								<jsp:include page="ProductSubSheet.jsp"/></td>
						</tr>
					</table>
				</logic:equal>
				<% User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID); 
				
					if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessMenuNovoCPB()){						
						%>
							<logic:equal parameter="currentSheet" value="NewCPBSheet">				
								<table width="60%" >
									<tr>
										<td align="left">
											<jsp:include page="/NewCPB/View/Util/NewCPBSubSheet.jsp"/></td>
									</tr>
								</table>
							</logic:equal>
						
						<%						
					}				
				%>
				
			</td>
		</tr>
	</table>
	<table>
	<tr>
		<td>
		</td>
	</tr>
</table>


