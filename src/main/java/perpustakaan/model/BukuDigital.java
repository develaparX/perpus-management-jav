package perpustakaan.model;

/**
 * Class BukuDigital yang mewarisi class Buku
 * Menambahkan properti khusus untuk buku digital: ukuranFile dan formatFile
 */
public class BukuDigital extends Buku {
    // Additional properties untuk buku digital
    private double ukuranFile; // dalam MB
    private String formatFile; // PDF atau EPUB
    
    // Default constructor
    public BukuDigital() {
        super(); // Memanggil constructor parent class
        this.ukuranFile = 0.0;
        this.formatFile = "";
    }
    
    // Constructor dengan parameter dasar
    public BukuDigital(String judul, String pengarang, int tahunTerbit, 
                      double ukuranFile, String formatFile) {
        super(judul, pengarang, tahunTerbit); // Memanggil super constructor
        this.ukuranFile = ukuranFile;
        this.formatFile = formatFile;
    }
    
    // Constructor dengan semua parameter termasuk status pinjam
    public BukuDigital(String judul, String pengarang, int tahunTerbit, 
                      boolean statusPinjam, double ukuranFile, String formatFile) {
        super(judul, pengarang, tahunTerbit, statusPinjam); // Memanggil super constructor
        this.ukuranFile = ukuranFile;
        this.formatFile = formatFile;
    }
    
    // Getter methods untuk additional properties
    public double getUkuranFile() {
        return ukuranFile;
    }
    
    public String getFormatFile() {
        return formatFile;
    }
    
    // Setter methods untuk additional properties
    public void setUkuranFile(double ukuranFile) {
        this.ukuranFile = ukuranFile;
    }
    
    public void setFormatFile(String formatFile) {
        this.formatFile = formatFile;
    }
    
    // Override toString() method untuk menampilkan info digital book
    @Override
    public String toString() {
        return String.format("Judul: %s, Pengarang: %s, Tahun: %d, Status: %s, Format: %s (%.1f MB)",
                getJudul(), getPengarang(), getTahunTerbit(),
                isStatusPinjam() ? "Dipinjam" : "Tersedia",
                formatFile, ukuranFile);
    }
    
    // Override equals() method untuk include additional properties
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        
        BukuDigital that = (BukuDigital) obj;
        return Double.compare(that.ukuranFile, ukuranFile) == 0 &&
               formatFile.equals(that.formatFile);
    }
    
    // Override hashCode() method untuk include additional properties
    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp = Double.doubleToLongBits(ukuranFile);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + formatFile.hashCode();
        return result;
    }
}