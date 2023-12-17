/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Account;
import entity.XeMay;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "EditControl", urlPatterns = {"/edit"})
public class EditControl extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String pmaXe = request.getParameter("maXe");
        String ptenXe = request.getParameter("tenXe");
        String phinhAnh1 = request.getParameter("hinhAnh1");
        
        String phinhAnh2 = request.getParameter("hinhAnh2");
        String phinhAnh3 = request.getParameter("hinhAnh3");
        String phinhAnh4 = request.getParameter("hinhAnh4");
        
        String pkhoiLuong = request.getParameter("khoiLuong");
        String pdaixRongxCao = request.getParameter("daixRongxCao");
        String pdungTichXiLanh = request.getParameter("dungTichXiLanh");
        String ptiSoNen = request.getParameter("tiSoNen");
        String pdungTichBinhXang = request.getParameter("dungTichBinhXang");
        String psoLuongCon = request.getParameter("soLuongCon");
        String psoLuongDaBan = request.getParameter("soLuongDaBan");
        
        String pgiaTien = request.getParameter("giaTien");
        String ptitle = request.getParameter("title");
        String pgioiThieu = request.getParameter("gioiThieu");
        String pdanhMuc = request.getParameter("danhMuc");
        
        double dgiaTien = Double.parseDouble(pgiaTien);
        int isoLuongCon = Integer.parseInt(request.getParameter("soLuongCon"));
        int isoLuongDaBan = Integer.parseInt(request.getParameter("soLuongDaBan"));
        
        XeMay xeMay = new XeMay();
        DAO dao = new DAO();
        xeMay = dao.getXeMayBytitle(ptitle);
        
        if(xeMay !=null)
        {
        	request.setAttribute("error", "Giá trị của cột Title của Xe bị trùng lặp với "
        			+ "một Xe khác. Hãy kiểm tra lại trước khi nhập!");
        }
        else
        {
        	if(dgiaTien >0 && isoLuongCon >= 100 && isoLuongDaBan >= 0)
            {
    			dao.editXeMay(ptenXe, phinhAnh1, pgiaTien, ptitle, pgioiThieu, pdanhMuc, pkhoiLuong, 
    					pdaixRongxCao, pdungTichXiLanh, ptiSoNen, pdungTichBinhXang, phinhAnh2, 
    					phinhAnh3, phinhAnh4, psoLuongCon, psoLuongDaBan, pmaXe);
    			request.setAttribute("mess", "Thực Hiện Chỉnh Sửa Thông Tin Xe Thành Công!");
            }
            else
            {
            	request.setAttribute("error", "Không Thể Chỉnh Sửa Do Phát Sinh Lỗi Trong Quá Trình Nhập Thông Tin,"
            			+ " Hãy Thực Hiện Chỉnh Sửa Lại Cẩn Thận!");
            }
        }
        
        
        
       request.getRequestDispatcher("manager").forward(request, response);
//        response.sendRedirect("manager");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
