package com.citibank.ods.common.util;

import java.util.HashMap;

public class BusinessConstants {

	/*
	 * Objetivo: Armazenar informações estáticas de classificação de conta corrente
	 * Projeto: RDIP
	 * Responsável: Eversystems
	 */
	
	public static int RECOMMENDED_ACCOUNT_CODE = 1;
	public static int TRADING_ACCOUNT_CODE = 2;
	public static int DESIGNATED_ACCOUNT_CODE = 3;
	public static int BLANK_ACCOUNT_CODE = 4;
	
	public static HashMap<Integer, String> CURRENT_ACCOUNT_INFORMATION = null;
	static {
		CURRENT_ACCOUNT_INFORMATION = new HashMap<Integer, String>();
		CURRENT_ACCOUNT_INFORMATION.put(RECOMMENDED_ACCOUNT_CODE, "Recommended");
		CURRENT_ACCOUNT_INFORMATION.put(TRADING_ACCOUNT_CODE, "Trading");
		CURRENT_ACCOUNT_INFORMATION.put(DESIGNATED_ACCOUNT_CODE, "Designated");
		CURRENT_ACCOUNT_INFORMATION.put(BLANK_ACCOUNT_CODE, "");
	}

	public static String getCurrentAccountDescription(int accountCode){
		return CURRENT_ACCOUNT_INFORMATION.get(accountCode);
	}
	
	/*
	 * Fim - RDIP - Classificação de Conta
	 * */

	public static int KNOW_EXP_EXCEEDED_LIMIT = 2000;
	
}
