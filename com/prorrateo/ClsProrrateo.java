/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prorrateo;

import com.gastos.ClsGastos;
import com.producto.ClsProducto;

/**
 *
 * @author jeant
 */
public class ClsProrrateo {
    private static String[][] prorrateo = new String[1][1];
    private static Double[] gastos = new Double[1];
    private static Double[] pesoProductos = new Double[1];
    
    private static final int CANTIDAD = 0;
    private static final int DESCRIPCION = 1;
    private static final int VALOR = 2;
    private static final int GASTO_VALOR = 3;
    private static final int GASTO_PESO = 4;
    private static final int COSTO_UNIDAD = 5;
    private static final int COSTO_TOTAL = 6;
    private final int MAX_FILAS = 3;
    private final int MAX_COLUMNAS = 7;
    
    private int filaActual = 0;
    
    public ClsProrrateo(int filas){
        if (filas > MAX_FILAS){
            throw(new IllegalArgumentException());
        }
        else{
            prorrateo = new String[filas][MAX_COLUMNAS];
            pesoProductos = new Double[filas];
            gastos = new Double[5];
        }
    }
    
    public static void imprimirDecorado(){
        for (int x = 0; x < prorrateo.length; x++) { 
            System.out.print("|");
            for (int y = 0; y < prorrateo[x].length; y++) {
                System.out.print(prorrateo[x][y]);
                if (y != prorrateo[x].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println("|");
        }
    }
    
    public static Double valorTotal(){
        Double total = 0.0;
        for (int fila = 0; fila < prorrateo.length; fila++) {
            total += (Double.valueOf(prorrateo[fila][VALOR])*Double.valueOf(prorrateo[fila][CANTIDAD]));
        }
        return total;
    }
    
    public static Double totalGastoValor(){
        Double total = 0.0;
        for (int i = 0; i < gastos.length; i+=2) {
            total += gastos[i];
        }
        return total;
    }
    
    public static Double coeficienteGastos(){
        return totalGastoValor()/valorTotal();
    }
    
    public static void gastosValor(){
        Double coeficiente = coeficienteGastos();
        for (int i = 0; i < prorrateo.length; i++) {
            prorrateo[i][GASTO_VALOR] = (Double.valueOf(prorrateo[i][VALOR])*coeficiente) + "";
        }
    }
    
    public static Double pesoTotal(){
        Double total = 0.0;
        for (int fila = 0; fila < prorrateo.length; fila++) {
            total += (Double.valueOf(prorrateo[fila][CANTIDAD])*pesoProductos[fila]);
        }
        return total;
    }
    
    public static Double totalGastoPeso(){
        Double total = 0.0;
        for (int i = 1; i < gastos.length; i+=2) {
            total += Double.valueOf(gastos[i]);
        }
        return total;
    }
    
    public static Double coeficienteGastosPeso(){
        return totalGastoPeso()/pesoTotal();
    }
    
    public static void gastosPeso(){
        Double coeficiente = coeficienteGastosPeso();
        for (int fila = 0; fila < prorrateo.length; fila++) {
            prorrateo[fila][GASTO_PESO] = (pesoProductos[fila]*coeficiente) + "";
        }
    }
    
    public static void calculoCostoUnitario(){
        for (int fila = 0; fila < prorrateo.length; fila++) {
            prorrateo[fila][COSTO_UNIDAD] = (Double.valueOf(prorrateo[fila][VALOR])
                                            +Double.valueOf(prorrateo[fila][GASTO_VALOR])
                                            +Double.valueOf(prorrateo[fila][GASTO_PESO])) + "";
        }
    }
    
    public static void calculoCostoTotal(){
        for (int fila = 0; fila < prorrateo.length; fila++) {
            prorrateo[fila][COSTO_TOTAL] = (Double.valueOf(prorrateo[fila][COSTO_UNIDAD])
                                           *Double.valueOf(prorrateo[fila][CANTIDAD])) + "";
        }
    }
    
    public static Double totalCuadro(){
        Double total = 0.0;
        for (int fila = 0; fila < prorrateo.length; fila++) {
            total += Double.valueOf(prorrateo[fila][COSTO_TOTAL]);
        }
        return total;
    }
    
    public static Double granTotal(){
        return (valorTotal() + totalGastoValor() + totalGastoPeso());
    }
    
    public static String comprobación(){
        Double diferencia = Math.abs(totalCuadro() - granTotal());
        if (diferencia <= 0.20){
            return "El ejercicio se ha resuelto correctamenta.";
        }
        else {
            return "Hay un error en ejercicio, los totales no coinciden.";
        }
    }
    
    public String agregaVendedorMatriz(ClsProducto producto){
        if (filaActual >= MAX_FILAS){
            return "Limite de filas alcanzado.";
        }
        else{
            prorrateo[filaActual][DESCRIPCION] = producto.getDescripcion();
            prorrateo[filaActual][CANTIDAD] = producto.getCantidad() + "";
            prorrateo[filaActual][VALOR] = producto.getValor() + "";
            pesoProductos[filaActual] = producto.getPeso();
            filaActual++;
        }
        return "OK!";
    }
    
    public void agregaGastos(ClsGastos objGastos){
        gastos[0] = objGastos.getSeguro();
        gastos[1] = objGastos.getFlete();
        gastos[2] = objGastos.getAduana();
        gastos[3] = objGastos.getAcarreo();
        gastos[4] = objGastos.getBanco();
    }
}
