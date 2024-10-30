package modelo;

public class Productos {

    private int IDPRODUCTO;
    private String CODIGO;
    private String NOMBRE;
    private int STOCK;
    private double PRECIO;
    private String DESCRIPCION;
    private String CATEGORIA;

    public Productos() {
    }

    public Productos(int IDPRODUCTO, String CODIGO, String NOMBRE, int STOCK, double PRECIO, String DESCRIPCION, String CATEGORIA) {
        this.IDPRODUCTO = IDPRODUCTO;
        this.CODIGO = CODIGO;
        this.NOMBRE = NOMBRE;
        this.STOCK = STOCK;
        this.PRECIO = PRECIO;
        this.DESCRIPCION = DESCRIPCION;
        this.CATEGORIA = CATEGORIA;

    }

    public int getIDPRODUCTO() {
        return IDPRODUCTO;
    }

    public void setIDPRODUCTO(int IDPRODUCTO) {
        this.IDPRODUCTO = IDPRODUCTO;
    }

    public String getCODIGO() {
        return CODIGO;
    }

    public void setCODIGO(String CODIGO) {
        this.CODIGO = CODIGO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public int getSTOCK() {
        return STOCK;
    }

    public void setSTOCK(int STOCK) {
        this.STOCK = STOCK;
    }

    public double getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(double PRECIO) {
        this.PRECIO = PRECIO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getCATEGORIA() {
        return CATEGORIA;
    }

    public void setCATEGORIA(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }

}
