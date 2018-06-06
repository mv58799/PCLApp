//
//©2002-2007 Accenture. All rights reserved. 
//
/**
* Classe que compacta uma função do cadastrada no SG
* 
* @see package com.citibank.ods.persistence.bg.dao.rdb.oracle;
* @version 1.0
* @author marcelo.s.oliveira,June 1 , 2007
* 
*/
package com.citibank.ods.common.security;

public class SGFunctionTrimmer {
	
	public String compactSGFunction(String path_) {
		String nonCompactedSGFunction = path_;
		String compactedWord = "";
		String compactedSGFunction = "";
		String moreCompactedWord = "";
		String moreCompactedSGFunction = "";
		nonCompactedSGFunction = nonCompactedSGFunction.substring(1,nonCompactedSGFunction.length());
		//Abreviando para as tres primeiras silabas após um caractere em upperCase
		for(int i= 0;i<nonCompactedSGFunction.length();i++){
			if (i!=0 && nonCompactedSGFunction.substring(i-1,i).equals(".")){
				compactedSGFunction = compactedSGFunction + ".";
			}
			if (Character.isUpperCase(nonCompactedSGFunction.charAt(i)) ){
				if( Character.isLowerCase(nonCompactedSGFunction.charAt(i+1))){
					compactedSGFunction = compactedSGFunction + nonCompactedSGFunction.substring(i, i+3);
					i=i+3;
				}else{
					compactedSGFunction = compactedSGFunction + nonCompactedSGFunction.substring(i, i+2);
					i = i+2;
				}
			}
		}
		//Substituindo segundo legenda :
		/*  Mov-->M;Hist-->H;Det-->D;Lis --> L;Ins-->I;Upd-->U;Del-->DE;Con-->C;Sea-->S
		    App-->A;Exe-->EX;Sho-->SH;Rep-->RE
		 **/
		compactedSGFunction = compactedSGFunction.replaceAll("Mov", "M");
		compactedSGFunction = compactedSGFunction.replaceAll("His", "H");
		compactedSGFunction = compactedSGFunction.replaceAll("Det", "D");
		compactedSGFunction = compactedSGFunction.replaceAll("Lis", "L");
		compactedSGFunction = compactedSGFunction.replaceAll("Ins", "I");
		compactedSGFunction = compactedSGFunction.replaceAll("Upd", "U");
		compactedSGFunction = compactedSGFunction.replaceAll("Del", "DE");
		compactedSGFunction = compactedSGFunction.replaceAll("Con", "C");
		compactedSGFunction = compactedSGFunction.replaceAll("Sea", "S");
		compactedSGFunction = compactedSGFunction.replaceAll("App", "A");
		compactedSGFunction = compactedSGFunction.replaceAll("Exe", "EX");
		compactedSGFunction = compactedSGFunction.replaceAll("Sho", "SH");
		compactedSGFunction = compactedSGFunction.replaceAll("Rep", "RE");
		return compactedSGFunction;
	}
	
}
