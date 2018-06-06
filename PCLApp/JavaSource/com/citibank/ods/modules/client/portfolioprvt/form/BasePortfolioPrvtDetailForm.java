package com.citibank.ods.modules.client.portfolioprvt.form;



import com.citibank.ods.common.form.BaseForm;

/**
 * @author m.nakamura
 *  
 */
public class BasePortfolioPrvtDetailForm extends BaseForm implements PortfolioPrvtDetailable
{

  // Codigo da filial da carteira
  private String m_portfBrchCode = "";

  // Codigo da Carteira
  private String m_portfCode = "";

  // Grupo de Negocio do Centro de Custo
  private String m_portfCostBusGrpCode = "";

  // Centro de Custo
  private String m_portfCostDivCode = "";

  // Codigo de Rentabilidade do Centro de Custo
  private String m_portfCostPrftyCtrCode = "";

  // Codigo da Origem do Cliente
  private String m_portfCostRegionCode = "";

  // Codigo do Officer responsavel pelo Centro de Custo
  private String m_portfCostRespOffcrCode = "";

  // Codigo do Provedor de Servico.
  private String m_portfCservSuplCode = "";

  // Descricao da Carteira
  private String m_portfNameText = "";

  // Codigo do Sub Grupo Network
  private String m_portfNetwkSubGrpCode = "";

  // Codigo do grupo network
  private String m_portfNetwkSubNetwkGrpCode = "";

  // Officer da carteira
  private String m_portfOffcrNbr = "";

  // Tipo de operacoes suportadas pela carteira
  private String m_portfOpernType = "";

  // Territorio
  private String m_portfRegionCode = "";

  // Codigo do segmento. (campo redundante no mainframe apenas incluido
  // porperformance)
  private String m_portfSegCode = "";

  // Codigo do Sub-Segmento
  private String m_portfSegSubCode = "";

  // Data de cadastramento da Carteira
  private String m_portfStartDate = "";

  // Status da Carteira
  private String m_portfStatCode = "";

  // Setor da carteira
  private String m_portfUnitCode = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou
  // aguardando aprovacao
  private String m_recStatCode = "";
  
  // Nome do officer
  private String m_offNameText = "";

  /**
   * @return Returns offNameText.
   */
  public String getOffNameText()
  {
    return m_offNameText;
  }
  /**
   * @param offNameText_ Field offNameText to be setted.
   */
  public void setOffNameText( String offNameText_ )
  {
    m_offNameText = offNameText_;
  }
  /**
   * @return Returns m_portfBrchCode.
   */
  public String getPortfBrchCode()
  {
    return m_portfBrchCode;
  }

  /**
   * @param portfBrchCode_ Field m_portfBrchCode to be setted.
   */
  public void setPortfBrchCode( String portfBrchCode_ )
  {
    m_portfBrchCode = portfBrchCode_;
  }

  /**
   * @return Returns m_portfCode.
   */
  public String getPortfCode()
  {
    return m_portfCode;
  }

  /**
   * @param portfCode_ Field m_portfCode to be setted.
   */
  public void setPortfCode( String portfCode_ )
  {
    m_portfCode = portfCode_;
  }

  /**
   * @return Returns m_portfCostBusGrpCode.
   */
  public String getPortfCostBusGrpCode()
  {
    return m_portfCostBusGrpCode;
  }

  /**
   * @param portfCostBusGrpCode_ Field m_portfCostBusGrpCode to be setted.
   */
  public void setPortfCostBusGrpCode( String portfCostBusGrpCode_ )
  {
    m_portfCostBusGrpCode = portfCostBusGrpCode_;
  }

  /**
   * @return Returns m_portfCostDivCode.
   */
  public String getPortfCostDivCode()
  {
    return m_portfCostDivCode;
  }

  /**
   * @param portfCostDivCode_ Field m_portfCostDivCode to be setted.
   */
  public void setPortfCostDivCode( String portfCostDivCode_ )
  {
    m_portfCostDivCode = portfCostDivCode_;
  }

  /**
   * @return Returns m_portfCostPrftyCtrCode.
   */
  public String getPortfCostPrftyCtrCode()
  {
    return m_portfCostPrftyCtrCode;
  }

  /**
   * @param portfCostPrftyCtrCode_ Field m_portfCostPrftyCtrCode to be setted.
   */
  public void setPortfCostPrftyCtrCode( String portfCostPrftyCtrCode_ )
  {
    m_portfCostPrftyCtrCode = portfCostPrftyCtrCode_;
  }

  /**
   * @return Returns m_portfCostRegionCode.
   */
  public String getPortfCostRegionCode()
  {
    return m_portfCostRegionCode;
  }

  /**
   * @param portfCostRegionCode_ Field m_portfCostRegionCode to be setted.
   */
  public void setPortfCostRegionCode( String portfCostRegionCode_ )
  {
    m_portfCostRegionCode = portfCostRegionCode_;
  }

  /**
   * @return Returns m_portfCostRespOffcrCode.
   */
  public String getPortfCostRespOffcrCode()
  {
    return m_portfCostRespOffcrCode;
  }

  /**
   * @param portfCostRespOffcrCode_ Field m_portfCostRespOffcrCode to be setted.
   */
  public void setPortfCostRespOffcrCode( String portfCostRespOffcrCode_ )
  {
    m_portfCostRespOffcrCode = portfCostRespOffcrCode_;
  }

