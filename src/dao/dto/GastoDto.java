package dao.dto;

import entities.Categoria;

public class GastoDto {
    private int id;
    private Categoria categoria;
    private double valor;
    private String fecha;
    private String descripcion;//el profe no tiene esto


    GastoDto(){
    }


    public GastoDto( Categoria categoria,String descripcion, double valor,  String fecha) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
