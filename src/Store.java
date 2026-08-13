import java.util.ArrayList;
import java.util.List;

public class Store {
    private final List<Product> catalog;
    private final List<Recipe> recipes;
    private final List<Order> orders;

    public Store() {
        catalog = new ArrayList<Product>();
        recipes = new ArrayList<Recipe>();
        orders = new ArrayList<Order>();
    }

    public void seedData() {
        Product bayam = new Vegetable(
                "V01",
                "Bayam Organik",
                12000,
                30,
                "Panen hari ini"
        );

        Product wortel = new Vegetable(
                "V02",
                "Wortel Organik",
                18000,
                25,
                "Lokal organik"
        );

        Product brokoli = new Vegetable(
                "V03",
                "Brokoli Organik",
                28000,
                15,
                "Tanpa pestisida"
        );

        Product apel = new Fruit(
                "B01",
                "Apel Fuji",
                35000,
                20,
                "Grade A"
        );

        Product pisang = new Fruit(
                "B02",
                "Pisang Cavendish",
                22000,
                25,
                "Matang alami"
        );

        Product alpukat = new Fruit(
                "B03",
                "Alpukat Mentega",
                30000,
                18,
                "Premium"
        );

        Product dadaAyam = new Meat(
                "D01",
                "Dada Ayam Fillet",
                48000,
                18,
                "Dingin 0-4 C"
        );

        Product salmon = new Meat(
                "D02",
                "Salmon Fillet",
                95000,
                10,
                "Dingin 0-4 C"
        );

        Product madu = new NaturalIngredient(
                "A01",
                "Madu Hutan",
                65000,
                15,
                "Tanpa pengawet"
        );

        Product oats = new NaturalIngredient(
                "A02",
                "Oat Organik",
                45000,
                20,
                "Tinggi serat"
        );

        catalog.add(bayam);
        catalog.add(wortel);
        catalog.add(brokoli);
        catalog.add(apel);
        catalog.add(pisang);
        catalog.add(alpukat);
        catalog.add(dadaAyam);
        catalog.add(salmon);
        catalog.add(madu);
        catalog.add(oats);

        Recipe tumisBayam = new Recipe(
                "Tumis Bayam Ayam Sehat",
                "Menu tinggi protein dan zat besi, siap dalam 20 menit.",
                bayam,
                wortel,
                dadaAyam
        );

        Recipe smoothie = new Recipe(
                "Smoothie Apel Pisang Madu",
                "Sarapan real food praktis, alami, dan mengenyangkan.",
                apel,
                pisang,
                madu
        );

        Recipe overnightOat = new Recipe(
                "Overnight Oat Alpukat",
                "Sarapan tinggi serat dengan lemak baik dari alpukat.",
                oats,
                alpukat,
                madu
        );

        recipes.add(tumisBayam);
        recipes.add(smoothie);
        recipes.add(overnightOat);
    }

    public void showCatalog() {
        System.out.println("\n=============== KATALOG FHO ===============");

        for (Product product : catalog) {
            System.out.println(product.getCatalogInfo());
        }
    }

    public Product findProduct(String code) {
        for (Product product : catalog) {
            if (product.getCode().equalsIgnoreCase(code)) {
                return product;
            }
        }

        return null;
    }

    public void addProduct(Product product) {
        catalog.add(product);
    }

    public void showRecipes() {
        System.out.println("\n=============== RESEP FHO ===============");

        for (int i = 0; i < recipes.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + recipes.get(i).getTitle()
            );
        }
    }

    public Recipe getRecipe(int index) {
        if (index >= 0 && index < recipes.size()) {
            return recipes.get(index);
        }

        return null;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void showAllOrders() {
        System.out.println("\n=============== SELURUH PESANAN ===============");

        if (orders.isEmpty()) {
            System.out.println("Belum ada pesanan masuk.");
            return;
        }

        for (Order order : orders) {
            order.showOrderSummary();
        }
    }
}