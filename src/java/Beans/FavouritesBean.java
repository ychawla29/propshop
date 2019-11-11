/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Yogesh Chawla
 */
public class FavouritesBean {
    private String userID, propertyID;

    public FavouritesBean() {
    }

    public FavouritesBean(String userID, String propertyID) {
        this.userID = userID;
        this.propertyID = propertyID;
    }

    public String getPropertyID() {
        return propertyID;
    }

    public String getUserID() {
        return userID;
    }

    public void setPropertyID(String propertyID) {
        this.propertyID = propertyID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Favourites[ UserID : "+userID+", PropertyID : "+propertyID+" ]";
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID!=null && propertyID!=null ? userID.hashCode()+propertyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;
        FavouritesBean favourites = (FavouritesBean) obj;
        if(obj instanceof FavouritesBean && this.userID.equals(favourites.userID) && this.propertyID.equals(favourites.propertyID)) equal = true;
        return equal;
    }
    
    
}
