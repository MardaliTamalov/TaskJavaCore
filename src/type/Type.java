package type;

public enum Type {
    PERSONAL("Личная"),
    WORK("Рабочая");
    private String type;
    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
