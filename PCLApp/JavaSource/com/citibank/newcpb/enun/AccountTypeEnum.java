package com.citibank.newcpb.enun;

import java.util.ArrayList;

import com.citibank.newcpb.bean.ResultTableBean;


public enum AccountTypeEnum {
	
	CITIBANK("C", "Citibank"),
	TERCEIRO("T","Custódia Externa");

	
	private String value;
	private String desc;
	
	private AccountTypeEnum(String value, String desc) {
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
	
    public static AccountTypeEnum fromValue(String value) throws IllegalArgumentException {
        try {
                for (AccountTypeEnum customerTypeEnum : AccountTypeEnum.values()) {
                        if (customerTypeEnum.getValue().toUpperCase().equals(value.toUpperCase())) {
                                return customerTypeEnum;
                        }
                }
                
                return null;
        } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Unknown enum value type :" + value);
        }
    }
    
    
    public static ArrayList<ResultTableBean> getAccountTypeList() {
        
        ArrayList<ResultTableBean> accountTypeList = new ArrayList<ResultTableBean>() ;
        
        for(AccountTypeEnum accountTypeEnum : AccountTypeEnum.values()){
            ResultTableBean resultTableBean = new ResultTableBean();
            resultTableBean.setResultCode(accountTypeEnum.getValue());
            resultTableBean.setResultDescription(accountTypeEnum.getDesc());
            accountTypeList.add(resultTableBean);
  
        }
        
        return accountTypeList;
        
    }
    	
	
}
