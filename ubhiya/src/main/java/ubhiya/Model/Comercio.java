package ubhiya.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ubhiya.ConexaoMySQL;

public class Comercio extends Usuario{

	private Long idComercio;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String ie;
	public String status;

	public Comercio() {
	}

	public Comercio(Usuario u, Long idComercio, String razaoSocial, String cnpj, String ie, String nomeFantasia) {
		this.setId(u.getId());
		this.setLogin(u.getLogin());
		this.setSenha(u.getSenha());
		this.idComercio = idComercio;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.ie = ie;
	}

	public void cadastrarComercio() {
		Long idC = this.cadastrar(this.getLogin(), this.getSenha(), 3);

		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();

		String sql = "insert into Estabelecimento (Usuario_id_usuario, razao_social, CNPJ, IE, nome_fantasia) values (?,?,?,?,?)";

		try{
			PreparedStatement stmt = con.prepareStatement(sql);	
			stmt.setLong(1, idC);
			stmt.setString(2, this.razaoSocial);
			stmt.setString(3, this.cnpj);
			stmt.setString(4, this.ie);
			stmt.setString(5, this.nomeFantasia);
			stmt.execute();
			stmt.close();

			this.status = "Usuário "+this.getLogin()+" inserido com sucesso";
			con.close();
		}catch(Exception e) {
			System.out.println("Inclusão não realizada: "+e);
			status = "Problema na inclusão: " +idC +e;
		}
	}

	public void deletarComercio(Long id) {
		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();
		String sql = "delete from Estabelecimento where Usuario_id_usuario = ?";

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

	public void alterarComercio() {
		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();

		this.alterar();
		String sql = "update Estabelecimento set razao_social = ?, CNPJ = ?, IE = ?, nome_fantasia = ? where Usuario_id_usuario = ?";

		try{
			PreparedStatement stmt = con.prepareStatement(sql);		
			stmt.setString(1, this.razaoSocial);
			stmt.setString(2, this.cnpj);
			stmt.setString(3, this.ie);
			stmt.setString(4, this.nomeFantasia);
			stmt.setLong(5, this.getId());
			stmt.execute();
			stmt.close();

			this.status = "Usuário "+ this.getLogin() + " alterado com sucesso";
			con.close();
		}catch(Exception e) {
			System.out.println("Exclusão não realizada: "+e);
			this.status = "Problema na alteração: "+e;
		}
	}

	public List<Comercio> buscarComercios() {
		Connection con = ConexaoMySQL.conectar();
		String sql = "select a.id_usuario, a.login, a.senha, b.id_estabelecimento, b.razao_social, b.CNPJ, b.IE, b.nome_fantasia from Usuario as a, Estabelecimento as b where b.Usuario_id_usuario = a.id_usuario";

		List<Comercio> comercios = new ArrayList<Comercio>(); 
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario(rs.getLong("id_usuario"), rs.getString("login"), rs.getString("senha"), 3);
				Comercio c = new Comercio(u, rs.getLong("id_estabelecimento"), rs.getString("razao_social"), rs.getString("CNPJ"), rs.getString("IE"), rs.getString("nome_fantasia"));
				comercios.add(c);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			this.status = "Problema na Pesquisa: "+e;
		}
		return comercios;		
	}

	public Long getIdComercio() {
		return idComercio;
	}

	public void setIdComercio(Long idComercio) {
		this.idComercio = idComercio;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getStatus() {
		return status;
	}

}
