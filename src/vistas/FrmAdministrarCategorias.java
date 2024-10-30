package vistas;

import controller.CategoriaDAO;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Producto_Categoria;

public class FrmAdministrarCategorias extends javax.swing.JInternalFrame {

    DefaultTableModel modelo = new DefaultTableModel();
    CategoriaDAO catdao = new CategoriaDAO();
    Producto_Categoria cat = new Producto_Categoria();
    private int categoriaid;

    public FrmAdministrarCategorias() {
        initComponents();
        this.setTitle("Administrar Categorias");
    }

    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void ListarCategoria() {
        List<Producto_Categoria> ListarCat = catdao.Categorias();
        modelo = (DefaultTableModel) TableCategoria.getModel();
        modelo.setRowCount(0);
        Object[] ob = new Object[3];
        for (int i = 0; i < ListarCat.size(); i++) {
            ob[0] = ListarCat.get(i).getCATEGORIAID();
            ob[1] = ListarCat.get(i).getCATEGORIA();
            ob[2] = ListarCat.get(i).getESTADO();
            modelo.addRow(ob);
        }
        TableCategoria.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TableCategoria = new javax.swing.JTable();
        btnEliminarCat = new javax.swing.JButton();
        btnModificarCat = new javax.swing.JButton();
        btnListarCat = new javax.swing.JButton();
        txtCategoria = new javax.swing.JTextField();
        txtIdCat = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CATEGORIAID", "CATEGORIA", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableCategoriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableCategoria);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 460, 360));

        btnEliminarCat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEliminarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminarCat.setText("Eliminar");
        btnEliminarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCatActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminarCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, 130, -1));

        btnModificarCat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnModificarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Actualizar (2).png"))); // NOI18N
        btnModificarCat.setText("Modificar");
        btnModificarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCatActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificarCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 130, -1));

        btnListarCat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnListarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/categorias.png"))); // NOI18N
        btnListarCat.setText("Listar");
        btnListarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarCatActionPerformed(evt);
            }
        });
        getContentPane().add(btnListarCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 260, 130, -1));
        getContentPane().add(txtCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 250, -1));
        getContentPane().add(txtIdCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 0, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Categoria : ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Administrar Categorias");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 680, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TableCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCategoriaMouseClicked
        // TODO add your handling code here:
        btnEliminarCat.setEnabled(true);
        btnModificarCat.setEnabled(true);
        btnListarCat.setEnabled(true);
        int fila = TableCategoria.rowAtPoint(evt.getPoint());
        txtIdCat.setText(TableCategoria.getValueAt(fila, 0).toString());
        cat = catdao.BuscarId(Integer.parseInt(txtIdCat.getText()));
        txtCategoria.setText("" + cat.getCATEGORIA());

    }//GEN-LAST:event_TableCategoriaMouseClicked

    private void btnListarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarCatActionPerformed
        // TODO add your handling code here:
        ListarCategoria();
    }//GEN-LAST:event_btnListarCatActionPerformed

    private void btnModificarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCatActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdCat.getText())) {
            JOptionPane.showMessageDialog(null, "Seleecione la categoria");
        } else {
            if (!"".equals(txtCategoria.getText())) {
                cat.setCATEGORIA(txtCategoria.getText());
                cat.setCATEGORIAID(Integer.parseInt(txtIdCat.getText()));
                catdao.ActualizarCat(cat);
                JOptionPane.showMessageDialog(null, "Categoria Modificado");
                LimpiarTable();
                ListarCategoria();
            }
        }
    }//GEN-LAST:event_btnModificarCatActionPerformed

    private void btnEliminarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCatActionPerformed
        // TODO add your handling code here:
        if ((!txtCategoria.getText().isEmpty())) {
            cat.setCATEGORIA(txtCategoria.getText());
            if (catdao.EliminarCat(cat.getCATEGORIA())) {
                JOptionPane.showMessageDialog(null, "Se Elimino la categoria");
                txtCategoria.setText("");
                this.ListarCategoria();
            } else {
                JOptionPane.showMessageDialog(null, "Error al elimnar la categoria");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una categoria");
        }
    }//GEN-LAST:event_btnEliminarCatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableCategoria;
    private javax.swing.JButton btnEliminarCat;
    private javax.swing.JButton btnListarCat;
    private javax.swing.JButton btnModificarCat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtIdCat;
    // End of variables declaration//GEN-END:variables
}
