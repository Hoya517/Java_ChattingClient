package com.daelim.member;

import java.sql.*;

public class MemberDAO {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    static String server = "61.83.168.88:3306";
    static String database = "D201740206";

    public static final String URL = "jdbc:mysql://"+server+"/"+database;
    public static final String USERID = "U201740206";
    public static final String USERPWD = "201740206";

    public MemberDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("db connect");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("drive connect failed...");
        }
    }

    public int insert(MemberVO vo) {
        try {
            conn = DriverManager.getConnection(URL, USERID, USERPWD);
            String sql = "insert into T_MEMBER(id, password, name, nickname, phone) values (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getId());
            pstmt.setString(2, vo.getPassword());
            pstmt.setString(3, vo.getName());
            pstmt.setString(4, vo.getNickname());
            pstmt.setString(5, vo.getPhone());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int isLoginCheck(String id, String password) {
        try {
            conn = DriverManager.getConnection(URL, USERID, USERPWD);
            String sql = "select * from T_MEMBER where id = ? and password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public String getNickname(String id) {
        String nickname = null;
        try {
            conn = DriverManager.getConnection(URL, USERID, USERPWD);
            String sql = "select nickname from T_MEMBER where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                nickname = rs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nickname;
    }

    public int idCheck(String id) {
        try {
            conn = DriverManager.getConnection(URL, USERID, USERPWD);
            String sql = "select nickname from T_MEMBER where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
