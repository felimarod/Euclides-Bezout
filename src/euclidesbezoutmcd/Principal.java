package euclidesbezoutmcd;

import euclidesbezoutmcd.presentacion.Modelo;
import euclidesbezoutmcd.presentacion.ModeloConsola;

class Principal {
    /**
     * Escribir los pasos del Algoritmo Extendido de Euclides 
     * para llegar a la Identidad de Bezóut, expresando con 
     * Coeficientes No Nulos A, B y C, el MCD de los números 
     * que están en paréntesis, como una Combinación Lineal 
     * entre ellos: MCD =A(1239) +B(4306) + C(2314)
     * 
     * @param args
     */
    public static void main(String[] args) {
        //new ModeloConsola().start();
        new Modelo().iniciar();
    }
}
