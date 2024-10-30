
package vistas;

import reportes.Reportes;

/**
 *
 * @author luisz
 */
public class FrmInicioVendedor extends javax.swing.JFrame {

    public FrmInicioVendedor() {
        initComponents();
        this.setExtendedState(FrmInicioVendedor.MAXIMIZED_BOTH);
        this.setTitle("Vendedor");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        cutmRegistrarVenta = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutmRegistrarCliente = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        escritorio.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/5026563.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        escritorio.add(jLabel1);
        jLabel1.setBounds(0, 0, 4541, 3000);

        getContentPane().add(escritorio, java.awt.BorderLayout.CENTER);

        fileMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Nventa.png"))); // NOI18N
        fileMenu.setMnemonic('f');
        fileMenu.setText("NUEVA VENTA");
        fileMenu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        cutmRegistrarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/compras.png"))); // NOI18N
        cutmRegistrarVenta.setMnemonic('o');
        cutmRegistrarVenta.setText("Venta");
        cutmRegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutmRegistrarVentaActionPerformed(evt);
            }
        });
        fileMenu.add(cutmRegistrarVenta);

        menuBar.add(fileMenu);

        editMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientes.png"))); // NOI18N
        editMenu.setMnemonic('e');
        editMenu.setText("CLIENTE");
        editMenu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        cutmRegistrarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registrar.png"))); // NOI18N
        cutmRegistrarCliente.setMnemonic('t');
        cutmRegistrarCliente.setText("Registrar");
        cutmRegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutmRegistrarClienteActionPerformed(evt);
            }
        });
        editMenu.add(cutmRegistrarCliente);

        menuBar.add(editMenu);

        helpMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/report.png"))); // NOI18N
        helpMenu.setMnemonic('h');
        helpMenu.setText("REPORTES");
        helpMenu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        contentMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("ReportexCliente");
        contentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(contentMenuItem);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        jMenuItem2.setText("ReportexVendedor");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        helpMenu.add(jMenuItem2);

        menuBar.add(helpMenu);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar-sesion.png"))); // NOI18N
        jMenu1.setText("Cerrar");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar-sesion.png"))); // NOI18N
        jMenuItem1.setText("cerrarsesion");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        menuBar.add(jMenu1);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cutmRegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutmRegistrarClienteActionPerformed
        // TODO add your handling code here:
        FrmRegistroClientes fmrCliente = new FrmRegistroClientes();
        escritorio.add(fmrCliente);
        fmrCliente.toFront();
        fmrCliente.setVisible(true);
    }//GEN-LAST:event_cutmRegistrarClienteActionPerformed

    private void cutmRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutmRegistrarVentaActionPerformed
        // TODO add your handling code here:
        FrmVenta fmrVenta = new FrmVenta();
        escritorio.add(fmrVenta);
        fmrVenta.toFront();
        fmrVenta.setVisible(true);
    }//GEN-LAST:event_cutmRegistrarVentaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        // System.exit(0);
        FrmLogin fmrlog = new FrmLogin();
        fmrlog.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void contentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentMenuItemActionPerformed
        Reportes report = new Reportes();
        report.reporteVentaxCliente();
    }//GEN-LAST:event_contentMenuItemActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Reportes report = new Reportes();
        report.reporteVentaxVendedor();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmInicioVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmInicioVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmInicioVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmInicioVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmInicioVendedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem cutmRegistrarCliente;
    private javax.swing.JMenuItem cutmRegistrarVenta;
    private javax.swing.JMenu editMenu;
    public static javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}
