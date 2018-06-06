package com.citibank.ods.modules.client.curacctprmntinstr.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplCurAcctPrmntInstrEntity;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject.BaseCurAcctPrmntInstrDetailFncVO;
import com.citibank.ods.modules.client.ipdocprvt.form.IpDocPrvtSearchable;

/**
 * @author michele.monteiro
 *  
 */

public class BaseCurAcctPrmntInstrDetailForm extends BaseForm implements IpDocPrvtSearchable
{

  // Numero do Cliente no CMS
  private String m_custNbrSrc = "";

  // Data e Hora da Ultima atualizacao efetuada pelo usuario.
  private String m_lastUpdDate = "";

  // Codigo do Usuario que efetuou a ultima atualizacao registro.
  private String m_lastUpdUserId = "";

  // Codigo do Documento IP
  private String m_prmntInstrCodeSrc = "";

  // Codigo da Conta Produto. Informacao gerada pela ODS Private para
  // identificar uma operacao ou um contrato
  private String m_prodAcctCode = "";

  // Codigo da sub conta produto. Codigo gerado pela ODS para identificar
  // produtos que tenham subcontas ou subcontratos
  private String m_prodUnderAcctCode = "";

  //Nome do Cliente
  private String m_custFullName = "";

  //Tela destino do bot�o buscar
  private String m_clickedSearch = "";

  //N�mero da conta corrente
  private String m_curAcctNbr = "";

  //N�mero da instru��o permante selecinada no grid
  private String m_selectedIpGrid = "";

  //Descri��o da instru��o permante selecinada no grid
  private String m_selectedIpDescGrid = "";

  //Indicador de dom�nio selecinada no grid
  private String m_selectedIpIndGrid = "";

  //N�mero da conta investimento selecinada no grid
  private String m_selectedInvstCurAcctGrid = "";

  // Domains ipDocDomains
  private String[][] ipDocDomains = null;

  //Indicador de Conta CCI - Combo.
  private DataSet m_prmntInstrInvstCurAcctIndDomain = null;

  //Indicador de Conta CCI.
  private String m_prmntInstrInvstCurAcctInd = "";

  //Descri��o da instru��o permanente
  private String m_prmntInstrText = "";

  //C�digo da instru��o permanente - Hiddem
  private String m_ipDocCodeSrc = "";

  //N�mero da conta CCI
  private String m_invstAcctCurNbr = "";

  // Lista com os �tens a serem exclu�dos
  private String[] m_selectedItemsInGrid = null;

  // Lista com os �tens a serem exclu�dos
  private String[] m_deletedItems = null;

  //Vari�vel de verifica��o se vem da tela de Associa��o Ip x Conta Corrente
  private String m_fromCurAcct = "";

  /**
   * @return m_custNbr. Retorna o n�mero do cliente.
   */
  public String getCustNbrSrc()
  {
    return m_custNbrSrc;
  }

  /**
   * @param custNbr_.Seta o n�mero do cliente.
   */
  public void setCustNbrSrc( String custNbr_ )
  {
    m_custNbrSrc = custNbr_;
  }

