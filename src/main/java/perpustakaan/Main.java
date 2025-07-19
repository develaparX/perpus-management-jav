package perpustakaan;

import perpustakaan.gui.MainApplication;

/**
 * Main entry point untuk Sistem Manajemen Perpustakaan Digital
 * Class ini berfungsi sebagai launcher untuk JavaFX application
 */
public class Main {
    
    /**
     * Main method yang memanggil MainApplication.main()
     * Includes proper exception handling untuk application startup
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Starting Sistem Manajemen Perpustakaan Digital...");
            
            // Call MainApplication.main() to launch JavaFX application
            MainApplication.main(args);
            
        } catch (Exception e) {
            System.err.println("Error starting application: " + e.getMessage());
            e.printStackTrace();
            
            // Exit with error code
            System.exit(1);
        }
    }
}