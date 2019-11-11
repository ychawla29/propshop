/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.UserBean;
import DAO.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.*;


/**
 *
 * @author Yogesh Chawla
 */
public class SerUserAction extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    Connection con;
    @Override
    public void init()
            throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        try{
        con = DBConnection.DBConnection.getInstance().getConnection();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            if(action.equals("add")){
                String userID = request.getParameter("userID");
                String userName = request.getParameter("userName");
                String userPass = request.getParameter("userPass");
                String userEmail = request.getParameter("userEmail");
                String userCnt = request.getParameter("userCnt");
                UserBean user = new UserBean(userID, userName, userPass, userEmail, userCnt);
                UserDAO userDao = DAOFactory.getUserDAO(DAOFactory.SQL);
                if(userDao.insert(user))
                    out.print("1");
                else 
                    out.print("0");
            }
            else if(action.equals("delete")){
                String userID = request.getParameter("userID");
                UserBean user = new UserBean();
                user.setId(userID);
                UserDAO userDao = DAOFactory.getUserDAO(DAOFactory.SQL);
                if(userDao.delete(user))
                    out.print("1");
                else
                    out.print("0");
            }
            else if(action.equals("update")){
                String userID = request.getParameter("userID");
                String userName = request.getParameter("userName");
                String userPass = request.getParameter("userPass");
                String userEmail = request.getParameter("userEmail");
                String userCnt = request.getParameter("userCnt");
                UserBean user = new UserBean(userID, userName, userPass, userEmail, userCnt);
                UserDAO userDao = DAOFactory.getUserDAO(DAOFactory.SQL);
                if(userDao.update(user))
                    out.print("1");
                else 
                    out.print("0");
            }
            else if(action.equals("get")){
                String userID = request.getParameter("userID");
                UserDAO userDao = DAOFactory.getUserDAO(DAOFactory.SQL);
                UserBean user = userDao.findByID(userID);
                JSONObject jo = new JSONObject();
                jo.put("userID", user.getId());
                jo.put("userName",user.getName());
                jo.put("userPass",user.getPassword());
                jo.put("userEmail",user.getEmail());
                jo.put("userCnt", user.getCnt_no());
                out.print(jo.toString());
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
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
