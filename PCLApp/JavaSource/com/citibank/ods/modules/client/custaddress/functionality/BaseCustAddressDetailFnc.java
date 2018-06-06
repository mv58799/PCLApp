package com.citibank.ods.modules.client.custaddress.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.bg.BaseTbgCustAddressEntity;
import com.citibank.ods.modules.client.custaddress.form.BaseCustAddressDetailForm;
import com.citibank.ods.modules.client.custaddress.functionality.valueobject.BaseCustAddressDetailFncVO;
import com.citibank.ods.persistence.bg.dao.BaseTbgCustAddressDAO;
import com.citibank.ods.persistence.bg.dao.TbgCustCellDAO;
import com.citibank.ods.persistence.bg.dao.TbgCustMailDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author hamilton.matos
 *  
 */
public abstract class BaseCustAddressDetailFnc extends BaseFnc
{

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {

    BaseCustAddressDetailForm custAddressDetailForm = ( BaseCustAddressDetailForm ) form_;

    BaseCustAddressDetailFncVO fncVO = ( BaseCustAddressDetailFncVO ) fncVO_;

    Long custNumber = ( custAddressDetailForm.getCustNbr() != null
                              && custAddressDetailForm.getCustNbr().length() > 0
                                                                                ? new Long(
                                                                                                  custAddressDetailForm.getCustNbr() )
                                                                                : null );

    String seqNumber = ( custAddressDetailForm.getAddrSeqNbr() != null
                                                                      ? new String(
                                                                                    custAddressDetailForm.getAddrSeqNbr() )
                                                                      : null );

    fncVO.getTbgCustAddressEntity().getData().setCustNbr( custNumber );
    fncVO.getTbgCustAddressEntity().getData().setAddrSeqNbr( seqNumber );

  }

