package com.eerp.example.utils.mybatis.metadata;

import lombok.Getter;

/**
 * Mapper Meta Data
 *
 * @author jiutou
 * @version 1.2.0
 */
public class MapperMetaData {

    @Getter
    private Class<?> mapperClass;
    @Getter
    private Class<?> entityClass;
    @Getter
    private EntityMetaData entityMetaData;
    @Getter
    private TableMetaData tableMetaData;

    public MapperMetaData(Class<?> mapperClass, Class<?> entityClass, EntityMetaData entityMetaData, TableMetaData tableMetaData) {
        this.mapperClass = mapperClass;
        this.entityClass = entityClass;
        this.entityMetaData = entityMetaData;
        this.tableMetaData = tableMetaData;
    }

}
