package biz.rageintegro.erealt.reference;


public enum RoomCountType {
    ONE("1"), TWO("2"), THREE("3"), FOUR("4"), MORE_THAN_FOUR(">4");

    private String caption;

    RoomCountType(String label) {
        this.caption = label;
    }

    public String getCaption() {
        return caption;
    }

    public String toString() {
        return caption;
    }
}
