package vistas;

import controller.CategoriaDAO;
import modelo.Productos;
import controller.Conexion;
import controller.ProductosDAO;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Producto_Categoria;

public class FrmProductos extends javax.swing.JInternalFrame {

    int obtenerIDcat = 0;

    Productos pro = new Productos();
    ProductosDAO proDao = new ProductosDAO();
    CategoriaDAO catDao = new CategoriaDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public FrmProductos() {
        initComponents();
        llenarCat();
        this.setTitle("Registrar Productos");
    }

    private void llenarCat() {
        List<Producto_Categoria> listcat = catDao.Categorias();
        cbCategoria.removeAllItems();
        for (int i = 0; i < listcat.size(); i++) {
            cbCategoria.addItem(listcat.get(i).getCATEGORIA());
        }
    }

    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void ListarProductos() {
        List<Productos> ListarPro = proDao.ListarProductos();
        modelo = (DefaultTableModel) TableProducto.getModel();
        modelo.setRowCount(0);
        Object[] ob = new Object[7];
        for (int i = 0; i < ListarPro.size(); i++) {
            ob[0] = ListarPro.get(i).getIDPRODUCTO();
            ob[1] = ListarPro.get(i).getCODIGO();
            ob[2] = ListarPro.get(i).getNOMBRE();
            ob[3] = ListarPro.get(i).getSTOCK();
            ob[4] = ListarPro.get(i).getPRECIO();
            ob[5] = ListarPro.get(i).getDESCRIPCION();
//            ob[6] = ListarPro.get(i).getCATEGORIAID();
            ob[6] = ListarPro.get(i).getCATEGORIA();
//            ob[7] = ListarPro.get(i).getESTADO();
            modelo.addRow(ob);
        }
        TableProducto.setModel(modelo);

    }

