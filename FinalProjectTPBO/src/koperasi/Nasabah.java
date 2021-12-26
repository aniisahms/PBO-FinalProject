/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author suharso
 */
public abstract class Nasabah {
    protected IntegerProperty nasabahID;
    protected StringProperty nama;
    protected StringProperty alamat;
    protected IntegerProperty numRek;
    protected ArrayList <Rekening> rekening;

    public Nasabah(Integer nasabahID, String nama, String alamat, ArrayList<Rekening> rekening) {
        this.nasabahID = new SimpleIntegerProperty(nasabahID);
        this.nama = new SimpleStringProperty(nama);
        this.alamat = new SimpleStringProperty(alamat);
        this.rekening = rekening;
        this.numRek = new SimpleIntegerProperty(this.rekening.size());
    }
    
    public Nasabah(Integer nasabahID, String nama, String alamat, Rekening rekening) {
        this.nasabahID = new SimpleIntegerProperty(nasabahID);
        this.rekening = new ArrayList<>();
        this.nama = new SimpleStringProperty(nama);
        this.alamat = new SimpleStringProperty(alamat);
        this.rekening.add(rekening);
        this.numRek = new SimpleIntegerProperty(this.rekening.size());
    }
    
    public Integer getNasabahID() {
        return nasabahID.get();
    }
    
    public void setNasabahID(Integer nasabahID) {
        this.nasabahID.set(nasabahID);
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public String getAlamat() {
        return alamat.get();
    }

    public void setAlamat(String alamat) {
        this.alamat.set(alamat);
    }

    public Integer getNumRek() {
        return numRek.get();
    }

    public ArrayList <Rekening> getRekening() {
        return rekening;
    }

    public void setRekening(ArrayList <Rekening> rekening) {
        this.rekening = rekening;
    }
    
    public IntegerProperty nasabahID(){
        return nasabahID;
    }
    
    public StringProperty namaProperty() {
        return nama;
    }
    
    public StringProperty alamatProperty() {
        return alamat;
    }
    
    public IntegerProperty numRekProperty() {
        return numRek;
    }
}
