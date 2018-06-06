<script language="javascript">
<!--

//<-------------------Início do tratamento da máscara---------------->
/* <summary>
 * Aplica a máscara no campo. Decide qual tipo de máscara dependendo 
 * do tipo escolhido
 * </summary>
 * <param name="type_">tipo de mascara : 'number' ou 'char' </param>
 * <param name="parameter1_">mascara ou número de dígitos inteiros</param>
 * <param name="parameter2_">direção de preenchimento ou número de dígitos decimais</param>
 * <param name="parameter3_">caracter de separação de milhares ('false' para nenhum)</param>
 * <param name="parameter4_">controle em que será aplicada a máscara </param>*/
function MaskFieldPress (type_, parameter1_, parameter2_, parameter3_, parameter4_)
{
	var field;
	if (parameter4_ != null)
	{
		field = parameter4_;
	}
	else
	{
		field = event.srcElement;
	}
	
	if (type_.toUpperCase() == 'NUMBER')
	{
		NumberFieldPress( parameter1_, parameter2_, parameter3_, field);
	}
	else
	{
		CharFieldPress( parameter1_, parameter2_, field);
	}
}

/* <summary>
 * Aplica a máscara no campo
 * </summary>
 * <param name="field_">campo digitado pelo programador</param>*/
function CharFieldPress ( mask, direction, field )
{
	var fieldValue = field.value;
	var text = "";
    var strTmpMask = "";
    var strTmpField = "";
    var i = 0;
    var j = 0;

	if (direction == null)
		direction = "left";
	
	field.style.textAlign = direction;
  
	if(event.keyCode != 9)
	{
	    if(event.keyCode != 16)
	    {
	        if(event.keyCode != 8)
	        {
		        if( ( mask.length > 0) && (fieldValue.length > 0))
		        {
					fieldValue = RemoveMask( fieldValue );
			        i = InitializeCounter( direction, mask.length - 1 );
			        j = InitializeCounter( direction, fieldValue.length - 1 );
			        while( ContinueFormating( direction, i, j, mask.length, fieldValue.length ) )
			        {
				        strTmpMask = mask.charAt( i );
				        strTmpField = fieldValue.charAt( j );
				        if( IsConstant( strTmpMask ) )
				        {
					        text = Concatenate( direction, text, strTmpMask );
					        i = UpdateCount( direction, i );
				        }		
				        else
				        {
					        if(strTmpMask == "_" || strTmpMask == "N" || strTmpMask == "^")
					        {
						        if(ValidateCarac( strTmpMask, strTmpField))
						        {
	                                text = Concatenate( direction, text, strTmpField );
						        }
						        else
						        {
						            field.value = text;
						            return;
						        }
					        }
					        i = UpdateCount( direction, i );
					        j = UpdateCount( direction, j );
				        }
			        }
			        text = InsertFinalCharacters( direction, text, mask ,i );
		            field.value = text;
		        }
		    }
		}
	}
	else
	{
		field.select();
	}	
}

/* <summary>
 * Verifica se deve continuar inserindo a máscara no campo
 * </summary>
 * <param name="direction_">direção para digitação</param>
 * <param name="masklength_">tamanho da máscara</param>
 * <param name="i_">contador da máscara</param>*/
function ContinueInserting( direction_, masklength_, i_ )
{
    if( direction_ != "left" )
    {
        if( i_ <= masklength_ - 1 )
        {
            return true;
        }
        else
            return false;
    }
    else
    {
        if( i_ >= 0 )
        {
            return true;
        }
        else
            return false;
        }
}
/* <summary>
 * Insere caracteres constantes da máscara
 * </summary>
 * <param name="direction_">direção para digitação</param>
 * <param name="text_">valor atual do campo</param>
 * <param name="mask_">valor da máscara</param>
 * <param name="i_">contador da máscara</param>*/
function InsertFinalCharacters( direction_, text_, mask_, i_ )
{
    var isContinue = true;
    var tmp = "";
    var tmptext = "";
    while( ( ContinueInserting( direction_, mask_.length, i_ ) ) && ( isContinue ) )
    {
        tmp = mask_.charAt( i_ );
        if( IsConstant( tmp ) )
        {
            tmptext = Concatenate( direction_, tmptext, tmp );
            i_ = UpdateCount( direction_, i_ );
        }
        else
        {
            isContinue = false;
        }
    }
    text_ = Concatenate( direction_, text_, tmptext )
    return text_;
}
/* <summary>
 * Verifica se o caracter é o último da máscara
 * </summary>
 * <param name="direction_">direção para digitação</param>
 * <param name="mask_">valor da máscara</param>
 * <param name="fieldValue_">valor atual do campo</param>
 * <param name="i_">contador da máscara</param>
 * <param name="maskField_">tamanho da máscara</param>*/
