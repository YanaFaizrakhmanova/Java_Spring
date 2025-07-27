package com.example.demo_xml;

public class Suspension {
    private Hinge hinge;
    private Differential differential;

    public Suspension(Hinge hinge, Differential differential) {
        this.hinge = hinge;
        this.differential = differential;
    }

    @Override
    public String toString() {
        return "Suspension" + " " +
                "hinge=" + " " + hinge +
                "differential=" + " " + differential;
    }
}
