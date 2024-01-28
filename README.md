# WORK3

# 完成情况

完成了基础任务

# Bonus

实现分页查询

使用Druid连接池

# 项目结构

* domain层 
  * Commodity 商品类
  * Order 订单类

* dao层 

  * BasicDao 

    通用的增删改查方法

  * CommodityDao

    继承BasicDao  完成对commodity表的增删查改

  * OrderDao 

    继承BasicDao 完成对order表的增删查改

* service层

  * CommodityService

    调用CommodityDao和OrderDao完成有关商品的业务要求

  * OrderService

    调用CommodityDao和OrderDao完成有关订单的业务要求

* view层

  * ManagementSystemView

    调用CommodityService和OrderService得到结果和显示数据					

* utils层

  * myUtils

    读入键盘输入字符的工具类

  * utilsByDruid

    使用Druid连接池的工具类		

* ManagementSystemApp

  主方法

