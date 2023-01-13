package repeatability;

public enum Repeatability {
    SINGLE("однократная"),
    DAILY("ежедневная"),
    WEEKLY("еженедельная"),
    MONTHLY("ежемесячная"),
    YEARLY("ежегодная");
    private String repeatability;

    Repeatability(String repeatability) {
        this.repeatability = repeatability;
    }

    public String getRepeatability() {
        return repeatability;
    }
}
