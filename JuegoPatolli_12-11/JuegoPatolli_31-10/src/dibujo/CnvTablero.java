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
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class CnvTablero extends Canvas {

    private Integer ancho, alto, cantidad;
    private Ficha ficha;
    private Jugador jugador;
    private Tablero tablero;
    private Integer movimiento;
    private Operacion operacion; 
    
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        //se establece el color para dibujar
//        g2d.setColor(Color.BLACK);
//        //se dibuja el rectángulo del contorno del Canvas (que es como un panel especial para dibujar)
//        Rectangle rect = new Rectangle();
//        rect.setBounds(0, 0, this.getWidth() - 1, this.getHeight() - 1);
//        g2d.draw(rect);

        //x,y es el punto donde se iniciará a dibujar el tablero
        //ancho y alto son las medidas de las casillas
        int x = 10, y = 10;//, ancho = 40, alto = 40, cantidad = 5;

        dibujaTablero(g2d, x, y, ancho, alto, cantidad);
//        if (jugador != null && ficha != null && tablero != null) {
//            
//        }
//        if (jugador != null && ficha != null && tablero != null) {
//            Integer mov = getMovimiento();
//            if (mov != null) {
//                moverFicha(g2d, jugador, ficha, tablero, mov);
//            } else {
//                insertarFicha(g2d, jugador, ficha, tablero);
//            }
//        }
    this.operacion = getOperacion();
    if (jugador != null && ficha != null && tablero != null) {
        if(operacion.equals(Operacion.Insertar)){
            dibujaFicha(g2d, insertar(jugador, ficha, tablero), jugador);
        }else if(operacion.equals(Operacion.Mover) && movimiento != null){
            dibujaFicha(g2d, mover(ficha, tablero, movimiento), jugador);
        }
    }
        dibujarLineas(x, y, ancho, alto, g2d, cantidad);
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


    //para dibujar una linea de casillas horizontal
    public void dibujaHorizontal(int x, int y, int ancho, int alto, int cuantos, Graphics2D g) {
        Rectangle rect = new Rectangle();
        for (int i = 0; i < cuantos; i++) {
            rect.setBounds(x, y, ancho, alto);
            g.draw(rect);
//            g.rotate(Math.toRadians(45), rect.getCenterX(), rect.getCenterY());
            x += ancho;
        }
    }

    //para dibujar una linea de casillas vertical
    public void dibujaVertical(int x, int y, int ancho, int alto, int cuantos, Graphics2D g) {
        Rectangle rect = new Rectangle();
        for (int i = 0; i < cuantos; i++) {
            rect.setBounds(x, y, ancho, alto);
            g.draw(rect);
//            g.rotate(Math.toRadians(45), rect.getCenterX(), rect.getCenterY());
            y += alto;
        }
    }

    public void dibujarLineas(int x, int y, int ancho, int alto, Graphics2D g, int cantidad) {
        g.setStroke(new BasicStroke(6));
        g.setColor(Color.RED);
        int tot = 4 + (cantidad);
        int x1 = (x + (ancho * (2 + cantidad))) + 3;
        int x2 = (x + (ancho * ((2 + cantidad)) + (2 * ancho))) - 4;
        int y1 = y + (alto * 2);
        int y2 = y + (alto * (tot + cantidad));

        g.drawLine(x1, y1, x2, y1);
        g.drawLine(x1, y2, x2, y2);
        g.drawLine(y1, x1, y1, x2);
        g.drawLine(y2, x1, y2, x2);
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
        g2d.setColor(Color.decode("#0000000"));
    }

    /*
    Este tambien creo que lo resumi mas abajo
    
    */
    public void insertarFicha(Graphics2D graphics2D, Jugador jugador, Ficha ficha, Tablero tablero) {
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setColor(jugador.getColor());
        Ellipse2D.Double fichaJ = null;

//        Double cX1 = (((((tablero.getCantidadCasillas()+2))*ancho)+(ancho*0.6))+10)+(ancho/2), cY1 = (((((tablero.getCantidadCasillas()+2))*alto)+(alto*0.6))+10)+(alto/2);
//        Double cX2 = (((((tablero.getCantidadCasillas()+2))*ancho)+(ancho*0.6))+10)-(ancho/2), cY2 = (((((tablero.getCantidadCasillas()+2))*alto)+(alto*0.6))+10)+(alto/2);
//        Double cX3 = (((((tablero.getCantidadCasillas()+2))*ancho)+(ancho*0.6))+10)-(ancho/2), cY3 = (((((tablero.getCantidadCasillas()+2))*alto)+(alto*0.6))+10)-(alto/2);
//        Double cX4 = (((((tablero.getCantidadCasillas()+2))*ancho)+(ancho*0.6))+10)+(ancho/2), cY4 = (((((tablero.getCantidadCasillas()+2))*alto)+(alto*0.6))+10)-(alto/2);
//        System.out.println("CI   X " + cX1.intValue()+ " Y "+cY1.intValue());
//        System.out.println("CII  X " + cX2.intValue()+ " Y "+cY2.intValue());
//        System.out.println("CIII X " + cX3.intValue()+ " Y "+cY3.intValue());
//        System.out.println("CIV  X " + cX4.intValue()+ " Y "+cY4.intValue());
        Double x, y;
//        Double a = ((((((tablero.getCantidadCasillas() + 2)) * ancho) + (ancho * 0.6)) + 10) + (ancho / 2) )- alto ;
        switch (jugador.getNumJugador()) {
            case 1:
                x = ficha.getPosicionX() + (ficha.getAncho() * (tablero.getCantidadCasillas() + 3.1));
                y = ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 1.1));
                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
                System.out.println(x.intValue());
                System.out.println(y.intValue());
                fichaJ = new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
                graphics2D.fill(fichaJ);
                break;
            case 2:
                x = ficha.getPosicionX() + (ficha.getAncho() * (tablero.getCantidadCasillas() + 2.1));
                y = ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 4.1));
                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
                fichaJ = new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
                graphics2D.fill(fichaJ);
                break;
            case 3:
                x = ficha.getPosicionX() + (ficha.getAncho() * (tablero.getCantidadCasillas() + 1.1));
                y = ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 2.1));
                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
                fichaJ = new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
                graphics2D.fill(fichaJ);
                break;
            case 4:
                x = ficha.getPosicionX() + (ficha.getAncho() * (tablero.getCantidadCasillas() + 4.1));
                y = ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 3.1));
                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
                fichaJ = new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
                graphics2D.fill(fichaJ);
                break;
            default:
                break;
        }
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(fichaJ);
    }

    /**
     * hACE EL CONTORNO DEL TABLERO -SOLO CONTORNO-
     *
     * @param g
     * @param casillas
     */
