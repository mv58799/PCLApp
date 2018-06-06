/*
 * Created on May 1, 2007
 *
 */
package com.citibank.ods.common.taglib;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

import com.citibank.ods.common.user.User;

/**
 * @author marcelo.s.oliveira
 * 
 * CustomTag que imprime mensagem de saudação para usuário
 */
public class WelcomeTag extends BaseBodyTag
{
  public int doStartTag() throws JspException
  {

    int morningPeriod = 12;
    int afternoonPeriod = 18;
    Calendar calendar = Calendar.getInstance();
    HttpSession session = pageContext.getSession();
    User user = ( User ) session.getAttribute( "user" );
    String userName = user.getFirstName() + " " + user.getLastName() + " - ";

    try
    {
      if ( calendar.get( Calendar.HOUR_OF_DAY ) <= morningPeriod )
      {
        pageContext.getOut().print( "Bom dia " + userName );
      }
      else
      {
        if ( calendar.get( Calendar.HOUR_OF_DAY ) <= afternoonPeriod )
        {
          pageContext.getOut().print( "Boa tarde " + userName );
        }
        else
        {
          pageContext.getOut().print( "Boa noite " + userName );
        }
      }
    }
    catch ( IOException ioe )
    {
      throw new JspException( "Error ao mostrar data e hora" );
    }
    return EVAL_BODY_BUFFERED;

  }

  public int doEndTag() throws JspException
  {
    return EVAL_PAGE;
  }
}