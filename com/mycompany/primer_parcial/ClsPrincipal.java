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
        ClsProrrateo prorrateo = new ClsProrrateo(num);
        ClsGastos gastos = new ClsGastos();
        for (int i = 0; i < num; i++) {
            ClsProducto producto = new ClsProducto();
            producto.recolectarInformacion();
            prorrateo.agregaVendedorMatriz(producto);
        }
        
        gastos.recolectarInformacion();
        prorrateo.agregaGastos(gastos);
        ClsProrrateo.ejecutar();
    }
}
