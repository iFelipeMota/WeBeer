package ubhiya.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ubhiya.ConexaoMySQL;
import ubhiya.Dto.CervejaDto;

public class Carta {
	private Long idCerveja;
	private Long idComercio;
	
	public Carta() {}
	
	public Carta(Long idCerveja, Long idComercio) {
		this.idCerveja = idCerveja;
		this.idComercio = idComercio;
	}
	
	public static List<CervejaDto> obterCartaPorComercio(Long idComercio) throws SQLException{
		Connection con = ConexaoMySQL.conectar();
		String sql = "select Cerveja.id_cerveja, Cerveja.nome_cerveja, Fabricante.nome_fantasia "
				+ "from Carta, Cerveja, Fabricante "
				+ "where Carta.Cerveja_id_cerveja = Cerveja.id_cerveja "
				+ "and Fabricante.id_fabricante = Cerveja.Fabricante_id_fabricante "
				+ "and Carta.Estabelecimento_id_estabelecimento = ?";

		List<CervejaDto> cervejas = new ArrayList<CervejaDto>(); 
		
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, idComercio);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				CervejaDto cerva = new CervejaDto(rs.getLong(1), rs.getString(2), rs.getString(3), null, null, null);
				cervejas.add(cerva);
			}
			rs.close();
			stmt.close();
			con.close();
		return cervejas;
	}
	
	public static boolean cervejaEstaNaCarta(Long idComercio, Long idCerveja) throws SQLException {
		Connection con = ConexaoMySQL.conectar();
		String sql = "select * from Carta where Estabelecimento_id_estabelecimento = ? and Cerveja_id_cerveja = ?";
		boolean ret = false;
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, idComercio);
		stmt.setLong(2, idCerveja);
		
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			ret = true;
		}
		rs.close();
		stmt.close();
		con.close();
		
		return ret;
	}
	
	public static void adicionarCarta(Long idComercio, Long idCerveja) throws SQLException{
		Connection con = ConexaoMySQL.conectar();

		String sql = "insert into Carta (Estabelecimento_id_estabelecimento, Cerveja_id_cerveja) values (?,?)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, idComercio);
		stmt.setLong(2, idCerveja);
		stmt.execute();
		
		stmt.close();
		con.close();
	}
	
	public static void removerCarta(Long idComercio, Long idCerveja) throws SQLException{
		Connection con = ConexaoMySQL.conectar();

		String sql = "delete from Carta where Estabelecimento_id_estabelecimento = ? and Cerveja_id_cerveja = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, idComercio);
		stmt.setLong(2, idCerveja);
		stmt.execute();
		
		stmt.close();
		con.close();
	}
	
	public Long getIdCerveja() {
		return idCerveja;
	}
	public void setIdCerveja(Long idCerveja) {
		this.idCerveja = idCerveja;
	}
	public Long getIdComercio() {
		return idComercio;
	}
	public void setIdComercio(Long idComercio) {
		this.idComercio = idComercio;
	}
}
