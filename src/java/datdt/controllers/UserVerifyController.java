/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.controllers;

import datdt.daos.sendEmail;
import datdt.dtos.LoginDTO;
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
@WebServlet(name = "UserVerifyController", urlPatterns = {"/UserVerifyController"})
public class UserVerifyController extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
                   String url = null;

        try{
            //feth form value
             HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            String name = request.getParameter("useremail");
           
      		//create instance object of the SendEmail Class
           sendEmail sm = new sendEmail();
      		//get the 6-digit code
           String code = sm.getRandom();
           
      		//craete new user using all information
            LoginDTO dto = new LoginDTO(username, name, code);
           //call the send email method
           boolean test = sm.sendEmail(dto);
           
      		//check if the email send successfully
           if(test){
               session.setAttribute("authcode", dto);
//               response.sendRedirect("verify.jsp");
                url = "verifycode.jsp";
           }
           
        }catch(Exception e){
            log("error at Verify Controller" + e.getMessage());
        }
        finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

   
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


   

}
