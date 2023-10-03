/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author jujulioed
 */
public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User insert(User user) throws SQLException {
        String sql = "insert into \"user\" (\"user\",\"password\") values(?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getUser());
        statement.setString(2, user.getPassword());
        statement.execute();
        
        ResultSet resultSet = statement.getGeneratedKeys();
        
        if(resultSet.next()) {
            int id = resultSet.getInt("id");
            user.setId(id);
        }
        System.out.println("Successfully inserted");
        return user;
    }
    
    public void update(User user) throws SQLException {
        String sql = "update \"user\"  set \"user\" = ? \"password\" = ? where \"id\" = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getUser());
        statement.setString(2, user.getPassword());
        statement.setInt(3, user.getId());
        statement.execute();
        
    }
    
    public void insertOrUpdate(User user) throws SQLException {
        if (user.getId() > 0) {
            update(user);
        } else {
            insert(user);
        }
    }
    
    public void delete(User user) throws SQLException {
        String sql = "delete from \"user\" where \"id\" = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, user.getId());
        statement.execute();
        
    }
    
    public ArrayList<User> selectAll() throws SQLException {
        String sql = "select * from \"user\"";
        PreparedStatement statement = connection.prepareStatement(sql);
         
        return search(statement);
    }
    
    public User selectPerId(User user) throws SQLException {
        String sql = "select * from \"user\" where \"id\" = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, user.getId());

        return search(statement).get(0);
    }
    
    public boolean userByUsernameAndPasswordExists(User user) throws SQLException{
        String sql = "select * from \"user\" where \"user\" = ? and \"password\" = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getUser());
        statement.setString(2, user.getPassword());
        
        
        statement.execute();
        System.out.println("Successfully Logged In");

        ResultSet resultSet = statement.getResultSet();
        return resultSet.next();
    }
    
    private ArrayList<User> search(PreparedStatement statement) throws SQLException {
        ArrayList<User> allUsers = new ArrayList<>();
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String user = resultSet.getString("user");
            String pass = resultSet.getString("password");
            
            User dbUser = new User(id, user, pass);
            allUsers.add(dbUser);
        }
        
        return allUsers;
    }

}
