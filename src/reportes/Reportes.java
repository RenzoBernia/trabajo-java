package reportes;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.xdevapi.Session;
import controller.Conexion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import vistas.FrmReporteProducxFecha;
import vistas.ReporteVentasxVendedor;

public class Reportes {

    //REPORTE PRODUCTOS - EXCEL
    public void reporteProducto() {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Productos");

        // Obtener la fila en la posición 0 (celda 1) para agregar la fecha y hora con salto de línea
        Row filaFechaHora = sheet.createRow(0);
        Cell celdaFechaHora = filaFechaHora.createCell(5);
        CellStyle fechaHoraStyle = book.createCellStyle();
        CreationHelper createHelper = book.getCreationHelper();
        fechaHoraStyle.setWrapText(true); // Habilitar el ajuste automático de texto

        // Establecer el contenido con salto de línea para mostrar la fecha y hora
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy\nHH:mm:ss");
        String fechaHora = dateFormat.format(new Date()); // Obtener la fecha y hora actual
        celdaFechaHora.setCellValue(fechaHora);
        celdaFechaHora.setCellStyle(fechaHoraStyle);

        // Ajustar el tamaño de la celda para que se muestren ambas líneas correctamente
        sheet.autoSizeColumn(0);

        try {
            // Load company logo
            InputStream logoStream = new FileInputStream("src/Img/logopdf.png");
            byte[] logoBytes = IOUtils.toByteArray(logoStream);
            int logoIndex = book.addPicture(logoBytes, Workbook.PICTURE_TYPE_PNG);
            logoStream.close();

            CreationHelper helper = book.getCreationHelper();
            Drawing drawing = sheet.createDrawingPatriarch();

            // Set the cell coordinates for the logo (above title)
            ClientAnchor anchorLogo = helper.createClientAnchor();
            anchorLogo.setCol1(1);
            anchorLogo.setRow1(1); // Adjusted to row 0
            Picture logo = drawing.createPicture(anchorLogo, logoIndex);
            logo.resize(1, 3);

            // Add company information (to the right of the logo, in separate cells)
            Row companyInfoRow = sheet.createRow(1);
            Cell companyInfoCell1 = companyInfoRow.createCell(3); // Adjusted to column 5
            companyInfoCell1.setCellValue("Empresa: " + "CompuListo SAC");

            Row companyInfoRow2 = sheet.createRow(2); // Adjusted to row 4
            Cell companyInfoCell2 = companyInfoRow2.createCell(3); // Adjusted to column 5
            companyInfoCell2.setCellValue("RUC: " + "20525456212");

            Row companyInfoRow3 = sheet.createRow(3); // Adjusted to row 5
            Cell companyInfoCell3 = companyInfoRow3.createCell(3); // Adjusted to column 5
            companyInfoCell3.setCellValue("Direccion: " + "La Victoria");

            // Set up title and merge cells
            CellStyle titleStyle = book.createCellStyle();
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            Font titleFont = book.createFont();
            titleFont.setFontName("Arial");
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 14);
            titleStyle.setFont(titleFont);

            Row titleRow = sheet.createRow(5); // Adjusted to row 5
            Cell titleCell = titleRow.createCell(2);
            titleCell.setCellStyle(titleStyle);
            titleCell.setCellValue("Reporte de Productos");

            sheet.addMergedRegion(new CellRangeAddress(5, 6, 2, 4)); // Adjusted to rows 5 and 6

            // Set up header style
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);

            Font headerFont = book.createFont();
            headerFont.setFontName("Arial");
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerFont.setFontHeightInPoints((short) 12);
            headerStyle.setFont(headerFont);

            String[] cabecera = new String[]{"Código", "Nombre", "Stock", "Precio", "Descripcion", "Categoria"};

