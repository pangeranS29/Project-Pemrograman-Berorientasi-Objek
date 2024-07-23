/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project3;

/**
 *
 * @author User
 */
public class CrudDatabaptis {
    
    private int userId;
    private String nama_calon;
    private String jenis_kelamin;
    private String tanggal_lahir;
    private String nama_ayah;
    private String nama_ibu;
    private String gereja;
    private String status;

  public CrudDatabaptis(int userId, String nama_calon, String jenis_kelamin, String tanggal_lahir, String nama_ayah, String nama_ibu, String gereja, String status) {
    this.userId = userId;
    this.nama_calon = nama_calon;
    this.jenis_kelamin = jenis_kelamin;
    this.tanggal_lahir = tanggal_lahir;
    this.nama_ayah = nama_ayah;
    this.nama_ibu = nama_ibu;
    this.gereja = gereja;
    this.status = status;
}
  
  public void setStatus(String status) {
    this.status = status;
}

public String getStatus() {
    return status;
}
    
     public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNama_calon() {
        return nama_calon;

    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public String getNama_ayah() {
        return nama_ayah;
    }

    public String getNama_ibu() {
        return nama_ibu;
    }

    public String getGereja() {
        return gereja;
    }

}
