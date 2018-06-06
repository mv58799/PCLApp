package com.citibank.ods.modules.client.relationprvt.functionality;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.modules.client.relationprvt.functionality.valueobject.BaseRelationPrvtDetailFncVO;
import com.citibank.ods.modules.client.relationprvt.functionality.valueobject.RelationPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplRelationPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplRelationPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.relationprvt.functionality;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 29, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class RelationPrvtDetailFnc extends BaseRelationPrvtDetailFnc implements
    ODSDetailFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#insert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void insert( BaseFncVO fncVO_ )
  {
    //    
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#delete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void delete( BaseFncVO fncVO_ )
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    loadDomains( ( BaseRelationPrvtDetailFncVO ) fncVO_ );
    super.load( ( BaseRelationPrvtDetailFncVO ) fncVO_ );
    loadNameText( ( BaseRelationPrvtDetailFncVO ) fncVO_ );
    loadPorfolio( ( BaseRelationPrvtDetailFncVO ) fncVO_ );
    loadOfficer( ( BaseRelationPrvtDetailFncVO ) fncVO_ );
    loadReltnSpcfClassServPackIndDomain( ( BaseRelationPrvtDetailFncVO ) fncVO_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {
    //
  }

  public void loadDomains( BaseRelationPrvtDetailFncVO relationFncVO_ )
  {
    loadReltnPrmtCodeDomain( relationFncVO_ );
  }

  public void loadReltnPrmtCodeDomain(
                                      BaseRelationPrvtDetailFncVO relationFncVO_ )
  {
    relationFncVO_.setReltnPrmtCodeDomain( ODSConstraintDecoder.decodeReltnPrmtCode() );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.classcmplc.functionality.BaseClassCmplcDetailFnc#getDAO()
   */
  protected BaseTplRelationPrvtDAO getDAO()
  {
    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplRelationPrvtDAO tplRelationPrvtDAO = odsDAOFactory.getTplRelationPrvtDAO();
    return tplRelationPrvtDAO;
  }

  /**
   * Retorna uma instancia do FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new RelationPrvtDetailFncVO();
  }
}