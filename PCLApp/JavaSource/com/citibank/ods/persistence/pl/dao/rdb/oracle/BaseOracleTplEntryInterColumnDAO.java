package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplEntryInterColumnDAO;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de Interface de
 * Entrada de Colunas.
 */
public abstract class BaseOracleTplEntryInterColumnDAO extends BaseOracleDAO
    implements BaseTplEntryInterColumnDAO
{

  // Codigo da interface de entrada
  protected String C_ENTRY_INTER_CODE = "ENTRY_INTER_CODE";

  // Descrição da interface de entrada
  protected String C_ENTRY_INTER_TEXT = "ENTRY_INTER_TEXT";

  // Código do tipo de dados da coluna
  protected String C_COL_DATA_TYPE_CODE = "COL_DATA_TYPE_CODE";

  // Nome da coluna
  protected String C_COL_NAME = "COL_NAME";
 
  // Descrição da coluna
  protected String C_COL_NAME_TEXT = "COL_TEXT";

  // Tamanho da coluna
  protected String C_COL_SIZE = "COL_SIZE";

  // Precisão da coluna (casas decimais)
  protected String C_COL_PRCSN_NBR = "COL_PRCSN_NBR";
  
  // Código do tipo de interface
  protected String C_ENTRY_TYPE_CODE = "ENTRY_TYPE_CODE";
  
  // Nome do arquivo de origem
  protected String C_ORIG_FILE_NAME = "ORIG_FILE_NAME";
  
  // Codigo do sistema
  protected String C_SYS_CODE = "SYS_CODE";
  
  // Segmento do sistema
  protected String C_SYS_SEG_CODE = "SYS_SEG_CODE";
}