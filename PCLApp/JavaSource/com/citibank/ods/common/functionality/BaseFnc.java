package com.citibank.ods.common.functionality;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.BaseObject;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.FormatUtils;

/**
 * 
 * Classe base para as classes do tipo Fnc. Esta classe possui implementações
 * para formatação e parsing de números e datas.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public abstract class BaseFnc extends BaseObject
{
  /**
   * 
   * A implementação deste método deve ser responsável pelo preenchimento dos
   * dados da FncVO. <br>
   * O preenchimento destes dados consiste na cópia de valores dispostos na
   * estrutura do Form para os dados dispostos na estrutura da FncVO. <br>
   * Adicionalmente, o parsing de números e datas provenientes do Form através
   * de atributos String ou String[] deverá ser realizado por este método,
   * utilizando os parsers implementados nesta classe.
   * 
   * @param form_ - Form que contempla os dados submetidos pela requisição.
   * @param fncVO_ - FncVO que será atualizada com os valores obtidos do Form.
   */
  public abstract void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ );

  /**
   * 
   * A implementação deste método deve ser responsável pelo preenchimento dos
   * dados do Form. <br>
   * O preenchimento destes dados consiste na cópia de valores dispostos na
   * estrutura da FncVO para os dados dispostos na estrutura do Form. <br>
   * Adicionalmente, a formatação de números e datas provenientes da FncVO para
   * popular os dados do Formd everá ser realizado por este método, utilizando
   * os formatadores implementados nesta classe.
   * 
   * @param form_ - Form que contempla os dados que serão apresentados.
   * @param fncVO_ - FncVO que contempla os dados que serão disponibilizados no
   *          Form.
   */
  public abstract void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ );

  /**
   * 
   * A implementação deste método deve ser responsável pela criação da instância
   * adequada de FncVO que é utilizada pela Fnc.
   * 
   * @return instância de FncVO criada.
   */
  public abstract BaseFncVO createFncVO();

  /**
   * 
   * Faz o parsing de uma String para um BigDecimal, considerando formato e
   * Locale do número disposto na String. O formato da String é definido no
   * MessageResources do Form, identificado pelo nome formatKey_. O Locale para
   * o parsing é definido no Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informações de MessageResources e
   *          Locale para o parsing.
   * @param value_ - String na qual será realizado o parsing.
   * @param formatKey_ - nome do formato utilizado para o parsing.
   * @return Instância de BigDecimal que representa o valor "parsed".
   */
  protected BigDecimal toBigDecimal( BaseForm baseForm_, String value_,
                                    String formatKey_ )
  {
    FormatUtils formatUtils = FormatUtils.getInstance();
    BigDecimal value = formatUtils.toBigDecimal(
                                                 value_,
                                                 formatKey_,
                                                 baseForm_.getCurrentLocale(),
                                                 baseForm_.getCurrentMessageResources() );
    return value;
  }

  /**
   * 
   * Faz o parsing de uma String para um BigInteger, considerando formato e
   * Locale do número disposto na String. O formato da String é definido no
   * MessageResources do Form, identificado pelo nome formatKey_. O Locale para
   * o parsing é definido no Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informações de MessageResources e
   *          Locale para o parsing.
   * @param value_ - String na qual será realizado o parsing.
   * @param formatKey_ - nome do formato utilizado para o parsing.
   * @return Instância de BigInteger que representa o valor "parsed".
   */
  protected BigInteger toBigInteger( BaseForm baseForm_, String value_,
                                    String formatKey_ )
  {
    FormatUtils formatUtils = FormatUtils.getInstance();
    BigInteger value = formatUtils.toBigInteger(
                                                 value_,
                                                 formatKey_,
                                                 baseForm_.getCurrentLocale(),
                                                 baseForm_.getCurrentMessageResources() );
    return value;
  }

  /**
   * 
   * Faz o parsing de uma String para um Integer, considerando formato e Locale
   * do número disposto na String. O formato da String é definido no
   * MessageResources do Form, identificado pelo nome formatKey_. O Locale para
   * o parsing é definido no Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informações de MessageResources e
   *          Locale para o parsing.
   * @param value_ - String na qual será realizado o parsing.
   * @param formatKey_ - nome do formato utilizado para o parsing.
   * @return Instância de Integer que representa o valor "parsed".
   */
  protected Integer toInteger( BaseForm baseForm_, String value_,
                              String formatKey_ )
  {
    FormatUtils formatUtils = FormatUtils.getInstance();
    Integer value = formatUtils.toInteger(
                                           value_,
                                           formatKey_,
                                           baseForm_.getCurrentLocale(),
                                           baseForm_.getCurrentMessageResources() );
    return value;
  }

  /**
   * 
   * Faz o parsing de uma String para um Date, considerando formato e Locale do
   * número disposto na String. O formato da String é definido no
   * MessageResources do Form, identificado pelo nome formatKey_. O Locale para
   * o parsing é definido no Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informações de MessageResources e
   *          Locale para o parsing.
   * @param value_ - String na qual será realizado o parsing.
   * @param formatKey_ - nome do formato utilizado para o parsing.
   * @return Instância de Date que representa o valor "parsed".
   */
  protected Date toDate( BaseForm baseForm_, String value_, String formatKey_ )
  {
    FormatUtils formatUtils = FormatUtils.getInstance();
    Date value = formatUtils.toDate( value_, formatKey_,
                                     baseForm_.getCurrentLocale(),
                                     baseForm_.getCurrentMessageResources() );
    return value;
  }

  /**
   * 
   * Formata um BigDecimal utilizando uma máscara, considerando o Locale de
   * formatação. A máscara de formatação é obtida do MessageResources do Form,
   * identificada pelo nome formatKey_.O Locale para formatação é definido no
   * Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informações de MessageResources e
   *          Locale para a formatação.
   * @param value_ - Valor que será formatado.
   * @param formatKey_ - Nome do formato (máscara) que será utilizada.
   * @return String que apresenta o valor informado formatado segundo a máscara
   *         e Locale de formatação. Será retornado uma string vazia ("") caso o
   *         valor informado para formatação seja null.
   */
  protected String format( BaseForm baseForm_, BigDecimal value_,
                          String formatKey_ )
  {
    FormatUtils formatUtils = FormatUtils.getInstance();
    String formattedValue = formatUtils.format(
                                                value_,
                                                formatKey_,
                                                baseForm_.getCurrentLocale(),
                                                baseForm_.getCurrentMessageResources() );
    return formattedValue;
  }

  /**
   * 
   * Formata um Integer utilizando uma máscara, considerando o Locale de
   * formatação. A máscara de formatação é obtida do MessageResources do Form,
   * identificada pelo nome formatKey_.O Locale para formatação é definido no
   * Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informações de MessageResources e
   *          Locale para a formatação.
   * @param value_ - Valor que será formatado.
   * @param formatKey_ - Nome do formato (máscara) que será utilizada.
   * @return String que apresenta o valor informado formatado segundo a máscara
   *         e Locale de formatação. Será retornado uma string vazia ("") caso o
   *         valor informado para formatação seja null.
   */
  protected String format( BaseForm baseForm_, Integer value_, String formatKey_ )
  {
    FormatUtils formatUtils = FormatUtils.getInstance();
    String formattedValue = formatUtils.format(
                                                value_,
                                                formatKey_,
                                                baseForm_.getCurrentLocale(),
                                                baseForm_.getCurrentMessageResources() );
    return formattedValue;
  }

  /**
   * 
   * Formata um BigInteger utilizando uma máscara, considerando o Locale de
   * formatação. A máscara de formatação é obtida do MessageResources do Form,
   * identificada pelo nome formatKey_.O Locale para formatação é definido no
   * Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informações de MessageResources e
   *          Locale para a formatação.
   * @param value_ - Valor que será formatado.
   * @param formatKey_ - Nome do formato (máscara) que será utilizada.
   * @return String que apresenta o valor informado formatado segundo a máscara
   *         e Locale de formatação. Será retornado uma string vazia ("") caso o
   *         valor informado para formatação seja null.
   */
  protected String format( BaseForm baseForm_, BigInteger value_,
                          String formatKey_ )
  {
    FormatUtils formatUtils = FormatUtils.getInstance();
    String formattedValue = formatUtils.format(
                                                value_,
                                                formatKey_,
                                                baseForm_.getCurrentLocale(),
                                                baseForm_.getCurrentMessageResources() );
    return formattedValue;
  }

  /**
   * 
   * Formata um Date utilizando uma máscara, considerando o Locale de
   * formatação. A máscara de formatação é obtida do MessageResources do Form,
   * identificada pelo nome formatKey_.O Locale para formatação é definido no
   * Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informações de MessageResources e
   *          Locale para a formatação.
   * @param value_ - Valor que será formatado.
   * @param formatKey_ - Nome do formato (máscara) que será utilizada.
   * @return String que apresenta o valor informado formatado segundo a máscara
   *         e Locale de formatação. Será retornado uma string vazia ("") caso o
   *         valor informado para formatação seja null.
   */
  protected String format( BaseForm baseForm_, Date value_, String formatKey_ )
  {
    FormatUtils formatUtils = FormatUtils.getInstance();
    String formattedValue = formatUtils.format(
                                                value_,
                                                formatKey_,
                                                baseForm_.getCurrentLocale(),
                                                baseForm_.getCurrentMessageResources() );
    return formattedValue;
  }
}