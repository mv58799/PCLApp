package com.citibank.ods.common.entity;


/**
 * 
 * Classe base para as calsse do tipo Entity do sistema ODS. 
 * Contém as constantes de valores de campos de controle em comum.
 *  
 */
public class BaseODSEntity extends BaseEntity
{

  public static final String C_REC_STAT_CODE_ACTIVE = "A";
  public static final String C_REC_STAT_CODE_INACTIVE = "I";
  public static final String C_REC_STAT_CODE_PENDING = "P";
  public static final String C_OPERN_CODE_DELETE = "E";
  public static final String C_OPERN_CODE_INSERT = "I";
  public static final String C_OPERN_CODE_UPDATE = "A";
  public static final String C_ADDR_TYPE_CODE_RESIDENTIAL = "R";
  public static final String C_ADDR_TYPE_CODE_COMERCIAL = "C";
  public static final String C_ADDR_TYPE_CODE_OUTROS = "O";
  public static final String C_INDICATOR_YES = "S";
  public static final String C_INDICATOR_NO = "N";
  public static final String C_PLYR_ROLE_TYPE_CODE_ADMINISTRATOR = "ADM";
  public static final String C_PLYR_ROLE_TYPE_CODE_MANAGER = "MNG";
  public static final String C_PLYR_ROLE_TYPE_CODE_AUDITOR = "AUD";
  public static final String C_PLYR_ROLE_TYPE_CODE_LIQUIDATOR = "LIQ";
  public static final String C_PLYR_ROLE_TYPE_CODE_CONTROLLER = "CTL";
  public static final String C_PLYR_ROLE_TYPE_CODE_CUSTODIANTE = "CST";
  public static final String C_PLYR_ROLE_TYPE_CODE_EMISSOR = "ISS";
  public static final String C_DATA_TYPE_DATE = "D";
  public static final String C_DATA_TYPE_NBR = "N";
  public static final String C_DATA_TYPE_ALPHA = "A";
  public static final String C_SIT_CONTRACT_INACTIVE ="IN";
  public static final String C_SIT_CONTRACT_ACTIVE = "AC";
  public static final String C_SIT_CONTRACT_PENDING = "PE";
  public static final String C_SIT_CONTRACT_BLOCKED = "BL";
  public static final String C_PRODUCT_CREDIT_LINE = "L";
  public static final String C_PRODUCT_CREDIT_COMMITMENT= "C";
  public static final String C_PROD_STAT_CODE_ACTIVE = "A";
  public static final String C_PROD_STAT_CODE_INACTIVE = "I";
  public static final String C_PROD_STAT_CODE_CLOSED  = "E";
  public static final String  C_TRANSACTION_DOC  = "1";
  public static final String C_TRANSACTION_TED = "2";
  public static final String C_TRANSACTION_OTHERS = "3";
  public static final String C_LEGAL_CLASS_CODE_15 = "15";
  public static final String C_LEGAL_CLASS_CODE_16 = "16";
  public static final String C_LEGAL_CLASS_CODE_19 = "19";
  public static final String C_LEGAL_CLASS_CODE_28 = "28";
  public static final String C_LEGAL_CLASS_CODE_30 = "30";
  public static final String C_LEGAL_CLASS_CODE_57 = "57";
  public static final String C_LEGAL_CLASS_CODE_58 = "58";
  public static final String C_LEGAL_CLASS_CODE_59 = "59";
  public static final String C_LEGAL_CLASS_CODE_71 = "71";
  public static final String C_LEGAL_CLASS_CODE_84 = "84";
  public static final String C_LEGAL_CLASS_CODE_101 = "101";
  public static final String C_LEGAL_CLASS_CODE_201 = "201";
  public static final String C_LEGAL_CLASS_CODE_301 = "301";
  public static final String C_LEGAL_CLASS_CODE_401 = "401";
  public static final String C_LEGAL_CLASS_CODE_603 = "603";
  public static final String C_LEGAL_CLASS_CODE_703 = "703";
  public static final String C_LEGAL_CLASS_CODE_801 = "801";
  public static final String C_LEGAL_CLASS_CODE_511 = "511";
  public static final String C_LEGAL_CLASS_CODE_500 = "500";
  public static final String C_LEGAL_CLASS_CODE_501 = "501";
  public static final String C_CUST_PRVT_STAT_ACTIVE = "A";
  public static final String C_CUST_PRVT_STAT_INACTIVE = "I";
  public static final String C_EMISSOR_RENDA_FIXA_FAMILE_CODE = "10";

}