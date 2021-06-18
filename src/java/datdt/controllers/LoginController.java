/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.controllers;

import datdt.dtos.Tool;
import datdt.daos.LoginDAO;
import datdt.daos.OderDAO;
import datdt.daos.VerifyRecaptcha;
import datdt.dtos.LoginDTO;
import java.io.IOException;
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
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String USER = "PageController";
    private static final String LOGIN = "login.jsp";
    private static final String VERIFY = "verify.jsp";
    private static final String SHD = "SearchDateController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String Rend = request.getParameter("txtRentDate");
        String Pname = request.getParameter("txtName");
        String url = LOGIN;
        try {
            LoginDAO DAO = new LoginDAO();
            OderDAO oDAO = new OderDAO();
            oDAO.setStatus();
            LoginDTO dto = DAO.checkLogin(username, Tool.sha256(password).trim());
            HttpSession session = request.getSession();
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
            if (dto == null || !verify) {
                if (dto == null) {
                    request.setAttribute("verify", "Your username or password is invalid!!!");
                } else {
                    request.setAttribute("verify", "Plese Verify!!!");
                }
                url = LOGIN;
            } else if (dto.getStatus().equals("Inactive  ")) {
                System.out.println(dto.getStatus());
                url = VERIFY;
                session.setAttribute("username", dto.getEmail());
                session.setAttribute("fullname", dto.getName());
                session.setAttribute("email", dto.getUseremail());
            }else if(!Rend.equals("") || !Pname.equals("")){
                    session.setAttribute("username", dto.getEmail());
                session.setAttribute("fullname", dto.getName());
                url = SHD;
            }
            else {
                url = USER;
                session.setAttribute("username", dto.getEmail());
                session.setAttribute("fullname", dto.getName());
            }
        } catch (Exception e) {
            log("error at login: " + e.getMessage());
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
