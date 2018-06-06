package com.citibank.ods.modules.product.fundsubscription.form;

import java.util.Date;

import com.citibank.ods.common.form.BaseForm;

@SuppressWarnings("serial")
public class FundSubscriptionImportApprovalForm extends BaseForm {
	private String tipoProduto;
	private Integer codigo;
	private Date dataImportacao;
	private String nomeArquivo;
	private Integer registros;
	private Boolean canApprove;
	
	public Boolean getCanApprove() {
		return canApprove;
	}
	public void setCanApprove(Boolean canApprove) {
		this.canApprove = canApprove;
	}
	public String getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
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
