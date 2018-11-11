<%@page import="ubhiya.Model.Fabricante"%>
<%@page import="java.util.List"%>
<%@page import="ubhiya.Model.Cerveja"%>
<%@page import="ubhiya.Dto.ListaPaginada"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@page import="ubhiya.Dto.CervejaDto"%>
<%@page import="ubhiya.Model.Carta"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	String path = request.getContextPath();

int pagina = 1;

if (request.getParameter("pagina") != null){
	pagina = Integer.parseInt(request.getParameter("pagina"));
}

Long totalPaginas = 0l;
ListaPaginada<CervejaDto> cervejasl = null;

try {
	cervejasl = Cerveja.buscarCervejas(pagina);
	totalPaginas = cervejasl.getTotalRegistros() / 10 + (cervejasl.getTotalRegistros() % 10 == 0 ? 0 : 1);

}catch (SQLException e){
	
}

%>

<html>
<head>
<meta charset="UTF-8">
<title>Cervejas</title>

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

	<div class="container">
		<div class="row">

			<%
			if(cervejasl == null){%>
				<div class="alert alert-warning" role="alert">
				Não há registros para serem exibidos.
			</div>
			<%}else{
			for(int i=0; i<=cervejasl.getLista().size()-1; i++){ 
				System.out.println(cervejasl.getLista().get(i).getIdCerveja());
				System.out.println(cervejasl.getLista().size());
			%>
			
			<div class="col-xs-6 col-md-3" onclick='<%="f"+i+".submit()"%>'>
				<form name='<%="f"+i%>' action="../ControleDeBusca">
					<input type="hidden" name="idCerveja" value="<%=cervejasl.getLista().get(i).getIdCerveja() %>">
					<div class="thumbnail">
						<div class="caption" style="overflow: hidden;">
							<h3><%=cervejasl.getLista().get(i).getNomeCerveja()%></h3>
							<%=cervejasl.getLista().get(i).getFabricante()%></br><%=cervejasl.getLista().get(i).getIbu()%></br>
							 <%=cervejasl.getLista().get(i).getAbv() %></br> Nota </br>
						</div>
					</div>
				</form>
			</div>

			<%}}%>
		</div>
		
		<nav aria-label="Page navigation example">
		<form method="post">
			<ul class="pagination">
				<%for(Long i=1l; i<=totalPaginas; i++){ %>
				<li class="page-item"><input type="submit" name="pagina"
					value="<%=i%>" <%=pagina==i?"disabled":""%>></li>
				<%}%>
			</ul>
		</form>
	</nav>
	</div>

	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script
		src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</body>
</html>