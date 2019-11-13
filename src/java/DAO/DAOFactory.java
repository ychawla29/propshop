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
    public static propertyDAO getPropertyDAO(int persistenceType) throws ClassNotFoundException, SQLException{
        propertyDAO pAO = null;
        switch(persistenceType){
            case DAOFactory.SQL : pAO = new PropertyDAOImplSQL();
            break;
            case DAOFactory.JPA : break;
        }
        return pAO;
    } 
    public static FavouritesDAO getFavouritesDAO(int persistenceType) throws ClassNotFoundException, SQLException{
        FavouritesDAO favouritesDAO = null;
        switch(persistenceType){
            case DAOFactory.SQL : favouritesDAO = new FavouritesDAOImplSQL();
            break;
            case DAOFactory.JPA : break;
        }
        return favouritesDAO;
    }
    public static RatingsDAO getRatingsDAO(int persistenceType) throws ClassNotFoundException, SQLException{
        RatingsDAO ratingsDao = null;
        switch(persistenceType){
            case DAOFactory.SQL : ratingsDao = new RatingsDAOImplSQL();
            break;
            case DAOFactory.JPA : break;
        }
        return ratingsDao;
    }
    public static PropertySoldDAO getPropertySoldDAO(int persistenceType) throws ClassNotFoundException, SQLException{
        PropertySoldDAO propertySoldDAO = null;
        switch(persistenceType){
            case DAOFactory.SQL : propertySoldDAO = new PropertySoldDAOImplSQL();
            break;
            case DAOFactory.JPA : break;
        }
        return propertySoldDAO;
    }
    public static ImageDAO getImageDAO(int persistenceType) throws ClassNotFoundException, SQLException{
        ImageDAO imageDAO = null;
        switch(persistenceType){
            case DAOFactory.SQL : imageDAO = new ImageDAOImplSQL();
            break;
            case DAOFactory.JPA : break;
        }
        return imageDAO;
    }
}
