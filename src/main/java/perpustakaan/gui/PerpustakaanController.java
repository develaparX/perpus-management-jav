package perpustakaan.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import perpustakaan.model.Buku;
import perpustakaan.model.BukuDigital;
import perpustakaan.service.PerpustakaanService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class untuk handling GUI events dalam aplikasi perpustakaan
 */
public class PerpustakaanController implements Initializable {

    // FXML annotated UI components
    @FXML
    private TextField txtJudul;
    
    @FXML
    private TextField txtPengarang;
    
    @FXML
    private TextField txtTahun;
    
    @FXML
    private ComboBox<String> cmbFormat;
    
    @FXML
    private TextField txtUkuranFile;
    
    @FXML
    private ListView<Buku> listViewBuku;
    
    @FXML
    private Button btnTambah;
    
    @FXML
    private Button btnPinjam;
    
    @FXML
    private Button btnKembali;
    
    // Service dependency
    private PerpustakaanService perpustakaanService;
    
    // Observable list untuk ListView
    private ObservableList<Buku> bukuObservableList;

    /**
     * Initialize method yang dipanggil setelah FXML dimuat
     * Setup initial data dan event listeners
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize service
        perpustakaanService = new PerpustakaanService();
        
        // Initialize observable list
        bukuObservableList = FXCollections.observableArrayList();
        listViewBuku.setItems(bukuObservableList);
        
        // Setup ComboBox items dan default value
        cmbFormat.getItems().addAll("Fisik", "PDF", "EPUB");
        cmbFormat.setValue("Fisik");
        
        // Setup event listener untuk ComboBox format
        cmbFormat.setOnAction(event -> {
            String selectedFormat = cmbFormat.getValue();
            boolean isDigital = "PDF".equals(selectedFormat) || "EPUB".equals(selectedFormat);
            txtUkuranFile.setDisable(!isDigital);
            
            if (!isDigital) {
                txtUkuranFile.clear();
            }
        });
        
        // Setup initial sample data
        setupSampleData();
        
        // Refresh display
        refreshListView();
    }
    
    /**
     * Setup sample data untuk testing
     */
    private void setupSampleData() {
        // Add sample regular books
        perpustakaanService.tambahBuku(new Buku("Java Programming", "John Doe", 2023));
        perpustakaanService.tambahBuku(new Buku("Data Structures", "Jane Smith", 2022));
        
        // Add sample digital books
        perpustakaanService.tambahBuku(new BukuDigital("Digital Library Management", "Alice Johnson", 2024, 15.5, "PDF"));
        perpustakaanService.tambahBuku(new BukuDigital("Modern Software Development", "Bob Wilson", 2023, 22.3, "EPUB"));
    }
    
    /**
     * Event handler untuk tombol Tambah Buku
     * Get input values, validate, create appropriate book object, and add to service
     */
    @FXML
    private void handleTambahBuku() {
        try {
            // Get input values dari form fields
            String judul = txtJudul.getText().trim();
            String pengarang = txtPengarang.getText().trim();
            String tahunText = txtTahun.getText().trim();
            String format = cmbFormat.getValue();
            
            // Validate required fields
            if (judul.isEmpty() || pengarang.isEmpty() || tahunText.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Semua field harus diisi!");
                return;
            }
            
            // Parse tahun dengan exception handling
            int tahun;
            try {
                tahun = Integer.parseInt(tahunText);
                if (tahun < 1000 || tahun > 2030) {
                    showAlert(Alert.AlertType.ERROR, "Input Error", "Tahun harus antara 1000-2030!");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Tahun harus berupa angka yang valid!");
                return;
            }
            
            // Create appropriate Buku atau BukuDigital object based on format selection
            Buku bukuBaru;
            if ("PDF".equals(format) || "EPUB".equals(format)) {
                // Untuk buku digital, perlu ukuran file
                String ukuranFileText = txtUkuranFile.getText().trim();
                if (ukuranFileText.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Input Error", "Ukuran file harus diisi untuk buku digital!");
                    return;
                }
                
                try {
                    double ukuranFile = Double.parseDouble(ukuranFileText);
                    if (ukuranFile <= 0) {
                        showAlert(Alert.AlertType.ERROR, "Input Error", "Ukuran file harus lebih dari 0!");
                        return;
                    }
                    bukuBaru = new BukuDigital(judul, pengarang, tahun, ukuranFile, format);
                } catch (NumberFormatException e) {
                    showAlert(Alert.AlertType.ERROR, "Input Error", "Ukuran file harus berupa angka yang valid!");
                    return;
                }
            } else {
                // Untuk buku fisik
                bukuBaru = new Buku(judul, pengarang, tahun);
            }
            
            // Call service.tambahBuku() dan refresh display
            perpustakaanService.tambahBuku(bukuBaru);
            refreshListView();
            
            // Clear form fields
            clearForm();
            
            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Buku berhasil ditambahkan!");
            
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + e.getMessage());
        }
    }
    
