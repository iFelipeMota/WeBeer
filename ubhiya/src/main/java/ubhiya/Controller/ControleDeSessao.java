package ubhiya.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ubhiya.Model.Usuario;


public class ControleDeSessao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControleDeSessao() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) /*throws ServletException, IOException*/ {
		try{
			response.setContentType("text/html");
			HttpSession session=request.getSession(true);
			//String n=(String)session.getAttribute("uname");
			String n;
			String s;

			if (request.getParameter("login") != null){
				request.getSession(true);
				System.out.println("Nova Sessão: "+session.isNew());

				n = request.getParameter("userName");
				System.out.println("nome: "+n);
				session.setAttribute("userName",n);

				s= request.getParameter("senha");
				System.out.println("senha: "+s);
				session.setAttribute("senha", s);

				Usuario u = new Usuario();
				u = Usuario.logar(n, s);
				session.setAttribute("id", u.getId());
				session.setAttribute("tipo", u.getTipoUser());

				System.out.println(session.getId());

				if(session.getAttribute("id").equals("")) {
					response.sendRedirect("index.jsp");
				}else {
					response.sendRedirect("jsp/cadUser.jsp");                	
				}
			}
			/*else if(request.getParameter("login") != null){
                System.out.println("Logout");
                session.invalidate();
                request.getSession(true);
                System.out.println("Login Novo User " +session.isNew());
                n = request.getParameter("userName");
                System.out.println("nome: "+n);
                session.setAttribute("userName",n);
                System.out.println(session.getId());
                response.sendRedirect("home.jsp");
            }*/
			else if(request.getParameter("logout") != null || request.getParameter("userNAme").equals(null)){
				System.out.println("Logout");
				session.invalidate();
				response.sendRedirect("index.jsp");
			}
			/*
            else if(request.getParameter("quiz") != null){
                System.out.println("Quiz");
                response.sendRedirect("index.jsp");
            }*/
			else{
				System.out.println("Trabson");
				request.setAttribute("userName", null);
				response.sendRedirect("home.jsp");
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
