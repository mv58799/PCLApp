package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author m.nakamura
 * 
 * Representação da tabela de Requerimento de Execução de Processos de Carga.
 */
public class BaseTplLoadProcExecRequestEntityVO extends BaseEntityVO
{
  // Motivo da re-execucao
  private String m_execReasText = "";

  // Data de referencia de execucao
  private Date m_execRefDate = null;

  // Data antiga de referencia de execucao. Esta data será usada para procurar
  // um registro na tabela a fim de atualizar sua nova data.
  private Date m_oldExecRefDate = null;

  // Data e hora da ultima atualiza atualizacao efetuada pelo usuario.
  private Date m_lastUpdDate = null;

  // Codigo do usuario (SOE ID) que efetuou a ultima atualizacao no registro.
  private String m_lastUpdUserId = "";

  // Numero do processo de carga: Codigo do batch que sera executado em
  // umadeterminada carga.
  private BigInteger m_loadProcNbr = null;

  // Descrição do processo.
  private String m_loadProcText = "";

  //Código do processo selecionado
  private BigInteger m_selectedLoadProcNbr = null;

  // Lista com as entidades que agregam as re-execuções do processo.
  private ArrayList m_loadProcExecEntities;

  /**
   * Recupera o motivo da re-execucao.
   * 
   * @return Retorna o motivo da re-execucao.
   */
  public String getExecReasText()
  {
    return m_execReasText;
  }

  /**
   * Seta o motivo da re-execucao.
   * 
   * @param execReasText_ - O motivo da re-execucao.
   */
  public void setExecReasText( String execReasText_ )
  {
    m_execReasText = execReasText_;
  }

  /**
   * Recupera a data de referencia de execucao.
   * 
   * @return Retorna a data de referencia de execucao.
   */
  public Date getExecRefDate()
  {
    return m_execRefDate;
  }

  /**
   * Seta a data de referencia de execucao.
   * 
   * @param execRefDate_ - A data de referencia de execucao.
   */
  public void setExecRefDate( Date execRefDate_ )
  {
    m_execRefDate = execRefDate_;
  }

  /**
   * Recupera a data antiga de referencia de execucao.
   * 
   * @return Retorna a data antiga de referencia de execucao.
   */
  public Date getOldExecRefDate()
  {
    return m_oldExecRefDate;
  }

  /**
   * Seta a data antiga de referencia de execucao.
   * 
   * @param execRefDate_ - A data antiga de referencia de execucao.
   */
  public void setOldExecRefDate( Date oldExecRefDate_ )
  {
    m_oldExecRefDate = oldExecRefDate_;
  }

  /**
   * Recupera a data e hora da ultima atualiza atualizacao efetuada pelo
   * usuario.
   * 
   * @return Recupera a data e hora da ultima atualiza atualizacao efetuada pelo
   *         usuario.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * Seta a data e hora da ultima atualiza atualizacao efetuada pelo usuario.
   * 
   * @param lastUpdDate_ - A data e hora da ultima atualiza atualizacao efetuada
   *          pelo usuario.
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
   * Recupera o numero do processo de carga.
   * 
   * @return Retorna o numero do processo de carga.
   */
  public BigInteger getLoadProcNbr()
  {
    return m_loadProcNbr;
  }

  /**
   * Seta o numero do processo de carga.
   * 
   * @param loadProcNbr_ - O numero do processo de carga.
   */
  public void setLoadProcNbr( BigInteger loadProcNbr_ )
  {
    m_loadProcNbr = loadProcNbr_;
  }

  /**
   * Recupera a descrição do processo de carga.
   * 
   * @return Retorna a descrição do processo de carga.
   */
  public String getLoadProcText()
  {
    return m_loadProcText;
  }

  /**
   * Seta a descrição do processo de carga.
   * 
   * @param loadProcText_ - A descrição do processo de carga.
   */
  public void setLoadProcText( String loadProcText_ )
  {
    m_loadProcText = loadProcText_;
  }

  /**
   * Recupera o numero do processo de carga selecionado.
   * 
   * @return Retorna o numero do processo de carga selecionado.
   */
  public BigInteger getSelectedLoadProcNbr()
  {
    return m_selectedLoadProcNbr;
  }

  /**
   * Seta o numero do processo de carga selecionado.
   * 
   * @param loadProcNbr_ - O numero do processo de carga selecionado.
   */
  public void setSelectedLoadProcNbr( BigInteger loadProcNbr_ )
  {
    m_selectedLoadProcNbr = loadProcNbr_;
  }

  /**
   * Recupera uma lista de entities com as re-execuções do processo selecionado.
   * 
   * @return Retorna uma lista de entities.
   */
  public ArrayList getLoadProcExecEntities()
  {
    return m_loadProcExecEntities;
  }

  /**
   * Seta as re-execuções do processo.
   * 
   * @param loadProcExecEntities_ - Re-execuções do processo.
   */
  public void setLoadProcExecEntities( ArrayList loadProcExecEntities_ )
  {
    m_loadProcExecEntities = loadProcExecEntities_;
  }
}