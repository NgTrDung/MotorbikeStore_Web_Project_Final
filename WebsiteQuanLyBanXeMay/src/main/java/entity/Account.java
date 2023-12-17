package entity;

import dao.DAO;

public class Account {
	private int maAccount;
    private String username;
    private String password;
    private int isAdmin;
    private String hoTen;
    private String cCCD;
    private String email;
    private Double tongChiTieu;
    
    private String strTongChiTieu;

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
    public int getMaAccount() {
        return maAccount;
    }

    public void setId(int maAccount) {
        this.maAccount = maAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public Account(int maAccount, String user, String pass, int isAdmin, String email, 
    		String hoTen, String cCCD, Double tongChiTieu) {
		
		this.maAccount = maAccount;
		this.username = user;
		this.password = pass;
		this.isAdmin = isAdmin;
		this.email = email;
		this.setHoTen(hoTen);
		this.setcCCD(cCCD);
		this.setTongChiTieu(tongChiTieu);
		
		DAO dao = new DAO();
		String kq= dao.chuyenDoiSo(String.format("%.0f",this.tongChiTieu));
		this.strTongChiTieu = kq;
	}
    
    public Account(int maAccount, Double tongChiTieu) {
		
		this.maAccount = maAccount;
		this.setTongChiTieu(tongChiTieu);
		
		DAO dao = new DAO();
		String kq= dao.chuyenDoiSo(String.format("%.0f",this.tongChiTieu));
		this.strTongChiTieu = kq;
	}

	public Account() {
		DAO dao = new DAO();
		String kq= dao.chuyenDoiSo(String.format("%.0f",this.tongChiTieu));
		this.strTongChiTieu = kq;
	}

	@Override
	public String toString() {
		return "Account [id=" + maAccount + ", user=" + username + ", pass=" + password + ", isAdmin=" + isAdmin
				+ ", email=" + email + "]";
	}

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getcCCD() {
		return cCCD;
	}

	public void setcCCD(String cCCD) {
		this.cCCD = cCCD;
	}

	public Double getTongChiTieu() {
		return tongChiTieu;
	}

	public void setTongChiTieu(Double tongChiTieu) {
		this.tongChiTieu = tongChiTieu;
	}

	public String getStrTongChiTieu() {
		return strTongChiTieu;
	}

	public void setStrTongChiTieu(String strTongChiTieu) {
		this.strTongChiTieu = strTongChiTieu;
	}
	
	
}
