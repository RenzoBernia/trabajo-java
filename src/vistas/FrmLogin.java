package vistas;

import modelo.Usuarios;
import controller.LoginDAO;
import javax.swing.JOptionPane;

public class FrmLogin extends javax.swing.JFrame {

    Usuarios lg = new Usuarios();
    LoginDAO login = new LoginDAO();
   
    public FrmLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtUsuario.setText("");
        txtContraseña.setText("");
        cbRol.setSelectedItem("");
    }

    public void validar() {
        String usuario = txtUsuario.getText();
        String contraseña = String.valueOf(txtContraseña.getPassword());
        String rol = cbRol.getSelectedItem().toString();

        if (!"".equals(usuario) || !"".equals(contraseña) || !"".equals(rol)) {
            //   lg = login.log(usuario, contraseña);
            lg = login.log(usuario, contraseña, rol);
            if(lg != null){
                Usuarios.user = lg;
            }
            
            try {
                if (lg.getROL().equals("Administrador")) {
                    FrmInicioAdministrador sis = new FrmInicioAdministrador();
                    sis.setVisible(true);
                    dispose();
                } else {
                    FrmInicioVendedor ven = new FrmInicioVendedor();
                    ven.toFront();
                    ven.setVisible(true);
                    dispose();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Usuario no registrado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Correo o la Contraseña incorrecta");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        cbRol = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo9-removebg-preview.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 350, 130));

        btnIniciar.setBackground(new java.awt.Color(204, 204, 204));
        btnIniciar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnIniciar.setText("Ingresar");
        btnIniciar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnIniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 110, 40));

        cbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Vendedor" }));
        getContentPane().add(cbRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 230, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contraseña");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, -1, -1));
        getContentPane().add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 226, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ROL");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Usuario");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 226, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondologin.PNG"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        validar();
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnIniciar;
    public javax.swing.JComboBox<String> cbRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JPasswordField txtContraseña;
    public javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
