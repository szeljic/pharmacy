
	<jsp:include page="htmls/header.jsp"></jsp:include>

	<jsp:include page="htmls/navbar.jsp"></jsp:include>
	
	<div id="main-content">
	
		<div class="col-xs-4 col-xs-offset-4">
	
	<% if(request.getParameter("login") != null) { %>
	
		<div class="alert alert-danger" role="alert">Wrong username and password!</div>
		
	<% } %>
	
		</div>

		<div class="container login-form">
		
			<div class="row">
		
					<div class="col-xs-4 col-xs-offset-4">
		
						<form action="LoginServlet" method="post">
						
							<div class="page-header">
								<h3>Sign In</h3>
								<p>Please enter your username and password.</p>
							</div>
							
							
							<div class="form-group">
								<input name="username" type="text" placeholder="Username" required autofocus>
							</div>
							
							<div class="form-group">
								<input name="password" type="password" placeholder="Password" required>
							</div>
							
							<div class="form-group">
								<button class="btn btn-primary" type="submit">Sign in</button>
							</div>
							
						</form>
						
					</div>
					
				</div>
			
			</div>
	</div>
		
	<jsp:include page="htmls/footer.jsp"></jsp:include>