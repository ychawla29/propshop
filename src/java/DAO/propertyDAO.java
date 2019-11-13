package DAO;

import Beans.propertyBean;
import Exception.PropertyAlreadyExists;
import Exception.PropertyNotFound;
import java.util.ArrayList;

public interface propertyDAO {

    public boolean insert(propertyBean user) throws PropertyAlreadyExists;
    public boolean update(propertyBean user) throws PropertyNotFound;
    public propertyBean findByID(String id) throws PropertyNotFound;
    public boolean delete(propertyBean user) throws PropertyNotFound;
    public ArrayList<propertyBean> getAll() throws PropertyNotFound;
    public ArrayList<propertyBean> sortPLTH() throws PropertyNotFound;
    public ArrayList<propertyBean> sortPHTL() throws PropertyNotFound;
    public ArrayList<propertyBean> sortRHTL() throws PropertyNotFound;
}
