/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.PropertySoldBean;
import DAO.DAOFactory;
import DAO.PropertySoldDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Yogesh Chawla
 */
public class SerPropertySoldAction extends HttpServlet {

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
                String userID = request.getParameter("userID");
                PropertySoldBean propertySoldBean = new PropertySoldBean(propertyID, userID);
                PropertySoldDAO propertySoldDAO = DAOFactory.getPropertySoldDAO(DAOFactory.SQL);
                if(propertySoldDAO.insert(propertySoldBean))
                    out.print("1");
                else
                    out.print("0");
            }
            else if(action.equals("delete")){
                String propertyID = request.getParameter("propertyID");
                PropertySoldBean propertySoldBean = new PropertySoldBean();
                propertySoldBean.setPropertyId(propertyID);
                PropertySoldDAO propertySoldDAO = DAOFactory.getPropertySoldDAO(DAOFactory.SQL);
                if(propertySoldDAO.delete(propertySoldBean))
                    out.print("1");
                else
                    out.print("0");
            }
            else if(action.equals("update")){
                String propertyID = request.getParameter("propertyID");
                String userID = request.getParameter("userID");
                PropertySoldBean propertySoldBean = new PropertySoldBean(propertyID, userID);
                PropertySoldDAO propertySoldDAO = DAOFactory.getPropertySoldDAO(DAOFactory.SQL);
                if(propertySoldDAO.update(propertySoldBean))
                    out.print("1");
                else
                    out.print("0");
            }
            else if(action.equals("getByID")){
                String propertyID = request.getParameter("propertyID");
                PropertySoldBean propertySoldBean;
                PropertySoldDAO propertySoldDAO = DAOFactory.getPropertySoldDAO(DAOFactory.SQL);
                propertySoldBean = propertySoldDAO.findByID(propertyID);
                JSONObject jo = new JSONObject();
                jo.put("propertyID", propertySoldBean.getPropertyId());
                jo.put("userID", propertySoldBean.getUserId());
                out.print(jo.toString());
            }
            else if(action.equals("getAll")){
                JSONArray ja = new JSONArray();
                PropertySoldDAO propertySoldDAO = DAOFactory.getPropertySoldDAO(DAOFactory.SQL);
                ArrayList<PropertySoldBean> propertySoldBeans = propertySoldDAO.getAll();
                for(PropertySoldBean propertySoldBean : propertySoldBeans){
                    JSONObject jo = new JSONObject();
                    jo.put("propertyID", propertySoldBean.getPropertyId());
                    jo.put("userID", propertySoldBean.getUserId());
                    ja.add(jo);
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
