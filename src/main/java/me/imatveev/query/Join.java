package me.imatveev.query;

public enum Join {
    INNER, LEFT, RIGHT;

    @Override
    public String toString() {
        if (this == INNER) {
            return "";
        }

        return super.toString();
    }
}
