package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Pelanggan;
import view.PelangganView;
import model.barang;
import service.KasirService;
import service.impl.KasirServiceImpl;

public class PelangganController {
    PelangganView pv;
    KasirService ks;
    private List<Pelanggan>listPelanggans;
    
    public PelangganController(PelangganView PelangganView){
        this.pv = PelangganView;
        ks = new KasirServiceImpl();
    }
    
     public void refreshTabel(){
       //KasirService k = new KasirServiceImpl();
       //List<Pelanggan> listPelanggan = k.getAll();
         listPelanggans = ks.getAllPelanggan();
         PelangganTableModel btm = new PelangganTableModel(listPelanggans);
         pv.getTabelPelanggan().setModel(btm);
   }
    
    public void clearForm(){
        pv.gettKode().setText("");
        pv.gettNama().setText("");
        pv.gettEmail().setText("");
        pv.gettAlamat().setText("");
        
    }
    
    public void enableForm(boolean kondisi){
        pv.gettKode().setEnabled(kondisi);
        pv.gettNama().setEnabled(kondisi);
        pv.gettEmail().setEnabled(kondisi);
        pv.gettAlamat().setEnabled(kondisi);
    }
    
    public boolean validasiForm(){
        if (
                pv.gettKode().getText().length()>0 && 
                pv.gettNama().getText().length()>0 && 
                pv.gettEmail().getText().length()>0 && 
                pv.gettAlamat().getText().length()>0) 
        {
            
            return true;
            
        }else{
            JOptionPane.showMessageDialog(pv, "Input tidak boleh kosong","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public void simpanp(){

            if(validasiForm()){
                Pelanggan p = new Pelanggan();
                p.setKode(pv.gettKode().getText());
                p.setNama(pv.gettNama().getText());
                p.setEmail(pv.gettEmail().getText());
                p.setAlamat(pv.gettAlamat().getText());
                ks.simpanp(p);
                JOptionPane.showMessageDialog(pv, "Data Berhasil di Simpan!","Information",JOptionPane.INFORMATION_MESSAGE);
                
            }
            
        }
    
     public void updatep(){

            if(validasiForm()){
               Pelanggan p = new Pelanggan();
                p.setNama(pv.gettNama().getText());
                p.setEmail(pv.gettEmail().getText());
                p.setAlamat(pv.gettAlamat().getText());
                p.setKode(pv.gettKode().getText());
                ks.updatep(p);
                JOptionPane.showMessageDialog(pv, "Data Berhasil di Ubah!","Information",JOptionPane.INFORMATION_MESSAGE);
                
            }
            
        }
     public void deletep(){
         Pelanggan b = new Pelanggan();
         b.setKode(pv.gettKode().getText());
         ks.deletep(b);
         JOptionPane.showMessageDialog(pv, "Data Berhasil di Hapus!","Information",JOptionPane.INFORMATION_MESSAGE);
                
     }
     
     private class PelangganTableModel extends AbstractTableModel {
         
         private List<Pelanggan> listPelanggans;
         private String[] columnNames= {"kode", "nama","email","alamat"};
         
         public PelangganTableModel(List<Pelanggan> listPelanggans){
             this.listPelanggans =listPelanggans;
         }

        @Override
        public int getRowCount() {
             return listPelanggans.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }
        
         @Override
        public String getColumnName(int column){
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Pelanggan p = listPelanggans.get(rowIndex);
            switch(columnIndex) {
                case 0:
                    return p.getKode();
                case 1:
                    return p.getNama();
                case 2:
                    return p.getEmail();
                case 3:
                    return p.getAlamat();
                default:
                    return "";
            } 
        }
         
     }

}