package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author m.nakamura
 * 
 * Representação da tabela de Interface de Entrada.
 */
public class BaseTplEntryInterEntityVO extends BaseEntityVO
{
  // Codigo da interface de entrada
  private BigInteger m_entryInterCode = null;

  // Nome da interface
  private String m_entryInterText = "";
 
  // Codigo da interface de entrada selecionada
  private BigInteger m_selectedEntryInterCode = null;

  // Tipo da Interface
  private BigInteger m_entryTypeCode = null;

  // Tipo da Interface selecionada
  private BigInteger m_selectedEntryTypeCode = null;

  // Nome do arquivo de origem
  private String m_origFileName = "";

  // Código do sistema
  private String m_sysCode = "";

  // Segmento do sistema
  private BigInteger m_sysSegCode = null;

  /**
   * Recupera o codigo da interface de entrada.
   * 
   * @return Retorna o codigo da interface de entrada.
   */
  public BigInteger getEntryInterCode()
  {
    return m_entryInterCode;
  }

  /**
   * Seta o codigo da interface de entrada.
   * 
   * @param entryInterCode_ - O codigo da interface de entrada a ser setado.
   */
  public void setEntryInterCode( BigInteger entryInterCode_ )
  {
    m_entryInterCode = entryInterCode_;
  }

  /**
   * Recupera o codigo da interface de entrada selecionada.
   * 
   * @return Retorna o codigo da interface de entrada selecionada.
   */
  public BigInteger getSelectedEntryInterCode()
  {
    return m_selectedEntryInterCode;
  }

  /**
   * Seta o codigo da interface de entrada selecionadas.
   * 
   * @param entryInterCode_ - O codigo da interface de entrada selecionada a ser
   *          setado.
   */
  public void setSelectedEntryInterCode( BigInteger entryInterCode_ )
  {
    m_selectedEntryInterCode = entryInterCode_;
  }

  /**
   * Recupera o nome da interface.
   * 
   * @return Retorna o nome da interface.
   */
  public String getEntryInterText()
  {
    return m_entryInterText;
  }

  /**
   * Seta o nome da interface.
   * 
   * @param entryInterText_ - O nome da interface a ser setado.
   */
  public void setEntryInterText( String entryInterText_ )
  {
    m_entryInterText = entryInterText_;
  }

  /**
   * Recupera o tipo da Interface.
   * 
   * @return Retorna o tipo da Interface.
   */
  public BigInteger getEntryTypeCode()
  {
    return m_entryTypeCode;
  }

  /**
   * Seta o tipo da Interface.
   * 
   * @param entryTypeCode_ - O tipo da Interface a ser setado.
   */
  public void setEntryTypeCode( BigInteger entryTypeCode_ )
  {
    m_entryTypeCode = entryTypeCode_;
  }

  /**
   * Recupera o tipo da Interface selecionada.
   * 
   * @return Retorna o tipo da Interface.
   */
  public BigInteger getSelectedEntryTypeCode()
  {
    return m_selectedEntryTypeCode;
  }

  /**
   * Seta o tipo da Interface selecionada.
   * 
   * @param entryTypeCode_ - O tipo da Interface a ser setado.
   */
  public void setSelectedEntryTypeCode( BigInteger entryTypeCode_ )
  {
    m_selectedEntryTypeCode = entryTypeCode_;
  }

  /**
   * Recupera o nome do arquivo de origem.
   * 
   * @return Retorna o nome do arquivo de origem.
   */
  public String getOrigFileName()
  {
    return m_origFileName;
  }

  /**
   * Seta o nome do arquivo de origem.
   * 
   * @param fileName_ - O nome do arquivo de origem.
   */
  public void setOrigFileName( String origFileName_ )
  {
    m_origFileName = origFileName_;
  }

  /**
   * Recupera o código do sistema.
   * 
   * @return Retorna o código do sistema.
   */
  public String getSysCode()
  {
    return m_sysCode;
  }

  /**
   * Seta o código do sistema.
   * 
   * @param sysCode_ - O código do sistema a ser setado.
   */
  public void setSysCode( String sysCode_ )
  {
    m_sysCode = sysCode_;
  }

  /**
   * Recupera o segmento do sistema.
   * 
   * @return Retorna o segmento do sistema.
   */
  public BigInteger getSysSegCode()
  {
    return m_sysSegCode;
  }

  /**
   * Seta o segmento do sistema.
   * 
   * @param sysSegCode_ - O segmento do sistema a ser setado.
   */
  public void setSysSegCode( BigInteger sysSegCode_ )
  {
    m_sysSegCode = sysSegCode_;
  }
}