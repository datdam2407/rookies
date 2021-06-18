/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.controllers;

import datdt.daos.CarDAO;
import datdt.daos.OderDAO;
import datdt.daos.OrderDetailDAO;
import datdt.dtos.CarDTO;
import datdt.dtos.CartObj;
import datdt.dtos.DiscountDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "ConfirmCartController", urlPatterns = {"/ConfirmCartController"})
public class ConfirmCartController extends HttpServlet {

    private static final String VIEW = "viewcart.jsp";

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
        String cusName = request.getParameter("txtName");
        String phone = request.getParameter("txtPhone");
        String address = request.getParameter("txtAddress");
        String discount = request.getParameter("txtDicountID");
        String Rent = request.getParameter("txtRentDate");
        String Pay = request.getParameter("txtPayDate");
        String Code = request.getParameter("txtDicountCode");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        HttpSession session = request.getSession();
                String action = request.getParameter("btAction");

        String url = "";
        try {
            if (cusName.equals("")) {
                request.setAttribute("name", "Name's Recieve not empty");
                url = VIEW;
            }
            if (phone.equals("")) {
                request.setAttribute("phone", "Give me phone number to contact!!!");
                url = VIEW;
            }
            if (address.equals("")) {
                request.setAttribute("address", "Hmm how can i find your address");
                url = VIEW;
            }
            session.setAttribute("usercusname", cusName);
            session.setAttribute("userphone", phone);
            session.setAttribute("useraddress", address);
            if (Rent.equals("")) {
                request.setAttribute("Rent", "RentDate not empty");
                url = VIEW;
            }
            if (Pay.equals("")) {
                request.setAttribute("Pay", "PayDate not empty");
                url = VIEW;
            }//2021-03-20
            String yearR = Rent.substring(0, 4);
            String yearP = Pay.substring(0, 4);
            int f = Integer.parseInt(yearR);
            int e = Integer.parseInt(yearP);
            String dayR = Rent.substring(8, 10);
            String dayP = Pay.substring(8, 10);
            String monthR = Rent.substring(5, 7);
            String payR = Pay.substring(5, 7);
            int a = Integer.parseInt(monthR);
            int b = Integer.parseInt(payR);
            int c = Integer.parseInt(dayR);
            System.out.println(c);
            int d = Integer.parseInt(dayP);
            System.out.println(d);
            if (f > e) {
                request.setAttribute("Year", "Invalid Year");
                url = VIEW;
            } else if (a > b) {
                request.setAttribute("Month", "Invalid Month");
                url = VIEW;
            } else if (c > d && a >= b && f >= e) {
                request.setAttribute("Day", "Invalid Day");
                url = VIEW;
            }
            boolean check = true;
            String username = (String) session.getAttribute("username");
            CartObj shoppingCart = (CartObj) session.getAttribute("shCart");
            CarDAO dao = new CarDAO();
            String ID = dao.getIDByCode(Code);
            int percent = dao.getDicountCode(ID);
          
           

            if(action.equals("Total Price"))
            {
                float Total = 0;
                for(CarDTO dto : shoppingCart.getCart().values())
                {
                    Total =  Total + dto.getPrice();
                    dto.setError("");
                }
                    Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(Rent);
                    Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(Pay);
                       long getDiff = date2.getTime() - date1.getTime();
                    float getDaysDiff = getDiff / (24 * 60 * 60 * 1000);
                Total = Total * (getDaysDiff + 1);
                session.setAttribute("total", Total);
                session.setAttribute("cusName", cusName);
                session.setAttribute("cusPhone", phone);
                session.setAttribute("cusAddress", address);
                session.setAttribute("rentalDate", Rent);
                    session.setAttribute("returnDate", Pay);
                     session.setAttribute("newtotal", null);
                                session.setAttribute("CodeID", null);
                url = "viewcart.jsp";
            }
            else
            {
                for (CarDTO dto : shoppingCart.getCart().values())
                {
                    dto.setError("");
                 if(dao.checkAvailableByDate(dto.getCarID(), Rent, Pay))
                    {
                        dto.setError("Car is not avaliable in this day");
                        check = false;
                    }
                }
            
            if (check == false) {
                session.setAttribute("cusName", cusName);
                session.setAttribute("cusPhone", phone);
                session.setAttribute("cusAddress", address);
               session.setAttribute("rentalDate", Rent);
                    session.setAttribute("returnDate", Pay);
                     request.setAttribute("car", "Car is not avaliable in this day !!!");
                url = "viewcart.jsp";
            } else {
                OderDAO oderDAO = new OderDAO();
                String lastID = oderDAO.getLastOderByUser(username);
                String orderID = null;
                if (lastID == null) {
                    orderID = shoppingCart.getCustomerName().trim() + "-1";
                } else {
                    String[] tmp = lastID.split("-");
                    orderID = shoppingCart.getCustomerName().trim() + "-" + (Integer.parseInt(tmp[1]) + 1);
                }
                if (Code.equals("")) {
                    float Total = 0;
                    for (CarDTO dtos : shoppingCart.getCart().values()) {
                        Total = Total + dtos.getPrice();
                    }
                    Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(Rent);
                    Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(Pay);
                    long getDiff = date2.getTime() - date1.getTime();
                    float getDaysDiff = getDiff / (24 * 60 * 60 * 1000);
                    System.out.println(getDaysDiff);
                    Total = Total * (getDaysDiff + 1);
                    
                    boolean createOrder = oderDAO.CreateOrder(orderID.trim(), username, formatter1.parse(Rent), (float) Math.round(Total * 100) / 100, "Ordered", discount, formatter1.parse(Pay), cusName, phone, address, new Date());
                    if (createOrder) {
                        OrderDetailDAO detailDAO = new OrderDetailDAO();
                        int count = 1;
                        for (CarDTO dto : shoppingCart.getCart().values()) {
                            String orderDetailID = orderID.trim() + "-" + count++;
                            detailDAO.createOrderDetails(orderDetailID.trim(), dto.getCarID(), orderID.trim());
                        }
                    }
                    request.setAttribute("total", Total);
                    request.setAttribute("orderDetail", shoppingCart.getCart().values());
                    session.setAttribute("cusName", cusName);
                    session.setAttribute("cusPhone", phone);
                    session.setAttribute("cusAddress", address);
                    session.setAttribute("rentalDate", Rent);
                    session.setAttribute("returnDate", Pay);
                    session.setAttribute("usercusname", null);
                    session.setAttribute("userphone", null);
                    session.setAttribute("useraddress", null);
                    session.setAttribute("CodeID", null);
                    session.setAttribute("total", null);
                    session.setAttribute("newtotal", null);
                    session.removeAttribute("shCart");
                    url = "viewoder.jsp";
                } else {
                    float Total = 0;
                    for (CarDTO dtos : shoppingCart.getCart().values()) {
                        Total = Total + dtos.getPrice();
                    }
                    Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(Rent);
//                    System.out.println(date1);
                    Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(Pay);
//                    System.out.println(date2);
                    long getDiff = date2.getTime() - date1.getTime();
                    float getDaysDiff = getDiff / (24 * 60 * 60 * 1000);
//                    System.out.println(getDaysDiff);
                    Total = ((Total * (getDaysDiff + 1)) - (Total * (getDaysDiff + 1) * percent / 100));
                    request.setAttribute("newtotal", Total);
                    boolean createOrder = oderDAO.CreateOrder(orderID, username, formatter1.parse(Rent), (float) Math.round(Total * 100) / 100, "Ordered", discount, formatter1.parse(Pay), cusName, phone, address, new Date());
                    if (createOrder) {
                        OrderDetailDAO detailDAO = new OrderDetailDAO();

                        int count = 1;
                        for (CarDTO dto : shoppingCart.getCart().values()) {
                            String orderDetailID = orderID + "-" + count++;
                            detailDAO.createOrderDetails(orderDetailID,dto.getCarID(), orderID);
                        }
                    }
                    request.setAttribute("total", Total);
                    request.setAttribute("orderDetail", shoppingCart.getCart().values());
                    session.setAttribute("cusName", cusName);
                    session.setAttribute("cusPhone", phone);
                    session.setAttribute("cusAddress", address);
                    session.setAttribute("rentalDate", Rent);
                    session.setAttribute("returnDate", Pay);
                    session.removeAttribute("shCart");
                    session.setAttribute("usercusname", null);
                    session.setAttribute("total", null);
                    session.setAttribute("userphone", null);
                    session.setAttribute("useraddress", null);
                    session.setAttribute("CodeID", null);
                    session.setAttribute("newtotal", null);
                    url = "viewoder.jsp";
                }
            }
            }
        } catch (Exception e) {
            log("ERROR at ConfirmCartController" + e.getMessage());
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
