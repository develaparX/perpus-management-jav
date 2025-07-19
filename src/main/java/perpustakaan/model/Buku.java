package perpustakaan.model;

import java.util.Objects;

/**
 * Class Buku untuk merepresentasikan buku dalam sistem perpustakaan
 * Mengimplementasikan konsep enkapsulasi dengan private fields dan public methods
 */
public class Buku {
    // Private fields untuk enkapsulasi
    private String judul;
    private String pengarang;
    private int tahunTerbit;
    private boolean statusPinjam;
    
    // Default constructor
    public Buku() {
        this.judul = "";
        this.pengarang = "";
        this.tahunTerbit = 0;
        this.statusPinjam = false;
    }
    
    // Overloaded constructor
    public Buku(String judul, String pengarang, int tahunTerbit) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.statusPinjam = false; // Default tidak dipinjam
    }
    
    // Overloaded constructor dengan semua parameter
    public Buku(String judul, String pengarang, int tahunTerbit, boolean statusPinjam) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.statusPinjam = statusPinjam;
    }
    
    // Getter methods
    public String getJudul() {
        return judul;
    }
    
    public String getPengarang() {
        return pengarang;
    }
    
    public int getTahunTerbit() {
        return tahunTerbit;
    }
    
    public boolean isStatusPinjam() {
        return statusPinjam;
    }
    
    // Setter methods
    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }
    
    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }
    
    public void setStatusPinjam(boolean statusPinjam) {
        this.statusPinjam = statusPinjam;
    }
    
    // Override toString() method
    @Override
    public String toString() {
        return String.format("Judul: %s, Pengarang: %s, Tahun: %d, Status: %s",
                judul, pengarang, tahunTerbit, 
                statusPinjam ? "Dipinjam" : "Tersedia");
    }
    
    // Override equals() method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Buku buku = (Buku) obj;
        return tahunTerbit == buku.tahunTerbit &&
               statusPinjam == buku.statusPinjam &&
               Objects.equals(judul, buku.judul) &&
               Objects.equals(pengarang, buku.pengarang);
    }
    
    // Override hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(judul, pengarang, tahunTerbit, statusPinjam);
    }
}