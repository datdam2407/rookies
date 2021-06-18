/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.controllers;

import datdt.daos.CarDAO;
import datdt.daos.CategoryDAO;
import datdt.dtos.CarDTO;
import datdt.dtos.CartObj;
import datdt.dtos.CategoryDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
@WebServlet(name = "PageController", urlPatterns = {"/PageController"})
public class PageController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String index = request.getParameter("PageIndex");
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("txtName");
        String quan = (String) session.getAttribute("txtQuantity");
       
        Date curret = new Date();
        java.sql.Date date1 = new java.sql.Date(curret.getTime());
        try {
            if (index == null) {
                index = "1";
            }
            int pageSize = 10;
            int endPage = 0;
            CategoryDAO cateDAO = new CategoryDAO();
            CarDAO dao = new CarDAO();
            int count = dao.count();
            endPage = count / pageSize;
            if (count % pageSize != 0) {
                endPage++;
            }
            List<CarDTO> carList = dao.getAllProduct(Integer.parseInt(index), pageSize);
//            for (int i = 0; i < carList.size(); i++) {
//                carList.get(i).setAvaliableCar(carList.get(i).getQuantity() - dao.getRentingQuantity(sdf.format(date), sdf.format(date), carList.get(i).getCarID()));
//            }
            List<CategoryDTO> cateroryList = cateDAO.getAllCategory();
            request.setAttribute("listPro", carList);
            request.setAttribute("end1", endPage);
            request.setAttribute("categoryList", cateroryList);
            request.setAttribute("PageIndex", index);
            session.setAttribute("producname", name);
            session.setAttribute("txtQuantity", quan);
            session.setAttribute("currentDate", date1);
            CartObj shoppingCart = (CartObj) session.getAttribute("shCart");

            if (shoppingCart != null) {
                float Total = 0;
                for (CarDTO dtos : shoppingCart.getCart().values()) {
                    Total = Total + dtos.getPrice();
                }
                session.setAttribute("total", Total);
            }
        } catch (Exception e) {
            log("Error at pageProductServlet" + e.getMessage());
        } finally {
            request.getRequestDispatcher("home.jsp").forward(request, response);
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
