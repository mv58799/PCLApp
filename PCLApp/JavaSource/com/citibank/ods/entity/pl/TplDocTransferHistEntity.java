package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplDocTransferEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplDocTransferHistEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.entity.pl;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 16, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class TplDocTransferHistEntity extends BaseTplDocTransferEntity {

	/**
	 * Construtor padrão - sem argumentos
	 */
	public TplDocTransferHistEntity() {
		m_data = new TplDocTransferHistEntityVO();
	}

	public TplDocTransferHistEntity(TplDocTransferEntity tplDocTransferEntity_,
			Date docTransferRefDate) {
		m_data = new TplDocTransferHistEntityVO();

		m_data.setBeneAcctTypeCode(tplDocTransferEntity_.getData().getBeneAcctTypeCode());
		m_data.setBeneCpfCnpjNbr(tplDocTransferEntity_.getData().getBeneCpfCnpjNbr());
		m_data.setBeneMainDestAcctInd(tplDocTransferEntity_.getData().getBeneMainDestAcctInd());
		m_data.setBeneNameText(tplDocTransferEntity_.getData().getBeneNameText());
		m_data.setAgnBankCode(tplDocTransferEntity_.getData().getAgnBankCode());
		m_data.setBrchCode(tplDocTransferEntity_.getData().getBrchCode());
		m_data.setCurAcctNbr(tplDocTransferEntity_.getData().getCurAcctNbr());
		m_data.setCustNbr(tplDocTransferEntity_.getData().getCustNbr());
		m_data.setDocTransferCode(tplDocTransferEntity_.getData()
				.getDocTransferCode());
		m_data.setIpDocCode(tplDocTransferEntity_.getData().getIpDocCode());
		m_data.setLastUpdDate(tplDocTransferEntity_.getData().getLastUpdDate());
		m_data.setLastUpdUserId(tplDocTransferEntity_.getData()
				.getLastUpdUserId());
		m_data.setOwnDestAcctInd(tplDocTransferEntity_.getData()
				.getOwnDestAcctInd());
		m_data.setTxnTypeCode(tplDocTransferEntity_.getData().getTxnTypeCode());
		((TplDocTransferHistEntityVO) m_data)
				.setRecStatCode(((TplDocTransferEntityVO) tplDocTransferEntity_
						.getData()).getRecStatCode());
		((TplDocTransferHistEntityVO) m_data)
				.setLastAuthDate(((TplDocTransferEntityVO) tplDocTransferEntity_
						.getData()).getLastAuthDate());
		((TplDocTransferHistEntityVO) m_data)
				.setLastAuthUserId(((TplDocTransferEntityVO) tplDocTransferEntity_
						.getData()).getLastAuthUserId());
		((TplDocTransferHistEntityVO) m_data)
				.setDocTrfRefDate(docTransferRefDate);
	}

	public boolean equals(Object obj) {

		TplDocTransferHistEntity tplDocTransferHistEntity = (TplDocTransferHistEntity) obj;

		if (m_data.getCustNbr().equals(
				tplDocTransferHistEntity.getData().getCustNbr())
				&& m_data.getIpDocCode().equals(
						tplDocTransferHistEntity.getData().getIpDocCode())
				&& m_data.getDocTransferCode()
						.equals(
								tplDocTransferHistEntity.getData()
										.getDocTransferCode())
				&& ((TplDocTransferHistEntityVO) m_data)
						.getDocTrfRefDate()
						.equals(
								((TplDocTransferHistEntityVO) tplDocTransferHistEntity
										.getData()).getDocTrfRefDate())) {
			return true;
		}
		return false;
	}

}