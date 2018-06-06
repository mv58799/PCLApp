/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplErEmEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplErEmMovEntityVO;

/**
 * @author fernando.salgado
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TplErEmEntity extends BaseTplErEmEntity
{

  public TplErEmEntity()
  {
    m_data = new TplErEmEntityVO();
  }

  public TplErEmEntity( TplErEmMovEntity tplErEmMovEntity,
                       String lastAuthUserId_, Date lastAuthDate_,
                       String recStatCode_ )
  {
    m_data = new TplErEmEntityVO();
    TplErEmEntityVO tplErEmEntityVO = ( TplErEmEntityVO ) m_data;
    TplErEmMovEntityVO tplErEmMovEntityVO = ( TplErEmMovEntityVO ) tplErEmMovEntity.getData();

    tplErEmEntityVO.setErNbr( tplErEmMovEntityVO.getErNbr() );
    tplErEmEntityVO.setEmNbr( tplErEmMovEntityVO.getEmNbr() );
    tplErEmEntityVO.setRoleCustCode( tplErEmMovEntityVO.getRoleCustCode() );
    tplErEmEntityVO.setLastUpdDate( tplErEmMovEntityVO.getLastUpdDate() );
    tplErEmEntityVO.setLastUpdUserId( tplErEmMovEntityVO.getLastUpdUserId() );
    tplErEmEntityVO.setLastAuthDate( lastAuthDate_ );
    tplErEmEntityVO.setLastAuthUserId( lastAuthUserId_ );
    tplErEmEntityVO.setRecStatCode( recStatCode_ );

  }

  /**
   * Um objeto do tipo TplErEmEntity pode ser considerado o mesmo
   * quando seus ers e ems são iguais
   */
  public boolean equals( Object obj_ )
  {
    TplErEmEntity entity = ( TplErEmEntity ) obj_;

    boolean isErEqual = entity.getData().getErNbr() == this.getData().getErNbr()
                        || entity.getData().getErNbr().equals(
                                                               this.getData().getErNbr() );

    boolean isEmEqual = entity.getData().getEmNbr() == this.getData().getEmNbr()
                        || entity.getData().getEmNbr().equals(
                                                               this.getData().getEmNbr() );

    return isErEqual && isEmEqual;

  }
}