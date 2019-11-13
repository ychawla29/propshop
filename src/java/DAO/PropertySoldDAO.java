/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.PropertySoldBean;
import Exception.*;
import java.util.ArrayList;
/**
 *
 * @author Yogesh Chawla
 */
public interface PropertySoldDAO {
    public boolean insert(PropertySoldBean propertySold) throws PropertySoldAlreadyExists;
    public boolean update(PropertySoldBean propertySold) throws PropertySoldNotFound;
    public PropertySoldBean  findByID(String id) throws PropertySoldNotFound;
    public boolean delete(PropertySoldBean propertySold) throws PropertySoldNotFound;
    public ArrayList<PropertySoldBean> getAll() throws PropertySoldNotFound;
}
