<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

	<script language="javascript">
		function extraActions( action ){};		
		
		function verificaCampo(combo)
		{
			var campo = document.forms[0].reltnEndReasText;
			
			var vrCombo = combo.options[combo.selectedIndex].text;
			
			if(vrCombo == 'Outros'){
				campo.disabled = false;
			}	
			else{
				campo.value = "";
				campo.disabled = true;
			}	
		}
	</script>		

	<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
		<jsp:param name="pageName" value="ER.ERDetail"/>
	</jsp:include>
	<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Inserção de Dados Complementares de ER</TITLE>
	</HEAD>

	<body>
		<html:form action="/ER.ERDetail.Insert.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Clientes" />
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>				
			<html:text property="backURL" value="ER.ERDetail.Insert.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead><tr><th class="subtitle" scope="colgroup" colspan="4">Inserção de Dados Complementares de ER</th></tr></thead>
								<tbody>
									<tr class="ODS_Detail_Line1">
										<TD width="40%">Número do ER*</TD>
										<TD width="60%">Origem</TD>

									</tr>
									<tr class="ODS_Detail_Line2">
										<TD width="40%"><html:text property="erNbr" styleClass="ODS_Text_Field_Size_30" maxlength="30"></html:text></TD>
										<TD width="60%">
											<html:select property="erReltnTrfInd" styleClass="ODS_Select_Field_Size_10">
												<html:option value=""></html:option>
												<html:options property="erReltnTrfIndDomain.columnValuesByName(INDICATOR_CODE)" labelProperty="erReltnTrfIndDomain.columnValuesByName(INDICATOR_TEXT)" />
											</html:select>
										</TD>
								    </tr>
							</table>

						        <table class="ODS_internalWidth" border="0">
									<tr class="ODS_Detail_Line1">
										<TD width="40%">Estimativa de Patrimônio</TD>
										<TD width="60%">&nbsp;</TD>
									<tr>
									<tr class="ODS_Detail_Line2">
										<TD width="40%">
											<html:select property="equityClassCode" styleClass="ODS_Select_Field_Size_20">
												<html:option value=""></html:option>
												<html:options property="equityClassCodeDomain.columnValuesByName(EQUITY_CLASS_CODE)" labelProperty="equityClassCodeDomain.columnValuesByName(EQUITY_CLASS_TEXT)" />
											</html:select>										    
										</TD>
										<td width="60%">&nbsp;</td>
									</tr>
								</tbody>
							</TABLE>
							<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%">&nbsp;</TD>
											<TD><html:button property="insertBtn" value="Confirmar Inserção" onclick="submitAction('insert');"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="javascript:clearAllPage();"></html:button></TD>
											<TD align="left"><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>										
										</TR>
									</tbody>
						</TABLE>
     				<jsp:include page="/View/Util/Footer.jsp" flush="true"/>			
				</html:form>
			</body>
			<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>

