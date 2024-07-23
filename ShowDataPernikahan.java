/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project3;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author User
 */
public class ShowDataPernikahan {
    private SimpleIntegerProperty id_pernikahan;
    private SimpleIntegerProperty user_id;
    private SimpleStringProperty nama_Calonsuami;
    private SimpleStringProperty nama_Calonistri;
    private SimpleStringProperty tanggal_pernikahan;
    private SimpleStringProperty tempat_pernikahan;
    private SimpleStringProperty status;

    public ShowDataPernikahan(int id_pernikahan, int user_id, String nama_Calonsuami, String nama_Calonistri, String tanggal_pernikahan, String tempat_pernikahan, String status) {
        this.id_pernikahan = new SimpleIntegerProperty(id_pernikahan);
        this.user_id = new SimpleIntegerProperty(user_id);
        this.nama_Calonsuami = new SimpleStringProperty(nama_Calonsuami);
        this.nama_Calonistri = new SimpleStringProperty(nama_Calonistri);
        this.tanggal_pernikahan = new SimpleStringProperty(tanggal_pernikahan);
        this.tempat_pernikahan = new SimpleStringProperty(tempat_pernikahan);
        this.status = new SimpleStringProperty(status);
    }

    public int getId_pernikahan() {
        return id_pernikahan.get();
    }

    public void setId_pernikahan(int id_pernikahan) {
        this.id_pernikahan.set(id_pernikahan);
    }

    public SimpleIntegerProperty id_pernikahanProperty() {
        return id_pernikahan;
    }

    public int getUser_id() {
        return user_id.get();
    }

    public void setUser_id(int user_id) {
        this.user_id.set(user_id);
    }

    public SimpleIntegerProperty user_idProperty() {
        return user_id;
    }

    public String getNama_Calonsuami() {
        return nama_Calonsuami.get();
    }

    public void setNama_Calonsuami(String nama_Calonsuami) {
        this.nama_Calonsuami.set(nama_Calonsuami);
    }

    public SimpleStringProperty nama_CalonsuamiProperty() {
        return nama_Calonsuami;
    }

    public String getNama_Calonistri() {
        return nama_Calonistri.get();
    }

    public void setNama_Calonistri(String nama_Calonistri) {
        this.nama_Calonistri.set(nama_Calonistri);
    }

    public SimpleStringProperty nama_CalonistProperty() {
        return nama_Calonistri;
    }

    public String getTanggal_pernikahan() {
        return tanggal_pernikahan.get();
    }

    public void setTanggal_pernikahan(String tanggal_pernikahan) {
        this.tanggal_pernikahan.set(tanggal_pernikahan);
    }

    public SimpleStringProperty tanggal_pernikahanProperty() {
        return tanggal_pernikahan;
    }

    public String getTempat_pernikahan() {
        return tempat_pernikahan.get();
    }

    public void setTempat_pernikahan(String tempat_pernikahan) {
        this.tempat_pernikahan.set(tempat_pernikahan);
    }

    public SimpleStringProperty tempat_pernikahanProperty() {
        return tempat_pernikahan;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }
}
