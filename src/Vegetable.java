public class Vegetable extends Product {
    private final String origin;

    public Vegetable(
            String code,
            String name,
            long price,
            int stock,
            String origin
    ) {
        super(code, name, price, stock);
        this.origin = origin;
    }

    @Override
    public String getCategory() {
        return "Sayuran";
    }

    @Override
    public String getQualityInfo() {
        return origin;
    }
}