import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Order {
    private static int sequence = 1001;

    private final String orderId;
    private final Customer customer;
    private final Map<Product, Integer> items;
    private final Delivery delivery;
    private final LocalDateTime createdAt;

    private OrderStatus status;

    public Order(
            Customer customer,
            Map<Product, Integer> items,
            Delivery delivery
    ) {
        this.orderId = "FHO-" + sequence;
        sequence++;

        this.customer = customer;
        this.items = items;
        this.delivery = delivery;
        this.createdAt = LocalDateTime.now();
        this.status = OrderStatus.WAITING_PAYMENT;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
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

    public long getGrandTotal() {
        return getSubtotal() + (long) delivery.calculateShippingCost();
    }

    public void reduceProductStock() {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            product.decreaseStock(quantity);
        }
    }

    public void showOrderSummary() {
        System.out.println("\n========== RINGKASAN PESANAN ==========");
        System.out.println("ID Pesanan : " + orderId);
        System.out.println("Pelanggan  : " + customer.getName());

        System.out.println(
                "Tanggal    : "
                        + createdAt.format(
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
                )
        );

        System.out.println("\nDaftar Produk:");

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

        System.out.printf("\nSubtotal Produk : Rp%,d%n", getSubtotal());

        System.out.println(
                "Pengiriman      : " + delivery.getDeliveryName()
        );

        System.out.printf(
                "Jarak           : %.1f km%n",
                delivery.getDistance()
        );

        System.out.printf(
                "Ongkos Kirim    : Rp%,d%n",
                (long) delivery.calculateShippingCost()
        );

        System.out.printf(
                "TOTAL BAYAR     : Rp%,d%n",
                getGrandTotal()
        );

        System.out.println("Status          : " + status);
    }
}