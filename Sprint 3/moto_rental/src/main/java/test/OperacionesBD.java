/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import beans.Motocicleta;
import connection.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Nestor
 */
public class OperacionesBD {
    public static void main(String[]args){
        listarMotocicleta();
        //actualizarMotocicleta(2, "Suzuki Drx");
    
    }
    
    public static void actualizarMotocicleta(int id, String marca){
        DBConnection conn = new DBConnection();
        String sql = "UPDATE motocicleta SET marca = '" + marca + "' WHERE id = " + id;
        
        try {
            Statement st = conn.getConnection().createStatement();
            st.executeUpdate(sql);
       } catch (Exception ex){
            System.out.println(ex.getMessage());
       } finally {
            conn.desconectar();
       }
    }
    
     public static void listarMotocicleta(){
        DBConnection conn = new DBConnection();
        String sql = "SELECT * FROM motocicleta;";
        
        try {
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
               int id = rs.getInt("id");
               String marca = rs.getString ("marca");
               String cilindraje = rs.getString("cilindraje");
               String modelo = rs.getString("modelo");
               String categoria = rs.getString("categoria");
               boolean exclusiva = rs.getBoolean("exclusiva");
               boolean novedad = rs.getBoolean("novedad");
                                            
               Motocicleta motocicleta = new Motocicleta(id, marca, cilindraje, modelo, categoria, exclusiva, novedad);
                System.out.println(motocicleta.toString());
            }
            st.executeQuery(sql);
       } catch (Exception ex){
            System.out.println(ex.getMessage());
       } finally {
            conn.desconectar();
       }
    }
}
