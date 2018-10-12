<%@page import="ubhiya.ConexaoMySQL"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>

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
	<header>
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#collapse-navbar"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>

					<a class="navbar-brand" href="<%=path%>/index.jsp">WeBeer</a>
				</div>

				<div class="collapse navbar-collapse" id="collapse-navbar">
					<ul class="nav navbar-nav">
						<li><a href="<%=path%>/jsp/cadUser.jsp">Administrador</a></li>
						<li><a href="<%=path%>/jsp/cadCliente.jsp">Consumidor</a></li>
						<li><a href="<%=path%>/jsp/cadComercio.jsp">Comércio</a></li>
						<li><a href="<%=path%>/jsp/cadFabricante.jsp">Fabricante</a></li>
					</ul>
				</div>
			</div>
		</nav>

	</header>
	<div class="container">
		<h2>Olá Mundo Cervejeiro!</h2>
		<p>Use os links ou a barra de navegação para acessar as telas de cadastros</p>

		<a href="<%=path%>/jsp/cadUser.jsp">Usuários</a> || <a
			href="<%=path%>/jsp/cadCliente.jsp">Consumidor</a> || <a
			href="<%=path%>/jsp/cadComercio.jsp">Comércio</a> || <a
			href="<%=path%>/jsp/cadFabricante.jsp">Fabricante</a>
	</div>
</body>
</html>
