package com.citibank.newcpb.enun;


public enum CountryTypeRegisterEnum {
	
	FISCAL_ADDRESS("R", "Residencia Fiscal"),
	CITIZENSHIP("C","Cidadania");

	private String value;
	private String desc;
	
	private CountryTypeRegisterEnum(String value, String desc) {
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
	
    public static CountryTypeRegisterEnum fromValue(String value) throws IllegalArgumentException {
        try {
                for (CountryTypeRegisterEnum customerTypeEnum : CountryTypeRegisterEnum.values()) {
                        if (customerTypeEnum.getValue().toUpperCase().equals(value.toUpperCase())) {
                                return customerTypeEnum;
                        }
                }
                
                return null;
        } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Unknown enum value type :" + value);
        }
    }
    
	
}
