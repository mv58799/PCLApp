package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplContactCustEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;

/**
 * @author hamilton.matos
 */

  /*
  * @author Ronaldo Machado G&P Java Team
  * descrição: Criação de novo método para corrigir a maneira em que é gravado o ID na tpl_contact_cust
  * data:04/07/2008
  */

public interface TplContactCustDAO extends BaseTplContactCustDAO
{

  public TplContactCustEntity insert( TplContactCustEntity tplContactCustEntity_ );

  public void update( TplContactCustEntity tplContactCustEntity_ );

  public void delete( TplContactCustEntity tplContactCustEntity_ );

  public void deleteBatch( TplContactCustEntity tplContactCustEntity_ );

  public DataSet list( BigInteger custNbr_, BigInteger ctcNbrSrc_,
                      String fullNameText_ );

  public BaseTplContactCustEntity find(
                                       BaseTplContactCustEntity tplContactCustEntity_ );

  public boolean existsActive( TplContactCustEntity tplContactCustEntity_ );

  /**
   * Método que retorna a sequence do número do contato na tela de inserção de
   * contato.
   *  
   */
  public Integer getNextContactCustNbr();
  
  /**
  * Método que retorna a sequence na inserção de Contact   
  *  
  */
  public Integer getNextValContactCustNbr();
}

