package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Connection.ConnectionDB;
import views.HomeView;

public class AuthModel {


    public AuthModel() {
    }


    public boolean conectado(String u, String p) {

    	Connection conn = null;
        Statement stmt = null;
        String sql = "SELECT email, password FROM users";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = ConnectionDB.getDataSource().getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String userDB = rs.getString("email").trim();
                String passDB = rs.getString("password").trim();
                if (userDB.equals(u) && passDB.equals(p)) {
                    rs.close();
                    return true;
                }
            }
            rs.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            try {
                if (stmt != null) {
                	stmt.close();
                }
                if (conn != null) {
                	conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
