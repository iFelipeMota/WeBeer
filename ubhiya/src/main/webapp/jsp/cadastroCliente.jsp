<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
	String path = request.getContextPath();
%>

<html>
<head>
<meta charset="UTF-8">
<title>WeBeer - Cadastro de Consumidor</title>
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

				<!-- div class="collapse navbar-collapse" id="collapse-navbar">
					<ul class="nav navbar-nav">
						<li><a href="<%=path%>/jsp/cadUser.jsp">Administrador</a></li>
						<li><a href="<%=path%>/jsp/cadCliente.jsp">Consumidor</a></li>
						<li><a href="<%=path%>/jsp/cadComercio.jsp">Comércio</a></li>
						<li><a href="<%=path%>/jsp/cadFabricante.jsp">Fabricante</a></li>
					</ul>
				</div-->
			</div>
		</nav>
	</header>
	
	<div class="container">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6 container">
				<form action="resposta.jsp" method="post">
					<div class="form-group">
						<label for="log-user">Login:</label> <input id="log-user"
							type="text" name="login" placeholder="Login" class="form-control"
							required>
					</div>

					<div class="form-group">
						<label for="log-senha">Senha:</label> <input id="log-senha"
							type="password" name="senha" placeholder="Sua Senha"
							class="form-control" required>
					</div>
					
					<div class="form-group">
						<label for="log-nome">Nome:</label> <input id="log-nome"
							type="text" name="nome" placeholder="Nome" class="form-control"
							required>
					</div>

					<div class="form-group">
						<label for="log-end">Endereço:</label> <input id="log-end"
							type="text" name="endereco" placeholder="Endereco"
							class="form-control" required>
					</div>
					<button type="submit" name="cadastrarCliente"
						class="btn btn-primary btn-lg login-button">Salvar</button>
				</form>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
</body>
</html>