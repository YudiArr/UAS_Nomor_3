public class Fruit extends Product {
    private final String grade;

    public Fruit(
            String code,
            String name,
            long price,
            int stock,
            String grade
    ) {
        super(code, name, price, stock);
        this.grade = grade;
    }

    @Override
    public String getCategory() {
        return "Buah";
    }

    @Override
    public String getQualityInfo() {
        return grade;
    }
}