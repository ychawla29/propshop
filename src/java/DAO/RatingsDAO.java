/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.RatingsBean;
import Exception.RatingsAlreadyExists;
import Exception.RatingsNotFound;
import java.util.ArrayList;

/**
 *
 * @author Yogesh Chawla
 */
public interface RatingsDAO {
    public boolean insert(RatingsBean ratings) throws RatingsAlreadyExists;
    public boolean update(RatingsBean ratings) throws RatingsNotFound;
    public RatingsBean findByID(String id) throws RatingsNotFound;
    public boolean delete(RatingsBean ratings) throws RatingsNotFound;
    public ArrayList<RatingsBean> getAll() throws RatingsNotFound;
    
}