  /**
   * Atualiza os atributos da Form com os atributos vindos do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseCustAddressDetailForm form = ( BaseCustAddressDetailForm ) form_;
    BaseCustAddressDetailFncVO fncVO = ( BaseCustAddressDetailFncVO ) fncVO_;

    String addrCityText = ( fncVO.getTbgCustAddressEntity().getData().getAddrCityText() != null
                                                                                               ? fncVO.getTbgCustAddressEntity().getData().getAddrCityText().toString()
                                                                                               : null );
    String addrCmplText = ( fncVO.getTbgCustAddressEntity().getData().getAddrCmplText() != null
                                                                                               ? fncVO.getTbgCustAddressEntity().getData().getAddrCmplText().toString()
                                                                                               : null );
    String addrCntryCode = ( fncVO.getTbgCustAddressEntity().getData().getAddrCntryCode() != null
                                                                                                 ? fncVO.getTbgCustAddressEntity().getData().getAddrCntryCode().toString()
                                                                                                 : null );
    String addrContactName = ( fncVO.getTbgCustAddressEntity().getData().getAddrContactName() != null
                                                                                                     ? fncVO.getTbgCustAddressEntity().getData().getAddrContactName().toString()
                                                                                                     : null );
    String addrNameText = ( fncVO.getTbgCustAddressEntity().getData().getAddrNameText() != null
                                                                                               ? fncVO.getTbgCustAddressEntity().getData().getAddrNameText().toString()
                                                                                               : null );
    String addrNeighbText = ( fncVO.getTbgCustAddressEntity().getData().getAddrNeighbText() != null
                                                                                                   ? fncVO.getTbgCustAddressEntity().getData().getAddrNeighbText().toString()
                                                                                                   : null );
    String addrSeqNbr = ( fncVO.getTbgCustAddressEntity().getData().getAddrSeqNbr() != null
                                                                                           ? fncVO.getTbgCustAddressEntity().getData().getAddrSeqNbr().toString()
                                                                                           : null );
    String addrStateCode = ( fncVO.getTbgCustAddressEntity().getData().getAddrStateCode() != null
                                                                                                 ? fncVO.getTbgCustAddressEntity().getData().getAddrStateCode().toString()
                                                                                                 : null );
    String addrTypeCode = ( fncVO.getTbgCustAddressEntity().getData().getAddrTypeCode() != null
                                                                                               ? fncVO.getTbgCustAddressEntity().getData().getAddrTypeCode().toString()
                                                                                               : null );
    String cellAreaCode = ( fncVO.getTbgCustAddressEntity().getData().getCellAreaCode() != null
                                                                                               ? fncVO.getTbgCustAddressEntity().getData().getCellAreaCode().toString()
                                                                                               : null );
    String cellMailInd = ( fncVO.getTbgCustAddressEntity().getData().getCellMailInd() != null
                                                                                             ? fncVO.getTbgCustAddressEntity().getData().getCellMailInd().toString()
                                                                                             : null );
    String cellOpCode = ( fncVO.getTbgCustAddressEntity().getData().getCellOpCode() != null
                                                                                           ? fncVO.getTbgCustAddressEntity().getData().getCellOpCode().toString()
                                                                                           : null );
    String cellPhoneNbr = ( fncVO.getTbgCustAddressEntity().getData().getCellPhoneNbr() != null
                                                                                               ? fncVO.getTbgCustAddressEntity().getData().getCellPhoneNbr().toString()
                                                                                               : null );
    String emailMailInd = ( fncVO.getTbgCustAddressEntity().getData().getEmailMailInd() != null
                                                                                               ? fncVO.getTbgCustAddressEntity().getData().getEmailMailInd().toString()
                                                                                               : null );
    String emailText = ( fncVO.getTbgCustAddressEntity().getData().getEmailText() != null
                                                                                         ? fncVO.getTbgCustAddressEntity().getData().getEmailText().toString()
                                                                                         : null );
    String faxAreaCode = ( fncVO.getTbgCustAddressEntity().getData().getFaxAreaCode() != null
                                                                                             ? fncVO.getTbgCustAddressEntity().getData().getFaxAreaCode().toString()
                                                                                             : null );
    String faxNbr = ( fncVO.getTbgCustAddressEntity().getData().getFaxNbr() != null
                                                                                   ? fncVO.getTbgCustAddressEntity().getData().getFaxNbr().toString()
                                                                                   : null );
    String mailBoxNbr = ( fncVO.getTbgCustAddressEntity().getData().getMailBoxNbr() != null
                                                                                           ? fncVO.getTbgCustAddressEntity().getData().getMailBoxNbr().toString()
                                                                                           : null );
    String phoneAreaCode = ( fncVO.getTbgCustAddressEntity().getData().getPhoneAreaCode() != null
                                                                                                 ? fncVO.getTbgCustAddressEntity().getData().getPhoneAreaCode().toString()
                                                                                                 : null );
    String phoneExtnNbr = ( fncVO.getTbgCustAddressEntity().getData().getPhoneExtnNbr() != null
                                                                                               ? fncVO.getTbgCustAddressEntity().getData().getPhoneExtnNbr().toString()
                                                                                               : null );
    String phoneNbr = ( fncVO.getTbgCustAddressEntity().getData().getPhoneNbr() != null
                                                                                       ? fncVO.getTbgCustAddressEntity().getData().getPhoneNbr().toString()
                                                                                       : null );
    String phoneOpCode = ( fncVO.getTbgCustAddressEntity().getData().getPhoneOpCode() != null
                                                                                             ? fncVO.getTbgCustAddressEntity().getData().getPhoneOpCode().toString()
                                                                                             : null );
    String telexAreaCode = ( fncVO.getTbgCustAddressEntity().getData().getTelexAreaCode() != null
                                                                                                 ? fncVO.getTbgCustAddressEntity().getData().getTelexAreaCode().toString()
                                                                                                 : null );
    String telexNbr = ( fncVO.getTbgCustAddressEntity().getData().getTelexNbr() != null
                                                                                       ? fncVO.getTbgCustAddressEntity().getData().getTelexNbr().toString()
                                                                                       : null );
    String zipCode = ( fncVO.getTbgCustAddressEntity().getData().getZipCode() != null
                                                                                     ? fncVO.getTbgCustAddressEntity().getData().getZipCode().toString()
                                                                                     : null );
    String zipCodeChangeText = ( fncVO.getTbgCustAddressEntity().getData().getZipCodeChangeText() != null
                                                                                                         ? fncVO.getTbgCustAddressEntity().getData().getZipCodeChangeText().toString()
                                                                                                         : null );
    String zipExtnCode = ( fncVO.getTbgCustAddressEntity().getData().getZipExtnCode() != null
                                                                                             ? fncVO.getTbgCustAddressEntity().getData().getZipExtnCode().toString()
                                                                                             : null );
    String custNbr = ( fncVO.getTbgCustAddressEntity().getData().getCustNbr() != null
                                                                                     ? fncVO.getTbgCustAddressEntity().getData().getCustNbr().toString()
                                                                                     : null );
    
    DataSet cellMailIndDomain = fncVO.getCellMailIndDomain();
    
    DataSet custCellResults = fncVO.getCustCellDomain();
    DataSet custMailResults = fncVO.getCustMailDomain();
    

    DataSet addrTypeCodeDomain = ( fncVO.getAddrTypeCodeDomain() );    
    DataSet emailMailIndDomain = ( fncVO.getEmailMailIndDomain() );

    form.setAddrCityText( addrCityText );
    form.setAddrCmplText( addrCmplText );
    form.setAddrCntryCode( addrCntryCode );
    form.setAddrContactName( addrContactName );
    form.setAddrNameText( addrNameText );
    form.setAddrNeighbText( addrNeighbText );
    form.setAddrSeqNbr( addrSeqNbr );
    form.setAddrStateCode( addrStateCode );
    form.setAddrTypeCode( addrTypeCode );
    form.setCellAreaCode( cellAreaCode );
    form.setCellMailInd( cellMailInd );
    form.setCellOpCode( cellOpCode );
    form.setCellPhoneNbr( cellPhoneNbr );
    form.setEmailMailInd( emailMailInd );
    form.setEmailText( emailText );
    form.setFaxAreaCode( faxAreaCode );
    form.setFaxNbr( faxNbr );
    form.setMailBoxNbr( mailBoxNbr );
    form.setPhoneAreaCode( phoneAreaCode );
    form.setPhoneExtnNbr( phoneExtnNbr );
    form.setPhoneNbr( phoneNbr );
    form.setPhoneOpCode( phoneOpCode );
    form.setTelexAreaCode( telexAreaCode );
    form.setTelexNbr( telexNbr );
    form.setZipCode( zipCode );
    form.setZipCodeChangeText( zipCodeChangeText );
    form.setZipExtnCode( zipExtnCode );
    form.setCustNbr( custNbr );
    form.setAddrTypeCodeDomain( addrTypeCodeDomain );
    form.setCustCellResults( custCellResults );
    form.setCustMailResults( custMailResults );    
    form.setEmailMailIndDomain( emailMailIndDomain );
    form.setCellMailIndDomain( cellMailIndDomain );

  }

  /**
   * Recupera um elementos de Endereço de cliente, dado os critérios
   */
  protected void loadCustAddress(
                                 BaseCustAddressDetailFncVO custAddressDetailFncVO_ )
  {
    BaseTbgCustAddressEntity custAddressEntity;

    BaseTbgCustAddressDAO custAddressDAO = this.getDAO();
    custAddressEntity = custAddressDAO.find( custAddressDetailFncVO_.getTbgCustAddressEntity() );
    custAddressDetailFncVO_.setTbgCustAddressEntity( custAddressEntity );
  }

