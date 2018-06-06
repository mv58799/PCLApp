package com.citibank.ods.modules.product.membershipTerm.functionality;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.common.util.Util;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.valueobject.TbgOfficerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplCustomerPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplTermAdhesionEntityVO;
import com.citibank.ods.modules.product.membershipTerm.form.MembershipTermDetailForm;
import com.citibank.ods.modules.product.membershipTerm.form.ProdutoTermoForm;
import com.citibank.ods.modules.product.membershipTerm.form.SegmentoForm;
import com.citibank.ods.modules.product.membershipTerm.functionality.valueobject.MembershipTermDetailFncVO;
import com.citibank.ods.modules.product.membershipTerm.functionality.valueobject.ProdutoTermoFncVO;
import com.citibank.ods.modules.product.membershipTerm.functionality.valueobject.SegmentoFncVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplTermAdhesionDAO;

public class MembershipTermDetailFnc extends BaseFnc implements ODSDetailFnc {

	public void insert(BaseFncVO fncVO_) {
		MembershipTermDetailFncVO fncVO = (MembershipTermDetailFncVO) fncVO_;

		OracleTplTermAdhesionDAO dao = new OracleTplTermAdhesionDAO();

		for (ProdutoTermoFncVO pt : fncVO.getListaProdutoTermo()) {
			TplTermAdhesionEntityVO vo = new TplTermAdhesionEntityVO();
			vo.setProdAcctCode(fncVO.getProdAcctCode());
			vo.setProdUnderAcctCode(fncVO.getProdUnderAcctCode());
			vo.setProdCode(pt.getProdCode());
			vo.setTrsrySysInd(fncVO.getTrsrySysInd());
			vo.setDpSysInd(fncVO.getDpSysInd());
			vo.setAdhTermTypeCode(pt.getAdhTermTypeCode());
			vo.setLastApprvDate(null);
			vo.setLastApprvUserId(null);
			vo.setLastUpdDate(new Date());
			vo.setLastUpdUserId(fncVO.getLoggedUser().getUserID());
			vo.setOpernTypeCode("I");

			dao.insert(vo, TableTypeEnum.MOVEMENT);
		}
	}

	public void update(BaseFncVO fncVO_) {
		MembershipTermDetailFncVO fncVO = (MembershipTermDetailFncVO) fncVO_;

		OracleTplTermAdhesionDAO dao = new OracleTplTermAdhesionDAO();

		for (ProdutoTermoFncVO pt : fncVO.getListaProdutoTermo()) {
			TplTermAdhesionEntityVO vo = new TplTermAdhesionEntityVO();
			vo.setProdAcctCode(fncVO.getProdAcctCode());
			vo.setProdUnderAcctCode(fncVO.getProdUnderAcctCode());
			vo.setProdCode(pt.getProdCode());
			vo.setTrsrySysInd(fncVO.getTrsrySysInd());
			vo.setDpSysInd(fncVO.getDpSysInd());
			vo.setAdhTermTypeCode(pt.getAdhTermTypeCode());
			vo.setLastApprvDate(null);
			vo.setLastApprvUserId(null);
			vo.setLastUpdDate(new Date());
			vo.setLastUpdUserId(fncVO.getLoggedUser().getUserID());
			vo.setOpernTypeCode("A");

			List<TplTermAdhesionEntityVO> inMOv = dao.findByProdAcctCodeAndProdUnderAcctCode(vo.getProdAcctCode(), vo.getProdUnderAcctCode(), TableTypeEnum.MOVEMENT);
			if (inMOv != null && inMOv.size() > 0 && inMOv.contains(vo)){
				dao.update(vo, TableTypeEnum.MOVEMENT);
			}else {
				dao.insert(vo, TableTypeEnum.MOVEMENT);
			}
		}

		for (TplTermAdhesionEntityVO eff : dao.findByProdAcctCodeAndProdUnderAcctCode(fncVO.getProdAcctCode(), fncVO.getProdUnderAcctCode(), TableTypeEnum.EFFECTIVE)) {

			if (!fncVO.getListaProdutoTermo().contains(new ProdutoTermoFncVO(eff.getProdCode()))) {
				eff.setLastUpdDate(new Date());
				eff.setLastUpdUserId(fncVO.getLoggedUser().getUserID());
				eff.setOpernTypeCode("E");
				
				List<TplTermAdhesionEntityVO> inMOv = dao.findByProdAcctCodeAndProdUnderAcctCode(eff.getProdAcctCode(), eff.getProdUnderAcctCode(), TableTypeEnum.MOVEMENT);
				if (inMOv != null && inMOv.size() > 0 && inMOv.contains(eff)){
					dao.update(eff, TableTypeEnum.MOVEMENT);
				}else {
					dao.insert(eff, TableTypeEnum.MOVEMENT);
				}
			}

		}
	}

