package controller;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto_Categoria;

public class CategoriaDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public List Categorias() {
        List<Producto_Categoria> listcat = new ArrayList<>();
        String sql = "SELECT * FROM producto_categoria";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto_Categoria cat = new Producto_Categoria();
                cat.setCATEGORIAID(rs.getInt("categoriaid"));
                cat.setCATEGORIA(rs.getString("categoria"));
                cat.setESTADO(rs.getInt("estado"));
                listcat.add(cat);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listcat;
    }

    public boolean GuardarCat(Producto_Categoria objeto) {
        String sql = "INSERT INTO producto_categoria VALUES (?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, objeto.getCATEGORIAID());
            ps.setString(2, objeto.getCATEGORIA());
            ps.setInt(3, objeto.getESTADO());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean ExisteCat(String categoria) {
        boolean respuesta = false;
        String sql = "SELECT categoria FROM producto_categoria WHERE categoria = '" + categoria + " ' ";
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

    public boolean ActualizarCat(Producto_Categoria cat) {
        String sql = "UPDATE producto_categoria SET categoria = ? WHERE categoriaid = ? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cat.getCATEGORIA());
            ps.setInt(2, cat.getCATEGORIAID());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public boolean EliminarCat(String CATEGORIA) {
        try {
            // Desactivar temporalmente la restricción de clave externa
            String disableForeignKeyCheck = "SET foreign_key_checks = 0;";
            PreparedStatement disableCheckPs = con.prepareStatement(disableForeignKeyCheck);
            disableCheckPs.execute();

            // Eliminar la categoría
            String deleteSql = "DELETE FROM producto_categoria WHERE CATEGORIA = ?";
            PreparedStatement deletePs = con.prepareStatement(deleteSql);
            deletePs.setString(1, CATEGORIA);
            deletePs.executeUpdate();

            // Volver a activar la restricción de clave externa
            String enableForeignKeyCheck = "SET foreign_key_checks = 1;";
            PreparedStatement enableCheckPs = con.prepareStatement(enableForeignKeyCheck);
            enableCheckPs.execute();

            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
//        String sql = "DELETE from producto_categoria WHERE categoriaid = ? ";
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setString(1, CATEGORIA);
//            ps.execute();
//            return true;
//        } catch (SQLException e) {
//            System.out.println(e.toString());
//            return false;
//        }
    }

    public Producto_Categoria BuscarId(int CATEGORIID) {
        Producto_Categoria cat = new Producto_Categoria();
        String sql = "SELECT * FROM Producto_Categoria WHERE categoriaid=? ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, CATEGORIID);
            rs = ps.executeQuery();
            if (rs.next()) {
                cat.setCATEGORIA(rs.getString("categoria"));
                cat.setESTADO(rs.getInt("estado"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return cat;
    }

}
