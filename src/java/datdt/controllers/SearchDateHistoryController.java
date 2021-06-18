/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.controllers;

import datdt.daos.OderDAO;
import datdt.daos.OrderDetailDAO;
import datdt.dtos.OrderDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MSI-PC
 */
@WebServlet(name = "SearchDateHistoryController", urlPatterns = {"/SearchDateHistoryController"})
public class SearchDateHistoryController extends HttpServlet {

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
        HttpSession session= request.getSession();
              String from = request.getParameter("txtRent");
        String to = request.getParameter("txtPay");
        try
        {
            String username = (String) session.getAttribute("username");
            OderDAO dao = new OderDAO();
            OrderDetailDAO ODdao = new OrderDetailDAO();
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(from);
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(to);
            List<OrderDTO> listOrder = dao.getAllOrderByUsernameAndDate(username, date1, date2);
            for(int i = 0; i < listOrder.size(); i++)
            {
                listOrder.get(i).setLs(ODdao.getAllOrderDetailByID(listOrder.get(i).getOderID()));
            }
            request.setAttribute("listSearchOrder", listOrder);
            session.setAttribute("from", from);
            session.setAttribute("to", to);
        }
        catch (Exception e) 
        {
            log("Error at SearchDateHistory" + e.getMessage());
        }
        finally
        {
            request.getRequestDispatcher("history.jsp").forward(request, response);
        }
    
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