//    carsadasadadas
    public void dibujaCuadro(Graphics2D g, int casillas) {
//        punto medio = 10+ ( ( ( ( casillas * 2 ) + 6 ) * ancho ) / 2)
        g.drawLine(10, 10, 10, 10 + (((casillas * 2) + 6) * ancho));
        g.drawLine(10, 10 + (((casillas * 2) + 6) * ancho), 10 + (((casillas * 2) + 6) * ancho), 10 + (((casillas * 2) + 6) * ancho));
        g.drawLine(10 + (((casillas * 2) + 6) * ancho), 10, 10, 10);
        g.drawLine(10 + (((casillas * 2) + 6) * ancho), 10 + (((casillas * 2) + 6) * ancho), 10 + (((casillas * 2) + 6) * ancho), 10);
    }
    /*
    Se sigue usando?OwO
    */
    public void ubicaCuadrante(Ficha ficha, Tablero tablero) {
        Double cX4 = (((((tablero.getCantidadCasillas() + 2)) * ancho) + (ancho * 0.6)) + 10) + (ancho / 2), cY4 = (((((tablero.getCantidadCasillas() + 2)) * alto) + (alto * 0.6)) + 10) + (alto / 2);
        Double cX3 = (((((tablero.getCantidadCasillas() + 2)) * ancho) + (ancho * 0.6)) + 10) - (ancho / 2), cY3 = (((((tablero.getCantidadCasillas() + 2)) * alto) + (alto * 0.6)) + 10) + (alto / 2);
        Double cX2 = (((((tablero.getCantidadCasillas() + 2)) * ancho) + (ancho * 0.6)) + 10) - (ancho / 2), cY2 = (((((tablero.getCantidadCasillas() + 2)) * alto) + (alto * 0.6)) + 10) - (alto / 2);
        Double cX1 = (((((tablero.getCantidadCasillas() + 2)) * ancho) + (ancho * 0.6)) + 10) + (ancho / 2), cY1 = (((((tablero.getCantidadCasillas() + 2)) * alto) + (alto * 0.6)) + 10) - (alto / 2);
        if (ficha.getNuevaPosicionX() >= cX1 && ficha.getNuevaPosicionY() <= cY1) {
            ficha.setCuadrante(1);
        } else if (ficha.getNuevaPosicionX() <= cX2 && ficha.getNuevaPosicionY() <= cY2) {
            ficha.setCuadrante(2);
        } else if (ficha.getNuevaPosicionX() <= cX3 && ficha.getNuevaPosicionY() >= cY3) {
            ficha.setCuadrante(3);
        } else if (ficha.getNuevaPosicionX() >= cX4 && ficha.getNuevaPosicionY() >= cY4) {
            ficha.setCuadrante(4);
        }
    }

    /*
        Double cX1 = (((((tablero.getCantidadCasillas()+2))*ancho)+(ancho*0.6))+10)+(ancho/2), cY1 = (((((tablero.getCantidadCasillas()+2))*alto)+(alto*0.6))+10)+(alto/2);
        Double cX2 = (((((tablero.getCantidadCasillas()+2))*ancho)+(ancho*0.6))+10)-(ancho/2), cY2 = (((((tablero.getCantidadCasillas()+2))*alto)+(alto*0.6))+10)+(alto/2);
        Double cX3 = (((((tablero.getCantidadCasillas()+2))*ancho)+(ancho*0.6))+10)-(ancho/2), cY3 = (((((tablero.getCantidadCasillas()+2))*alto)+(alto*0.6))+10)-(alto/2);
        Double cX4 = (((((tablero.getCantidadCasillas()+2))*ancho)+(ancho*0.6))+10)+(ancho/2), cY4 = (((((tablero.getCantidadCasillas()+2))*alto)+(alto*0.6))+10)-(alto/2);
        System.out.println("CI   X " + cX1.intValue()+ " Y "+cY1.intValue());
        System.out.println("CII  X " + cX2.intValue()+ " Y "+cY2.intValue());
        System.out.println("CIII X " + cX3.intValue()+ " Y "+cY3.intValue());
        System.out.println("CIV  X " + cX4.intValue()+ " Y "+cY4.intValue());
     */
 /*
    Como realizar el movimiento
    
    1.- identificar cuadrante
    2.- identifica x e y
    3.- identifica para donde se encuentra digamos "cargado" de momento (para donde esta, si esta en la horizontal o la vertical)
    4.- identifica cuantas casillas avanza
    5.- identifica en que cuadrante va a quedar
    6.- Mover 
    6.- considera casillas especiales, casillas de trampa, casillas centrales
    7.- busca como ver otras fichas que no sean la tuya
    8.- no se que más poner pero si se que me faltan cosas xc
    
    
    
    
    
    
    
    
    
    com diuajr multiplas cpsas 
    
    implementar una nueva clase
    cnvJuego/partida
    que tenga objetos de cnvTablero y cnvFihca
    el metodo paint 
    que llame a los paint esto weyes o algo asi
    
    La verdad la verdad es pegarte un buen tiro hacer el de 10 en 10 de esta manera :(
     */
    public void moverFicha(Graphics2D graphics2D, Jugador jugador, Ficha ficha, Tablero tablero, Integer movimiento) {
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setColor(jugador.getColor());
        Double basePositiva = (((((tablero.getCantidadCasillas() + 2)) * ancho) + (ancho * 0.6)) + 10) + (ancho / 2);
        Double baseNegativa = (((((tablero.getCantidadCasillas() + 2)) * alto) + (alto * 0.6)) + 10) - (alto / 2);
//        Double basePositiva = (((((tablero.getCantidadCasillas() + 2)) * alto) + (ficha.getAlto() * 0.6)) + 10) + (ficha.getAlto() / 2);
//        Double baseNegativa = (((((tablero.getCantidadCasillas() + 2)) * ancho) + (ficha.getAncho() * 0.6)) + 10) - (ficha.getAncho() / 2);
//        ubicaCuadrante(ficha, tablero);
        Ellipse2D.Double fichaJ = null;

        Double x = 0d, y = 0d;
        switch (movimiento) {
            case 1:
                ficha.setPosicionX(ficha.getNuevaPosicionX());
                ficha.setPosicionY(ficha.getNuevaPosicionY());
                //vertical
                if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    //cambiar del CI al CII
                    if (ficha.getPosicionY().intValue() == 14) {
                        x = ficha.getPosicionX() - ficha.getAncho();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    }
                    //horizontal
                } else if (ficha.getPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                    if (ficha.getPosicionX().intValue() == basePositiva.intValue()) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else {
                        x = ficha.getPosicionX() - ficha.getAncho();
                        y = ficha.getPosicionY();
                    }
                    //vertical
                } else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    //cambiar del CI al CII
                    if (ficha.getPosicionY().intValue() == baseNegativa) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    }
                    //horizontal
                } else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                    if (ficha.getPosicionX().intValue() == 14) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY();
                    }
                    //horizontal
                } else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    //cambiar del CI al CII
                    if (ficha.getPosicionX().intValue() == baseNegativa) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    }
                    //vertical
                } else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                    if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    }
                    //vertical
                } else if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                    //cambiar del CI al CII
                    if (ficha.getPosicionY().intValue() == basePositiva) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    }
                    //horizontal
                } else if (ficha.getPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    }
                }
                System.out.println(x.intValue() + " " + y.intValue());
                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
                fichaJ = new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
                graphics2D.fill(fichaJ);
