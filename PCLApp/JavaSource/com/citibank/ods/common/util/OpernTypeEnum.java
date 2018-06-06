package com.citibank.ods.common.util;

public enum OpernTypeEnum {


	INSERT("I"),
	UPDATE("A"),
	DELETE("E"),
	;
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String sufixo) {
		this.code = sufixo;
	}

	private OpernTypeEnum(String sufixo) {
		this.code = sufixo;
	}
}
