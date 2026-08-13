public class NextDayDelivery extends Delivery {
    public NextDayDelivery(double distance) {
        super(distance);
    }

    @Override
    public String getDeliveryName() {
        return "Next Day Delivery (Kurir Toko)";
    }

    @Override
    public double calculateShippingCost() {
        return 20000;
    }

    @Override
    public String getEstimatedArrival() {
        return "Besok pukul 08.00 - 17.00";
    }
}