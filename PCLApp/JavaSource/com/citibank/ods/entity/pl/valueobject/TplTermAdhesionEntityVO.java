package com.citibank.ods.entity.pl.valueobject;

public class TplTermAdhesionEntityVO extends BaseTplTermAdhesionEntityVO {

	private static final long serialVersionUID = 1L;

	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof TplTermAdhesionEntityVO){
			TplTermAdhesionEntityVO o = (TplTermAdhesionEntityVO)obj;
			return o.getProdCode() != null && o.getProdCode().equals(this.getProdCode()); 
		}else {
			return false;
		}
	}
}
