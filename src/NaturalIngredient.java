public class NaturalIngredient extends Product {
    private final String benefit;

    public NaturalIngredient(
            String code,
            String name,
            long price,
            int stock,
            String benefit
    ) {
        super(code, name, price, stock);
        this.benefit = benefit;
    }

    @Override
    public String getCategory() {
        return "Bahan Alami";
    }

    @Override
    public String getQualityInfo() {
        return benefit;
    }
}