package isr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    void testAddition() {
        assertEquals(new BigDecimal(1 + 1), Calculator.process("1 + 1"));
        assertEquals(new BigDecimal(1 + 1 + 123), Calculator.process("1 + 1 + 123"));
        assertEquals(new BigDecimal("12345678900000").add(new BigDecimal("12345678900000")),
                Calculator.process("12345678900000+12345678900000"));
    }

    @Test
    void testSubtraction() {
        assertEquals(new BigDecimal(1 - 1), Calculator.process("1-1"));
        assertEquals(new BigDecimal(123 - 321 - 333), Calculator.process("123-321-333"));
        assertEquals(new BigDecimal("12345678900000").subtract(new BigDecimal("12345678900000987654321")),
                Calculator.process("12345678900000 - 12345678900000987654321"));
    }

    @Test
    void testMultiplication() {
        assertEquals(new BigDecimal(0), Calculator.process("0*123456"));
        assertEquals(new BigDecimal(123456), Calculator.process("1*123456"));
        assertEquals(new BigDecimal(2 * 2), Calculator.process("2*2"));
        assertEquals(new BigDecimal("12345678900000").multiply(new BigDecimal("12345678900000987654321")),
                Calculator.process("12345678900000 * 12345678900000987654321"));
    }

    @Test
    void testDivision() {
        assertEquals(new BigDecimal(0), Calculator.process("0/1"));
        assertEquals(new BigDecimal(1), Calculator.process("5/5"));
        assertEquals(new BigDecimal(4 / 2), Calculator.process("4/2"));
        assertEquals(new BigDecimal("12345678900000").divide(new BigDecimal("10000000000000")),
                Calculator.process("12345678900000 / 10000000000000"));
    }

    @Test
    void testIntegerDivision() {
        assertEquals(new BigDecimal(5 / 2), Calculator.process("5//2"));
    }

    @Test
    void testMixed() {
        assertEquals(new BigDecimal(3), Calculator.process("1+2*3-4*5/5"));
    }
}