/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.FavouritesBean;
import Exception.*;
import java.util.ArrayList;
import practice.PropertyNotFound;

/**
 *
 * @author Yogesh Chawla
 */
public interface FavouritesDAO {
    public boolean insert(FavouritesBean favourites) throws UserNotFound;
    public boolean update(FavouritesBean favourites) throws UserNotFound;
    public boolean delete(FavouritesBean favourites) throws UserNotFound;
    public FavouritesBean getFavouritesByUserID(String userID) throws UserNotFound;
    public FavouritesBean getfavouritesByPropertyID(String propertyID) throws PropertyNotFound;
    public ArrayList<FavouritesBean> getAll() throws PropertyNotFound;
}
