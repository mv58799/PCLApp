package com.citibank.ods.common.util;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.BaseTbgOfficerEntity;
import com.citibank.ods.entity.pl.BaseTplRelationPrvtEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * Classe utilizada para a decodificação das constantes do sistema ODS
 * 
 * @see package com.citibank.ods.common.util;
 * @version 1.0
 * @author bruno.zanetti,Mar 14, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class ODSConstraintDecoder extends BaseConstraintDecoder
{

  private static final String C_REC_STAT_CODE = "REC_STAT_CODE";

  private static final String C_REC_STAT_TEXT = "REC_STAT_TEXT";

  private static final String C_REC_STAT_DESCRIPTION_ACTIVE = "Ativo";

  private static final String C_REC_STAT_DESCRIPTION_INACTIVE = "Inativo";

  private static final String C_REC_STAT_DESCRIPTION_PENDING = "Pendente";

  private static final String C_OPERN_CODE = "OPERN_CODE";

  private static final String C_OPERN_TEXT = "OPERN_TEXT";

  private static final String C_OPERN_DESCRIPTION_INSERT = "Inserção";

  private static final String C_OPERN_DESCRIPTION_DELETE = "Exclusão";

  private static final String C_OPERN_DESCRIPTION_UPDATE = "Alteração";

  private static final String C_ADDR_TYPE_CODE = "ADDR_TYPE_CODE";

  private static final String C_ADDR_TYPE_TEXT = "ADDR_TYPE_TEXT";

  private static final String C_ADDR_TYPE_DESCRIPTION_RESIDENTIAL = "Residencial";

  private static final String C_ADDR_TYPE_DESCRIPTION_COMERCIAL = "Comercial";

  private static final String C_ADDR_TYPE_DESCRIPTION_OUTROS = "Outros";

  private static final String C_OFFCR_STAT_CODE = "OFFCR_STAT_CODE";

  private static final String C_OFFCR_STAT_TEXT = "OFFCR_STAT_TEXT";

  private static final String C_OFFCR_CAT_CODE = "OFFCR_CAT_CODE";

  private static final String C_OFFCR_CAT_TEXT = "OFFCR_CAT_TEXT";

  private static final String C_RELTN_PRMT_CODE = "RELTN_PRMT_CODE";

  private static final String C_RELTN_PRMT_TEXT = "RELTN_PRMT_TEXT";

  private static final String C_OFFCR_STAT_TEXT_INCLUDED = "Incluído";

  private static final String C_OFFCR_STAT_TEXT_ACTIVE = "Ativo";

  private static final String C_OFFCR_CAT_TEXT_INTERNAL = "Interna";

  private static final String C_OFFCR_CAT_TEXT_EXTERNAL = "Externo";

  private static final String C_OFFCR_CAT_TEXT_TELEMARK = "Telemark";

  private static final String C_RELTN_PRMT_TEXT_1 = "1";

  private static final String C_RELTN_PRMT_TEXT_2 = "2";

  private static final String C_RELTN_PRMT_TEXT_3 = "3";

  private static final String C_RELTN_PRMT_TEXT_4 = "4";

  private static final String C_RELTN_PRMT_TEXT_5 = "5";

  private static final String C_RELTN_PRMT_TEXT_6 = "6";

  private static final String C_RELTN_PRMT_TEXT_7 = "7";

  private static final String C_INDICATOR_NAME_YES = "Sim";

  private static final String C_INDICATOR_NAME_NO = "Não";

  private static final String C_INDICATOR_CODE = "INDICATOR_CODE";

  private static final String C_INDICATOR_TEXT = "INDICATOR_TEXT";

  private static final String C_PLYR_ROLE_TYPE_CODE = "PLYR_ROLE_TYPE_CODE";

  private static final String C_PLYR_ROLE_TYPE_TEXT = "PLYR_ROLE_TYPE_TEXT";

  private static final String C_PLYR_ROLE_TYPE_DESCRIPTION_ADMINISTRATOR = "Administrador";

  private static final String C_PLYR_ROLE_TYPE_DESCRIPTION_MANAGER = "Gestor";

  private static final String C_PLYR_ROLE_TYPE_DESCRIPTION_AUDITOR = "Auditor";

  private static final String C_PLYR_ROLE_TYPE_DESCRIPTION_LIQUIDATOR = "Liquidante";

  private static final String C_PLYR_ROLE_TYPE_DESCRIPTION_CONTROLLER = "Controlador";

  private static final String C_PLYR_ROLE_TYPE_DESCRIPTION_CUSTODIANTE = "Custodiante";
  
  private static final String C_PLYR_ROLE_TYPE_DESCRIPTION_EMISSOR = "Emissor";
  
  private static final String C_DATA_TYPE_NBR = "Número";

  private static final String C_DATA_TYPE_DATE = "Data";

  private static final String C_DATA_TYPE_ALPHA = "Texto";

  private static final String C_DATA_TYPE_CODE = "DATA_TYPE_CODE";

  private static final String C_DATA_TYPE_TEXT = "DATA_TYPE_TEXT";

  private static final String C_SIT_CONTRACT_CODE = "SIT_CONTRACT_CODE";

  private static final String C_SIT_CONTRACT_TEXT = "SIT_CONTRACT_TEXT";

  private static final String C_SIT_CONTRACT_INACTIVE = "Inativo";

  private static final String C_SIT_CONTRACT_ACTIVE = "Ativo";

  private static final String C_SIT_CONTRACT_PENDING = "Pendente";

  private static final String C_SIT_CONTRACT_BLOCKED = "Bloqueado";

  private static final String C_INDICATOR_TRUE = "true";

  private static final String C_INDICATOR_FALSE = "false";

  private static final String C_INDICATOR_ON = "on";

  private static final String C_INDICATOR_OFF = "";

  private static final String C_PRODUCT_CREDIT_LINE = "LINE";

  private static final String C_PRODUCT_CREDIT_COMMITMENT = "COMMITMENT";

  private static final String C_CLASS_TYPE_CREDIT_CODE = "CLASS_TYPE_CREDIT_CODE";

  private static final String C_CLASS_TYPE_CREDIT_TEXT = "CLASS_TYPE_CREDIT_TEXT";

  private static final String C_PROD_STAT_CODE = "PROD_STAT_CODE";

  private static final String C_PROD_STAT_TEXT = "PROD_STAT_TEXT";

  private static final String C_PROD_STAT_CODE_CLOSED = "ENCERRADO";

  private static final String C_PROD_STAT_CODE_INACTIVE = "INATIVO";

  private static final String C_PROD_STAT_CODE_ACTIVE = "ATIVO";

  private static final String C_TRANSACTION_CODE = "TRANSACTION_CODE";
  
  private static final String C_ACCT_TYPE_CODE = "ACCT_TYPE_CODE";
  
  private static final String C_ACCT_TYPE_TEXT = "ACCT_TYPE_TEXT";

  private static final String C_TRANSACTION_TEXT = "TRANSACTION_TEXT";

  private static final String C_TRANSACTION_DOC = "Doc";

  private static final String C_TRANSACTION_TED = "Ted";

  private static final String C_TRANSACTION_OTHERS = "Outros";

  private static final String C_PROD_LEGAL_CLASS_CODE = "PROD_LEGAL_CLASS_CODE";

  private static final String C_PROD_LEGAL_CLASS_TEXT = "PROD_LEGAL_CLASS_TEXT";

  private static final String C_LEGAL_CLASS_TEXT_15 = "CGMI BRAZIL";

  private static final String C_LEGAL_CLASS_TEXT_16 = "FHL Investment LTD";

  private static final String C_LEGAL_CLASS_TEXT_19 = "CITC Trading S.A.";

  private static final String C_LEGAL_CLASS_TEXT_28 = "Citi Cp Mercantil";

  private static final String C_LEGAL_CLASS_TEXT_30 = "Citipar Participações";

  private static final String C_LEGAL_CLASS_TEXT_57 = "Banco Citibank S.A.";

  private static final String C_LEGAL_CLASS_TEXT_58 = "BRAZIL CREDICARD S.A";

  private static final String C_LEGAL_CLASS_TEXT_59 = "FNC Comercio Ltda -";

  private static final String C_LEGAL_CLASS_TEXT_71 = "Citibank Cia Hipotec";

  private static final String C_LEGAL_CLASS_TEXT_84 = "CITIBANK CARDS";

  private static final String C_LEGAL_CLASS_TEXT_101 = "Citibank N.A. - Sao";

  private static final String C_LEGAL_CLASS_TEXT_201 = "Banco Citibank S.A. ";

  private static final String C_LEGAL_CLASS_TEXT_301 = "Citibank D.T.V.M. S.";

  private static final String C_LEGAL_CLASS_TEXT_401 = "Citibank C.C.T.V.M";

  private static final String C_LEGAL_CLASS_TEXT_603 = "FNC Comercio Ltda -";

  private static final String C_LEGAL_CLASS_TEXT_703 = "Citibank Leasing S.A";

  private static final String C_LEGAL_CLASS_TEXT_801 = "Citibank Seguros S.A";

  private static final String C_LEGAL_CLASS_TEXT_511 = "Citicorp Leasing International Inc.";

  private static final String C_LEGAL_CLASS_TEXT_500 = "Citibank N.A. IBF";

  private static final String C_LEGAL_CLASS_TEXT_501 = "Citibank N.A. Nassau";

  private static final String C_CUST_PRVT_STAT_ACTIVE = "Ativo";

  private static final String C_CUST_PRVT_STAT_INACTIVE = "Inativo";

  private static final String C_CUST_PRVT_STAT_TEXT = "CUST_PRVT_STAT_TEXT";

  private static final String C_CUST_PRVT_STAT_CODE = "CUST_PRVT_STAT_CODE";
  
  private static final String C_ORIGIN_YES = "Consumer";
  
  private static final String C_ORIGIN_NO =  "Private";

  /**
   * 
   * "Método de decodificação de códigos de status dos registros. Este método
   * deve ser utilizado quando se deseja decodificar códigos de status de
   * registro retornados em uma consulta em lista. Este método retorna um Data
   * Set com o conjunto de códigos e descrições com o qual se deve fazer um
   * Outer Join para que seja realizada a decodificação "
   * 
   * @return Data Set contendo os códigos e descrições dos status de registro
   *         existentes
   */
  public static DataSet decodeRecStatus()
  {
    String[] names = { C_REC_STAT_CODE, C_REC_STAT_TEXT };
    String[] codes = { BaseODSEntity.C_REC_STAT_CODE_ACTIVE,
                      BaseODSEntity.C_REC_STAT_CODE_INACTIVE,
                      BaseODSEntity.C_REC_STAT_CODE_PENDING };
    String[] values = { C_REC_STAT_DESCRIPTION_ACTIVE,
                       C_REC_STAT_DESCRIPTION_INACTIVE,
                       C_REC_STAT_DESCRIPTION_PENDING };
    DataSet result = new DecoderDataSet( names, codes, values );

    return result;
  }

  /**
   * 
   * "Método que realiza a decodificação de um código de status de registro,
   * retornando a descrição do código passado como parâmetro. Retorna null caso
   * seja passado um código inexistente."
   * 
   * @param code_ - Código de status de registro que deve ser decodificado
   * @return Descrição do código contido no parâmetro code_. Retorna null caso
   *         não exista descrição para o código passado.
   * @exception
   * @see
   */
  public static String decodeRecStatus( String code_ )
  {
    if ( code_.equals( BaseODSEntity.C_REC_STAT_CODE_ACTIVE ) )
    {
      return C_REC_STAT_DESCRIPTION_ACTIVE;
    }
    else if ( code_.equals( BaseODSEntity.C_REC_STAT_CODE_INACTIVE ) )
    {
      return C_REC_STAT_DESCRIPTION_INACTIVE;
    }
    else if ( code_.equals( BaseODSEntity.C_REC_STAT_CODE_PENDING ) )
    {
      return C_REC_STAT_DESCRIPTION_PENDING;
    }

    return null;
  }

  /**
   * 
   * "Método de decodificação de códigos de operação. Este método deve ser
   * utilizado quando se deseja decodificar códigos de operação retornados em
   * uma consulta em lista. Este método retorna um Data Set com o conjunto de
   * códigos e descrições com o qual se deve fazer um Outer Join para que seja
   * realizada a decodificação "
   * 
   * @return Data Set contendo os códigos e descrições das operações existentes
   */
  public static DataSet decodeOpern()
  {
    String[] names = { C_OPERN_CODE, C_OPERN_TEXT };
    String[] codes = { BaseODSEntity.C_OPERN_CODE_INSERT,
                      BaseODSEntity.C_OPERN_CODE_DELETE,
                      BaseODSEntity.C_OPERN_CODE_UPDATE };
    String[] values = { C_OPERN_DESCRIPTION_INSERT, C_OPERN_DESCRIPTION_DELETE,
                       C_OPERN_DESCRIPTION_UPDATE };
    DataSet result = new DecoderDataSet( names, codes, values );

    return result;
  }

  /**
   * 
   * "Método que realiza a decodificação de um código de operação, retornando a
   * descrição do código passado como parâmetro. Retorna null caso seja passado
   * um código inexistente."
   * 
   * @param code_ - Código de operação que deve ser decodificado
   * @return Descrição do código contido no parâmetro code_. Retorna null caso
   *         não exista descrição para o código passado.
   * @exception
   * @see
   */
  public static String decodeOpern( String code_ )
  {
    if ( BaseODSEntity.C_OPERN_CODE_INSERT.equals( code_ ) )
    {
      return C_OPERN_DESCRIPTION_INSERT;
    }
    else if ( BaseODSEntity.C_OPERN_CODE_DELETE.equals( code_ ) )
    {
      return C_OPERN_DESCRIPTION_DELETE;
    }
    else if ( BaseODSEntity.C_OPERN_CODE_UPDATE.equals( code_ ) )
    {
      return C_OPERN_DESCRIPTION_UPDATE;
    }

    return null;
  }

  /**
   * 
   * "Método que realiza a recodificação de um código de operação, retornando o
   * código da descrição passada como parâmetro. Retorna null caso seja passado
   * um código inexistente."
   * 
   * @param code_ - Descrição da operação que deve ser recodificada
   * @return Código da descrição contida no parâmetro text_. Retorna null caso
   *         não exista código para a descrição passada.
   * @exception
   * @see
   */
  public static String recodeOpern( String text_ )
  {
    if ( C_OPERN_DESCRIPTION_INSERT.equals( text_ ) )
    {
      return BaseODSEntity.C_OPERN_CODE_INSERT;
    }
    else if ( C_OPERN_DESCRIPTION_DELETE.equals( text_ ) )
    {
      return BaseODSEntity.C_OPERN_CODE_DELETE;
    }
    else if ( C_OPERN_DESCRIPTION_UPDATE.equals( text_ ) )
    {
      return BaseODSEntity.C_OPERN_CODE_UPDATE;
    }

    return null;
  }

  /**
   * 
   * "Método de decodificação de códigos de tipo de endereço. Este método deve
   * ser utilizado quando se deseja decodificar códigos de tipo de endereço
   * retornados em uma consulta em lista. Este método retorna um Data Set com o
   * conjunto de códigos e descrições com o qual se deve fazer um Outer Join
   * para que seja realizada a decodificação "
   * 
   * @return Data Set contendo os códigos e descrições dos tipos de endereços
   *         existentes
   */
  public static DataSet decodeAddrType()
  {
    String[] names = { C_ADDR_TYPE_CODE, C_ADDR_TYPE_TEXT };
    String[] codes = { BaseODSEntity.C_ADDR_TYPE_CODE_COMERCIAL,
                      BaseODSEntity.C_ADDR_TYPE_CODE_RESIDENTIAL,
                      BaseODSEntity.C_ADDR_TYPE_CODE_OUTROS };
    String[] values = { C_ADDR_TYPE_DESCRIPTION_COMERCIAL,
                       C_ADDR_TYPE_DESCRIPTION_RESIDENTIAL,
                       C_ADDR_TYPE_DESCRIPTION_OUTROS };
    DataSet result = new DecoderDataSet( names, codes, values );

    return result;

  }

  /**
   * 
   * "Método que realiza a decodificação de um código de tipo de endereço,
   * retornando a descrição do código passado como parâmetro. Retorna null caso
   * seja passado um código inexistente."
   * 
   * @param code_ - Código de status de registro que deve ser decodificado
   * @return Descrição do código contido no parâmetro code_. Retorna null caso
   *         não exista descrição para o código passado.
   * @exception
   * @see
   */
  public static String decodeAddrType( String code_ )
  {
    if ( code_.equals( BaseODSEntity.C_ADDR_TYPE_CODE_COMERCIAL ) )
    {
      return C_ADDR_TYPE_DESCRIPTION_COMERCIAL;
    }
    else if ( code_.equals( BaseODSEntity.C_ADDR_TYPE_CODE_RESIDENTIAL ) )
    {
      return C_ADDR_TYPE_DESCRIPTION_RESIDENTIAL;
    }
    else if ( code_.equals( BaseODSEntity.C_ADDR_TYPE_CODE_OUTROS ) )
    {
      return C_ADDR_TYPE_DESCRIPTION_OUTROS;
    }

    return null;

  }

  /**
   * 
   * "Método que realiza a decodificação de um código de tipo de categoria de
   * officer, retornando a descrição do código passado como parâmetro. Retorna
   * null caso seja passado um código inexistente."
   * 
   * @param code_ - Código de categoria de officer que deve ser decodificado
   * @return Descrição do código contido no parâmetro code_. Retorna null caso
   *         não exista descrição para o código passado.
   * @exception
   * @see
   */
  public static DataSet decodeOffcrCatCode()
  {
    String[] names = { C_OFFCR_CAT_CODE, C_OFFCR_CAT_TEXT };
    String[] codes = { BaseTbgOfficerEntity.C_OFFCR_CAT_CODE_INTERNAL,
                      BaseTbgOfficerEntity.C_OFFCR_CAT_CODE_EXTERNAL,
                      BaseTbgOfficerEntity.C_OFFCR_CAT_CODE_TELEMARK };
    String[] values = { C_OFFCR_CAT_TEXT_INTERNAL, C_OFFCR_CAT_TEXT_EXTERNAL,
                       C_OFFCR_CAT_TEXT_TELEMARK };
    DataSet result = new DecoderDataSet( names, codes, values );

    return result;

  }

  /**
   * 
   * "Método que realiza a decodificação de um código de tipo de categoria de
   * officer, retornando a descrição do código passado como parâmetro. Retorna
   * null caso seja passado um código inexistente."
   * 
   * @param code_ - Código de categoria de officer que deve ser decodificado
   * @return Descrição do código contido no parâmetro code_. Retorna null caso
   *         não exista descrição para o código passado.
   * @exception
   * @see
   */
  public static String decodeOffcrCatCode( String code_ )
  {
    if ( code_.equals( BaseTbgOfficerEntity.C_OFFCR_CAT_CODE_INTERNAL ) )
    {
      return C_OFFCR_CAT_TEXT_INTERNAL;
    }
    else if ( code_.equals( BaseTbgOfficerEntity.C_OFFCR_CAT_CODE_EXTERNAL ) )
    {
      return C_OFFCR_CAT_TEXT_EXTERNAL;
    }
    else if ( code_.equals( BaseTbgOfficerEntity.C_OFFCR_CAT_CODE_TELEMARK ) )
    {
      return C_OFFCR_CAT_TEXT_TELEMARK;
    }

    return null;
  }

  /**
   * 
   * "Método que realiza a decodificação de um código de tipo de status de
   * officer, retornando a descrição do código passado como parâmetro. Retorna
   * null caso seja passado um código inexistente."
   * 
   * @param code_ - Código de status de officer que deve ser decodificado
   * @return Descrição do código contido no parâmetro code_. Retorna null caso
   *         não exista descrição para o código passado.
   * @exception
   * @see
   */
  public static DataSet decodeOffcrStatCode()
  {
    String[] names = { C_OFFCR_STAT_CODE, C_OFFCR_STAT_TEXT };
    String[] codes = { BaseTbgOfficerEntity.C_OFFCR_STAT_CODE_INCLUDED,
                      BaseTbgOfficerEntity.C_OFFCR_STAT_CODE_ACTIVE };
    String[] values = { C_OFFCR_STAT_TEXT_INCLUDED, C_OFFCR_STAT_TEXT_ACTIVE };
    DataSet result = new DecoderDataSet( names, codes, values );

    return result;

  }

  /**
   * 
   * "Método que realiza a decodificação de um código de tipo de status de
   * officer, retornando a descrição do código passado como parâmetro. Retorna
   * null caso seja passado um código inexistente."
   * 
   * @param code_ - Código de status de officer que deve ser decodificado
   * @return Descrição do código contido no parâmetro code_. Retorna null caso
   *         não exista descrição para o código passado.
   * @exception
   * @see
   */
  public static String decodeOffcrStatCode( String code_ )
  {
    if ( code_.equals( BaseTbgOfficerEntity.C_OFFCR_STAT_CODE_INCLUDED ) )
    {
      return C_OFFCR_STAT_TEXT_INCLUDED;
    }
    else if ( code_.equals( BaseTbgOfficerEntity.C_OFFCR_STAT_CODE_ACTIVE ) )
    {
      return C_OFFCR_STAT_TEXT_ACTIVE;
    }

    return null;
  }

  /**
   * 
   * "Método que realiza a decodificação de um código de origem de cliente,
   * retornando a descrição do código passado como parâmetro. Retorna null caso
   * seja passado um código inexistente."
   * 
   * @param code_ - Código de origem de cliente que deve ser decodificado
   * @return Descrição do código contido no parâmetro code_. Retorna null caso
   *         não exista descrição para o código passado.
   * @exception
   * @see
   */
  public static DataSet decodeReltnPrmtCode()
  {
    String[] names = { C_RELTN_PRMT_CODE, C_RELTN_PRMT_TEXT };
    String[] codes = { BaseTplRelationPrvtEntity.C_RELTN_PRMT_CODE_1,
                      BaseTplRelationPrvtEntity.C_RELTN_PRMT_CODE_2,
                      BaseTplRelationPrvtEntity.C_RELTN_PRMT_CODE_3,
                      BaseTplRelationPrvtEntity.C_RELTN_PRMT_CODE_4,
                      BaseTplRelationPrvtEntity.C_RELTN_PRMT_CODE_5,
                      BaseTplRelationPrvtEntity.C_RELTN_PRMT_CODE_6,
                      BaseTplRelationPrvtEntity.C_RELTN_PRMT_CODE_7 };
    String[] values = { C_RELTN_PRMT_TEXT_1, C_RELTN_PRMT_TEXT_2,
                       C_RELTN_PRMT_TEXT_3, C_RELTN_PRMT_TEXT_4,
                       C_RELTN_PRMT_TEXT_5, C_RELTN_PRMT_TEXT_6,
                       C_RELTN_PRMT_TEXT_7 };
    DataSet result = new DecoderDataSet( names, codes, values );

    return result;
  }

  /**
   * 
   * "Método que realiza a decodificação de um código de origem do cliente,
   * retornando a descrição do código passado como parâmetro. Retorna null caso
   * seja passado um código inexistente."
   * 
   * @param code_ - Código de origem do cliente que deve ser decodificado
   * @return Descrição do código contido no parâmetro code_. Retorna null caso
   *         não exista descrição para o código passado.
   * @exception
   * @see
   */
  public static String decodeReltnPrmtCode( String code_ )
  {
    if ( code_.equals( BaseTplRelationPrvtEntity.C_RELTN_PRMT_CODE_1 ) )
    {
      return C_RELTN_PRMT_TEXT_1;
    }
    if ( code_.equals( BaseTplRelationPrvtEntity.C_RELTN_PRMT_CODE_2 ) )
    {
      return C_RELTN_PRMT_TEXT_2;
    }
    if ( code_.equals( BaseTplRelationPrvtEntity.C_RELTN_PRMT_CODE_3 ) )
    {
      return C_RELTN_PRMT_TEXT_3;
    }
    if ( code_.equals( BaseTplRelationPrvtEntity.C_RELTN_PRMT_CODE_4 ) )
    {
      return C_RELTN_PRMT_TEXT_4;
    }
    if ( code_.equals( BaseTplRelationPrvtEntity.C_RELTN_PRMT_CODE_5 ) )
    {
      return C_RELTN_PRMT_TEXT_5;
    }
    if ( code_.equals( BaseTplRelationPrvtEntity.C_RELTN_PRMT_CODE_6 ) )
    {
      return C_RELTN_PRMT_TEXT_6;
    }
    if ( code_.equals( BaseTplRelationPrvtEntity.C_RELTN_PRMT_CODE_7 ) )
    {
      return C_RELTN_PRMT_TEXT_7;
    }

    return null;
  }

  /**
   * 
   * "Método que realiza a decodificação de um código Para indicador Sim/ Não,
   * retornando a descrição do código passado como parâmetro. Retorna null caso
   * seja passado um código inexistente."
   * 
   * @param code_ - Código de origem de cliente que deve ser decodificado
   * @return Descrição do código contido no parâmetro code_. Retorna null caso
   *         não exista descrição para o código passado.
   * @exception
   * @see
   */
  public static DataSet decodeIndicador()
  {
    String[] names = { C_INDICATOR_CODE, C_INDICATOR_TEXT };
    String[] codes = { BaseODSEntity.C_INDICATOR_YES,
                      BaseODSEntity.C_INDICATOR_NO };
    String[] values = { C_INDICATOR_NAME_YES, C_INDICATOR_NAME_NO };
    DataSet result = new DecoderDataSet( names, codes, values );

    return result;
  }

  /**
   * 
   * "Método que realiza a decodificação de um código de tipo de indicador sim/
   * não, retornando a descrição do código passado como parâmetro. Retorna null
   * caso seja passado um código inexistente."
   * 
   * @param code_ - Código de status de registro que deve ser decodificado
   * @return Descrição do código contido no parâmetro code_. Retorna null caso
   *         não exista descrição para o código passado.
   * @exception
   * @see
   */
  public static String decodeIndicator( String code_ )
  {
    if ( code_.equals( BaseODSEntity.C_INDICATOR_YES ) )
    {
      return C_INDICATOR_NAME_YES;
    }
    else if ( code_.equals( BaseODSEntity.C_INDICATOR_NO ) )
    {
      return C_INDICATOR_NAME_NO;
    }

    return null;

  }

  /**
   * 
   * "Método que realiza a recodificação de um código de operação, retornando o
   * código da descrição passada como parâmetro. Retorna null caso seja passado
   * um código inexistente."
   * 
   * @param code_ - Descrição da operação que deve ser recodificada
   * @return Código da descrição contida no parâmetro text_. Retorna null caso
   *         não exista código para a descrição passada.
   * @exception
   * @see
   */
  public static String recodeIndicator( String text_ )
  {
    if ( C_INDICATOR_NAME_NO.equals( text_ ) )
    {
      return BaseODSEntity.C_INDICATOR_NO;
    }
    else if ( C_INDICATOR_NAME_YES.equals( text_ ) )
    {
      return BaseODSEntity.C_INDICATOR_YES;
    }

    return null;
  }

  /**
   * 
   * "Método de decodificação de códigos de tipo de papel do player. Este método
   * deve ser utilizado quando se deseja decodificar códigos de tipo de papel do
   * player de registro retornados em uma consulta em lista. Este método retorna
   * um Data Set com o conjunto de códigos e descrições com o qual se deve fazer
   * um Outer Join para que seja realizada a decodificação "
   * 
   * @return Data Set contendo os códigos e descrições de tipo de papel do
   *         player existentes
   */
  public static DataSet decodeRoleType()
  {
    String[] names = { C_PLYR_ROLE_TYPE_CODE, C_PLYR_ROLE_TYPE_TEXT };
    String[] codes = { BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_ADMINISTRATOR,
                      BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_MANAGER,
                      BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_AUDITOR,
                      BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_LIQUIDATOR,
                      BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_CONTROLLER,
                      BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_CUSTODIANTE,
                      BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_EMISSOR};
    String[] values = { C_PLYR_ROLE_TYPE_DESCRIPTION_ADMINISTRATOR,
                       C_PLYR_ROLE_TYPE_DESCRIPTION_MANAGER,
                       C_PLYR_ROLE_TYPE_DESCRIPTION_AUDITOR,
                       C_PLYR_ROLE_TYPE_DESCRIPTION_LIQUIDATOR,
                       C_PLYR_ROLE_TYPE_DESCRIPTION_CONTROLLER,
                       C_PLYR_ROLE_TYPE_DESCRIPTION_CUSTODIANTE,
                       C_PLYR_ROLE_TYPE_DESCRIPTION_EMISSOR};
    DataSet result = new DecoderDataSet( names, codes, values );

    return result;
  }

  /**
   * 
   * "Método que realiza a decodificação de um código de tipo de papel do
   * player, retornando a descrição do código passado como parâmetro. Retorna
   * null caso seja passado um código inexistente."
   * 
   * @param code_ - Código de tipo de papel do player que deve ser decodificado
   * @return Descrição do código contido no parâmetro code_. Retorna null caso
   *         não exista descrição para o código passado.
   * @exception
   * @see
   */
  public static String decodeRoleType( String code_ )
  {
    if ( code_.equals( BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_ADMINISTRATOR ) )
    {
      return C_PLYR_ROLE_TYPE_DESCRIPTION_ADMINISTRATOR;
    }
    else if ( code_.equals( BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_MANAGER ) )
    {
      return C_PLYR_ROLE_TYPE_DESCRIPTION_MANAGER;
    }
    else if ( code_.equals( BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_AUDITOR ) )
    {
      return C_PLYR_ROLE_TYPE_DESCRIPTION_AUDITOR;
    }
    else if ( code_.equals( BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_LIQUIDATOR ) )
    {
      return C_PLYR_ROLE_TYPE_DESCRIPTION_LIQUIDATOR;
    }
    else if ( code_.equals( BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_CONTROLLER ) )
    {
      return C_PLYR_ROLE_TYPE_DESCRIPTION_CONTROLLER;
    }
    else if ( code_.equals( BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_CUSTODIANTE ) )
    {
      return C_PLYR_ROLE_TYPE_DESCRIPTION_CUSTODIANTE;
    }
    else if ( code_.equals( BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_EMISSOR ) )
    {
        return C_PLYR_ROLE_TYPE_DESCRIPTION_EMISSOR;
    }

    return null;

  }

  /**
   * 
   * "Método que realiza a recodificação de um código de um papel de player,
   * retornando o código da descrição passada como parâmetro. Retorna null caso
   * seja passado um código inexistente."
   * 
   * @param code_ - Descrição da operação que deve ser recodificada
   * @return Código da descrição contida no parâmetro text_. Retorna null caso
   *         não exista código para a descrição passada.
   * @exception
   * @see
   */
  public static String recodeRoleType( String text_ )
  {
    if ( C_PLYR_ROLE_TYPE_DESCRIPTION_ADMINISTRATOR.equals( text_ ) )
    {
      return BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_ADMINISTRATOR;
    }
    else if ( C_PLYR_ROLE_TYPE_DESCRIPTION_AUDITOR.equals( text_ ) )
    {
      return BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_AUDITOR;
    }
    else if ( C_PLYR_ROLE_TYPE_DESCRIPTION_CONTROLLER.equals( text_ ) )
    {
      return BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_CONTROLLER;
    }
    if ( C_PLYR_ROLE_TYPE_DESCRIPTION_CUSTODIANTE.equals( text_ ) )
    {
      return BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_CUSTODIANTE;
    }
    else if ( C_PLYR_ROLE_TYPE_DESCRIPTION_LIQUIDATOR.equals( text_ ) )
    {
      return BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_LIQUIDATOR;
    }
    else if ( C_PLYR_ROLE_TYPE_DESCRIPTION_MANAGER.equals( text_ ) )
    {
      return BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_MANAGER;
    }
    else if ( C_PLYR_ROLE_TYPE_DESCRIPTION_EMISSOR.equals( text_ ) )
    {
        return BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_EMISSOR;
    }

    return text_;
  }

  /**
   * 
   * "Método que realiza a decodificação do dado, podendo ser numérico,
   * alphanumérico e data, retornando a descrição do código passado como
   * parâmetro. Retorna null caso seja passado um código inexistente."
   * 
   * @param code_ - Código de origem de cliente que deve ser decodificado
   * @return Descrição do código contido no parâmetro code_. Retorna null caso
   *         não exista descrição para o código passado.
   * @exception
   * @see
   */
  public static DataSet decodeDataType()
  {
    String[] names = { C_DATA_TYPE_CODE, C_DATA_TYPE_TEXT };
    String[] codes = { BaseODSEntity.C_DATA_TYPE_DATE,
                      BaseODSEntity.C_DATA_TYPE_NBR,
                      BaseODSEntity.C_DATA_TYPE_ALPHA };
    String[] values = { C_DATA_TYPE_DATE, C_DATA_TYPE_NBR, C_DATA_TYPE_ALPHA };
    DataSet result = new DecoderDataSet( names, codes, values );

    return result;
  }

  /**
   * 
   * "Método que realiza a decodificação do tipo de dado, podendo ser numérico,
   * alphanumérico e data retornando a descrição do código passado como
   * parâmetro. Retorna null caso seja passado um código inexistente."
   * 
   * @param code_ - Código de status de registro que deve ser decodificado
   * @return Descrição do código contido no parâmetro code_. Retorna null caso
   *         não exista descrição para o código passado.
   * @exception
   * @see
   */
  public static String decodeDataType( String code_ )
  {
    if ( code_.equals( BaseODSEntity.C_DATA_TYPE_ALPHA ) )
    {
      return C_DATA_TYPE_ALPHA;
    }
    else if ( code_.equals( BaseODSEntity.C_DATA_TYPE_DATE ) )
    {
      return C_DATA_TYPE_DATE;
    }
    else if ( code_.equals( BaseODSEntity.C_DATA_TYPE_NBR ) )
    {
      return C_DATA_TYPE_NBR;
    }

    return null;

  }

  /**
   * 
   * "Método que realiza a decodificação da situação do contrato, podendo ser
   * ativo, inativo, bloqueado e pendente. Retorna null caso seja passado um
   * código inexistente."
   * 
   * @return Descrição da situação do contrato.
   * @exception
   * @see
   */
  public static DataSet decodeSitContract()
  {
    String[] names = { C_SIT_CONTRACT_CODE, C_SIT_CONTRACT_TEXT };
    String[] codes = { BaseODSEntity.C_SIT_CONTRACT_ACTIVE,
                      BaseODSEntity.C_SIT_CONTRACT_INACTIVE,
                      BaseODSEntity.C_SIT_CONTRACT_BLOCKED,
                      BaseODSEntity.C_SIT_CONTRACT_PENDING };
    String[] values = { C_SIT_CONTRACT_ACTIVE, C_SIT_CONTRACT_INACTIVE,
                       C_SIT_CONTRACT_BLOCKED, C_SIT_CONTRACT_PENDING };
    DataSet result = new DecoderDataSet( names, codes, values );

    return result;
  }

  /**
   * 
   * Método que realiza a decodificação da situação do contrato, podendo ser
   * ativo, inativo, bloqueado e pendente. Retorna null caso seja passado um
   * código inexistente."
   * 
   * @param code_ - Código de status de registro que deve ser decodificado
   * @return Descrição do código contido no parâmetro code_. Retorna null caso
   *         não exista descrição para o código passado.
   * @exception
   * @see
   */
  public static String decodeSitContract( String code_ )
  {
    if ( code_.equals( BaseODSEntity.C_SIT_CONTRACT_ACTIVE ) )
    {
      return C_SIT_CONTRACT_ACTIVE;
    }
    else if ( code_.equals( BaseODSEntity.C_SIT_CONTRACT_INACTIVE ) )
    {
      return C_SIT_CONTRACT_INACTIVE;
    }
    else if ( code_.equals( BaseODSEntity.C_SIT_CONTRACT_BLOCKED ) )
    {
      return C_SIT_CONTRACT_BLOCKED;
    }
    else if ( code_.equals( BaseODSEntity.C_SIT_CONTRACT_PENDING ) )
    {
      return C_SIT_CONTRACT_PENDING;
    }

    return null;

  }

  public static String decodeIndicatorCondition( String code_ )
  {
    if ( code_.equals( BaseODSEntity.C_INDICATOR_YES ) )
    {
      return C_INDICATOR_TRUE;
    }
    else if ( code_.equals( BaseODSEntity.C_INDICATOR_NO ) )
    {
      return C_INDICATOR_FALSE;
    }
    return null;
  }

  public static String decodeIndicatorConditionInverse( String code_ )
  {
    if ( code_.equals( C_INDICATOR_ON ) )
    {
      return BaseODSEntity.C_INDICATOR_YES;
    }
    else if ( code_.equals( C_INDICATOR_OFF ) )
    {
      return BaseODSEntity.C_INDICATOR_NO;
    }
    return null;
  }

  public static DataSet decodeClassTypeCredit()
  {
    String[] names = { C_CLASS_TYPE_CREDIT_CODE, C_CLASS_TYPE_CREDIT_TEXT };
    String[] codes = { BaseODSEntity.C_PRODUCT_CREDIT_LINE,
                      BaseODSEntity.C_PRODUCT_CREDIT_COMMITMENT };
    String[] values = { C_PRODUCT_CREDIT_LINE, C_PRODUCT_CREDIT_COMMITMENT };
    DataSet result = new DecoderDataSet( names, codes, values );

    return result;
  }

  public static DataSet decodeProdStatCode()
  {
    String[] names = { C_PROD_STAT_CODE, C_PROD_STAT_TEXT };
    String[] codes = { BaseODSEntity.C_PROD_STAT_CODE_ACTIVE,
                      BaseODSEntity.C_PROD_STAT_CODE_INACTIVE,
                      BaseODSEntity.C_PROD_STAT_CODE_CLOSED };
    String[] values = { C_PROD_STAT_CODE_ACTIVE, C_PROD_STAT_CODE_INACTIVE,
                       C_PROD_STAT_CODE_CLOSED };
    DataSet result = new DecoderDataSet( names, codes, values );

    return result;
  }

  /**
   * Método genérico para mostrar no combo os valores passados por
   * parâmentro.Decodifica as informações (S/N)
   */
  public static DataSet decodeDataRecord( String optionYes, String optionNo )
  {
    String[] names = { C_INDICATOR_CODE, C_INDICATOR_TEXT };
    String[] codes = { BaseODSEntity.C_INDICATOR_YES,
                      BaseODSEntity.C_INDICATOR_NO };
    String[] values = { optionYes, optionNo };
    DataSet result = new DecoderDataSet( names, codes, values );

    return result;
  }

  public static DataSet decodeTransaction()
  {
    String[] names = { C_TRANSACTION_CODE, C_TRANSACTION_TEXT };
    String[] codes = { BaseODSEntity.C_TRANSACTION_DOC,
                      BaseODSEntity.C_TRANSACTION_TED,
                      BaseODSEntity.C_TRANSACTION_OTHERS };
    String[] values = { C_TRANSACTION_DOC, C_TRANSACTION_TED,
                       C_TRANSACTION_OTHERS };
    DataSet result = new DecoderDataSet( names, codes, values );

    return result;
  }
  
  public static DataSet decodeAcctTypeCode()
  {
	  String[] names = { C_ACCT_TYPE_CODE, C_ACCT_TYPE_TEXT };
	  String[] codes = { "P","C", "A"  }; //A - corrente - incluido 13/12/2012
	  String[] values = { "Poupança", "Corretora", "Corrente" };
	  DataSet result = new DecoderDataSet( names, codes, values );

	  return result;
  }
  
  public static String decodeAcctTypeCode( String code_ )
  {
	  if ( code_.equals( "Poupança" ) )
	  {
		return "P";
	  }
	  else if ( code_.equals( "Corretora" ) )
	  {
		return "C";
	  } else if ( code_.equals("Corrente")) 
	  {
		return "A";
	  }
	  return null;
  }  

  public static String decodeTransaction( String code_ )
  {
    if ( code_.equals( BaseODSEntity.C_TRANSACTION_DOC ) )
    {
      return C_TRANSACTION_DOC;
    }
    else if ( code_.equals( BaseODSEntity.C_TRANSACTION_TED ) )
    {
      return C_TRANSACTION_TED;
    }
    else if ( code_.equals( BaseODSEntity.C_TRANSACTION_OTHERS ) )
    {
      return C_TRANSACTION_OTHERS;
    }
    return null;
  }

  public static DataSet decodeProdLegalClass()
  {
    String[] names = { C_PROD_LEGAL_CLASS_CODE, C_PROD_LEGAL_CLASS_TEXT };
    String[] codes = { BaseODSEntity.C_LEGAL_CLASS_CODE_201,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_58,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_15,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_19,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_28,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_401,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_84,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_71,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_301,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_703,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_101,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_500,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_501,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_801,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_511,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_30,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_16,
                      BaseODSEntity.C_LEGAL_CLASS_CODE_603

    };

    String[] values = { C_LEGAL_CLASS_TEXT_201, C_LEGAL_CLASS_TEXT_58,
                       C_LEGAL_CLASS_TEXT_15, C_LEGAL_CLASS_TEXT_19,
                       C_LEGAL_CLASS_TEXT_28, C_LEGAL_CLASS_TEXT_401,
                       C_LEGAL_CLASS_TEXT_84, C_LEGAL_CLASS_TEXT_71,
                       C_LEGAL_CLASS_TEXT_301, C_LEGAL_CLASS_TEXT_703,
                       C_LEGAL_CLASS_TEXT_101, C_LEGAL_CLASS_TEXT_500,
                       C_LEGAL_CLASS_TEXT_501, C_LEGAL_CLASS_TEXT_801,
                       C_LEGAL_CLASS_TEXT_511, C_LEGAL_CLASS_TEXT_30,
                       C_LEGAL_CLASS_TEXT_16, C_LEGAL_CLASS_TEXT_603

    };

    DataSet result = new DecoderDataSet( names, codes, values );

    return result;
  }

  public static DataSet decodeCustomerStatCode()
  {
    String[] names = { C_CUST_PRVT_STAT_CODE, C_CUST_PRVT_STAT_TEXT };
    String[] codes = { BaseODSEntity.C_CUST_PRVT_STAT_ACTIVE,
                      BaseODSEntity.C_CUST_PRVT_STAT_INACTIVE };
    String[] values = { C_CUST_PRVT_STAT_ACTIVE, C_CUST_PRVT_STAT_INACTIVE };
    DataSet result = new DecoderDataSet( names, codes, values );

    return result;
  }
  
  
  public static DataSet decodeOriginCode()
   {
	 String[] names = { C_INDICATOR_CODE, C_INDICATOR_TEXT };
	 String[] codes = { BaseODSEntity.C_INDICATOR_YES,
					   BaseODSEntity.C_INDICATOR_NO };
	 String[] values = { C_ORIGIN_YES, C_ORIGIN_NO };
	 DataSet result = new DecoderDataSet( names, codes, values );

	 return result;

   }
}