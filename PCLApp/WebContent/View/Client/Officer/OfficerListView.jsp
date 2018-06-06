
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
<script language=javascript>
	function extraActions(action){

		if ( action == 'listHistory' ){
			document.forms[0].action = "OfficerCmpl.OfficerCmplHistoryList.List.Show";
			document.forms[0].backURL.disabled = false; 
		}
		else if ( action == 'officerCmplDelete' ){
			document.forms[0].action = "OfficerCmpl.OfficerCmplDetail.Delete.Show";
			document.forms[0].backURL.disabled = false; 
		}
		else if ( action == 'officerCmpl' ){
			document.forms[0].action = "OfficerCmpl.OfficerCmplDetail.Insert.Show";
			document.forms[0].backURL.disabled = false; 
		}
		else if(action== 'clearPage'){
			document.forms[0].action = "Officer.OfficerList.List.ClearPage";
			document.forms[0].backURL.disabled = true;	
		}
	};

	function setSelectedKeys(offcrNbr){
		document.forms[0].selectedOffcrNbr.value = offcrNbr;
	};

	function controlButtons(){

		if (document.forms[0].existingData.value == "1"){
			document.forms[0].deleteOfficerCmplBtn.disabled=false;
		}
		else {
			document.forms[0].deleteOfficerCmplBtn.disabled= true;
		}


	}
</script>

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="Officer.OfficerList" />
	<jsp:param name="gridId" value="gridTable" />
	<jsp:param name="headerId" value="gridHeader" />
	<jsp:param name="controlNames" value="'detailBtn', 'officerCmplBtn'" />
	<jsp:param name="searchInputFields" value="'offcrNbrSrc', 'offcrNameTextSrc'" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>


