package com.example.springbootgradle.db;

import static java.lang.System.getenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class ConnectionChecker {
    public void check()throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://ec2-3-38-44-84.ap-northeast-2.compute.amazonaws.com:3306/spring-db", "root", "1234");

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SHOW DATABASES");
        rs = st.getResultSet();
        while (rs.next()) {
            String str = rs.getString(1);
            System.out.println(str);
        }
    }

    public void add() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://ec2-3-38-44-84.ap-northeast-2.compute.amazonaws.com:3306/spring-db", "root", "1234");

        PreparedStatement pstmt = conn.prepareStatement(
            "insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, "1");
        pstmt.setString(2, "kyeongrok");
        pstmt.setString(3, "12345678");
        pstmt.executeUpdate();
    }

    public void select() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://ec2-3-38-44-84.ap-northeast-2.compute.amazonaws.com:3306/spring-db", "root", "1234");

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from users");
        rs = st.getResultSet();
        while (rs.next()) {
            String col1 = rs.getString(1);
            String col2 = rs.getString(2);
            String col3 = rs.getString(3);
            System.out.printf("%s %s %s\n", col1, col2, col3);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionChecker connectionChecker = new ConnectionChecker();
//        connectionChecker.check();
//        connectionChecker.add();
//        connectionChecker.select();
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");
        System.out.printf("%s %s %s\n", dbHost, dbUser, dbPassword);
    }
}
