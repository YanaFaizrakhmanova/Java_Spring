package demo_annotation;
import demo_annotation.Starter;
import demo_annotation.SparkPlug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Engine {
    private final Starter starter;
    private final SparkPlug sparkPlug;

    @Autowired
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

