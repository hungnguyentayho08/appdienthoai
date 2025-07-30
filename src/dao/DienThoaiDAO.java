package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Dienthoai;
import util.DBConnection;

public class DienThoaiDAO {
	public List<Dienthoai> getAll(){
		List<Dienthoai> list = new ArrayList<>();
		Connection conn = DBConnection.getConnection();
		String sql = "SELECT * FROM dienthoai" ;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString("ma");
				String ten = rs.getString("ten");
				String hang = rs.getString("hang");
				double gia = rs.getDouble("gia");
				int soluong = rs.getInt("soluong");
				list.add(new Dienthoai(ma , ten ,hang ,gia ,soluong));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list ;
	} 
}
