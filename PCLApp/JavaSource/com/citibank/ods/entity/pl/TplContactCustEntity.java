package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplContactCustEntityVO;

/**
 * @author Hamilton Matos
 *  
 */

public class TplContactCustEntity extends BaseTplContactCustEntity
{

  /**
   * Construtor padrão - sem argumentos
   */
  public TplContactCustEntity()
  {
    m_data = new TplContactCustEntityVO();
  }

  /**
   * Construtor - Recupera dados de tipo de operação
   */
  public TplContactCustEntity( TplContactCustEntity contactCustEntity_,
                              String opernCode_ )
  {

    m_data = new TplContactCustEntityVO();
    m_data.setCtcNbr( contactCustEntity_.getData().getCtcNbr() );
    m_data.setFullNameText( contactCustEntity_.getData().getFullNameText() );
	m_data.setFullNameText_2( contactCustEntity_.getData().getFullNameText_2() );
	m_data.setFullNameText_3( contactCustEntity_.getData().getFullNameText_3() );
    m_data.setPhoneDddCode( contactCustEntity_.getData().getPhoneDddCode() );
    m_data.setPhoneNbr( contactCustEntity_.getData().getPhoneNbr() );
    m_data.setPhoneExtnNbr( contactCustEntity_.getData().getPhoneExtnNbr() );

    ( ( TplContactCustEntityVO ) m_data ).setOpernCode( opernCode_ );
  }

  /**
   * Sobrescreve método equals da classe Object. Verifica se os dois valores da
   * chave (número do cliente e número do contato) são numericamente iguais nos
   * dois objetos.
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  public boolean equals( Object obj_ )
  {
    TplContactCustEntity obj = ( TplContactCustEntity ) obj_;

    if ( m_data.getCtcNbr() == null || m_data.getCustNbr() == null
         || obj.getData().getCtcNbr() == null
         || obj.getData().getCustNbr() == null )
    {
      return false;
    }

    if ( m_data.getCtcNbr().equals( obj.getData().getCtcNbr() )
         && m_data.getCustNbr().equals( obj.getData().getCustNbr() ) )
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  public int hashCode()
  {
    int result = 17;
    result = 37 * result + m_data.getCtcNbr().intValue();
    result = 37 * result + m_data.getCustNbr().intValue();
    return result;
  }
}