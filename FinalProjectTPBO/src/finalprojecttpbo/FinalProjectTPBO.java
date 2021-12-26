/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojecttpbo;

import db.DBHelper;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import koperasi.Individu;
import koperasi.NasabahDataModel;
import koperasi.Rekening;

/**
 *
 * @author suharso
 */
public class FinalProjectTPBO extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FormSistemKoperasi.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            if (null != DBHelper.getConnection("SQLITE")){
                System.out.println("Koneksi sukses");
            }
            else {
                System.out.println("Koneksi gagal");
            }
             
            NasabahDataModel ndm = new NasabahDataModel("SQLITE");
            Individu in = new Individu(1, "Ucup", 2017051012l, 2017051013l, "Jatimulyo", new Rekening(11, 10000.));
            ndm.addNasabah(in);
            System.out.println("Sukses ditambahkan");

            //launch(args);
        } catch (SQLException ex) {
            System.out.println("Gagal ditambahkan");
            Logger.getLogger(FinalProjectTPBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
