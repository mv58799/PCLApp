package com.citibank.ods.modules.client.relationprvt.form;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;

/**
*@author l.braga
*
*/

public class BaseRelationPrvtDetailForm extends BaseForm implements RelationPrvtDetailable
{
  
  // Código do Relacionamento selecionado
  private String m_selectedReltnNbr = "";
  
  // Date da ultima alteracao
  private String m_lastUpdateDate = "";

  // Operador da ultima alteracao
  private String m_lastUpdateOpId = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou aguardando aprovacao
  private String m_recStatCode = "";

  // Indicador de Recebimento de Extrato Isolado da Conta Corrente.
  private String m_reltnAcctStmtInd = "";

  // Numero do cliente enderecador para celular.
  private String m_reltnAddrCellCustNbr = "";

  // ponteiro de celular principal do cliente
  private String m_reltnAddrCellSeqNbr = "";

  // Numero do Cliente enderecador para email.
  private String m_reltnAddrEmailCustNbr = "";

  // ponteiro de email principal do cliente.
  private String m_reltnAddrEmailSeqNbr = "";

  // Codigo de categoria do relacionamento.
  private String m_reltnCatCode = "";

  // Classe de Relacionamento
  private String m_reltnClassCode = "";

  // Faixa de pontuacao do RELTN, para fins de determinacao da Classe de Relacionamento
  private String m_reltnClassScoreCode = "";

  // Tipo de comunicacao com o Cliente.
  private String m_reltnCommTypeCode = "";

  // Numero do cliente corporate na  arquitetura cosmos (Numero Base)
  private String m_reltnCorpBaseNbr = "";

  // Numero do primeiro Cliente do Relacionamento.
  private String m_reltnCust1Nbr = "";

  // Numero do segundo Cliente do Relacionamento.
  private String m_reltnCust2Nbr = "";

  // Numero do terceiro Cliente do Relacionamento.
  private String m_reltnCust3Nbr = "";

  // Numero do quarto Cliente do Relacionamento.
  private String m_reltnCust4Nbr = "";

  // Numero do quinto Cliente do Relacionamento.
  private String m_reltnCust5Nbr = "";

  // Numero do cliente enderecador
  private String m_reltnCustAddrNbr = "";

  // Numero da sequencia do endereco
  private String m_reltnCustAddrSeqNbr = "";

  // Data do estabelecimento do Relacionamento.
  private String m_reltnEstabDate = "";

  // Indicador de Recebimento de Extrato Isolado do Multifundos
  private String m_reltnMfStmtInd = "";

  // Numero do Relacionamento
  private String m_reltnNbr = "";

  // Codigo da Carteira
  private String m_reltnPortfCode = "";

  // Codigo da Origem do Cliente
  private String m_reltnPrmtCode = "";

  // Codigo do nivel de risco do Relacionamento p/ o Private
  private String m_reltnRiskLevelCode = "";

  // Indicador de Recebimento de Extrato Isolado de Poupanca
  private String m_reltnSavStmtInd = "";

  // Codigo do Segmento
  private String m_reltnSegCode = "";

  // Indicador de pacote da classe especifica do relacionamento
  private String m_reltnSpcfClassServPackInd = "";

  // Codigo do tipo de recebimento de extrato do CitiStatement
  private String m_reltnStmtOptnCode = "";

  // Indicador de Recebimento de Extrato Isolado do TD
  private String m_reltnTdStmtInd = "";

  // Tipo de Relacionamento.
  private String m_reltnTypeCode = "";
  
  // Nome do cliente no Primeiro relacionamento.
  private String m_custFullNameText = "";
  
  // Nome do cliente no Segundo relacionamento.
  private String m_custFullNameText1 = "";
  
  // Nome do cliente no Terceiro relacionamento.
  private String m_custFullNameText2 = "";
  
  // Nome do cliente no Quarto relacionamento.
  private String m_custFullNameText3 = "";
  
  // Nome do cliente no Quinto relacionamento.
  private String m_custFullNameText4 = "";
  
  private DataSet m_reltnSpcfClassServPackIndDomain;
  
