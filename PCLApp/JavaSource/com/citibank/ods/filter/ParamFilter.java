package com.citibank.ods.filter;


/*
 * Struts 1 Fix for CVE2014-0114
 * Targeted to Servlet v2.3 (Struts 1.3.x)
 * Note: Servlet v2.2 does not support Filters
 * Note: Servlet v3.0 is used in Struts 2
 * 
 * Configure the location of your error page in web.xml with parameter 'errorPage'
 * Configure location of temp folder to write uploaded files to within web.xml's parameter 'filleUploadTempRepository'
 * You will need to log the request when an invalid String is found according to how your app is performing logging
 */

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.citibank.ods.common.logger.ApplicationLogger;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ParamFilter implements Filter {
	
 private String excludeParams;
 private String unicodePattern;
 private String octalPattern;
 private String errorPage;
 private Pattern classLoaderPattern;
 private Pattern unicodeCompiledPattern;
 private Pattern octalCompiledPattern;
 private String fileUploadTempRepository = "fileUploadTempRepository";
 //public static final String DEFAULT_COMMAND_PATTERN = "(.*\.|^|.*|\[('|" + '))(c|C)lass(\\.|('+"'"+'|")]|\[).*';
 public static final String DEFAULT_COMMAND_PATTERN = "(.*\\.|^|.*|\\[('|\"))(c|C)lass(\\.|('|\")]|\\[).*";
 private String decodedValue = "";		
 
public void init(FilterConfig config) throws ServletException {
	
	//excludeParams = config.getInitParameter("excludeParams");
	//unicodePattern = config.getInitParameter("unicodePattern");
	//octalPattern = config.getInitParameter("octalPattern");
	
	classLoaderPattern = Pattern.compile(DEFAULT_COMMAND_PATTERN, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
	unicodeCompiledPattern = Pattern.compile("\\\\u([A-Fa-f0-9]{4})");
	octalCompiledPattern = Pattern.compile("\\\\([0-7]{1,4})");
	errorPage = config.getInitParameter("errorPage");
	fileUploadTempRepository = config.getInitParameter(fileUploadTempRepository);
	
}

public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	ApplicationLogger.init();
	// check the request type
		if (req instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;
			
			boolean isParameterValid = true;
			boolean isCookieValid = true;
			
			//request.setCharacterEncoding("UTF-8");
			
			//Check if it is a multi-part post 
			if (request.getContentType()!=null && request.getContentType().toUpperCase().startsWith(("MULTIPART"))) {
				//Get the multi-part post 
				try {
					request = new MultiReadHttpServletRequest(request);
					isParameterValid = validateMultiPart((MultiReadHttpServletRequest) request);
				} catch (FileUploadException fue) {
					ApplicationLogger.getInstance().error("The file could not be uploaded. ");					
				}
			} else {
				// normal request, handle with parameter map
				if (request.getParameterMap() != null) {
					isParameterValid = validateParameter(request.getParameterMap()); 
				} 
			}
			
			//Get the cookie values (checks name and value) - do this only if the parameter check was successful
			if (isParameterValid && request.getCookies() != null) {
				Cookie[] cookies = request.getCookies();
				isCookieValid = validateCookies(cookies);
			} else {
				isCookieValid = true;
			}
			
			// Customize this if you want to handle it according to your app's error handling paradigm.
			if (!isParameterValid || !isCookieValid) {
				ApplicationLogger.getInstance().error("ParamFilter: Error on attempting acess the URL. isParameterValid:["+isParameterValid + "] isCookieValid:["+isCookieValid + "]");	
				
				response.reset();
				response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
				response.sendRedirect(request.getContextPath()+errorPage);
				request.getSession().invalidate();
				return;	
			} else {
				// no problems with request, continue the chain
				chain.doFilter(request, response);
			}
		} else {
			// not an HTTP request
			ApplicationLogger.getInstance().info("ParamFilter: not an HTTP request");
			chain.doFilter(req, res);
		}
}

private boolean validateMultiPart(MultiReadHttpServletRequest request) throws UnsupportedEncodingException, FileUploadException {
	DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
	fileItemFactory.setRepository(new File(fileUploadTempRepository));
	ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
	boolean isMpParamValid = true;
	
	
	List items = upload.parseRequest(request);
	Iterator it = items.iterator();
	while (it.hasNext()) {
		FileItem item = (FileItem) it.next();
		if (item.isFormField()) {
			// this is a form field
			decodedValue = decode(item.getFieldName());
			Matcher matcher = classLoaderPattern.matcher(decodedValue); //name of form field
			if (matcher.find()) {
				isMpParamValid = false;
				break;
			}else{
				decodedValue = "";
				matcher.reset();
			}
			decodedValue = decode(item.getString("UTF-8"));
			matcher = classLoaderPattern.matcher(decodedValue); //name of form value
			if (matcher.find()) {
				isMpParamValid = false;
				break;
			}else{
				decodedValue = "";
				matcher.reset();
			}
			
		} else {	
			// this is a file we are looking at
			decodedValue = decode(item.getFieldName());
			Matcher matcher = classLoaderPattern.matcher(decodedValue); //name of form field
			if (matcher.find()) {
				isMpParamValid = false;
				break;
			}else{
				decodedValue = "";
				matcher.reset();
			}
			decodedValue = decode(item.getName());
			matcher = classLoaderPattern.matcher(decodedValue); //filename
			if (matcher.find()) {
				isMpParamValid = false;
				break;
			}
			decodedValue = "";
			matcher.reset();
		}
    }
	return isMpParamValid;

}

/**
 * Returns boolean indicating whether the cookies are valid.
 */
private boolean validateCookies(Cookie[] cookies) {
	boolean isCookieValid = true;
	for (int i = 0; i < cookies.length; i++) {
		String name = cookies[i].getName();
		String value = cookies[i].getValue();
		decodedValue = decode(name);
		Matcher matcher = classLoaderPattern.matcher(decodedValue);
		if (matcher.find()) {
			isCookieValid = false;
			break;
		}else{
		matcher.reset();
		decodedValue = "";
		}
		decodedValue = decode(value);
		matcher = classLoaderPattern.matcher(decodedValue);
		if (matcher.find()) {
			isCookieValid = false;
			break;
		}else{
			matcher.reset();
			decodedValue = "";
		}
		decodedValue = "";
		matcher.reset();
	}
	return isCookieValid;
}

/**
 * Returns boolean indicating whether the request parameters are valid.
 */
private boolean validateParameter(Map map) {
	boolean isParamValid = true;
	Iterator entries = map.entrySet().iterator();
	while (entries.hasNext()) {
		Entry thisEntry = (Entry) entries.next();
		String paramName = (String) thisEntry.getKey();
		String[] paramValues = (String[]) thisEntry.getValue();
		decodedValue = decode(paramName);
		Matcher matcher = classLoaderPattern.matcher(decodedValue);
		if (classLoaderPattern.matcher(decodedValue).find()) {
			isParamValid = false;
			System.out.println(decodedValue);
			break;			
		}else{
			matcher.reset();
			decodedValue = "";
		}
		
		for (int i = 0; i < paramValues.length; i++) {
			decodedValue = decode(paramValues[i]);
			if (classLoaderPattern.matcher(decodedValue).find()) {
				isParamValid = false;
				System.out.println(decodedValue);
				break;
			}else{
				matcher.reset();
				decodedValue = "";
		    }
		matcher.reset();
		decodedValue = "";	
	}
 }
	return isParamValid;
}
private String decode(String input) {
	String result = null;
	
	if (input == null)
		return null;
	
	// URL decoding
	String urlDecode = null;
	try {
		urlDecode = URLDecoder.decode(input, "UTF-8");
	}
	catch (Exception ex) {
		urlDecode = input;
	}
	result = urlDecode;
	
	try {
		String temp = null;
		// decode unicode
		Matcher m1 = unicodeCompiledPattern.matcher(result);      
		StringBuffer b1 = new StringBuffer();
		while (m1.find())
		{
			temp = String.valueOf(((char)Integer.parseInt(m1.group(1), 16)));
			m1.appendReplacement(b1, convertDollarOrBackslash(temp));
		}
		m1.appendTail(b1);
		result = b1.toString();
		//System.out.println("Unicode decoding result: " + result);
		//debugLog("Unicode decoding result: " + result);
				
		// decode hex code
		/*Matcher m2 = hexPattern.matcher(result);
		StringBuffer b2 = new StringBuffer();
		while (m2.find())
		{
			temp = String.valueOf(((char)Integer.parseInt(m2.group(1), 16)));
			m2.appendReplacement(b2, convertDollarOrBackslash(temp));
		}
		m2.appendTail(b2);
		result = b2.toString();
		debugLog("Hex decoding result: " + result);
		*/
		// decode octal code	
		Matcher m3 = octalCompiledPattern.matcher(result);
		StringBuffer b3 = new StringBuffer();
		while (m3.find())
		{
			temp = String.valueOf(((char)Integer.parseInt(m3.group(1), 8)));
			m3.appendReplacement(b3, convertDollarOrBackslash(temp));
		}
		m3.appendTail(b3);
		result = b3.toString();
		//System.out.println("Octal decoding result: " + result);
		//debugLog("Octal decoding result: " + result);
		
	}
	catch (Throwable th) {
		System.out.println("Filter decoding result: " + result);
		//errorLog(th.getMessage());
	}

	return result;
}

private String convertDollarOrBackslash(String input) {
	if ("\\".equals(input))
		return "\\\\";
	else if ("$".equals(input))
		return "\\$";
	else
		return input;
}

public void destroy() { }

}
