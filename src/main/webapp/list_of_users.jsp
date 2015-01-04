	<%@page import="java.util.Collections"%>
	<%@ page import="java.util.List" %>
	<%@ page import="com.szeljic.pharmacy.Beans.UserBean" %>
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="com.szeljic.pharmacy.DAO.*" %>
	
	<%
		if(session.getAttribute("username") == null || (Integer)session.getAttribute("type") != 1){
			response.sendRedirect("error-404.jsp");
		}
	%>


	<jsp:include page="htmls/header.jsp"></jsp:include>

	<jsp:include page="htmls/navbar.jsp"></jsp:include>
	
	<div id="main-content">
	
		<div class="col-xs-12">
		
		<table class="table table-bordered table-striped">
			
				<tr>
					<th>#</th>
					<th>Username</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>UMCN</th>
					<th>Options</th>
				</tr>
				
				<% 
					List<UserBean> list = new ArrayList<UserBean>();
					UserDAO userDAO = new UserDAOImpl();
					list = userDAO.listOfUsers(2);
					Collections.sort(list);
					for(int i = 0; i < list.size(); i++){
						UserBean user = list.get(i);
				%>
				
				<tr>
					<td><%= i+1 %></td>
					<td><%= user.getUsername() %></td>
					<td><%= user.getFirstName() %></td>
					<td><%= user.getLastName() %></td>
					<td><%= user.getUmcn() %></td>
					<td class="text-center">
						<a href="add_user.jsp?id=<%=user.getId()%>"><span class="glyphicon glyphicon-pencil"></span></a>
						<a href="RemoveUserServlet?id=<%=user.getId()%>"><span class="glyphicon glyphicon-remove"></span></a>
					</td>
				</tr>
				
				<% } %>
				
			</table>
		
		</div>
	
	</div>
		
	<jsp:include page="htmls/footer.jsp"></jsp:include>