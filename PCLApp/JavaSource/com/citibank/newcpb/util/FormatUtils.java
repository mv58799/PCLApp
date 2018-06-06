package com.citibank.newcpb.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.MessageResources;

import com.citibank.newcpb.exception.UnexpectedException;

public class FormatUtils {

	private static final FormatUtils ms_instance = new FormatUtils();
	private static final BigInteger C_BIG_INTEGER_INSTANCE = new BigInteger("0");
	private static final Integer C_INTEGER_INSTANCE = new Integer(0);
	private static final Date C_DATE_INSTANCE = new Date();

	public static final String C_FORMAT_DATE_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd.HH.mm. ss. SSS";
	public static final String C_FORMAT_DATE_DD_MM_YYYY_HHMMSS = "dd/MM/yyyy HH:mm:ss";
	public static final String C_FORMAT_DATE_DD_MM_YYYY_HHMM = "dd/MM/yyyy HH:mm";
	public static final String C_FORMAT_DATE_DD_MM_YYYY = "dd/MM/yyyy";

	protected FormatUtils() {
		super();
	}

	public static FormatUtils getInstance() {
		return ms_instance;
	}

	public String format(BigDecimal valueToFormat_, String formatKey_, Locale locale_, MessageResources messageResources_) {
		return format((Object) valueToFormat_, formatKey_, locale_, messageResources_);
	}

	public String format(BigInteger valueToFormat_, String formatKey_, Locale locale_, MessageResources messageResources_) {
		return format((Object) valueToFormat_, formatKey_, locale_, messageResources_);
	}

	public String format(Integer valueToFormat_, String formatKey_, Locale locale_, MessageResources messageResources_) {
		return format((Object) valueToFormat_, formatKey_, locale_, messageResources_);
	}

	public String format(Date valueToFormat_, String formatKey_, Locale locale_, MessageResources messageResources_) {
		return format((Object) valueToFormat_, formatKey_, locale_, messageResources_);
	}

	public BigDecimal toBigDecimal(String valueToParse_, String formatKey_, Locale locale_, MessageResources messageResources_) {
		BigDecimal parsedValue = null;
		Format format = null;
		Number parsedNumber = null;
		final char C_CHAR_0 = '0';
		final char C_CHAR_9 = '9';
		final char C_CHAR_DOT = '.';

		if (valueToParse_ != null && !"".equals(valueToParse_)) {
			String formatSring = getFormatString(formatKey_, locale_, messageResources_);
			format = createFormat(C_BIG_INTEGER_INSTANCE, locale_, formatSring);
			((NumberFormat) format).setParseIntegerOnly(true);
			ParsePosition parsePosition = new ParsePosition(0);

			parsedNumber = ((NumberFormat) format).parse(valueToParse_, parsePosition);
			if (valueToParse_.length() > parsePosition.getIndex()) {
				valueToParse_ = valueToParse_.substring(parsePosition.getIndex() + 1);
			} else {
				valueToParse_ = valueToParse_.substring(parsePosition.getIndex());
			}
			boolean end = false;
			StringBuffer mantissa = new StringBuffer(valueToParse_.length() + 1);
			for (int i = 0; i < valueToParse_.length() && !end; i++) {
				char parsedChar = valueToParse_.charAt(i);
				if (parsedChar < C_CHAR_0 || parsedChar > C_CHAR_9) {
					end = true;
				} else {
					mantissa.append(parsedChar);
				}
			}
			if (mantissa.length() > 0) {
				mantissa.insert(0, C_CHAR_DOT);
			}

			BigDecimal parsedCharacteristic = new BigDecimal(parsedNumber.doubleValue());

			parsedValue = new BigDecimal(parsedCharacteristic.toString() + mantissa.toString());
		}
		return parsedValue;

	}

	public BigDecimal toBigDecimal(String value) {
		NumberFormat format = NumberFormat.getInstance(Locale.US);
		try {
			Number number = format.parse(value);
			return new BigDecimal(number.doubleValue());
		} catch (ParseException e) {
			throw new RuntimeException();
		}

	}

	public String formatNumber(BigDecimal value) {
		NumberFormat format = NumberFormat.getInstance(Locale.US);

		String number = format.format(value.doubleValue());
		return number;
	}

	public BigInteger toBigInteger(String valueToParse_, String formatKey_, Locale locale_, MessageResources messageResources_) {
		BigInteger parsedValue = null;
		Format format = null;
		Number parsedNumber = null;

		if (valueToParse_ != null && !"".equals(valueToParse_)) {
			String formatSring = getFormatString(formatKey_, locale_, messageResources_);
			format = createFormat(C_BIG_INTEGER_INSTANCE, locale_, formatSring);
			try {
				parsedNumber = ((NumberFormat) format).parse(valueToParse_);
			} catch (ParseException e_) {
				throw new UnexpectedException("valueToParse_ [" + valueToParse_ + "] cannot be parsed by pattern [" + formatSring + "] of formatKey_ [" + formatKey_
						+ "] in messageResources [" + messageResources_.getConfig() + "] using locale_ [" + locale_.toString(), e_);
			}
			BigDecimal parsedCharacteristic = new BigDecimal(parsedNumber.doubleValue());
			parsedValue = new BigInteger(parsedCharacteristic.toString());
		}
		return parsedValue;
	}

