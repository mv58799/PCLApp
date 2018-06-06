<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

		<script language="javascript">
			function extraActions(action){
				if (action =='RelationPrvt.RelationPrvtList')
				{
					document.forms[0].action = "RelationPrvt.RelationPrvtList.List.Show";
					document.forms[0].backURL.disabled = false;
				}
				else if (action =='CustomerPrvt.CustomerPrvtList')
				{
					document.forms[0].action = "CustomerPrvt.CustomerPrvtList.List.Show";
					document.forms[0].backURL.disabled = false;
				}
				else if (action =='Contract.CurAccountList')
				{
					document.forms[0].action = "Contract.CurAccountList.List.Show";
					document.forms[0].backURL.disabled = false;
				}
			}; 	

			function disabledFieldSubmit(status){
				document.forms[0].custNbrSrc.disabled = status;
				document.forms[0].custFullNameTextSrc.disabled = status;
			};																	
		</script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">	
			<jsp:param name="pageName" value="EREM.EREMMovementList" />
			<jsp:param name="gridId" value="gridTable" />
			<jsp:param name="headerId" value="gridHeader" />
			<jsp:param name="controlNames" value="'alterBtn','approvedBtn'"/>
			<jsp:param name="approvalControlNames" value="'','','alterBtn'"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Consulta de Associação ER x EM com Pendência de Aprovação</TITLE>
	</HEAD>

	<body>
		<html:form action="/EREM.EREMMovementList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação" />
		    </jsp:include>
			<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="EREM.EREMMovementList.List.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead><tr><th class="subtitle" scope="colgroup" colspan="4">Consulta de Associação ER x EM com Pendência de Aprovação</th></tr></thead>
								<tbody>
									<tr class="ODS_Detail_Line1">
										<TD width="15%">Nro. ER</TD>
										<TD width="30%"><html:text property="erNbrSrc" styleClass="ODS_Text_Field_Size_20" maxlength="30"></html:text></TD>
										<TD width="20%">Nro. Relacionamento</TD>
										<TD width="35%"><html:text property="reltnNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text>&nbsp;&nbsp;<html:button property="getBtn" value="Buscar" onclick="submitAction('PreparedSearch.RelationPrvt.RelationPrvtList');"></html:button></TD>
									</tr>
									<tr class="ODS_Detail_Line2">
										<TD width="15%">Nro. EM</TD>
										<TD width="30%"><html:text property="emNbrSrc" styleClass="ODS_Text_Field_Size_20" maxlength="30"></html:text></TD>
										<TD width="20%">Nro. Conta Corrente</TD>
										<TD width="35%"><html:text property="curAcctNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="15" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNNNNNN','left',null)"></html:text>&nbsp;&nbsp;<html:button property="getBtn" value="Buscar" onclick="submitAction('PreparedSearch.Contract.CurAccountList');"></html:button></TD>
									</tr>
									<tr class="ODS_Detail_Line1">
			    						<TD width="15%">Nro. Cliente&nbsp;</TD>
										<TD colspan="3"><html:text property="custNbrSrc" styleClass="ODS_Text_Field_Size_15" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text>&nbsp;&nbsp;
											<html:button property="getBtn" value="Buscar" onclick="submitAction('PreparedSearch.CustomerPrvt.CustomerPrvtList');"></html:button>
										</TD>
									</tr>
									<tr class="ODS_Detail_Line1">
			    						<td width="15%" >Nome do Cliente&nbsp;</td>
										<td colspan="3"><html:text property="custFullNameTextSrc" styleClass="ODS_Text_Field_Size_60" maxlength="60"></html:text>&nbsp;&nbsp;
											<html:button property="getBtn" value="Buscar" onclick="submitAction('PreparedSearch.CustomerPrvt.CustomerPrvtList');"></html:button>
										</TD>											
									</tr>
									<tr class="ODS_Detail_Line2">
										<td colspan="4">
											<table><tr>									
												<TD>Usuário de Última Atualização&nbsp;</TD>
												<TD><html:text property="lastUpdUserIdSrc" styleClass="ODS_Text_Field_Size_20" maxlength="20"/></TD>
											</tr></table>
										</td>
									</tr>
								</tbody>
							</TABLE>

							<table class="ODS_internalWidth" border="0">
								<tbody>
									<TR>
										<TD width="100%"></TD>
										<TD><html:button property="listBtn" value="Consultar" onclick="disabledFieldSubmit(false);submitAction('list');disabledFieldSubmit(true);"></html:button></TD>
										<TD><html:button property="approvedBtn" disabled="true" value="Aprovação" onclick="submitAction('approve');"></html:button></TD>
										<TD><html:button property="alterBtn" value="Alterar" onclick="submitAction('update');" disabled="true"></html:button></TD>
										<TD><html:button property="clearBtn" value="Limpar" onclick="clearAllPage();"></html:button></TD>
									</TR>
								</tbody>
							</TABLE>
		
							<html:hidden property="selectedERNbr"/>
							<html:hidden property="selectedEMNbr"/>
		
							<table class="ODS_internalWidth" border="0" id="gridTable">
								<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th></tr></thead>
									<tbody>
										<tr id="gridHeader" class="fixed">
											<TH class="ODS_header" width="5%"></TH>
											<TH class="ODS_header" width="25%">Nome do Cliente</TH>
											<TH class="ODS_header" width="20%">Papel do Cliente no Relacionamento</TH>
											<TH class="ODS_header" width="18%">Número do EM</TH>
											<TH class="ODS_header" width="18%">Número do ER</TH>
											<TH class="ODS_header" width="14%">Usuário de Última Atualização</TH>
											<TH class="ODS_header" width="10%">Data/Hora de Última Atualização</TH>
											<TH class="ODS_header" width="5%">Ação</TH>
										</tr>

										<ods:DataSetRows name="EREMMovementListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
											<bean:define name="resultRow" property="stringByName(ER_NBR)" id="selectedERNbr" type="java.lang.String"></bean:define>
											<bean:define name="resultRow" property="stringByName(EM_NBR)" id="selectedEMNbr" type="java.lang.String"></bean:define>
											<bean:define name="resultRow" property="stringByName(LAST_UPD_USER_ID)" id="lastUpdUserId" type="java.lang.String"></bean:define>
											<bean:define name="resultRow" property="stringByName(OPERN_CODE)" id="opernCode" type="java.lang.String"></bean:define>
											<TD width="5%"><input type="radio" class="radio" name="selection" onclick="javascript:selectedEMNbr.value='<%=selectedEMNbr%>';selectedERNbr.value='<%=selectedERNbr%>';disableButtons(false); disableApproveButtons('<%= lastUpdUserId %>','<%= opernCode %>','true');"/></td>												
											<TD width="25%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedEMNbr.value='<%=selectedEMNbr%>';selectedERNbr.value='<%=selectedERNbr%>';submitAction('approve');" ><bean:write name="resultRow" property="stringByName(CUST_FULL_NAME_TEXT)"/></a></TD>
											<TD width="20%"><bean:write name="resultRow" property="stringByName(ROLE_CUST_TEXT)"/></td>
											<TD width="18%" align="right"><bean:write name="resultRow" property="stringByName(EM_NBR)"/></td>
											<TD width="18%" align="right"><bean:write name="resultRow" property="stringByName(ER_NBR)"/></td>
											<TD width="14%"><bean:write name="resultRow" property="stringByName(LAST_UPD_USER_ID)"/></td>
											<TD width="10%"><bean:write name="resultRow" property="dateByName(LAST_UPD_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/></td>
											<TD width="4%"><bean:write name="resultRow" property="stringByName(OPERN_TEXT)"/></td>
										</ods:DataSetRows>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>

						<table class="ODS_internalWidth" border="0">
							<tbody>
								<TR>
									<TD width="100%"></TD>
									<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
								</TR>
							</tbody>
						</TABLE>
					</td>
				</tr>
			</table>
			<jsp:include page="/View/Util/Footer.jsp" flush="true"/>			
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>

