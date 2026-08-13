public class Meat extends Product {
    private final String storageTemperature;

    public Meat(
            String code,
            String name,
            long price,
            int stock,
            String storageTemperature
    ) {
        super(code, name, price, stock);
        this.storageTemperature = storageTemperature;
    }

    @Override
    public String getCategory() {
        return "Daging";
    }

    @Override
    public String getQualityInfo() {
        return storageTemperature;
    }
}