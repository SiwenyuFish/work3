package managementsystem.service;

import managementsystem.dao.CommodityDao;
import managementsystem.dao.OrderDao;
import managementsystem.domain.Order;

import java.util.Date;
import java.util.List;


public class OrderService {
    private OrderDao orderDao=new OrderDao();
    private CommodityDao commodityDao=new CommodityDao();
    private String sql;

    /**
     *方法：添加新的订单
     * 输入的商品号若在商品表中查询不到则订单添加失败
     * @return 添加成功返回真
     */
    public boolean addNewOrder(int commodityid,int count){

        sql="INSERT INTO `order` (`commodityid`,`ordertime`,`count`,`orderprice`) VALUES(?,?,?,?)";

        String sql2="SELECT `price` FROM `commodity` WHERE `commodityid`=?";
        Object price= null;

        try {
            price = commodityDao.queryScalar(sql2,commodityid);
            double sum=(double) price*(double) count;
        } catch (Exception e) {
            System.out.println("不存在商品号为"+commodityid+"的商品，订单添加失败");
            return false;
        }

        double sum=(double) price*(double) count;

        boolean insertDate=orderDao.updateData(sql,commodityid,new Date(),count,sum);

        if(!insertDate){
            System.out.println("添加订单出错");
            return false;
        }
        System.out.println("添加订单成功");
        return true;
    }

    /**
     *方法：删除订单
     * @return 删除成功返回真
     */
    public boolean deleteFromOrder(int num){
        sql="DELETE FROM `order` WHERE `orderid`=?";
        boolean insertDate=commodityDao.updateData(sql,num);
        if(!insertDate){
            System.out.println("该订单不存在");
            return false;
        }
        System.out.println("删除订单成功");
        return true;
    }

    /**
     *方法：分页查询订单
     */
    public List<Order> searchFromOrder(int num1,int num2){
        sql="SELECT `orderid`,`commodityid`,`ordertime`,`count`,`orderprice`FROM `order`\n" +
                "LIMIT ?,?";
        return orderDao.queryMuti(sql,Order.class,num1,num2);
    }

    /**
     *方法：按价格升序排序订单
     */
    public List<Order> searchFromOrderByPriceASC(){
        sql="SELECT `orderid`,`commodityid`,`ordertime`,`count`,`orderprice`FROM `order` \n" +
                "ORDER BY `orderprice`ASC";
        return orderDao.queryMuti(sql,Order.class);
    }

    /**
     *方法：按价格降序排序订单
     */
    public List<Order> searchFromOrderByPriceDESC(){
        sql="SELECT `orderid`,`commodityid`,`ordertime`,`count`,`orderprice`FROM `order` \n" +
                "ORDER BY `orderprice`DESC";
        return orderDao.queryMuti(sql,Order.class);
    }

    /**
     *方法：按下单时间升序排序订单
     */
    public List<Order> searchFromOrderByOrdertimeASC(){
        sql="SELECT `orderid`,`commodityid`,`ordertime`,`count`,`orderprice`FROM `order` \n" +
                "ORDER BY `ordertime`ASC";
        return orderDao.queryMuti(sql,Order.class);
    }

    /**
     *方法：按下单时间降序排序订单
     */
    public List<Order> searchFromOrderByOrdertimeDESC(){
        sql="SELECT `orderid`,`commodityid`,`ordertime`,`count`,`orderprice`FROM `order` \n" +
                "ORDER BY `ordertime`DESC";
        return orderDao.queryMuti(sql,Order.class);
    }

    /**
     *方法：修改订单
     * 在修改订单商品的件数同时自动修改下单时间和订单价格
     * @return 修改成功返回真
     */
    public boolean updateFromOrder(int num1,int num2){

        Object preCount= null;
        Object prePrice= null;
        try {
            String sql2="SELECT `count` FROM `order` WHERE `orderid`=?";
            String sql3="SELECT `orderprice` FROM `order` WHERE `orderid`=?";
            preCount = orderDao.queryScalar(sql2,num2);
            prePrice = orderDao.queryScalar(sql3,num2);
        } catch (Exception e) {
            System.out.println("该订单不存在");
            return false;
        }

        double orderprice= (double)prePrice/(int)preCount*num1;

        sql="UPDATE `order` SET `count`=? ,`ordertime`=?,`orderprice`=? WHERE `orderid`=?";
        orderDao.updateData(sql,num1,new Date(),orderprice,num2);


        System.out.println("修改订单商品的件数成功");
        return true;
    }



}
