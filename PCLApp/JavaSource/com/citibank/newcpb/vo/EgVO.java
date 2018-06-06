package com.citibank.newcpb.vo;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

public class EgVO {
	
	private String erNbr; // Numero do ER
	private String egNbr; // Numero EG
	private ArrayList<RegisterDataCustomerVO> customerList;

	public String getErNbr() {
		return erNbr;
	}

	public void setErNbr(String erNbr) {
		this.erNbr = erNbr;
	}

	public String getEgNbr() {
		return egNbr;
	}

	public void setEgNbr(String egNbr) {
		this.egNbr = egNbr;
	}


	public String getCustEmNbrFullName() {
		String text = "";
		if(customerList!=null){
			for (RegisterDataCustomerVO vo : customerList) {
				if(!StringUtils.isBlank(text)){
					text += "<p>";
				}
				text += vo.getNumberEM() + " / " + vo.getName();
			}
		}
		return text;
	}


	public ArrayList<RegisterDataCustomerVO> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<RegisterDataCustomerVO> customerList) {
		this.customerList = customerList;
	}
	
	public ArrayList<String> equals(EgVO other) {

		ArrayList<String> idDiffList = new ArrayList<String>();

		if (other != null){
			
			if (!StringUtils.isBlank(this.erNbr) && !StringUtils.isBlank(other.erNbr)) {
				if (!this.erNbr.equals(other.erNbr)) {
					idDiffList.add("erNbr");
				}
			} else if ((StringUtils.isBlank(this.erNbr) && !StringUtils.isBlank(other.erNbr))
					|| (StringUtils.isBlank(erNbr) && !StringUtils.isBlank(this.erNbr))) {
				idDiffList.add("erNbr");
			}
			
			if (!StringUtils.isBlank(this.egNbr) && !StringUtils.isBlank(other.egNbr)) {
				if (!this.egNbr.equals(other.egNbr)) {
					idDiffList.add("egNbr");
				}
			} else if ((StringUtils.isBlank(this.egNbr) && !StringUtils.isBlank(other.egNbr))
					|| (StringUtils.isBlank(egNbr) && !StringUtils.isBlank(this.egNbr))) {
				idDiffList.add("egNbr");
			}		
		}
		
		return idDiffList;
	}
}
