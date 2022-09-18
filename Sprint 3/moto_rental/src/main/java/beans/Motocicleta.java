/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author Nestor
 */
public class Motocicleta {
    private int id;
    private String marca;
    private String cilindraje;
    private String modelo;
    private String categoria;
    private boolean exclusiva;
    private boolean novedad;

    public Motocicleta(int id, String marca, String cilindraje, String modelo, String categoria, boolean exclusiva, boolean novedad) {
        this.id = id;
        this.marca = marca;
        this.cilindraje = cilindraje;
        this.modelo = modelo;
        this.categoria = categoria;
        this.exclusiva = exclusiva;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isExclusiva() {
        return exclusiva;
    }

    public void setExclusiva(boolean exclusiva) {
        this.exclusiva = exclusiva;
    }

    public boolean isNovedad() {
        return novedad;
    }

    public void setNovedad(boolean novedad) {
        this.novedad = novedad;
    }

    @Override
    public String toString() {
        return "Motocicleta{" + "id=" + id + ", marca=" + marca + ", cilindraje=" + cilindraje + ", modelo=" + modelo + ", categoria=" + categoria + ", exclusiva=" + exclusiva + ", novedad=" + novedad + '}';
    }

    

}