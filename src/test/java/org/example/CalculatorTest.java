package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculatorTest {
    @Test
    public void testPerformOperation() {
        Calculator calculator = new Calculator();

        // Test addition
        assertEquals(10.0, calculator.performOperation(5, 5, "+"), 0.0);

        // Test subtraction
        assertEquals(5.0, calculator.performOperation(10, 5, "-"), 0.0);

        // Test multiplication
        assertEquals(50.0, calculator.performOperation(5, 10, "*"), 0.0);

        // Test division
        assertEquals(2.0, calculator.performOperation(10, 5, "/"), 0.0);

        // Test division by zero (expecting NaN)
        assertTrue(Double.isNaN(calculator.performOperation(5, 0, "/")));
    }
}
