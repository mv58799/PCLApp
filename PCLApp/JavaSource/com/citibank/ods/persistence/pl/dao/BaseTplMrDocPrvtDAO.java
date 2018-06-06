package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity;

/**
 * @author m.nakamura
 * 
 * Interface para acesso ao banco de dados de Memória de Risco.
 */
public interface BaseTplMrDocPrvtDAO {
	/**
	 * Retorna uma entidade com os campos de detalhe.
	 * 
	 * @param baseTplMrDocPrvtEntity_ - Entidade que possui os dados necessários
	 *          para busca na tabela.
	 * @return BaseTplMrDocPrvtEntity - Entidade com os campos de detalhe.
	 */
	public BaseTplMrDocPrvtEntity find(BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_);

	/**
	*Retorna uma entidade com os campos de detalhe.
	* 
	* @param baseTplMrDocPrvtEntity_ - Entidade que contem o id
	*          para busca na tabela.
	* @return BaseTplMrDocPrvtEntity - Entidade com os campos de detalhe.
	*/
	public BaseTplMrDocPrvtEntity findById(BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_);
	
	public BaseTplMrDocPrvtEntity findActive(BaseTplMrDocPrvtEntity baseTplMrDocPrvtEntity_ );
}