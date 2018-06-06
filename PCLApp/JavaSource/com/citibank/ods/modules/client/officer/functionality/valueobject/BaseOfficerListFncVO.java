package com.citibank.ods.modules.client.officer.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * @see package
 *      com.citibank.ods.modules.client.officer.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 26, 2007
 *  
 */

public class BaseOfficerListFncVO extends BaseFncVO
{

  //Número do Officer
  private BigInteger m_offcrNbrSrc = null;

  // Nome do Officer
  private String m_offcrNameTextSrc = "";

  // Número do officer real quando o Código for de um officer fictício
  private BigInteger m_offcrRealNbrSrc = null;

  //Número do officer selecionado no grid
  private String m_selectedOffcrNbr = "";

  //Número internacional do officer
  private BigInteger m_offcrIntlNbrSrc = null;

  //Tipo do officer
  private String m_offcrTypeCodeSrc = null;
  
  //Número do relacionamento
  private BigInteger m_reltnNbrSrc = null;
  
  //Tipo do Officer - Combo
  private DataSet m_offcrTypeCodeDomain = null;

  // DataSet como os resultados do banco.
  private DataSet m_results;

  //Constante do número do Officer
  public static final String C_OFFCR_NBR_SRC_DESCRIPTION = "Número do Officer";

  // Constante Nome do officer
  public static final String C_OFFCR_NAME_TEXT_SRC_DESCRIPTION = "Nome do Officer";

  // Constante Número de Officer Real
  public static final String C_OFFCR_REAL_NBR_SRC_DESCRIPTION = "Número de Officer Real";

  //Constante tipo de Banker
  public static final String C_OFFCR_TYPE_CODE_DESCRIPTION = "Tipo de Banker";

  //Constante número internacional do Banker
  public static final String C_OFFCR_INTL_DESCRIPTION = "Número Internacional do Banker";
  
  //Constante número internacional do Banker
  public static final String C_RELATION_NBR_DESCRIPTION = "Número do relacionamento";
  /**
   * @param selectedOffcrNbr_.Seta o número do officer selecionado no grid.
   */
  public void setSelectedOffcrNbr( String selectedOffcrNbr_ )
  {
    m_selectedOffcrNbr = selectedOffcrNbr_;
  }

  /**
   * @return m_selectedOffcrNbr. Retorna o número do officer selecionado no
   *         grid.
   */
  public String getSelectedOffcrNbr()
  {
    return m_selectedOffcrNbr;
  }

  /**
   * @return m_offcrNameTextSrc.Retorna o nome do officer.
   */
  public String getOffcrNameTextSrc()
  {
    return m_offcrNameTextSrc;
  }

  /**
   * @param offcrNameTextSrc_.Seta o nome do officer.
   */
  public void setOffcrNameTextSrc( String offcrNameTextSrc_ )
  {
    m_offcrNameTextSrc = offcrNameTextSrc_;
  }

  /**
   * @return m_offcrNbrSrc. Retorna o número do officer.
   */
  public BigInteger getOffcrNbrSrc()
  {
    return m_offcrNbrSrc;
  }

  /**
   * @param offcrNbrSrc_.Seta o número do officer.
   */
  public void setOffcrNbrSrc( BigInteger offcrNbrSrc_ )
  {
    m_offcrNbrSrc = offcrNbrSrc_;
  }

  /**
   * @return m_offcrRealNbrSrc. Retorna o número real do officer.
   */
  public BigInteger getOffcrRealNbrSrc()
  {
    return m_offcrRealNbrSrc;
  }

  /**
   * @param offcrRealNbrSrc_.Seta o número real do officer.
   */
  public void setOffcrRealNbrSrc( BigInteger offcrRealNbrSrc_ )
  {
    m_offcrRealNbrSrc = offcrRealNbrSrc_;
  }

  /**
   * @return m_results. Retorna uma lista com o resultado da pesquisa.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * @param results_.Seta a lista com o resultado da pesquisa.
   */
  public void setResults( DataSet results_ )
  {
    m_results = results_;
  }

  /**
   * @return m_offcrIntlNbrSrc. Retorna o número internacional do officer.
   */
  public BigInteger getOffcrIntlNbrSrc()
  {
    return m_offcrIntlNbrSrc;
  }

  /**
   * @param offcrIntlNbrSrc_.Seta o número internacional do officer.
   */
  public void setOffcrIntlNbrSrc( BigInteger offcrIntlNbrSrc_ )
  {
    m_offcrIntlNbrSrc = offcrIntlNbrSrc_;
  }

  /**
   * @return m_offcrTypeCodeDomain. Retorna uma lista com os tipos de officer.
   */
  public DataSet getOffcrTypeCodeDomain()
  {
    return m_offcrTypeCodeDomain;
  }

  /**
   * @param offcrTypeCodeDomain_.Seta na lista os tipos de officer.
   */
  public void setOffcrTypeCodeDomain( DataSet offcrTypeCodeDomain_ )
  {
    m_offcrTypeCodeDomain = offcrTypeCodeDomain_;
  }

  /**
   * @return m_offcrTypeCodeSrc. Retorna o tipo de officer.
   */
  public String getOffcrTypeCodeSrc()
  {
    return m_offcrTypeCodeSrc;
  }

  /**
   * @param offcrTypeCodeSrc_.Seta o tipo de officer.
   */
  public void setOffcrTypeCodeSrc( String offcrTypeCodeSrc_ )
  {
    m_offcrTypeCodeSrc = offcrTypeCodeSrc_;
  }
  
  
  /**
   * @return Returns reltnNbrSrc.
   */
  public BigInteger getReltnNbrSrc()
  {
    return m_reltnNbrSrc;
  }
  /**
   * @param reltnNbrSrc_ Field reltnNbrSrc to be setted.
   */
  public void setReltnNbrSrc( BigInteger reltnNbrSrc_ )
  {
    m_reltnNbrSrc = reltnNbrSrc_;
  }
}