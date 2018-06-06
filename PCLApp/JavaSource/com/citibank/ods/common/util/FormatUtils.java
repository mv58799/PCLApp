package com.citibank.ods.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.struts.util.MessageResources;

import com.citibank.ods.common.BaseObject;
import com.citibank.ods.common.exception.UnexpectedException;

/**
 * 
 * Singleton para formatação e parsing de números e datas.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class FormatUtils extends BaseObject
{
  /**
   * ms_instance - instância do Singleton;
   */
  private static final FormatUtils ms_instance = new FormatUtils();

  /**
   * C_BIG_INTEGER_INSTANCE - instância utilizada para identificar que o tipo de
   * Format que será instacioando é o Format adequado para tratamento de
   * BigInteger.
   */
  private static final BigInteger C_BIG_INTEGER_INSTANCE = new BigInteger( "0" );

  /**
   * C_INTEGER_INSTANCE - instância utilizada para identificar que o tipo de
   * Format que será instacioando é o Format adequado para tratamento de
   * Integer.
   */
  private static final Integer C_INTEGER_INSTANCE = new Integer( 0 );

  /**
   * C_DATE_INSTANCE - instância utilizada para identificar que o tipo de Format
   * que será instacioando é o Format adequado para tratamento de Date.
   */
  private static final Date C_DATE_INSTANCE = new Date();

  /**
   * Construtor protegido para que haja somente a instância do Singleton.
   */
  protected FormatUtils()
  {
    super();
  }

  /**
   * 
   * Obtém a instância do Sibgleton.
   * 
   * @return instância do Singleton.
   */
  public static FormatUtils getInstance()
  {
    return ms_instance;
  }

  /**
   * 
   * Formata um BigDecimal utilizando uma máscara, considerando o Locale de
   * formatação. A máscara de formatação é obtida do MessageResources,
   * identificada pelo nome formatKey_.
   * 
   * @param valueToFormat_ - Valor que será formatado.
   * @param formatKey_ - Nome do formato (máscara) que será utilizada.
   * @param locale_ - Identificação de localização para aplicação do formato.
   * @param messageResources_ - Arquivo que contempla a parametrização das
   *          máscaras identificadas pelo formatKey_.
   * 
   * @return String que apresenta o valor informado formatado segundo a máscara
   *         e Locale de formatação. Será retornado uma string vazia ("") caso o
   *         valor informado para formatação seja null.
   */
  public String format( BigDecimal valueToFormat_, String formatKey_,
                       Locale locale_, MessageResources messageResources_ )
  {
    return format( ( Object ) valueToFormat_, formatKey_, locale_,
                   messageResources_ );
  }

  /**
   * 
   * Formata um BigInteger utilizando uma máscara, considerando o Locale de
   * formatação. A máscara de formatação é obtida do MessageResources,
   * identificada pelo nome formatKey_.
   * 
   * @param valueToFormat_ - Valor que será formatado.
   * @param formatKey_ - Nome do formato (máscara) que será utilizada.
   * @param locale_ - Identificação de localização para aplicação do formato.
   * @param messageResources_ - Arquivo que contempla a parametrização das
   *          máscaras identificadas pelo formatKey_.
   * 
   * @return String que apresenta o valor informado formatado segundo a máscara
   *         e Locale de formatação. Será retornado uma string vazia ("") caso o
   *         valor informado para formatação seja null.
   */
  public String format( BigInteger valueToFormat_, String formatKey_,
                       Locale locale_, MessageResources messageResources_ )
  {
    return format( ( Object ) valueToFormat_, formatKey_, locale_,
                   messageResources_ );
  }

  /**
   * 
   * Formata um Integer utilizando uma máscara, considerando o Locale de
   * formatação. A máscara de formatação é obtida do MessageResources,
   * identificada pelo nome formatKey_.
   * 
   * @param valueToFormat_ - Valor que será formatado.
   * @param formatKey_ - Nome do formato (máscara) que será utilizada.
   * @param locale_ - Identificação de localização para aplicação do formato.
   * @param messageResources_ - Arquivo que contempla a parametrização das
   *          máscaras identificadas pelo formatKey_.
   * 
   * @return String que apresenta o valor informado formatado segundo a máscara
   *         e Locale de formatação. Será retornado uma string vazia ("") caso o
   *         valor informado para formatação seja null.
   */
  public String format( Integer valueToFormat_, String formatKey_,
                       Locale locale_, MessageResources messageResources_ )
  {
    return format( ( Object ) valueToFormat_, formatKey_, locale_,
                   messageResources_ );
  }

  /**
   * 
   * Formata um Date utilizando uma máscara, considerando o Locale de
   * formatação. A máscara de formatação é obtida do MessageResources,
   * identificada pelo nome formatKey_.
   * 
   * @param valueToFormat_ - Valor que será formatado.
   * @param formatKey_ - Nome do formato (máscara) que será utilizada.
   * @param locale_ - Identificação de localização para aplicação do formato.
   * @param messageResources_ - Arquivo que contempla a parametrização das
   *          máscaras identificadas pelo formatKey_.
   * 
   * @return String que apresenta o valor informado formatado segundo a máscara
   *         e Locale de formatação. Será retornado uma string vazia ("") caso o
   *         valor informado para formatação seja null.
   */
  public String format( Date valueToFormat_, String formatKey_, Locale locale_,
                       MessageResources messageResources_ )
  {
    return format( ( Object ) valueToFormat_, formatKey_, locale_,
                   messageResources_ );
  }

  /**
   * 
   * Faz o parsing de uma String para um BigDecimal, considerando formato e
   * Locale do número disposto na String. O formato da String é definido no
   * MessageResources, identificado pelo nome formatKey_.
   * 
   * @param valueToParse_ - String na qual será realizado o parsing.
   * @param formatKey_ - Nome do formato utilizado para o parsing.
   * @param locale_ - Identificação de localização para aplicação do formato.
   * @param messageResources_ - Arquivo que contempla a parametrização das
   *          máscaras identificadas pelo formatKey_.
   * @return Instância de BigDecimal que representa o valor "parsed".
   */
  public BigDecimal toBigDecimal( String valueToParse_, String formatKey_,
                                 Locale locale_,
                                 MessageResources messageResources_ )
  {
    BigDecimal parsedValue = null;
    Format format = null;
    Number parsedNumber = null;
    final char C_CHAR_0 = '0';
    final char C_CHAR_9 = '9';
    final char C_CHAR_DOT = '.';

    if ( valueToParse_ != null && !"".equals( valueToParse_ ) )
    {
      String formatSring = getFormatString( formatKey_, locale_,
                                            messageResources_ );
      format = createFormat( C_BIG_INTEGER_INSTANCE, locale_, formatSring );
      ( ( NumberFormat ) format ).setParseIntegerOnly( true );
      ParsePosition parsePosition = new ParsePosition( 0 );

      parsedNumber = ( ( NumberFormat ) format ).parse( valueToParse_,
                                                        parsePosition );
      if ( valueToParse_.length() > parsePosition.getIndex() )
      {
        valueToParse_ = valueToParse_.substring( parsePosition.getIndex() + 1 );
      }
      else
      {
        valueToParse_ = valueToParse_.substring( parsePosition.getIndex() );
      }
      boolean end = false;
      StringBuffer mantissa = new StringBuffer( valueToParse_.length() + 1 );
      for ( int i = 0; i < valueToParse_.length() && !end; i++ )
      {
        char parsedChar = valueToParse_.charAt( i );
        if ( parsedChar < C_CHAR_0 || parsedChar > C_CHAR_9 )
        {
          end = true;
        }
        else
        {
          mantissa.append( parsedChar );
        }
      }
      if ( mantissa.length() > 0 )
      {
        mantissa.insert( 0, C_CHAR_DOT );
      }

      BigDecimal parsedCharacteristic = new BigDecimal(
                                                        parsedNumber.doubleValue() );

      parsedValue = new BigDecimal( parsedCharacteristic.toString()
                                    + mantissa.toString() );
    }
    return parsedValue;

  }

  public BigDecimal toBigDecimal(String value){
	  NumberFormat format = NumberFormat.getInstance(Locale.US);				
	  try {
		  Number number = format.parse(value);
		  return new BigDecimal(number.doubleValue());
	  } catch (ParseException e) {
		  throw new RuntimeException();
	  }

  }
  
  public String formatNumber(BigDecimal value){
	  NumberFormat format = NumberFormat.getInstance(Locale.US);				

	  String number = format.format(value.doubleValue());
	  return number;
  }

  /**
   * 
   * Faz o parsing de uma String para um BigInteger, considerando formato e
   * Locale do número disposto na String. O formato da String é definido no
   * MessageResources, identificado pelo nome formatKey_.
   * 
   * @param valueToParse_ - String na qual será realizado o parsing.
   * @param formatKey_ - Nome do formato utilizado para o parsing.
   * @param locale_ - Identificação de localização para aplicação do formato.
   * @param messageResources_ - Arquivo que contempla a parametrização das
   *          máscaras identificadas pelo formatKey_.
   * @return Instância de BigInteger que representa o valor "parsed".
   */
  public BigInteger toBigInteger( String valueToParse_, String formatKey_,
                                 Locale locale_,
                                 MessageResources messageResources_ )
  {
    BigInteger parsedValue = null;
    Format format = null;
    Number parsedNumber = null;

    if ( valueToParse_ != null && !"".equals( valueToParse_ ) )
    {
      String formatSring = getFormatString( formatKey_, locale_,
                                            messageResources_ );
      format = createFormat( C_BIG_INTEGER_INSTANCE, locale_, formatSring );
      try
      {
        parsedNumber = ( ( NumberFormat ) format ).parse( valueToParse_ );
      }
      catch ( ParseException e_ )
      {
        throw new UnexpectedException( "valueToParse_ [" + valueToParse_
                                                                     + "] cannot be parsed by pattern ["
                                                                     + formatSring + "] of formatKey_ ["
                                                                     + formatKey_ + "] in messageResources ["
                                                                     + messageResources_.getConfig()
                                                                     + "] using locale_ ["
                                                                     + locale_.toString(), e_ );
      }
      BigDecimal parsedCharacteristic = new BigDecimal(
                                                        parsedNumber.doubleValue() );
      parsedValue = new BigInteger( parsedCharacteristic.toString() );
    }
    return parsedValue;
  }

  /**
   * 
   * Faz o parsing de uma String para um Integer, considerando formato e Locale
   * do número disposto na String. O formato da String é definido no
   * MessageResources, identificado pelo nome formatKey_.
   * 
   * @param valueToParse_ - String na qual será realizado o parsing.
   * @param formatKey_ - Nome do formato utilizado para o parsing.
   * @param locale_ - Identificação de localização para aplicação do formato.
   * @param messageResources_ - Arquivo que contempla a parametrização das
   *          máscaras identificadas pelo formatKey_.
   * @return Instância de Integer que representa o valor "parsed".
   */
  public Integer toInteger( String valueToParse_, String formatKey_,
                           Locale locale_, MessageResources messageResources_ )
  {
    Integer parsedValue = null;
    Format format = null;
    Number parsedNumber = null;

    if ( valueToParse_ != null && !"".equals( valueToParse_ ) )
    {
      String formatSring = getFormatString( formatKey_, locale_,
                                            messageResources_ );
      format = createFormat( C_INTEGER_INSTANCE, locale_, formatSring );
      try
      {
        parsedNumber = ( ( NumberFormat ) format ).parse( valueToParse_ );
      }
      catch ( ParseException e_ )
      {
        throw new UnexpectedException( "valueToParse_ [" + valueToParse_
                                                                     + "] cannot be parsed by pattern ["
                                                                     + formatSring + "] of formatKey_ ["
                                                                     + formatKey_ + "] in messageResources ["
                                                                     + messageResources_.getConfig()
                                                                     + "] using locale_ ["
                                                                     + locale_.toString(), e_ );
      }
      BigDecimal parsedCharacteristic = new BigDecimal(
                                                        parsedNumber.doubleValue() );
      parsedValue = new Integer( parsedCharacteristic.toString() );
    }
    return parsedValue;
  }

  /**
   * 
   * Faz o parsing de uma String para um Date, considerando formato e Locale do
   * número disposto na String. O formato da String é definido no
   * MessageResources, identificado pelo nome formatKey_.
   * 
   * @param valueToParse_ - String na qual será realizado o parsing.
   * @param formatKey_ - Nome do formato utilizado para o parsing.
   * @param locale_ - Identificação de localização para aplicação do formato.
   * @param messageResources_ - Arquivo que contempla a parametrização das
   *          máscaras identificadas pelo formatKey_.
   * @return Instância de Date que representa o valor "parsed".
   */
  public Date toDate( String valueToParse_, String formatKey_, Locale locale_,
                     MessageResources messageResources_ )
  {
    Date parsedValue = null;
    Format format = null;

    if ( valueToParse_ != null && !"".equals( valueToParse_ ) )
    {
      String formatSring = getFormatString( formatKey_, locale_,
                                            messageResources_ );
      format = createFormat( C_DATE_INSTANCE, locale_, formatSring );
      try
      {
        parsedValue = ( ( DateFormat ) format ).parse( valueToParse_ );
      }
      catch ( ParseException e_ )
      {
        throw new UnexpectedException( "valueToParse_ [" + valueToParse_
                                                                     + "] cannot be parsed by pattern ["
                                                                     + formatSring + "] of formatKey_ ["
                                                                     + formatKey_ + "] in messageResources ["
                                                                     + messageResources_.getConfig()
                                                                     + "] using locale_ ["
                                                                     + locale_.toString(), e_ );
      }
    }
    return parsedValue;

  }

  /**
   * 
   * Formata o valor de um Objeto utilizando uma máscara, considerando o Locale
   * de formatação. A máscara de formatação é obtida do MessageResources,
   * identificada pelo nome formatKey_.
   * 
   * @param valueToFormat_ - Valor que será formatado.
   * @param formatKey_ - Nome do formato (máscara) que será utilizada.
   * @param locale_ - Identificação de localização para aplicação do formato.
   * @param messageResources_ - Arquivo que contempla a parametrização das
   *          máscaras identificadas pelo formatKey_.
   * 
   * @return String que apresenta o valor informado formatado segundo a máscara
   *         e Locale de formatação. Será retornado uma string vazia ("") caso o
   *         valor informado para formatação seja null.
   */
  private String format( Object valueToFormat_, String formatKey_,
                        Locale locale_, MessageResources messageResources_ )
  {
    Format format = null;
    String formattedValue = null;

    if ( formatKey_ == null || "".equals( formatKey_ ) )
    {
      throw new UnexpectedException( "formatKey_ not specified." );
    }
    if ( locale_ == null )
    {
      throw new UnexpectedException( "locale_ not specified." );
    }
    if ( messageResources_ == null )
    {
      throw new UnexpectedException( "messageResources_ not specified." );
    }

    if ( valueToFormat_ != null )
    {
      String formatString = getFormatString( formatKey_, locale_,
                                             messageResources_ );
      format = createFormat( valueToFormat_, locale_, formatString );
      formattedValue = format.format( valueToFormat_ );
    }
    return formattedValue;
  }

  /**
   * 
   * Constrói uma instância de Format, considerando o tipo do objeto que será
   * formatado, o Locale e a máscara de formatação.
   * 
   * @param valueToFormat_ - Valor que será formatado.
   * @param locale_ - Identificação de localização para aplicação do formato.
   * @param formatString_ - Máscara de formatação.
   * 
   * @return instância de Format para o tipo de objeto, máscara e Locale
   *         informados.
   */
  private Format createFormat( Object valueToFormat_, Locale locale_,
                              String formatString_ )
  {
    Format format = null;

    if ( valueToFormat_ instanceof BigDecimal
         || valueToFormat_ instanceof BigInteger
         || valueToFormat_ instanceof Integer )
    {
      format = DecimalFormat.getNumberInstance( locale_ );
      ( ( DecimalFormat ) format ).applyLocalizedPattern( formatString_ );
    }
    else
    {
      format = SimpleDateFormat.getDateInstance();
      ( ( DateFormat ) format ).setLenient( false );
      ( ( SimpleDateFormat ) format ).applyLocalizedPattern( formatString_ );
    }

    return format;
  }

  /**
   * 
   * Obtém a máscara de formatação do arquivo de propriedades que contempla as
   * máscaras paramentrizadas por nome.
   * 
   * @param formatKey_ - Nome da máscara de formatação.
   * @param locale_ - Locale de aplicação da máscara.
   * @param messageResources_ - Arquivo de propriedades que contempla as
   *          máscaras de formatação parametrizadas.
   * @return máscara de formatação.
   */
  private String getFormatString( String formatKey_, Locale locale_,
                                 MessageResources messageResources_ )
  {
    String formatString = messageResources_.getMessage( locale_, formatKey_ );
    if ( formatString == null
         || ( formatString.startsWith( "???" ) && formatString.endsWith( "???" ) ) )
    {
      throw new UnexpectedException( "formatKey_ [" + formatKey_
                                     + "] not found in messageResources_ ["
                                     + messageResources_.getConfig() + "]" );
    }
    return formatString;
  }
}