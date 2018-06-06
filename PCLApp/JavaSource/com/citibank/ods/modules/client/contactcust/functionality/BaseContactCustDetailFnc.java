package com.citibank.ods.modules.client.contactcust.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplContactCustEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.contactcust.form.BaseContactCustDetailForm;
import com.citibank.ods.modules.client.contactcust.form.ContactCustDetailForm;
import com.citibank.ods.modules.client.contactcust.functionality.valueobject.BaseContactCustDetailFncVO;
import com.citibank.ods.modules.client.contactcust.functionality.valueobject.ContactCustDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplContactCustDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author hamilton.matos
 */
public abstract class BaseContactCustDetailFnc extends BaseFnc
{
  /*
   * Diplay Name - Numero do Cliente no CMS
   */
  protected static final String C_CUST_NBR_DESCRIPTION = "Numero do Cliente no CMS";

  /*
   * Diplay Name - Numero do Contato
   */
  protected static final String C_CTC_NBR_DESCRIPTION = "Numero do Contato";

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {

    BaseContactCustDetailForm contactCustDetailForm = ( ContactCustDetailForm ) form_;

    BaseContactCustDetailFncVO fncVO = ( BaseContactCustDetailFncVO ) fncVO_;

    BigInteger custNbr = ( contactCustDetailForm.getCustNbrSrc() != null
                           && contactCustDetailForm.getCustNbrSrc().length() > 0
                                                                             ? new BigInteger(
                                                                                               contactCustDetailForm.getCustNbrSrc() )
                                                                             : null );

    BigInteger ctcNbr = ( contactCustDetailForm.getCtcNbr() != null
                          && contactCustDetailForm.getCtcNbr().length() > 0
                                                                           ? new BigInteger(
                                                                                             contactCustDetailForm.getCtcNbr() )
                                                                           : null );

    String custFullNameText = ( contactCustDetailForm.getCustFullNameText() != null
                                && contactCustDetailForm.getCustFullNameText().length() > 0
                                                                                           ? contactCustDetailForm.getCustFullNameText()
                                                                                           : null );

    BigInteger phoneDddCode = ( contactCustDetailForm.getPhoneDddCode() != null
                                && contactCustDetailForm.getPhoneDddCode().length() > 0
                                                                                       ? new BigInteger(
                                                                                                         contactCustDetailForm.getPhoneDddCode() )
                                                                                       : null );

    BigInteger phoneExtnNbr = ( contactCustDetailForm.getPhoneExtnNbr() != null
                                && contactCustDetailForm.getPhoneExtnNbr().length() > 0
                                                                                       ? new BigInteger(
                                                                                                         contactCustDetailForm.getPhoneExtnNbr() )
                                                                                       : null );

    BigInteger phoneNbr = ( contactCustDetailForm.getPhoneNbr() != null
                            && contactCustDetailForm.getPhoneNbr().length() > 0
                                                                               ? new BigInteger(
                                                                                                 contactCustDetailForm.getPhoneNbr() )
                                                                               : null );

    String fullNameText = ( contactCustDetailForm.getFullNameText() != null
                            && contactCustDetailForm.getFullNameText().length() > 0
                                                                                   ? contactCustDetailForm.getFullNameText()
                                                                                   : null );

	String fullNameText_2 = ( contactCustDetailForm.getFullNameText_2() != null
							&& contactCustDetailForm.getFullNameText_2().length() > 0
																				   ? contactCustDetailForm.getFullNameText_2()
																				   : null );

	String fullNameText_3 = ( contactCustDetailForm.getFullNameText_3() != null
							&& contactCustDetailForm.getFullNameText_3().length() > 0
																				   ? contactCustDetailForm.getFullNameText_3()
																				   : null );


    fncVO.setCustFullNameText( custFullNameText );
    fncVO.getTplContactCustEntity().getData().setCustNbr( custNbr );
    fncVO.getTplContactCustEntity().getData().setCtcNbr( ctcNbr );
    fncVO.getTplContactCustEntity().getData().setFullNameText( fullNameText );
	fncVO.getTplContactCustEntity().getData().setFullNameText_2( fullNameText_2 );
	fncVO.getTplContactCustEntity().getData().setFullNameText_3( fullNameText_3 );	
    fncVO.getTplContactCustEntity().getData().setPhoneDddCode( phoneDddCode );
    fncVO.getTplContactCustEntity().getData().setPhoneExtnNbr( phoneExtnNbr );
    fncVO.getTplContactCustEntity().getData().setPhoneNbr( phoneNbr );

  }

