package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model;

public enum IncidentStatus {
    NEW(0), IN_PROGRESS(1), RESOLVED(2), CLOSED(3);
    private int value;

    IncidentStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
