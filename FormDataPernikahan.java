/*

Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template */ package project3;
/**
*

@author User
*/
public class FormDataPernikahan {
private int userId;
private String nama_Calonsuami;
private String nama_Calonistri;
private String tanggal_pernikahan;
private String tempat_pernikahan;
private String status;

public FormDataPernikahan(int userId, String nama_Calonsuami, String nama_Calonistri, String tanggal_pernikahan, String tempat_pernikahan,String status) {
this.userId = userId;
this.nama_Calonsuami = nama_Calonsuami;
this.nama_Calonistri = nama_Calonistri;
this.tanggal_pernikahan = tanggal_pernikahan;
this.tempat_pernikahan = tempat_pernikahan;
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

public String getNama_Calonsuami() {
return nama_Calonsuami;
}

public void setNama_Calonsuami(String nama_Calonsuami) {
this.nama_Calonsuami = nama_Calonsuami;
}

public String getNama_Calonistri() {
return nama_Calonistri;
}

public void setNama_Calonistri(String nama_Calonistri) {
this.nama_Calonistri = nama_Calonistri;
}

public String getTanggal_pernikahan() {
return tanggal_pernikahan;
}

public void setTanggal_pernikahan(String tanggal_pernikahan) {
this.tanggal_pernikahan = tanggal_pernikahan;
}

public String getTempat_pernikahan() {
return tempat_pernikahan;
}

public void setTempat_pernikahan(String tempat_pernikahan) {
this.tempat_pernikahan = tempat_pernikahan;
}

}