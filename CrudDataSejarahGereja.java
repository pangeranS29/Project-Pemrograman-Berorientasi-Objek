package project3;

/**
 *
 * @author User
 */
public class CrudDataSejarahGereja {
    private int id;
    private String deskripsi;

    public CrudDataSejarahGereja(int id, String deskripsi) {
        this.id = id;
        this.deskripsi = deskripsi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
