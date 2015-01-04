
<%
	
	session.invalidate();
	String site = "../index.jsp";
	response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", site);

%>
