/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.PropertySoldBean;
import Exception.*;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author Yogesh Chawla
 */
public class PropertySoldDAOImplSQL implements PropertySoldDAO {

    private final Connection con;
    public PropertySoldDAOImplSQL() throws ClassNotFoundException, SQLException, SQLException {
        con = DBConnection.DBConnection.getInstance().getConnection();
    }
	public boolean insert(PropertySoldBean propertySold) throws PropertySoldAlreadyExists {
		   try{
	            PreparedStatement pstmt = con.prepareStatement("insert into property_sold values(?,?)");
	            pstmt.setString(1, propertySold.getPropertyId());
	            pstmt.setString(2, propertySold.getUserId());	            
	            if(pstmt.executeUpdate() == 1){
	                return true;
	            }
	        }
	        catch(Exception e){
	            throw new PropertySoldAlreadyExists("Property Sold Already Created : "+propertySold);
	        }
	        return false;
	}

    @Override
	public boolean update(PropertySoldBean propertySold) throws PropertySoldNotFound {
		try{
            PreparedStatement pstmt = con.prepareStatement("update property_sold set property_id = ?, user_id = ? where property_id = ?");
            pstmt.setString(1, propertySold.getPropertyId());
            pstmt.setString(2, propertySold.getUserId());
            if(pstmt.executeUpdate() == 1){
                return true;
            }
        }
        catch(Exception ex){
            throw new PropertySoldNotFound("Given Property Sold not found : "+propertySold.getPropertyId());
        }
        return false;
	}

    @Override
	public PropertySoldBean findByID(String id) throws PropertySoldNotFound {
		PropertySoldBean propertySold = new PropertySoldBean();
        try{
            Statement stmt = con.createStatement();
            String sql = "select * from property_sold where property_id='"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                propertySold.setPropertyId(id);
                propertySold.setUserId(rs.getString("user_id"));                
            }
        }
        catch(Exception ex){
            throw new PropertySoldNotFound("Given property for Sol dnot found : "+id);
        }
        return propertySold;
	}

    @Override
	public boolean delete(PropertySoldBean propertySold) throws PropertySoldNotFound {
		try{
            PreparedStatement pstmt = con.prepareStatement("delete from property_sold where property_id = ?");
            pstmt.setString(1, propertySold.getPropertyId());
            if(pstmt.executeUpdate() == 1){
                return true;
            }
        }
        catch(Exception ex){
            throw new PropertySoldNotFound("Given Property for Sold Not Found : "+propertySold.getPropertyId());
        }
        return false;
	}

    @Override
	public ArrayList getAll() throws PropertySoldNotFound {
		ArrayList<PropertySoldBean> propertySolds= new ArrayList<>();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from property_sold");
            while(rs.next()){
            	PropertySoldBean propertySold= new PropertySoldBean();
            	 propertySold.setPropertyId(rs.getString("property_id"));
                 propertySold.setUserId(rs.getString("user_id"));
                 propertySolds.add(propertySold);
            }
        }
        catch(Exception ex){
            throw new PropertySoldNotFound("Property For Sold NotFound ");
        }
        return propertySolds;
	}

	
}
