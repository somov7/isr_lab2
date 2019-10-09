package isr;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class Calculator {

    private static Map<String, Operator> operatorMap = Map.of(
        "+", new Operator(0, (l, r) -> l.add(r)),
        "-", new Operator(0, (l, r) -> l.subtract(r)),
        "*", new Operator(1, (l, r) -> l.multiply(r)),
        "/", new Operator(1, (l, r) -> l.divide(r)),
        "%", new Operator(1, (l, r) -> l.remainder(r)),
        "//", new Operator(1, (l, r) -> l.divideToIntegralValue(r))
    );

    private static final int MAX_PRECEDENCE = 1;

    /**
     * Parse and calculate the expression
     * 
     * @param expression string expression
     * @return BigDecimal representing the result
     * @throws RuntimeException if operator not found or if algorithm error occured
     */
    public static BigDecimal process(String expression) {
        expression = expression.replaceAll("\\s", "");

        // System.out.println("expression = " + expression);

        LinkedList<Operator> operators = new LinkedList<>();
        LinkedList<BigDecimal> operands = new LinkedList<>();

        String[] operatorsStr = expression.split("\\d+");
        String[] operandsStr = expression.split("\\D+");
        // System.out.println("operatorsStr = " + Arrays.toString(operatorsStr));
        // System.out.println("operandsStr = " + Arrays.toString(operandsStr));

        for (String operatorStr : operatorsStr) {
            if (operatorStr.isBlank()) {
                continue;
            }
            Operator op = operatorMap.get(operatorStr);
            if (op == null) {
                throw new RuntimeException("Operator not found: \"" + operatorStr + "\"");
            }
            operators.add(op);
        }

        for (String operandStr : operandsStr) {
            operands.add(new BigDecimal(operandStr));
        }

        // System.out.println("operators = " + operators);
        // System.out.println("operands = " + operands);

        int curPrecedence = MAX_PRECEDENCE;
        while (true) {
            ListIterator<Operator> opIt = operators.listIterator();
            ListIterator<BigDecimal> oprIt = operands.listIterator();

            while (opIt.hasNext()) {
                Operator op = opIt.next();
                BigDecimal opr = oprIt.next();

                if (op.getPrecedence() == curPrecedence) {

                    BigDecimal l = opr;
                    oprIt.remove();

                    BigDecimal r = oprIt.next();
                    oprIt.remove();

                    BigDecimal ans = op.calc(l, r);
                    oprIt.add(ans);
                    oprIt.previous();

                    opIt.remove();
                }
            }

            if (curPrecedence == 0) {
                break;
            }
            curPrecedence--;
        }

        if (operands.size() != 1) {
            throw new RuntimeException("Algorithm error: " + expression);
        }

        return operands.getFirst();
    }

    /**
     * Return operators available for use inside expressions
     * 
     * @return Set of strings
     */
    public static Set<String> getAvalableOperators() {
        return operatorMap.keySet();
    }
}
