package test;

import beans.Motocicletas;
import connection.DBConnection1;
import java.sql.ResultSet;
import java.sql.Statement;

public class OperacionesBD1 {
    public static void main(String[] args) {
        listarMotocicleta();
        
    }
    
    public static void actualizarMotocicleta(int id, String cilindraje){
        DBConnection1 con = new DBConnection1();
        String sql = "UPDATE motocicleta SET cilindraje = " + "'"
                +cilindraje+"'WHERE id = "+id;
        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            con.desconectar();
        }
    }
    
   public static void listarMotocicleta(){
        DBConnection1 con = new DBConnection1();
        String sql = "SELECT * FROM motocicleta";
                
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String marca = rs.getString("marca");
                String cilindraje = rs.getString("cilindraje");
                String modelo = rs.getString("modelo");
                int disponibles = rs.getInt("disponibles");
                boolean novedad = rs.getBoolean("novedad");
                Motocicletas motocicletas = new Motocicletas(id, marca, cilindraje, modelo, 
                        disponibles, novedad);
                System.out.println(motocicletas.toString());
                
            }
            st.executeQuery(sql);
            
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally {
            con.desconectar();
        }
    }


    
}