	public void delete(BaseFncVO fncVO_) {
		MembershipTermDetailFncVO fncVO = (MembershipTermDetailFncVO) fncVO_;

		OracleTplTermAdhesionDAO dao = new OracleTplTermAdhesionDAO();

		for (ProdutoTermoFncVO pt : fncVO.getListaProdutoTermo()) {
			TplTermAdhesionEntityVO vo = new TplTermAdhesionEntityVO();
			vo.setProdAcctCode(fncVO.getProdAcctCode());
			vo.setProdUnderAcctCode(fncVO.getProdUnderAcctCode());
			vo.setProdCode(pt.getProdCode());
			vo.setTrsrySysInd(fncVO.getTrsrySysInd());
			vo.setDpSysInd(fncVO.getDpSysInd());
			vo.setAdhTermTypeCode(pt.getAdhTermTypeCode());
			vo.setLastApprvDate(null);
			vo.setLastApprvUserId(null);
			vo.setLastUpdDate(new Date());
			vo.setLastUpdUserId(fncVO.getLoggedUser().getUserID());
			vo.setOpernTypeCode("E");

			List<TplTermAdhesionEntityVO> inMOv = dao.findByProdAcctCodeAndProdUnderAcctCode(vo.getProdAcctCode(), vo.getProdUnderAcctCode(), TableTypeEnum.MOVEMENT);
			if (inMOv != null && inMOv.size() > 0  && inMOv.contains(vo)){
				dao.update(vo, TableTypeEnum.MOVEMENT);
			}else {
				dao.insert(vo, TableTypeEnum.MOVEMENT);
			}
		}
	}

	public void validateInsert(BaseFncVO fncVO_) {
		MembershipTermDetailFncVO fncVO = (MembershipTermDetailFncVO) fncVO_;

		if (fncVO.getListaProdutoTermo().isEmpty()) {
			fncVO_.addError(MembershipTermDetailFncVO.C_ERROR_TERM_ADHESION_LISTA_PRODUTO_VAZIA);
		}
	}

	public void validateUpdate(BaseFncVO fncVO_) {
		MembershipTermDetailFncVO fncVO = (MembershipTermDetailFncVO) fncVO_;

		if (fncVO.getListaProdutoTermo().isEmpty()) {
			fncVO_.addError(MembershipTermDetailFncVO.C_ERROR_TERM_ADHESION_LISTA_PRODUTO_VAZIA);
		}
	}

	public void validateDelete(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub
		
	}

	public void loadForConsult(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub
		
	}

	public void loadForInsert(BaseFncVO fncVO_) {
		MembershipTermDetailFncVO fncVO = (MembershipTermDetailFncVO) fncVO_;

		loadDefaultData(fncVO);
	}

	public void loadForUpdate(BaseFncVO fncVO_) {
		MembershipTermDetailFncVO fncVO = (MembershipTermDetailFncVO) fncVO_;

		
//			fncVO_.addError(MembershipTermListFncVO.C_ERROR_TERM_ADHESION_PENDENTE_APROVACAO);
//			return;
//		}
		OracleTplTermAdhesionDAO dao = new OracleTplTermAdhesionDAO();
		List<TplTermAdhesionEntityVO> findByCurAcctNbr = null;
		List<ProdutoTermoFncVO> listaProdutoTermo = new ArrayList<ProdutoTermoFncVO>();
		fncVO.setListaProdutoTermo(listaProdutoTermo);
		
		if (fncVO.getSelectedCode() != null) {
			String prodAcctNum = fncVO.getSelectedCode().substring(0, fncVO.getSelectedCode().indexOf(","));
			String prodAcctUnderNum = fncVO.getSelectedCode().substring(fncVO.getSelectedCode().indexOf(",") +1);
			fncVO.setProdAcctCode(Util.tryParseLong(prodAcctNum));
			fncVO.setProdUnderAcctCode( Util.tryParseLong(prodAcctUnderNum));
			findByCurAcctNbr = dao.findByProdAcctCodeAndProdUnderAcctCode(Util.tryParseLong(prodAcctNum), Util.tryParseLong(prodAcctUnderNum), TableTypeEnum.MOVEMENT);
			loadDefaultData(fncVO);
			
		}else {
			loadDefaultData(fncVO);
			findByCurAcctNbr = dao.findByCurAcctNbr(fncVO.getCurAcctNbr(), TableTypeEnum.EFFECTIVE);
		}

		
		for (TplTermAdhesionEntityVO t : findByCurAcctNbr) {
			fncVO.setTrsrySysInd(t.getTrsrySysInd());
			fncVO.setDpSysInd(t.getDpSysInd());

			ProdutoTermoFncVO o = new ProdutoTermoFncVO();
			o.setProdCode(t.getProdCode());
			o.setAdhTermTypeCode(t.getAdhTermTypeCode());
			if (!"E".equals(t.getOpernTypeCode()))
				listaProdutoTermo.add(o);
		}

	}

