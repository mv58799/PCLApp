package com.citibank.ods.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String printNTimes(String c, int times ){
    	return printNTimes(c,", ",  times);
    }
    public static String printNTimes(String c, String intercalatedBy, int times ){
    	StringBuilder sb = new StringBuilder();
    	for (int x =0; x < times; x++){
    		sb.append(c).append(intercalatedBy);
    	}
    	return sb.toString().replaceAll(intercalatedBy + "$", "");
    }
    
    public static Integer parseInteger(BigDecimal v){
    	if (v == null)
    		return null;
    	return v.intValue();
    }
    public static Integer parseInteger(BigInteger v){
    	if (v == null)
    		return null;
    	return v.intValue();
    }
    
    public static java.sql.Date parseSqlDate(Date d){
    	if (d==null)
    		return null;
    	return new java.sql.Date(d.getTime());
    }
    
    public static java.sql.Timestamp parseSqlTimestamp(Date d){
    	if (d==null)
    		return null;
    	return new java.sql.Timestamp(d.getTime());
    }
    
    public static String getTextFromField(String s){
    	if (s== null)
    		return "";
    	String rep = s.replaceAll("([A-Z]+)", " $1");
    	return rep.substring(0,1).toUpperCase() + rep.substring(1) ;
    }

    public static Date parseDate(String d) {
    	return parseDate(d, "dd/MM/yyyy");
    }
	public static Date parseDate(String d, String pattern) {
		if (d == null)
			return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(d);
		} catch (ParseException e) {
		}
		return null;
	}
    
	public static boolean isEmpty(String str){
		if (str == null)
			return true;
		
		return (str.trim().length() <= 0);
	}
	
	public static boolean isAllEmpty(String... str){
		if (str == null || str.length ==0)
			return true;
		boolean contains = false;
		for (String s: str){
			if (s==null) continue;
			
			if (s.trim().length() <= 0)
				continue;
			else 
				contains = true;
		}
		
		return !contains;
	}
	

	public static BigDecimal parseBigDecimal(Object valorNovo) {
		if (valorNovo==null)
			return null;
		try {
			if (valorNovo instanceof String){
				return new BigDecimal(tryParseDouble((String)valorNovo));
			}else if (valorNovo instanceof Integer){
				return new BigDecimal((Integer)valorNovo);
			}else if (valorNovo instanceof Long){
				return BigDecimal.valueOf((Long)valorNovo);
			}else if (valorNovo instanceof Double){
				return BigDecimal.valueOf((Double)valorNovo);
			}else {
				return new BigDecimal(valorNovo.toString());
			}
		}catch(Exception e){
			return null;
		}
	}
	

	public static BigInteger parseBigInteger(Object valorNovo) {
		if (valorNovo==null)
			return null;
		try {
		if (valorNovo instanceof String){
			return new BigInteger((String)valorNovo);
		}
		if (valorNovo instanceof Integer){
			return BigInteger.valueOf((Integer)valorNovo);
		}
		if (valorNovo instanceof Long){
			return BigInteger.valueOf((Long)valorNovo);
		}
		if (valorNovo instanceof Double){
			return BigInteger.valueOf(new Double((Double)valorNovo).longValue());
		}
		if (valorNovo instanceof BigDecimal){
			return BigInteger.valueOf(((BigDecimal)valorNovo).longValue());
		}
		}catch(Exception e){
			return null;
		}
		return null;
	}
	
	public static Long tryParseLong(String s){
		try {
			if (s==null)
				return null;
			
			return Long.valueOf(removeNonNumericChar(s));
		}catch(Exception e){
			return null;
		}
	}

	public static Double tryParseDouble(String s) throws Exception{
		if (s==null)
			return null;
		NumberFormat sf = NumberFormat.getInstance();
		sf.setMaximumFractionDigits(2);
		sf.setParseIntegerOnly(false);
		s = s.trim();
		if (s.matches("^.*\\.\\d\\d$")){
			sf.setMinimumFractionDigits(2);
			String left = s.replaceAll("(^.*)(\\.\\d\\d)$", "$1");
			String right = s.replaceAll("(^.*)(\\.\\d\\d)$", "$2");
			String num = left.replaceAll("\\D", "")+ right;
			Number parse = sf.parse(num);
			return new Double(num);
		}else if (s.matches("^.*,\\d\\d$")){
			sf.setMinimumFractionDigits(2);
			String left = s.replaceAll("(^.*)(,\\d\\d)$", "$1");
			String right = s.replaceAll("(^.*)(,\\d\\d)$", "$2");
			String num = left.replaceAll("\\D", "")+ right.replaceAll(",", ".");
			return new Double(num);
		}else {
			sf.setMinimumFractionDigits(0);
			return Double.valueOf(s.replaceAll("\\D", ""));
		}
	}

	public static <T extends Number> String  stringOf(T s){
		if (s == null)
			return null;
		NumberFormat sf = NumberFormat.getInstance();
		if (s instanceof Double){
			sf.setMaximumFractionDigits(2);
			sf.setParseIntegerOnly(false);
			sf.setMinimumFractionDigits(2);
			sf.setGroupingUsed(false);
			return sf.format((Double) s);
		} else if (s instanceof Long){
			sf.setMaximumFractionDigits(0);
			sf.setParseIntegerOnly(true);
			sf.setGroupingUsed(false);
			sf.setMinimumFractionDigits(0);
			return sf.format((Long) s);
		} else if (s instanceof BigDecimal){
			sf.setMaximumFractionDigits(2);
			sf.setParseIntegerOnly(false);
			sf.setGroupingUsed(false);
			sf.setMinimumFractionDigits(2);
			return sf.format(((BigDecimal) s).doubleValue());
		} else if (s instanceof BigInteger){
			sf.setMaximumFractionDigits(0);
			sf.setParseIntegerOnly(true);
			sf.setGroupingUsed(false);
			sf.setMinimumFractionDigits(0);
			return sf.format(((BigInteger) s).intValue());
		} else if (s instanceof Integer){
			sf.setMaximumFractionDigits(0);
			sf.setParseIntegerOnly(true);
			sf.setGroupingUsed(false);
			sf.setMinimumFractionDigits(0);
			return sf.format((Integer) s);
		}else {
			return s.toString();
		}
	}
	public static String removeNonNumericChar(String e){
		
		return e==null?null:e.replaceAll("\\D", "");
	}

	public static String dateToString(Date subscptOpenDate, String pattern) {
		if (subscptOpenDate == null)
			return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(subscptOpenDate);
	}

	public static void setTimestamp(int i, PreparedStatement ps,
			Date date) throws SQLException {
		if (date!= null){
			ps.setTimestamp(i,new Timestamp(date.getTime()));
		}else {
			ps.setNull(i, Types.TIMESTAMP);
		}
	}

}
