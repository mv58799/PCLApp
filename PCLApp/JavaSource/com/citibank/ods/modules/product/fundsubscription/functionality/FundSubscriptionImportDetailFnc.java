package com.citibank.ods.modules.product.fundsubscription.functionality;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.user.User;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.entity.pl.valueobject.TplDataSubscptEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplFundMortEntityVO;
import com.citibank.ods.modules.product.broker.functionality.valueobject.CellProperty;
import com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.FundSubscriptionImportDetailVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplDataSubscptDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplFundMortDAO;
public class FundSubscriptionImportDetailFnc extends BaseFnc  {

	

	@Override
	public BaseFncVO createFncVO() {
		return new FundSubscriptionImportDetailVO();
	}

	public void clearPage(BaseFncVO fncVO_) {
		// FIXME RENATO : Verificar este método, acredito que está classe está errada. A classe que está sendo utilizada aqui é a do termo de adesão.
//		MembershipTermFncConsultVO fnc = ( MembershipTermFncConsultVO ) fncVO_;
//
//	    fnc.clearErrors();
//	    fnc.clearMessages();
//	    fnc.setInputCC( null );
		
	}

	@Override
	public void updateFormFromFncVO(ActionForm form_, BaseFncVO fncVO_) {
		try {
			BeanUtils.copyProperties(form_, fncVO_);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateFncVOFromForm(ActionForm form_, BaseFncVO fncVO_) {
		try {
			BeanUtils.copyProperties(fncVO_, form_);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static <T> List<T> readXlsFile(InputStream file, Class<T> c,  List<String[]> messages)
			throws IOException, InstantiationException, IllegalAccessException,
			InvocationTargetException, InvalidFormatException {
		return readXlsFile(file, c, 1, messages);
	}
	public static <T> List<T> readXlsFile(InputStream file, Class<T> class1, int startIndex, List<String[]> m)
			throws IOException, InstantiationException, IllegalAccessException,
			InvocationTargetException, InvalidFormatException {
		List<T> l = new ArrayList<T>();
		//Get the workbook instance for XLS file 
		Workbook workbook = create(file);
//		HSSFWorkbook workbook = new HSSFWorkbook(file);
		 
		//Get first sheet from the workbook
		Sheet sheet = workbook.getSheetAt(0);
		 
		//Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = sheet.iterator();
		 int rowIndex = 0;
		while (rowIterator.hasNext()){
			Row row = rowIterator.next();
			if (rowIndex++ < startIndex)
				continue;
			
			Iterator<Cell> cellIterator = row.cellIterator();
			int col = 0;
			Map<Integer, Object> content = new HashMap<Integer, Object>();
			while (cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					content.put(col, cell.getNumericCellValue() );
					break;
				case Cell.CELL_TYPE_BLANK:
					content.put(col, null);
					break;

				case Cell.CELL_TYPE_STRING:
					content.put(col, cell.getStringCellValue());
					break;

				case Cell.CELL_TYPE_BOOLEAN:
					content.put(col, cell.getBooleanCellValue());
					break;

				case Cell.CELL_TYPE_ERROR:
					content.put(col, cell.getErrorCellValue());
					break;

				case Cell.CELL_TYPE_FORMULA:
					tryParseFormula(col, content, cell);
					break;
				default:
					m.add(new String[] {"ERROR_LINE_IMPORT",String.valueOf(rowIndex), "coluna "+ col});
					break;
				}
				col ++;
			}
			boolean isValidLine = true;
			T i = class1.newInstance();
			Field[] declaredFields = class1.getDeclaredFields();
			for (Field f : declaredFields){
				CellProperty cp = f.getAnnotation(CellProperty.class);
				if (cp != null){
					try {
						Object con = content.get(cp.value()) ==null? null: content.get(cp.value());
						if (con ==null){
							if (cp.isMandatory()){
								isValidLine = false;
								break;
							}
							continue;
						}
						
					
						
						if (con instanceof String ){
							if (!"".equals(cp.mustMatch())){
								if (!con.toString().matches(cp.mustMatch())){
									if (cp.isMandatory()){
										isValidLine = false;
										break;
									}else {
										m.add(new String[] {"ERROR_LINE_IMPORT",String.valueOf(rowIndex), com.citibank.ods.common.util.Util.getTextFromField(f.getName())});
										continue;
									}
								}
							}
							 if (f.getType().isAssignableFrom(Date.class)){
									for (String pattern : cp.patterns()){
										try {
											SimpleDateFormat sdf = new SimpleDateFormat(pattern);
											Date parse = sdf.parse((String)con);
											if (parse!= null){
												BeanUtils.setProperty(i, f.getName(), parse);
												break;
											}
										}catch(Exception e){
										}
									}
							 	}else if (con != null && f.getType().isAssignableFrom(Double.class)){
							 		BeanUtils.setProperty(i, f.getName(), com.citibank.ods.common.util.Util.tryParseDouble(con.toString()));
							 	}else if (con != null && f.getType().isAssignableFrom(Long.class)){
							 		BeanUtils.setProperty(i, f.getName(), com.citibank.ods.common.util.Util.tryParseLong(con.toString()));
								}else if (con != null && f.getType().isAssignableFrom(Boolean.class)){
							 		//BeanUtils.setProperty(i, f.getName(), CellProperty.YES_VALUES.contains(con.toString().trim().toLowerCase()));
							 		//"yes","sim","y","s","true","t"
									BeanUtils.setProperty(i, f.getName(),( new HashSet<String>(Arrays.asList("yes","sim","y","s","true","t"))).contains(con.toString().trim().toLowerCase()));
								}else {
									if (con!=null){
										String s = null;
										if (con.getClass().isAssignableFrom(Double.class)){
											s = com.citibank.ods.common.util.Util.stringOf((Double)con);
										}else if (con.getClass().isAssignableFrom(Long.class)){
											s = com.citibank.ods.common.util.Util.stringOf((Long)con);
										}else{
											s = con.toString();
										}
										
										if (s.length() > cp.maxLength()){
											s = s.substring(0, cp.maxLength());
										}
										BeanUtils.setProperty(i, f.getName(), s);
									}
								}
						}else if (con instanceof Double ){
								if (f.getType().isAssignableFrom(Double.class)){
									BeanUtils.setProperty(i, f.getName(), (Double)con);
								}else  if (f.getType().isAssignableFrom(String.class)){
									BeanUtils.setProperty(i, f.getName(),com.citibank.ods.common.util.Util.stringOf((Double)con));
								}else if (f.getType().isAssignableFrom(Long.class)){
									BeanUtils.setProperty(i,f.getName(),new Double((Double)con).longValue());
								}
						
						}else {
							BeanUtils.setProperty(i, f.getName(), con);
						}
					}catch(Exception e){
						m.add(new String[] {"ERROR_LINE_IMPORT",String.valueOf(rowIndex), com.citibank.ods.common.util.Util.getTextFromField(f.getName())});
					}
				}
			}
			if (isValidLine)
				l.add(i);
		}
		return l;
	}
	
	
	private static void tryParseFormula(int col, Map<Integer, Object> content,
			Cell cell) {
		try {
			content.put(col, cell.getStringCellValue());
		}catch(Exception e ){
			try {
				content.put(col, cell.getNumericCellValue());
			} catch (Exception e2) {
				try {
					content.put(col, cell.getBooleanCellValue());
				} catch (Exception e3) {
					try {
						content.put(col, cell.getErrorCellValue());
					}catch(Exception e4){
						try {
							content.put(col, cell.getErrorCellValue());
						}catch(Exception e5){
							content.put(col, null);
						}
					}
				}
			}
		}
	}
	
	 public static Workbook create(InputStream inp) throws IOException, InvalidFormatException {
         // If clearly doesn't do mark/reset, wrap up
         if(! inp.markSupported()) {
                 inp = new PushbackInputStream(inp, 8);
         }

         if(POIFSFileSystem.hasPOIFSHeader(inp)) {
                 return new HSSFWorkbook(inp);
         }
         if(POIXMLDocument.hasOOXMLHeader(inp)) {
                 return new XSSFWorkbook(OPCPackage.open(inp));
         }
         throw new IllegalArgumentException("Your InputStream was neither an OLE2 stream, nor an OOXML stream");
 }
	
	 public static void defineFund(User u, TplDataSubscptEntityVO d,
				String fundName) {
		 defineFund(u.getUserID(), d, fundName, true);
	 }
	public static void defineFund(String u, TplDataSubscptEntityVO d,
			String fundName, boolean autoCommit) {
		if (!com.citibank.ods.common.util.Util.isEmpty(fundName)){
			OracleTplFundMortDAO fundDao = new OracleTplFundMortDAO();
			TplFundMortEntityVO fund = null;
			fund = fundDao.getFundsByName(fundName, TableTypeEnum.EFFECTIVE);
			if (fund == null){
				fund = fundDao.getFundsByName(fundName, TableTypeEnum.EFFECTIVE);
				if (fund == null){
					fund = new TplFundMortEntityVO();
					fund.setFundName(fundName);
					fund.setLastUpdDate(new Date());
					fund.setLastUpdUserId(u);
					fund.setLastApprvDate(new Date());
					fund.setLastApprvUserId(u);
					fund.setTableType(TableTypeEnum.EFFECTIVE);
					fundDao.insert(fund, autoCommit);
				}
			}
			d.setFundNameText(fund.getFundName());
		}
		return;
	}
	
	

	public static void defineFields(TplDataSubscptEntityVO d) {
			OracleTplDataSubscptDAO dao = new OracleTplDataSubscptDAO();
			String playerFull = dao.searchPlayerFullNameByCGC(d.getPlyrCpfNbr());
//			d.setI
			
		return;
	}
	
}
