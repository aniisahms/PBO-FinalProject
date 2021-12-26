/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi;

import db.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author suharso
 */
public class NasabahDataModel {
    private final Connection conn;

    public NasabahDataModel(String driver) throws SQLException {
        this.conn = DBHelper.getConnection(driver);
    }
    
    public void addNasabah(Individu nasabah) throws SQLException {
        String insertNasabah = "INSERT INTO tabel_nasabah (nasabahID, nama, alamat)"
                + "VALUES(?,?,?)";
        
        String insertIndividu = "INSERT INTO tabel_individu (nasabahID, nik, npwp)"
                + "VALUES(?,?,?)";
        
        String insertRekening = "INSERT INTO tabel_rekening (noRekening, saldo, nasabahID)"
                + "VALUES(?,?,?)";
        
        //insertNasabah
        PreparedStatement stmtNasabah = conn.prepareStatement(insertNasabah);
        stmtNasabah.setInt(1, nasabah.getNasabahID());
        stmtNasabah.setString(2, nasabah.getNama());
        stmtNasabah.setString(3, nasabah.getAlamat());
        stmtNasabah.execute();
        
        //insertIndividu
        PreparedStatement stmtIndividu = conn.prepareStatement(insertIndividu);
        stmtIndividu.setInt(1, nasabah.getNasabahID());
        stmtIndividu.setLong(2, nasabah.getNik());
        stmtIndividu.setLong(3, nasabah.getNpwp());
        stmtIndividu.execute();
        
        //insertRekening
        PreparedStatement stmtRekening = conn.prepareStatement(insertRekening);
        stmtRekening.setInt(1, nasabah.getRekening().get(0).getNoRekening());
        stmtRekening.setDouble(2, nasabah.getRekening().get(0).getSaldo());
        stmtRekening.setLong(3, nasabah.getNasabahID());
        stmtRekening.execute();
    }
    
    public void addNasabah(Perusahaan nasabah) throws SQLException {
        String insertNasabah = "INSERT INTO tabel_nasabah (nasabahID, nama, alamat)"
                + "VALUES(?,?,?)";
        
        String insertPerusahaan = "INSERT INTO tabel_individu (nasabahID, nib)"
                + "VALUES(?,?)";
        
        String insertRekening = "INSERT INTO tabel_rekening (noRekening, saldo, nasabahID)"
                + "VALUES(?,?,?)";
        
        //insertNasabah
        PreparedStatement stmtNasabah = conn.prepareStatement(insertNasabah);
        stmtNasabah.setInt(1, nasabah.getNasabahID());
        stmtNasabah.setString(2, nasabah.getNama());
        stmtNasabah.setString(3, nasabah.getAlamat());
        stmtNasabah.execute();
        
        //insertIndividu
        PreparedStatement stmtIndividu = conn.prepareStatement(insertPerusahaan);
        stmtIndividu.setInt(1, nasabah.getNasabahID());
        stmtIndividu.setString(2, nasabah.getNib());
        stmtIndividu.execute();
        
        //insertRekening
        PreparedStatement stmtRekening = conn.prepareStatement(insertRekening);
        stmtRekening.setInt(1, nasabah.getRekening().get(0).getNoRekening());
        stmtRekening.setDouble(2, nasabah.getRekening().get(0).getSaldo());
        stmtRekening.setLong(3, nasabah.getNasabahID());
        stmtRekening.execute();
    }
    
    
    public ObservableList<Individu> getIndividu() {
        ObservableList<Individu> data = FXCollections.observableArrayList();
        
        String sql = "SELECT nasabahID, nama, nik, npwp, alamat "
                + "FROM tabel_nasabah NATURAL JOIN tabel_individu";
        
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            
            while(rs.next()) {
                String sqlAccount = "SELECT noRekening, saldo"
                    + "FROM tabel_rekening WHERE nasabahID = " + rs.getInt(1);
                
                ResultSet rsAccount = conn.createStatement().executeQuery(sqlAccount);
                
                ArrayList<Rekening> dataRekening = new ArrayList<>();
                
                while(rsAccount.next()) {
                    dataRekening.add(new Rekening(rsAccount.getInt(1), rsAccount.getDouble(2)));
                }
                data.add(new Individu(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getLong(4), rs.getString(5), dataRekening));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    
    
    public ObservableList<Perusahaan> getPerusahaan() {
        ObservableList<Perusahaan> data = FXCollections.observableArrayList();
        
        String sql = "SELECT nasabahID, nama, alamat, nib "
                + "FROM tabel_nasabah NATURAL JOIN tabel_perusahaan";
        
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            
            while(rs.next()) {
                String sqlAccount = "SELECT noRekening, saldo"
                    + "FROM tabel_rekening WHERE nasabahID = " + rs.getInt(1);
                
                ResultSet rsAccount = conn.createStatement().executeQuery(sqlAccount);
                
                ArrayList<Rekening> dataRekening = new ArrayList<>();
                
                while(rsAccount.next()) {
                    dataRekening.add(new Rekening(rsAccount.getInt(1), rsAccount.getDouble(2)));
                }
                data.add(new Perusahaan(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), dataRekening));
            }  
        }
        catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    
    
    public ObservableList<Rekening> getRekening(int nasabahID) {
        ObservableList<Rekening> data = FXCollections.observableArrayList();
        
        String sql = "SELECT noRekening, saldo"
                + "FROM tabel_rekening WHERE nasabahID = " + nasabahID;
        
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            
            while (rs.next()){
                data.add(new Rekening(rs.getInt(1), rs.getDouble(2)));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    
    
    public int nextNasabahID() throws SQLException {
        String sql = "SELECT MAX (nasabahID) FROM tabel_nasabah";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        
        while(rs.next()) {
            return rs.getInt(1)==0 ? 1000001 : rs.getInt(1)+1;
        }
        return 1000001;
    }
    
    public int nextNoRekening(int nasabahID) throws SQLException {
        String sql = "SELECT MAX (noRekening) "
                + "FROM tabel_rekening WHERE nasabahID = " + nasabahID;
        ResultSet rs = conn.createStatement().executeQuery(sql);
        
        while(rs.next()) {
            return rs.getInt(1)+1;
        }
        return 0;
    }
}
