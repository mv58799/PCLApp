package com.citibank.ods.modules.client.officer.form;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.officer.form;
 * @version 1.0
 * @author l.braga,23/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseOfficerDetailForm extends BaseForm implements
    OfficerDetailable
{
  // Categoria do Officer 1-Interna 2-Externo 3-Telemark
  private String m_offcrCatCode = "";

  // Código do canal de venda do Officer
  private String m_offcrChnnlCode = "";

  // Email do Officer
  private String m_offcrEmailName = "";

  // Nome do Officer
  private String m_offcrNameText = "";

  // Número do Officer
  private String m_offcrNbr = "";

  // Apelido
  private String m_offcrNcknName = "";

  // Código da operadora dp telephone do officer
  private String m_offcrPhoneOpCode = "";

  // Código área telefone secundário
  private String m_offcrPhoneSecAreaCode = "";

  // Extensão do telephone secundário
  private String m_offcrPhoneSecExtnNbr = "";

  // Numero do telephone secundário
  private String m_offcrPhoneSecNbr = "";

  // Operadora do telephone secundario
  private String m_offcrPhoneSecOpCode = "";

  // Número do officer real quando o Código for de um officer fictício
  private String m_offcrRealNbr = "";

  // Officer Remote
  private String m_offcrRemoteCode = "";

  // Officer backup
  private String m_offcrSecCode = "";

  // Data inicial de trabalho do Officer
  private String m_offcrStartDate = "";

  // Status do Officer 1-Incluido 2-Ativo
  private String m_offcrStatCode = "";

  private DataSet m_offcrCatCodeDomain;

  private DataSet m_offcrStatCodeDomain;

  //Tipo do officer
  private String m_offcrTypeCode = "";

  //Número Internacional do officer
  private String m_offcrIntlNbr = "";

  //Tipo do officer- Combo
  private DataSet m_offcrTypeCodeDomain = null;

  //Variável de controle - Verifica se existe ou não dados complementares de
  // banker
  private String m_existingData = "";
  
  /**
   * @return Returns offcrCatCode.
   */
  public String getOffcrCatCode()
  {
    return m_offcrCatCode;
  }

  /**
   * @param offcrCatCode_ Field offcrCatCode to be setted.
   */
  public void setOffcrCatCode( String offcrCatCode_ )
  {
    m_offcrCatCode = offcrCatCode_;
  }

  /**
   * @return Returns offcrChnnlCode.
   */
  public String getOffcrChnnlCode()
  {
    return m_offcrChnnlCode;
  }

  /**
   * @param offcrChnnlCode_ Field offcrChnnlCode to be setted.
   */
  public void setOffcrChnnlCode( String offcrChnnlCode_ )
  {
    m_offcrChnnlCode = offcrChnnlCode_;
  }

  /**
   * @return Returns offcrEmailName.
   */
  public String getOffcrEmailName()
  {
    return m_offcrEmailName;
  }

  /**
   * @param offcrEmailName_ Field offcrEmailName to be setted.
   */
  public void setOffcrEmailName( String offcrEmailName_ )
  {
    m_offcrEmailName = offcrEmailName_;
  }

  /**
   * @return Returns offcrNameText.
   */
  public String getOffcrNameText()
  {
    return m_offcrNameText;
  }

  /**
   * @param offcrNameText_ Field offcrNameText to be setted.
   */
  public void setOffcrNameText( String offcrNameText_ )
  {
    m_offcrNameText = offcrNameText_;
  }

  /**
   * @return Returns offcrNbr.
   */
  public String getOffcrNbr()
  {
    return m_offcrNbr;
  }

  /**
   * @param offcrNbr_ Field offcrNbr to be setted.
   */
  public void setOffcrNbr( String offcrNbr_ )
  {
    m_offcrNbr = offcrNbr_;
  }

  /**
   * @return Returns offcrNcknName.
   */
  public String getOffcrNcknName()
  {
    return m_offcrNcknName;
  }

  /**
   * @param offcrNcknName_ Field offcrNcknName to be setted.
   */
  public void setOffcrNcknName( String offcrNcknName_ )
  {
    m_offcrNcknName = offcrNcknName_;
  }

  /**
   * @return Returns offcrPhoneOpCode.
   */
  public String getOffcrPhoneOpCode()
  {
    return m_offcrPhoneOpCode;
  }

  /**
   * @param offcrPhoneOpCode_ Field offcrPhoneOpCode to be setted.
   */
  public void setOffcrPhoneOpCode( String offcrPhoneOpCode_ )
  {
    m_offcrPhoneOpCode = offcrPhoneOpCode_;
  }

  /**
   * @return Returns offcrPhoneSecAreaCode.
   */
  public String getOffcrPhoneSecAreaCode()
  {
    return m_offcrPhoneSecAreaCode;
  }

  /**
   * @param offcrPhoneSecAreaCode_ Field offcrPhoneSecAreaCode to be setted.
   */
  public void setOffcrPhoneSecAreaCode( String offcrPhoneSecAreaCode_ )
  {
    m_offcrPhoneSecAreaCode = offcrPhoneSecAreaCode_;
  }

  /**
   * @return Returns offcrPhoneSecExtnNbr.
   */
  public String getOffcrPhoneSecExtnNbr()
  {
    return m_offcrPhoneSecExtnNbr;
  }

  /**
   * @param offcrPhoneSecExtnNbr_ Field offcrPhoneSecExtnNbr to be setted.
   */
  public void setOffcrPhoneSecExtnNbr( String offcrPhoneSecExtnNbr_ )
  {
    m_offcrPhoneSecExtnNbr = offcrPhoneSecExtnNbr_;
  }

  /**
   * @return Returns offcrPhoneSecNbr.
   */
  public String getOffcrPhoneSecNbr()
  {
    return m_offcrPhoneSecNbr;
  }

  /**
   * @param offcrPhoneSecNbr_ Field offcrPhoneSecNbr to be setted.
   */
  public void setOffcrPhoneSecNbr( String offcrPhoneSecNbr_ )
  {
    m_offcrPhoneSecNbr = offcrPhoneSecNbr_;
  }

  /**
   * @return Returns offcrPhoneSecOpCode.
   */
  public String getOffcrPhoneSecOpCode()
  {
    return m_offcrPhoneSecOpCode;
  }

  /**
   * @param offcrPhoneSecOpCode_ Field offcrPhoneSecOpCode to be setted.
   */
  public void setOffcrPhoneSecOpCode( String offcrPhoneSecOpCode_ )
  {
    m_offcrPhoneSecOpCode = offcrPhoneSecOpCode_;
  }

  /**
   * @return Returns offcrRealNbr.
   */
  public String getOffcrRealNbr()
  {
    return m_offcrRealNbr;
  }

  /**
   * @param offcrRealNbr_ Field offcrRealNbr to be setted.
   */
  public void setOffcrRealNbr( String offcrRealNbr_ )
  {
    m_offcrRealNbr = offcrRealNbr_;
  }

  /**
   * @return Returns offcrRemoteCode.
   */
  public String getOffcrRemoteCode()
  {
    return m_offcrRemoteCode;
  }

  /**
   * @param offcrRemoteCode_ Field offcrRemoteCode to be setted.
   */
  public void setOffcrRemoteCode( String offcrRemoteCode_ )
  {
    m_offcrRemoteCode = offcrRemoteCode_;
  }

  /**
   * @return Returns offcrSecCode.
   */
  public String getOffcrSecCode()
  {
    return m_offcrSecCode;
  }

  /**
   * @param offcrSecCode_ Field offcrSecCode to be setted.
   */
  public void setOffcrSecCode( String offcrSecCode_ )
  {
    m_offcrSecCode = offcrSecCode_;
  }

  /**
   * @return Returns offcrStartDate.
   */
  public String getOffcrStartDate()
  {
    return m_offcrStartDate;
  }

  /**
   * @param offcrStartDate_ Field offcrStartDate to be setted.
   */
  public void setOffcrStartDate( String offcrStartDate_ )
  {
    m_offcrStartDate = offcrStartDate_;
  }

  /**
   * @return Returns offcrStatCode.
   */
  public String getOffcrStatCode()
  {
    return m_offcrStatCode;
  }

  /**
   * @param offcrStatCode_ Field offcrStatCode to be setted.
   */
  public void setOffcrStatCode( String offcrStatCode_ )
  {
    m_offcrStatCode = offcrStatCode_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.officer.form.OfficerDetailable#getSelectedOffcrNbr()
   */
  public String getSelectedOffcrNbr()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.officer.form.OfficerDetailable#setSelectedOffcrNbr()
   */
  public void setSelectedOffcrNbr( String selectedOffcrNbr_ )
  {
    setOffcrNbr( selectedOffcrNbr_ );
  }

  /**
   * @return Returns offcrCatCodeDomain.
   */
  public DataSet getOffcrCatCodeDomain()
  {
    return m_offcrCatCodeDomain;
  }

  /**
   * @param offcrCatCodeDomain_ Field offcrCatCodeDomain to be setted.
   */
  public void setOffcrCatCodeDomain( DataSet offcrCatCodeDomain_ )
  {
    m_offcrCatCodeDomain = offcrCatCodeDomain_;
  }

  /**
   * @return Returns offcrStatCodeDomain.
   */
  public DataSet getOffcrStatCodeDomain()
  {
    return m_offcrStatCodeDomain;
  }

  /**
   * @param offcrStatCodeDomain_ Field offcrStatCodeDomain to be setted.
   */
  public void setOffcrStatCodeDomain( DataSet offcrStatCodeDomain_ )
  {
    m_offcrStatCodeDomain = offcrStatCodeDomain_;
  }

  /**
   * @return Returns offcrIntlNbr.
   */
  public String getOffcrIntlNbr()
  {
    return m_offcrIntlNbr;
  }

  /**
   * @param offcrIntlNbr_ Field offcrIntlNbr to be setted.
   */
  public void setOffcrIntlNbr( String offcrIntlNbr_ )
  {
    m_offcrIntlNbr = offcrIntlNbr_;
  }

  /**
   * @return Returns offcrTypeCode.
   */
  public String getOffcrTypeCode()
  {
    return m_offcrTypeCode;
  }

  /**
   * @param offcrTypeCode_ Field offcrTypeCode to be setted.
   */
  public void setOffcrTypeCode( String offcrTypeCode_ )
  {
    m_offcrTypeCode = offcrTypeCode_;
  }
  
  
  /**
   * @return Returns offcrTypeCodeDomain.
   */
  public DataSet getOffcrTypeCodeDomain()
  {
    return m_offcrTypeCodeDomain;
  }
  /**
   * @param offcrTypeCodeDomain_ Field offcrTypeCodeDomain to be setted.
   */
  public void setOffcrTypeCodeDomain( DataSet offcrTypeCodeDomain_ )
  {
    m_offcrTypeCodeDomain = offcrTypeCodeDomain_;
  }
  
  
  /**
   * @return Returns existingData.
   */
  public String getExistingData()
  {
    return m_existingData;
  }
  /**
   * @param existingData_ Field existingData to be setted.
   */
  public void setExistingData( String existingData_ )
  {
    m_existingData = existingData_;
  }
}