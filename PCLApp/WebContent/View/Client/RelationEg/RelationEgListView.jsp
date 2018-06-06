
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

	<script language="javascript">
		function extraActions( action )
		{
			if (action =='RelationPrvt.RelationPrvtList')
			{
				document.forms[0].action = "RelationPrvt.RelationPrvtList.List.Show";
				document.forms[0].backURL.disabled = false;
			}
		};

	 </script>		

	<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
		<jsp:param name="pageName" value="RelationEg.RelationEgList"/>
		<jsp:param name="gridId" value="gridTable"/>
		<jsp:param name="headerId" value="gridHeader"/>
		<jsp:param name="controlNames" value="'alterBtn','deleteBtn', 'detailBtn'"/>
	</jsp:include>
	<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Consulta de Associação Relacionamento x EG</TITLE>
	</HEAD>

	<body>
		<html:form action="/RelationEg.RelationEgList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Relacionamentos" />
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>	
			<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="RelationEg.RelationEgList.List.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta de Associação Relacionamento x EG</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="3%">EG</TD>
											<TD width="10%"><html:text property="egNbrSrc" styleClass="ODS_Text_Field_Size_5" maxlength="4"></html:text></TD>
											<TD width="3%">ER</TD>
											<td width="30%">
													<html:select styleClass="ODS_Text_Field_Size_30" property="erNbrSrc">
														<html:option value=""></html:option>
														<html:options property="erNbrDomain.columnValuesByName(ER_NBR)"
														labelProperty="erNbrDomain.columnValuesByName(ER_NBR)" />
													</html:select>	

											</TD>
											<TD width="20%">Número do Relacionamento</TD>
											<TD width="17%"><html:text property="reltnNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text>&nbsp;&nbsp;<html:button property="searchReltnNbr" value="Buscar" onclick="submitAction('PreparedSearch.RelationPrvt.RelationPrvtList');"></html:button></TD>
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
											<TD width="100%"></TD>
											<TD><html:button property="listBtn" value="Consultar" onclick="submitAction('list');"></html:button></TD>
											<TD><html:button property="insertBtn" value="Inserir" onclick="submitAction('insert');"></html:button></TD>
											<TD><html:button property="detailBtn" disabled="true" value="Detalhar" onclick="submitAction('detail');"></html:button></TD>
											<TD><html:button property="alterBtn" value="Alterar" onclick="submitAction('update');" disabled="true"></html:button></TD>
											<TD><html:button property="deleteBtn" value="Excluir" onclick="submitAction('delete');" disabled="true"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>

				<tr>
					<html:hidden property="selectedEgNbr" value="" />
					<html:hidden property="selectedErNbr" value="" />
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th>
								</tr>
							</thead>	
								<logic:equal name="RelationEgListForm" property="flagAllData" value="N" >
									<tr class="ODS_Detail_Line2" >
										<td style="color:red" colspan="7">Devido ao volume esta
										pesquisa foi limitada a 50 linhas.<br>
									    Favor, utilizar os filtros a
										fim de especificar a sua consulta. </td>
									</tr>
								</logic:equal>						
							<tr>
								<td>
								<table id="gridTable" class="ODS_internalWidth" border="0">
									
									<tbody>
									
										<tr id="gridHeader" class="fixed">
											<TH class="ODS_header" width="3%">&nbsp;</TH>
											<TH class="ODS_header" width="17%">Cliente Owner</TH>
											<TH class="ODS_header" width="18%">Cliente Agregado 1</TH>
											<TH class="ODS_header" width="18%">Cliente Agregado 2</TH>
											<TH class="ODS_header" width="10%">EG</TH>
											<TH class="ODS_header" width="17%">Nro.Relacionamento</TH>
											<TH class="ODS_header" width="17%">Nro. ER</TH>
										</tr>

										<ods:DataSetRows name="RelationEgListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
											<bean:define name="resultRow" property="stringByName(EG_NBR)" id="selectedEgNbr"></bean:define>
											<bean:define name="resultRow" property="stringByName(ER_NBR)" id="selectedErNbr"></bean:define>
			 									<TD width="3%"><input type="radio" class="radio" name="selection" onclick="javascript:selectedEgNbr.value='<%= selectedEgNbr %>'; selectedErNbr.value='<%= selectedErNbr %>';disableButtons(false);"></td>
												<TD width="17%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedEgNbr.value='<%= selectedEgNbr %>';selectedErNbr.value='<%= selectedErNbr %>';submitAction('detail');">
													<bean:write name="resultRow" property="stringByName(CUST_SHORT_NAME_TEXT1)"/></a></td>
												<TD width="18%"><bean:write name="resultRow" property="stringByName(CUST_SHORT_NAME_TEXT2)"/></td>
												<TD width="18%"><bean:write name="resultRow" property="stringByName(CUST_SHORT_NAME_TEXT3)"/></td>
												<TD width="10%" align="right"><bean:write name="resultRow" property="stringByName(EG_NBR)"/></td>
												<TD width="17%" align="right"><bean:write name="resultRow" property="bigDecimalByName(RELTN_NBR)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>'/></td>
												<TD width="17%" align="right"><bean:write name="resultRow" property="stringByName(ER_NBR)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>'/></td>
											</tr>
										</ods:DataSetRows>
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
											<TD width="100%"></TD>
											<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
			</table>
			<jsp:include page="/View/Util/Footer.jsp" flush="true"/>	
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>



