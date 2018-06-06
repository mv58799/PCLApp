<head>
<META http-equiv="Expires" content="-1" />
<META http-equiv="Pragma" content="no-cache" />
<META http-equiv="Cache-Control" content="no-cache" />
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<script language="javascript">
  function extraActions(action){
			  
    if(action == "centrApproval"){
    	  
	  document.forms[0].action = document.forms[0].selectedModuleCode.value+".Approval.Show";
	  document.forms[0].backURL.disabled = false;
	  				    
	}
	else if((action == "centrUpdate")){  
	  
	  document.forms[0].action = document.forms[0].selectedModuleCode.value+".Update.Show";
	  document.forms[0].backURL.disabled = false;
	  		      
   }
			
  }; 
  
  function setKeys(selectCode,selectModuleCode){
     document.forms[0].selectedCode.value = selectCode;
     document.forms[0].selectedModuleCode.value = selectModuleCode;
  };
  																		
</script>

</head>