package vistas;

import com.itextpdf.text.log.Logger;
import controller.Conexion;
import controller.VentaDAO;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Ventas;
import reportes.Reportes;

public class ReporteVentasxVendedor extends javax.swing.JInternalFrame {

    DefaultTableModel modelo = new DefaultTableModel();
    VentaDAO Vdao = new VentaDAO();

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public ReporteVentasxVendedor() {
        initComponents();
        this.setTitle("DETALLE DE VENTA POR VENDEDOR");
    }

    public void ListarVentasxVendedor() {
        List<Ventas> ListVenxVend = Vdao.ListarVentxVend();
        modelo = (DefaultTableModel) TableVentas.getModel();
        modelo.setRowCount(0);
        Object[] ob = new Object[8];
        for (int i = 0; i < ListVenxVend.size(); i++) {
            //    ob[0] = ListVenxVend.get(i).getIdProducto();
            ob[0] = ListVenxVend.get(i).getNombreProducto();
            ob[1] = ListVenxVend.get(i).getCantidad();
            ob[2] = ListVenxVend.get(i).getPrecio();
            ob[3] = ListVenxVend.get(i).getSubtotal();
            ob[4] = ListVenxVend.get(i).getIGV();
            ob[5] = ListVenxVend.get(i).getTotal();
            //    ob[5] = ListVenxVend.get(i).getIDUSUARIO();
            ob[6] = ListVenxVend.get(i).getNombreVendedor();
            ob[7] = ListVenxVend.get(i).getFECHA();
            modelo.addRow(ob);
        }
        TableVentas.setModel(modelo);
    }

    private void ListarxFecha() {
        try {
            // OBTENER LAS FECHAS DEL RANGO
            java.util.Date fechaInicio = jdcFechaInicio.getDate();
            java.util.Date fechaFin = jdcFechaFin.getDate();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String fechaInicioFormat = formatter.format(fechaInicio);
            String fechaFinFormat = formatter.format(fechaFin);

            // OBTENER EL NOMBRE DEL VENDEDOR
            String nombreVendedor = txtNombreVendedor.getText(); // Aquí debes obtener el nombre del campo correspondiente

            //LLENAR TABLA  - ENCABEZADO
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"NombreProducto", "Cantidad", "Precio", "Subtotal", "IGV", "Total", "NombreVendedor", "FECHA"});

            // CREAR LA CONSULTA SQL CON FILTROS
            String sql = "  select "
                    + "    p.IDPRODUCTO as IdProducto, p.NOMBRE as NombreProducto, "
                    + "    d.CANTIDAD as Cantidad, p.PRECIO as Precio,    "
                    + "	v.TOTAL - v.IGV as Subtotal,  v.IGV as IGV, "
                    + "    v.TOTAL as Total, v.idusuario as IdUsuario, "
                    + "    u.VENDEDOR as NombreVendedor, v.FECHA as FECHA "
                    + "from usuarios u "
                    + "inner join ventas v on u.IDUSUARIO = v.idusuario  "
                    + "inner join ventas_detalle d on v.IDVENTA = d.IDVENTA  "
                    + "inner join productos p on d.IDPRODUCTO = p.IDPRODUCTO "
                    + "WHERE v.FECHA BETWEEN ? AND ? "; // Agregamos la condición para el rango de fechas

            // Agregamos la condición para el NombreVendedor si existe
            if (!nombreVendedor.isEmpty()) {
                sql += "AND u.VENDEDOR LIKE ? ";
            }

            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            // Establecer los parámetros de fecha para el rango
            ps.setString(1, fechaInicioFormat);
            ps.setString(2, fechaFinFormat);

            // Establecer el parámetro para el NombreVendedor si existe
            if (!nombreVendedor.isEmpty()) {
                ps.setString(3, "%" + nombreVendedor + "%");
            }

            rs = ps.executeQuery();

            // CARGAR LA DATA A LA TABLA
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("NombreProducto"), rs.getInt("Cantidad"), rs.getDouble("Precio"), rs.getDouble("Subtotal"),
                    rs.getDouble("IGV"), rs.getDouble("Total"), rs.getString("NombreVendedor"), rs.getString("FECHA")});
            }
            TableVentas.setModel(model);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        TableVentas = new javax.swing.JTable();
        title = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnListar = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnPDF = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jdcFechaInicio = new com.toedter.calendar.JDateChooser();
        txtNombreVendedor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnFecha = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jdcFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableVentas.setBackground(new java.awt.Color(204, 204, 204));
        TableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NombreProducto", "Cantidad", "Precio", "Subtotal", "IGV", "Total", "NombreVendedor", "FECHA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableVentasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableVentas);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 940, 400));

        title.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("DETALLE DE VENTA POR VENDEDOR");
        getContentPane().add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5), "DESCARGA REPORTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/listarventas.png"))); // NOI18N
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });
        jPanel1.add(btnListar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 40, 30));

        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });
        jPanel1.add(btnExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 40, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ExportarPDF");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        btnPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf.png"))); // NOI18N
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });
        jPanel1.add(btnPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 40, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ExportarExcel");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Listar Detalle Venta");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, 240, 170));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5), "Datos a Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jdcFechaInicio.setDateFormatString("dd/MM/yyyy");
        jPanel2.add(jdcFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 160, -1));
        jPanel2.add(txtNombreVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 150, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Vendedor");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Fecha Inicial");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        btnFecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFecha.setText("BUSCAR");
        btnFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaActionPerformed(evt);
            }
        });
        jPanel2.add(btnFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Fecha Final");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, -1, -1));

        jdcFechaFin.setDateFormatString("dd/MM/yyyy");
        jPanel2.add(jdcFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 150, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 670, 170));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TableVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableVentasMouseClicked

    }//GEN-LAST:event_TableVentasMouseClicked

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        ListarVentasxVendedor();
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        Reportes report = new Reportes();
        report.reporteVentaxVendedor();
    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        Reportes report = new Reportes();
        report.generarRepVentaxVendedor();
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaActionPerformed
        ListarxFecha();
    }//GEN-LAST:event_btnFechaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable TableVentas;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnFecha;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnPDF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcFechaFin;
    private com.toedter.calendar.JDateChooser jdcFechaInicio;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtNombreVendedor;
    // End of variables declaration//GEN-END:variables
}
