package com.citibank.newcpb.enun;

import java.util.ArrayList;

import com.citibank.newcpb.bean.ResultTableBean;

public enum DocumentsEnum {

	DOCINI("D", "Documentação inicial"),
	MANDOC("M","Manutenção da documentação"),
	REVDOC("R","Revisão da documentação");

	private String value;
	private String desc;
	
	private DocumentsEnum(String value, String desc) {
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
	
    public static DocumentsEnum fromValue(String value) throws IllegalArgumentException {
        try {
                for (DocumentsEnum customerTypeEnum : DocumentsEnum.values()) {
                        if (customerTypeEnum.getValue().toUpperCase().equals(value.toUpperCase())) {
                                return customerTypeEnum;
                        }
                }
                
                return null;
        } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Unknown enum value type :" + value);
        }
}
    
    public static ArrayList<ResultTableBean> getTypeList() {
        
        ArrayList<ResultTableBean> constitutionTypeList = new ArrayList<ResultTableBean>() ;
        
        for(DocumentsEnum customerTypeEnum : DocumentsEnum.values()){
            ResultTableBean resultTableBean = new ResultTableBean();
            resultTableBean.setResultCode(customerTypeEnum.getValue());
            resultTableBean.setResultDescription(customerTypeEnum.getDesc());
            constitutionTypeList.add(resultTableBean);
  
        }
        
        return constitutionTypeList;
        
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
