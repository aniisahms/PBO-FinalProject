/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi;

import java.util.ArrayList;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

/**
 *
 * @author suharso
 */
public class Individu extends Nasabah {
    private LongProperty nik;
    private LongProperty npwp;

    public Individu(Integer nasabahID, String nama, Long nik, Long npwp, String alamat, ArrayList<Rekening> rekening) {
        super(nasabahID, nama, alamat, rekening);
        this.nik = new SimpleLongProperty(nik);
        this.npwp = new SimpleLongProperty(npwp);
    }

    public Individu(Integer nasabahID, String nama, Long nik, Long npwp, String alamat, Rekening rekening) {
        super(nasabahID, nama, alamat, rekening);
        this.nik = new SimpleLongProperty(nik);
        this.npwp = new SimpleLongProperty(npwp);
    }

    public Long getNik() {
        return nik.get();
    }

    public void setNik(Long nik) {
        this.nik.set(nik);
    }

    public Long getNpwp() {
        return npwp.get();
    }

    public void setNpwp(Long npwp) {
        this.npwp.set(npwp);
    }
    
    public LongProperty nikProperty() {
        return nik;
    }
    
    public LongProperty npwpProperty() {
        return npwp;
    }
}