  /**
   * @return Returns custFullNameText5.
   */
  public String getCustFullNameText5()
  {
    return m_custFullNameText5;
  }
  /**
   * @param custFullNameText5_ Field custFullNameText5 to be setted.
   */
  public void setCustFullNameText5( String custFullNameText5_ )
  {
    m_custFullNameText5 = custFullNameText5_;
  }
  // Nome do cliente no Quinto relacionamento.
  private String m_custFullNameText5 = "";
  
  //Combo de Origem do Cliente
  private DataSet m_reltnPrmtCodeDomain ;
  
  // Descrição da carteira.
  private String m_portfNameText = "";
  
  // Officer da carteira.
  private String m_portfOffcrNbr = "";
  
  // Nome do Officer
  private String m_offcrNameText = "";
  
  private String reltnAddrCellCustNbr_custFullNameText  = "";
  
  private String reltnAddrEmailCustNbr_custFullNameText = "";
  
  private String reltnCustAddrNbr_custFullNameText = "";


  /**
  * @return Returns m_lastUpdateDate.
  */
  public String getLastUpdateDate()
  {
    return m_lastUpdateDate;
  }

  /**
  * @param lastUpdateDate_ Field m_lastUpdateDate to be setted.
  */
  public void setLastUpdateDate( String lastUpdateDate_ )
  {
    m_lastUpdateDate = lastUpdateDate_;
  }

  /**
  * @return Returns m_lastUpdateOpId.
  */
  public String getLastUpdateOpId()
  {
    return m_lastUpdateOpId;
  }

