package com.citibank.newcpb.enun;

import java.util.ArrayList;

import com.citibank.newcpb.bean.ResultTableBean;


public enum AccountTypeRDIPEnum {
	
	TRADING("T", "Trading Account"),
	RECOMMENDED("R","Recommended Account"),
	DESIGNATED("D","Designated Account");

	
	private String value;
	private String desc;
	
	private AccountTypeRDIPEnum(String value, String desc) {
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
	
    public static AccountTypeRDIPEnum fromValue(String value) throws IllegalArgumentException {
        try {
                for (AccountTypeRDIPEnum customerTypeEnum : AccountTypeRDIPEnum.values()) {
                        if (customerTypeEnum.getValue().toUpperCase().equals(value.toUpperCase())) {
                                return customerTypeEnum;
                        }
                }
                
                return null;
        } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Unknown enum value type :" + value);
        }
    }
    
    
    public static ArrayList<ResultTableBean> getAccountTypeRDIPList() {
        
        ArrayList<ResultTableBean> accountTypeRDIPList = new ArrayList<ResultTableBean>() ;
        
        for(AccountTypeRDIPEnum accountTypeRDIPEnum : AccountTypeRDIPEnum.values()){
            ResultTableBean resultTableBean = new ResultTableBean();
            resultTableBean.setResultCode(accountTypeRDIPEnum.getValue());
            resultTableBean.setResultDescription(accountTypeRDIPEnum.getDesc());
            accountTypeRDIPList.add(resultTableBean);
  
        }
        
        return accountTypeRDIPList;
        
    }
    	
	
}
