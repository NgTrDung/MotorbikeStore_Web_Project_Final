package entity;

import java.util.Date;

import dao.DAO;

public class HoaDon {
	private int maHoaDon;
	private int maAccount;
	private double tongTien;
	private Date ngayThanhToan;
	
	private String strTongTien;
	
	public HoaDon(int maHoaDon, int maAccount, double tongTien, Date ngayThanhToan) 
	{
		
		this.setMaHoaDon(maHoaDon);
		this.setMaAccount(maAccount);
		this.setTongTien(tongTien);
		this.setNgayThanhToan(ngayThanhToan);
		
		DAO dao = new DAO();
		String kq= dao.chuyenDoiSo(String.format("%.0f",this.tongTien));
		this.strTongTien = kq;
	}
	public HoaDon() {
		DAO dao = new DAO();
		String kq= dao.chuyenDoiSo(String.format("%.0f",this.tongTien));
		this.strTongTien = kq;
	}
	
	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + getMaHoaDon() + ", maAccount=" + getMaAccount() + ", tongTien=" + getTongTien() 
				+ ", ngayThanhToan=" + getNgayThanhToan() + "]";
	}
	public int getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public int getMaAccount() {
		return maAccount;
	}
	public void setMaAccount(int maAccount) {
		this.maAccount = maAccount;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public Date getNgayThanhToan() {
		return ngayThanhToan;
	}
	public void setNgayThanhToan(Date ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}
	public String getStrTongTien() {
		return strTongTien;
	}
	public void setStrTongTien(String strTongTien) {
		this.strTongTien = strTongTien;
	}
	
	
}
