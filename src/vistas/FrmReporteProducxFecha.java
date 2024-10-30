package vistas;

import com.toedter.calendar.JDateChooser;
import controller.Conexion;
import controller.VentaDAO;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Ventas;
import reportes.Reportes;

public class FrmReporteProducxFecha extends javax.swing.JInternalFrame {

    DefaultTableModel modelo = new DefaultTableModel();
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    VentaDAO vdao = new VentaDAO();

    public FrmReporteProducxFecha() {
        initComponents();
        this.setTitle("REPORTE DE PRODUCTO POR RANGO DE FECHA");
    }

    public void ListarVentasxProducto() {
        List<Ventas> ListVenxVend = vdao.ProdxFecha();
        modelo = (DefaultTableModel) TableVentasxFecha.getModel();
        modelo.setRowCount(0);
        Object[] ob = new Object[7];
        for (int i = 0; i < ListVenxVend.size(); i++) {
            ob[0] = ListVenxVend.get(i).getNombreProducto();
            ob[1] = ListVenxVend.get(i).getCantidad();
            ob[2] = ListVenxVend.get(i).getPrecio();
            ob[3] = ListVenxVend.get(i).getSubtotal();
            ob[4] = ListVenxVend.get(i).getIGV();
            ob[5] = ListVenxVend.get(i).getTotal();
            ob[6] = ListVenxVend.get(i).getFECHA();

            modelo.addRow(ob);
        }
        TableVentasxFecha.setModel(modelo);
    }

    private void ListarxFecha() {
        try {
            // OBTENER LAS FECHAS DEL RANGO
            java.util.Date fechaInicio = jdcFechaInicio.getDate();
            java.util.Date fechaFin = jdcFechaFin.getDate();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String fechaInicioFormat = formatter.format(fechaInicio);
            String fechaFinFormat = formatter.format(fechaFin);

            // OBTENER EL NOMBRE DEL PRODUCTO
            String nombreProducto = txtNombreProducto.getText(); // Aquí debes obtener el nombre del campo correspondiente

            // FILTRAMOS LA INFORMACION EN LA TABLA
            DefaultTableModel model = new DefaultTableModel();

            model.setColumnIdentifiers(new Object[]{"NombreProducto", "Cantidad","Precio","Subtotal","IGV", "Total", "FECHA"});
            String sql = "SELECT  "
                    + "    p.IDPRODUCTO as IdProducto,  "
                    + "    p.NOMBRE as NombreProducto, "
                    + "    d.CANTIDAD as Cantidad,  "
                    + "    p.PRECIO as Precio, "
                    + "    d.CANTIDAD * p.PRECIO as Subtotal, "
                    + "    v.IGV as IGV, "
                    + "    v.TOTAL as Total, "
                    + "    v.FECHA as FECHA "
                    + "    FROM ventas v  "
                    + "    INNER JOIN ventas_detalle d ON v.IDVENTA = d.IDVENTA "
                    + "    INNER JOIN productos p ON d.IDPRODUCTO = p.IDPRODUCTO WHERE v.FECHA BETWEEN ?  AND  ? ";

            // SELECT * FROM `usuarios` WHERE fecha BETWEEN '2016-03-20' AND '2016-20-31'
            // select b.VENDEDOR, a.*  from ventas a INNER JOIN usuarios b ON a.idusuario = b.IDUSUARIO WHERE fecha BETWEEN '06/11/2023'  AND '09/11/2023' ;
            // Agregamos la condición para el NombreVendedor si existe
            if (!nombreProducto.isEmpty()) {
                sql += "AND p.NOMBRE LIKE  ? ";
            }

            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            // Establecer los parámetros de fecha para el rango
            ps.setString(1, fechaInicioFormat);
            ps.setString(2, fechaFinFormat);

            // Establecer el parámetro para el NombreProducto si existe
            if (!nombreProducto.isEmpty()) {
                ps.setString(3, "%" + nombreProducto + "%");
            }

            rs = ps.executeQuery();
            // CARGAR LA DATA A LA TABLA
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("NombreProducto"), rs.getInt("Cantidad"),rs.getDouble("Precio"),
                    rs.getDouble("Subtotal"), rs.getDouble("IGV") ,rs.getDouble("Total"), rs.getString("FECHA")});
            }
            TableVentasxFecha.setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jdcFechaInicio = new com.toedter.calendar.JDateChooser();
        jdcFechaFin = new com.toedter.calendar.JDateChooser();
        btnVxF = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnPDF = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableVentasxFecha = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5), "ProductoxFecha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jdcFechaInicio.setDateFormatString("dd/MM/yyyy");
        jPanel4.add(jdcFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 160, -1));

        jdcFechaFin.setDateFormatString("dd/MM/yyyy");
        jPanel4.add(jdcFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 150, -1));

        btnVxF.setBackground(new java.awt.Color(204, 204, 204));
        btnVxF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnVxF.setText("BUSCAR");
        btnVxF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVxFActionPerformed(evt);
            }
        });
        jPanel4.add(btnVxF, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha Inicial");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha Final");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Producto");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));
        jPanel4.add(txtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 150, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 660, 160));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5), "REPORTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setToolTipText("");
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf.png"))); // NOI18N
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });
        jPanel1.add(btnPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 40, 30));

        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        jPanel1.add(btnExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 40, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Exportar PDF");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Exportar EXCEL");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo-producto.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 40, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Listar Productos");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 100, 230, 160));

        TableVentasxFecha.setBackground(new java.awt.Color(204, 204, 204));
        TableVentasxFecha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NombreProducto", "Cantidad", "Precio", "Subtotal", "IGV", "Total", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TableVentasxFecha);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 950, 310));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("REPORTE DE PRODUCTO POR RANGO DE FECHA");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVxFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVxFActionPerformed
        ListarxFecha();
    }//GEN-LAST:event_btnVxFActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ListarVentasxProducto();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        Reportes report = new Reportes();
        report.reporteProdxFecha();
    }//GEN-LAST:event_btnPDFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable TableVentasxFecha;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnVxF;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcFechaFin;
    private com.toedter.calendar.JDateChooser jdcFechaInicio;
    private javax.swing.JTextField txtNombreProducto;
    // End of variables declaration//GEN-END:variables
}
