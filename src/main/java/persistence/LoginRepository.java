
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import commons.Constants;
import java.sql.PreparedStatement;

public class LoginRepository {
    
    
    public void login(String nome, String senha) throws SQLException{
        Connection con;
        String sql = "SELECT email, senha FROM usuarios WHERE email = '" + nome +"' AND senha = '"+ senha+ "'";
        
        con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery(sql);
        
        if (rs.next()){
            System.out.println("Possui");
        } else {
            System.out.println("Não possui");
        }
        
        con.close();
    }
    
}
