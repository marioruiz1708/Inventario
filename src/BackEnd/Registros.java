/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import com.toedter.calendar.JDateChooser;
import FrontEnd.ProductosFrame;
import java.awt.Label;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Uriel RD
 */
public class Registros {
    ProductosFrame productosFrame=new ProductosFrame();
    
     public void GuardarSalidas
      (String idsalida,String txtidproducto,String idTecnico,JTextField cantidad,JTextField fecha,JTextField nombre,
              JTextField codeprod,JTextArea comentario,
              JTable tabla){
       try{
           Querys querys=new Querys();
           Updates updates=new Updates();
            Conexion conexion=new Conexion();
            Connection registrar=conexion.ConnectDB();
            String id,idtecnico,cantidad2,fecha2,sql,sql2;
            cantidad2=cantidad.getText();
            int cantidad3=Integer.parseInt(cantidad2);
            fecha2=fecha.getText();
            int id2=Integer.parseInt(idsalida);
            int id3=Integer.parseInt(idTecnico);
            int id4=Integer.parseInt(txtidproducto);
            String stock=querys.getStockProductobyID(id4);
            int stock2=Integer.parseInt(stock);
            if(cantidad2.equals("0") || cantidad3>stock2 || cantidad3<0){
                JOptionPane.showMessageDialog(null,"ERROR AL INTRODUCIR LA CANTIDAD NO PUEDE SER MAYOR A LO QUE HAY EN STOCK");
            }else if(idsalida.isEmpty()||txtidproducto.isEmpty()||cantidad.getText().isEmpty()||fecha.getText().isEmpty()||
                    nombre.getText().isEmpty()||codeprod.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Error, Tienes Campos Vacios");
            }else{
            sql="Insert into GuardarSalidas values(?,?,?,?,?,?)";
                try {
                    PreparedStatement pst=registrar.prepareStatement(sql);
                    pst.setInt(1, id2);
                    pst.setInt(2, cantidad3);
                    pst.setInt(3, id3);
                    pst.setInt(4,id4);
                    pst.setString(5, fecha2);
                    pst.setString(6, comentario.getText());
                    //            medico_Especialidad.RegistrarMedico_Especialidad(txtid.getText(), idespecialidad);
                    int n=pst.executeUpdate();
                    if(n>0){
                        JOptionPane.showMessageDialog(null, "Se Registro con Exito");
                        cantidad.setText(null);
                        nombre.setText(null);
                        codeprod.setText(null);
                        comentario.setText(null);
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al registrar");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al registrar " +e);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e + "Error");
    }
}
      public void RegistrarProducto
      (JTextField descripcion,JTextField Precio,JTextField Barras,JComboBox Status,JTextField inicial,
              JTextField Stock,JTextField Entrada,JDateChooser fechaentrada,JComboBox Marca,JTable tabla,
              JLabel labelcode){
       try{
           Querys querys=new Querys();
           Mostrar mostrar=new Mostrar();
            Conexion conexion=new Conexion();
            Connection registrar=conexion.ConnectDB();
            String id,sql;
            id="0";
            int id2=Integer.parseInt(id);
            String idmarca=querys.getIDbyNameMarca((String)Marca.getSelectedItem());
            int idmarca2=Integer.parseInt(idmarca);
            String idstatus=querys.getIDbyNameStatus((String)Status.getSelectedItem());
            int idstatus2=Integer.parseInt(idstatus);
            DateFormat f=new SimpleDateFormat("dd/MM/yyyy");
            String fechaentrada2=f.format(fechaentrada.getDate());
            if(querys.RepetirNombreProducto(descripcion.getText())==true){
                JOptionPane.showMessageDialog(null,"El Producto ya Existe");
            }else if(querys.RepetirBarras(Barras.getText())==true){
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
            sql="Insert into producto values(?,?,?,?,?,?,?,?,?,?)";
                try {
                    PreparedStatement pst=registrar.prepareStatement(sql);
                    pst.setInt(1, id2);
                    pst.setString(2, descripcion.getText().toUpperCase());
                    pst.setDouble(3,Double.parseDouble(Precio.getText()));
                    pst.setString(4,Barras.getText());
                    pst.setInt(5,idstatus2);
                    pst.setInt(6,Integer.parseInt(inicial.getText()));
                    pst.setInt(7,Integer.parseInt(Stock.getText()));
                    pst.setInt(8,Integer.parseInt(Entrada.getText()));
                    pst.setString(9,fechaentrada2);
                    pst.setInt(10, idmarca2);
                    //            medico_Especialidad.RegistrarMedico_Especialidad(txtid.getText(), idespecialidad);
                    int n=pst.executeUpdate();
                    if(n>0){
                        JOptionPane.showMessageDialog(null, "Se Registro con Exito");
                        descripcion.setText(null);
                        Precio.setText(null);
                        Barras.setText(null);
                        Status.setSelectedIndex(0);
                        inicial.setText("0");
                        Stock.setText(null);
                        Entrada.setText(null);
                        Marca.setSelectedIndex(0);
                        labelcode.setIcon(null);
                        mostrar.MostrarProductos(tabla);
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al registrar");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al registrar " +e);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e + "Error");
    }
}
      public void RegistrarMarca(JTextField id,JTextField marca,JTable tabla,JComboBox combomarca){
       try{
           Querys querys=new Querys();
            Conexion conexion=new Conexion();
            Mostrar mostrar=new Mostrar();
            Connection registrar=conexion.ConnectDB();
            String sql;
            int id2=Integer.parseInt(id.getText());
            if(querys.RepetirNombreProducto(marca.getText())==true){
                JOptionPane.showMessageDialog(null,"Esa marca ya existe");
            }else if(marca.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Campo vacio");
            }else{
            sql="Insert into Marca values(?,?)";
                try {
                    PreparedStatement pst=registrar.prepareStatement(sql);
                    pst.setInt(1, id2);
                    pst.setString(2, marca.getText());
                    int n=pst.executeUpdate();
                    if(n>0){
                        JOptionPane.showMessageDialog(null, "Se Registro con Exito");
                        marca.setText(null);
                        id.setText("0");
                        mostrar.MostrarMarca(tabla);
                        combomarca.removeAllItems();
                        combomarca.addItem("Seleccione una Marca");
                        productosFrame.LlenarComboMarca(combomarca);
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al registrar");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al registrar " +e);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e + "Error");
    }
}
      public void RegistrarStatus(JTextField id,JTextField status,JTable tabla,JComboBox combostatus){
       try{
           Querys querys=new Querys();
            Conexion conexion=new Conexion();
            Mostrar mostrar=new Mostrar();
            Connection registrar=conexion.ConnectDB();
            String sql;
            int id2=Integer.parseInt(id.getText());
            if(querys.RepetirNombreStatus(status.getText())==true){
                JOptionPane.showMessageDialog(null,"Este dato ya existe");
            }else if(status.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Campo vacio");
            }else{
            sql="Insert into Status values(?,?)";
                try {
                    PreparedStatement pst=registrar.prepareStatement(sql);
                    pst.setInt(1, id2);
                    pst.setString(2, status.getText());
                    int n=pst.executeUpdate();
                    if(n>0){
                        JOptionPane.showMessageDialog(null, "Se Registro con Exito");
                        status.setText(null);
                        id.setText("0");
                        mostrar.MostrarStatus(tabla);
                        combostatus.removeAllItems();
                        combostatus.addItem("Seleccione un Status");
                        productosFrame.LlenarComboStatus(combostatus);
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al registrar");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al registrar " +e);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e + "Error");
    }
     }
       public void RegistrarTecnico(JTextField idtecnico,JTextField nombretecnico,JTextField usuario,JTextField password,
       JTable tabla){
       try{
           Querys querys=new Querys();
            Conexion conexion=new Conexion();
            Mostrar mostrar=new Mostrar();
            Connection registrar=conexion.ConnectDB();
            String sql;
            int id=Integer.parseInt(idtecnico.getText());
            if(querys.RepetirUsuarioTecnico(usuario.getText())==true){
                JOptionPane.showMessageDialog(null,"El Usuario ya Existe");
            }else if(usuario.getText().isEmpty() || nombretecnico.getText().isEmpty() || password.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Campos vacios");
            }else{
            sql="Insert into Tecnico values(?,?,?,?)";
                try {
                    PreparedStatement pst=registrar.prepareStatement(sql);
                    pst.setInt(1, id);
                    pst.setString(2,nombretecnico.getText());
                    pst.setString(3, password.getText());
                    pst.setString(4,usuario.getText());
                    int n=pst.executeUpdate();
                    if(n>0){
                        JOptionPane.showMessageDialog(null, "Se Registro con Exito");
                        idtecnico.setText("0");
                        nombretecnico.setText(null);
                        usuario.setText(null);
                        password.setText(null);
                        mostrar.MostrarTecnico(tabla);
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al registrar");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al registrar " +e);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e + "Error");
    }
}
       public void RegistrarAdmin(JTextField idadmin,JTextField nombretecnico,JTextField usuario,JTextField password,
       JTable tabla){
       try{
           Querys querys=new Querys();
            Conexion conexion=new Conexion();
            Mostrar mostrar=new Mostrar();
            Connection registrar=conexion.ConnectDB();
            String sql;
            int id=Integer.parseInt(idadmin.getText());
            if(querys.RepetirUsuarioAdmin(usuario.getText())==true){
                JOptionPane.showMessageDialog(null,"El Usuario ya Existe");
            }else if(usuario.getText().isEmpty() || nombretecnico.getText().isEmpty() || password.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Campos vacios");
            }else{
            sql="Insert into Administrador values(?,?,?,?)";
                try {
                    PreparedStatement pst=registrar.prepareStatement(sql);
                    pst.setInt(1, id);
                    pst.setString(2,nombretecnico.getText());
                    pst.setString(3, usuario.getText());
                    pst.setString(4,password.getText());
                    int n=pst.executeUpdate();
                    if(n>0){
                        JOptionPane.showMessageDialog(null, "Se Registro con Exito");
                        idadmin.setText("0");
                        nombretecnico.setText(null);
                        usuario.setText(null);
                        password.setText(null);
                        mostrar.MostrarAdmin(tabla);
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al registrar");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al registrar " +e);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e + "Error");
    }
   }
     public void RegistrarSalidaTecnico
      (String idsalida,String idregistro,String txtidproducto,String idTecnico,String cantidad,String fecha,String comentario,
              JTable tabla){
       try{
           Querys querys=new Querys();
           Updates updates=new Updates();
            Conexion conexion=new Conexion();
            Connection registrar=conexion.ConnectDB();
            String id,idtecnico,cantidad2,fecha2,sql,sql2;
            cantidad2=cantidad;
            int cantidad3=Integer.parseInt(cantidad2);
            fecha2=fecha;
            int id2=Integer.parseInt(idsalida);
            int id3=Integer.parseInt(idTecnico);
            int id4=Integer.parseInt(txtidproducto);
            String stock=querys.getStockProductobyID(id4);
            int stock2=Integer.parseInt(stock);
            sql="Insert into SalidaProductoTecnico values(?,?,?,?,?,?,?)";
                try {
                    PreparedStatement pst=registrar.prepareStatement(sql);
                    pst.setInt(1, id2);
                    pst.setInt(2, cantidad3);
                    pst.setInt(3, id3);
                    pst.setInt(4,id4);
                    pst.setString(5, fecha2);
                    pst.setString(6, comentario);
                    pst.setInt(7, cantidad3);
                    int n=pst.executeUpdate();
                    if(n>0){
                        JOptionPane.showMessageDialog(null, "Registrado");
                        updates.ActualizarStockBYProductoTecnico(cantidad3, id4, stock2,tabla);  
                        Deletes deletes= new Deletes();
                        deletes.EliminarSalidasGuardadas(idregistro, tabla, idTecnico);
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al registrar");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al registrar " +e);
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e + "Error");
    }
}
       public void RegistrarRegresarProducto(String idsalidaproducto,JComboBox Justificante,JTextField Cantidad,
               JTextField Fecha,JTextField producto,JTable jTable1,JTable jTable2,JLabel labelregistro){
       try{
            Querys querys=new Querys();
            Conexion conexion=new Conexion();
            Mostrar mostrar=new Mostrar();
            Connection registrar=conexion.ConnectDB();
            String sql;
            int idregistro2=0;
            int totalintroducido=Integer.parseInt(Cantidad.getText());
            String Cantidad1=querys.getOpearcionbySalidas(idsalidaproducto);
            int cantidad2=Integer.parseInt(Cantidad1);
            if(totalintroducido==0 || totalintroducido<0){
                JOptionPane.showMessageDialog(null,"Error al introducir la cantidad del producto");
            }else if(cantidad2<totalintroducido){
                JOptionPane.showMessageDialog(null,"Error, La Cantidad Introducida no puede ser Mayor");
            }else if(idsalidaproducto.equals("0") || producto.getText().isEmpty() || Cantidad.getText().isEmpty()){
                 JOptionPane.showMessageDialog(null,"Error, Campos Vacios");
            }else{
            sql="Insert into RegresarProducto values(?,?,?,?,?)";
                try {
                    PreparedStatement pst=registrar.prepareStatement(sql);
                    pst.setInt(1, idregistro2);
                    pst.setInt(2,Integer.parseInt(idsalidaproducto));
                    pst.setString(3,(String)Justificante.getSelectedItem());
                    pst.setInt(4,totalintroducido);
                    pst.setString(5,Fecha.getText());
                    int n=pst.executeUpdate();
                    if(n>0){
                        JOptionPane.showMessageDialog(null, "Se Registro con Exito");
                        int op=cantidad2-totalintroducido;
                        try {
                         String sql2="Update SalidaProductoTecnico set Operacion="+op+" where idSalidaProductoTecnico="+idsalidaproducto+";";
                          PreparedStatement pst2=registrar.prepareStatement(sql2);
                          int n2=pst2.executeUpdate();
                            if(n2>0){
                
                            }else{
                               JOptionPane.showMessageDialog(null, "Error");
                            }
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Error al registrar " + e);
                        }
                        if(Justificante.getSelectedItem().toString().equals("Regresar")){
                         try {
                         int idproducto=Integer.parseInt(querys.getIDProductobyIDSalida(idsalidaproducto));
                         int stock=Integer.parseInt(querys.getStockProductobyID(idproducto));
                         String idtecnico=querys.getIDTecnicobyIDSalida(idsalidaproducto);
                         int suma=stock+totalintroducido;
                         String sql2="Update Producto set Stock="+suma+" where idProducto="+idproducto+";";
                          PreparedStatement pst2=registrar.prepareStatement(sql2);
                          int n2=pst2.executeUpdate();
                            if(n2>0){
                               mostrar.MostrarProductoTecnico(jTable1);
                               mostrar.MostrarSalidas(jTable2,idtecnico);
                            }else{
                               JOptionPane.showMessageDialog(null, "Error al Sumar Stock");
                            }
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Error al registrar " + e);
                        }   
                        }
                        System.out.println(Integer.parseInt(querys.getFilasfromSalidaProductoTecnico()));
                              int i=0;
                              int numerofilas=Integer.parseInt(querys.getFilasfromSalidaProductoTecnico())-1;
                              for (int j = jTable2.getRowCount()-1; j >=0; j--) {
                                  String fila=String.valueOf(jTable2.getValueAt(j,6));
                                  int fila2=Integer.parseInt(fila);
                                    if(fila2==0){
                                    DefaultTableModel modelo=(DefaultTableModel)jTable2.getModel();
                                    modelo.removeRow(j);
                                     
                                    }
                                }
//                            for (int i = 0; i <Integer.parseInt(querys.getFilasfromSalidaProductoTecnico()); i++){
                        String idtecnico=querys.getIDTecnicobyIDSalida(idsalidaproducto);
                         mostrar.MostrarProductoTecnico(jTable1);
                         mostrar.MostrarSalidas(jTable2,idtecnico);
                        producto.setText(null);
                        Justificante.setSelectedIndex(0);
                        Cantidad.setText(null);
                        labelregistro.setText("0");
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al registrar");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al registrar " +e);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e + "Error");
    }
}
}
