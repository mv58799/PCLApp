String.prototype.isArgument=function()
{
	return /^([a-zA-Z]){1,}=([0-9]){1,}$/.test(this);
}


/*
call this function just work like window.open(url,name,feature);
however, for IE5.0+, it will open a showModelessDialog window;
and For Gecko(Mozilla or Netscape), the child window will stay on top focus untill user close it.
*/


function dialog(url,name,feature,isModal)
{
 if(url==null){return false;}
 url = url
 if(name==null){name=""}
 if(feature==null){feature=""};
 if(window.showModelessDialog)
 {
  	var WindowFeature = new Object();
	WindowFeature["width"] = 800;
	WindowFeature["height"]  =600;
	WindowFeature["left"]  = "";
	WindowFeature["top"]  =  "";
	WindowFeature["resizable"]  = "";

	if(feature !=null && feature!="")
	{
      feature = ( feature.toLowerCase()).split(",");
	
      for(var i=0;i< feature.length;i++)
		{
          if( feature[i].isArgument())
			{
           var featureName = feature[i].split("=")[0];
	   var featureValue = feature[i].split("=")[1];
	  
	   if(WindowFeature[featureName]!=null){WindowFeature[featureName] = featureValue; }
			}
		}
	}
 
  if(WindowFeature["resizable"]==1 || WindowFeature["resizable"]=="1" || WindowFeature["resizable"].toString().toLowerCase()=="yes"){WindowFeature["resizable"] = "resizable:1;minimize:1;maximize:1;"}
  if(WindowFeature["left"]!=""){WindowFeature["left"] ="dialogLeft:" +  WindowFeature["left"] +"px;";}
  if(WindowFeature["top"]!=""){WindowFeature["top"] ="dialogTop:" +  WindowFeature["Top"] +"px;"; }
  if(window.ModelessDialog ==null){window.ModelessDialog = new Object() ; };
  if(name!="")
  {
   if(window.ModelessDialog[name]!=null && !window.ModelessDialog[name].closed )
   {
     window.ModelessDialog[name].focus();
	 return window.ModelessDialog[name];
   }
  }
	var F = WindowFeature["left"] +WindowFeature["top"] +  "dialogWidth:"+WindowFeature["width"] +" px;dialogHeight:"+WindowFeature["height"]+"px;center:1;help:0;" + WindowFeature["resizable"] +"status:0;unadorned:0;edge: raised; ;border:thick;"
	if(isModal)
	{
		window.showModalDialog(url,self,F);
		return false;
	}
	else
	{
		window.ModelessDialog[name] = window.showModelessDialog(url,self,F);
		return window.ModelessDialog[name];
	}	
 }
 else
 {
   if(document.getBoxObjectFor)
   {
	

	 if(isModal)
	 {		 
		 var Modal = window.open(url,name,"modal=1," + feature);
		 var ModalFocus = function()
		 {
			if(!Modal.closed){Modal.focus();}
			else{Modal =null;window.removeEventListener(ModalFocus,"focus");ModalFocus = null; };					
		 }
		 window.addEventListener( "focus",ModalFocus, false ); 
		 return false;
	 }
	 else
	 {
		return window.open(url,name,"modal=1," + feature);
	 }	 
   }
   else
   { 
     return window.open(url,name,feature);
   }
   //
 }
 return null;
}

function dialog2(url,name,feature,isModal)
{
 if(url==null){return false;}
 url = url
 if(name==null){name=""}
 if(feature==null){feature=""};
 if(window.showModelessDialog)
 {
  	var WindowFeature = new Object();
	WindowFeature["width"] = 800;
	WindowFeature["height"]  =600;
	WindowFeature["left"]  = "";
	WindowFeature["top"]  =  "";
	WindowFeature["resizable"]  = "";

	if(feature !=null && feature!="")
	{
      feature = ( feature.toLowerCase()).split(",");
	
      for(var i=0;i< feature.length;i++)
		{
          if( feature[i].isArgument())
			{
           var featureName = feature[i].split("=")[0];
	   var featureValue = feature[i].split("=")[1];
	  
	   if(WindowFeature[featureName]!=null){WindowFeature[featureName] = featureValue; }
			}
		}
	}
 
  if(WindowFeature["resizable"]==1 || WindowFeature["resizable"]=="1" || WindowFeature["resizable"].toString().toLowerCase()=="yes"){WindowFeature["resizable"] = "resizable:1;minimize:1;maximize:1;"}
  if(WindowFeature["left"]!=""){WindowFeature["left"] ="dialogLeft:" +  WindowFeature["left"] +"px;";}
  if(WindowFeature["top"]!=""){WindowFeature["top"] ="dialogTop:" +  WindowFeature["Top"] +"px;"; }
  if(window.ModelessDialog ==null){window.ModelessDialog = new Object() ; };
  if(name!="")
  {
   if(window.ModelessDialog[name]!=null && !window.ModelessDialog[name].closed )
   {
     window.ModelessDialog[name].focus();
	 return window.ModelessDialog[name];
   }
  }
	var F = WindowFeature["left"] +WindowFeature["top"] +  "dialogWidth:"+WindowFeature["width"] +" px;dialogHeight:"+WindowFeature["height"]+"px;center:1;help:0;" + WindowFeature["resizable"] +"status:0;unadorned:0;edge: raised; ;border:thick;"
	if(isModal)
	{
		
		return window.showModalDialog(url,self,F);
	}
	else
	{
		window.ModelessDialog[name] = window.showModelessDialog(url,self,F);
		return window.ModelessDialog[name];
	}	
 }
 else
 {
   if(document.getBoxObjectFor)
   {
	

	 if(isModal)
	 {		 
		 var Modal = window.open(url,name,"modal=1," + feature);
		 var ModalFocus = function()
		 {
			if(!Modal.closed){Modal.focus();}
			else{Modal =null;window.removeEventListener(ModalFocus,"focus");ModalFocus = null; };					
		 }
		 window.addEventListener( "focus",ModalFocus, false ); 
		 return Modal;
	 }
	 else
	 {
		return window.open(url,name,"modal=1," + feature);
	 }	 
   }
   else
   { 
     return window.open(url,name,feature);
   }
   //
 }
 return null;
}

function modal2(url,feature)
{
	
	return dialog2(url,"",feature,true);
}
   
function modal(url,feature)
{
	dialog(url,"",feature,true);
	return false;
}