	public void loadForDelete(BaseFncVO fncVO_) {
		loadForUpdate(fncVO_);
	}

	@Override
	public void updateFncVOFromForm(ActionForm form_, BaseFncVO fncVO_) {
		MembershipTermDetailForm form = (MembershipTermDetailForm) form_;
		MembershipTermDetailFncVO fncVO = (MembershipTermDetailFncVO) fncVO_;

		try {
			fncVO.setReltnNbr(Long.parseLong(form.getReltnNbr()));
		} catch (NumberFormatException e) { }

		try {
			fncVO.setCurAcctNbr(Long.parseLong(form.getCurAcctNbr()));
		} catch (NumberFormatException e) { }

		fncVO.setCustFullName(form.getCustFullName());
		fncVO.setCustTypeCode(form.getCustTypeCode());

		try {
			fncVO.setCustCpfNbr(Long.parseLong(form.getCustCpfNbr()));
		} catch (NumberFormatException e) { }

		fncVO.setOffcrName(form.getOffcrName());

		try {
			fncVO.setOffcrNbr(Integer.parseInt(form.getOffcrNbr()));
		} catch (NumberFormatException e) { }

		try {
			fncVO.setPortfBrchCode(Integer.parseInt(form.getPortfBrchCode()));
		} catch (NumberFormatException e) { }

		fncVO.setAddrStateCode(form.getAddrStateCode());
		fncVO.setTrsrySysInd(form.getTrsrySysInd());
		fncVO.setDpSysInd(form.getDpSysInd());

		List<SegmentoFncVO> listaSegmento = new ArrayList<SegmentoFncVO>();
		fncVO.setListaSegmento(listaSegmento);

		if (form.getListaSegmento() != null) {
			for (SegmentoForm s : form.getListaSegmento()) {
				SegmentoFncVO o = new SegmentoFncVO();
				o.setSegCode(s.getSegCode());
				o.setSegName(s.getSegName());

				listaSegmento.add(o);
			}
		}

		fncVO.setSegCode(form.getSegCode());

		if (form.getArrayProdutoTermo() != null) {
			List<ProdutoTermoForm> listaProdutoTermo = new ArrayList<ProdutoTermoForm>();
			form.setListaProdutoTermo(listaProdutoTermo);

			for (String arrayProdutoTermo : form.getArrayProdutoTermo().split(";")) {
				String[] produtoTermo = arrayProdutoTermo.split(",");

				String produto = produtoTermo[0];
				String termo = produtoTermo[1];

				ProdutoTermoForm o = new ProdutoTermoForm();
				o.setProdCode(produto);
				o.setAdhTermTypeCode(termo);

				listaProdutoTermo.add(o);
			}
		}

		List<ProdutoTermoFncVO> listaProdutoTermo = new ArrayList<ProdutoTermoFncVO>();
		fncVO.setListaProdutoTermo(listaProdutoTermo);

		if (form.getListaProdutoTermo() != null) {
			for (ProdutoTermoForm p : form.getListaProdutoTermo()) {
				ProdutoTermoFncVO o = new ProdutoTermoFncVO();
				o.setProdCode(p.getProdCode());
				o.setAdhTermTypeCode(p.getAdhTermTypeCode());

				listaProdutoTermo.add(o);
			}
		}

		fncVO.setProdCode(form.getProdCode());
		fncVO.setAdhTermTypeCode(form.getAdhTermTypeCode());
		fncVO.setSelectedCode(form.getSelectedCode());
	}

