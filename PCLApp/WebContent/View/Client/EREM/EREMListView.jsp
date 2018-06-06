
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
				if ( action == 'listHistory' ){
					document.forms[0].action = "EREM.EREMHistoryList.List.Show";
				}
				else if (action =='RelationPrvt.RelationPrvtList')
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
				
				else if (action == 'list' )
				{
					document.forms[0].custNbrSrc.disabled = false;
					document.forms[0].custFullNameTextSrc.disabled = false;	
				}	
				else if(action== 'clearPage')
				{
					document.forms[0].action = "EREM.EREMList.List.ClearPage";
					document.forms[0].backURL.disabled = true;	
				}
				else if(action== 'insertER')
				{					
					document.forms[0].action = "ER.ERDetail.Insert.Show";
					document.forms[0].backURL.disabled = true;	
				}
				else if(action== 'updateER')
				{					
					document.forms[0].action = "ER.ERDetail.Update.Show";
					document.forms[0].backURL.disabled = true;	
				}
				
			}; 
		</script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">	
			<jsp:param name="pageName" value="EREM.EREMList" />
			<jsp:param name="gridId" value="gridTable" />
			<jsp:param name="headerId" value="gridHeader" />
			<jsp:param name="controlNames" value="'alterERBtn','alterBtn','deleteBtn','detailBtn'" />
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Consulta de Associação ER x EM</TITLE>
	</HEAD>

	<body>
		<html:form action="/EREM.EREMList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Clientes" />
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>
			<html:text property="backURL"
				value="EREM.EREMList.List.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta de Associação ER x EM</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
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
											<TD><html:button property="insertER" value="Inserir ER" onclick="submitAction('insertER');"></html:button></TD>
											<TD><html:button property="detailBtn" value="Detalhar" onclick="submitAction('detail');" disabled="true"></html:button></TD>
											<TD><html:button property="alterBtn" value="Alterar" onclick="submitAction('update');" disabled="true"></html:button></TD>																						
											<TD><html:button property="alterERBtn" value="Alterar ER" onclick="submitAction('updateER');" disabled="true"></html:button></TD>
											<TD><html:button property="deleteBtn" value="Excluir" onclick="submitAction('delete');" disabled="true"></html:button></TD>
											<TD><html:button property="histBtn" value="Histórico" onclick="submitAction('listHistory');"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="clearResultSetInServer();submitAction('clearPage');"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>

				<tr>
					<html:hidden property="selectedERNbr"/>
					<html:hidden property="selectedEMNbr"/>
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
								<table id="gridTable" class="ODS_internalWidth" border="0">
									<tbody>
										<tr id="gridHeader" class="fixed">
											<TH class="ODS_header" width="5%"></TH>
											<TH class="ODS_header" width="37%">Nome do Cliente</TH>
											<TH class="ODS_header" width="25%">Papel do Cliente no Relacionamento</TH>
											<TH class="ODS_header" width="18%">Número do EM</TH>
											<TH class="ODS_header" width="18%">Número do ER</TH>
										</tr>
										<ods:DataSetRows name="EREMListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
											<bean:define name="resultRow" property="stringByName(EM_NBR)" id="selectedEMNbr" type="java.lang.String"></bean:define>
											<bean:define name="resultRow" property="stringByName(ER_NBR)" id="selectedERNbr" type="java.lang.String"></bean:define>
												<TD width="5%"><input type="radio" class="radio" name="selection" onclick="javascript:selectedEMNbr.value='<%=selectedEMNbr%>';selectedERNbr.value='<%=selectedERNbr%>';disableButtons(false);"/></td>												
												<TD width="37%"><a  class="ODS_CursorHand" href="#" onclick="javascript:selectedEMNbr.value='<%=selectedEMNbr%>';selectedERNbr.value='<%=selectedERNbr%>';submitAction('detail');"><bean:write name="resultRow" property="stringByName(CUST_FULL_NAME_TEXT)"/></a></TD>
												<TD width="25%"><bean:write name="resultRow" property="stringByName(ROLE_CUST_TEXT)"/></td>
												<TD width="18%" align="right"><bean:write name="resultRow" property="stringByName(EM_NBR)"/></td>
												<TD width="18%" align="right"><bean:write name="resultRow" property="stringByName(ER_NBR)"/></td>
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
