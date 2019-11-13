package DAO;

import Beans.propertyBean;
import Exception.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PropertyDAOImplSQL implements propertyDAO {

    private final Connection connection;
    public PropertyDAOImplSQL() throws ClassNotFoundException, SQLException {
        connection = DBConnection.DBConnection.getInstance().getConnection();
    }

	public boolean insert(propertyBean property) throws PropertyAlreadyExists {
        try{
            PreparedStatement pstmt = connection.prepareStatement("insert into property values(?,?,?,?,?,?,?,?)");
            pstmt.setString(1, property.getId());
            pstmt.setString(2, property.getTitle());
            pstmt.setString(3, property.getDescription());
            pstmt.setFloat(4, property.getPrice());            
            pstmt.setString(5, property.getType());
            pstmt.setFloat(6, property.getArea());
            pstmt.setDate(7,(Date) property.getDate());
            pstmt.setString(8, property.getAddress());
            pstmt.setString(9, property.getPostedBy());
            
            if(pstmt.executeUpdate() == 1){
                return true;
            }
        }
        catch(Exception e){
            throw new PropertyAlreadyExists("Property Already Created : "+property);
        }
        return false;

	}

	public boolean update(propertyBean property) throws PropertyNotFound {
        try{
            PreparedStatement pstmt = connection.prepareStatement("update prperty set property_id= ?, property_title = ?, property_description= ?, property_price = ?,property_type=?,property_area=?,property_date=?,property_address=?,property_postedBy=? where property_id = ?");
            pstmt.setString(1, property.getId());
            pstmt.setString(2, property.getTitle());
            pstmt.setString(3, property.getDescription());
            pstmt.setFloat(4, property.getPrice());            
            pstmt.setString(5, property.getType());
            pstmt.setFloat(6, property.getArea());
            pstmt.setDate(7,(Date) property.getDate());
            pstmt.setString(8, property.getAddress());
            pstmt.setString(9, property.getPostedBy());
            if(pstmt.executeUpdate() == 1){
                return true;
            }
        }
        catch(Exception ex){
            throw new PropertyNotFound("Given Property not found : "+property.getId());
        }
        return false;
    }

    @Override
    public propertyBean findByID(String id) throws PropertyNotFound {
		propertyBean property = new propertyBean();
        try{
            Statement stmt = connection.createStatement();
            String sql = "select * from property where property_id='"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
            	property.setId(id);
            	property.setTitle(rs.getString("property_title"));
            	property.setDescription(rs.getString("property_description"));
            	property.setPrice(rs.getFloat("property_price"));
            	property.setType(rs.getString("property_type"));
            	property.setArea(rs.getFloat("property_area"));
            	property.setDate(rs.getDate("property_date"));
            	property.setAddress(rs.getString("property_address"));
            	property.setPostedBy(rs.getString("property_postedBy"));
            	
            }
        }
        catch(Exception ex){
            throw new PropertyNotFound("Given Property Id not found : "+id);
        }
        return property;

	}

	public boolean delete(propertyBean property) throws PropertyNotFound {
		try{
			PreparedStatement psmt = connection.prepareStatement("delete from property where property_id=?");
			psmt.setString(1, property.getId());
			if(psmt.executeUpdate()==1)				
				return true;
		}catch(Exception e){
            throw new PropertyNotFound("Given Property not found : "+property.getId());			
		}
		return false;
	}

    @Override
    public ArrayList getAll() throws PropertyNotFound {
		ArrayList<propertyBean> propertys = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from property");
            while(rs.next()){
            	propertyBean property = new propertyBean();
            	property.setId(rs.getString("property_id"));
            	property.setTitle(rs.getString("property_title"));
            	property.setDescription(rs.getString("property_description"));
            	property.setPrice(rs.getFloat("property_price"));
            	property.setType(rs.getString("property_type"));
            	property.setArea(rs.getFloat("property_area"));
            	property.setDate(rs.getDate("property_date"));
            	property.setAddress(rs.getString("property_address"));
            	property.setPostedBy(rs.getString("property_postedBy"));            	
                propertys.add(property);
            }
        }
        catch(Exception ex){
            throw new PropertyNotFound("Property Not Found");
        }
        return propertys;
	}
    
    @Override
    public ArrayList<propertyBean> sortPLTH() throws PropertyNotFound{
        ArrayList<propertyBean> propBeans = new ArrayList<>();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from property order by property_price");
            while(rs.next()){
                propertyBean property = new propertyBean();
            	property.setId(rs.getString("property_id"));
            	property.setTitle(rs.getString("property_title"));
            	property.setDescription(rs.getString("property_description"));
            	property.setPrice(rs.getFloat("property_price"));
            	property.setType(rs.getString("property_type"));
            	property.setArea(rs.getFloat("property_area"));
            	property.setDate(rs.getDate("property_date"));
            	property.setAddress(rs.getString("property_address"));
            	property.setPostedBy(rs.getString("property_postedBy")); 
                propBeans.add(property);
            }
        }
        catch(Exception e){
            throw new PropertyNotFound("Some error occured while extracting the property");
        }
        return propBeans;
    }
    
    public ArrayList<propertyBean> sortPHTL() throws PropertyNotFound {
        ArrayList<propertyBean> propBeans = new ArrayList<>();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from property order by property_price desc");
            while(rs.next()){
                propertyBean property = new propertyBean();
            	property.setId(rs.getString("property_id"));
            	property.setTitle(rs.getString("property_title"));
            	property.setDescription(rs.getString("property_description"));
            	property.setPrice(rs.getFloat("property_price"));
            	property.setType(rs.getString("property_type"));
            	property.setArea(rs.getFloat("property_area"));
            	property.setDate(rs.getDate("property_date"));
            	property.setAddress(rs.getString("property_address"));
            	property.setPostedBy(rs.getString("property_postedBy")); 
                propBeans.add(property);
            }
        }
        catch(Exception e){
            throw new PropertyNotFound("Some error occured while extracting the property");
        }
        return propBeans;
    }
    
    public ArrayList<propertyBean> sortRHTL() throws PropertyNotFound {
         ArrayList<propertyBean> propBeans = new ArrayList<>();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT property.property_id, property.property_title, property.property_description, property.property_price,"
                    + " property.property_type, property.property_area, property.property_date, property.property_address, property.property_postedBy,"
                    + " count(ratings.ratings) as ratings from property JOIN ratings ON property.property_id = ratings.property_id group by"
                    + " ratings.ratings order by ratings.ratings desc");
            while(rs.next()){
                propertyBean property = new propertyBean();
            	property.setId(rs.getString("property_id"));
            	property.setTitle(rs.getString("property_title"));
            	property.setDescription(rs.getString("property_description"));
            	property.setPrice(rs.getFloat("property_price"));
            	property.setType(rs.getString("property_type"));
            	property.setArea(rs.getFloat("property_area"));
            	property.setDate(rs.getDate("property_date"));
            	property.setAddress(rs.getString("property_address"));
            	property.setPostedBy(rs.getString("property_postedBy")); 
                propBeans.add(property);
            }
        }
        catch(Exception e){
            throw new PropertyNotFound("Some error occured while extracting the property");
        }
        return propBeans;
    }
}

