# Sistem Manajemen Perpustakaan Digital

Aplikasi desktop untuk mengelola perpustakaan digital yang dibangun menggunakan Java dan JavaFX. Sistem ini mendukung manajemen buku fisik dan digital dengan implementasi konsep Object-Oriented Programming (OOP) yang lengkap.

## 📋 Daftar Isi

- [Fitur Utama](#-fitur-utama)
- [Teknologi yang Digunakan](#-teknologi-yang-digunakan)
- [Persyaratan Sistem](#-persyaratan-sistem)
- [Instalasi dan Setup](#-instalasi-dan-setup)
- [Cara Menjalankan Aplikasi](#-cara-menjalankan-aplikasi)
- [Struktur Proyek](#-struktur-proyek)
- [Konsep OOP yang Diimplementasikan](#-konsep-oop-yang-diimplementasikan)
- [Dokumentasi API](#-dokumentasi-api)
- [Testing](#-testing)
- [Kontribusi](#-kontribusi)
- [Lisensi](#-lisensi)

## 🚀 Fitur Utama

### Manajemen Buku

- ✅ Tambah buku baru (fisik dan digital)
- ✅ Lihat daftar semua buku
- ✅ Cari buku berdasarkan judul
- ✅ Update informasi buku
- ✅ Hapus buku dari koleksi

### Sistem Peminjaman

- ✅ Pinjam buku yang tersedia
- ✅ Kembalikan buku yang dipinjam
- ✅ Validasi status ketersediaan buku
- ✅ Exception handling untuk skenario error

### Buku Digital

- ✅ Support format file (PDF, EPUB, MOBI, dll)
- ✅ Informasi ukuran file
- ✅ Metadata khusus buku digital

### Interface Pengguna

- ✅ GUI berbasis JavaFX yang user-friendly
- ✅ Form input yang intuitif
- ✅ Tabel untuk menampilkan data buku
- ✅ Notifikasi dan pesan error yang informatif

## 🛠 Teknologi yang Digunakan

- **Java 11** - Bahasa pemrograman utama
- **JavaFX 17.0.2** - Framework untuk GUI desktop
- **Maven** - Build tool dan dependency management
- **FXML** - Markup language untuk UI JavaFX

## 📋 Persyaratan Sistem

### Minimum Requirements

- Java Development Kit (JDK) 11 atau lebih tinggi
- Apache Maven 3.6.0 atau lebih tinggi
- RAM minimal 512 MB
- Ruang disk minimal 100 MB

### Recommended Requirements

- JDK 17 atau lebih tinggi
- Maven 3.8.0 atau lebih tinggi
- RAM 1 GB atau lebih
- Ruang disk 500 MB

## 🔧 Instalasi dan Setup

### 1. Clone Repository

```bash
git clone <repository-url>
cd sistem-manajemen-perpustakaan-digital
```

### 2. Verifikasi Java dan Maven

```bash
# Cek versi Java
java -version

# Cek versi Maven
mvn -version
```

### 3. Install Dependencies

```bash
mvn clean install
```

### 4. Compile Project

```bash
mvn compile
```

## ▶️ Cara Menjalankan Aplikasi

### Menjalankan GUI Application

```bash
# Menggunakan Maven JavaFX plugin
mvn javafx:run

# Atau menggunakan main class
mvn exec:java -Dexec.mainClass="perpustakaan.Main"
```

### Menjalankan Tests

```bash
# Menjalankan semua tests
mvn test

# Menjalankan test fungsionalitas lengkap
mvn test-compile exec:java -Dexec.mainClass="perpustakaan.ApplicationTest" -Dexec.classpathScope=test
```

### Build JAR File

```bash
mvn package
```

## 📁 Struktur Proyek

```
sistem-manajemen-perpustakaan-digital/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── perpustakaan/
│   │   │       ├── Main.java                    # Entry point aplikasi
│   │   │       ├── gui/
│   │   │       │   ├── MainApplication.java     # JavaFX Application class
│   │   │       │   └── PerpustakaanController.java # Controller untuk UI
│   │   │       ├── model/
│   │   │       │   ├── Buku.java               # Model buku fisik
│   │   │       │   ├── BukuDigital.java        # Model buku digital (inheritance)
│   │   │       │   └── BukuTidakTersediaException.java # Custom exception
│   │   │       └── service/
│   │   │           ├── PerpustakaanService.java # Business logic layer
│   │   │           └── Pinjaman.java           # Model untuk data peminjaman
│   │   └── resources/
│   │       └── perpustakaan/
│   │           └── gui/
│   │               └── main.fxml               # UI layout file
│   └── test/
│       └── java/
│           └── perpustakaan/
│               └── ApplicationTest.java        # Comprehensive test suite
├── .kiro/                                      # Kiro IDE configuration
├── target/                                     # Maven build output
├── pom.xml                                     # Maven configuration
├── .gitignore                                  # Git ignore rules
└── README.md                                   # Dokumentasi proyek
```

## 🎯 Konsep OOP yang Diimplementasikan

### 1. Encapsulation (Enkapsulasi)

- **Private fields** dengan **public getter/setter methods**
- Data hiding untuk melindungi integritas data
- Validation dalam setter methods

```java
public class Buku {
    private String judul;        // Private field
    private String pengarang;    // Private field

    public String getJudul() {   // Public getter
        return judul;
    }

    public void setJudul(String judul) {  // Public setter dengan validasi
        if (judul != null && !judul.trim().isEmpty()) {
            this.judul = judul;
        }
    }
}
```

### 2. Inheritance (Pewarisan)

- **BukuDigital** extends **Buku**
- Method overriding untuk behavior yang berbeda
- Super constructor call

```java
public class BukuDigital extends Buku {
    private double ukuranFile;
    private String formatFile;

    @Override
    public String toString() {
        return super.toString() + ", Format: " + formatFile + " (" + ukuranFile + " MB)";
    }
}
```

### 3. Polymorphism (Polimorfisme)

- **Runtime polymorphism** dengan method overriding
- **Collection polymorphism** dengan ArrayList<Buku>
- **instanceof** operator untuk type checking

```java
ArrayList<Buku> daftarBuku = new ArrayList<>();
daftarBuku.add(new Buku("Java Basics", "Author A", 2020, false));
daftarBuku.add(new BukuDigital("Python Guide", "Author B", 2021, false, 12.3, "PDF"));

// Polymorphic method call
for (Buku buku : daftarBuku) {
    System.out.println(buku.toString()); // Calls appropriate toString() method
}
```

### 4. Abstraction (Abstraksi)

- **Service layer** untuk memisahkan business logic
- **Interface segregation** dengan method yang focused
- **Data access abstraction** dalam PerpustakaanService

### 5. Exception Handling

- **Custom exception class**: BukuTidakTersediaException
- **Try-catch blocks** untuk error handling
- **Proper exception propagation**

```java
public void pinjamBuku(String judul) throws BukuTidakTersediaException {
    Buku buku = cariBuku(judul);
    if (buku == null) {
        throw new BukuTidakTersediaException("Buku dengan judul '" + judul + "' tidak ditemukan");
    }
    if (buku.isStatusPinjam()) {
        throw new BukuTidakTersediaException("Buku '" + judul + "' sedang dipinjam");
    }
    buku.setStatusPinjam(true);
}
```

## 📚 Dokumentasi API

### Model Classes

#### Buku

```java
public class Buku {
    // Constructors
    public Buku()
    public Buku(String judul, String pengarang, int tahunTerbit, boolean statusPinjam)

    // Getters & Setters
    public String getJudul()
    public void setJudul(String judul)
    public String getPengarang()
    public void setPengarang(String pengarang)
    public int getTahunTerbit()
    public void setTahunTerbit(int tahunTerbit)
    public boolean isStatusPinjam()
    public void setStatusPinjam(boolean statusPinjam)

    // Methods
    public String toString()
    public boolean equals(Object obj)
    public int hashCode()
}
```

#### BukuDigital extends Buku

```java
public class BukuDigital extends Buku {
    // Additional Constructors
    public BukuDigital(String judul, String pengarang, int tahunTerbit,
                      boolean statusPinjam, double ukuranFile, String formatFile)

    // Additional Getters & Setters
    public double getUkuranFile()
    public void setUkuranFile(double ukuranFile)
    public String getFormatFile()
    public void setFormatFile(String formatFile)

    // Overridden Methods
    @Override
    public String toString()
}
```

### Service Classes

#### PerpustakaanService

```java
public class PerpustakaanService {
    // CRUD Operations
    public void tambahBuku(Buku buku)
    public boolean hapusBuku(String judul)
    public Buku cariBuku(String judul)
    public ArrayList<Buku> getDaftarBuku()

    // Borrowing Operations
    public void pinjamBuku(String judul) throws BukuTidakTersediaException
    public void kembalikanBuku(String judul) throws BukuTidakTersediaException

    // Utility Methods
    public int getTotalBuku()
    public int getBukuTersedia()
    public int getBukuDipinjam()
}
```

### Exception Classes

#### BukuTidakTersediaException

```java
public class BukuTidakTersediaException extends Exception {
    public BukuTidakTersediaException(String message)
}
```

## 🧪 Testing

### Menjalankan Test Suite

```bash
# Test lengkap dengan output detail
mvn test-compile exec:java -Dexec.mainClass="perpustakaan.ApplicationTest" -Dexec.classpathScope=test
```

### Test Coverage

Test suite mencakup:

1. **Model Classes Testing**

   - Encapsulation (getter/setter)
   - Inheritance (BukuDigital extends Buku)
   - Method overriding (toString, equals)

2. **Service Layer Testing**

   - CRUD operations
   - Business logic validation
   - Data integrity

3. **Exception Handling Testing**

   - Custom exception scenarios
   - Error message validation
   - Exception propagation

4. **Polymorphism Testing**
   - Mixed object collections
   - Runtime type checking
   - Method dispatch

### Sample Test Output

```
=== Testing Sistem Manajemen Perpustakaan Digital ===

1. Testing Model Classes (Encapsulation):
   ✓ Model classes working correctly

2. Testing Service Layer (CRUD Operations):
   ✓ Service layer working correctly

3. Testing Exception Handling:
   ✓ Exception handling working correctly

4. Testing Polymorphism:
   ✓ Polymorphism working correctly

=== All Tests Completed Successfully! ===
```

## 🎨 Screenshots dan Demo

### Main Interface

- Form input untuk menambah buku baru
- Tabel untuk menampilkan daftar buku
- Button untuk operasi CRUD dan peminjaman

### Features Demo

- Tambah buku fisik dan digital
- Pencarian buku berdasarkan judul
- Peminjaman dan pengembalian buku
- Error handling dengan dialog informatif

## 🔧 Troubleshooting

### Common Issues

#### 1. JavaFX Runtime Error

```
Error: JavaFX runtime components are missing
```

**Solution**: Gunakan Maven JavaFX plugin:

```bash
mvn javafx:run
```

#### 2. Java Version Compatibility

```
Error: Unsupported class file major version
```

**Solution**: Pastikan menggunakan JDK 11 atau lebih tinggi:

```bash
java -version
```

#### 3. Maven Dependencies

```
Error: Could not resolve dependencies
```

**Solution**: Clean dan reinstall dependencies:

```bash
mvn clean install
```

## 🤝 Kontribusi

### Development Guidelines

1. Fork repository ini
2. Buat feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit perubahan (`git commit -m 'Add some AmazingFeature'`)
4. Push ke branch (`git push origin feature/AmazingFeature`)
5. Buat Pull Request

### Code Style

- Gunakan Java naming conventions
- Tambahkan JavaDoc untuk public methods
- Write unit tests untuk fitur baru
- Follow OOP best practices

### Testing Requirements

- Semua fitur baru harus memiliki unit tests
- Test coverage minimal 80%
- Integration tests untuk UI components

## 📄 Lisensi

Proyek ini dilisensikan di bawah MIT License - lihat file [LICENSE](LICENSE) untuk detail lengkap.

## 👥 Tim Pengembang

- **Developer**: [Nama Developer]
- **Email**: [email@example.com]
- **GitHub**: [github.com/username]

## 📞 Dukungan

Jika Anda mengalami masalah atau memiliki pertanyaan:

1. Cek [Issues](../../issues) yang sudah ada
2. Buat [Issue baru](../../issues/new) jika diperlukan
3. Hubungi tim pengembang melalui email

## 🔄 Changelog

### Version 1.0.0 (Current)

- ✅ Implementasi lengkap CRUD operations
- ✅ GUI berbasis JavaFX
- ✅ Sistem peminjaman dan pengembalian
- ✅ Support buku digital dengan metadata
- ✅ Exception handling yang robust
- ✅ Comprehensive test suite
- ✅ Complete OOP implementation

### Planned Features (v1.1.0)

- 🔄 Database persistence (H2/SQLite)
- 🔄 User authentication system
- 🔄 Advanced search filters
- 🔄 Export/Import functionality
- 🔄 Reporting features

---

**Terima kasih telah menggunakan Sistem Manajemen Perpustakaan Digital!** 📚✨
