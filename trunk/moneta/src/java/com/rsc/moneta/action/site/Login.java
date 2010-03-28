/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsc.moneta.action.site;

import com.opensymphony.xwork2.Action;
import com.rsc.moneta.action.BaseAction;
import com.rsc.moneta.action.dao.UserDao;
import com.rsc.moneta.bean.User;

/**
 *
 * @author sulic
 */
public class Login extends BaseAction {

    private String phonenumber;
    private String password;
    private User user;

    @Override
    public String execute() throws Exception {
        setUser(new UserDao(em).getUserByPhoneAndPassword(phonenumber, password));
        session.put("user", user);
        return (user != null) ? Action.SUCCESS : Action.ERROR;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return phonenumber;
    }

    public void setUsername(String username) {
        this.phonenumber = username;
    }



}