<TITLE>Consulta de Banker</TITLE>
</HEAD>
<body>
<html:form action="/Officer.OfficerList.List.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet"/>
		<jsp:param name="currentSubSheet" value="Banker" />
    </jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<html:hidden property="clrScreen" value="false"/>
	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
				<html:hidden property="selectedOffcrNbr" value=""/>
				<html:hidden property="existingData" value=""/>

				<html:text property="backURL" value="Officer.OfficerList.List.Show" style="display:none"></html:text> 
				<td>	
					<table class="ODS_internalWidth" border="0">
						<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Consulta de Banker</th></tr></thead>
						<tr>
							<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_line11" height="25"><td colspan="3">Dados Cadastrais:</td></tr>
										<tr>
					 						<td colspan="5"><img src='<%= request.getContextPath()%>/Common/image/20grey1.gif' height="1"  width="100%"></td>
										</tr>
										<tr class="ODS_detail_line1">
											<TD width="9%">Número</TD>
											<TD width="15%"><html:text property="offcrNbrSrc"  styleClass="ODS_Text_Field_Size_10" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text></TD>
											<TD width="10%" align="right">Nome&nbsp;&nbsp;</TD>
											<TD width="50%"><html:text property="offcrNameTextSrc" styleClass="ODS_Text_Field_Size_50" maxlength="40"></html:text></TD>
											<td width="17%">&nbsp;</td>
										</tr>
										<tr class="ODS_detail_line2">
											<td width="9%">Nro. Real</td>
											<td width="15%" colspan="3"><html:text property="offcrRealNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="6"	onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text></td>
										</tr>
							
										<tr class="ODS_line11" height="25"><td colspan="3">Dados Complementares: </td></tr>
										<tr>
					 						<td colspan="5"><img src='<%= request.getContextPath()%>/Common/image/20grey1.gif' height="1"  width="100%"></td>
										</tr>
										<TR class="ODS_detail_line2" >
											<td colspan ="5">
												<table>
													<tr>
														<td width="18%">Nro. Internacional&nbsp;&nbsp;</td>
														<td width="20%"><html:text property="offcrIntlNbrSrc" styleClass="ODS_Text_Field_Size_15" maxlength="11"	onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text></td>
														<td width="18%" align="right">Tipo de Banker&nbsp;&nbsp;</td>
														<td width="30%">
															<html:select property="offcrTypeCodeSrc" styleClass="ODS_Select_Field_Size_40">
																<html:option value=""></html:option>
																<html:options property="offcrTypeCodeDomain.columnValuesByName(OFFCR_TYPE_CODE)"
																labelProperty="offcrTypeCodeDomain.columnValuesByName(OFFCR_TYPE_TEXT)" />
															</html:select></td>
														<td width="14%"></td>
													</tr>
												</table>
											</td>
										</TR>
										<TR class="ODS_detail_line2">
											<td colspan ="5"></td>
										</TR>
									</tbody>
								</TABLE>
	
								<TABLE class="ODS_internalWidth" border="0">
									<TBODY>
										<TR>
											<TD width="100%">&nbsp;</TD>
											<TD><html:button property="listBtn" value="Consultar" onclick="submitAction('list');"></html:button></TD>
											<TD><html:button property="detailBtn" disabled="true" value="Detalhar"	onclick="submitAction('detail');"></html:button></TD>
											<TD><html:button property="officerCmplBtn" disabled="true" value="Alterar / Inserir"	onclick="submitAction('officerCmpl');"></html:button></TD>
											<TD><html:button property="deleteOfficerCmplBtn"  disabled="true" value="Excluir Dados Cmpl." onclick="submitAction('officerCmplDelete');"></html:button></TD>
											<TD><html:button property="histBtn"	value="Histórico Dados Cmpl." onclick="submitAction('listHistory');"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar"	onclick="clearResultSetInServer();submitAction('clearPage');"></html:button></TD>
										</TR>
									</TBODY>
								</TABLE>
				
								<table class="ODS_internalWidth" border="0">
									<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th></tr></thead>
										<tr>
											<td>
												<DIV class="ODS_DivGridVertical">
													<table id="gridTable" border="0" class="ODS_internalWidth">
														<tbody>
															<tr id="gridHeader" class="fixed">
																<th class="ODS_header" width="3%"></th>
																<th class="ODS_header" width="34%">Nome</th>
																<th class="ODS_header" width="6%">Número</th>
																<th class="ODS_header" width="8%">Nro. Real</th>
																<th class="ODS_header" width="15%">Nro. Internacional</th>
																<th class="ODS_header" width="34%">Tipo do Banker</th>
															</tr>
													
															<ods:DataSetRows name="OfficerListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
																<logic:equal name="step" value="0">
																	<tr class="ODS_line1">
																</logic:equal>
																<logic:equal name="step" value="1">
																	<tr class="ODS_line2">
																</logic:equal>
																<bean:define name="resultRow" property="stringByName(OFFCR_NBR)" id="selectedOffcrNbr" type="java.lang.String"></bean:define>
																<bean:define name="resultRow" property="stringByName(QTD)" id="existingData" type="java.lang.String"></bean:define>

																<TD width="3%"><input type="radio" class="radio" name="selection" onclick="javascript:selectedOffcrNbr.value='<%=selectedOffcrNbr%>';existingData.value='<%=existingData%>';disableButtons(false);controlButtons(); "></td>
																<td class="alignLeft"  width="34%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedOffcrNbr.value='<%=selectedOffcrNbr%>';existingData.value='<%=existingData%>';submitAction('detail');"><bean:write name="resultRow" property="stringByName(OFFCR_NAME_TEXT)"/></a></td>
																<td class="alignRight" width="6%"><bean:write name="resultRow" property="stringByName(OFFCR_NBR)" /></td>
																<td class="alignRight" width="8%"><bean:write name="resultRow" property="stringByName(OFFCR_REAL_NBR)"/></td>
																<td class="alignRight" width="15%"><bean:write name="resultRow" property="stringByName(OFFCR_INTL_NBR)"/></td>
																<td class="centralized" width="34%"><bean:write name="resultRow" property="stringByName(OFFCR_TYPE_TEXT)"/></td>
																<td class="centralized" width="1%" style="display:none"><bean:write name="resultRow" property="stringByName(QTD)"/></td>
														</ods:DataSetRows>
													</tbody>
												</table>
											</DIV>
										</td>	
									</tr>
								</TABLE>
	
								<TABLE class="ODS_internalWidth" border="0">
									<TBODY>
										<TR>
											<td width="100%"></td>
											<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
										</TR>
									</TBODY>
								</TABLE>
							</html:form> 
						<jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
					</table>
				</td>
			</tr>
		</table>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>

