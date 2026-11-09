package kelas.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kelas.Navigator;
import kelas.data.DataProduk;
import kelas.model.Produk;

public class FormController {

    @FXML
    private TextField namaField;
    @FXML
    private ComboBox<String> kategoriBox;
    @FXML
    private TextField hargaField;
    @FXML
    private Label pesanLabel;

    private Navigator navigator;
    private DataProduk data;

    @FXML
    private void initialize() {
        kategoriBox.getItems().addAll("Makanan", "Minuman");
        kategoriBox.setValue("Makanan");
    }

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;
    }

    @FXML
    private void simpan() {
        try {
            String nama = namaField.getText();
            String kategori = kategoriBox.getValue();
            double harga = Double.parseDouble(hargaField.getText().trim());

            Produk p = data.tambah(nama, kategori, harga);
            navigator.bukaDetail(p);
        } catch (NumberFormatException e) {
            pesanLabel.setText("Harga harus berupa angka yang valid.");
        } catch (IllegalArgumentException e) {
            pesanLabel.setText(e.getMessage());
        } catch (IOException e) {
            pesanLabel.setText("Gagal membuka halaman detail.");
        }
    }

    @FXML
    private void bukaDaftar() {
        try {
            navigator.bukaDaftar();
        } catch (IOException e) {
            pesanLabel.setText("Gagal membuka daftar.");
        }
    }
}
