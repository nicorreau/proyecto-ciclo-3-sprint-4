package controller;

import java.util.Map;

public interface IUsuarioController {
     
    public String login(String username, String contrasena);
    
    public String register(String username, String contrasena, String nombre, String apellidos, String email, double saldo, boolean premium);
        
      
    public String pedir(String username);
    
    public String modificar(String username, String nuevaContraseña, String nuevoNombre,
            String nuevosApellidos, String nuevoEmail, double nuevosaldo, boolean nuevoPremium);
    
     public String verDisponibles(String username);

    public String devolverMotocicletas(String username, Map<Integer, Integer> disponibles);

    public String eliminar(String username);

    public String restarDinero(String username, double nuevoSaldo);
    
    }