  /**
  * @param lastUpdateOpId_ Field m_lastUpdateOpId to be setted.
  */
  public void setLastUpdateOpId( String lastUpdateOpId_ )
  {
    m_lastUpdateOpId = lastUpdateOpId_;
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
  * @return Returns m_reltnAcctStmtInd.
  */
  public String getReltnAcctStmtInd()
  {
    return m_reltnAcctStmtInd;
  }

  /**
  * @param reltnAcctStmtInd_ Field m_reltnAcctStmtInd to be setted.
  */
  public void setReltnAcctStmtInd( String reltnAcctStmtInd_ )
  {
    m_reltnAcctStmtInd = reltnAcctStmtInd_;
  }

  /**
  * @return Returns m_reltnAddrCellCustNbr.
  */
  public String getReltnAddrCellCustNbr()
  {
    return m_reltnAddrCellCustNbr;
  }

  /**
  * @param reltnAddrCellCustNbr_ Field m_reltnAddrCellCustNbr to be setted.
  */
  public void setReltnAddrCellCustNbr( String reltnAddrCellCustNbr_ )
  {
    m_reltnAddrCellCustNbr = reltnAddrCellCustNbr_;
  }

  /**
  * @return Returns m_reltnAddrCellSeqNbr.
  */
  public String getReltnAddrCellSeqNbr()
  {
    return m_reltnAddrCellSeqNbr;
  }

  /**
  * @param reltnAddrCellSeqNbr_ Field m_reltnAddrCellSeqNbr to be setted.
  */
  public void setReltnAddrCellSeqNbr( String reltnAddrCellSeqNbr_ )
  {
    m_reltnAddrCellSeqNbr = reltnAddrCellSeqNbr_;
  }

  /**
  * @return Returns m_reltnAddrEmailCustNbr.
  */
  public String getReltnAddrEmailCustNbr()
  {
    return m_reltnAddrEmailCustNbr;
  }

  /**
  * @param reltnAddrEmailCustNbr_ Field m_reltnAddrEmailCustNbr to be setted.
  */
  public void setReltnAddrEmailCustNbr( String reltnAddrEmailCustNbr_ )
  {
    m_reltnAddrEmailCustNbr = reltnAddrEmailCustNbr_;
  }

  /**
  * @return Returns m_reltnAddrEmailSeqNbr.
  */
  public String getReltnAddrEmailSeqNbr()
  {
    return m_reltnAddrEmailSeqNbr;
  }

  /**
  * @param reltnAddrEmailSeqNbr_ Field m_reltnAddrEmailSeqNbr to be setted.
  */
  public void setReltnAddrEmailSeqNbr( String reltnAddrEmailSeqNbr_ )
  {
    m_reltnAddrEmailSeqNbr = reltnAddrEmailSeqNbr_;
  }

  /**
  * @return Returns m_reltnCatCode.
  */
  public String getReltnCatCode()
  {
    return m_reltnCatCode;
  }

  /**
  * @param reltnCatCode_ Field m_reltnCatCode to be setted.
  */
  public void setReltnCatCode( String reltnCatCode_ )
  {
    m_reltnCatCode = reltnCatCode_;
  }

  /**
  * @return Returns m_reltnClassCode.
  */
  public String getReltnClassCode()
  {
    return m_reltnClassCode;
  }

  /**
  * @param reltnClassCode_ Field m_reltnClassCode to be setted.
  */
  public void setReltnClassCode( String reltnClassCode_ )
  {
    m_reltnClassCode = reltnClassCode_;
  }

  /**
  * @return Returns m_reltnClassScoreCode.
  */
  public String getReltnClassScoreCode()
  {
    return m_reltnClassScoreCode;
  }

  /**
  * @param reltnClassScoreCode_ Field m_reltnClassScoreCode to be setted.
  */
  public void setReltnClassScoreCode( String reltnClassScoreCode_ )
  {
    m_reltnClassScoreCode = reltnClassScoreCode_;
  }

  /**
  * @return Returns m_reltnCommTypeCode.
  */
  public String getReltnCommTypeCode()
  {
    return m_reltnCommTypeCode;
  }

  /**
  * @param reltnCommTypeCode_ Field m_reltnCommTypeCode to be setted.
  */
  public void setReltnCommTypeCode( String reltnCommTypeCode_ )
  {
    m_reltnCommTypeCode = reltnCommTypeCode_;
  }

  /**
  * @return Returns m_reltnCorpBaseNbr.
  */
  public String getReltnCorpBaseNbr()
  {
    return m_reltnCorpBaseNbr;
  }

  /**
  * @param reltnCorpBaseNbr_ Field m_reltnCorpBaseNbr to be setted.
  */
  public void setReltnCorpBaseNbr( String reltnCorpBaseNbr_ )
  {
    m_reltnCorpBaseNbr = reltnCorpBaseNbr_;
  }

  /**
  * @return Returns m_reltnCust1Nbr.
  */
  public String getReltnCust1Nbr()
  {
    return m_reltnCust1Nbr;
  }

  /**
  * @param reltnCust1Nbr_ Field m_reltnCust1Nbr to be setted.
  */
  public void setReltnCust1Nbr( String reltnCust1Nbr_ )
  {
    m_reltnCust1Nbr = reltnCust1Nbr_;
  }

  /**
  * @return Returns m_reltnCust2Nbr.
  */
  public String getReltnCust2Nbr()
  {
    return m_reltnCust2Nbr;
  }

  /**
  * @param reltnCust2Nbr_ Field m_reltnCust2Nbr to be setted.
  */
  public void setReltnCust2Nbr( String reltnCust2Nbr_ )
  {
    m_reltnCust2Nbr = reltnCust2Nbr_;
  }

  /**
  * @return Returns m_reltnCust3Nbr.
  */
  public String getReltnCust3Nbr()
  {
    return m_reltnCust3Nbr;
  }

  /**
  * @param reltnCust3Nbr_ Field m_reltnCust3Nbr to be setted.
  */
  public void setReltnCust3Nbr( String reltnCust3Nbr_ )
  {
    m_reltnCust3Nbr = reltnCust3Nbr_;
  }

  /**
  * @return Returns m_reltnCust4Nbr.
  */
  public String getReltnCust4Nbr()
  {
    return m_reltnCust4Nbr;
  }

  /**
  * @param reltnCust4Nbr_ Field m_reltnCust4Nbr to be setted.
  */
  public void setReltnCust4Nbr( String reltnCust4Nbr_ )
  {
    m_reltnCust4Nbr = reltnCust4Nbr_;
  }

  /**
  * @return Returns m_reltnCust5Nbr.
  */
  public String getReltnCust5Nbr()
  {
    return m_reltnCust5Nbr;
  }

  /**
  * @param reltnCust5Nbr_ Field m_reltnCust5Nbr to be setted.
  */
  public void setReltnCust5Nbr( String reltnCust5Nbr_ )
  {
    m_reltnCust5Nbr = reltnCust5Nbr_;
  }

  /**
  * @return Returns m_reltnCustAddrNbr.
  */
  public String getReltnCustAddrNbr()
  {
    return m_reltnCustAddrNbr;
  }

  /**
  * @param reltnCustAddrNbr_ Field m_reltnCustAddrNbr to be setted.
  */
  public void setReltnCustAddrNbr( String reltnCustAddrNbr_ )
  {
    m_reltnCustAddrNbr = reltnCustAddrNbr_;
  }

  /**
  * @return Returns m_reltnCustAddrSeqNbr.
  */
  public String getReltnCustAddrSeqNbr()
  {
    return m_reltnCustAddrSeqNbr;
  }

  /**
  * @param reltnCustAddrSeqNbr_ Field m_reltnCustAddrSeqNbr to be setted.
  */
  public void setReltnCustAddrSeqNbr( String reltnCustAddrSeqNbr_ )
  {
    m_reltnCustAddrSeqNbr = reltnCustAddrSeqNbr_;
  }

  /**
  * @return Returns m_reltnEstabDate.
  */
  public String getReltnEstabDate()
  {
    return m_reltnEstabDate;
  }

  /**
  * @param reltnEstabDate_ Field m_reltnEstabDate to be setted.
  */
  public void setReltnEstabDate( String reltnEstabDate_ )
  {
    m_reltnEstabDate = reltnEstabDate_;
  }

  /**
  * @return Returns m_reltnMfStmtInd.
  */
  public String getReltnMfStmtInd()
  {
    return m_reltnMfStmtInd;
  }

  /**
  * @param reltnMfStmtInd_ Field m_reltnMfStmtInd to be setted.
  */
  public void setReltnMfStmtInd( String reltnMfStmtInd_ )
  {
    m_reltnMfStmtInd = reltnMfStmtInd_;
  }

  /**
  * @return Returns m_reltnNbr.
  */
  public String getReltnNbr()
  {
    return m_reltnNbr;
  }

  /**
  * @param reltnNbr_ Field m_reltnNbr to be setted.
  */
  public void setReltnNbr( String reltnNbr_ )
  {
    m_reltnNbr = reltnNbr_;
  }

  /**
  * @return Returns m_reltnPortfCode.
  */
  public String getReltnPortfCode()
  {
    return m_reltnPortfCode;
  }

  /**
  * @param reltnPortfCode_ Field m_reltnPortfCode to be setted.
  */
  public void setReltnPortfCode( String reltnPortfCode_ )
  {
    m_reltnPortfCode = reltnPortfCode_;
  }

  /**
  * @return Returns m_reltnPrmtCode.
  */
  public String getReltnPrmtCode()
  {
    return m_reltnPrmtCode;
  }

  /**
  * @param reltnPrmtCode_ Field m_reltnPrmtCode to be setted.
  */
  public void setReltnPrmtCode( String reltnPrmtCode_ )
  {
    m_reltnPrmtCode = reltnPrmtCode_;
  }

  /**
  * @return Returns m_reltnRiskLevelCode.
  */
  public String getReltnRiskLevelCode()
  {
    return m_reltnRiskLevelCode;
  }

  /**
  * @param reltnRiskLevelCode_ Field m_reltnRiskLevelCode to be setted.
  */
  public void setReltnRiskLevelCode( String reltnRiskLevelCode_ )
  {
    m_reltnRiskLevelCode = reltnRiskLevelCode_;
  }

  /**
  * @return Returns m_reltnSavStmtInd.
  */
  public String getReltnSavStmtInd()
  {
    return m_reltnSavStmtInd;
  }

  /**
  * @param reltnSavStmtInd_ Field m_reltnSavStmtInd to be setted.
  */
  public void setReltnSavStmtInd( String reltnSavStmtInd_ )
  {
    m_reltnSavStmtInd = reltnSavStmtInd_;
  }

  /**
  * @return Returns m_reltnSegCode.
  */
  public String getReltnSegCode()
  {
    return m_reltnSegCode;
  }

  /**
  * @param reltnSegCode_ Field m_reltnSegCode to be setted.
  */
  public void setReltnSegCode( String reltnSegCode_ )
  {
    m_reltnSegCode = reltnSegCode_;
  }

  /**
  * @return Returns m_reltnSpcfClassServPackInd.
  */
  public String getReltnSpcfClassServPackInd()
  {
    return m_reltnSpcfClassServPackInd;
  }

  /**
  * @param reltnSpcfClassServPackInd_ Field m_reltnSpcfClassServPackInd to be setted.
  */
  public void setReltnSpcfClassServPackInd( String reltnSpcfClassServPackInd_ )
  {
    m_reltnSpcfClassServPackInd = reltnSpcfClassServPackInd_;
  }

  /**
  * @return Returns m_reltnStmtOptnCode.
  */
  public String getReltnStmtOptnCode()
  {
    return m_reltnStmtOptnCode;
  }

  /**
  * @param reltnStmtOptnCode_ Field m_reltnStmtOptnCode to be setted.
  */
  public void setReltnStmtOptnCode( String reltnStmtOptnCode_ )
  {
    m_reltnStmtOptnCode = reltnStmtOptnCode_;
  }

  /**
  * @return Returns m_reltnTdStmtInd.
  */
  public String getReltnTdStmtInd()
  {
    return m_reltnTdStmtInd;
  }

  /**
  * @param reltnTdStmtInd_ Field m_reltnTdStmtInd to be setted.
  */
  public void setReltnTdStmtInd( String reltnTdStmtInd_ )
  {
    m_reltnTdStmtInd = reltnTdStmtInd_;
  }

  /**
  * @return Returns m_reltnTypeCode.
  */
  public String getReltnTypeCode()
  {
    return m_reltnTypeCode;
  }

  /**
  * @param reltnTypeCode_ Field m_reltnTypeCode to be setted.
  */
  public void setReltnTypeCode( String reltnTypeCode_ )
  {
    m_reltnTypeCode = reltnTypeCode_;
  }
  /**
   * @return Returns custFullNameText.
   */
  public String getCustFullNameText()
  {
    return m_custFullNameText;
  }
  /**
   * @param custFullNameText_ Field custFullNameText to be setted.
   */
  public void setCustFullNameText( String custFullNameText_ )
  {
    m_custFullNameText = custFullNameText_;
  }
  /**
   * @return Returns custFullNameText1.
   */
  public String getCustFullNameText1()
  {
    return m_custFullNameText1;
  }
  /**
   * @param custFullNameText1_ Field custFullNameText1 to be setted.
   */
  public void setCustFullNameText1( String custFullNameText1_ )
  {
    m_custFullNameText1 = custFullNameText1_;
  }
  /**
   * @return Returns custFullNameText2.
   */
  public String getCustFullNameText2()
  {
    return m_custFullNameText2;
  }
  /**
   * @param custFullNameText2_ Field custFullNameText2 to be setted.
   */
  public void setCustFullNameText2( String custFullNameText2_ )
  {
    m_custFullNameText2 = custFullNameText2_;
  }
  /**
   * @return Returns custFullNameText3.
   */
  public String getCustFullNameText3()
  {
    return m_custFullNameText3;
  }
  /**
   * @param custFullNameText3_ Field custFullNameText3 to be setted.
   */
  public void setCustFullNameText3( String custFullNameText3_ )
  {
    m_custFullNameText3 = custFullNameText3_;
  }
  /**
   * @return Returns custFullNameText4.
   */
  public String getCustFullNameText4()
  {
    return m_custFullNameText4;
  }
  /**
   * @param custFullNameText4_ Field custFullNameText4 to be setted.
   */
  public void setCustFullNameText4( String custFullNameText4_ )
  {
    m_custFullNameText4 = custFullNameText4_;
  }
  /**
   * @return Returns portfNameText.
   */
  public String getPortfNameText()
  {
    return m_portfNameText;
  }
  /**
   * @param portfNameText_ Field portfNameText to be setted.
   */
  public void setPortfNameText( String portfNameText_ )
  {
    m_portfNameText = portfNameText_;
  }
  /**
   * @return Returns portfOffcrNbr.
   */
  public String getPortfOffcrNbr()
  {
    return m_portfOffcrNbr;
  }
  /**
   * @param portfOffcrNbr_ Field portfOffcrNbr to be setted.
   */
  public void setPortfOffcrNbr( String portfOffcrNbr_ )
  {
    m_portfOffcrNbr = portfOffcrNbr_;
  }
  /**
   * @return Returns reltnPrmtCodeDomain.
   */
  public DataSet getReltnPrmtCodeDomain()
  {
    return m_reltnPrmtCodeDomain;
  }
  /**
   * @param reltnPrmtCodeDomain_ Field reltnPrmtCodeDomain to be setted.
   */
  public void setReltnPrmtCodeDomain( DataSet reltnPrmtCodeDomain_ )
  {
    m_reltnPrmtCodeDomain = reltnPrmtCodeDomain_;
  }

  /**
   * @return Returns NUll
   */
  public String getSelectedReltnNbr()
  {
    return m_selectedReltnNbr; 
  }

  /**
   * @param selectedReltnNbr_
   * O metodo seta a varal selecinada na tela no reltnNbr.
   */
  public void setSelectedReltnNbr( String selectedReltnNbr_ )
  {
    m_selectedReltnNbr = selectedReltnNbr_;
  }
  /**
   * @return Returns the reltnAddrCellCustNbr_custFullNameText.
   */
  public String getReltnAddrCellCustNbrCustFullNameText()
  {
    return reltnAddrCellCustNbr_custFullNameText;
  }
  /**
   * @param reltnAddrCellCustNbr_custFullNameText_ The reltnAddrCellCustNbr_custFullNameText to set.
   */
  public void setReltnAddrCellCustNbrCustFullNameText(
                                                       String reltnAddrCellCustNbr_custFullNameText_ )
  {
    reltnAddrCellCustNbr_custFullNameText = reltnAddrCellCustNbr_custFullNameText_;
  }
  /**
   * @return Returns the reltnAddrEmailCustNbr_custFullNameText.
   */
  public String getReltnAddrEmailCustNbrCustFullNameText()
  {
    return reltnAddrEmailCustNbr_custFullNameText;
  }
  /**
   * @param reltnAddrEmailCustNbr_custFullNameText_ The reltnAddrEmailCustNbr_custFullNameText to set.
   */
  public void setReltnAddrEmailCustNbrCustFullNameText(
                                                        String reltnAddrEmailCustNbr_custFullNameText_ )
  {
    reltnAddrEmailCustNbr_custFullNameText = reltnAddrEmailCustNbr_custFullNameText_;
  }
  /**
   * @return Returns the reltnCustAddrNbr_custFullNameText.
   */
  public String getReltnCustAddrNbrCustFullNameText()
  {
    return reltnCustAddrNbr_custFullNameText;
  }
  /**
   * @param reltnCustAddrNbr_custFullNameText_ The reltnCustAddrNbr_custFullNameText to set.
   */
  public void setReltnCustAddrNbrCustFullNameText(
                                                   String reltnCustAddrNbr_custFullNameText_ )
  {
    reltnCustAddrNbr_custFullNameText = reltnCustAddrNbr_custFullNameText_;
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
   * @return Returns reltnSpcfClassServPackIndDomain.
   */
  public DataSet getReltnSpcfClassServPackIndDomain()
  {
    return m_reltnSpcfClassServPackIndDomain;
  }
  /**
   * @param reltnSpcfClassServPackIndDomain_ Field reltnSpcfClassServPackIndDomain to be setted.
   */
  public void setReltnSpcfClassServPackIndDomain(
                                                 DataSet reltnSpcfClassServPackIndDomain_ )
  {
    m_reltnSpcfClassServPackIndDomain = reltnSpcfClassServPackIndDomain_;
  }
}
