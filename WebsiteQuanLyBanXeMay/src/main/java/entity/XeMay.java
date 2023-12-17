package entity;

import dao.DAO;

public class XeMay {
	private int maXe;
    private String tenXe;
    private String hinhAnh1;
    private Double giaTien;
    private String title;
    private String gioiThieu;
    private String khoiLuong;
    private String daiRongCao;
    private String dungTichXiLanh;
    private String tiSoNen;
    private String dungTichBinhXang;
    private String hinhAnh2;
    private String hinhAnh3;
    private String hinhAnh4;
    private int soLuongCon;
    private int soLuongDaBan;
    
    private String strGiaTien;

    public XeMay()
    {
//    	DAO dao = new DAO();
//		String kq= dao.chuyenDoiSo(String.format("%.0f",this.giaTien));
//		this.strGiaTien = kq;
    }

	public XeMay(int maXe, String tenXe, String hinhAnh1, Double giaTien, String title, 
			String gioiThieu, String khoiLuong, String daiRongCao, String dungTichXiLanh, 
			String tiSoNen, String dungTichBinhXang, String hinhAnh2, String hinhAnh3, String hinhAnh4,
			int soLuongCon, int soLuongDaBan) 
	{		
		this.setMaXe(maXe);
		this.setTenXe(tenXe);
		this.setHinhAnh1(hinhAnh1);
		this.setGiaTien(giaTien);
		this.setTitle(title);
		this.setGioiThieu(gioiThieu);
		this.setKhoiLuong(khoiLuong);
		this.setDaiRongCao(daiRongCao);
		this.setDungTichXiLanh(dungTichXiLanh);
		this.setTiSoNen(tiSoNen);
		this.setDungTichBinhXang(dungTichBinhXang);
		this.setHinhAnh2(hinhAnh2);
		this.setHinhAnh3(hinhAnh3);
		this.setHinhAnh4(hinhAnh4);
		this.setSoLuongCon(soLuongCon);
		this.setSoLuongDaBan(soLuongDaBan);
		
		DAO dao = new DAO();
		String kq= dao.chuyenDoiSo(String.format("%.0f",this.giaTien));
		this.strGiaTien = kq;
	}
	
	public XeMay(int maXe, int soLuongDaBan) 
	{		
		this.setMaXe(maXe);
		this.setSoLuongDaBan(soLuongDaBan);
		
//		DAO dao = new DAO();
//		String kq= dao.chuyenDoiSo(String.format("%.0f",this.giaTien));
//		this.strGiaTien = kq;
	}


	public int getMaXe() {
		return maXe;
	}


	public void setMaXe(int maXe) {
		this.maXe = maXe;
	}


	public String getTenXe() {
		return tenXe;
	}


	public void setTenXe(String tenXe) {
		this.tenXe = tenXe;
	}


	public String getHinhAnh1() {
		return hinhAnh1;
	}


	public void setHinhAnh1(String hinhAnh1) {
		this.hinhAnh1 = hinhAnh1;
	}


	public Double getGiaTien() {
		return giaTien;
	}


	public void setGiaTien(Double giaTien) {
		this.giaTien = giaTien;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getGioiThieu() {
		return gioiThieu;
	}


	public void setGioiThieu(String gioiThieu) {
		this.gioiThieu = gioiThieu;
	}


	public String getKhoiLuong() {
		return khoiLuong;
	}


	public void setKhoiLuong(String khoiLuong) {
		this.khoiLuong = khoiLuong;
	}


	public String getDaiRongCao() {
		return daiRongCao;
	}


	public void setDaiRongCao(String daiRongCao) {
		this.daiRongCao = daiRongCao;
	}


	public String getDungTichXiLanh() {
		return dungTichXiLanh;
	}


	public void setDungTichXiLanh(String dungTichXiLanh) {
		this.dungTichXiLanh = dungTichXiLanh;
	}


	public String getTiSoNen() {
		return tiSoNen;
	}


	public void setTiSoNen(String tiSoNen) {
		this.tiSoNen = tiSoNen;
	}


	public String getDungTichBinhXang() {
		return dungTichBinhXang;
	}


	public void setDungTichBinhXang(String dungTichBinhXang) {
		this.dungTichBinhXang = dungTichBinhXang;
	}


	public String getHinhAnh2() {
		return hinhAnh2;
	}


	public void setHinhAnh2(String hinhAnh2) {
		this.hinhAnh2 = hinhAnh2;
	}


	public String getHinhAnh3() {
		return hinhAnh3;
	}


	public void setHinhAnh3(String hinhAnh3) {
		this.hinhAnh3 = hinhAnh3;
	}


	public String getHinhAnh4() {
		return hinhAnh4;
	}


	public void setHinhAnh4(String hinhAnh4) {
		this.hinhAnh4 = hinhAnh4;
	}


	public int getSoLuongCon() {
		return soLuongCon;
	}


	public void setSoLuongCon(int soLuongCon) {
		this.soLuongCon = soLuongCon;
	}

	public int getSoLuongDaBan() {
		return soLuongDaBan;
	}

	public void setSoLuongDaBan(int soLuongDaBan) {
		this.soLuongDaBan = soLuongDaBan;
	}

	public String getStrGiaTien() {
		return strGiaTien;
	}

	public void setStrGiaTien(String strGiaTien) {
		this.strGiaTien = strGiaTien;
	}
}
