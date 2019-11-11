/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.FavouritesBean;
import Exception.PropertyNotFound;
import Exception.UserNotFound;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author Yogesh Chawla
 */
public class FavouritesDAOImplSQL implements FavouritesDAO {

    private final Connection con;
    
    public FavouritesDAOImplSQL() throws ClassNotFoundException, SQLException {
        con = DBConnection.DBConnection.getInstance().getConnection();
    }
    
    @Override
    public boolean insert(FavouritesBean favourites) throws UserNotFound {
        try{
            PreparedStatement pstmt = con.prepareStatement("insert into favourites values(?,?)");
            pstmt.setString(1, favourites.getPropertyID());
            pstmt.setString(2, favourites.getUserID());
            if(pstmt.executeUpdate() == 1)
                return true;
        }
        catch(Exception ex){
            throw new UserNotFound("Given User or Property Found "+favourites);
        }
        return false;
    }

    @Override
    public boolean delete(FavouritesBean favourites) throws UserNotFound {
        try{
            PreparedStatement pstmt = con.prepareStatement("delete from favourites where property_id=? and user_id=?");
            pstmt.setString(1, favourites.getPropertyID());
            pstmt.setString(2, favourites.getUserID());
            if(pstmt.executeUpdate() == 1)
                return true;
        }
        catch(Exception e){
            throw new UserNotFound("Given User or Property Found "+favourites);
        }
        return false;
    }

    @Override
    public FavouritesBean getFavouritesByUserID(String userID) throws UserNotFound {
        FavouritesBean favourites = null;
        try{
            Statement stmt = con.createStatement();
            String sql = "select * from favourites where user_id = '"+userID+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                favourites = new FavouritesBean(rs.getString("user_id"), rs.getString("property_id"));
            }
        }
        catch(Exception ex){
            throw new UserNotFound("Given User Id not Found : "+userID);
        }
        return favourites;
    }

    @Override
    public FavouritesBean getfavouritesByPropertyID(String propertyID) throws PropertyNotFound {
        FavouritesBean favourites = null;
        try{
            Statement stmt = con.createStatement();
            String sql = "select * from favourites where property_id = '"+propertyID+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                favourites = new FavouritesBean(rs.getString("user_id"), rs.getString("property_id"));
            }
        }
        catch(Exception ex){
            throw new PropertyNotFound("Given Property Id not Found : "+propertyID);
        }
        return favourites;
    }

    @Override
    public ArrayList<FavouritesBean> getAll() throws PropertyNotFound {
        ArrayList<FavouritesBean> favourites = new ArrayList<>();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from favourites");
            while(rs.next()){
                FavouritesBean favourite = new FavouritesBean(rs.getString("user_id"), rs.getString("property_id"));
                favourites.add(favourite);
            }
        }
        catch(Exception ex){
            throw new PropertyNotFound("No Record Found");
        }
        return favourites;
    }
    
    
}
