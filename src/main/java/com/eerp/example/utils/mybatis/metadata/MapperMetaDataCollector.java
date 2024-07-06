package com.eerp.example.utils.mybatis.metadata;

import com.eerp.example.utils.mybatis.builder.NameUtils;
import com.eerp.example.utils.mybatis.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.ResolvableType;
import org.springframework.jdbc.core.metadata.TableMetaDataContext;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


@Slf4j
public class MapperMetaDataCollector {
    /**
     * Key: mapper interface class
     * Value: MapperMetaData
     */
    private static final Map<Class, MapperMetaData> mapperMetaDataMap1 = new HashMap<>();
    /**
     * Key: entity class
     * Value: MapperMetaData
     */
    private static final Map<Class, MapperMetaData> mapperMetaDataMap2 = new HashMap<>();

    public static MapperMetaData forMapperClass(Class<?> mapperClass) {
        return mapperMetaDataMap1.get(mapperClass);
    }

    public static MapperMetaData forEntityClass(Class<?> entityClass) {
        return mapperMetaDataMap2.get(entityClass);
    }

    public void collectMetaData(DataSource dataSource, Class<?> mapperClass, String catalog, String schema) {
        ResolvableType baseMapperType = getParentMapperType(mapperClass);
        if (baseMapperType == null) {
            return;
        }
        //
        ResolvableType entityType = baseMapperType.getGeneric(0);
        Class<?> entityClass = entityType.getRawClass();
        String tableName = getTableName(entityClass);
        //
        TableMetaDataContext tableMetaDataContext = new TableMetaDataContext();
        tableMetaDataContext.setTableName(tableName);
        tableMetaDataContext.setSchemaName(schema);
        tableMetaDataContext.setCatalogName(catalog);
        tableMetaDataContext.processMetaData(dataSource, new ArrayList<>(), new String[0]);
        //
        TableMetaData tableMetaData = resolveEntityClass(entityClass, tableMetaDataContext);
        EntityMetaData entityMetaData = resolveEntityClass(entityClass, tableMetaData);
        MapperMetaData mapperMetaData = new MapperMetaData(mapperClass, entityClass, entityMetaData, tableMetaData);
        //
        mapperMetaDataMap1.put(mapperClass, mapperMetaData);
        mapperMetaDataMap2.put(entityClass, mapperMetaData);
        //
    }

    private TableMetaData resolveEntityClass(Class<?> entityClass, TableMetaDataContext context) {
        String tableName = getTableName(entityClass);
        AtomicReference<String> idColumnName = new AtomicReference<>();
        Map<String, String> tableColumnMap = new LinkedHashMap<>();
        //
        ReflectionUtils.doWithFields(entityClass, field -> {
            // ignoreField
            if (ignoreField(field)) {
                return;
            }
            //
            String columnName;
            Column column = field.getAnnotation(Column.class);
            if (column != null && StringUtils.hasText(column.name())) {
                columnName = column.name();
            } else {
                columnName = NameUtils.getUnderLineName(field.getName());
            }

            //
            if (!existsTableColumn(context, columnName)) {
                log.warn("数据库表 {} 中不存在列 {}", tableName, columnName);
                return;
            }

            //
            tableColumnMap.put(field.getName(), columnName);
            //
            if (isIdField(field)) {
                //
                if (StringUtils.hasText(idColumnName.get())) {
                    log.warn("实体类存在 {} 主键定义重复", entityClass.getName());
                }
                //
                idColumnName.set(columnName);
            }
        });
        // lower case all table columns
        List<String> tableColumns = new ArrayList<>();
        for (String tableColumn : context.getTableColumns()) {
            tableColumns.add(tableColumn.toLowerCase());
        }
        //
        return new TableMetaData(tableName, idColumnName.get(), tableColumns, tableColumnMap);
    }

    private boolean ignoreField(Field field) {
        Class<?> fieldType = field.getType();
        if (LocalDate.class == fieldType || LocalTime.class == fieldType || LocalDateTime.class == fieldType) {
            return false;
        }
        return field.isAnnotationPresent(Transient.class)
                || !BeanUtils.isSimpleProperty(fieldType)
                || Modifier.isStatic(field.getModifiers());
    }

    private boolean isIdField(Field field) {
        return field.isAnnotationPresent(Id.class) || field.getName().equalsIgnoreCase("id");
    }

    private boolean existsTableColumn(TableMetaDataContext context, String columnName) {
        for (String tableColumn : context.getTableColumns()) {
            // 判断Column的别名是否包含"`"，如果包含，判断的时候需要将两边的"`"去掉
            if (tableColumn.equalsIgnoreCase(columnName.replaceAll("`", ""))) {
                return true;
            }
        }
        return false;
    }

    private String getTableName(Class<?> entityClass) {
        Table table = entityClass.getAnnotation(Table.class);
        if (table != null && StringUtils.hasText(table.name())) {
            return table.name();
        }
        return NameUtils.getUnderLineName(entityClass.getSimpleName());
    }

    private EntityMetaData resolveEntityClass(Class<?> entityClass, TableMetaData tableMetaData) {
        String entityName = getEntityName(entityClass);
        AtomicReference<String> idFiledName = new AtomicReference<>();
        Map<String, Field> entityFieldMap = new LinkedHashMap<>();
        //
        ReflectionUtils.doWithFields(entityClass, field -> {
            if (tableMetaData.hasField(field.getName())) {
                entityFieldMap.put(field.getName(), field);
            }
            //
            if (isIdField(field)) {
                idFiledName.set(field.getName());
            }
        });
        //
        return new EntityMetaData(entityName, idFiledName.get(), entityFieldMap);
    }

    private String getEntityName(Class<?> entityClass) {
        Entity entity = entityClass.getAnnotation(Entity.class);
        if (entity != null && StringUtils.hasText(entity.name())) {
            return entity.name();
        }
        return entityClass.getSimpleName();
    }

    private ResolvableType getParentMapperType(Class mapperClass) {
        for (Type type : mapperClass.getGenericInterfaces()) {
            ResolvableType mapperType = ResolvableType.forType(type);
            if (BaseMapper.class == mapperType.getRawClass()) {
                return mapperType;
            }
        }
        //
        return null;
    }

}
