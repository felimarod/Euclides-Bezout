package euclidesbezoutmcd.presentacion;

import euclidesbezoutmcd.logica.Bezout;
import euclidesbezoutmcd.logica.Euclides;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Modelo {
    private VistaInicial vistaInicial;
    private VistaOperaciones vistaOperaciones;

    public void iniciar(){
        getVistaInicial().setVisible(true);
    }
    public void mostrarResultados(){
        int [] numeros = new int[3];
        try{
            numeros[0] = Integer.parseInt(getVistaInicial().getJtNum1().getText());
            numeros[1] = Integer.parseInt(getVistaInicial().getJtNum2().getText());
            numeros[2] = Integer.parseInt(getVistaInicial().getJtNum3().getText());
            for (int numero : numeros)
                if(numero == 0){
                    JOptionPane.showMessageDialog(null, "Por favor ingrese números diferentes de cero.");
                    return;
                }
            
            Euclides algoritmoE = new Euclides(numeros);
            if(algoritmoE.tieneCombiancionesLineales()){
                for(int i = 0; i < algoritmoE.getCombinacionesLineales().size(); i++){
                    vistaOperaciones = null;
                    
                    algoritmoE.hallarMCD(i);

                    getVistaOperaciones().setTitle(Arrays.toString(algoritmoE.getNumeros()));
                    getVistaOperaciones().getJlTitulo().setText("<html><h2>"
                            + Arrays.toString(algoritmoE.getNumeros())
                            + "</h2></html>");

                    // Algoritmos
                    String algoritmos = "<html>";
                    for(String linea: algoritmoE.getListaAlgoritmos()){
                        algoritmos += linea + "<br>";
                    }
                    algoritmos += "</html>";
                    getVistaOperaciones().getJlAEuclides().setText(algoritmos);

                    getVistaOperaciones().getJlResultadoMCD().setText(
                            "<html>MCD de "+ numeros[0] +", " + numeros[1] + 
                            " y "+ numeros[2] + " es: " + algoritmoE.getResultado()
                            + "</html>"
                    );

                    Bezout identidadB = new Bezout(algoritmoE.getListaDivisiones(), algoritmoE.getNumeros(), algoritmoE.getResultado());
                    identidadB.realizarCombinacionLineal();

                    // Identidades
                    String identidades = "<html>";
                    for (String linea: identidadB.getListaIdentidades()){
                        identidades += linea + "<br>";
                    }
                    identidades += "</html>";
                    getVistaOperaciones().getJlIBezout().setText(identidades);
                    
                    // Regresion
                    String regresion = "<html>";
                    for(String linea: identidadB.getListaRegresion()){
                        regresion += linea + "<br>";
                    }
                    regresion += "</html>";
                    getVistaOperaciones().getJlRLineal().setText(regresion);

                    getVistaOperaciones().getJlResultadoCL().setText(
                            "<html>La Combinación Lineal es: "
                                    + "" + identidadB.getCombinacionLineal()
                                            + "</html>"
                    );
                    getVistaOperaciones().setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null,"El MCD de "+ numeros[0] +", " + numeros[1] + 
                        " y "+ numeros[2] + " es: " + algoritmoE.mcd() + "\nNo hay una combincación lineal para "+ numeros[0] +", " + numeros[1] + 
                        " y "+ numeros[2]);
            }
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(null, "Ingrese números.");
        }
    }

    public VistaInicial getVistaInicial() {
        if( vistaInicial == null )
            vistaInicial = new VistaInicial(this);
        return vistaInicial;
    }

    public VistaOperaciones getVistaOperaciones() {
        if (vistaOperaciones == null)
            vistaOperaciones = new VistaOperaciones();
        return vistaOperaciones;
    }
    
}
