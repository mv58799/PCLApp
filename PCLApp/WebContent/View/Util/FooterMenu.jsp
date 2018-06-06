
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<link rel="stylesheet" type="text/css" href="../Client/Customer/Common/css/citi.css">
<script language="javascript" src="../Client/Customer/Common/js/dialog_window.js"></script>
<script language="JavaScript">
	function RedirectPage()
	{
		window.location = './FindProposal';	
	}
	
	function RefuseApplication()
	{
		retval = window.modal2('./Submit','width=800,height=600,toolbars=0,resizable=0');
		if ( retval == 'OK' )
		{
			window.location = './FindProposal';
		}				
	}
</script>
<br>
	<table class="CI_mainTable" border="0">
		<tr>
			<td align="center">
				<html:button styleClass="button" property="ProposalMap" value="Seqüência de Aprovação" onclick="window.modal('./ProposalMap','width=800,height=600,toolbars=0,resizable=0,scrollbars=1');return false;"></html:button>
				<html:button styleClass="button" property="WorkflowHistory" value="Histórico da Proposta" onclick="window.modal('./WorkflowHistory','width=800,height=600,toolbars=0,resizable=0,scrollbars=1');return false;"></html:button>
				<html:button styleClass="button" property="DocChecklist" value="Checklist de Documentos" onclick="window.modal('./DocChecklist','width=800,height=600,toolbars=0,resizable=0,scrollbars=1');return false;"></html:button>
				<html:button styleClass="button" property="Submit" value="Submit" onclick="RefuseApplication()"></html:button>
				<html:button styleClass="button" property="" value="Salvar Tela" onclick=""></html:button>
				<html:button styleClass="button" property="" value="Next" onclick=""></html:button>
			</td>
		</tr>
	</table>
<br>