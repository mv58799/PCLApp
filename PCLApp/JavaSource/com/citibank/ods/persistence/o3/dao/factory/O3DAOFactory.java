package com.citibank.ods.persistence.o3.dao.factory;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.persistence.dao.factory.BaseDAOFactory;

/**
 * Title: Classe de métodos abstratos DAO do sistema ODS
 * @author ralf.davi.filho
 * @version 1.0
 */
public abstract class O3DAOFactory extends BaseDAOFactory
{

  /**
   * Comment for <code>C_DAO_FACTORY_SYSTEM</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private static final String C_DAO_FACTORY_SYSTEM = "ods";

  /**
   * Comment for <code>ms_instance</code>: DAO Factory singleton instance
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  protected static O3DAOFactory ms_instance;

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
    ms_instance = ( O3DAOFactory ) BaseDAOFactory.createDAOFactory( C_DAO_FACTORY_SYSTEM );
  }

  /**
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  public O3DAOFactory()
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
  public static final O3DAOFactory getInstance()
  {
    return ms_instance;
  }

}