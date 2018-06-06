
package com.citibank.ods.modules.product.fundsubscription.functionality.valueobject;

import java.util.Date;

import com.citibank.ods.common.functionality.valueobject.BaseFncVO;

/**
 * @author citi
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
@SuppressWarnings("serial")
public class FundSubscriptionDetailApprovalFncVO extends BaseFncVO {

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
	private String atestadoDivergencia;
	private String boletimReserva;
	private String termoTreinamento;
	private String documentacao;
	private String ota;
	private String codigoCorretora;
	private String conhecimentoProduto;
	private String ccDebito;
	private String cpfInvestidor;
	private String funcionalGerente;
	private String nivelRisco;
	private String nomeAprovador;
	private String codigoFundo;
	private String nomeFundo;
	private String nomeProduto;
	private Long codigoInvestidor;
	private String nomeInvestidor;
	private String perfilGRB;
	private String tipoTransacao;
	private String valorNovo;
	private String valorInvestimento;
	private String selectedCode;
	private String action;
	private Boolean canApprove;
	private Long codigo;
	private Date dataImportacao;
	private String nomeArquivo;
	private Integer registros;
	
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
	public Boolean getCanApprove() {
		return canApprove;
	}
	public void setCanApprove(Boolean canApprove) {
		this.canApprove = canApprove;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
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
	public String getAtestadoDivergencia() {
		return atestadoDivergencia;
	}
	public void setAtestadoDivergencia(String atestadoDivergencia) {
		this.atestadoDivergencia = atestadoDivergencia;
	}
	public String getBoletimReserva() {
		return boletimReserva;
	}
	public void setBoletimReserva(String boletimReserva) {
		this.boletimReserva = boletimReserva;
	}
	public String getTermoTreinamento() {
		return termoTreinamento;
	}
	public void setTermoTreinamento(String termoTreinamento) {
		this.termoTreinamento = termoTreinamento;
	}
	public String getDocumentacao() {
		return documentacao;
	}
	public void setDocumentacao(String documentacao) {
		this.documentacao = documentacao;
	}
	public String getOta() {
		return ota;
	}
	public void setOta(String ota) {
		this.ota = ota;
	}
	public String getCodigoCorretora() {
		return codigoCorretora;
	}
	public void setCodigoCorretora(String codigoCorretora) {
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
	public Long getCodigoInvestidor() {
		return codigoInvestidor;
	}
	public void setCodigoInvestidor(Long codigoInvestidor) {
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
