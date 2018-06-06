<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!-- Hidden de Controle quando o botão consultar é pressionado -->	
<input type="hidden" value="N" name="executingList"/>

<logic:messagesPresent message="true">
	<div class = "ODS_Div"> 	
		<table id="serverMessageArea" class="ODS_BorderMsg" >
			<html:messages message="true" id="msg">
				<tr><td class="ODS_Message"><bean:write name="msg"/></td></tr>
			</html:messages>	
		</table>
	</div>
</logic:messagesPresent>

<logic:messagesPresent message="false">
	<div class = "ODS_Div"> 
		<table id="serverMessageArea" class="ODS_BorderError">
			<html:messages message="false" id="msg">
				<tr><td class="ODS_Error" ><bean:write name="msg"/></td></tr>
			</html:messages>
		</table>
	</div>
</logic:messagesPresent>

<div class = "ODS_Div"> 
	<table id="tableError" style="display:none" class="ODS_BorderError">
		<tr>
			<td>
				<P class="ODS_Error" id="validationMessageArea" style="display:none"></P>
			</td>
		</tr>
	</table>
</div>

