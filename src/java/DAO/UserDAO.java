/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Beans.*;
import Exception.*;
import java.util.ArrayList;

/**
 *
 * @author Yogesh Chawla
 */
public interface UserDAO {
    
    public boolean insert(UserBean user) throws UserAlreadyExists;
    public boolean update(UserBean user) throws UserNotFound;
    public UserBean findByID(String id) throws UserNotFound;
    public boolean delete(UserBean user) throws UserNotFound;
    public ArrayList<UserBean> getAll() throws UserNotFound;
    
}
