import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Kelas Buku
class Buku {
    private String judul;
    private String pengarang;
    private int tahunTerbit;
    private String kategori;
    private boolean tersedia;

    public Buku(String judul, String pengarang, int tahunTerbit, String kategori) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.kategori = kategori;
        this.tersedia = true; // Set default status buku tersedia
    }

    public String getJudul() {
        return judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public String getKategori() {
        return kategori;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void pinjamBuku() {
        tersedia = false; // Set status buku menjadi tidak tersedia saat dipinjam
    }

    public void kembalikanBuku() {
        tersedia = true; // Set status buku menjadi tersedia saat dikembalikan
    }
}

// Kelas Perpustakaan
class Perpustakaan {
    private List<Buku> daftarBuku;
    private Map<String, List<Buku>> katalogBuku;

    public Perpustakaan() {
        daftarBuku = new ArrayList<>();
        katalogBuku = new HashMap<>();
    }

    public void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
        // Tambahkan buku ke dalam katalog berdasarkan kategori
        if (katalogBuku.containsKey(buku.getKategori())) {
            katalogBuku.get(buku.getKategori()).add(buku);
        } else {
            List<Buku> bukuKategori = new ArrayList<>();
            bukuKategori.add(buku);
            katalogBuku.put(buku.getKategori(), bukuKategori);
        }
        System.out.println("Buku berhasil ditambahkan ke perpustakaan.");
    }

    public void hapusBuku(String judul) {
        Buku bukuDihapus = null;
        for (Buku buku : daftarBuku) {
            if (buku.getJudul().equalsIgnoreCase(judul)) {
                bukuDihapus = buku;
                break;
            }
        }

        if (bukuDihapus != null) {
            daftarBuku.remove(bukuDihapus);
            // Hapus buku dari katalog berdasarkan kategori
            List<Buku> bukuKategori = katalogBuku.get(bukuDihapus.getKategori());
            bukuKategori.remove(bukuDihapus);
            System.out.println("Buku berhasil dihapus dari perpustakaan.");
        } else {
            System.out.println("Buku dengan judul tersebut tidak ditemukan.");
        }
    }

    public void cariBuku(String judul) {
        boolean bukuDitemukan = false;
        for (Buku buku : daftarBuku) {
            if (buku.getJudul().equalsIgnoreCase(judul)) {
                bukuDitemukan = true;
                System.out.println("Buku ditemukan:");
                System.out.println("Judul: " + buku.getJudul());
                System.out.println("Pengarang: " + buku.getPengarang());
                System.out.println("Tahun Terbit: " + buku.getTahunTerbit());
                System.out.println("Kategori: " + buku.getKategori());
                System.out.println("Status: " + (buku.isTersedia() ? "Tersedia" : "Tidak Tersedia"));
                break;
            }
        }

        if (!bukuDitemukan) {
            System.out.println("Buku dengan judul tersebut tidak ditemukan.");
        }
    }

    public void cariBukuByKategori(String kategori) {
        if (katalogBuku.containsKey(kategori)) {
            List<Buku> bukuKategori = katalogBuku.get(kategori);
            System.out.println("Buku dengan kategori \"" + kategori + "\":");
            for (Buku buku : bukuKategori) {
                System.out.println("- " + buku.getJudul() + " oleh " + buku.getPengarang());
            }
        } else {
            System.out.println("Tidak ada buku dengan kategori \"" + kategori + "\".");
        }
    }

    public void pinjamBuku(String judul) {
        boolean bukuDitemukan = false;
        for (Buku buku : daftarBuku) {
            if (buku.getJudul().equalsIgnoreCase(judul)) {
                bukuDitemukan = true;
                if (buku.isTersedia()) {
                    buku.pinjamBuku();
                    System.out.println("Buku berhasil dipinjam.");
                } else {
                    System.out.println("Buku sedang tidak tersedia.");
                }
                break;
            }
        }

        if (!bukuDitemukan) {
            System.out.println("Buku dengan judul tersebut tidak ditemukan.");
        }
    }

    public void kembalikanBuku(String judul) {
        boolean bukuDitemukan = false;
        for (Buku buku : daftarBuku) {
            if (buku.getJudul().equalsIgnoreCase(judul)) {
                bukuDitemukan = true;
                if (!buku.isTersedia()) {
                    buku.kembalikanBuku();
                    System.out.println("Buku berhasil dikembalikan.");
                } else {
                    System.out.println("Buku sudah tersedia.");
                }
                break;
            }
        }

        if (!bukuDitemukan) {
            System.out.println("Buku dengan judul tersebut tidak ditemukan.");
        }
    }
}

// Kelas Utama
public class UAS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Perpustakaan perpustakaan = new Perpustakaan();

        boolean running = true;
        while (running) {
            System.out.println("=== Pengelolaan Buku Perpustakaan ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Hapus Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Cari Buku Berdasarkan Kategori");
            System.out.println("5. Pinjam Buku");
            System.out.println("6. Kembalikan Buku");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            int menu = scanner.nextInt();
            scanner.nextLine(); // Membersihkan new line

            switch (menu) {
                case 1:
                    System.out.print("Masukkan judul buku: ");
                    String judul = scanner.nextLine();
                    System.out.print("Masukkan nama pengarang: ");
                    String pengarang = scanner.nextLine();
                    System.out.print("Masukkan tahun terbit: ");
                    int tahunTerbit = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan new line
                    System.out.print("Masukkan kategori buku: ");
                    String kategori = scanner.nextLine();

                    Buku buku = new Buku(judul, pengarang, tahunTerbit, kategori);
                    perpustakaan.tambahBuku(buku);
                    break;

                case 2:
                    System.out.print("Masukkan judul buku yang akan dihapus: ");
                    String judulHapus = scanner.nextLine();
                    perpustakaan.hapusBuku(judulHapus);
                    break;

                case 3:
                    System.out.print("Masukkan judul buku yang akan dicari: ");
                    String judulCari = scanner.nextLine();
                    perpustakaan.cariBuku(judulCari);
                    break;

                case 4:
                    System.out.print("Masukkan kategori buku yang akan dicari: ");
                    String kategoriCari = scanner.nextLine();
                    perpustakaan.cariBukuByKategori(kategoriCari);
                    break;

                case 5:
                    System.out.print("Masukkan judul buku yang akan dipinjam: ");
                    String judulPinjam = scanner.nextLine();
                    perpustakaan.pinjamBuku(judulPinjam);
                    break;

                case 6:
                    System.out.print("Masukkan judul buku yang akan dikembalikan: ");
                    String judulKembalikan = scanner.nextLine();
                    perpustakaan.kembalikanBuku(judulKembalikan);
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Menu tidak valid.");
            }

            System.out.println();
        }

        System.out.println("Terima kasih telah menggunakan program ini!");
    }
}
