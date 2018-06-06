package com.citibank.ods.modules.client.mrdocprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity;
import com.citibank.ods.modules.client.contactcust.form.ContactCustSearchable;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.BaseMrDocPrvtDetailFncVO;

/**
 * @author m.nakamura
 * 
 * Form com os dados da tela de detalhe de Mem�ria de Risco
 */
public class BaseMrDocPrvtDetailForm extends BaseForm implements
     ContactCustSearchable
{

  // Data e Hora da Ultima Atualizacao Efetuada pelo Usuario.
  private String m_lastUpdDate = "";

  // Codigo do Usuario que Efetuou a Ultima Atualizacao no Registro.
  private String m_lastUpdUserId = "";

  // Descricao da Mem�ria de Risco
  private String m_mrDocText = "";

  // Indicador de Utilizacao de Conta CCI: 'S' (Sim), 'N' (Nao)
  private String m_mrInvstCurAcctInd = "";
  
  private String m_invstCurAcctNbrSrc = "";

  // Codigo da Conta Produto
  private String m_prodAcctCode = "";

  // Codigo da Sub-conta Produto
  private String m_prodUnderAcctCode = "";

  // N�mero do contato da busca
  private String m_ctcNbrSrc = "";

  // N�mero do contato selecionado no grid
  private String m_associatedCtcNbr = "";

  // Nome do contato
  private String m_fullNameText = "";
  
  private String m_fullNameText_2 = "";
  
  private String m_fullNameText_3 = "";

  // Vetor com os nomes dos contatos do cliente
  private String[] m_fullNameTextArray;
  
  private String[] m_fullName_2_TextArray;
  
  private String[] m_fullName_3_TextArray;

  // Vetor com os n�meros dos contatos dos clientes
  private String[] m_ctcNbrArray;

  //N�mero da conta corrente
  private String m_curAcctNbrSrc = "";

  //Indicador de conta CCI _ Combo
  private DataSet m_mrInvstCurAcctIndDomain = null;

  // Url da tela destino - Implementa��o bot�o buscar
  private String m_clickedSearch = "";

  //N�mero do cliente
  private String m_custNbrSrc = "";

  // Nome do cliente
  private String m_custFullNameText = "";

  //Vari�vel de controle - Chamada da tela de
  private String m_fromCurAccount = "";
  
  private String m_custCurAcctNbrSrc;

  //Lista com os �tens inseridos
  private String[] m_selectedItemsInGrid = null;
  
  //Lista com os �tens a serem exclu�dos
  private String[] m_deletedItems = null;
  
  // Vetor com o n�mero de telefone do cliente
  private String[] m_phoneNbrArray;
  
  // Vetor com o n�mero do ramal do cliente
  private String[] m_phoneExtNbrArray;
  
  // Vetor com o DDD do telefone do cliente
  private String[] m_phoneDddCodeArray;
  
  private String phoneDddCode = "";
  
  private String phoneNbr = "";
  
  private String phoneExtnNbr = "";
  
  //Indicador que indica se a j� existe um MR ativo na current.
  private boolean m_isMrActive;
  
  //Indicador que confirma o cancelamento do MR ativo na current.
  private boolean m_isConfirmCancel;

  /**
   * Recupera a Data e Hora da Ultima Atualizacao Efetuada pelo Usuario.
   * 
   * @return Retorna a Data e Hora da Ultima Atualizacao Efetuada pelo Usuario.
   */
  public String getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * Seta a Data e Hora da Ultima Atualizacao Efetuada pelo Usuario.
   * 
   * @param lastUpdDate_ - A Data e Hora da Ultima Atualizacao Efetuada pelo
   *          Usuario.
   */
  public void setLastUpdDate( String lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * Recupera o Codigo do Usuario que Efetuou a Ultima Atualizacao no Registro.
   * 
   * @return Retorna o Codigo do Usuario que Efetuou a Ultima Atualizacao no
   *         Registro.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * Seta o Codigo do Usuario que Efetuou a Ultima Atualizacao no Registro.
   * 
   * @param lastUpdUserId_ - O Codigo do Usuario que Efetuou a Ultima
   *          Atualizacao no Registro.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * Recupera a Descricao do Instrucao Permanente
   * 
   * @return Retorna a Descricao do Instrucao Permanente
   */
  public String getMrDocText()
  {
    return m_mrDocText;
  }

  /**
   * Seta a Descricao do Instrucao Permanente
   * 
   * @param mrDocText_ - A Descricao do Instrucao Permanente
   */
  public void setMrDocText( String mrDocText_ )
  {
    m_mrDocText = mrDocText_;
  }

  /**
   * Recupera o Indicador de Utilizacao de Conta CCI
   * 
   * @return Retorna o Indicador de Utilizacao de Conta CCI
   */
  public String getMrInvstCurAcctInd()
  {
    return m_mrInvstCurAcctInd;
  }

  /**
   * Seta o Indicador de Utilizacao de Conta CCI
   * 
   * @param mrInvstCurAcctInd_ - O Indicador de Utilizacao de Conta CCI
   */
  public void setMrInvstCurAcctInd( String mrInvstCurAcctInd_ )
  {
    m_mrInvstCurAcctInd = mrInvstCurAcctInd_;
  }

  /**
   * Retorna o Codigo da Conta Produto
   * 
   * @return Retorna o Codigo da Conta Produto
   */
  public String getProdAcctCode()
  {
    return m_prodAcctCode;
  }

  /**
   * Seta o Codigo da Conta Produto
   * 
   * @param prodAcctCode_ - o Codigo da Conta Produto
   */
  public void setProdAcctCode( String prodAcctCode_ )
  {
    m_prodAcctCode = prodAcctCode_;
  }

  /**
   * Recupera o Codigo da Sub-conta Produto
   * 
   * @return Retorna o Codigo da Sub-conta Produto
   */
  public String getProdUnderAcctCode()
  {
    return m_prodUnderAcctCode;
  }

  /**
   * Seta o Codigo da Sub-conta Produto
   * 
   * @param prodUnderAcctCode_ - O Codigo da Sub-conta Produto
   */
  public void setProdUnderAcctCode( String prodUnderAcctCode_ )
  {
    m_prodUnderAcctCode = prodUnderAcctCode_;
  }

  /**
   * Recupera o N�mero do Contato
   * 
   * @return Retorna o N�mero do Contato
   */
  public String getCtcNbrSrc()
  {
    return m_ctcNbrSrc;
  }

  /**
   * Seta o N�mero do Contato
   * 
   * @param ctcNbr_ - O N�mero do Contato
   */
  public void setCtcNbrSrc( String ctcNbrSrc_ )
  {
    m_ctcNbrSrc = ctcNbrSrc_;
  }

  /**
   * Recupera o Nome do Contato
   * 
   * @return Retorna o Nome do Contato
   */
  public String getFullNameText()
  {
    return m_fullNameText;
  }

  /**
   * Seta o Nome do Contato
   * 
   * @param fullNameText_ - O Nome do Contato
   */
  public void setFullNameText( String fullNameText_ )
  {
    m_fullNameText = fullNameText_;
  }

  /**
   * Retorna o vetor com os nomes dos contatos do cliente.
   * 
   * @return String[] - Vetor com os nomes dos contatos do cliente.
   */
  public String[] getFullNameTextArray()
  {
    return m_fullNameTextArray;
  }

  /**
   * Seta o vetor com os nomes dos contatos do cliente.
   * 
   * @param fullNameTextArray_ - Vetor com os nomes dos contatos do cliente.
   */
  public void setFullNameTextArray( String[] fullNameTextArray_ )
  {
    m_fullNameTextArray = fullNameTextArray_;
  }

  /**
   * Retorna o vetor com os n�meros dos contatos do cliente.
   * 
   * @return String[] - Vetor com os n�meros dos contatos do cliente.
   */
  public String[] getCtcNbrArray()
  {
    return m_ctcNbrArray;
  }

  /**
   * Seta o vetor com os n�meros dos contatos do cliente.
   * 
   * @param ctcNbrArray_ - Vetor com os n�meros dos contatos do cliente.
   */
  public void setCtcNbrArray( String[] ctcNbrArray_ )
  {
    m_ctcNbrArray = ctcNbrArray_;
  }

  /**
   * Recupera o N�mero do Contato selecionado no grid.
   * 
   * @return Retorna o N�mero do Contato selecionado no grid.
   */
  public String getAssociatedCtcNbr()
  {
    return m_associatedCtcNbr;
  }

  /**
   * Seta o N�mero do Contato selecionado no grid
   * 
   * @param associatedCtcNbr_ - O N�mero do Contato selecionado no grid
   */
  public void setAssociatedCtcNbr( String associatedCtcNbr_ )
  {
    m_associatedCtcNbr = associatedCtcNbr_;
  }

  /**
   * @return Retorna o n�mero da conta corrente.
   */
  public String getCurAcctNbrSrc()
  {
    return m_curAcctNbrSrc;
  }

  /**
   * @param curAcctNbr_. Seta o n�mero da conta corrente.
   */
  public void setCurAcctNbrSrc( String curAcctNbr_ )
  {
    m_curAcctNbrSrc = curAcctNbr_;
  }

  /**
   * @return Retorna o combo com as op��es sim e n�o.
   */
  public DataSet getMrInvstCurAcctIndDomain()
  {
    return m_mrInvstCurAcctIndDomain;
  }

  /**
   * @param mrInvstCurAcctIndDomain_. Seta as op��es de valores no combo.
   */
  public void setMrInvstCurAcctIndDomain( DataSet mrInvstCurAcctIndDomain_ )
  {
    m_mrInvstCurAcctIndDomain = mrInvstCurAcctIndDomain_;
  }

  /**
   * @return Retorna a action destino do bot�o buscar.
   */
  public String getClickedSearch()
  {
    return m_clickedSearch;
  }

  /**
   * @param clickedSearch_. Seta a url destino do bot�o buscar.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
  }

  /**
   * M�todo da interface CurAccountSearchable.
   */
  public String getSelectedCurAcctNbrSrc()
  {

    return null;
  }

  /**
   * M�todo da interface CurAccountSearchable.
   */
  public String getSelectedProdAcctCodeSrc()
  {

    return null;
  }

  /**
   * M�todo da interface CurAccountSearchable.
   */
  public String getSelectedProdUnderAcctCodeSrc()
  {

    return null;
  }

  /**
   * M�todo da interface CurAccountSearchable.Seta o n�mero da conta corrente
   * selecionada.
   */
  public void setSelectedCurAcctNbrSrc( String curAcctNbr_ )
  {
    setCurAcctNbrSrc( curAcctNbr_ );
  }

  /**
   * M�todo da interface CurAccountSearchable.Seta o n�mero da conta produto
   * selecionada.
   */
  public void setSelectedProdAcctCodeSrc( String prodAcctCode_ )
  {
    setProdAcctCode( prodAcctCode_ );
  }

  /**
   * M�todo da interface CurAccountSearchable.Seta o n�mero da sub conta produto
   * selecionada.
   */
  public void setSelectedProdUnderAcctCodeSrc( String prodUnderAcctCode_ )
  {
    setProdUnderAcctCode( prodUnderAcctCode_ );
  }

  /**
   * M�todo da interface ContactCustSearchable.
   */
  public String getSelectedCtcNbrSrc()
  {
    return null;
  }

  /**
   * M�todo da interface ContactCustSearchable.Seta o n�mero do contato
   * selecionado no grid no filtro de pesquisa.
   */
  public void setSelectedCtcNbrSrc( String ctcNbrSrc_ )
  {
    setCtcNbrSrc( ctcNbrSrc_ );

  }

  /**
   * @return Retorna o n�mero do cliente.
   */
  public String getCustNbrSrc()
  {
    return m_custNbrSrc;
  }

  /**
   * @param custNbr_.Seta o n�mero do cliente
   */
  public void setCustNbrSrc( String custNbr_ )
  {
    m_custNbrSrc = custNbr_;
  }

  /**
   * @return Retorna o nome do cliente.
   */
  public String getCustFullNameText()
  {
    return m_custFullNameText;
  }

  /**
   * @param custFullNameText_.Seta o nome do cliente
   */
  public void setCustFullNameText( String custFullNameText_ )
  {
    m_custFullNameText = custFullNameText_;
  }

  /**
   * @return Returns fromCurAccount.
   */
  public String getFromCurAccount()
  {
    return m_fromCurAccount;
  }

  /**
   * @param fromCurAccount_ Field fromCurAccount to be setted.
   */
  public void setFromCurAccount( String fromCurAccount_ )
  {
    m_fromCurAccount = fromCurAccount_;
  }

  /**
   * Realiza as valida��es de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength( BaseMrDocPrvtDetailFncVO.C_MR_DOC_TEXT,
                                    m_mrDocText,
                                    BaseTplMrDocPrvtEntity.C_MR_DOC_TEXT_SIZE,
                                    errors );
    return errors;
  }

  public String[] getSelectedItemsInGrid()
  {
    return m_selectedItemsInGrid;
  }

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
   * @return Returns phoneDddCodeArray.
   */
  public String[] getPhoneDddCodeArray()
  {
    return m_phoneDddCodeArray;
  }
  /**
   * @param phoneDddCodeArray_ Field phoneDddCodeArray to be setted.
   */
  public void setPhoneDddCodeArray( String[] phoneDddCodeArray_ )
  {
    m_phoneDddCodeArray = phoneDddCodeArray_;
  }
  /**
   * @return Returns phoneExtNbrArray.
   */
  public String[] getPhoneExtNbrArray()
  {
    return m_phoneExtNbrArray;
  }
  /**
   * @param phoneExtNbrArray_ Field phoneExtNbrArray to be setted.
   */
  public void setPhoneExtNbrArray( String[] phoneExtNbrArray_ )
  {
    m_phoneExtNbrArray = phoneExtNbrArray_;
  }
  /**
   * @return Returns phoneNbrArray.
   */
  public String[] getPhoneNbrArray()
  {
    return m_phoneNbrArray;
  }
  
  /**
   * @param phoneExtNbrArray_ Field phoneExtNbrArray to be setted.
   */
  public void setPhoneNbrArray( String[] phoneNbrArray_ )
  {
    m_phoneNbrArray = phoneNbrArray_;
  }
  
  /*
   * (non-Javadoc)
   * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
   *      javax.servlet.http.HttpServletRequest)
   */
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
      for ( int j = 0; j < m_deletedItems.length; j++ )
      {
        m_deletedItems[ j ] = "N";
      }
    }

  }
  
  /**
   * @return
   */
  public String getInvstCurAcctNbrSrc() {
	return m_invstCurAcctNbrSrc;
  }

  /**
   * @param string
   */
  public void setInvstCurAcctNbrSrc(String m_invstCurAcctNbrSrc_) {
	m_invstCurAcctNbrSrc = m_invstCurAcctNbrSrc_;
  }

  /**
   * @return
   */	
  public String getFullNameText_2() {
	return m_fullNameText_2;
  }

