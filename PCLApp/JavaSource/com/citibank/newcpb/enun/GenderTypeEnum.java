package com.citibank.newcpb.enun;

import java.util.ArrayList;

import com.citibank.newcpb.bean.ResultTableBean;

public enum GenderTypeEnum {
	
	MASC("M", "Masculino"),
	FEM("F","Feminino");
	
	
	private String value;
	private String desc;
	
	private GenderTypeEnum(String value, String desc) {
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
	
    public static GenderTypeEnum fromValue(String value) throws IllegalArgumentException {
        try {
                for (GenderTypeEnum genderTypeEnum : GenderTypeEnum.values()) {
                        if (genderTypeEnum.getValue().toUpperCase().equals(value.toUpperCase())) {
                                return genderTypeEnum;
                        }
                }
                
                return null;
        } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Unknown enum value type :" + value);
        }
}
    
    public static ArrayList<ResultTableBean> getGenderTypeList() {
        
        ArrayList<ResultTableBean> genderTypeList = new ArrayList<ResultTableBean>() ;
        
        for(GenderTypeEnum genderTypeEnum : GenderTypeEnum.values()){
            ResultTableBean resultTableBean = new ResultTableBean();
            resultTableBean.setResultCode(genderTypeEnum.getValue());
            resultTableBean.setResultDescription(genderTypeEnum.getDesc());
            genderTypeList.add(resultTableBean);
  
        }
        
        return genderTypeList;
        
    }


	
	
}