            Row filaEncabezados = sheet.createRow(8);

            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }

            Conexion con = new Conexion();
            PreparedStatement ps;
            ResultSet rs;
            Connection conn = con.getConnection();

            int numFilaDatos = 9;

            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);

            ps = conn.prepareStatement("SELECT codigo, nombre, stock ,precio, descripcion, categoria FROM productos");
            rs = ps.executeQuery();

            int numCol = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);

                for (int a = 0; a < numCol; a++) {

                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }

                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);

            sheet.setZoom(100);
            String fileName = "productos";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "Reporte Generado");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //REPORTE CATEGORIA - EXCEL
    public void reporteCategoria() {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Productos");

        // Obtener la fila en la posición 0 (celda 1) para agregar la fecha y hora con salto de línea
        Row filaFechaHora = sheet.createRow(0);
        Cell celdaFechaHora = filaFechaHora.createCell(0);
        CellStyle fechaHoraStyle = book.createCellStyle();
        CreationHelper createHelper = book.getCreationHelper();
        fechaHoraStyle.setWrapText(true); // Habilitar el ajuste automático de texto

        // Establecer el contenido con salto de línea para mostrar la fecha y hora
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy\nHH:mm:ss");
        String fechaHora = dateFormat.format(new Date()); // Obtener la fecha y hora actual
        celdaFechaHora.setCellValue(fechaHora);
        celdaFechaHora.setCellStyle(fechaHoraStyle);

        // Ajustar el tamaño de la celda para que se muestren ambas líneas correctamente
        sheet.autoSizeColumn(0);

        try {

            InputStream is = new FileInputStream("src/Img/Excel.png");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();

            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();

            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(1);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1, 3);

            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);

            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Reporte de Categoria");

            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));

            String[] cabecera = new String[]{"CategoriaId", "Categoria", "Estado"};

            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);

            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);

            Row filaEncabezados = sheet.createRow(4);

            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }

            Conexion con = new Conexion();
            PreparedStatement ps;
            ResultSet rs;
            Connection conn = con.getConnection();

            int numFilaDatos = 5;

            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);

            ps = conn.prepareStatement("SELECT categoriaid, categoria, estado FROM producto_categoria");
            rs = ps.executeQuery();

            int numCol = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);

                for (int a = 0; a < numCol; a++) {

                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }

                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);

            sheet.setZoom(150);
            String fileName = "categoria";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "Reporte Generado");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //REPORTE USUARIOS - EXCEL
    public void reporteUsuarios() {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Usuarios");

        // Obtener la fila en la posición 0 (celda 1) para agregar la fecha y hora con salto de línea
        Row filaFechaHora = sheet.createRow(0);
        Cell celdaFechaHora = filaFechaHora.createCell(0);
        CellStyle fechaHoraStyle = book.createCellStyle();
        CreationHelper createHelper = book.getCreationHelper();
        fechaHoraStyle.setWrapText(true); // Habilitar el ajuste automático de texto

        // Establecer el contenido con salto de línea para mostrar la fecha y hora
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy\nHH:mm:ss");
        String fechaHora = dateFormat.format(new Date()); // Obtener la fecha y hora actual
        celdaFechaHora.setCellValue(fechaHora);
        celdaFechaHora.setCellStyle(fechaHoraStyle);

        // Ajustar el tamaño de la celda para que se muestren ambas líneas correctamente
        sheet.autoSizeColumn(0);
        try {

            InputStream is = new FileInputStream("src/Img/Excel.png");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();

            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();

            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(1);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1, 3);

            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);

            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Reporte de Usuarios");

            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));

            String[] cabecera = new String[]{"Vendedor", "Telefono", "Direcciion", "Rol"};

            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);

            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);

            Row filaEncabezados = sheet.createRow(4);

            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }

            Conexion con = new Conexion();
            PreparedStatement ps;
            ResultSet rs;
            Connection conn = con.getConnection();

            int numFilaDatos = 5;

            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);

            ps = conn.prepareStatement("SELECT vendedor, telefono, direccion, rol FROM usuarios");
            rs = ps.executeQuery();

            int numCol = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);

                for (int a = 0; a < numCol; a++) {

                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }

                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);

            sheet.setZoom(150);
            String fileName = "usuarios";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "Reporte Generado");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //REPORTE CLIENTES - EXCEL
    public void reporteClientes() {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Clientes");

        // Obtener la fila en la posición 0 (celda 1) para agregar la fecha y hora con salto de línea
        Row filaFechaHora = sheet.createRow(0);
        Cell celdaFechaHora = filaFechaHora.createCell(0);
        CellStyle fechaHoraStyle = book.createCellStyle();
        CreationHelper createHelper = book.getCreationHelper();
        fechaHoraStyle.setWrapText(true); // Habilitar el ajuste automático de texto

        // Establecer el contenido con salto de línea para mostrar la fecha y hora
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy\nHH:mm:ss");
        String fechaHora = dateFormat.format(new Date()); // Obtener la fecha y hora actual
        celdaFechaHora.setCellValue(fechaHora);
        celdaFechaHora.setCellStyle(fechaHoraStyle);

        // Ajustar el tamaño de la celda para que se muestren ambas líneas correctamente
        sheet.autoSizeColumn(0);

        try {

            InputStream is = new FileInputStream("src/Img/Excel.png");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();

            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();

            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(1);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1, 3);

            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);

            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Reporte de Clientes");

            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));

            String[] cabecera = new String[]{"Nombre", "Dni", "Telefono", "Direccion", "Mail"};

            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);

            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);

            Row filaEncabezados = sheet.createRow(4);

            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }

            Conexion con = new Conexion();
            PreparedStatement ps;
            ResultSet rs;
            Connection conn = con.getConnection();

            int numFilaDatos = 5;

            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);

            ps = conn.prepareStatement("SELECT nombrecliente, dni, telefono, direccion, mail FROM clientes");
            rs = ps.executeQuery();

            int numCol = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);

                for (int a = 0; a < numCol; a++) {

                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }

                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);

            sheet.setZoom(150);
            String fileName = "clientes";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "Reporte Generado");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //REPORTE VENTA X CLIENTE - EXCEL
    public void reporteVentaxCliente() {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("VentaxCliente");

        // Obtener la fila en la posición 0 (celda 1) para agregar la fecha y hora con salto de línea
        Row filaFechaHora = sheet.createRow(0);
        Cell celdaFechaHora = filaFechaHora.createCell(0);
        CellStyle fechaHoraStyle = book.createCellStyle();
        CreationHelper createHelper = book.getCreationHelper();
        fechaHoraStyle.setWrapText(true); // Habilitar el ajuste automático de texto

        // Establecer el contenido con salto de línea para mostrar la fecha y hora
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy\nHH:mm:ss");
        String fechaHora = dateFormat.format(new Date()); // Obtener la fecha y hora actual
        celdaFechaHora.setCellValue(fechaHora);
        celdaFechaHora.setCellStyle(fechaHoraStyle);

        // Ajustar el tamaño de la celda para que se muestren ambas líneas correctamente
        sheet.autoSizeColumn(0);

        try {

            InputStream is = new FileInputStream("src/Img/Excel.png");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();

            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();

            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(1);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1, 3);

            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);

            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Reporte de Venta x Cliente");

            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));

            String[] cabecera = new String[]{"NombreCliente", "NombreProducto",
                "Cantidad", "Precio", "Total", "Fecha"};

            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);

            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);

            Row filaEncabezados = sheet.createRow(4);

            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }

            Conexion con = new Conexion();
            PreparedStatement ps;
            ResultSet rs;
            Connection conn = con.getConnection();

            int numFilaDatos = 5;

            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);

            ps = conn.prepareStatement("SELECT "
                    + "    c.NOMBRECLIENTE as NombreCliente, "
                    + "    p.NOMBRE as NombreProducto, "
                    + "    d.CANTIDAD as Cantidad, "
                    + "    p.PRECIO as Precio, "
                    + "    v.TOTAL as Total, "
                    + "    v.FECHA as FECHA "
                    + "FROM clientes c "
                    + "INNER JOIN ventas v ON c.IDCLIENTE = v.IDCLIENTE "
                    + "INNER JOIN ventas_detalle d ON v.IDVENTA = d.IDVENTA "
                    + "INNER JOIN productos p ON d.IDPRODUCTO = p.IDPRODUCTO "
                    + "ORDER BY FECHA;");
            rs = ps.executeQuery();

            int numCol = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);

                for (int a = 0; a < numCol; a++) {

                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }

                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);

            sheet.setZoom(150);
            String fileName = "VentaxCliente";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "Reporte Generado");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //REPORTE DE PRODUCTO X FECHA - PDF
    public void reporteProdxFecha() {
        Document documento = new Document();

        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar como PDF");
            chooser.setSelectedFile(new File(".pdf"));

            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                String guardar = file.getParent();
                String fileName = file.getName();
                try {
                    PdfWriter.getInstance(documento, new FileOutputStream(guardar + "/" + fileName));
                } catch (IOException | DocumentException e) {
                    e.printStackTrace();
                }
            }
            documento.open();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date();
            String fechaHora = formatter.format(fechaActual);

            // Crear dos Paragraphs para la hora y la fecha separadas
            Paragraph hora = new Paragraph("Hora: " + fechaHora.substring(11), FontFactory.getFont(FontFactory.TIMES_ROMAN));
            hora.setAlignment(Element.ALIGN_RIGHT);
            documento.add(hora);

            Paragraph fecha = new Paragraph("Fecha: " + fechaHora.substring(0, 10), FontFactory.getFont(FontFactory.TIMES_ROMAN));
            fecha.setAlignment(Element.ALIGN_RIGHT);
            documento.add(fecha);

            // Agregar un espacio adicional entre la fecha y la tabla
            documento.add(new Paragraph("\n"));

            Paragraph titulo = new Paragraph("Reporte de Producto" + "\n\n", FontFactory.getFont(FontFactory.HELVETICA, 24, Font.COLOR_NORMAL));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(90);
            table.setWidths(new int[]{3, 3, 3, 2, 3, 3, 3});
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);
            table.addCell("NombreProducto");
            table.addCell("Cantidad");
            table.addCell("Precio");
            table.addCell("Subtotal");
            table.addCell("IGV");
            table.addCell("Total");
            table.addCell("FECHA");

            int nFila = FrmReporteProducxFecha.TableVentasxFecha.getRowCount();
            for (int i = 0; i < nFila; i++) {
                for (int j = 0; j < 7; j++) {
                    Object valor = FrmReporteProducxFecha.TableVentasxFecha.getValueAt(i, j);
                    if (valor != null) {
                        table.addCell(valor.toString());
                    } else {
                        table.addCell("");
                    }
                }
            }

            documento.add(table);

            documento.close();

            JOptionPane.showMessageDialog(null, "El archivo ha sido generado correctamente.");
            String guardar = null;
            String fileName = null;

            // Abrir automáticamente el archivo generado
            File generatedFile = new File(guardar + "/" + fileName);
            if (Desktop.isDesktopSupported() && generatedFile.exists()) {
                Desktop.getDesktop().open(generatedFile);
            }

        } catch (DocumentException | IOException e) {
            System.out.println("Error: " + e);
        }
    }

    //REPORTE VENTA X VENDEDOR - EXCEL
    public void reporteVentaxVendedor() {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("VentaxVendedor");

        // Obtener la fila en la posición 0 (celda 1) para agregar la fecha y hora con salto de línea
        Row filaFechaHora = sheet.createRow(0);
        Cell celdaFechaHora = filaFechaHora.createCell(0);
        CellStyle fechaHoraStyle = book.createCellStyle();
        CreationHelper createHelper = book.getCreationHelper();
        fechaHoraStyle.setWrapText(true); // Habilitar el ajuste automático de texto

        // Establecer el contenido con salto de línea para mostrar la fecha y hora
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy\nHH:mm:ss");
        String fechaHora = dateFormat.format(new Date()); // Obtener la fecha y hora actual
        celdaFechaHora.setCellValue(fechaHora);
        celdaFechaHora.setCellStyle(fechaHoraStyle);

        // Ajustar el tamaño de la celda para que se muestren ambas líneas correctamente
        sheet.autoSizeColumn(0);

        try {

            InputStream is = new FileInputStream("src/Img/Excel.png");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();

            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();

            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(1);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1, 3);

            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);

            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Reporte de Venta x Vendedor");

            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));

            String[] cabecera = new String[]{"IdProducto", "NombreProducto",
                "Cantidad", "Precio", "Subtotal", "IGV", "Total", "IdUsuario", "NombreVendedor", "Fecha"};

            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);

            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);

            Row filaEncabezados = sheet.createRow(4);
            int columnCount = 0; // Variable para rastrear las columnas en el bucle de datos
            for (int i = 0; i < cabecera.length; i++) {

                Cell celdaEnzabezado = filaEncabezados.createCell(columnCount);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
                columnCount++;

            }

            Conexion con = new Conexion();
            PreparedStatement ps;
            ResultSet rs;
            Connection conn = con.getConnection();

            int numFilaDatos = 5;

            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);

            ps = conn.prepareStatement(" select "
                    + "    p.IDPRODUCTO as IdProducto, p.NOMBRE as NombreProducto, "
                    + "    d.CANTIDAD as Cantidad, p.PRECIO as Precio,   "
                    + "	v.TOTAL - v.IGV as Subtotal,  v.IGV as IGV, "
                    + "    v.TOTAL as Total, v.idusuario as IdUsuario,  "
                    + "    u.VENDEDOR as NombreVendedor, v.FECHA as FECHA  "
                    + "from usuarios u "
                    + "inner join ventas v on u.IDUSUARIO = v.idusuario  "
                    + "inner join ventas_detalle d on v.IDVENTA = d.IDVENTA  "
                    + "inner join productos p on d.IDPRODUCTO = p.IDPRODUCTO "
                    + "ORDER BY FECHA;");
            rs = ps.executeQuery();

            int numCol = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);

                for (int a = 0; a < numCol; a++) {

                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));

                }

                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);

            sheet.setZoom(100);
            String fileName = "VentaxVendedor";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "Reporte Generado");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //REPORTE DE CLIENTES EN PDF - ME PERMITE SELECCIONAR DONDE GUARDARLO Y QUE NOMBRE DARLE - PDF
    public void generarReporte() {
        Document documento = new Document();

        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar como PDF");
            chooser.setSelectedFile(new File(".pdf"));

            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                String guardar = file.getParent();
                String fileName = file.getName();
                try {
                    PdfWriter.getInstance(documento, new FileOutputStream(guardar + "/" + fileName));
                } catch (IOException | DocumentException e) {
                    e.printStackTrace();
                }
            }
            documento.open();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date();
            String fechaHora = formatter.format(fechaActual);

            // Crear dos Paragraphs para la hora y la fecha separadas
            Paragraph hora = new Paragraph("Hora: " + fechaHora.substring(11), FontFactory.getFont(FontFactory.TIMES_ROMAN));
            hora.setAlignment(Element.ALIGN_RIGHT);
            documento.add(hora);

            Paragraph fecha = new Paragraph("Fecha: " + fechaHora.substring(0, 10), FontFactory.getFont(FontFactory.TIMES_ROMAN));
            fecha.setAlignment(Element.ALIGN_RIGHT);
            documento.add(fecha);

            // Agregar un espacio adicional entre la fecha y la tabla
            documento.add(new Paragraph("\n"));

            Paragraph titulo = new Paragraph("Reporte de Clientes" + "\n\n", FontFactory.getFont(FontFactory.HELVETICA, 24, Font.COLOR_NORMAL));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{2, 3, 2, 3, 2});
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);
            table.addCell("DNI");
            table.addCell("NOMBRE");
            table.addCell("TELEFONO");
            table.addCell("DIRECCION");
            table.addCell("MAIL");

            try {
                Conexion cn = new Conexion();
                Connection con = cn.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM clientes");
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    do {
                        table.addCell(rs.getString(2));
                        table.addCell(rs.getString(3));
                        table.addCell(rs.getString(4));
                        table.addCell(rs.getString(5));
                        table.addCell(rs.getString(6));
                    } while (rs.next());
                    documento.add(table);
                } else {
                    JOptionPane.showMessageDialog(null, "No existen datos");
                }

            } catch (SQLException e) {
                System.out.println("Error en la conexión: " + e);
            }

            documento.close();

            JOptionPane.showMessageDialog(null, "El archivo ha sido generado correctamente.");
            String guardar = null;
            String fileName = null;

            // Abrir automáticamente el archivo generado
            File generatedFile = new File(guardar + "/" + fileName);
            if (Desktop.isDesktopSupported() && generatedFile.exists()) {
                Desktop.getDesktop().open(generatedFile);
            }

        } catch (DocumentException | IOException e) {
            System.out.println("Error: " + e);
        }
    }

    //REPORTE DE VENTA X VENDEDOR EN PDF
    public void generarRepVentaxVendedor() {
        Document documento = new Document();
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar como PDF");
            chooser.setSelectedFile(new File(".pdf"));

            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                String guardar = file.getParent();
                String fileName = file.getName();

                try {
                    PdfWriter.getInstance(documento, new FileOutputStream(guardar + "/" + fileName));
                } catch (IOException | DocumentException e) {
                    e.printStackTrace();
                }
            }
            documento.open();
            
            Image img = Image.getInstance("src/img/logopdf.png");
