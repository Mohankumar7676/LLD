package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Column;
import model.Row;

public class Table {
    String name;
    Map<String, Column> columns = new HashMap<>();
    List<Row> rows = new ArrayList<>();

    public Table(String name) {
        this.name = name;
    }

    public void addColumn(Column column) {
        columns.put(column.name, column);
    }

    public void insertRow(Row row) {
        rows.add(row);
    }

    public void printAllRecords() {
        for (Row row : rows) {
            for (String columnName : columns.keySet()) {
                System.out.print(row.getColumnValue(columnName) + "\t");
            }
            System.out.println();
        }
    }

    public void filterRecords(String columnName, Object value) {
        for (Row row : rows) {
            if (row.getColumnValue(columnName).equals(value)) {
                for (String colName : columns.keySet()) {
                    System.out.print(row.getColumnValue(colName) + "\t");
                }
                System.out.println();
            }
        }
    }

    public String getName() {
        return name;
    }
}
