/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProdQlfyPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdQlfyPrvtHistEntityVO;

/**
 * @author fernando.salgado
 *  
 */
public class TplProdQlfyPrvtHistEntity extends BaseTplProdQlfyPrvtEntity
{
  public TplProdQlfyPrvtHistEntity()
  {
    m_data = new TplProdQlfyPrvtHistEntityVO();
  }

  public TplProdQlfyPrvtHistEntity(
                                   TplProdQlfyPrvtEntity tplProdQlfyPrvtEntity,
                                   Date prodQlfyRefDate )
  {
    m_data = new TplProdQlfyPrvtHistEntityVO();

    TplProdQlfyPrvtHistEntityVO prvtHistEntityVO = ( TplProdQlfyPrvtHistEntityVO ) m_data;
    TplProdQlfyPrvtEntityVO prvtEntityVO = ( TplProdQlfyPrvtEntityVO ) tplProdQlfyPrvtEntity.getData();

    prvtHistEntityVO.setProdQlfyCode( prvtEntityVO.getProdQlfyCode() );
    prvtHistEntityVO.setProdQlfyRefDate( prodQlfyRefDate );
    prvtHistEntityVO.setProdQlfyText( prvtEntityVO.getProdQlfyText() );
    prvtHistEntityVO.setLastAuthDate( prvtEntityVO.getLastAuthDate() );
    prvtHistEntityVO.setLastAuthUserId( prvtEntityVO.getLastAuthUserId() );
    prvtHistEntityVO.setLastUpdDate( prvtEntityVO.getLastUpdDate() );
    prvtHistEntityVO.setLastUpdUserId( prvtEntityVO.getLastUpdUserId() );
    prvtHistEntityVO.setRecStatCode( prvtEntityVO.getRecStatCode() );
  }

}