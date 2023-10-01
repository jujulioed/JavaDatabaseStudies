/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.User;

/**
 *
 * @author duke
 */
public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(User user) throws SQLException {
        String sql = "insert into \"user\" (\"user\",\"password\") values('" + user.getUser() + "','" + user.getPassword() + "');";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        System.out.println("Cadastrado com sucesso");
        statement.close();
    }

}
