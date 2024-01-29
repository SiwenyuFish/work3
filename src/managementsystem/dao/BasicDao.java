package managementsystem.dao;

import managementsystem.utils.utilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasicDao<T> {

    private QueryRunner queryRunner=new QueryRunner();

    /**
     *对表进行增删改，添加了事务处理
     * @return 受影响的记录条数
     */
    public boolean updateData(String sql,Object...parameters){

        int update=0;
        Connection connection=null;
        try {
            connection = utilsByDruid.getConnection();

            //关闭自动提交
            connection.setAutoCommit(false);

            update = queryRunner.update(connection, sql, parameters);

            //提交
            connection.commit();
        } catch (SQLException e) {
            try {
                //执行出现异常则回滚
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }finally {
            utilsByDruid.close(null,null,connection);
        }
        return update>0;
    }

    /**
     *方法：查询多条数据
     * @param sql 有？防止sql注入
     * @param tClass 传入一个类的class对象
     * @param parameters 传入？上的具体的值
     * @return 返回的是ArrayList集合
     */
    public List<T>queryMuti(String sql,Class<T> tClass,Object...parameters){

        Connection connection=null;

        try {
            connection=utilsByDruid.getConnection();
            return queryRunner.query(connection,sql,new BeanListHandler<T>(tClass),parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            utilsByDruid.close(null,null,connection);
        }

    }

    /**
     * 方法：查询单条数据
     * @return 返回的是单条对象
     */
    public T querySingle(String sql,Class<T>tClass,Object...parameters){
        Connection connection=null;
        try {
            connection=utilsByDruid.getConnection();
            return queryRunner.query(connection,sql,new BeanHandler<T>(tClass),parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            utilsByDruid.close(null,null,connection);
        }
    }

    /**
     *方法：查询单列数据
     * @return 某个列的值
     */
    public Object queryScalar(String sql,Object...parameters){
        Connection connection=null;
        try {
            connection=utilsByDruid.getConnection();
            return queryRunner.query(connection,sql,new ScalarHandler(),parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            utilsByDruid.close(null,null,connection);
        }
    }

}


