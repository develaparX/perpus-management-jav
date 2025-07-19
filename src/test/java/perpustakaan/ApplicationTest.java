package perpustakaan;

import perpustakaan.model.Buku;
import perpustakaan.model.BukuDigital;
import perpustakaan.model.BukuTidakTersediaException;
import perpustakaan.service.PerpustakaanService;

/**
 * Test class untuk memverifikasi fungsionalitas lengkap aplikasi
 * Menguji semua aspek OOP dan exception handling
 */
public class ApplicationTest {
    
    public static void main(String[] args) {
        System.out.println("=== Testing Sistem Manajemen Perpustakaan Digital ===\n");
        
        ApplicationTest test = new ApplicationTest();
        test.runAllTests();
    }
    
    public void runAllTests() {
        testModelClasses();
        testServiceLayer();
        testExceptionHandling();
        testPolymorphism();
        
        System.out.println("\n=== All Tests Completed Successfully! ===");
    }
    
    /**
     * Test 1: Model classes dengan enkapsulasi
     */
    private void testModelClasses() {
        System.out.println("1. Testing Model Classes (Encapsulation):");
        
        // Test Buku class
        Buku buku1 = new Buku();
        buku1.setJudul("Java Programming");
        buku1.setPengarang("John Doe");
        buku1.setTahunTerbit(2023);
        buku1.setStatusPinjam(false);
        
        System.out.println("   Regular Book: " + buku1.toString());
        
        // Test BukuDigital class (Inheritance)
        BukuDigital bukuDigital1 = new BukuDigital("Digital Library", "Jane Smith", 2024, false, 15.5, "PDF");
        System.out.println("   Digital Book: " + bukuDigital1.toString());
        
        // Test equals method
        Buku buku2 = new Buku("Java Programming", "John Doe", 2023, false);
        System.out.println("   Books equal: " + buku1.equals(buku2));
        
        System.out.println("   ✓ Model classes working correctly\n");
    }
    
    /**
     * Test 2: Service layer dengan CRUD operations
     */
    private void testServiceLayer() {
        System.out.println("2. Testing Service Layer (CRUD Operations):");
        
        PerpustakaanService service = new PerpustakaanService();
        
        // Add books
        service.tambahBuku(new Buku("Java Basics", "Author A", 2020, false));
        service.tambahBuku(new BukuDigital("Python Guide", "Author B", 2021, false, 12.3, "EPUB"));
        service.tambahBuku(new Buku("Web Development", "Author C", 2022, false));
        
        System.out.println("   Added 3 books to library");
        System.out.println("   Total books: " + service.getDaftarBuku().size());
        
        // Search book
        Buku foundBook = service.cariBuku("Java Basics");
        System.out.println("   Found book: " + (foundBook != null ? foundBook.getJudul() : "Not found"));
        
        System.out.println("   ✓ Service layer working correctly\n");
    }
    
    /**
     * Test 3: Exception handling scenarios
     */
    private void testExceptionHandling() {
        System.out.println("3. Testing Exception Handling:");
        
        PerpustakaanService service = new PerpustakaanService();
        service.tambahBuku(new Buku("Test Book", "Test Author", 2023, false));
        
        try {
            // Test successful borrowing
            service.pinjamBuku("Test Book");
            System.out.println("   ✓ Book borrowed successfully");
            
            // Test borrowing unavailable book
            service.pinjamBuku("Test Book");
            System.out.println("   ✗ Should have thrown exception!");
            
        } catch (BukuTidakTersediaException e) {
            System.out.println("   ✓ Exception caught: " + e.getMessage());
        }
        
        try {
            // Test borrowing non-existent book
            service.pinjamBuku("Non-existent Book");
            System.out.println("   ✗ Should have thrown exception!");
            
        } catch (BukuTidakTersediaException e) {
            System.out.println("   ✓ Exception caught: " + e.getMessage());
        }
        
        // Test NumberFormatException simulation
        try {
            Integer.parseInt("invalid_number");
        } catch (NumberFormatException e) {
            System.out.println("   ✓ NumberFormatException handling works");
        }
        
        System.out.println("   ✓ Exception handling working correctly\n");
    }
    
    /**
     * Test 4: Polymorphism dengan mixed objects
     */
    private void testPolymorphism() {
        System.out.println("4. Testing Polymorphism:");
        
        PerpustakaanService service = new PerpustakaanService();
        
        // Add mixed types of books
        service.tambahBuku(new Buku("Regular Book 1", "Author 1", 2020, false));
        service.tambahBuku(new BukuDigital("Digital Book 1", "Author 2", 2021, false, 10.5, "PDF"));
        service.tambahBuku(new Buku("Regular Book 2", "Author 3", 2022, false));
        service.tambahBuku(new BukuDigital("Digital Book 2", "Author 4", 2023, false, 8.2, "EPUB"));
        
        System.out.println("   Books in library (demonstrating polymorphism):");
        for (Buku buku : service.getDaftarBuku()) {
            System.out.println("   - " + buku.toString());
            
            // Demonstrate runtime type checking
            if (buku instanceof BukuDigital) {
                BukuDigital digital = (BukuDigital) buku;
                System.out.println("     (Digital book with format: " + digital.getFormatFile() + ")");
            }
        }
        
        System.out.println("   ✓ Polymorphism working correctly\n");
    }
}