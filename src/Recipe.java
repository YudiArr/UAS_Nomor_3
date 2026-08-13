import java.util.Arrays;
import java.util.List;

public class Recipe {
    private final String title;
    private final String description;
    private final List<Product> ingredients;

    public Recipe(
            String title,
            String description,
            Product... ingredients
    ) {
        this.title = title;
        this.description = description;
        this.ingredients = Arrays.asList(ingredients);
    }

    public String getTitle() {
        return title;
    }

    public void displayRecipe() {
        System.out.println("\n========== " + title.toUpperCase() + " ==========");
        System.out.println("Deskripsi: " + description);

        System.out.println("\nBahan yang dibutuhkan:");

        for (Product product : ingredients) {
            System.out.printf(
                    "- %s | Harga: Rp%,d%n",
                    product.getName(),
                    product.getPrice()
            );
        }
    }

    public int addIngredientsToCart(Cart cart) {
        int totalAdded = 0;

        for (Product product : ingredients) {
            if (product.getStock() > 0) {
                cart.addItem(product, 1);
                totalAdded++;
            }
        }

        return totalAdded;
    }
}