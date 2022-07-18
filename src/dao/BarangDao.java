package dao;
import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.barang;

public class BarangDao {
    private Connection connection;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;
    
    private PreparedStatement getAllStatement;
    private PreparedStatement getByNamaStatement;
    
    private final String queryInsert = "INSERT INTO barang (kode,nama,satuan,jumlah,harga) "
            + "VALUES (?,?,?,?,?)";
    private final String queryUpdate = "UPDATE barang SET nama=?, satuan=?, jumlah=?, harga=? "
            + "WHERE kode=?";
    private final String queryDelete = "DELETE FROM barang where kode=?";
     
    private final String querySelect = "SELECT * FROM barang";
    private final String cariNama = "SELECT * FROM barang WHERE nama like ?";
    
    public void setConnection(Connection connection) throws SQLException{
        this.connection = connection;
        insert = this.connection.prepareStatement(queryInsert);
        update = this.connection.prepareStatement(queryUpdate);
        delete = this.connection.prepareStatement(queryDelete);
        
        getAllStatement = this.connection.prepareStatement(querySelect);
        getByNamaStatement = this.connection.prepareStatement(cariNama);
    }
    
   
    
    
    
    public barang insert(barang b) throws SQLException{
        insert.setString(1, b.getKode());
        insert.setString(2, b.getNama());
        insert.setString(3, b.getSatuan());
        insert.setInt(4, b.getJumlah());
        insert.setInt(5, b.getHarga());
        insert.executeUpdate();
        return b;
        
        
    }
    public barang update(barang b) throws SQLException{
      update.setString(1, b.getNama());
      update.setString(2, b.getSatuan());
      update.setInt(3, b.getJumlah());
      update.setInt(4, b.getHarga());
      update.setString(5, b.getKode());
      update.executeUpdate();
      return b;
    }
    
    public barang delete(barang b) throws SQLException{
      delete.setString(1, b.getKode());
      delete.executeUpdate();
      return b;
    }
    
    public List<barang> getAll() throws SQLException {
        List<barang> barangs = new ArrayList<barang>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()) {
            barang barang = new barang();
            barang.setKode(rs.getString("kode"));
            barang.setNama(rs.getString("nama"));
            barang.setSatuan(rs.getString("satuan"));
            barang.setJumlah(rs.getInt("jumlah"));
            barang.setHarga(rs.getInt("harga"));
            barangs.add(barang);
        }
        return barangs;
    }
    
    public List<barang> getByNama(String nama) throws SQLException {
        List<barang> barangs = new ArrayList<barang>();
        getByNamaStatement.setString(1, "%"+nama+"%");
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()) {
            barang barang = new barang();
            barang.setKode(rs.getString("kode"));
            barang.setNama(rs.getString("nama"));
            barang.setSatuan(rs.getString("satuan"));
            barang.setJumlah(rs.getInt("jumlah"));
            barang.setHarga(rs.getInt("harga"));
            barangs.add(barang);
        }
        return barangs;
    }

}
            