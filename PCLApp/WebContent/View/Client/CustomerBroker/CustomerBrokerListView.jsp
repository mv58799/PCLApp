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

		function extraActions( action )
		{
			if (action =='CustomerBroker.CustomerBrokerList')
			 {
			  document.forms[0].action = "CustomerBroker.CustomerBrokerList.List.Show";
			  document.forms[0].backURL.disabled = false;
			 }
		};
	 </script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="CustomerBroker.CustomerBrokerList"/>
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>		
			<jsp:param name="controlNames" value="'detailBtn','deleteBtn'"/>
			<jsp:param name="fieldsWithMask" value="['bkrCnpjNbrSrc','CHAR','NN.NNN.NNN/NNNN-NN','left',null]"/>	
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE> Consulta de Associação Cliente x Corretoras </TITLE>
	</HEAD>
	<body>
		<html:form action="/CustomerBroker.CustomerBrokerList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="CustomerBroker"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>	
			<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>		
			<html:text property="backURL" value="CustomerPrvt.CustomerPrvtList.List.Show" style="display:none"></html:text> 
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta de Associação Cliente x Corretoras</th>
								</tr>
							</thead>
							<tr>
								<td >&nbsp;</td>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">

									<tbody>

										<tr class="ODS_line11" height="25"><td colspan="3">Dados de Cliente</td></tr>

										<tr height="8">
					 						<td colspan="3" width="100%"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="250%" height="1"></td>
										</tr>

										<tr class="ODS_Detail_line1">
											<td colspan="5">										
												<table><tr>
													<TD width="17%">Número do Cliente</TD>
													<TD width="16%"><html:text property="custNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text></TD>
													<TD width="7%">Nome</TD>
													<TD><html:text property="custFullNameTextSrc" styleClass="ODS_Text_Field_Size_60" maxlength="60"></html:text></TD>
												</tr></table>																		
											</td>
						        		</tr>

										<tr class="ODS_line11" height="25"><td colspan="3">Dados de Corretora</td></tr>

										<tr height="8">
					 						<td colspan="3" width="100%"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="250%" height="1"></td>
										</tr>

										<tr class="ODS_Detail_Line1">
											<td colspan="5">										
												<table><tr>
													<TD width="17%">CNPJ</TD>
													<TD width="16%"><html:text property="bkrCnpjNbrSrc" styleClass="ODS_Text_Field_Size_15" maxlength="18" onkeyup="MaskFieldPress('CHAR','NN.NNN.NNN/NNNN-NN','left',null)"></html:text></TD>
													<TD width="7%"> &nbsp; Razão Social</TD>
													<TD><html:text property="bkrNameTextSrc" styleClass="ODS_Text_Field_Size_60" maxlength="60"></html:text></TD>
												</tr></table>	
											</td>
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
											<TD width="6%"><html:button property="listBtn" value="Consultar" onclick="submitAction('list');"></html:button></TD>
											<TD align="left" width="44"><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>					
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
				<tr>
					<html:hidden property="selectedBkrCnpjNbr" />
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
											<TH class="ODS_header" width="2%">&nbsp;</TH>
											<TH class="ODS_header" width="51%">Razão Social</TH>
											<TH class="ODS_header" width="22%">CNPJ</TH>
											<TH class="ODS_header" width="25%">Endereço</TH>	
										</tr>
										<ods:DataSetRows name="CustomerBrokerListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">				
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
											<bean:define name="resultRow" property="stringByName(BKR_CNPJ_NBR)" id="selectedBkrCnpjNbr" type="java.lang.String"></bean:define>
			 										<TD width="2%"><input type="checkbox" class="checkbox" name="selection" onclick="javascript:selectedBkrCnpjNbr.value='<%= selectedBkrCnpjNbr %>'; disableButtons(false);"/></td>
													<TD width="51%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedBkrCnpjNbr.value='<%= selectedBkrCnpjNbr %>';submitAction('detail');"><bean:write name="resultRow" property="stringByName(BKR_NAME_TEXT)"/></a></td>
													<TD width="22%" align="right"><bean:write name="resultRow" property="stringByName(BKR_CNPJ_NBR)"/></td>
													<TD width="25%" align="right"><bean:write name="resultRow" property="stringByName(BKR_ADDR_TEXT)"/></td>
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
											<TD width="100%">&nbsp;</TD>
											<TD><html:button property="deleteBtn" value="Inserir Corretoras Selecionadas" onclick="submitAction('insert'); " disabled = "true"></html:button></TD>
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
									<th class="subtitle" scope="colgroup" colspan="3">Corretoras associadas</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table id="gridTable" class="ODS_internalWidth" border="0">
									<tbody>
										<tr id="gridHeader" class="fixed">
											<TH class="ODS_header" width="2%">&nbsp;</TH>
											<TH class="ODS_header" width="51%">Razão Social</TH>
											<TH class="ODS_header" width="22%">CNPJ</TH>
											<TH class="ODS_header" width="25%">Endereço</TH>	
										</tr>
										<ods:DataSetRows name="CustomerBrokerListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">				
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
											<bean:define name="resultRow" property="stringByName(BKR_CNPJ_NBR)" id="selectedBkrCnpjNbr" type="java.lang.String"></bean:define>
			 										<TD width="2%"><input type="checkbox" class="checkbox" name="selection" onclick="javascript:selectedBkrCnpjNbr.value='<%= selectedBkrCnpjNbr %>'; disableButtons(false);"/></td>
													<TD width="51%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedBkrCnpjNbr.value='<%= selectedBkrCnpjNbr %>';submitAction('detail');"><bean:write name="resultRow" property="stringByName(BKR_NAME_TEXT)"/></a></td>
													<TD width="22%" align="right"><bean:write name="resultRow" property="stringByName(BKR_CNPJ_NBR)"/></td>
													<TD width="25%" align="right"><bean:write name="resultRow" property="stringByName(BKR_ADDR_TEXT)"/></td>
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
											<TD width="100%">&nbsp;</TD>
											<TD><html:button property="deleteBtn" value="Excluir Corretoras selecionadas" onclick="submitAction('delete'); " disabled = "true"></html:button></TD>
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
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%">&nbsp;</TD>
											<TD><html:button property="insertBtn" value="Confirmar Associação" onclick="submitAction('insert'); " disabled = "true"></html:button></TD>
											<TD align="left"><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>										
										</TR>
									</tbody>
								</TABLE>
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
