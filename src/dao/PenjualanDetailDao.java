package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Penjualan;
import model.PenjualanDetail;
import model.barang;

public class PenjualanDetailDao {
   private Connection connection;
    public PreparedStatement insert;
    
    
    private final String queryInsert = "INSERT INTO penjualan_detail (kode_barang,jumlah,harga,subtotal,kode_penjualan) "
            + "VALUES (?,?,?,?,?)";
    
    public void setConnection(Connection connection) throws SQLException{
        this.connection = connection;
        insert = this.connection.prepareStatement(queryInsert);
    }
    
    public PenjualanDetail insert(PenjualanDetail penjualanDetail) throws SQLException{
        insert.setString(1, penjualanDetail.getBarang().getKode());
        insert.setInt(2, penjualanDetail.getJumlah());
        insert.setDouble(3, penjualanDetail.getHarga());
        insert.setDouble(4, penjualanDetail.getSubtotal());
        insert.setString(5, penjualanDetail.getPenjualan().getKodePenjualan());
        insert.executeUpdate();
        return penjualanDetail;
    } 
}
