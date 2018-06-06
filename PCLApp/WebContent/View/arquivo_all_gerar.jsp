<%@page import="java.text.SimpleDateFormat,java.util.Date,java.io.File,java.io.FileInputStream,java.io.BufferedInputStream,javax.servlet.ServletOutputStream"%>
<%	
	//Data de Log Informada	
	String acao = request.getParameter("acao");
	String dirName = request.getParameter("dirName");
	String fileName = request.getParameter("fileName");
	String filePath = request.getParameter("fileName");
	
	if (acao.equals("gerar")) {
	FileInputStream fis = null;
	java.io.BufferedInputStream  bis= null;
	ServletOutputStream sos = null;
	
	try {

		byte[] buffer = new byte[4096];
		
		File file = new File(filePath);			
		if(file.exists() && file.canRead()){
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			int lastIndex = fileName.lastIndexOf("/");
			if (lastIndex < 0) {
					lastIndex = fileName.lastIndexOf("\"");
			}
			fileName = fileName.substring(lastIndex+1);
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
			request.getRequestDispatcher("arquivo_all.jsp").forward(request, response);
		}
	} catch (Exception e) {
		request.setAttribute("errorMsg1", "Error");
		request.setAttribute("errorMsg2", "Error on download file " + filePath);
		request.getRequestDispatcher("arquivo_all.jsp").forward(request, response);
	} finally{
		if(fis != null) fis.close();
		if(bis != null) bis.close();
		if(sos != null) sos.close();
	}
	} else {
	   request.setAttribute("hasContent", "true");
	   
	   	if ((dirName != null) && (!dirName.equals("")) ) {
			File f = new File(dirName);
			out.print("<table>");
			if (f.exists() && f.isDirectory()){
				File[] list = f.listFiles();
				for (int x =0; x < list.length; x++ ){
					File s = list[x];
					out.print("<tr><td>"+  s.getName() + "</td><td>" + s.lastModified() + "</td> </tr>");
				}
			}
			out.print("</table>");
		}
	   
	  
	}
	
%>