  /**
   * Atualiza os atributos da Form com os atributos vindos do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {

    BaseContactCustDetailForm form = ( ContactCustDetailForm ) form_;
    BaseContactCustDetailFncVO fncVO = ( ContactCustDetailFncVO ) fncVO_;

    String ctcNbr = ( fncVO.getTplContactCustEntity().getData().getCtcNbr() != null
                                                                                   ? fncVO.getTplContactCustEntity().getData().getCtcNbr().toString()
                                                                                   : null );
    String custNbr = ( fncVO.getTplContactCustEntity().getData().getCustNbr() != null
                                                                                     ? fncVO.getTplContactCustEntity().getData().getCustNbr().toString()
                                                                                     : null );
    String fullNameText = ( fncVO.getTplContactCustEntity().getData().getFullNameText() != null
                                                                                               ? fncVO.getTplContactCustEntity().getData().getFullNameText().toString()
                                                                                               : null );
	String fullNameText_2 = ( fncVO.getTplContactCustEntity().getData().getFullNameText_2() != null
																							   ? fncVO.getTplContactCustEntity().getData().getFullNameText_2().toString()
																							   : null );
	String fullNameText_3 = ( fncVO.getTplContactCustEntity().getData().getFullNameText_3() != null
																							   ? fncVO.getTplContactCustEntity().getData().getFullNameText_3().toString()
																							   : null );
    String lastAuthDate = ( fncVO.getTplContactCustEntity().getData().getLastAuthDate() != null
                                                                                               ? fncVO.getTplContactCustEntity().getData().getLastAuthDate().toString()
                                                                                               : null );
    String lastAuthUserId = ( fncVO.getTplContactCustEntity().getData().getLastAuthUserId() != null
                                                                                                   ? fncVO.getTplContactCustEntity().getData().getLastAuthUserId().toString()
                                                                                                   : null );
    String lastUpdDate = ( fncVO.getTplContactCustEntity().getData().getLastUpdDate() != null
                                                                                             ? fncVO.getTplContactCustEntity().getData().getLastUpdDate().toString()
                                                                                             : null );
    String lastUpdUserId = ( fncVO.getTplContactCustEntity().getData().getLastUpdUserId() != null
                                                                                                 ? fncVO.getTplContactCustEntity().getData().getLastUpdUserId().toString()
                                                                                                 : null );
    String phoneDddCode = ( fncVO.getTplContactCustEntity().getData().getPhoneDddCode() != null
                                                                                               ? fncVO.getTplContactCustEntity().getData().getPhoneDddCode().toString()
                                                                                               : null );
    String phoneExtnNbr = ( fncVO.getTplContactCustEntity().getData().getPhoneExtnNbr() != null
                                                                                               ? fncVO.getTplContactCustEntity().getData().getPhoneExtnNbr().toString()
                                                                                               : null );
    String phoneNbr = ( fncVO.getTplContactCustEntity().getData().getPhoneNbr() != null
                                                                                       ? fncVO.getTplContactCustEntity().getData().getPhoneNbr().toString()
                                                                                       : null );
    String recStatCode = ( fncVO.getTplContactCustEntity().getData().getRecStatCode() != null
                                                                                             ? fncVO.getTplContactCustEntity().getData().getRecStatCode().toString()
                                                                                             : null );

    String custFullNameText = ( fncVO.getCustFullNameText() != null
                                                                   ? fncVO.getCustFullNameText().toString()
                                                                   : null );

    form.setCustFullNameText( custFullNameText );
    form.setCtcNbr( ctcNbr );
    form.setCustNbrSrc( custNbr );
    form.setFullNameText( fullNameText );
	form.setFullNameText_2( fullNameText_2 );
	form.setFullNameText_3( fullNameText_3 );
    form.setLastAuthDate( lastAuthDate );
    form.setLastAuthUserId( lastAuthUserId );
    form.setLastUpdDate( lastUpdDate );
    form.setLastUpdUserId( lastUpdUserId );
    form.setPhoneDddCode( phoneDddCode );
    form.setPhoneExtnNbr( phoneExtnNbr );
    form.setPhoneNbr( phoneNbr );
    form.setRecStatCode( recStatCode );
  }

  /**
   * Recupera um elemento de Contato de Cliente dado os critérios
   */
  protected void loadContactCust(
                                 BaseContactCustDetailFncVO contactCustDetailFncVO_ )
  {
    BaseTplContactCustEntity contactCustEntity;

    BaseTplContactCustDAO contactCustDAO = this.getDAO();
    contactCustEntity = contactCustDAO.find( contactCustDetailFncVO_.getTplContactCustEntity() );
    contactCustDetailFncVO_.setTplContactCustEntity( contactCustEntity );
  }

  /**
   * Recupera o nome completo do cliente
   */
  protected void loadCustFullNameText(
                                      BaseContactCustDetailFncVO contactCustDetailFncVO_ )
  {

    if ( contactCustDetailFncVO_.getTplContactCustEntity().getData().getCustNbr() != null
         && contactCustDetailFncVO_.getTplContactCustEntity().getData().getCustNbr().longValue() > 0 )
    {
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr(
                                               contactCustDetailFncVO_.getTplContactCustEntity().getData().getCustNbr() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

      //Realiza a consulta no DAO
      customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      contactCustDetailFncVO_.setCustFullNameText( customerPrvtEntity.getData().getCustFullNameText() );
    }
  }

  protected abstract BaseTplContactCustDAO getDAO();

}