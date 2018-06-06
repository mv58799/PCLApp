<head>
<META http-equiv="Expires" content="-1" />
<META http-equiv="Pragma" content="no-cache" />
<META http-equiv="Cache-Control" content="no-cache" />

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.citibank.newcpb.form.BaseForm"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.newcpb.action.CommonAction"%>

<script language="javascript">
	// BEGIN: Control to avoid taking back command
	history.go(1);
	// END: Control to avoid taking back command	
	
	var bAlreadySubmitted;
	bAlreadySubmitted= false;

	function clearPage() {
		clearInputs();
		validationMessageArea.innerHTML = "";
		validationMessageArea.style.display = "none";
		tableError.style.display = "none";

		if (document.getElementById("serverMessageArea") != null) {
			serverMessageArea.style.display = "none";
		}
		if (<%=request.getParameter("gridId")%>	!= null) {
			hideGrid();
		}
		if (<%=request.getParameter("controlNames")%> != null) {
			disableButtons(true);
		}
		
		var pagebanneList = document.getElementsByClassName("pagebanner");
		if(pagebanneList!=null){
			for (i = 0; i < pagebanneList.length; i++) {
				if (pagebanneList[i] !=null) {
					pagebanneList[i].innerHTML="";
				}
			}
		}
		
		
		var pagelinksList = document.getElementsByClassName("pagelinks");
		if(pagelinksList!=null){
			for (i = 0; i < pagelinksList.length; i++) {
				if (pagelinksList[i] !=null) {
					pagelinksList[i].innerHTML="";
				}
			}		
		}		
	}

	// limpa os dados dos campos da tela
	function clearInputs() {
		// Recuperando campos de input
		var inputs = document.getElementsByTagName("input");

		// Percorrendo cada input e limpando
		for (i = 0; i < inputs.length; i++) {

			if (inputs[i].type == "text") {
				if (!inputs[i].disabled) {
					inputs[i].value = "";
				}
			}

			if (inputs[i].type == "file") {
				if (!inputs[i].disabled) {
					inputs[i].value = "";
				}
			}

			if (inputs[i].type == "checkbox") {
				if (!inputs[i].disabled) {
					inputs[i].checked = false;
				}
			}
		}

		// Recuperando campos de select
		var selects = document.getElementsByTagName("select");

		// Percorrendo cada select e limpando
		for (i = 0; i < selects.length; i++) {
			if (!selects[i].disabled) {
				selects[i].options[0].selected = true;
			}
		}

	}
	
	// esconde os dados do grid
	function hideGrid()	{
		var gridId ="<%=request.getParameter("gridId")%>";
		var headerId = "<%=request.getParameter("headerId")%>";

		// Recuperando a tabela e suas linhas (TRs)
		var table = document.getElementById(gridId);
		var rows = table.getElementsByTagName("tr");

		//Recupera o conteúdo da tabela e o texto do header
		//Cria uma nova tabela para substituir a atual.
		var tableOuterHtml = table.outerHTML;
		var headerOuterHtml = rows[0].outerHTML;
		var newTableOuterHtml = "";
		var headerEndPosition = -1;

		//Esconde a tabela atual com os campos populados
		table.style.display = "none";

		//Manipula substring para armazenar em variável auxiliar a tabela e o conteúdo do header.
		//Fecha a nova tabela criada. Insere a nova tabela na tela.
		headerEndPosition = tableOuterHtml.toUpperCase().search(/<\/TR>/);
		headerEndPosition = headerEndPosition + 5;
		newTableOuterHtml = tableOuterHtml.substring(0, headerEndPosition);
		newTableOuterHtml = newTableOuterHtml + "</table>";

		table.insertAdjacentHTML("afterEnd", newTableOuterHtml);

	}

	// desabilita os botões configurados
	function disableButtons(status) {
		var controlNames = [<%=request.getParameter("controlNames")%>];
		for (i = 0; i < controlNames.length; i++) {
			var controls = document.getElementsByName(controlNames[i]);
			if (controls.length > 0) {
				control = controls[0];
				control.disabled = status;
			}
		}
	}
	
	function submitActionNewCPB(action){
		
		
	    if ( isSubmitted() ){
	       showStandardWaitMessage();
		}
		else{
			
			setSubmitted();		
			
			document.forms[0].action= document.forms[0].action + "?method=" + action + "&urlDistinguisher=" + <%=new java.util.Date().getTime()%>
			document.forms[0].submit();
		}
	}

	function submitDonwload(actionDonw){	
			var formDown=document.forms[0].action;
			document.forms[0].action= document.forms[0].action + "?method=" + actionDonw + "&urlDistinguisher=" + <%=new java.util.Date().getTime()%>
			document.forms[0].submit();					
			document.forms[0].action=formDown;
	}
	
	function showStandardWaitMessage()
	{
		alert('Aguarde!!\nO sistema esta processando a sua requisicao.');
	}
	
	function setSubmitted()
	{
		bAlreadySubmitted= true;
		setWaiting();
	}
	
	function setWaiting()
	{
		var formsList = document.forms;
		var graterWidth = document.forms[0].offsetWidth;
		var graterHeight = 0;

		for ( var i=0; i < formsList.length; i++ )
		{
			graterHeight += document.forms[i].offsetHeight;
		}

		var divhtml =
			"<div id='waitingDiv' style='position:absolute; left:0px; top:0px; " +
			"width:" + graterWidth + "px;" +
			"height: " + graterHeight + "px; " +
			"cursor: wait; background-image:url(<%=request.getContextPath()%>/Common/image/transparent.gif); z-index: 3' ></div>";

		document.body.insertAdjacentHTML( "afterBegin", divhtml );
	}
	
	function isSubmitted()
	{
		return bAlreadySubmitted;
	}

	function hiddenBox(objImg,idBox){

	
		
		endImg = objImg.src;

	
		objVisibleBox = "infoBase_";
		objNoVisibleBox = "infoInvest_infoRenda_";	

		txtVisible = "infoBase_";
		txtNoVisibleBox = "infoInvest_infoRenda_";


	
		noTxt = idBox + "_";			
		if(endImg.indexOf("plus.gif") > 0){
			txtImg = endImg.replace("plus","minus");
			visible = "block";
			txtVisible = txtVisible + idBox + "_";
			txtNoVisibleBox = txtNoVisibleBox.replace(noTxt,"");		
		}else{
			txtImg = endImg.replace("minus","plus");
			visible = "none";			
			txtVisible = txtVisible.replace(noTxt,"");
			txtNoVisibleBox = txtNoVisibleBox + idBox + "_";
		}
		eval("document.getElementById('" + idBox + "').style.display = '" + visible + "'");
		objVisibleBox.value = txtVisible;
		objNoVisibleBox.value = txtNoVisibleBox;
		objImg.src = txtImg;		

		
	}
	
	function loadHiddenBox(objVisible,objNoVisible){


		
		arrayVisible = objVisible.value.split("_");
		arrayNoVisible = objNoVisible.value.split("_");

		for(i=0;i < arrayVisible.length-1;i++){
			objImg = eval("document.getElementById('img_" + arrayVisible[i] + "')");
			txtImg = objImg.src;
			objImg.src = txtImg.replace("plus","minus");
			//Exibe Box
			eval("document.getElementById('" + arrayVisible[i] + "').style.display = 'block'");			
		}
		for(i=0;i < arrayNoVisible.length-1;i++){
			objImg = eval("document.getElementById('img_" + arrayNoVisible[i] + "')");
			txtImg = objImg.src;
			objImg.src = txtImg.replace("minus","plus");
			//Esconde Box
			eval("document.getElementById('" + arrayNoVisible[i] + "').style.display = 'none'");			
		}		
	}

	function maskDate(){
		
		element = event.srcElement;
		element.maxLength = "10";
		if ( !maskInt() ){
			event.returnValue = false;
			return;
		}
	
		var mydata	= "";
		var data	= element.value;
		mydata		= mydata + data;
		
		if (mydata.length == 2 || mydata.length == 5){
			if(event.keyCode != 8 && event.keyCode != 46){
				mydata			+= '/'; 
				element.value	 = mydata;
			}
		}
	}
	
	function maskDateMonthYear(){
		
		element = event.srcElement;
		element.maxLength = "7";
		if ( !maskInt() ){
			event.returnValue = false;
			return;
		}
	
		var mydata	= "";
		var data	= element.value;
		mydata		= mydata + data;
		
		if (mydata.length == 2){
			if(event.keyCode != 8 && event.keyCode != 46){
				mydata			+= '/'; 
				element.value	 = mydata;
			}
		}
	}

	function maskInt(){

		var downShiftKey  = event.shiftKey;
		var downCtrlKey   = event.ctrlKey;
			
		if (
			 (
				!(event.keyCode >= 96 && event.keyCode <= 105) && // 0 a 9 on Num pad
				!(event.keyCode >= 48 && event.keyCode <= 57)  && // 0 a 9 on Alpha
				!(downCtrlKey && !(event.keyCode == 67 && event.keyCode == 86)) && // Ctrl V and Ctrl C
				event.keyCode != 8 &&	// Backspace
				event.keyCode != 9 &&	// TAB
				event.keyCode != 13 &&	// Enter
				event.keyCode != 16 &&	// Shift
				event.keyCode != 17 &&	// Ctrl
				event.keyCode != 18 &&	// Alt
				event.keyCode != 20 &&	// Capslock
				event.keyCode != 27	&&  // Esc
				event.keyCode != 33 &&	// Page Up
				event.keyCode != 34 &&	// Page Down
				event.keyCode != 35 &&	// End
				event.keyCode != 36 &&	// Home
				event.keyCode != 37 &&	// Left Arrow
				event.keyCode != 38 &&	// Top Arrow
				event.keyCode != 39 &&	// Right Arrow
				event.keyCode != 40 &&	// Bottom Arrow
				event.keyCode != 45 &&	// Insert
				event.keyCode != 46 &&	// Del
				event.keyCode != 144    // Numlock				
			  )
			)
		{
			event.returnValue = false;
		}

		// Shift + (0 a 9 on Alpha)
		if (downShiftKey && (event.keyCode >= 48 && event.keyCode <= 57) ) {
			event.returnValue = false;
		}
	
		return true;
	}


	function onlyNumbers( obj ){
		obj.value = obj.value.replace(/[^0-9]/g,"");
	}


	function isOnlyNumbers(evt){
	   var charCode = (evt.which) ? evt.which : event.keyCode
	   if (charCode > 31 && (charCode < 48 || charCode > 57))
	      return false;

	   return true;
	}

	function maskCpfCnpj(control){	

		if (control == null)
		{
			control = event.srcElement;
		}
		
		if (control.value != null && control.value != "")
		{
			if (control.value.replace( /\D/g , "").length > 11)
			{
				MaskFieldPress('CHAR','NN.NNN.NNN/NNNN-NN',null,null,control);
			}
			else
			{
				MaskFieldPress('CHAR','NNN.NNN.NNN-NN',null,null,control);
			}
		}
	}
	
	function completeCpfCnpj(control){	

		if (control == null)
		{
			control = event.srcElement;
		}
		
		if (control.value != null && control.value != "")
		{
			if (control.value.replace( /\D/g , "").length > 0){
				if (control.value.replace( /\D/g , "").length > 11){
					control.value = pad(control.value.replace( /\D/g , ""), 11);
					MaskFieldPress('CHAR','NN.NNN.NNN/NNNN-NN',null,null,control);
				}else{
					control.value = pad(control.value.replace( /\D/g , ""), 14);
					MaskFieldPress('CHAR','NNN.NNN.NNN-NN',null,null,control);
				}
			}			
		}
	}
	
	function pad(str, length) {
		  const resto = length - String(str).length;
		  return '0'.repeat(resto > 0 ? resto : '0') + str;
		}
	
	function maskValue(w, e, m, r, a) {
	    // Cancela se o evento for Backspace
	    if (!e) var e = window.event
	    if (e.keyCode) code = e.keyCode;
	    else if (e.which) code = e.which;
	    // Variáveis da função
	    var txt = (!r) ? w.value.replace(/[^\d]+/gi, '') : w.value.replace(/[^\d]+/gi, '').reverse();
	    var mask = (!r) ? m : m.reverse();
	    var pre = (a) ? a.pre : "";
	    var pos = (a) ? a.pos : "";
	    var ret = "";
	    if (code == 9 || code == 8 || txt.length > mask.length) return false;
	    // Loop na máscara para aplicar os caracteres
	    for (var x = 0, y = 0, z = mask.length; x < z && y < txt.length;) {
	        if (mask.charAt(x) != '#') {
	            ret += mask.charAt(x);
	            x++;
	        } else {
	            ret += txt.charAt(y);
	            y++;
	            x++;
	        }
	    }
	    // Retorno da função
	    ret = (!r) ? ret : ret.reverse()
	    w.value = pre + ret + pos;
	}
	// Novo método para o objeto 'String'
	String.prototype.reverse = function() {
	    return this.split('').reverse().join('');
	};
	
	function toUppercase(control){	

		if (control == null){
			control = event.srcElement;
		}
		
		if (control.value != null && control.value != ""){
			control.value = control.value.toUpperCase();
		}
	}


	function loadFormFatca(){

		var isFatcaCheckElement = document.getElementsByName("registerConsumer.formType"); 
		var isFatcaCheck = false;

		  if(isFatcaCheckElement!=null && isFatcaCheck === false ){
			  for (var i=0;i<isFatcaCheckElement.length;i++){ 	
				  if (isFatcaCheckElement[i].checked == true && ((isFatcaCheckElement[i].value ==="8") || (isFatcaCheckElement[i].value ==="9"))){ 
						isFatcaCheck = true;
				  }  
			  }
		  }
		  
		  var tabFormFatca = null;
		  if(isFatcaCheck){
			  tabFormFatca = document.getElementById("tabFormAssgnFatcaPF");
			  tabFormFatca.style.display = "";  
		  }else{

			  	var signatureDateFatcaClear = document.getElementsByName("registerConsumer.signatureDateFatca");
			  	
	        	for (var i=0;i<signatureDateFatcaClear.length;i++){ 	
	        		signatureDateFatcaClear[i].value="";
	        	}

	        	
	        	tabFormFatca = document.getElementById("tabFormAssgnFatcaPF");
	        	tabFormFatca.style.display = "none";

		  }
	}

	function loadFormCrs(){

		var isCrsCheckElement = document.getElementsByName("registerConsumer.isCrs"); 
		var isCrsCheck = false;


		  if(isCrsCheckElement!=null && isCrsCheck === false ){
			  for (var i=0;i<isCrsCheckElement.length;i++){ 	  
				  if (isCrsCheckElement[i].checked == true && isCrsCheckElement[i].value ==="true"){ 
					  isCrsCheck = true;
				  }  
			  }
		  }

		  var tabFormCrs = null;
		  if(isCrsCheck){
			  tabFormCrs = document.getElementById("tabFormAssgnCrsPF");
			  tabFormCrs.style.display = "";  
		  }else{

			  	var signatureDateCrsClear = document.getElementsByName("registerConsumer.signatureDateCrs");
			  	
	        	for (var i=0;i<signatureDateCrsClear.length;i++){ 	
	        		signatureDateCrsClear[i].value="";
	        	}

	        	
				tabFormCrs = document.getElementById("tabFormAssgnCrsPF");
				tabFormCrs.style.display = "none";

		  }
	}


	function loadFormW8W9FatcaPJ(){
		  try {
			  var custFatcaPjInUs = document.getElementsByName("registerConsumer.custFatcaPjInUs"); 
			  var custFatcaPjOutUs = document.getElementsByName("registerConsumer.custFatcaPjOutUs"); 
			  var custFatcaPjOwnrUs = document.getElementsByName("registerConsumer.custFatcaPjOwnrUs"); 
			  var tabFormW8W9 = null;
			  var isFatcaCheck = false;
			  
			  if(custFatcaPjInUs!=null && isFatcaCheck === false ){
				  for (var i=0;i<custFatcaPjInUs.length;i++){ 	  
					  if (custFatcaPjInUs[i].checked == true && custFatcaPjInUs[i].value ==="true"){ 
						  isFatcaCheck = true;
					  }  
				  }
			  }

			  if(custFatcaPjOutUs!=null && isFatcaCheck === false){
				  for (var i=0;i<custFatcaPjOutUs.length;i++){ 

					  if (custFatcaPjOutUs[i].checked == true && custFatcaPjOutUs[i].value ==="true"){ 
						  isFatcaCheck = true;
					  }  
				  }
			  }

			  if(custFatcaPjOwnrUs!=null && isFatcaCheck === false){
				  for (var i=0;i<custFatcaPjOwnrUs.length;i++){ 
					  if (custFatcaPjOwnrUs[i].checked == true && custFatcaPjOwnrUs[i].value ==="true"){ 
						  isFatcaCheck = true;
					  }  
				  }
			  }
			  
			  if(isFatcaCheck){
					tabFormW8W9 = document.getElementById("tabFormW8W9FatcaPJ");
					tabFormW8W9.style.display = "";  
			  }else{
				  var signatureDatew8Clear = document.getElementsByName("registerConsumer.signatureDateFatca");
			
		        	for (var i=0;i<signatureDatew8Clear.length;i++){ 	
		        		signatureDatew8Clear[i].value="";
		        	}

		        	var formTypeClear = document.getElementsByName("registerConsumer.formType");
		        	
		        	for (var i=0;i<formTypeClear.length;i++){ 	
		        		formTypeClear[i].checked = false;
		        		
		        	}
		        	
					tabFormW8W9 = document.getElementById("tabFormW8W9FatcaPJ");
					tabFormW8W9.style.display = "none";
			  }
		} catch (e) {
			alert(e);
		}
	}

	function loadFormSelfCertCrsPJ(){
		  try {
			  var custCrsPjAddrOutUs = document.getElementsByName("registerConsumer.custCrsPjAddrOutUs"); 
			  var custCrsPjEnfLiab = document.getElementsByName("registerConsumer.custCrsPjEnfLiab"); 
			  var custCrsPjInvstOut = document.getElementsByName("registerConsumer.custCrsPjInvstOut"); 
			  var tabFormSelfCertCrsPJ = null;
			  var isCrsCheck = false;
			  
			  if(custCrsPjAddrOutUs!=null && isCrsCheck === false ){
				  for (var i=0;i<custCrsPjAddrOutUs.length;i++){ 	  
					  if (custCrsPjAddrOutUs[i].checked == true && custCrsPjAddrOutUs[i].value ==="true"){ 
						  isCrsCheck = true;
					  }  
				  }
			  }

			  if(custCrsPjEnfLiab!=null && isCrsCheck === false){
				  for (var i=0;i<custCrsPjEnfLiab.length;i++){ 

					  if (custCrsPjEnfLiab[i].checked == true && custCrsPjEnfLiab[i].value ==="true"){ 
						  isCrsCheck = true;
					  }  
				  }
			  }

			  if(custCrsPjInvstOut!=null && isCrsCheck === false){
				  for (var i=0;i<custCrsPjInvstOut.length;i++){ 
					  if (custCrsPjInvstOut[i].checked == true && custCrsPjInvstOut[i].value ==="true"){ 
						  isCrsCheck = true;
					  }  
				  }
			  }
			  
			  if(isCrsCheck){
				  tabFormSelfCertCrsPJ = document.getElementById("tabFormSelfCertCrsPJ");
				  tabFormSelfCertCrsPJ.style.display = "";  
			  }else{
				  var signatureDateCrsClear = document.getElementsByName("registerConsumer.signatureDateCrs");
			
		        	for (var i=0;i<signatureDateCrsClear.length;i++){ 	
		        		signatureDateCrsClear[i].value="";
		        	}
		        	
		        	tabFormSelfCertCrsPJ = document.getElementById("tabFormSelfCertCrsPJ");
		        	tabFormSelfCertCrsPJ.style.display = "none";
			  }
		} catch (e) {
			alert(e);
		}
	}



	function loadViewBankerName(){

		 var soeidBankerName = $('input[name=registerConsumer.SOEIDBankerName]').val();
		 $('#bankerNameTd').html(soeidBankerName);  

	}



	function loadPageRegisterCustomerPJ(formType){

		try {
			loadViewBankerName();
			loadFormW8W9FatcaPJ();
			loadFormSelfCertCrsPJ();			
		} catch (e) {
			alert(e);
		}
	}

	function loadPageRegisterCustomerPF(formType){

		try {
			loadViewBankerName();
			loadFormCrs();
			loadFormFatca();			
		} catch (e) {
			alert(e);
		}
	}

	

	function searchAccuntGrbName(){

		  var contextPath='<%=request.getContextPath()%>';

		  
		  console.log(contextPath);
		  var accountGrbNumber = $('input[name=selectRegisterAccountMigrate.accountGrbNumber]').val();
		  console.log("AccountGrbName:" + accountGrbNumber);
		  $.ajax({  
			  	
			    type: "POST",  
			    url: contextPath+"/FrontController/NEWCPB.AccountMigration?method=searchGrbName",  
			    data: "selectRegisterAccountMigrate.accountGrbNumber=" + accountGrbNumber,  
			    success: function(response){  

			    	console.log("Setando Retorno");
			    	$('#grbNameTd').html(response);  
			    	
			    },  
			    error: function(e){  
			      alert('Error: ' + e);  
			    }  
			  }); 

	}

	function searchAccountCustodiaName(){

		  var contextPath='<%=request.getContextPath()%>';

		  
		  console.log(contextPath);
		  var accountCustodiaNumber = $('input[name=selectRegisterAccountMigrate.accountCustodiaNumber]').val();
		  console.log("accountCustodiaNumber:" + accountCustodiaNumber);
		  $.ajax({  
			  	
			    type: "POST",  
			    url: contextPath+"/FrontController/NEWCPB.AccountMigration?method=searchCustodiaName",  
			    data: "selectRegisterAccountMigrate.accountCustodiaNumber=" + accountCustodiaNumber,  
			    success: function(response){  

			    	console.log("Setando Retorno");
			    	$('#custodiaNameTd').html(response);  
			    	
			    },  
			    error: function(e){  
			      alert('Error: ' + e);  
			    }  
			  }); 

	}


	function loadAccountCustomerName(){

		 $('#grbNameTd').html( $('input[name=selectRegisterAccountMigrate.accountGrbName]').val());  
		 $('#custodiaNameTd').html( $('input[name=selectRegisterAccountMigrate.accountCustodiaName]').val()); 

		

	}

	function searchBankerName(){

		  var contextPath='<%=request.getContextPath()%>';

		  
		  console.log(contextPath);
		  var soeidBanker = $('input[name=registerConsumer.SOEIDBankerNumber]').val();
		  console.log("SOID:" + soeidBanker);
		  $.ajax({  
			  	
			    type: "POST",  
			    url: contextPath+"/FrontController/NEWCPB.RegisterDataCustomer?method=searchBanker",  
			    data: "registerConsumer.SOEIDBankerNumber=" + soeidBanker,  
			    success: function(response){  

			    	console.log("Setando Retorno");
			    	$('#bankerNameTd').html(response);  
			    	
			    },  
			    error: function(e){  
			      alert('Error: ' + e);  
			    }  
			  }); 

	}


	function searchSOEIDEmployeeName(){

		  var contextPath='<%=request.getContextPath()%>';

		  
		  console.log(contextPath);
		  var soeidEmployee = $('input[name=selectRegisterOfficerBanker.employeeSOEID]').val();
		  console.log("SOID Employee:" + soeidEmployee);
		  $.ajax({  
			  	
			    type: "POST",  
			    url: contextPath+"/FrontController/NEWCPB.Banker?method=searchEployeeName",  
			    data: "selectRegisterOfficerBanker.employeeSOEID=" + soeidEmployee,  
			    success: function(response){  

			    	console.log("Setando Retorno");

				  	var employeeName = document.getElementsByName("selectRegisterOfficerBanker.employeeName");
				  	
		        	for (var i=0;i<employeeName.length;i++){ 	
		        		employeeName[i].value=response;
		        	} 
			    	
			    },  
			    error: function(e){  
			      alert('Error: ' + e);  
			    }  
			  }); 

	}

	function searchSOEIDSupervisorName(){

		  var contextPath='<%=request.getContextPath()%>';

		  
		  console.log(contextPath);
		  var soeidSupervisor = $('input[name=selectRegisterOfficerBanker.supervisorSOEID]').val();
		  console.log("SOID Supervisor:" + soeidSupervisor);
		  $.ajax({  
			  	
			    type: "POST",  
			    url: contextPath+"/FrontController/NEWCPB.Banker?method=searchSupervisorName",  
			    data: "selectRegisterOfficerBanker.supervisorSOEID=" + soeidSupervisor,  
			    success: function(response){  

			    	console.log("Setando Retorno");

				  	var supervisorName = document.getElementsByName("selectRegisterOfficerBanker.supervisorName");
				  	
		        	for (var i=0;i<supervisorName.length;i++){ 	
		        		supervisorName[i].value=response;
		        	} 
			    	
			    },  
			    error: function(e){  
			      alert('Error: ' + e);  
			    }  
			  }); 

	}


	function searchSOEIDAssociateName(){

		  var contextPath='<%=request.getContextPath()%>';

		  
		  console.log(contextPath);
		  var soeidAssociate = $('input[name=selectRegisterOfficerBanker.associateSOEID]').val();
		  console.log("SOID Associate:" + soeidAssociate);
		  $.ajax({  
			  	
			    type: "POST",  
			    url: contextPath+"/FrontController/NEWCPB.Banker?method=searchAssociateName",  
			    data: "selectRegisterOfficerBanker.associateSOEID=" + soeidAssociate,  
			    success: function(response){  

			    	console.log("Setando Retorno");

				  	var associateName = document.getElementsByName("selectRegisterOfficerBanker.associateName");
				  	
		        	for (var i=0;i<associateName.length;i++){ 	
		        		associateName[i].value=response;
		        	} 
			    	
			    },  
			    error: function(e){  
			      alert('Error: ' + e);  
			    }  
			  }); 

	}


	

	function limitarTamanho(box, valor, campospan){
		var conta = valor - box.length;
		document.getElementById(campospan).innerHTML = "";
		if(box.length >= valor){
			document.getElementById(campospan).innerHTML = "Limite de caracteres excedido, máximo permitido: " + valor + ".";
			var clearComments = document.getElementsByName("selectRegister.comments");

        	for (var i=0;i<clearComments.length;i++){ 	
        		clearComments[i].value=clearComments[i].value.substr(0,valor);
        	}
		}	
	}

	
</script>

</head>