  /**
   * @return Returns m_portfCservSuplCode.
   */
  public String getPortfCservSuplCode()
  {
    return m_portfCservSuplCode;
  }

  /**
   * @param portfCservSuplCode_ Field m_portfCservSuplCode to be setted.
   */
  public void setPortfCservSuplCode( String portfCservSuplCode_ )
  {
    m_portfCservSuplCode = portfCservSuplCode_;
  }

  /**
   * @return Returns m_portfNameText.
   */
  public String getPortfNameText()
  {
    return m_portfNameText;
  }

  /**
   * @param portfNameText_ Field m_portfNameText to be setted.
   */
  public void setPortfNameText( String portfNameText_ )
  {
    m_portfNameText = portfNameText_;
  }

  /**
   * @return Returns m_portfNetwkSubGrpCode.
   */
  public String getPortfNetwkSubGrpCode()
  {
    return m_portfNetwkSubGrpCode;
  }

  /**
   * @param portfNetwkSubGrpCode_ Field m_portfNetwkSubGrpCode to be setted.
   */
  public void setPortfNetwkSubGrpCode( String portfNetwkSubGrpCode_ )
  {
    m_portfNetwkSubGrpCode = portfNetwkSubGrpCode_;
  }

  /**
   * @return Returns m_portfNetwkSubNetwkGrpCode.
   */
  public String getPortfNetwkSubNetwkGrpCode()
  {
    return m_portfNetwkSubNetwkGrpCode;
  }

  /**
   * @param portfNetwkSubNetwkGrpCode_ Field m_portfNetwkSubNetwkGrpCode to be
   *          setted.
   */
  public void setPortfNetwkSubNetwkGrpCode( String portfNetwkSubNetwkGrpCode_ )
  {
    m_portfNetwkSubNetwkGrpCode = portfNetwkSubNetwkGrpCode_;
  }

  /**
   * @return Returns m_portfOffcrNbr.
   */
  public String getPortfOffcrNbr()
  {
    return m_portfOffcrNbr;
  }

  /**
   * @param portfOffcrNbr_ Field m_portfOffcrNbr to be setted.
   */
  public void setPortfOffcrNbr( String portfOffcrNbr_ )
  {
    m_portfOffcrNbr = portfOffcrNbr_;
  }

  /**
   * @return Returns m_portfOpernType.
   */
  public String getPortfOpernType()
  {
    return m_portfOpernType;
  }

  /**
   * @param portfOpernType_ Field m_portfOpernType to be setted.
   */
  public void setPortfOpernType( String portfOpernType_ )
  {
    m_portfOpernType = portfOpernType_;
  }

  /**
   * @return Returns m_portfRegionCode.
   */
  public String getPortfRegionCode()
  {
    return m_portfRegionCode;
  }

  /**
   * @param portfRegionCode_ Field m_portfRegionCode to be setted.
   */
  public void setPortfRegionCode( String portfRegionCode_ )
  {
    m_portfRegionCode = portfRegionCode_;
  }

  /**
   * @return Returns m_portfSegCode.
   */
  public String getPortfSegCode()
  {
    return m_portfSegCode;
  }

  /**
   * @param portfSegCode_ Field m_portfSegCode to be setted.
   */
  public void setPortfSegCode( String portfSegCode_ )
  {
    m_portfSegCode = portfSegCode_;
  }

  /**
   * @return Returns m_portfSegSubCode.
   */
  public String getPortfSegSubCode()
  {
    return m_portfSegSubCode;
  }

  /**
   * @param portfSegSubCode_ Field m_portfSegSubCode to be setted.
   */
  public void setPortfSegSubCode( String portfSegSubCode_ )
  {
    m_portfSegSubCode = portfSegSubCode_;
  }

  /**
   * @return Returns m_portfStartDate.
   */
  public String getPortfStartDate()
  {
    return m_portfStartDate;
  }

  /**
   * @param portfStartDate_ Field m_portfStartDate to be setted.
   */
  public void setPortfStartDate( String portfStartDate_ )
  {
    m_portfStartDate = portfStartDate_;
  }

  /**
   * @return Returns m_portfStatCode.
   */
  public String getPortfStatCode()
  {
    return m_portfStatCode;
  }

  /**
   * @param portfStatCode_ Field m_portfStatCode to be setted.
   */
  public void setPortfStatCode( String portfStatCode_ )
  {
    m_portfStatCode = portfStatCode_;
  }

  /**
   * @return Returns m_portfUnitCode.
   */
  public String getPortfUnitCode()
  {
    return m_portfUnitCode;
  }

  /**
   * @param portfUnitCode_ Field m_portfUnitCode to be setted.
   */
  public void setPortfUnitCode( String portfUnitCode_ )
  {
    m_portfUnitCode = portfUnitCode_;
  }

  /**
   * @return Returns m_recStatCode.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * @param recStatCode_ Field m_recStatCode to be setted.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  /**
   * @return Returns null.
   */
  public String getSelectedPortfCode()
  {

    return null;
  }

  /**
   * @param portfCode_ 
   * Metado seta o valor selecionado no checkBox na chave do banco.
   */
  public void setSelectedPortfCode( String portfCode_ )
  {
    setPortfCode(portfCode_);
    
  }

}