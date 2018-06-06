package com.citibank.ods.modules.product.membershipTerm.functionality.valueobject;

import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

public class ProdutoTermoFncVO extends BaseODSFncVO {

	private static final long serialVersionUID = 1L;

	private String prodCode;
	private String adhTermTypeCode;

	public ProdutoTermoFncVO() {
		super();
	}

	public ProdutoTermoFncVO(String prodCode) {
		super();
		this.prodCode = prodCode;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((prodCode == null) ? 0 : prodCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoTermoFncVO other = (ProdutoTermoFncVO) obj;
		if (prodCode == null) {
			if (other.prodCode != null)
				return false;
		} else if (!prodCode.equals(other.prodCode))
			return false;
		return true;
	}

}
