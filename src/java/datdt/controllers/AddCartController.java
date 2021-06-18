/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.controllers;

import datdt.daos.CarDAO;
import datdt.dtos.CarDTO;
import datdt.dtos.CartObj;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AddCartController", urlPatterns = {"/AddCartController"})
public class AddCartController extends HttpServlet {

    private final String CART = "PageController";
//    private final String Sh = "SearchController";
    private final String Shd = "SearchDateController";
    private final String Login = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String index = request.getParameter("PageIndex");
        String rent = request.getParameter("txtRentDate");
        String pay = request.getParameter("txtPayDate");
        String Pname = request.getParameter("txtName");
        String Cate = request.getParameter("txtCategory");
        String Page = request.getParameter("Page");
//        String quan = (String) session.getAttribute("txtQuantity");
        String url = CART;
        try {
            if (username == null) {
                url = Login;
            } else if (Page == null) {
                url = CART;
            } else if (!rent.equals("")) {
                url = Shd;
            }
//            else if(Page != null){
//                url = Sh;
//            }
            if (username != null) {

                String productID = request.getParameter("txtCarID");
                CarDAO CarDAO = new CarDAO();
                CarDTO CarDTO = CarDAO.getCarByKey(productID);
//                CarDTO.setCartQuantity(1);
                float Total = 0;
                CartObj shoppingCart = (CartObj) session.getAttribute("shCart");

                if (shoppingCart == null) {
                    shoppingCart = new CartObj(username);
                }
                 for (CarDTO dto : shoppingCart.getCart().values())
                {
                    Total = Total + dto.getPrice();
                }
                shoppingCart.addToCart(CarDTO);
                session.setAttribute("shCart", shoppingCart);
                 session.setAttribute("total", Total);

            }
            
//            session.setAttribute("txtQuantity", quan);
            request.setAttribute("Page", Page);
            request.setAttribute("PageIndex", index);
            request.setAttribute("txtRentDate", rent);
            request.setAttribute("txtPayDate", pay);
            request.setAttribute("PageIndex", index);
            session.setAttribute("newtotal", null);
            request.setAttribute("txtPName", Pname);
            request.setAttribute("txtCategory", Cate);
        } catch (Exception e) {
            log("ERROR AT AddController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
