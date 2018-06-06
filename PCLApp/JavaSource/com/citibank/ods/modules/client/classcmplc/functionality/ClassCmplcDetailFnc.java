package com.citibank.ods.modules.client.classcmplc.functionality;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplClassCmplcEntity;
import com.citibank.ods.entity.pl.TplClassCmplcEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplHistEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplClassCmplcEntityVO;
import com.citibank.ods.modules.client.classcmplc.functionality.valueobject.ClassCmplcDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplClassCmplcDAO;
import com.citibank.ods.persistence.pl.dao.TplClassCmplcDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplHistDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.classcmplc.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 13, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class ClassCmplcDetailFnc extends BaseClassCmplcDetailFnc implements
    ODSDetailFnc
{

  private String C_CLASS_CMPLC = "Classificação Compliance";

  private String C_CUSTOMER_PRVT_CMPL = "Dados Complementares de Cliente";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#insert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void insert( BaseFncVO fncVO_ )
  {
    validateInsert( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      ClassCmplcDetailFncVO classCmplcDetailFncVO = ( ClassCmplcDetailFncVO ) fncVO_;
      TplClassCmplcEntity tplClassCmplcEntity = ( TplClassCmplcEntity ) classCmplcDetailFncVO.getBaseTplClassCmplcEntity();
      TplClassCmplcEntityVO tplClassCmplcEntityVO = ( TplClassCmplcEntityVO ) tplClassCmplcEntity.getData();

      tplClassCmplcEntityVO.setRecStatCode( TplClassCmplcEntity.C_REC_STAT_CODE_ACTIVE );
      tplClassCmplcEntityVO.setLastUpdUserId( classCmplcDetailFncVO.getLoggedUser().getUserID() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplClassCmplcDAO classCmplcDAO = factory.getTplClassCmplcDAO();
      classCmplcDAO.insert( ( TplClassCmplcEntity ) classCmplcDetailFncVO.getBaseTplClassCmplcEntity() );

    }
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

    validateDelete( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {

      ClassCmplcDetailFncVO classCmplcDetailFncVO = ( ClassCmplcDetailFncVO ) fncVO_;
      TplClassCmplcEntity tplClassCmplcEntity = ( TplClassCmplcEntity ) classCmplcDetailFncVO.getBaseTplClassCmplcEntity();
      TplClassCmplcEntityVO tplClassCmplcEntityVO = ( TplClassCmplcEntityVO ) tplClassCmplcEntity.getData();

      tplClassCmplcEntityVO.setRecStatCode( TplClassCmplcEntity.C_REC_STAT_CODE_INACTIVE );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplClassCmplcDAO classCmplcDAO = factory.getTplClassCmplcDAO();
      classCmplcDAO.delete( ( TplClassCmplcEntity ) classCmplcDetailFncVO.getBaseTplClassCmplcEntity() );

    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    ClassCmplcDetailFncVO classCmplcDetailFncVO = ( ClassCmplcDetailFncVO ) fncVO_;

    // Validar campos obrigatórios
    TplClassCmplcEntityVO classCmplcEntityVO = ( TplClassCmplcEntityVO ) classCmplcDetailFncVO.getBaseTplClassCmplcEntity().getData();
    if ( classCmplcEntityVO.getClassCmplcCode() == null
         || classCmplcEntityVO.getClassCmplcCode().equals( "" ) )
    {
      fncVO_.addError( ClassCmplcDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_NAME_CODE );
    }
    if ( !fncVO_.hasErrors() )
    {
      if ( classCmplcEntityVO.getClassCmplcText() == null
           || classCmplcEntityVO.getClassCmplcText().equals( "" ) )
      {
        fncVO_.addError( ClassCmplcDetailFncVO.C_ERROR_MANDATORY_FIELD,
                         C_DISPLAY_NAME_TEXT );
      }
      if ( !fncVO_.hasErrors() )
      {
        // Verifica se já existe algum registro com status ativo
        if ( exists( classCmplcDetailFncVO ) )
        {
          fncVO_.addError( ClassCmplcDetailFncVO.C_ERROR_DUPLICATE_PK );
        }
      }
    }
  }

  /*
   * Verifica se existe algum registro com a chave passada, com status ativo ou
   * não
   */
  private boolean exists( ClassCmplcDetailFncVO classCmplcDetailFncVO_ )
  {
    TplClassCmplcDAO tplClassCmplcDAO = ODSDAOFactory.getInstance().getTplClassCmplcDAO();
    return tplClassCmplcDAO.exists( ( TplClassCmplcEntity ) classCmplcDetailFncVO_.getBaseTplClassCmplcEntity() );
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
    ClassCmplcDetailFncVO classCmplcDetailFncVO = ( ClassCmplcDetailFncVO ) fncVO_;

    // Validar se já existe este codigo no cadastro de clientes
    if ( !fncVO_.hasErrors() )
    {
      TplCustomerPrvtCmplEntity customerPrvtCmplDetailEntity = new TplCustomerPrvtCmplEntity();
      customerPrvtCmplDetailEntity.getData().setClassCmplcCode(
                                                                classCmplcDetailFncVO.getBaseTplClassCmplcEntity().getData().getClassCmplcCode() );

      if ( this.existsInCustomer( customerPrvtCmplDetailEntity ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_IS_REFERENCED, C_CLASS_CMPLC,
                         C_CUSTOMER_PRVT_CMPL );
      }

      // Validar se já existe este codigo no movimento de clientes
      if ( !fncVO_.hasErrors() )
      {
        TplCustomerPrvtCmplMovEntity customerPrvtCmplDetailMovEntity = new TplCustomerPrvtCmplMovEntity();
        customerPrvtCmplDetailMovEntity.getData().setClassCmplcCode(
                                                                     classCmplcDetailFncVO.getBaseTplClassCmplcEntity().getData().getClassCmplcCode() );

        if ( this.existsInCustomerMovement( customerPrvtCmplDetailMovEntity ) )
        {
          fncVO_.addError( BaseODSFncVO.C_ERROR_IS_REFERENCED, C_CLASS_CMPLC,
                           C_CUSTOMER_PRVT_CMPL );
        }

        // Validar se já existe este codigo no histórico de clientes
        if ( !fncVO_.hasErrors() )
        {
          TplCustomerPrvtCmplHistEntity customerPrvtCmplDetailHistEntity = new TplCustomerPrvtCmplHistEntity();
          customerPrvtCmplDetailHistEntity.getData().setClassCmplcCode(
                                                                        classCmplcDetailFncVO.getBaseTplClassCmplcEntity().getData().getClassCmplcCode() );

          if ( this.existsInCustomerHistory( customerPrvtCmplDetailHistEntity ) )
          {
            fncVO_.addError( BaseODSFncVO.C_ERROR_IS_REFERENCED, C_CLASS_CMPLC,
                             C_CUSTOMER_PRVT_CMPL );
          }
        }
      }
    }
  }

  public boolean existsInCustomer(
                                  TplCustomerPrvtCmplEntity TplCustomerPrvtCmplEntity_ )
  {
    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplCustomerPrvtCmplDAO customerPrvtCmplDAO = factory.getTplCustomerPrvtCmplDAO();
    return customerPrvtCmplDAO.search( TplCustomerPrvtCmplEntity_ );
  }

  public boolean existsInCustomerMovement(
                                          TplCustomerPrvtCmplMovEntity TplCustomerPrvtCmplMovEntity_ )
  {
    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplCustomerPrvtCmplMovDAO customerPrvtCmplMovDAO = factory.getTplCustomerPrvtCmplMovDAO();
    return customerPrvtCmplMovDAO.search( TplCustomerPrvtCmplMovEntity_ );
  }

  public boolean existsInCustomerHistory(
                                         TplCustomerPrvtCmplHistEntity TplCustomerPrvtCmplHistEntity_ )
  {
    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplCustomerPrvtCmplHistDAO customerPrvtCmplHistDAO = factory.getTplCustomerPrvtCmplHistDAO();
    return customerPrvtCmplHistDAO.search( TplCustomerPrvtCmplHistEntity_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
    BaseTplClassCmplcEntity baseTplClassCmplcEntity = new BaseTplClassCmplcEntity();
    ClassCmplcDetailFncVO classCmplcDetailFncVO = ( ClassCmplcDetailFncVO ) fncVO_;
    classCmplcDetailFncVO.setBaseTplClassCmplcEntity( baseTplClassCmplcEntity );
    load( classCmplcDetailFncVO );
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
    super.load( ( ClassCmplcDetailFncVO ) fncVO_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.classcmplc.functionality.BaseClassCmplcDetailFnc#getDAO()
   */
  protected BaseTplClassCmplcDAO getDAO()
  {
    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplClassCmplcDAO tplClassCmplcDAO = odsDAOFactory.getTplClassCmplcDAO();
    return tplClassCmplcDAO;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ClassCmplcDetailFncVO();
  }

}