package com.citibank.ods.modules.client.custaddress.functionality;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.custaddress.functionality.valueobject.BaseCustAddressDetailFncVO;
import com.citibank.ods.modules.client.custaddress.functionality.valueobject.CustAddressDetailFncVO;
import com.citibank.ods.persistence.bg.dao.BaseTbgCustAddressDAO;
import com.citibank.ods.persistence.bg.dao.TbgCustAddressDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author hamilton.matos
 *  
 */

public class CustAddressDetailFnc extends BaseCustAddressDetailFnc implements
    ODSDetailFnc
{

  public void validateDelete( BaseFncVO fncVO_ )
  {
    //
  }

  public void validateInsert( BaseFncVO fncVO_ )
  {
    //
  }

  public void validateUpdate( BaseFncVO fncVO_ )
  {
    //
  }
  
  /**
   * Retorna uma instância do FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new CustAddressDetailFncVO();
  }

  protected BaseTbgCustAddressDAO getDAO()
  {   
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    TbgCustAddressDAO tbgCustAddressDAO = factory.getTbgCustAddressDAO();
    return tbgCustAddressDAO;
  }

  public void update( CustAddressDetailFncVO officerCmplDetailFncVO_ )
  {
    //
  }

  private void validateUpdate( CustAddressDetailFncVO officerCmplDetailFncVO_ )
  {
    //
  }


  public void insert( BaseFncVO fncVO_ )
  {
    //
  }

  public void update( BaseFncVO fncVO_ )
  {
    //
  }

  public void delete( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Carregamentos iniciais - Detail
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    BaseCustAddressDetailFncVO fncVO = ( BaseCustAddressDetailFncVO ) fncVO_;
    loadCustAddress( fncVO );
    loadDomains( fncVO );
  }

  public void loadForInsert( BaseFncVO fncVO_ )
  {
    //
  }

  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    //
  }

  public void loadForDelete( BaseFncVO fncVO_ )
  {
    //
  }
}