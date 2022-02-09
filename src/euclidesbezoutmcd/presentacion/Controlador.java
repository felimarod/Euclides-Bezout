/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euclidesbezoutmcd.presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Usuario
 */
public class Controlador implements ActionListener{
    Modelo modelo;

    public Controlador(VistaInicial vista) {
        this.modelo = vista.getModelo();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(modelo.getVistaInicial().getJbRealizarProceso())){
            modelo.mostrarResultados();
        }
    }
    
}
