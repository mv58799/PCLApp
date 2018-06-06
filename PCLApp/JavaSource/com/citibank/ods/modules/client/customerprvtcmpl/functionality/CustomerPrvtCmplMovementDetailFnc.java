/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.customerprvtcmpl.functionality;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.TbgOfficerEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplHistEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplCustomerPrvtCmplEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplCustomerPrvtCmplHistEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplCustomerPrvtCmplMovEntityVO;
import com.citibank.ods.modules.client.customerprvtcmpl.form.CustomerPrvtCmplMovementDetailForm;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject.BaseCustomerPrvtCmplDetailFncVO;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject.CustomerPrvtCmplMovementDetailFncVO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplHistDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author bruno.zanetti
 *  
 */
public class CustomerPrvtCmplMovementDetailFnc extends
    BaseCustomerPrvtCmplDetailFnc implements ODSMovementDetailFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customer.functionality.CustomerListFnc#getDAO()
   */

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    CustomerPrvtCmplMovementDetailForm customerPrvtCmplMovementDetailForm = ( CustomerPrvtCmplMovementDetailForm ) form_;
    CustomerPrvtCmplMovementDetailFncVO customerPrvtCmplMovementDetailFncVO = ( CustomerPrvtCmplMovementDetailFncVO ) fncVO_;

   	Date lastUpdDate = null;
   	if (customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity()!= null && customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData() != null){
   		lastUpdDate = customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getLastUpdDate();
   	}

    customerPrvtCmplMovementDetailFncVO.setBaseTplCustomerPrvtCmplEntity( customerPrvtCmplMovementDetailFncVO.getTplCustomerPrvtCmplMovEntity() );

    super.updateFncVOFromForm( customerPrvtCmplMovementDetailForm,
                               customerPrvtCmplMovementDetailFncVO );

    BaseTplCustomerPrvtCmplEntity movementEntity = customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity();
    TplCustomerPrvtCmplMovEntityVO movementEntityVO = ( TplCustomerPrvtCmplMovEntityVO ) movementEntity.getData();

    movementEntityVO.setOpernCode( ODSConstraintDecoder.recodeOpern( customerPrvtCmplMovementDetailForm.getOpernCode() ) );
    movementEntityVO.setLastUpdUserId( customerPrvtCmplMovementDetailForm.getLastUpdUserId() );
    movementEntityVO.setLastUpdDate(lastUpdDate);

  }

  protected BaseTplCustomerPrvtCmplDAO getDAO()
  {
    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplCustomerPrvtCmplMovDAO tplCustomerPrvtCmplMovDAO = odsDAOFactory.getTplCustomerPrvtCmplMovDAO();
    return tplCustomerPrvtCmplMovDAO;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new CustomerPrvtCmplMovementDetailFncVO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {
    //    realiza validação
    fncVO_.clearErrors();
    validateUpdate( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      CustomerPrvtCmplMovementDetailFncVO customerPrvtCmplMovementDetailFncVO = ( CustomerPrvtCmplMovementDetailFncVO ) fncVO_;
      //entity atual que será utilizada para alterar registro do movimento.
      TplCustomerPrvtCmplMovEntity customerPrvtCmplMovementEntity = ( TplCustomerPrvtCmplMovEntity ) customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity();

      ODSDAOFactory odsDaoFactory = ODSDAOFactory.getInstance();
      TplCustomerPrvtCmplMovDAO tplCustomerPrvtCmplMovDAO = odsDaoFactory.getTplCustomerPrvtCmplMovDAO();

      customerPrvtCmplMovementEntity.getData().setLastUpdDate( new Date() );
      customerPrvtCmplMovementEntity.getData().setLastUpdUserId(
                                                                 customerPrvtCmplMovementDetailFncVO.getLoggedUser().getUserID() );

      tplCustomerPrvtCmplMovDAO.update( customerPrvtCmplMovementEntity );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#approve(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void approve( BaseFncVO fncVO_ )
  {
    //    realiza validação
    fncVO_.clearErrors();
    validateApprove( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      CustomerPrvtCmplMovementDetailFncVO customerPrvtCmplMovementDetailFncVO = ( CustomerPrvtCmplMovementDetailFncVO ) fncVO_;
      ODSDAOFactory odsDaoFactory = ODSDAOFactory.getInstance();

      // 1 - Cria registro na tabela de histórico:
      // 1.1 - Encontra registro atual na tabela de cadastro com a mesma chave
      // do movimento
      BaseTplCustomerPrvtCmplEntity currentEntity = new TplCustomerPrvtCmplEntity();
      currentEntity.getData().setCustNbr(
                                          customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr() );
      TplCustomerPrvtCmplDAO currentDAO = odsDaoFactory.getTplCustomerPrvtCmplDAO();
      TplCustomerPrvtCmplHistDAO historyDAO = odsDaoFactory.getTplCustomerPrvtCmplHistDAO();
      TplCustomerPrvtCmplMovDAO movementDAO = odsDaoFactory.getTplCustomerPrvtCmplMovDAO();

      currentEntity = currentDAO.find( currentEntity );
      TplCustomerPrvtCmplEntityVO currentEntityVO = null;

      BaseTplCustomerPrvtCmplEntity historyEntity = null;
      TplCustomerPrvtCmplHistEntityVO historyEntityVO = null;

      //verifica se o registro existe na tabela de cadastro para guardar em
      // histórico
      if ( currentEntity != null )
      {
        currentEntityVO = ( TplCustomerPrvtCmplEntityVO ) currentEntity.getData();

        // 1.2 - Cria entidade de histórico a partir da de movimento
        historyEntity = new TplCustomerPrvtCmplHistEntity();
        historyEntityVO = ( TplCustomerPrvtCmplHistEntityVO ) historyEntity.getData();

        historyEntityVO.setClassCmplcCode( currentEntityVO.getClassCmplcCode() );
        historyEntityVO.setCustNbr( currentEntityVO.getCustNbr() );
        historyEntityVO.setEmNbr( currentEntityVO.getEmNbr() );
        historyEntityVO.setGlbRevenSysOffcrNbr( currentEntityVO.getGlbRevenSysOffcrNbr() );
        historyEntityVO.setLastAuthDate( currentEntityVO.getLastAuthDate() );
        historyEntityVO.setLastAuthUserId( currentEntityVO.getLastAuthUserId() );
        historyEntityVO.setLastUpdDate( currentEntityVO.getLastUpdDate() );
        historyEntityVO.setLastUpdUserId( currentEntityVO.getLastUpdUserId() );
        historyEntityVO.setMailRecvInd( currentEntityVO.getMailRecvInd() );
        historyEntityVO.setOffclMailRecvInd( currentEntityVO.getOffclMailRecvInd() );
        historyEntityVO.setOffcrNbr( currentEntityVO.getOffcrNbr() );
        historyEntityVO.setPrvtCustNbr( currentEntityVO.getOffcrNbr() );
        historyEntityVO.setPrvtKeyNbr( currentEntityVO.getPrvtKeyNbr() );
        historyEntityVO.setWealthPotnlCode( currentEntityVO.getWealthPotnlCode() );
        historyEntityVO.setRecStatCode( currentEntityVO.getRecStatCode() );
		historyEntityVO.setPrvtCustTypeCode( currentEntityVO.getPrvtCustTypeCode() );
		historyEntityVO.setCustPrvtStatCode( currentEntityVO.getCustPrvtStatCode() );
		
        // 1.2 - Atualiza REF_DATE
        historyEntityVO.setCustRefDate( new Date() );
        // 1.3 - Insere registro na tabela de histórico
        historyDAO.insert( ( TplCustomerPrvtCmplHistEntity ) historyEntity );
      }
      else
      {
        currentEntity = new TplCustomerPrvtCmplEntity();
        currentEntityVO = ( TplCustomerPrvtCmplEntityVO ) currentEntity.getData();
      }

      // 2 - Atualiza registro na tabela de cadastro:
      BaseTplCustomerPrvtCmplEntity movementEntity = customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity();
      TplCustomerPrvtCmplMovEntityVO movementEntityVO = ( TplCustomerPrvtCmplMovEntityVO ) movementEntity.getData();
      // 2.1 - Copia dados da entidade de movimento para a de cadastro
      // recuperada em 1.1
      currentEntityVO.setClassCmplcCode( movementEntityVO.getClassCmplcCode() );
      currentEntityVO.setCustNbr( movementEntityVO.getCustNbr() );
      currentEntityVO.setEmNbr( movementEntityVO.getEmNbr() );
      currentEntityVO.setGlbRevenSysOffcrNbr( movementEntityVO.getGlbRevenSysOffcrNbr() );
      currentEntityVO.setLastAuthDate( movementEntityVO.getLastAuthDate() );
      currentEntityVO.setLastAuthUserId( movementEntityVO.getLastAuthUserId() );
      currentEntityVO.setLastUpdDate( movementEntityVO.getLastUpdDate() );
      currentEntityVO.setLastUpdUserId( movementEntityVO.getLastUpdUserId() );
      currentEntityVO.setMailRecvInd( movementEntityVO.getMailRecvInd() );
      currentEntityVO.setOffclMailRecvInd( movementEntityVO.getOffclMailRecvInd() );
      currentEntityVO.setOffcrNbr( movementEntityVO.getOffcrNbr() );
      //Alteraçao G&P 29/05/2008 INICIO
      currentEntityVO.setPrvtCustNbr( movementEntityVO.getPrvtCustNbr() );
	  //Alteraçao G&P 29/05/2008 FIM
      currentEntityVO.setPrvtKeyNbr( movementEntityVO.getPrvtKeyNbr() );
      currentEntityVO.setWealthPotnlCode( movementEntityVO.getWealthPotnlCode() );
      currentEntityVO.setPrvtCustTypeCode( movementEntityVO.getPrvtCustTypeCode() );
      currentEntityVO.setCustPrvtStatCode( movementEntityVO.getCustPrvtStatCode() );
      // 2.2 - Atualiza REC_STATUS, LAST_AUTH_DATE e LAST_AUTH_USER_ID
      currentEntityVO.setLastAuthDate( new Date() );
      currentEntityVO.setLastAuthUserId( customerPrvtCmplMovementDetailFncVO.getLoggedUser().getUserID() );
      if ( movementEntityVO.getOpernCode().equals(
                                                   BaseODSEntity.C_OPERN_CODE_DELETE ) )
      {
        currentEntityVO.setRecStatCode( TplCustomerPrvtCmplEntity.C_REC_STAT_CODE_INACTIVE );
      }
      else
      {
        currentEntityVO.setRecStatCode( TplCustomerPrvtCmplEntity.C_REC_STAT_CODE_ACTIVE );
      }

      //Se o registro foi inserido no histórico e, portanto, se encontra no
      // cadastro
      if ( historyEntity != null )
      {
        //2.3.1 - Realiza a atualização do registro na tabela de cadastro
        if ( currentEntityVO.getRecStatCode().equals(
                                                      TplCustomerPrvtCmplEntity.C_REC_STAT_CODE_INACTIVE ) )
        {
          currentDAO.delete( ( TplCustomerPrvtCmplEntity ) currentEntity );
        }
        else
        {
          currentDAO.update( ( TplCustomerPrvtCmplEntity ) currentEntity );
        }
      }
      else
      {
        // 2.3.2 - Realiza a inserção do registro na tabela de cadastro
        currentDAO.insert( ( TplCustomerPrvtCmplEntity ) currentEntity );
      }

      // 3 - Remove movimento
      // 3.1 - Exclui registro da tabela de movimento
      movementDAO.delete( ( TplCustomerPrvtCmplMovEntity ) movementEntity );

    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#reprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void reprove( BaseFncVO fncVO_ )
  {
    //    realiza validação
    validateReprove( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      CustomerPrvtCmplMovementDetailFncVO officerCmplMovementDetailFncVO = ( CustomerPrvtCmplMovementDetailFncVO ) fncVO_;
      ODSDAOFactory odsDaoFactory = ODSDAOFactory.getInstance();

      // 1 - Atualiza registro na tabela de cadastro:
      BaseTplCustomerPrvtCmplEntity movementEntity = officerCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity();
      TplCustomerPrvtCmplMovDAO movementDAO = odsDaoFactory.getTplCustomerPrvtCmplMovDAO();

      // 2 - Remove movimento
      // 2.1 - Exclui registro da tabela de movimento
      movementDAO.delete( ( TplCustomerPrvtCmplMovEntity ) movementEntity );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    BaseCustomerPrvtCmplDetailFncVO baseCustomerPrvtCmplDetailFncVO = ( BaseCustomerPrvtCmplDetailFncVO ) fncVO_;
    load( baseCustomerPrvtCmplDetailFncVO );

    if ( !baseCustomerPrvtCmplDetailFncVO.isFromSearch() )
    {
      loadCustomerPrvtCmpl( ( BaseCustomerPrvtCmplDetailFncVO ) fncVO_ );
    }
    baseCustomerPrvtCmplDetailFncVO.setFromSearch( false );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    load( ( BaseCustomerPrvtCmplDetailFncVO ) fncVO_ );
    loadCustomerPrvtCmpl( ( BaseCustomerPrvtCmplDetailFncVO ) fncVO_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
    loadCustomerPrvtCmpl( ( BaseCustomerPrvtCmplDetailFncVO ) fncVO_ );
    load( ( BaseCustomerPrvtCmplDetailFncVO ) fncVO_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    CustomerPrvtCmplMovementDetailFncVO customerPrvtCmplMovementDetailFncVO = ( CustomerPrvtCmplMovementDetailFncVO ) fncVO_;
    TplCustomerPrvtCmplMovEntityVO tplCustomerPrvtCmplMovEntityVO = ( TplCustomerPrvtCmplMovEntityVO ) customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData();

    //  Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    TplCustomerPrvtCmplDAO customerPrvtCmplDAO = factory.getTplCustomerPrvtCmplDAO();
    TplCustomerPrvtCmplMovDAO customerPrvtCmplMovDAO = factory.getTplCustomerPrvtCmplMovDAO();

    //    testar usuário
    if ( !customerPrvtCmplMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                                  tplCustomerPrvtCmplMovEntityVO.getLastUpdUserId() ) )
    {
      customerPrvtCmplMovementDetailFncVO.addError( BaseODSFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
    }
    else
    {
      // testar preenchimento dos campos
      if ( !customerPrvtCmplMovementDetailFncVO.hasErrors() )
      {
        if ( tplCustomerPrvtCmplMovEntityVO.getCustNbr() == null
             || tplCustomerPrvtCmplMovEntityVO.getCustNbr().longValue() == 0 )
        {
          customerPrvtCmplMovementDetailFncVO.addError(
                                                        BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                                                        C_CUST_NBR );
        }

//        if ( !customerPrvtCmplMovementDetailFncVO.hasErrors() )
//        {
//          if ( tplCustomerPrvtCmplMovEntityVO.getEmNbr() == null
//               || tplCustomerPrvtCmplMovEntityVO.getEmNbr().length() == 0 )
//          {
//            customerPrvtCmplMovementDetailFncVO.addError(
//                                                          BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
//                                                          C_EM_NBR );
//          }

          if ( !customerPrvtCmplMovementDetailFncVO.hasErrors() )
          {

            if ( customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getOffcrNbr() != null
                 && customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getOffcrNbr().intValue() > 0 )
            {
              TbgOfficerEntity tbgOfficerEntity = new TbgOfficerEntity();
              tbgOfficerEntity.getData().setOffcrNbr(
                                                      customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getOffcrNbr() );

              TbgOfficerDAO officerDAO = factory.getTbgOfficerDAO();

              if ( !officerDAO.exists( tbgOfficerEntity ) )
              {
                fncVO_.addError( BaseODSFncVO.C_INVALID_FK, C_UPDATE,
                                 C_OFFCR_NBR );
              }
            }
          }
          if ( !fncVO_.hasErrors() )
          {
			if (customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getEmNbr() != null)
			{
			
            DataSet result = null;
            try
            {
              result = customerPrvtCmplDAO.list(
                                                 customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getEmNbr(),
                                                 null, null, null, null, null,
                                                 null, null, null, null);
            }
            catch ( Exception ex )
            {
              throw new UnexpectedException( C_ERROR );
            }
            if ( result != null && result.size() > 0 )
            {
              if ( verifyEmNbrByCpf( customerPrvtCmplMovementDetailFncVO ) )
              {
                fncVO_.addError( BaseODSFncVO.C_ERROR_EMNBR_DUPLICATED );
              }

            }
           }
          }
        }
      }
    }
 // }

  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                                        com.citibank.ods.Globals.FuncionalityFormatKeys.C_FORMAT_PRESENTATION );

    super.updateFormFromFncVO( form_, fncVO_ );

    // Acertando os tipos
    CustomerPrvtCmplMovementDetailFncVO customerFncVO = ( CustomerPrvtCmplMovementDetailFncVO ) fncVO_;
    CustomerPrvtCmplMovementDetailForm customerForm = ( CustomerPrvtCmplMovementDetailForm ) form_;

    // Atualizando os dados: FncVO -> Form
    TplCustomerPrvtCmplMovEntityVO customerEntityVO = ( TplCustomerPrvtCmplMovEntityVO ) customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData();

    if ( customerEntityVO.getOpernCode() != null )
    {
      customerForm.setOpernCode( ODSConstraintDecoder.decodeOpern( customerEntityVO.getOpernCode() ) );
    }
    if ( customerEntityVO.getLastUpdDate() != null )
    {
      customerForm.setLastUpdDate( dateFormat.format( customerEntityVO.getLastUpdDate() ) );
    }
    if ( customerEntityVO.getLastUpdUserId() != null )
    {
      customerForm.setLastUpdUserId( customerEntityVO.getLastUpdUserId() );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {
    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplCustomerPrvtCmplDAO customerPrvtCmplDAO = factory.getTplCustomerPrvtCmplDAO();

    CustomerPrvtCmplMovementDetailFncVO customerPrvtCmplMovementDetailFncVO = ( CustomerPrvtCmplMovementDetailFncVO ) fncVO_;
    TplCustomerPrvtCmplMovEntityVO tplCustomerPrvtCmplMovEntityVO = ( TplCustomerPrvtCmplMovEntityVO ) customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData();
    DataSet result;

    //  testar usuário
    if ( customerPrvtCmplMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                                 tplCustomerPrvtCmplMovEntityVO.getLastUpdUserId() ) )
    {
      customerPrvtCmplMovementDetailFncVO.addError( BaseODSFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );
    }

    if ( !customerPrvtCmplMovementDetailFncVO.hasErrors() )
    {
      //Validar se os campos únicos estão respeitados na tabela de histórico

      BaseTplCustomerPrvtCmplEntity currentEntity = new TplCustomerPrvtCmplEntity();
      currentEntity.getData().setCustNbr(
                                          customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr() );
      TplCustomerPrvtCmplDAO currentDAO = factory.getTplCustomerPrvtCmplDAO();

      currentEntity = currentDAO.find( currentEntity );

      TplCustomerPrvtCmplMovEntity movementEntity = ( TplCustomerPrvtCmplMovEntity ) customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity();
      TplCustomerPrvtCmplMovEntityVO movementEntityVO = ( TplCustomerPrvtCmplMovEntityVO ) movementEntity.getData();

      if ( movementEntityVO.getOpernCode() != null
           && movementEntityVO.getOpernCode().equals(
                                                      BaseODSEntity.C_OPERN_CODE_INSERT ) )
      {

        if ( !customerPrvtCmplMovementDetailFncVO.hasErrors() )
        {
          //Validar se os campos únicos estão respeitados na tabela de cadastro

         
		  if (customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getEmNbr() != null)
          {
         
            result = null;
            try
            {
             result = customerPrvtCmplDAO.list(
                                               customerPrvtCmplMovementDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getEmNbr(),
                                               null,
                                               null,
                                               null,
                                               null,
                                               null,
                                               null,
                                               null,
                                               BaseODSEntity.C_REC_STAT_CODE_ACTIVE,
                                               null);
            }
            catch ( Exception ex )
            {
              throw new UnexpectedException( C_ERROR );
            }
            if ( result != null && result.size() > 0 )
            {
            
			  if ( verifyEmNbrByCpf( customerPrvtCmplMovementDetailFncVO ) )
			  {
										fncVO_.addError( BaseODSFncVO.C_ERROR_UNIQUE_CONSTRAINT_DUPLICATED,
										                   C_EM_NBR ); 

              }
            }
         }
      }
    }
  }
}
  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateReprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateReprove( BaseFncVO fncVO_ )
  {
    //
  } 
}