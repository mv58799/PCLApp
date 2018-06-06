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
  * descri��o: Cria��o de novo m�todo para corrigir a maneira em que � gravado o ID na tpl_contact_cust
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
   * M�todo que retorna a sequence do n�mero do contato na tela de inser��o de
   * contato.
   *  
   */
  public Integer getNextContactCustNbr();
  
  /**
  * M�todo que retorna a sequence na inser��o de Contact   
  *  
  */
  public Integer getNextValContactCustNbr();
}