//            Paragraph fecha = new Paragraph();
            com.itextpdf.text.Font negrita = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, com.itextpdf.text.Font.BOLD, BaseColor.BLUE);
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
//            Encabezado.addCell(fecha);


            documento.add(Encabezado);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date();
            String fechaHora = formatter.format(fechaActual);

            // Crear dos Paragraphs para la hora y la fecha separadas
            Paragraph hora = new Paragraph("Hora: " + fechaHora.substring(11), FontFactory.getFont(FontFactory.TIMES_ROMAN));
            hora.setAlignment(Element.ALIGN_RIGHT);
            documento.add(hora);

            Paragraph fecha = new Paragraph("Fecha: " + fechaHora.substring(0, 10), FontFactory.getFont(FontFactory.TIMES_ROMAN));
            fecha.setAlignment(Element.ALIGN_RIGHT);
            documento.add(fecha);

            // Agregar un espacio adicional entre la fecha y la tabla
            documento.add(new Paragraph("\n"));

            Paragraph titulo = new Paragraph("Reporte de Venta x Vendedor" + "\n\n", FontFactory.getFont(FontFactory.HELVETICA, 24, Font.COLOR_NORMAL));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{6, 4, 2, 2, 2, 2, 7, 7});
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);
            //  table.addCell("IDPRODUCTO");
            table.addCell("NombreProducto");
            table.addCell("Cantidad");
            table.addCell("Precio");
            table.addCell("Subtotal");
            table.addCell("IGV");
            table.addCell("Total");
            //  table.addCell("IdUsuario");
            table.addCell("NombreVendedor");
            table.addCell("Fecha");

            int nFila = ReporteVentasxVendedor.TableVentas.getRowCount();
            for (int i = 0; i < nFila; i++) {
                for (int j = 0; j < 8; j++) {
                    Object valor = ReporteVentasxVendedor.TableVentas.getValueAt(i, j);
                    if (valor != null) {
                        table.addCell(valor.toString());
                    } else {
                        table.addCell("");
                    }
                }
            }

