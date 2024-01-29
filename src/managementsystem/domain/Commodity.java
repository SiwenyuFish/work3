package managementsystem.domain;

public class Commodity {
    private Integer commodityid;
    private String name;
    private Double price;

    public Commodity() {
    }

    public Integer getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Integer commodityid) {
        this.commodityid = commodityid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "商品号=" + commodityid +
                ", 商品名='" + name + '\'' +
                ", 价格=" + price +
                '}';
    }
}
