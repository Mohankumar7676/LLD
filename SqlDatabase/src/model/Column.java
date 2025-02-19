package model;

public class Column {
    public String name;
    String type;
    boolean isRequired;
    int maxLength;
    int minValue;

    public Column(String name, String type, boolean isRequired, int maxLength, int minValue) {
        this.name = name;
        this.type = type;
        this.isRequired = isRequired;
        this.maxLength = maxLength;
        this.minValue = minValue;
    }

}