/**
 * @return
 */
  public String getFullNameText_3() {
	return m_fullNameText_3;
  }

  /**
 * @return
 */
  public String getPhoneDddCode() {
	return phoneDddCode;
  }

/**
 * @return
 */
  public String getPhoneExtnNbr() {
	return phoneExtnNbr;
  }

/**
 * @return
 */
  public String getPhoneNbr() {
	return phoneNbr;
  }

/**
 * @param string
 */
  public void setFullNameText_2(String m_fullNameText_2_) {
	m_fullNameText_2 = m_fullNameText_2_;
  }

/**
 * @param string
 */
  public void setFullNameText_3(String m_fullNameText_3_) {
	m_fullNameText_3 = m_fullNameText_3_;
  }

/**
 * @param string
 */
  public void setPhoneDddCode(String phoneDddCode_) {
	phoneDddCode = phoneDddCode_;
  }

/**
 * @param string
 */
  public void setPhoneExtnNbr(String phoneExtnNbr_) {
	phoneExtnNbr = phoneExtnNbr_;
  }

/**
 * @param string
 */
  public void setPhoneNbr(String phoneNbr_) {
	phoneNbr = phoneNbr_;
  }

/**
 * @return
 */
  public String[] getFullName_2_TextArray() {
	return m_fullName_2_TextArray;
  }

