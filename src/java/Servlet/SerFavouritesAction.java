/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.FavouritesBean;
import DAO.DAOFactory;
import DAO.FavouritesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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
public class SerFavouritesAction extends HttpServlet {

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
            String action =  request.getParameter("action");
            if(action.equals("insert")){
                String userID = request.getParameter("userID");
                String propertyID = request.getParameter("popertyID");
                FavouritesBean favouritesBean = new FavouritesBean(userID, propertyID);
                FavouritesDAO favouritesDAO = DAOFactory.getFavouritesDAO(DAOFactory.SQL);
                if(favouritesDAO.insert(favouritesBean))
                    out.print("1");
                else
                    out.print("0");
            }
            else if(action.equals("delete")){
                String userID = request.getParameter("userID");
                String propertyID = request.getParameter("popertyID");
                FavouritesBean favouritesBean = new FavouritesBean(userID, propertyID);
                FavouritesDAO favouritesDAO = DAOFactory.getFavouritesDAO(DAOFactory.SQL);
                if(favouritesDAO.delete(favouritesBean))
                    out.print("1");
                else
                    out.print("0");
            }
            else if(action.equals("getByPID")){
                String propertyID = request.getParameter("popertyID");
                FavouritesBean favouritesBean;
                FavouritesDAO favouritesDAO = DAOFactory.getFavouritesDAO(DAOFactory.SQL);
                favouritesBean = favouritesDAO.getfavouritesByPropertyID(propertyID);
                JSONObject jo = new JSONObject();
                jo.put("userID", favouritesBean.getUserID());
                jo.put("prpertyID", favouritesBean.getPropertyID());
                out.print(jo.toString());
            }
            else if(action.equals("getByUID")){
                String userID = request.getParameter("userID");
                FavouritesBean favouritesBean;
                FavouritesDAO favouritesDAO = DAOFactory.getFavouritesDAO(DAOFactory.SQL);
                favouritesBean = favouritesDAO.getFavouritesByUserID(userID);
                JSONObject jo = new JSONObject();
                jo.put("userID", favouritesBean.getUserID());
                jo.put("prpertyID", favouritesBean.getPropertyID());
                out.print(jo.toString());
            }
            else if(action.equals("getAll")){
                JSONArray ja = new JSONArray();
                FavouritesDAO fDao = DAOFactory.getFavouritesDAO(DAOFactory.SQL);
                ArrayList<FavouritesBean> fBeans =  fDao.getAll();
                for(FavouritesBean favouritesBean : fBeans){
                    JSONObject jo = new JSONObject();
                    jo.put("userID", favouritesBean.getUserID());
                    jo.put("prpertyID", favouritesBean.getPropertyID());
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
