package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import views.HomeView;

public class AuthModel {


    public AuthModel() {
    }


    public boolean conectado(String u, String p) {
    	String host ="jdbc:mysql://uywiohjkpxink6lw:22LfDvbA07QUq1XOKk4d@b0ufffjehz0mbockqctk-mysql.services.clever-cloud.com:3306/b0ufffjehz0mbockqctk";
    	String user = "uywiohjkpxink6lw";
    	String pass = "22LfDvbA07QUq1XOKk4d";
        Connection conn = null;
        Statement stmt = null;
        String sql = "SELECT email, password FROM users";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(host, user, pass);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String userDB = rs.getString("email").trim();
                String passDB = rs.getString("password").trim();
                if (userDB.equals(u) && passDB.equals(p)) {
                    new HomeView().home();
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
