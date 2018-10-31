package ubhiya.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ubhiya.ConexaoMySQL;

public class Cerveja {

	private Long idCerveja;
	private Long idFabricante;
	private String nomeCerveja;
	private String tipoCerveja;
	private String descricaoCerveja;
	private Long ibu;
	private double abv;
	
	public Cerveja() {}
	
	public void cadastrarFabricante() throws SQLException{

		new ConexaoMySQL();
		Connection con = ConexaoMySQL.conectar();

		String sql = "insert into Cerveja (id_fabricante, nome_cerveja, tipo_cerveja, descricao_cerveja, ibu, abv) values (?,?,?,?,?,?)";

		PreparedStatement stmt = con.prepareStatement(sql);	
		stmt.setLong(1, this.idFabricante);
		stmt.setString(2, this.nomeCerveja);
		stmt.setString(3, this.tipoCerveja);
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
		stmt.setString(2, this.tipoCerveja);
		stmt.setString(3, this.descricaoCerveja);
		stmt.setLong(4, this.ibu);
		stmt.setDouble(5, this.abv);
		stmt.setLong(6, this.idCerveja);
		stmt.execute();
		stmt.close();

		con.close();

	}

	public List<Cerveja> buscarCervejas() throws SQLException {
		//Connection con = ConexaoMySQL.conectar();
		//TODO Implementar busca de cervejas
		return null;		
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
	public String getTipoCerveja() {
		return tipoCerveja;
	}
	public void setTipoCerveja(String tipoCerveja) {
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