/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Network;
import dao.UserDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import view.LoginView;
import view.SignInView;

/**
 *
 * @author jujulioed
 */
public class SignInController {
    
    private final SignInView view;

    public SignInController(SignInView view) {
        this.view = view;
    }
    
    public void saveUser() {
        
        String user = view.getUserField().getText();
        String pass = view.getPassField().getText();
        
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
        User userToRegister = new User(user, pass);
        try {
            Connection connection = new Network().getConnection();
            UserDAO userDao = new UserDAO(connection);
            userDao.insert(userToRegister);
            
        } catch (SQLException ex) {
            Logger.getLogger(SignInView.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
