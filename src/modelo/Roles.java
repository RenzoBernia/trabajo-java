
package modelo;

public class Roles {
    private int ROLESID;
    private String ROL;

    public Roles() {
    }

    public Roles(int ROLESID, String ROL) {
        this.ROLESID = ROLESID;
        this.ROL = ROL;
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
