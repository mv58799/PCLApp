package com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.BaseTplContactCustEntity;
import com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity;

/**
 * @author m.nakamura
 * 
 * Agregador da entidade que representa a tabela memória de risco.
 */
public class BaseMrDocPrvtDetailFncVO extends BaseODSFncVO
{
  /**
  * Constante de descrição do campo de Conta Corrente 
  */
  public static final String C_CUR_ACCT_NBR = "Conta Corrente";
  
  public static final String C_INVEST_CUR_ACCT_NBR = "Conta CCI";
  
  // Entidade que agrega a tabela de Conta de Produtos
  protected BaseTo3ProductAccountEntity m_to3ProductAccountEntity = null;

  // Entidade que agrega a tabela de Memória de Risco
  protected BaseTplMrDocPrvtEntity m_tplMrDocPrvtEntity = null;
  
  //Entidade que agrega a tabela de Contatos
  protected BaseTplContactCustEntity m_tplContactCustEntity = null; 

  // Número do contato da busca
  private BigInteger m_ctcNbr = null;

  // Nome do contato da busca
  private String m_fullNameText = "";

  // Número do contato selecionado no grid
  private BigInteger m_associatedCtcNbr = null;

  // Lista com as contatos inseridos.
  protected List m_insertedContactCust;

  // Lista com as contatos deletados.
  protected List m_deletedContactCust;

  // Indicador que indica se a tela foi carregada de uma tela de busca.
  private boolean m_isFromSearch;
  
  //Indicador que indica se a já existe um MR ativo na current.
  private boolean m_isMrActive;
  
  //Indicador que confirma o cancelamento do MR ativo na current.
  private boolean m_isConfirmCancel;

  // Indicador de Conta Investimento - Combo
  private DataSet m_mrInvstCurAcctIndDomain = null;

  // Url da tela destino - Implementação botão buscar
  private String m_clickedSearch = "";

  //Constante da descrição do memo de risco
  public static final String C_MR_DOC_TEXT = "Descrição do Documento MR";

  //Constante do número do contato
  public static final String C_CTC_NBR = "Número do Contato";

  // Nome do cliente da busca
  private String m_custFullNameText = "";

  //variável de controle quando a funcionalidade é chamada a partir de conta
  // corrente.
  private String m_fromCurAccount = "";

  //Lista de itens selecionados no grid
  ArrayList m_selectedItemsInGrid = new ArrayList();
  
  //Lista de itens selecionados no grid
  ArrayList m_deletedItems = new ArrayList();
  
  private boolean m_insertContact = false;

  /**
   * Recupera a entidade que representa a tabela memória de risco.
   * 
   * @return Retorna a entidade que representa a tabela memória de risco.
   */
  public BaseTplMrDocPrvtEntity getTplMrDocPrvtEntity()
  {
    return m_tplMrDocPrvtEntity;
  }

  /**
   * Seta a entidade que representa a tabela memória de risco.
   * 
   * @param tplMrDocPrvtEntity_ - A entidade que representa a tabela memória de
   *          risco.
   */
  public void setTplMrDocPrvtEntity( BaseTplMrDocPrvtEntity tplMrDocPrvtEntity_ )
  {
    m_tplMrDocPrvtEntity = tplMrDocPrvtEntity_;
  }

  /**
   * Recupera a entidade que representa a tabela Conta de Produto.
   * 
   * @return Retorna a entidade que representa a tabela Conta de Produto.
   */
  public BaseTo3ProductAccountEntity getTo3ProductAccountEntity()
  {
    return m_to3ProductAccountEntity;
  }

  /**
   * Seta a entidade que representa a tabela Conta de Produto.
   * 
   * @param to3ProductAccountEntity_ - A entidade que representa a tabela Conta
   *          de Produto.
   */
  public void setTo3ProductAccountEntity(
                                         BaseTo3ProductAccountEntity to3ProductAccountEntity_ )
  {
    m_to3ProductAccountEntity = to3ProductAccountEntity_;
  }

  /**
   * Recupera o Número do Contato
   * 
   * @return Retorna o Número do Contato
   */
  public BigInteger getCtcNbr()
  {
    return m_ctcNbr;
  }

