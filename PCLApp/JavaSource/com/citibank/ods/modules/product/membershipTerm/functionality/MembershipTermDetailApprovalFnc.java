package com.citibank.ods.modules.product.membershipTerm.functionality;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.TableTypeEnum;
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

public class MembershipTermDetailApprovalFnc extends BaseFnc implements ODSMovementDetailFnc {

	public void update(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub

	}

	public void approve(BaseFncVO fncVO_) {
		MembershipTermDetailFncVO fncVO = (MembershipTermDetailFncVO) fncVO_;

		OracleTplTermAdhesionDAO dao = new OracleTplTermAdhesionDAO();

		String[] keys = fncVO.getSelectedCode().split(",");

		Long prodAcctCode = Long.parseLong(keys[0]);
		Long prodUnderAcctCode = Long.parseLong(keys[1]);
		List<TplTermAdhesionEntityVO> listTermInMov = dao.findByProdAcctCodeAndProdUnderAcctCode(prodAcctCode, prodUnderAcctCode, TableTypeEnum.MOVEMENT);
		List<TplTermAdhesionEntityVO> listTermInEffe = dao.findByProdAcctCodeAndProdUnderAcctCode(prodAcctCode, prodUnderAcctCode, TableTypeEnum.EFFECTIVE);
		@SuppressWarnings("unchecked")
		Collection<TplTermAdhesionEntityVO> subtract = (Collection<TplTermAdhesionEntityVO>)CollectionUtils.subtract(listTermInEffe, listTermInMov);
		for (TplTermAdhesionEntityVO tplTermAdhesionEntityVO : subtract) {
			tplTermAdhesionEntityVO.setOpernTypeCode("E");
			listTermInMov.add(tplTermAdhesionEntityVO);
		}
		for (TplTermAdhesionEntityVO mov : listTermInMov) {
			mov.setLastApprvDate(new Date());
			mov.setLastApprvUserId(fncVO.getLoggedUser().getUserID());

			TplTermAdhesionEntityVO eff = dao.findByKey(mov.getProdAcctCode(), mov.getProdUnderAcctCode(), mov.getProdCode(), mov.getSysCode(), mov.getSysSegCode(), TableTypeEnum.EFFECTIVE);

			if ("I".equals(mov.getOpernTypeCode())) {
				dao.insert(mov, TableTypeEnum.EFFECTIVE);

				dao.delete(mov.getProdAcctCode(), mov.getProdUnderAcctCode(), mov.getProdCode(), mov.getSysCode(), mov.getSysSegCode(), TableTypeEnum.MOVEMENT);

			} else if ("A".equals(mov.getOpernTypeCode())) {
				if (eff == null) {
					dao.insert(mov, TableTypeEnum.EFFECTIVE);
				} else {
					dao.insert(eff, TableTypeEnum.HISTORIC);

					dao.update(mov, TableTypeEnum.EFFECTIVE);
				}

				dao.delete(mov.getProdAcctCode(), mov.getProdUnderAcctCode(), mov.getProdCode(), mov.getSysCode(), mov.getSysSegCode(), TableTypeEnum.MOVEMENT);

			} else if ("E".equals(mov.getOpernTypeCode())) {
				dao.insert(eff, TableTypeEnum.HISTORIC);

				dao.delete(mov.getProdAcctCode(), mov.getProdUnderAcctCode(), mov.getProdCode(), mov.getSysCode(), mov.getSysSegCode(), TableTypeEnum.EFFECTIVE);

				dao.delete(mov.getProdAcctCode(), mov.getProdUnderAcctCode(), mov.getProdCode(), mov.getSysCode(), mov.getSysSegCode(), TableTypeEnum.MOVEMENT);

			}
		}
	}

	public void reprove(BaseFncVO fncVO_) {
		MembershipTermDetailFncVO fncVO = (MembershipTermDetailFncVO) fncVO_;

		OracleTplTermAdhesionDAO dao = new OracleTplTermAdhesionDAO();

		String[] keys = fncVO.getSelectedCode().split(",");

		Long prodAcctCode = Long.parseLong(keys[0]);
		Long prodUnderAcctCode = Long.parseLong(keys[1]);

		for (TplTermAdhesionEntityVO mov : dao.findByProdAcctCodeAndProdUnderAcctCode(prodAcctCode, prodUnderAcctCode, TableTypeEnum.MOVEMENT)) {
			mov.setLastApprvDate(new Date());
			mov.setLastApprvUserId(fncVO.getLoggedUser().getUserID());

			dao.delete(mov.getProdAcctCode(), mov.getProdUnderAcctCode(), mov.getProdCode(), mov.getSysCode(), mov.getSysSegCode(), TableTypeEnum.MOVEMENT);
		}
	}

	public void validateUpdate(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub

	}

	public void validateApprove(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub

	}

	public void validateReprove(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub

	}

