package vistas;

//import Modelo.Eventos;
import modelo.Usuarios;
import controller.LoginDAO;
import java.util.HashSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Roles;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;

public class FrmUsuarios extends javax.swing.JInternalFrame {

    //Eventos event = new Eventos();
    DefaultTableModel modelo = new DefaultTableModel();
    Usuarios lg = new Usuarios();
    LoginDAO login = new LoginDAO();

    public FrmUsuarios() {
        initComponents();
        llenarRol();
        this.setTitle("Registro Usuarios");
    }

    private void llenarRol() {
        List<Roles> listaroles = login.Roles();
        cbRol.removeAllItems();
        for (int i = 0; i < listaroles.size(); i++) {
            cbRol.addItem(listaroles.get(i).getROL());
        }
    }

    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void ListarUsuarios() {
        List<Usuarios> Listar = login.ListarUsuarios();
        modelo = (DefaultTableModel) TableUsuarios.getModel();
        modelo.setRowCount(0);
        Object[] ob = new Object[7];
        for (int i = 0; i < Listar.size(); i++) {
            ob[0] = Listar.get(i).getIDUSUARIO();
            ob[1] = Listar.get(i).getVENDEDOR();
            ob[2] = Listar.get(i).getUSUARIO();
            ob[3] = Listar.get(i).getCONTRASEÑA();
            ob[4] = Listar.get(i).getTELEFONO();
            ob[5] = Listar.get(i).getDIRECCION();
            ob[6] = Listar.get(i).getROL();
            modelo.addRow(ob);
        }
        TableUsuarios.setModel(modelo);
    }

