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
import DAO.ImageDAO;
import Beans.ImageBean;
import DAO.DAOFactory;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Yogesh Chawla
 */
public class SerImageAction extends HttpServlet {

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
                String imageID = request.getParameter("imageID");
                String propertyID = request.getParameter("propertyID");
                String imageURL = request.getParameter("imageURL");
                ImageBean imageBean = new ImageBean(imageID, propertyID, imageURL);
                ImageDAO imageDAO = DAOFactory.getImageDAO(DAOFactory.SQL);
                if(imageDAO.insert(imageBean))
                    out.print("1");
                else
                    out.print("0");
            }
            else if(action.equals("delete")){
                String imageID = request.getParameter("imageID");
                ImageDAO imageDAO = DAOFactory.getImageDAO(DAOFactory.SQL);
                ImageBean imageBean = new ImageBean();
                imageBean.setImageId(imageID);
                if(imageDAO.delete(imageBean))
                    out.print("1");
                else
                    out.print("0");
            }
            else if(action.equals("update")){
                String imageID = request.getParameter("imageID");
                String propertyID = request.getParameter("propertyID");
                String imageURL = request.getParameter("imageURL");
                ImageBean imageBean = new ImageBean(imageID, propertyID, imageURL);
                ImageDAO imageDAO = DAOFactory.getImageDAO(DAOFactory.SQL);
                if(imageDAO.update(imageBean))
                    out.print("1");
                else
                    out.print("0");
            }
            else if(action.equals("getByID")){
                String imageID = request.getParameter("imageID");
                ImageDAO imageDAO = DAOFactory.getImageDAO(DAOFactory.SQL);
                JSONArray ja = new JSONArray();
                ArrayList<ImageBean> imageBeans = imageDAO.getAllByID(imageID);
                for(ImageBean imageBean : imageBeans){
                    JSONObject jo = new JSONObject();
                    jo.put("imageID", imageBean.getImageId());
                    jo.put("propertyID", imageBean.getPropertyId());
                    jo.put("imageURL", imageBean.getImageURL());
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
