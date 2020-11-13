/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Tablero {
    private Integer cantidadCasillas;
    private ArrayList<Jugador> jugadores;

    public Integer getCantidadCasillas() {
        return cantidadCasillas;
    }

    public void setCantidadCasillas(Integer cantidadCasillas) {
        this.cantidadCasillas = cantidadCasillas;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
    
}
