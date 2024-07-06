package com.eerp.example.utils.mybatis.mapper;

import com.eerp.example.utils.mybatis.builder.OrderBy;
import com.eerp.example.utils.mybatis.builder.P;
import com.eerp.example.utils.mybatis.builder.PropertyFunction;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


public interface BaseMapper<E> {

    /**
     * 数据库选择性插入操作（实体中字段值为空的会忽略）
     *
     * @param entity entity
     * @return int
     */
    @InsertProvider(type = BaseSqlProvider.class, method = "insertSelectiveProvider")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insertSelective(E entity);

    @InsertProvider(type = BaseSqlProvider.class, method = "batchInsertProvider")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int batchInsert(@Param("entities") Collection<E> entities);

    /**
     * 数据库更新操作
     *
     * @param entity entity
     * @return int
     */
    @UpdateProvider(type = BaseSqlProvider.class, method = "updateProvider")
    int update(E entity);

    /**
     * 数据库选择性更新操作（实体中字段为空的会忽略）
     *
     * @param entity entity
     * @return int
     */
    @UpdateProvider(type = BaseSqlProvider.class, method = "updateSelectiveProvider")
    int updateSelective(E entity);

    /**
     * 数据库删除操作
     *
     * @param id id
     * @return return
     */
    @DeleteProvider(type = BaseSqlProvider.class, method = "deleteProvider")
    int delete(Long id);

    /**
     * 数据库逻辑删除操作（实际是一次更新操作，将 is_deleted 字段设置为 true）
     *
     * @param id id
     * @return int
     */
    @UpdateProvider(type = BaseSqlProvider.class, method = "deleteLogicalProvider")
    int deleteLogical(Long id);

    @UpdateProvider(type = BaseSqlProvider.class, method = "deleteLogicalByIdsProvider")
    int deleteLogicalByIds(@Param("ids") Collection<Long> ids);

    /**
     * 根据主键选择
     *
     * @param id id
     * @return E
     */
    @SelectProvider(type = BaseSqlProvider.class, method = "selectProvider")
    E selectById(Long id);

    @SelectProvider(type = BaseSqlProvider.class, method = "selectIdsProvider")
    List<E> selectByIds(@Param("ids") Collection<Long> ids);

    /**
     * 根据属性查询
     *
     * @param propertyFunction propertyFunction
     * @param value            value
     * @param <R>              R
     * @return return
     */
    @SelectProvider(type = BaseSqlProvider.class, method = "selectByProperty")
    <R> E selectByProperty(@Param("property") PropertyFunction<E, R> propertyFunction, @Param("value") Object value);

    @SelectProvider(type = BaseSqlProvider.class, method = "selectByProperties")
    E selectByProperties(@Param("ps") P<E, Object>... ps);

    @SelectProvider(type = BaseSqlProvider.class, method = "countByProperty")
    <R> int countByProperty(@Param("property") PropertyFunction<E, R> propertyFunction, @Param("value") Object value);

    /**
     * 根据属性查询列表
     *
     * @param propertyFunction
     * @param value
     * @param <R>
     * @return
     */
    @SelectProvider(type = BaseSqlProvider.class, method = "selectByProperty")
    <R> List<E> selectListByProperty(@Param("property") PropertyFunction<E, R> propertyFunction, @Param("value") Object value);

    @SelectProvider(type = BaseSqlProvider.class, method = "selectByProperties")
    List<E> selectListByProperties(@Param("ps") P<E, ? extends Serializable>... ps);

    @SelectProvider(type = BaseSqlProvider.class, method = "countByProperties")
    <R> int countByProperties(@Param("ps") P<E, R>... ps);

    /**
     * 根据条件选择
     *
     * @param condition   condition
     * @param <Condition> Condition
     * @return List<E>
     */
    @SelectProvider(type = BaseSqlProvider.class, method = "selectListProvider")
    <Condition> List<E> selectList(@Param("condition") Condition condition);

    /**
     * 根据条件选择
     *
     * @param condition   condition
     * @param <Condition> Condition
     * @return List<E>
     */
    @SelectProvider(type = BaseSqlProvider.class, method = "selectListProvider")
    <Condition> List<E> selectListWithOrder(@Param("condition") Condition condition, @Param("order") OrderBy order);

}