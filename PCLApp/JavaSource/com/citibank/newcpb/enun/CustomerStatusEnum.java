package com.citibank.newcpb.enun;

import java.util.ArrayList;

import com.citibank.newcpb.bean.ResultTableBean;

public enum CustomerStatusEnum {
	
	ACTIVE("A", "Ativo"),
	INACTIVE("I","Inativo");

	private String value;
	private String desc;
	
	private CustomerStatusEnum(String value, String desc) {
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
	
    public static CustomerStatusEnum fromValue(String value) throws IllegalArgumentException {
        try {
                for (CustomerStatusEnum customerTypeEnum : CustomerStatusEnum.values()) {
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
        
        for(CustomerStatusEnum customerTypeEnum : CustomerStatusEnum.values()){
            ResultTableBean resultTableBean = new ResultTableBean();
            resultTableBean.setResultCode(customerTypeEnum.getValue());
            resultTableBean.setResultDescription(customerTypeEnum.getDesc());
            constitutionTypeList.add(resultTableBean);
  
        }
        
        return constitutionTypeList;
        
    }


	
	
}
