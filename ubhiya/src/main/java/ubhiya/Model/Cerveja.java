package ubhiya.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ubhiya.ConexaoMySQL;
import ubhiya.Dto.CervejaDto;
import ubhiya.Dto.ListaPaginada;

public class Cerveja {

	private Long idCerveja;
	private Long idFabricante;
	private String nomeCerveja;
	//TODO Definir como ser� o tipo de cerveja (string ou fk de uma nova tabela de tipos)
	private Long tipoCerveja;//private String tipoCerveja;
	private String descricaoCerveja;
	private Long ibu;
	private double abv;
	
	public Cerveja() {}
	public Cerveja(Long idFabricante, String nomeCerveja, Long tipoCerveja, String descricaoCerveja, Long ibu, double abv) {
		this.idFabricante = idFabricante;
		this.nomeCerveja = nomeCerveja;
		this.tipoCerveja = tipoCerveja;
		this.descricaoCerveja = descricaoCerveja;
		this.ibu = ibu;
		this.abv = abv;
	}
	
	public void cadastrarCerveja() throws SQLException{

		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();

		String sql = "insert into Cerveja (Fabricante_id_fabricante, nome_cerveja, tipo_cerveja, descricao_cerveja, ibu, abv) values (?,?,?,?,?,?)";

		PreparedStatement stmt = con.prepareStatement(sql);	
		stmt.setLong(1, this.idFabricante);
		stmt.setString(2, this.nomeCerveja);
		stmt.setLong(3, this.tipoCerveja);//stmt.setString(3, this.tipoCerveja);
		stmt.setString(4, this.descricaoCerveja);
		stmt.setLong(5, this.ibu);
		stmt.setDouble(6, this.abv);
		stmt.execute();
		stmt.close();

		con.close();

	}
	
	public void deletarCerveja(Long id) throws SQLException{
		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();
		String sql = "delete from Cerveja where id_cerveja = ?";

		
		PreparedStatement stmt = con.prepareStatement(sql);		
		stmt.setLong(1, id);
		stmt.execute();
		stmt.close();

		con.close();
	}

	public void alterarCerveja() throws SQLException{
		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();

		String sql = "update Cerveja set nome_cerveja = ?, tipo_cerveja = ?, descricao_cerveja = ?, ibu = ?, abv = ? where id_cerveja = ?";

		PreparedStatement stmt = con.prepareStatement(sql);		
		stmt.setString(1, this.nomeCerveja);
		stmt.setLong(2, this.tipoCerveja);//stmt.setString(2, this.tipoCerveja);
		stmt.setString(3, this.descricaoCerveja);
		stmt.setLong(4, this.ibu);
		stmt.setDouble(5, this.abv);
		stmt.setLong(6, this.idCerveja);
		stmt.execute();
		stmt.close();

		con.close();

	}

	public static ListaPaginada<CervejaDto> buscarCervejas(int pagina) throws SQLException {
		Connection con = ConexaoMySQL.conectar();
		String sql = "select f.nome_fantasia,"
				+ " c.id_cerveja, c.nome_cerveja, c.descricao_cerveja, c.ibu, c.abv"
				+ " from Cerveja c inner join Fabricante f on c.Fabricante_id_fabricante = f.Usuario_id_usuario "
				+ "limit 0,10";
				//"select f.nome_fantasia, c.id_cerveja, c.nome_cerveja, c.descricao_cerveja, c.ibu, c.abv from Cerveja c "
				//+ "inner join Fabricante f on f.id_fabricante = c.Fabricante_id_fabricante "
				//+ "limit " + (pagina-1)*10 + ",10"; 

		List<CervejaDto> cervejas = new ArrayList<CervejaDto>();
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			CervejaDto cerva = new CervejaDto(rs.getLong(2), rs.getString(3), rs.getString(1), rs.getString(4), rs.getInt(5), rs.getDouble(6));
			cervejas.add(cerva);
		}
		//rs.close();
		stmt.close(); 
		System.out.println(cervejas.size());
		
