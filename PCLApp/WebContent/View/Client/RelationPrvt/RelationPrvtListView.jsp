<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>

<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/Common/css/citi.css">

<script language="javascript">
	function extraActions(action)
	{
		if (action =='CustomerPrvt.CustomerPrvtList')
		{
			document.forms[0].action = "CustomerPrvt.CustomerPrvtList.List.Show";
			document.forms[0].backURL.disabled = false;
		}
		else if (action == 'Contract.CurAccountList')
		{
			document.forms[0].action = "Contract.CurAccountList.List.Show";
	  		document.forms[0].backURL.disabled = false;
		}
		else if(action== 'clearPage')
		{
			document.forms[0].action = "RelationPrvt.RelationPrvtList.List.ClearPage";
			document.forms[0].backURL.disabled = true;	
		}
	}																	
</script>

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="RelationPrvt.RelationPrvtList" />
	<jsp:param name="gridId" value="gridTable" />
	<jsp:param name="headerId" value="gridHeader" />
	<jsp:param name="controlNames" value="'detailBtn'" />
    <jsp:param name="searchInputFields" value="'reltnNbrSrc','custNbrSrc','curAcctNbrSrc'" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Consulta de Relacionamentos Private</TITLE>
</HEAD>
<body>
<html:form action="/RelationPrvt.RelationPrvtList.List.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Relacionamentos" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>
	<html:hidden property="selectedReltnNbr" value="" />

	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<html:text property="backURL" value="RelationPrvt.RelationPrvtList.List.Show" style="display:none"></html:text>
			<td>
				<table class="ODS_internalWidth" border="0">
					<thead><tr height="40">	<th class="subtitle" scope="colgroup" colspan="6">Consulta de Relacionamentos Private</th></tr></thead>
					<tbody>
					<tr class="ODS_Detail_Line1">
						<TD width="18%">Nro. Cliente</TD>
						<TD colspan="5"><html:text property="custNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text></TD>
					</tr>
					<tr class="ODS_Detail_Line1">
						<TD width="18%">Nome do Cliente</TD>
						<TD colspan="5"><html:text property="custFullNameTextSrc"styleClass="ODS_Text_Field_Size_60"></html:text>&nbsp;&nbsp;<html:button property="getBtn" value="Buscar" onclick="submitAction('PreparedSearch.CustomerPrvt.CustomerPrvtList');"></html:button>
						</TD>
					</tr>
					<tr class="ODS_Detail_Line2">
						<td width="18%">Nro. Relacionamento</TD>
						<TD><html:text property="reltnNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text></TD>
						<TD width="20%" align="right">Nro. Conta Corrente&nbsp;&nbsp;</TD>
						<TD><html:text property="curAcctNbrSrc" styleClass="ODS_Text_Field_Size_15" maxlength="15" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNNNNNN','left',null)"></html:text>&nbsp;&nbsp;<html:button property="getBtn" value="Buscar" onclick="submitAction('PreparedSearch.Contract.CurAccountList');"></html:button></TD>
						<TD width="5%">&nbsp;</TD>
						<TD width="15%"><html:checkbox property="ownerSelectedSrc" value="S" styleClass="checkBox" >Titular</html:checkbox></TD>
					</tr>
				</tbody>
			</TABLE>

			<table class="ODS_internalWidth" border="0">
				<tbody>
					<TR>
						<TD width="100%"></TD>
						<TD><html:button property="listBtn" value="Consultar" onclick="submitAction('list');"></html:button></TD>
						<TD><html:button property="detailBtn" value="Detalhar" disabled="true" onclick="submitAction('detail');"></html:button></TD>
						<TD><html:button property="clearBtn" value="Limpar" onclick="clearResultSetInServer();submitAction('clearPage');"></html:button></TD>
					</TR>
				</tbody>
			</TABLE>

			<table class="ODS_internalWidth" border="0">
				<thead><tr><th class="subtitle" scope="colgroup" colspan="3" width="100%">Resultado da Consulta</th></tr></thead>
				<tr>
					<td>
						<DIV class=ODS_DivGridVertical>
							<table class="ODS_internalWidth" id="gridTable" border="0">
								<tbody>
									<tr id="gridHeader" class="fixed">
										<TH class="ODS_header" width="3%">&nbsp;</TH>
										<TH class="ODS_header" width="30%">Nome do Titular</TH>
										<TH class="ODS_header" width="15%">Número do Titular</TH>
										<TH class="ODS_header" width="15%">Conta Corrente Titular</TH>
										<TH class="ODS_header" width="15%">Número do Relacionamento</TH>
										<TH class="ODS_header" width="7%">Nível de Risco</TH>
									</tr>
									<ods:DataSetRows name="RelationPrvtListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
										<logic:equal name="step" value="0">
											<tr class="ODS_line1">
										</logic:equal>
										<logic:equal name="step" value="1">
											<tr class="ODS_line2"> 
										</logic:equal>
		    							<bean:define name="resultRow" property="bigDecimalByName(RELTN_NBR)" id="selectedReltnNbr" type="java.math.BigDecimal"></bean:define>
										<td class="centralized" width="3%" align="center">
											<input type="radio" class="radio" name="selection" onclick="<%="javascript:selectedReltnNbr.value=" + selectedReltnNbr + ";disableButtons(false);"%>"></td>
										<td width="30%" align="left"><a class="ODS_CursorHand" href="#" onclick="<%="javascript:selectedReltnNbr.value=" + selectedReltnNbr + ";submitAction('detail');"%>"><bean:write name="resultRow" property="stringByName(CUST_FULL_NAME_TEXT)" /></a></td>
										<td width="15%" align="right"><bean:write name="resultRow" property="stringByName(RELTN_CUST_1_NBR)"/></td>
										<td width="15%" align="right"><bean:write name="resultRow" property="stringByName(CUR_ACCT_NBR)"/></td>
										<td width="15%" align="right"><bean:write name="resultRow" property="stringByName(RELTN_NBR)"/></td>
										<td width="7%" align="center"><bean:write name="resultRow" property="stringByName(RELTN_RISK_LEVEL_CODE)"/></td>
									</ods:DataSetRows>
								</tbody>
							</table>
						</DIV>
					</td>
				</tr>
			</table>

			<TABLE class="ODS_internalWidth" border="0">
				<TBODY>
					<TR>
						<td width="100%"></td>
						<TD><html:button property="backBtn" value="Voltar"
							onclick="submitAction('back');"></html:button></TD>
					</TR>
				</TBODY>
			</TABLE>
		</td>
	</tr>
</table>
</html:form> <jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
