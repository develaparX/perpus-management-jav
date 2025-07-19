module perpustakaan {
    requires javafx.controls;
    requires javafx.fxml;
    
    exports perpustakaan.model;
    exports perpustakaan.service;
    exports perpustakaan.gui;
    
    // Opens GUI package to JavaFX FXML for reflection access
    opens perpustakaan.gui to javafx.fxml;
}