		Long total = 0l;
		sql = "select count(*) from Cerveja";
		PreparedStatement stmts = con.prepareStatement(sql);
		ResultSet rss = stmts.executeQuery();

		System.out.println("count");
		if (rss.next()) {
			total = rss.getLong(1);
		}
		con.close();
		System.out.println(total);
		
		return new ListaPaginada<CervejaDto>(cervejas, total);		
	}
	
	public static ListaPaginada<CervejaDto> buscarCervejasFabricante(Long idFabricante, int pagina) throws SQLException {
		Connection con = ConexaoMySQL.conectar();
		String sql = "select f.nome_fantasia, c.id_cerveja, c.nome_cerveja, c.descricao_cerveja, c.ibu, c.abv "
				+ "from Cerveja c "
				+ "inner join Fabricante f on c.Fabricante_id_fabricante = f.Usuario_id_usuario "
				+ "where f.id_fabricante = ? "
				+ "limit " + (pagina-1)*10 + ",10"; 

		List<CervejaDto> cervejas = new ArrayList<CervejaDto>(); 
		System.out.println(idFabricante);
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, idFabricante);
		ResultSet rs = stmt.executeQuery(); 
		System.out.println("Passou");
		
		while(rs.next()) {
			CervejaDto cerva = new CervejaDto(rs.getLong(2), rs.getString(3), rs.getString(1), rs.getString(4), rs.getInt(5), rs.getDouble(6));
			cervejas.add(cerva);
		}
		System.out.println(cervejas.size());
		//rs.close();
		stmt.close();
		System.out.println(cervejas.size());
		
		Long total = 0l;
		sql = "select count(*) from Cerveja";
		PreparedStatement stmts = con.prepareStatement(sql);
		System.out.println(cervejas.size());
		ResultSet rss = stmts.executeQuery();

		System.out.println("count");
		if (rss.next()) {
			total = rss.getLong(1);
		}
		con.close();
		System.out.println(total);
		
		return new ListaPaginada<CervejaDto>(cervejas, total);		
	}

	
	public static CervejaDto obterCerveja (Long idCerveja) throws SQLException {
		//TODO acrescentar campo com a m�dia das avalia��es
		Connection con = ConexaoMySQL.conectar();
		String sql = "select f.nome_fantasia, c.id_cerveja, c.nome_cerveja, c.ibu, c.abv, c.descricao_cerveja from Cerveja c "
				+ "inner join Fabricante f on f.id_fabricante = c.Fabricante_id_fabricante "
				+ "where c.id_cerveja = ?"; 

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, idCerveja);
		ResultSet rs = stmt.executeQuery();
		
		CervejaDto cerva = null;
		
		if(rs.next()) {
			cerva = new CervejaDto(rs.getLong(2), rs.getString(3), rs.getString(1), rs.getString(6), rs.getInt(4), rs.getDouble(5));
		} else {
			
		}
		rs.close();
		stmt.close();
		con.close();
		
		return cerva;
	}
	
	//Getters e setters
	public Long getIdFabricante() {
		return idFabricante;
	}
	public void setIdFabricante(Long idFabricante) {
		this.idFabricante = idFabricante;
	}
	public String getNomeCerveja() {
		return nomeCerveja;
	}
	public void setNomeCerveja(String nomeCerveja) {
		this.nomeCerveja = nomeCerveja;
	}
	public Long getTipoCerveja() {
		return tipoCerveja;
	}
	public void setTipoCerveja(Long tipoCerveja) {
		this.tipoCerveja = tipoCerveja;
	}
	public String getDescricaoCerveja() {
		return descricaoCerveja;
	}
	public void setDescricaoCerveja(String descricaoCerveja) {
		this.descricaoCerveja = descricaoCerveja;
	}
	public Long getIbu() {
		return ibu;
	}
	public void setIbu(Long ibu) {
		this.ibu = ibu;
	}
	public double getAbv() {
		return abv;
	}
	public void setAbv(double abv) {
		this.abv = abv;
	}
		
	public Long getIdCerveja() {
		return idCerveja;
	}
	public void setIdCerveja(Long idCerveja) {
		this.idCerveja = idCerveja;
	}
}