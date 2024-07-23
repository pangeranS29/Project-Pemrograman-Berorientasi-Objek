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
public class ShowDataNaiksidi {
    
    private SimpleIntegerProperty id_naiksidi;
    private SimpleIntegerProperty user_id;
    private SimpleStringProperty nama_calon;
    private SimpleStringProperty jenis_kelamin;
    private SimpleStringProperty tanggal_lahir;
    private SimpleStringProperty nama_ayah;
    private SimpleStringProperty nama_ibu;
    private SimpleStringProperty gereja;
    private SimpleStringProperty status;

    public ShowDataNaiksidi(int id_naiksidi, int user_id, String nama_calon, String jenis_kelamin, String tanggal_lahir, String nama_ayah, String nama_ibu, String gereja, String status) {
        this.id_naiksidi = new SimpleIntegerProperty(id_naiksidi);
        this.user_id = new SimpleIntegerProperty(user_id);
        this.nama_calon = new SimpleStringProperty(nama_calon);
        this.jenis_kelamin = new SimpleStringProperty(jenis_kelamin);
        this.tanggal_lahir = new SimpleStringProperty(tanggal_lahir);
        this.nama_ayah = new SimpleStringProperty(nama_ayah);
        this.nama_ibu = new SimpleStringProperty(nama_ibu);
        this.gereja = new SimpleStringProperty(gereja);
        this.status = new SimpleStringProperty(status);
    }

    public int getId_naiksidi() {
        return id_naiksidi.get();
    }

    public void setId_naiksidi(int id_baptis) {
        this.id_naiksidi.set(id_baptis);
    }

    public SimpleIntegerProperty id_naiksidiProperty() {
        return id_naiksidi;
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

    public String getNama_calon() {
        return nama_calon.get();
    }

    public void setNama_calon(String nama_calon) {
        this.nama_calon.set(nama_calon);
    }

    public SimpleStringProperty nama_calonProperty() {
        return nama_calon;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin.get();
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin.set(jenis_kelamin);
    }

    public SimpleStringProperty jenis_kelaminProperty() {
        return jenis_kelamin;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir.get();
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir.set(tanggal_lahir);
    }

    public SimpleStringProperty tanggal_lahirProperty() {
        return tanggal_lahir;
    }

    public String getNama_ayah() {
        return nama_ayah.get();
    }

    public void setNama_ayah(String nama_ayah) {
        this.nama_ayah.set(nama_ayah);
    }

    public SimpleStringProperty nama_ayahProperty() {
        return nama_ayah;
    }

    public String getNama_ibu() {
        return nama_ibu.get();
    }

    public void setNama_ibu(String nama_ibu) {
        this.nama_ibu.set(nama_ibu);
    }

    public SimpleStringProperty nama_ibuProperty() {
        return nama_ibu;
    }

    public String getGereja() {
        return gereja.get();
    }

    public void setGereja(String gereja) {
        this.gereja.set(gereja);
    }

    public SimpleStringProperty gerejaProperty() {
        return gereja;
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
