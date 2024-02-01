package managementsystem.view;

import managementsystem.domain.Commodity;
import managementsystem.domain.Order;
import managementsystem.service.CommodityService;
import managementsystem.service.OrderService;
import managementsystem.utils.myUtils;

import java.util.List;

public class ManagementSystemView {


    private boolean flag=true;
    private boolean loop=true;

    private char key=' ';

    CommodityService commodityService=new CommodityService();
    OrderService orderService=new OrderService();

    public void insertOrder(){
        System.out.print("请输入商品号：");
        int id=myUtils.readInt();
        System.out.print("该订单的商品件数为：");
        while(true) {
            int cnt = myUtils.readInt();
            if(cnt>0) {
                orderService.addNewOrder(id,cnt);
                break;
            }
            System.out.print("商品的件数必须大于0，请重新输入：");
        }
    }

    public void deleteOrder(){
        System.out.print("请输入删除订单的订单号：");
        int num=myUtils.readInt();
        orderService.deleteFromOrder(num);
    }

    public void searchOrder(){
        System.out.print("请输入要查询的下标（从第0开始）：");
        int num1;
        while (true) {
            num1 = myUtils.readInt();
            if(num1>=0){
                break;
            }
            System.out.print("查询的下标必须大于等于0，请重新输入：");
        }
        System.out.print("请输入每一页的数据条数：");
        int num2;
        while (true) {
            num2 = myUtils.readInt();
            if(num2>0){
                break;
            }
            System.out.print("每一页的数据条数必须大于0，请重新输入：");
        }
        List<Order> orders=orderService.searchFromOrder(num1,num2);
        for(Order order:orders){
           System.out.println(order.toString());
       }
    }

    public void searchOrderBypriceASC(){
        List<Order> orders=orderService.searchFromOrderByPriceASC();
        for(Order order:orders){
            System.out.println(order.toString());
        }
    }

    public void searchOrderBypriceDESC(){
        List<Order> orders=orderService.searchFromOrderByPriceDESC();
        for(Order order:orders){
            System.out.println(order.toString());
        }
    }

    public void searchOrderByOrdertimeASC(){
        List<Order> orders=orderService.searchFromOrderByOrdertimeASC();
        for(Order order:orders){
            System.out.println(order.toString());
        }
    }

    public void searchOrderByOrdertimeDESC(){
        List<Order> orders=orderService.searchFromOrderByOrdertimeDESC();
        for(Order order:orders){
            System.out.println(order.toString());
        }
    }

    public void updateOrder(){
        System.out.print("请输入修改订单的订单号：");
        int num1=myUtils.readInt();
        System.out.print("该订单的商品件数修改为：");
        while(true) {
            int num2 = myUtils.readInt();
            if(num2>0) {
                orderService.updateFromOrder(num2,num1);
                break;
            }
            System.out.print("商品的件数必须大于0，请重新输入：");
        }
    }

    public void insertCommodity(){
        System.out.print("请输入商品名：");
        String name=myUtils.readString(10);
        System.out.print("请输入商品价格：");
        while(true) {
            double price = myUtils.readDouble();
            if(price>0) {
                commodityService.addNewCommodity(name, price);
                break;
            }
            System.out.print("商品的价格必须大于0，请重新输入：");
        }
    }

    public void deleteCommodity(){
        System.out.print("请输入删除商品的商品号：");
        int num=myUtils.readInt();
        commodityService.deleteFromCommodity(num);
    }

    public void searchCommodity(){
        List<Commodity> commodities=commodityService.searchFromCommodity();
        for(Commodity commodity:commodities){
            System.out.println(commodity.toString());
        }
    }

    public void updateCommodity(){
        System.out.print("请输入修改商品的商品编号：");
        int num1=myUtils.readInt();
        System.out.print("该商品的价格修改为：");
        while(true) {
            double num2 = myUtils.readDouble();
            if(num2>0) {
                commodityService.updateFromCommodity(num2, num1);
                break;
            }
            System.out.print("商品的价格必须大于0，请重新输入：");
        }
    }


    public void exit(){
        char c = myUtils.readConfirmSelection();
        if (c == 'Y'){
            flag = false;
        }
    }

    public void exitMenu(){
        char c = myUtils.readConfirmSelection();
        if (c == 'Y'){
            loop = false;
        }
    }

    public void mianMenu() {
        do{
            System.out.println("\n=========订单管理系统=========");
            System.out.println("\t\t1.进入订单管理子系统");
            System.out.println("\t\t2.进入商品管理子系统");
            System.out.println("\t\t3.退出");
            System.out.print("请输入你的选择（1-3）：");

            key = myUtils.readChar();
            loop=true;

            switch (key){
                case '1':
                    showOrderMenu();
                    break;
                case '2':
                    showCommodityMenu();
                    break;
                case '3':
                    exit();
                    break;
            }

        }while(flag);
    }

public void showOrderMenu(){
    do{
        System.out.println("\n=========订单管理子系统=========");
        System.out.println("\t\t1.新增订单");
        System.out.println("\t\t2.分页查询订单信息");
        System.out.println("\t\t3.删除订单");
        System.out.println("\t\t4.修改订单");
        System.out.println("\t\t5.排序所有订单信息(按价格降序)");
        System.out.println("\t\t6.排序所有订单信息(按价格升序)");
        System.out.println("\t\t7.排序所有订单信息(按下单时间降序)");
        System.out.println("\t\t8.排序所有订单信息(按下单时间升序)");
        System.out.println("\t\t9.返回上一级菜单");
        System.out.print("请输入你的选择（1-9）：");

        key = myUtils.readChar();

        switch (key){
            case '1':
                insertOrder();
                break;
            case '2':
                searchOrder();
                break;
            case '3':
                deleteOrder();
                break;
            case '4':
                updateOrder();
                break;
            case '5':
                searchOrderBypriceDESC();
                break;
            case '6':
                searchOrderBypriceASC();
                break;
            case '7':
                searchOrderByOrdertimeDESC();
                break;
            case '8':
                searchOrderByOrdertimeASC();
                break;
            case '9':
                exitMenu();
                break;
        }

    }while(loop);
}

    public void showCommodityMenu(){
        do{
            System.out.println("\n=========商品管理子系统=========");
            System.out.println("\t\t1.新增商品");
            System.out.println("\t\t2.删除商品");
            System.out.println("\t\t3.输出商品列表");
            System.out.println("\t\t4.修改商品信息");
            System.out.println("\t\t5.返回上一级菜单");
            System.out.print("请输入你的选择（1-5）：");

            key = myUtils.readChar();

            switch (key){
                case '1':
                    insertCommodity();
                    break;
                case '2':
                    deleteCommodity();
                    break;
                case '3':
                    searchCommodity();
                    break;
                case '4':
                    updateCommodity();
                    break;
                case '5':
                    exitMenu();
                    break;
            }

        }while(loop);
    }

}





