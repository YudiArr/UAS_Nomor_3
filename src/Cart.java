import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private final Map<Product, Integer> items;

    public Cart() {
        items = new LinkedHashMap<Product, Integer>();
    }

    public void addItem(Product product, int quantity) {
        if (items.containsKey(product)) {
            int currentQuantity = items.get(product);
            items.put(product, currentQuantity + quantity);
        } else {
            items.put(product, quantity);
        }
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clearCart() {
        items.clear();
    }

    public Map<Product, Integer> getItemsCopy() {
        return new LinkedHashMap<Product, Integer>(items);
    }

    public long getSubtotal() {
        long subtotal = 0;

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            subtotal += product.getPrice() * quantity;
        }

        return subtotal;
    }

    public void showCart() {
        System.out.println("\n========== KERANJANG BELANJA ==========");

        if (isEmpty()) {
            System.out.println("Keranjang masih kosong.");
            return;
        }

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            System.out.printf(
                    "%s x%d = Rp%,d%n",
                    product.getName(),
                    quantity,
                    product.getPrice() * quantity
            );
        }

        System.out.printf("Subtotal Produk: Rp%,d%n", getSubtotal());
    }
}