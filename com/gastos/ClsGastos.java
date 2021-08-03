/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gastos;

import java.util.Scanner;

/**
 *
 * @author jeant
 */
public class ClsGastos {
    private Scanner t = new Scanner(System.in);
    private Double seguro;
    private Double flete;
    private Double aduana;
    private Double acarreo;
    private Double banco;
    
    public void recolectarInformacion(){
        System.out.println("Seguro: ");
        setSeguro(Double.valueOf(getT().nextLine()));
        System.out.println("Flete: ");
        setFlete(Double.valueOf(getT().nextLine()));
        System.out.println("Aduana: ");
        setAduana(Double.valueOf(getT().nextLine()));
        System.out.println("Acarreo: ");
        setAcarreo(Double.valueOf(getT().nextLine()));
        System.out.println("Banco: ");
        setBanco(Double.valueOf(getT().nextLine()));
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
     * @return the seguro
     */
    public Double getSeguro() {
        return seguro;
    }

    /**
     * @param seguro the seguro to set
     */
    public void setSeguro(Double seguro) {
        this.seguro = seguro;
    }

    /**
     * @return the flete
     */
    public Double getFlete() {
        return flete;
    }

    /**
     * @param flete the flete to set
     */
    public void setFlete(Double flete) {
        this.flete = flete;
    }

    /**
     * @return the aduana
     */
    public Double getAduana() {
        return aduana;
    }

    /**
     * @param aduana the aduana to set
     */
    public void setAduana(Double aduana) {
        this.aduana = aduana;
    }

    /**
     * @return the acarreo
     */
    public Double getAcarreo() {
        return acarreo;
    }

    /**
     * @param acarreo the acarreo to set
     */
    public void setAcarreo(Double acarreo) {
        this.acarreo = acarreo;
    }

    /**
     * @return the banco
     */
    public Double getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(Double banco) {
        this.banco = banco;
    }
}
