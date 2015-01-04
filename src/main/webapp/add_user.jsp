	<%@ page import="com.szeljic.pharmacy.Beans.UserBean" %>
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
		UserBean user = new UserBean();
		if(edit){
			
			UserDAO userDAO = new UserDAOImpl();
			user = userDAO.getUser(Integer.parseInt(request.getParameter("id")));
			
			if(user.getType() != 2){
				response.sendRedirect("error-404.jsp");
			}
			
		}
	%>
	
	<div id="main-content">
	
		<div class="row">
		
			<div class="col-xs-8 col-xs-offset-2">
			
			<div id="message-password" class="alert alert-danger" role="alert" style="display: none">Password is too short!</div>
			<div id="message-username" class="alert alert-danger" role="alert" style="display: none">Username is not valid!</div>
			<div id="message-firstname" class="alert alert-danger" role="alert" style="display: none">First name is not valid!</div>
			<div id="message-lastname" class="alert alert-danger" role="alert" style="display: none">Last name is not valid!</div>
			<div id="message-umcn" class="alert alert-danger" role="alert" style="display: none">UMCN is not valid!</div>
			
			
			<% 
			if(request.getParameter("username") != null){
				
				if(request.getParameter("username").equals("fail")){ %>
				
					<div class="alert alert-danger" role="alert">Username is not valid!</div> 
					
				<% }
				
			}
			
			if(request.getParameter("username") != null){
				
				if(request.getParameter("username").equals("exist")){ %>
				
					<div class="alert alert-danger" role="alert">Username already exist!</div> 
					
				<% }
				
			}
			
			if(request.getParameter("firstname") != null){
				
				if(request.getParameter("firstname").equals("fail")){
					
					%><div class="alert alert-danger" role="alert">First name is not valid!</div><%
					
				}
				
			}
			
			if(request.getParameter("lastname") != null){
				
				if(request.getParameter("lastname").equals("fail")){
					
					%><div class="alert alert-danger" role="alert">Last name is not valid!</div><%
					
				}
				
			}
			
			if(request.getParameter("password") != null){
				
				if(request.getParameter("password").equals("fail")){
					
					%><div class="alert alert-danger" role="alert">Password is not valid!</div><%
					
				}
				
			}
			
			if(request.getParameter("umcn") != null){
				
				if(request.getParameter("umcn").equals("fail")){
					
					%><div class="alert alert-danger" role="alert">UMCN is not valid!</div><%
					
				}
				
			}
			
		%>
			
				<h1>
					<% if(!edit) { %>
						Add User
					<% } else { %>
						Edit User
					<% } %>
				</h1>
				
				<br />
				
					<form method="post" onsubmit="return checkForm();" action="<% if(!edit) { %>AddUserServlet <% } else { %> EditUserServlet <% } %>">
						
						<div class="form-group">
							<label>First name</label>
							<input id="firstname" name="firstname" type="text" class="form-control" value="<%= (user.getFirstName() != null) ? user.getFirstName() : "" %>" required>
						</div>
						
						<div class="form-group">
							<label>Last name</label>
							<input id="lastname" name="lastname" type="text" class="form-control" value="<%= (user.getLastName() != null) ? user.getLastName() : "" %>" required> 
						</div>
						
						<div class="form-group">
							<label>Username</label>
							<input id="username" name="username" type="text" class="form-control"  value="<%= (user.getUsername() != null) ? user.getUsername() : "" %>" required>
						</div>
						
						<div class="form-group">
							<label>Password</label>
							<input id="password" name="password" type="password" class="form-control" required>
						</div>
						
						<div class="form-group">
							<label>UMCN</label>
							<input id="umcn" name="umcn" type="text" class="form-control"  value="<%= (user.getUmcn() != null) ? user.getUmcn() : "" %>">
						</div>
						
						<%
							if(edit){ %>
							<div class="form-group">
								<input type="hidden" name="id" value="<%= request.getParameter("id") %>">
							</div>
						
						<% } %>
						
						
						
						<button id="button" type="submit" class="btn btn-primary pull-right">
							<% if(!edit) { %>
								Add User
							<% } else { %>
								Edit User
							<% } %>
						</button>
						
					</form>
			
			</div>
			
		</div>
			
	</div>
	
	
	<jsp:include page="htmls/footer.jsp"></jsp:include>
	
	<script type="text/javascript">
		
		function checkForm(){
			
			$("#message-firstname").css({"display":"none"});
			$("#message-lastname").css({"display":"none"});
			$("#message-username").css({"display":"none"});
			$("#message-password").css({"display":"none"});
			$("#message-umcn").css({"display":"none"});
			
			var matches = $("#firstname").val().match(/\d+/g);
			if (matches != null) {
				$("#message-firstname").css({"display":"block"});
				return false;
			}
			
			matches = $("#lastname").val().match(/\d+/g);
			if (matches != null) {
				$("#message-lastname").css({"display":"block"});
				return false;
			} 
			
			matches = $("#username").val().match(/\d+/g);
			if (matches != null) {
				$("#message-username").css({"display":"block"});
				return false;
			} 
			
			if($("#password").val().length < 6){
				$("#message-password").css({"display":"block"});
				return false;
			} 
			
			if(!$("#umcn").val().match(/^\d+$/) || $("#umcn").val().length != 13){
				$("#message-umcn").css({"display":"block"});
				return false;
			}
		}
	
	</script>