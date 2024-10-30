
package modelo;

public class Producto_Categoria {
    
    private int CATEGORIAID;
    private String CATEGORIA;
    private int ESTADO;

    public Producto_Categoria() {
    }

    public Producto_Categoria(int CATEGORIAID, String CATEGORIA, int ESTADO) {
        this.CATEGORIAID = CATEGORIAID;
        this.CATEGORIA = CATEGORIA;
        this.ESTADO = ESTADO;
    }

    public int getCATEGORIAID() {
        return CATEGORIAID;
    }

    public void setCATEGORIAID(int CATEGORIAID) {
        this.CATEGORIAID = CATEGORIAID;
    }

    public String getCATEGORIA() {
        return CATEGORIA;
    }

    public void setCATEGORIA(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }

    public int getESTADO() {
        return ESTADO;
    }

    public void setESTADO(int ESTADO) {
        this.ESTADO = ESTADO;
    }
    
    
}
