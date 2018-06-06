/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplErEmMovEntityVO;

/**
 * @author fernando.salgado
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TplErEmMovEntity extends BaseTplErEmEntity
{
  public TplErEmMovEntity()
  {
    m_data = new TplErEmMovEntityVO();
  }

  public TplErEmMovEntity( TplErEmEntity tplErEmEntity_, String opernCode_ )
  {
    m_data = new TplErEmMovEntityVO();
    TplErEmMovEntityVO tplErEmMovEntityVO = ( TplErEmMovEntityVO ) m_data;
    tplErEmMovEntityVO.setErNbr( tplErEmEntity_.getData().getErNbr() );
    tplErEmMovEntityVO.setEmNbr( tplErEmEntity_.getData().getEmNbr() );
    tplErEmMovEntityVO.setRoleCustCode( tplErEmEntity_.getData().getRoleCustCode() );
    tplErEmMovEntityVO.setLastUpdDate( tplErEmEntity_.getData().getLastUpdDate() );
    tplErEmMovEntityVO.setLastUpdUserId( tplErEmEntity_.getData().getLastUpdUserId() );
    tplErEmMovEntityVO.setOpernCode( opernCode_ );
  }
  
  /**
   * Um objeto do tipo TplErEmEntity pode ser considerado o mesmo
   * quando seus ers e ems são iguais
   */
  public boolean equals( Object obj_ )
  {
    TplErEmMovEntity entity = ( TplErEmMovEntity ) obj_;

    boolean isErEqual = entity.getData().getErNbr() == this.getData().getErNbr()
                        || entity.getData().getErNbr().equals(
                                                               this.getData().getErNbr() );

    boolean isEmEqual = entity.getData().getEmNbr() == this.getData().getEmNbr()
                        || entity.getData().getEmNbr().equals(
                                                               this.getData().getEmNbr() );

    return isErEqual && isEmEqual;

  }
}