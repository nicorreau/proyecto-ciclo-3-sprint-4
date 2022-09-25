package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import beans.Motocicletas;
import connection.DBConnection1;

public class MotocicletaController implements IMotocicletaController {

    @Override
    public String listar(boolean ordenar, String orden) {

        Gson gson = new Gson();

        DBConnection1 con = new DBConnection1();
        String sql = "Select * from motocicleta";

        if (ordenar == true) {
            sql += " order by genero " + orden;
        }

        List<String> motocicletas = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String marca = rs.getString("marca");
                String cilindraje = rs.getString("cilindraje");
                String modelo = rs.getString("modelo");
                int disponibles = rs.getInt("disponibles");
                boolean novedad = rs.getBoolean("novedad");
                Motocicletas motocicleta = new Motocicletas(id, marca, cilindraje, modelo, disponibles, novedad);

                motocicletas.add(gson.toJson(motocicleta));

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return gson.toJson(motocicletas);

    }

    @Override
    public String devolver(int id, String username) {

        DBConnection1 con = new DBConnection1();
        String sql = "Delete from alquiler where id= " + id + " and username = '" 
                + username + "' limit 1";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeQuery(sql);

            this.sumarCantidad(id);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    
       @Override
    public String sumarCantidad(int id) {

        DBConnection1 con = new DBConnection1();

        String sql = "Update motocicleta set disponibles = (Select disponibles from motocicleta where id = " 
                + id + ") + 1 where id = " + id;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }
    
    
    @Override
    public String alquilar(int id, String username) {

        Timestamp fecha = new Timestamp(new Date().getTime());
        DBConnection1 con = new DBConnection1();
        String sql = "Insert into alquiler values ('" + id + "', '" + username + "', '" + fecha + "')";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            String modificar = modificar(id);

            if (modificar.equals("true")) {
                return "true";
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }
        return "false";
    }

     @Override
    public String modificar(int id) {

        DBConnection1 con = new DBConnection1();
        String sql = "Update motocicleta set disponibles = (disponibles - 1) where id = " + id;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }
}
