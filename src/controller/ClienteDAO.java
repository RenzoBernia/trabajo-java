package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Clientes;

public class ClienteDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarCliente(Clientes cl) {
        String sql = "INSERT INTO clientes (dni, nombrecliente, telefono, direccion, mail) VALUES (?,?,?,?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getDNI());
            ps.setString(2, cl.getNOMBRECLIENTE());
            ps.setInt(3, cl.getTELEFONO());
            ps.setString(4, cl.getDIRECCION());
            ps.setString(5, cl.getMAIL());
            ps.execute();
            System.out.println("Modificando usuario con ID: " + cl.getIDCLIENTE());
            System.out.println("Nombre: " + cl.getNOMBRECLIENTE());
            System.out.println("Correo: " + cl.getDIRECCION());
            return true;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public List ListarCliente() {
        List<Clientes> ListaCl = new ArrayList();
        String sql = "SELECT * FROM clientes";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Clientes cl = new Clientes();
                cl.setIDCLIENTE(rs.getInt("idcliente"));
                cl.setDNI(rs.getInt("dni"));
                cl.setNOMBRECLIENTE(rs.getString("nombrecliente"));
                cl.setTELEFONO(rs.getInt("telefono"));
                cl.setDIRECCION(rs.getString("direccion"));
                cl.setMAIL(rs.getString("mail"));
                ListaCl.add(cl);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaCl;
    }

    public boolean EliminarCliente(int idcliente) {
        String sql = "DELETE FROM clientes WHERE idcliente = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idcliente);
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

    public boolean ModificarCliente(Clientes cl) {
        String sql = "UPDATE clientes SET dni=?, nombrecliente=?, telefono=?, direccion=?, mail=? WHERE idcliente=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getDNI());
            ps.setString(2, cl.getNOMBRECLIENTE());
            ps.setInt(3, cl.getTELEFONO());
            ps.setString(4, cl.getDIRECCION());
            ps.setInt(5, cl.getIDCLIENTE());
            ps.setString(6, cl.getMAIL());
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

    public Clientes Buscarcliente(int dni) {
        Clientes cl = new Clientes();
        String sql = "SELECT * FROM clientes WHERE dni = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                cl.setIDCLIENTE(rs.getInt("idcliente"));
                cl.setNOMBRECLIENTE(rs.getString("nombrecliente"));
                cl.setTELEFONO(rs.getInt("telefono"));
                cl.setDIRECCION(rs.getString("direccion"));
                cl.setMAIL(rs.getString("mail"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return cl;
    }

}
