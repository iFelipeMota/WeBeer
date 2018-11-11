package ubhiya.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ubhiya.Model.Cliente;
import ubhiya.Model.Comercio;
import ubhiya.Model.Fabricante;
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
			session.setAttribute("status", null);

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
				session.setAttribute("usuario", u);
				System.out.println("tipo: "+session.getAttribute("tipo"));

				System.out.println(session.getId());

				if(/*session.getAttribute("tipo")*/u.getTipoUser()==null) {
					session.setAttribute("status", "Login não realizado. Usuário ou senha inválidos");
					response.sendRedirect("index.jsp");
				}else if(/*session.getAttribute("tipo").toString().equals("1")*/u.getTipoUser()==1) {
					response.sendRedirect("jsp/welcomeAdmin.jsp");                	
				}else if(u.getTipoUser()==2) {
					Cliente cliente = Cliente.obterCliente(u.getId());
					session.setAttribute("cliente", cliente);
					response.sendRedirect("jsp/welcomeCliente.jsp");
				}else if(u.getTipoUser()==3) {
					Comercio comercio = Comercio.obterComercio(u.getId());
					session.setAttribute("comercio", comercio);
					response.sendRedirect("jsp/welcomeComercio.jsp");
				}else if(u.getTipoUser()==4) {
					Fabricante fabricante = Fabricante.obterFabricante(u.getId());
					session.setAttribute("fabricante", fabricante);
					response.sendRedirect("jsp/welcomeFabricante.jsp");
				}
			}
			else if(request.getParameter("logout") != null /*|| request.getParameter("userNAme").equals(null)*/){
				System.out.println("Logout");
				session.invalidate();
				response.sendRedirect("index.jsp");
			}
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
