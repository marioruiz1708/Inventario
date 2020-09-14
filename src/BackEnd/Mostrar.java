/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import BackEnd.Querys;
import BackEnd.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Uriel RD
 */
public class Mostrar {
    Conexion conexion=new Conexion();
    Connection con=conexion.ConnectDB();
    
    public void MostrarProductoTecnico(JTable tabla){
        try{
       DefaultTableModel defaultTableModel;
       String[]tit={"Codigo","Producto","Precio","Stock"}; 
       Object []datos=new Object[50];
       JButton btncomentarios = new JButton("Comentarios");
       btncomentarios.setName("btncomentarios");
       String sql="select producto.barras as Codigo,producto.descripcion as Producto, "
               + "producto.Precio,producto.stock from producto"+";";
       defaultTableModel=new DefaultTableModel(null,tit);
        Connection conn=conexion.ConnectDB();
        try {
            Statement st=(Statement)conn.createStatement();
            ResultSet resul=st.executeQuery(sql);
            while(resul.next()){
                datos[0]=resul.getString("Codigo");
                datos[1]=resul.getString("Producto");
                datos[2]=resul.getString("Precio");
                datos[3]=resul.getString("Stock");
                defaultTableModel.addRow(datos);
            }
            tabla.setModel(defaultTableModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void MostrarProductos(JTable tabla){
       try{
       DefaultTableModel defaultTableModel;
       String[]tit={"ID","Descripcion","Precio","Barras","Status","Marca","Inicial","Entrada","Stock","FechaEntrada"};
       String sql="select producto.idProducto as ID,producto.Descripcion,producto.Precio,producto.Barras,Status.Status,"
               + "Marca.nombre as Marca,producto.Inicial,producto.Entrada,producto.Stock,producto.FechaEntrada from "
               + "Producto inner join Marca on Producto.idmarca=marca.idmarca inner join Status on "
               + "status.idstatus=producto.idstatus;";
       String[] datos=new String[50];
       defaultTableModel=new DefaultTableModel(null,tit);
        Connection conn=conexion.ConnectDB();
        try {
            Statement st=(Statement)conn.createStatement();
            ResultSet resul=st.executeQuery(sql);
            while(resul.next()){
                datos[0]=resul.getString("ID");
                datos[1]=resul.getString("Descripcion");
                datos[2]=resul.getString("Precio");
                datos[3]=resul.getString("Barras");
                datos[4]=resul.getString("Status");
                datos[5]=resul.getString("Marca");
                datos[6]=resul.getString("Inicial");
                datos[7]=resul.getString("Entrada");
                datos[8]=resul.getString("Stock");
                datos[9]=resul.getString("FechaEntrada");
                defaultTableModel.addRow(datos);
            }
            tabla.setModel(defaultTableModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void MostrarMarca(JTable tabla){
        try{
       DefaultTableModel defaultTableModel;
       String[]tit={"idMarca","Nombre"};
       String[] datos=new String[50];
       String sql="select * from marca" + ";";
       defaultTableModel=new DefaultTableModel(null,tit);
        Connection conn=conexion.ConnectDB();
        try {
            Statement st=(Statement)conn.createStatement();
            ResultSet resul=st.executeQuery(sql);
            while(resul.next()){
                datos[0]=resul.getString("idMarca");
                datos[1]=resul.getString("Nombre");
                defaultTableModel.addRow(datos);
            }
            tabla.setModel(defaultTableModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void MostrarStatus(JTable tabla){
        try{
       DefaultTableModel defaultTableModel;
       String[]tit={"idStatus","Status"};
       String[] datos=new String[50];
       String sql="select * from status" + ";";
       defaultTableModel=new DefaultTableModel(null,tit);
        Connection conn=conexion.ConnectDB();
        try {
            Statement st=(Statement)conn.createStatement();
            ResultSet resul=st.executeQuery(sql);
            while(resul.next()){
                datos[0]=resul.getString("idStatus");
                datos[1]=resul.getString("Status");
                defaultTableModel.addRow(datos);
            }
            tabla.setModel(defaultTableModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void MostrarTecnico(JTable tabla){
        try{
       DefaultTableModel defaultTableModel;
       String[]tit={"idTecnico","Nombre","Usuario","Password"};
       String[] datos=new String[50];
       String sql="select idTecnico,Nombre,Usuario,Password from Tecnico"+ ";";
       defaultTableModel=new DefaultTableModel(null,tit);
       Connection conn=conexion.ConnectDB();
        try {
            Statement st=(Statement)conn.createStatement();
            ResultSet resul=st.executeQuery(sql);
            while(resul.next()){
                datos[0]=resul.getString("idTecnico");
                datos[1]=resul.getString("Nombre");
                datos[2]=resul.getString("Usuario");
                datos[3]=resul.getString("Password");
                defaultTableModel.addRow(datos);
            }
            tabla.setModel(defaultTableModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void MostrarProductosSalida(JTable tabla){
       try{
       DefaultTableModel defaultTableModel;
       String[]tit={"Registro","Codigo","Descripcion","Marca","Status","Inicial","Entrada","Salida","Tecnico","FechaSalida","Stock"};
       String sql="select SalidaProductoTecnico.idSalidaProductoTecnico as Registro,producto.barras "
               + "as Codigo,producto.Descripcion,Marca.Nombre as Marca,Status.Status,producto.Inicial,producto."
               + "Entrada,SalidaProductoTecnico.Cantidad as Salida,Tecnico.Nombre as Tecnico,SalidaProductoTecnico.Fecha "
               + "as FechaSalida,producto.Stock from Producto inner join Status on producto.idstatus=Status.idstatus "
               + "inner join Marca on producto.idMarca=Marca.idmarca inner join SalidaProductoTecnico "
               + "on Salidaproductotecnico.idproducto=producto.idproducto inner join Tecnico on "
               + "tecnico.idtecnico=SalidaProductoTecnico.idtecnico;";
       String[] datos=new String[50];
       defaultTableModel=new DefaultTableModel(null,tit);
        Connection conn=conexion.ConnectDB();
        try {
            Statement st=(Statement)conn.createStatement();
            ResultSet resul=st.executeQuery(sql);
            while(resul.next()){
                datos[0]=resul.getString("Registro");
                datos[1]=resul.getString("Codigo");
                datos[2]=resul.getString("Descripcion");
                datos[3]=resul.getString("Marca");
                datos[4]=resul.getString("Status");
                datos[5]=resul.getString("Inicial");
                datos[6]=resul.getString("Entrada");
                datos[7]=resul.getString("Salida");
                datos[8]=resul.getString("Tecnico");
                datos[9]=resul.getString("FechaSalida");
                datos[10]=resul.getString("Stock");
                defaultTableModel.addRow(datos);
            }
            tabla.setModel(defaultTableModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void MostrarAdmin(JTable tabla){
        try{
       DefaultTableModel defaultTableModel;
       String[]tit={"idAdministrador","Nombre","Usuario","Password"};
       String[] datos=new String[50];
       String sql="select * from administrador"+ ";";
       defaultTableModel=new DefaultTableModel(null,tit);
       Connection conn=conexion.ConnectDB();
        try {
            Statement st=(Statement)conn.createStatement();
            ResultSet resul=st.executeQuery(sql);
            while(resul.next()){
                datos[0]=resul.getString("idAdministrador");
                datos[1]=resul.getString("Nombre");
                datos[2]=resul.getString("Usuario");
                datos[3]=resul.getString("Password");
                defaultTableModel.addRow(datos);
            }
            tabla.setModel(defaultTableModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void MostrarSalidasGuardadas(JTable tabla, String id){
       try{
       DefaultTableModel defaultTableModel;
       String[]tit={"Registro","Codigo","Descripcion","Marca","Precio","Salida","FechaSalida"};
       String sql="select GuardarSalidas.idguardarsalidas as Registro,Producto.Barras as "
               + "Codigo,Producto.Descripcion,Marca.Nombre as Marca,Producto.Precio,GuardarSalidas.Cantidad "
               + "as Salida,GuardarSalidas.Fecha as FechaSalida from Producto inner join Marca on "
               + "producto.idmarca=marca.idmarca inner "
               + "join GuardarSalidas on GuardarSalidas.idProducto=Producto.idproducto where idtecnico="+id+";";
       String[] datos=new String[50];
       defaultTableModel=new DefaultTableModel(null,tit);
        Connection conn=conexion.ConnectDB();
        try {
            Statement st=(Statement)conn.createStatement();
            ResultSet resul=st.executeQuery(sql);
            while(resul.next()){
                datos[0]=resul.getString("Registro");
                datos[1]=resul.getString("Codigo");
                datos[2]=resul.getString("Descripcion");
                datos[3]=resul.getString("Marca");
                datos[4]=resul.getString("Precio");
                datos[5]=resul.getString("Salida");
                datos[6]=resul.getString("FechaSalida");
                defaultTableModel.addRow(datos);
            }
            tabla.setModel(defaultTableModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
       public void MostrarSalidas(JTable tabla, String id){
       try{
       Querys querys=new Querys();
       DefaultTableModel defaultTableModel;
       String[]tit={"Registro","Codigo","Descripcion","Salida","Stock","Fecha","Productos_sin_Justificar"};
       String sql="select SalidaProductoTecnico.idSalidaProductoTecnico as Registro,producto.barras as "
               + "Codigo,producto.Descripcion,SalidaProductoTecnico.Cantidad as Salida,Producto.Stock,"
               + "SalidaProductoTecnico.Fecha,SalidaProductoTecnico.Operacion as Productos_sin_Justificar "
               + "from producto inner join SalidaProductoTecnico on "
               + "producto.idproducto=SalidaProductoTecnico.idproducto where idtecnico="+id+";";
       String[] datos=new String[50];
       defaultTableModel=new DefaultTableModel(null,tit);
        Connection conn=conexion.ConnectDB();
        try {
            Statement st=(Statement)conn.createStatement();
            ResultSet resul=st.executeQuery(sql);
            while(resul.next()){
                datos[0]=resul.getString("Registro");
                datos[1]=resul.getString("Codigo");
                datos[2]=resul.getString("Descripcion");
                datos[3]=resul.getString("Salida");
                datos[4]=resul.getString("Stock");
                datos[5]=resul.getString("Fecha");
                datos[6]=resul.getString("Productos_sin_Justificar");
                defaultTableModel.addRow(datos);
            }
            tabla.setModel(defaultTableModel);
            int numerofilas=Integer.parseInt(querys.getFilasfromSalidaProductoTecnico())-1;
            for (int j = tabla.getRowCount()-1; j >=0; j--) {
             String fila=String.valueOf(tabla.getValueAt(j,6));
             int fila2=Integer.parseInt(fila);
             if(fila2==0){
             DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
             modelo.removeRow(j);                        
            }
             }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
       public void MostrarJustificantes(JTable tabla){
       try{
       Querys querys=new Querys();
       DefaultTableModel defaultTableModel;
       String[]tit={"Descripcion","Codigo","Salida","Falta_Justificar","Cantidad_Justificada","Fecha","Justificante","Tecnico"};
       String sql="select producto.Descripcion,producto.Barras as Codigo,SalidaProductoTecnico.Cantidad as "
               + "Salida,SalidaProductoTecnico.Operacion as Falta_Justificar,RegresarProducto.Cantidad as "
               + "Cantidad_Justificada,RegresarProducto.Fecha,RegresarProducto.Justificante,Tecnico.Nombre as "
               + "Tecnico from Producto inner join SalidaProductoTecnico on "
               + "Producto.idproducto=SalidaProductoTecnico.idproducto inner join Tecnico on "
               + "tecnico.idtecnico=SalidaProductoTecnico.idtecnico inner join RegresarProducto on "
               + "RegresarProducto.idSalidaProductoTecnico=SalidaProductoTecnico.idSalidaProductoTecnico;";
       String[] datos=new String[50];
       defaultTableModel=new DefaultTableModel(null,tit);
        Connection conn=conexion.ConnectDB();
        try {
            Statement st=(Statement)conn.createStatement();
            ResultSet resul=st.executeQuery(sql);
            while(resul.next()){
                datos[0]=resul.getString("Descripcion");
                datos[1]=resul.getString("Codigo");
                datos[2]=resul.getString("Salida");
                datos[3]=resul.getString("Falta_Justificar");
                datos[4]=resul.getString("Cantidad_Justificada");
                datos[5]=resul.getString("Fecha");
                datos[6]=resul.getString("Justificante");
                datos[7]=resul.getString("Tecnico");
                defaultTableModel.addRow(datos);
            }
            tabla.setModel(defaultTableModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