/**
 * @param strings
 */
  public void setFullName_2_TextArray(String[] m_fullName_2_TextArray_) {
	m_fullName_2_TextArray = m_fullName_2_TextArray_;
  }

/**
 * @return
 */
  public String[] getFullName_3_TextArray() {
	return m_fullName_3_TextArray;
  }

/**
 * @param strings
 */
  public void setFullName_3_TextArray(String[] m_fullName_3_TextArray_) {
	m_fullName_3_TextArray = m_fullName_3_TextArray_;
  }

/**
 * @return
 */
  public boolean getIsMrActive() {
	return m_isMrActive;
  }

/**
 * @param b
 */
  public void setMrActive(boolean m_isMrActive_) {
	m_isMrActive = m_isMrActive_;
  }

/**
 * @return
 */
  public boolean getIsConfirmCancel() {
	return m_isConfirmCancel;
  }

/**
 * @param b
 */
  public void setIsConfirmCancel(boolean m_isConfirmCancel_) {
	m_isConfirmCancel = m_isConfirmCancel_;
  }

/**
 * @return
 */
  public String getCustCurAcctNbrSrc() {
	return m_custCurAcctNbrSrc;
  }

/**
 * @param string
 */
  public void setCustCurAcctNbrSrc(String m_custCurAcctNbrSrc_) {
	m_custCurAcctNbrSrc = m_custCurAcctNbrSrc_;
  }

}