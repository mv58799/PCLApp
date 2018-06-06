/*
 * Created on Jan 28, 2007
 */
package com.citibank.ods;

/**
 * @author User
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class Globals
{
  /**
   * Constantes utilizadas pelo JSP para formatação de dados através do tag
   * Bean:write
   */
  public abstract class FormatKeys
  {
    public static final String C_FORMAT_CURRENCY_2DEC = "view.format.currency.2dec";

    public static final String C_FORMAT_TAX_6DEC = "view.format.tax.6dec";

    public static final String C_FORMAT_DATE_DDMMYYYY = "view.format.date.ddMMyyyy";

    public static final String C_FORMAT_DATETIME_DDMMYYYY_HHMM = "view.format.datetime.ddMMyyyy.hhmm";

    public static final String C_FORMAT_NUMBER_INTEGER = "view.format.number.integer";
  }

  /**
   * Constantes utilizadas pelas funcionalidades para formatação de dados
   */
  public abstract class FuncionalityFormatKeys
  {
    public static final String C_FORMAT_DATE_DDMMYYYY = "dd/MM/yyyy";
    
    public static final String C_FORMAT_DATE_DDMM = "dd/MM";

    public static final String C_FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.S";

    public static final String C_FORMAT_PRESENTATION = "dd/MM/yyyy HH:mm:ss";

    public static final String C_FORMAT_DATETIME_DDMMYYYY_HHMM = "dd/MM/yyyy HH:mm";
    
    public static final String C_FORMAT_DATETIME_HHMM = "HH:mm";
  }

  /**
   * Constantes utilizadas por propriedades do JSP e camadas de persistência
   * para indicadores booleanos
   */
  public abstract class BooleanIndicatorKeys
  {
    public static final String C_BOOLEAN_IND_SIM = "S";

    public static final String C_BOOLEAN_IND_NAO = "N";
  }
  
  public abstract class GetVersionSystem 
  {
  	  public static final String C_VERSION_SYSTEM ="v.1.03";
  }  	
  	

}