public abstract class Product {
    private final String code;
    private final String name;
    private final long price;
    private int stock;

    public Product(String code, String name, long price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            this.stock = 0;
        } else {
            this.stock = stock;
        }
    }

    public void decreaseStock(int quantity) {
        setStock(stock - quantity);
    }

    public abstract String getCategory();

    public abstract String getQualityInfo();

    public String getCatalogInfo() {
        return String.format(
                "%-4s | %-22s | %-14s | Rp%,10d | Stok: %-3d | %s",
                code,
                name,
                getCategory(),
                price,
                stock,
                getQualityInfo()
        );
    }
}