    /**
     * Event handler untuk tombol Pinjam
     * Get selected book, validate, and call service to borrow book
     */
    @FXML
    private void handlePinjamBuku() {
        try {
            // Get selected book dari ListView
            Buku selectedBuku = listViewBuku.getSelectionModel().getSelectedItem();
            
            if (selectedBuku == null) {
                showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih buku yang ingin dipinjam terlebih dahulu!");
                return;
            }
            
            // Implement try-catch untuk BukuTidakTersediaException
            try {
                // Call service.pinjamBuku() method
                perpustakaanService.pinjamBuku(selectedBuku.getJudul());
                
                // Update display dan show appropriate messages
                refreshListView();
                showAlert(Alert.AlertType.INFORMATION, "Sukses", 
                    "Buku '" + selectedBuku.getJudul() + "' berhasil dipinjam!");
                
            } catch (perpustakaan.model.BukuTidakTersediaException e) {
                showAlert(Alert.AlertType.ERROR, "Buku Tidak Tersedia", e.getMessage());
            }
            
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + e.getMessage());
        }
    }
    
    /**
     * Event handler untuk tombol Kembalikan
     * Get selected book and call service to return book
     */
    @FXML
    private void handleKembalikanBuku() {
        try {
            // Get selected book dari ListView
            Buku selectedBuku = listViewBuku.getSelectionModel().getSelectedItem();
            
            if (selectedBuku == null) {
                showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih buku yang ingin dikembalikan terlebih dahulu!");
                return;
            }
            
            // Check if book is actually borrowed
            if (!selectedBuku.isStatusPinjam()) {
                showAlert(Alert.AlertType.WARNING, "Peringatan", 
                    "Buku '" + selectedBuku.getJudul() + "' tidak sedang dipinjam!");
                return;
            }
            
            // Call service.kembalikanBuku() method
            perpustakaanService.kembalikanBuku(selectedBuku.getJudul());
            
            // Update display dan show success message
            refreshListView();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", 
                "Buku '" + selectedBuku.getJudul() + "' berhasil dikembalikan!");
                
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + e.getMessage());
        }
    }
    
    /**
     * Refresh ListView dengan data terbaru dari service
     * Clear dan repopulate ListView dengan current book data
     * Use polymorphism untuk displaying both Buku dan BukuDigital objects
     * Format display strings untuk showing book information via toString() methods
     */
    private void refreshListView() {
        // Clear existing data
        bukuObservableList.clear();
        
        // Repopulate dengan current book data dari service
        // Menggunakan polymorphism: ArrayList<Buku> dapat menyimpan objek Buku dan BukuDigital
        // ListView akan memanggil toString() method dari masing-masing objek untuk display
        bukuObservableList.addAll(perpustakaanService.getDaftarBuku());
        
        // Update selection jika ada item
        if (!bukuObservableList.isEmpty()) {
            listViewBuku.getSelectionModel().selectFirst();
        }
    }
    
    /**
     * Helper method untuk menampilkan Alert dialog
     * @param alertType Type of alert (ERROR, INFORMATION, etc.)
     * @param title Title of the alert
     * @param message Message content
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Helper method untuk clear semua form fields
     */
    private void clearForm() {
        txtJudul.clear();
        txtPengarang.clear();
        txtTahun.clear();
        txtUkuranFile.clear();
        cmbFormat.setValue("Fisik");
    }
    
    /**
     * Get reference to PerpustakaanService (untuk testing atau akses eksternal)
     * @return PerpustakaanService instance
     */
    public PerpustakaanService getPerpustakaanService() {
        return perpustakaanService;
    }
}