/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProdRiskCatPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdRiskCatPrvtHistEntityVO;

/**
 * @author fernando.salgado
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TplProdRiskCatPrvtHistEntity extends BaseTplProdRiskCatPrvtEntity
{
  public TplProdRiskCatPrvtHistEntity(){
    m_data = new TplProdRiskCatPrvtHistEntityVO();
  }
  
  public TplProdRiskCatPrvtHistEntity(TplProdRiskCatPrvtEntity tplProdRiskCatPrvtEntity, Date prodRiskCatRefDate){
    m_data = new TplProdRiskCatPrvtHistEntityVO();
    
    TplProdRiskCatPrvtHistEntityVO prvtHistEntityVO = (TplProdRiskCatPrvtHistEntityVO) m_data;
    TplProdRiskCatPrvtEntityVO prvtEntityVO = (TplProdRiskCatPrvtEntityVO) tplProdRiskCatPrvtEntity.getData();
    
    prvtHistEntityVO.setProdRiskCatCode(prvtEntityVO.getProdRiskCatCode());
    prvtHistEntityVO.setProdRiskCatRefDate(prodRiskCatRefDate);
    prvtHistEntityVO.setProdRiskCatText(prvtEntityVO.getProdRiskCatText());
    prvtHistEntityVO.setLastAuthDate(prvtEntityVO.getLastAuthDate());
    prvtHistEntityVO.setLastAuthUserID(prvtEntityVO.getLastAuthUserID());
    prvtHistEntityVO.setLastUpdDate(prvtEntityVO.getLastUpdDate());
    prvtHistEntityVO.setLastUpdUserID(prvtEntityVO.getLastUpdUserID());
    prvtHistEntityVO.setRecStatCode(prvtEntityVO.getRecStatCode());
  }
}