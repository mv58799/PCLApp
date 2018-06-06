/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProductFamilyPrvtEntityVO;

/**
 * @author fernando.salgado
 * 
 * Contem as informacoes de cadastro de Familia dos Produtos do Private
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TplProductFamilyPrvtEntity extends BaseTplProductFamilyPrvtEntity
{
  /**
   * Construtor padrão - sem argumentos
   */
  public TplProductFamilyPrvtEntity()
  {
    m_data = new TplProductFamilyPrvtEntityVO();
  }

  /**
   * Construtor - Carrega os atributos com os atributos do movimento
   */
  public TplProductFamilyPrvtEntity(
                                    TplProductFamilyPrvtMovEntity tplProductFamilyPrvtMovEntity_,
                                    Date lastAuthDate_, String lastAuthUserId_,
                                    String recStatCode_ )
  {
    m_data = new TplProductFamilyPrvtEntityVO();

    m_data.setProdFamlCode( tplProductFamilyPrvtMovEntity_.getData().getProdFamlCode() );
    m_data.setProdFamlName( tplProductFamilyPrvtMovEntity_.getData().getProdFamlName() );
    m_data.setProdFamlText( tplProductFamilyPrvtMovEntity_.getData().getProdFamlText() );
    m_data.setLastUpdUserId( tplProductFamilyPrvtMovEntity_.getData().getLastUpdUserId() );
    m_data.setLastUpdDate( tplProductFamilyPrvtMovEntity_.getData().getLastUpdDate() );
    ( ( TplProductFamilyPrvtEntityVO ) m_data ).setLastAuthDate( lastAuthDate_ );
    ( ( TplProductFamilyPrvtEntityVO ) m_data ).setLastAuthUserId( lastAuthUserId_ );
    ( ( TplProductFamilyPrvtEntityVO ) m_data ).setRecStatCode( recStatCode_ );
  }
}