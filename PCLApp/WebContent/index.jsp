<html>

<head>
<script>
	function redirectFrameset() {
		window.location.href = "<%=request.getContextPath()%>/FrontController/Startup.Execute";
	}
</script>

</head>

<body onLoad="javascript:redirectFrameset();"></body>

</html>