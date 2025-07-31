package demo_annotation;

import org.springframework.stereotype.Component;

@Component
public class Accumulator {
    @Override
    public String toString() {
        return "Accumulator";
    }
}
