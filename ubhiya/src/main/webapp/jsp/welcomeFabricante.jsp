<%@page import="ubhiya.Model.Fabricante"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%

	String path = request.getContextPath();
	
	Fabricante fabricante = (Fabricante)request.getSession().getAttribute("fabricante");
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
  		<h1>Bem vindo, <%=fabricante.getNomeFantasia() %></h1>
  		<p>Cadastre aqui suas cervejas</p>
  		<p>
  			<a class="btn btn-primary btn-lg" href="cadastroCerveja.jsp" role="button">Cadastrar cerveja</a>
  			<a class="btn btn-primary btn-lg" href="listaCervejas.jsp" role="button">Listar cervejas</a>
  		</p>
	</div>
</body>
</html>