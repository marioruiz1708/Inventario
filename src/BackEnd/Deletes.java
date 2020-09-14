/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import com.toedter.calendar.JDateChooser;
import FrontEnd.ProductosFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Uriel RD
 */
public class Deletes {
   Mostrar mostrar=new Mostrar();
   
    public void EliminarProducto(JLabel id,JTextField descripcion,JTextField Precio,JTextField Barras,JComboBox Status,JTextField inicial,
              JTextField Stock,JTextField Entrada,JComboBox Marca,JTable tabla,
              JLabel labelcode){
        Conexion conexion=new Conexion();
        Connection conn=conexion.ConnectDB();
        String sql;
        try{    
        sql="Delete from producto where idproducto="+id.getText()+";";
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Se ha Eliminado el Producto");
                id.setText("0");
                descripcion.setText(null);
                Precio.setText(null);
                Barras.setText(null);
                Status.setSelectedIndex(0);
                inicial.setText(null);
                Stock.setText(null);
                Entrada.setText("0");
                Marca.setSelectedIndex(0);
                labelcode.setIcon(null);
                mostrar.MostrarProductos(tabla);
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
            }
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, ex);
        }
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
     }
    public void EliminarMarca(JTextField id,JTextField nombremarca,JComboBox combomarca,JTable tablamarca,JTable tableproducto){
        Conexion conexion=new Conexion();
        Connection conn=conexion.ConnectDB();
        ProductosFrame productosFrame=new ProductosFrame();
        String sql;
        try{    
        sql="Delete from Marca where idmarca="+id.getText()+";";
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Se ha Eliminado el Producto");
                id.setText("0");
                nombremarca.setText(null);
                combomarca.removeAllItems();
                combomarca.addItem("Seleccione una Marca");
                productosFrame.LlenarComboMarca(combomarca);
                mostrar.MostrarMarca(tablamarca);
                mostrar.MostrarProductos(tableproducto);
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
            }
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, ex);
        }
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
     }
    public void EliminarStatus(JTextField id,JTextField nombrestatus,JComboBox combostatus,JTable tablestatus,JTable tableproducto){
        Conexion conexion=new Conexion();
        Connection conn=conexion.ConnectDB();
        ProductosFrame productosFrame=new ProductosFrame();
        String sql;
        try{    
        sql="Delete from Status where idstatus="+id.getText()+";";
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Se ha Eliminado el Status");
                id.setText("0");
                nombrestatus.setText(null);
                combostatus.removeAllItems();
                combostatus.addItem("Seleccione un Status");
                productosFrame.LlenarComboStatus(combostatus);
                mostrar.MostrarStatus(tablestatus);
                mostrar.MostrarProductos(tableproducto);
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
            }
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, ex);
        }
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
     }
    public void EliminarTecnico(JTextField idtecnico,JTextField nombretecnico,
            JTextField usuariotecnico,JTextField password,JTable tabletecnico,JTable tabletecnicosalida){
        Conexion conexion=new Conexion();
        Connection conn=conexion.ConnectDB();
        ProductosFrame productosFrame=new ProductosFrame();
        String sql;
        try{    
        sql="Delete from Tecnico where idtecnico="+idtecnico.getText()+";";
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Se ha Eliminado el Tecnico");
                idtecnico.setText("0");
                nombretecnico.setText(null);
                usuariotecnico.setText(null);
                password.setText(null);
                mostrar.MostrarTecnico(tabletecnico);
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
            }
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, ex);
        }
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
     }
    public void EliminarAdmin(JTextField idadmin,JTextField nombreadmin,
            JTextField usuarioadmin,JTextField password,JTable tableadmin){
        Conexion conexion=new Conexion();
        Connection conn=conexion.ConnectDB();
        ProductosFrame productosFrame=new ProductosFrame();
        String sql;
        try{    
        sql="Delete from Administrador where idadministrador="+idadmin.getText()+";";
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Se ha Eliminado el Administrador");
                idadmin.setText("0");
                nombreadmin.setText(null);
                usuarioadmin.setText(null);
                password.setText(null);
                mostrar.MostrarAdmin(tableadmin);
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
            }
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, ex);
        }
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
     }
    public void EliminarSalidasGuardadas(String id,JTable tableadmin,String idtecnico){
        Conexion conexion=new Conexion();
        Connection conn=conexion.ConnectDB();
        String sql;
        try{    
        sql="Delete from GuardarSalidas where idGuardarSalidas="+id+";";
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if(n>0){
                mostrar.MostrarSalidasGuardadas(tableadmin,idtecnico);
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
            }
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, ex);
        }
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
     }
}
