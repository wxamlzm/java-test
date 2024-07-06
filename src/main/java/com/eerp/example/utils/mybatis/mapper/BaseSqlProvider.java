package com.eerp.example.utils.mybatis.mapper;

import com.eerp.example.utils.exception.BaseException;
import com.eerp.example.utils.mybatis.annotation.Condition;
import com.eerp.example.utils.mybatis.builder.SqlBuilderUtils;
import com.eerp.example.utils.mybatis.metadata.MapperMetaData;
import com.eerp.example.utils.mybatis.metadata.MapperMetaDataCollector;
import com.eerp.example.utils.mybatis.metadata.TableMetaData;
import com.eerp.example.utils.mybatis.builder.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.ResolvableType;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Type;
import java.util.*;

@Slf4j
public class BaseSqlProvider {

    private static final String ID_EQ = "id = #{id}";

    private static final String[] INSERT_IGNORE = {"id", "sysTime"};

    private static final String[] UPDATE_IGNORE = {"id", "createdAt", "sysTime"};

    static {
        Arrays.sort(INSERT_IGNORE);
        Arrays.sort(UPDATE_IGNORE);
    }


    public <E> String batchInsertProvider(@Param("entities") Collection<E> entities) {
        Objects.requireNonNull(entities, "insert with entity can not be null");
        E e = entities.iterator().next();
        TableMetaData tableMetaData = getTableMetaData(e.getClass());

        Map<String, String> tableColumnMap = tableMetaData.getTableColumnMap();
        SQL sql = new SQL().INSERT_INTO(tableMetaData.getTableName());

        String[] columns = tableColumnMap.entrySet().stream().filter(x -> Arrays.binarySearch(INSERT_IGNORE, x.getKey()) < 0).map(Map.Entry::getValue).toArray(String[]::new);
        String[] columnKeys = tableColumnMap.entrySet().stream().filter(x -> Arrays.binarySearch(INSERT_IGNORE, x.getKey()) < 0).map(Map.Entry::getKey).toArray(String[]::new);
        sql.INTO_COLUMNS(columns);

        StringBuffer result = new StringBuffer(sql.toString());
        result.append(" VALUES");

        for (int i = 0; i < entities.size(); i++) {
            for (String column : columnKeys) {
                sql.INTO_VALUES();
            }
            int finalI = i;
            result.append(" (");
            result.append(String.join(",", Arrays.stream(columnKeys).map(column -> "#{entities[" + finalI + "]." + column + "}").toArray(String[]::new)));
            result.append(" )");
            if (i != entities.size() - 1) {
                result.append(" ,");
            }
        }
        return result.toString();
    }

    public <E> String insertSelectiveProvider(E entity) {
        Objects.requireNonNull(entity, "insert with entity can not be null");

        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(entity);
        TableMetaData tableMetaData = getTableMetaData(entity.getClass());

        Map<String, String> tableColumnMap = tableMetaData.getTableColumnMap();
        SQL sql = new SQL().INSERT_INTO(tableMetaData.getTableName());
        for (Map.Entry<String, String> entry : tableColumnMap.entrySet()) {
            // 忽略 ID 字段，采用数据库自增 ID
            if (Arrays.binarySearch(INSERT_IGNORE, entry.getKey()) >= 0) {
                continue;
            }
            // Selective 模式，模型值为空则忽略此字段
            Object value = beanWrapper.getPropertyValue(entry.getKey());
            if (value == null) {
                continue;
            }
            sql.VALUES(entry.getValue(), "#{" + entry.getKey() + "}");
        }
        return sql.toString();
    }

    public <E> String updateProvider(E entity) {
        return buildUpdateSql(entity, false);
    }

    public <E> String updateSelectiveProvider(E entity) {
        return buildUpdateSql(entity, true);
    }

    private String buildUpdateSql(Object entity, boolean selective) {
        //
        Assert.notNull(entity, "数据库更新操作参数 entity 不能为空！");
        //
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(entity);
        TableMetaData tableMetaData = getTableMetaData(entity.getClass());

        Map<String, String> tableColumnMap = tableMetaData.getTableColumnMap();

        SQL sql = new SQL().UPDATE(tableMetaData.getTableName());

        for (Map.Entry<String, String> entry : tableColumnMap.entrySet()) {
            if (Arrays.binarySearch(UPDATE_IGNORE, entry.getKey()) >= 0) {
                continue;
            }
            // Selective 模式，模型值为空则忽略此字段
            Object value = beanWrapper.getPropertyValue(entry.getKey());
            if (selective && value == null) {
                continue;
            }
            sql.SET(getEq(entry.getValue(), entry.getKey()));
        }
        return sql.WHERE(ID_EQ).toString();
    }


