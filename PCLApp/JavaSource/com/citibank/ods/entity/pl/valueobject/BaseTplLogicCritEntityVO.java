package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author m.nakamura
 * 
 * Representação da tabela de Crítica Lógica
 */
public class BaseTplLogicCritEntityVO extends BaseEntityVO
{
  // Tipo de dados
  private String m_dataTypeCode = "";

  // Data e hora da ultima atualizaca efetuada pelo usuario.
  private Date m_lastUpdDate = null;

  // Codigo do usuario (SOE ID) que efetuou a ultima atualizacao no registro.
  private String m_lastUpdUserId = "";

  // Indica se havera necessidade de alteracoes no ETL (programa de carga)
  private String m_loadProgUpdInd = "";

  // Codigo da critica logica da carga.
  private BigInteger m_logicCritCode = null;

  // Indica se ha dominio para esta critica
  private String m_logicCritDomInd = "";

  // Descricao da critica logica da carga
  private String m_logicCritText = "";

  // Codigo do Status do registro.
  private String m_recStatCode = "";

  /**
   * Recupera o tipo de dados.
   * 
   * @return Retorna o tipo de dados.
   */
  public String getDataTypeCode()
  {
    return m_dataTypeCode;
  }

  /**
   * Seta o tipo de dados.
   * 
   * @param dataTypeCode_ - O tipo de dados a ser setado.
   */
  public void setDataTypeCode( String dataTypeCode_ )
  {
    m_dataTypeCode = dataTypeCode_;
  }

  /**
   * Recupera a data e hora da ultima atualizaca efetuada pelo usuario.
   * 
   * @return Retorna a data e hora da ultima atualizaca efetuada pelo usuario.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * Seta a data e hora da ultima atualizaca efetuada pelo usuario.
   * 
   * @param lastUpdDate_ - A data e hora da ultima atualizaca efetuada pelo
   *          usuario.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * Recupera o codigo do usuario (SOE ID) que efetuou a ultima atualizacao no
   * registro.
   * 
   * @return Retorna o codigo do usuario (SOE ID) que efetuou a ultima
   *         atualizacao no registro.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * Seta o codigo do usuario (SOE ID) que efetuou a ultima atualizacao no
   * registro.
   * 
   * @param lastUpdUserId_ - O codigo do usuario (SOE ID) que efetuou a ultima
   *          atualizacao no registro.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * Recupera o status que indica se havera necessidade de alteracoes no ETL
   * (programa de carga)
   * 
   * @return Retorna o status que indica se havera necessidade de alteracoes no
   *         ETL (programa de carga)
   */
  public String getLoadProgUpdInd()
  {
    return m_loadProgUpdInd;
  }

  /**
   * Seta o status que indica se havera necessidade de alteracoes no ETL
   * (programa de carga)
   * 
   * @param loadProgUpdInd_ - O status que indica se havera necessidade de
   *          alteracoes no ETL (programa de carga)
   */
  public void setLoadProgUpdInd( String loadProgUpdInd_ )
  {
    m_loadProgUpdInd = loadProgUpdInd_;
  }

  /**
   * Recupera o codigo da critica logica da carga.
   * 
   * @return Retorna o codigo da critica logica da carga.
   */
  public BigInteger getLogicCritCode()
  {
    return m_logicCritCode;
  }

  /**
   * Seta o codigo da critica logica da carga.
   * 
   * @param logicCritCode_ - O codigo da critica logica da carga.
   */
  public void setLogicCritCode( BigInteger logicCritCode_ )
  {
    m_logicCritCode = logicCritCode_;
  }

  /**
   * Recupera o status que indica se ha dominio para esta critica
   * 
   * @return Retorna o status que indica se ha dominio para esta critica
   */
  public String getLogicCritDomInd()
  {
    return m_logicCritDomInd;
  }

  /**
   * Seta o status que indica se ha dominio para esta critica
   * 
   * @param logicCritDomInd_ - O status que indica se ha dominio para esta
   *          critica
   */
  public void setLogicCritDomInd( String logicCritDomInd_ )
  {
    m_logicCritDomInd = logicCritDomInd_;
  }

  /**
   * Recupera a descricao da critica logica da carga
   * 
   * @return Recupera a descricao da critica logica da carga
   */
  public String getLogicCritText()
  {
    return m_logicCritText;
  }

  /**
   * Seta a descricao da critica logica da carga.
   * 
   * @param logicCritText_ - A descricao da critica logica da carga.
   */
  public void setLogicCritText( String logicCritText_ )
  {
    m_logicCritText = logicCritText_;
  }

  /**
   * Recupera o codigo do status do registro.
   * 
   * @return Retorna o codigo do status do registro.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * Seta o codigo do status do registro.
   * 
   * @param recStatCode_ - O codigo do status do registro.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }
}