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
import javax.swing.JOptionPane;
import model.User;
import view.LoginView;
import view.MenuView;

/**
 *
 * @author jujulioed
 */
public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
    }

    public void authenticate() {
        
        String user = view.getUserField().getText();
        String pass = view.getPassField().getText();
        User usertToAuthenticate = new User(user, pass);
        try {
            Connection connection = new Network().getConnection();
            UserDAO userDao = new UserDAO(connection);
            boolean exists = userDao.userByUsernameAndPasswordExists(usertToAuthenticate);
        
            if (exists) {
                MenuView menuScreen = new MenuView();
                menuScreen.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(view, "BAD CREDENTIALS", "An error ocurred", 0);
                view.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    
}