    public String deleteProvider(ProviderContext providerContext) {
        TableMetaData tableMetaData = getTableMetaData(providerContext);
        return new SQL().DELETE_FROM(tableMetaData.getTableName())
                .WHERE(ID_EQ)
                .toString();
    }

    public String deleteLogicalProvider(ProviderContext providerContext) {
        TableMetaData tableMetaData = getTableMetaData(providerContext);
        return new SQL().UPDATE(tableMetaData.getTableName())
                .SET("del_flag = 1")
                .WHERE(ID_EQ)
                .toString();
    }


    public String deleteLogicalByIdsProvider(ProviderContext providerContext, @Param("ids") Collection<Long> ids) {
        TableMetaData tableMetaData = getTableMetaData(providerContext);

        String inExpression = SqlBuilderUtils.inExpression("ids", ids.size());

        return new SQL().UPDATE(tableMetaData.getTableName())
                .SET("del_flag = 1")
                .WHERE(tableMetaData.getIdColumn() + " in " + inExpression)
                .toString();
    }


    public String selectProvider(ProviderContext providerContext) {
        return baseSelect(providerContext, false)
                .WHERE(ID_EQ)
                .toString();
    }

    public String selectIdsProvider(ProviderContext providerContext, @Param("ids") Collection<Long> ids) {
        SQL sql = baseSelect(providerContext, false);
        TableMetaData tableMetaData = getTableMetaData(providerContext);
        if (CollectionUtils.isEmpty(ids)) {
            return sql.WHERE("1=3").toString();
        }
        if (ids.size() == 1) {
            return sql.WHERE(getEq(tableMetaData.getIdColumn(), "ids[0]")).toString();
        }
        String inExpression = SqlBuilderUtils.inExpression("ids", ids.size());
        return sql.WHERE(tableMetaData.getIdColumn() + " in " + inExpression)
                .toString();
    }

    public String selectByProperty(ProviderContext providerContext, Map<String, Object> args) {
        return propertySql(providerContext, args, false);
    }

    public String countByProperty(ProviderContext providerContext, Map<String, Object> args) {
        return propertySql(providerContext, args, true);
    }

    private String propertySql(ProviderContext providerContext, Map<String, Object> args, Boolean isCount) {
        SQL sql = baseSelect(providerContext, isCount);
        TableMetaData tableMetaData = getTableMetaData(providerContext);

        PropertyFunction propertyFunction = (PropertyFunction) args.get("property");
        String columnName = getColumnName(tableMetaData, propertyFunction);
        Object value = args.get("value");
        if (value instanceof List) {
            List list = (List) value;
            if (CollectionUtils.isEmpty(list)) {
                sql.WHERE("1=2");
            } else if (Objects.equals(1, list.size())) {
                sql.WHERE(getEq(columnName, "value[0]"));
            } else {
                String inExpression = SqlBuilderUtils.inExpression("value", list.size());
                sql.WHERE(columnName + " in " + inExpression);
            }
        } else {
            sql.WHERE(getEq(columnName, "value"));
        }
        return sql.toString();
    }

    public String selectByProperties(ProviderContext providerContext, @Param("ps") P[] ps) {
        return propertiesSql(providerContext, ps, false);
    }

    public String countByProperties(ProviderContext providerContext, @Param("ps") P[] ps) {
        return propertiesSql(providerContext, ps, true);
    }

    public String propertiesSql(ProviderContext providerContext, P[] ps, Boolean isCount) {
        SQL sql = baseSelect(providerContext, isCount);
        TableMetaData tableMetaData = getTableMetaData(providerContext);
        if (ps == null || ps.length == 0) {
            return sql.WHERE("1=2").toString();
        }
        int i = 0;
        for (P p : ps) {
            String columnName = getColumnName(tableMetaData, p.getFunction());
            if (p.getValue() instanceof List) {
                List value = (List) p.getValue();
                if (CollectionUtils.isEmpty(value)) {
                    sql.WHERE("1=2");
                } else if (Objects.equals(1, value.size())) {
                    sql.WHERE(getEq(columnName, "ps[" + i++ + "].value[0]"));
                } else {
                    String inExpression = SqlBuilderUtils.inExpression("ps[" + i++ + "].value", value.size());
                    sql.WHERE(columnName + " in " + inExpression);
                }
            } else {
                sql.WHERE(getEq(columnName, "ps[" + i++ + "].value"));
            }
        }
        return sql.toString();
    }

