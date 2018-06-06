package com.citibank.ods.modules.product.membershipTerm.form;

import com.citibank.ods.common.form.BaseForm;

public class ProdutoTermoForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private String prodCode;
	private String adhTermTypeCode;

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getAdhTermTypeCode() {
		return adhTermTypeCode;
	}

	public void setAdhTermTypeCode(String adhTermTypeCode) {
		this.adhTermTypeCode = adhTermTypeCode;
	}

}
