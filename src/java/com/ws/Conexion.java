/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diana
 */
public class Conexion {
    Connection conexion;
    Statement Sentencias;
    ResultSet Datos;
    PreparedStatement psPrepararaSentencia;
    
    public Conexion() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // String url= "jdbc:sqlserver://localhost:1433;databaseName=curso2;integratedSecurity=fals";
            String url= "jdbc:mysql://localhost:3306/prueba";
            conexion= DriverManager.getConnection(url, "root", "1234");
            //Sentencias = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Sentencias = conexion.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ResultSet Consultar1 (){
        try {
            Datos = Sentencias.executeQuery("select * from Usuarios");
           
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Datos;
    }
 
    public boolean insertUpdate(String nombre, int edad, String imagen){
        String sql="insert into usuario( nombre, edad, imagen) values(?,?,?)";
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
           
            pst.setString(1, nombre);
            pst.setInt(2, edad);
            pst.setString(3, imagen);
            
             int n = pst.executeUpdate();
            if (n>0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return false;
    }
    
//    public cls_usuarios ConsultaPorCedula(int id){
//        cls_usuarios usuario= new cls_usuarios();
//        try {
//            Datos= Sentencias.executeQuery("select * from Usuarios where idUsuario =" + id);
//            while(Datos.next()){
//                usuario.setNombre(Datos.getString("nombre"));
//                usuario.setId(Datos.getInt("idUsuario"));
//                usuario.setApellido(Datos.getString("apellido"));
//                usuario.setPassword(Datos.getString("password"));
//                usuario.setEstado(Datos.getInt("estado"));
//            }
//            
//  
//        } catch (SQLException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       return usuario; 
//    }
//    
//    public boolean insertUpdate(cls_usuarios usuario){
//        
//        
//        String sql="insert into usuarios( nombre, apellido, usuario,password, estado) values(?,?,?,?,?)";
//        try {
//            PreparedStatement pst = conexion.prepareStatement(sql);
//           
//            pst.setString(1, usuario.getNombre());
//            pst.setString(2, usuario.getApellido());
//            pst.setString(3, usuario.getUsuario());
//            pst.setString(4, usuario.getPassword());
//            pst.setInt(5, usuario.getEstado());
//            int n = pst.executeUpdate();
//            if (n>0){
//                return true;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//
//        return false;
//    }
//    
//    public ResultSet Consultar2(String valor){
//        try {
//            Datos= Sentencias.executeQuery("select * from usuarios where nombre ='"+ valor+"'");
//        } catch (SQLException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return Datos;
//    }
//    
//    public boolean update(cls_usuarios usuario){
//        String sql="update usuarios set nombre=?, apellido=? where idusuario="+usuario.getId();
//        try {
//            PreparedStatement psd= conexion.prepareStatement(sql);
//            psd.setString(1, usuario.getNombre());
//            psd.setString(2, usuario.getApellido());
//            int n = psd.executeUpdate();
//            if (n>0){
//                return true;
//            }else{
//                return false;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return true;
//    }
}
