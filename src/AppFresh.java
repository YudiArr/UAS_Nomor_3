import java.util.Scanner;

public class AppFresh {
    private static final Scanner input = new Scanner(System.in);
    private static final Store store = new Store();

    public static void main(String[] args) {
        store.seedData();

        System.out.println("======================================================");
        System.out.println("     FRESH HARVEST ORGANICS (FHO) - REAL FOOD");
        System.out.println("======================================================");
        System.out.println("Sayur, buah, daging, dan bahan alami berkualitas.");
        System.out.println("Maksimal jangkauan pengiriman: 25 km");

        boolean running = true;

        while (running) {
            System.out.println("\n============== MENU UTAMA ==============");
            System.out.println("1. Masuk sebagai Pelanggan");
            System.out.println("2. Masuk sebagai Admin");
            System.out.println("0. Keluar");

            int choice = readInt("Pilih menu: ");

            switch (choice) {
                case 1:
                    Customer customer = new Customer(
                            "C001",
                            "Bambang",
                            "Bambang@email.com"
                    );
                    customerMenu(customer);
                    break;

                case 2:
                    Admin admin = new Admin(
                            "A001",
                            "Asep",
                            "admin@fho.id"
                    );
                    adminMenu(admin);
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Menu tidak tersedia.");
            }
        }

        System.out.println("\nTerima kasih telah menggunakan FHO.");
    }

    private static void customerMenu(Customer customer) {
        boolean back = false;

        while (!back) {
            System.out.println("\n========== MENU PELANGGAN ==========");
            System.out.println("Halo, " + customer.getName());
            System.out.println("1. Lihat Katalog");
            System.out.println("2. Tambah Produk ke Keranjang");
            System.out.println("3. Lihat Resep dan Beli Bahan");
            System.out.println("4. Lihat Keranjang");
            System.out.println("5. Checkout dan Pembayaran");
            System.out.println("6. Lihat Riwayat Pesanan");
            System.out.println("0. Kembali");

            int choice = readInt("Pilih menu: ");

            switch (choice) {
                case 1:
                    store.showCatalog();
                    break;

                case 2:
                    addProductToCart(customer);
                    break;

                case 3:
                    buyRecipeIngredients(customer);
                    break;

                case 4:
                    customer.getCart().showCart();
                    break;

                case 5:
                    checkout(customer);
                    break;

                case 6:
                    customer.showOrderHistory();
                    break;

                case 0:
                    back = true;
                    break;

                default:
                    System.out.println("Menu tidak tersedia.");
            }
        }
    }

    private static void adminMenu(Admin admin) {
        boolean back = false;

        while (!back) {
            System.out.println("\n========== MENU ADMIN ==========");
            System.out.println("Halo, " + admin.getName());
            System.out.println("1. Lihat Katalog dan Stok");
            System.out.println("2. Tambah Produk Baru");
            System.out.println("3. Ubah Stok Produk");
            System.out.println("4. Lihat Semua Pesanan");
            System.out.println("0. Kembali");

            int choice = readInt("Pilih menu: ");

            switch (choice) {
                case 1:
                    store.showCatalog();
                    break;

                case 2:
                    addNewProduct();
                    break;

                case 3:
                    updateStock();
                    break;

                case 4:
                    store.showAllOrders();
                    break;

                case 0:
                    back = true;
                    break;

                default:
                    System.out.println("Menu tidak tersedia.");
            }
        }
    }

    private static void addProductToCart(Customer customer) {
        store.showCatalog();

        String productCode = readText("\nMasukkan kode produk (0 untuk batal): ");

        if (productCode.equals("0")) {
            return;
        }

        Product product = store.findProduct(productCode);

        if (product == null) {
            System.out.println("Produk tidak ditemukan.");
            return;
        }

        int quantity = readInt("Masukkan jumlah pembelian: ");

        if (quantity <= 0) {
            System.out.println("Jumlah pembelian harus lebih dari 0.");
            return;
        }

        if (quantity > product.getStock()) {
            System.out.println("Stok tidak mencukupi.");
            return;
        }

        customer.getCart().addItem(product, quantity);

        System.out.println(
                product.getName() + " berhasil ditambahkan ke keranjang."
        );
    }

    private static void buyRecipeIngredients(Customer customer) {
        store.showRecipes();

        int recipeChoice = readInt("\nPilih resep (0 untuk batal): ");

        if (recipeChoice == 0) {
            return;
        }

        Recipe recipe = store.getRecipe(recipeChoice - 1);

        if (recipe == null) {
            System.out.println("Resep tidak ditemukan.");
            return;
        }

        recipe.displayRecipe();

        String confirmation = readText(
                "\nMasukkan semua bahan resep ke keranjang? (y/n): "
        );

        if (confirmation.equalsIgnoreCase("y")) {
            int total = recipe.addIngredientsToCart(customer.getCart());

            System.out.println(
                    total + " bahan berhasil dimasukkan ke keranjang."
            );
        }
    }

