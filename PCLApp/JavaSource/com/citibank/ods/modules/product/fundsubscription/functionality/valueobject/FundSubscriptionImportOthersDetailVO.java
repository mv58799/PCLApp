package com.citibank.ods.modules.product.fundsubscription.functionality.valueobject;

import java.util.Date;

import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.broker.functionality.valueobject.CellProperty;

public class FundSubscriptionImportOthersDetailVO extends BaseFncVO implements IFundSubscriptionImportVO{
	private static final long serialVersionUID = 1L;
	@CellProperty(value=0, maxLength=5, isMandatory=true, mustMatch="^\\d+$")
	private Long protocolo;
	@CellProperty(value=1)
	private Date dataDeAbertura;
	@CellProperty(2)
	private String nomeDoCliente;
	@CellProperty(3)
	private String assunto;
	@CellProperty(4)
	private String evento;
	@CellProperty(value=5)
	private Date dataDeSolucao;
	@CellProperty(6)
	private String fase;
	@CellProperty(value=7)
	private Date dataDaResolucaoDaFase;
	@CellProperty(8)
	private String comentario;
	@CellProperty(9)
	private Boolean attestationDeMismatch;
	@CellProperty(10)
	private Boolean boletimReservaOK;
	@CellProperty(11)
	private String conhecimentoDoProduto;
	@CellProperty(12)
	private Long contaCorrenteDoDebito;
	@CellProperty(value=13, maxLength=10)
	private Long cpfDoInvestidor;
	@CellProperty(14)
	private String funcionalDoGerente;
	@CellProperty(15)
	private Long nivelDeRiscoDoProduto;
	@CellProperty(16)
	private String nomeDoAprovadorDoMismatch;
	@CellProperty(17)
	private String nomeInvestidor;
	@CellProperty(18)
	private String nomeDoFundo;//Produto
	@CellProperty(19)
	private String perfilDoClienteGRB;
	@CellProperty(20)
	private Double quantoDesteValorNovo;
	@CellProperty(21)
	private Boolean termoDeTreinamentoOK;
	@CellProperty(22)
	private String tipoDeTransacao;
	@CellProperty(23)
	private Double valorDoInvestimento;
	