//               }
                break;

            case 2:
                ficha.setPosicionX(ficha.getNuevaPosicionX());
                ficha.setPosicionY(ficha.getNuevaPosicionY());
                //CI
                //vertical
                if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    //cambiar del CI al CII
                    if (ficha.getPosicionY().intValue() == (14 + ficha.getAlto())) {
                        x = ficha.getPosicionX() - (ficha.getAlto());
                        y = ficha.getPosicionY() - (ficha.getAlto());
                    } else if (ficha.getPosicionY().intValue() == 14) {
                        x = ficha.getPosicionX() - (ficha.getAlto());
                        y = ficha.getPosicionY() + (ficha.getAlto());
                    } else { //moverse
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 2);
                    }
                    //horizontal
                } else if (ficha.getPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                    //Girar
                    if (ficha.getPosicionX().intValue() == (basePositiva.intValue() + ficha.getAlto())) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == basePositiva.intValue()) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 2);
                    } else { //moverse
                        x = ficha.getPosicionX() - (ficha.getAlto() * 2);
                        y = ficha.getPosicionY();
                    }
                    //CII
                    //vertical
                } else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    //girar
                    if (ficha.getPosicionY().intValue() == (baseNegativa - ficha.getAlto())) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionY().intValue() == baseNegativa) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 2);
                        y = ficha.getPosicionY();
                    } else { //moverse
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 2);
                    }
                    //horizontal
                } else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                    //cambiar del CII al CIII
                    if (ficha.getPosicionX().intValue() == (14 + ficha.getAlto())) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == 14) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else { //moverse
                        x = ficha.getPosicionX() - (ficha.getAlto() * 2);
                        y = ficha.getPosicionY();
                    }
                    //CIII    
                    //horizontal
                } else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    //girar
                    if (ficha.getPosicionX().intValue() == (baseNegativa - ficha.getAlto())) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == baseNegativa) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 2);
                    } else { //moverse
                        x = ficha.getPosicionX() + (ficha.getAlto() * 2);
                        y = ficha.getPosicionY();
                    }
                    //vertical
                } else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                    //cambiar del CIII al CIV
                    if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 4)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else { //moverse
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 2);
                    }
                    //CIV
                    //vertical
                } else if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                    if (ficha.getPosicionY().intValue() == (basePositiva + ficha.getAlto())) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionY().intValue() == basePositiva) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 2);
                        y = ficha.getPosicionY();
                    } else { //moverse
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 2);
                    }
                    //horizontal
                    //cambiar del CIV al CI 
                } else if (ficha.getPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 4)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else { //moverse
                        x = ficha.getPosicionX() + (ficha.getAlto() * 2);
                        y = ficha.getPosicionY();
                    }
                }
                System.out.println(x.intValue() + " " + y.intValue());
                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
                fichaJ = new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
                graphics2D.fill(fichaJ);
                break;
            case 3:
                ficha.setPosicionX(ficha.getNuevaPosicionX());
                ficha.setPosicionY(ficha.getNuevaPosicionY());
                //CI
                //vertical
                if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    //cambiar del CI al CII
                    if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() - (ficha.getAlto());
                        y = ficha.getPosicionY() - (ficha.getAlto() * 2);
                    } else if (ficha.getPosicionY().intValue() == (14 + ficha.getAlto())) {
                        x = ficha.getPosicionX() - (ficha.getAlto());
                        y = ficha.getPosicionY();// - (ficha.getAlto());
                    } else if (ficha.getPosicionY().intValue() == 14) {
                        x = ficha.getPosicionX() - (ficha.getAlto());
                        y = ficha.getPosicionY() + (ficha.getAlto() * 2);
                    } else { //moverse
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 3);
                    }
                    //horizontal
                } else if (ficha.getPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                    //Girar
                    if (ficha.getPosicionX().intValue() == (basePositiva.intValue() + (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 2);
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (basePositiva.intValue() + ficha.getAlto())) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 2);
                    } else if (ficha.getPosicionX().intValue() == basePositiva.intValue()) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 3);
                    } else { //moverse
                        x = ficha.getPosicionX() - (ficha.getAlto() * 3);
                        y = ficha.getPosicionY();
                    }
                    //CII
                    //vertical
                } else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    //girar
                    if (ficha.getPosicionY().intValue() == (baseNegativa - (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 2);
                    } else if (ficha.getPosicionY().intValue() == (baseNegativa - ficha.getAlto())) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 2);
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionY().intValue() == baseNegativa) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 3);
                        y = ficha.getPosicionY();
                    } else { //moverse
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 3);
                    }
                    //horizontal
                } else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                    //cambiar del CII al CIII
                    if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 2);
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (14 + ficha.getAlto())) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == 14) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 2);
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else { //moverse
                        x = ficha.getPosicionX() - (ficha.getAlto() * 3);
                        y = ficha.getPosicionY();
                    }
                    //CIII    
                    //horizontal
                } else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    //girar
                    if (ficha.getPosicionX().intValue() == (baseNegativa - (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 2);
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (baseNegativa - ficha.getAlto())) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 2);
                    } else if (ficha.getPosicionX().intValue() == baseNegativa) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 3);
                    } else { //moverse
                        x = ficha.getPosicionX() + (ficha.getAlto() * 3);
                        y = ficha.getPosicionY();
                    }
                    //vertical
                } else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                    //cambiar del CIII al CIV
                    if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 3)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 2);
                    } else if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 4)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    } else if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 2);
                    } else { //moverse
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 3);
                    }
                    //CIV
                    //vertical
                } else if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                    if (ficha.getPosicionY().intValue() == (basePositiva + (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 2);
                    } else if (ficha.getPosicionY().intValue() == (basePositiva + ficha.getAlto())) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 2);
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionY().intValue() == basePositiva) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 3);
                        y = ficha.getPosicionY();
                    } else { //moverse
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 3);
                    }
                    //horizontal
                    //cambiar del CIV al CI 
                } else if (ficha.getPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 3)))) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 2);
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 4)))) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 2);
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else { //moverse
                        x = ficha.getPosicionX() + (ficha.getAlto() * 3);
                        y = ficha.getPosicionY();
                    }
                }
                System.out.println(x.intValue() + " " + y.intValue());
                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
                fichaJ = new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
                graphics2D.fill(fichaJ);
                break;
            case 4:
                ficha.setPosicionX(ficha.getNuevaPosicionX());
                ficha.setPosicionY(ficha.getNuevaPosicionY());
                //CI
                //vertical
                if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    //cambiar del CI al CII
                    if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * 3))) {
                        x = ficha.getPosicionX() - (ficha.getAlto());
                        y = ficha.getPosicionY() - (ficha.getAlto() * 3);
                    } else if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() - (ficha.getAlto());
                        y = ficha.getPosicionY() - (ficha.getAlto());
                    } else if (ficha.getPosicionY().intValue() == (14 + ficha.getAlto())) {
                        x = ficha.getPosicionX() - (ficha.getAlto());
                        y = ficha.getPosicionY() + (ficha.getAlto());
                    } else if (ficha.getPosicionY().intValue() == 14) {
                        x = ficha.getPosicionX() - (ficha.getAlto());
                        y = ficha.getPosicionY() + (ficha.getAlto() * 3);
                    } else { //moverse
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 4);
                    }
                    //horizontal
                } else if (ficha.getPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                    //Girar
                    if (ficha.getPosicionX().intValue() == (basePositiva.intValue() + (ficha.getAlto() * 3))) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (basePositiva.intValue() + (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 2);
                        y = ficha.getPosicionY() - (ficha.getAlto() * 2);
                    } else if (ficha.getPosicionX().intValue() == (basePositiva.intValue() + ficha.getAlto())) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 3);
                    } else if (ficha.getPosicionX().intValue() == basePositiva.intValue()) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 3);
                    } else { //moverse
                        x = ficha.getPosicionX() - (ficha.getAlto() * 4);
                        y = ficha.getPosicionY();
                    }
                    //CII
                    //vertical
                } else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    //girar
                    if (ficha.getPosicionY().intValue() == (baseNegativa - (ficha.getAlto() * 3))) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 3);
                    } else if (ficha.getPosicionY().intValue() == (baseNegativa - (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 2);
                        y = ficha.getPosicionY() + (ficha.getAlto() * 2);
                    } else if (ficha.getPosicionY().intValue() == (baseNegativa - ficha.getAlto())) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionY().intValue() == baseNegativa) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 4);
                        y = ficha.getPosicionY();
                    } else { //moverse
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 4);
                    }
                    //horizontal
                } else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                    //cambiar del CII al CIII
                    if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * 3))) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() - (ficha.getAlto());
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (14 + ficha.getAlto())) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == 14) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else { //moverse
                        x = ficha.getPosicionX() - (ficha.getAlto() * 4);
                        y = ficha.getPosicionY();
                    }
                    //CIII    
                    //horizontal
                } else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    //girar
                    if (ficha.getPosicionX().intValue() == (baseNegativa - (ficha.getAlto() * 3))) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (baseNegativa - (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 2);
                        y = ficha.getPosicionY() + (ficha.getAlto() * 2);
                    } else if (ficha.getPosicionX().intValue() == (baseNegativa - ficha.getAlto())) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 3);
                    } else if (ficha.getPosicionX().intValue() == baseNegativa) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 4);
                    } else { //moverse
                        x = ficha.getPosicionX() + (ficha.getAlto() * 4);
                        y = ficha.getPosicionY();
                    }
                    //vertical
                } else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                    //cambiar del CIII al CIV
                    if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 2)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 3);
                    } else if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 3)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 4)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 3);
                    } else { //moverse
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 4);
                    }
                    //CIV
                    //vertical
                } else if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                    if (ficha.getPosicionY().intValue() == (basePositiva + (ficha.getAlto() * 3))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 3);
                    } else if (ficha.getPosicionY().intValue() == (basePositiva + (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 2);
                        y = ficha.getPosicionY() - (ficha.getAlto() * 2);
                    } else if (ficha.getPosicionY().intValue() == (basePositiva + ficha.getAlto())) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionY().intValue() == basePositiva) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 4);
                        y = ficha.getPosicionY();
                    } else { //moverse
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 4);
                    }
                    //horizontal
                    //cambiar del CIV al CI 
                } else if (ficha.getPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 2)))) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 3)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 4)))) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() - (ficha.getAlto());
                    } else { //moverse
                        x = ficha.getPosicionX() + (ficha.getAlto() * 4);
                        y = ficha.getPosicionY();
                    }
                }
                System.out.println(x.intValue() + " " + y.intValue());
                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
                fichaJ = new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
                graphics2D.fill(fichaJ);
                break;
            case 10:
                ficha.setPosicionX(ficha.getNuevaPosicionX());
                ficha.setPosicionY(ficha.getNuevaPosicionY());
                //CI
                //vertical
                if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    //cambiar del CI al CII
                    switch (tablero.getCantidadCasillas()) {
                        case 2:
                            if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * 3))) {
                                x = ficha.getPosicionX() - (ficha.getAlto() * 3);
                                y = ficha.getPosicionY() + (ficha.getAlto());
                            } else if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * 2))) {
                                x = ficha.getPosicionX() - (ficha.getAlto() * 4);
                                y = ficha.getPosicionY() + (ficha.getAlto() * 2);
                            } else if (ficha.getPosicionY().intValue() == (14 + ficha.getAlto())) {
                                x = ficha.getPosicionX() - (ficha.getAlto() * 5);
                                y = ficha.getPosicionY() + (ficha.getAlto() * 3);
                            } else if (ficha.getPosicionY().intValue() == 14) {
                                x = ficha.getPosicionX() - (ficha.getAlto() * 5);
                                y = ficha.getPosicionY() + (ficha.getAlto() * 6);
                            } else { //moverse
                                //do nothing ;)
                                x = ficha.getPosicionX();
                                y = ficha.getPosicionY() - (ficha.getAlto() * 4);
                            }
                            break;
                        case 3:
                            if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * 4))) {
                                x = ficha.getPosicionX() - (ficha.getAlto() * 3);
                                y = ficha.getPosicionY() + (ficha.getAlto());
                            } else if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * 3))) {
                                x = ficha.getPosicionX() - (ficha.getAlto() * 3);
                                y = ficha.getPosicionY() + (ficha.getAlto());
                            } else if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * 2))) {
                                x = ficha.getPosicionX() - (ficha.getAlto() * 4);
                                y = ficha.getPosicionY() + (ficha.getAlto() * 2);
                            } else if (ficha.getPosicionY().intValue() == (14 + ficha.getAlto())) {
                                x = ficha.getPosicionX() - (ficha.getAlto() * 5);
                                y = ficha.getPosicionY() + (ficha.getAlto() * 3);
                            } else if (ficha.getPosicionY().intValue() == 14) {
                                x = ficha.getPosicionX() - (ficha.getAlto() * 5);
                                y = ficha.getPosicionY() + (ficha.getAlto() * 6);
                            } else { //moverse
                                //do nothing ;)
                                x = ficha.getPosicionX();
                                y = ficha.getPosicionY() - (ficha.getAlto() * 4);
                            }
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                    }
                    //horizontal
                } else if (ficha.getPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                    //Girar
                    if (ficha.getPosicionX().intValue() == (basePositiva.intValue() + (ficha.getAlto() * 3))) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 5);
                        y = ficha.getPosicionY() - (ficha.getAlto() * 2);
                    } else if (ficha.getPosicionX().intValue() == (basePositiva.intValue() + (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() - (ficha.getAlto());
                    } else if (ficha.getPosicionX().intValue() == (basePositiva.intValue() + ficha.getAlto())) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 2);
                        y = ficha.getPosicionY();
                    } else if (ficha.getPosicionX().intValue() == basePositiva.intValue()) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 2);
                        y = ficha.getPosicionY();
                    } else { //moverse
                        x = ficha.getPosicionX() - (ficha.getAlto() * 5);
                        y = ficha.getPosicionY() - (ficha.getAlto() * 3);
                    }

                    /*
                    Aqui va pos == pos
                     */
                    //CII
                    //vertical
                } else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    //girar
                    if (ficha.getPosicionY().intValue() == (baseNegativa - (ficha.getAlto() * 3))) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 3);
                    } else if (ficha.getPosicionY().intValue() == (baseNegativa - (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 2);
                        y = ficha.getPosicionY() + (ficha.getAlto() * 2);
                    } else if (ficha.getPosicionY().intValue() == (baseNegativa - ficha.getAlto())) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionY().intValue() == baseNegativa) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 4);
                        y = ficha.getPosicionY();
                    } else { //moverse
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 4);
                    }
                    //horizontal
                } else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                    //cambiar del CII al CIII
                    if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * 3))) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() - (ficha.getAlto());
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (14 + ficha.getAlto())) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == 14) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else { //moverse
                        x = ficha.getPosicionX() - (ficha.getAlto() * 4);
                        y = ficha.getPosicionY();
                    }

                    /*
                    
                     */
                    //CIII    
                    //horizontal
                } else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    //girar
                    if (ficha.getPosicionX().intValue() == (baseNegativa - (ficha.getAlto() * 3))) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (baseNegativa - (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 2);
                        y = ficha.getPosicionY() + (ficha.getAlto() * 2);
                    } else if (ficha.getPosicionX().intValue() == (baseNegativa - ficha.getAlto())) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 3);
                    } else if (ficha.getPosicionX().intValue() == baseNegativa) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 4);
                    } else { //moverse
                        x = ficha.getPosicionX() + (ficha.getAlto() * 4);
                        y = ficha.getPosicionY();
                    }
                    //vertical
                } else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                    //cambiar del CIII al CIV
                    if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 2)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 3);
                    } else if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 3)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 4)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 3);
                    } else { //moverse
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + (ficha.getAlto() * 4);
                    }

                    /*
                    
                     */
                    //CIV
                    //vertical
                } else if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                    if (ficha.getPosicionY().intValue() == (basePositiva + (ficha.getAlto() * 3))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 3);
                    } else if (ficha.getPosicionY().intValue() == (basePositiva + (ficha.getAlto() * 2))) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 2);
                        y = ficha.getPosicionY() - (ficha.getAlto() * 2);
                    } else if (ficha.getPosicionY().intValue() == (basePositiva + ficha.getAlto())) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionY().intValue() == basePositiva) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 4);
                        y = ficha.getPosicionY();
                    } else { //moverse
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - (ficha.getAlto() * 4);
                    }
                    //horizontal
                    //cambiar del CIV al CI 
                } else if (ficha.getPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 2)))) {
                        x = ficha.getPosicionX() + (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 3)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 4)))) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX() - (ficha.getAlto() * 3);
                        y = ficha.getPosicionY() - (ficha.getAlto());
                    } else { //moverse
                        x = ficha.getPosicionX() + (ficha.getAlto() * 4);
                        y = ficha.getPosicionY();
                    }

                    /*
                    
                     */
                }
                System.out.println(x.intValue() + " " + y.intValue());
                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
                fichaJ = new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
                graphics2D.fill(fichaJ);
                break;
        }
