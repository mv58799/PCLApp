package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.form.BaseForm;

/**
 * @author m.nakamura
 * 
 * Representa��o da tabela de Interface de Entrada de Atributos.
 */
public class BaseTplEntryInterColumnEntityVO extends BaseForm
{
  // Codigo da interface de entrada
  private BigInteger m_entryInterCode = null;

  // Nome da coluna
  private String m_colName = "";

  // Nome da coluna selecionada
  private String m_selectedColName = "";

  // Descri��o da coluna
  private String m_colText = "";

  // C�digo do tipo de dados da coluna
  private String m_colDataTypeCode = "";

  // Tamanho da coluna
  private BigInteger m_colSize = null;

  // Precis�o da coluna.
  private BigInteger m_colPrcsnNbr = null;

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
   * Recupera o nome da coluna.
   * 
   * @return Retorna o nome da coluna.
   */
  public String getColName()
  {
    return m_colName;
  }

  /**
   * Seta o nome da coluna.
   * 
   * @param colName_ - O nome da coluna.
   */
  public void setColName( String colName_ )
  {
    m_colName = colName_;
  }

  /**
   * Recupera o nome da coluna selecionada.
   * 
   * @return Retorna o nome da coluna.
   */
  public String getSelectedColName()
  {
    return m_selectedColName;
  }

  /**
   * Seta o nome da coluna selecionada.
   * 
   * @param colName_ - O nome da coluna.
   */
  public void setSelectedColName( String colName_ )
  {
    m_selectedColName = colName_;
  }

  /**
   * Retorna a descri��o do atributo.
   * 
   * @return Retorna a descri��o do atributo.
   */
  public String getColText()
  {
    return m_colText;
  }

  /**
   * Seta a descri��o do atributo.
   * 
   * @param colText_ - Seta a descri��o do atributo.
   */
  public void setColText( String colText_ )
  {
    m_colText = colText_;
  }

  /**
   * Recupera o c�digo do tipo de dado do atributo.
   * 
   * @return Retorna o c�digo do tipo de dado do atributo
   */
  public String getColDataTypeCode()
  {
    return m_colDataTypeCode;
  }

  /**
   * Seta o c�digo do tipo de dado do atributo.
   * 
   * @param colDataTypeCode_ - o c�digo do tipo de dado do atributo.
   */
  public void setColDataTypeCode( String colDataTypeCode_ )
  {
    m_colDataTypeCode = colDataTypeCode_;
  }

  /**
   * Retorna o tamanho da coluna.
   * 
   * @return O tamanho da coluna.
   */
  public BigInteger getColSize()
  {
    return m_colSize;
  }

  /**
   * Seta o tamanho da coluna.
   * 
   * @param colSize_ - O tamanho da coluna.
   */
  public void setColSize( BigInteger colSize_ )
  {
    m_colSize = colSize_;
  }

  /**
   * Retorna a precis�o da coluna(casas decimais)
   * 
   * @return A precis�o da coluna(casas decimais)
   */
  public BigInteger getColPrcsnNbr()
  {
    return m_colPrcsnNbr;
  }

  /**
   * Seta a precis�o da coluna.
   * 
   * @param colPrcsnNbr_ - A precis�o da coluna.
   */
  public void setColPrcsnNbr( BigInteger colPrcsnNbr_ )
  {
    m_colPrcsnNbr = colPrcsnNbr_;
  }
}