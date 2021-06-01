package com.daelim.chat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatDAO {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    static String server = "61.83.168.88:3306";
    static String database = "D201740206";

    public static final String URL = "jdbc:mysql://"+server+"/"+database;
    public static final String USERID = "U201740206";
    public static final String USERPWD = "201740206";

    public ChatDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("db connect");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("drive connect failed...");
        }
    }

    public void insert(ChatVO vo) {
        try {
            conn = DriverManager.getConnection(URL, USERID, USERPWD);
            String sql = "insert into T_CHAT(memo) values (?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getChat());
            System.out.println(vo.getChat());
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
    }

    public List<String> select() {
        List<String> list = new ArrayList<String>();
        String str;

        try {
            conn = DriverManager.getConnection(URL, USERID, USERPWD);
            String sql = "select memo from T_CHAT order by _id asc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                str = rs.getString("memo");

                list.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }


}
