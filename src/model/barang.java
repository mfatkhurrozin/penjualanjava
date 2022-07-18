/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ACER
 */
public class barang {
   
    private String kode;
    private String nama;
    private String satuan;
    private Integer jumlah;
    private Integer harga;

    //Kode
    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
    
    //Nama
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    //Satuan    
    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    //Jumlah
    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    //Harga    
    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }
    
    
}
