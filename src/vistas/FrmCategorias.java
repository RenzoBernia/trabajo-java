package vistas;

import controller.CategoriaDAO;
import javax.swing.JOptionPane;
import modelo.Producto_Categoria;

public class FrmCategorias extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmCategorias
     */
    public FrmCategorias() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        btnGuardarCat = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Categoria : ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));
        getContentPane().add(txtCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 210, -1));

        btnGuardarCat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardarCat.setText("Guardar");
        btnGuardarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCatActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Crear Categoria");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 0, 390, 274));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCatActionPerformed
        // TODO add your handling code here:
        Producto_Categoria categoria = new Producto_Categoria();
        CategoriaDAO catdao = new CategoriaDAO();

        if (txtCategoria.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingresar una categoria");
        } else {
            if (!catdao.ExisteCat(txtCategoria.getText())) {
                categoria.setCATEGORIA(txtCategoria.getText());
                categoria.setESTADO(1);
                if (catdao.GuardarCat(categoria)) {
                    JOptionPane.showMessageDialog(null, "Categoria Guardada");
                } else {
                    JOptionPane.showMessageDialog(null, "Categoria no se Guardo");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La Categoria ya existe");
            }
        }
        txtCategoria.setText("");
    }//GEN-LAST:event_btnGuardarCatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarCat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtCategoria;
    // End of variables declaration//GEN-END:variables

}
