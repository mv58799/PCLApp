package com.citibank.ods.modules.client.officer.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.modules.client.officer.functionality.valueobject.BaseOfficerDetailFncVO;
import com.citibank.ods.modules.client.officer.functionality.valueobject.OfficerDetailFncVO;
import com.citibank.ods.persistence.bg.dao.BaseTbgOfficerDAO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.pl.dao.TplOfficerTypeDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.client.officer.functionality;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 26, 2007
 *  
 */

public class OfficerDetailFnc extends BaseOfficerDetailFnc implements
    ODSDetailFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#insert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void insert( BaseFncVO fncVO_ )
  {
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
    loadDomains( ( BaseOfficerDetailFncVO ) fncVO_ );
    super.load( ( BaseOfficerDetailFncVO ) fncVO_ );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
    //Casting do fncVO específico
    OfficerDetailFncVO fncVO = ( OfficerDetailFncVO ) fncVO_;
    super.load( fncVO );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {
    //
  }

  public void loadDomains( BaseOfficerDetailFncVO officerFncVO_ )
  {
    loadOffcrCatCodeDomain( officerFncVO_ );
    loadOffcrStatCodeDomain( officerFncVO_ );
    loadOfficerTypeDomain( officerFncVO_ );
  }

  public void loadOffcrCatCodeDomain( BaseOfficerDetailFncVO officerFncVO_ )
  {
    officerFncVO_.setOffcrCatCodeDomain( ODSConstraintDecoder.decodeOffcrCatCode() );
  }

  public void loadOffcrStatCodeDomain( BaseOfficerDetailFncVO officerFncVO_ )
  {
    officerFncVO_.setOffcrStatCodeDomain( ODSConstraintDecoder.decodeOffcrStatCode() );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.classcmplc.functionality.BaseClassCmplcDetailFnc#getDAO()
   */
  protected BaseTbgOfficerDAO getDAO()
  {
    ODSDAOFactory daoFactory = ODSDAOFactory.getInstance();
    TbgOfficerDAO tbgOfficerDAO = daoFactory.getTbgOfficerDAO();
    return tbgOfficerDAO;
  }

  /**
   * Carrega os valores existentes de tipos de officers para exibição em combo
   * boxes
   * 
   * @param officerListFncVO_ - Objeto contendo o estado atual da aplicação
   */
  public void loadOfficerTypeDomain( BaseOfficerDetailFncVO fncVO_ )
  {

    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplOfficerTypeDAO officerTypeDAO = factory.getTplOfficerTypeDAO();
    //Realiza a consulta no DAO
    DataSet result = officerTypeDAO.loadOfficerType();
    //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    fncVO_.setOffcrTypeCodeDomain( result );

  }

}