/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Uriel RD
 */

public class Conexion {
    public Connection ConnectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.70/inventario","root","pegasodametupoder17");
//            JOptionPane.showMessageDialog(null, "Conectado");
//            System.out.println("Connected to database");
//            192.168.43.74 
//              select host from mysql.user where user='root';
//           create user 'root'@'%' identified by '';
//          grant all privileges on *.* to 'root'@'%';
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos" + e);
            return null;
        }
    }
}
