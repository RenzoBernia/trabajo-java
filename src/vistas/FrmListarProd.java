package vistas;

import controller.Conexion;
import modelo.Productos;
import controller.ProductosDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmListarProd extends javax.swing.JInternalFrame {

    FrmProductos list;
    DefaultTableModel modelo = new DefaultTableModel();
    ProductosDAO proDao = new ProductosDAO();

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public FrmListarProd() {
        initComponents();
        this.setTitle("LISTAR PRODUCTOS");
    }

    public void ListarProductos() {
        List<Productos> ListarPro = proDao.ListarProductos();
        modelo = (DefaultTableModel) TableProducto.getModel();
        modelo.setRowCount(0);
        Object[] ob = new Object[6];
        for (int i = 0; i < ListarPro.size(); i++) {
//            ob[0] = ListarPro.get(i).getIDPRODUCTO();
            ob[0] = ListarPro.get(i).getCODIGO();
            ob[1] = ListarPro.get(i).getNOMBRE();
            ob[2] = ListarPro.get(i).getSTOCK();
            ob[3] = ListarPro.get(i).getPRECIO();
            ob[4] = ListarPro.get(i).getDESCRIPCION();
//            ob[6] = ListarPro.get(i).getCATEGORIAID();
            ob[5] = ListarPro.get(i).getCATEGORIA();
//            ob[7] = ListarPro.get(i).getESTADO();
            modelo.addRow(ob);
        }
        TableProducto.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnListProd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableProducto = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        rdbtnNombre = new javax.swing.JRadioButton();
        rdbtnCategoria = new javax.swing.JRadioButton();
        btnBuscar = new javax.swing.JButton();
        jlTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnListProd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnListProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo-producto.png"))); // NOI18N
        btnListProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListProdActionPerformed(evt);
            }
        });
        getContentPane().add(btnListProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        TableProducto.setBackground(new java.awt.Color(204, 204, 204));
        TableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "CANTIDAD", "PRECIO", "DESCRIPCION", "CATEGORIA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 790, 430));

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 260, 30));

        buttonGroup1.add(rdbtnNombre);
        rdbtnNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdbtnNombre.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnNombre.setText("Nombre");
        getContentPane().add(rdbtnNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, -1, -1));

        buttonGroup1.add(rdbtnCategoria);
        rdbtnCategoria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdbtnCategoria.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnCategoria.setText("Categoria");
        rdbtnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnCategoriaActionPerformed(evt);
            }
        });
        getContentPane().add(rdbtnCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, 30, 30));

        jlTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jlTitulo.setText("Buscar Productos");
        getContentPane().add(jlTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/fondo2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListProdActionPerformed
        // TODO add your handling code here:
        ListarProductos();
    }//GEN-LAST:event_btnListProdActionPerformed

    private void TableProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProductoMouseClicked

    }//GEN-LAST:event_TableProductoMouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed

    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        DefaultTableModel model = (DefaultTableModel) TableProducto.getModel();
        model.setRowCount(0);

        if (rdbtnNombre.isSelected()) {
            String nombre = txtBuscar.getText();

            String sql = "SELECT * FROM productos WHERE nombre LIKE '%" + nombre + "%' ";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                 //   int IDPRODUCTO = rs.getInt("idproducto");
                    String CODIGO = rs.getString("codigo");
                    String NOMBRE = rs.getString("nombre");
                    int STOCK = rs.getInt("stock");
                    double PRECIO = rs.getDouble("precio");
                    String DESCRIPCION = rs.getString("descripcion");
                    String CATEGORIA = rs.getString("categoria");

                    Object[] fila = new Object[6];
                //    fila[0] = IDPRODUCTO;
                    fila[0] = CODIGO;
                    fila[1] = NOMBRE;
                    fila[2] = STOCK;
                    fila[3] = PRECIO;
                    fila[4] = DESCRIPCION;
                    fila[5] = CATEGORIA;
                    model.addRow(fila);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e);
            }
        } else if (rdbtnCategoria.isSelected()) {
            String categoria = txtBuscar.getText();
            String sql = "SELECT * FROM productos WHERE categoria LIKE '%" + categoria + "%' ";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                //    int IDPRODUCTO = rs.getInt("idproducto");
                    String CODIGO = rs.getString("codigo");
                    String NOMBRE = rs.getString("nombre");
                    int STOCK = rs.getInt("stock");
                    double PRECIO = rs.getDouble("precio");
                    String DESCRIPCION = rs.getString("descripcion");
                    String CATEGORIA = rs.getString("categoria");

                    Object[] fila = new Object[6];
                //    fila[0] = IDPRODUCTO;
                    fila[0] = CODIGO;
                    fila[1] = NOMBRE;
                    fila[2] = STOCK;
                    fila[3] = PRECIO;
                    fila[4] = DESCRIPCION;
                    fila[5] = CATEGORIA;
                    model.addRow(fila);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccion la busqueda");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void rdbtnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbtnCategoriaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable TableProducto;
    private javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnListProd;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JRadioButton rdbtnCategoria;
    private javax.swing.JRadioButton rdbtnNombre;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
