package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Pelanggan;

/**
 *
 * @author F
 */
public class PelangganDao {
    
    private Connection connection;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;
    
    private PreparedStatement getAllStatement;
    private PreparedStatement getByNamaStatement;
    
    private final String queryInsert = "INSERT INTO pelanggan (kode,nama,email,alamat) "
            + "VALUES (?,?,?,?)";
    private final String queryUpdate = "UPDATE pelanggan SET nama=?, email=?, alamat=? "
            + "WHERE kode=?";
    private final String queryDelete = "DELETE FROM pelanggan where kode=?";
     
    private final String querySelect = "SELECT * FROM pelanggan";
    private final String cariNama = "SELECT * FROM pelanggan WHERE nama like ?";
    
    public void setConnection(Connection connection) throws SQLException{
        this.connection = connection;
        insert = this.connection.prepareStatement(queryInsert);
        update = this.connection.prepareStatement(queryUpdate);
        delete = this.connection.prepareStatement(queryDelete);
        
        getAllStatement = this.connection.prepareStatement(querySelect);
        getByNamaStatement = this.connection.prepareStatement(cariNama);
    }
    
   
    
    
    
    public Pelanggan insert(Pelanggan b) throws SQLException{
        insert.setString(1, b.getKode());
        insert.setString(2, b.getNama());
        insert.setString(3, b.getEmail());
        insert.setString(4, b.getAlamat());
        insert.executeUpdate();
        return b;
        
        
    }
    public Pelanggan update(Pelanggan b) throws SQLException{
      update.setString(1, b.getNama());
      update.setString(2, b.getEmail());
      update.setString(3, b.getAlamat());
      update.setString(4, b.getKode());
      update.executeUpdate();
      return b;
    }
    
    public Pelanggan delete(Pelanggan b) throws SQLException{
      delete.setString(1, b.getKode());
      delete.executeUpdate();
      return b;
    }
    
    public List<Pelanggan> getAllPelanggan() throws SQLException {
        List<Pelanggan> Pelanggans = new ArrayList<Pelanggan>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()) {
            Pelanggan Pelanggan = new Pelanggan();
            Pelanggan.setKode(rs.getString("kode"));
            Pelanggan.setNama(rs.getString("nama"));
            Pelanggan.setEmail(rs.getString("email"));
            Pelanggan.setAlamat(rs.getString("alamat"));
            Pelanggans.add(Pelanggan);
        }
        return Pelanggans;
    }
    
    public List<Pelanggan> getByNamaPelanggan(String nama) throws SQLException {
        List<Pelanggan> Pelanggans = new ArrayList<Pelanggan>();
        getByNamaStatement.setString(1, "%"+nama+"%");
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()) {
            Pelanggan Pelanggan = new Pelanggan();
            Pelanggan.setKode(rs.getString("kode"));
            Pelanggan.setNama(rs.getString("nama"));
            Pelanggan.setEmail(rs.getString("email"));
            Pelanggan.setAlamat(rs.getString("alamat"));
            Pelanggans.add(Pelanggan);
        }
        return Pelanggans;
    }

}
         
