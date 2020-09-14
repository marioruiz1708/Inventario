/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Uriel RD
 */
public class Querys {
    Conexion conexion=new Conexion();
    Connection con=conexion.ConnectDB();
    
    public String getStockProductobyID(int id){
        String[] vec=new String[50];
        String sql="select * from producto where idproducto="
                +id+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Stock");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
        }
        return vec[0];
    }
    public ArrayList<String> llenarcomboMarca(){
        ArrayList<String> lista = new ArrayList<String>();
        String sql= "select * from marca";
        try {
           Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                lista.add(rs.getString("Nombre"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        return lista;
    }
    public ArrayList<String> llenarcomboStatus(){
        ArrayList<String> lista = new ArrayList<String>();
        String sql= "select * from Status";
        try {
           Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                lista.add(rs.getString("Status"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        return lista;
    }
    public String getIDbyNameStatus(String name){
        String[] vec=new String[50];
        String sql="select * from status where status='"
                +name+"';";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("idStatus");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,"Soy el error x1"+ex);
        }
        return vec[0];
    }
    public String getIDbyNameMarca(String name){
        String[] vec=new String[50];
        String sql="select * from marca where nombre='"
                +name+"';";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("idMarca");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,"Soy el errorx3"+ex);
        }
        return vec[0];
    }
    public boolean RepetirNombreProducto(String n){
        String[] vec=new String[50];
        String sql="select * from producto where descripcion = "+
                "'"+n+"'"+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Descripcion");
           if(vec[0].equals(n)){
          return true;
         }
        }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,"Soy el errorx4"+ ex);
        }  
        return false;
    }
    public boolean RepetirNombreMarca(String n){
        String[] vec=new String[50];
        String sql="select * from marca where nombre = "+
                "'"+n+"'"+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Nombre");
           if(vec[0].equals(n)){
          return true;
         }
        }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
        }
        return false;
    }
     public boolean RepetirBarras(String n){
        String[] vec=new String[50];
        String sql="select * from producto where barras = '"+
                ""+n+"'"+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Barras");
           if(vec[0].equals(n)){
          return true;
         }
        }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
        }  
        return false;
    }
     public String getNombreProductobyID(String id){
        String[] vec=new String[50];
        String sql="select * from producto where idproducto="
                +id+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Descripcion");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
        }
        return vec[0];
    }
    public String getBarrasbyID(String id){
        String[] vec=new String[50];
        String sql="select * from producto where idproducto='"
                +id+"';";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Barras");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
        }
        return vec[0];
    }
    public String getNombreMarcabyID(String id){
        String[] vec=new String[50];
        String sql="select * from marca where idmarca="
                +id+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Nombre");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
        }
        return vec[0];
    }
    public boolean RepetirNombreStatus(String n){
        String[] vec=new String[50];
        String sql="select * from Status where status = '"+
                ""+n+"'"+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Status");
           if(vec[0].equals(n)){
          return true;
         }
        }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
        }  
        return false;
    }
    public String getNombreStatusbyID(String id){
        String[] vec=new String[50];
        String sql="select * from status where idstatus="
                +id+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Status");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
        }
        return vec[0];
    }
    public boolean RepetirUsuarioTecnico(String n){
        String[] vec=new String[50];
        String sql="select * from Tecnico where usuario = '"+
                ""+n+"'"+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("usuario");
           if(vec[0].equals(n)){
          return true;
         }
        }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
        }  
        return false;
    }
    public String getUsuarioTecnicobyID(String id){
        String[] vec=new String[50];
        String sql="select * from tecnico where idtecnico="
                +id+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Usuario");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
        }
        return vec[0];
    }
    public String getComentariobyID(String id){
        String[] vec=new String[50];
        String sql="select * from SalidaProductoTecnico where idsalidaproductotecnico="
                +id+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Comentario");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
        }
        return vec[0];
    }
    public boolean RepetirUsuarioAdmin(String n){
        String[] vec=new String[50];
        String sql="select * from Administrador where usuario = '"+
                ""+n+"'"+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("usuario");
           if(vec[0].equals(n)){
          return true;
         }
        }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
        }  
        return false;
    }
    public String getUsuarioAdminbyID(String id){
        String[] vec=new String[50];
        String sql="select * from Administrador where idAdministrador="
                +id+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Usuario");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
        }
        return vec[0];
    }
    public String getCantidadbyIDGuardarSalida(String id){
        String[] vec=new String[50];
        String sql="select * from GuardarSalidas where idGuardarSalidas="
                +id+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Cantidad");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
        }
        return vec[0];
    }
    public String getComentariobyIDGuardarSalida(String id){
        String[] vec=new String[50];
        String sql="select * from GuardarSalidas where idGuardarSalidas="
                +id+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Comentario");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
        }
        return vec[0];
    }
    public String getCantidadbyidSalidaProductoTecnico(String id){
        String[] vec=new String[50];
        String sql="select * from SalidaProductoTecnico where idSalidaProductoTecnico="
                +id+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Cantidad");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
        }
        return vec[0];
    }
    public String getOpearcionbySalidas(String id){
        String[] vec=new String[50];
        String sql="select * from SalidaProductoTecnico where idSalidaProductoTecnico="
                +id+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("Operacion");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
        }
        return vec[0];
    }
    public String getIDProductobyIDSalida(String id){
        String[] vec=new String[50];
        String sql="select * from SalidaProductoTecnico where idSalidaProductoTecnico="
                +id+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("idProducto");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
        }
        return vec[0];
    }
    public String getIDTecnicobyIDSalida(String id){
        String[] vec=new String[50];
        String sql="select * from SalidaProductoTecnico where idSalidaProductoTecnico="
                +id+";";  
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("idTecnico");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
        }
        return vec[0];
    }
     public String getFilasfromSalidaProductoTecnico(){
        String[] vec=new String[50];
        String sql="select count(*) from SalidaProductoTecnico;";
        try {
            Statement st = (Statement)con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            vec[0] = rs.getString("count(*)");
          }     
        }catch (SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
        }
        return vec[0];
    }
}
