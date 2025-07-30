package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() {
		Connection conn = null ;
		try {
			String url = "jdbc:mysql://localhost:3306/dienthoai_db" ;
			String user = "root" ;
			String pass = "" ;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user ,pass);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
