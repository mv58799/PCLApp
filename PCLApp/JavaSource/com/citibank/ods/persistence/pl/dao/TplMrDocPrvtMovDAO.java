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
 * Interface para acesso ao banco de dados de movimento de Memória de Risco.
 */
public interface TplMrDocPrvtMovDAO extends BaseTplMrDocPrvtDAO {
	/**
	 * Insere dados na tabela de Memória de Risco.
	 * 
	 * @param mrDocPrvtMovEntity_ - Entidade com os dados a serem inseridos.
	 */
	public void insert(TplMrDocPrvtMovEntity mrDocPrvtMovEntity_);

	/**
	 * Verifica se MR existe em movimento.
	 * 
	 * @param mrDocPrvtMovEntity_ - Entidade com MR a ser verificado.
	 * @return true se MR existe em movimento, false caso contrário.
	 */
	public boolean existsMovement(TplMrDocPrvtMovEntity mrDocPrvtMovEntity_);

	/**
	 * Retorna Data Set com os campos do grid da consulta em lista.
	 * 
	 * @param mrDocCode_ - Código Documento MR
	 * @param mrDocText_ - Descrição Documento MR
	 * @param mrInvstCurAcctInd_ - Indicador de conta CCI
	 * @param custNbr_ - Número do cliente
	 * @param curAcctNbr_ - Número da conta corrente
	 * @param lastUpdUserId_ - Nome do usuário da última atualização
	 * @param lastUpdDate_ - Data da última atualização
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
	 * @param lastAuthDate_ - Data da aprovação.
	 * @param lastAuthUserId_ - Usuário da aprovação.
	 */
	public void copyFromMovToCurrent(
		BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_,
		Date lastAuthDate,
		String lastAuthUserId);

	public BigInteger getNextVal();

	public boolean findExists(BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_);
	

}