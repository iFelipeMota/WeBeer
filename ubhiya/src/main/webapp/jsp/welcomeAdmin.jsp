<%@page import="ubhiya.Model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
	String path = request.getContextPath();
	Usuario u = (Usuario)request.getSession().getAttribute("usuario");
	String nome = u.getLogin();
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
  		<h1>Bem vindo, <%=nome %></h1>
  		<p>Manutenção de admins, clientes, comércios e fabricantes</p>
  		<p>
  			<a class="btn btn-primary btn-lg" href="cadCliente.jsp" role="button">Clientes</a>
  			<a class="btn btn-primary btn-lg" href="cadComercio.jsp" role="button">Comércios</a>
  			<a class="btn btn-primary btn-lg" href="cadFabricante.jsp" role="button">Fabricantes</a>
  			<a class="btn btn-primary btn-lg" href="cadUser.jsp" role="button">Admins</a>
  		</p>
	</div>
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	
</body>
</html>