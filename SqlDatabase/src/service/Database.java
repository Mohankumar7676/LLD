package service;

import java.util.HashMap;
import java.util.Map;

public class Database {
    Map<String, Table> tables = new HashMap<>();

    public void createTable(String tableName) {
        if (!tables.containsKey(tableName)) {
            tables.put(tableName, new Table(tableName));
        } else {
            System.out.println("Table already exists!");
        }
    }

    public void dropTable(String tableName) {
        if (tables.containsKey(tableName)) {
            tables.remove(tableName);
        } else {
            System.out.println("Table does not exist!");
        }
    }

    public Table getTable(String tableName) {
        return tables.get(tableName);
    }

    public boolean tableExists(String tableName) {
        return tables.containsKey(tableName);
    }
}
