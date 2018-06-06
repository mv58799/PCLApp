package com.citibank.newcpb.enun;


public enum AddressTypeRegisterEnum {
	
	RESIDENTIAL("R", "Residencial"),
	COMMERCIAL("C","Comercial"),
	OTHER("O","Outros");
	
	private String value;
	private String desc;
	
	private AddressTypeRegisterEnum(String value, String desc) {
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
	
    public static AddressTypeRegisterEnum fromValue(String value) throws IllegalArgumentException {
        try {
                for (AddressTypeRegisterEnum customerTypeEnum : AddressTypeRegisterEnum.values()) {
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
