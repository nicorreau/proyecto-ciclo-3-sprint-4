package beans;

public class Motocicletas {
    
    private int id;
    private String marca;
    private String cilindraje;
    private String modelo;
    private int disponibles;
    private boolean novedad;

    public Motocicletas(int id, String marca, String cilindraje, String modelo, int disponibles, boolean novedad) {
        this.id = id;
        this.marca = marca;
        this.cilindraje = cilindraje;
        this.modelo = modelo;
        this.disponibles = disponibles;
        this.novedad = novedad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(String cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public boolean isNovedad() {
        return novedad;
    }

    public void setNovedad(boolean novedad) {
        this.novedad = novedad;
    }

    @Override
    public String toString() {
        return "Motocicletas{" + "id=" + id + ", marca=" + marca + ", cilindraje=" + cilindraje + ", modelo=" + modelo + ", disponibles=" + disponibles + ", novedad=" + novedad + '}';
    }
    
    
    
}
