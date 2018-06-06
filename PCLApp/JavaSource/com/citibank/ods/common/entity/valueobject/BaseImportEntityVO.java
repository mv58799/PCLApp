package com.citibank.ods.common.entity.valueobject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.citibank.ods.common.util.Column;

public class BaseImportEntityVO extends BaseEntityVO {

	public String getColumnsForQuery(){
		StringBuilder sb = new StringBuilder();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field f : fields){
			if (f.isAnnotationPresent(Column.class)){
				Column c = f.getAnnotation(Column.class);
				sb.append(c.value()).append(", ");
			}
		}
		return sb.toString().replaceAll(",\\s$", "");
	}
	
	public String getValues(){
		StringBuilder sb = new StringBuilder();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field f : fields){
			if (f.isAnnotationPresent(Column.class)){
				Column c = f.getAnnotation(Column.class);
				sb.append(c.value()).append(", ");
			}
		}
		return sb.toString().replaceAll(",\\s$", "");
	}
	
	
	public void setValues(PreparedStatement ps){
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field f : fields){
			if (f.isAnnotationPresent(Column.class)){
				Column c = f.getAnnotation(Column.class);
				if (f.getType().isAssignableFrom(BigInteger.class)){
					
				}else if (f.getType().isAssignableFrom(BigDecimal.class)){
					
				}else if (f.getType().isAssignableFrom(Date.class)){
					
				}else if (f.getType().isAssignableFrom(String.class)){
					
				}
			}
		}
	}

	public String getValuesForUpdate() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StringBuilder sb = new StringBuilder();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field f : fields){
			if (f.isAnnotationPresent(Column.class)){
				Column c = f.getAnnotation(Column.class);
				sb.append(c.value()).append(" = ").append("?").append(", ");
			}
		}
		return sb.toString().replaceAll(",\\s$", "");
	}
//
//	private String getValueForSQL(Object o, Field f) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
//		Object property = PropertyUtils.getProperty(this, f.getName());
//		if (property ==null){
//			return "NULL";
//		}
//		if (f.getType().isAssignableFrom(BigInteger.class)){
//			return property.toString();
//		}else if (f.getType().isAssignableFrom(BigDecimal.class)){
//			return "TO_NUMBER(" + ((BigDecimal)property).toString() + ")";
//		}else if (f.getType().isAssignableFrom(Date.class)){
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
//			String format = sdf.format((Date)property);
//			return String.format("TO_DATE('%s', 'DD/MM/YYYY HH24:MI:SS'", format);
//		}else if (f.getType().isAssignableFrom(String.class)){
//			
//		}
//		return null;
//	}
}