//        ArrayList<Double> posiciones = movimiento(ficha, tablero, movimiento);
//        if (null != ficha.getCuadrante()) {
//            switch (ficha.getCuadrante()) {
//                case 1:
//                    x = posiciones.get(0);
//                    y = posiciones.get(1);
//                    fichaJ = new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
//                    graphics2D.fill(fichaJ);
//                    break;
//                case 2:
//
//                    break;
//                case 3:
//
//                    break;
//                case 4:
//
//                    break;
//                default:
//                    break;
//            }
//        }
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(fichaJ);
    }

    public ArrayList<Double> movimiento(Ficha ficha, Tablero tablero, Integer movimiento) {
        Double basePositiva = (((((tablero.getCantidadCasillas() + 2)) * ficha.getAlto()) + (ficha.getAlto() * 0.6)) + 10) + (ficha.getAlto() / 2);
        Double baseNegativa = (((((tablero.getCantidadCasillas() + 2)) * ficha.getAncho()) + (ficha.getAncho() * 0.6)) + 10) - (ficha.getAncho() / 2);
        ArrayList<Double> coordenadas = new ArrayList<>();
        Double x = 0d, y = 0d;
        switch (movimiento) {
            /*
            if (vble1 >= vble2)
                System.out.println("La variable 1 es mayor o igual que la variable 2");
            if (vble1 <= vble2)
                System.out.println("La variable 1 es menor o igual que la variable 2");
             */
            case 1:
                ficha.setPosicionX(ficha.getNuevaPosicionX());
                ficha.setPosicionY(ficha.getNuevaPosicionY());
                //vertical
                if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    //cambiar del CI al CII
                    if (ficha.getPosicionY().intValue() == 16) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    }
                    //horizontal
                } else if (ficha.getNuevaPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    x = ficha.getPosicionX() - ficha.getAlto();
                    y = ficha.getPosicionY();
                    //vertical
                } else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    //cambiar del CI al CII
                    if (ficha.getPosicionY().intValue() == baseNegativa) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    }
                    //horizontal
                } else if (ficha.getNuevaPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                    if (ficha.getPosicionX() == 16) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY();
                    }
                    //horizontal
                } else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    //cambiar del CI al CII
                    if (ficha.getPosicionX().intValue() == baseNegativa) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    }
                    //vertical
                } else if (ficha.getNuevaPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                    if (ficha.getPosicionY().intValue() == (16 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    }
                    //vertical
                } else if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() >= baseNegativa.intValue()) {
                    //cambiar del CI al CII
                    if (ficha.getPosicionY().intValue() == basePositiva) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    }
                    //horizontal
                } else if (ficha.getNuevaPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    if (ficha.getNuevaPosicionX().intValue() == (16 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    }
                }

                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
//                }
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 10:

                break;
        }
        coordenadas.add(x);
        coordenadas.add(y);
        return coordenadas;
    }

    public Ellipse2D.Double mover(Ficha ficha, Tablero tablero, Integer movimiento) {
        Double basePositiva = (((((tablero.getCantidadCasillas() + 2)) * ancho) + (ancho * 0.6)) + 10) + (ancho / 2);
        Double baseNegativa = (((((tablero.getCantidadCasillas() + 2)) * alto) + (alto * 0.6)) + 10) - (alto / 2);

        Double x = 0d, y = 0d;
        for (int i = 0; i < movimiento; i++) {
            ficha.setPosicionX(ficha.getNuevaPosicionX());
            ficha.setPosicionY(ficha.getNuevaPosicionY());
            //vertical
            if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                //cambiar del CI al CII
                if (ficha.getPosicionY().intValue() == 14) {
                    x = ficha.getPosicionX() - ficha.getAncho();
                    y = ficha.getPosicionY();
                } else {
                    x = ficha.getPosicionX();
                    y = ficha.getPosicionY() - ficha.getAlto();
                }
                //horizontal
            } else if (ficha.getPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                if (ficha.getPosicionX().intValue() == basePositiva.intValue()) {
                    x = ficha.getPosicionX();
                    y = ficha.getPosicionY() - ficha.getAlto();
                } else {
                    x = ficha.getPosicionX() - ficha.getAncho();
                    y = ficha.getPosicionY();
                }
                //vertical
            } else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                //cambiar del CI al CII
                if (ficha.getPosicionY().intValue() == baseNegativa) {
                    x = ficha.getPosicionX() - ficha.getAlto();
                    y = ficha.getPosicionY();
                } else {
                    x = ficha.getPosicionX();
                    y = ficha.getPosicionY() + ficha.getAlto();
                }
                //horizontal
            } else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                if (ficha.getPosicionX().intValue() == 14) {
                    x = ficha.getPosicionX();
                    y = ficha.getPosicionY() + ficha.getAlto();
                } else {
                    x = ficha.getPosicionX() - ficha.getAlto();
                    y = ficha.getPosicionY();
                }
                //horizontal
            } else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                //cambiar del CI al CII
                if (ficha.getPosicionX().intValue() == baseNegativa) {
                    x = ficha.getPosicionX();
                    y = ficha.getPosicionY() + ficha.getAlto();
                } else {
                    x = ficha.getPosicionX() + ficha.getAlto();
                    y = ficha.getPosicionY();
                }
                //vertical
            } else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                if (ficha.getPosicionY().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                    x = ficha.getPosicionX() + ficha.getAlto();
                    y = ficha.getPosicionY();
                } else {
                    x = ficha.getPosicionX();
                    y = ficha.getPosicionY() + ficha.getAlto();
                }
                //vertical
            } else if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                //cambiar del CI al CII
                if (ficha.getPosicionY().intValue() == basePositiva) {
                    x = ficha.getPosicionX() + ficha.getAlto();
                    y = ficha.getPosicionY();
                } else {
                    x = ficha.getPosicionX();
                    y = ficha.getPosicionY() - ficha.getAlto();
                }
                //horizontal
            } else if (ficha.getPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                if (ficha.getPosicionX().intValue() == (14 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                    x = ficha.getPosicionX();
                    y = ficha.getPosicionY() - ficha.getAlto();
                } else {
                    x = ficha.getPosicionX() + ficha.getAlto();
                    y = ficha.getPosicionY();
                }
            }
//        System.out.println(x.intValue() + " " + y.intValue());
            ficha.setNuevaPosicionX(x);
            ficha.setNuevaPosicionY(y);
        }
        return new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));

