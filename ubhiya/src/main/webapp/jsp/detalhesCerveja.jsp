<%@page import="ubhiya.Model.Avaliacao"%>
<%@page import="ubhiya.Model.Cliente"%>
<%@page import="ubhiya.Model.Cerveja"%>
<%@page import="ubhiya.Model.Carta"%>
<%@page import="ubhiya.Model.Comercio"%>
<%@page import="ubhiya.Dto.CervejaDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
	Long idCerveja = Long.valueOf(request.getParameter("idCerveja"));
	String mensagem = null;
	CervejaDto cerveja = null;
	int tipoUser = 0;
	boolean estaNaCarta = false;
	Double pontuacao = null;
	Double media = 0d;
	Comercio comercio = (Comercio)request.getSession().getAttribute("comercio");
	Cliente cliente = (Cliente)request.getSession().getAttribute("cliente");
	
	if (idCerveja != null){
		try {
			cerveja = Cerveja.obterCerveja(idCerveja);
			media = Avaliacao.obterPontuacaoMedia(idCerveja);
		}catch (Exception e){
			mensagem = "Erro ao consultar cerveja" + e.getMessage();	
		}	
	} else {
		mensagem = "Cerveja não encontrada";
	}
	
	if (request.getParameter("addCarta") != null){
		try{
			Carta.adicionarCarta(comercio.getIdComercio(), cerveja.getIdCerveja());
		}catch (Exception e){
			mensagem = "Erro ao adicionar cerveja à carta: " + e.getMessage();
		}
	} else if (request.getParameter("removeCarta") != null){
		try{
			Carta.removerCarta(comercio.getIdComercio(), cerveja.getIdCerveja());
		}catch (Exception e){
			mensagem = "Erro ao remover cerveja da carta: " + e.getMessage();
		}
	} else if (request.getParameter("nota") != null){
		Avaliacao avaliacao = new Avaliacao(idCerveja, cliente.getIdCliente(), Double.valueOf(request.getParameter("nota")));
		
		try {
			avaliacao.gravar();
			media = Avaliacao.obterPontuacaoMedia(idCerveja);
		}catch (Exception e){
			mensagem = "Erro: " + e.getMessage();
		}
	}
	
	if (comercio != null){
		tipoUser=3;
		
		try{
			estaNaCarta = Carta.cervejaEstaNaCarta(comercio.getIdComercio(), idCerveja);
		}catch (Exception e){
			mensagem = "Erro ao verificar carta: " + e.getMessage();
		}
	} else if (cliente != null){
		tipoUser = 4;
		
		try{
			pontuacao = Avaliacao.obterPontuacao(idCerveja, cliente.getIdCliente());
		}catch(Exception e){
			
		}
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
	<div class="container">
		<%if (cerveja != null){%>
			<h1><%=cerveja.getNomeCerveja() %></h1>
			<h3><%=cerveja.getFabricante() %></h3>
			<hr/>
			<p><%=cerveja.getDescricaoCerveja() %></p>
			<p><b>IBU:&nbsp;</b><%=cerveja.getIbu() %></p>
			<p><b>ABV:&nbsp;</b><%=cerveja.getAbv() %> %</p>
			<p><b>Pontuação média:&nbsp;</b><%=media %></p>
			<form name="f" method="post">		
				<input type="hidden" name="idCerveja" value="<%=idCerveja%>">
			<%if(tipoUser == 3){
				if (mensagem == null){%>
						
					<%if (estaNaCarta){%>
						<input class="btn btn-danger" type="submit" name="removeCarta" value="Remover da minha carta">
					<%}else{ %>
						<input class="btn btn-success" type="submit" name="addCarta" value="Adicionar à minha carta">	
					<%}%>
					
				<%} else {%>
					<h3><%=mensagem%></h3>
			<%}} else if (tipoUser == 4){%>
					<label for="nota">Avalie esta cerveja:</label>
					<select name="nota" onchange="f.submit()">
						<%for (Long i = 1l; i <= 5; i++){ %>
							<option value='<%=i%>' <%=pontuacao != null && pontuacao.longValue()==i?"selected":""%>><%=i%></option>
						<%}%>
					</select>

				<%}%>
			</form>
			<%} else {%>
				<h3><%=mensagem%></h3>
			<%}%>
	</div>
</body>
</html>