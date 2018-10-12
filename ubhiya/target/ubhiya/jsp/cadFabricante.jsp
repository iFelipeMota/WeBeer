<%@page import="java.util.ArrayList"%>
<%@page import="ubhiya.Model.Usuario"%>
<%@page import="ubhiya.Model.Fabricante"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	String path = request.getContextPath();

	String status;
	Fabricante c = new Fabricante();
	c.setLogin("");
	c.setSenha("");
	c.setTipoUser(2);
	c.setRazaoSocial("");
	c.setCnpj("");
	c.setIe("");
	c.setNomeFantasia("");
	List<Fabricante> comercios = new ArrayList<Fabricante>(c.buscarComercios());

	if (request.getParameter("add") != null) {
		/*Usuario u = new Usuario(request.getParameter("login"), request.getParameter("senha"), 1);
		u.cadastrar();
		response.sendRedirect(request.getRequestURI());
		status = u.getStatus();*/
		c.setLogin(request.getParameter("login"));
		c.setSenha(request.getParameter("senha"));
		c.setRazaoSocial(request.getParameter("razaoS"));
		c.setCnpj(request.getParameter("cnpj"));
		c.setIe(request.getParameter("ie"));
		c.setNomeFantasia(request.getParameter("nomeF"));
		c.cadastrarComercio();
		response.sendRedirect(request.getRequestURI());
	}
	if (request.getParameter("del") != null) {
		c.deletar(Long.valueOf(request.getParameter("id")));
		response.sendRedirect(request.getRequestURI());
	}
	if (request.getParameter("alt") != null) {
		c = comercios.get(Integer.parseInt(request.getParameter("i")) - 1);
		/*c.setLogin(comercios.get(Integer.parseInt(request.getParameter("i")) - 1).getLogin());
		c.setSenha(comercios.get(Integer.parseInt(request.getParameter("i")) - 1).getSenha());
		c.setRazaoSocial(comercios.get(Integer.parseInt(request.getParameter("i")) - 1).getRazaoSocial());
		c.setCnpj(comercios.get(Integer.parseInt(request.getParameter("i")) - 1).getCnpj());
		c.setIe(comercios.get(Integer.parseInt(request.getParameter("i")) - 1).getIe());
		c.setNomeFantasia(comercios.get(Integer.parseInt(request.getParameter("i")) - 1).getNomeFantasia());*/
	}
	if (request.getParameter("sal") != null) {
		c.setLogin(request.getParameter("login"));
		c.setSenha(request.getParameter("senha"));
		c.setRazaoSocial(request.getParameter("razaoS"));
		c.setCnpj(request.getParameter("cnpj"));
		c.setIe(request.getParameter("ie"));
		c.setNomeFantasia(request.getParameter("nomeF"));
		c.setId(Long.valueOf(request.getParameter("id")));
		c.alterarComercio();
		response.sendRedirect(request.getRequestURI());
	}
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
		<div class="row">
			<div class="col-md-4 container">
				<form>
					<div class="form-group">
						<label for="log-user">Login:</label> <input id="log-user"
							type="text" name="login" placeholder="Login" class="form-control"
							value="<%=c.getLogin()%>">
					</div>

					<div class="form-group">
						<label for="log-senha">Senha:</label> <input id="log-senha"
							type="password" name="senha" placeholder="Sua Senha"
							class="form-control" value="<%=c.getSenha()%>">
					</div>
					
					<div class="form-group">
						<label for="log-nomef">Nome Fantasia:</label> <input id="log-nomef"
							type="text" name="nomeF" placeholder="Nome Fantasia" class="form-control"
							value="<%=c.getNomeFantasia()%>">
					</div>

					<div class="form-group">
						<label for="log-rs">Razão Social:</label> <input id="log-rs"
							type="text" name="razaoS" placeholder="Razão Social"
							class="form-control" value="<%=c.getRazaoSocial()%>">
					</div>
					
					<div class="form-group">
						<label for="log-cnpj">CNPJ:</label> <input id="log-cnpj"
							type="text" name="cnpj" placeholder="CNPJ"
							class="form-control" value="<%=c.getCnpj()%>">
					</div>
					
					<div class="form-group">
						<label for="log-ie">IE:</label> <input id="log-ie"
							type="text" name="ie" placeholder="IE"
							class="form-control" value="<%=c.getIe()%>">
					</div>
					<%
						if (request.getParameter("alt") != null) {
					%>
					<button type="submit" name="sal"
						class="btn btn-primary btn-lg login-button">Alterar</button>
					<input type="hidden" name="id" value="<%=c.getId()%>">
					<%
						} else {
					%>
					<button type="submit" name="add"
						class="btn btn-primary btn-lg login-button">Salvar</button>
					<%
						}
					%>
				</form>
			</div>

			<div class="col-md-8">
				<table class="table table-striped">
					<tr>
						<th></th>
						<th>ID</th>
						<th>Login</th>
						<th>Senha</th>
						<th>Razão Social</th>
						<th>Nome Fantasia</th>
						<th>CNPJ</th>
						<th>IE</th>
						<th></th>
					</tr>
					<%
						int i = 0;
						for (Fabricante comercio : comercios) {
							i++;
					%>
					<tr>
						<td><%=i%></td>
						<td><%=comercio.getId()%></td>
						<td><%=comercio.getLogin()%></td>
						<td><%=comercio.getSenha()%></td>
						<td><%=comercio.getRazaoSocial()%></td>
						<td><%=comercio.getNomeFantasia()%></td>
						<td><%=comercio.getCnpj()%></td>
						<td><%=comercio.getIe()%></td>
						<td>
							<form>
								<input type="hidden" name="id" value="<%=comercio.getId()%>">
								<input type="hidden" name="i" value="<%=i%>"> <input
									type="submit" name="alt" value="Alterar"> <input
									type="submit" name="del" value="Excluir">
							</form>
						</td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>
	</div>
</body>
</html>