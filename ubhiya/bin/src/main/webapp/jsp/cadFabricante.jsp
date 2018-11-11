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
	Fabricante f = new Fabricante();
	f.setLogin("");
	f.setSenha("");
	f.setTipoUser(4);
	f.setRazaoSocial("");
	f.setCnpj("");
	f.setIe("");
	f.setNomeFantasia("");
	List<Fabricante> fabricantes = new ArrayList<Fabricante>(f.buscarFabricantes());

	if (request.getParameter("add") != null) {
		/*Usuario u = new Usuario(request.getParameter("login"), request.getParameter("senha"), 1);
		u.cadastrar();
		response.sendRedirect(request.getRequestURI());
		status = u.getStatus();*/
		f.setLogin(request.getParameter("login"));
		f.setSenha(request.getParameter("senha"));
		f.setRazaoSocial(request.getParameter("razaoS"));
		f.setCnpj(request.getParameter("cnpj"));
		f.setIe(request.getParameter("ie"));
		f.setNomeFantasia(request.getParameter("nomeF"));
		f.cadastrarFabricante();
		response.sendRedirect(request.getRequestURI());
	}
	if (request.getParameter("del") != null) {
		f.deletar(Long.valueOf(request.getParameter("id")));
		response.sendRedirect(request.getRequestURI());
	}
	if (request.getParameter("alt") != null) {
		f = fabricantes.get(Integer.parseInt(request.getParameter("i")) - 1);
		/*c.setLogin(comercios.get(Integer.parseInt(request.getParameter("i")) - 1).getLogin());
		c.setSenha(comercios.get(Integer.parseInt(request.getParameter("i")) - 1).getSenha());
		c.setRazaoSocial(comercios.get(Integer.parseInt(request.getParameter("i")) - 1).getRazaoSocial());
		c.setCnpj(comercios.get(Integer.parseInt(request.getParameter("i")) - 1).getCnpj());
		c.setIe(comercios.get(Integer.parseInt(request.getParameter("i")) - 1).getIe());
		c.setNomeFantasia(comercios.get(Integer.parseInt(request.getParameter("i")) - 1).getNomeFantasia());*/
	}
	if (request.getParameter("sal") != null) {
		f.setLogin(request.getParameter("login"));
		f.setSenha(request.getParameter("senha"));
		f.setRazaoSocial(request.getParameter("razaoS"));
		f.setCnpj(request.getParameter("cnpj"));
		f.setIe(request.getParameter("ie"));
		f.setNomeFantasia(request.getParameter("nomeF"));
		f.setId(Long.valueOf(request.getParameter("id")));
		f.alterarFabricante();
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
							value="<%=f.getLogin()%>">
					</div>

					<div class="form-group">
						<label for="log-senha">Senha:</label> <input id="log-senha"
							type="password" name="senha" placeholder="Sua Senha"
							class="form-control" value="<%=f.getSenha()%>">
					</div>

					<div class="form-group">
						<label for="log-nomef">Nome Fantasia:</label> <input
							id="log-nomef" type="text" name="nomeF"
							placeholder="Nome Fantasia" class="form-control"
							value="<%=f.getNomeFantasia()%>">
					</div>

					<div class="form-group">
						<label for="log-rs">Razão Social:</label> <input id="log-rs"
							type="text" name="razaoS" placeholder="Razão Social"
							class="form-control" value="<%=f.getRazaoSocial()%>">
					</div>

					<div class="form-group">
						<label for="log-cnpj">CNPJ:</label> <input id="log-cnpj"
							type="text" name="cnpj" placeholder="CNPJ" class="form-control"
							value="<%=f.getCnpj()%>">
					</div>

					<div class="form-group">
						<label for="log-ie">IE:</label> <input id="log-ie" type="text"
							name="ie" placeholder="IE" class="form-control"
							value="<%=f.getIe()%>">
					</div>
					<%
						if (request.getParameter("alt") != null) {
					%>
					<button type="submit" name="sal"
						class="btn btn-primary btn-lg login-button">Alterar</button>
					<input type="hidden" name="id" value="<%=f.getId()%>">
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
						for (Fabricante fabricante : fabricantes) {
							i++;
					%>
					<tr>
						<td><%=i%></td>
						<td><%=fabricante.getId()%></td>
						<td><%=fabricante.getLogin()%></td>
						<td><%=fabricante.getSenha()%></td>
						<td><%=fabricante.getRazaoSocial()%></td>
						<td><%=fabricante.getNomeFantasia()%></td>
						<td><%=fabricante.getCnpj()%></td>
						<td><%=fabricante.getIe()%></td>
						<td>
							<form>
								<input type="hidden" name="id" value="<%=fabricante.getId()%>">
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
	<script src="jquery.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>