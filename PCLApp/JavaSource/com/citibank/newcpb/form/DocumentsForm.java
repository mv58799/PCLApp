package com.citibank.newcpb.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.citibank.newcpb.bean.ResultTableBean;
import com.citibank.newcpb.vo.DocumentsVO;
import com.citibank.newcpb.vo.StatusCpfCnpjVO;

public class DocumentsForm extends BaseForm implements Serializable {
	
	
private static final long serialVersionUID = 1L;
	
	private String screenTitle;
	
	private String filterNumberEM;
	private String filterCpfCnpj;
	private String filterName;
	private String filterTitulo;
	private String filterArquivo;
	private String filterMonthYear;
	private String filterProcPA;
	
	private StatusCpfCnpjVO selectedRegister;
	private ArrayList<DocumentsVO> resultList;
	
	private boolean isUpdate;
	private boolean isApprove;
	private boolean isOnlyView;
	private boolean isFromApprove;
	private String idDiffList;
	
	//Combos da tela 
	private ArrayList<ResultTableBean> tituloValues;
	
	public ArrayList<DocumentsVO> getResultList() {
		return resultList;
	}
	public void setResultList(ArrayList<DocumentsVO> resultList) {
		this.resultList = resultList;
	}
	public boolean isApprove() {
		return isApprove;
	}
	public void setApprove(boolean isApprove) {
		this.isApprove = isApprove;
	}
	public boolean isOnlyView() {
		return isOnlyView;
	}
	public void setOnlyView(boolean isOnlyView) {
		this.isOnlyView = isOnlyView;
	}
	public String getIdDiffList() {
		return idDiffList;
	}
	public void setIdDiffList(String idDiffList) {
		this.idDiffList = idDiffList;
	}
	public String getFilterNumberEM() {
		return filterNumberEM;
	}
	public void setFilterNumberEM(String filterNumberEM) {
		this.filterNumberEM = filterNumberEM;
	}
	public String getScreenTitle() {
		return screenTitle;
	}
	public void setScreenTitle(String screenTitle) {
		this.screenTitle = screenTitle;
	}
	public boolean isUpdate() {
		return isUpdate || isOnlyView;
	}
	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	public String getFilterCpfCnpj() {
		return filterCpfCnpj;
	}
	public void setFilterCpfCnpj(String filterCpfCnpj) {
		this.filterCpfCnpj = filterCpfCnpj;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public boolean isFromApprove() {
		return isFromApprove;
	}
	public void setFromApprove(boolean isFromApprove) {
		this.isFromApprove = isFromApprove;
	}
	public String getFilterTitulo() {
		return filterTitulo;
	}
	public void setFilterTitulo(String filterTitulo) {
		this.filterTitulo = filterTitulo;
	}
	
	public String getFilterArquivo() {
		return filterArquivo;
	}
	public void setFilterArquivo(String filterArquivo) {
		this.filterArquivo = filterArquivo;
	}
	
	public String getFilterProcPA() {
		return filterProcPA;
	}
	public void setFilterProcPA(String filterProcPA) {
		this.filterProcPA = filterProcPA;
	}
	public String getFilterMonthYear() {
		return filterMonthYear;
	}
	public void setFilterMonthYear(String filterMonthYear) {
		this.filterMonthYear = filterMonthYear;
	}
	public ArrayList<ResultTableBean> getTituloValues() {
		return tituloValues;
	}
	public void setTituloValues(ArrayList<ResultTableBean> tituloValues) {
		this.tituloValues = tituloValues;
	}
	public StatusCpfCnpjVO getSelectedRegister() {
		return selectedRegister;
	}
	public void setSelectedRegister(StatusCpfCnpjVO selectedRegister) {
		this.selectedRegister = selectedRegister;
	}
}
