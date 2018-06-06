package com.citibank.ods.common.util;

public enum TableTypeEnum {

	EFFECTIVE(""),
	MOVEMENT("_MOV"),
	HISTORIC("_HIST"),
	;
	
	private String sufixo;

	public String getSufixo() {
		return sufixo;
	}

	public void setSufixo(String sufixo) {
		this.sufixo = sufixo;
	}

	private TableTypeEnum(String sufixo) {
		this.sufixo = sufixo;
	}
}
