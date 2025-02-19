import model.Column;
import model.Row;
import service.Database;
import service.Table;

public class SqlApplication {
    public static void main(String[] args) {

        Database db = new Database();

        db.createTable("students");
        Table students = db.getTable("students");

        Column idColumn = new Column("id", "int", true, 0, 1024);
        Column nameColumn = new Column("name", "string", true, 20, 0);
        students.addColumn(idColumn);
        students.addColumn(nameColumn);

        Row row1 = new Row();
        row1.addColumnValue("id", 1025);
        row1.addColumnValue("name", "John Doe");

        Row row2 = new Row();
        row2.addColumnValue("id", 1030);
        row2.addColumnValue("name", "Jane Smith");

        students.insertRow(row1);
        students.insertRow(row2);

        System.out.println("All records in 'students' table:");
        students.printAllRecords();

        System.out.println("Filtering records with name 'John Doe':");
        students.filterRecords("name", "John Doe");

        db.dropTable("students");
    }
}