    private void LimpiarProductos() {
        txtIdproducto.setText("");
        txtCodigoPro.setText("");
        txtNomPro.setText("");
        txtCantPro.setText("");
        txtPrecioPro.setText("");
        txtDesProd.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCodigoPro = new javax.swing.JTextField();
        txtNomPro = new javax.swing.JTextField();
        txtCantPro = new javax.swing.JTextField();
        txtDesProd = new javax.swing.JTextField();
        txtPrecioPro = new javax.swing.JTextField();
        txtIdproducto = new javax.swing.JTextField();
        btnGuardarpro = new javax.swing.JButton();
        btnEditarpro = new javax.swing.JButton();
        btnEliminarPro = new javax.swing.JButton();
        btnNuevoProd = new javax.swing.JButton();
        cbCategoria = new javax.swing.JComboBox<>();
        btnListar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableProducto = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 153, 153));
        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Codigo:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Nombre:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Cantidad:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Descripcion:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Precio:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Categoria:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        txtCodigoPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoProActionPerformed(evt);
            }
        });
        txtCodigoPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoProKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoProKeyTyped(evt);
            }
        });
        jPanel2.add(txtCodigoPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 150, -1));

        txtNomPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomProKeyTyped(evt);
            }
        });
        jPanel2.add(txtNomPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 150, -1));

        txtCantPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantProKeyTyped(evt);
            }
        });
        jPanel2.add(txtCantPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 150, -1));

        txtDesProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDesProdKeyTyped(evt);
            }
        });
        jPanel2.add(txtDesProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 150, -1));

        txtPrecioPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioProKeyTyped(evt);
            }
        });
        jPanel2.add(txtPrecioPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 150, -1));
        jPanel2.add(txtIdproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 0, 0));

        btnGuardarpro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarpro.setText("Guardar");
        btnGuardarpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarproActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardarpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        btnEditarpro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnEditarpro.setText("Modificar");
        btnEditarpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarproActionPerformed(evt);
            }
        });
        jPanel2.add(btnEditarpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, -1, -1));

        btnEliminarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarPro.setText("Eliminar");
        btnEliminarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminarPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        btnNuevoProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo-producto.png"))); // NOI18N
        btnNuevoProd.setText("Nuevo");
        btnNuevoProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProdActionPerformed(evt);
            }
        });
        jPanel2.add(btnNuevoProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 110, -1));

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Categoria:", "Item2", "Item3", "Item4" }));
        cbCategoria.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbCategoriaMouseClicked(evt);
            }
        });
        cbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriaActionPerformed(evt);
            }
        });
        jPanel2.add(cbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 150, -1));

        btnListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/producto.png"))); // NOI18N
        btnListar.setText("Listar");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });
        jPanel2.add(btnListar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 340, 400));

        TableProducto.setBackground(new java.awt.Color(204, 204, 204));
        TableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO", "NOMBRE", "CANTIDAD", "PRECIO", "DESCRIPCION", "CATEGORIA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableProducto);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 720, 400));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("REGISTRO DE PRODUCTO");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/fondo2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoProKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9')
            evt.consume();
    }//GEN-LAST:event_txtCodigoProKeyTyped

    private void txtNomProKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomProKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' '))
            evt.consume();
    }//GEN-LAST:event_txtNomProKeyTyped

    private void txtCantProKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantProKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9')
            evt.consume();
    }//GEN-LAST:event_txtCantProKeyTyped

    private void txtDesProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesProdKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != ' '))
            evt.consume();
    }//GEN-LAST:event_txtDesProdKeyTyped

    private void txtPrecioProKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioProKeyTyped
        char c = evt.getKeyChar();
     if ((c < '0' || c > '9') && (c!= '.'))
            evt.consume();
    }//GEN-LAST:event_txtPrecioProKeyTyped

    private void btnGuardarproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarproActionPerformed
        if ("".equals(txtCodigoPro.getText()) || "".equals(txtNomPro.getText()) || "".equals(txtCantPro.getText())
                || "".equals(txtDesProd.getText()) || "".equals(txtPrecioPro.getText()) || "".equals(cbCategoria.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        } else {
            String codigo = txtCodigoPro.getText();
            String nombre = txtNomPro.getText();
            int stock = Integer.parseInt(txtCantPro.getText());
            double precio = Double.parseDouble(txtPrecioPro.getText());
            String descripcion = txtDesProd.getText();
            //           int categoriaid = Integer.parseInt(cbCategoria.getSelectedItem().toString());
            String categoria = cbCategoria.getSelectedItem().toString();
            //           int estado = Integer.parseInt(cbCategoria.getSelectedItem().toString());
            pro.setCODIGO(codigo);
            pro.setNOMBRE(nombre);
            pro.setSTOCK(stock);
            pro.setPRECIO(precio);
            pro.setDESCRIPCION(descripcion);
//            pro.setCATEGORIAID(categoriaid);
            pro.setCATEGORIA(categoria);
//            pro.setESTADO(estado);
            proDao.RegistrarProductos(pro);
            JOptionPane.showMessageDialog(null, "Productos Registrado");
            LimpiarTable();
            ListarProductos();
            LimpiarProductos();
            btnEditarpro.setEnabled(false);
            btnEliminarPro.setEnabled(false);
            btnGuardarpro.setEnabled(true);
        }
    }//GEN-LAST:event_btnGuardarproActionPerformed

    private void btnEditarproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarproActionPerformed

        if ("".equals(txtIdproducto.getText())) {
            JOptionPane.showMessageDialog(null, "Seleecione una fila");
        } else {
            if (!"".equals(txtCodigoPro.getText()) || !"".equals(txtNomPro.getText()) || !"".equals(txtCantPro.getText())
                    || !"".equals(txtDesProd.getText()) || !"".equals(txtPrecioPro.getText()) || !"".equals(cbCategoria.getSelectedItem())) {

                pro.setCODIGO(txtCodigoPro.getText());
                pro.setNOMBRE(txtNomPro.getText());
                pro.setSTOCK(Integer.parseInt(txtCantPro.getText()));
                pro.setPRECIO(Double.parseDouble(txtPrecioPro.getText()));
                pro.setDESCRIPCION(txtDesProd.getText());
//            pro.setCATEGORIAID((int) cbCategoria.getSelectedItem());
                pro.setCATEGORIA((String) cbCategoria.getSelectedItem());
                pro.setIDPRODUCTO(Integer.parseInt(txtIdproducto.getText()));

//            pro.setESTADO((int) cbCategoria.getSelectedItem());
                proDao.ModificarProductos(pro);
                JOptionPane.showMessageDialog(null, "Producto Modificado");
                LimpiarTable();
                ListarProductos();
                LimpiarProductos();
                btnEditarpro.setEnabled(false);
                btnEliminarPro.setEnabled(false);
                btnGuardarpro.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btnEditarproActionPerformed

    private void btnEliminarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdproducto.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int IDPRODUCTO = Integer.parseInt(txtIdproducto.getText());
                proDao.EliminarProductos(IDPRODUCTO);
                LimpiarTable();
                LimpiarProductos();
                ListarProductos();
                btnEditarpro.setEnabled(false);
                btnEliminarPro.setEnabled(false);
                btnGuardarpro.setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        }
    }//GEN-LAST:event_btnEliminarProActionPerformed

    private void btnNuevoProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProdActionPerformed
        // TODO add your handling code here:
        LimpiarProductos();
        btnEditarpro.setEnabled(false);
        btnEliminarPro.setEnabled(false);
        btnGuardarpro.setEnabled(true);
    }//GEN-LAST:event_btnNuevoProdActionPerformed

    private void TableProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProductoMouseClicked
        // TODO add your handling code here:
        btnEditarpro.setEnabled(true);
        btnEliminarPro.setEnabled(true);
        btnGuardarpro.setEnabled(true);
        int fila = TableProducto.rowAtPoint(evt.getPoint());
        txtIdproducto.setText(TableProducto.getValueAt(fila, 0).toString());
        pro = proDao.BuscarId(Integer.parseInt(txtIdproducto.getText()));
        txtCodigoPro.setText(pro.getCODIGO());
        txtNomPro.setText(pro.getNOMBRE());
        txtCantPro.setText("" + pro.getSTOCK());
        txtDesProd.setText("" + pro.getDESCRIPCION());
        txtPrecioPro.setText("" + pro.getPRECIO());
