//***********************************
//Validations of application Citibank
//***********************************

//This function only number (Navigator Firefox and IE)

function onlyNumber(keyEvt){
	
	if(typeof(keyEvt) == 'undefined')

	var keyEvt = window.event;

	var code = keyEvt.charCode;
	var key = keyEvt.keyCode;
	
	if (navigator.appName=='Netscape'){
		if  (code >= 48 && code <= 57){
			return true; 
		}else if ( code == 0 && (key ==8 || key==9 )){
			return true;
		}else if ( code == 0 && key==46){
			return true;
		}else{ 
			return false; } 
	
	}else if (navigator.appName=='Microsoft Internet Explorer')	{
		if  (key >= 48 && key <= 57){
			return true; 
		}else if  (key ==8 || key==9 ){
			return true;
		}else{
			return false;}
	}
	
}


//This function input mask in some fields.
function mascara(o,f){
    v_obj=o;
    v_fun=f;
    setTimeout("execmascara()",1);
}

function execmascara(){
    v_obj.value=v_fun(v_obj.value);
}

function telefone(v){
    v=v.replace(/^(\d\d)(\d)/g,"($1) $2");
    v=v.replace(/(\d{4})(\d)/,"$1-$2");    
    return v;
}

function cpf(v){
    v=v.replace(/(\d{3})(\d)/,"$1.$2");    
    v=v.replace(/(\d{3})(\d)/,"	$1.$2");                    
    v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2"); 
    return v;
}

function cep(v){
    v=v.replace(/^(\d{5})(\d)/,"$1-$2");
    return v;
}

function cnpj(v){
    v=v.replace(/^(\d{2})(\d)/,"$1.$2");
    v=v.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3");
    v=v.replace(/\.(\d{3})(\d)/,".$1/$2");
    v=v.replace(/(\d{4})(\d)/,"$1-$2");
    return v;
}

//This function clear spaces in texbox field

function Trim(str){
    while (str.charAt(0) == " ")
        str = str.substr(1,str.length -1);
    while (str.charAt(str.length-1) == " ")
        str = str.substr(0,str.length-1);

    return str;
} 


//This function verify if e-mail is valid

function verifyEmail(field) {

    var email = (Trim(field.value));
    if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))) {
        alert("Email invalido. Por favor, verifique!");
    }		
}

//This function verify if time is valid

function verifyTime(field) {
    
    var expReg = /^([0-1]\d|2[0-3]):[0-5]\d$/;
    if ((!field.value.match(expReg)) && (field.value!='')) {
        alert('Hora inválida');
        field.focus();
    }
}

// Mask to date

function mascara_Data(field) {
    tammax=8;
    if(typeof(tecla) == 'undefined');
    var key = window.event;
    var code = (tecla.which ? tecla.which : tecla.keyCode ? tecla.keyCode : tecla.charCode);
    var vr = field.value;
    vr = vr.replace( "/", "" );
    vr = vr.replace( "/", "" );
    var tam = vr.length;
    
    if (tam < tammax) { tam = vr.length + 1; }
    if (code == 8) { tam = tam - 1; }
    tam = tam - 1;
    if ( (tam >= 2) && (tam < 3) ) {
        vr = vr.substr( 0, tam - 0 ) + '/' + vr.substr( tam - 0, 2 ); }
    if ( (tam >= 3) && (tam < 4) ) {
         vr = vr.substr( 0, tam - 1 ) + '/' + vr.substr( tam - 1, 2 ); }
    if (tam == 4) {
         vr = vr.substr( 0, tam - 2 ) + '/' + vr.substr( tam - 2, 2 ) + '/' + vr.substr( tam - 0, 5 ); }
    if (tam == 5) {
         vr = vr.substr( 0, tam - 3 ) + '/' + vr.substr( tam - 3, 2 ) + '/' + vr.substr( tam - 1, 6 ); }
    if (tam == 6) {
         vr = vr.substr( 0, tam - 4 ) + '/' + vr.substr( tam - 4, 2 ) + '/' + vr.substr( tam - 2, 7 ); }
    if (tam == 7) {
         vr = vr.substr( 0, tam - 5 ) + '/' + vr.substr( tam - 5, 2 ) + '/' + vr.substr( tam - 3, 8 ); }
   
    field.value = vr;

}

//Function verify date

function verifyDate(field) {
   
    var expReg = /^(([0-2]\d|[3][0-1])\/([0]\d|[1][0-2])\/[1-2][0-9]\d{2})$/;
    if ((!field.value.match(expReg)) && (field.value!='')) {
        alert('Data inválida');
        campo.focus();
    } 
}

//This function show message confirmation to click in cancel button

function btnCancel(){
    if(confirm("Confirma os dados introduzidos?") ){
        return true;
    }else {
        alert("Operação cancelada!")
        return false;
    }
}



