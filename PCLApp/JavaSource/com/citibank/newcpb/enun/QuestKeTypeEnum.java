package com.citibank.newcpb.enun;

import java.util.ArrayList;

import com.citibank.newcpb.bean.ResultTableBean;


public enum QuestKeTypeEnum {
	
	PROD("PROD", "Conhecimento e Experiência em Produtos"),
	FMA("FMA","Frequência Média ao Ano"),
	VMA("VMA","Volume Médio ao Ano");
	
	
	private String value;
	private String desc;
	
	private QuestKeTypeEnum(String value, String desc) {
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
	
    public static QuestKeTypeEnum fromValue(String value) throws IllegalArgumentException {
        try {
                for (QuestKeTypeEnum customerTypeEnum : QuestKeTypeEnum.values()) {
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
        
        for(QuestKeTypeEnum accountTypeEnum : QuestKeTypeEnum.values()){
            ResultTableBean resultTableBean = new ResultTableBean();
            resultTableBean.setResultCode(accountTypeEnum.getValue());
            resultTableBean.setResultDescription(accountTypeEnum.getDesc());
            accountTypeList.add(resultTableBean);
  
        }
        
        return accountTypeList;
        
    }
    	
	
}
