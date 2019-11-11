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
public class UserBean {
    private String id,name,password,email,cnt_no;

    public UserBean() {
    }

    public UserBean(String id, String name, String password, String email, String cnt_no) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.cnt_no = cnt_no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnt_no() {
        return cnt_no;
    }

    public void setCnt_no(String cnt_no) {
        this.cnt_no = cnt_no;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;
        UserBean other = (UserBean) obj;
        if(obj instanceof UserBean && this.id.equals(other.getId())){
            equal = true;
        }
        return equal;
    }

    @Override
    public String toString() {
        return "[UserBean{ ID:+"+id+", Name: "+name+"}]";
    }
}
