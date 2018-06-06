package com.citibank.ods.modules.client.relationprvt.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.relationprvt.form.BaseRelationPrvtListForm;
import com.citibank.ods.modules.client.relationprvt.functionality.valueobject.BaseRelationPrvtListFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplRelationPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
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

public abstract class BaseRelationPrvtListFnc extends BaseFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseRelationPrvtListFncVO relationFncVO = ( BaseRelationPrvtListFncVO ) fncVO_;
    BaseRelationPrvtListForm relationForm = ( BaseRelationPrvtListForm ) form_;

    // Atualizando os dados: Form -> FncVO

    BigInteger custCpfCnpjNbrSrc = relationForm.getCustCpfCnpjNbrSrc() != null
                                   && relationForm.getCustCpfCnpjNbrSrc().length() > 0
                                                                                      ? new BigInteger(
                                                                                                        relationForm.getCustCpfCnpjNbrSrc() )
                                                                                      : null;
    String custFullNameTextSrc = relationForm.getCustFullNameTextSrc() != null
                                 && relationForm.getCustFullNameTextSrc().length() > 0
                                                                                      ? relationForm.getCustFullNameTextSrc()
                                                                                      : null;
    BigInteger custNbrSrc = relationForm.getCustNbrSrc() != null
                            && relationForm.getCustNbrSrc().length() > 0
                                                                        ? new BigInteger(
                                                                                          relationForm.getCustNbrSrc() )
                                                                        : null;
    BigInteger reltnNbrSrc = relationForm.getReltnNbrSrc() != null
                             && relationForm.getReltnNbrSrc().length() > 0
                                                                          ? new BigInteger(
                                                                                            relationForm.getReltnNbrSrc() )
                                                                          : null;
    DataSet results = relationForm.getResults() != null
                                                       ? relationForm.getResults()
                                                       : null;
    String selectedReltnNbr = relationForm.getSelectedReltnNbr() != null
                              && relationForm.getSelectedReltnNbr().length() > 0
                                                                                ? relationForm.getSelectedReltnNbr()
                                                                                : null;
    BigInteger acctNbr = relationForm.getCurAcctNbrSrc() != null
                         && relationForm.getCurAcctNbrSrc().length() > 0
                                                                        ? new BigInteger(
                                                                                          relationForm.getCurAcctNbrSrc() )
                                                                        : null;
    String ownerSelected;

    if ( relationForm.getOwnerSelectedSrc().equals( "S" ) )
    {
      ownerSelected = com.citibank.ods.Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_SIM;

    }
    else
    {
      ownerSelected = com.citibank.ods.Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO;
    }

    relationFncVO.setCustCpfCnpjNbrSrc( custCpfCnpjNbrSrc );
    relationFncVO.setCustFullNameTextSrc( custFullNameTextSrc );
    relationFncVO.setCustNbrSrc( custNbrSrc );
    relationFncVO.setReltnNbrSrc( reltnNbrSrc );
    relationFncVO.setResults( results );
    relationFncVO.setSelectedReltnNbr( selectedReltnNbr );
    relationFncVO.setOwnerCustNbrInd( ownerSelected );
    relationFncVO.setAcctNbrSrc( acctNbr );

    relationFncVO.setClickedSearch( "" );
    //loadNameText( relationFncVO );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {

    BaseRelationPrvtListForm baseRelationPrvtListForm = ( BaseRelationPrvtListForm ) form_;
    BaseRelationPrvtListFncVO baseRelationPrvtListFncVO = ( BaseRelationPrvtListFncVO ) fncVO_;

    String custCpfCnpjNbrSrc = baseRelationPrvtListFncVO.getCustCpfCnpjNbrSrc() != null
                                                                                       ? baseRelationPrvtListFncVO.getCustCpfCnpjNbrSrc().toString()
                                                                                       : "";
    String custFullNameTextSrc = baseRelationPrvtListFncVO.getCustFullNameTextSrc() != null
                                                                                           ? baseRelationPrvtListFncVO.getCustFullNameTextSrc()
                                                                                           : "";
    String custNbrSrc = baseRelationPrvtListFncVO.getCustNbrSrc() != null
                                                                         ? baseRelationPrvtListFncVO.getCustNbrSrc().toString()
                                                                         : "";
    String reltnNbrSrc = baseRelationPrvtListFncVO.getReltnNbrSrc() != null
                                                                           ? baseRelationPrvtListFncVO.getReltnNbrSrc().toString()
                                                                           : "";
    DataSet results = baseRelationPrvtListFncVO.getResults() != null
                                                                    ? baseRelationPrvtListFncVO.getResults()
                                                                    : null;
    String selectedReltnNbr = baseRelationPrvtListFncVO.getSelectedReltnNbr() != null
                              && !baseRelationPrvtListFncVO.getSelectedReltnNbr().equals(
                                                                                          "" )
                                                                                              ? baseRelationPrvtListFncVO.getSelectedReltnNbr()
                                                                                              : "";
    String curAcctNbr = baseRelationPrvtListFncVO.getAcctNbrSrc() != null
                                                                         ? baseRelationPrvtListFncVO.getAcctNbrSrc().toString()
                                                                         : "";
    String ownerSelectedSrc = baseRelationPrvtListFncVO.getOwnerCustNbrInd() != null
                              && !baseRelationPrvtListFncVO.getOwnerCustNbrInd().equals(
                                                                                         "" )
                                                                                             ? baseRelationPrvtListFncVO.getOwnerCustNbrInd()
                                                                                             : "";

    baseRelationPrvtListForm.setCustCpfCnpjNbrSrc( custCpfCnpjNbrSrc );
    baseRelationPrvtListForm.setCustFullNameTextSrc( custFullNameTextSrc );
    baseRelationPrvtListForm.setCustNbrSrc( custNbrSrc );
    baseRelationPrvtListForm.setReltnNbrSrc( reltnNbrSrc );
    baseRelationPrvtListForm.setResults( results );
    baseRelationPrvtListForm.setSelectedReltnNbr( selectedReltnNbr );
    baseRelationPrvtListForm.setCurAcctNbrSrc( curAcctNbr );
    baseRelationPrvtListForm.setOwnerSelectedSrc( ownerSelectedSrc );

    baseRelationPrvtListForm.setClickedSearch( baseRelationPrvtListFncVO.getClickedSearch() );

  }

  public void loadNameText( BaseRelationPrvtListFncVO fncVO )
  {

    if ( fncVO.getCustNbrSrc() != null )
    {
      TplCustomerPrvtEntity entity = new TplCustomerPrvtEntity();
      entity.getData().setCustNbr( fncVO.getCustNbrSrc() );
      ODSDAOFactory factory = ODSDAOFactory.getInstance();

      TplCustomerPrvtDAO prvtDAO = factory.getTplCustomerPrvtDAO();

      entity = ( TplCustomerPrvtEntity ) prvtDAO.find( entity );
      fncVO.setCustFullNameTextSrc( entity.getData().getCustFullNameText() );

    }

  }

  protected abstract BaseTplRelationPrvtDAO getDAO();

}