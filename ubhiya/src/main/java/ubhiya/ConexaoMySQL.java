package ubhiya;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
	public static String status = "Não Conectado.";
	
	public static java.sql.Connection conectar(){
		Connection con = null;
		
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
			
			String server = "localhost";
			String banco = "webeerDev";
			String url = "jdbc:mysql://localhost:3306/webeer?useTimezone=true&serverTimezone=UTC";//"jdbc:mysql://db:3306/webeer";
			String user = "webeer"; //"root";
			String pass = "webeer";  //null;
			
			con = DriverManager.getConnection(url, user, pass);
			
			if(con != null) {
				status = "Conectado com sucesso.";
			}
			else {
				status = "Não foi possível conectar.";
			}
			
			return con;
		} catch(ClassNotFoundException e) {
			status = "O driver especificado não foi encontrado."+e;
			System.out.println("O driver especificado não foi encontrado.");
			return null;
		} catch(SQLException e) {
			status = "Não foi possível conectar ao banco. "+e;
			System.out.println("Não foi possível conectar ao banco. "+e);
			return null;
		}
	}
	
	public static String conStatus() {
		return status;
	}
	
	public static boolean desconectar() {
		try {
			ConexaoMySQL.conectar().close();
			return true;
		}catch (SQLException e) {
			return false;
		}
	}
	
	public static java.sql.Connection reiniciar(){
		desconectar();
		return conectar();
	}
}
