package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity;
import com.citibank.ods.entity.pl.TplMrDocPrvtMovEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de movimento de Mem�ria de Risco.
 */
public interface TplMrDocPrvtMovDAO extends BaseTplMrDocPrvtDAO {
	/**
	 * Insere dados na tabela de Mem�ria de Risco.
	 * 
	 * @param mrDocPrvtMovEntity_ - Entidade com os dados a serem inseridos.
	 */
	public void insert(TplMrDocPrvtMovEntity mrDocPrvtMovEntity_);

	/**
	 * Verifica se MR existe em movimento.
	 * 
	 * @param mrDocPrvtMovEntity_ - Entidade com MR a ser verificado.
	 * @return true se MR existe em movimento, false caso contr�rio.
	 */
	public boolean existsMovement(TplMrDocPrvtMovEntity mrDocPrvtMovEntity_);

	/**
	 * Retorna Data Set com os campos do grid da consulta em lista.
	 * 
	 * @param mrDocCode_ - C�digo Documento MR
	 * @param mrDocText_ - Descri��o Documento MR
	 * @param mrInvstCurAcctInd_ - Indicador de conta CCI
	 * @param custNbr_ - N�mero do cliente
	 * @param curAcctNbr_ - N�mero da conta corrente
	 * @param lastUpdUserId_ - Nome do usu�rio da �ltima atualiza��o
	 * @param lastUpdDate_ - Data da �ltima atualiza��o
	 * @return DataSet - Data Set com os campos do grid da consulta em lista.
	 */
	public DataSet list(
		BigInteger mrDocCode_,
		String mrDocText_,
		String mrInvstCurAcctInd_,
		BigInteger custNbr_,
		String custName_,
		BigInteger curAcctNbr_,
		String lastUpdUserId_,
		Date lastUpdDate_,
	    BigInteger prodAcctCode_,
	    BigInteger prodAcctUnderCode_ );
	    
	public ArrayList list(BigInteger prodAcctCode_,BigInteger prodAcctUnderCode_ );
   	    

	/**
	 * Atualiza a tabela de Movemento de Memo de Risco.
	 * 
	 * @param mrDocPrvtMovEntity_ - Entidade com os dados a serem atualizados.
	 */
	public void update(TplMrDocPrvtMovEntity mrDocPrvtMovEntity_);

	/**
	 * Deleta registro da tabela de Movemento de Memo de Risco.
	 * 
	 * @param mrDocPrvtMovEntity_ - Entidade com as chaves do registro a ser
	 *          deletado.
	 */
	public void delete(TplMrDocPrvtMovEntity mrDocPrvtMovEntity_);

	/**
	 * Copia registro de Mov para Current.
	 * 
	 * @param baseTplMrDocPrvtEntity_ - Entidade com as chaves do registro a ser
	 *          copiado.
	 * @param lastAuthDate_ - Data da aprova��o.
	 * @param lastAuthUserId_ - Usu�rio da aprova��o.
	 */
	public void copyFromMovToCurrent(
		BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_,
		Date lastAuthDate,
		String lastAuthUserId);

	public BigInteger getNextVal();

	public boolean findExists(BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_);
	

}