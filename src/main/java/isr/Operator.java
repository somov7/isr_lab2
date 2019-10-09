package isr;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class Operator {
    private int precedence;
    private BiFunction<BigDecimal, BigDecimal, BigDecimal> operation;

    public Operator(int precedence, BiFunction<BigDecimal, BigDecimal, BigDecimal> operation) {
        this.precedence = precedence;
        this.operation = operation;
    }

    public int getPrecedence() {
        return precedence;
    }

    public BigDecimal calc(BigDecimal l, BigDecimal r) {
        return operation.apply(l, r);
    }
}
