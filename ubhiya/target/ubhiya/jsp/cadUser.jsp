<%@page import="java.util.ArrayList"%>
<%@page import="ubhiya.Model.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	String path = request.getContextPath();

	String status;
	Usuario.setStatus(null);
	Usuario u = new Usuario();
	u.setLogin("");
	u.setSenha("");
	u.setTipoUser(1);
	List<Usuario> admns = new ArrayList<Usuario>(u.buscarAdm());

	if (request.getParameter("add") != null) {
		/*Usuario u = new Usuario(request.getParameter("login"), request.getParameter("senha"), 1);
		u.cadastrar();
		response.sendRedirect(request.getRequestURI());
		status = u.getStatus();*/
		u.setLogin(request.getParameter("login"));
		u.setSenha(request.getParameter("senha"));
		u.cadastrar();
		response.sendRedirect(request.getRequestURI());
	}
	if (request.getParameter("del") != null) {
		u.deletar(Long.valueOf(request.getParameter("id")));
		response.sendRedirect(request.getRequestURI());
	}
	if (request.getParameter("alt") != null) {
		u = admns.get(Integer.parseInt(request.getParameter("i")) - 1);
	}
	if (request.getParameter("sal") != null) {
		u.setLogin(request.getParameter("login"));
		u.setSenha(request.getParameter("senha"));
		u.setId(Long.valueOf(request.getParameter("id")));
		u.alterar();
		response.sendRedirect(request.getRequestURI());
	}
%>

<html>
<head>
<meta charset="UTF-8">
<title>WeBeer - Cadastro de Administrador</title>
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
							value="<%=u.getLogin()%>">
					</div>

					<div class="form-group">
						<label for="log-senha">Senha:</label> <input id="log-senha"
							type="password" name="senha" placeholder="Sua Senha"
							class="form-control" value="<%=u.getSenha()%>">
					</div>

					<%
						if (request.getParameter("alt") != null) {
					%>
					<button type="submit" name="sal"
						class="btn btn-primary btn-lg login-button">Alterar</button>
					<input type="hidden" name="id" value="<%=u.getId()%>">
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
						<th></th>
					</tr>
					<%
						int i = 0;
						for (Usuario adm : admns) {
							i++;
					%>
					<tr>
						<td><%=i%></td>
						<td><%=adm.getId()%></td>
						<td><%=adm.getLogin()%></td>
						<td><%=adm.getSenha()%></td>
						<td>
							<form>
								<input type="hidden" name="id" value="<%=adm.getId()%>">
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