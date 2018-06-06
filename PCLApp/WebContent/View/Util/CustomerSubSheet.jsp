
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>

	<script type="text/javascript">
	 	var id_menu_Customer =
		[[null, 'Clientes', null, '_id20:frmMenu', null
			<ods:AccessGrantedTag m_SGFunction="/EREM.EREMList.List.Show.Clear">, [null, 'Consulta de ER x EM', './EREM.EREMList.List.Show.Clear?currentSubSheet=Clientes' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null],</ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/CustomerPrvt.CustomerPrvtList.List.Show.Clear">, [null, 'Clientes Private', './CustomerPrvt.CustomerPrvtList.List.Show.Clear?currentSubSheet=Clientes' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null],</ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/Contract.CurAccountList.List.Show.Clear">, [null, 'Conta Corrente / CCI', './Contract.CurAccountList.List.Show.Clear?currentSubSheet=Clientes' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]</ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/KnowledgeExperience.KnowledgeExperienceList.List.Show.Clear">, [null, 'Question&aacute;rio K&amp;E', './KnowledgeExperience.KnowledgeExperienceList.List.Show.Clear?currentSubSheet=Clientes' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]</ods:AccessGrantedTag>], _cmSplit,
		[null, 'Relacionamentos', null,'_id20:frmMenu', null
			<ods:AccessGrantedTag m_SGFunction="/RelationEg.RelationEgList.List.Show.Clear">, [null, 'Consulta de Relacionamentos x EG', './RelationEg.RelationEgList.List.Show.Clear?currentSubSheet=Relacionamentos' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null],</ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/RelationPrvt.RelationPrvtList.List.Show.Clear">, [null, 'Relacionamentos Private', './RelationPrvt.RelationPrvtList.List.Show.Clear?currentSubSheet=Relacionamentos' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]</ods:AccessGrantedTag>],_cmSplit,
		[null, 'Banker', null,'_id20:frmMenu', null
			<ods:AccessGrantedTag m_SGFunction="/Officer.OfficerList.List.Show.Clear">, [null, 'Banker', './Officer.OfficerList.List.Show.Clear?currentSubSheet=Banker' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null],</ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/PortfolioPrvt.PortfolioPrvtList.List.Show.Clear">, [null, 'Carteiras', './PortfolioPrvt.PortfolioPrvtList.List.Show.Clear?currentSubSheet=Banker' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]</ods:AccessGrantedTag>],_cmSplit,
		
		<ods:AccessGrantedTag m_SGFunction="/MrDocPrvt.MrDocPrvtList.List.Show.Clear">, [null, 'MR/IP', './MrDocPrvt.MrDocPrvtList.List.Show.Clear?currentSubSheet=MR/IP' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null],_cmSplit,</ods:AccessGrantedTag>
		
		[null, 'Parâmetros', null, '_id20:frmMenu', null,
			[null, 'Classificação Compliance', null, '_id20:frmMenu', null
			<ods:AccessGrantedTag m_SGFunction="/ClassCmplc.ClassCmplcList.List.Show.Clear">, [null, 'Consultar', './ClassCmplc.ClassCmplcList.List.Show.Clear?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/ClassCmplc.ClassCmplcDetail.Insert.Show">, [null, 'Inserir', './ClassCmplc.ClassCmplcDetail.Insert.Show?currentSubSheet=Parâmetros' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]</ods:AccessGrantedTag>]],_cmSplit,
		[null, 'Aprovação', null, '_id20:frmMenu', null
			<ods:AccessGrantedTag m_SGFunction="/CentrApproval.CentrApprovalMovementList.List.Execute">, [null, 'Aprovação Centralizada', './CentrApproval.CentrApprovalMovementList.List.Execute?currentSheet=CustomerSheet' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
			
			/*<ods:AccessGrantedTag m_SGFunction="/EREM.EREMMovementList.List.Execute">, [null, 'Consulta de ER x EM', './EREM.EREMMovementList.List.Execute?currentSubSheet=Aprovação' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/RelationEg.RelationEgMovementList.List.Execute">, [null, 'Consulta de Relacionamentos x EG', './RelationEg.RelationEgMovementList.List.Execute?currentSubSheet=Aprovação' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/OfficerCmpl.OfficerCmplMovementList.List.Execute">, [null, 'Banker - Dados Complementares', './OfficerCmpl.OfficerCmplMovementList.List.Execute?currentSubSheet=Aprovação' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/CustomerPrvtCmpl.CustomerPrvtCmplMovementList.List.Execute">, [null, 'Clientes - Dados Complementares', './CustomerPrvtCmpl.CustomerPrvtCmplMovementList.List.Execute?currentSubSheet=Aprovação' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/CustomerPrvtCmpl.CustomerPrvtCmplMovementList.List.Execute">, [null, 'Clientes - Dados Complementares', './CustomerPrvtCmpl.CustomerPrvtCmplMovementList.List.Execute?urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
			
			<ods:AccessGrantedTag m_SGFunction="/BkrPortfMgmt.BkrPortfMgmtMovementList.List.Execute">, [null, 'Consulta de Carteira x Corretora', './BkrPortfMgmt.BkrPortfMgmtMovementList.List.Execute?currentSubSheet=Aprovação' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/IpDocPrvt.IpDocPrvtMovementList.List.Execute">, [null, 'Instrução Permanente', './IpDocPrvt.IpDocPrvtMovementList.List.Execute?currentSubSheet=Aprovação' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/CurAcctPrmntInstr.CurAcctPrmntInstrMovementList.List.Execute">, [null, 'Consulta de Conta Corrente x Instrução Permanente', './CurAcctPrmntInstr.CurAcctPrmntInstrMovementList.List.Execute?currentSubSheet=Aprovação' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null], </ods:AccessGrantedTag>
			<ods:AccessGrantedTag m_SGFunction="/MrDocPrvt.MrDocPrvtMovList.List.Execute">, [null, 'Memo de Risco', './MrDocPrvt.MrDocPrvtMovList.List.Execute?currentSubSheet=Aprovação' + '&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null] </ods:AccessGrantedTag>*/]];
	</script><div id="id_menu_Customer"></div>

	<script type="text/javascript">	cmDraw ('id_menu_Customer', id_menu_Customer, 'hbr', cmThemeOffice, 'ODS_ThemeOffice');</script>