  /**
   * @return m_lastUpdDate. Retorna a data da �ltima atualiza��o.
   */
  public String getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_. Seta a data da �ltima atualiza��o.
   */
  public void setLastUpdDate( String lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return m_lastUpdUserId. Retorna o usu�rio da �ltima atualiza��o.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_.Seta o usu�rio da �ltima atualiza��o.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return m_prmntInstrCode. Retorna o n�mero da instru��o permanente.
   */
  public String getPrmntInstrCodeSrc()
  {
    return m_prmntInstrCodeSrc;
  }

  /**
   * @param prmntInstrCode_. Seta o c�digo da instru��o permanente.
   */
  public void setPrmntInstrCodeSrc( String prmntInstrCode_ )
  {
    m_prmntInstrCodeSrc = prmntInstrCode_;
  }

  /**
   * @return m_prodAcctCode. Retorna o n�mero da conta produto.
   */
  public String getProdAcctCode()
  {
    return m_prodAcctCode;
  }

  /**
   * @param prodAcctCode_. Seta o n�mero da conta produto.
   */
  public void setProdAcctCode( String prodAcctCode_ )
  {
    m_prodAcctCode = prodAcctCode_;
  }

  /**
   * @return m_prodUnderAcctCode. Retorna o n�mero da sub conta produto.
   */
  public String getProdUnderAcctCode()
  {
    return m_prodUnderAcctCode;
  }

  /**
   * @param prodUnderAcctCode_.Seta o n�mero da sub conta produto.
   */
  public void setProdUnderAcctCode( String prodUnderAcctCode_ )
  {
    m_prodUnderAcctCode = prodUnderAcctCode_;
  }

  /**
   * @return m_custFullName. Retorna o nome do cliente.
   */
  public String getCustFullName()
  {
    return m_custFullName;
  }

  /**
   * @param custFullName_.Seta o nome do cliente.
   */
  public void setCustFullName( String custFullName_ )
  {
    m_custFullName = custFullName_;
  }

  /**
   * @return clickedSearch.Retorna a URL destino do bot�o buscar.
   */
  public String getClickedSearch()
  {
    return m_clickedSearch;
  }

  /**
   * @param clickedSearch_.Seta a URL destino do bot�o buscar.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
  }

  /**
   * @return m_curAcctNbr.Retorna o n�mero da conta corrente.
   */
  public String getCurAcctNbr()
  {
    return m_curAcctNbr;
  }

  /**
   * @param curAcctNbr_.Seta o n�mero da conta corrente.
   */
  public void setCurAcctNbr( String curAcctNbr_ )
  {
    m_curAcctNbr = curAcctNbr_;
  }

  /**
   * @return m_selectedIpGrid. Retorna o n�mero da instru��o permanente
   *         selecionada no grid.
   */
  public String getSelectedIpGrid()
  {
    return m_selectedIpGrid;
  }

  /**
   * @param selectedIpGrid_. Seta o n�mero da instru��o permanente selecionada
   *          no grid.
   */
  public void setSelectedIpGrid( String selectedIpGrid_ )
  {
    m_selectedIpGrid = selectedIpGrid_;
  }

  /**
   * @return m_ipDocDomains. Retorna um array de string.
   */
  public String[][] getIpDocDomains()
  {
    return ipDocDomains;
  }

  /**
   * @param ipDocDomains_.Seta um array de string.
   */
  public void setIpDocDomains( String[][] ipDocDomains_ )
  {
    ipDocDomains = ipDocDomains_;
  }

  /**
   * @return m_prmntInstrInvstCurAcctInd. Retorna o indicador de conta CCI.
   */
  public String getPrmntInstrInvstCurAcctInd()
  {
    return m_prmntInstrInvstCurAcctInd;
  }

  /**
   * @param prmntInstrInvstCurAcctInd_.Seta o indicador de conta CCI
   */
  public void setPrmntInstrInvstCurAcctInd( String prmntInstrInvstCurAcctInd_ )
  {
    m_prmntInstrInvstCurAcctInd = prmntInstrInvstCurAcctInd_;
  }

  /**
   * @return m_prmntInstrInvstCurAcctIndDomain. Seta o dataSet com Sim/N�o.
   */
  public DataSet getPrmntInstrInvstCurAcctIndDomain()
  {
    return m_prmntInstrInvstCurAcctIndDomain;
  }

  /**
   * @param prmntInstrInvstCurAcctIndDomain_.Retorna o valores populados no
   *          dataSet de indicador de conta CCI.
   */
  public void setPrmntInstrInvstCurAcctIndDomain(
                                                 DataSet prmntInstrInvstCurAcctIndDomain_ )
  {
    m_prmntInstrInvstCurAcctIndDomain = prmntInstrInvstCurAcctIndDomain_;
  }

  /**
   * @return m_prmntInstrText. Retorna a descri��o da instru��o permanente.
   */
  public String getPrmntInstrText()
  {
    return m_prmntInstrText;
  }

  /**
   * @param prmntInstrText_.Seta a descri��o da instru��o permanente.
   */
  public void setPrmntInstrText( String prmntInstrText_ )
  {
    m_prmntInstrText = prmntInstrText_;
  }

  /**
   * 
   * M�todo da interface CurAccountDetailable.
   */
  public String getSelectedCurAcctNbr()
  {
    return null;
  }

  /**
   * 
   * M�todo da interface CurAccountDetailable.
   */
  public String getSelectedProdAcctCode()
  {

    return null;
  }

  /**
   * 
   * M�todo da interface CurAccountDetailable.
   */
  public String getSelectedProdUnderAcctCode()
  {

    return null;
  }

  /**
   * 
   * M�todo da interface CurAccountDetailable.
   */
  public void setSelectedCurAcctNbr( String curAcctNbr_ )
  {
    setCurAcctNbr( curAcctNbr_ );
  }

  /**
   * 
   * M�todo da interface CurAccountDetailable.
   */
  public void setSelectedProdAcctCode( String selectedProdAcctCode_ )
  {

    setProdAcctCode( selectedProdAcctCode_ );
  }

  /**
   * 
   * M�todo da interface CurAccountDetailable.
   */
  public void setSelectedProdUnderAcctCode( String selectedProdUnderAcctCode_ )
  {

    setProdUnderAcctCode( selectedProdUnderAcctCode_ );
  }

  /**
   * 
   * M�todo da interface IpDocPrvtSearchable
   */
  public String getSelectedPrmntInstrCodeSrc()
  {

    return null;
  }

  /**
   * 
   * M�todo da interface IpDocPrvtSearchable
   */
  public void setSelectedPrmntInstrCodeSrc( String prmntInstrCode_ )
  {
    setPrmntInstrCodeSrc( prmntInstrCode_ );
  }

  /**
   * @return m_ipDocCodeSrc. Retorna o c�digo da instru��o permanente - Hidden
   */
  public String getIpDocCodeSrc()
  {
    return m_ipDocCodeSrc;
  }

  /**
   * @param ipDocCodeSrc_.Seta o c�digo da instru��o permanente - Hidden.
   */
  public void setIpDocCodeSrc( String ipDocCodeSrc_ )
  {
    m_ipDocCodeSrc = ipDocCodeSrc_;
  }

  /**
   * @return m_invstAcctCurNbr. Retorna o n�mero da conta investimento.
   */
  public String getInvstAcctCurNbr()
  {
    return m_invstAcctCurNbr;
  }

  /**
   * @param invstAcctCurNbr_.Seta o n�mero da conta investimento.
   */
  public void setInvstAcctCurNbr( String invstAcctCurNbr_ )
  {
    m_invstAcctCurNbr = invstAcctCurNbr_;
  }

  /**
   * @return Returns selectedInvstCurAcctGrid.
   */
  public String getSelectedInvstCurAcctGrid()
  {
    return m_selectedInvstCurAcctGrid;
  }

  /**
   * @param selectedInvstCurAcctGrid_ Field selectedInvstCurAcctGrid to be
   *          setted.
   */
  public void setSelectedInvstCurAcctGrid( String selectedInvstCurAcctGrid_ )
  {
    m_selectedInvstCurAcctGrid = selectedInvstCurAcctGrid_;
  }

  /**
   * @return Returns selectedIpDescGrid.
   */
  public String getSelectedIpDescGrid()
  {
    return m_selectedIpDescGrid;
  }

  /**
   * @param selectedIpDescGrid_ Field selectedIpDescGrid to be setted.
   */
  public void setSelectedIpDescGrid( String selectedIpDescGrid_ )
  {
    m_selectedIpDescGrid = selectedIpDescGrid_;
  }

  /**
   * @return Returns selectedIpIndGrid.
   */
  public String getSelectedIpIndGrid()
  {
    return m_selectedIpIndGrid;
  }

  /**
   * @param selectedIpIndGrid_ Field selectedIpIndGrid to be setted.
   */
  public void setSelectedIpIndGrid( String selectedIpIndGrid_ )
  {
    m_selectedIpIndGrid = selectedIpIndGrid_;
  }

  /**
   * @return Returns selectedItemsInGrid.
   */
  public String[] getSelectedItemsInGrid()
  {
    return m_selectedItemsInGrid;
  }

  /**
   * @param selectedItemsInGrid_ Field selectedItemsInGrid to be setted.
   */
  public void setSelectedItemsInGrid( String[] selectedItemsInGrid_ )
  {
    m_selectedItemsInGrid = selectedItemsInGrid_;
  }

  /**
   * @return Returns deletedItems.
   */
  public String[] getDeletedItems()
  {
    return m_deletedItems;
  }

  /**
   * @param deletedItems_ Field deletedItems to be setted.
   */
  public void setDeletedItems( String[] deletedItems_ )
  {
    m_deletedItems = deletedItems_;
  }

  /**
   * @return Returns m_fromCurAcct.
   */
  public String getFromCurAcct()
  {
    return m_fromCurAcct;
  }

  /**
   * @param fromCurAcct_ Field fromCurAcct_ to be setted.
   */
  public void setFromCurAcct( String fromCurAcct_ )
  {
    m_fromCurAcct = fromCurAcct_;
  }

  /**
   * Realiza as valida��es de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength(
                                    BaseCurAcctPrmntInstrDetailFncVO.C_PRMNT_INSTR_CODE,
                                    m_prmntInstrCodeSrc,
                                    BaseTplCurAcctPrmntInstrEntity.C_PRMNT_INSTR_CODE,
                                    errors );

    return errors;
  }

  public void reset( ActionMapping arg0_, HttpServletRequest arg1_ )
  {
    if ( m_selectedItemsInGrid != null )
    {
      for ( int i = 0; i < m_selectedItemsInGrid.length; i++ )
      {
        m_selectedItemsInGrid[ i ] = "N";
      }
    }

    if ( m_deletedItems != null )
    {
      for ( int i = 0; i < m_deletedItems.length; i++ )
      {
        m_deletedItems[ i ] = "N";
      }
    }

  }
}