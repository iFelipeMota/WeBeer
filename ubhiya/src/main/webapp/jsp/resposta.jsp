<%@page import="ubhiya.Model.Usuario"%>
<%@page import="ubhiya.Model.Fabricante"%>
<%@page import="ubhiya.Model.Comercio"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ubhiya.Model.Cliente"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
	String path = request.getContextPath();

	boolean error = false;
	boolean success = false;
		
	String mensagem = null;

	if (request.getParameter("cadastrarCliente") != null) {
		Cliente c = new Cliente();
		c.setTipoUser(2);
		c.setLogin(request.getParameter("login"));
		c.setSenha(request.getParameter("senha"));
		c.setNome(request.getParameter("nome"));
		c.setEndereco(request.getParameter("endereco"));
		/*List<Cliente> clientes = c.buscarClientes();
		
		for (Cliente cliente: clientes){
			if (cliente.getLogin().equals(c.getLogin())){
				error = true;
				mensagem = "Erro: Login já cadastrado!";
				break;
			}
		}*/
		if (Usuario.existeLogin(c.getLogin())){
			error = true;
			mensagem = "Erro: Login já cadastrado!";
		}
		
		if (!error){
			c.cadastrarCliente();
			mensagem = "Cadastro efetuado com sucesso!";
			System.out.println(mensagem);
		}
		
		//response.sendRedirect(request.getRequestURI());
	} else if (request.getParameter("cadastrarComercio") != null) {
		Comercio co = new Comercio();
		co.setTipoUser(3);
		co.setLogin(request.getParameter("login"));
		co.setSenha(request.getParameter("senha"));
		co.setRazaoSocial(request.getParameter("razaoS"));
		co.setCnpj(request.getParameter("cnpj"));
		co.setIe(request.getParameter("ie"));
		co.setNomeFantasia(request.getParameter("nomeF"));
		
		//List<Comercio> comercios = co.buscarComercios();
		
		if (Usuario.existeLogin(co.getLogin())){
			error = true;
			mensagem = "Erro: Login já cadastrado!";
		} else if (Comercio.existeCnpj(co.getCnpj())){
			error = true;
			mensagem = "Erro: Já existe uma empresa cadastrada com o cnpj informado!";
		}
		
		/*for (Comercio comercio: comercios){
			if (comercio.getLogin().equals(co.getLogin())){
				error = true;
				mensagem = "Erro: Login já cadastrado!";
				break;
			} else if (comercio.getCnpj().equals(co.getCnpj())){
				error = true;
				mensagem = "Erro: Já existe uma empresa cadastrada com o cnpj informado!";
				break;
			}
		}*/
		
		if (!error){
			co.cadastrarComercio();
			mensagem = "Cadastro efetuado com sucesso!";
		}
	} else if (request.getParameter("cadastrarFabricante") != null) {
		Fabricante f = new Fabricante();
		f.setTipoUser(4);
		f.setLogin(request.getParameter("login"));
		f.setSenha(request.getParameter("senha"));
		f.setRazaoSocial(request.getParameter("razaoS"));
		f.setCnpj(request.getParameter("cnpj"));
		f.setIe(request.getParameter("ie"));
		f.setNomeFantasia(request.getParameter("nomeF"));
		
		/*List<Fabricante> fabricantes = f.buscarFabricantes();
		
		for (Fabricante fabricante: fabricantes){
			if (fabricante.getLogin().equals(f.getLogin())){
				error = true;
				mensagem = "Erro: Login já cadastrado!";
				break;
			} else if (fabricante.getCnpj().equals(f.getCnpj())){
				error = true;
				mensagem = "Erro: Já existe uma empresa cadastrada com o cnpj informado!";
				break;
			}
		}*/
		
		if (Usuario.existeLogin(f.getLogin())){
			error = true;
			mensagem = "Erro: Login já cadastrado!";
		} else if (Fabricante.existeCnpj(f.getCnpj())){
			error = true;
			mensagem = "Erro: Já existe uma empresa cadastrada com o cnpj informado!";
		}
		
		if (!error){
			f.cadastrarFabricante();
			mensagem = "Cadastro efetuado com sucesso!";
		}
	}

	%>

<html>
<head>
<meta charset="ISO-8859-1" http-equiv="refresh" content="3; url=../index.jsp">
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
<h2 class='<%=error?"text-danger":"text-success"%>'><%=mensagem %></h2>
<a href="../index.jsp"
						class="btn btn-primary btn-lg">Home</a>
</body>
</html>