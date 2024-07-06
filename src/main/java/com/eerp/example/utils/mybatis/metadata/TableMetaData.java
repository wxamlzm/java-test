package com.eerp.example.utils.mybatis.metadata;

import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TableMetaData {

    @Getter
    private String tableName;

    private String tableIdColumn;

    private List<String> tableColumns;
    // Key : entity field - Value : table column
    private Map<String, String> tableColumnMap;

    public TableMetaData(String tableName, String tableIdColumn, List<String> tableColumns, Map<String, String> tableColumnMap) {
        this.tableName = tableName;
        this.tableIdColumn = tableIdColumn;
        this.tableColumns = tableColumns;
        this.tableColumnMap = tableColumnMap;
    }

    public boolean isIdColumn(String column) {
        return tableIdColumn.equalsIgnoreCase(column);
    }

    public String getIdColumn() {
        return tableIdColumn;
    }

    public boolean hasField(String fieldName) {
        return tableColumnMap.containsKey(fieldName);
    }

    public boolean hasColumn(String columnName) {
        return tableColumns.contains(columnName);
    }

    public String getColumn(String fieldName) {
        return tableColumnMap.get(fieldName);
    }

    public List<String> getColumns() {
        return Collections.unmodifiableList(tableColumns);
    }

    public Map<String, String> getTableColumnMap() {
        return Collections.unmodifiableMap(tableColumnMap);
    }
}
