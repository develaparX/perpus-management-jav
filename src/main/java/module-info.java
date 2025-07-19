module perpustakaan {
    requires javafx.controls;
    requires javafx.fxml;
    
    exports perpustakaan;
    exports perpustakaan.model;
    exports perpustakaan.service;
    exports perpustakaan.gui;
    
    opens perpustakaan.gui to javafx.fxml;
}