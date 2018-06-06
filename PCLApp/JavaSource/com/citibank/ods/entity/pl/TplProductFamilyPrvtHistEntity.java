/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProductFamilyPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductFamilyPrvtHistEntityVO;

/**
 * @author fernando.salgado
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TplProductFamilyPrvtHistEntity extends
    BaseTplProductFamilyPrvtEntity
{
  /**
   * Construtor padrão - sem argumentos
   */
  public TplProductFamilyPrvtHistEntity()
  {
    m_data = new TplProductFamilyPrvtHistEntityVO();
  }

  /**
   * Construtor - Carrega os atributos de instancia com os atributos de Current
   */
  public TplProductFamilyPrvtHistEntity(
                                        TplProductFamilyPrvtEntity tplProductFamilyPrvtEntity_,
                                        Date prodFamlPrvtRefDate_ )
  {
    m_data = new TplProductFamilyPrvtHistEntityVO();

    m_data.setProdFamlCode( tplProductFamilyPrvtEntity_.getData().getProdFamlCode() );
    m_data.setProdFamlName( tplProductFamilyPrvtEntity_.getData().getProdFamlName() );
    m_data.setProdFamlText( tplProductFamilyPrvtEntity_.getData().getProdFamlText() );
    m_data.setLastUpdUserId( tplProductFamilyPrvtEntity_.getData().getLastUpdUserId() );
    m_data.setLastUpdDate( tplProductFamilyPrvtEntity_.getData().getLastUpdDate() );
    ( ( TplProductFamilyPrvtHistEntityVO ) m_data ).setLastAuthDate( ( ( TplProductFamilyPrvtEntityVO ) tplProductFamilyPrvtEntity_.getData() ).getLastAuthDate() );
    ( ( TplProductFamilyPrvtHistEntityVO ) m_data ).setLastAuthUserId( ( ( TplProductFamilyPrvtEntityVO ) tplProductFamilyPrvtEntity_.getData() ).getLastAuthUserId() );
    ( ( TplProductFamilyPrvtHistEntityVO ) m_data ).setRecStatCode( ( ( TplProductFamilyPrvtEntityVO ) tplProductFamilyPrvtEntity_.getData() ).getRecStatCode() );
    ( ( TplProductFamilyPrvtHistEntityVO ) m_data ).setProdFamlRefDate( prodFamlPrvtRefDate_ );
  }
}