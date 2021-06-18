/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.controllers;

import datdt.daos.CarDAO;
import datdt.daos.CategoryDAO;
import datdt.dtos.CarDTO;
import datdt.dtos.CategoryDTO;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "SearchDateController", urlPatterns = {"/SearchDateController"})
public class SearchDateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String index = request.getParameter("PageIndex");
        String RentDate = request.getParameter("txtRentDate");
//        System.out.println(RentDate);
        //2021-03-06
        String PayDate = request.getParameter("txtPayDate");
        HttpSession session = request.getSession();
        String url = null;
        String isSearch = request.getParameter("Search");
        String Pname = request.getParameter("txtName");
        String Cate = request.getParameter("txtCategory");
        String Quan = request.getParameter("txtQuantity");

        try {
            CarDAO dao = new CarDAO();

            url = "PageController";

            if (RentDate.equals("")) {
                request.setAttribute("Rent", "RentDate not empty");

            }
            if (PayDate.equals("")) {
                request.setAttribute("Pay", "PayDate not empty");
            }

            String yearR = RentDate.substring(0, 4);
            String yearP = PayDate.substring(0, 4);
            int f = Integer.parseInt(yearR);
            int e = Integer.parseInt(yearP);
            String dayR = RentDate.substring(8, 10);
            String dayP = PayDate.substring(8, 10);
            String monthR = RentDate.substring(5, 7);
            String payR = PayDate.substring(5, 7);
            int a = Integer.parseInt(monthR);
            int b = Integer.parseInt(payR);
            int c = Integer.parseInt(dayR);
            System.out.println(c);
            int d = Integer.parseInt(dayP);
            System.out.println(d);
            if (f > e) {
                request.setAttribute("Year", "Invalid Year");
//                } 3-4 7-3
                url = "PageController";
            } else if (a > b && f >= e) {
                request.setAttribute("Month", "Invalid Month");
                url = "PageController";
            } else if (c > d && a >= b && f >= e) {
                request.setAttribute("Day", "Invalid Day");
                url = "PageController";
            } else {
                CategoryDAO cate = new CategoryDAO();
                List<CategoryDTO> cateroryList = cate.getAllCategory();

               List<CarDTO> list = dao.getAllProduct();
                for(int i = 0; i < list.size(); i++)
            {
//            
                if(dao.checkAvailableByDate(list.get(i).getCarID(), RentDate, PayDate))
                {
                    list.remove(list.get(i));
                    i--;
                }
            }
                if(Pname.equals(""))
            {
                for(int i = 0; i < list.size(); i++)
                {
                    if(!list.get(i).getCategoryID().equals(Cate))
                    {
                        list.remove(list.get(i));
                        i--;
                    }
                }
            }
                 else
            {
                for(int i = 0; i < list.size(); i++)
                {
                    if(!list.get(i).getName().toLowerCase().contains(Pname.toLowerCase()))
                    {
                        list.remove(list.get(i));
                        i--;
                    }
                }
            }
                if (index == null) {
                    index = "1";
                }
                int pageSize = 10;
                int endPage = 0;
                int count = list.size();
                endPage = count / pageSize;
                if (count % pageSize != 0) {
                    endPage++;
                }
                List<CarDTO> lsssCar = new ArrayList<>();
                for (int i = Integer.parseInt(index) * pageSize - pageSize; lsssCar.size() < pageSize && i < list.size(); i++) {

                    lsssCar.add(list.get(i));
                }
                request.setAttribute("searchPro", lsssCar);
                request.setAttribute("end", endPage);
                session.setAttribute("name", Pname);
                session.setAttribute("category", Cate);
                request.setAttribute("Search", isSearch);
                request.setAttribute("PageIndex", index);
                request.setAttribute("searchPro", lsssCar);
                session.setAttribute("txtRentDate", RentDate);
                session.setAttribute("txtPayDate", PayDate);
                session.setAttribute("txtQuantity", Quan);
                request.setAttribute("categoryList", cateroryList);
                url = "home.jsp";
            }
        } catch (Exception e) {
            log("ERROR AT SearchController: " + e.getMessage());
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
