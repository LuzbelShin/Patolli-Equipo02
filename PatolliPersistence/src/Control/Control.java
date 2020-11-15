/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BO.*;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 *
 * @author bel
 */
public class Control extends Canvas {

    private Integer ancho, alto, cantidad;
    private final ArrayList<Ficha> fichasPartida = new ArrayList<>();
    private Ficha ficha;
    private Jugador jugador;
    private Tablero tablero;
    private Integer movimiento;
    private Operacion operacion;

    @Override
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        int x = 10, y = 10;
        dibujaTablero(g2d, x, y, ancho, alto, cantidad);

        this.operacion = getOperacion();
        if (jugador != null && ficha != null && tablero != null) {
            if (operacion.equals(Operacion.Insertar)) {
                System.out.println(jugador.getFichas());
                dibujaFicha(g2d, insertar(jugador, ficha, tablero), jugador);
                System.out.println(jugador.getFichas());
            } else if (operacion.equals(Operacion.Mover) && movimiento != null) {
                mover(ficha, tablero, movimiento);
                cargarFichas(g2d, jugador);
            }
        }
    }

    public void setDimensiones(Integer ancho, Integer alto) {
        this.ancho = ancho;
        this.alto = alto;
    }

    public void setCantidadCasillas(Integer casillas) {
        this.cantidad = casillas;
    }

    public Integer getCasillas() {
        return cantidad;
    }

    public Integer getAncho() {
        return ancho;
    }

    public Integer getAlto() {
        return alto;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Integer getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Integer movimiento) {
        this.movimiento = movimiento;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    public void dibujaHorizontal(int x, int y, int ancho, int alto, int cuantos, Graphics2D g) {
        Rectangle rect = new Rectangle();
        for (int i = 0; i < cuantos; i++) {
            rect.setBounds(x, y, ancho, alto);
            g.draw(rect);
            x += ancho;
        }
    }

    public void dibujaVertical(int x, int y, int ancho, int alto, int cuantos, Graphics2D g) {
        Rectangle rect = new Rectangle();
        for (int i = 0; i < cuantos; i++) {
            rect.setBounds(x, y, ancho, alto);
            g.draw(rect);
            y += alto;
        }
    }

    public void dibujarLineas(Graphics2D g, int x, int y, int ancho, int alto, int cantidad) {
        g.setStroke(new BasicStroke(6));
        g.setColor(Color.RED);
        int tot = 4 + (cantidad);
        int xInicio = (x + (ancho * (2 + cantidad))) + 3, xFin = (x + (ancho * ((2 + cantidad)) + (2 * ancho))) - 4;
        int yInicio = y + (alto * 2), yFin = y + (alto * (tot + cantidad));

        g.drawLine(xInicio, yInicio, xFin, yInicio);
        g.drawLine(xInicio, yFin, xFin, yFin);
        g.drawLine(yInicio, xInicio, yInicio, xFin);
        g.drawLine(yFin, xInicio, yFin, xFin);
    }

    public void dibujaCentralSuperior(Graphics2D g2d, int x, int y, int ancho, int alto, int cantidad) {
        dibujaVertical(x + (ancho * (2 + cantidad)), y, ancho, alto, 2, g2d);
        dibujaVertical(x + (ancho * (3 + cantidad)), y, ancho, alto, 2, g2d);
        dibujaVertical(x + (ancho * (2 + cantidad)), y + (alto * 2), ancho, alto, cantidad, g2d);
        dibujaVertical(x + (ancho * (3 + cantidad)), y + (alto * 2), ancho, alto, cantidad, g2d);
    }

    public void dibujaCentralInferior(Graphics2D g2d, int x, int y, int ancho, int alto, int cantidad) {
        int tot = 4 + cantidad;
        dibujaVertical(x + (ancho * (2 + cantidad)), y + (alto * (tot)), ancho, alto, cantidad, g2d);
        dibujaVertical(x + (ancho * (3 + cantidad)), y + (alto * (tot)), ancho, alto, cantidad, g2d);
        dibujaVertical(x + (ancho * (2 + cantidad)), y + (alto * (tot + cantidad)), ancho, alto, 2, g2d);
        dibujaVertical(x + (ancho * (3 + cantidad)), y + (alto * (tot + cantidad)), ancho, alto, 2, g2d);
    }

    public void dibujaLateralIzquierda(Graphics2D g2d, int x, int y, int ancho, int alto, int cantidad) {
        dibujaHorizontal(x, y + (alto * (2 + cantidad)), ancho, alto, 2, g2d);
        dibujaHorizontal(x, y + (alto * (3 + cantidad)), ancho, alto, 2, g2d);
        dibujaHorizontal(x + (ancho * 2), y + (alto * (2 + cantidad)), ancho, alto, cantidad, g2d);
        dibujaHorizontal(x + (ancho * 2), y + (alto * (3 + cantidad)), ancho, alto, cantidad, g2d);
    }

    public void dibujaLateralDerecha(Graphics2D g2d, int x, int y, int ancho, int alto, int cantidad) {
        int tot = 4 + cantidad;
        dibujaHorizontal(x + (ancho * tot), y + (alto * (2 + cantidad)), ancho, alto, cantidad, g2d);
        dibujaHorizontal(x + (ancho * tot), y + (alto * (3 + cantidad)), ancho, alto, cantidad, g2d);
        dibujaHorizontal(x + (ancho * (tot + cantidad)), y + (alto * (2 + cantidad)), ancho, alto, 2, g2d);
        dibujaHorizontal(x + (ancho * (tot + cantidad)), y + (alto * (3 + cantidad)), ancho, alto, 2, g2d);
    }

    public void dibujaCasillasCentrales(Graphics2D g2d, int x, int y, int ancho, int alto, int cantidad) {
        g2d.setColor(Color.decode("#7a70ad"));
        dibujaHorizontal(x + (ancho * (2 + cantidad)), y + (alto * (2 + cantidad)), ancho, alto, 2, g2d);
        dibujaHorizontal(x + (ancho * (2 + cantidad)), y + (alto * (3 + cantidad)), ancho, alto, 2, g2d);
    }

    public void dibujaTablero(Graphics2D g2d, int x, int y, int ancho, int alto, int cantidad) {
        g2d.setColor(Color.decode("#0000000"));
        g2d.setStroke(new BasicStroke(2));
        dibujaCentralSuperior(g2d, x, y, ancho, alto, cantidad);
        dibujaLateralIzquierda(g2d, x, y, ancho, alto, cantidad);
        dibujaLateralDerecha(g2d, x, y, ancho, alto, cantidad);
        dibujaCentralInferior(g2d, x, y, ancho, alto, cantidad);
        dibujaCasillasCentrales(g2d, x, y, ancho, alto, cantidad);
        dibujarLineas(g2d, x, y, ancho, alto, cantidad);
        g2d.setColor(Color.decode("#0000000"));
    }

    public Ficha mover(Ficha ficha, Tablero tablero, Integer movimiento) {
        if (verificarFicha(ficha)) {
            Double basePositiva = (((((tablero.getCantidadCasillas() + 2)) * ancho) + (ancho * 0.6)) + 10) + (ancho / 2);

            Double baseNegativa = (((((tablero.getCantidadCasillas() + 2)) * alto) + (alto * 0.6)) + 10) - (alto / 2);

            Double x = 0d, y = 0d;
            for (int i = 0; i < movimiento; i++) {
                ficha.setPosicionX(ficha.getNuevaPosicionX());
                ficha.setPosicionY(ficha.getNuevaPosicionY());
                if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    if (ficha.getPosicionY().intValue() == 14) {
                        x = ficha.getPosicionX() - ficha.getAncho();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    }
                } else if (ficha.getPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                    if (ficha.getPosicionX().intValue() == basePositiva.intValue()) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else {
                        x = ficha.getPosicionX() - ficha.getAncho();
                        y = ficha.getPosicionY();
                    }
                } else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    if (ficha.getPosicionY().intValue() == baseNegativa) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    }
                } else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                    if (ficha.getPosicionX().intValue() == 14) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY();
                    }
                } else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    if (ficha.getPosicionX().intValue() == baseNegativa) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    }
                } else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                    if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    }
                } else if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                    if (ficha.getPosicionY().intValue() == basePositiva) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    }
                } else if (ficha.getPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    }
                }
                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
            }
            return ficha;
        }
        return null;
    }

    public Ficha insertar(Jugador jugador, Ficha ficha, Tablero tablero) {
        Double x = 0d, y = 0d;
        switch (jugador.getNumJugador()) {
            case 1:
                x = ficha.getPosicionX() + (ficha.getAncho() * (tablero.getCantidadCasillas() + 3.1));
                y = ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 1.1));
                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
                break;
            case 2:
                x = ficha.getPosicionX() + (ficha.getAncho() * (tablero.getCantidadCasillas() + 2.1));
                y = ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 4.1));
                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
                break;
            case 3:
                x = ficha.getPosicionX() + (ficha.getAncho() * (tablero.getCantidadCasillas() + 1.1));
                y = ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 2.1));
                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
                break;
            case 4:
                x = ficha.getPosicionX() + (ficha.getAncho() * (tablero.getCantidadCasillas() + 4.1));
                y = ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 3.1));
                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
                break;
            default:
                break;
        }
        fichasPartida.add(ficha);
        return ficha;
    }

    public Ellipse2D.Double generaFicha(Ficha ficha) {
        return new Ellipse2D.Double(ficha.getNuevaPosicionX(), ficha.getNuevaPosicionY(), ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
    }

    public Boolean verificarFicha(Ficha ficha) {
        return fichasPartida.contains(ficha);
    }

    public void dibujaFicha(Graphics2D graphics2D, Ficha ficha, Jugador jugador) {
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setColor(jugador.getColor());
        graphics2D.fill(generaFicha(ficha));
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(generaFicha(ficha));
    }

    public void cargarFichas(Graphics2D graphics2D, Jugador jugador) {
        for (Ficha ficha : fichasPartida) {
            if (movimiento.equals(Operacion.Insertar)) {

            } else if (movimiento.equals(Operacion.Mover)) {

            }
            dibujaFicha(graphics2D, ficha, jugador);
        }
    }

}
