package com.citibank.newcpb.security;

import java.util.HashMap;
import java.util.Map;

public class SGModuleBean {

	private Integer id;

	private String name;

	private Map<Integer, SGFunctionBean> functions;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Integer, SGFunctionBean> getFunctions() {
		return this.functions;
	}

	public void setFunctions(Map<Integer, SGFunctionBean> functions) {
		this.functions = functions;
	}

	public void addFunction(SGFunctionBean function) {
		if (this.functions == null) {
			this.functions = new HashMap<Integer, SGFunctionBean>();
		}
		this.functions.put(function.getId(), function);
	}
}
