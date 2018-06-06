package com.citibank.ods.persistence.bg.dao.factory;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.persistence.dao.factory.BaseDAOFactory;
import com.citibank.ods.persistence.bg.dao.TbgBankDAO;
import com.citibank.ods.persistence.bg.dao.TbgBranchDAO;
import com.citibank.ods.persistence.bg.dao.TbgCustAddressDAO;
import com.citibank.ods.persistence.bg.dao.TbgCustCellDAO;
import com.citibank.ods.persistence.bg.dao.TbgCustMailDAO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.bg.dao.factory;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 18, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BGDAOFactory extends BaseDAOFactory
{

  /**
   * Comment for <code>C_DAO_FACTORY_SYSTEM</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private static final String C_DAO_FACTORY_SYSTEM = "bg";

  /**
   * Comment for <code>ms_instance</code>: DAO Factory singleton instance
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  protected static BGDAOFactory ms_instance;

  /**
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  public static void bootstrap()
  {
    if ( ms_instance != null )
    {
      throw new UnexpectedException(
                                     "bootstrap cannot be invoke more than once." );
    }
    ms_instance = ( BGDAOFactory ) BaseDAOFactory.createDAOFactory( C_DAO_FACTORY_SYSTEM );
  }

  /**
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  public BGDAOFactory()
  {
    if ( ms_instance != null )
    {
      throw new UnexpectedException( "This constructor cannot be called twice" );
    }
  }

  /**
   * @return
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  public static final BGDAOFactory getInstance()
  {
    return ms_instance;
  }

  /**
   * @return
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  public abstract TbgBankDAO getTbgBankDAO();

  public abstract TbgBranchDAO getTbgBranchDAO();

  public abstract TbgOfficerDAO getTbgOfficerDAO();

  public abstract TbgCustAddressDAO getTbgCustAddressDAO();

  public abstract TbgCustMailDAO getTbgCustMailDAO();

  public abstract TbgCustCellDAO getTbgCustCellDAO();

}