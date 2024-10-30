package vistas;

import modelo.Config;
import controller.ProductosDAO;
import javax.swing.JOptionPane;

public class FrmConfig extends javax.swing.JInternalFrame {

    /**
     * Creates new form productos
     */
    public FrmConfig() {
        initComponents();
    }

    Config conf = new Config();
    ProductosDAO proDao = new ProductosDAO();

    public void ListarConfig() {
        conf = proDao.BuscarDatos();
        txtIdConfig.setText("" + conf.getId());
        txtRucConfig.setText("" + conf.getRuc());
        txtNombreConfig.setText("" + conf.getNombre());
        txtTelefonoConfig.setText("" + conf.getTelefono());
        txtDireccionConfig.setText("" + conf.getDireccion());
        txtMensaje.setText("" + conf.getRazon());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        btnActualizarConfig = new javax.swing.JButton();
        txtNombreConfig = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtTelefonoConfig = new javax.swing.JTextField();
        txtIdConfig = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtMensaje = new javax.swing.JTextField();
        txtDireccionConfig = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtRucConfig = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 153, 153));
        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("RUC");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("NOMBRE");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, -1, -1));

        btnActualizarConfig.setText("ACTUALIZAR");
        btnActualizarConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarConfigActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizarConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, 35));

        txtNombreConfig.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreConfigKeyTyped(evt);
            }
        });
        getContentPane().add(txtNombreConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 220, 30));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("DATOS DE LA EMPRESA");
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));

        txtTelefonoConfig.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoConfigKeyTyped(evt);
            }
        });
        getContentPane().add(txtTelefonoConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 218, 30));
        getContentPane().add(txtIdConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 263, 0, 0));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("TELÉFONO");
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, -1, -1));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("RAZON SOCIAL");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        txtMensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMensajeKeyTyped(evt);
            }
        });
        getContentPane().add(txtMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 400, 30));

        txtDireccionConfig.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionConfigKeyTyped(evt);
            }
        });
        getContentPane().add(txtDireccionConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 147, 30));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("DIRECCIÓN");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        txtRucConfig.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucConfigKeyTyped(evt);
            }
        });
        getContentPane().add(txtRucConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 147, 30));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/empresa_1.png"))); // NOI18N
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 450, 300));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/fondo2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarConfigActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtRucConfig.getText()) || !"".equals(txtNombreConfig.getText()) || !"".equals(txtTelefonoConfig.getText())
                || !"".equals(txtDireccionConfig.getText()) || !"".equals(txtMensaje.getText())) {

            conf.setRuc(txtRucConfig.getText());
            conf.setNombre(txtNombreConfig.getText());
            conf.setTelefono(txtTelefonoConfig.getText());
            conf.setDireccion(txtDireccionConfig.getText());
            conf.setRazon(txtMensaje.getText());
            conf.setId(Integer.parseInt(txtIdConfig.getText()));
            proDao.ModificarDatos(conf);
            JOptionPane.showMessageDialog(null, "Datos de la empresa modificado");
            ListarConfig();
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnActualizarConfigActionPerformed

    private void txtNombreConfigKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreConfigKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' '))
            evt.consume();
    }//GEN-LAST:event_txtNombreConfigKeyTyped

    private void txtTelefonoConfigKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoConfigKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (c < '0' || c > '9')
            evt.consume();
    }//GEN-LAST:event_txtTelefonoConfigKeyTyped

    private void txtMensajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMensajeKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' '))
            evt.consume();
    }//GEN-LAST:event_txtMensajeKeyTyped

    private void txtDireccionConfigKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionConfigKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' '))
            evt.consume();
    }//GEN-LAST:event_txtDireccionConfigKeyTyped

    private void txtRucConfigKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucConfigKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (c < '0' || c > '9')
            evt.consume();
    }//GEN-LAST:event_txtRucConfigKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizarConfig;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    public javax.swing.JTextField txtDireccionConfig;
    public javax.swing.JTextField txtIdConfig;
    public javax.swing.JTextField txtMensaje;
    public javax.swing.JTextField txtNombreConfig;
    public javax.swing.JTextField txtRucConfig;
    public javax.swing.JTextField txtTelefonoConfig;
    // End of variables declaration//GEN-END:variables
}
