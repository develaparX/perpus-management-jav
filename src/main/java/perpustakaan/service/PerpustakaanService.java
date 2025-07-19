package perpustakaan.service;

import perpustakaan.model.Buku;
import perpustakaan.model.BukuTidakTersediaException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class untuk mengelola operasi perpustakaan
 * Mengimplementasikan interface Pinjaman dan menyediakan CRUD operations
 */
public class PerpustakaanService implements Pinjaman {
    
    private ArrayList<Buku> daftarBuku;
    
    /**
     * Constructor untuk inisialisasi service
     */
    public PerpustakaanService() {
        this.daftarBuku = new ArrayList<>();
    }
    
    /**
     * Menambah buku baru ke dalam koleksi
     * @param buku objek buku yang akan ditambahkan (bisa Buku atau BukuDigital)
     */
    public void tambahBuku(Buku buku) {
        if (buku != null) {
            daftarBuku.add(buku);
        }
    }
    
    /**
     * Menghapus buku berdasarkan judul
     * @param judul judul buku yang akan dihapus
     * @return true jika berhasil dihapus, false jika tidak ditemukan
     */
    public boolean hapusBuku(String judul) {
        if (judul == null || judul.trim().isEmpty()) {
            return false;
        }
        
        return daftarBuku.removeIf(buku -> 
            buku.getJudul().equalsIgnoreCase(judul.trim()));
    }
    
    /**
     * Update informasi buku berdasarkan judul
     * @param judul judul buku yang akan diupdate
     * @param bukuBaru objek buku dengan informasi baru
     * @return true jika berhasil diupdate, false jika tidak ditemukan
     */
    public boolean updateBuku(String judul, Buku bukuBaru) {
        if (judul == null || judul.trim().isEmpty() || bukuBaru == null) {
            return false;
        }
        
        for (int i = 0; i < daftarBuku.size(); i++) {
            if (daftarBuku.get(i).getJudul().equalsIgnoreCase(judul.trim())) {
                daftarBuku.set(i, bukuBaru);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Mencari buku berdasarkan judul
     * @param judul judul buku yang dicari
     * @return objek Buku jika ditemukan, null jika tidak ditemukan
     */
    public Buku cariBuku(String judul) {
        if (judul == null || judul.trim().isEmpty()) {
            return null;
        }
        
        for (Buku buku : daftarBuku) {
            if (buku.getJudul().equalsIgnoreCase(judul.trim())) {
                return buku;
            }
        }
        return null;
    }
    
    /**
     * Mendapatkan daftar semua buku
     * @return List berisi semua buku (menggunakan polymorphism untuk Buku dan BukuDigital)
     */
    public List<Buku> getDaftarBuku() {
        return new ArrayList<>(daftarBuku);
    }
    
    /**
     * Implementasi method pinjamBuku dari interface Pinjaman
     * @param judul judul buku yang akan dipinjam
     * @throws BukuTidakTersediaException jika buku tidak ditemukan atau sudah dipinjam
     */
    @Override
    public void pinjamBuku(String judul) throws BukuTidakTersediaException {
        if (judul == null || judul.trim().isEmpty()) {
            throw new BukuTidakTersediaException("Judul buku tidak boleh kosong");
        }
        
        Buku buku = cariBuku(judul);
        if (buku == null) {
            throw new BukuTidakTersediaException("Buku dengan judul '" + judul + "' tidak ditemukan");
        }
        
        if (buku.isStatusPinjam()) {
            throw new BukuTidakTersediaException("Buku '" + judul + "' sedang dipinjam");
        }
        
        buku.setStatusPinjam(true);
    }
    
    /**
     * Implementasi method kembalikanBuku dari interface Pinjaman
     * @param judul judul buku yang akan dikembalikan
     */
    @Override
    public void kembalikanBuku(String judul) {
        if (judul == null || judul.trim().isEmpty()) {
            return;
        }
        
        Buku buku = cariBuku(judul);
        if (buku != null && buku.isStatusPinjam()) {
            buku.setStatusPinjam(false);
        }
    }
    
    /**
     * Mendapatkan jumlah total buku dalam koleksi
     * @return jumlah buku
     */
    public int getJumlahBuku() {
        return daftarBuku.size();
    }
    
    /**
     * Mendapatkan daftar buku yang tersedia (belum dipinjam)
     * @return List buku yang tersedia
     */
    public List<Buku> getBukuTersedia() {
        List<Buku> bukuTersedia = new ArrayList<>();
        for (Buku buku : daftarBuku) {
            if (!buku.isStatusPinjam()) {
                bukuTersedia.add(buku);
            }
        }
        return bukuTersedia;
    }
    
    /**
     * Mendapatkan daftar buku yang sedang dipinjam
     * @return List buku yang sedang dipinjam
     */
    public List<Buku> getBukuDipinjam() {
        List<Buku> bukuDipinjam = new ArrayList<>();
        for (Buku buku : daftarBuku) {
            if (buku.isStatusPinjam()) {
                bukuDipinjam.add(buku);
            }
        }
        return bukuDipinjam;
    }
}