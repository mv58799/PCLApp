/*
 * Created on Mar 26, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import com.citibank.ods.Globals;
import com.citibank.ods.common.logger.ApplicationLogger;
import com.citibank.ods.common.logger.LoggerFactory;

/**
 * @author leonardo.nakada
 * 
 * Classe utilitária de validação
 */
public class ODSValidator {
	/*
	 * Validar Campo BigInteger
	 */
	public static final synchronized void validateBigInteger(
		String fieldName_,
		String fieldValue_,
		int maxLength_,
		ActionErrors errors_) {
		if (fieldValue_ != null && !"".equals(fieldValue_)) {
			try {
				new BigInteger(fieldValue_);

				validateMaxLength(fieldName_, fieldValue_, maxLength_, errors_);
			} catch (Exception e) {
				errors_.add(
					"validateBigInteger",
					new ActionMessage(
						"INCORRECT_DATATYPE_INTEGER",
						fieldName_));
			}
		}
	}

	/*
	 * Valida MaxLength
	 */
	public static final synchronized void validateMaxLength(
		String fieldName_,
		String fieldValue_,
		int maxLength_,
		ActionErrors errors_) {
		if (fieldValue_ != null && fieldValue_.length() > maxLength_) {
			errors_.add(
				"validateMaxLength",
				new ActionMessage(
					"MAX_LENGTH_ERROR",
					fieldName_,
					String.valueOf(maxLength_)));
		}
	}

	/*
	 * Valida Data
	 */
	public static final synchronized void validateDate(
		String fieldName_,
		String fieldValue_,
		ActionErrors errors_) {
		if (fieldValue_ != null && !"".equals(fieldValue_)) {
			SimpleDateFormat sdf =
				new SimpleDateFormat(
					Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY);
			sdf.setLenient(false);

			try {
				sdf.parse(fieldValue_);
			} catch (Exception e) {
				errors_.add(
					"validateDate",
					new ActionMessage(
						"INCORRECT_DATATYPE_DATE",
						fieldName_,
						Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY));
			}
		}
	}

	public static final synchronized void validateDateHour(
															String fieldName_,
															String fieldValue_,
															ActionErrors errors_) {
		
		if (fieldValue_ != null && !"".equals(fieldValue_)) {
			SimpleDateFormat sdf =	new SimpleDateFormat(Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM);
			
			sdf.setLenient(false);

			try {
				sdf.parse(fieldValue_);
			} catch (Exception e) {
				errors_.add(
					"validateDate",
					new ActionMessage(
						"INCORRECT_DATATYPE_DATE",
						fieldName_,
						Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM));
			}
		}
	}

	/*
	 * Valida BigDecimal
	 */
	public static final synchronized void validateBigDecimal(
		String fieldName_,
		String fieldValue_,
		int maxPrecision,
		int maxScale,
		ActionErrors errors_) {
		if (fieldValue_ != null && !"".equals(fieldValue_)) {
			try {
				BigDecimal decimal = new BigDecimal(fieldValue_);

				BigInteger integerPart = decimal.toBigInteger();
				int decimalPart = decimal.scale();

				if (String.valueOf(integerPart).length() > maxPrecision
					|| decimalPart > maxScale) {
					errors_.add(
						"validateBigDecimal",
						new ActionMessage(
							"MAX_LENGTH_DECIMAL",
							fieldValue_,
							String.valueOf(maxPrecision),
							String.valueOf(maxScale)));
				}
			} catch (NumberFormatException nfe) {
				errors_.add(
					"validateBigDecimal",
					new ActionMessage(
						"INCORRECT_DATATYPE_DECIMAL",
						fieldName_));
			}
		}
	}

	/*
	 * Valida Alfanumérico
	 */
	public static final synchronized void validateAlphanumeric(
		String fieldName_,
		String fieldValue_,
		int maxLength_,
		ActionErrors errors_) {
		if (fieldValue_ != null && !"".equals(fieldValue_)) {
			String regex = "[0-9A-Za-z]*";
			Pattern p = Pattern.compile(regex);

			Matcher matcher = p.matcher(fieldValue_);
			if (!matcher.matches()) {
				errors_.add(
					"validateAlphanumeric",
					new ActionMessage(
						"INCORRECT_DATATYPE_ALPHANUMERIC",
						fieldName_));
			} else {
				validateMaxLength(fieldName_, fieldValue_, maxLength_, errors_);
			}
		}
	}

}