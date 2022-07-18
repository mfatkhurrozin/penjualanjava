package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import view.BarangView;
import model.barang;
import service.KasirService;
import service.impl.KasirServiceImpl;
import view.TabelBarang;

public class BarangController {
    BarangView bv;
    TabelBarang tb;
    KasirService ks;
    private List<barang>listBarangs;
    
    public BarangController(BarangView barangView){
        this.bv = barangView;
        ks = new KasirServiceImpl();
    }
    public BarangController(TabelBarang tabelBarang){
        this.tb = tabelBarang;
        ks = new KasirServiceImpl();
    }
    
     public void refreshTabel(){
       //KasirService k = new KasirServiceImpl();
       //List<Barang> listBarang = k.getAll();
         listBarangs = ks.getAll();
         BarangTableModel btm = new BarangTableModel(listBarangs);
         bv.getTabelBarang().setModel(btm);
   }
     public void refreshTabel2(){
       //KasirService k = new KasirServiceImpl();
       //List<Barang> listBarang = k.getAll();
         listBarangs = ks.getAll();
         BarangTableModel btm = new BarangTableModel(listBarangs);
         tb.getTabelBarang().setModel(btm);
   }
     
    
    public void clearForm(){
        bv.getKode().setText("");
        bv.getNama().setText("");
        bv.getCmbSatuan().setSelectedIndex(0);
        bv.getJumlah().setText("");
        bv.getHarga().setText("");
        
    }
    
    public void enableForm(boolean kondisi){
        bv.getKode().setEnabled(kondisi);
        bv.getNama().setEnabled(kondisi);
        bv.getCmbSatuan().setEnabled(kondisi);
        bv.getJumlah().setEnabled(kondisi);
        bv.getHarga().setEnabled(kondisi);
    }
    
    public boolean validasiForm(){
        if (
                bv.getKode().getText().length()>0 && 
                bv.getNama().getText().length()>0 && 
                bv.getCmbSatuan().getSelectedIndex() !=0 && 
                bv.getJumlah().getText().length()>0 && 
                bv.getHarga().getText().length()>0) {
            
            return true;
            
        }else{
            JOptionPane.showMessageDialog(bv, "Input tidak boleh kosong","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public void simpan(){

            if(validasiForm()){
                barang b = new barang();
                b.setKode(bv.getKode().getText());
                b.setNama(bv.getNama().getText());
                b.setSatuan(bv.getCmbSatuan().getSelectedItem().toString());
                b.setJumlah(Integer.parseInt(bv.getJumlah().getText()));
                b.setHarga(Integer.parseInt(bv.getHarga().getText()));
                ks.simpan(b);
                JOptionPane.showMessageDialog(bv, "Data Berhasil di Simpan!","Information",JOptionPane.INFORMATION_MESSAGE);
                
            }
            
        }
    
     public void update(){

            if(validasiForm()){
                barang b = new barang();
                b.setNama(bv.getNama().getText());
                b.setSatuan(bv.getCmbSatuan().getSelectedItem().toString());
                b.setJumlah(Integer.parseInt(bv.getJumlah().getText()));
                b.setHarga(Integer.parseInt(bv.getHarga().getText()));
                b.setKode(bv.getKode().getText());
                ks.update(b);
                JOptionPane.showMessageDialog(bv, "Data Berhasil di Ubah!","Information",JOptionPane.INFORMATION_MESSAGE);
                
            }
            
        }
     public void delete(){
         barang b = new barang();
         b.setKode(bv.getKode().getText());
         ks.delete(b);
         JOptionPane.showMessageDialog(bv, "Data Berhasil di Hapus!","Information",JOptionPane.INFORMATION_MESSAGE);
                
     }
     
     private class BarangTableModel extends AbstractTableModel {
         
         private List<barang> listBarangs;
         private String[] columnNames= {"kode", "nama","satuan","jumlah","harga"};
         
         public BarangTableModel(List<barang> listBarangs){
             this.listBarangs =listBarangs;
         }

        @Override
        public int getRowCount() {
             return listBarangs.size();
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
            barang p = listBarangs.get(rowIndex);
            switch(columnIndex) {
                case 0:
                    return p.getKode();
                case 1:
                    return p.getNama();
                case 2:
                    return p.getSatuan();
                case 3:
                    return p.getJumlah();
                case 4:
                    return p.getHarga();
                default:
                    return "";
            } 
        }
         
     }

}