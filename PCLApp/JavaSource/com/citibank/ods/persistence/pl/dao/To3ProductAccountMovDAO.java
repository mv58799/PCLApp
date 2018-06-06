package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.To3ProductAccountMovEntity;

/**
 * Interface de acesso a base dados
 * 
 * @author michele.monteiro,16/05/2007
 *  
 */

public interface To3ProductAccountMovDAO extends BaseTo3ProductAccountDAO
{

  public DataSet list( BigInteger reltnNbr_, BigInteger custNbr_,
                      BigInteger curAcctNbr_, String prodCode_,
                      String curAcctNbrBlank_, String custNbrBlank_,
                      String reltnNbrBlank_, String lastUpdUserId_,
                      String custFullNameText_ );

  public To3ProductAccountMovEntity insert(
                                           To3ProductAccountMovEntity to3ProductAccountMovEntity_ );

  public To3ProductAccountMovEntity update(
                                           To3ProductAccountMovEntity to3ProductAccountMovEntity_ );

  public To3ProductAccountMovEntity delete(
                                           To3ProductAccountMovEntity to3ProductAccountMovEntity_ );

  public boolean exists( To3ProductAccountMovEntity to3ProductAccountMovEntity_ );

}