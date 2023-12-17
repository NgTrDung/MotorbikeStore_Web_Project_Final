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

import com.microsoft.sqlserver.jdbc.SQLServerException;


@WebServlet(name = "AddControl", urlPatterns = {"/add"})
public class AddControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String pname = request.getParameter("tenXe");
        String pimage = request.getParameter("hinhAnh1");
        
        String pimage2 = request.getParameter("hinhAnh2");
        String pimage3 = request.getParameter("hinhAnh3");
        String pimage4 = request.getParameter("hinhAnh4");
        String pkhoiLuong = request.getParameter("khoiLuong");
        String pdaiRongCao = request.getParameter("daiRongCao");
        String pdungTichXiLanh = request.getParameter("dungTichXiLanh");
        String ptiSoNen = request.getParameter("tiSoNen");
        String pdungTichBinhXang = request.getParameter("dungTichBinhXang");
        String psoLuongCon = request.getParameter("soLuongCon");
        String psoLuongDaBan = request.getParameter("soLuongDaBan");
        
        String pgiaTien = request.getParameter("giaTien");
        String ptitle = request.getParameter("title");
        String pgioiThieu = request.getParameter("gioiThieu");
        String pdanhMuc = request.getParameter("danhMuc");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        //int sid = a.getId();
        
        double dgiaTien = Double.parseDouble(pgiaTien);
        int isoLuongCon = Integer.parseInt(request.getParameter("soLuongCon"));
        int isoLuongDaBan = Integer.parseInt(request.getParameter("soLuongDaBan"));
        
        DAO dao = new DAO();
        XeMay xeMay = dao.getXeMayBytitle(ptitle);
        if(xeMay!=null)
        {
        	request.setAttribute("error", "Giá trị của cột Title của Xe bị trùng lặp với "
        			+ "một Xe khác. Hãy kiểm tra lại trước khi nhập!");
        }
        else
        {
        	if(dgiaTien > 0 && isoLuongCon >= 1 && isoLuongDaBan == 0)
            {
    			dao.insertXeMay(pname, pimage, pgiaTien, ptitle, pgioiThieu, pdanhMuc, 
    					pkhoiLuong, pdaiRongCao, pdungTichXiLanh, ptiSoNen, pdungTichBinhXang, pimage2, pimage3, pimage4, 
    					psoLuongCon, psoLuongDaBan);
    			request.setAttribute("mess", "Thực Hiện Thêm Xe Mới Thành Công!");
            }
            else
            {
            	request.setAttribute("error", "Không Thể Thêm Xe Mới, "
            			+ "Hãy Kiểm Tra Lại Quá Trình Nhập Thông Tin Một Cách Cẩn Thận!");
            }
        }
        
        
        request.getRequestDispatcher("manager").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