//               }
    }

    public Ellipse2D.Double insertar(Jugador jugador, Ficha ficha, Tablero tablero){
        Double x = 0d, y = 0d;
//        Double a = ((((((tablero.getCantidadCasillas() + 2)) * ancho) + (ancho * 0.6)) + 10) + (ancho / 2) )- alto ;
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
        return new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
    }
    
    public void dibujaFicha(Graphics2D graphics2D, Ellipse2D.Double ficha, Jugador jugador){
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setColor(jugador.getColor());
        graphics2D.fill(ficha);
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(ficha);
    }
    /*
    
    public void moverFicha(Graphics2D graphics2D, Jugador jugador, Ficha ficha, Tablero tablero, Integer movimiento) {
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setColor(jugador.getColor());
//        Double basePositiva = (((((tablero.getCantidadCasillas() + 2)) * ancho) + (ancho * 0.6)) + 10) - (ancho / 2);
//        Double baseNegativa = (((((tablero.getCantidadCasillas() + 2)) * alto) + (alto * 0.6)) + 10) + (alto / 2);
        ubicaCuadrante(ficha, tablero);
        Ellipse2D.Double fichaJ = null;
        Double x, y;
        ArrayList<Double> posiciones = movimiento(ficha, tablero, movimiento);
        if (null != ficha.getCuadrante()) {
            switch (ficha.getCuadrante()) {
                case 1:
                    x = posiciones.get(0);
                    y = posiciones.get(1);
                    fichaJ = new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
                    graphics2D.fill(fichaJ);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                default:
                    break;
            }
        }
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(fichaJ);
    }
    
    
    public ArrayList<Double> movimiento(Ficha ficha, Tablero tablero, Integer movimiento) {
        Double basePositiva = (((((tablero.getCantidadCasillas() + 2)) * ficha.getAlto()) + (ficha.getAlto() * 0.6)) + 10) + (ficha.getAlto() / 2);
        Double baseNegativa = (((((tablero.getCantidadCasillas() + 2)) * ficha.getAncho()) + (ficha.getAncho() * 0.6)) + 10) - (ficha.getAncho() / 2);
        ArrayList<Double> coordenadas = new ArrayList<>();
        Double x = 0d, y = 0d;
        switch (movimiento) {
           
             
            case 1:
                ficha.setPosicionX(ficha.getNuevaPosicionX());
                ficha.setPosicionY(ficha.getNuevaPosicionY());
//                if (ficha.getCuadrante() == 1) {
                //vertical
                if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    //cambiar del CI al CII
                    if (ficha.getPosicionY().intValue() == 16) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    }
                    //horizontal
                } else if (ficha.getNuevaPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    x = ficha.getPosicionX() - ficha.getAlto();
                    y = ficha.getPosicionY();
                }
//                    ficha.setNuevaPosicionX(x);
//                    ficha.setNuevaPosicionY(y);
//                } else if (ficha.getCuadrante() == 2) {
                //vertical
                else if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() <= baseNegativa.intValue()) {
                    //cambiar del CI al CII
                    if (ficha.getPosicionY().intValue() == baseNegativa) {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    }
                    //horizontal
                } else if (ficha.getNuevaPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == baseNegativa.intValue()) {
                    if (ficha.getPosicionX() == 16) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else {
                        x = ficha.getPosicionX() - ficha.getAlto();
                        y = ficha.getPosicionY();
                    }
                }
//                    ficha.setNuevaPosicionX(x);
//                    ficha.setNuevaPosicionY(y);
//                } else if (ficha.getCuadrante() == 3) {
                //horizontal
                else if (ficha.getPosicionX().intValue() <= baseNegativa.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    //cambiar del CI al CII
                    if (ficha.getPosicionX().intValue() == baseNegativa) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    } else {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    }
                    //vertical
                } else if (ficha.getNuevaPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() >= basePositiva.intValue()) {
                    if (ficha.getPosicionY().intValue() == (16 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() + ficha.getAlto();
                    }
                }
//                    ficha.setNuevaPosicionX(x);
//                    ficha.setNuevaPosicionY(y);
//                } else if (ficha.getCuadrante() == 4) {
                //vertical
                else if (ficha.getPosicionX().intValue() == basePositiva.intValue() && ficha.getPosicionY().intValue() >= baseNegativa.intValue()) {
                    //cambiar del CI al CII
                    if (ficha.getPosicionY().intValue() == basePositiva) {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    } else {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    }
                    //horizontal
                } else if (ficha.getNuevaPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
                    if (ficha.getNuevaPosicionX().intValue() == (16 + (ficha.getAlto() * ((tablero.getCantidadCasillas() * 2) + 5)))) {
                        x = ficha.getPosicionX();
                        y = ficha.getPosicionY() - ficha.getAlto();
                    } else {
                        x = ficha.getPosicionX() + ficha.getAlto();
                        y = ficha.getPosicionY();
                    }
                }

                ficha.setNuevaPosicionX(x);
                ficha.setNuevaPosicionY(y);
//                }
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 10:

                break;
        }
        coordenadas.add(x);
        coordenadas.add(y);
        return coordenadas;
    }
     */
