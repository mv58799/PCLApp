<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
				<jsp:param name="pageName" value="KnowledgeExperience.KnowledgeExperienceList"/>
				<jsp:param name="gridId" value="gridTable"/>
				<jsp:param name="headerId" value="gridHeader"/>
				<jsp:param name="searchInputFields"
					value="'clientNumber','clientNameText'" />
				<jsp:param name="controlNames" value="'detailBtn'" />
		</jsp:include>
		
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>


<script language="javascript">

	function extraActions(action){
	}																	

</script>
		
		<TITLE>Consulta de Question&aacute;rio K&amp;E</TITLE>
	</HEAD>

	<body onload="disableButtons(true);">
		<html:form action="/KnowledgeExperience.KnowledgeExperienceList.List.Show.do">
			
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
			  	<jsp:param name="currentSheet" value="CustomerSheet" />
				<jsp:param name="currentSubSheet" value="Clientes" />
			</jsp:include>
			
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:hidden property="selectedClientNumber" />
			<html:hidden property="selectedClientNameText" />			
			
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta de Question&aacute;rio K&amp;E</th>
								</tr>
							</thead>
							
							<html:text property="backURL" value="KnowledgeExperience.KnowledgeExperienceList.List.Show" style="display:none"></html:text>
							
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<tr class="ODS_Detail_Line2">
											<TD width="18%">Número Cliente</TD>
											<TD width="25%"><html:text property="clientNumber" styleClass="ODS_Text_Field_Size_20" maxlength="11"	onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text></TD>
											<TD width="25%">&nbsp;</TD>
											<TD width="32%"></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="18%" >Nome do Cliente</TD>
											<TD colspan="3"><html:text property="clientNameText" styleClass="ODS_Text_Field_Size_60" maxlength="60"></html:text></TD>
										</tr>									
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%">&nbsp;</TD>
											<TD width="6%">&nbsp;</TD>
											<TD align="right">&nbsp;</TD>
											<TD align="right">
												<html:button property="detailBtn" value="Detalhar" onclick="submitAction('detail');" disabled="false"></html:button>
											</TD>											
											<TD align="right">
												<html:button property="listBtn" value="Consultar" onclick="submitAction('list');"></html:button>
											</TD>
											<TD align="left" width="44">
												<html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button>
											</TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>

				<tr>
										
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th>
								</tr>
							</thead>
							<tr>
								<td>
								
								<DIV class="ODS_DivGridVerticalScroll">								
								<table id="gridTable" border="0">
									<tbody>
										<tr id="gridHeader" class="fixed">
										    <TH class="ODS_header" width="10px">&nbsp;</TH>
											<TH class="ODS_header" width="200px">Número do Cliente</TH>
											<TH class="ODS_header" width="600px">Nome do Cliente</TH>											
										</tr>
										
										<ods:DataSetRows name="KnowledgeExperienceListForm" property="results"
											dataSetRowName="resultRow" stepIndexName="step"
											sequenceRestartStep="2">
											<logic:equal name="step" value="0">
												<tr class="ODS_Line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_Line2">
											</logic:equal>
												<bean:define name="resultRow" property="stringByName(CUST_NBR)"
												id="selectedClientNumber" type="java.lang.String"></bean:define>
												<bean:define name="resultRow" property="stringByName(CUST_FULL_NAME_TEXT)"
												id="selectedClientNameText" type="java.lang.String"></bean:define>												
													<td width="10px">
														<input class="radio" type="radio" name="selection1"
														onclick="javascript:selectedClientNumber.value='<%=selectedClientNumber%>';selectedClientNameText.value='<%=selectedClientNameText%>';disableButtons(false);">
													</td>
													<td width="200px">
														<bean:write name="resultRow" property="stringByName(CUST_NBR)"/>
													</td>
													<td width="600px">
														<bean:write name="resultRow" property="stringByName(CUST_FULL_NAME_TEXT)"/>
													</td>
												</tr>
										</ods:DataSetRows>
									</tbody>									
								</TABLE>
								</DIV>
							</tr>
						</TABLE>						
						
					</td>
				</tr>
				<jsp:include page="/View/Util/Footer.jsp" flush="true"/>
			</table>			
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>