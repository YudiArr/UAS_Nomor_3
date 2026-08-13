import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private final Cart cart;
    private final List<Order> orderHistory;

    public Customer(String id, String name, String email) {
        super(id, name, email);

        cart = new Cart();
        orderHistory = new ArrayList<Order>();
    }

    @Override
    public String getRole() {
        return "Pelanggan";
    }

    public Cart getCart() {
        return cart;
    }

    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    public void showOrderHistory() {
        System.out.println("\n===== RIWAYAT PESANAN =====");

        if (orderHistory.isEmpty()) {
            System.out.println("Belum ada pesanan.");
            return;
        }

        for (Order order : orderHistory) {
            order.showOrderSummary();
        }
    }
}