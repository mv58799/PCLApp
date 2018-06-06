package com.citibank.ods.modules.product.fundsubscription.form;

import java.util.Collection;

import org.apache.struts.upload.FormFile;

import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.modules.product.fundsubscription.form.valueobject.FundSubscriptionImportHistoryVO;

public class FundSubscriptionImportDetailForm extends BaseForm{
	private static final long serialVersionUID = 3708794612849374462L;
	private FormFile file;
	private String message;
	private String productType;
	
	private Collection<FundSubscriptionImportHistoryVO> history;
	public Collection<FundSubscriptionImportHistoryVO> getHistory() {
		return history;
	}
	public void setHistory(Collection<FundSubscriptionImportHistoryVO> history) {
		this.history = history;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
}
