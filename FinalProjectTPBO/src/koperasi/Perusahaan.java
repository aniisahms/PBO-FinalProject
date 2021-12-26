/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author suharso
 */
public class Perusahaan extends Nasabah {
    private StringProperty nib;

    public Perusahaan(Integer nasabahID, String nama, String alamat, String nib, ArrayList<Rekening> rekening) {
        super(nasabahID, nama, alamat, rekening);
        this.nib = new SimpleStringProperty(nib);
    }

    public Perusahaan(Integer nasabahID, String nama, String alamat, String nib, Rekening rekening) {
        super(nasabahID, nama, alamat, rekening);
        this.nib = new SimpleStringProperty(nib);
    }

    public String getNib() {
        return nib.get();
    }

    public void setNib(String nib) {
        this.nib.set(nib);
    }
    
    public StringProperty nibProperty() {
        return nib;
    }
}
