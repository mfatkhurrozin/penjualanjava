package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.User;
import service.KasirService;
import service.impl.KasirServiceImpl;
import view.LoginView;
import view.MenuView;
import view.UserView;

public class UserController {
    private LoginView lv;
    private UserView uv;
    private KasirService ks;
    private List<User>listUsers;

    public UserController(LoginView loginView) {
        this.lv = loginView;
        ks = new KasirServiceImpl();
    }
    
    public UserController(UserView userView) {
        this.uv = userView;
        ks = new KasirServiceImpl();
    }
    
      public void refreshTabel(){
       //KasirService k = new KasirServiceImpl();
       //List<User> listUser = k.getAll();
         listUsers = ks.getAllUser();
         UserTableModel btm = new UserTableModel(listUsers);
         uv.getTabelUser().setModel(btm);
   }
    
     public void clearForm(){
        uv.gettUsername().setText("");
        uv.gettPassword().setText("");
        uv.clearHakakses();
        uv.gettNamaLengkap().setText("");
        uv.gettAlamat().setText("");
        
    }
    
    public void enableForm(boolean kondisi){
        uv.gettUsername().setEnabled(kondisi);
        uv.gettPassword().setEnabled(kondisi);
        uv.gettNamaLengkap().setEnabled(kondisi);
        uv.gettAlamat().setEnabled(kondisi);
        uv.getbSimpan().setEnabled(kondisi);
    }
    
    public boolean validasiForm(){
        if (
                uv.gettUsername().getText().length()>0 && 
                uv.gettPassword().getText().length()>0 && 
                uv.getHakakses().length() >0 &&
                uv.gettNamaLengkap().getText().length()>0 && 
                uv.gettAlamat().getText().length()>0) 
        {
            
            return true;
            
        }else{
            JOptionPane.showMessageDialog(uv, "Input tidak boleh kosong","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public void simpanu(){

            if(validasiForm()){
                User u = new User();
                u.setUsername(uv.gettUsername().getText());
                u.setPassword(uv.gettPassword().getText());
                u.setHakAkses(uv.getHakakses());
                u.setNamaLengkap(uv.gettNamaLengkap().getText());
                u.setAlamat(uv.gettAlamat().getText());
                ks.simpanu(u);
                JOptionPane.showMessageDialog(uv, "Data Berhasil di Simpan!","Information",JOptionPane.INFORMATION_MESSAGE);
                
            }
            
        }
    
     public void updateu(){

            if(validasiForm()){
               User u = new User();
                u.setUsername(uv.gettUsername().getText());
                u.setPassword(uv.gettPassword().getText());
                u.setHakAkses(uv.getHakakses());
                u.setNamaLengkap(uv.gettNamaLengkap().getText());
                u.setAlamat(uv.gettAlamat().getText());
                
                ks.updateu(u);
                JOptionPane.showMessageDialog(uv, "Data Berhasil di Ubah!","Information",JOptionPane.INFORMATION_MESSAGE);
                
            }
            
        }
     public void deleteu(){
         User u = new User();
         u.setUsername(uv.gettUsername().getText());
         ks.deleteu(u);
         JOptionPane.showMessageDialog(uv, "Data Berhasil di Hapus!","Information",JOptionPane.INFORMATION_MESSAGE);
                
     }
    
    
    
    public void login(){
        User User = ks.getByuser(lv.gettUsername().getText(), lv.gettPassword().getText());
        
        if(User != null){
            JOptionPane.showMessageDialog(lv, "Berhasil Login", "Info", JOptionPane.INFORMATION_MESSAGE);
            lv.dispose();
            MenuView mv = new MenuView();
            mv.setVisible(true);
            if(User.getHakAkses().equalsIgnoreCase("pemilik")){
                mv.getLabelPengguna().setText(User.getNamaLengkap());
            }
            if(User.getHakAkses().equalsIgnoreCase("kasir")){
                mv.getLabelPengguna().setText(User.getNamaLengkap());
                mv.getjMenu1().setVisible(false);
            }
        }else{
            JOptionPane.showMessageDialog(lv,"Gagal Login", "Error !",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private class UserTableModel extends AbstractTableModel {
        
         private List<User> listUsers;
         private String[] columnNames= {"Username", "Password","Hak Akses","Nama","Alamat"};
         
         public UserTableModel(List<User> listUsers){
             this.listUsers =listUsers;
         }

        @Override
        public int getRowCount() {
            return listUsers.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }
        
        @Override
        public String getColumnName(int column){
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            User u = listUsers.get(rowIndex);
            switch(columnIndex) {
                case 0:
                    return u.getUsername();
                case 1:
                    return u.getPassword();
                case 2:
                    return u.getHakAkses();
                case 3:
                    return u.getNamaLengkap();
                case 4:
                    return u.getAlamat();
                default:
                    return "";
            } 
        }
         
     }
        
}
    
   
    

