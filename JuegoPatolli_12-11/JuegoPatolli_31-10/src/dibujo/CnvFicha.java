/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dibujo;

import POJO.*;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Bel
 */
public class CnvFicha extends Canvas {
    private Ficha ficha;
    private Jugador jugador;
    private Tablero tablero;
    
    public CnvFicha(){
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        insertarFicha(g2d, jugador, ficha, tablero);
    }

    public void insertarFicha(Graphics2D graphics2D, Jugador jugador, Ficha ficha, Tablero tablero) {
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setColor(jugador.getColor());
        Ellipse2D.Double fichaJ = null;
        switch (jugador.getNumJugador()) {
            case 1:
                fichaJ = new Ellipse2D.Double(ficha.getPosicionX()+ (ficha.getAncho() * (tablero.getCantidadCasillas() + 3.1)), ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 1.1)), ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
                graphics2D.fill(fichaJ);
                break;
            case 2:
                fichaJ = new Ellipse2D.Double(ficha.getPosicionX()+ (ficha.getAncho() * (tablero.getCantidadCasillas() + 2.1)), ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 4.1)), ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
                graphics2D.fill(fichaJ);
                break;
            case 3:
                fichaJ = new Ellipse2D.Double(ficha.getPosicionX()+ (ficha.getAncho() * (tablero.getCantidadCasillas() + 1.1)), ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 2.1)), ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
                graphics2D.fill(fichaJ);
                break;
            case 4:
                fichaJ = new Ellipse2D.Double(ficha.getPosicionX()+ (ficha.getAncho() * (tablero.getCantidadCasillas() + 4.1)), ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 3.1)), ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
                graphics2D.fill(fichaJ);
                break;
            default:
                break;
        }
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(fichaJ);
    }

    
//    public void insertarFichaJugador1(Graphics2D g2d, int x, int y, int ancho, int alto, Color color, int cantidad) {
//        g2d.setStroke(new BasicStroke(1));
//        g2d.setColor(color);
//        Ellipse2D.Double ficha = new Ellipse2D.Double(x + (ancho * (cantidad + 3.1)), y + (alto * (cantidad + 1.1)), ancho - (ancho / 4), alto - (alto / 4));//(x + (ancho / 7), y + (alto / 7), ancho - (ancho / 4), alto - (alto / 4));
//        g2d.fill(ficha);
//        g2d.setColor(Color.BLACK);
//        g2d.draw(ficha);
//    }

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
}
