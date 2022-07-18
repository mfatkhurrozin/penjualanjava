package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDao {
    private Connection connection;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;
    
    private PreparedStatement getAllStatement;
    private PreparedStatement getByUserStatement;
     private PreparedStatement getByNamaStatement;
     
    
    private final String queryInsert = "INSERT INTO user (username,password,hak_akses,nama_lengkap,alamat) "
            + "VALUES (?,?,?,?,?)";
    private final String queryUpdate = "UPDATE user SET password=?, hak_akses=?,nama_lengkap=?, alamat=? "
            + "WHERE username=?";
    private final String queryDelete = "DELETE FROM user where username=?";
    
    private final String getByUserQuery = "SELECT * FROM user WHERE username = ? AND password = SHA1(?) ";
//    private final String getByUserQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
    private final String querySelect = "SELECT * FROM user";
    private final String cariNama = "SELECT * FROM user WHERE username like ?";
    
     public void setConnection(Connection connection) throws SQLException{
        this.connection = connection;
        getByUserStatement = this.connection.prepareStatement(getByUserQuery);
        insert = this.connection.prepareStatement(queryInsert);
        update = this.connection.prepareStatement(queryUpdate);
        delete = this.connection.prepareStatement(queryDelete);
        
        getAllStatement = this.connection.prepareStatement(querySelect);
        getByNamaStatement = this.connection.prepareStatement(cariNama);
        
     }
     
     public User insert(User u) throws SQLException{
        insert.setString(1, u.getUsername());
        insert.setString(2, u.getPassword());
        insert.setString(3, u.getHakAkses());
        insert.setString(4, u.getNamaLengkap());
        insert.setString(5, u.getAlamat());
        insert.executeUpdate();
        return u;
        
        
    }
    public User update(User u) throws SQLException{
        update.setString(1, u.getPassword());
        update.setString(2, u.getHakAkses());
        update.setString(3, u.getNamaLengkap());
        update.setString(4, u.getAlamat());
        update.setString(5, u.getUsername());
        update.executeUpdate();
      return u;
    }
    
    public User delete(User u) throws SQLException{
      delete.setString(1, u.getUsername());
      delete.executeUpdate();
      return u;
    }
    
    public List<User> getAllUser() throws SQLException {
        List<User> Users = new ArrayList<User>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()) {
            User User = new User();
            User.setUsername(rs.getString("username"));
            User.setPassword(rs.getString("password"));
            User.setHakAkses(rs.getString("hak_akses"));
            User.setNamaLengkap(rs.getString("nama_lengkap"));
            User.setAlamat(rs.getString("alamat"));
            Users.add(User);
        }
        return Users;
    }
    
    public List<User> getByNamaUser(String nama) throws SQLException {
        List<User> Users = new ArrayList<User>();
        getByNamaStatement.setString(1, "%"+nama+"%");
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()) {
           User User = new User();
            User.setUsername(rs.getString("username"));
            User.setPassword(rs.getString("password"));
            User.setHakAkses(rs.getString("hak_akses"));
            User.setNamaLengkap(rs.getString("nama_lengkap"));
            User.setAlamat(rs.getString("alamat"));
            Users.add(User);
        }
        return Users;
    }
     
     public User getByUser (String username, String password) throws SQLException{
        getByUserStatement.setString(1, username);
        getByUserStatement.setString(2, password);
        ResultSet rs = getByUserStatement.executeQuery();
        
        if(rs.next()) {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setHakAkses(rs.getString("hak_akses"));
            user.setNamaLengkap(rs.getString("nama_lengkap"));
            user.setAlamat(rs.getString("alamat"));
            return user;
        }
        return null;
         
     }
    
}
