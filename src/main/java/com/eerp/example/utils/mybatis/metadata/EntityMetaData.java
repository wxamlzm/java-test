package com.eerp.example.utils.mybatis.metadata;

import lombok.Getter;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Entity Meta Data
 *
 * @author jiutou
 * @version 1.2.0
 */
public class EntityMetaData {

    @Getter
    private String entityName;

    private String entityIdField;

    private Map<String, Field> entityFieldMap;

    public EntityMetaData(String entityName, String entityIdField, Map<String, Field> entityFieldMap) {
        this.entityName = entityName;
        this.entityIdField = entityIdField;
        this.entityFieldMap = entityFieldMap;
    }

    public boolean isIdField(String field) {
        return entityIdField.equalsIgnoreCase(field);
    }

    public String getIdField() {
        return entityIdField;
    }

    public boolean hasField(String fieldName) {
        return entityFieldMap.containsKey(fieldName);
    }

    public Set<String> getFields() {
        return Collections.unmodifiableSet(entityFieldMap.keySet());
    }

}