  /**
   * Seta o Número do Contato
   * 
   * @param ctcNbr_ - O Número do Contato
   */
  public void setCtcNbr( BigInteger ctcNbr_ )
  {
    m_ctcNbr = ctcNbr_;
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
   * Recupera a lista com os contratos inseridos em memória (ainda não
   * persistidas).
   * 
   * @return List - A lista com os contratos inseridos em memória (ainda não
   *         persistidas).
   */
  public List getInsertedContactCust()
  {
    return m_insertedContactCust;
  }

  /**
   * Reseta a lista com os contratos inseridos em memória (ainda não
   * persistidas).
   */
  public void resetInsertedContactCust()
  {
    if ( m_insertedContactCust != null )
    {
      m_insertedContactCust.clear();
    }
  }

  /**
   * Recupera a lista com os contatos deletados em memória (ainda não
   * persistidas).
   * 
   * @return List - A lista com os contatos deletados em memória (ainda não
   *         persistidas).
   */
  public List getDeletedContactCust()
  {
    return m_deletedContactCust;
  }

  /**
   * Reseta a lista com os contratos deletados em memória (ainda não
   * persistidas).
   */
  public void resetDeletedContactCust()
  {
    if ( m_deletedContactCust != null )
    {
      m_deletedContactCust.clear();
    }
  }

  /**
   * Recupera o Número do Contato selecionado no grid.
   * 
   * @return Retorna o Número do Contato selecionado no grid.
   */
  public BigInteger getAssociatedCtcNbr()
  {
    return m_associatedCtcNbr;
  }

  /**
   * Seta o Número do Contato selecionado no grid
   * 
   * @param associatedCtcNbr_ - O Número do Contato selecionado no grid
   */
  public void setAssociatedCtcNbr( BigInteger associatedCtcNbr_ )
  {
    m_associatedCtcNbr = associatedCtcNbr_;
  }

  /**
   * Verifica se a tela foi carregada de uma tela de busca.
   * 
   * @return true se a tela foi carregada de uma tela de busca, false caso
   *         contrário.
   */
  public boolean isFromSearch()
  {
    return m_isFromSearch;
  }

  /**
   * Seta o indicador que indica se a tela foi carregada de uma tela de busca.
   * 
   * @param isFromSearch_ - true se a tela foi carregada de uma tela de busca,
   *          false caso contrário.
   */
  public void setFromSearch( boolean isFromSearch_ )
  {
    m_isFromSearch = isFromSearch_;
  }

  /**
   * @return Retorna as opções do indicador de investimennto.
   */
  public DataSet getMrInvstCurAcctIndDomain()
  {
    return m_mrInvstCurAcctIndDomain;
  }

  /**
   * @param mrInvstCurAcctIndDomain_.Seta no combo os indicadores de
   *          investimento.
   */
  public void setMrInvstCurAcctIndDomain( DataSet mrInvstCurAcctIndDomain_ )
  {
    m_mrInvstCurAcctIndDomain = mrInvstCurAcctIndDomain_;
  }

  /**
   * @return Retorna a action destino do botão buscar.
   */
  public String getClickedSearch()
  {
    return m_clickedSearch;
  }

  /**
   * @param clickedSearch_. Seta a url destino do botão buscar.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
  }

  /**
   * @return Retorna o nome do cliente.
   */
  public String getCustFullNameText()
  {
    return m_custFullNameText;
  }

  /**
   * @param custFullNameText_.Seta o nome do cliente.
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

  public ArrayList getSelectedItemsInGrid()
  {
    return m_selectedItemsInGrid;
  }

  public void setSelectedItemsInGrid( ArrayList selectedItemsInGrid_ )
  {
    m_selectedItemsInGrid = selectedItemsInGrid_;
  }
 
  

  /**
   * @return Returns deletedItems.
   */
  public ArrayList getDeletedItems()
  {
    return m_deletedItems;
  }
  /**
   * @param deletedItems_ Field deletedItems to be setted.
   */
  public void setDeletedItems( ArrayList deletedItems_ )
  {
    m_deletedItems = deletedItems_;
  }
  
  
  /**
   * @return Returns insertContact.
   */
  public boolean isInsertContact()
  {
    return m_insertContact;
  }
  /**
   * @param insertContact_ Field insertContact to be setted.
   */
  public void setInsertContact( boolean insertContact_ )
  {
    m_insertContact = insertContact_;
  }
  /**
   * @return
   */
  public BaseTplContactCustEntity getTplContactCustEntity() {
	return m_tplContactCustEntity;
  }

  /**
   * @param entity
   */
  public void setTplContactCustEntity(BaseTplContactCustEntity m_tplContactCustEntity_) {
	m_tplContactCustEntity = m_tplContactCustEntity_;
  }

/**
 * @return
 */
  public boolean isMrActive() {
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
  public boolean isConfirmCancel() {
	  return m_isConfirmCancel;
  }

  /**
   * @param b
   */
  public void setIsConfirmCancel(boolean m_isConfirmCancel_) {
	  m_isConfirmCancel = m_isConfirmCancel_;
  }


}