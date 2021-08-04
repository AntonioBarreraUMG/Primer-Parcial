/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prorrateo;

import com.gastos.ClsGastos;
import com.producto.ClsProducto;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author jeant
 */
public class ClsProrrateo {
    private static String[][] prorrateo = new String[1][1];
    private static Double[] gastos = new Double[1];
    private static Double[] pesoProductos = new Double[1];
    public static NumberFormat formatoCantidad = NumberFormat.getCurrencyInstance(new Locale("es","GT"));
    
    private static final int CANTIDAD = 0;
    private static final int DESCRIPCION = 1;
    private static final int VALOR = 2;
    private static final int GASTO_VALOR = 3;
    private static final int GASTO_PESO = 4;
    private static final int COSTO_UNIDAD = 5;
    private static final int COSTO_TOTAL = 6;
    private final int MAX_COLUMNAS = 7;
    
    private int filaActual = 0;
    
    public ClsProrrateo(int filas){
        prorrateo = new String[filas][MAX_COLUMNAS];
        pesoProductos = new Double[filas];
        gastos = new Double[5];
    }
    
    public static String cambiarFormato(String cantidad){
        Double nuevaCantidad = Double.parseDouble(cantidad);
        return formatoCantidad.format(nuevaCantidad);
    }
    
    public static void imprimirDecorado(){
        System.out.println("\n--------------------------PRORRATEO DE FACTURA--------------------------");
        System.out.println("Cantidad Descripcion Valor Gasto_Valor Gasto_Peso Costo_Unidad Costo_Total");
        for (int x = 0; x < prorrateo.length; x++) { 
            System.out.print("|");
            for (int y = 0; y < prorrateo[x].length; y++) {
                if (y > 1){
                    System.out.print(cambiarFormato(prorrateo[x][y]));
                }
                else{
                    System.out.print(prorrateo[x][y]);
                }
                if (y != prorrateo[x].length - 1) {
                    System.out.print("\t\t");
                }
            }
            System.out.println("|");
        }
    }
    
    public static Double obtenerValorTotal(){
        Double total = 0.0;
        for (int fila = 0; fila < prorrateo.length; fila++) {
            total += (Double.valueOf(prorrateo[fila][VALOR])*Double.valueOf(prorrateo[fila][CANTIDAD]));
        }
        return total;
    }
    
    public static Double obtenerSumaArreglo(int inicio){
        Double total = 0.0;
        for (int i = inicio; i < gastos.length; i+=2) {
            total += gastos[i];
        }
        return total;
    }
    
    public static Double obtenerCoeficiente(Double a, Double b){
        return a / b;
    }
    
    public static void llenarGastosValor(Double coeficiente){
        for (int i = 0; i < prorrateo.length; i++) {
            prorrateo[i][GASTO_VALOR] = (Double.valueOf(prorrateo[i][VALOR])*coeficiente) + "";
        }
    }
    
    public static Double obtenerPesoTotal(){
        Double total = 0.0;
        for (int fila = 0; fila < prorrateo.length; fila++) {
            total += (Double.valueOf(prorrateo[fila][CANTIDAD])*pesoProductos[fila]);
        }
        return total;
    }
    
    public static Double obtenerTotalGastoPeso(){
        Double total = 0.0;
        for (int i = 1; i < gastos.length; i+=2) {
            total += Double.valueOf(gastos[i]);
        }
        return total;
    }
    
    public static void llenarGastosPeso(Double coeficiente){
        for (int fila = 0; fila < prorrateo.length; fila++) {
            prorrateo[fila][GASTO_PESO] = (pesoProductos[fila]*coeficiente) + "";
        }
    }
    
    public static void llenarCostoUnitario(){
        for (int fila = 0; fila < prorrateo.length; fila++) {
            prorrateo[fila][COSTO_UNIDAD] = (Double.valueOf(prorrateo[fila][VALOR])
                                            +Double.valueOf(prorrateo[fila][GASTO_VALOR])
                                            +Double.valueOf(prorrateo[fila][GASTO_PESO])) + "";
        }
    }
    
    public static void llenarCostoTotal(){
        for (int fila = 0; fila < prorrateo.length; fila++) {
            prorrateo[fila][COSTO_TOTAL] = (Double.valueOf(prorrateo[fila][COSTO_UNIDAD])
                                           *Double.valueOf(prorrateo[fila][CANTIDAD])) + "";
        }
    }
    
    public static Double obtenerTotalCuadro(){
        Double total = 0.0;
        for (int fila = 0; fila < prorrateo.length; fila++) {
            total += Double.valueOf(prorrateo[fila][COSTO_TOTAL]);
        }
        return total;
    }
    
    public static String comprobacion(Double a, Double b){
        Double diferencia = Math.abs(a - b);
        if (diferencia <= 0.20){
            return "El ejercicio se ha resuelto correctamente.\n";
        }
        else {
            return "Hay un error en el ejercicio, los totales no coinciden.\n";
        }
    }
    
    public static void ejecutar(){
        //Paso 1
        Double valorTotal = obtenerValorTotal();
        //Paso 2
        Double totalGastoValor = obtenerSumaArreglo(0);
        //Paso 3
        Double coeficienteGasto = obtenerCoeficiente(totalGastoValor,valorTotal);
        //Paso 4
        llenarGastosValor(coeficienteGasto);
        //Paso 5
        Double pesoTotal = obtenerPesoTotal();
        //Paso 6
        Double totalGastoPeso = obtenerSumaArreglo(1);
        //Paso 7
        Double coeficienteGastoPeso = obtenerCoeficiente(totalGastoPeso,pesoTotal);
        //Paso 8
        llenarGastosPeso(coeficienteGastoPeso);
        //Paso 9
        llenarCostoUnitario();
        llenarCostoTotal();
        imprimirDecorado();
        //Paso 10
        Double totalCuadro = obtenerTotalCuadro();
        Double granTotal = valorTotal + totalGastoValor + totalGastoPeso;
        String resultado = comprobacion(totalCuadro, granTotal);
        System.out.println("\n*Total del cuadro: " + totalCuadro
                       + "\n\n*Gran Total: " + granTotal
                       + "\n\n*" + resultado);
    }
    
    public String agregaVendedorMatriz(ClsProducto producto){
        if (filaActual >= prorrateo.length){
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
