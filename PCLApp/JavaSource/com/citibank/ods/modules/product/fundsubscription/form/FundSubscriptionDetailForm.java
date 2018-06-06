package com.citibank.ods.modules.product.fundsubscription.form;

import java.util.Collection;

import com.citibank.ods.common.form.BaseForm;

@SuppressWarnings("serial")
public class FundSubscriptionDetailForm  extends BaseForm {
	private String tipoProduto;
	private String protocolo;
	private String dataAbertura;
	private Integer custNbr;
	private String nomeCliente;
	private String assunto;
	private String evento;
	private String dataSolucao;
	private String fase;
	private String dataResolucaoFase;
	private String comentario;
	private Collection<String> atestadoDivergenciaList;
	private String atestadoDivergencia;
	private Collection<String> boletimReservaList;
	private String boletimReserva;
	private Collection<String> termoTreinamentoList;
	private String termoTreinamento;
	private Collection<String> documentacaoList;
	private String documentacao;
	private Collection<String> otaList;
	private String ota;
	private Integer codigoCorretora = null;
	private String conhecimentoProduto;
	private String ccDebito;
	private String cpfInvestidor;
	private String funcionalGerente;
	private String nivelRisco;
	private String nomeAprovador;
	private String codigoFundo;
	private String nomeFundo;
	private String nomeProduto;
	private Integer codigoInvestidor;
	private String nomeInvestidor;
	private String perfilGRB;
	private String tipoTransacao;
	private String valorNovo;
	private String valorInvestimento;
	private String acctNbr;

	private String operation;

	private String selectedCode;

	private Boolean isConsultName = false;
	
	private Boolean isReturnConsult = false;
	
	private Boolean clear = false;
	

	private Boolean blockConfirm = false;
	
	public Boolean getBlockConfirm() {
		return blockConfirm;
	}
	public void setBlockConfirm(Boolean blockCOnfirm) {
		this.blockConfirm = blockCOnfirm;
	}
	
