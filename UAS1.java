// Deklarasi kelas MenuMakanan
class MenuMakanan {
    // Atribut
    private String nama;
    private int harga;
    
    // Konstruktor
    public MenuMakanan(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }
    
    // Metode getter
    public String getNama() {
        return nama;
    }
    
    public int getHarga() {
        return harga;
    }
}

// Deklarasi kelas Pesanan
class Pesanan {
    // Atribut
    private MenuMakanan menu;
    private int jumlah;
    
    // Konstruktor
    public Pesanan(MenuMakanan menu, int jumlah) {
        this.menu = menu;
        this.jumlah = jumlah;
    }
    
    // Metode getter
    public MenuMakanan getMenu() {
        return menu;
    }
    
    public int getJumlah() {
        return jumlah;
    }
    
    // Metode untuk menghitung total harga pesanan
    public int hitungTotalHarga() {
        return menu.getHarga() * jumlah;
    }
}

// Deklarasi kelas Pemesanan
class Pemesanan {
    // Atribut
    private Pesanan[] daftarPesanan;
    private int jumlahPesanan;
    
    // Konstruktor
    public Pemesanan() {
        daftarPesanan = new Pesanan[10];
        jumlahPesanan = 0;
    }
    
    // Metode untuk menambah pesanan
    public void tambahPesanan(Pesanan pesanan) {
        daftarPesanan[jumlahPesanan] = pesanan;
        jumlahPesanan++;
    }
    
    // Metode untuk menghitung total harga pesanan
    public int hitungTotalHarga() {
        int totalHarga = 0;
        for (int i = 0; i < jumlahPesanan; i++) {
            totalHarga += daftarPesanan[i].hitungTotalHarga();
        }
        return totalHarga;
    }
    
    // Metode untuk menampilkan nota pembayaran
    public void tampilkanNota() {
        System.out.println("==================================");
        System.out.println("           NOTA PEMBAYARAN         ");
        System.out.println("==================================");
        for (int i = 0; i < jumlahPesanan; i++) {
            Pesanan pesanan = daftarPesanan[i];
            MenuMakanan menu = pesanan.getMenu();
            int harga = menu.getHarga();
            int jumlah = pesanan.getJumlah();
            int totalHarga = pesanan.hitungTotalHarga();
            
            System.out.println("Menu: " + menu.getNama());
            System.out.println("Harga: " + harga);
            System.out.println("Jumlah: " + jumlah);
            System.out.println("Subtotal: " + totalHarga);
            System.out.println("----------------------------------");
        }
        System.out.println("Total Harga: " + hitungTotalHarga());
        System.out.println("==================================");
    }
}

// Kelas utama
public class  UAS1 {
    public static void main(String[] args) {
        // Membuat menu makanan
        MenuMakanan menu1 = new MenuMakanan("Nasi Goreng", 12000);
        MenuMakanan menu2 = new MenuMakanan("Ayam Goreng", 15000);
        MenuMakanan menu3 = new MenuMakanan("Mie Ayam", 10000);
        
        // Membuat pesanan
        Pesanan pesanan1 = new Pesanan(menu1, 3);
        Pesanan pesanan2 = new Pesanan(menu2, 1);
        Pesanan pesanan3 = new Pesanan(menu3, 3);
        
        // Membuat objek pemesanan
        Pemesanan pemesanan = new Pemesanan();
        
        // Menambahkan pesanan
        pemesanan.tambahPesanan(pesanan1);
        pemesanan.tambahPesanan(pesanan2);
        pemesanan.tambahPesanan(pesanan3);
        
        // Menampilkan nota pembayaran
        pemesanan.tampilkanNota();
    }
}
