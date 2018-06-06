 <%@page import="com.citibank.latam.common.exception.CompoundException,
                 com.citibank.latam.sgway.service.LegacySgConnector,
                 com.citibank.latam.sgway.util.RecordList"%>
<html>
<head>
<title>SG VALIDATOR</title>
<%!
  void detailCL(ClassLoader cl, JspWriter out) throws Exception {
    if (cl != null)
      if (cl instanceof java.net.URLClassLoader) {
        java.net.URL urls[] = ((java.net.URLClassLoader) cl).getURLs();
        if (urls != null)
          for (int i = 0; i < urls.length; i++) {
            out.print(
                urls[i].getFile() + "<br>");
          }
      }
      else if (cl instanceof java.lang.ClassLoader) {
        out.print(cl.toString());
      }
      else {
        out.print("Instace of:" + cl.getClass().getSuperclass());
      }
  }
%>


<body>
<table width="100%">
  <tr>
    <td>
      <FONT color=#003366 size=3>
      <%
        if (request.getParameter("systemID") != null) {
          try {
            String mUserName = null;
            int systemID = Integer.parseInt(request.getParameter("systemID"));
            String request_ipaddress = request.getRemoteAddr();
            // comentado para rodar sem o SG
            String userId_header = request.getHeader("sm_user").trim();
            // verifica se o usuario foi enviado no HEADER do HTTP request
            if (userId_header != null && userId_header.length() > 1) {
              String sm_session_espec = request.getHeader("sm_serversessionspec");
              if (sm_session_espec != null && sm_session_espec.length() > 0) {
                out.println("Creating LegacySgConnector object !!! => initSG() <br>");
                LegacySgConnector legacyConnector = new LegacySgConnector();
                out.println("Creating LegacySgConnector object !!! => initSG() Created <br>");
                try {
                  out.println("Calling getSystemModules method !!! <br>");
                  RecordList rlst = legacyConnector.getSystemModules(userId_header, request_ipaddress, sm_session_espec, systemID);
                  out.println("Returned getSystemModules Size= " + rlst.getRecordCount() + " <br>");
                  for (int i = 0; i < rlst.getRecordCount(); i++) {
                    out.println("Module=" + rlst.getString(i, 0) + "|" + rlst.getString(i, 1) + "<br>");
                    out.println("Calling getSystemModuleFunctions method !!! <br>");
                    RecordList rlst2 = legacyConnector.getSystemModuleFunctions(userId_header, request_ipaddress, sm_session_espec, systemID, rlst.getInt(i, 0));
                    out.println("Returned getSystemModuleFunctions Size= " + rlst2.getRecordCount() + " <br>");
                    for (int j = 0; j < rlst2.getRecordCount(); j++) {
                      out.println("Functions=" + rlst2.getString(j, 0) + "|" + rlst2.getString(j, 1) + "<br>");
                    }
                    out.println("<hr>");
                    out.flush();
                  }
                }
                catch (CompoundException ex) {
                  out.println("Error getting user details:: initSG()=>" + ex.getMessage() + "<br>");
                  ex.printStackTrace();
                }
              }
              else {
                out.println("Error at initSG():: sm_session_espec from header is null or invalid: [" + sm_session_espec + "]<br>");
              }
            }
            else {
              out.println("Error at initSG():: sm_user from header is null or invalid: [" + userId_header + "] <br>");
            }
          }
          catch (Exception ex) {
            out.println(ex);
            out.println("<br><hr>");
            out.println("java.class.path=[" + System.getProperty("java.class.path") + "]");
            out.println("<br><hr>");
            // print the classpath
            ClassLoader fatherCL = com.citibank.latam.sgway.service.LegacySgConnector.class.getClassLoader();
            if (fatherCL != null) {
              out.println(fatherCL.toString());
              out.println("<br>");
              try {
                detailCL(fatherCL, out);
              }
              catch (Exception exc) {
                out.println("error detailing CL");
              }
              out.println("<br><hr>");
              while ((fatherCL = fatherCL.getParent()) != null) {
                out.println(fatherCL.toString());
                out.println("<br>");
                try {
                  detailCL(fatherCL, out);
                }
                catch (Exception exc) {
                  out.println("error detailing CL");
                }
                out.println("<br><hr>");
              }
            }
          }
        }
        else {
      %>
        <b>          The parameter systemID must be informed !!!
        <%}        %>

      </font>
    </td>
  </tr>
</table>
</body>
</html>
