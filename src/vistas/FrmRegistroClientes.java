package vistas;

import controller.ClienteDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Clientes;

public class FrmRegistroClientes extends javax.swing.JInternalFrame {

    Clientes cl = new Clientes();
    ClienteDAO client = new ClienteDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    

    public FrmRegistroClientes() {
        initComponents();
        this.setTitle("Registro de Clientes");
    }

    public void ListarCliente() {
        List<Clientes> ListarCl = client.ListarCliente();
        modelo = (DefaultTableModel) tableClientes.getModel();
        modelo.setRowCount(0);
        Object[] ob = new Object[6];
        for (int i = 0; i < ListarCl.size(); i++) {
            ob[0] = ListarCl.get(i).getIDCLIENTE();
            ob[1] = ListarCl.get(i).getDNI();
            ob[2] = ListarCl.get(i).getNOMBRECLIENTE();
            ob[3] = ListarCl.get(i).getTELEFONO();
            ob[4] = ListarCl.get(i).getDIRECCION();
            ob[5] = ListarCl.get(i).getMAIL();
            modelo.addRow(ob);
        }
        tableClientes.setModel(modelo);
    }

    public void LimpiarCliente() {
        txtIdCliente.setText("");
        txtClienteDNI.setText("");
        txtClienteNombre.setText("");
        txtClienteTelefono.setText("");
        txtClienteDireccion.setText("");
        txtMail.setText("");
    }

    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtIdCliente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnNuevoCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtClienteNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtClienteDNI = new javax.swing.JTextField();
        txtClienteDireccion = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        txtClienteTelefono = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        txtMail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 153, 153));
        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(txtIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 254, 10, 0));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("DNI:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        btnNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoCliente.setText("NUEVO");
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, 120, 30));

        tableClientes.setBackground(new java.awt.Color(204, 204, 204));
        tableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDCLIENTE", "DNI", "NOMBRECLIENTE", "TELEFONO", "DIRECCION", "MAIL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableClientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 720, 380));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Registro de Cliente");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        txtClienteNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClienteNombreKeyTyped(evt);
            }
        });
        getContentPane().add(txtClienteNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 250, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NOMBRE:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/usuario.png"))); // NOI18N
        jButton1.setText("LISTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 98, 30));

        txtClienteDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClienteDNIKeyTyped(evt);
            }
        });
        getContentPane().add(txtClienteDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 250, -1));

        txtClienteDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClienteDireccionKeyTyped(evt);
            }
        });
        getContentPane().add(txtClienteDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 250, -1));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 122, 30));

        txtClienteTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClienteTelefonoKeyTyped(evt);
            }
        });
        getContentPane().add(txtClienteTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 250, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TELEFONO:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 140, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("DIRECCION:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/registrar.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 140, 31));
        getContentPane().add(txtMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 250, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("E-MAIL: ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        // TODO add your handling code here:
        LimpiarCliente();
        btnRegistrar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnNuevoCliente.setEnabled(true);
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void txtClienteNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteNombreKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' '))
            evt.consume();
    }//GEN-LAST:event_txtClienteNombreKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ListarCliente();
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnNuevoCliente.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtClienteDNIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteDNIKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9')
            evt.consume();
    }//GEN-LAST:event_txtClienteDNIKeyTyped

    private void txtClienteDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteDireccionKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' '))
            evt.consume();
    }//GEN-LAST:event_txtClienteDireccionKeyTyped

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdCliente.getText())) {
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        } else {
            if (!"".equals(txtClienteDNI.getText()) || !"".equals(txtClienteNombre.getText())
                    || !"".equals(txtClienteTelefono.getText()) || !"".equals(txtClienteDireccion.getText()) || !"".equals(txtMail.getText())) {
                cl.setDNI(Integer.parseInt(txtClienteDNI.getText()));
                cl.setNOMBRECLIENTE(txtClienteNombre.getText());
                cl.setTELEFONO(Integer.parseInt(txtClienteTelefono.getText()));
                cl.setDIRECCION(txtClienteDireccion.getText());
                cl.setMAIL(txtMail.getText());
                cl.setIDCLIENTE(Integer.parseInt(txtIdCliente.getText()));
                client.ModificarCliente(cl);
                JOptionPane.showMessageDialog(null, "Cliente Modificado");
                btnModificar.setEnabled(false);
                LimpiarTable();
                LimpiarCliente();
                ListarCliente();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtClienteTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteTelefonoKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9')
            evt.consume();
    }//GEN-LAST:event_txtClienteTelefonoKeyTyped

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:

        if (!"".equals(txtIdCliente.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdCliente.getText());
                client.EliminarCliente(id);
                LimpiarTable();
                LimpiarCliente();
                ListarCliente();
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtClienteDNI.getText()) || !"".equals(txtClienteNombre.getText()) || !"".equals(txtClienteTelefono.getText()) 
                || !"".equals(txtClienteDireccion.getText())|| !"".equals(txtMail.getText())) {
            cl.setDNI(Integer.parseInt(txtClienteDNI.getText()));
            cl.setNOMBRECLIENTE(txtClienteNombre.getText());
            cl.setTELEFONO(Integer.parseInt(txtClienteTelefono.getText()));
            cl.setDIRECCION(txtClienteDireccion.getText());
            cl.setMAIL(txtMail.getText());
            client.RegistrarCliente(cl);
            LimpiarTable();
            LimpiarCliente();
            ListarCliente();
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnNuevoCliente.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Cliente Registrado");
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void tableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClientesMouseClicked
        // TODO add your handling code here:

        int fila = tableClientes.getSelectedRow();
        if (fila >= 0) {

            int idcliente = (int) tableClientes.getValueAt(fila, 0);
            int dni = (int) tableClientes.getValueAt(fila, 1);
            String nombrecliente = (String) tableClientes.getValueAt(fila, 2);
            int telefono = (int) tableClientes.getValueAt(fila, 3);
            String direccion = (String) tableClientes.getValueAt(fila, 4);
            String mail = (String) tableClientes.getValueAt(fila, 5);

            txtIdCliente.setText(String.valueOf(idcliente));
            txtClienteDNI.setText(String.valueOf(dni));
            txtClienteNombre.setText(nombrecliente);
            txtClienteTelefono.setText(String.valueOf(telefono));
            txtClienteDireccion.setText(direccion);
            txtMail.setText(mail);

            btnRegistrar.setEnabled(false);
            btnEliminar.setEnabled(true);
            btnModificar.setEnabled(true);
        }

    }//GEN-LAST:event_tableClientesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnNuevoCliente;
    public javax.swing.JButton btnRegistrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tableClientes;
    public javax.swing.JTextField txtClienteDNI;
    public javax.swing.JTextField txtClienteDireccion;
    public javax.swing.JTextField txtClienteNombre;
    public javax.swing.JTextField txtClienteTelefono;
    private javax.swing.JTextField txtIdCliente;
    public javax.swing.JTextField txtMail;
    // End of variables declaration//GEN-END:variables
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        <editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmMenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmMenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmMenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmMenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        </editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmRegistroClientes().setVisible(true);
//            }
//        });
//    }
    
}
