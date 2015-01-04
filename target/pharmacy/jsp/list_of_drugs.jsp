
<%@page import="com.szeljic.pharmacy.DAO.DrugDAO"%>
<%@page import="com.szeljic.pharmacy.DAO.DrugDAOImpl"%>
<%@page import="com.szeljic.pharmacy.Beans.DrugBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@ page import="java.util.List"%>

<%
	if (session.getAttribute("username") == null || ((Integer) session.getAttribute("type") != 1 && (Integer) session.getAttribute("type") != 2))
	{
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
				<th>Name</th>
				<th>Producer</th>
				<th class="text-center" style="width: 15%">Options</th>
			</tr>


			<%
				List<DrugBean> list = new ArrayList<DrugBean>();
				DrugDAO drugDAO = new DrugDAOImpl();
				list = drugDAO.listOfDrugs();
				Collections.sort(list);
				for (int i = 0; i < list.size(); i++)
				{
					DrugBean drug = list.get(i);
			%>

			<tr>
				<td class="text-center"><%=i + 1%></td>
				<td><%=drug.getName()%></td>
				<td><%=drug.getProducer()%></td>
				<td class="text-center"><a
					href="info_drug.jsp?id=<%=drug.getId()%>"><span
						class="glyphicon glyphicon-info-sign"></span></a> <%
 	if ((Integer) session.getAttribute("type") == 1)
 		{
 %><a
					href="add_drug.jsp?id=<%=drug.getId()%>"><span
						class="glyphicon glyphicon-pencil"></span></a>
					<%
						}
					%> <%
 	if ((Integer) session.getAttribute("type") == 1)
 		{
 %><a
					href="RemoveDrugServlet?id=<%=drug.getId()%>"><span
						class="glyphicon glyphicon-remove"></span></a>
					<%
						}
					%> <%
 	if ((Integer) session.getAttribute("type") == 2)
 		{
 %><a
					href="order_drug.jsp?id=<%=drug.getId()%>"><span
						class="glyphicon glyphicon-shopping-cart"></span></a>
					<%
						}
					%></td>
			</tr>

			<%
				}
			%>

		</table>

	</div>

</div>


<jsp:include page="htmls/footer.jsp"></jsp:include>