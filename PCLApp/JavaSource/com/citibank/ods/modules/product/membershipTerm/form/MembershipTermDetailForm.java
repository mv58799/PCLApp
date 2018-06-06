package com.citibank.ods.modules.product.membershipTerm.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.form.BaseForm;

public class MembershipTermDetailForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private String reltnNbr;
	private String curAcctNbr;
	private String custFullName;
	private String custTypeCode;
	private String custCpfNbr;
	private String offcrName;
	private String offcrNbr;
	private String portfBrchCode;
	private String addrStateCode;
	private String trsrySysInd;
	private String dpSysInd;
	private List<SegmentoForm> listaSegmento;
	private String segCode;
	private List<ProdutoTermoForm> listaProdutoTermo;
	private String prodCode;
	private String adhTermTypeCode;
	private String selectedCode;
	private String arrayProdutoTermo;

	private String segName;
	
	private Boolean canApprove;
	
	public Boolean getCanApprove() {
		return canApprove;
	}

	public void setCanApprove(Boolean canApprove) {
		this.canApprove = canApprove;
	}

	public String getSegName() {
		return segName;
	}

	public void setSegName(String segName) {
		this.segName = segName;
	}

	public String getReltnNbr() {
		return reltnNbr;
	}

	public void setReltnNbr(String reltnNbr) {
		this.reltnNbr = reltnNbr;
	}

	public String getCurAcctNbr() {
		return curAcctNbr;
	}

	public void setCurAcctNbr(String curAcctNbr) {
		this.curAcctNbr = curAcctNbr;
	}

	public String getCustFullName() {
		return custFullName;
	}

	public void setCustFullName(String custFullName) {
		this.custFullName = custFullName;
	}

	public String getCustTypeCode() {
		return custTypeCode;
	}

	public void setCustTypeCode(String custTypeCode) {
		this.custTypeCode = custTypeCode;
	}

	public String getCustCpfNbr() {
		return custCpfNbr;
	}

	public void setCustCpfNbr(String custCpfNbr) {
		this.custCpfNbr = custCpfNbr;
	}

	public String getOffcrName() {
		return offcrName;
	}

	public void setOffcrName(String offcrName) {
		this.offcrName = offcrName;
	}

	public String getOffcrNbr() {
		return offcrNbr;
	}

	public void setOffcrNbr(String offcrNbr) {
		this.offcrNbr = offcrNbr;
	}

	public String getPortfBrchCode() {
		return portfBrchCode;
	}

	public void setPortfBrchCode(String portfBrchCode) {
		this.portfBrchCode = portfBrchCode;
	}

	public String getAddrStateCode() {
		return addrStateCode;
	}

	public void setAddrStateCode(String addrStateCode) {
		this.addrStateCode = addrStateCode;
	}

	public String getTrsrySysInd() {
		return trsrySysInd;
	}

	public void setTrsrySysInd(String trsrySysInd) {
		this.trsrySysInd = trsrySysInd;
	}

	public String getDpSysInd() {
		return dpSysInd;
	}

	public void setDpSysInd(String dpSysInd) {
		this.dpSysInd = dpSysInd;
	}

	public List<SegmentoForm> getListaSegmento() {
		return listaSegmento;
	}

	public void setListaSegmento(List<SegmentoForm> listaSegmento) {
		this.listaSegmento = listaSegmento;
	}

	public String getSegCode() {
		return segCode;
	}

	public void setSegCode(String segCode) {
		this.segCode = segCode;
	}

	public List<ProdutoTermoForm> getListaProdutoTermo() {
		return listaProdutoTermo;
	}

	public void setListaProdutoTermo(List<ProdutoTermoForm> listaProdutoTermo) {
		this.listaProdutoTermo = listaProdutoTermo;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getAdhTermTypeCode() {
		return adhTermTypeCode;
	}

	public void setAdhTermTypeCode(String adhTermTypeCode) {
		this.adhTermTypeCode = adhTermTypeCode;
	}

	public String getSelectedCode() {
		return selectedCode;
	}

	public void setSelectedCode(String selectedCode) {
		this.selectedCode = selectedCode;
	}

	public String getArrayProdutoTermo() {
		return arrayProdutoTermo;
	}

	public void setArrayProdutoTermo(String arrayProdutoTermo) {
		this.arrayProdutoTermo = arrayProdutoTermo;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);

		setTrsrySysInd("N");
		setDpSysInd("N");
	}
}
