package model;

public class Dienthoai {
	private String ma ;
	private String ten ;
	private String hang ;
	private double gia ;
	private int soluong ;
	public Dienthoai(String ma, String ten, String hang, double gia, int soluong) {
		super();
		this.ma = ma;
		this.ten = ten;
		this.hang = hang;
		this.gia = gia;
		this.soluong = soluong;
	}
	public String getMa() {
		return ma;
	}
	public void setMa(String ma) {
		this.ma = ma;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getHang() {
		return hang;
	}
	public void setHang(String hang) {
		this.hang = hang;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	
	
	

}
