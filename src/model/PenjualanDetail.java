package model;


public class PenjualanDetail {
    private Integer kodeDetail;
    private Integer jumlah;
    private Double harga;
    private Double subtotal;
    private barang barang;
    private Penjualan penjualan;

    public Integer getKodeDetail() {
        return kodeDetail;
    }

    public void setKodeDetail(Integer kodeDetail) {
        this.kodeDetail = kodeDetail;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public barang getBarang() {
        return barang;
    }

    public void setBarang(barang barang) {
        this.barang = barang;
    }

    public Penjualan getPenjualan() {
        return penjualan;
    }

    public void setPenjualan(Penjualan penjualan) {
        this.penjualan = penjualan;
    }

    
    
    
}
