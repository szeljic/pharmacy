

    <nav class="navbar navbar-default" role="navigation">
  		<div class="container-fluid">
  		
    		<div class="navbar-header">
      			<a class="navbar-brand" href="index.jsp">Pharmacy</a>
    		</div>

	    	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	    	
	    	<% if(session.getAttribute("username") != null) { %>
	    	
	    	<% if(Integer.parseInt(session.getAttribute("type").toString()) == 0){ %>
	    		<jsp:include page="navs/admin.jsp"></jsp:include>
	    	<% } else if(Integer.parseInt(session.getAttribute("type").toString()) == 1) { %>
	    		<jsp:include page="navs/pharmacist.jsp"></jsp:include>
	    	<% } else if(Integer.parseInt(session.getAttribute("type").toString()) == 2) { %>
	    		<jsp:include page="navs/reg_user.jsp"></jsp:include>
	    	<% } else { %>
	     		<jsp:include page="navs/guest.jsp"></jsp:include>
	      	<% } %>
	      	<% } else { %>
	      		<jsp:include page="navs/guest.jsp"></jsp:include>
	      	<% } %>
	    	</div>
  		</div>
	</nav>
