package com.citibank.ods.modules.product.broker.functionality.valueobject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Retention(RetentionPolicy.RUNTIME)
public @interface CellProperty {

	//public static final Set<String> YES_VALUES = new HashSet<String>(Arrays.asList("yes","sim","y","s","true","t"));
	
	int value();
	String[] patterns() default  {"yyyy-MM-dd kk:mm:ss", "dd-MM-yyyy hh:mm:ss", "dd/MM/yyyy hh:mm:ss","dd/MM/yyyy hh:mm:ssss","yyyy/MM/dd hh:mm:ss","yyyy/MM/dd hh:mm:ssss", "yyyy/MM/dd", "yy/MM/dd", "dd/MM/yyyy", "dd/MM/yy" };
	int maxLength() default Integer.MAX_VALUE;
	boolean isMandatory() default false;
	String mustMatch() default "";
}
