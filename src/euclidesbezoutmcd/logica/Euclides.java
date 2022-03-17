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
        List<int[]> combinacionesPosibles = Combinaciones.getInstance().getCombinaciones(numeros);
        int mcdAux = 0, contadorLineas;
        for (int[] posible : combinacionesPosibles) {
            contadorLineas = 0;

            for(int i=posible.length-2; i>=0;i--){
                if(i == posible.length-2){
                    mcdAux = mcd(posible[i], posible[i+1]);
                    if(cantidadAlgoritmos(posible[i], posible[i+1]) > 1)
                        contadorLineas++;
                } else {
                    if(cantidadAlgoritmos(posible[i], mcdAux) > 1)
                        contadorLineas++;
                    mcdAux = mcd(posible[i], mcdAux);
                }
            }
            //System.out.println(contadorLineas);
            if(contadorLineas == posible.length-1)
                combinacionesLineales.add(posible);
        }
        return combinacionesLineales.size() > 0;        
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
        for(int i=numerosOrdenados.length-2; i>=0;i--)
            if(i == numerosOrdenados.length-2)
                resultado = mcd(numerosOrdenados[i], numerosOrdenados[i+1]);
            else
                resultado = mcd(numerosOrdenados[i], resultado);
        
        // Guardar algoritmos de Euclides en una lista
        listaDivisiones.stream().map(operacion -> operacion.getDatos()).forEachOrdered(datos -> {
            listaAlgoritmos.add(datos[0] + " = " + datos[1] + " x " + datos[2] + " + " + datos[3]);
        });
    }
    
    public int mcd(){
        int numerosOrdenados [] = Combinaciones.getInstance().getCombinaciones(numeros).get(0);
        
        resultado = 1;
        listaDivisiones.clear();
        for(int i=numerosOrdenados.length-2; i>=0;i--)
            if(i == numerosOrdenados.length-2)
                resultado = mcd(numerosOrdenados[i], numerosOrdenados[i+1]);
            else
                resultado = mcd(numerosOrdenados[i], resultado);
        return resultado;
    }
            
    private int mcd(int mayor, int menor){
        
        if(mayor < menor){
            int aux = menor;
            menor = mayor;
            mayor = aux;
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
