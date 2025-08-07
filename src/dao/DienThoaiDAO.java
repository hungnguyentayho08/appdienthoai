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
				int ma = rs.getInt("ma");
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
			ps.setInt(1, dt.getMa()) ;
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
	
	public boolean delete(int ma) {
		Connection conn = DBConnection.getConnection();
		String sql = "DELETE FROM dienthoai WHERE ma = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1 ,ma);
			return ps.executeUpdate() > 0 ;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	} 
	
	public boolean updateDienThoai(Dienthoai dt) {
	    String sql = "UPDATE dienthoai SET ten = ?, hang = ?, gia = ?, soluong = ? WHERE ma = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, dt.getTen());
	        stmt.setString(2, dt.getHang());
	        stmt.setDouble(3, dt.getGia());
	        stmt.setInt(4, dt.getSoluong());
	        stmt.setInt(5, dt.getMa()); // WHERE ma = ?

	        int rows = stmt.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public List<Dienthoai> timKiemDienThoai(String keyword) {
	    List<Dienthoai> result = new ArrayList<>();
	    String sql = "SELECT * FROM dienthoai WHERE ten LIKE ? OR hang LIKE ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, "%" + keyword + "%");
	        stmt.setString(2, "%" + keyword + "%");
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Dienthoai dt = new Dienthoai(
	                rs.getInt("ma"),
	                rs.getString("ten"),
	                rs.getString("hang"),
	                rs.getDouble("gia"),
	                rs.getInt("soluong")
	            );
	            result.add(dt);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}



				
				
				
				
	}
	
	