	public Long getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(Long protocolo) {
		this.protocolo = protocolo;
	}
	public Date getDataDeAbertura() {
		return dataDeAbertura;
	}
	public void setDataDeAbertura(Date dataDeAbertura) {
		this.dataDeAbertura = dataDeAbertura;
	}
	public String getNomeDoCliente() {
		return nomeDoCliente;
	}
	public void setNomeDoCliente(String nomeDoCliente) {
		this.nomeDoCliente = nomeDoCliente;
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
	public Date getDataDeSolucao() {
		return dataDeSolucao;
	}
	public void setDataDeSolucao(Date dataDeSolucao) {
		this.dataDeSolucao = dataDeSolucao;
	}
	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
	public Date getDataDaResolucaoDaFase() {
		return dataDaResolucaoDaFase;
	}
	public void setDataDaResolucaoDaFase(Date dataDaResolucaoDaFase) {
		this.dataDaResolucaoDaFase = dataDaResolucaoDaFase;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Boolean getAttestationDeMismatch() {
		return attestationDeMismatch;
	}
	public void setAttestationDeMismatch(Boolean attestationDeMismatch) {
		this.attestationDeMismatch = attestationDeMismatch;
	}
	public Boolean getBoletimReservaOK() {
		return boletimReservaOK;
	}
	public void setBoletimReservaOK(Boolean boletimReservaOK) {
		this.boletimReservaOK = boletimReservaOK;
	}
	
	public String getConhecimentoDoProduto() {
		return conhecimentoDoProduto;
	}
	public void setConhecimentoDoProduto(String conhecimentoDoProduto) {
		this.conhecimentoDoProduto = conhecimentoDoProduto;
	}
	public Long getContaCorrenteDoDebito() {
		return contaCorrenteDoDebito;
	}
	public void setContaCorrenteDoDebito(Long contaCorrenteDoDebito) {
		this.contaCorrenteDoDebito = contaCorrenteDoDebito;
	}
	public Long getCpfDoInvestidor() {
		return cpfDoInvestidor;
	}
	public void setCpfDoInvestidor(Long cpfDoInvestidor) {
		this.cpfDoInvestidor = cpfDoInvestidor;
	}
	public String getFuncionalDoGerente() {
		return funcionalDoGerente;
	}
	public void setFuncionalDoGerente(String funcionalDoGerente) {
		this.funcionalDoGerente = funcionalDoGerente;
	}
	public Long getNivelDeRiscoDoProduto() {
		return nivelDeRiscoDoProduto;
	}
	public void setNivelDeRiscoDoProduto(Long nivelDeRiscoDoProduto) {
		this.nivelDeRiscoDoProduto = nivelDeRiscoDoProduto;
	}
	public String getNomeDoAprovadorDoMismatch() {
		return nomeDoAprovadorDoMismatch;
	}
	public void setNomeDoAprovadorDoMismatch(String nomeDoAprovadorDoMismatch) {
		this.nomeDoAprovadorDoMismatch = nomeDoAprovadorDoMismatch;
	}
	public String getNomeDoFundo() {
		return nomeDoFundo;
	}
	public void setNomeDoFundo(String nomeDoFundo) {
		this.nomeDoFundo = nomeDoFundo;
	}
	
	public String getPerfilDoClienteGRB() {
		return perfilDoClienteGRB;
	}
	public void setPerfilDoClienteGRB(String perfilDoClienteGRB) {
		this.perfilDoClienteGRB = perfilDoClienteGRB;
	}
	public Double getQuantoDesteValorNovo() {
		return quantoDesteValorNovo;
	}
	public void setQuantoDesteValorNovo(Double quantoDesteValorNovo) {
		this.quantoDesteValorNovo = quantoDesteValorNovo;
	}
	public Boolean getTermoDeTreinamentoOK() {
		return termoDeTreinamentoOK;
	}
	public void setTermoDeTreinamentoOK(Boolean termoDeTreinamentoOK) {
		this.termoDeTreinamentoOK = termoDeTreinamentoOK;
	}
	public String getTipoDeTransacao() {
		return tipoDeTransacao;
	}
	public void setTipoDeTransacao(String tipoDeTransacao) {
		this.tipoDeTransacao = tipoDeTransacao;
	}
	public Double getValorDoInvestimento() {
		return valorDoInvestimento;
	}
	public void setValorDoInvestimento(Double valorDoInvestimento) {
		this.valorDoInvestimento = valorDoInvestimento;
	}
	@Override
	public String toString() {
		return "FundSubscriptionImportOthersDetailVO [protocolo=" + protocolo
				+ ", dataDeAbertura=" + dataDeAbertura + ", nomeDoCliente="
				+ nomeDoCliente + ", assunto=" + assunto + ", evento=" + evento
				+ ", dataDeSolucao=" + dataDeSolucao + ", fase=" + fase
				+ ", dataDaResolucaoDaFase=" + dataDaResolucaoDaFase
				+ ", comentario=" + comentario + ", attestationDeMismatch="
				+ attestationDeMismatch + ", boletimReservaOK="
				+ boletimReservaOK + ", conhecimentoDoProduto="
				+ conhecimentoDoProduto + ", contaCorrenteDoDebito="
				+ contaCorrenteDoDebito + ", cpfDoInvestidor="
				+ cpfDoInvestidor + ", funcionalDoGerente="
				+ funcionalDoGerente + ", nivelDeRiscoDoProduto="
				+ nivelDeRiscoDoProduto + ", nomeDoAprovadorDoMismatch="
				+ nomeDoAprovadorDoMismatch + ", nomeInvestidor="
				+ nomeInvestidor + ", nomeDoFundo=" + nomeDoFundo
				+ ", perfilDoClienteGRB=" + perfilDoClienteGRB
				+ ", quantoDesteValorNovo=" + quantoDesteValorNovo
				+ ", termoDeTreinamentoOK=" + termoDeTreinamentoOK
				+ ", tipoDeTransacao=" + tipoDeTransacao
				+ ", valorDoInvestimento=" + valorDoInvestimento + "]";
	}
	public String getCodigoCorretora() {
		return null;
	}
	public void setCodigoCorretora(String codigoCorretora) {
		
	}
	public String getNomeDoInvestidor() {
		return nomeInvestidor;
	}
	public void setNomeDoInvestidor(String nomeDoInvestidor) {
		this.nomeInvestidor = nomeDoInvestidor;
	}
}
