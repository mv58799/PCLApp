package com.citibank.ods.modules.product.fundsubscription.form.valueobject;

import java.util.Date;

import com.citibank.ods.common.form.BaseForm;

@SuppressWarnings("serial")
public class FundSubscriptionImportHistoryVO extends BaseForm{

	private String tipoProduto;
	private Long codigo;
	private Date dataImportacao;
	private String nomeArquivo;
	private Integer registros;
	//FIXME Retirar construtores
	public FundSubscriptionImportHistoryVO() {}
	public FundSubscriptionImportHistoryVO(String tipoProduto, Long codigo, String dataImportacao, String nomeArquivo, Integer registros) {
	
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
