package com.dareit;

public enum DatabaseSelection {
    MySQL("M"), HSQLDB("H");

    public final String label;


    DatabaseSelection(String label) {
        this.label = label;
    }

    public static DatabaseSelection valueOfLabel(String label) {
        for (DatabaseSelection e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