	public Integer toInteger(String valueToParse_, String formatKey_, Locale locale_, MessageResources messageResources_) {
		Integer parsedValue = null;
		Format format = null;
		Number parsedNumber = null;

		if (valueToParse_ != null && !"".equals(valueToParse_)) {
			String formatSring = getFormatString(formatKey_, locale_, messageResources_);
			format = createFormat(C_INTEGER_INSTANCE, locale_, formatSring);
			try {
				parsedNumber = ((NumberFormat) format).parse(valueToParse_);
			} catch (ParseException e_) {
				throw new UnexpectedException("valueToParse_ [" + valueToParse_ + "] cannot be parsed by pattern [" + formatSring + "] of formatKey_ [" + formatKey_
						+ "] in messageResources [" + messageResources_.getConfig() + "] using locale_ [" + locale_.toString(), e_);
			}
			BigDecimal parsedCharacteristic = new BigDecimal(parsedNumber.doubleValue());
			parsedValue = new Integer(parsedCharacteristic.toString());
		}
		return parsedValue;
	}

	public Date toDate(String valueToParse_, String formatKey_, Locale locale_, MessageResources messageResources_) {
		Date parsedValue = null;
		Format format = null;

		if (valueToParse_ != null && !"".equals(valueToParse_)) {
			String formatSring = getFormatString(formatKey_, locale_, messageResources_);
			format = createFormat(C_DATE_INSTANCE, locale_, formatSring);
			try {
				parsedValue = ((DateFormat) format).parse(valueToParse_);
			} catch (ParseException e_) {
				throw new UnexpectedException("valueToParse_ [" + valueToParse_ + "] cannot be parsed by pattern [" + formatSring + "] of formatKey_ [" + formatKey_
						+ "] in messageResources [" + messageResources_.getConfig() + "] using locale_ [" + locale_.toString(), e_);
			}
		}
		return parsedValue;

	}
	
	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(pattern);
		String dtFmt = date != null ? format.format(date) : "";
		return dtFmt;
	}

	public static Date formatToDate(String valueToParse_, String formatSring) {
		Date date = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(formatSring);
			date = formatter.parse(valueToParse_);
		} catch (ParseException e) {
			throw new UnexpectedException("valueToParse_ [" + valueToParse_ + "] cannot be parsed by pattern [" + formatSring + "]", e);
		}
		return date;

	}

	private String format(Object valueToFormat_, String formatKey_, Locale locale_, MessageResources messageResources_) {
		Format format = null;
		String formattedValue = null;

		if (formatKey_ == null || "".equals(formatKey_)) {
			throw new UnexpectedException("formatKey_ not specified.");
		}
		if (locale_ == null) {
			throw new UnexpectedException("locale_ not specified.");
		}
		if (messageResources_ == null) {
			throw new UnexpectedException("messageResources_ not specified.");
		}

		if (valueToFormat_ != null) {
			String formatString = getFormatString(formatKey_, locale_, messageResources_);
			format = createFormat(valueToFormat_, locale_, formatString);
			formattedValue = format.format(valueToFormat_);
		}
		return formattedValue;
	}

	private Format createFormat(Object valueToFormat_, Locale locale_, String formatString_) {
		Format format = null;

		if (valueToFormat_ instanceof BigDecimal || valueToFormat_ instanceof BigInteger || valueToFormat_ instanceof Integer) {
			format = DecimalFormat.getNumberInstance(locale_);
			((DecimalFormat) format).applyLocalizedPattern(formatString_);
		} else {
			format = SimpleDateFormat.getDateInstance();
			((DateFormat) format).setLenient(false);
			((SimpleDateFormat) format).applyLocalizedPattern(formatString_);
		}

		return format;
	}

	private String getFormatString(String formatKey_, Locale locale_, MessageResources messageResources_) {
		String formatString = messageResources_.getMessage(locale_, formatKey_);
		if (formatString == null || (formatString.startsWith("???") && formatString.endsWith("???"))) {
			throw new UnexpectedException("formatKey_ [" + formatKey_ + "] not found in messageResources_ [" + messageResources_.getConfig() + "]");
		}
		return formatString;
	}
	  
	  public static String generateIdLog(){
	  	try {
				return dateToStringFormated(new Date(), "ddMMyyyyHHmmssSSS");
			} catch (Exception e) {
				System.out.println("Erro ao gerar o IdLog.");
				e.printStackTrace();
				return "";
			}
	  }
	  
	  public static String dateToStringFormated(Date data, String format)  {
	      
	      if (data == null)  {
	              return null;
	          } 
	        
	      SimpleDateFormat sdf = new SimpleDateFormat(format);
	      return sdf.format(data);
	  }
	  
	public static String formatterValue(String value) throws ParseException {
		String formatedValue = null;
		if (value != null) {
			formatedValue = value.replace(".", "").replace("-", "").replace("/", "").replace("_", "");

			DecimalFormat formatter = new DecimalFormat();
			formatter.setGroupingSize(3);
			formatter.setMaximumFractionDigits(0);
			
			DecimalFormatSymbols newSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
			newSymbols.setGroupingSeparator(".".charAt(0));
			formatter.setDecimalFormatSymbols(newSymbols);
			formatedValue = formatter.format(Long.valueOf(value));
		}
		return formatedValue;
	}
	
	public static String unformatterValue(String value) throws ParseException {
		String unformatedValue = null;
		if (value != null) {
			unformatedValue = value.replace(".", "").replace("-", "").replace("/", "").replace("_", "");
		}
		return unformatedValue;
	}
	  
	    public static String unformatterDoc( String doc ) throws ParseException{
	        String unformatedDoc = null;
	        if ( doc != null ){
	            unformatedDoc = doc.replace(".", "").replace("-", "").replace("/", "").replace("_", "");
	        }
	        return unformatedDoc;
	    }
	    
	    public static String formatterDoc( String doc ) throws ParseException{
	        String unformatedDoc = null;
	        if ( doc != null ){
	            unformatedDoc = doc.replace(".", "").replace("-", "").replace("/", "").replace("_", "");
	            
	            if(unformatedDoc.length()<=11){
	            	StringUtils.leftPad(doc, 11, "0");
	            	unformatedDoc = formatCPF(unformatedDoc);
	            }else{
	            	StringUtils.leftPad(doc, 14, "0");
	            	unformatedDoc = formatCNPJ(unformatedDoc);
	            }
	        }
	        return unformatedDoc;
	    }
	    
	    public static String formatCPF(String rawCpf_) {
	    	rawCpf_ = rawCpf_.trim();
	    	while (rawCpf_.length() < 11) {
	    		rawCpf_ = "0" + rawCpf_;
	    	}

	    	rawCpf_ = rawCpf_.substring(rawCpf_.length() - 11);
	    	StringBuilder formattedCpf = new StringBuilder();

	    	formattedCpf.append(rawCpf_.substring(0, 3));
	    	formattedCpf.append(".");
	    	formattedCpf.append(rawCpf_.substring(3, 6));
	    	formattedCpf.append(".");
	    	formattedCpf.append(rawCpf_.substring(6, 9));
	    	formattedCpf.append("-");
	    	formattedCpf.append(rawCpf_.substring(9, 11));

	    	return formattedCpf.toString();
	    }
	    	
	    public static String formatCNPJ(String rawCnpj) {
	    	if (rawCnpj != null) {
	    		String stringCnpj = rawCnpj.toString();

	    		stringCnpj = stringCnpj.trim();
	    		while (stringCnpj.length() < 14) {
	    			stringCnpj = "0" + stringCnpj;
	    		}

	    		stringCnpj = stringCnpj.substring(stringCnpj.length() - 14);
	    		StringBuilder formattedCnpj = new StringBuilder();

	    		formattedCnpj.append(stringCnpj.substring(0, 2));
	    		formattedCnpj.append(".");
	    		formattedCnpj.append(stringCnpj.substring(2, 5));
	    		formattedCnpj.append(".");
	    		formattedCnpj.append(stringCnpj.substring(5, 8));
	    		formattedCnpj.append("/");
	    		formattedCnpj.append(stringCnpj.substring(8, 12));
	    		formattedCnpj.append("-");
	    		formattedCnpj.append(stringCnpj.substring(12, 14));

	    		return formattedCnpj.toString();
	    	}
	    	return "";
	    }
	    
		public static boolean isNumberInteger(String value){
			
			try {
				new Integer(value);
				return true;
			} catch (NumberFormatException e) {
				return false;
				
			} catch (Exception e) {
				return false;
				
			}
		}
		
		public static boolean isDateValidate(String value){
			
			try {			
				
				String DatePattern = "^(?:(31)(\\D)(0?[13578]|1[02])\\2|(29|30)(\\D)(0?[13-9]|1[0-2])\\5|(0?[1-9]|1\\d|2[0-8])(\\D)(0?[1-9]|1[0-2])\\8)((?:1[6-9]|[2-9]\\d)?\\d{2})$|^(29)(\\D)(0?2)\\12((?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)$";  
				if (value.matches(DatePattern) ) {  
					FormatUtils.stringToDate(value);
					return true;

				}else{  
					return false;
				} 
				
			} catch (NumberFormatException e) {
				return false;
				
			} catch (Exception e) {
				return false;			
			}
		}
		
	    public static Date stringToDate(String data) throws ParseException {   
	        if (data == null || data.equals(""))  
	            return null;  
	          
	        Date date = null;  
	        try {  
	            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	            date = (Date)formatter.parse(data);  
	        } catch (ParseException e) {              
	            throw e;  
	        }  
	        return date;  
	    }
	    
		public static BigDecimal formatStringToBigDecimalScale(String value){
			
		    if(value!=null && !value.equals("")){
		    	value = value.trim();
		    	value = value.replace(".", "");
		    	value = value.replace(",", ".");
		    	
		    	return new BigDecimal(value).setScale(2, BigDecimal.ROUND_UNNECESSARY);
		    	
		    }else{
		    	return null;
		    }
		}
}