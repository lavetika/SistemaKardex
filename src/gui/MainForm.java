/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import conexion.Cliente;
import datos.BaseDeDatosKardex;
import dominio.Kardex;

/**
 *
 * @author Invitado
 */
public class MainForm extends javax.swing.JFrame implements GUIObserver {

    private final Cliente clienteServer;
    private BaseDeDatosKardex lstKardex;

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Sistema Kárdex");

        this.clienteServer = new Cliente(this);
        lstKardex = new BaseDeDatosKardex();
        lstKardex.inicializarKardex();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblInstruccion = new javax.swing.JLabel();
        lblTituloRecibido = new javax.swing.JLabel();
        txtRecibido = new javax.swing.JTextField();
        btnConectarServer = new javax.swing.JButton();
        lblEncabezado = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(487, 350));
        setMinimumSize(new java.awt.Dimension(487, 350));
        setPreferredSize(new java.awt.Dimension(487, 350));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInstruccion.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        lblInstruccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInstruccion.setText("Sistema Kardex");
        getContentPane().add(lblInstruccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 467, -1));

        lblTituloRecibido.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblTituloRecibido.setText("Recibido:");
        getContentPane().add(lblTituloRecibido, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 174, -1, 30));

        txtRecibido.setEditable(false);
        txtRecibido.setBackground(new java.awt.Color(255, 255, 255));
        txtRecibido.setFocusable(false);
        getContentPane().add(txtRecibido, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 204, 410, 36));

        btnConectarServer.setBackground(new java.awt.Color(255, 204, 0));
        btnConectarServer.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        btnConectarServer.setText("Conectar a servidor");
        btnConectarServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarServerActionPerformed(evt);
            }
        });
        getContentPane().add(btnConectarServer, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, -1, -1));

        lblEncabezado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FED148.jpeg"))); // NOI18N
        getContentPane().add(lblEncabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 467, 70));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blanco.jpg"))); // NOI18N
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 467, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConectarServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarServerActionPerformed
        new Thread(clienteServer).start();
        this.btnConectarServer.setEnabled(false);
    }//GEN-LAST:event_btnConectarServerActionPerformed

    private String buscarKardex(String alumno) {
        Kardex kardex = new Kardex(alumno.split("\\.")[1]);
        if (lstKardex.getLstKardex().contains(kardex)) {
            return lstKardex.getLstKardex().get(lstKardex.getLstKardex().indexOf(kardex)).toString();
        }
        return "NO.SE.HA.ENCONTRADO.NADA!";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConectarServer;
    private javax.swing.JLabel lblEncabezado;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblInstruccion;
    private javax.swing.JLabel lblTituloRecibido;
    private javax.swing.JTextField txtRecibido;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateAlumno(String contenido) {
        txtRecibido.setText(contenido);
        clienteServer.enviar(buscarKardex(contenido));
    }

    @Override
    public void updateMaestro(String contenido) {
        txtRecibido.setText(contenido);
        String[] array = contenido.split("\\.");

        String idMaestro = array[1];
        int idMateria = Integer.parseInt(array[2]);
        String idAlumno = array[3];
        int calificacion = Integer.parseInt(array[4]);

        lstKardex.actualizarCalificacion(idMaestro, idAlumno, idMateria, calificacion);
    }
}
