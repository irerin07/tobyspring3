package com.tistory.irerin07.tobyspring3.user.dao;

import com.tistory.irerin07.tobyspring3.user.domain.User;

import java.sql.*;

/**
 * @since       2021.06.03
 * @author      민경수
 * @description user dao
 **********************************************************************************************************************/
public class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.nysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testDb", "sa", "");

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?,)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.nysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testDb", "sa", "");

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }
}