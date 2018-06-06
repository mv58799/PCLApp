package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de Requerimento de Execução de
 * Processos de Carga.
 */
public interface TplLoadProcExecRequestDAO extends
    BaseTplLoadProcExecRequestDAO
{
  public ArrayList selectByPK( BigInteger loadProcNbr_ );

  public void deleteAll( BigInteger loadProcNbr_ );
}