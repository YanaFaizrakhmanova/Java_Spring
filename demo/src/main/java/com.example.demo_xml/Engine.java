package com.example.demo_xml;

public class Engine {
    private Starter starter;
    private SparkPlug sparkPlug;

    public Engine(Starter starter, SparkPlug sparkPlug) {
        this.starter = starter;
        this.sparkPlug = sparkPlug;
    }

    @Override
    public String toString() {
        return "Engine" + " " +
                "starter=" + " " + starter + " " +
        "sparkPlug=" + " " + sparkPlug;
        };
}

