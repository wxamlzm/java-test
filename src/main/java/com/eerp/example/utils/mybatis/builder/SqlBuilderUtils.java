package com.eerp.example.utils.mybatis.builder;

import com.eerp.example.utils.mybatis.metadata.MapperMetaData;
import com.eerp.example.utils.mybatis.metadata.MapperMetaDataCollector;
import com.eerp.example.utils.mybatis.metadata.TableMetaData;
import org.springframework.util.StringUtils;


/**
 * User : liulu
 * Date : 2018/7/11 14:14
 * version $Id: SqlBuilderUtils.java, v 0.1 Exp $
 */
public abstract class SqlBuilderUtils {

    private SqlBuilderUtils() {

    }

    public static String inExpression(String property, int size) {
        StringBuilder sb = new StringBuilder(" (");
        for (int i = 0; i < size; i++) {
            sb.append("#{").append(property).append("[").append(i).append("]").append("}");
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        return sb.append(")").toString();
    }


    public static String baseColumns(Class<?> entityClass) {
        MapperMetaData mapperMetaData = MapperMetaDataCollector.forEntityClass(entityClass);
        if (mapperMetaData == null) {
            return "";
        }
        TableMetaData tableMetaData = mapperMetaData.getTableMetaData();
        return StringUtils.collectionToDelimitedString(tableMetaData.getTableColumnMap().values(), ", ", tableMetaData.getTableName() + ".", "");
    }


}