    private static void checkout(Customer customer) {
        Cart cart = customer.getCart();

        if (cart.isEmpty()) {
            System.out.println("Keranjang masih kosong.");
            return;
        }

        cart.showCart();

        double distance = readDouble(
                "\nMasukkan jarak alamat dari FHO (km): "
        );

        if (distance <= 0) {
            System.out.println("Jarak tidak valid.");
            return;
        }

        if (distance > 25) {
            System.out.println(
                    "Maaf, FHO hanya melayani area maksimal 25 km."
            );
            return;
        }

        System.out.println("\nPilih Jenis Pengiriman:");
        System.out.println("1. Instant Delivery");
        System.out.println("   Estimasi: sekitar 1 jam");
        System.out.println("   Ongkir: simulasi perhitungan Gojek API");
        System.out.println("2. Next Day Delivery");
        System.out.println("   Estimasi: besok pukul 08.00 - 17.00");
        System.out.println("   Ongkir: flat Rp20.000");

        int deliveryChoice = readInt("Pilih pengiriman: ");

        Delivery delivery;

        if (deliveryChoice == 1) {
            delivery = new InstantDelivery(distance);
        } else if (deliveryChoice == 2) {
            delivery = new NextDayDelivery(distance);
        } else {
            System.out.println("Layanan pengiriman tidak valid.");
            return;
        }

        Order order = new Order(
                customer,
                cart.getItemsCopy(),
                delivery
        );

        order.showOrderSummary();

        PaymentMethod paymentMethod = choosePaymentMethod();

        if (paymentMethod == null) {
            return;
        }

        boolean paymentSuccess = paymentMethod.pay(order.getGrandTotal());

        if (paymentSuccess) {
            order.setStatus(OrderStatus.PAID);

            order.reduceProductStock();

            customer.addOrder(order);
            store.addOrder(order);

            cart.clearCart();

            System.out.println("\n==============================================");
            System.out.println("PEMBAYARAN BERHASIL");
            System.out.println("Pesanan " + order.getOrderId() + " sedang diproses.");
            System.out.println(
                    "Estimasi pengiriman: "
                            + delivery.getEstimatedArrival()
            );
            System.out.println("==============================================");
        }
    }

    private static PaymentMethod choosePaymentMethod() {
        System.out.println("\n===== PAYMENT GATEWAY (SIMULASI) =====");
        System.out.println("1. E-Wallet");
        System.out.println("2. Virtual Account");
        System.out.println("3. Kartu Kredit");

        int choice = readInt("Pilih metode pembayaran: ");

        switch (choice) {
            case 1:
                return new EWalletPayment();

            case 2:
                return new VirtualAccountPayment();

            case 3:
                return new CreditCardPayment();

            default:
                System.out.println("Metode pembayaran tidak valid.");
                return null;
        }
    }

    private static void addNewProduct() {
        String code = readText("Kode produk: ");

        if (store.findProduct(code) != null) {
            System.out.println("Kode produk sudah digunakan.");
            return;
        }

        String name = readText("Nama produk: ");
        long price = readLong("Harga produk: ");
        int stock = readInt("Stok produk: ");

        if (price <= 0 || stock < 0) {
            System.out.println("Harga atau stok tidak valid.");
            return;
        }

        System.out.println("\nPilih kategori:");
        System.out.println("1. Sayuran");
        System.out.println("2. Buah");
        System.out.println("3. Daging");
        System.out.println("4. Bahan Alami");

        int category = readInt("Pilih kategori: ");

        Product product = null;

        switch (category) {
            case 1:
                product = new Vegetable(
                        code,
                        name,
                        price,
                        stock,
                        "Lokal organik"
                );
                break;

            case 2:
                product = new Fruit(
                        code,
                        name,
                        price,
                        stock,
                        "Grade A"
                );
                break;

            case 3:
                product = new Meat(
                        code,
                        name,
                        price,
                        stock,
                        "Dingin 0-4 C"
                );
                break;

            case 4:
                product = new NaturalIngredient(
                        code,
                        name,
                        price,
                        stock,
                        "Tanpa pengawet"
                );
                break;

            default:
                System.out.println("Kategori tidak valid.");
                return;
        }

        store.addProduct(product);

        System.out.println("Produk berhasil ditambahkan.");
    }

    private static void updateStock() {
        store.showCatalog();

        String productCode = readText("\nMasukkan kode produk: ");

        Product product = store.findProduct(productCode);

        if (product == null) {
            System.out.println("Produk tidak ditemukan.");
            return;
        }

        int newStock = readInt("Masukkan stok baru: ");

        if (newStock < 0) {
            System.out.println("Stok tidak boleh negatif.");
            return;
        }

        product.setStock(newStock);

        System.out.println(
                "Stok " + product.getName()
                        + " berhasil diperbarui menjadi "
                        + product.getStock()
        );
    }

    private static String readText(String message) {
        System.out.print(message);
        return input.nextLine().trim();
    }

    private static int readInt(String message) {
        try {
            return Integer.parseInt(readText(message));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static long readLong(String message) {
        try {
            return Long.parseLong(readText(message));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double readDouble(String message) {
        try {
            return Double.parseDouble(
                    readText(message).replace(",", ".")
            );
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}