//    public Double movimientoY(Ficha ficha, Integer movimiento) {
//        Double basePositiva = (((((tablero.getCantidadCasillas() + 2)) * ancho) + (ancho * 0.6)) + 10) - (ancho / 2);
//        Double baseNegativa = (((((tablero.getCantidadCasillas() + 2)) * alto) + (alto * 0.6)) + 10) + (alto / 2);
//        Double y = 0d;
//        switch (movimiento) {
//            case 1:
//                ficha.setPosicionX(ficha.getNuevaPosicionX());
//                ficha.setPosicionY(ficha.getNuevaPosicionY());
//                //vertical
//                if (ficha.getPosicionX().intValue() == baseNegativa.intValue() && ficha.getPosicionY().intValue() <= basePositiva.intValue()) {
//                    //cambiar del CI al CII
//                    if (ficha.getPosicionY().intValue() == 16) {
//                        y = ficha.getPosicionY();
//                    } else {
//                        y = ficha.getPosicionY() - ficha.getAlto();
//                    }
//                    //horizontal
//                } else if (ficha.getNuevaPosicionX().intValue() >= basePositiva.intValue() && ficha.getPosicionY().intValue() == basePositiva.intValue()) {
//                    y = ficha.getPosicionY();
//                }
//                ficha.setNuevaPosicionY(y);
//                break;
//            case 2:
//
//                break;
//            case 3:
//
//                break;
//            case 4:
//
//                break;
//            case 10:
//
//                break;
//        }
//        return y;
//    }
//        switch (jugador.getNumJugador()) {
//            case 1:
//                x = ficha.getPosicionX()+ (ficha.getAncho() * (tablero.getCantidadCasillas() + 3.1));
//                y = ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 1.1));
//                ficha.setNuevaPosicionX(x);
//                ficha.setNuevaPosicionY(y);
//                fichaJ = new Ellipse2D.Double( x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
//                graphics2D.fill(fichaJ);
//                break;
//            case 2:
//                x = ficha.getPosicionX()+ (ficha.getAncho() * (tablero.getCantidadCasillas() + 2.1));
//                y = ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 4.1));
//                ficha.setNuevaPosicionX(x);
//                ficha.setNuevaPosicionY(y);
//                fichaJ = new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
//                graphics2D.fill(fichaJ);
//                break;
//            case 3:
//                x = ficha.getPosicionX()+ (ficha.getAncho() * (tablero.getCantidadCasillas() + 1.1));
//                y = ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 2.1));
//                ficha.setNuevaPosicionX(x);
//                ficha.setNuevaPosicionY(y);
//                fichaJ = new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
//                graphics2D.fill(fichaJ);
//                break;
//            case 4:
//                x = ficha.getPosicionX()+ (ficha.getAncho() * (tablero.getCantidadCasillas() + 4.1));
//                y = ficha.getPosicionY() + (ficha.getAlto() * (tablero.getCantidadCasillas() + 3.1));
//                ficha.setNuevaPosicionX(x);
//                ficha.setNuevaPosicionY(y);
//                fichaJ = new Ellipse2D.Double(x, y, ficha.getAncho() - (ficha.getAncho() / 4), ficha.getAlto() - (ficha.getAlto() / 4));
//                graphics2D.fill(fichaJ);
//                break;
//            default:
//                break;
//        }
//    graphics2D.setColor (Color.BLACK);
//
//    graphics2D.draw (fichaJ);
//}
//    public void insertarFichaJugador1(Graphics2D g2d, int x, int y, int ancho, int alto, Color color, int cantidad) {
//        g2d.setStroke(new BasicStroke(1));
//        g2d.setColor(color);
//        Ellipse2D.Double ficha = new Ellipse2D.Double(x + (ancho * (cantidad + 3.1)), y + (alto * (cantidad + 1.1)), ancho - (ancho / 4), alto - (alto / 4));//(x + (ancho / 7), y + (alto / 7), ancho - (ancho / 4), alto - (alto / 4));
//        g2d.fill(ficha);
//        g2d.setColor(Color.BLACK);
//        g2d.draw(ficha);
//    }
//
//    public void insertarFichaJugador3(Graphics2D g2d, int x, int y, int ancho, int alto, Color color, int cantidad) {
//        g2d.setStroke(new BasicStroke(1));
//        g2d.setColor(color);
//        Ellipse2D.Double ficha = new Ellipse2D.Double(x + (ancho * (cantidad + 1.1)), y + (alto * (cantidad + 2.1)), ancho - (ancho / 4), alto - (alto / 4));//(x + (ancho / 7), y + (alto / 7), ancho - (ancho / 4), alto - (alto / 4));
//        g2d.fill(ficha);
////        g2d.setColor(Color.BLACK);
//        g2d.draw(ficha);
//    }
//
//    public void insertarFichaJugador2(Graphics2D g2d, int x, int y, int ancho, int alto, Color color, int cantidad) {
//        g2d.setStroke(new BasicStroke(1));
//        g2d.setColor(color);
//        Ellipse2D.Double ficha = new Ellipse2D.Double(x + (ancho * (cantidad + 2.1)), y + (alto * (cantidad + 4.1)), ancho - (ancho / 4), alto - (alto / 4));//(x + (ancho / 7), y + (alto / 7), ancho - (ancho / 4), alto - (alto / 4));
//        g2d.fill(ficha);
////        g2d.setColor(Color.BLACK);
//        g2d.draw(ficha);
//    }
//
//    public void insertarFichaJugador4(Graphics2D g2d, int x, int y, int ancho, int alto, Color color, int cantidad) {
//        g2d.setStroke(new BasicStroke(1));
//        g2d.setColor(color);
//        Ellipse2D.Double ficha = new Ellipse2D.Double(x + (ancho * (cantidad + 4.1)), y + (alto * (cantidad + 3.1)), ancho - (ancho / 4), alto - (alto / 4));//(x + (ancho / 7), y + (alto / 7), ancho - (ancho / 4), alto - (alto / 4));
//        g2d.fill(ficha);
//        g2d.setColor(Color.BLACK);
//        g2d.draw(ficha);
//    }
//        String c1, c2, c3, c4;
//        if(ficha.getPosicionX() < cX && ficha.getPosicionY() < cY ){
//            if((((((tablero.getCantidadCasillas()-2))*ancho)+(ancho*0.6))+10) < ficha.getPosicionX()){
//                if((((((tablero.getCantidadCasillas()-2))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                    
//                } else if((((((tablero.getCantidadCasillas()-2))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                } else if((((((tablero.getCantidadCasillas()-1))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                } else if((((((tablero.getCantidadCasillas()))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                } else if((((((tablero.getCantidadCasillas()+1))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                }
//                
//            } else if((((((tablero.getCantidadCasillas()-1))*ancho)+(ancho*0.6))+10) < ficha.getPosicionX()){
//                if((((((tablero.getCantidadCasillas()-2))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                    
//                } else if((((((tablero.getCantidadCasillas()-2))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                } else if((((((tablero.getCantidadCasillas()-1))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                } else if((((((tablero.getCantidadCasillas()))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                } else if((((((tablero.getCantidadCasillas()+1))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                }
//            } else if((((((tablero.getCantidadCasillas()))*ancho)+(ancho*0.6))+10) < ficha.getPosicionX()){
//                if((((((tablero.getCantidadCasillas()-2))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                    
//                } else if((((((tablero.getCantidadCasillas()-2))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                } else if((((((tablero.getCantidadCasillas()-1))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                } else if((((((tablero.getCantidadCasillas()))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                } else if((((((tablero.getCantidadCasillas()+1))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                }
//            } else if((((((tablero.getCantidadCasillas()+11))*ancho)+(ancho*0.6))+10) < ficha.getPosicionX()){
//                if((((((tablero.getCantidadCasillas()-2))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                    
//                } else if((((((tablero.getCantidadCasillas()-2))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                } else if((((((tablero.getCantidadCasillas()-1))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                } else if((((((tablero.getCantidadCasillas()))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                } else if((((((tablero.getCantidadCasillas()+1))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                }
//            } else if (ficha.getPosicionX() < cX){
//                if((((((tablero.getCantidadCasillas()-2))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                    
//                } else if((((((tablero.getCantidadCasillas()-2))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                } else if((((((tablero.getCantidadCasillas()-1))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                } else if((((((tablero.getCantidadCasillas()))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                } else if((((((tablero.getCantidadCasillas()+1))*alto)+(alto*0.6))+10) < ficha.getPosicionY()){
//                
//                }
//            }
//        }else if(ficha.getPosicionX() < cX && ficha.getPosicionY() > cY ){
//            c2 = "C2";
//        }else if(ficha.getPosicionX() > cX && ficha.getPosicionY() < cY ){
//            c3 = "C4";
//        }else if(ficha.getPosicionX() > cX && ficha.getPosicionY() > cY ){
//            c3 = "C1";
//        }
}