//        cbCategoria.setToolTipText("" + pro.getCATEGORIAID());
        cbCategoria.setToolTipText("" + pro.getCATEGORIA());
//        cbCategoria.setToolTipText("" + pro.getESTADO());
    }//GEN-LAST:event_TableProductoMouseClicked

    private void txtCodigoProKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCodigoProKeyPressed

    private void txtCodigoProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoProActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoProActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        // TODO add your handling code here:
        ListarProductos();
    }//GEN-LAST:event_btnListarActionPerformed

    private void cbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCategoriaActionPerformed

    private void cbCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbCategoriaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCategoriaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable TableProducto;
    public javax.swing.JButton btnEditarpro;
    public javax.swing.JButton btnEliminarPro;
    public javax.swing.JButton btnGuardarpro;
    private javax.swing.JButton btnListar;
    public javax.swing.JButton btnNuevoProd;
    public javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField txtCantPro;
    public javax.swing.JTextField txtCodigoPro;
    public javax.swing.JTextField txtDesProd;
    public javax.swing.JTextField txtIdproducto;
    public javax.swing.JTextField txtNomPro;
    public javax.swing.JTextField txtPrecioPro;
    // End of variables declaration//GEN-END:variables

    private int CATEGORIAID() {
        String sql = "SELECT * FROM producto_categoria WHERE descripcion = '" + this.cbCategoria.getSelectedItem() + "'";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                obtenerIDcat = rs.getInt("categoriaid");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ide de la categoria");
        }
        return obtenerIDcat;
    }

}
