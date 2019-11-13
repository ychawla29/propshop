/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.*;
import Beans.propertyBean;
import java.util.ArrayList;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Yogesh Chawla
 */
public class SerPropertyAction extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
           String action = request.getParameter("action");
           if(action.equals("insert")){
               String propertyID = request.getParameter("propertyID");
               String popertyTitle = request.getParameter("propertyTitle");
               String propertyDescription = request.getParameter("propertyDescription");
               float propertyPrice = Float.parseFloat(request.getParameter("propertyPrice"));
               String propertyType = request.getParameter("propertyType");
               float propertyArea = Float.parseFloat(request.getParameter("propertyArea"));
               String propertyDate = request.getParameter("propertyDate");
               Date date = new Date(propertyDate);
               String address = request.getParameter("propertyAddress");
               String propertyPostedBy = request.getParameter("propertyPostedBy");
               propertyBean pBean = new propertyBean(propertyID, popertyTitle, propertyDescription, propertyType, address, propertyPostedBy, date, propertyPrice, propertyArea);
               propertyDAO pDao = DAOFactory.getPropertyDAO(DAOFactory.SQL);
               if(pDao.insert(pBean))
                   out.print("1");    
               else
                   out.print("0");
           }
           else if(action.equals("delete")){
               String propertyID = request.getParameter("propertyID");
               propertyBean pBean = new propertyBean();
               pBean.setId(propertyID);
               propertyDAO pDao = DAOFactory.getPropertyDAO(DAOFactory.SQL);
               if(pDao.delete(pBean))
                   out.print("1");
               else
                   out.print("0");
           }
           else if(action.equals("update")){
               String propertyID = request.getParameter("propertyID");
               String popertyTitle = request.getParameter("propertyTitle");
               String propertyDescription = request.getParameter("propertyDescription");
               float propertyPrice = Float.parseFloat(request.getParameter("propertyPrice"));
               String propertyType = request.getParameter("propertyType");
               float propertyArea = Float.parseFloat(request.getParameter("propertyArea"));
               String propertyDate = request.getParameter("propertyDate");
               Date date = new Date(propertyDate);
               String address = request.getParameter("propertyAddress");
               String propertyPostedBy = request.getParameter("propertyPostedBy");
               propertyBean pBean = new propertyBean(propertyID, popertyTitle, propertyDescription, propertyType, address, propertyPostedBy, date, propertyPrice, propertyArea);
               propertyDAO pDao = DAOFactory.getPropertyDAO(DAOFactory.SQL);
               if(pDao.update(pBean))
                   out.print("1");
               else
                   out.print("0");
           }
           else if(action.equals("getByPID")){
               String propertyID = request.getParameter("propertyID");
               propertyBean pBean = new propertyBean();
               propertyDAO pDao = DAOFactory.getPropertyDAO(DAOFactory.SQL);
               pBean = pDao.findByID(propertyID);
               JSONObject pjo = new JSONObject();
               pjo.put("propertyID", pBean.getId());
               pjo.put("propertyTitle", pBean.getTitle());
               pjo.put("propertyDescription", pBean.getDescription());
               pjo.put("propertyPrice", pBean.getPrice());
               pjo.put("propertyType", pBean.getType());
               pjo.put("propertyArea", pBean.getArea());
               pjo.put("propertyDate", pBean.getDate());
               pjo.put("propertyAddress", pBean.getAddress());
               pjo.put("propjectPostedBy", pBean.getPostedBy());
               out.print(pjo.toString());
           }
           else if(action.equals("getAll")){
               JSONArray ja = new JSONArray();
               propertyDAO pDao = DAOFactory.getPropertyDAO(DAOFactory.SQL);
               ArrayList<propertyBean> pBeans = pDao.getAll();
               for(propertyBean pBean : pBeans){
                    JSONObject pjo = new JSONObject();
                    pjo.put("propertyID", pBean.getId());
                    pjo.put("propertyTitle", pBean.getTitle());
                    pjo.put("propertyDescription", pBean.getDescription());
                    pjo.put("propertyPrice", pBean.getPrice());
                    pjo.put("propertyType", pBean.getType());
                    pjo.put("propertyArea", pBean.getArea());
                    pjo.put("propertyDate", pBean.getDate());
                    pjo.put("propertyAddress", pBean.getAddress());
                    pjo.put("propjectPostedBy", pBean.getPostedBy());
                    ja.add(pjo);
               }
               out.print(ja.toString());
           }
        }
        catch(Exception e){
            e.printStackTrace();
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
