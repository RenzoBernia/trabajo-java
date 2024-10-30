package modelo;

public class Ventas {

    private int IDVENTA;
    private int IDCLIENTE;
    private int IDUSUARIO;
    private double TOTAL;
    private String FECHA;

    private String VENDEDOR;
    private double subtotal;
    private double IGV;
    //Atributos para reportes
    private int IdProducto;
    private String NombreProducto;
    private int Cantidad;
    private double Precio;
    private double Total;
    private int IdUsuario;
    private String NombreVendedor;

    public Ventas() {
    }

    public Ventas(String VENDEDOR) {
        this.VENDEDOR = VENDEDOR;
    }

    public Ventas(String FECHA, int IdProducto, String NombreProducto, int Cantidad, double Precio,double subtotal, double IGV, double Total, int IdUsuario, String NombreVendedor) {
        this.FECHA = FECHA;
        this.IdProducto = IdProducto;
        this.NombreProducto = NombreProducto;
        this.Cantidad = Cantidad;
        this.Precio = Precio;
        this.subtotal=subtotal;
        this.IGV=IGV;
        this.Total = Total;
        this.IdUsuario = IdUsuario;
        this.NombreVendedor = NombreVendedor;
    }

    public Ventas(int IDVENTA, int IDCLIENTE, int IDUSUARIO, double TOTAL, String FECHA) {
        this.IDVENTA = IDVENTA;
        this.IDCLIENTE = IDCLIENTE;
        this.IDUSUARIO = IDUSUARIO;
        this.TOTAL = TOTAL;
        this.FECHA = FECHA;
    }

    public Ventas(int IDVENTA, int IDCLIENTE, int IDUSUARIO, double IGV, double TOTAL, String FECHA) {
        this.IDVENTA = IDVENTA;
        this.IDCLIENTE = IDCLIENTE;
        this.IDUSUARIO = IDUSUARIO;
        this.IGV = IGV;
        this.TOTAL = TOTAL;
        this.FECHA = FECHA;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIGV() {
        return IGV;
    }

    public void setIGV(double IGV) {
        this.IGV = IGV;
    }
    
    public int getIDVENTA() {
        return IDVENTA;
    }

    public void setIDVENTA(int IDVENTA) {
        this.IDVENTA = IDVENTA;
    }

    public int getIDCLIENTE() {
        return IDCLIENTE;
    }

    public void setIDCLIENTE(int IDCLIENTE) {
        this.IDCLIENTE = IDCLIENTE;
    }

    public int getIDUSUARIO() {
        return IDUSUARIO;
    }

    public void setIDUSUARIO(int IDUSUARIO) {
        this.IDUSUARIO = IDUSUARIO;
    }

    public double getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(double TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getNombreVendedor() {
        return NombreVendedor;
    }

    public void setNombreVendedor(String NombreVendedor) {
        this.NombreVendedor = NombreVendedor;
    }

    public String getVENDEDOR() {
        return VENDEDOR;
    }

    public void setVENDEDOR(String VENDEDOR) {
        this.VENDEDOR = VENDEDOR;
    }

}