    private String getColumnName(TableMetaData tableMetaData, PropertyFunction function) {
        String property = SerializedLambdaUtils.getProperty(function);
        String column = tableMetaData.getColumn(property);
        return column == null || column.isEmpty() ? property : tableMetaData.getTableName() + "." + column;
    }

    private SQL baseSelect(ProviderContext providerContext, Boolean isCount) {
        TableMetaData tableMetaData = getTableMetaData(providerContext);
        if (isCount) {
            return new SQL().SELECT("count(*)").FROM(tableMetaData.getTableName());
        }
        String columns = StringUtils.collectionToDelimitedString(tableMetaData.getTableColumnMap().values(), ", ", tableMetaData.getTableName() + ".", "");
        return new SQL().SELECT(columns).FROM(tableMetaData.getTableName());
    }

    private TableMetaData getTableMetaData(ProviderContext providerContext) {
        Class<?> entityClass = getEntityClass(providerContext);
        if (entityClass == null) {
            throw new BaseException("no MapperMetaData for ProviderContext " + providerContext);
        }
        return getTableMetaData(entityClass);
    }

    private TableMetaData getTableMetaData(Class<?> entityClass) {
        MapperMetaData mapperMetaData = MapperMetaDataCollector.forEntityClass(entityClass);
        if (mapperMetaData == null) {
            throw new BaseException("no MapperMetaData for class " + entityClass.getName());
        }
        return mapperMetaData.getTableMetaData();
    }


    @SuppressWarnings("unchecked")
    public String selectListProvider(ProviderContext providerContext, Map<String, Object> args) throws Exception {
        return selectList(providerContext, args);
    }

    private String selectList(ProviderContext providerContext, Map<String, Object> args) {
        SQL sql = baseSelect(providerContext, false);
        TableMetaData metaData = getTableMetaData(providerContext);

        Object t = args.get("condition");
        OrderBy order = args.containsKey("order") ? (OrderBy) args.get("order") : null;
        ReflectionUtils.doWithFields(t.getClass(), field -> {
            Condition logicCondition = field.getAnnotation(Condition.class);
            String mappedProperty = logicCondition == null || StringUtils.isEmpty(logicCondition.property())
                    ? field.getName() : logicCondition.property();
            if (!metaData.hasField(mappedProperty)) {
                return;
            }
            PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(t.getClass(), field.getName());
            if (pd == null || pd.getReadMethod() == null) {
                return;
            }

            Object value = ReflectionUtils.invokeMethod(pd.getReadMethod(), t);
            if (!StringUtils.isEmpty(value)) {
                String mappedColumn = metaData.getColumn(mappedProperty);
                Logic logic = logicCondition == null ? Logic.EQ : logicCondition.logic();

                if (logic == Logic.NULL || logic == Logic.NOT_NULL) {
                    sql.WHERE(mappedColumn + logic.getCode());
                } else if (logic == Logic.IN || logic == Logic.NOT_IN) {
                    if (value instanceof Collection) {
                        sql.WHERE(mappedColumn + logic.getCode() +
                                SqlBuilderUtils.inExpression("condition." + field.getName(), ((Collection) value).size()));
                    }
                } else {
                    sql.WHERE(mappedColumn + logic.getCode() + "#{condition." + field.getName() + "}");
                }
            }
        });

        if (order != null) {
            sql.ORDER_BY(order.build());
        }
        String result = sql.toString();
        log.debug(result);
        return result;
    }

    private Class<?> getEntityClass(ProviderContext providerContext) {
        Class<?> mapperType = providerContext.getMapperType();
        for (Type parent : mapperType.getGenericInterfaces()) {
            ResolvableType parentType = ResolvableType.forType(parent);
            if (parentType.getRawClass() == BaseMapper.class) {
                return parentType.getGeneric(0).getRawClass();
            }
        }
        return null;
    }

    private String getEq(String column, String property) {
        return column + " = #{" + property + "}";
    }


}
