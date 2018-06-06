package com.citibank.ods.modules.client.erem.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.erem.form.BaseEREMListForm;
import com.citibank.ods.modules.client.erem.functionality.valueobject.BaseEREMListFncVO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.erem.functionality;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 9, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseEREMListFnc extends BaseFnc
{

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    //  Faz cast para os tipos corretos
    BaseEREMListFncVO baseErEmListFncVO = ( BaseEREMListFncVO ) fncVO_;
    BaseEREMListForm baseErEmListForm = ( BaseEREMListForm ) form_;

    // Atualiza os dados: Form -> FncVO
    BigInteger custNbrSrc = ( baseErEmListForm.getCustNbrSrc() != null
                              && baseErEmListForm.getCustNbrSrc().length() > 0
                                                                              ? new BigInteger(
                                                                                                baseErEmListForm.getCustNbrSrc() )
                                                                              : null );
    String erNbrSrc = ( baseErEmListForm.getErNbrSrc() != null
                        && baseErEmListForm.getErNbrSrc().length() > 0
                                                                      ? baseErEmListForm.getErNbrSrc()
                                                                      : null );

    String emNbrSrc = ( baseErEmListForm.getEmNbrSrc() != null
                        && baseErEmListForm.getEmNbrSrc().length() > 0
                                                                      ? baseErEmListForm.getEmNbrSrc()
                                                                      : null );

    DataSet results = ( baseErEmListForm.getResults() != null
                                                             ? baseErEmListForm.getResults()
                                                             : null );

    String selectedEmNbr = ( baseErEmListForm.getSelectedEMNbr() != null
                             && baseErEmListForm.getSelectedEMNbr().length() > 0
                                                                                ? baseErEmListForm.getSelectedEMNbr()
                                                                                : null );

    String selectedErNbr = ( baseErEmListForm.getSelectedERNbr() != null
                             && baseErEmListForm.getSelectedERNbr().length() > 0
                                                                                ? baseErEmListForm.getSelectedERNbr()
                                                                                : null );
    String custFullNameTextSrc = ( baseErEmListForm.getCustFullNameTextSrc() != null
                                   && !baseErEmListForm.getCustFullNameTextSrc().equals(
                                                                                         "" )
                                                                                             ? baseErEmListForm.getCustFullNameTextSrc()
                                                                                             : "" );

    String curAcctNbrSrc = baseErEmListForm.getCurAcctNbrSrc();
    if ( curAcctNbrSrc != null && !curAcctNbrSrc.equals( "" ) )
    {
      baseErEmListFncVO.setAcctNbr( new BigInteger( curAcctNbrSrc ) );
    }
    else
    {
      baseErEmListFncVO.setAcctNbr( null );
    }

    String reltnNbrSrc = baseErEmListForm.getReltnNbrSrc();
    if ( reltnNbrSrc != null && !reltnNbrSrc.equals( "" ) )
    {
      baseErEmListFncVO.setReltnNbrSrc( new BigInteger( reltnNbrSrc ) );
    }
    else
    {
      baseErEmListFncVO.setReltnNbrSrc( null );
    }

    baseErEmListFncVO.setCustNbrSrc( custNbrSrc );
    baseErEmListFncVO.setErNbrSrc( erNbrSrc );
    baseErEmListFncVO.setEmNbrSrc( emNbrSrc );
    baseErEmListFncVO.setResults( results );
    baseErEmListFncVO.setSelectedEmNbr( selectedEmNbr );
    baseErEmListFncVO.setSelectedErNbr( selectedErNbr );
    baseErEmListFncVO.setCustFullNameTextSrc( custFullNameTextSrc );

    baseErEmListFncVO.setClickedSearch( "" );

  } /*
     * (non-Javadoc)
     * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
     *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
     */

  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseEREMListFncVO erEmListFncVO = ( BaseEREMListFncVO ) fncVO_;
    BaseEREMListForm erEmListForm = ( BaseEREMListForm ) form_;

    String reltnNbr = erEmListFncVO.getReltnNbrSrc() != null
                                                            ? erEmListFncVO.getReltnNbrSrc().toString()
                                                            : "";
    String custNbr = erEmListFncVO.getCustNbrSrc() != null
                                                          ? erEmListFncVO.getCustNbrSrc().toString()
                                                          : "";
    String acctNbr = erEmListFncVO.getAcctNbr() != null
                                                       ? erEmListFncVO.getAcctNbr().toString()
                                                       : "";
    String custFullName = ( erEmListFncVO.getCustFullNameTextSrc() != null
                            && !erEmListFncVO.getCustFullNameTextSrc().equals(
                                                                               "" )
                                                                                   ? erEmListFncVO.getCustFullNameTextSrc()
                                                                                   : "" );

    erEmListForm.setErNbrSrc( erEmListFncVO.getErNbrSrc() );
    erEmListForm.setEmNbrSrc( erEmListFncVO.getEmNbrSrc() );
    erEmListForm.setReltnNbrSrc( reltnNbr );
    erEmListForm.setCustNbrSrc( custNbr );
    erEmListForm.setCustFullNameTextSrc( custFullName );
    erEmListForm.setCurAcctNbrSrc( acctNbr );
    erEmListForm.setResults( erEmListFncVO.getResults() );
    erEmListForm.setClickedSearch( erEmListFncVO.getClickedSearch() );
  }

  public void load( BaseEREMListFncVO erEmFncVO_ )
  {
    loadCustText( erEmFncVO_ );
  }

  public void loadCustText( BaseEREMListFncVO erEmFncVO_ )
  {
    if ( erEmFncVO_.getCustNbrSrc() != null
         && erEmFncVO_.getCustNbrSrc().longValue() > 0 )
    {
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr( erEmFncVO_.getCustNbrSrc() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

      //Realiza a consulta no DAO
      customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      erEmFncVO_.setCustFullNameTextSrc( customerPrvtEntity.getData().getCustFullNameText() );
    }
  }

  public void loadEmNbr( BaseEREMListFncVO erEmFncVO_ )
  {
    if ( erEmFncVO_.getCustNbrSrc() != null
         && erEmFncVO_.getCustNbrSrc().longValue() > 0 )
    {
      TplCustomerPrvtCmplEntity customerPrvtCmplEntity = new TplCustomerPrvtCmplEntity();
      customerPrvtCmplEntity.getData().setCustNbr( erEmFncVO_.getCustNbrSrc() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtCmplDAO tplCustomerPrvtCmplDAO = factory.getTplCustomerPrvtCmplDAO();

      if ( tplCustomerPrvtCmplDAO.existsActive( customerPrvtCmplEntity ) )
      {
        //Realiza a consulta no DAO
        customerPrvtCmplEntity = ( TplCustomerPrvtCmplEntity ) tplCustomerPrvtCmplDAO.find( customerPrvtCmplEntity );

        //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
        erEmFncVO_.setEmNbrSrc( customerPrvtCmplEntity.getData().getEmNbr() );
      }
      else
      {
        erEmFncVO_.setEmNbrSrc( null );
      }

    }
  }

}