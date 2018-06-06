<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods" %>
<%@ page import="com.citibank.ods.common.util.DefStaticVars"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>

<table class="ODS_mainTable" border="0">
	<tr>
		<td>
		<table class="ODS_internalWidth" border="0">
			<tr>
				<td width="80%"><IMG class="logo"
					src="../Common/image/cpb_signature_only_grey_red139x17.jpg"></td>
				<td width="20%" align="right"><img class="logo"
					src="../Common/image/citi_2c_gry_pos_rgb120x83.jpg"></td>
			</TR>
			<TR>
			<td colspan="2" align="right">
			  <table class="ODS_internalWidth" border="0">
			   <tr>
			   <%			   	
			   	String version = DefStaticVars.getInstance().getVersion;
			   %>
			   <td align="left"><span class="ODS_Gray_Bold"><%=version%></span></td>
				<td  align="right"><span class="ODS_Sheet_Link"
					onclick="javascript:window.location = 'http://portal.privatebank.citigroup.net/'"><img
					border="0" src="<%= request.getContextPath() %>/Common/image/dot1.gif">Intranet Private
				Global </span> <span class="ODS_Sheet_Link"
					onclick="javascript:window.location = 'http://www.citi.net/EN/Pages/business_privatebank_index.aspx?src=/EN/latinamerica/brazil'"><img
					border="0" src="<%= request.getContextPath() %>/Common/image/dot1.gif">Intranet Private
				Brasil </span> <span class="ODS_Sheet_Link"
					onclick="javascript:window.location = 'http://1842citi.br.citicorp.com/citipublisher/Private/' "><img
					border="0" src="<%= request.getContextPath() %>/Common/image/dot1.gif">Help Me </span>
				<span class="ODS_Sheet_Link"
					onclick="document.forms[0].action='./ODSMenu.Show';document.forms[0].submit();"><img
					border="0" src="<%= request.getContextPath() %>/Common/image/dot1.gif">Página
				Inicial </span> <span class="ODS_Sheet_Link"
					onclick="document.forms[0].action='./ODSLogoff.Show';document.forms[0].submit();"><img
					border="0" src="<%= request.getContextPath() %>/Common/image/dot1.gif">Sair </span>
				</td>
			</tr>
			</table>					
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right"><span class="ODS_Gray_Bold"><ods:WelcomeTag></ods:WelcomeTag><ods:DateAndTimeTag></ods:DateAndTimeTag></span></td>
			</tr>
		</table>

		<logic:equal parameter="currentSheet"	value="CustomerSheet">
			<table class="ODS_internalWidth" border="0" cellspacing="0">
				<tr>
					<td>
					<table border="0" cellspacing="0">
						<tr>
							<td height="1" width="1"></td>
							<td><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_top.gif"
								width="100%" height="1"></td>
							<td height="1" width="1"></td>

							<td height="1" width="1"></td>
							<td><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_top_grey.gif"
								width="100%" height="1"></td>
							<td height="1" width="1"></td>
							
							<td height="1" width="1"></td>
							<td><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_top_grey.gif"
								width="100%" height="1"></td>
							<td height="1" width="1"></td>
						</tr>

						<tr>
							<td align="center" height="28" width="1"><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_l_start.gif"></td>
							<td align="center" width="60"><span class="ODS_Sheet_Link"
								onclick="document.forms[0].action='./ODSMenuClient.Show';document.forms[0].submit();">Cliente</span></td>
							<td align="center" height="28" width="1"><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_r.gif"></td>

							<td align="center" height="28" width="1"><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_l_grey.gif"></td>
							<TD class="ODS_line" align="center" width="60" height="28"><span
								class="ODS_Sheet_Link_Disabled"
								onclick="document.forms[0].action='./ODSMenuProducts.Show';document.forms[0].submit();">Produto</span></TD>
							<td align="center" height="28" width="1"><IMG border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_r_grey.gif"></td>
								
							<% User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID); 
							
								if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessMenuNovoCPB()){						
									%>
							<td align="center" height="28" width="1"><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_l_grey.gif"></td>
							<TD class="ODS_line" align="center" width="60" height="28"><span
								class="ODS_Sheet_Link_Disabled"
								onclick="document.forms[0].action='./ODSMenuNewCPB.Show';document.forms[0].submit();">Novo&nbsp;CPB</span></TD>
							<td align="center" height="28" width="1"><IMG border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_r_grey_end.gif"></td>
								<%						
								}				
							%>
						</tr>
					</table>
					</td>
					<td class="ODS_line" width="100%">&nbsp;</td>
					<html:hidden property="currentSheet"  value="CustomerSheet"/>	
				</tr>
			</table>
		</logic:equal> 
		
		<logic:equal parameter="currentSheet" value="ProductSheet">

			<table class="ODS_internalWidth" border="0" cellspacing="0">
				<tr>
					<td>
					<table border="0" cellspacing="0">
						<tr>
							<td height="1" width="1"></td>
							<td><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_top_grey.gif"
								width="100%" height="1"></td>
							<td height="1" width="1"></td>

							<td height="1" width="1"></td>
							<td><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_top.gif" width="100%"
								height="1"></td>
							<td height="1" width="1"></td>
							
							<td height="1" width="1"></td>
							<td><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_top_grey.gif"
								width="100%" height="1"></td>
							<td height="1" width="1"></td>
						</tr>

						<tr>
							<td align="center" height="28" width="1"><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_l_grey_start.gif"></td>
							<td class="ODS_line" align="center" width="60"><span
								class="ODS_Sheet_Link_Disabled"
								onclick="document.forms[0].action='./ODSMenuClient.Show';document.forms[0].submit();">Cliente</span></td>
							<td align="center" height="28" width="1"><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_r_grey2.gif"></td>

							<td align="center" height="28" width="1"><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_l.gif"></td>
							<td align="center" width="60"><span class="ODS_Sheet_Link"
								onclick="document.forms[0].action='./ODSMenuProducts.Show';document.forms[0].submit();">Produto</span></td>
							<td align="center" height="28" width="1"><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_r.gif"></td>

								
							<% User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID); 
							
								if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessMenuNovoCPB()){						
									%>
							<td align="center" height="28" width="1"><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_l_grey.gif"></td>
								
								
							<td class="ODS_line" align="center" width="60"><span 
							class="ODS_Sheet_Link_Disabled"
								onclick="document.forms[0].action='./ODSMenuNewCPB.Show';document.forms[0].submit();">Novo&nbsp;CPB</span></td>
							<td align="center" height="28" width="1"><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_r_grey_end.gif"></td>
								<%						
								}				
							%>
								
								
						</tr>
					</table>
					</td>
					<td class="ODS_line" width="100%">&nbsp;</td>
				<html:hidden property="currentSheet"  value="ProductSheet"/>	
				</tr>
			</table>
		</logic:equal>
		
		<logic:equal parameter="currentSheet" value="NewCPBSheet">

			<table class="ODS_internalWidth" border="0" cellspacing="0">
				<tr>
					<td>
					<table border="0" cellspacing="0">
						<tr>
							<td height="1" width="1"></td>
							<td><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_top_grey.gif"
								width="100%" height="1"></td>
							<td height="1" width="1"></td>

							<td height="1" width="1"></td>
							<td><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_top_grey.gif"
								width="100%" height="1"></td>
							<td height="1" width="1"></td>
							
							<td height="1" width="1"></td>
							<td><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_top.gif" width="100%"
								height="1"></td>
							<td height="1" width="1"></td>
						</tr>

						<tr>
								
							<td align="center" height="28" width="1"><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_l_grey_start.gif"></td>
							<td class="ODS_line" align="center" width="60"><span
								class="ODS_Sheet_Link_Disabled"
								onclick="document.forms[0].action='./ODSMenuClient.Show';document.forms[0].submit();">Cliente</span></td>
							<td align="center" height="28" width="1"><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_r_grey.gif"></td>

							<td align="center" height="28" width="1"><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_l_grey.gif"></td>
							<TD class="ODS_line" align="center" width="60" height="28"><span
								class="ODS_Sheet_Link_Disabled"
								onclick="document.forms[0].action='./ODSMenuProducts.Show';document.forms[0].submit();">Produto</span></TD>
							<td align="center" height="28" width="1"><IMG border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_r_grey2.gif"></td>
								
							<% User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID); 
							
								if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessMenuNovoCPB()){						
									%>
							<td align="center" height="28" width="1"><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_l.gif"></td>
							<td align="center" width="60"><span class="ODS_Sheet_Link"
								onclick="document.forms[0].action='./ODSMenuNewCPB.Show';document.forms[0].submit();">Novo&nbsp;CPB</span></td>
							<td align="center" height="28" width="1"><img border="0"
								src="<%= request.getContextPath() %>/Common/image/tab_r_end.gif"></td>
								<%						
								}				
							%>
						</tr>
						
						
					</table>
					</td>
					<td class="ODS_line" width="100%">&nbsp;</td>
				<html:hidden property="currentSheet"  value="NewCPBSheet"/>	
				</tr>
			</table>
		</logic:equal></td>
	</tr>
</table>


