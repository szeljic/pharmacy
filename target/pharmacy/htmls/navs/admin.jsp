 
	    <ul class="nav navbar-nav">
			<li><a id="home-link" href="panel.jsp">Home <span class="sr-only">(current)</span></a></li>
			        
			<li class="dropdown">
		        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Pharmacist<span class="caret"></span></a>
		        <ul class="dropdown-menu" role="menu">
				    <li><a id="list-of-pharmacists" href="list_of_pharmacists.jsp">List of pharmacists</a></li>
				    <li><a id="add-pharmacist" href="add_pharmacist.jsp">Add pharmacist</a></li>
		        </ul>
	        </li>
	    </ul>
	    
	    <ul class="nav navbar-nav navbar-right">
	        		<li><a id="logout-link" href="htmls/logout.jsp">Logout(<%= session.getAttribute("username") %>)</a></li>
	    </ul>