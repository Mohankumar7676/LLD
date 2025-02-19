import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// Row represents a single record in a table
class Row {
    int id;
    String name;
    int age;

    public Row(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("{ id: %d, name: '%s', age: %d }", id, name, age);
    }
}

// Table represents a collection of rows with an auto-incrementing ID
class Table {
    private String name;
    private Map<Integer, Row> indexById; // Index for quick access by ID
    private AtomicInteger autoIncrementId;

    public Table(String name) {
        this.name = name;
        this.indexById = new HashMap<>();
        this.autoIncrementId = new AtomicInteger(1);
    }

    // Insert a new row into the table
    public int insert(String name, int age) {
        int id = autoIncrementId.getAndIncrement();
        Row row = new Row(id, name, age);
        indexById.put(id, row);
        return id;
    }

    // Update a row based on ID
    public boolean update(int id, String name, int age) {
        if (!indexById.containsKey(id)) return false;
        indexById.put(id, new Row(id, name, age));
        return true;
    }

    // Delete a row by ID
    public boolean delete(int id) {
        return indexById.remove(id) != null;
    }

    // Retrieve all rows
    public List<Row> getAllRows() {
        return new ArrayList<>(indexById.values());
    }

    // Retrieve a row by ID
    public Row getById(int id) {
        return indexById.get(id);
    }

    @Override
    public String toString() {
        return "Table: " + name + "\nRows: " + getAllRows();
    }
}

// Database manages multiple tables
class Database {
    private String name;
    private Map<String, Table> tables;

    public Database(String name) {
        this.name = name;
        this.tables = new HashMap<>();
    }

    // Create a new table
    public boolean createTable(String tableName) {
        if (tables.containsKey(tableName)) return false;
        tables.put(tableName, new Table(tableName));
        return true;
    }

    // Get a table by name
    public Table getTable(String tableName) {
        return tables.get(tableName);
    }

    @Override
    public String toString() {
        return "Database: " + name + "\nTables: " + tables.keySet();
    }
}

// DatabaseManager manages multiple databases
class DatabaseManager {
    private Map<String, Database> databases;

    public DatabaseManager() {
        this.databases = new HashMap<>();
    }

    // Create a new database
    public boolean createDatabase(String dbName) {
        if (databases.containsKey(dbName)) return false;
        databases.put(dbName, new Database(dbName));
        return true;
    }

    // Get a database by name
    public Database getDatabase(String dbName) {
        return databases.get(dbName);
    }

    @Override
    public String toString() {
        return "Databases: " + databases.keySet();
    }
}

// Example Usage
public class InMemoryDB {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        // Create Database
        dbManager.createDatabase("testDB");
        Database db = dbManager.getDatabase("testDB");

        // Create Table
        db.createTable("users");
        Table usersTable = db.getTable("users");

        // Insert Rows
        int id1 = usersTable.insert("Alice", 25);
        int id2 = usersTable.insert("Bob", 30);

        // Print Table Data
        System.out.println("After Insert:");
        System.out.println(usersTable);

        // Update a Row
        usersTable.update(id1, "Alice Johnson", 26);
        System.out.println("After Update:");
        System.out.println(usersTable);

        // Delete a Row
        usersTable.delete(id2);
        System.out.println("After Delete:");
        System.out.println(usersTable);
    }
}
