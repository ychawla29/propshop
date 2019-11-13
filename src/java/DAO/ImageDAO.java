/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.ImageBean;
import Exception.*;
import java.util.ArrayList;

/**
 *
 * @author Yogesh Chawla
 */
public interface ImageDAO {	
    public boolean insert(ImageBean image) throws ImageAlreadyExists;
    public boolean update(ImageBean image) throws ImageNotFound;
    public ImageBean findByID(String id) throws ImageNotFound;
    public boolean delete(ImageBean image) throws ImageNotFound;
    public ArrayList<ImageBean> getAllByID(String id) throws ImageNotFound;
}
