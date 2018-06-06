package com.citibank.ods.modules.product.prodplayerrole.functionality.valueobject;

import java.util.ArrayList;

import com.citibank.ods.entity.pl.TplPlayerEntity;
import com.citibank.ods.entity.pl.TplProdPlayerRoleMovEntity;
import com.citibank.ods.entity.pl.TplProductEntity;

//import com.citibank.ods.entity.pl.TplProdPlayerRoleEntity;

/**
 * @author atilio.l.araujo
 *  
 */

public class ProdPlayerRoleMovementDetailFncVO extends
    BaseProdPlayerRoleDetailFncVO
{
  
  private TplProdPlayerRoleMovEntity productToInsertFncVO = null;

  private TplProdPlayerRoleMovEntity productToDeleteFncVO = null;

  private TplProductEntity tplProductEntity = null;

  private TplPlayerEntity tplPlayerEntity = null;

  public ProdPlayerRoleMovementDetailFncVO()
  {
    m_baseTplProdPlayerRoleEntityList = new ArrayList();
    productToInsertFncVO = new TplProdPlayerRoleMovEntity();
    productToDeleteFncVO = new TplProdPlayerRoleMovEntity();
    tplPlayerEntity = new TplPlayerEntity();
    tplProductEntity = new TplProductEntity();
  }

  public TplProdPlayerRoleMovEntity getProductToDeleteFncVO()
  {
    return productToDeleteFncVO;
  }

  public void setProductToDeleteFncVO(
                                      TplProdPlayerRoleMovEntity productToDeleteFncVO_ )
  {
    productToDeleteFncVO = productToDeleteFncVO_;
  }

  public TplProdPlayerRoleMovEntity getProductToInsertFncVO()
  {
    return productToInsertFncVO;
  }

  public void setProductToInsertFncVO(
                                      TplProdPlayerRoleMovEntity productToInsertFncVO_ )
  {
    productToInsertFncVO = productToInsertFncVO_;
  }

  public TplPlayerEntity getTplPlayerEntity()
  {
    return tplPlayerEntity;
  }

  public void setTplPlayerEntity( TplPlayerEntity tplPlayerEntity_ )
  {
    tplPlayerEntity = tplPlayerEntity_;
  }

  public TplProductEntity getTplProductEntity()
  {
    return tplProductEntity;
  }

  public void setTplProductEntity( TplProductEntity tplProductEntity_ )
  {
    tplProductEntity = tplProductEntity_;
  }
}