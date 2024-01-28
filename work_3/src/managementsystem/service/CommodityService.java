package managementsystem.service;

import managementsystem.dao.CommodityDao;
import managementsystem.dao.OrderDao;
import managementsystem.domain.Commodity;

import java.util.Date;
import java.util.List;

public class CommodityService {
    private CommodityDao commodityDao=new CommodityDao();
    private OrderDao orderDao=new OrderDao();
    private String sql;

    /**
     * 方法：添加新的商品
     * @return 添加成功返回真
     */
    public boolean addNewCommodity(String name,double price){
        sql="INSERT INTO commodity (`name`,`price`) VALUES(?,?)";
        boolean insertDate=commodityDao.updateData(sql,name,price);
        if(!insertDate){
            System.out.println("添加商品出错");
            return false;
        }
        System.out.println("添加商品成功");
        return true;
    }

    /**
     * 方法：删除商品
     * 若要删除的商品在订单表中存在则不允许删除
     * @return 删除成功返回真
     */
    public boolean deleteFromCommodity(int commodityid) {

        sql = "DELETE FROM `commodity` WHERE `commodityid`=?";
        String sql2 = "SELECT `orderid` FROM `order` WHERE `commodityid`=?";
        boolean flag = false;
        Object orderid = null;

        orderid = orderDao.queryScalar(sql2, commodityid);

        if (orderid != null) {
            System.out.println("订单表中订单号为" + orderid + "的订单有商品号为" + commodityid + "的商品，不能删除");
            return false;
        }

        boolean insertDate = commodityDao.updateData(sql, commodityid);
        if (!insertDate) {
            System.out.println("该商品不存在");
            return false;
        }
        System.out.println("删除商品成功");
        return true;

    }

    /**
     * 方法：输出所有商品信息
     * @return List
     */
    public List<Commodity> searchFromCommodity(){
        sql="SELECT `commodityid`,`name`,`price`FROM `commodity`";
        return commodityDao.queryMuti(sql,Commodity.class);

    }

    /**
     * 方法：修改商品
     * @return 修改成功返回真
     */
    public boolean updateFromCommodity(double num1,int num2){
        sql="UPDATE `commodity` SET `price`=? WHERE `commodityid`=?";
        boolean insertDate=commodityDao.updateData(sql,num1,num2);
        if(!insertDate){
            System.out.println("该商品不存在");
            return false;
        }
        System.out.println("修改商品价格成功");
        return true;
    }



}
