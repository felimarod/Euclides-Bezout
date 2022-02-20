/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euclidesbezoutmcd.logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Combinaciones {
    private static Combinaciones  instance;
    private Combinaciones(){
        
    }
    public static Combinaciones getInstance(){
        if(instance == null) 
            instance = new Combinaciones();
        return instance;
    }
    private int numeroCombinaciones;
    private int factorial(int numero){
        if(numero == 0)
            return 1;
        else
            return factorial(numero-1) * numero;                    
    }
   
    public List<int[]> getCombinaciones(int [] n){
        numeroCombinaciones = factorial(n.length);
        
        List <int[]> posiciones = new ArrayList<>();
        int [] arrayAux;
        List <Integer> aux;
        
        int num, contador = 0;
        
        //Recorre la cantidad de combinaciones posibles
        for(int i = 0; i < numeroCombinaciones ; i++){
            //Recorre las posiciones
            
            do{
                contador = 0;
                aux = new ArrayList<>();
                arrayAux = new int [n.length];

                do{
                    do{
                        num = (int) (Math.random() * n.length);
                    }while(aux.contains(num));
                    aux.add(num);
                    contador++;
                }while(contador < n.length);
                for (int j = 0; j < n.length; j++) {
                    arrayAux[j] = aux.get(j);
                }
            }while(existeArreglo(arrayAux, posiciones));
            
            posiciones.add(arrayAux);
        }
        List<int[]> retorno = new ArrayList<>();
        for(int [] arreglo: posiciones){
            arrayAux = new int[arreglo.length];
            for(int i = 0; i<arreglo.length; i++)
                arrayAux[i] = n[arreglo[i]];

            retorno.add(arrayAux);
        }
        return retorno;
    }

    private boolean existeArreglo(int[] array, List<int[]> retorno) {
        int incidencias;
        for(int [] comparar : retorno){
            incidencias = 0;
            for (int i = 0; i < comparar.length; i++)
                if(array[i] == comparar[i])
                    incidencias++;
            if(incidencias == array.length)
                return true;
        }
        return false;
    }
}
