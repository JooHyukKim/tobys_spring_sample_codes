package user.springbook.domain;

public enum Level {
    BASIC(1, 2),
    SILVER(2, 3),
    GOLD(3, null);

    private final Integer value;
    private final Integer next;

    Level(Integer value, Integer next) {
        this.value = value;
        this.next = next;
    }

    public Integer intValue() {
        return value;
    }

    public Level nextLevel() {
        if (this.next == null) return null;
        return valueOf(this.next);
    }

    public static Level valueOf(Integer value) {
        switch (value) {
            case 1:
                return BASIC;
            case 2:
                return SILVER;
            case 3:
                return GOLD;
            default:
                throw new AssertionError("Unknown value : " + value);
        }
    }
}
