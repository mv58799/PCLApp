package com.citibank.ods.modules.product.membershipTerm.functionality.valueobject;

import java.util.List;

import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

public class MembershipTermDetailFncVO extends BaseODSFncVO {

	private static final long serialVersionUID = 1L;

	private Long prodAcctCode;
	private Long prodUnderAcctCode;
	private Long reltnNbr;
	private Long curAcctNbr;
	private String custFullName;
	private String custTypeCode;
	private Long custCpfNbr;
	private String offcrName;
	private Integer offcrNbr;
	private Integer portfBrchCode;
	private String addrStateCode;
	private String trsrySysInd;
	private String dpSysInd;
	private List<SegmentoFncVO> listaSegmento;
	private String segCode;
	private String segName;
	
	private List<ProdutoTermoFncVO> listaProdutoTermo;
	private String prodCode;
	private String adhTermTypeCode;
	private String selectedCode;

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

	public Long getProdAcctCode() {
		return prodAcctCode;
	}

	public void setProdAcctCode(Long prodAcctCode) {
		this.prodAcctCode = prodAcctCode;
	}

	public Long getProdUnderAcctCode() {
		return prodUnderAcctCode;
	}

	public void setProdUnderAcctCode(Long prodUnderAcctCode) {
		this.prodUnderAcctCode = prodUnderAcctCode;
	}

	public Long getReltnNbr() {
		return reltnNbr;
	}

	public void setReltnNbr(Long reltnNbr) {
		this.reltnNbr = reltnNbr;
	}

	public Long getCurAcctNbr() {
		return curAcctNbr;
	}

	public void setCurAcctNbr(Long curAcctNbr) {
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

	public Long getCustCpfNbr() {
		return custCpfNbr;
	}

	public void setCustCpfNbr(Long custCpfNbr) {
		this.custCpfNbr = custCpfNbr;
	}

	public String getOffcrName() {
		return offcrName;
	}

	public void setOffcrName(String offcrName) {
		this.offcrName = offcrName;
	}

	public Integer getOffcrNbr() {
		return offcrNbr;
	}

	public void setOffcrNbr(Integer offcrNbr) {
		this.offcrNbr = offcrNbr;
	}

	public Integer getPortfBrchCode() {
		return portfBrchCode;
	}

	public void setPortfBrchCode(Integer portfBrchCode) {
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

	public List<SegmentoFncVO> getListaSegmento() {
		return listaSegmento;
	}

	public void setListaSegmento(List<SegmentoFncVO> listaSegmento) {
		this.listaSegmento = listaSegmento;
	}

	public String getSegCode() {
		return segCode;
	}

	public void setSegCode(String segCode) {
		this.segCode = segCode;
	}

	public List<ProdutoTermoFncVO> getListaProdutoTermo() {
		return listaProdutoTermo;
	}

	public void setListaProdutoTermo(List<ProdutoTermoFncVO> listaProdutoTermo) {
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

}
