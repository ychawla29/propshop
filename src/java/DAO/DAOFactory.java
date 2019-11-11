/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.SQLException;

/**
 *
 * @author Yogesh Chawla
 */
public class DAOFactory {
    public final static int SQL = 1;
    public final static int JPA = 2;
    
    public static UserDAO getUserDAO(int persistenceType) throws ClassNotFoundException, SQLException{
        
        UserDAO userDao = null;
        switch(persistenceType){
            case DAOFactory.SQL : userDao = new UserDAOImplSQL();
            break;
            case DAOFactory.JPA : break;
        }
        return userDao;
    }
}
