
package controllers;

import commons.Constants;
import gui.login.LoginView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import persistence.LoginRepository;

public class LoginController {
    public void loginUsuario(LoginView view) throws SQLException{
        
        Connection con;
        con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
        
        LoginRepository login = new LoginRepository();
        login.login(view.getLogin().getText(), view.getSenha().getText());
        
    }
    
}
