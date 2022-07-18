/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Koneksi {
    protected static Connection konek;
    public static Connection getKoneksi(){
        if (konek == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setDatabaseName("penjualan_db");
            dataSource.setUser("root");
            dataSource.setPassword("");
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);

            try {
            konek = (Connection) dataSource.getConnection();
            } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return konek;
    }
}
