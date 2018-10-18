package tests;

import helper.Helper;

import java.sql.*;

public class dbTest {
    public static void main(String[] args) {
        Connection connection = Helper.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("select login from users where login=?");
            ps.setString(1,"azaza");
            ResultSet rs = ps.executeQuery();
            System.out.println(rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
