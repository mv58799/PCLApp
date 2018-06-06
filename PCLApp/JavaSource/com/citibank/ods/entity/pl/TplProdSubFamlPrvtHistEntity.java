/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProdSubFamlPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdSubFamlPrvtHistEntityVO;

/**
 * @author fernando.salgado
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TplProdSubFamlPrvtHistEntity extends
    BaseTplProdSubFamlPrvtEntity
{
  /**
   * Construtor padrão - sem argumentos
   */
  public TplProdSubFamlPrvtHistEntity()
  {
    m_data = new TplProdSubFamlPrvtHistEntityVO();
  }

  /**
   * Construtor - Carrega os atributos de instancia com os atributos de Current
   */
  public TplProdSubFamlPrvtHistEntity(
                                           TplProdSubFamlPrvtEntity familyPrvtEntity_,
                                           Date prodSubFamlPrvtRefDate_ )
  {
    m_data = new TplProdSubFamlPrvtHistEntityVO();

    m_data.setProdSubFamlCode( familyPrvtEntity_.getData().getProdSubFamlCode() );
    m_data.setProdSubFamlName( familyPrvtEntity_.getData().getProdSubFamlName() );
    m_data.setProdSubFamlText( familyPrvtEntity_.getData().getProdSubFamlText() );
    m_data.setProdFamlCode( familyPrvtEntity_.getData().getProdFamlCode() );
    m_data.setLastUpdUserId( familyPrvtEntity_.getData().getLastUpdUserId() );
    m_data.setLastUpdDate( familyPrvtEntity_.getData().getLastUpdDate() );
    ( ( TplProdSubFamlPrvtHistEntityVO ) m_data ).setLastAuthDate( ( ( TplProdSubFamlPrvtEntityVO ) familyPrvtEntity_.getData() ).getLastAuthDate() );
    ( ( TplProdSubFamlPrvtHistEntityVO ) m_data ).setLastAuthUserId( ( ( TplProdSubFamlPrvtEntityVO ) familyPrvtEntity_.getData() ).getLastAuthUserId() );
    ( ( TplProdSubFamlPrvtHistEntityVO ) m_data ).setRecStatCode( ( ( TplProdSubFamlPrvtEntityVO ) familyPrvtEntity_.getData() ).getRecStatCode() );
    ( ( TplProdSubFamlPrvtHistEntityVO ) m_data ).setProdSubFamlRefDate( prodSubFamlPrvtRefDate_ );
  }
}