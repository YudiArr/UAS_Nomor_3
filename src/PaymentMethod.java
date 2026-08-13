public interface PaymentMethod {
    boolean pay(long amount);

    String getPaymentName();
}