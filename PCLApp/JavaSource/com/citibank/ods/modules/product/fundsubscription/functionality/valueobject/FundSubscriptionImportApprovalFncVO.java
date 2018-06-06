package com.citibank.ods.modules.product.fundsubscription.functionality.valueobject;

import java.util.Date;

import com.citibank.ods.common.functionality.valueobject.BaseFncVO;

@SuppressWarnings("serial")
public class FundSubscriptionImportApprovalFncVO extends BaseFncVO {
	private String tipoProduto;
	private Long codigo;
	private Date dataImportacao;
	private String nomeArquivo;
	private Integer registros;
	private String selectedCode;
	private Boolean canApprove;
	
	public Boolean getCanApprove() {
		return canApprove;
	}
	public void setCanApprove(Boolean canApprove) {
		this.canApprove = canApprove;
	}
	public String getSelectedCode() {
		return selectedCode;
	}
	public void setSelectedCode(String selectedCode) {
		this.selectedCode = selectedCode;
	}
	public String getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Date getDataImportacao() {
		return dataImportacao;
	}
	public void setDataImportacao(Date dataImportacao) {
		this.dataImportacao = dataImportacao;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public Integer getRegistros() {
		return registros;
	}
	public void setRegistros(Integer registros) {
		this.registros = registros;
	}
	
	
}