    private void nuevoUsuario() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtUsuario.setText("");
        txtPass.setText("");
    }

    private boolean validarCorreo(String correo) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(correo);
    }
    private boolean validarTelefono(String telefono) {
    // Expresión regular para validar un número de teléfono de 9 dígitos
    //String regex = "^\\d{9}$";
    //return telefono.matches(regex);
        IntegerValidator validator = IntegerValidator.getInstance();
        return validator.isValid(telefono) && telefono.length() == 9;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel33 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableUsuarios = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtDireccion = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        cbRol = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        btnModificar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtIdusuario = new javax.swing.JTextField();

        setBackground(new java.awt.Color(153, 153, 153));
        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("REGISTRO DE USUARIOS");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 240, 40));

        TableUsuarios.setBackground(new java.awt.Color(204, 204, 204));
        TableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Usario", "Contraseña", "Telefono", "Direccion", "Rol"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableUsuariosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(TableUsuarios);
        if (TableUsuarios.getColumnModel().getColumnCount() > 0) {
            TableUsuarios.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 1000, 350));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5), "DATOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 240, 30));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Contraseña");
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, -1, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Rol:");
        jPanel1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, -1, -1));

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 226, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Telefono");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Direccion");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, -1));

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 190, 30));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Nombre:");
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 226, 30));

        cbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Rol", "item 1", "item 2" }));
        cbRol.setToolTipText("Seleccionar Rol\nitem 1\nitem 2");
        cbRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRolActionPerformed(evt);
            }
        });
        jPanel1.add(cbRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 226, 30));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Usuario");
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPassKeyTyped(evt);
            }
        });
        jPanel1.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 190, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 730, 190));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5), "OPCIONES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(null);

        btnModificar.setBackground(new java.awt.Color(204, 204, 204));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Actualizar (2).png"))); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificar);
        btnModificar.setBounds(20, 130, 40, 40);

        btnListar.setBackground(new java.awt.Color(204, 204, 204));
        btnListar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registrar.png"))); // NOI18N
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });
        jPanel2.add(btnListar);
        btnListar.setBounds(20, 30, 40, 40);

        btnRegistrar.setBackground(new java.awt.Color(204, 204, 204));
        btnRegistrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/usuario.png"))); // NOI18N
        btnRegistrar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegistrar);
        btnRegistrar.setBounds(20, 80, 40, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Listar Usuarios");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(80, 40, 90, 15);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Registrar Usuario");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(80, 90, 120, 15);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Actualizar Usuario");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(80, 140, 109, 15);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 60, 220, 190));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/fondo2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 630));

        txtIdusuario.setText("jTextField1");
        txtIdusuario.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtIdusuario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtIdusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdusuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 32, -1, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' '))
            evt.consume();
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        
       
        if (txtNombre.getText().equals("") || txtUsuario.getText().equals("")
                || txtPass.getPassword().equals("") || txtTelefono.getText().equals("")
                || txtDireccion.getText().equals("") || cbRol.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null, "Todo los campos son requeridos");
        } else {
            
            if (!validarCorreo(txtUsuario.getText()) && !validarTelefono(txtTelefono.getText())) {
               
                JOptionPane.showMessageDialog(null, "El correo electrónico y el número de teléfono no son válidos.");
            } else if (!validarCorreo(txtUsuario.getText())) {
               
                JOptionPane.showMessageDialog(null, "El correo electrónico no es válido.");
            } else if (!validarTelefono(txtTelefono.getText())) {
                
                JOptionPane.showMessageDialog(null, "El número de teléfono no es válido.");
            } 
            
            
            String nom = txtNombre.getText();
            int tel = Integer.parseInt(txtTelefono.getText());
            String dir = txtDireccion.getText();
            String usuario = txtUsuario.getText();
            String pass = String.valueOf(txtPass.getPassword());
            
            String rol = cbRol.getSelectedItem().toString();
            
            System.out.println("ROL: " + rol);
            
             int rolesId = obtenerRolesID(rol);
            
            System.out.println("ROLid: " + rolesId);
            lg.setVENDEDOR(nom);
            lg.setTELEFONO(tel);
            lg.setDIRECCION(dir);
            lg.setUSUARIO(usuario);
            lg.setCONTRASEÑA(pass);
            lg.setROLESID(rolesId);
            lg.setROL(rol);
            
            if (login.Registrar(lg)) {
            JOptionPane.showMessageDialog(null, "Usuario Registrado");
            } else {
            JOptionPane.showMessageDialog(null, "Error al Registrar Usuario");
            }
            
            LimpiarTable();
            ListarUsuarios();
            nuevoUsuario();
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9')
            evt.consume();
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' '))
            evt.consume();
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyTyped
        // TODO add your handling code here:
        //      event.numberKeyPress(evt);
    }//GEN-LAST:event_txtPassKeyTyped

    private void TableUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableUsuariosMouseClicked
        int fila = TableUsuarios.getSelectedRow();
        if (fila >= 0) {

            int idusuario = (int) TableUsuarios.getValueAt(fila, 0);
            String nombre = (String) TableUsuarios.getValueAt(fila, 1);
            String usuario = (String) TableUsuarios.getValueAt(fila, 2);
            String contraseña = (String) TableUsuarios.getValueAt(fila, 3);
            int telefono = (int) TableUsuarios.getValueAt(fila, 4);
            String direccion = (String) TableUsuarios.getValueAt(fila, 5);         
            String rol = (String) TableUsuarios.getValueAt(fila, 6);

            txtIdusuario.setText(String.valueOf(idusuario));
            txtNombre.setText(nombre);
            txtTelefono.setText(String.valueOf(telefono));
            txtDireccion.setText(direccion);
            txtUsuario.setText(usuario);
            txtPass.setText(contraseña);
            cbRol.getSelectedItem().toString();
            //txtDireccion.setText(rol);
            
            btnRegistrar.setEnabled(false);
            btnModificar.setEnabled(true);
        }

    }//GEN-LAST:event_TableUsuariosMouseClicked

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        ListarUsuarios();
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        
        if (txtNombre.getText().equals("") || txtUsuario.getText().equals("")
                || txtPass.getPassword().equals("") || txtTelefono.getText().equals("")
                || txtDireccion.getText().equals("") || cbRol.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null, "Todo los campos son requeridos");
        } 
        
        // Verificar si hay un usuario seleccionado en la tabla
        int selectedRow = TableUsuarios.getSelectedRow();  
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un usuario para modificar.");
            return;  
        }

        // Obtener el ID del usuario a modificar
        int idUsuario = Integer.parseInt(TableUsuarios.getValueAt(selectedRow, 0).toString());
        lg.setIDUSUARIO(idUsuario);
            
        if (!validarCorreo(txtUsuario.getText()) && !validarTelefono(txtTelefono.getText())) {
            
            JOptionPane.showMessageDialog(null, "El correo electrónico y el número de teléfono no son válidos.");
        } else if (!validarCorreo(txtUsuario.getText())) {
            
            JOptionPane.showMessageDialog(null, "El correo electrónico no es válido.");
        } else if (!validarTelefono(txtTelefono.getText())) {
            
            JOptionPane.showMessageDialog(null, "El número de teléfono no es válido.");
        } 
            
        lg.setVENDEDOR(txtNombre.getText());
        lg.setUSUARIO(txtUsuario.getText());
        lg.setCONTRASEÑA(String.valueOf(txtPass.getPassword()));
        lg.setTELEFONO(Integer.parseInt(txtTelefono.getText()));
        lg.setDIRECCION(txtDireccion.getText());

        String rolSeleccionado = cbRol.getSelectedItem().toString();
        int rolesId = obtenerRolesID(rolSeleccionado);  // Método que retorna el rolesID
        lg.setROLESID(rolesId);
        lg.setROL(rolSeleccionado);

        boolean resultado = login.ModificarUsuario(lg);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Usuario modificado correctamente.");
            LimpiarTable();
            ListarUsuarios();   
            nuevoUsuario();
        } else {
          JOptionPane.showMessageDialog(null, "Error al modificar el usuario.");
         }
}

private int obtenerRolesID(String rol) {
    List<Roles> listaroles = login.Roles();  // Obtener la lista de roles
    for (Roles role : listaroles) {
        if (role.getROL().equals(rol)) {
            return role.getROLESID();  // Devuelve el ID del rol que coincide
        }
    }
    return 0;  // Devuelve 0 si no encuentra el rol

    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtIdusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdusuarioActionPerformed

    private void cbRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbRolActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable TableUsuarios;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegistrar;
    public javax.swing.JComboBox<String> cbRol;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane6;
    public javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIdusuario;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JPasswordField txtPass;
    public javax.swing.JTextField txtTelefono;
    public javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
//            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmUsuarios().setVisible(true);
//            }
//        });
//    }
}
