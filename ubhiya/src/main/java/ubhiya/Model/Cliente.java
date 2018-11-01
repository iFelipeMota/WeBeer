package ubhiya.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ubhiya.ConexaoMySQL;

public class Cliente extends Usuario{
	
	private Long idCliente;
	private String nome;
	private String endereco;
	public String status;
	
	public Cliente() {
	}
	
	public Cliente(Usuario u, Long idCliente, String nome, String endereco) {
		this.setId(u.getId());
		this.setLogin(u.getLogin());
		this.setSenha(u.getSenha());
		this.idCliente = idCliente;
		this.nome = nome;
		this.endereco = endereco;
	}

	public void cadastrarCliente() {
		Long idC = this.cadastrar(this.getLogin(), this.getSenha(), 2);
		
		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();
		
		String sql = "insert into Cliente (Usuario_id_usuario, nome, endereco) values (?,?,?)";

		try{
			PreparedStatement stmt = con.prepareStatement(sql);	
			stmt.setLong(1, idC);
			stmt.setString(2, this.nome);
			stmt.setString(3, this.endereco);
			stmt.execute();
			stmt.close();

			this.status = "Usuário "+this.getLogin()+" inserido com sucesso";
			con.close();
		}catch(Exception e) {
			System.out.println("Inclusão não realizada: "+e);
			status = "Problema na inclusão: " +idC +e;
		}
	}
	
	public void deletarCliente(Long id) {
		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();
		String sql = "delete from Cliente where Usuario_id_usuario = ?";

		try{
			PreparedStatement stmt = con.prepareStatement(sql);		
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();

			status = "Usuário excluído com sucesso";
			con.close();
		}catch(Exception e) {
			System.out.println("Exclusão não realizada: "+e);
			status = "Problema na exclusão: "+e;
		}
	}
	
	public void alterarCliente() {
		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();
		
		this.alterar();
		String sql = "update Cliente set nome = ?, endereco = ? where Usuario_id_usuario = ?";

		try{
			PreparedStatement stmt = con.prepareStatement(sql);		
			stmt.setString(1, this.nome);
			stmt.setString(2, this.endereco);
			stmt.setLong(3, this.getId());
			stmt.execute();
			stmt.close();

			this.status = "Usuário "+ this.getLogin() + " alterado com sucesso";
			con.close();
		}catch(Exception e) {
			System.out.println("Exclusão não realizada: "+e);
			this.status = "Problema na alteração: "+e;
		}
	}
	
	public List<Cliente> buscarClientes() {
		Connection con = ConexaoMySQL.conectar();
		String sql = "select a.id_usuario, a.login, a.senha, b.id_cliente, b.nome, b.endereco from Usuario as a, Cliente as b where b.Usuario_id_usuario = a.id_usuario";

		List<Cliente> clientes = new ArrayList<Cliente>(); 
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario(rs.getLong("id_usuario"), rs.getString("login"), rs.getString("senha"), 2);
				Cliente c = new Cliente(u, rs.getLong("id_cliente"), rs.getString("nome"), rs.getString("endereco"));
				clientes.add(c);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			this.status = "Problema na Pesquisa: "+e;
		}
		return clientes;		
	}
	
	public static Cliente obterCliente(Long idUsuario) throws SQLException{
		Connection con = ConexaoMySQL.conectar();
		String sql = "select a.id_usuario, a.login, a.senha, b.id_cliente, b.nome, b.endereco from Usuario as a, Cliente as b where b.Usuario_id_usuario = a.id_usuario and b.Usuario_id_usuario = ?";
		Cliente cliente = null;
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, idUsuario);
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			cliente = new Cliente(new Usuario(idUsuario, rs.getString(2), null, 2), rs.getLong(4), rs.getString(5), rs.getString(6));
		}
		rs.close();
		stmt.close();
		con.close();
		
		return cliente;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getStatus() {
		return status;
	}

	
}
