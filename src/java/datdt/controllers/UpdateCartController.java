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
@WebServlet(name = "UpdateCartController", urlPatterns = {"/UpdateCartController"})
public class UpdateCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       String name = request.getParameter("txtcarName");
        try
        {
            CarDAO dao = new CarDAO();
            HttpSession session = request.getSession();
            CartObj shCart = (CartObj) session.getAttribute("shCart");
            List<CarDTO> list = dao.AddMoreCarToCart(name);
            
            if(list != null)
            {
                for(CarDTO dtos : shCart.getCart().values())
                {
                    for(int i = 0; i < list.size(); i++)
                    {
                        if(dtos.getName().equals(name))
                        {
                            if(dtos.getCarID().equals(list.get(i).getCarID()))
                            {
                                list.remove(i);
                            }
                        }
                    }
                }
                if(list.size() != 0)
                {
                    shCart.addToCart(list.get(0));
                    float Total = 0;
                    for (CarDTO dto : shCart.getCart().values())
                    {
                        Total = Total + dto.getPrice();
                    }
                    session.setAttribute("shCart", shCart);
                    session.setAttribute("total", Total);
                    session.removeAttribute("notify");
                }
                else
                {
                    session.setAttribute("notify", "There are no available car for " + name +"!!!");
                }
            }
            else
            {
                session.setAttribute("notify", "There are no available car for " + name +"!!!");
            }
   
        }
        catch (Exception e) 
        {
            log("ERROR at updateToCart" + e.getMessage());
        } 
        finally 
        {
//            request.getRequestDispatcher("viewcart.jsp").forward(request, response);
                response.sendRedirect("viewcart.jsp");
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
