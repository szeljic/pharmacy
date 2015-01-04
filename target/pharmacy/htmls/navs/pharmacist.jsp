		<ul class="nav navbar-nav">
			<li><a id="home-link" href="panel.jsp">Home <span class="sr-only">(current)</span></a></li>
			  
			<li class="dropdown">
		        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Users<span class="caret"></span></a>
		        <ul class="dropdown-menu" role="menu">
				    <li><a href="list_of_users.jsp">List of Users</a></li>
				    <li><a href="add_user.jsp">Add User</a></li>
		        </ul>
	        </li> 
			        
			<li class="dropdown">
		        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Drugs<span class="caret"></span></a>
		        <ul class="dropdown-menu" role="menu">
				    <li><a href="list_of_drugs.jsp">List of drugs</a></li>
				    <li><a href="search_for_drugs.jsp">Search for drugs</a></li>
				    <li><a href="add_drug.jsp">Add drug</a></li>
		        </ul>
	        </li>
	                
	        <li class="dropdown">
		        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Orders<span class="caret"></span></a>
		        <ul class="dropdown-menu" role="menu">
				    <li><a href="unresponse_orders.jsp">Unresponse Orders</a></li>
				    <li><a href="response_orders.jsp">Response Orders</a></li>
				    <li><a href="all_ordered_drugs.jsp">List of All Orders</a></li>
		        </ul>
	        </li>
	        
	    </ul>
	    
	    <ul class="nav navbar-nav navbar-right">
	        		<li><a id="logout-link" href="htmls/logout.jsp">Logout(<%= session.getAttribute("username") %>)</a></li>
	    </ul>