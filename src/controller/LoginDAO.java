package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Roles;
import modelo.Usuarios;

public class LoginDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public Usuarios log(String usuario, String contraseña, String rol) {
        Usuarios l = new Usuarios();
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?  AND rol = ? ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
//            ps.setInt(3, rolesid);
            ps.setString(3, rol);
            rs = ps.executeQuery();
            if (rs.next()) {
                l.setIDUSUARIO(rs.getInt("idusuario"));
//                l.setNOMBRE(rs.getString("nombre"));
                l.setUSUARIO(rs.getString("usuario"));
                l.setCONTRASEÑA(rs.getString("contraseña"));
//                l.setROLESID(rs.getInt("rolesid"));
                l.setROL(rs.getString("rol"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }

    public boolean Registrar(Usuarios reg) {
        String sql = "INSERT INTO usuarios (vendedor, usuario, contraseña, telefono, direccion, rolesid, rol) VALUES (?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, reg.getVENDEDOR());
            ps.setString(2, reg.getUSUARIO());
            ps.setString(3, reg.getCONTRASEÑA());
            ps.setInt(4, reg.getTELEFONO());
            ps.setString(5, reg.getDIRECCION());
            ps.setInt(6, reg.getROLESID());
            ps.setString(7, reg.getROL());
            ps.execute();
            
            return true;
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public List ListarUsuarios() {
        List<Usuarios> Lista = new ArrayList();
        String sql = "SELECT * FROM usuarios";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuarios lg = new Usuarios();
                lg.setIDUSUARIO(rs.getInt("idusuario"));
                lg.setVENDEDOR(rs.getString("vendedor"));
                lg.setUSUARIO(rs.getString("usuario"));
                lg.setCONTRASEÑA(rs.getString("contraseña"));
                lg.setTELEFONO(rs.getInt("telefono"));
                lg.setDIRECCION(rs.getString("direccion"));               
                lg.setROLESID(rs.getInt("rolesid"));
                lg.setROL(rs.getString("rol"));
                Lista.add(lg);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Lista;
    }

    public List Roles() {
        List<Roles> listaroles = new ArrayList<>();
        String sql = "SELECT * FROM roles";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Roles rol = new Roles();
                rol.setROLESID(rs.getInt("rolesid"));
                rol.setROL(rs.getString("rol"));
                listaroles.add(rol);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaroles;
    }
    
    public boolean ModificarUsuario(Usuarios us){
        String sql = "UPDATE usuarios  SET vendedor=?, usuario=?, contraseña=?, telefono=?, direccion=?, rol=?, rolesid=? WHERE idusuario=? ";
        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getVENDEDOR());
            ps.setString(2, us.getUSUARIO());
            ps.setString(3, us.getCONTRASEÑA());
            ps.setInt(4, us.getTELEFONO());
            ps.setString(5, us.getDIRECCION());
            ps.setString(6, us.getROL());
            ps.setInt(7, us.getROLESID());  // Asegúrate de incluir esto
            ps.setInt(8, us.getIDUSUARIO());
            
            int rowsUpdated = ps.executeUpdate();
            
            //de abajo recien añadi asi que se peude borrar
             return rowsUpdated > 0;
        } 
        catch (SQLException e) {
            System.out.println("Error al modificar usuario: " + e.getMessage());
            return false;
        }
        
    }
   
}
