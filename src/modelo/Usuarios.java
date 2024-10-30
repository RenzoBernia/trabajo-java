
package modelo;


public class Usuarios {
    private int IDUSUARIO;
    private String VENDEDOR;
    private String USUARIO;
    private String CONTRASEÑA;
    private int TELEFONO;
    private String DIRECCION;
    private int ROLESID;
    private String ROL;
    public static Usuarios user;

    public Usuarios() {
    }

    public Usuarios(int IDUSUARIO, String VENDEDOR, String USUARIO, String CONTRASEÑA, int TELEFONO, String DIRECCION, int ROLESID, String ROL) {
        this.IDUSUARIO = IDUSUARIO;
        this.VENDEDOR = VENDEDOR;
        this.USUARIO = USUARIO;
        this.CONTRASEÑA = CONTRASEÑA;
        this.TELEFONO = TELEFONO;
        this.DIRECCION = DIRECCION;
        this.ROLESID = ROLESID;
        this.ROL = ROL;
    }

    public int getIDUSUARIO() {
        return IDUSUARIO;
    }

    public void setIDUSUARIO(int IDUSUARIO) {
        this.IDUSUARIO = IDUSUARIO;
    }

    public String getVENDEDOR() {
        return VENDEDOR;
    }

    public void setVENDEDOR(String VENDEDOR) {
        this.VENDEDOR = VENDEDOR;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getCONTRASEÑA() {
        return CONTRASEÑA;
    }

    public void setCONTRASEÑA(String CONTRASEÑA) {
        this.CONTRASEÑA = CONTRASEÑA;
    }

    public int getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(int TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public int getROLESID() {
        return ROLESID;
    }

    public void setROLESID(int ROLESID) {
        this.ROLESID = ROLESID;
    }

    public String getROL() {
        return ROL;
    }

    public void setROL(String ROL) {
        this.ROL = ROL;
    }


    
    
}