//            int nFila = ReporteVentasxVendedor.TableVentas.getRowCount();
//            //1era fila de cabecera
//            for (int i = 0; i < nFila; i++) {
//                table.addCell(ReporteVentasxVendedor.TableVentas.getValueAt(i, 0).toString());
//                table.addCell(ReporteVentasxVendedor.TableVentas.getValueAt(i, 1).toString());
//                table.addCell(ReporteVentasxVendedor.TableVentas.getValueAt(i, 2).toString());
//                table.addCell(ReporteVentasxVendedor.TableVentas.getValueAt(i, 3).toString());
//                table.addCell(ReporteVentasxVendedor.TableVentas.getValueAt(i, 4).toString());
//                table.addCell(ReporteVentasxVendedor.TableVentas.getValueAt(i, 5).toString());
//                table.addCell(ReporteVentasxVendedor.TableVentas.getValueAt(i, 6).toString());
//                table.addCell(ReporteVentasxVendedor.TableVentas.getValueAt(i, 7).toString());
//            }
            documento.add(table);

            documento.close();

            JOptionPane.showMessageDialog(null, "El archivo ha sido generado correctamente.");
        } catch (DocumentException e) {
            System.out.println("Error en la creación del documento: " + e);
        } catch (IOException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EnviarCorreo() {

//        Properties pro = new Properties();
//        pro.setProperty("mail.smtp.host", "smtp.gmail.com");
//        pro.setProperty("mail.smtp.starttls", "true");
//        pro.setProperty("mail.smtp.port", "587");
//        pro.setProperty("mail.smtp.auth", "true");
//        
//        Session session = Session.class.cast(pro);
//        
//        String correoRemite = "luis@gmail.com";
//        String passwordRemite = "";
//        String correoReceptor = txtcorreo.getText();
//        String asunto = "Su boleta";
//        String mensaje = "Este es su documento";
//        
//        MimeMessage message = new MimeMessage(session);
//        message.setFrom(new InternetAdress(correoRemite));
    }

}
