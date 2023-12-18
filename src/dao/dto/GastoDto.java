package dao.dto;

import entities.Categoria;

public class GastoDto {
    private int id;
    private Integer categoriaId;
    private double valor;
    private String fecha;
    private String descripcion;//el profe no tiene esto


    public GastoDto(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GastoDto(Integer categoriaId, String descripcion, double valor, String fecha) {
        this.categoriaId = categoriaId;
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

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

}
