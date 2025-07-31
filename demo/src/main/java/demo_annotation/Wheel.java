package demo_annotation;

import org.springframework.stereotype.Component;

@Component
public class Wheel {
    @Override
    public String toString() {
        return "Wheel";
    }
}
