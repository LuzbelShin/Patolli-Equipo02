/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import java.awt.Color;

/**
 *
 * @author Usuario
 */
public class Ficha {
    private Color color;
    private Double posicionX;
    private Double posicionY;
    private Double nuevaPosicionX;
    private Double nuevaPosicionY;
    private Integer ancho;
    private Integer alto;
    private Integer cuadrante;
    private Boolean fichaJugada;
    private Boolean disponible;
    
//    private Integer posicionJugador; ??????????????
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = null;
    }

    public Double getPosicionX() {
        return posicionX;
    }

    public Double getPosicionY() {
        return posicionY;
    }

    public void setPosicionX(Double posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(Double posicionY) {
        this.posicionY = posicionY;
    }

    public Integer getAncho() {
        return ancho;
    }

    public void setAncho(Integer ancho) {
        this.ancho = ancho;
    }

    public Integer getAlto() {
        return alto;
    }

    public void setAlto(Integer alto) {
        this.alto = alto;
    }

    public Double getNuevaPosicionX() {
        return nuevaPosicionX;
    }

    public void setNuevaPosicionX(Double nuevaPosicionX) {
        this.nuevaPosicionX = nuevaPosicionX;
    }

    public Double getNuevaPosicionY() {
        return nuevaPosicionY;
    }

    public void setNuevaPosicionY(Double nuevaPosicionY) {
        this.nuevaPosicionY = nuevaPosicionY;
    }

    public Integer getCuadrante() {
        return cuadrante;
    }

    public void setCuadrante(Integer cuadrante) {
        this.cuadrante = cuadrante;
    }

    public Boolean getFichaJugada() {
        return fichaJugada;
    }

    public void setFichaJugada(Boolean fichaJugada) {
        this.fichaJugada = fichaJugada;
    }

    
    
}
  