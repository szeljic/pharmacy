	<%@page import="com.szeljic.pharmacy.Beans.UserBean"%>
<%@page import="com.szeljic.pharmacy.Beans.DrugBean"%>
<%@page import="com.szeljic.pharmacy.DAO.OrderDAOImpl"%>
<%@page import="com.szeljic.pharmacy.DAO.OrderDAO"%>
<%@page import="com.szeljic.pharmacy.Beans.OrderBean"%>
<%@ page import="java.util.List" %>
	<%@ page import="java.util.ArrayList" %>
	
	<%
		if(session.getAttribute("username") == null || (Integer)session.getAttribute("type") != 1){
			response.sendRedirect("error-404.jsp");
		}
	
		
		boolean isSearch = false;
		if(request.getParameter("username") != null && request.getParameter("drug") != null){
			isSearch = true;
		}
	
	%>
	
	<jsp:include page="htmls/header.jsp"></jsp:include>

	<jsp:include page="htmls/navbar.jsp"></jsp:include>
	
	<div id="main-content">
	
		
		<div class="col-xs-10 col-xs-offset-2">
	
			<form class="form-inline pull-right" method="post" action="SearchUsernameDrugServlet">
			
				<div class="form-group">
					<input id="input" name="username" type="search" class="form-control" placeholder="Enter Username" <%if(isSearch){ %>value="<%= request.getParameter("username") %>"<% } %> required>
				</div>
			
				<div class="form-group">
					<input id="input" name="drug" type="search" class="form-control" placeholder="Enter Drug" <%if(isSearch){ %>value="<%= request.getParameter("drug") %>"<% } %>>
				</div>
				
				<div class="form-group">
					<button id="button" type="submit" class="btn btn-primary">Search</button>
				</div>
			
			</form>
			
		</div>		
		
		
	
		<div class="col-xs-12">
		
		<br />
		
		<table class="table table-bordered table-striped">
			
				<tr>
					<th class="text-center" style="width: 35px">#</th>
					<th>User</th>
					<th>Drug Name</th>
					<th>Drug Producer</th>
					<th>Price</th>
					<th>Items</th>
					<th>Sum</th>
					<th class="text-center" style="width: 15%">Options</th>
				</tr>
				
				
				<% 
					List<OrderBean> list = new ArrayList<OrderBean>();
					OrderDAO orderDAO = new OrderDAOImpl();
					if(request.getParameter("username") == null && request.getParameter("drug") == null){
						list = orderDAO.getAllOrders();
					} else {
						list = orderDAO.searchForUsernameDrug(request.getParameter("username"), request.getParameter("drug"));
					}
					for(int i = 0; i < list.size(); i++){
						OrderBean order = list.get(i);
						DrugBean drug = order.getDrugRef();
						UserBean user = order.getUserRef();
				%>
				
				<tr>
					<td class="text-center"><%= i+1 %></td>
					<td><%= user.getUsername() %></td>
					<td><%= drug.getName() %></td>
					<td><%= drug.getProducer() %></td>
					<td><%= drug.getPrice() %></td>
					<td><%= order.getOrdered() %></td>
					<td><%= order.getSum() %></td>
					<td class="text-center">
						<% if(!order.isStatus()) { %>
							<a href="ApproveOrderServlet?id=<%=order.getId()%>&page=all"><span class="glyphicon glyphicon-ok"></span></a>
							<a href="DenyOrderServlet?id=<%=order.getId()%>&page=all"><span class="glyphicon glyphicon-remove"></span></a>
						<% } else {
							if(order.isApproval()){ %> Approve <% }
							else { %> Deny <% }	
							%>
						
						<% } %>
					</td>
				</tr>
				
				<% 
					}%>
				
		</table>
		
		</div>
		
	</div>
	
	<jsp:include page="htmls/footer.jsp"></jsp:include>