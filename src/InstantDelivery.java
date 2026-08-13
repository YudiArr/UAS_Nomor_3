public class InstantDelivery extends Delivery {
    public InstantDelivery(double distance) {
        super(distance);
    }

    @Override
    public String getDeliveryName() {
        return "Instant Delivery (Simulasi Gojek API)";
    }

    @Override
    public double calculateShippingCost() {
        /*
         * Simulasi Gojek API:
         * Biaya dasar Rp7.000
         * Ditambah Rp2.500 untuk setiap kilometer.
         */
        return 7000 + (distance * 2500);
    }

    @Override
    public String getEstimatedArrival() {
        return "Sekitar 1 jam setelah pembayaran";
    }
}