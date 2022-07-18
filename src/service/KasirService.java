package service;

import java.util.List;
import model.Pelanggan;
import model.Penjualan;
import model.PenjualanDetail;
import model.User;
import model.barang;

public interface KasirService {
    barang simpan(barang b);
    barang update(barang b);
    barang delete(barang b);
    
    Pelanggan simpanp(Pelanggan p);
    Pelanggan updatep(Pelanggan p);
    Pelanggan deletep(Pelanggan p);
    
    User simpanu(User u);
    User updateu(User u);
    User deleteu(User u);
    
    List<barang> getAll();
    List<barang> getCariNama(String nama);
    
    List<Pelanggan> getAllPelanggan();
    List<Pelanggan> getCariNamaPelanggan(String nama);
    
    List<User> getAllUser();
    
    Penjualan simpanPenjualan(Penjualan p);
    
    PenjualanDetail simpanPenjualanDetail(PenjualanDetail pd);
    User getByuser(String username, String password);
}
