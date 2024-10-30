/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package vistas;


/**
 *
 * @author luisz
 */
public class FrmInicioAdministrador extends javax.swing.JFrame {

    /**
     * Creates new form FrmInicioAdministrador
     */
    public FrmInicioAdministrador() {
        initComponents();
        this.setExtendedState(FrmInicioAdministrador.MAXIMIZED_BOTH);
        this.setTitle("Administrador");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        CLIENTES = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        USUARIOS = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        CATEGORIA = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        PRODUCTO = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        CONFIG = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        REPORTES = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        SALIR = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        escritorio.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoadmin.jpg"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(1985, 676));
        jLabel1.setMinimumSize(new java.awt.Dimension(1985, 676));
        jLabel1.setPreferredSize(new java.awt.Dimension(3500, 4000));
        escritorio.add(jLabel1);
        jLabel1.setBounds(0, 0, 3500, 4000);

        getContentPane().add(escritorio, java.awt.BorderLayout.CENTER);

        CLIENTES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientes.png"))); // NOI18N
        CLIENTES.setMnemonic('f');
        CLIENTES.setText("CLIENTES");
        CLIENTES.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        openMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registrar.png"))); // NOI18N
        openMenuItem.setMnemonic('o');
        openMenuItem.setText("RegistoCli");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        CLIENTES.add(openMenuItem);

        menuBar.add(CLIENTES);

        USUARIOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registrar.png"))); // NOI18N
        USUARIOS.setMnemonic('e');
        USUARIOS.setText("USUARIOS");
        USUARIOS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        cutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registrar.png"))); // NOI18N
        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("RegistroUsu");
        cutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuItemActionPerformed(evt);
            }
        });
        USUARIOS.add(cutMenuItem);

        menuBar.add(USUARIOS);

        CATEGORIA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/categorias.png"))); // NOI18N
        CATEGORIA.setMnemonic('h');
        CATEGORIA.setText("CATEGORIA");
        CATEGORIA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        contentMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("CrearCategoria");
        contentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentMenuItemActionPerformed(evt);
            }
        });
        CATEGORIA.add(contentMenuItem);

        aboutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/categorias.png"))); // NOI18N
        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("AdministrarCategorias");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        CATEGORIA.add(aboutMenuItem);

        menuBar.add(CATEGORIA);

        PRODUCTO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/producto.png"))); // NOI18N
        PRODUCTO.setText("PRODUCTOS");
        PRODUCTO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/config.png"))); // NOI18N
        jMenuItem1.setText("RegistroProd");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        PRODUCTO.add(jMenuItem1);

        menuBar.add(PRODUCTO);

        CONFIG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/config.png"))); // NOI18N
        CONFIG.setText("CONFIG");
        CONFIG.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/config.png"))); // NOI18N
        jMenuItem2.setText("ConfiguracionEmpresa");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        CONFIG.add(jMenuItem2);

        menuBar.add(CONFIG);

        REPORTES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/report.png"))); // NOI18N
        REPORTES.setText("REPORTES");
        REPORTES.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        jMenuItem3.setText("Reportes");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        REPORTES.add(jMenuItem3);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        jMenuItem5.setText("ReporteVxF");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        REPORTES.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        jMenuItem6.setText("ReporteFecha");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        REPORTES.add(jMenuItem6);

        menuBar.add(REPORTES);

        SALIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar-sesion.png"))); // NOI18N
        SALIR.setText("Cerrar");
        SALIR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar-sesion.png"))); // NOI18N
        jMenuItem4.setText("CerrarSesion");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        SALIR.add(jMenuItem4);

        menuBar.add(SALIR);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        FrmRegistroClientes frmregistroclientes = new FrmRegistroClientes();
        escritorio.add(frmregistroclientes);
        frmregistroclientes.toFront();
        frmregistroclientes.setVisible(true);
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void cutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuItemActionPerformed
        FrmUsuarios frmusuarios = new FrmUsuarios();
        escritorio.add(frmusuarios);
        frmusuarios.toFront();
        frmusuarios.setVisible(true);
    }//GEN-LAST:event_cutMenuItemActionPerformed

    private void contentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentMenuItemActionPerformed
        FrmCategorias frmcat = new FrmCategorias();
        escritorio.add(frmcat);
        frmcat.toFront();
        frmcat.setVisible(true);
    }//GEN-LAST:event_contentMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        FrmAdministrarCategorias frmadm = new FrmAdministrarCategorias();
        escritorio.add(frmadm);
        frmadm.toFront();
        frmadm.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        FrmProductos frmproductos = new FrmProductos();
        escritorio.add(frmproductos);
        frmproductos.toFront();
        frmproductos.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        FrmConfig frmconfig = new FrmConfig();
        escritorio.add(frmconfig);
        frmconfig.toFront();
        frmconfig.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        FrmReportes frmreportes = new FrmReportes();
        escritorio.add(frmreportes);
        frmreportes.toFront();
        frmreportes.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
//        System.exit(0);
        FrmLogin fmrlog = new FrmLogin();
        fmrlog.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        ReporteVentasxVendedor frmvxv = new ReporteVentasxVendedor();
        escritorio.add(frmvxv);
        frmvxv.toFront();
        frmvxv.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        FrmReporteProducxFecha rpvxv = new FrmReporteProducxFecha();
        escritorio.add(rpvxv);
        rpvxv.toFront();
        rpvxv.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmInicioAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmInicioAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmInicioAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmInicioAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmInicioAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu CATEGORIA;
    private javax.swing.JMenu CLIENTES;
    private javax.swing.JMenu CONFIG;
    private javax.swing.JMenu PRODUCTO;
    private javax.swing.JMenu REPORTES;
    private javax.swing.JMenu SALIR;
    private javax.swing.JMenu USUARIOS;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    public static javax.swing.JDesktopPane escritorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    // End of variables declaration//GEN-END:variables

}
