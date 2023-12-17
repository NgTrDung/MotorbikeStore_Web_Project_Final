/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.GioHang;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "DeleteAccountControl", urlPatterns = {"/deleteAccount"})
public class DeleteAccountControl extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        System.out.println("id: "+id);
        DAO dao = new DAO();
        
        int iid = Integer.parseInt(id);
        
        List <GioHang> gioHang = dao.getGioHangBymaAccount(iid);
        System.out.println(gioHang.toString());
        
        if(gioHang.size() != 0)
        {
        	request.setAttribute("error", "Không Thể Xóa Tài Khoản Này Vì Đang Có Tồn Tại Giỏ hàng!");
        }
        else
        {
        	dao.deleteCartByAccountID(id);
            //dao.deleteProductBySellID(id);
            dao.deleteReviewByAccountID(id);
            dao.deleteInvoiceByAccountId(id);
//            dao.deleteTongChiTieuBanHangByUserID(id);
            dao.deleteAccount(id);
            
            request.setAttribute("mess", "Thực Hiện Xóa Tài Khoản Thành Công!");
        }
        
        request.getRequestDispatcher("managerAccount").forward(request, response);
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
    }
}
