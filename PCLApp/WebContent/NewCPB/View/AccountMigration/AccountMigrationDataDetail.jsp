<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>
<%@ page import="com.citibank.newcpb.enums.ScreenNamesEnum"%>
<%@ page import="com.citibank.newcpb.form.AccountMigrationDataForm"%>

<html:html>
	<head>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="GENERATOR" content="IBM Software Development Platform">
		<meta http-equiv="Content-Style-Type" content="text/css">
		
		<link href="<%=request.getContextPath()%>/Common/css/citi.css" rel="stylesheet" type="text/css">
		
		<jsp:include page="/NewCPB/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="AccountMigration" />
			<jsp:param name="gridId" value="gridTable" />
			<jsp:param name="headerId" value="gridHeader" />
			<jsp:param name="controlNames" value="'alterBtn','approvedBtn'" />
			<jsp:param name="approvalControlNames" value="'','','alterBtn'" />
		</jsp:include>
		<jsp:include page="/NewCPB/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<script type="text/javascript" src="<%= request.getContextPath() %>/Common/js/jquery.js"></script>
		
		<title>Contas - Inserir Contas Migradas (De-Para)</title>
	</head>
	<body>
		<html:form action="/NEWCPB.AccountMigration.do">
		
		<html:hidden property="selectRegisterAccountMigrate.accountGrbName"></html:hidden>
		<html:hidden property="selectRegisterAccountMigrate.accountCustodiaName"></html:hidden>
	
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="NewCPBSheet" />
				<jsp:param name="currentSubSheet" value="AccountMigration" />
			</jsp:include>
	
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"/>			
			<html:hidden property="clrScreen" value="true" />			
			<html:text property="idDiffList" disabled="true" style="display:none"></html:text>
			<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
	               String userId = user != null ? user.getUserID() : null;
	               AccountMigrationDataForm formBean = (AccountMigrationDataForm)session.getAttribute("AccountMigrationDataForm");
	         %>
			<table class="ODS_mainTable" cellspacing="0" width="100%">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">							
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">									
										<%=formBean.getScreenTitle()%>
									</th>
								</tr>
							</thead>
							
							</table>
							<table border="0" width="100%" >	
							<tbody>
							<tr><td colspan="3">&nbsp;</td></tr>
								<tr class="ODS_Detail_line1">
									<td>										
										<table border="0">
											<tr>
												<td>Conta Corrente Citibank* <p>(Conta encerrada que continha os ativos)&nbsp; </td>
												<td><html:text property="selectRegisterAccountMigrate.accountGrbNumber" styleClass="ODS_Text_Field_Size_10" disabled="<%=formBean.isOnlyView() || formBean.isEdit()%>"  onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" maxlength="15"></html:text></td>
												<td><html:button property="button" value="Consultar" onclick="searchAccuntGrbName();" disabled="<%=formBean.isOnlyView() %>"/></td>
												<td>&nbsp;&nbsp;</td>
												<td style="text-align: left;"><div id="grbNameTd"></div></td>
											</tr>
											
											<tr>
												<td colspan="5">
												</td>
											</tr>
																						
											<tr>
												<td>Conta Custódia* <p>(Nova conta onde estão os ativos)</td>
												<td><html:text property="selectRegisterAccountMigrate.accountCustodiaNumber" styleClass="ODS_Text_Field_Size_10" disabled="<%=formBean.isOnlyView() %>"   onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" maxlength="15"></html:text></td>
												<td><html:button property="button" value="Consultar" onclick="searchAccountCustodiaName();" disabled="<%=formBean.isOnlyView() %>"/></td>
												<td>&nbsp;&nbsp;</td>
												<td width="50%" style="text-align: left;"><div id="custodiaNameTd"></div></td>
											
											
											</tr>
											<tr>
												<td colspan="5">
												</td>
											</tr>
																						
											<tr>
												<td>Data da Migração* <p> (Data em que os ativos foram migrados)&nbsp;</td>
												<td colspan="3">
													<html:text styleClass="ODS_Text_Field_Size_10" property="selectRegisterAccountMigrate.migrationDateString" disabled="<%=formBean.isOnlyView() %>" onkeydown="maskDate();" maxlength="10" />
												</td>	
											</tr>	
											
											<tr>
												<td colspan="5">
												</td>
											</tr>
								
										</table>																		
									</td>
								</tr>
							</tbody>
						</table>	
					</td>
					
					
				<logic:equal name="AccountMigrationDataForm" property="approve" value="false" >
					<logic:equal name="AccountMigrationDataForm" property="onlyView" value="false" >
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td colspan="3">
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<tr>
											<td width="100%"></td>
											<TD>
											<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBContaInsContasMigradas()){ %>
												<html:button property="button" value="Salvar" onclick="submitActionNewCPB('save');"></html:button>
											<% }%>				
											</td>	
											
											<logic:equal name="AccountMigrationDataForm" property="fromApprove" value="false" >								
												<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('back');"></html:button></TD>
											</logic:equal>	
											
											<logic:equal name="AccountMigrationDataForm" property="fromApprove" value="true" >								
												<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('backApprove');"></html:button></TD>
											</logic:equal>	
											
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</logic:equal>
					
					<logic:equal name="AccountMigrationDataForm" property="onlyView" value="true" >
						
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td colspan="3">
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<tr>
											<td width="100%"></td>														
											
											
											<logic:equal name="AccountMigrationDataForm" property="fromApprove" value="false" >								
												<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('back');"></html:button></TD>
											</logic:equal>	
											
											<logic:equal name="AccountMigrationDataForm" property="fromApprove" value="true" >								
												<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('backApprove');"></html:button></TD>
											</logic:equal>	
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</logic:equal>
				</logic:equal>
				</tr>
				
				<logic:equal name="AccountMigrationDataForm" property="approve" value="true" >
					<tr><td colspan="3">&nbsp;</td></tr>
					<tr>
						<td colspan="3"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td>
					</tr>
					<tr><td colspan="3">&nbsp;</td></tr>
					<tr>
						<td colspan="3">
							<table width="100%">
								<tr class="ODS_Detail_Line1">
									<td width="33%">Usuário de Última Atualização</td>
									<td width="33%">Data/Hora de Última Atualização</td>
									<TD width="33%">Ação</TD>
								</tr>
								<tr class="ODS_Detail_Line2">
									<td width="33%"><html:text styleClass="ODS_Text_Field_Size_20" property="selectRegisterAccountMigrate.lastUpdUserSafe" disabled="true"></html:text></td>
									<td width="33%"><html:text styleClass="ODS_Text_Field_Size_20" property="selectRegisterAccountMigrate.lastUpdDateFormatedSafe" disabled="true"></html:text></td>
									<td width="33%"><html:text styleClass="ODS_Text_Field_Size_10" property="selectRegisterAccountMigrate.recStatCodeText" disabled="true"></html:text></TD>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="ODS_Detail_Line1"><td colspan="3">&nbsp;</td></tr>
					<tr class="ODS_Detail_Line2"><td colspan="3">&nbsp;</td></tr>
					<tr>
						<td colspan="3">
							<table class="ODS_internalWidth" border="0">
							<bean:define id="lastUpdUserId" name ="AccountMigrationDataForm" property="selectRegisterAccountMigrate.lastUpdUserSafe" type="java.lang.String"/>
								<tbody>
									<tr>
										<td width="100%"></td>		
										<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBAprovRej(ScreenNamesEnum.ACCOUNT_MIGRATED.getNome())){ %>								
											<logic:equal name="AccountMigrationDataForm" property="approve" value="true" >
												<logic:notEqual name="lastUpdUserId" value="<%=userId%>">
													<td><html:button property="approvedBtn" value="Aprovar" onclick="submitActionNewCPB('approve');"></html:button></td>																					
												</logic:notEqual>
												<td><html:button property="rejectBtn" value="Reprovar" onclick="submitActionNewCPB('reprove');"></html:button></td>	
											</logic:equal>
										<% } %>
										<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('backApprove');"></html:button></td>
									</TR>
								</tbody>
							</table>
						</td>
					</tr>
				</logic:equal>
			</table>		
		</html:form>	
		<jsp:include page="/View/Util/Footer.jsp" flush="true"/>	
	</body>	
	<jsp:include page="/View/Util/NoCacheIE.jsp" />
	
	<script type="text/javascript">

		loadAccountCustomerName();
		
		var input = document.getElementsByName('idDiffList')[0].value;
				
		if(input){
			var fields = input.substring(1).substring(0, input.length-2).replace(/\s/g, '').split(',');
			
			for (var i = 0; i < fields.length; i++) {
				if(document.getElementsByName('selectRegisterAccountMigrate.' + fields[i])){
					if(document.getElementsByName('selectRegisterAccountMigrate.' + fields[i])[0]){
						document.getElementsByName('selectRegisterAccountMigrate.' + fields[i])[0].classList.add("fieldDiff");
					}
					
					
					if(document.getElementById('selectRegisterAccountMigrate.' + fields[i])){
						document.getElementById('selectRegisterAccountMigrate.' + fields[i]).style.border="1px solid red";	
					}
				}
			}
		} 
		
		</script>
</html:html>