package com.citibank.ods.modules.client.relationprvt.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.relationprvt.functionality.valueobject.RelationPrvtListFncVO;
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

public class RelationPrvtListFnc extends BaseRelationPrvtListFnc implements
    ODSListFnc
{

  public static final String C_BOOLEAN_IND_SIM = "S";

  public static final String C_BOOLEAN_IND_NAO = "N";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    RelationPrvtListFncVO relationPrvtListFncVO = ( RelationPrvtListFncVO ) fncVO_;
    if ( !fncVO_.hasErrors() )
    {

      // Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();

      TplRelationPrvtDAO relationPrvtDAO = factory.getTplRelationPrvtDAO();
      DataSet result = relationPrvtDAO.list(
                                             relationPrvtListFncVO.getReltnNbrSrc(),
                                             relationPrvtListFncVO.getCustNbrSrc(),
                                             relationPrvtListFncVO.getCustFullNameTextSrc(),
                                             relationPrvtListFncVO.getOwnerCustNbrInd(),
                                             relationPrvtListFncVO.getAcctNbrSrc(),
                                             relationPrvtListFncVO.getCustCpfCnpjNbrSrc() );

      relationPrvtListFncVO.setResults( result );

      if ( result.size() > 0 )
      {
        relationPrvtListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
      }
      else
      {
        relationPrvtListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
      }

    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    RelationPrvtListFncVO relationPrvtListFncVO = ( RelationPrvtListFncVO ) fncVO_;

    if ( relationPrvtListFncVO.isFromSearch() )
    {
      loadNameText( relationPrvtListFncVO );
      relationPrvtListFncVO.setFromSearch( false );
    }
    else
    {
      relationPrvtListFncVO.setResults( null );
      relationPrvtListFncVO.setAcctNbrSrc( null );
      relationPrvtListFncVO.setReltnNbrSrc( null );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.relationprvt.functionality.BaseRelationPrvtListFnc#getDAO()
   */
  protected BaseTplRelationPrvtDAO getDAO()
  {

    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new RelationPrvtListFncVO();
  }

  public void clearPage( BaseFncVO fncVO_ )
  {
    RelationPrvtListFncVO relationPrvtListFncVO = ( RelationPrvtListFncVO ) fncVO_;

    relationPrvtListFncVO.clearErrors();
    relationPrvtListFncVO.clearMessages();
    relationPrvtListFncVO.setResults( null );
    relationPrvtListFncVO.setAcctNbrSrc( null );
    relationPrvtListFncVO.setReltnNbrSrc( null );
    relationPrvtListFncVO.setCustNbrSrc( null );
    relationPrvtListFncVO.setCustFullNameTextSrc( null );
    relationPrvtListFncVO.setOwnerCustNbrInd( null );
    relationPrvtListFncVO.setCustCpfCnpjNbrSrc( null );
    relationPrvtListFncVO.setSelectedReltnNbr(null);

  }

}