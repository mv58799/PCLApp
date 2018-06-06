/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplProdQlfyPrvtMovEntityVO;

/**
 * @author fernando.salgado
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TplProdQlfyPrvtMovEntity extends BaseTplProdQlfyPrvtEntity
{
  /**
   * Constante do tamanho do Qualificador
   */
  public static final int C_LAST_UPD_USER_ID_SIZE = 20;

  /**
   * Construtor padrão - sem argumentos
   */
  public TplProdQlfyPrvtMovEntity()
  {
    m_data = new TplProdQlfyPrvtMovEntityVO();
  }

  /*
   * Construtor parametro current
   */
  public TplProdQlfyPrvtMovEntity( TplProdQlfyPrvtEntity prvtEntity_,
                                  String opernCode_ )
  {
    m_data = new TplProdQlfyPrvtMovEntityVO();

    m_data.setProdQlfyCode( prvtEntity_.getData().getProdQlfyCode() );
    m_data.setProdQlfyText( prvtEntity_.getData().getProdQlfyText() );
    m_data.setLastUpdUserId( prvtEntity_.getData().getLastUpdUserId() );
    m_data.setLastUpdDate( prvtEntity_.getData().getLastUpdDate() );
    ( ( TplProdQlfyPrvtMovEntityVO ) m_data ).setOpernCode( opernCode_ );
  }

}