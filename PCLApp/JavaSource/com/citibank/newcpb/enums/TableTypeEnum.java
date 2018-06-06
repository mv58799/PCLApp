package com.citibank.newcpb.enums;

public enum TableTypeEnum {

	EFFECTIVE("CAD"),
	MOVEMENT("MOV"),
	HISTORIC("HIST"),
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
