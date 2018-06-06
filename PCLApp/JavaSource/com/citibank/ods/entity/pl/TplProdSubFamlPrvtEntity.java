/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProdSubFamlPrvtEntityVO;

/**
 * @author fernando.salgado
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TplProdSubFamlPrvtEntity extends
    BaseTplProdSubFamlPrvtEntity
{
  /**
   * Construtor padrão - sem argumentos
   */
  public TplProdSubFamlPrvtEntity()
  {
    m_data = new TplProdSubFamlPrvtEntityVO();
  }

  /**
   * Construtor - Carrega os atributos com os atributos do movimento
   */
  public TplProdSubFamlPrvtEntity(
                                       TplProdSubFamlPrvtMovEntity prvtMovEntity,
                                       Date lastAuthDate_,
                                       String lastAuthUserId_,
                                       String recStatCode_ )
  {
    m_data = new TplProdSubFamlPrvtEntityVO();

    m_data.setProdSubFamlCode( prvtMovEntity.getData().getProdSubFamlCode() );
    m_data.setProdSubFamlName( prvtMovEntity.getData().getProdSubFamlName() );
    m_data.setProdSubFamlText( prvtMovEntity.getData().getProdSubFamlText() );
    m_data.setProdFamlCode( prvtMovEntity.getData().getProdFamlCode() );
    m_data.setLastUpdUserId( prvtMovEntity.getData().getLastUpdUserId() );
    m_data.setLastUpdDate( prvtMovEntity.getData().getLastUpdDate() );
    ( ( TplProdSubFamlPrvtEntityVO ) m_data ).setLastAuthDate( lastAuthDate_ );
    ( ( TplProdSubFamlPrvtEntityVO ) m_data ).setLastAuthUserId( lastAuthUserId_ );
    ( ( TplProdSubFamlPrvtEntityVO ) m_data ).setRecStatCode( recStatCode_ );
  }
}