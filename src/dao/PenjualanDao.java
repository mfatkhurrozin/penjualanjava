package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Penjualan;

public class PenjualanDao {
    private Connection connection;
    public PreparedStatement insert;
    
    
    private final String queryInsert = "INSERT INTO penjualan (kode_penjualan,tanggal_penjualan,total_penjualan) "
            + "VALUES (?,?,?)";
    
    public void setConnection(Connection connection) throws SQLException{
        this.connection = connection;
        insert = this.connection.prepareStatement(queryInsert);
    }
    
    public Penjualan insert(Penjualan penjualan) throws SQLException{
        insert.setString(1, penjualan.getKodePenjualan());
        insert.setObject(2, penjualan.getTanggalPenjualan());
        insert.setDouble(3, penjualan.getTotalTransaksi());
        insert.executeUpdate();
        return penjualan;
    }
}
