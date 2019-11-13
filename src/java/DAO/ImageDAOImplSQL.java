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
import Beans.ImageBean;
import Exception.ImageAlreadyExists;
import Exception.ImageNotFound;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ImageDAOImplSQL implements ImageDAO{

    private final Connection con;
    public ImageDAOImplSQL() throws ClassNotFoundException, SQLException, SQLException {
        con = DBConnection.DBConnection.getInstance().getConnection();
    }

	public boolean insert(ImageBean image) throws ImageAlreadyExists {
        try{
            PreparedStatement pstmt = con.prepareStatement("insert into image values(?,?,?)");
            pstmt.setString(1, image.getImageId());
            pstmt.setString(2, image.getPropertyId());
            pstmt.setString(3, image.getImageURL());           
            if(pstmt.executeUpdate() == 1){
                return true;
            }
        }
        catch(Exception e){
            throw new ImageAlreadyExists("Image Already Created : "+image);
        }
        return false;

	}

	public boolean update(ImageBean image) throws ImageNotFound {
        try{
            PreparedStatement pstmt = con.prepareStatement("update images set image_id = ?, property_id = ?, image_url = ?, user_cnt_no = ? where image_id = ?");
            pstmt.setString(1, image.getImageId());
            pstmt.setString(2, image.getPropertyId());
            pstmt.setString(3, image.getImageURL());           
            if(pstmt.executeUpdate() == 1){
                return true;
            }
        }
        catch(Exception ex){
            throw new ImageNotFound("Given Image not found : "+image.getImageId());
        }
        return false;
	}

	public ImageBean findByID(String id) throws ImageNotFound {
		ImageBean image = new ImageBean();
        try{
            Statement stmt = con.createStatement();
            String sql = "select * from images where image_id='"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                image.setImageId(id);
                image.setImageURL(rs.getString("image_url"));
                image.setPropertyId(rs.getString("property_id"));
            }
        }
        catch(Exception ex){
            throw new ImageNotFound("Given Image not found : "+id);
        }
        return image;
        }

	public boolean delete(ImageBean image) throws ImageNotFound {
		try{
            PreparedStatement pstmt = con.prepareStatement("delete from images where image_id = ?");
            pstmt.setString(1, image.getImageId());
            if(pstmt.executeUpdate() == 1){
                return true;
            }
        }
        catch(Exception ex){
            throw new ImageNotFound("Given Image not found : "+image.getImageId());
        }
        return false;
	}

	public ArrayList getAllByID(String id) throws ImageNotFound {
		ArrayList<ImageBean> images = new ArrayList<>();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from images where image_id="+id);
            while(rs.next()){
                ImageBean image = new ImageBean();
                image.setImageId(rs.getString("image_id"));
                image.setImageURL(rs.getString("image_url"));
                image.setPropertyId(rs.getString("property_id"));
                images.add(image);
            }
        }
        catch(Exception ex){
            throw new ImageNotFound("Images Not Found");
        }
        return images;
	}

}
