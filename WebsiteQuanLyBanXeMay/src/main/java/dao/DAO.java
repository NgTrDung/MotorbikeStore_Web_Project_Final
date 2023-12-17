/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.GioHang;
import entity.FeedBack;
//import entity.SoLuongXeDaBan;
//import entity.TongChiTieuMuaHang;
//import entity.Account;
import entity.DanhMuc;
import entity.HoaDon;
import entity.XeMay;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<XeMay> getAllXeMay() {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> getTop10SanPhamBanChay() {
        List<XeMay> list = new ArrayList<>();
        String query = "select top(10) *\r\n"
        		+ "from XeMay\r\n"
        		+ "order by soLuongDaBan desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getInt(17)
                  ));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    public List<DanhMuc> getAllCategory() {
        List<DanhMuc> list = new ArrayList<>();
        String query = "select * from DanhMuc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DanhMuc(rs.getInt(1),rs.getString(2)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    public List<HoaDon> getAllInvoice() {
        List<HoaDon> list = new ArrayList<>();
        String query = "select * from HoaDon";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new HoaDon(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getDate(4)
                       ));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public double totalMoneyDay(int day) {
        String query = "select \r\n"
        		+ "	SUM(tongTien) \r\n"
        		+ "from HoaDon\r\n"
        		+ "where DATEPART(dw,[ngayThanhToan]) = ?\r\n"
        		+ "Group by ngayThanhToan ";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, day);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getDouble(1);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public double totalMoneyMonth(int month) {
        String query = "select SUM(tongTien) from HoaDon\r\n"
        		+ "where MONTH(ngayThanhToan)=? and YEAR(ngayThanhToan)=YEAR(GETDATE())\r\n"
        		+ "Group by MONTH(ngayThanhToan)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, month);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getDouble(1);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public int countAllXeMay() {
        String query = "select count(*) from XeMay";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getInt(1);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public double sumAllInvoice() {
        String query = "select SUM(tongTien) from HoaDon \r\n"
        		+ " where DATEDIFF(day, dbo.func_ngayDauTienTrongTuan(GETDATE()), \r\n"
        		+ " ngayThanhToan) >= 0 and \r\n"
        		+ " DATEDIFF(day, dbo.func_ngayCuoiCungTrongTuan(GETDATE()), \r\n"
        		+ " ngayThanhToan) <= 0";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getDouble(1);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public int countAllReview() {
        String query = "select count(*) from FeedBack";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getInt(1);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public int getmaDanhMucBymaXe(String maXe) {
        String query = "select [maDanhMuc] from XeMay\r\n"
        		+ "where [maXe] =?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, maXe);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getInt(1);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Account";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                		rs.getString(5),
                		rs.getString(6),
                		rs.getString(7),
                		rs.getDouble(8)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
   
    public List<Account> getTop10KhachHang() {
        List<Account> list = new ArrayList<>();
        String query = "select top(10) *\r\n"
        		+ "from Account\r\n"
        		+ "order by tongChiTieu desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getDouble(8)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }

    public List<XeMay> getTop3() {
        List<XeMay> list = new ArrayList<>();
        String query = "select top 3 * from XeMay";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }

    public List<XeMay> getNext3XeMay(int soLuong) {
        List<XeMay> list = new ArrayList<>();
        String query = "SELECT *\n"
                + "  FROM XeMay\n"
                + " ORDER BY maXe\n"
                + "OFFSET ? ROWS\n"
                + " FETCH NEXT 3 ROWS ONLY";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, soLuong);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> getNext4VisionProduct(int soLuong) {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\r\n"
        		+ "where maDanhMuc=2\r\n"
        		+ "order by maXe desc\r\n"
        		+ "offset ? rows\r\n"
        		+ "fetch next 4 rows only";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, soLuong);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> getAllProduct() {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> getNext4AirBladeProduct(int soLuong) {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\r\n"
        		+ "where maDanhMuc=1\r\n"
        		+ "order by maXe desc\r\n"
        		+ "offset ? rows\r\n"
        		+ "fetch next 4 rows only";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, soLuong);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> getXeMayBymaDanhMuc(String maDanhMuc) {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\n"
                + "where maDanhMuc = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, maDanhMuc);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }

    public List<XeMay> getProductBySellIDAndIndex(int id, int indexPage) {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay \r\n"
        		+ "where maXe = ?\r\n"
        		+ "order by [maXe]\r\n"
        		+ "offset ? rows\r\n"
        		+ "fetch next 5 rows only";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, (indexPage-1)*5);
            rs = ps.executeQuery();
            while (rs.next()) {
            	 list.add(new XeMay(rs.getInt(1),
                         rs.getString(2),
                         rs.getString(3),
                         rs.getDouble(4),
                         rs.getString(5),
                         rs.getString(6),
                         rs.getString(8),
                         rs.getString(9),
                         rs.getString(10),
                         rs.getString(11),
                         rs.getString(12),
                         rs.getString(13),
                         rs.getString(14),
                         rs.getString(15),
                         rs.getInt(16),
                         rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> getXeMayByIndex(int indexPage) {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay \r\n"
        		+ "order by [maXe]\r\n"
        		+ "offset ? rows\r\n"
        		+ "fetch next 9 rows only";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, (indexPage-1)*9);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }

    public List<XeMay> searchBytenXe(String txtSearch) {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\n"
                + "where [tenXe] like ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<HoaDon> searchByNgayXuat(String ngayXuat) {
        List<HoaDon> list = new ArrayList<>();
        String query = "select * from HoaDon\r\n"
        		+ "where [ngayThanhToan] ='"+ngayXuat+"'";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new HoaDon(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getDate(4)
                       ));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> searchgiaTienUnder14() {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\r\n"
        		+ "where [giaTien] < 10000000";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> searchgiaTien14To20() {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\r\n"
        		+ "where [giaTien] >= 14000000 and [giaTien]<= 20000000";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }

    public List<XeMay> searchBygiaTienMinToMax(String giaTienMin,String giaTienMax) {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\r\n"
        		+ "where [giaTien] >= ? and [giaTien]<=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, giaTienMin);
            ps.setString(2, giaTienMax);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> searchPriceAbove20() {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\r\n"
        		+ "where [giaTien] > 20000000";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> getRelatedXeMay(int maDanhMucXeMayDetail) {
        List<XeMay> list = new ArrayList<>();
        String query = "select top 4 * from XeMay\r\n"
        		+ "where [maDanhMuc] =?\r\n"
        		+ "order by maXe desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maDanhMucXeMayDetail);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<FeedBack> getAllFeedBackBymaXe(String maXe) {
        List<FeedBack> list = new ArrayList<>();
        String query = "select * from FeedBack\r\n"
        		+ "where [maXe] =?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, maXe);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new FeedBack(rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDate(5)
                       ));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }

    public XeMay getXeMayBymaXe(String maXe) {
        String query = "select *from XeMay\n"
                + "where maXe = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, maXe);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return null;
    }
    
    public List<GioHang> getGioHangBymaAccount(int maAccount) {
    	 List<GioHang> list = new ArrayList<>();
        String query = "select * from GioHang where maAccount = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new GioHang(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }

    public GioHang checkGioHangExist(int maAccount,int maXe) {

       String query = "select * from GioHang\r\n"
       		+ "where [maAccount] = ? and [maXe] = ?";
       try {
           conn = new DBContext().getConnection();//mo ket noi voi sql
           ps = conn.prepareStatement(query);
           ps.setInt(1, maAccount);
           ps.setInt(2, maXe);
           rs = ps.executeQuery();
           while (rs.next()) {
               return new GioHang(rs.getInt(1),
                       rs.getInt(2),
                       rs.getInt(3),
                       rs.getInt(4));
           }
       } catch (Exception e) {
    	   e.printStackTrace();
    	    System.out.println("Có lỗi");
       }
      return null;
   }
    
    public int checkAccountAdmin(int userID) {

        String query = "select isAdmin from Account where [maAccount]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);

            rs = ps.executeQuery();  
            while (rs.next()) {
            	 return rs.getInt(1);
             }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public Account checkTongChiTieuMuaHangExist(int maAccount) {

        String query = "select * from Account where [maAccount]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
           
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getDouble(8));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
       return null;
    }
    
    public XeMay checkSoLuongXeDaBanExist(int maXe) {

        String query = "select * from XeMay where maXe = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maXe);
           
            rs = ps.executeQuery();
            while (rs.next()) {
                return new XeMay(rs.getInt(1),
                        rs.getInt(17)
                       );
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
       return null;
    }
    
    
    
    public List<DanhMuc> getAllDanhMuc() {
        List<DanhMuc> list = new ArrayList<>();
        String query = "select * from DanhMuc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DanhMuc(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    

    public XeMay getLast() {
        String query = "select top 1 * from XeMay\n"
                + "order by maXe desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return null;
    }
    
    public List<XeMay> get8Last() {
    	List<XeMay> list = new ArrayList<>();
        String query = "select top 8 * from XeMay order by maXe desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	list.add(new XeMay(rs.getInt(1),
            			rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> get4VisionLast() {
    	List<XeMay> list = new ArrayList<>();
        String query = "select top 4 * from XeMay\r\n"
        		+ "where maDanhMuc = 2\r\n"
        		+ "order by maXe desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	list.add(new XeMay(rs.getInt(1),
            			rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> get4AirBladeLast() {
    	List<XeMay> list = new ArrayList<>();
        String query = "select top 4 * from XeMay\r\n"
        		+ "where maDanhMuc = 1\r\n"
        		+ "order by maXe desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	list.add(new XeMay(rs.getInt(1),
            			rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }

    public Account login(String user, String pass) {
        String query = "select * from Account\n"
                + "where [username] = ?\n"
                + "and password = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                		rs.getString(5),
                		rs.getString(6),
                		rs.getString(7),
                		rs.getDouble(8));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return null;
    }

    public Account checkAccountExist(String username) {
        String query = "select * from Account\n"
                + "where [username] = ?\n";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                		rs.getString(5),
                		rs.getString(6),
                		rs.getString(7),
                		rs.getDouble(8));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return null;
    }
    
    public Account checkAccountExistByusernameAndemail(String username, String email) {
        String query = "select * from Account where [username]=? and [email]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                		rs.getString(5),
                		rs.getString(6),
                		rs.getString(7),
                		rs.getDouble(8));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return null;
    }
    
    public FeedBack getNewFeedBack(int maAccount, int maXe) {
        String query = "select top 1 * from FeedBack\r\n"
        		+ "where maAccount = ? and maXe = ?\r\n"
        		+ "order by maFeedBack desc";
        FeedBack fb = new FeedBack();
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
            ps.setInt(2, maXe);
            rs = ps.executeQuery();
            while (rs.next()) {
                return fb = new FeedBack(rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDate(5));
            }
        } catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("co loi");
        }
        return null;
    }

    public void singup(String user, String pass, String email, String hoTen, String cCCD) {
        String query = "insert into Account \n"
                + "values(?,?,0,?,?,?,0)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, email);
            ps.setString(4, hoTen);
            ps.setString(5, cCCD);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    public void deleteCartByAccountID(String id) {
        String query = "delete from GioHang\n"
                + "where [maAccount] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    public void deleteInvoiceByAccountId(String id) {
        String query = "delete from HoaDon\n"
                + "where [maAccount] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteTongChiTieuMuaHangByUserID(String id) {
        String query = "delete from Account\n"
                + "where [maAccount] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    
    public String deleteProduct(String pid) {
    	String del="0";
        String query = "delete from XeMay\n"
                + "where [maXe] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();
            del="1";
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return del;
    }
    
    public void deleteProductBySellID(String id) {
        String query = "delete from Product\n"
                + "where [sell_ID] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteGioHangBymaAccount(int maAccount) {
        String query = "delete from GioHang \r\n"
        		+ "where [maAccount]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteCartByProductID(String productID) {
        String query = "delete from GioHang where [maGioHang]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteSoLuongDaBanByProductID(String productID) {
        String query = "delete from XeMay where [maXe]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteReviewByProductID(String productID) {
        String query = "delete from FeedBack where [maXe] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteReviewByAccountID(String id) {
        String query = "delete from FeedBack where [maAccount] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteAccount(String id) {
        String query = "delete from Account where maAccount= ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteSupplier(String idSupplier) {
        String query = "delete from Supplier\r\n"
        		+ "where idSupplier=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, idSupplier);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteGioHang(int maXe) {
        String query = "delete from GioHang where maXe = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maXe);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }

    public void insertXeMay(String tenXe, String hinhAnh1, String giaTien,
            String title, String gioiThieu, String maDanhMuc, String khoiLuong, 
            String daiRongCao, String dungTichXiLanh, String tiSoNen, 
            String dungTichBinhXang, String hinhAnh2, String hinhAnh3, String hinhAnh4, 
            String soLuongCon, String soLuongDaBan) {
        String query = "insert XeMay([tenXe],[hinhAnh1],[giaTien],[title],\r\n"
        		+ "[gioiThieu],[maDanhMuc],[khoiLuong],[daiRongCao],[dungTichXiLanh],\r\n"
        		+ "[tiSoNen],[dungTichBinhXang],[hinhAnh2],[hinhAnh3],[hinhAnh4],"
        		+ "[soLuongCon],[soLuongDaBan])\r\n"
        		+ "values(N'"+tenXe+"','"+hinhAnh1+"','"+giaTien+"',N'"+title+"',N'"+
        		gioiThieu+"','"+maDanhMuc+"',N'"+khoiLuong+"',N'"+daiRongCao
        		+"',N'"+dungTichXiLanh+"',N'"+tiSoNen+"',N'"+dungTichBinhXang+"','"+
        		hinhAnh2+"','"+hinhAnh3+"','"+hinhAnh4+"','"+soLuongCon+"','"+
        		soLuongDaBan+"')";
        try {
        	
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
           
        } catch (Exception e) {
        	
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void insertAccount(String user, String pass, String isAdmin, String email, 
    		String hoTen, String cCCD) {
        String query = "insert Account([username], password, isAdmin, email, hoTen, cCCD, "
        		+ "tongChiTieu)\r\n"
        		+ "values(?,?,?,?,?,?,0)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, isAdmin);
            ps.setString(4, email);
            ps.setString(5, hoTen);
            ps.setString(6, cCCD);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
 
    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
  
    public void insertFeedBack(int maAccount, int maXe, String noiDung) {
        String query = "insert FeedBack(maAccount, maXe, noiDung, ngayDanhGia)\r\n"
        		+ "values(?,?,?,?)";

        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
            ps.setInt(2, maXe);
            ps.setString(3, noiDung);
            ps.setDate(4,getCurrentDate());
            ps.executeUpdate();
            System.out.println("hello");
            
        } catch (Exception e) {	
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void insertHoaDon(int maAccount, double tongTien) {
        String query = "insert HoaDon(maAccount,tongTien,ngayThanhToan)\r\n"
        		+ "values(?,?,?)";

        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
            ps.setDouble(2, tongTien);
            ps.setDate(3,getCurrentDate());
            ps.executeUpdate();
           
        } catch (Exception e) {	
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void insertGioHang(int maAccount, int maXe, int soLuong) {
        String query = "insert GioHang(maAccount, maXe, soLuong)\r\n"
        		+ "values(?,?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
            ps.setInt(2, maXe);
            ps.setInt(3, soLuong);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }

    public void editXeMay(String ptenXe, String phinhAnh1, String pgiaTien, String ptitle, 
    		String pgioiThieu, String pdanhMuc, 
    		String pkhoiLuong, String pdaixRongxCao, 
    		String pdungTichXiLanh, String ptiSoNen, String pdungTichBinhXang, 
    		String phinhAnh2, String phinhAnh3, String phinhAnh4, String psoLuongCon, 
    		String psoLuongDaBan, String pmaXe) {
        String query = "update XeMay\r\n"
        		+ "set [tenXe] = ?,\r\n"
        		+ "[hinhAnh1] = ?,\r\n"
        		+ "giaTien = ?,\r\n"
        		+ "title = ?,\r\n"
        		+ "[gioiThieu] = ?,\r\n"
        		+ "maDanhMuc = ?,\r\n"
        		+ "khoiLuong = ?,\r\n"
        		+ "daiRongCao = ?,\r\n"
        		+ "dungTichXiLanh = ?,\r\n"
        		+ "tiSoNen = ?,\r\n"
        		+ "dungTichBinhXang = ?,\r\n"
        		+ "hinhAnh2 =?,\r\n"
        		+ "hinhAnh3 =?,\r\n"
        		+ "hinhAnh4 =?,\r\n"
        		+ "soLuongCon =?,\r\n"
        		+ "soLuongDaBan =?\r\n"
        		+ "where [maXe] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, ptenXe);
            ps.setString(2, phinhAnh1);
            ps.setString(3, pgiaTien);
            ps.setString(4, ptitle);
            ps.setString(5, pgioiThieu);
            ps.setString(6, pdanhMuc);
            ps.setString(7, pkhoiLuong);
            ps.setString(8, pdaixRongxCao);
            ps.setString(9, pdungTichXiLanh);
            ps.setString(10, ptiSoNen);
            ps.setString(11, pdungTichBinhXang);
            ps.setString(12, phinhAnh2);
            ps.setString(13, phinhAnh3);
            ps.setString(14, phinhAnh4);
            ps.setString(15, psoLuongCon);
            ps.setString(16, psoLuongDaBan);
            ps.setString(17, pmaXe);
            ps.executeUpdate();
           
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    
    public void editProfile(String username, String password, String email, 
    		String hoTen, String cCCD, int maAccount) {
        String query = "update Account set [username]=?,\r\n"
        		+ "[password]=?,\r\n"
        		+ "[email]=?,\r\n"
        		+ "[hoTen]=?,\r\n"
        		+ "[cCCD]=?\r\n"
        		+ "where [maAccount] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, hoTen);
            ps.setString(5, cCCD);
            ps.setInt(6, maAccount);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void editTongChiTieu(int maAccount, double totalMoneyVAT) {
        String query = "exec dbo.proc_CapNhatTongChiTieu ?,?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
            ps.setDouble(2, totalMoneyVAT);
          
            ps.executeUpdate();
            
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void editSoLuongDaBan(int productID, int soLuongBanThem) {
        String query = "exec dbo.proc_CapNhatSoLuongXeDaBan ?,?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ps.setInt(2, soLuongBanThem);
          
            ps.executeUpdate();
            
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void editsoLuongGioHang(int maAccount, int maXe, int soLuong) {
        String query = "update GioHang set [soLuong]=?\r\n"
        		+ "where [maAccount]=? and [maXe]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, soLuong);
            ps.setInt(2, maAccount);
            ps.setInt(3, maXe);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public XeMay getXeMayBytitle(String maXe) {
        String query = "select *from XeMay\n"
                + "where title = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, maXe);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16),
                        rs.getInt(17));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return null;
    }
    
    public String chuyenDoiSo(String str)
    {
    	// Chuyển chuỗi thành số
    	long soStr = Long.parseLong(str);
    	
    	// Định dạng số với dấu phân cách hàng nghìn
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String ketQua = numberFormat.format(soStr);
        return ketQua;
    }
    
    public Double Get_Money(int index,List<List<Double>> dDoanhThuTuan) {
    	try {
    		int n = dDoanhThuTuan.size();
    		for(int i=0;i<n;i++) {
    			if((dDoanhThuTuan.get(i).get(0).intValue())==index) {
    				return dDoanhThuTuan.get(i).get(1);
    			}
    		}
    		return 0.0;
    	}
    	catch(Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    	return 0.0;
    	
    }
    
    
    public List<List<Double>> totalMoneyDay_Current() {
        String query = "SELECT datepart(weekday,ngayThanhToan),sum(tongTien) from HoaDon "
        		+ "where DATEDIFF(day, dbo.func_ngayDauTienTrongTuan(GETDATE()), "
        		+ "ngayThanhToan) >= 0 and "
        		+ "DATEDIFF(day, dbo.func_ngayCuoiCungTrongTuan(GETDATE()), "
        		+ "ngayThanhToan) <= 0 group by ngayThanhToan";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            
            List<List<Double>> dDoanhThuTuan = new ArrayList<List<Double>>();
            
            rs = ps.executeQuery();
            while (rs.next()) 
            {
            	List<Double> thu = new ArrayList<Double>();
            	thu.add(rs.getDouble(1));
            	thu.add(rs.getDouble(2));
            	dDoanhThuTuan.add(thu);//rs.getDouble(1));
            }
            return dDoanhThuTuan;
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return null;
    }

   public static void main(String[] args) {
        DAO dao = new DAO();
   }

}
