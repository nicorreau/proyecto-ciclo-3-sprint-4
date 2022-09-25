package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gson.Gson;
import beans.Usuario;
import connection.DBConnection1;
import java.util.HashMap;
import java.util.Map;


public class UsuarioController implements IUsuarioController{
    
    @Override
    public String login(String username, String contrasena){
        
        Gson gson = new Gson();
        DBConnection1 con = new DBConnection1();
        String sql = "SELECT * FROM usuario WHERE username= '"+username
                +"' and contrasena ='" + contrasena +"'";
        try {
           Statement st = con.getConnection().createStatement();
           ResultSet  rs = st.executeQuery(sql);
           while(rs.next()){
               String nombre = rs.getString("nombre");
               String apellidos = rs.getString("apellidos");
               String email = rs.getString("email");
               double saldo = rs.getDouble("saldo");
               boolean premium = rs.getBoolean("premium");
              Usuario usuario = new Usuario(username, contrasena, nombre, apellidos, email, saldo, premium);
              
              return gson.toJson(usuario);
           }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            con.desconectar();
        }
        return "false";
    }
    
    @Override
    public String register(String username, String contrasena, String nombre, String apellidos, String email,
            double saldo, boolean premium) {

        Gson gson = new Gson();

        DBConnection1 con = new DBConnection1();
        String sql = "Insert into usuario values('" + username + "', '" + contrasena + "', '" + nombre
                + "', '" + apellidos + "', '" + email + "', " + saldo + ", " + premium + ")";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            Usuario usuario = new Usuario(username, contrasena, nombre, apellidos, email, saldo, premium);

            st.close();

            return gson.toJson(usuario);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        } finally {
            con.desconectar();
        }

        return "false";

    }
    
       @Override
    public String pedir(String username) {

        Gson gson = new Gson();

        DBConnection1 con = new DBConnection1();
        String sql = "Select * from usuario where username = '" + username + "'";

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String contrasena = rs.getString("contrasena");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                double saldo = rs.getDouble("saldo");
                boolean premium = rs.getBoolean("premium");

                Usuario usuario = new Usuario(username, contrasena,
                        nombre, apellidos, email, saldo, premium);

                return gson.toJson(usuario);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }
    
        @Override
    public String modificar(String username, String nuevaContrasena,
            String nuevoNombre, String nuevosApellidos,
            String nuevoEmail, double nuevoSaldo, boolean nuevoPremium) {

        DBConnection1 con = new DBConnection1();

        String sql = "Update usuario set contrasena = '" + nuevaContrasena
                + "', nombre = '" + nuevoNombre + "', "
                + "apellidos = '" + nuevosApellidos + "', email = '"
                + nuevoEmail + "', saldo = " + nuevoSaldo + ", premium = ";

        if (nuevoPremium == true) {
            sql += " 1 ";
        } else {
            sql += " 0 ";
        }

        sql += " where username = '" + username + "'";

        try {

            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";

    }
    
        @Override
    public String verDisponibles(String username) {

        DBConnection1 con = new DBConnection1();
        String sql = "Select id,count(*) as num_disponibles from alquiler where username = '"
                + username + "' group by id;";

        Map<Integer, Integer> disponibles = new HashMap<Integer, Integer>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int num_disponibles = rs.getInt("num_disponibles");

                disponibles.put(id, num_disponibles);
            }

            devolverMotocicletas(username, disponibles);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";

    }

    @Override
    public String devolverMotocicletas(String username, Map<Integer, Integer> disponibles) {

        DBConnection1 con = new DBConnection1();

        try {
            for (Map.Entry<Integer, Integer> motocicleta : disponibles.entrySet()) {
                int id = motocicleta.getKey();
                int num_disponibles = motocicleta.getValue();

                String sql = "Update motocicleta set disponibles = (Select disponibles + " + num_disponibles
                        + " from motocicleta where id = " + id + ") where id = " + id;

                Statement st = con.getConnection().createStatement();
                st.executeUpdate(sql);

            }

            this.eliminar(username);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return "false";
    }

    @Override
    public String eliminar(String username) {

        DBConnection1 con = new DBConnection1();

        String sql1 = "Delete from alquiler where username = '" + username + "'";
        String sql2 = "Delete from usuario where username = '" + username + "'";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql1);
            st.executeUpdate(sql2);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }
    
     @Override
    public String restarDinero(String username, double nuevoSaldo) {

        DBConnection1 con = new DBConnection1();
        String sql = "Update usuario set saldo = " + nuevoSaldo + " where username = '" + username + "'";

        try {

            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }
}


