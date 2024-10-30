package vistas;

import modelo.Clientes;
import modelo.Config;
import modelo.Ventas_Detalle;
import java.awt.event.KeyEvent;
import modelo.Productos;
import modelo.Ventas;
import modelo.Usuarios;
//import Reportes.ReporteExcel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controller.ClienteDAO;
import controller.ProductosDAO;
import controller.VentaDAO;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.Timer;
import static vistas.FrmInicioAdministrador.escritorio;

public class FrmVenta extends javax.swing.JInternalFrame {

    Date fechaVenta = new Date();
    String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaVenta);
    ProductosDAO proDao = new ProductosDAO();
    Productos pro = new Productos();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    int item;
    double Totalpagar = 0.00;
    Clientes cl = new Clientes();
    ClienteDAO client = new ClienteDAO();
    Ventas v = new Ventas();
    VentaDAO Vdao = new VentaDAO();
    Ventas_Detalle Dv = new Ventas_Detalle();
    Usuarios log = new Usuarios();
    double totalPagarSinIGV = 0.00;
    double igv = 0.00;

    public FrmVenta() {
        initComponents();
        mostrarFechaYHora();
    }

    public FrmVenta(Usuarios priv) {
        initComponents();
        setTitle("Modulo Venta");
        txtIdVenta.setVisible(false);
        txtNombreCli.setVisible(false);
        lblDateTime = new JLabel();
        lblDateTime.setText("<html>Fecha:<br>Hora:</html>");
        getContentPane().add(lblDateTime);
    }

    private void mostrarFechaYHora() {
        // Crear un temporizador para actualizar la fecha y hora cada segundo
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la fecha y hora actual
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                String formattedDate = dateFormat.format(now);
                String formattedTime = timeFormat.format(now);

                // Actualizar el texto del JLabel usando HTML
                lblDateTime.setText("<html>Fecha: " + formattedDate + "<br>Hora: " + formattedTime + "</html>");
            }
        });
        timer.start(); // Iniciar el temporizador
    }

    private void LimparVenta() {
        txtCodigoVenta.setText("");
        txtDescripcionVenta.setText("");
        txtCantidadVenta.setText("");
        txtStockDisponible.setText("");
        txtPrecioVenta.setText("");
        txtIdVenta.setText("");
    }

    private void TotalPagar() {
        totalPagarSinIGV = 0.00;
        int numFila = TableVenta.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(TableVenta.getModel().getValueAt(i, 4)));
            totalPagarSinIGV = totalPagarSinIGV + cal;
        }

        // Verifica si el radio button de factura está seleccionado
        if (rdbtnFactura.isSelected()) {
            // Calcula el valor del IGV (18% en este ejemplo)
            igv = totalPagarSinIGV * 0.18;
        } else {
            igv = 0.00;
        }

        //Caltula el subtotal
        double subtotal = totalPagarSinIGV;

        //Caltula el total con IGV
        Totalpagar = subtotal + igv;

        //Actualiza el JLabel del SubTotal
        jLabelSubTotal.setText(String.format("%.2f", subtotal));

        // Actualiza el JLabel del IGV
        jLabelIGV.setText(String.format("%.2f", igv));

        // Actualiza el JLabel del Total
        LabelTotal.setText(String.format("%.2f", totalPagarSinIGV + igv));
    }

    private void RegistrarVenta() {
        int idcliente = Integer.parseInt(txtId.getText());
        int vendedor = Usuarios.user.getIDUSUARIO();
        double IGV = igv;
        double monto = Totalpagar;
        v.setIDCLIENTE(idcliente);
        v.setIDUSUARIO(vendedor);
        v.setIGV(IGV);
        v.setTOTAL(monto);
        v.setFECHA(fechaActual);
        Vdao.RegistrarVenta(v);
    }

    private void RegistrarDetalle() {
        int id = Vdao.IdVenta();
        for (int i = 0; i < TableVenta.getRowCount(); i++) {
            int idproducto = Integer.parseInt(TableVenta.getValueAt(i, 0).toString());
            int cant = Integer.parseInt(TableVenta.getValueAt(i, 2).toString());
            double IGV = Double.parseDouble(TableVenta.getValueAt(i, 3).toString());
            double precio = Double.parseDouble(TableVenta.getValueAt(i, 4).toString());
            Dv.setIDPRODUCTO(idproducto);
            Dv.setCANTIDAD(cant);
            Dv.setIGV(igv);
            Dv.setPRECIO(precio);
            Dv.setIDVENTA(id);
            Vdao.RegistrarDetalle(Dv);
        }
        String cliente = txtNombreClienteventa.getText();
    }

    private void ValidarStock() {
        int stock = Integer.parseInt(txtStockDisponible.getText());
        if (stock == 10) {
            JOptionPane.showMessageDialog(null, "Stock limite, no procede la venta");
            btnGenerarVenta.setVisible(false);
        }
    }

    private void ActualizarStock() {
        for (int i = 0; i < TableVenta.getRowCount(); i++) {
            int idproducto = Integer.parseInt(TableVenta.getValueAt(i, 0).toString());
            int cant = Integer.parseInt(TableVenta.getValueAt(i, 2).toString());
            pro = proDao.BuscarId(idproducto);
            int StockActual = pro.getSTOCK() - cant;
            Vdao.ActualizarStock(StockActual, idproducto);
        }
    }

    private void LimpiarTableVenta() {
        tmp = (DefaultTableModel) TableVenta.getModel();
        int fila = TableVenta.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }
    }

    private void LimpiarClienteventa() {
        txtRucVenta.setText("");
        txtNombreClienteventa.setText("");
        txtNombreCli.setText("");
        LabelTotal.setText("");
        jLabelIGV.setText("");
        jLabelSubTotal.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        txtNombreCli = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        btnGenerarVenta = new javax.swing.JButton();
        jPanelProducto = new javax.swing.JPanel();
        btnBuscarProd = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtStockDisponible = new javax.swing.JTextField();
        txtCodigoVenta = new javax.swing.JTextField();
        txtDescripcionVenta = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCantidadVenta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnEliminarventa = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtNombreClienteventa = new javax.swing.JTextField();
        txtRucVenta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanelCliente = new javax.swing.JPanel();
        btnRegistro = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableVenta = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabelIGV = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        LabelTotal = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabelSubTotal = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        txtIdVenta = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        rdbtnBoleta = new javax.swing.JRadioButton();
        rdbtnFactura = new javax.swing.JRadioButton();
        lblDateTime = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();

        setBackground(new java.awt.Color(153, 153, 153));
        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(txtNombreCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 0, 0));
        getContentPane().add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(678, 126, 0, 0));

        btnGenerarVenta.setBackground(new java.awt.Color(0, 153, 153));
        btnGenerarVenta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/print.png"))); // NOI18N
        btnGenerarVenta.setText("Imprimir");
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });
        getContentPane().add(btnGenerarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 570, -1, -1));

        jPanelProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5), "Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanelProducto.setForeground(new java.awt.Color(255, 255, 255));
        jPanelProducto.setOpaque(false);
        jPanelProducto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBuscarProd.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscarProd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBuscarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo-producto.png"))); // NOI18N
        btnBuscarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProdActionPerformed(evt);
            }
        });
        jPanelProducto.add(btnBuscarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Descripción");
        jPanelProducto.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Precio");
        jPanelProducto.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, -1, -1));

        txtStockDisponible.setEditable(false);
        jPanelProducto.add(txtStockDisponible, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 79, 30));

        txtCodigoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoVentaActionPerformed(evt);
            }
        });
        txtCodigoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyTyped(evt);
            }
        });
        jPanelProducto.add(txtCodigoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 180, 30));

        txtDescripcionVenta.setEditable(false);
        txtDescripcionVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionVentaKeyTyped(evt);
            }
        });
        jPanelProducto.add(txtDescripcionVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 191, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Stock Disponible");
        jPanelProducto.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, -1, -1));

        txtPrecioVenta.setEditable(false);
        jPanelProducto.add(txtPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 130, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cantidad");
        jPanelProducto.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        txtCantidadVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadVentaActionPerformed(evt);
            }
        });
        txtCantidadVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyTyped(evt);
            }
        });
        jPanelProducto.add(txtCantidadVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 130, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Código");
        jPanelProducto.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Buscar");
        jPanelProducto.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        btnEliminarventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarventaActionPerformed(evt);
            }
        });
        jPanelProducto.add(btnEliminarventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 50, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Eliminar");
        jPanelProducto.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, -1, -1));

        getContentPane().add(jPanelProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 540, 190));

        txtNombreClienteventa.setEditable(false);
        txtNombreClienteventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreClienteventaActionPerformed(evt);
            }
        });
        getContentPane().add(txtNombreClienteventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, 169, 30));

        txtRucVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRucVentaActionPerformed(evt);
            }
        });
        txtRucVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRucVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucVentaKeyTyped(evt);
            }
        });
        getContentPane().add(txtRucVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 160, 170, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Dni/Ruc");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nombre:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, -1, -1));

        jPanelCliente.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5), "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanelCliente.setForeground(new java.awt.Color(255, 255, 255));
        jPanelCliente.setOpaque(false);
        jPanelCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegistro.setText("Registrar Cliente");
        btnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroActionPerformed(evt);
            }
        });
        jPanelCliente.add(btnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientes.png"))); // NOI18N
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        jPanelCliente.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Buscar");
        jPanelCliente.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        getContentPane().add(jPanelCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 120, 260, 190));
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 390, 0, 0));

        TableVenta.setBackground(new java.awt.Color(204, 204, 204));
        TableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableVentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableVenta);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 1000, 230));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel1.setOpaque(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("IGV:");

        jLabelIGV.setText("-----");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Total a Pagar:");

        LabelTotal.setText("-----");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Subtotal:");

        jLabelSubTotal.setText("-----");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel13))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel14)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelTotal)
                    .addComponent(jLabelIGV)
                    .addComponent(jLabelSubTotal))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSubTotal)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabelIGV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(LabelTotal))
                .addGap(23, 23, 23))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 560, 180, 100));
        getContentPane().add(txtRazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, 0, 0));
        getContentPane().add(txtIdVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 0, 10));
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 390, 0, 0));

        buttonGroup1.add(rdbtnBoleta);
        rdbtnBoleta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rdbtnBoleta.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnBoleta.setText("BOLETA");
        getContentPane().add(rdbtnBoleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        buttonGroup1.add(rdbtnFactura);
        rdbtnFactura.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rdbtnFactura.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnFactura.setText("FACTURA");
        getContentPane().add(rdbtnFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, -1, -1));

        lblDateTime.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDateTime.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblDateTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 180, 60));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("REGISTRAR VENTA");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/fondo2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 680));

        txtColor.setText("jTextField1");
        getContentPane().add(txtColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 0, 0));

        txtMarca.setText("jTextField1");
        getContentPane().add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 0, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDescripcionVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionVentaKeyTyped
        // TODO add your handling code here:
        //  event.textKeyPress(evt);
    }//GEN-LAST:event_txtDescripcionVentaKeyTyped

    private void txtCodigoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoVentaActionPerformed

    private void txtCodigoVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyPressed
        // TODO add your handling code here:
        //        String cod = txtCodigoVenta.getText();
        //        boolean txtCodigoVenta = evt.getKeyCode() == KeyEvent.VK_ENTER;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCodigoVenta.getText())) {
                String cod = txtCodigoVenta.getText();
                pro = proDao.BuscarPro(cod);
                if (pro.getNOMBRE() != null) {
                    txtId.setText("" + pro.getIDPRODUCTO());
                    txtDescripcionVenta.setText("" + pro.getNOMBRE());
                    txtPrecioVenta.setText("" + pro.getPRECIO());
                    txtStockDisponible.setText("" + pro.getSTOCK());
                    txtCantidadVenta.requestFocus();
                } else {
                    LimparVenta();
                    txtCodigoVenta.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del producto");
                txtCodigoVenta.requestFocus();
            }
            ValidarStock();
        }
    }//GEN-LAST:event_txtCodigoVentaKeyPressed

    private void txtCodigoVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9')
            evt.consume();
    }//GEN-LAST:event_txtCodigoVentaKeyTyped

    private void txtCantidadVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadVentaActionPerformed

    private void txtCantidadVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyPressed
        // TODO add your handling code here:
        // boolean name = evt.getKeyCode() == KeyEvent.VK_ENTER;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCantidadVenta.getText())) {
                int id = Integer.parseInt(txtId.getText());
                String descripcion = txtDescripcionVenta.getText();
                int cant = Integer.parseInt(txtCantidadVenta.getText());
                double precio = Double.parseDouble(txtPrecioVenta.getText());
                double total = cant * precio;
                int stock = Integer.parseInt(txtStockDisponible.getText());
                if (stock >= cant) {
                    item = item + 1;
                    tmp = (DefaultTableModel) TableVenta.getModel();
                    for (int i = 0; i < TableVenta.getRowCount(); i++) {
                        if (TableVenta.getValueAt(i, 1).equals(txtDescripcionVenta.getText())) {
                            JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
                            return;
                        }
                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(id);
                    lista.add(descripcion);
                    lista.add(cant);
                    lista.add(precio);
                    lista.add(total);
                    Object[] O = new Object[5];
                    O[0] = lista.get(1);
                    O[1] = lista.get(2);
                    O[2] = lista.get(3);
                    O[3] = lista.get(4);
                    O[4] = lista.get(5);
                    tmp.addRow(O);
                    TableVenta.setModel(tmp);
                    TotalPagar();
                    LimparVenta();
                    txtCodigoVenta.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Stock no disponible");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese Cantidad");
            }
        }
    }//GEN-LAST:event_txtCantidadVentaKeyPressed

    private void txtCantidadVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9')
            evt.consume();
    }//GEN-LAST:event_txtCantidadVentaKeyTyped

    private void txtRucVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRucVentaActionPerformed
        // TODO add your handling code here:
        //evt.getKeyCode() == KeyEvent.VK_ENTER;
    }//GEN-LAST:event_txtRucVentaActionPerformed

    private void txtRucVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucVentaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String rucTex = txtRucVenta.getText().trim();
            if (!rucTex.isEmpty()) {
                if (rucTex.length() == 8 || rucTex.length() == 11) {
                    try {
                        int dni = Integer.parseInt(rucTex);
                        cl = client.Buscarcliente(dni);
                        if (cl.getNOMBRECLIENTE() != null) {
                            txtNombreClienteventa.setText("" + cl.getNOMBRECLIENTE());
                            txtNombreCli.setText("" + cl.getIDCLIENTE());
                            txtTelefono.setText("" + cl.getTELEFONO());
                            txtDireccion.setText("" + cl.getDIRECCION());
                        } else {
                            txtRucVenta.setText("");
                            JOptionPane.showMessageDialog(null, "El cliente no existe");
                            FrmRegistroClientes frmregistroclientes = new FrmRegistroClientes();
                            frmregistroclientes.setVisible(true);
                        }

                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "El RUC o DNI no es valido.");
                        txtRucVenta.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El RUC debe tener 11 y DNI debe tener 8 d{igitos.");

                } // Fin validar RUC y DNI
            }
        }//Fin validar enter
    }//GEN-LAST:event_txtRucVentaKeyPressed

    private void txtRucVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucVentaKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9')
            evt.consume();
    }//GEN-LAST:event_txtRucVentaKeyTyped

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed
        // TODO add your handling code here:
        if (rdbtnBoleta.isSelected()) {
            if (TableVenta.getRowCount() > 0) {
                if (!"".equals(txtNombreClienteventa.getText())) {
                    TotalPagar();
                    RegistrarVenta();
                    RegistrarDetalle();
                    ActualizarStock();
                    pdfB();
                    LimpiarTableVenta();
                    LimpiarClienteventa();
                } else {
                    JOptionPane.showMessageDialog(null, "Debes buscar un cliente");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay productos en la venta");
            }

            JOptionPane.showMessageDialog(null, "Venta Generada");
        } else if (rdbtnFactura.isSelected()) {
            if (TableVenta.getRowCount() > 0) {
                if (!"".equals(txtNombreClienteventa.getText())) {
                    TotalPagar();
                    RegistrarVenta();
                    RegistrarDetalle();
                    ActualizarStock();
                    pdfV();
                    LimpiarTableVenta();
                    LimpiarClienteventa();
                } else {
                    JOptionPane.showMessageDialog(null, "Debes buscar un cliente");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay productos en la venta");
            }

            JOptionPane.showMessageDialog(null, "Venta Generada");
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un tipo de documento válido (boleta o factura)");
        }
    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    private void btnEliminarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarventaActionPerformed
        // TODO add your handling code here:
        modelo = (DefaultTableModel) TableVenta.getModel();
        modelo.removeRow(TableVenta.getSelectedRow());
        TotalPagar();
        txtCodigoVenta.requestFocus();
    }//GEN-LAST:event_btnEliminarventaActionPerformed

    private void txtNombreClienteventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreClienteventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreClienteventaActionPerformed

    private void TableVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableVentaMouseClicked

//        int fila = TableVenta.rowAtPoint(evt.getPoint());
//        txtIdVenta.setText(TableVenta.getValueAt(fila, 0).toString());

    }//GEN-LAST:event_TableVentaMouseClicked

    private void btnBuscarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProdActionPerformed
        // TODO add your handling code here:
        FrmListarProd frmlistprod = new FrmListarProd();
        FrmInicioVendedor.escritorio.add(frmlistprod);
        frmlistprod.toFront();
        frmlistprod.setVisible(true);
    }//GEN-LAST:event_btnBuscarProdActionPerformed

    private void btnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroActionPerformed
        // TODO add your handling code here:
        FrmRegistroClientes frmCliente = new FrmRegistroClientes();
        FrmInicioVendedor.escritorio.add(frmCliente);
        frmCliente.toFront();
        frmCliente.setVisible(true);
    }//GEN-LAST:event_btnRegistroActionPerformed

    private void txtCodigoVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyReleased
        if (evt.getSource() == txtCodigoVenta) {

        }
    }//GEN-LAST:event_txtCodigoVentaKeyReleased

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        FrmRegistroClientes frmregistroclientes = new FrmRegistroClientes();
        FrmInicioVendedor.escritorio.add(frmregistroclientes);
        frmregistroclientes.toFront();
        frmregistroclientes.setVisible(true);
    }//GEN-LAST:event_btnClientesActionPerformed

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
//            java.util.logging.Logger.getLogger(FrmMenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmMenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmMenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmMenuAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmVenta().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel LabelTotal;
    public javax.swing.JTable TableVenta;
    private javax.swing.JButton btnBuscarProd;
    private javax.swing.JButton btnClientes;
    public javax.swing.JButton btnEliminarventa;
    public javax.swing.JButton btnGenerarVenta;
    public javax.swing.JButton btnRegistro;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelIGV;
    private javax.swing.JLabel jLabelSubTotal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCliente;
    private javax.swing.JPanel jPanelProducto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDateTime;
    private javax.swing.JRadioButton rdbtnBoleta;
    private javax.swing.JRadioButton rdbtnFactura;
    public javax.swing.JTextField txtCantidadVenta;
    public javax.swing.JTextField txtCodigoVenta;
    private javax.swing.JTextField txtColor;
    public javax.swing.JTextField txtDescripcionVenta;
    public javax.swing.JTextField txtDireccion;
    public javax.swing.JTextField txtId;
    public javax.swing.JTextField txtIdVenta;
    private javax.swing.JTextField txtMarca;
    public javax.swing.JTextField txtNombreCli;
    public javax.swing.JTextField txtNombreClienteventa;
    public javax.swing.JTextField txtPrecioVenta;
    public javax.swing.JTextField txtRazonSocial;
    public javax.swing.JTextField txtRucVenta;
    public javax.swing.JTextField txtStockDisponible;
    public javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
 // Método para impirmir PDF
    public void pdfV() {
        try {
            int id = Vdao.IdVenta();
            FileOutputStream archivo;
            File file = new File("src/pdf/venta" + id + ".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance("src/Img/logopdf.png");
            Paragraph fecha = new Paragraph();
            com.itextpdf.text.Font negrita = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, com.itextpdf.text.Font.BOLD, BaseColor.BLUE);

            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            fecha.add("Factura: " + id + "\n" + "Fecha: " + dateFormat.format(date) + "\n\n");
            PdfPTable Encabezado = new PdfPTable(4);

            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            Encabezado.addCell(img);
            String ruc = "20525456212";
            String nom = "COMPULISTO";
            String tlf = "987852456";
            String dir = "LA VICTORIA";
            String tpc = "COMPULISTO SAC";

            Encabezado.addCell("");
            Encabezado.addCell("Ruc: " + ruc + "\nNombre: " + nom + "\nTelefono: " + tlf + "\nDireccion: " + dir + "\nRazon: " + tpc);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);

            Paragraph ven = new Paragraph();
            ven.add(Chunk.NEWLINE);
            ven.add("Datos del Vendedor" + "\n\n");
            doc.add(ven);

//            PdfPTable tablaven = new PdfPTable(1);
//            tablaven.setWidthPercentage(100);
//            tablaven.getDefaultCell().setBorder(0);
//            float[] Columnaven = new float[]{50f};
//            tablaven.setWidths(Columnaven);
//            tablaven.setHorizontalAlignment(Element.ALIGN_LEFT);
//            PdfPCell ven1 = new PdfPCell(new Phrase("Vendedor", negrita));
//            ven1.setBorder(0);
//            tablaven.addCell(ven1);
//            tablaven.addCell(LabelVendedor.getText());
//            doc.add(tablaven);
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("Datos del Clientes" + "\n\n");
            doc.add(cli);

            PdfPTable tablacli = new PdfPTable(4);
            tablacli.setWidthPercentage(100);
            tablacli.getDefaultCell().setBorder(0);
            float[] Columnacli = new float[]{15f, 50f, 15f, 20f};
            tablacli.setWidths(Columnacli);
            tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cl1 = new PdfPCell(new Phrase("Dni/Ruc", negrita));
            PdfPCell cl2 = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cl3 = new PdfPCell(new Phrase("Telefono", negrita));
            PdfPCell cl4 = new PdfPCell(new Phrase("Direccion", negrita));
            cl1.setBorder(0);
            cl2.setBorder(0);
            cl3.setBorder(0);
            cl4.setBorder(0);

//            cl1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            cl2.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            cl3.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            cl4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tablacli.addCell(cl1);
            tablacli.addCell(cl2);
            tablacli.addCell(cl3);
            tablacli.addCell(cl4);

            tablacli.addCell(txtRucVenta.getText());
            tablacli.addCell(txtNombreClienteventa.getText());
            tablacli.addCell(txtTelefono.getText());
            tablacli.addCell(txtDireccion.getText());

            doc.add(tablacli);

            //*******productos***
            PdfPTable tablapro = new PdfPTable(4);
            tablapro.setWidthPercentage(100);
            tablapro.getDefaultCell().setBorder(0);
            float[] Columnapro = new float[]{10f, 70f, 15f, 20f};
            Encabezado.setWidths(Columnapro);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell pro1 = new PdfPCell(new Phrase("Cant", negrita));
            PdfPCell pro2 = new PdfPCell(new Phrase("Descripción", negrita));
            PdfPCell pro3 = new PdfPCell(new Phrase("Precio U.", negrita));
            PdfPCell pro4 = new PdfPCell(new Phrase("Precio T.", negrita));
            pro1.setBorder(0);
            pro2.setBorder(0);
            pro3.setBorder(0);
            pro4.setBorder(0);

//            pro1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            pro2.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            pro3.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            pro4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tablapro.addCell(pro1);
            tablapro.addCell(pro2);
            tablapro.addCell(pro3);
            tablapro.addCell(pro4);

            for (int i = 0; i < TableVenta.getRowCount(); i++) {
                String producto = TableVenta.getValueAt(i, 1).toString();
                String cantidad = TableVenta.getValueAt(i, 2).toString();
                String precio = TableVenta.getValueAt(i, 3).toString();
                String total = TableVenta.getValueAt(i, 4).toString();

                tablapro.addCell(cantidad);
                tablapro.addCell(producto);
                tablapro.addCell(precio);
                tablapro.addCell(total);

            }
            doc.add(tablapro);

            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Subtotal: " + String.format("S/ %.2f", totalPagarSinIGV));
            info.add(Chunk.NEWLINE);
            info.add("IGV (18%): " + String.format("S/ %.2f", igv));
            info.add(Chunk.NEWLINE);
            info.add("Total a pagar: " + String.format("S/ %.2f", totalPagarSinIGV + igv));
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);

            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelación y Firma \n\n");
            firma.add("-------------------------");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);

            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("Gracias por su Compra ");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);

        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }
    }
    //GENERAR BOLETA

    public void pdfB() {
        try {
            int id = Vdao.IdVenta();
            FileOutputStream archivo;
            File file = new File("src/pdf/venta" + id + ".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance("src/Img/logopdf.png");
            Paragraph fecha = new Paragraph();
            com.itextpdf.text.Font negrita = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, com.itextpdf.text.Font.BOLD, BaseColor.BLUE);

            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            fecha.add("Boleta: " + id + "\n" + "Fecha: " + dateFormat.format(date) + "\n\n");
            PdfPTable Encabezado = new PdfPTable(4);

            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            Encabezado.addCell(img);
            String ruc = "20525456212";
            String nom = "COMPULISTO";
            String tlf = "987852456";
            String dir = "LA VICTORIA";
            String tpc = "COMPULISTO SAC";

            Encabezado.addCell("");
            Encabezado.addCell("Ruc: " + ruc + "\nNombre: " + nom + "\nTelefono: " + tlf + "\nDireccion: " + dir + "\nRazon: " + tpc);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);

//            Paragraph ven = new Paragraph();
//            ven.add(Chunk.NEWLINE);
//            ven.add("Datos del Vendedor" + "\n\n");
//            doc.add(ven);
//
//            PdfPTable tablaven = new PdfPTable(1);
//            tablaven.setWidthPercentage(100);
//            tablaven.getDefaultCell().setBorder(0);
//            float[] Columnaven = new float[]{50f};
//            tablaven.setWidths(Columnaven);
//            tablaven.setHorizontalAlignment(Element.ALIGN_LEFT);
//            PdfPCell ven1 = new PdfPCell(new Phrase("Vendedor", negrita));
//            ven1.setBorder(0);
//            tablaven.addCell(ven1);
//            tablaven.addCell(LabelVendedor.getText());
//            doc.add(tablaven);
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("Datos del Clientes" + "\n\n");
            doc.add(cli);

            PdfPTable tablacli = new PdfPTable(4);
            tablacli.setWidthPercentage(100);
            tablacli.getDefaultCell().setBorder(0);
            float[] Columnacli = new float[]{15f, 50f, 15f, 20f};
            tablacli.setWidths(Columnacli);
            tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cl1 = new PdfPCell(new Phrase("Dni/Ruc", negrita));
            PdfPCell cl2 = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cl3 = new PdfPCell(new Phrase("Telefono", negrita));
            PdfPCell cl4 = new PdfPCell(new Phrase("Direccion", negrita));
            cl1.setBorder(0);
            cl2.setBorder(0);
            cl3.setBorder(0);
            cl4.setBorder(0);

//            cl1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            cl2.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            cl3.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            cl4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tablacli.addCell(cl1);
            tablacli.addCell(cl2);
            tablacli.addCell(cl3);
            tablacli.addCell(cl4);

            tablacli.addCell(txtRucVenta.getText());
            tablacli.addCell(txtNombreClienteventa.getText());
            tablacli.addCell(txtTelefono.getText());
            tablacli.addCell(txtDireccion.getText());

            doc.add(tablacli);

            //*******productos***
            PdfPTable tablapro = new PdfPTable(4);
            tablapro.setWidthPercentage(100);
            tablapro.getDefaultCell().setBorder(0);
            float[] Columnapro = new float[]{10f, 70f, 15f, 20f};
            Encabezado.setWidths(Columnapro);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell pro1 = new PdfPCell(new Phrase("Cant", negrita));
            PdfPCell pro2 = new PdfPCell(new Phrase("Descripción", negrita));
            PdfPCell pro3 = new PdfPCell(new Phrase("Precio U.", negrita));
            PdfPCell pro4 = new PdfPCell(new Phrase("Precio T.", negrita));
            pro1.setBorder(0);
            pro2.setBorder(0);
            pro3.setBorder(0);
            pro4.setBorder(0);

//            pro1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            pro2.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            pro3.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            pro4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tablapro.addCell(pro1);
            tablapro.addCell(pro2);
            tablapro.addCell(pro3);
            tablapro.addCell(pro4);

            for (int i = 0; i < TableVenta.getRowCount(); i++) {
                String producto = TableVenta.getValueAt(i, 1).toString();
                String cantidad = TableVenta.getValueAt(i, 2).toString();
                String precio = TableVenta.getValueAt(i, 3).toString();
                String total = TableVenta.getValueAt(i, 4).toString();

                tablapro.addCell(cantidad);
                tablapro.addCell(producto);
                tablapro.addCell(precio);
                tablapro.addCell(total);

            }
            doc.add(tablapro);

            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Subtotal: " + String.format("S/ %.2f", totalPagarSinIGV));
            info.add(Chunk.NEWLINE);
            info.add("IGV (18%): " + String.format("S/ %.2f", igv));
            info.add(Chunk.NEWLINE);
            info.add("Total a pagar: " + String.format("S/ %.2f", totalPagarSinIGV + igv));
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);

            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelación y Firma \n\n");
            firma.add("-------------------------");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);

            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("Gracias por su Compra ");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);

        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }
    }

}
