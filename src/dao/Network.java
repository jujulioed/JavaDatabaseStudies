/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jujulioed
 */
public class Network {
    
    public Connection getConnection() throws SQLException {
        Connection link = DriverManager.getConnection("jdbc:postgresql://localhost:5432/javaexampledatabase", "postgres", "postgres");
        return link;
    }
}