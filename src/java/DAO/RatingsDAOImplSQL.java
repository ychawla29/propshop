/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Yogesh Chawla
 */
import Beans.RatingsBean;
import Exception.RatingsAlreadyExists;
import Exception.RatingsNotFound;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RatingsDAOImplSQL implements RatingsDAO{

    private final Connection con;
    public RatingsDAOImplSQL() throws ClassNotFoundException, SQLException {
        con = DBConnection.DBConnection.getInstance().getConnection();
    }

	public boolean insert(RatingsBean ratings) throws RatingsAlreadyExists {
        try{
            PreparedStatement pstmt = con.prepareStatement("insert into ratings values(?,?,?)");
            pstmt.setInt(1, ratings.getPropertyId());
            pstmt.setString(2, ratings.getUserId());
            pstmt.setInt(3, ratings.getRatings());
            if(pstmt.executeUpdate() == 1){
                return true;
            }
        }
        catch(Exception e){
            throw new RatingsAlreadyExists("Ratings Already Exists Already Created [Insert]: "+ratings);
        }
        return false;

	}

	public boolean update(RatingsBean ratings) throws RatingsNotFound {
		try{
            PreparedStatement pstmt = con.prepareStatement("update ratings set property_id = ?, user_id= ?, ratings = ? where property_id = ?");
            pstmt.setInt(1, ratings.getPropertyId());
            pstmt.setString(2, ratings.getUserId());
            pstmt.setInt(3, ratings.getRatings());
            if(pstmt.executeUpdate() == 1){
                return true;
            }
        }
        catch(Exception ex){
            throw new RatingsNotFound("Given Ratings Not Found: "+ratings.getPropertyId());
        }
        return false;
	}

	public RatingsBean findByID(String id) throws RatingsNotFound {
		RatingsBean rating = new RatingsBean();
        try{
            Statement stmt = con.createStatement();
            String sql = "select * from ratings where property_id='"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
            	rating.setPropertyId(Integer.parseInt(id));
            	rating.setUserId(rs.getString("user_id"));
            	rating.setRatings(rs.getInt("ratings"));
            }
        }
        catch(Exception ex){
            throw new RatingsNotFound("Given Ratings Not Found: "+id);
        }
        return rating;
	}

	public boolean delete(RatingsBean ratings) throws RatingsNotFound {
		try{
            PreparedStatement pstmt = con.prepareStatement("delete from ratings where property_id = ?");
            pstmt.setInt(1, ratings.getPropertyId());
            if(pstmt.executeUpdate() == 1){
                return true;
            }
        }
        catch(Exception ex){
            throw new RatingsNotFound("Given Ratings not found : "+ratings.getPropertyId());
        }
        return false;
	}

	public ArrayList getAll() throws RatingsNotFound {
		ArrayList<RatingsBean> ratings = new ArrayList<>();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ratings");
            while(rs.next()){
            	RatingsBean rating = new RatingsBean();
            	rating.setPropertyId(rs.getInt("property_id"));
            	rating.setUserId(rs.getString("user_id"));
            	rating.setRatings(rs.getInt("ratings"));
            	ratings.add(rating);
            }
        }
        catch(Exception ex){
            throw new RatingsNotFound("Ratings Not Found");
        }
        return ratings;
	}

}

