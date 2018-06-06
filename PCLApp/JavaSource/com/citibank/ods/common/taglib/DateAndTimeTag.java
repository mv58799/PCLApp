/*
 * Created on May 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.common.taglib;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.jsp.JspException;

/**
 * @author marcelo.s.oliveira
 *
 * CustomTag que imprime data e hora GMT -3
 */
public class DateAndTimeTag extends BaseBodyTag{
	public int doStartTag() throws JspException {
		Locale locale = new Locale("pt","BR");
		SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm'H' 'GMT -3'", locale);
		Date today = new Date();

		try {
			pageContext.getOut().print(df.format(today).toUpperCase());
		 } catch (IOException ioe) {
		 	throw new JspException("Error ao mostrar data e hora");
		 }
		return EVAL_BODY_BUFFERED;
		
	}
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
		
	}
}
