package perpustakaan.service;

import perpustakaan.model.BukuTidakTersediaException;

/**
 * Interface untuk operasi peminjaman buku dalam sistem perpustakaan
 */
public interface Pinjaman {
    
    /**
     * Meminjam buku berdasarkan judul
     * @param judul judul buku yang akan dipinjam
     * @throws BukuTidakTersediaException jika buku tidak tersedia atau sudah dipinjam
     */
    void pinjamBuku(String judul) throws BukuTidakTersediaException;
    
    /**
     * Mengembalikan buku berdasarkan judul
     * @param judul judul buku yang akan dikembalikan
     */
    void kembalikanBuku(String judul);
}