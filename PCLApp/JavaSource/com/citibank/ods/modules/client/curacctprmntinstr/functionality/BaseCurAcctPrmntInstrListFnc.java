package com.citibank.ods.modules.client.curacctprmntinstr.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.curacctprmntinstr.form.BaseCurAcctPrmntInstrListForm;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject.BaseCurAcctPrmntInstrListFncVO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.client.curacctprmntinstr.functionality;
 * @version 1.0
 * @author michele.monteiro,18/06/2007
 *  
 */

public class BaseCurAcctPrmntInstrListFnc extends BaseFnc implements ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new BaseCurAcctPrmntInstrListFncVO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseCurAcctPrmntInstrListFncVO baseCurAcctPrmntInstrListFncVO = ( BaseCurAcctPrmntInstrListFncVO ) fncVO_;
    BaseCurAcctPrmntInstrListForm baseCurAcctPrmntInstrListForm = ( BaseCurAcctPrmntInstrListForm ) form_;

    BigInteger curAcctNbr = ( baseCurAcctPrmntInstrListForm.getCurAcctNbrSrc() != null
                              && baseCurAcctPrmntInstrListForm.getCurAcctNbrSrc().length() > 0
                                                                                              ? new BigInteger(
                                                                                                                baseCurAcctPrmntInstrListForm.getCurAcctNbrSrc() )
                                                                                              : null );

    BigInteger custNbr = ( baseCurAcctPrmntInstrListForm.getCustNbrSrc() != null
                           && baseCurAcctPrmntInstrListForm.getCustNbrSrc().length() > 0
                                                                                        ? new BigInteger(
                                                                                                          baseCurAcctPrmntInstrListForm.getCustNbrSrc() )
                                                                                        : null );

    BigInteger prmntInstrCode = ( baseCurAcctPrmntInstrListForm.getPrmntInstrCodeSrc() != null
                                  && baseCurAcctPrmntInstrListForm.getPrmntInstrCodeSrc().length() > 0
                                                                                                      ? new BigInteger(
                                                                                                                        baseCurAcctPrmntInstrListForm.getPrmntInstrCodeSrc() )
                                                                                                      : null );
    String custFullName = ( baseCurAcctPrmntInstrListForm.getCustFullNameSrc() != null
                            && !baseCurAcctPrmntInstrListForm.getCustFullNameSrc().equals(
                                                                                           "" )
                                                                                               ? baseCurAcctPrmntInstrListForm.getCustFullNameSrc()
                                                                                               : "" );

    baseCurAcctPrmntInstrListFncVO.setCurAcctNbrSrc( curAcctNbr );
    baseCurAcctPrmntInstrListFncVO.setCustNbrSrc( custNbr );
    baseCurAcctPrmntInstrListFncVO.setPrmntInstrCodeSrc( prmntInstrCode );
    baseCurAcctPrmntInstrListFncVO.setCustFullNameSrc( custFullName );
    baseCurAcctPrmntInstrListFncVO.setPrmntInstrInvstCurAcctIndSrc( baseCurAcctPrmntInstrListForm.getPrmntInstrInvstCurAcctIndSrc() );
    baseCurAcctPrmntInstrListFncVO.setClickedSearch( "" );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseCurAcctPrmntInstrListFncVO baseCurAcctPrmntInstrListFncVO = ( BaseCurAcctPrmntInstrListFncVO ) fncVO_;
    BaseCurAcctPrmntInstrListForm baseCurAcctPrmntInstrListForm = ( BaseCurAcctPrmntInstrListForm ) form_;

    String curAcctNbr = ( baseCurAcctPrmntInstrListFncVO.getCurAcctNbrSrc() != null
                                                                                   ? baseCurAcctPrmntInstrListFncVO.getCurAcctNbrSrc().toString()
                                                                                   : null );

    String custNbr = ( baseCurAcctPrmntInstrListFncVO.getCustNbrSrc() != null
                                                                             ? baseCurAcctPrmntInstrListFncVO.getCustNbrSrc().toString()
                                                                             : null );

    String prmntInstrCode = ( baseCurAcctPrmntInstrListFncVO.getPrmntInstrCodeSrc() != null
                                                                                           ? baseCurAcctPrmntInstrListFncVO.getPrmntInstrCodeSrc().toString()
                                                                                           : null );
    String custFullName = ( baseCurAcctPrmntInstrListFncVO.getCustFullNameSrc() != null
                            && !baseCurAcctPrmntInstrListFncVO.getCustFullNameSrc().equals(
                                                                                            "" )
                                                                                                ? baseCurAcctPrmntInstrListFncVO.getCustFullNameSrc()
                                                                                                : "" );

    baseCurAcctPrmntInstrListForm.setCurAcctNbrSrc( curAcctNbr );
    baseCurAcctPrmntInstrListForm.setCustNbrSrc( custNbr );
    baseCurAcctPrmntInstrListForm.setPrmntInstrCodeSrc( prmntInstrCode );
    baseCurAcctPrmntInstrListForm.setCustFullNameSrc( custFullName );
    baseCurAcctPrmntInstrListForm.setPrmntInstrInvstCurAcctIndSrc( baseCurAcctPrmntInstrListFncVO.getPrmntInstrInvstCurAcctIndSrc() );
    baseCurAcctPrmntInstrListForm.setResults( baseCurAcctPrmntInstrListFncVO.getResults() );
    baseCurAcctPrmntInstrListForm.setClickedSearch( baseCurAcctPrmntInstrListFncVO.getClickedSearch() );
    baseCurAcctPrmntInstrListForm.setPrmntInstrInvstCurAcctIndDomain( baseCurAcctPrmntInstrListFncVO.getPrmntInstrInvstCurAcctIndDomain() );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {

  }

  public void loadCustomerName(
                               BaseCurAcctPrmntInstrListFncVO baseCurAcctPrmntInstrListFncVO_ )
  {
    if ( baseCurAcctPrmntInstrListFncVO_.getCustNbrSrc() != null
         && baseCurAcctPrmntInstrListFncVO_.getCustNbrSrc().longValue() > 0 )
    {
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr(
                                               baseCurAcctPrmntInstrListFncVO_.getCustNbrSrc() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

      //Realiza a consulta no DAO
      customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      baseCurAcctPrmntInstrListFncVO_.setCustFullNameSrc( customerPrvtEntity.getData().getCustFullNameText() );
    }
    else
    {
      baseCurAcctPrmntInstrListFncVO_.setCustFullNameSrc( "" );
    }
  }

  protected void loadDomains( BaseFncVO fncVO_ )
  {
    BaseCurAcctPrmntInstrListFncVO fncVO = ( BaseCurAcctPrmntInstrListFncVO ) fncVO_;
    fncVO.setPrmntInstrInvstCurAcctIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

}