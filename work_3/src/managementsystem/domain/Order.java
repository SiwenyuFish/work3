package managementsystem.domain;

public class Order {
    private Integer orderid;
    private  Integer commodityid;
    private String ordertime;
    private  Integer count;
    private Double orderprice;

    public Order() {
    }

    public Integer getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Integer commodityid) {
        this.commodityid = commodityid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }



    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public Double getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(Double orderprice) {
        this.orderprice = orderprice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "订单号=" + orderid +
                ", 商品号=" + commodityid +
                ", 下单时间='" + ordertime + '\'' +
                ", 件数=" + count +
                ", 订单价格=" + orderprice +
                '}';
    }
}
