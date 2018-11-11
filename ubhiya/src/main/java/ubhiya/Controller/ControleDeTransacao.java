package ubhiya.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ubhiya.Model.Cerveja;
import ubhiya.Model.Fabricante;

/**
 * Servlet implementation class ControleDeTransacao
 */
public class ControleDeTransacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
		}catch (Exception e) {
			System.out.println("Erro: "+e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("cadastrarCerveja")!=null) {
			
			//Fabricante f = (Fabricante)request.getSession().getAttribute("fabricante");
			
			Cerveja cerva = new Cerveja(
					/*f.getIdFabricante()*/Long.valueOf(request.getSession().getAttribute("id").toString()),
					request.getParameter("nomeCerveja"),
					Long.valueOf(request.getParameter("tipoCerveja")),
					request.getParameter("descricaoCerveja"),
					Long.valueOf(request.getParameter("ibu")),
					Double.valueOf(request.getParameter("abv"))					
					);
			try {
				cerva.cadastrarCerveja();
				request.getSession().setAttribute("status", "Cadastro realizado com sucesso.");
				response.sendRedirect("jsp/cadastroCerveja.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block

				request.getSession().setAttribute("status", "Erro ao cadastrar.");
				response.sendRedirect("jsp/cadastroCerveja.jsp");
				e.printStackTrace();
			}
		}
		
		doGet(request, response);
	}

}
