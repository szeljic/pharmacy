	<%@page import="com.szeljic.pharmacy.Beans.DrugBean"%>
	<%@page import="com.szeljic.pharmacy.DAO.DrugDAOImpl"%>
	<%@page import="com.szeljic.pharmacy.DAO.DrugDAO"%>
	
	<%
		if(session.getAttribute("username") == null || (Integer)session.getAttribute("type") != 2){
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
	
		<div class="row">
		
			<div class="col-xs-8 col-xs-offset-2">		
			
			<% 
			if(request.getParameter("emptystore") != null){
				
				if(request.getParameter("emptystore").equals("fail")){ %>
				
					<div id="message-store-empty" class="alert alert-danger" role="alert">Store is empty!</div>
					
				<% }
				
			}
			
			if(request.getParameter("notenough") != null){
				
				if(request.getParameter("notenough").equals("fail")){ %>
				
					<div id="message-not-enough" class="alert alert-danger" role="alert">Not as much a drugs in stock!</div> 
					
				<% }
				
			}
			
			%>
			
			</div>
			
			<div class="col-md-4 col-md-offset-4">
			
				
				<h1>Order Drug</h1>
			
				<br />
				
				
					<div class="form-group">
						<label>Name</label>
						<p><%= drug.getName()%></p>
					</div>
					
					<div class="form-group">	
						<label>Producer</label>
						<p><%= drug.getProducer()%></p>
					</div>	
					
					<div class="form-group" style="display: inline">
						<label>Price: </label>
						<span id="price"><%= drug.getPrice()%></span><span>&euro;</span>
					</div>
					
					<br />
					<br />
						
					<div class="form-group" style="display: inline">
						<label>Store: </label>
						<span><%= drug.getStore()%></span>
					</div>
					
					<br />
					<br />
				
					<form method="post" class="form-inline" action="OrderDrugServlet">
					
						<div class="form-group">
						    <label>Order</label>
						    <input name="order" type="number" min="1" max="<%= drug.getStore() %>" onchange="sumPrice(this.value)">
					    </div>
					  
						<div class="form-group">
							<input type="hidden" name="id" value="<%=request.getParameter("id")%>">
						</div>
					  
						<button type="submit" class="btn btn-primary pull-right">Order</button>
					 
					</form>
					 <br />
					<div class="form-group">
					    <label>Sum: </label> <span id="sum"></span><span id="euroSign" style="display: none;">&euro;</span>
					</div>
		
			</div>
			
		</div>
		
	</div>
	<%} else { 
		response.sendRedirect("list_of_drugs.jsp");
	}
	%>
	
	<jsp:include page="htmls/footer.jsp"></jsp:include>
	
	<script type="text/javascript">
	
		function sumPrice(value){
			var price = $("#price").text();
			var sum = price * value;
			$("#sum").text(sum);
			$("#euroSign").css({"display":"inline"});
		}
	
	</script>