  /**
   * Recupera os dados referentes ao tipo de endereço, indicador se cliente
   * receberá mala direta no celular e indicador se cliente receberá mala direta
   * no e-mail
   */
  protected void loadDomains( BaseCustAddressDetailFncVO custAddressDetailFncVO_ )
  {
    loadAddrTypeCodeDomain( custAddressDetailFncVO_ );
    loadCustCellDomain( custAddressDetailFncVO_ );
    loadCustMailDomain( custAddressDetailFncVO_ );
    loadCellMailIndDomain( custAddressDetailFncVO_ );
  }
  
  private void loadCellMailIndDomain(
                                     BaseCustAddressDetailFncVO custAddressDetailFncVO_ )
  {
    custAddressDetailFncVO_.setCellMailIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  protected abstract BaseTbgCustAddressDAO getDAO();

  private void loadCustMailDomain(
                                      BaseCustAddressDetailFncVO custAddressDetailFncVO_ )
  { 
    // 	Obtém a instância da Factory
  
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    
    TbgCustMailDAO tplCustMailDAO = factory.getTbgCustMailDAO();
    DataSet results = tplCustMailDAO.list(custAddressDetailFncVO_.getTbgCustAddressEntity().getData().getCustNbr());
    
    custAddressDetailFncVO_.setCustMailDomain(results);
    
  }

  private void loadCustCellDomain(
                                     BaseCustAddressDetailFncVO custAddressDetailFncVO_ )
  {
    // 	Obtém a instância da Factory
    
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    TbgCustCellDAO tplCustCellDAO = factory.getTbgCustCellDAO();
    DataSet results = tplCustCellDAO.list( custAddressDetailFncVO_.getTbgCustAddressEntity().getData().getCustNbr());
    
    custAddressDetailFncVO_.setCustCellDomain(results);
    
  }

  private void loadAddrTypeCodeDomain(
                                      BaseCustAddressDetailFncVO custAddressDetailFncVO_ )
  {
    custAddressDetailFncVO_.setAddrTypeCodeDomain( ODSConstraintDecoder.decodeAddrType() );
  }

}