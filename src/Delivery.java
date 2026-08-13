interface Deliverable {
    double calculateShippingCost();

    String getEstimatedArrival();
}

public abstract class Delivery implements Deliverable {
    protected double distance;

    public Delivery(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public abstract String getDeliveryName();
}