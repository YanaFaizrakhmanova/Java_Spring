package demo_annotation;

import org.springframework.stereotype.Component;

@Component
public class SparkPlug {
    @Override
    public String toString() {
        return "SparkPlug";
    }
}
