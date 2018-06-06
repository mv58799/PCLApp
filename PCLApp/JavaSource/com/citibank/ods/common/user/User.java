package com.citibank.ods.common.user;

import java.io.Serializable;
import java.util.ArrayList;

import com.citibank.newcpb.security.UserAccessBean;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * Classe que representa um usuário no sistema
 * 
 *@see package com.citibank.ods.common.User; 
 *@version 1.0
 *@author bruno.zanetti,Mar 14, 2007
 *
 *<PRE>
 *<U>Updated by: Marcelo S. Oliveira</U>
 *<U>Description: Adição de atributos relacionados ao componente de autorização SecurityGateway</U>
 *</PRE>
 */

public class User implements Serializable
{
  // SOEID do usuário
  protected String m_userID;
  protected String m_ipAddress;
  protected String m_sessionSpecs;
  protected ArrayList m_functions;
  protected String m_firstName;
  protected String m_lastName;
  
  private UserAccessBean userAccess;
  
  /**
   * Construtor Nulo a ser utilizado no modo de simulação
   * do securityGateway
   * TODO : Contrutor que recebe instância da classe LegacyConector
   * e preenche os atributos da classe user com dados vindos do SecurityGateway
   */
  public User(){
  	m_userID = null;
  	m_ipAddress = null;
	m_sessionSpecs = null;
	m_functions = null;
	m_firstName = "";
	m_lastName = "";
  }
  
  /**
   * @return Returns soeID.
   */
  public String getUserID()
  {
    return m_userID;
  }
  /**
   * @param soeID_ Field soeID to be setted.
   */
  public void setUserID( String soeID_ )
  {
    m_userID = soeID_;
  }
/**
 * @return Returns the functions.
 */
public ArrayList getFunctions() {
	return m_functions;
}
/**
 * @param functions The functions to set.
 */
public void setFunctions(ArrayList functions_) {
	this.m_functions = functions_;
}
/**
 * @return Returns the ipAddress.
 */
public String getIpAddress() {
	return m_ipAddress;
}
/**
 * @param address The ipAddress to set.
 */
public void setIpAddress(String address) {
	m_ipAddress = address;
}

/**
 * @return Returns the sessionSpecs.
 */
public String getSessionSpecs() {
	return m_sessionSpecs;
}
/**
 * @param specs The sessionSpecs to set.
 */
public void setSessionSpecs(String specs) {
	m_sessionSpecs = specs;
}
/**
 * @return Returns the m_firstName.
 */
public String getFirstName() {
	return m_firstName;
}
/**
 * @param name The m_firstName to set.
 */
public void setFirstName(String name) {
	m_firstName = name;
}

public String getLastName() {
	return m_lastName;
}
/**
 * @param name The m_firstName to set.
 */
public void setLastName(String name) {
	m_lastName = name;
}

public UserAccessBean getUserAccess() {
	return userAccess;
}

public void setUserAccess(UserAccessBean userAccess) {
	this.userAccess = userAccess;
}
}
