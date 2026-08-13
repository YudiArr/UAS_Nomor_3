public class EWalletPayment implements PaymentMethod {
    @Override
    public boolean pay(long amount) {
        System.out.printf(
                "\nMemproses pembayaran E-Wallet sebesar Rp%,d...\n",
                amount
        );

        System.out.println("Payment Gateway: pembayaran berhasil.");
        return true;
    }

    @Override
    public String getPaymentName() {
        return "E-Wallet";
    }
}