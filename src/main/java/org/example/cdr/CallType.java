package org.example.cdr;

public enum CallType {
    INCOMING("02"),
    OUTGOING("01");

    private final String value;

    CallType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CallType fromValue(String value) {
        for (CallType type : values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return null;
    }
}
