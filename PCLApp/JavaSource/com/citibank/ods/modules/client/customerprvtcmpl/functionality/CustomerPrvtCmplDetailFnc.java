/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.customerprvtcmpl.functionality;

import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.TbgOfficerEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplMovEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplCustomerPrvtCmplMovEntityVO;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject.BaseCustomerPrvtCmplDetailFncVO;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject.CustomerPrvtCmplDetailFncVO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplMovDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author gerson.a.rodrigues
 *  
 */
public class CustomerPrvtCmplDetailFnc
	extends BaseCustomerPrvtCmplDetailFnc
	implements ODSDetailFnc {

	//Número do cpf/cnpj
	public static final String C_CUST_CPF_CNPJ_NBR = "CUST_CPF_CNPJ_NBR";

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.modules.client.customer.functionality.CustomerDetailFnc#getDAO()
	 */
	protected BaseTplCustomerPrvtCmplDAO getDAO() {

		return ODSDAOFactory.getInstance().getTplCustomerPrvtCmplDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
	 */
	public BaseFncVO createFncVO() {
		return new CustomerPrvtCmplDetailFncVO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#insert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void insert(BaseFncVO fncVO_) {
		CustomerPrvtCmplDetailFncVO customerPrvtCmplDetailFncVO =
			(CustomerPrvtCmplDetailFncVO) fncVO_;
		if (!customerPrvtCmplDetailFncVO.hasErrors()) {

			if (existsActive(customerPrvtCmplDetailFncVO)) {
				validateUpdate(customerPrvtCmplDetailFncVO);
				if (!customerPrvtCmplDetailFncVO.hasErrors()) {
					this.createMovementRecord(
						customerPrvtCmplDetailFncVO,
						TplCustomerPrvtCmplMovEntity.C_OPERN_CODE_UPDATE);
					//Alteracao G&P 2008/05/28
					customerPrvtCmplDetailFncVO.setFromSearch(false);
				}

			} else {
				validateInsert(customerPrvtCmplDetailFncVO);
				if (!customerPrvtCmplDetailFncVO.hasErrors()) {
					this.createMovementRecord(
						customerPrvtCmplDetailFncVO,
						TplCustomerPrvtCmplMovEntity.C_OPERN_CODE_INSERT);
					customerPrvtCmplDetailFncVO.setFromSearch(false);
				}

			}
		}
	}

	/**
	 * 
	 * "Cria o registro na tabela de movimento contendo as novas informações."
	 * @param officerCmplDetailFncVO_ FncVO da funcionalidade contendo os dados
	 *          que serão processados.
	 */
	private void createMovementRecord(
		CustomerPrvtCmplDetailFncVO customerPrvtCmplDetailFncVO_,
		String opernCode_) {
		//entity de movimento que será populada para ser inserida no bando de
		// dados.
		TplCustomerPrvtCmplMovEntity tplCustomerPrvtCmplMovEntity =
			new TplCustomerPrvtCmplMovEntity();

		ODSDAOFactory odsDaoFactory = ODSDAOFactory.getInstance();
		TplCustomerPrvtCmplMovDAO tplCustomerPrvtCmplMovDAO =
			odsDaoFactory.getTplCustomerPrvtCmplMovDAO();

		tplCustomerPrvtCmplMovEntity.getData().setLastUpdDate(new Date());
		tplCustomerPrvtCmplMovEntity.getData().setLastUpdUserId(
			customerPrvtCmplDetailFncVO_.getLoggedUser().getUserID());
        
        tplCustomerPrvtCmplMovEntity.getData().setPrvtCustTypeCode(
        	customerPrvtCmplDetailFncVO_
        	    .getBaseTplCustomerPrvtCmplEntity()
        	    .getData()
        	    .getPrvtCustTypeCode());
        	    		
		tplCustomerPrvtCmplMovEntity.getData().setClassCmplcCode(
			customerPrvtCmplDetailFncVO_
				.getBaseTplCustomerPrvtCmplEntity()
				.getData()
				.getClassCmplcCode());
		tplCustomerPrvtCmplMovEntity.getData().setCustNbr(
			customerPrvtCmplDetailFncVO_
				.getBaseTplCustomerPrvtCmplEntity()
				.getData()
				.getCustNbr());
		tplCustomerPrvtCmplMovEntity.getData().setEmNbr(
			customerPrvtCmplDetailFncVO_
				.getBaseTplCustomerPrvtCmplEntity()
				.getData()
				.getEmNbr());
		tplCustomerPrvtCmplMovEntity.getData().setGlbRevenSysOffcrNbr(
			customerPrvtCmplDetailFncVO_
				.getBaseTplCustomerPrvtCmplEntity()
				.getData()
				.getGlbRevenSysOffcrNbr());
		tplCustomerPrvtCmplMovEntity.getData().setMailRecvInd(
			customerPrvtCmplDetailFncVO_
				.getBaseTplCustomerPrvtCmplEntity()
				.getData()
				.getMailRecvInd());
		tplCustomerPrvtCmplMovEntity.getData().setOffclMailRecvInd(
			customerPrvtCmplDetailFncVO_
				.getBaseTplCustomerPrvtCmplEntity()
				.getData()
				.getOffclMailRecvInd());
		tplCustomerPrvtCmplMovEntity.getData().setOffcrNbr(
			customerPrvtCmplDetailFncVO_
				.getBaseTplCustomerPrvtCmplEntity()
				.getData()
				.getOffcrNbr());
		tplCustomerPrvtCmplMovEntity.getData().setPrvtCustNbr(
			customerPrvtCmplDetailFncVO_
				.getBaseTplCustomerPrvtCmplEntity()
				.getData()
				.getPrvtCustNbr());
		tplCustomerPrvtCmplMovEntity.getData().setPrvtKeyNbr(
			customerPrvtCmplDetailFncVO_
				.getBaseTplCustomerPrvtCmplEntity()
				.getData()
				.getPrvtKeyNbr());
		tplCustomerPrvtCmplMovEntity.getData().setWealthPotnlCode(
			customerPrvtCmplDetailFncVO_
				.getBaseTplCustomerPrvtCmplEntity()
				.getData()
				.getWealthPotnlCode());
		tplCustomerPrvtCmplMovEntity.getData().setCustPrvtStatCode(
			customerPrvtCmplDetailFncVO_
				.getBaseTplCustomerPrvtCmplEntity()
				.getData()
				.getCustPrvtStatCode());

		(
			(TplCustomerPrvtCmplMovEntityVO) tplCustomerPrvtCmplMovEntity
				.getData())
				.setOpernCode(
			opernCode_);

		tplCustomerPrvtCmplMovDAO.insert(tplCustomerPrvtCmplMovEntity);

	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void update(BaseFncVO fncVO_) {
		this.validateUpdate(fncVO_);
		if (!fncVO_.hasErrors()) {
			CustomerPrvtCmplDetailFncVO customerPrvtCmplDetailFncVO =
				(CustomerPrvtCmplDetailFncVO) fncVO_;

		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#delete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void delete(BaseFncVO fncVO_) {
		this.validateDelete(fncVO_);
		if (!fncVO_.hasErrors()) {
			CustomerPrvtCmplDetailFncVO customerPrvtCmplDetailFncVO =
				(CustomerPrvtCmplDetailFncVO) fncVO_;
			this.createMovementRecord(
				customerPrvtCmplDetailFncVO,
				TplCustomerPrvtCmplMovEntity.C_OPERN_CODE_DELETE);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void loadForConsult(BaseFncVO fncVO_) {
		loadCustomerPrvtCmpl((BaseCustomerPrvtCmplDetailFncVO) fncVO_);
		load((BaseCustomerPrvtCmplDetailFncVO) fncVO_);

	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void loadForInsert(BaseFncVO fncVO_) {

		BaseCustomerPrvtCmplDetailFncVO detailFncVO =
			(BaseCustomerPrvtCmplDetailFncVO) fncVO_;
		//Alteraçao G&P 2008/05/28

		if (!detailFncVO.isOffcrClear()) {

			if (existsInMovement((CustomerPrvtCmplDetailFncVO) fncVO_)) {
				fncVO_.addError(BaseODSFncVO.C_ERROR_IN_MOVEMENT);
			} else if (!detailFncVO.isFromSearch()) {
				detailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setEmNbr("");
				detailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setOffcrNbr(null);
				detailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setPrvtKeyNbr(null);
				detailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setPrvtCustNbr(null);
				detailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setWealthPotnlCode(null);
				detailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setPrvtCustTypeCode(null);
				detailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setClassCmplcCode(null);
				detailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setCustPrvtStatCode("");
				detailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setGlbRevenSysOffcrNbr(null);
				detailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setOffclMailRecvInd("");
				detailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setMailRecvInd("");
				if (existsActive((CustomerPrvtCmplDetailFncVO) fncVO_)) {

					BaseTplCustomerPrvtCmplDAO customerDAO = this.getDAO();
					BaseTplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity =
						(BaseTplCustomerPrvtCmplEntity) customerDAO.find(
							detailFncVO.getBaseTplCustomerPrvtCmplEntity());
					//Alteraçao G&P INICIO
					if (detailFncVO
						.getBaseTplCustomerPrvtCmplEntity()
						.getData()
						.getOffcrNbr()
						!= null) {
						tplCustomerPrvtCmplEntity.getData().setOffcrNbr
						(detailFncVO
						 .getBaseTplCustomerPrvtCmplEntity()
						 .getData()
						 .getOffcrNbr());
					}
					//Alteraçao G&P FIM        	
					detailFncVO.setBaseTplCustomerPrvtCmplEntity(
						tplCustomerPrvtCmplEntity);

				} else {
					TplCustomerPrvtCmplEntity customerPrvtCmplEntity =
						new TplCustomerPrvtCmplEntity();
					customerPrvtCmplEntity.getData().setCustNbr(
						detailFncVO
							.getBaseTplCustomerPrvtCmplEntity()
							.getData()
							.getCustNbr());
					customerPrvtCmplEntity.getData().setOffcrNbr(
						detailFncVO
							.getBaseTplCustomerPrvtCmplEntity()
							.getData()
							.getOffcrNbr());

					detailFncVO.setBaseTplCustomerPrvtCmplEntity(
						customerPrvtCmplEntity);

				}
				detailFncVO.setFromSearch(false);
			}
		} else {
			detailFncVO.setOffcrClear(false);
		}
		load(detailFncVO);

	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void loadForUpdate(BaseFncVO fncVO_) {
		BaseCustomerPrvtCmplDetailFncVO baseCustomerPrvtCmplDetailFncVO =
			(BaseCustomerPrvtCmplDetailFncVO) fncVO_;

		if (!baseCustomerPrvtCmplDetailFncVO.isFromSearch()) {
			if (!existsInMovement((CustomerPrvtCmplDetailFncVO) fncVO_)) {
				loadCustomerPrvtCmpl((BaseCustomerPrvtCmplDetailFncVO) fncVO_);
			} else {
				fncVO_.addError(BaseODSFncVO.C_ERROR_IN_MOVEMENT);
			}
		} else {
			baseCustomerPrvtCmplDetailFncVO.setFromSearch(false);
		}

		load(baseCustomerPrvtCmplDetailFncVO);

	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void loadForDelete(BaseFncVO fncVO_) {
		if (!existsInMovement((CustomerPrvtCmplDetailFncVO) fncVO_)) {
			loadCustomerPrvtCmpl((BaseCustomerPrvtCmplDetailFncVO) fncVO_);
			load((BaseCustomerPrvtCmplDetailFncVO) fncVO_);

		} else {
			fncVO_.addError(BaseODSFncVO.C_ERROR_IN_MOVEMENT);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void validateInsert(BaseFncVO fncVO_) {

		//Obtém a instância da Factory
		ODSDAOFactory plFactory = ODSDAOFactory.getInstance();

		//Obtém a instância do DAO necessário
		TplCustomerPrvtCmplDAO customerPrvtCmplDAO =
			plFactory.getTplCustomerPrvtCmplDAO();
		TplCustomerPrvtCmplMovDAO customerPrvtCmplMovDAO =
			plFactory.getTplCustomerPrvtCmplMovDAO();
		CustomerPrvtCmplDetailFncVO customerPrvtCmplDetailFncVO =
			(CustomerPrvtCmplDetailFncVO) fncVO_;

		// Validar Campos Obrigatórios
		if (customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr()== null
			|| customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr().longValue()== 0) {
			fncVO_.addError(BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_CUST_NBR);
		}

		//	  Verifica se já existe algum registro com status ativo
		if (existsActive(customerPrvtCmplDetailFncVO)) {
			fncVO_.addError(BaseODSFncVO.C_ERROR_DUPLICATE_PK);
		}

		// Validar se já existe movimento com este codigo, caso os campos
		// obrigatórios estejam presentes
		if (!fncVO_.hasErrors()) {
			if (this.existsInMovement(customerPrvtCmplDetailFncVO)) {
				fncVO_.addError(BaseODSFncVO.C_ERROR_IN_MOVEMENT);
			}
			// Validar se os campos referenciados estão corretos
			if (!fncVO_.hasErrors()) {
				TplCustomerPrvtEntity tplCustomerPrvtEntity =
					new TplCustomerPrvtEntity();
				tplCustomerPrvtEntity.getData().setCustNbr(customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr());

				TplCustomerPrvtDAO customerPrvtDAO =
					plFactory.getTplCustomerPrvtDAO();

				if (!customerPrvtDAO.exists(tplCustomerPrvtEntity)) {
					fncVO_.addError(BaseODSFncVO.C_ERROR_PK_NOT_FOUND,C_CUST_NBR);
				}

				if (!fncVO_.hasErrors()) {
					if (customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getOffcrNbr()!= null
						&& customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getOffcrNbr().intValue()
							> 0) {
						TbgOfficerEntity tbgOfficerEntity =
							new TbgOfficerEntity();
						tbgOfficerEntity.getData().setOffcrNbr(
							customerPrvtCmplDetailFncVO
								.getBaseTplCustomerPrvtCmplEntity()
								.getData()
								.getOffcrNbr());

						TbgOfficerDAO officerDAO = plFactory.getTbgOfficerDAO();

						if (!officerDAO.exists(tbgOfficerEntity)) {
							fncVO_.addError(
								BaseODSFncVO.C_INVALID_FK,
								C_INSERT,
								C_OFFCR_NBR);
						}
					}

					if (!fncVO_.hasErrors()) {
						//Validar se os campos únicos estão respeitados na tabela de
						// cadastro
						if (!fncVO_.hasErrors()) {
							
							if (customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getEmNbr()!= null){
								
								DataSet result = null;
								try {
								  result =
								    customerPrvtCmplDAO.list(customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getEmNbr(),
																		null,
																		null,
																		null,
																		null,
																		null,
																		null,
																		null,
																		null,
																		null);
																		
								} catch (Exception ex) {
								  throw new UnexpectedException(C_ERROR);
								}
								if (result != null && result.size() > 0) {
								  
								  if (verifyEmNbrByCpf(customerPrvtCmplDetailFncVO)) {
									  fncVO_.addError(BaseODSFncVO.C_ERROR_EMNBR_DUPLICATED);
								  }
								}

							}
						}

						//Validar se os campos únicos estão respeitados na tabela de
						// movimento
						if (!fncVO_.hasErrors()) {
							
							if (customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getEmNbr()!= null){
								
							  DataSet result = null;
							  try {
								result =
									customerPrvtCmplMovDAO.list(customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getEmNbr(),
										null,
										null,
										null,
										null,
										null,
										null,
										null,
										null,
										null,
										null,
										null,
										null);
							  } catch (Exception ex) {
								throw new UnexpectedException(C_ERROR);
							  }
							  if (result != null && result.size() > 0) {
													
								if (
									verifyEmNbrByCpf(customerPrvtCmplDetailFncVO)) {
									fncVO_.addError(
										BaseODSFncVO.C_ERROR_EMNBR_DUPLICATED);
							  	}
								
							  }
							}
						}
					}
				}
			}
		}

	}

	/*
	 * Verifica se existe um registro na tabela de movimento com os critérios
	 * passados
	 * 
	 * @see com.citibank.ods.modules.product.prodriskcatprvt.functionality.BaseProdRiskCatPrvtDetailFnc#getDAO()
	 */
	private boolean existsInMovement(CustomerPrvtCmplDetailFncVO customerPrvtCmplDetailFncVO_) {
		TplCustomerPrvtCmplMovDAO tplCustomerPrvtCmplMovDAO =
			ODSDAOFactory.getInstance().getTplCustomerPrvtCmplMovDAO();

		TplCustomerPrvtCmplMovEntity customerMovEntity =
			new TplCustomerPrvtCmplMovEntity();
		customerMovEntity.getData().setCustNbr(
			customerPrvtCmplDetailFncVO_
				.getBaseTplCustomerPrvtCmplEntity()
				.getData()
				.getCustNbr());

		return tplCustomerPrvtCmplMovDAO.exists(customerMovEntity);
	}

	/*
	 * Verifica se existe algum registro com a chave passada e seu status é ativo
	 */
	private boolean existsActive(CustomerPrvtCmplDetailFncVO customerPrvtCmplDetailFncVO_) {
		TplCustomerPrvtCmplDAO tplCustomerPrvtCmplDAO =
			ODSDAOFactory.getInstance().getTplCustomerPrvtCmplDAO();
		return tplCustomerPrvtCmplDAO.existsActive(
			(TplCustomerPrvtCmplEntity) customerPrvtCmplDetailFncVO_
				.getBaseTplCustomerPrvtCmplEntity());
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void validateUpdate(BaseFncVO fncVO_) {
		//Obtém a instância da Factory
		ODSDAOFactory plFactory = ODSDAOFactory.getInstance();
		TplCustomerPrvtCmplDAO customerPrvtCmplDAO =
			plFactory.getTplCustomerPrvtCmplDAO();
		TplCustomerPrvtCmplMovDAO customerPrvtCmplMovDAO =
			plFactory.getTplCustomerPrvtCmplMovDAO();

		CustomerPrvtCmplDetailFncVO customerPrvtCmplDetailFncVO =
			(CustomerPrvtCmplDetailFncVO) fncVO_;

		//  Validar Campos Obrigatórios
		if (customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr()== null
			|| customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr().longValue()== 0) {
			fncVO_.addError(BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_CUST_NBR);
		}

		if (!fncVO_.hasErrors()) {

			if (customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getOffcrNbr()!= null
				&& customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getOffcrNbr().intValue()> 0) {
				TbgOfficerEntity tbgOfficerEntity = new TbgOfficerEntity();
				tbgOfficerEntity.getData().setOffcrNbr(customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getOffcrNbr());
				
				TbgOfficerDAO officerDAO = plFactory.getTbgOfficerDAO();

				if (!officerDAO.exists(tbgOfficerEntity)) {
					fncVO_.addError(BaseODSFncVO.C_INVALID_FK,C_UPDATE,C_OFFCR_NBR);
				}
			}

			// Validar se já existe movimento com este codigo, caso os campos
			// obrigatórios estejam presentes
			if (!fncVO_.hasErrors()) {
				if (!existsActive(customerPrvtCmplDetailFncVO)) {
					fncVO_.addError(BaseODSFncVO.C_ERROR_IN_MOVEMENT);
				}

				// Validar se já existe movimento com este codigo, caso os campos
				// obrigatórios estejam presentes
				if (!fncVO_.hasErrors()) {
					if (this.existsInMovement(customerPrvtCmplDetailFncVO)) {
						fncVO_.addError(BaseODSFncVO.C_ERROR_IN_MOVEMENT);
					}
				}
			}
			if (!fncVO_.hasErrors()) {
				//Validar se os campos únicos estão respeitados na tabela de
				// cadastro
				if (!fncVO_.hasErrors()) {
					
					if (customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getEmNbr()!= null){						
					
					  DataSet result = null;
					  try {
						result =
							customerPrvtCmplDAO.list(
								customerPrvtCmplDetailFncVO
									.getBaseTplCustomerPrvtCmplEntity()
									.getData()
									.getEmNbr(),
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null);
								
					  } catch (Exception ex) {
						throw new UnexpectedException(C_ERROR);
					  }
					  if (result != null && result.size() > 0) {
						if (verifyEmNbrByCpf(customerPrvtCmplDetailFncVO)) {
							fncVO_.addError(BaseODSFncVO.C_ERROR_EMNBR_DUPLICATED);
						}

					  }
					}
				}

				//Validar se os campos únicos estão respeitados na tabela de
				// movimento
				if (!fncVO_.hasErrors()) {

					if (customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getEmNbr()!= null) {
						DataSet result = null;
						try {
							result =
								customerPrvtCmplMovDAO.list(
									customerPrvtCmplDetailFncVO
										.getBaseTplCustomerPrvtCmplEntity()
										.getData()
										.getEmNbr(),
									null,
									null,
									null,
									null,
									null,
									null,
									null,
									null,
									null,
									null,
									null,
									null);
						} catch (Exception ex) {
							throw new UnexpectedException(C_ERROR);
						}
						if (result != null && result.size() > 0) {
							if (
								verifyEmNbrByCpf(customerPrvtCmplDetailFncVO)) {
								fncVO_.addError(BaseODSFncVO.C_ERROR_EMNBR_DUPLICATED);

							}
						}
					}
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void validateDelete(BaseFncVO fncVO_) {
		CustomerPrvtCmplDetailFncVO customerPrvtCmplDetailFncVO =
			(CustomerPrvtCmplDetailFncVO) fncVO_;

		//  Validar Campos Obrigatórios
		if (customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr()== null
			|| customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr().longValue()== 0) {
			fncVO_.addError(BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_CUST_NBR);
		}

		// Validar se já existe movimento com este codigo, caso os campos
		// obrigatórios estejam presentes
		if (!fncVO_.hasErrors()) {
			if (this.existsInMovement(customerPrvtCmplDetailFncVO)) {
				fncVO_.addError(BaseODSFncVO.C_ERROR_IN_MOVEMENT);
			}

			if (!fncVO_.hasErrors()) {
				if (!this.existsActive(customerPrvtCmplDetailFncVO)) {
					fncVO_.addError(BaseODSFncVO.C_ERROR_IN_MOVEMENT);
				}
			}
		}
	}

}