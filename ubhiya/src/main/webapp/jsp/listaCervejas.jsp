<%@page import="ubhiya.Model.Fabricante"%>
<%@page import="ubhiya.Model.Cerveja"%>
<%@page import="ubhiya.Dto.ListaPaginada"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@page import="ubhiya.Dto.CervejaDto"%>
<%@page import="ubhiya.Model.Carta"%>
<%@page import="java.util.List"%>
<%@page import="ubhiya.Model.Comercio"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
	int pagina = 1;

	if (request.getParameter("pagina") != null){
		pagina = Integer.parseInt(request.getParameter("pagina"));
	}
	Fabricante fabricante = (Fabricante)request.getSession().getAttribute("fabricante");
	Long totalPaginas = 0l;
	ListaPaginada<CervejaDto> cervejas = null;
	
	try {
		System.out.println("ERRO1");
		//cervejas = Cerveja.buscarCervejas(pagina);
		cervejas = Cerveja.buscarCervejasFabricante(Long.valueOf(request.getSession().getAttribute("id").toString()), pagina);
		System.out.println("ERRO2");
		totalPaginas = cervejas.getTotalRegistros() / 10 + (cervejas.getTotalRegistros() % 10 == 0 ? 0 : 1);
		System.out.println("ERRO3");
	}catch (SQLException e){
		System.out.println("ERRO");
	}
	
	
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Webeer</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="jumbotron">
  		<h1>Lista de cervejas</h1>
  		<p>Para maiores detalhes sobre alguma cerveja, clique sobre ela na tabela.</p>
	</div>
		
		<table class="table table-hover">
	  		<thead>
	    		<tr>
	    			<th scope="col">#</th>
	      			<th scope="col">Cervejaria</th>
	      			<th scope="col">Cerveja</th>
	      			<th scope="col">IBU</th>
	      			<th scope="col">ABV</th>
			    </tr>
	  		</thead>
	  		<tbody>	  		
	  		
	  		<%for(int i=0; i<cervejas.getLista().size(); i++){ %>
			    <tr onclick='<%="f"+i+".submit()"%>'>
			      <th scope="row">
			      	<%=i+1%>
			      	<form name='<%="f"+i%>' action="detalhesCerveja.jsp" method="post">
			      		<input type="hidden" name="idCerveja" value="<%=cervejas.getLista().get(i).getIdCerveja() %>">
			      	</form>
			      </th>
			      <td><%=cervejas.getLista().get(i).getFabricante() %></td>
			      <td><%=cervejas.getLista().get(i).getNomeCerveja() %></td>
			      <td><%=cervejas.getLista().get(i).getIbu() %></td>
			      <td><%=cervejas.getLista().get(i).getAbv() %></td>
			      
			    </tr>
		    <%}%>
		    
		  </tbody>
		</table>

		<nav aria-label="Page navigation example">
  <form method="post">
  <ul class="pagination">
  <%for(Long i=1l; i<=totalPaginas; i++){ %>
    <li class="page-item"><input type="submit" name="pagina" value="<%=i%>" <%=pagina==i?"disabled":""%> ></li>
  <%}%>
  </ul>
  </form>
</nav>
</body>
</html>