	<%@ page import="com.szeljic.pharmacy.Beans.DrugBean" %>
	<%@ page import="com.szeljic.pharmacy.DAO.*" %>
	
	<%
		if(session.getAttribute("username") == null || (Integer)session.getAttribute("type") != 1){
			response.sendRedirect("error-404.jsp");
		}
	%>
	
	
	<jsp:include page="htmls/header.jsp"></jsp:include>

	<jsp:include page="htmls/navbar.jsp"></jsp:include>
	
	<%
		boolean edit = (request.getParameter("id") != null) ? true : false;
		DrugBean drug = new DrugBean();
		if(edit){
		
			DrugDAO drugDAO = new DrugDAOImpl();
			drug = drugDAO.getDrug(Integer.parseInt(request.getParameter("id")));
		
	}
	%>
	
	<div id="main-content">
	
		<div class="row">
		
			<div class="col-xs-8 col-xs-offset-2">		
			
				<%
				if(!edit){ %>
					<h1>Add Drug</h1>
				<%} else { %>
					<h1>Edit Drug</h1>
				<% } %>
				
				<form method="post" action="<% if(!edit) { %>AddDrugServlet <% } else { %> EditDrugServlet <% } %>">
				
					<div class="form-group">
						<label>Name</label>
						<input id="name" name="name" type="text" class="form-control" value="<%= (drug.getName() != null) ? drug.getName() : "" %>" required>
					</div>
					
					<div class="form-group">
						<label>Producer</label>
						<input id="producer" name="producer" type="text" class="form-control" value="<%= (drug.getProducer() != null) ? drug.getProducer() : "" %>" required>
					</div>
					
					<div class="form-group">
						<label>Uses</label>
						<textarea name="uses" class="form-control" rows="4"><%= (drug.getUses() != null) ? drug.getUses() : "" %></textarea>
					</div>
					
					<div class="form-group">
						<label>How to use</label>
						<textarea name="how-to-use" class="form-control" rows="4"><%= (drug.getHowToUse() != null) ? drug.getHowToUse() : "" %></textarea>
					</div>
					
					<div class="form-group">
						<label>Side effects</label>
						<textarea name="side-effects" class="form-control" rows="4"><%= (drug.getSideEffects() != null) ? drug.getSideEffects() : "" %></textarea>
					</div>
					
					<div class="form-group">
						<label>Price</label>
						<input id="price" name="price" type="number" class="form-control" value="<%= drug.getPrice() %>" style="width: 200px" required>
					</div>
					
					<div class="form-group">
						<label>Store</label>
						<input id="store" name="store" type="number" class="form-control" value="<%= drug.getStore() %>" style="width: 200px" required>
					</div>
					
					<%
							if(edit){ %>
							<div class="form-group">
								<input type="hidden" name="id" value="<%= request.getParameter("id") %>">
							</div>
						
						<% } %>
					
					<button id="button" type="submit" class="btn btn-primary pull-right">
							<% if(!edit) { %>
								Add Drug
							<% } else { %>
								Edit Drug
							<% } %>
					</button>
				
				
				</form>
			
			</div>
			
		</div>
		
	</div>
	
	
	<jsp:include page="htmls/footer.jsp"></jsp:include>