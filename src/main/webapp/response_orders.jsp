	<%@page import="com.szeljic.pharmacy.Beans.DrugBean"%>
	<%@page import="com.szeljic.pharmacy.Beans.UserBean"%>
	<%@ page import="java.util.List" %>
	<%@ page import="com.szeljic.pharmacy.Beans.OrderBean" %>
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
					OrderDAO OrderDAO = new OrderDAOImpl();
					list = OrderDAO.getAllOrders();
					DrugDAO drugDAO = new DrugDAOImpl();
					UserDAO userDAO = new UserDAOImpl();
					for(int i = 0; i < list.size(); i++){
						OrderBean order = list.get(i);
						DrugBean drug = drugDAO.getDrug(order.getDrug());
						UserBean user = userDAO.getUser(order.getUser());
						if(order.isStatus()){
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
							<%if(order.isApproval()){ %> Approve <% }
								else { %> Deny <% }	
							%>
					</td>
				</tr>
				

						<%}
						} %>
				
		</table>
		
		</div>
		
	</div>
	
	<jsp:include page="htmls/footer.jsp"></jsp:include>