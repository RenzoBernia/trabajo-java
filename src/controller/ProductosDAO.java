package controller;

import modelo.Config;
import modelo.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Clientes;
import modelo.Ventas;

public class ProductosDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarProductos(Productos pro) {
        String sql = "INSERT INTO productos (codigo, nombre, stock, precio , descripcion, categoria) VALUES (?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCODIGO());
            ps.setString(2, pro.getNOMBRE());
            ps.setInt(3, pro.getSTOCK());
            ps.setDouble(4, pro.getPRECIO());
            ps.setString(5, pro.getDESCRIPCION());
//            ps.setInt(6, pro.getCATEGORIAID());
            ps.setString(6, pro.getCATEGORIA());
//            ps.setInt(7, pro.getESTADO());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public List ListarProductos() {
        List<Productos> Listapro = new ArrayList();
        String sql = "SELECT * FROM productos";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos pro = new Productos();
                pro.setIDPRODUCTO(rs.getInt("idproducto"));
                pro.setCODIGO(rs.getString("codigo"));
                pro.setNOMBRE(rs.getString("nombre"));
                pro.setSTOCK(rs.getInt("stock"));
                pro.setPRECIO(rs.getDouble("precio"));
                pro.setDESCRIPCION(rs.getString("descripcion"));
                pro.setCATEGORIA(rs.getString("categoria"));
//                pro.setCATEGORIAID(rs.getInt("categoriaid"));
//                pro.setESTADO(rs.getInt("estado"));
                Listapro.add(pro);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapro;
    }

    public boolean EliminarProductos(int idproducto) {
        String sql = "DELETE FROM productos WHERE idproducto = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idproducto);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public boolean ModificarProductos(Productos pro) {
        String sql = "UPDATE productos SET codigo=?, nombre=?, stock=?, precio=? , descripcion=? , categoria=? WHERE idproducto=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCODIGO());
            ps.setString(2, pro.getNOMBRE());
            ps.setInt(3, pro.getSTOCK());
            ps.setDouble(4, pro.getPRECIO());
            ps.setString(5, pro.getDESCRIPCION());
//            ps.setInt(6, pro.getCATEGORIAID());
            ps.setString(6, pro.getCATEGORIA());
            ps.setInt(7, pro.getIDPRODUCTO());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public Productos BuscarPro(String cod) {
        Productos producto = new Productos();
        String sql = "SELECT * FROM productos WHERE codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                producto.setIDPRODUCTO(rs.getInt("idproducto"));
                producto.setCODIGO(rs.getString("codigo"));
                producto.setNOMBRE(rs.getString("nombre"));
                producto.setPRECIO(rs.getDouble("precio"));
                producto.setSTOCK(rs.getInt("stock"));
                producto.setDESCRIPCION(rs.getString("descripcion"));
//                producto.setCATEGORIAID(rs.getInt("categoriaid"));
                producto.setCATEGORIA(rs.getString("categoria"));
//                producto.setESTADO(rs.getInt("estado"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }

    public Productos BuscarId(int idproducto) {
        Productos pro = new Productos();
        String sql = "SELECT * FROM productos WHERE idproducto = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idproducto);
            rs = ps.executeQuery();
            if (rs.next()) {
//                pro.setIDPRODUCTO(rs.getInt("idproducto"));
                pro.setCODIGO(rs.getString("codigo"));
                pro.setNOMBRE(rs.getString("nombre"));
                pro.setSTOCK(rs.getInt("stock"));
                pro.setPRECIO(rs.getInt("precio"));
                pro.setDESCRIPCION(rs.getString("descripcion"));
//                pro.setCATEGORIAID(rs.getInt("categoriaid"));
                pro.setCATEGORIA(rs.getString("categoria"));
//                pro.setESTADO(rs.getInt("estado"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return pro;
    }

    public Config BuscarDatos() {
        Config conf = new Config();
        String sql = "SELECT * FROM config";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                conf.setId(rs.getInt("id"));
                conf.setRuc(rs.getString("ruc"));
                conf.setNombre(rs.getString("nombre"));
                conf.setTelefono(rs.getString("telefono"));
                conf.setDireccion(rs.getString("direccion"));
                conf.setRazon(rs.getString("razon"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return conf;
    }

    public boolean ModificarDatos(Config conf) {
        String sql = "UPDATE config SET ruc=?, nombre=?, telefono=?, direccion=?, razon=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, conf.getRuc());
            ps.setString(2, conf.getNombre());
            ps.setString(3, conf.getTelefono());
            ps.setString(4, conf.getDireccion());
            ps.setString(5, conf.getRazon());
            ps.setInt(6, conf.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public boolean ValidarProducto(String producto) {
        boolean respuesta = false;
        String sql = "SELECT * FROM productos WHERE nombre = ' " + producto + "'";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Errro al consultar categoria: " + e);
        }
        return respuesta;
    }


}
