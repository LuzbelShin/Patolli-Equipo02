/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantallas;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class frameCrearSala extends javax.swing.JFrame {

    public static String NOMBRE_SALA;
    /**
     * Creates new form frameConfiguracion
     */
    public frameCrearSala() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelNumJugadores = new javax.swing.JLabel();
        lblNombreSala = new javax.swing.JLabel();
        textFieldNombreSala = new javax.swing.JTextField();
        buttonGuardar = new javax.swing.JButton();
        comboBoxNumJugadores = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 300));
        setMinimumSize(new java.awt.Dimension(400, 300));
        setResizable(false);
        getContentPane().setLayout(null);

        labelNumJugadores.setText("Número de jugadores");
        getContentPane().add(labelNumJugadores);
        labelNumJugadores.setBounds(50, 70, 103, 14);

        lblNombreSala.setText("Nombre sala");
        getContentPane().add(lblNombreSala);
        lblNombreSala.setBounds(60, 110, 103, 14);

        textFieldNombreSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldNombreSalaActionPerformed(evt);
            }
        });
        getContentPane().add(textFieldNombreSala);
        textFieldNombreSala.setBounds(200, 110, 102, 20);

        buttonGuardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        buttonGuardar.setText("Guardar");
        buttonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(buttonGuardar);
        buttonGuardar.setBounds(130, 190, 147, 61);

        comboBoxNumJugadores.setModel(cargarComboBox());
        getContentPane().add(comboBoxNumJugadores);
        comboBoxNumJugadores.setBounds(200, 70, 102, 20);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/TItuloCrear_Sala.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(90, 10, 220, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoCrearPartida.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 400, 300);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldNombreSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldNombreSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldNombreSalaActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        if(!textFieldNombreSala.getText().isEmpty()){
            NOMBRE_SALA = textFieldNombreSala.getText();
            frameConfiguracionPartida pane = new frameConfiguracionPartida();
            pane.setVisible(true);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this, "Debes de ingresar un nombre para la sala.", "Información.", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private DefaultComboBoxModel<Integer> cargarComboBox() {
        DefaultComboBoxModel<Integer> defaultComboBoxModel = new DefaultComboBoxModel<>();
        defaultComboBoxModel.addElement(2);
        defaultComboBoxModel.addElement(3);
        defaultComboBoxModel.addElement(4);
        return defaultComboBoxModel;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JComboBox<Integer> comboBoxNumJugadores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelNumJugadores;
    private javax.swing.JLabel lblNombreSala;
    private javax.swing.JTextField textFieldNombreSala;
    // End of variables declaration//GEN-END:variables
}
