
package modelo;


public class Clientes {
    
    private int IDCLIENTE;
    private int DNI;
    private String NOMBRECLIENTE;
    private int TELEFONO;
    private String DIRECCION;
    private String MAIL;

    public Clientes() {
    }

    public Clientes(int IDCLIENTE, int DNI, String NOMBRECLIENTE, int TELEFONO, String DIRECCION, String MAIL) {
        this.IDCLIENTE = IDCLIENTE;
        this.DNI = DNI;
        this.NOMBRECLIENTE = NOMBRECLIENTE;
        this.TELEFONO = TELEFONO;
        this.DIRECCION = DIRECCION;
        this.MAIL = MAIL;
    }

    public int getIDCLIENTE() {
        return IDCLIENTE;
    }

    public void setIDCLIENTE(int IDCLIENTE) {
        this.IDCLIENTE = IDCLIENTE;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNOMBRECLIENTE() {
        return NOMBRECLIENTE;
    }

    public void setNOMBRECLIENTE(String NOMBRECLIENTE) {
        this.NOMBRECLIENTE = NOMBRECLIENTE;
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

    public String getMAIL() {
        return MAIL;
    }

    public void setMAIL(String MAIL) {
        this.MAIL = MAIL;
    }
    
    
}
