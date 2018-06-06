package com.citibank.newcpb.enun;

import java.util.ArrayList;

import com.citibank.newcpb.bean.ResultTableBean;

public enum CustomerTypeEnum {
	
	NATUTAL_PERSON("F", "Fisica"),
	LEGAL_PERSON("J","Juridica");

	private String value;
	private String desc;
	
	private CustomerTypeEnum(String value, String desc) {
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
	
    public static CustomerTypeEnum fromValue(String value) throws IllegalArgumentException {
        try {
                for (CustomerTypeEnum customerTypeEnum : CustomerTypeEnum.values()) {
                        if (customerTypeEnum.getValue().toUpperCase().equals(value.toUpperCase())) {
                                return customerTypeEnum;
                        }
                }
                
                return null;
        } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Unknown enum value type :" + value);
        }
}
    
    public static ArrayList<ResultTableBean> getCustomerTypeList() {
        
        ArrayList<ResultTableBean> constitutionTypeList = new ArrayList<ResultTableBean>() ;
        
        for(CustomerTypeEnum customerTypeEnum : CustomerTypeEnum.values()){
            ResultTableBean resultTableBean = new ResultTableBean();
            resultTableBean.setResultCode(customerTypeEnum.getValue());
            resultTableBean.setResultDescription(customerTypeEnum.getDesc());
            constitutionTypeList.add(resultTableBean);
  
        }
        
        return constitutionTypeList;
        
    }


	
	
}
