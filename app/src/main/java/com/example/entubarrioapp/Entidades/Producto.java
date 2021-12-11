package com.example.entubarrioapp.Entidades;

public class Producto {
    /*private String nombreP;
    private int codigoP;
    private double precioP;

    public Producto() {
    }

    public Producto(String nombreP, int codigoP, double precioP) {
        this.nombreP = nombreP;
        this.codigoP = codigoP;
        this.precioP = precioP;
    }

     @Override
    public String toString() {
        return "     "+codigoP +"                   "+nombreP+"       "+ precioP;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public int getCodigoP() {
        return codigoP;
    }

    public void setCodigoP(int codigoP) {
        this.codigoP = codigoP;
    }

    public double getPrecioP() {
        return precioP;
    }

    public void setPrecioP(double precioP) {
        this.precioP = precioP;
    }*/

    private String codigoP;
    private String categoriaP;
    private String nombreP;
    private String descripcionP;
    private Float precioP;
    private int cantidadP;

    public Producto() {
    }

    public String getCodigoP() {
        return codigoP;
    }

    public void setCodigoP(String codigoP) {
        this.codigoP = codigoP;
    }

    public String getCategoriaP() {
        return categoriaP;
    }

    public void setCategoriaP(String categoriaP) {
        this.categoriaP = categoriaP;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public Float getPrecioP() {
        return precioP;
    }

    public void setPrecioP(Float precioP) {
        this.precioP = precioP;
    }

    public int getCantidadP() {
        return cantidadP;
    }

    public void setCantidadP(int cantidadP) {
        this.cantidadP = cantidadP;
    }
}
