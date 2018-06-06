/*
 * Created on Apr 5, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao;

import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdPlayerRoleEntity;

/**
 * @author acacio.domingos
 *  
 */

public interface TplProdPlayerRoleDAO extends BaseTplProdPlayerRoleDAO
{
  public TplProdPlayerRoleEntity insert(
                                        TplProdPlayerRoleEntity tplProdPlayerRoleEntity_ );

  public TplProdPlayerRoleEntity update(
                                        TplProdPlayerRoleEntity tplProdPlayerRoleEntity_ );

  public DataSet list( String plyrCnpjNbr_, String plyrName_,
                      String plyrRoleTypeCode_, String prodCode_,
                      String sysCode_, String sysSegCode_, String productName_ );

  public boolean existsRelationActive( String plyrCnpjNbr_,
                                      String plyrRoleTypeCode_ );

  public ArrayList getRoleTypes( String plyrCnpjNbr_, String plyrRoleTypeCode_ );

  public boolean exists( TplProdPlayerRoleEntity tplProdPlayerRoleEntity_ );

  public void delete( String recStatCode_, String plyrCnpjNbr_ );

  //Lista de produtos na tela de Associação de Player x Produto.
  public ArrayList listProduct( String prodCode_, String prodName_ );

}