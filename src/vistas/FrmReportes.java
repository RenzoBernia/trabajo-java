package vistas;

import reportes.Reportes;

public class FrmReportes extends javax.swing.JInternalFrame {

    public FrmReportes() {
        initComponents();
        this.setTitle("Reportes");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnReporteProducto = new javax.swing.JButton();
        btnReporteCategoria = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnReporteVentaxCliente = new javax.swing.JButton();
        btnReporteVentasxVendedor = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("REPORTES");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5), "Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setToolTipText("");
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnReporteProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnReporteProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        btnReporteProducto.setText("Producto");
        btnReporteProducto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnReporteProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteProductoActionPerformed(evt);
            }
        });
        jPanel2.add(btnReporteProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 110, 30));

        btnReporteCategoria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnReporteCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        btnReporteCategoria.setText("Categoria");
        btnReporteCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteCategoriaActionPerformed(evt);
            }
        });
        jPanel2.add(btnReporteCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 200, 130));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5), "Ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setToolTipText("");
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnReporteVentaxCliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnReporteVentaxCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        btnReporteVentaxCliente.setText("Cliente");
        btnReporteVentaxCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnReporteVentaxCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteVentaxClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnReporteVentaxCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, 30));

        btnReporteVentasxVendedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnReporteVentasxVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        btnReporteVentasxVendedor.setText("Vendedor");
        btnReporteVentasxVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteVentasxVendedorActionPerformed(evt);
            }
        });
        jPanel1.add(btnReporteVentasxVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 140, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 220, 160));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5), "DatosPersonales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setToolTipText("");
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        jButton1.setText("Clientes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        btnUsuarios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        btnUsuarios.setText("Usuarios");
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });
        jPanel3.add(btnUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 180, 140));

        jButton2.setText("REPORTEPDF");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReporteProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteProductoActionPerformed
        Reportes report = new Reportes();
        report.reporteProducto();
    }//GEN-LAST:event_btnReporteProductoActionPerformed

    private void btnReporteCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteCategoriaActionPerformed
        Reportes report = new Reportes();
        report.reporteCategoria();
    }//GEN-LAST:event_btnReporteCategoriaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Reportes report = new Reportes();
        report.reporteClientes();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        Reportes report = new Reportes();
        report.reporteUsuarios();
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnReporteVentaxClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteVentaxClienteActionPerformed
        Reportes report = new Reportes();
        report.reporteVentaxCliente();
    }//GEN-LAST:event_btnReporteVentaxClienteActionPerformed

    private void btnReporteVentasxVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteVentasxVendedorActionPerformed
        Reportes report = new Reportes();
        report.reporteVentaxVendedor();
    }//GEN-LAST:event_btnReporteVentasxVendedorActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Reportes report = new Reportes();
        report.generarReporte();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReporteCategoria;
    private javax.swing.JButton btnReporteProducto;
    private javax.swing.JButton btnReporteVentasxVendedor;
    private javax.swing.JButton btnReporteVentaxCliente;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
