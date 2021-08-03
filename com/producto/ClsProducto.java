/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.producto;

import java.util.Scanner;

/**
 *
 * @author jeant
 */
public class ClsProducto {
    private Scanner t = new Scanner(System.in);
    private String descripcion;
    private int cantidad;
    private Double valor;
    private Double peso;
    
    public void recolectarInformacion(){
        System.out.println("Ingrese la descripcion: ");
        setDescripcion(getT().nextLine());
        System.out.println("Ingrese la cantidad: ");
        setCantidad(getT().nextInt());
        getT().nextLine();
        System.out.println("Ingrese el valor: ");
        setValor(Double.valueOf(getT().nextLine()));
        System.out.println("Ingrese el peso: ");
        setPeso(Double.valueOf(getT().nextLine()));
    }

    /**
     * @return the t
     */
    public Scanner getT() {
        return t;
    }

    /**
     * @param t the t to set
     */
    public void setT(Scanner t) {
        this.t = t;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * @return the peso
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    
}
