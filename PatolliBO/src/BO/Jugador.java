/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Jugador {
    
    private String nombre;
    private Integer dinero;
    private Color color;
    private int numJugador;
    private Boolean partida;
    private ArrayList<Ficha> fichas;

    public Jugador(){
        fichas = new ArrayList<>(5);
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDinero() {
        return dinero;
    }

    public void setDinero(Integer dinero) {
        this.dinero = dinero;
    }
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getNumJugador() {
        return numJugador;
    }

    public void setNumJugador(int numJugador) {
        this.numJugador = numJugador;
    }

    public ArrayList<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(ArrayList<Ficha> fichas) {
        this.fichas = fichas;
    }

    public void addFichas(Ficha ficha) {
        this.fichas.add(ficha);
    }

    public Boolean getPartida() {
        return partida;
    }

    public void setPartida(Boolean partida) {
        this.partida = partida;
    }
    
    
}
