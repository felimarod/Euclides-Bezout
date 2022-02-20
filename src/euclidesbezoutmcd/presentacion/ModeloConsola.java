package euclidesbezoutmcd.presentacion;

import java.util.Scanner;

import euclidesbezoutmcd.logica.Bezout;
import euclidesbezoutmcd.logica.Euclides;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModeloConsola {
    Scanner sc;
    public ModeloConsola(){
        sc = new Scanner(System.in);
    }
    public static void main(String[] args) {
        
        new ModeloConsola().start();
    }
    
   
    public void start(){
        //int [] numeros = {pedirNumero(1),pedirNumero(2), pedirNumero(3)};
        
        int [] numeros = {1239,4306,2314};
//        int [] numeros = {15,21,35,77};
        //int [] numeros = {1994, 6871, 412200};// 2*997,    6871,   2^3*3^2*5^2*229 = 1 combinacion
        
        //int [] numeros = {15,42,80};          //   3*5,   2*3*7,  2^4*5 = 3 hallarCombinacionesFinales
        //int [] numeros = {6,10,15};           //   2*3,     2*5,    3*5 = 3 hallarCombinacionesFinales
        
        //int [] numeros = {5,6,21};            //     5,     2*3,    3*7 = 1 combinacion

        //int [] numeros = {14,25,35};          //   2*7,     5*5,    5*7 = 2 hallarCombinacionesFinales
        //int [] numeros = {52,75,35};          // 2*3*7,   3*5*5,    5*7 = 1 combinacion
        //int [] numeros = {52,75,105};         // 2*3*7,   3*5*5,  3*5*7 = 1 combinacion
        //int [] numeros = {52,150,210};        // 2*3*7, 2*3*5*5,2*3*5*7 = 1 combinacion
        //int [] numeros = {7,25,35};           //     7,     5*5,    5*7 = 0 combinacion

        //int [] numeros = {18,21,45};          // 2*3^2,     3*7,  3^2*5 = 1 combinacion
        //int [] numeros = {6,7,15};            //   2*3,       7,    3*5 = 1 combinacion
        //int [] numeros = {6,7,21};            //   2*3,       7,    3*7 = 2 hallarCombinacionesFinales
        //int [] numeros = {6,14,21};           //   2*3,     2*7,    3*7 = 2 hallarCombinacionesFinales
        //int [] numeros = {6,9,21};            //   2*3,     3^2,    3*7 = 0 combinacion //TODOS SON MULTIPLOS DE 3
        //int [] numeros = {6,9,42};            //   2*3,     3^2,  2*3*7 = 0 combinacion //TODOS SON MULTIPLOS DE 3

        //int [] numeros = {6,45,21};           //   2*3,   3^2*5,    3*7 = 0 combinacion //TODOS SON MULTIPLOS DE 3

        //int [] numeros = {16,24,14};         //16, 24, 14
        //int [] numeros = {16,24,35};         //
        Euclides algoritmoE = new Euclides(numeros);
        if(algoritmoE.tieneCombiancionesLineales()){
            
            algoritmoE.hallarMCD(0);
            
            System.out.println("Algoritmos");
            algoritmoE.getListaAlgoritmos().forEach(linea -> {
                System.out.println(linea);
            });
            
            String salida = "El MCD de ";
            for(int i=0; i<numeros.length; i++)
                if(i == numeros.length-1)
                    salida += "y " +numeros[i] + " es: " + algoritmoE.getResultado();
                else if( i == numeros.length-2)
                    salida += numeros[i] + " ";
                else 
                    salida += numeros[i] + ", ";
            System.out.println(salida);

            Bezout identidadB = new Bezout(algoritmoE.getListaDivisiones(), algoritmoE.getNumeros(), algoritmoE.getResultado());
            identidadB.realizarCombinacionLineal();
            
            System.out.println("Identidades");
            identidadB.getListaIdentidades().forEach(linea -> {
                System.out.println(linea);
            });
            
            System.out.println("Regresion");
            identidadB.getListaRegresion().forEach(linea ->{
                System.out.println(linea);
            });
            
            System.out.println("La Combinación Lineal es: " + identidadB.getCombinacionLineal());
        } else {
            String salida = "El MCD de ";
            for(int i=0; i<numeros.length; i++)
                if(i == numeros.length-1)
                    salida += "y " +numeros[i] + " es: " + algoritmoE.mcd();
                else if( i == numeros.length-2)
                    salida += numeros[i] + " ";
                else 
                    salida += numeros[i] + ", ";
            System.out.println(salida);
            System.out.println("No hay una combincación lineal para "+ numeros[0] +", " + numeros[1] + 
                    " y "+ numeros[2]);
        }
        
        //new LibroAE(pedirNumero(1), pedirNumero(2)).Calcular();;

    }
    private int pedirNumero(int n){
        System.out.println("Ingrese el número " + n);
        return Integer.parseInt(sc.nextLine());
    }

    
}