function IsLastCharacter( direction_, mask_, fieldValue_, i_, maskField_ )
{
    if( direction_ != "left" )
    {
        if( ( fieldValue_.length + maskField_.length ) == mask_.length )
        {
            if( IsConstant( mask_.charAt( i_ ) ) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
            return false;
    }
    else
    {
       if( ( fieldValue_.length + maskField_.length ) == mask_.length )
        {
            if( IsConstant( mask_.charAt( 0 ) ) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
            return false;
    }
}
/* <summary>
 * Verifica se o caracter é uma constante da máscara
 * </summary>
 * <param name="value_">valor atual da máscara</param>*/
function IsConstant (valor)
{
	switch(valor)
	{
		case "/":
			return true;
		case ".":
			return true;
		case ",":
			return true;
		case "-":
			return true;
		case "(":
			return true;
		case ")":
			return true;
		case ":":
			return true;			
		default:
			return false;
	}
}
/* <summary>
 *  Retira as constantes da máscara
 *  </summary>
 *  <param name="value_">valor atual da máscara</param>*/
function RemoveMask( value_ )
{
    var withoutLiterals = "";
    for ( i = 0; i < value_.length; i++ )
    {
        if ( !IsConstant( value_.charAt( i ) ) )
        {
            withoutLiterals += value_.charAt( i );
        }
    }
    return withoutLiterals;
}

/* <summary>
 *  Valida se os caracteres digitados correspondem aos caracteres da máscara
 *  </summary>
 *  <param name="strTmpMask_">valor atual da máscara</param>
 *  <param name="strTmpField_">valor atual do campo</param>*/
function ValidateCarac ( strTmpMask_, strTmpField_ )
{
	var isValidCaracter = false;

	switch(strTmpMask_)
	{
		case "_":
			if( strTmpField_.search(/([a-z]|[A-Z]|[0-9])/) >= 0 )
			{
				isValidCaracter = true;
			}
			break;
		case "^":
			if( strTmpField_.search(/([a-z]|[A-Z])/) >= 0 )
			{
				isValidCaracter = true;
			}
			break;
		case "N":
			if( strTmpField_.search(/([0-9])/) >= 0 )
			{
				isValidCaracter = true;
			}
			break;
		default:
			isValidCaracter = false;
	}
		
	return isValidCaracter;
}
/* <summary>
 *  Inicializa os contadores
 *  </summary>
 *  <param name="direction_">direção para digitação</param>
 *  <param name="maxValue_">valor máximo</param>*/
function InitializeCounter ( direction_, maxValue_ ) 
{
    var returnValue = 0
	if( direction_ == "left")
	{
		returnValue = maxValue_;
	}
	return returnValue;
}

/* <summary>
 *  Verifica se o caracter é o último da máscara
 *  </summary>
 *  <param name="direction_">direção para digitação</param>
 *  <param name="i_">contador atual da máscara</param>
 *  <param name="j_">contador atual do campo</param>
 *  <param name="masklength_">tamanho da máscara</param>
 *  <param name="fieldlength_">tamanho do campo</param>*/
function ContinueFormating( direction_, i_, j_, masklength_, fieldlength_ )
{
    if( direction_ != "left")
    {
        if( ( i_ < masklength_ ) && ( j_ < fieldlength_ ) )
        {
            return true;
        }
        else
            return false;
    }
    else
    {
        if( ( i_ >= 0 ) && ( j_ >= 0 ) )
        {
            return true;
        }
        else
            return false;
    }
}
/* <summary>
 *  Concatena o valor da máscara com o campo digitado
 *  </summary>
 *  <param name="direction_">direção para digitação</param>
 *  <param name="text_">valor atual do campo</param>
 *  <param name="textToInsert_">texto a ser concatenado</param>*/
function Concatenate( direction_, text_, textToInsert_ )
{
    var valueConcatenated = new String( text_ );
    if( direction_ != "left" )
    {
        valueConcatenated += textToInsert_;
    }
    else
    {
        valueConcatenated = textToInsert_ + text_;
    } 
    return valueConcatenated;
}
/* <summary>
 *  Atualiza o contador
 *  </summary>
 *  <param name="direction_">direção para digitação</param>
 *  <param name="count_">contador (máscara ou campo)</param>*/
function UpdateCount( direction_, count_ )
{
    if( direction_ != "left" )
    {
        count_++;
    }
    else
    {
        count_--;
    }
    return count_;
}
/* <summary>
 *  Verifica se o último caracter é uma constante na máscara
 *  </summary>
 *  <param name="direction_">direção para digitação</param>
 *  <param name="mask_">máscara</param>*/
function LastIsConstant ( direction_, mask_ )
{
    var lastCharacter = "";
    if( direction_ != "left")
    {
        lastCharacter = mask_.charAt( mask_.length - 1);
        if( IsConstant( lastCharacter ) )
        {
            return true;
        }
        else
            return false;
    }
    else
    {
        lastCharacter = mask_.charAt( 0 )
        if( IsConstant( lastCharacter ) )
        {
            return true;
        }
        else
            return false;
    }        
}    
//<-----------------------Fim do tratamento da máscara---------------------->

//----------------Início do tratamento da formatação de números------------->
/* <summary>
 *  Formata o número digitado
 *  </summary>
 *  <param name="field_">campo digitado pelo programador</param>*/
function NumberFieldPress ( integerDigits, decimalDigits, strThousandSeparator, field )
{
 /*var field = event.srcElement;*/
 var number = field.value;
 //var integerDigits = field.integerDigits;
 //var decimalDigits = field.decimalDigits;
 //var strThousandSeparator =  field.thousandSeparator;
 thousandSeparator =  ((strThousandSeparator == null)||(strThousandSeparator.toUpperCase() == "FALSE")) ? false : true;
 var intPart = "";
 var decimalPart = "";
 var tmpExcess = "";
 var tmpIntPart = "";
 var tmpDecimalPart = "";
 var decimalNumber = "";
 var lastCharacter = "";
 var hasComma = false;
    var tmpNumber = "";
    var tmpNegative = "";
 
 if(event.keyCode != 9)
 {
     if(event.keyCode != 16)
     {
         if(event.keyCode != 8)
         {
             if( ( number != null ) && ( number.length > 0 ) )
             {
     if(number.charAt(0) == "-")
     {
      tmpNegative = number.charAt(0);
      number = number.substring(1, number.length );
     }
     for( i = 0; number.charAt( i ); i++)
              {
                  if(number.charAt(i).search(/([0-9])/) >= 0)
               {
                tmpNumber += number.charAt(i);
               }
               else if(tmpNumber.length > 0 && 
                 number.charAt(i).search(/([.])/) >= 0)
                 {
                  tmpNumber += number.charAt(i);
                 }
              }
              number = tmpNumber;



              lastCharacter = number.length - 1;
              if( number.charAt( lastCharacter ) == "." )
              {
               hasComma = true;
              }
              
              numberParts = number.split( "." );
              intPart = numberParts[0];
              if( numberParts.length > 1)
              {
               decimalPart = numberParts[1];
              }
              
              //handling integer part
              if( intPart.length > integerDigits )
              {
               for( j = 0; j < integerDigits; j++)
               {
                 tmpIntPart += intPart.charAt( j );
               }
               intPart = tmpIntPart;
               for( i = integerDigits; i < number.length; i++)
               {
                    tmpExcess += number.charAt( i );
               }
               
              }




              
              if( tmpExcess.length > 0 || decimalPart.length > 0)
              {
               tmpDecimalPart = tmpExcess.split( "," );
               tmpExcess = tmpDecimalPart[0];
               if(tmpDecimalPart.length > 1)
               {
                decimalPart = tmpDecimalPart[1];
               }
               var tmpDecimal = "";
               tmpDecimal = tmpExcess + decimalPart;
               if(tmpDecimal.length > decimalDigits)
               {
                for( i = 0; i < decimalDigits; i++)
                {
                 decimalNumber += tmpDecimal.charAt( i );
                }
               }
               else
               {
                decimalNumber = tmpDecimal;
               }

              }
              if( ( decimalDigits > 0 ) && ( ( decimalNumber != null && decimalNumber.length  > 0 ) || hasComma ) )  
              {
               field.value = tmpNegative + intPart + "." + decimalNumber;
              }
              else 
              {
                field.value = tmpNegative + intPart;
              }
             }
             else
             {
              field.value = "";
             }
      }
  }
 }
 else
 {
  field.select();
 }
}
/* <summary>
*  Retira os pontos do campo atual
*  </summary>
*  <param name="number_">número atual no campo</param>*/
function RemovePoints ( number_ )
{
 var withoutpoints = new String( number_ )
 withoutpoints = withoutpoints.replace(/[.]/g, "");
 withoutpoints = withoutpoints.replace(/[-]/g, "");
 return withoutpoints; 
}
/* <summary>
*  Coloca o ponto de separador de milhar
*  </summary>
*  <param name="intPart_">parte inteira do número digitado</param>*/
function FormatThousandSeparation ( intPart_ )
{
 var finalText = "";
 var countAux = 0;
 if(intPart_ != null)
 {
  for(i = (intPart_.length - 1); i >= 0; i--)
  {
   if(countAux == 3)
   {
    finalText = "," + finalText;
    countAux = 0;
   }
   finalText = intPart_.charAt( i ) + finalText;
   countAux++;
  }
 }
 return finalText;
}

function NumberFieldPressWithTS ( integerDigits, decimalDigits, field )
{
 /*var field = event.srcElement;*/
 var number = field.value;
 //var integerDigits = field.integerDigits;
 //var decimalDigits = field.decimalDigits;
 //var strThousandSeparator =  field.thousandSeparator;

 var intPart = "";
 var decimalPart = "";
 var tmpExcess = "";
 var tmpIntPart = "";
 var tmpDecimalPart = "";
 var decimalNumber = "";
 var lastCharacter = "";
 var hasComma = false;
    var tmpNumber = "";
    var tmpNegative = "";
 
 if(event.keyCode != 9)
 {
     if(event.keyCode != 16)
     {
         if(event.keyCode != 8)
         {
             if( ( number != null ) && ( number.length > 0 ) )
             {
     if(number.charAt(0) == "-")
     {
      tmpNegative = number.charAt(0);
      number = number.substring(1, number.length );
     }
     for( i = 0; number.charAt( i ); i++)
              {
                  if(number.charAt(i).search(/([0-9])/) >= 0)
               {
                tmpNumber += number.charAt(i);
               }
               else if(tmpNumber.length > 0 && 
                 number.charAt(i).search(/([.])/) >= 0)
                 {
                  tmpNumber += number.charAt(i);
                 }
              }
              number = tmpNumber;



              lastCharacter = number.length - 1;
              if( number.charAt( lastCharacter ) == "." )
              {
               hasComma = true;
              }
              
              numberParts = number.split( "." );
              intPart = numberParts[0];
              if( numberParts.length > 1)
              {
               decimalPart = numberParts[1];
              }
              
              //handling integer part
              if( intPart.length > integerDigits )
              {
               for( j = 0; j < integerDigits; j++)
               {
                 tmpIntPart += intPart.charAt( j );
               }
               intPart = tmpIntPart;
               for( i = integerDigits; i < number.length; i++)
               {
                    tmpExcess += number.charAt( i );
               }
               
              }
              
              if( tmpExcess.length > 0 || decimalPart.length > 0)
              {
               tmpDecimalPart = tmpExcess.split( "," );
               tmpExcess = tmpDecimalPart[0];
               if(tmpDecimalPart.length > 1)
               {
                decimalPart = tmpDecimalPart[1];
               }
               var tmpDecimal = "";
               tmpDecimal = tmpExcess + decimalPart;
               if(tmpDecimal.length > decimalDigits)
               {
                for( i = 0; i < decimalDigits; i++)
                {
                 decimalNumber += tmpDecimal.charAt( i );
                }
               }
               else
               {
                decimalNumber = tmpDecimal;
               }

              }
              if( ( decimalDigits > 0 ) && ( ( decimalNumber != null && decimalNumber.length  > 0 ) || hasComma ) )  
              {
               field.value = tmpNegative + FormatThousandSeparation(intPart) + "." + decimalNumber;
              }
              else 
              {
                field.value = tmpNegative + FormatThousandSeparation(intPart);
              }
             }
             else
             {
              field.value = "";
             }
      }
  }
 }
 else
 {
  field.select();
 }
}

//-----------------Fim do tratamento da formatação de números--------------->


</script>
