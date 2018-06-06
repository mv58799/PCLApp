/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplErEmEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplErEmHistEntityVO;

/**
 * @author fernando.salgado
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TplErEmHistEntity extends BaseTplErEmEntity
{
  /**
   * Construtor
   */
  public TplErEmHistEntity()
  {
    m_data = new TplErEmHistEntityVO();
  }

  /**
   * Construtor - Criando um objeto a partir de outro já populado
   */
  public TplErEmHistEntity( TplErEmEntity tplErEmEntity_, Date refDate_ )
  {
    m_data = new TplErEmHistEntityVO();
    TplErEmHistEntityVO emHistEntityVO = ( TplErEmHistEntityVO ) m_data;
    TplErEmEntityVO tplErEmEntityVO = ( TplErEmEntityVO ) tplErEmEntity_.getData();

    emHistEntityVO.setErNbr( tplErEmEntityVO.getErNbr() );
    emHistEntityVO.setEmNbr( tplErEmEntityVO.getEmNbr() );
    emHistEntityVO.setRoleCustCode( tplErEmEntityVO.getRoleCustCode() );
    emHistEntityVO.setErEmRefDate( refDate_ );
    emHistEntityVO.setLastUpdDate( tplErEmEntityVO.getLastUpdDate() );
    emHistEntityVO.setLastUpdUserId( tplErEmEntityVO.getLastUpdUserId() );
    emHistEntityVO.setLastAuthDate( tplErEmEntityVO.getLastAuthDate() );
    emHistEntityVO.setLastAuthUserId( tplErEmEntityVO.getLastAuthUserId() );
    emHistEntityVO.setRecStatCode( tplErEmEntityVO.getRecStatCode() );
  }
}