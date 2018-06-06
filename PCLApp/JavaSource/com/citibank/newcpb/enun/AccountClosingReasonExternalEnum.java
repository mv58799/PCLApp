package com.citibank.newcpb.enun;

import java.util.ArrayList;

import com.citibank.newcpb.bean.ResultTableBean;


public enum AccountClosingReasonExternalEnum {
	
	CITIBANK("C", "Citibank"),
	TERCEIRO("T","Terceiro"),
	CLIENT("X","Cliente");


	
	private String value;
	private String desc;
	
	private AccountClosingReasonExternalEnum(String value, String desc) {
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
	
    public static AccountClosingReasonExternalEnum fromValue(String value) throws IllegalArgumentException {
        try {
                for (AccountClosingReasonExternalEnum customerTypeEnum : AccountClosingReasonExternalEnum.values()) {
                        if (customerTypeEnum.getValue().toUpperCase().equals(value.toUpperCase())) {
                                return customerTypeEnum;
                        }
                }
                
                return null;
        } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Unknown enum value type :" + value);
        }
    }
    
    
    public static ArrayList<ResultTableBean> getAccountClosingReasonExternalList() {
        
        ArrayList<ResultTableBean> accountTypeList = new ArrayList<ResultTableBean>() ;
        
        for(AccountClosingReasonExternalEnum accountTypeEnum : AccountClosingReasonExternalEnum.values()){
            ResultTableBean resultTableBean = new ResultTableBean();
            resultTableBean.setResultCode(accountTypeEnum.getValue());
            resultTableBean.setResultDescription(accountTypeEnum.getDesc());
            accountTypeList.add(resultTableBean);
  
        }
        
        return accountTypeList;
        
    }
    	
	
}
