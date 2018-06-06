<%@page import="java.text.SimpleDateFormat,java.util.Date,java.io.File,java.io.FileInputStream,java.io.BufferedInputStream,javax.servlet.ServletOutputStream"%>
<%	
	//Data de Log Informada	
	String data = request.getParameter("campoData");
	//Formata data Log Informada	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
	Date dateFile =  sdf.parse(data);
	SimpleDateFormat sdfC = new SimpleDateFormat("yyyyMMdd");
	//Data de Log arquivo
	String dateLog = sdfC.format(dateFile);
	//Nome do arquivo
	String fileName = "PrivateLayerApplication." + dateLog + ".log";
	//Diretorio onde se Encontra o Arquivo
	String dirPath = "/apps/was/aplic/privatelayer/log";
	String filePath = dirPath + "/" + fileName;
	
	FileInputStream fis = null;
	java.io.BufferedInputStream  bis= null;
	ServletOutputStream sos = null;
	
	try {

		byte[] buffer = new byte[4096];
		
		File file = new File(filePath);			
		if(file.exists() && file.canRead()){
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			response.setHeader("Content-Disposition","attachment; filename=" + fileName);
			response.setContentType("application/force-download");			
			sos = response.getOutputStream();
			int nBytes = -1;
			while((nBytes = bis.read(buffer, 0, 4096)) != -1 ) {
				sos.write(buffer, 0, nBytes);
			}
			sos.flush();
			request.setAttribute("errorMsg1", "");
			request.setAttribute("errorMsg2", "");
		} else {
			request.setAttribute("errorMsg1", "Error");
			request.setAttribute("errorMsg2", "File not exists or cannot be read.");
			request.getRequestDispatcher("arquivo.jsp").forward(request, response);
		}
	} catch (Exception e) {
		request.setAttribute("errorMsg1", "Error");
		request.setAttribute("errorMsg2", "Error on download file " + filePath);
		request.getRequestDispatcher("arquivo.jsp").forward(request, response);
	} finally{
		if(fis != null) fis.close();
		if(bis != null) bis.close();
		if(sos != null) sos.close();
	}
%>