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
 * Classe base para as classes do tipo Fnc. Esta classe possui implementa��es
 * para formata��o e parsing de n�meros e datas.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public abstract class BaseFnc extends BaseObject
{
  /**
   * 
   * A implementa��o deste m�todo deve ser respons�vel pelo preenchimento dos
   * dados da FncVO. <br>
   * O preenchimento destes dados consiste na c�pia de valores dispostos na
   * estrutura do Form para os dados dispostos na estrutura da FncVO. <br>
   * Adicionalmente, o parsing de n�meros e datas provenientes do Form atrav�s
   * de atributos String ou String[] dever� ser realizado por este m�todo,
   * utilizando os parsers implementados nesta classe.
   * 
   * @param form_ - Form que contempla os dados submetidos pela requisi��o.
   * @param fncVO_ - FncVO que ser� atualizada com os valores obtidos do Form.
   */
  public abstract void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ );

  /**
   * 
   * A implementa��o deste m�todo deve ser respons�vel pelo preenchimento dos
   * dados do Form. <br>
   * O preenchimento destes dados consiste na c�pia de valores dispostos na
   * estrutura da FncVO para os dados dispostos na estrutura do Form. <br>
   * Adicionalmente, a formata��o de n�meros e datas provenientes da FncVO para
   * popular os dados do Formd ever� ser realizado por este m�todo, utilizando
   * os formatadores implementados nesta classe.
   * 
   * @param form_ - Form que contempla os dados que ser�o apresentados.
   * @param fncVO_ - FncVO que contempla os dados que ser�o disponibilizados no
   *          Form.
   */
  public abstract void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ );

  /**
   * 
   * A implementa��o deste m�todo deve ser respons�vel pela cria��o da inst�ncia
   * adequada de FncVO que � utilizada pela Fnc.
   * 
   * @return inst�ncia de FncVO criada.
   */
  public abstract BaseFncVO createFncVO();

  /**
   * 
   * Faz o parsing de uma String para um BigDecimal, considerando formato e
   * Locale do n�mero disposto na String. O formato da String � definido no
   * MessageResources do Form, identificado pelo nome formatKey_. O Locale para
   * o parsing � definido no Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informa��es de MessageResources e
   *          Locale para o parsing.
   * @param value_ - String na qual ser� realizado o parsing.
   * @param formatKey_ - nome do formato utilizado para o parsing.
   * @return Inst�ncia de BigDecimal que representa o valor "parsed".
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
   * Locale do n�mero disposto na String. O formato da String � definido no
   * MessageResources do Form, identificado pelo nome formatKey_. O Locale para
   * o parsing � definido no Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informa��es de MessageResources e
   *          Locale para o parsing.
   * @param value_ - String na qual ser� realizado o parsing.
   * @param formatKey_ - nome do formato utilizado para o parsing.
   * @return Inst�ncia de BigInteger que representa o valor "parsed".
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
   * do n�mero disposto na String. O formato da String � definido no
   * MessageResources do Form, identificado pelo nome formatKey_. O Locale para
   * o parsing � definido no Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informa��es de MessageResources e
   *          Locale para o parsing.
   * @param value_ - String na qual ser� realizado o parsing.
   * @param formatKey_ - nome do formato utilizado para o parsing.
   * @return Inst�ncia de Integer que representa o valor "parsed".
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
   * n�mero disposto na String. O formato da String � definido no
   * MessageResources do Form, identificado pelo nome formatKey_. O Locale para
   * o parsing � definido no Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informa��es de MessageResources e
   *          Locale para o parsing.
   * @param value_ - String na qual ser� realizado o parsing.
   * @param formatKey_ - nome do formato utilizado para o parsing.
   * @return Inst�ncia de Date que representa o valor "parsed".
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
   * Formata um BigDecimal utilizando uma m�scara, considerando o Locale de
   * formata��o. A m�scara de formata��o � obtida do MessageResources do Form,
   * identificada pelo nome formatKey_.O Locale para formata��o � definido no
   * Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informa��es de MessageResources e
   *          Locale para a formata��o.
   * @param value_ - Valor que ser� formatado.
   * @param formatKey_ - Nome do formato (m�scara) que ser� utilizada.
   * @return String que apresenta o valor informado formatado segundo a m�scara
   *         e Locale de formata��o. Ser� retornado uma string vazia ("") caso o
   *         valor informado para formata��o seja null.
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
   * Formata um Integer utilizando uma m�scara, considerando o Locale de
   * formata��o. A m�scara de formata��o � obtida do MessageResources do Form,
   * identificada pelo nome formatKey_.O Locale para formata��o � definido no
   * Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informa��es de MessageResources e
   *          Locale para a formata��o.
   * @param value_ - Valor que ser� formatado.
   * @param formatKey_ - Nome do formato (m�scara) que ser� utilizada.
   * @return String que apresenta o valor informado formatado segundo a m�scara
   *         e Locale de formata��o. Ser� retornado uma string vazia ("") caso o
   *         valor informado para formata��o seja null.
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
   * Formata um BigInteger utilizando uma m�scara, considerando o Locale de
   * formata��o. A m�scara de formata��o � obtida do MessageResources do Form,
   * identificada pelo nome formatKey_.O Locale para formata��o � definido no
   * Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informa��es de MessageResources e
   *          Locale para a formata��o.
   * @param value_ - Valor que ser� formatado.
   * @param formatKey_ - Nome do formato (m�scara) que ser� utilizada.
   * @return String que apresenta o valor informado formatado segundo a m�scara
   *         e Locale de formata��o. Ser� retornado uma string vazia ("") caso o
   *         valor informado para formata��o seja null.
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
   * Formata um Date utilizando uma m�scara, considerando o Locale de
   * formata��o. A m�scara de formata��o � obtida do MessageResources do Form,
   * identificada pelo nome formatKey_.O Locale para formata��o � definido no
   * Locale do Form.
   * 
   * @param baseForm_ - Form que contempla as informa��es de MessageResources e
   *          Locale para a formata��o.
   * @param value_ - Valor que ser� formatado.
   * @param formatKey_ - Nome do formato (m�scara) que ser� utilizada.
   * @return String que apresenta o valor informado formatado segundo a m�scara
   *         e Locale de formata��o. Ser� retornado uma string vazia ("") caso o
   *         valor informado para formata��o seja null.
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