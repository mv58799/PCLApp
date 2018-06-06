<head>
<META http-equiv="Expires" content="-1" />
<META http-equiv="Pragma" content="no-cache" />
<META http-equiv="Cache-Control" content="no-cache" />
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.citibank.ods.common.form.BaseForm"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>

<script language="javascript">

	// BEGIN: Control to avoid taking back command
	history.go(1);
	// END: Control to avoid taking back command	
	
	//BEGIN:
	//Functions and global variable to avoid http requests "in a row".
	//Another http request can´t be triggered before response of a prior request
	var bAlreadySubmitted;
	bAlreadySubmitted= false;

	//Sets the global variable used to control if a request to the web server
	//has already been triggered
	function setSubmitted()
	{
		bAlreadySubmitted= true;
		setWaiting();
	}
	
	//Sets the global variable to false.
	function resetSubmitted()
	{
		bAlreadySubmitted= false;
	}
	
	//Gets a boolean value meaning if a request to the web server has already been triggered
	function isSubmitted()
	{
		return bAlreadySubmitted;
	}
	
	//Show the default error message in case of a new request to the web server
	//before the response of a prior request
	function showStandardWaitMessage()
	{
		alert('Aguarde!!\nO sistema esta processando a sua requisicao.');
	}
	//END: End of code to avoid requests "in a row"

	// COloca a tela em estado de espera
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

	// Desabilita tela
	function disableWindow()
	{
		setWaiting();
	}

	// remove o estado de espera da tela
	function enableWindow()
	{
		document.body.removeChild( waitingDiv );
	}

	// realiza o submit das telas de acordo com a ação escolhida
	function submitAction( action, validate, shouldConfirm,orderBy)
	{
		if (validate == true)
		{
			if (!checkMandatoryFields())
				return false;
		}
		
		var pageName = "<%=request.getParameter("pageName")%>";
		var modulo = pageName.substring(0,pageName.indexOf("."));
		var nome   = pageName.substring(pageName.indexOf(".")+1);
		var searchInputFields = [<%=request.getParameter("searchInputFields")%>];		
		<%String pageName = request.getParameter("pageName");
		String name = pageName.substring(pageName.indexOf(".") + 1);
		String formName = name + "Form";
		BaseForm form = (BaseForm) session.getAttribute(formName);
		String backUrl = form.getBackURL();%>
		var backpage = "<%=backUrl%>";
		
		if ( isSubmitted() )
		{
			showStandardWaitMessage();
		}
		else
		{
			setSubmitted();
		  
			if( action == 'back' )
			{
				// desabilita os campos de input nas telas utilizadas para busca
				if (searchInputFields != null)
				{
					for (i = 0; i < searchInputFields.length; i++)
					{
						var controls = document.getElementsByName(searchInputFields[i]);
						if (controls.length > 0)
						{
							control = controls[0];	
							control.disabled = true; 
						}
					}
				}

				document.forms[0].action = backpage;

				document.forms[0].backURL.disabled = true;	
			}
			else if (action.search(/PreparedSearch/) > -1)
			{
				document.forms[0].action = pageName + "." + action;
				document.forms[0].backURL.disabled = true;
			}
			else if (nome.search(/Detail/) > -1)
			{
				if ( action == 'approve' )
				{
					document.forms[0].action = pageName + ".Approval.Approve";
					document.forms[0].backURL.disabled = true;
				}
				else if ( action == 'reprove' )
				{
					if (!confirm('Os dados serão reprovados. Pressione Ok para confirmar.')){
						enableSubmit();
						return;
					}
					document.forms[0].action = pageName + ".Approval.Reprove";
					document.forms[0].backURL.disabled = true;
				}
				else if ( action == 'insert' )
				{
					
					document.forms[0].action = pageName + ".Insert.Execute";
					document.forms[0].backURL.disabled = true;
				}
				else if ( action == 'update' )
				{
					document.forms[0].action = pageName + ".Update.Execute";
					document.forms[0].backURL.disabled = true;
				}
				else if ( action == 'delete' )
				{
					if (!confirm('Os dados serão excluídos. Pressione Ok para confirmar.')){
						enableSubmit();
						return;
					}
					document.forms[0].action = pageName + ".Delete.Execute";
					document.forms[0].backURL.disabled = true;
				}
			}
			else if (nome.search(/List/) > -1)
			{
				var pageNameSemTipo = pageName.substring(0,pageName.indexOf("List"));
		  	
			  	if ( action == 'insert' )
				{
					document.forms[0].action = pageNameSemTipo + "Detail.Insert.Show";
					document.forms[0].backURL.disabled = false;
				}
				else if ( action == 'update' )
				{
					document.forms[0].action = pageNameSemTipo + "Detail.Update.Show";
					document.forms[0].backURL.disabled = false;
				}	
				else if ( action == 'delete' )
				{
					document.forms[0].action = pageNameSemTipo + "Detail.Delete.Show";
					document.forms[0].backURL.disabled = false;
				}
				else if ( action == 'detail' )
				{
					document.forms[0].action = pageNameSemTipo + "Detail.Consult.Show";
					document.forms[0].backURL.disabled = false;
				}
				else if ( action == 'approve' )
				{
					document.forms[0].action = pageNameSemTipo + "Detail.Approval.Show";
					document.forms[0].backURL.disabled = false;
				}
				else if ( action == 'list' )
				{
					if (orderBy != null){
					  document.forms[0].orderBy.value=orderBy;
					}					
					document.forms[0].backURL.disabled = true;
					document.forms[0].executingList.value='S';
					document.forms[0].action = pageNameSemTipo + "List.List.Execute";
				}
				else if ( action == 'deleteFromList' )
				{
					if (!confirm('Os dados serão excluídos. Pressione Ok para confirmar.')){
						enableSubmit();
						return;
					}
					document.forms[0].action = pageNameSemTipo + "Detail.Delete.Execute";
					document.forms[0].backURL.disabled = true;
				}
				
			}

 			extraActions(action);
			 
		
			//Workaround para resolver bug do IE que não se adequa às tags META para "no cache"
			//Solução consiste em acrescentar variável aleatória que é passada por "get" garantindo 
			//diferença entre as URL de cada chamada e por consequencia evitando cache.
			
			//document.forms[0].action= document.forms[0].action + "?urlDistinguisher=" + Math.random();
			document.forms[0].action= document.forms[0].action + "?urlDistinguisher=" + <%=new java.util.Date().getTime()%>
			document.forms[0].submit();
		}
	}
		

	// 
	function enableSubmit(){
		enableWindow();
		resetSubmitted();
	}

	// esconde os dados do grid
	function hideGrid()
	{
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
		newTableOuterHtml = tableOuterHtml.substring(0,headerEndPosition);
		newTableOuterHtml = newTableOuterHtml + "</table>";
				
		table.insertAdjacentHTML("afterEnd", newTableOuterHtml );	


	}

	// limpa os dados dos campos da tela
	function clearInputs()
	{
		// Recuperando campos de input
		var inputs = document.getElementsByTagName("input");   
  		
		// Percorrendo cada input e limpando
		for(i = 0; i < inputs.length; i++)
		{
			
			if (inputs[i].type == "text")
			{
				if (!inputs[i].disabled)
				{
					inputs[i].value = "";
				}
			}
			
			if (inputs[i].type == "file")
			{
				if (!inputs[i].disabled)
				{
					inputs[i].value = "";
				}
			}

			if (inputs[i].type == "checkbox")
			{
				if (!inputs[i].disabled)
				{
					inputs[i].checked = false;
				}
			}
		}

		// Recuperando campos de select
		var selects = document.getElementsByTagName("select");   
  		
		// Percorrendo cada select e limpando
		for(i = 0; i < selects.length; i++)
		{
			if (!selects[i].disabled)
			{
				selects[i].options[0].selected = true;
			}
		}  

	}
	
	function clearAllInputs()
	{
		// Recuperando campos de input
		var inputs = document.getElementsByTagName("input");   
  		
		// Percorrendo cada input e limpando
		for(i = 0; i < inputs.length; i++)
		{
			if (inputs[i].type == "text")
			{
				inputs[i].value = "";				
			}

			if (inputs[i].type == "checkbox")
			{
				inputs[i].checked = false;				
			}
		}

		// Recuperando campos de select
		var selects = document.getElementsByTagName("select");   
  		
		// Percorrendo cada select e limpando
		for(i = 0; i < selects.length; i++)
		{
			if (!selects[i].disabled)
			{
				selects[i].options[0].selected = true;
			}
		}  

	}

	// desabilita os botões configurados
	function disableButtons(status)
	{
		var controlNames = [<%=request.getParameter("controlNames")%>];
		for (i = 0; i < controlNames.length; i++)
		{
			var controls = document.getElementsByName(controlNames[i]);
			if (controls.length > 0)
			{
				control = controls[0];
				control.disabled = status; 
			}
		}
	}


	// desabilita os campos configurados
	function disableFields(group, status)
	{
		var controlFields = [<%=request.getParameter("fieldGroups")%> ];
		for (disableFieldsGroupCounter = 0; disableFieldsGroupCounter < controlFields.length; disableFieldsGroupCounter++)
		{
			if (controlFields[disableFieldsGroupCounter][0]==group)
			{
				for (disableFieldsControlCounter=1; disableFieldsControlCounter<controlFields[disableFieldsGroupCounter].length; disableFieldsControlCounter++)
				{
					var control = document.getElementsByName(controlFields[disableFieldsGroupCounter][disableFieldsControlCounter]);
					control[disableFieldsGroupCounter].disabled = status;
				}
			}
		}
	}


	// desabilita os botões de aprovação configurados
	function disableApproveButtons(userID, opernCode, status)
	{
		<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
String userId = user != null ? user.getUserID() : null;%>
		var loggedUser = '<%=userId%>';
		var controlNames = [<%=request.getParameter("approvalControlNames")%>];

		// possui apenas 3 botões, em ordem: Aprovar, Reprovar, Update
		// testa aprovar:
		var controls = document.getElementsByName(controlNames[0]);
		if (controls.length > 0)
		{
			var control = controls[0];
			enable = (loggedUser != userID) && (status);
			control.disabled = !enable; 
		}
		// testa reprovar:
		controls = document.getElementsByName(controlNames[1]);
		if (controls.length > 0)
		{
			var control = controls[0];
			control.disabled = !status; 
		}
		// testa update:
		controls = document.getElementsByName(controlNames[2]);
		if (controls.length > 0)
		{
			var control = controls[0];
			if (opernCode == 'E') // não é possível fazer update na exclusão
			{
				control.disabled = true;
			}
			else
			{
				enable = (loggedUser == userID) && (status);
				control.disabled = !enable;
			}
		}
	}

	// testa o preenchimento dos campos configurados
	function checkMandatoryFields()
	{
		validationMessageArea.innerHTML = "";
		var retorno = true;
		var controlNames = [<%=request.getParameter("mandatoryControlNames")%>];
		var ControlLabels = [<%=request.getParameter("mandatoryControlLabels")%>];
		
		for (i = 0; i < controlNames.length; i++)
		{
			var controls = document.getElementsByName(controlNames[i]);
			if (controls.length > 0)
			{
				control = controls[0];
				if (control.value == "")
				{
					validationMessageArea.innerHTML += 'Erro: Campo "' + ControlLabels[i] + '" é obrigatório.<br>';
					validationMessageArea.style.display = "inline";
					tableError.style.display = "inline";
					retorno = false;
				}
			}
			i++;
		}
		return retorno;
	}

	function clearPage()
	{
		clearInputs();
		validationMessageArea.innerHTML = "";
		validationMessageArea.style.display = "none";
		tableError.style.display = "none";

		if (document.getElementById("serverMessageArea")!= null){
			serverMessageArea.style.display = "none";
		}
		if (<%=request.getParameter("gridId")%> != null)
		{
			hideGrid();
		}
		if (<%=request.getParameter("controlNames")%> !=null)
		{
			disableButtons(true);
		}
	}
	
	function clearCustPage(){
	    
	    clearInputs();
	    
	    //Limpa dados do Banker
	    document.forms[0].offcrNbrSrc.value = "";	    
        document.forms[0].offcrText.value = "";
        document.forms[0].offcrClear.value = "true";
		
		validationMessageArea.innerHTML = "";
		validationMessageArea.style.display = "none";
		tableError.style.display = "none";

		if (document.getElementById("serverMessageArea")!= null){
			serverMessageArea.style.display = "none";
		}
		if (<%=request.getParameter("gridId")%> != null)
		{
			hideGrid();
		}
		if (<%=request.getParameter("controlNames")%> !=null)
		{
			disableButtons(true);
		}
		
		//submitAction('CustomerPrvt.CustomerPrvtList.List.Show');
	  
	}
	
	function clearAllPage()
	{
		clearAllInputs();
		validationMessageArea.innerHTML = "";
		validationMessageArea.style.display = "none";
		tableError.style.display = "none";
		
		if (document.getElementById("serverMessageArea")!= null){
			serverMessageArea.style.display = "none";
		}		
		if (<%=request.getParameter("gridId")%> != null)
		{
			hideGrid();
		}
		if (<%=request.getParameter("controlNames")%> !=null)
		{
			disableButtons(true);
		}
	}

	//Função clear que limpa o resultSet no servidor
	function clearResultSetInServer()
	{
		clearAllInputs();
		validationMessageArea.innerHTML = "";
		validationMessageArea.style.display = "none";
		tableError.style.display = "none";

		if (document.getElementById("serverMessageArea")!= null){
			serverMessageArea.style.display = "none";
		}
		if (<%=request.getParameter("controlNames")%> !=null)
		{
			disableButtons(true);
		}
	}
	

	// BEGIN: Controle de back no Browser
		history.go(1);
	// END

	var message="Função desabilitada!";

	// BEGIN: Desabilitando botão direito
	function clickIE4(){
	if (event.button==2){
	event.button=0;
	return false;
	}
	}

	if (document.all&&!document.getElementById){
	document.onmousedown=clickIE4;
	}

	document.oncontextmenu=new Function("return false")
	// END

	// BEGIN: Desabilitando Ctrl N
	document.onkeydown=checkKeys
	function checkKeys()
	{
	if (event.ctrlKey && event.keyCode==78)
	{
	  event.keyCode=0;
	  return false;
	}
	}

	//Função desabilita o botão buscar quando a backurl é nula.
	function disabledBack(){

		if (document.forms[0].backBtn != null){
			if ('<%=backUrl%>' == 'null'){
				document.forms[0].backBtn.style.display= 'none';
	
			}
			else{		
				document.forms[0].backBtn.style.display= 'inline';		
			}	
		}
	}
	
	var fieldsWithMask = [<%=request.getParameter("fieldsWithMask")%>];

	var addEvent = function addEvent()
	{ 
		var controlName = event.srcElement;
		for (j = 0; j < fieldsWithMask.length; j++)
		{
			if (controlName == fieldsWithMask[j][0])
			{
				var innerType = fieldsWithMask[j][1];
				var innerParameter1 = fieldsWithMask[j][2];
				var innerParameter2 = fieldsWithMask[j][3];
				var innerParameter3 = fieldsWithMask[j][4];
				MaskFieldPress(innerType,innerParameter1,innerParameter2,innerParameter3);
				return;
			}
		}
	};

	function formatFields()
	{
		if (fieldsWithMask[0] != null)
		{
			for (i = 0; i < fieldsWithMask.length; i++)
			{
				var controls = document.getElementsByName(fieldsWithMask[i][0]);
				if (controls.length > 0)
				{
					control = controls[0];
					var type = fieldsWithMask[i][1];
					var parameter1 = fieldsWithMask[i][2];
					var parameter2 = fieldsWithMask[i][3];
					var parameter3 = fieldsWithMask[i][4];
					
					if (type == 'CPF/CNPJ')
					{
						maskCpfCnpj(control);
					}
					else
					{
						MaskFieldPress(type,parameter1,parameter2,parameter3,control);
					}
				}	
			}
		}
	}

	function unformatFields()
	{
		var fieldsWithMask = [<%=request.getParameter("fieldsWithMask")%>];
		if (fieldsWithMask != null)
		{
			for (i = 0; i < fieldsWithMask.length; i++)
			{
				var controls = document.getElementsByName(fieldsWithMask[i][0]);
				if (controls.length > 0)
				{
					control = controls[0];
					control.value = RemoveMask(control.value);
				}	
			}
		}
	}

	function maskCpfCnpj(control)
	{	
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
	
	 function formatTime(e){
       // pega elemento do evento
       var input = e.srcElement || e.target,
       // pega valor
       value = input.value;

       // tira os caracters invalidos e quebra string em partes N:

       value = value.replace(/\D/g,'').split('');

       // pega tamanho
       vL = value.length;
       for(var i = 0; i < vL; i++){
       // se for 1 ou 3 adiciona depois do valor ':'
         if(i == 1 || i == 3){
           value[i] += ':';
         }
       }

       // junta tudo e coloca como valor
       input.value = value.join('');
     }
     
     function formataDataHora(obj){
		var valor = obj.value;
		if(valor.length == 2) obj.value = obj.value + "/";
		else if(valor.length == 5) obj.value = obj.value + "/";
		else if(valor.length == 10) obj.value = obj.value + " ";
		else if(valor.length == 13) obj.value = obj.value + ":";
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

	function setup()
	{
		disabledBack();
		formatFields();
		currentSubSheet();
	}
	window.attachEvent("onload",setup);
	
	function currentSubSheet(){
	if ('<%=form.getCurrentSubSheet()%>' != 'null'){
    	var currentMenuItem = 'MenuItem' + '<%=form.getCurrentSubSheet()%>';
		var obj = document.getElementById(currentMenuItem);
		obj.className = 'ODS_ThemeOfficeSelectedMenu';
	}	

	}
	
	function formataMoeda(objTextBox, SeparadorMilesimo, SeparadorDecimal, e){
    var sep = 0;
    var key = '';
    var i = j = 0;
    var len = len2 = 0;
    var strCheck = '0123456789';
    var aux = aux2 = '';
    var whichCode = (window.Event) ? e.which : e.keyCode;
    // 13=enter, 8=backspace as demais retornam 0(zero)
    // whichCode==0 faz com que seja possivel usar todas as teclas como delete, setas, etc
    if ((whichCode == 13) || (whichCode == 0) || (whichCode == 8))
    	return true;
    key = String.fromCharCode(whichCode); // Valor para o código da Chave


    if (strCheck.indexOf(key) == -1)
    	return false; // Chave inválida
    len = objTextBox.value.length;
    for(i = 0; i < len; i++)
        if ((objTextBox.value.charAt(i) != '0') && (objTextBox.value.charAt(i) != SeparadorDecimal))
        	break;
    aux = '';
    for(; i < len; i++)
        if (strCheck.indexOf(objTextBox.value.charAt(i))!=-1)
        	aux += objTextBox.value.charAt(i);
    aux += key;
    len = aux.length;
    if (len == 0)
    	objTextBox.value = '';
    if (len == 1)
    	objTextBox.value = '0'+ SeparadorDecimal + '0' + aux;
    if (len == 2)
    	objTextBox.value = '0'+ SeparadorDecimal + aux;
    if (len > 2) {
        aux2 = '';
        for (j = 0, i = len - 3; i >= 0; i--) {
            if (j == 3) {
                aux2 += SeparadorMilesimo;
                j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
        }
        objTextBox.value = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--)
        	objTextBox.value += aux2.charAt(i);
        objTextBox.value += SeparadorDecimal + aux.substr(len - 2, len);
    }
    return false;
}
	function hiddenBox(objImg,idBox){
		endImg = objImg.src;
		objVisibleBox = document.getElementsByName("visibleBox")[0];
		objNoVisibleBox = document.getElementsByName("noVisibleBox")[0];	
		txtVisible = objVisibleBox.value;
		txtNoVisibleBox = objNoVisibleBox.value;
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









	// END
</script>

</head>