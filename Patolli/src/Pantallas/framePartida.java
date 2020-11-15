/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantallas;

import BO.*;

import Control.Control;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Bel
 */
public class framePartida extends javax.swing.JFrame {

    private Control cnvTablero;
    
    
    /**
     * Creates new form tablero
     */
    public framePartida() {            
//        cnvTablero  = new Control();
//        cnvTablero.setBounds(10, 10, 670, 670);
//        cnvTablero.setBackground(Color.decode("#FFFFFF"));
//        escogerTablero();
//        this.add(cnvTablero);
        initComponents();
        dibujaTablero();
        this.setTitle(RegistroDeJuego.SALA);
        centrarVentana();
        this.getContentPane().setBackground(Color.decode("#FFFFFF"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTablero = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnTirarCanias = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnInsertarFicha = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTablero.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblTablero.setForeground(new java.awt.Color(255, 0, 0));
        lblTablero.setText("Aqui va el tablero");

        jLabel1.setText("Turno de:");

        jTextField1.setEditable(false);

        btnTirarCanias.setText("Tirar cañas");
        btnTirarCanias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTirarCaniasActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnInsertarFicha.setText("Insertar ficha");
        btnInsertarFicha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarFichaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(947, 947, 947)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTirarCanias, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnInsertarFicha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTablero, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addGap(27, 27, 27)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                .addComponent(jTextField1)))))
                .addContainerGap(318, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTablero)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(btnInsertarFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTirarCanias, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(648, 648, 648))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTirarCaniasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTirarCaniasActionPerformed
        int aux = tirarCanias();
        switch(aux){
            case 0:
                JOptionPane.showMessageDialog(this, "Usted saco "+aux+" puntos\nNo puede moverse este turno.", "Resultado cañas", JOptionPane.PLAIN_MESSAGE);
                break;
            case 1:
                JOptionPane.showMessageDialog(this, "Usted saco "+aux+" puntos\nTiene derecho a ingresar una nueva ficha. (Aplican restricciones)", "Resultado cañas", JOptionPane.PLAIN_MESSAGE);
                Integer insertar = (Integer) JOptionPane.showConfirmDialog(this, "¿Desea ingresar una ficha?", "", JOptionPane.YES_NO_OPTION);
                if(insertar == 0){
                    setDatos();
                    cnvTablero.setOperacion(Operacion.Insertar);
                    cnvTablero.repaint();
                } else{
                    cnvTablero.setOperacion(Operacion.Mover);
                    cnvTablero.setMovimiento(1);
                    cnvTablero.repaint();
                }
                /*
                aqui va el if de una ficha a una casilla de la meta
                */
                break;
            case 2:
                JOptionPane.showMessageDialog(this, "Usted saco "+aux+" puntos\nAvance "+aux+" casillas.", "Resultado cañas", JOptionPane.PLAIN_MESSAGE);
                cnvTablero.setOperacion(Operacion.Mover);
                cnvTablero.setMovimiento(2);
                cnvTablero.repaint();
                /*
                que avance 2
                */
                break;
            case 3:
                JOptionPane.showMessageDialog(this, "Usted saco "+aux+" puntos\nAvance "+aux+" casillas.", "Resultado cañas", JOptionPane.PLAIN_MESSAGE);
                cnvTablero.setOperacion(Operacion.Mover);
                cnvTablero.setMovimiento(3);
                cnvTablero.repaint();
                /*
                que avance 3
                */
                break;
            case 4:
                JOptionPane.showMessageDialog(this, "Usted saco "+aux+" puntos\nAvance "+aux+" casillas.", "Resultado cañas", JOptionPane.PLAIN_MESSAGE);
                cnvTablero.setOperacion(Operacion.Mover);
                cnvTablero.setMovimiento(4);
                cnvTablero.repaint();
                /*
                que avance 4
                */
                break;
            case 5:
                JOptionPane.showMessageDialog(this, "Usted saco "+aux+" puntos\nAvance 10 casillas.", "Resultado cañas", JOptionPane.PLAIN_MESSAGE);
                cnvTablero.setOperacion(Operacion.Mover);
                cnvTablero.setMovimiento(10);
                cnvTablero.repaint();
                /*
                que avance 10
                */
                break;
            default:                
                break;
        }
    }//GEN-LAST:event_btnTirarCaniasActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        RegistroDeJuego pane = new RegistroDeJuego();
        pane.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnInsertarFichaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarFichaActionPerformed
        setDatos();
        cnvTablero.setOperacion(Operacion.Insertar);
        cnvTablero.repaint();
//        dibujaFicha();
//        canvasFicha.repaint();
    }//GEN-LAST:event_btnInsertarFichaActionPerformed
    
    /**
     * Método para centrar la ventana en la pantalla.
     */
    private void centrarVentana() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();

        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }

        setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
    }

    private void dibujaTablero(){
        lblTablero.setVisible(false);
        cnvTablero  = new Control();
        cnvTablero.setBounds(10, 10, 800, 800);
        cnvTablero.setBackground(Color.decode("#FFFFFF"));
        escogerTablero();
        this.add(cnvTablero);
    }

    private void setDatos(){
        Tablero auxTablero = setTablero();
        Jugador auxJugador = setJugador();
        Ficha auxFicha = setFicha();
        
        cnvTablero.setTablero(auxTablero);
        cnvTablero.setJugador(auxJugador);
        cnvTablero.setFicha(auxFicha);
    }
    
    private Tablero setTablero(){
        Tablero tablero = new Tablero();
        tablero.setCantidadCasillas(cnvTablero.getCasillas());
        return tablero;
    }
    
    private Jugador setJugador(){
        Jugador jugador = new Jugador();
        jugador.setColor(frameConfiguracionPartida.COLOR);
        jugador.setNumJugador(1);
        return jugador;
    }
    
    private Ficha setFicha(){
        Ficha ficha = new Ficha();
        ficha.setPosicionX(10d);
        ficha.setPosicionY(10d);
        ficha.setAncho(cnvTablero.getAncho());
        ficha.setAlto(cnvTablero.getAlto());
        return ficha;
    }
    
    

    private void escogerTablero(){
        switch (frameConfiguracionPartida.CASILLAS) {
            case 36:
                cnvTablero.setCantidadCasillas(2);
                cnvTablero.setDimensiones(40, 40);
                break;
            case 44:
                cnvTablero.setCantidadCasillas(3);
                cnvTablero.setDimensiones(40, 40);
                break;
            case 52:
                cnvTablero.setCantidadCasillas(4);
                cnvTablero.setDimensiones(40, 40);
                break;
            case 60:
                cnvTablero.setCantidadCasillas(5);
                cnvTablero.setDimensiones(40, 40);
                break;
        }
            
    }
    
    private int tirarCanias(){
        int movimientos = 0;
        for(int i = 0; i < 5; i++){
            Double aux = Math.random()*2;
            movimientos += aux.intValue();
        }        
        return movimientos;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInsertarFicha;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTirarCanias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblTablero;
    // End of variables declaration//GEN-END:variables
}