/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TienIchHoTro;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author congc
 */
public class ConnectToSQL {
    private static  Connection conn;
    
    public static  Connection getConnect(){
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url ="jdbc:sqlserver://CONGHEHE\\SQLEXPRESS:1433;databaseName=QLTRUNGTAMTIENGANH1";
            String user ="sa";
            String password = "123";
            conn= DriverManager.getConnection(url,user,password);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  conn;
    }
}
