package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	
	public boolean insert(Dienthoai dt) {
		Connection conn = DBConnection.getConnection();
		String sql = "INSERT INTO dienthoai(ma, ten, hang, gia, soluong) VALUES (?, ?, ?, ?, ?)" ;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dt.getMa()) ;
			ps.setString(2, dt.getTen()) ;
			ps.setString(3, dt.getHang()) ;
			ps.setDouble(4, dt.getGia()) ;
			ps.setInt(5, dt.getSoluong()) ;
			
			return ps.executeUpdate() > 0 ;
					
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false ;
	}
	
	public boolean delete(String ma) {
		Connection conn = DBConnection.getConnection();
		String sql = "DELETE FROM dienthoai WHERE ma = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1 ,ma);
			return ps.executeUpdate() > 0 ;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	} 
}
