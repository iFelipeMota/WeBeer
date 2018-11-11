package ubhiya.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ubhiya.ConexaoMySQL;

public class Fabricante  extends Usuario{

	private Long idFabricante;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String ie;
	public String status;

	public Fabricante() {
	}

	public Fabricante(Usuario u, Long idFabricante, String razaoSocial, String cnpj, String ie, String nomeFantasia) {
		this.setId(u.getId());
		this.setLogin(u.getLogin());
		this.setSenha(u.getSenha());
		this.idFabricante = idFabricante;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.ie = ie;
	}

	public void cadastrarFabricante() {
		Long idC = this.cadastrar(this.getLogin(), this.getSenha(), 4);

		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();

		String sql = "insert into Fabricante (Usuario_id_usuario, razao_social, CNPJ, IE, nome_fantasia) values (?,?,?,?,?)";

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

	public void deletarFabricante(Long id) {
		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();
		String sql = "delete from Fabricante where Usuario_id_usuario = ?";

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

	public void alterarFabricante() {
		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();

		this.alterar();
		String sql = "update Fabricante set razao_social = ?, CNPJ = ?, IE = ?, nome_fantasia = ? where Usuario_id_usuario = ?";

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

	public List<Fabricante> buscarFabricantes() {
		Connection con = ConexaoMySQL.conectar();
		String sql = "select a.id_usuario, a.login, a.senha, b.id_fabricante, b.razao_social, b.CNPJ, b.IE, b.nome_fantasia from Usuario as a, Fabricante as b where b.Usuario_id_usuario = a.id_usuario";

		List<Fabricante> fabricantes = new ArrayList<Fabricante>(); 
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario(rs.getLong("id_usuario"), rs.getString("login"), rs.getString("senha"), 4);
				Fabricante c = new Fabricante(u, rs.getLong("id_fabricante"), rs.getString("razao_social"), rs.getString("CNPJ"), rs.getString("IE"), rs.getString("nome_fantasia"));
				fabricantes.add(c);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			this.status = "Problema na Pesquisa: "+e;
		}
		return fabricantes;		
	}
	
	public static Fabricante obterFabricante(Long idUsuario) throws SQLException{
		Connection con = ConexaoMySQL.conectar();
		String sql = "select a.id_usuario, a.login, a.senha, b.id_fabricante, b.nome_fantasia, b.razao_social, b.cnpj, b.ie from Usuario as a, Fabricante as b where b.Usuario_id_usuario = a.id_usuario and b.Usuario_id_usuario = ?";
		Fabricante fabricante = null;
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, idUsuario);
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			fabricante = new Fabricante(new Usuario(idUsuario, rs.getString(2), null, 4), rs.getLong(4), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(5));
		}
		rs.close();
		stmt.close();
		con.close();
		
		return fabricante;
	}

	//M�todo para verificar se j� existe fabricante cadastrado com o cnpj informado
	public static boolean existeCnpj(String cnpj) throws SQLException {
		Connection con = ConexaoMySQL.conectar();
		String sql = "select * from Fabricante where cnpj = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, cnpj);
		ResultSet rs = stmt.executeQuery();
		boolean ret = false;
		if (rs.next()) {
			ret = true;
		}
		rs.close();
		stmt.close();
		return ret;
	}
	
	public Long getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(Long idFabricante) {
		this.idFabricante = idFabricante;
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