	public void loadForUpdate(BaseFncVO fncVO_) {
		MembershipTermDetailFncVO fncVO = (MembershipTermDetailFncVO) fncVO_;

		loadDefaultData(fncVO);

		OracleTplTermAdhesionDAO dao = new OracleTplTermAdhesionDAO();

		List<ProdutoTermoFncVO> listaProdutoTermo = new ArrayList<ProdutoTermoFncVO>();
		fncVO.setListaProdutoTermo(listaProdutoTermo);

		for (TplTermAdhesionEntityVO mov : dao.findByCurAcctNbr(fncVO.getCurAcctNbr(), TableTypeEnum.MOVEMENT)) {
			if (!"E".equals(mov.getOpernTypeCode())){
				fncVO.setTrsrySysInd(mov.getTrsrySysInd());
				fncVO.setDpSysInd(mov.getDpSysInd());
	
				ProdutoTermoFncVO o = new ProdutoTermoFncVO();
				o.setProdCode(mov.getProdCode());
				o.setAdhTermTypeCode(mov.getAdhTermTypeCode());
				listaProdutoTermo.add(o);
			}
		}

	}

	public void loadForApprove(BaseFncVO fncVO_) {
		MembershipTermDetailFncVO fncVO = (MembershipTermDetailFncVO) fncVO_;

		loadDefaultData(fncVO);

		OracleTplTermAdhesionDAO dao = new OracleTplTermAdhesionDAO();

		List<ProdutoTermoFncVO> listaProdutoTermo = new ArrayList<ProdutoTermoFncVO>();
		fncVO.setListaProdutoTermo(listaProdutoTermo);
		fncVO.setCanApprove(false);
		for (TplTermAdhesionEntityVO mov : dao.findByCurAcctNbr(fncVO.getCurAcctNbr(), TableTypeEnum.MOVEMENT)) {
			if (!"E".equals(mov.getOpernTypeCode())){
				fncVO.setTrsrySysInd(mov.getTrsrySysInd());
				fncVO.setDpSysInd(mov.getDpSysInd());
	
				ProdutoTermoFncVO o = new ProdutoTermoFncVO();
				o.setProdCode(mov.getProdCode());
				o.setAdhTermTypeCode(mov.getAdhTermTypeCode());
				listaProdutoTermo.add(o);
			}
			if (!mov.getLastUpdUserId().equals(fncVO.getLoggedUser().getUserID())){
				fncVO.setCanApprove(true);
			}
		}

	}

	public void loadForConsult(BaseFncVO fncVO_) {
		loadForUpdate(fncVO_);
	}

	@Override
	public void updateFncVOFromForm(ActionForm form_, BaseFncVO fncVO_) {
		MembershipTermDetailForm form = (MembershipTermDetailForm) form_;
		MembershipTermDetailFncVO fncVO = (MembershipTermDetailFncVO) fncVO_;

		try {
			fncVO.setReltnNbr(Long.parseLong(form.getReltnNbr()));
		} catch (NumberFormatException e) {
		}

		try {
			fncVO.setCurAcctNbr(Long.parseLong(form.getCurAcctNbr()));
		} catch (NumberFormatException e) {
		}

		fncVO.setCustFullName(form.getCustFullName());
		fncVO.setCustTypeCode(form.getCustTypeCode());

		try {
			fncVO.setCustCpfNbr(Long.parseLong(form.getCustCpfNbr()));
		} catch (NumberFormatException e) {
		}

		fncVO.setOffcrName(form.getOffcrName());

		try {
			fncVO.setOffcrNbr(Integer.parseInt(form.getOffcrNbr()));
		} catch (NumberFormatException e) {
		}

		try {
			fncVO.setPortfBrchCode(Integer.parseInt(form.getPortfBrchCode()));
		} catch (NumberFormatException e) {
		}

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
		form.setCanApprove(fncVO.getCanApprove());
	}

	@Override
	public BaseFncVO createFncVO() {
		return new MembershipTermDetailFncVO();
	}

	private void loadDefaultData(MembershipTermDetailFncVO fncVO) {
		OracleTplTermAdhesionDAO dao = new OracleTplTermAdhesionDAO();

		String[] keys = fncVO.getSelectedCode().split(",");

		Long prodAcctCode = Long.parseLong(keys[0]);
		Long prodUnderAcctCode = Long.parseLong(keys[1]);

		BaseTo3ProductAccountEntity productAccount = dao.getProductAccountByProdAcctCodeAndProdUnderAcctCode(prodAcctCode, prodUnderAcctCode);

		Long custNbr = productAccount.getData().getCustNbr().longValue();
		Long reltnNbr = productAccount.getData().getReltnNbr().longValue();
		Long curAcctNbr = productAccount.getData().getCurAcctNbr().longValue();

		fncVO.setProdAcctCode(prodAcctCode);
		fncVO.setProdUnderAcctCode(prodUnderAcctCode);
		fncVO.setReltnNbr(reltnNbr);
		fncVO.setCurAcctNbr(curAcctNbr);
		
		TplCustomerPrvtEntityVO customer = dao	.getCustomer(custNbr);
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
