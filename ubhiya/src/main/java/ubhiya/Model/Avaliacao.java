package ubhiya.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import ubhiya.ConexaoMySQL;

public class Avaliacao {
	private Long idCerveja;
	private Long idCliente;
	private Date dataAvaliacao;
	private Double pontuacao;
	
	public Avaliacao (Long idCerveja, Long idCliente, Double pontuacao) {
		this.idCerveja = idCerveja;
		this.idCliente = idCliente;
		this.pontuacao = pontuacao;
	}
	
	public void gravar() throws SQLException {
		Double pontuacaoAnterior = this.obterPontuacao(this.idCerveja, this.idCliente);
		
		Connection con = ConexaoMySQL.conectar();
		
		if (pontuacaoAnterior == null) {
			String sql = "insert into Avaliacao (Cliente_id_cliente, Cerveja_id_cerveja, pontuacao, data_avaliacao) values (?,?,?, sysdate())";
	
			PreparedStatement stmt = con.prepareStatement(sql);	
			stmt.setLong(1, this.idCliente);
			stmt.setLong(2, this.idCerveja);
			stmt.setDouble(3, this.pontuacao);
			stmt.execute();
			stmt.close();
		} else {
			String sql = "update Avaliacao set pontuacao = ?, data_avaliacao = sysdate() where Cliente_id_cliente = ? and Cerveja_id_cerveja = ?"; 
			
			PreparedStatement stmt = con.prepareStatement(sql);	
			stmt.setLong(2, this.idCliente);
			stmt.setLong(3, this.idCerveja);
			stmt.setDouble(1, this.pontuacao);
			stmt.execute();
			stmt.close();
		}
		con.close();
	}
	
	public static Double obterPontuacao(Long idCerveja, Long idCliente) throws SQLException{
		Connection con = ConexaoMySQL.conectar();
		
		String sql = "select pontuacao from Avaliacao where Cliente_id_cliente = ? and Cerveja_id_cerveja = ?";

		PreparedStatement stmt = con.prepareStatement(sql);	
		stmt.setLong(1, idCliente);
		stmt.setLong(2, idCerveja);
		ResultSet rs = stmt.executeQuery();
		
		Double ret = null;
		
		if (rs.next()) {
			ret = rs.getDouble(1);
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		return ret;
	}
	
	public static Double obterPontuacaoMedia(Long idCerveja) throws SQLException{
		Connection con = ConexaoMySQL.conectar();
		
		String sql = "select avg(pontuacao) from Avaliacao where Cerveja_id_cerveja = ?";

		PreparedStatement stmt = con.prepareStatement(sql);	
		stmt.setLong(1, idCerveja);
		ResultSet rs = stmt.executeQuery();
		
		Double ret = null;
		
		if (rs.next()) {
			ret = rs.getDouble(1);
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		return ret;
	}
	
	public Long getIdCerveja() {
		return idCerveja;
	}
	public void setIdCerveja(Long idCerveja) {
		this.idCerveja = idCerveja;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}
	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}
	public Double getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(Double pontuacao) {
		this.pontuacao = pontuacao;
	}
}