	public Boolean getClear() {
		return clear;
	}
	public void setClear(Boolean clear) {
		this.clear = clear;
	}
	public Boolean getIsReturnConsult() {
		return isReturnConsult;
	}
	public void setIsReturnConsult(Boolean isReturnConsult) {
		this.isReturnConsult = isReturnConsult;
	}
	public Boolean getIsConsultName() {
		return isConsultName;
	}
	public void setIsConsultName(Boolean isConsultName) {
		this.isConsultName = isConsultName;
	}
	public String getSelectedCode() {
		return selectedCode;
	}
	public void setSelectedCode(String selectedCode) {
		this.selectedCode = selectedCode;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getAcctNbr() {
		return acctNbr;
	}
	public void setAcctNbr(String acctNbr) {
		this.acctNbr = acctNbr;
	}
	public String getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	public String getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	public String getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public Integer getCustNbr() {
		return custNbr;
	}
	public void setCustNbr(Integer custNbr) {
		this.custNbr = custNbr;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getDataSolucao() {
		return dataSolucao;
	}
	public void setDataSolucao(String dataSolucao) {
		this.dataSolucao = dataSolucao;
	}
	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
	public String getDataResolucaoFase() {
		return dataResolucaoFase;
	}
	public void setDataResolucaoFase(String dataResolucaoFase) {
		this.dataResolucaoFase = dataResolucaoFase;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Collection<String> getAtestadoDivergenciaList() {
		return atestadoDivergenciaList;
	}
	public void setAtestadoDivergenciaList(Collection<String> atestadoDivergenciaList) {
		this.atestadoDivergenciaList = atestadoDivergenciaList;
	}
	public String getAtestadoDivergencia() {
		return atestadoDivergencia;
	}
	public void setAtestadoDivergencia(String atestadoDivergencia) {
		this.atestadoDivergencia = atestadoDivergencia;
	}
	public Collection<String> getBoletimReservaList() {
		return boletimReservaList;
	}
	public void setBoletimReservaList(Collection<String> boletimReservaList) {
		this.boletimReservaList = boletimReservaList;
	}
	public String getBoletimReserva() {
		return boletimReserva;
	}
	public void setBoletimReserva(String boletimReserva) {
		this.boletimReserva = boletimReserva;
	}
	public Collection<String> getTermoTreinamentoList() {
		return termoTreinamentoList;
	}
	public void setTermoTreinamentoList(Collection<String> termoTreinamentoList) {
		this.termoTreinamentoList = termoTreinamentoList;
	}
	public String getTermoTreinamento() {
		return termoTreinamento;
	}
	public void setTermoTreinamento(String termoTreinamento) {
		this.termoTreinamento = termoTreinamento;
	}
	public Collection<String> getDocumentacaoList() {
		return documentacaoList;
	}
	public void setDocumentacaoList(Collection<String> documentacaoList) {
		this.documentacaoList = documentacaoList;
	}
	public String getDocumentacao() {
		return documentacao;
	}
	public void setDocumentacao(String documentacao) {
		this.documentacao = documentacao;
	}
	public Collection<String> getOtaList() {
		return otaList;
	}
	public void setOtaList(Collection<String> otaList) {
		this.otaList = otaList;
	}
	public String getOta() {
		return ota;
	}
	public void setOta(String ota) {
		this.ota = ota;
	}
	public Integer getCodigoCorretora() {
		return codigoCorretora;
	}
	public void setCodigoCorretora(Integer codigoCorretora) {
		this.codigoCorretora = codigoCorretora;
	}
	public String getConhecimentoProduto() {
		return conhecimentoProduto;
	}
	public void setConhecimentoProduto(String conhecimentoProduto) {
		this.conhecimentoProduto = conhecimentoProduto;
	}
	public String getCcDebito() {
		return ccDebito;
	}
	public void setCcDebito(String ccDebito) {
		this.ccDebito = ccDebito;
	}
	public String getCpfInvestidor() {
		return cpfInvestidor;
	}
	public void setCpfInvestidor(String cpfInvestidor) {
		this.cpfInvestidor = cpfInvestidor;
	}
	public String getFuncionalGerente() {
		return funcionalGerente;
	}
	public void setFuncionalGerente(String funcionalGerente) {
		this.funcionalGerente = funcionalGerente;
	}
	public String getNivelRisco() {
		return nivelRisco;
	}
	public void setNivelRisco(String nivelRisco) {
		this.nivelRisco = nivelRisco;
	}
	public String getNomeAprovador() {
		return nomeAprovador;
	}
	public void setNomeAprovador(String nomeAprovador) {
		this.nomeAprovador = nomeAprovador;
	}
	public String getCodigoFundo() {
		return codigoFundo;
	}
	public void setCodigoFundo(String codigoFundo) {
		this.codigoFundo = codigoFundo;
	}
	public String getNomeFundo() {
		return nomeFundo;
	}
	public void setNomeFundo(String nomeFundo) {
		this.nomeFundo = nomeFundo;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Integer getCodigoInvestidor() {
		return codigoInvestidor;
	}
	public void setCodigoInvestidor(Integer codigoInvestidor) {
		this.codigoInvestidor = codigoInvestidor;
	}
	public String getNomeInvestidor() {
		return nomeInvestidor;
	}
	public void setNomeInvestidor(String nomeInvestidor) {
		this.nomeInvestidor = nomeInvestidor;
	}
	public String getPerfilGRB() {
		return perfilGRB;
	}
	public void setPerfilGRB(String perfilGRB) {
		this.perfilGRB = perfilGRB;
	}
	public String getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	public String getValorNovo() {
		return valorNovo;
	}
	public void setValorNovo(String valorNovo) {
		this.valorNovo = valorNovo;
	}
	public String getValorInvestimento() {
		return valorInvestimento;
	}
	public void setValorInvestimento(String valorInvestimento) {
		this.valorInvestimento = valorInvestimento;
	}
	
	

}
