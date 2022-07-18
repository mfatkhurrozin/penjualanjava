/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;
import config.Koneksi;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.barang;
import dao.BarangDao;
import dao.PelangganDao;
import dao.PenjualanDao;
import dao.PenjualanDetailDao;
import dao.UserDao;
import model.Pelanggan;
import model.Penjualan;
import model.PenjualanDetail;
import model.User;
import service.KasirService;

/**
 *
 * @author ACER
 */
public class KasirServiceImpl implements KasirService{

    private BarangDao bDAO;
    private PelangganDao pDAO;
    private Connection kon;
    private PenjualanDao penjualanDao;
    private PenjualanDetailDao penjualanDetailDao;
    private UserDao userDao;
    
    public KasirServiceImpl() {
        try {
            kon = Koneksi.getKoneksi();
            bDAO = new BarangDao();
            bDAO.setConnection(kon);
            
             pDAO = new PelangganDao();
             pDAO.setConnection(kon);
            
            penjualanDao = new PenjualanDao();
            penjualanDao.setConnection(kon);
            
            penjualanDetailDao = new PenjualanDetailDao();
            penjualanDetailDao.setConnection(kon);
            
            userDao = new UserDao();
            userDao.setConnection(kon);
            
        } catch (Exception ex) {
            Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    @Override
    public barang simpan(barang b) {
        try {
            kon.setAutoCommit(false);
            bDAO.insert(b);
            kon.commit();
            kon.setAutoCommit(true);
        } catch (Exception e) {
            try {
            kon.rollback();
            } catch (SQLException ex1) {

            Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE,
            null, ex1);
            }
        }
        return b;
    }

    @Override
    public barang update(barang b) {
        try {
        kon.setAutoCommit(false);
        bDAO.update(b);
        kon.commit();
        kon.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
            kon.rollback();
            } catch (SQLException ex1) {
            Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE,
            null, ex1);
            }
        }
        return b;
    }

    @Override
    public barang delete(barang b) {
        try {
            kon.setAutoCommit(false);
            bDAO.delete(b);
            kon.commit();
            kon.setAutoCommit(true);
            } catch (SQLException ex) {
                try {
                kon.rollback();
                } catch (SQLException ex1) {
                Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE,
                null, ex1);
                }
            }
            return b;
    }

    @Override
    public List<barang> getAll() {
        try {
            return bDAO.getAll();
        } catch (Exception ex) {
            Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<barang>();
    }

    @Override
    public List<barang> getCariNama(String nama) {
       try {
            return bDAO.getByNama(nama);
        } catch (Exception ex) {
            Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Penjualan simpanPenjualan(Penjualan p) {
        try {
            kon.setAutoCommit(false);
            penjualanDao.insert(p);
            kon.commit();
            kon.setAutoCommit(true);
        } catch (Exception e) {
            try {
            kon.rollback();
            } catch (SQLException ex1) {

            Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE,
            null, ex1);
            }
        }
        return p;
    }

    @Override
    public PenjualanDetail simpanPenjualanDetail(PenjualanDetail pd) {
       try {
            kon.setAutoCommit(false);
            penjualanDetailDao.insert(pd);
            kon.commit();
            kon.setAutoCommit(true);
        } catch (Exception e) {
            try {
            kon.rollback();
            } catch (SQLException ex1) {

            Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE,
            null, ex1);
            }
        }
        return pd;
    }

    @Override
    public Pelanggan simpanp(Pelanggan p) {
       try {
            kon.setAutoCommit(false);
            pDAO.insert(p);
            kon.commit();
            kon.setAutoCommit(true);
        } catch (Exception e) {
            try {
            kon.rollback();
            } catch (SQLException ex1) {

            Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE,
            null, ex1);
            }
        }
        return p;
    }

    @Override
    public Pelanggan updatep(Pelanggan p) {
        try {
        kon.setAutoCommit(false);
        pDAO.update(p);
        kon.commit();
        kon.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
            kon.rollback();
            } catch (SQLException ex1) {
            Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE,
            null, ex1);
            }
        }
        return p;
    }

    @Override
    public Pelanggan deletep(Pelanggan p) {
        try {
            kon.setAutoCommit(false);
            pDAO.delete(p);
            kon.commit();
            kon.setAutoCommit(true);
            } catch (SQLException ex) {
                try {
                kon.rollback();
                } catch (SQLException ex1) {
                Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE,
                null, ex1);
                }
            }
            return p;
    }

    @Override
    public List<Pelanggan> getAllPelanggan() {
        try {
            return pDAO.getAllPelanggan();
        } catch (Exception ex) {
            Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<Pelanggan>();
    }

    @Override
    public List<Pelanggan> getCariNamaPelanggan(String nama) {
       try {
            return pDAO.getByNamaPelanggan(nama);
        } catch (Exception ex) {
            Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User getByuser(String username, String password) {
         try {
            return userDao.getByUser(username,password);
        } catch (Exception ex) {
            Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User simpanu(User u) {
        try {
            kon.setAutoCommit(false);
            userDao.insert(u);
            kon.commit();
            kon.setAutoCommit(true);
        } catch (Exception e) {
            try {
            kon.rollback();
            } catch (SQLException ex1) {

            Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE,
            null, ex1);
            }
        }
        return u;
    }

    @Override
    public User updateu(User u) {
        try {
        kon.setAutoCommit(false);
        userDao.update(u);
        kon.commit();
        kon.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
            kon.rollback();
            } catch (SQLException ex1) {
            Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE,
            null, ex1);
            }
        }
        return u;
    }

    @Override
    public User deleteu(User u) {
        try {
            kon.setAutoCommit(false);
            userDao.delete(u);
            kon.commit();
            kon.setAutoCommit(true);
            } catch (SQLException ex) {
                try {
                kon.rollback();
                } catch (SQLException ex1) {
                Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE,
                null, ex1);
                }
            }
            return u;
    }

    @Override
    public List<User> getAllUser() {
        try {
            return userDao.getAllUser();
        } catch (Exception ex) {
            Logger.getLogger(KasirServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<User>();
    }
    
}
