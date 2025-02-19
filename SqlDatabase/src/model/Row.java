package model;

import java.util.HashMap;
import java.util.Map;

public class Row {
    Map<String, Object> data = new HashMap<>();

    public void addColumnValue(String columnName, Object value) {
        data.put(columnName, value);
    }

    public Object getColumnValue(String columnName) {
        return data.get(columnName);
    }

}

