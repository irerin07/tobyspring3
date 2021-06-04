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
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", ""); // DB 연결을 위한 Connection을 가져온다

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?,)"); // SQL을 담은 Statement(또는 PreparedStatement)를 만든다.
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate(); // 만들어진 Statement를 실행한다.

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );
        ps.setString(1, id);

        /*
        조회의 경우 SQL 쿼리의 실행 결과를 ResultSet으로 받아서 정보를 저장할 오브젝트에 옮긴다.
         */
        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        /*
        작업 중에 생성된 Connection Statement, ResultSet같은 리소스는 작업을 마친 후 반드시 닫아준다.
         */
        rs.close();
        ps.close();
        c.close();

        return user;
    }
}