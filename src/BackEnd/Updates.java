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
public class Updates {
    Mostrar mostrar=new Mostrar();
    Querys querys=new Querys();
    
    public void ActualizarStockBYProductoTecnico(int cantidad,int txtidproducto,int Stock,JTable tabla){
        Conexion conexion=new Conexion();
        Connection conn=conexion.ConnectDB();
        String nombre,sql;
        int resta=Stock-cantidad;
        try{    
        sql="Update producto set stock="+resta+" where idproducto="+txtidproducto+";";   
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if(n>0){
                
            }else{
                JOptionPane.showMessageDialog(null, "Error al actualizar");
            }
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, "Error al registrar " + ex);
        }
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
     }
    public void ActualizarProductoEntrada(JLabel id,JTextField descripcion,JTextField Precio,JTextField Barras,JComboBox Status,
            JTextField inicial,JTextField Stock,JTextField Entrada,JDateChooser fechaentrada,JComboBox Marca,JTable tabla,
              JLabel labelcode){
        Conexion conexion=new Conexion();
        Connection conn=conexion.ConnectDB();
        String sql;
        DateFormat f=new SimpleDateFormat("dd/MM/yyyy");
        String fechaentrada2=f.format(fechaentrada.getDate());
        String idstatus=querys.getIDbyNameStatus((String)Status.getSelectedItem());
        String idmarca=querys.getIDbyNameMarca((String)Marca.getSelectedItem());
        try{    
        sql="Update producto set Descripcion='"+descripcion.getText()+"',precio="+Precio.getText()+
                ",barras='"+Barras.getText()+"',idstatus="+idstatus+",inicial="+inicial.getText()+""
                + ",stock="+Stock.getText()+",entrada="+Entrada.getText()+",fechaentrada='"+fechaentrada2+""
                + "',idmarca="+idmarca+" where idproducto="+id.getText()+";";
        if((querys.RepetirNombreProducto(descripcion.getText())==true) && 
                !querys.getNombreProductobyID(id.getText()).equals(descripcion.getText())){
            JOptionPane.showMessageDialog(null,"El Producto ya Existe");
        }else if((querys.RepetirBarras(Barras.getText())==true)&&
                !querys.getBarrasbyID(id.getText()).equals(Barras.getText())){
            JOptionPane.showMessageDialog(null,"El Codigo de Barras ya existe");
        }else if(descripcion.getText().isEmpty() || Precio.getText().isEmpty() || Barras.getText().isEmpty()
                    || Status.getSelectedItem().equals("Seleccione un Status") || inicial.getText().isEmpty()
                    || Stock.getText().isEmpty() || Entrada.getText().isEmpty() || 
                    Marca.getSelectedItem().equals("Seleccione una Marca")){
            JOptionPane.showMessageDialog(null,"Hay Datos Vacios o Sin Seleccionar");
        }else if(labelcode.getIcon()==null){
                JOptionPane.showMessageDialog(null,"No ha Visualizado su codigo");
        }
        else{
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Se Actualizo con Exito");
                mostrar.MostrarProductos(tabla);
                id.setText("0");
                descripcion.setText(null);
                Precio.setText(null);
                Barras.setText(null);
                Status.setSelectedIndex(0);
                inicial.setText("0");
                Stock.setText(null);
                Entrada.setText(null);
                Marca.setSelectedIndex(0);
                labelcode.setIcon(null);
            }else{
                JOptionPane.showMessageDialog(null, "Error al actualizar");
            }
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, "Error al registrar aqui " + ex);
        }
        }
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,"Soy el eroor x2"+e);
        }
     }
    public void ActualizarMarca(JTextField idmarca,JTextField marca,JTable tabla,JComboBox combomarca,JTable tablaproducto){
        Conexion conexion=new Conexion();
        Connection conn=conexion.ConnectDB();
        ProductosFrame productosFrame=new ProductosFrame();
        String nombre,sql;
        try{    
        sql="Update marca set nombre='"+marca.getText()+"' where idmarca="+idmarca.getText()+";";
        if((querys.RepetirNombreMarca(marca.getText())==true) && 
                !(querys.getNombreMarcabyID(idmarca.getText()).equals(marca.getText()))){
            JOptionPane.showMessageDialog(null,"El Nombre de la Marca ya Existe");
        }else if(marca.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Error, Campo Vacio");
        }else{
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Se Actualizo con Exito");
                marca.setText(null);
                idmarca.setText("0");
                mostrar.MostrarMarca(tabla);
                mostrar.MostrarProductos(tablaproducto);
                combomarca.removeAllItems();
                combomarca.addItem("Seleccione una Marca");
                productosFrame.LlenarComboMarca(combomarca);
            }else{
                JOptionPane.showMessageDialog(null, "Error al actualizar");
                idmarca.setText("0");
                marca.setText(null);
            }
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, "Error al registrar aqui " + ex);
            idmarca.setText("0");
            marca.setText(null);
        }
        }
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,"Soy el error"+e);
        }
     }
    public void ActualizarStatus(JTextField idstatus,JTextField status,JTable tabla,JComboBox combostatus,JTable tableproducto){
        Conexion conexion=new Conexion();
        Connection conn=conexion.ConnectDB();
        ProductosFrame productosFrame=new ProductosFrame();
        String nombre,sql;
        try{    
        sql="Update Status set Status='"+status.getText()+"' where idstatus="+idstatus.getText()+";";
        if((querys.RepetirNombreStatus(status.getText())==true) && 
                !(querys.getNombreStatusbyID(idstatus.getText()).equals(status.getText()))){
            JOptionPane.showMessageDialog(null,"El Nombre ya Existe");
        }else if(status.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Error, Campo Vacio");
        }else{
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Se Actualizo con Exito");
                status.setText(null);
                idstatus.setText("0");
                mostrar.MostrarStatus(tabla);
                mostrar.MostrarProductos(tableproducto);
                combostatus.removeAllItems();
                combostatus.addItem("Seleccione un Status");
                productosFrame.LlenarComboStatus(combostatus);
            }else{
                JOptionPane.showMessageDialog(null, "Error al actualizar");
                idstatus.setText("0");
                status.setText(null);
            }
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, "Error al registrar aqui " + ex);
            idstatus.setText("0");
            status.setText(null);
        }
        }
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
     }
    public void ActualizarTecnico(JTextField idtecnico,JTextField nombretecnico,JTextField usuario,JTextField password,
       JTable tabla){
        Conexion conexion=new Conexion();
        Connection conn=conexion.ConnectDB();
        ProductosFrame productosFrame=new ProductosFrame();
        String nombre,sql;
        try{
        int id=Integer.parseInt(idtecnico.getText());
        sql="Update Tecnico set Nombre='"+nombretecnico.getText()+"',password='"+password.getText()+
                "',usuario='"+usuario.getText()+"' where idtecnico="+id+";";
        if((querys.RepetirUsuarioTecnico(usuario.getText())==true) && 
                !(querys.getUsuarioTecnicobyID(idtecnico.getText()).equals(usuario.getText()))){
            JOptionPane.showMessageDialog(null,"El Uusario ya Existe");
        }else if(nombretecnico.getText().isEmpty() || usuario.getText().isEmpty() || password.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Error, Campos Vacio");
        }else{
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Se Actualizo con Exito");
                idtecnico.setText("0");
                nombretecnico.setText(null);
                usuario.setText(null);
                password.setText(null);
                mostrar.MostrarTecnico(tabla);
            }else{
                JOptionPane.showMessageDialog(null, "Error al actualizar");
            }
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, "Error al registrar aqui " + ex);
        }
        }
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
     }
    public void ActualizarAdmin(JTextField idadmin,JTextField nombreadmin,JTextField usuario,JTextField password,
       JTable tabla){
        Conexion conexion=new Conexion();
        Connection conn=conexion.ConnectDB();
        ProductosFrame productosFrame=new ProductosFrame();
        String nombre,sql;
        try{
        int id=Integer.parseInt(idadmin.getText());
        sql="Update Administrador set Nombre='"+nombreadmin.getText()+"',password='"+password.getText()+
                "',usuario='"+usuario.getText()+"' where idadministrador="+id+";";
        if((querys.RepetirUsuarioAdmin(usuario.getText())==true) && 
                !(querys.getUsuarioAdminbyID(idadmin.getText()).equals(usuario.getText()))){
            JOptionPane.showMessageDialog(null,"El Uusario ya Existe");
        }else if(nombreadmin.getText().isEmpty() || usuario.getText().isEmpty() || password.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Error, Campos Vacio");
        }else{
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Se Actualizo con Exito");
                idadmin.setText("0");
                nombreadmin.setText(null);
                usuario.setText(null);
                password.setText(null);
                mostrar.MostrarAdmin(tabla);
            }else{
                JOptionPane.showMessageDialog(null, "Error al actualizar");
            }
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, "Error al registrar aqui " + ex);
        }
        }
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
     }
}

