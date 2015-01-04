	<%@page import="com.szeljic.pharmacy.Beans.DrugBean"%>
	<%@page import="com.szeljic.pharmacy.DAO.DrugDAOImpl"%>
	<%@page import="com.szeljic.pharmacy.DAO.DrugDAO"%>

	<%
		if(session.getAttribute("username") == null || ((Integer)session.getAttribute("type") != 1 && (Integer)session.getAttribute("type") != 2)){
			response.sendRedirect("error-404.jsp");
		}
	%>

	<jsp:include page="htmls/header.jsp"></jsp:include>

	<jsp:include page="htmls/navbar.jsp"></jsp:include>
	
	<%
		int id = Integer.parseInt(request.getParameter("id"));
		DrugDAO drugDAO = new DrugDAOImpl();
		DrugBean drug = drugDAO.getDrug(id);	
		if(drug != null){
	%>
	
		
		<div id="main-content">
	
			<div class="col-xs-8 col-xs-offset-2">	
			
			<div class="form-group">
				<label>Name</label>
				<p><%= drug.getName()%></p>
			</div>
			
			<div class="form-group">	
				<label>Producer</label>
				<p><%= drug.getProducer()%></p>
			</div>	
			
			<div class="form-group">
				<label>Uses</label>
				<p><%= drug.getUses()%></p>
			</div>
			
			<div class="form-group">
				<label>How to use</label>
				<p><%= drug.getHowToUse()%></p>
			</div>
				
			<div class="form-group">
				<label>Side effects</label>
				<p><%= drug.getSideEffects()%></p>
			</div>
			<% if((Integer)session.getAttribute("type") == 2){ %>
			<form method="get" action="order_drug.jsp">
				<div class="form-group">
					<input type="hidden" name="id" value="<%= drug.getId() %>">
				</div>
				<div class="form-group">
					<button id="button" type="submit" class="btn btn-primary pull-right">Order</button>
				</div>
			</form>
			<%} %>
			
			
			
			</div>
			
		</div>
	<%} else { 
		response.sendRedirect("list_of_drugs.jsp");
	}%>
	
	<jsp:include page="htmls/footer.jsp"></jsp:include>