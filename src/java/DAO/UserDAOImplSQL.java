/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.UserBean;
import Exception.UserAlreadyExists;
import Exception.UserNotFound;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Yogesh Chawla
 */
public class UserDAOImplSQL implements UserDAO {

    private final Connection con;
    public UserDAOImplSQL() throws ClassNotFoundException, SQLException, SQLException {
        con = DBConnection.DBConnection.getInstance().getConnection();
    }

    @Override
    public boolean insert(UserBean user) throws UserAlreadyExists {
        try{
            PreparedStatement pstmt = con.prepareStatement("insert into user values(?,?,?,?,?)");
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getCnt_no());
            if(pstmt.executeUpdate() == 1){
                return true;
            }
        }
        catch(Exception e){
            throw new UserAlreadyExists("User Already Created : "+user);
        }
        return false;
    }

    @Override
    public boolean update(UserBean user) throws UserNotFound {
        try{
            PreparedStatement pstmt = con.prepareStatement("update user set user_name = ?, user_password = ?, user_email = ?, user_cnt_no = ? where user_id = ?");
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getCnt_no());
            pstmt.setString(5, user.getId());
            if(pstmt.executeUpdate() == 1){
                return true;
            }
        }
        catch(Exception ex){
            throw new UserNotFound("Given User not found : "+user.getId());
        }
        return false;
    }

    @Override
    public UserBean findByID(String id) throws UserNotFound {
        UserBean user = new UserBean();
        try{
            Statement stmt = con.createStatement();
            String sql = "select * from user where user_id='"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                user.setId(id);
                user.setName(rs.getString("user_name"));
                user.setPassword(rs.getString("user_password"));
                user.setEmail(rs.getString("user_email"));
                user.setCnt_no(rs.getString("user_cnt_no"));
            }
        }
        catch(Exception ex){
            throw new UserNotFound("Given user not found : "+id);
        }
        return user;
    }

    @Override
    public boolean delete(UserBean user) throws UserNotFound {
        try{
            PreparedStatement pstmt = con.prepareStatement("delete from user where user_id = ?");
            pstmt.setString(1, user.getId());
            if(pstmt.executeUpdate() == 1){
                return true;
            }
        }
        catch(Exception ex){
            throw new UserNotFound("Given User not found : "+user.getId());
        }
        return false;
    }

    @Override
    public ArrayList<UserBean> getAll() throws UserNotFound {
        ArrayList<UserBean> users = new ArrayList<>();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user");
            while(rs.next()){
                UserBean user = new UserBean();
                user.setId(rs.getString("user_id"));
                user.setName(rs.getString("user_name"));
                user.setPassword(rs.getString("user_password"));
                user.setEmail(rs.getString("user_email"));
                user.setCnt_no(rs.getString("user_cnt_no"));
                users.add(user);
            }
        }
        catch(Exception ex){
            throw new UserNotFound("Users Not Found");
        }
        return users;
    }
    
    
}
