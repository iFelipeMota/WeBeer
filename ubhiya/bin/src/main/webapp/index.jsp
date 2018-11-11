<%@page import="ubhiya.ConexaoMySQL"%>
<%@page import="ubhiya.Controller.ControleDeSessao"%>
<%
	String path = request.getContextPath();
	String login = "";
	String senha = "";

	if (session.getAttribute("userName") != null) {
		login = (String) session.getAttribute("userName");
		senha = (String) session.getAttribute("senha");
	}
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

<link rel="stylesheet" type="text/css" href="<%=path%>/jsp/estilos.css">
</head>
<body>
	<header>
		<nav class="navbar navbar-default">
			<div class="container">

				<a class="navbar-brand" href="#">WeBeer</a>

			</div>
		</nav>

	</header>

	<div class="row">
		<div class="col-md-8">
			<div class="container">
				<p>O WeBeer é para você que gosta de saborear e conhecer...</p>
				<p>Apresentação do Sistema</p>
				<p>Insira uma Encheção de Linguiça aqui</p>
				<p>Insira uma Encheção de Linguiça aqui</p>
			</div>
		</div>

		<div class="col-md-4">
			<div class="login-banner container">
				<!--form action="ControleDeSessao"-->
				<form action="jsp/resposta.jsp" method="post">
					<div class="form-group">
						<label for="log-user">Login:</label> <input id="log-user"
							type="text" name="userName" placeholder="Login"
							class="form-control" value="<%=login%>">
					</div>

					<div class="form-group">
						<label for="log-senha">Senha:</label> <input id="log-senha"
							type="password" name="senha" placeholder="Sua Senha"
							class="form-control" value="<%=senha%>">
					</div>
					
					<%if(session.getAttribute("status")!=null){ %>
					<div class="alert alert-warning" role="alert">
						<%=session.getAttribute("status") %>
					</div>
					<%} %>
					<button type="submit" name=login
						class="btn btn-primary btn-lg login-button" value="entrar">Logar</button>
				</form>
				
				<a href="jsp/cadastroCliente.jsp"
						class="btn btn-primary btn-lg">Cadastro Cliente</a>
				<a href="jsp/cadastroComercio.jsp"
						class="btn btn-primary btn-lg">Cadastro Comércio</a>
				<a href="jsp/cadastroFabricante.jsp"
						class="btn btn-primary btn-lg">Cadastro Fabricante</a>		
				
			</div>
		</div>
	</div>
	<footer>
		<address>
			<strong>WeBeer - Cervejas Artesanais</strong><br> Rua Lúpulo,
			75, Vila Cevada<br> São Paulo, SP<br> Tel: (13) 3479-9087
		</address>
		<address></address>
	</footer>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