	@Override
	public void updateFormFromFncVO(ActionForm form_, BaseFncVO fncVO_) {
		MembershipTermDetailForm form = (MembershipTermDetailForm) form_;
		MembershipTermDetailFncVO fncVO = (MembershipTermDetailFncVO) fncVO_;

		form.setReltnNbr(fncVO.getReltnNbr() == null ? null : String.valueOf(fncVO.getReltnNbr()));
		form.setCurAcctNbr(fncVO.getCurAcctNbr() == null ? null : String.valueOf(fncVO.getCurAcctNbr()));
		form.setCustFullName(fncVO.getCustFullName());
		form.setCustTypeCode(fncVO.getCustTypeCode());
		form.setCustCpfNbr(fncVO.getCustCpfNbr() == null ? null : String.valueOf(fncVO.getCustCpfNbr()));
		form.setOffcrName(fncVO.getOffcrName());
		form.setOffcrNbr(fncVO.getOffcrNbr() == null ? null : String.valueOf(fncVO.getOffcrNbr()));
		form.setPortfBrchCode(fncVO.getPortfBrchCode() == null ? null : String.valueOf(fncVO.getPortfBrchCode()));
		form.setAddrStateCode(fncVO.getAddrStateCode());
		form.setTrsrySysInd(fncVO.getTrsrySysInd());
		form.setDpSysInd(fncVO.getDpSysInd());

		List<SegmentoForm> listaSegmento = new ArrayList<SegmentoForm>();
		form.setListaSegmento(listaSegmento);

		if (fncVO.getListaSegmento() != null) {
			for (SegmentoFncVO s : fncVO.getListaSegmento()) {
				SegmentoForm o = new SegmentoForm();
				o.setSegCode(s.getSegCode());
				o.setSegName(s.getSegName());

				listaSegmento.add(o);
			}
		}

		form.setSegCode(fncVO.getSegCode());
		form.setSegName(fncVO.getSegName());
		List<ProdutoTermoForm> listaProdutoTermo = new ArrayList<ProdutoTermoForm>();
		form.setListaProdutoTermo(listaProdutoTermo);

		if (fncVO.getListaProdutoTermo() != null) {
			for (ProdutoTermoFncVO p : fncVO.getListaProdutoTermo()) {
				ProdutoTermoForm o = new ProdutoTermoForm();
				o.setProdCode(p.getProdCode());
				o.setAdhTermTypeCode(p.getAdhTermTypeCode());

				listaProdutoTermo.add(o);
			}
		}

		form.setProdCode(fncVO.getProdCode());
		form.setAdhTermTypeCode(fncVO.getAdhTermTypeCode());
		form.setSelectedCode(fncVO.getSelectedCode());
	}

	@Override
	public BaseFncVO createFncVO() {
		return new MembershipTermDetailFncVO();
	}

	private void loadDefaultData(MembershipTermDetailFncVO fncVO) {
		OracleTplTermAdhesionDAO dao = new OracleTplTermAdhesionDAO();

		BaseTo3ProductAccountEntity productAccount = null;
		if (fncVO.getCurAcctNbr()!= null)
			productAccount = dao.getProductAccountByCurAcctNbr(fncVO.getCurAcctNbr());
		else {
			productAccount = dao.getProductAccountByProdAcctCodeAndProdUnderAcctCode(fncVO.getProdAcctCode(), fncVO.getProdUnderAcctCode());
			fncVO.setCurAcctNbr(productAccount.getData().getCurAcctNbr().longValue());
		}
		Long prodAcctCode = productAccount.getData().getProdAcctCode().longValue();
		Long prodUnderAcctCode = productAccount.getData().getProdUnderAcctCode().longValue();
		Long custNbr = productAccount.getData().getCustNbr().longValue();
		Long reltnNbr = productAccount.getData().getReltnNbr().longValue();

		fncVO.setProdAcctCode(prodAcctCode);
		fncVO.setProdUnderAcctCode(prodUnderAcctCode);
		fncVO.setReltnNbr(reltnNbr);

		TplCustomerPrvtEntityVO customer = dao.getCustomer(custNbr);
		if (customer != null) {
			fncVO.setCustFullName(customer.getCustFullNameText());
			fncVO.setCustTypeCode(customer.getCustTypeCode());
			fncVO.setCustCpfNbr(customer.getCustCpfCnpjNbr().longValue());
		}

		TbgOfficerEntityVO officer = dao.getOfficer(reltnNbr);
		if (officer != null) {
			fncVO.setOffcrName(officer.getOffcrNameText());
			fncVO.setOffcrNbr(officer.getOffcrNbr().intValue());
		}

		fncVO.setPortfBrchCode(dao.getPortfBrchCode(reltnNbr));

		fncVO.setAddrStateCode(dao.getAddrStateCode(custNbr));

		List<SegmentoFncVO> listaSegmento = new ArrayList<SegmentoFncVO>();
		fncVO.setListaSegmento(listaSegmento);
		fncVO.setSegCode(productAccount.getData().getSegCode());
		fncVO.setSegName("050".equals(fncVO.getSegCode())?"Private": "Gold");

	}

}
