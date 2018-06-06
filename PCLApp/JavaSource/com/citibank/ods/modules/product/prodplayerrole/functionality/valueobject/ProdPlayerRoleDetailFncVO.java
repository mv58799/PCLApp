package com.citibank.ods.modules.product.prodplayerrole.functionality.valueobject;

import java.util.ArrayList;

import com.citibank.ods.entity.pl.TplPlayerEntity;
import com.citibank.ods.entity.pl.TplProdPlayerRoleEntity;
import com.citibank.ods.entity.pl.TplProductEntity;

/**
 * @author atilio.l.araujo
 *  
 */
public class ProdPlayerRoleDetailFncVO extends BaseProdPlayerRoleDetailFncVO
{
  private TplProdPlayerRoleEntity productToInsertFncVO = null;

  private TplProdPlayerRoleEntity productToDeleteFncVO = null;

  private TplProductEntity tplProductEntity = null;

  private TplPlayerEntity tplPlayerEntity = null;

  


  public ProdPlayerRoleDetailFncVO()
  {
    m_baseTplProdPlayerRoleEntityList = new ArrayList();
    productToInsertFncVO = new TplProdPlayerRoleEntity();
    productToDeleteFncVO = new TplProdPlayerRoleEntity();
    tplPlayerEntity = new TplPlayerEntity();
    tplProductEntity = new TplProductEntity();
  }

  public TplProdPlayerRoleEntity getProductToDeleteFncVO()
  {
    return productToDeleteFncVO;
  }

  public void setProductToDeleteFncVO(
                                      TplProdPlayerRoleEntity productToDeleteFncVO_ )
  {
    productToDeleteFncVO = productToDeleteFncVO_;
  }

  public TplProdPlayerRoleEntity getProductToInsertFncVO()
  {
    return productToInsertFncVO;
  }

  public void setProductToInsertFncVO(
                                      TplProdPlayerRoleEntity productToInsertFncVO_ )
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