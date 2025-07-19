package perpustakaan.model;

/**
 * Custom exception untuk menangani kasus ketika buku yang diminta tidak tersedia
 * untuk dipinjam (sudah dipinjam atau tidak ditemukan).
 * 
 * Exception ini extends Exception sehingga merupakan checked exception
 * yang harus ditangani dengan try-catch atau dideklarasikan dalam method signature.
 */
public class BukuTidakTersediaException extends Exception {
    
    /**
     * Constructor yang menerima pesan error
     * 
     * @param message Pesan error yang menjelaskan mengapa buku tidak tersedia
     */
    public BukuTidakTersediaException(String message) {
        super(message);
    }
}