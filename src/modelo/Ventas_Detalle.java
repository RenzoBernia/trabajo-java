
package modelo;

public class Ventas_Detalle {
    
    private int IDDETALLEVENTA;
    private int IDVENTA;
    private int IDPRODUCTO;
    private int CANTIDAD;
    private double PRECIO;
    private double Subtotal;
    private double IGV;

    public Ventas_Detalle() {
    }

    public Ventas_Detalle(int IDDETALLEVENTA, int IDVENTA, int IDPRODUCTO, int CANTIDAD, double PRECIO) {
        this.IDDETALLEVENTA = IDDETALLEVENTA;
        this.IDVENTA = IDVENTA;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANTIDAD = CANTIDAD;
        this.PRECIO = PRECIO;
    }

    public Ventas_Detalle(int IDDETALLEVENTA, int IDVENTA, int IDPRODUCTO, int CANTIDAD, double PRECIO, double IGV, double Subtotal) {
        this.IDDETALLEVENTA = IDDETALLEVENTA;
        this.IDVENTA = IDVENTA;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANTIDAD = CANTIDAD;
        this.PRECIO = PRECIO;
        this.IGV = IGV;
        this.Subtotal=Subtotal;
    }

    public double getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(double Subtotal) {
        this.Subtotal = Subtotal;
    }

    public double getIGV() {
        return IGV;
    }

    public void setIGV(double IGV) {
        this.IGV = IGV;
    }
    
    
    public int getIDDETALLEVENTA() {
        return IDDETALLEVENTA;
    }

    public void setIDDETALLEVENTA(int IDDETALLEVENTA) {
        this.IDDETALLEVENTA = IDDETALLEVENTA;
    }

    public int getIDVENTA() {
        return IDVENTA;
    }

    public void setIDVENTA(int IDVENTA) {
        this.IDVENTA = IDVENTA;
    }

    public int getIDPRODUCTO() {
        return IDPRODUCTO;
    }

    public void setIDPRODUCTO(int IDPRODUCTO) {
        this.IDPRODUCTO = IDPRODUCTO;
    }

    public int getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(int CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public double getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(double PRECIO) {
        this.PRECIO = PRECIO;
    }
    
    
}
