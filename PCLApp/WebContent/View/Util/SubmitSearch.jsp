<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html:hidden property="clickedSearch" />
<script language="javascript">
	
	function SubmitSearch ()
	{
		if (document.forms[0].clickedSearch.value!= ""){		
			submitAction(document.forms[0].clickedSearch.value);	
		}
	}
	window.attachEvent("onload", SubmitSearch);
</script>
	
