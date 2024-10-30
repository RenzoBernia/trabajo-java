package controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.filechooser.FileSystemView;
import modelo.Ventas;
import modelo.Ventas_Detalle;

public class VentaDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public int IdVenta() {
        int IDVENTA = 0;
        String sql = "SELECT MAX(IDVENTA) FROM ventas";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                IDVENTA = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return IDVENTA;
    }

    public int RegistrarVenta(Ventas v) {
        String sql = "INSERT INTO ventas (idcliente, idusuario,subtotal, igv, total,fecha) VALUES (?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, v.getIDCLIENTE());
            ps.setInt(2, v.getIDUSUARIO());
            ps.setDouble(3, v.getSubtotal());
            ps.setDouble(4, v.getIGV());
            ps.setDouble(5, v.getTOTAL());
            ps.setString(6, v.getFECHA());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }

    public int RegistrarDetalle(Ventas_Detalle Dv) {
        String sql = "INSERT INTO ventas_detalle (idproducto, cantidad,subtotal, igv, precio, idventa) VALUES (?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, Dv.getIDPRODUCTO());
            ps.setInt(2, Dv.getCANTIDAD());
            ps.setDouble(3, Dv.getSubtotal());
            ps.setDouble(4, Dv.getIGV());
            ps.setDouble(5, Dv.getPRECIO());
            ps.setInt(6, Dv.getIDVENTA());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }

    public boolean ActualizarStock(int cant, int idproducto) {
        String sql = "UPDATE productos SET stock = ? WHERE idproducto = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, idproducto);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public List Listarventas() {
        List<Ventas> ListarVenta = new ArrayList();
        String sql = " select b.VENDEDOR, a.*  from ventas a INNER JOIN usuarios b ON a.idusuario = b.IDUSUARIO ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ventas vent = new Ventas();
                vent.setVENDEDOR(rs.getString("VENDEDOR"));
                vent.setIDVENTA(rs.getInt("IDVENTA"));
                vent.setIDCLIENTE(rs.getInt("idcliente"));
                vent.setIDUSUARIO(rs.getInt("idusuario"));
                vent.setTOTAL(rs.getDouble("total"));
                vent.setFECHA(rs.getString("fecha"));
                ListarVenta.add(vent);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListarVenta;
    }

    public Ventas BuscarVenta(int idventa) {
        Ventas cl = new Ventas();
        String sql = "SELECT * FROM ventas WHERE idventa = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idventa);
            rs = ps.executeQuery();
            if (rs.next()) {
                cl.setIDVENTA(rs.getInt("idventa"));
                cl.setIDCLIENTE(rs.getInt("idcliente"));
                cl.setIDUSUARIO(rs.getInt("idusuario"));
                cl.setTOTAL(rs.getDouble("total"));
                cl.setFECHA(rs.getString("fecha"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return cl;
    }

    public List ListarVentxVend() {
        List<Ventas> ListarVenxVend = new ArrayList();
        String sql = "  select "
                + "    p.IDPRODUCTO as IdProducto, p.NOMBRE as NombreProducto, "
                + "    d.CANTIDAD as Cantidad, p.PRECIO as Precio,     	"
                + "	v.TOTAL - v.IGV as Subtotal,  v.IGV as IGV, "
                + "    v.TOTAL as Total, v.idusuario as IdUsuario, "
                + "    u.VENDEDOR as NombreVendedor, v.FECHA as FECHA "
                + "from usuarios u "
                + "inner join ventas v on u.IDUSUARIO = v.idusuario  "
                + "inner join ventas_detalle d on v.IDVENTA = d.IDVENTA  "
                + "inner join productos p on d.IDPRODUCTO = p.IDPRODUCTO;  ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ventas vent = new Ventas();
                vent.setIdProducto(rs.getInt("IDPRODUCTO"));
                vent.setNombreProducto(rs.getString("NombreProducto"));
                vent.setCantidad(rs.getInt("CANTIDAD"));
                vent.setPrecio(rs.getDouble("Precio"));
                vent.setSubtotal(rs.getDouble("Subtotal"));
                vent.setIGV(rs.getDouble("IGV"));
                vent.setTotal(rs.getDouble("Total"));
                vent.setIDUSUARIO(rs.getInt("IdUsuario"));
                vent.setNombreVendedor(rs.getString("NombreVendedor"));
                vent.setFECHA(rs.getString("Fecha"));
                ListarVenxVend.add(vent);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListarVenxVend;
    }

    public List ProdxFecha() {
        List<Ventas> Listarprodxfecha = new ArrayList<>();
        String sql = "SELECT  "
                + "   p.IDPRODUCTO as IdProducto,  "
                + "   p.NOMBRE as NombreProducto, "
                + "   d.CANTIDAD as Cantidad,  "
                + "   p.PRECIO as Precio, "
                + "   d.CANTIDAD * p.PRECIO as Subtotal, "
                + "   v.IGV as IGV, "
                + "   v.TOTAL as Total, "
                + "   v.FECHA as FECHA "
                + "   FROM ventas v  "
                + "   INNER JOIN ventas_detalle d ON v.IDVENTA = d.IDVENTA "
                + "   INNER JOIN productos p ON d.IDPRODUCTO = p.IDPRODUCTO; ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ventas prod = new Ventas();
                prod.setIdProducto(rs.getInt("IDPRODUCTO"));
                prod.setNombreProducto(rs.getString("NombreProducto"));
                prod.setCantidad(rs.getInt("Cantidad"));
                prod.setPrecio(rs.getDouble("Precio"));
                prod.setSubtotal(rs.getDouble("Subtotal"));
                prod.setIGV(rs.getDouble("IGV"));
                prod.setTotal(rs.getDouble("Total"));
                prod.setFECHA(rs.getString("Fecha"));
                Listarprodxfecha.add(prod);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listarprodxfecha;
    }
}
