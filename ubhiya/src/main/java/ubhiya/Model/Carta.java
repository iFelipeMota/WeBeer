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
