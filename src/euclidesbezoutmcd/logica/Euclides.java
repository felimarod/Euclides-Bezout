package euclidesbezoutmcd.logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Euclides{
    int [] numeros;
    List<Division> listaDivisiones;
    List<String> listaAlgoritmos;
    List<int []> combinacionesLineales;
    int resultado;
    
    public Euclides(){
        listaDivisiones = new ArrayList<>();
        listaAlgoritmos = new ArrayList<>();
        combinacionesLineales = new ArrayList<>();
    }
    public Euclides(int [] numeros){
        listaDivisiones = new ArrayList<>();
        listaAlgoritmos = new ArrayList<>();
        combinacionesLineales = new ArrayList<>();
        
        // Pasa todos los numeros a positivo
        for(int i = 0; i < numeros.length; i++)
            if(numeros[i] < 0)
                numeros[i] *= -1;
        // Organiza de mayor a menor
        Arrays.sort(numeros);
        this.numeros = numeros;
    }
    
    public boolean tieneCombiancionesLineales(){
        List<int[]> combinacionesPosibles = new ArrayList<>();
        int [][] orden = {{0,2,1},{1,2,0},{2,1,0}};
        int [] aux;
        
        for(int i = 0; i < 3; i++){
            if(combinacionSirve(orden[i])){
                aux = new int [3];
                aux[0] = numeros[orden[i][0]];
                aux[1] = numeros[orden[i][1]];
                aux[2] = numeros[orden[i][2]];
                combinacionesPosibles.add(aux);
            }
        }
        
        if(combinacionesPosibles.size() > 0){
            combinacionesPosibles.forEach(posible ->{
                if(cantidadAlgoritmos(posible[1], posible[2]) > 1 && cantidadAlgoritmos(posible[0], mcd(posible[1],posible[2])) > 1){
                    combinacionesLineales.add(posible);
                }
            });
            return combinacionesLineales.size() > 0;
        } else {
            return false;
        }
    }
    
    private boolean combinacionSirve(int [] orden){
        int mcd1, mcd2;
        mcd1 = mcd(numeros[orden[1]], numeros[orden[2]]);
        mcd2 = mcd(numeros[orden[0]], mcd1);
        return mcd2 != mcd1;
    }
    
    private int cantidadAlgoritmos(int num1, int num2){
        listaAlgoritmos.clear();
        listaDivisiones.clear();
        mcd(num1, num2);
        // Guardar divisiones como algoritmo de Euclides
        listaDivisiones.stream().map(operacion -> operacion.getDatos()).forEachOrdered(datos -> {
            listaAlgoritmos.add(datos[0] + " = " + datos[1] + " x " + datos[2] + " + " + datos[3]);
        });
        return getListaAlgoritmos().size();
    }
    
    public void hallarMCD(int n){
        int numerosOrdenados [] = combinacionesLineales.get(n);
        
        listaDivisiones.clear();
        resultado = mcd(numerosOrdenados[0], mcd(numerosOrdenados[1], numerosOrdenados[2]));
        
        // Guardar algoritmos de Euclides en una lista
        listaDivisiones.stream().map(operacion -> operacion.getDatos()).forEachOrdered(datos -> {
            listaAlgoritmos.add(datos[0] + " = " + datos[1] + " x " + datos[2] + " + " + datos[3]);
        });
    }
    
    public int mcd(){
        
        return mcd(numeros[2], mcd(numeros[1], numeros[0]));
    }
            
    private int mcd(int mayor, int menor){
        
        if(mayor < menor){
            int aux = menor;
            menor = mayor;
            mayor = menor;
        }
            
        listaDivisiones.add(new Division(mayor, menor));
        while(listaDivisiones.get(listaDivisiones.size()-1).getResiduo() != 0){
            listaDivisiones.add(
                new Division(
                        listaDivisiones.get(listaDivisiones.size()-1).getDivisor(),
                        listaDivisiones.get(listaDivisiones.size()-1).getResiduo()
                    )
                );
        }
        listaAlgoritmos.clear();
        return listaDivisiones.get(listaDivisiones.size()-1).getDivisor();
    }
    
    public void setNumeros(int [] numeros){
        // Pasa todos los numeros a positivo
        for(int i = 0; i < numeros.length; i++)
            if(numeros[i] < 0)
                numeros[i] *= -1;
        // Organiza de mayor a menor
        Arrays.sort(numeros);
        this.numeros = numeros;
    }
    public int[] getNumeros() {
        return numeros;
    }

    public List<Division> getListaDivisiones() {
        return listaDivisiones;
    }

    public List<int[]> getCombinacionesLineales() {
        return combinacionesLineales;
    }
    
    public List<String> getListaAlgoritmos() {
        return listaAlgoritmos;
    }

    public int getResultado() {
        return resultado;
    }
    
}