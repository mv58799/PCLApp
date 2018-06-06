package com.citibank.newcpb.enun;

import java.util.ArrayList;

import com.citibank.newcpb.bean.ResultTableBean;


public enum AccountClosingReasonCitibankEnum {
	
	SOL_CLIENT("X", "Solicitação do Cliente"),
	DEC_GER_NE("B", "Decisão gerencial pelo Negócio"),
	DEC_GER_AML("A","Decisão gerencial por AML");
	

	
	private String value;
	private String desc;
	
	private AccountClosingReasonCitibankEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
    public static AccountClosingReasonCitibankEnum fromValue(String value) throws IllegalArgumentException {
        try {
                for (AccountClosingReasonCitibankEnum customerTypeEnum : AccountClosingReasonCitibankEnum.values()) {
                        if (customerTypeEnum.getValue().toUpperCase().equals(value.toUpperCase())) {
                                return customerTypeEnum;
                        }
                }
                
                return null;
        } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Unknown enum value type :" + value);
        }
    }
    
    
    public static ArrayList<ResultTableBean> getAccountClosingCitiBankList() {
        
        ArrayList<ResultTableBean> accountTypeList = new ArrayList<ResultTableBean>() ;
        
        for(AccountClosingReasonCitibankEnum accountTypeEnum : AccountClosingReasonCitibankEnum.values()){
            ResultTableBean resultTableBean = new ResultTableBean();
            resultTableBean.setResultCode(accountTypeEnum.getValue());
            resultTableBean.setResultDescription(accountTypeEnum.getDesc());
            accountTypeList.add(resultTableBean);
  
        }
        
        return accountTypeList;
        
    }
    	
	
}
