package com.citibank.ods.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
  private ByteArrayOutputStream cachedBytes;

  public MultiReadHttpServletRequest(HttpServletRequest request) {
    super(request);
  }


  public ServletInputStream getInputStream() throws IOException {
    if (cachedBytes == null)
      cacheInputStream();

      return new CachedServletInputStream();
  }

  public BufferedReader getReader() throws IOException {
		if (cachedBytes == null)
			cacheInputStream();

		return new BufferedReader(new InputStreamReader(new CachedServletInputStream()));
	}
  
  private void cacheInputStream() throws IOException {
    /* Cache the inputstream in order to read it multiple times. 
     */
    cachedBytes = new ByteArrayOutputStream();
    ServletInputStream is = super.getInputStream();
    byte[] buffer = new byte[1024];
    int len;
    while ((len = is.read(buffer)) > -1 ) {
    	cachedBytes.write(buffer, 0, len);
    }
    cachedBytes.flush();
  }

  /* An inputstream which reads the cached request body */
  public class CachedServletInputStream extends ServletInputStream {
    private ByteArrayInputStream input;

    public CachedServletInputStream() {
      /* create a new input stream from the cached request body */
      input = new ByteArrayInputStream(cachedBytes.toByteArray());
    }


    public int read() throws IOException {
      return input.read();
    }
  }
}