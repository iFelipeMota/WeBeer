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

	String path = request.getContextPath();
	String mensagem = null;
	boolean erro = false;
	
	List<CervejaDto> cartaDeCervejas = new ArrayList<CervejaDto>();
	
	Comercio comercio = (Comercio)request.getSession().getAttribute("comercio");
	
	if (request.getParameter("delete") != null){
		try{
			Carta.removerCarta(comercio.getIdComercio(), Long.valueOf(request.getParameter("id")));
		}catch (Exception e){
			mensagem = "Erro ao remover cerveja da carta: " + e.getMessage();
		}
	}
	
	try{
		cartaDeCervejas = Carta.obterCartaPorComercio(comercio.getIdComercio());
	} catch (SQLException e){
		mensagem = "Erro ao buscar carta de cervejas: " + e.getMessage();
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


	<%@include file="../jspf/header.jspf"%>
	
	<div class="jumbotron">
  		<h1>Bem vindo, <%=comercio.getNomeFantasia() %></h1>
  		<p>Divulgue sua carta de cervejas</p>
  		<p>
  			<a class="btn btn-primary btn-lg" href="cervejas.jsp" role="button">Pesquisar cervejas</a>
  		</p>
	</div>
	<%if(mensagem != null){%>
		<h3 class="text-danger"><%=mensagem%></h3>
	<%} else if(cartaDeCervejas.size() == 0){ %>
		<h3 class="text-warning">Nenhuma cerveja na sua carta</h3>
	<%} else {%>
		<table class="table table-striped">
	  		<thead>
	    		<tr>
	    			<th scope="col">#</th>
	      			<th scope="col">Cervejaria</th>
	      			<th scope="col">Cerveja</th>
	      			<th scope="col"></th>
			    </tr>
	  		</thead>
	  		<tbody>
	  		<%for(int i=0; i<cartaDeCervejas.size(); i++){ %>
			    <tr>
			      <th scope="row"><%=i+1%></th>
			      <td><%=cartaDeCervejas.get(i).getFabricante() %></td>
			      <td><%=cartaDeCervejas.get(i).getNomeCerveja() %></td>
			      <td>
			      	<form>
	      				<input name="delete" class="btn btn-danger" type="submit" value="X">
	      				<input name="id" type="hidden" value="<%=cartaDeCervejas.get(i).getIdCerveja() %>">
	     			</form>
			      </td>
			    </tr>
		    <%}}%>
		  </tbody>
		</table>
</body>
</html>