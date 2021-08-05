/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.primer_parcial;

import com.gastos.ClsGastos;
import com.producto.ClsProducto;
import com.prorrateo.ClsProrrateo;
import java.util.Scanner;

/**
 *
 * @author jeant
 */
public class ClsPrincipal {
    private static Scanner t = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("¿Cuantos productos desea agregar?");
        int num = t.nextInt();
        t.nextLine();
        System.out.println("\n");
        ClsProrrateo prorrateo = new ClsProrrateo(num);
        ClsGastos gastos = new ClsGastos();
        ClsProducto producto = new ClsProducto();
        
        try {
            for (int i = 0; i < num; i++) {
                System.out.println("------Producto " + (i+1) + "------");
                producto.recolectarInformacion();
                prorrateo.agregaVendedorMatriz(producto);
                System.out.println("----------------------\n");
            }
        } catch (NegativeArraySizeException e){
            System.out.println("Porfavor, ingrese el tipo de dato que se le solicita.");
        }
        
        try {
            gastos.recolectarInformacion();
            prorrateo.agregaGastos(gastos);
            ClsProrrateo.ejecutar();
        } catch (Exception e){
            System.out.println("Algo ha fallado durane la ejecucion.");
        }
    }
}
