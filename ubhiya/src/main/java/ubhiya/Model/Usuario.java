package ubhiya.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ubhiya.ConexaoMySQL;

public class Usuario {

	private Long id;
	private String login;
	private String senha;
	private Integer tipoUser;
	private static String status;

	public Usuario() {
		login = "";
		senha = "";
		tipoUser = null;
	}

	public Usuario(Long id, String login, String senha, Integer tipoUser) {
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.tipoUser = tipoUser;
	}

	public static Usuario logar(String login, String senha) throws SQLException {
		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();
		String sql = "select * from Usuario where login=? and senha=?";
		
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, login);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			Usuario u = new Usuario();
			if(rs.next()) {
			u  = new Usuario(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getInt(4));			
			}
			
			rs.close();
			stmt.close();
			con.close();
			
			return u;
	}
	
	public void cadastrar() {
		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();
		String sql = "insert into Usuario (login, senha, tipo_usuario) values (?,?,?)";

		try{
			PreparedStatement stmt = con.prepareStatement(sql);		
			stmt.setString(1, this.login);
			stmt.setString(2, this.senha);
			stmt.setInt(3, this.tipoUser);
			stmt.execute();
			stmt.close();

			Usuario.status = "Usuário "+login+" inserido com sucesso";
			con.close();
		}catch(Exception e) {
			System.out.println("Inclusão não realizada: "+e);
			Usuario.status = "Problema na inclusão: "+e;
		}
	}

	public Long cadastrar(String login, String senha, Integer tipoUsuario) {
		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();
		
		Long idU=Long.valueOf(0);

		try{
			String sql = "insert into Usuario (login, senha, tipo_usuario) values (?,?,?)";

			PreparedStatement stmt = con.prepareStatement(sql);		
			stmt.setString(1, this.login);
			stmt.setString(2, this.senha);
			stmt.setInt(3, this.tipoUser);
			stmt.execute();
			stmt.close();
			
			Usuario.status = "Usuário "+login+" inserido com sucesso";

			sql = "select max(id_usuario) from Usuario";

			PreparedStatement stmt2 = con.prepareStatement(sql);
			ResultSet rs = stmt2.executeQuery();
			
			while(rs.next()) {
				idU  = rs.getLong(1);
			}
			
			
			rs.close();
			stmt2.close();

			con.close();
		}catch(Exception e) {
			System.out.println("Inclusão não realizada: "+e);
			Usuario.status = "Problema na inclusão: "+e;
		}

		return idU;
	}

	public void deletar(Long id) {
		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();
		String sql = "delete from Usuario where id_usuario = ?";

		try{
			PreparedStatement stmt = con.prepareStatement(sql);		
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();

			Usuario.status = "Usuário excluído com sucesso";
			con.close();
		}catch(Exception e) {
			System.out.println("Exclusão não realizada: "+e);
			Usuario.status = "Problema na exclusão: "+e;
		}
	}

	public void alterar() {
		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();
		String sql = "update Usuario set login = ?, senha = ? where id_usuario = ?";

		try{
			PreparedStatement stmt = con.prepareStatement(sql);		
			stmt.setString(1, this.login);
			stmt.setString(2, this.senha);
			stmt.setLong(3, this.id);
			stmt.execute();
			stmt.close();

			Usuario.status = "Usuário "+ this.login + " alterado com sucesso";
			con.close();
		}catch(Exception e) {
			System.out.println("Exclusão não realizada: "+e);
			Usuario.status = "Problema na alteração: "+e;
		}
	}

	public List<Usuario> buscarAdm() {
		Connection con = ConexaoMySQL.conectar();
		String sql = "select * from Usuario where tipo_usuario=1";

		List<Usuario> admns = new ArrayList<Usuario>(); 
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario(rs.getLong("id_usuario"), rs.getString("login"), rs.getString("senha"), 1);
				admns.add(u);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			Usuario.status = "Problema na Pesquisa: "+e;
		}
		return admns;		
	}
	
	public static boolean existeLogin(String login) throws SQLException {
		Connection con = ConexaoMySQL.conectar();
		String sql = "select * from Usuario where login = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, login);
		ResultSet rs = stmt.executeQuery();
		boolean ret = false;
		if (rs.next()) {
			ret = true;
		}
		rs.close();
		stmt.close();
		return ret;
	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Integer getTipoUser() {
		return tipoUser;
	}
	public void setTipoUser(Integer tipoUser) {
		this.tipoUser = tipoUser;
	}
	public String getStatus() {
		return status;
	}
	public static void setStatus(String status) {
		Usuario.status = status;
	}
}
